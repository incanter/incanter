
;;; test-cases.clj -- Unit tests of Incanter functions 

;; by David Edgar Liebke http://incanter.org
;; March 12, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 12, 2009: First version

;; to run these tests:
;; (use 'tests test-cases)
;;  need to load this file to define data variables
;; (use 'clojure.contrib.test-is) 
;; then run tests
;; (run-tests 'incanter.tests.test-cases)

(ns incanter.io-tests
  (:use clojure.test 
        (incanter core io)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.io.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;(def incanter-home (System/getProperty "incanter.home"))
(def incanter-home "../../")

;; read in a dataset from a space delimited file 
(def test-data (read-dataset 
                 (str incanter-home "data/cars.dat") 
                 :delim \space
                 :row-fn #(map parse-string (filter seq  %))
                 :header true)) ; default delimiter: \,
;; read in a dataset from a comma delimited file 
(def test-csv-data (read-dataset 
                     (str incanter-home "data/cars.csv") 
                     :row-fn #(map parse-string %)
                     :header true))
;; read in a dataset from a tab delimited file 
(def test-tdd-data (read-dataset 
                     (str incanter-home "data/cars.tdd") 
                     :header true 
                     :row-fn #(map parse-string %)
                     :delim \tab)) 
;; read in the iris dataset from a space delimited file 
(def iris-data (read-dataset 
                 (str incanter-home "data/iris.dat") 
                 :delim \space
                 :row-fn #(map parse-string %)
                 :header true))
;; read in the social science survey dataset from a space delimited file 
(def ols-data (to-matrix (read-dataset 
                           (str incanter-home "data/olsexamp.dat")
                           :delim \space
                           :header true)))

;; convert the space-delimited dataset into a matrix
(def test-mat (to-matrix test-data))
;; convert the csv dataset into a matrix
(def test-csv-mat (to-matrix test-csv-data)) 
;; convert the tab-delimited dataset into a matrix
(def test-tdd-mat (to-matrix test-tdd-data))
;; convert the iris-data into a matrix, encoding strings into multiple dummy variables
(def iris-mat (to-matrix iris-data))
(def iris-mat-dummies (to-matrix iris-data :dummies true))


(deftest io-validation
  ;; validate matrices read from files
  (is (= (reduce plus test-mat) (matrix [770 2149] 2)))
  (is (= (reduce plus test-csv-mat) (matrix [770 2149] 2)))
  (is (= (reduce plus test-tdd-mat) (matrix [770 2149] 2)))
  ;; confirm that iris species factor was converted to two dummy variables
  (is (= (first iris-mat) (matrix [5.10 3.50 1.40 0.20 0] 5)))
  (is (= (first iris-mat-dummies) (matrix [5.10 3.50 1.40 0.20 0 0] 6)))) ;; end of io-validation tests

(deftest read-dataset-validation
  (doseq [[name cars-dataset]
	  [["dat" test-data]
	   ["csv" test-csv-data]
	   ["tdd" test-tdd-data]]]
    (is (= [:speed :dist] (:column-names cars-dataset)) (str "Reading column names for " name " failed"))
    (is (= 50 (count (:rows cars-dataset))) (str "Reading rows for " name " failed")))) ;; end of read-dataset-validation tests


(defn default-col-names
  "Returns the default column names for a dataset with n columns."
  [n]
  (col-names (to-dataset (range n) :transpose true)))

(deftest default-column-names
  (testing "Default column names should match core/to-dataset"
    (is (= (col-names (read-dataset (java.io.StringReader. "11,12,13\n21,22,23")))
           (default-col-names 3)))))

(defn data
  "Returns string s as a StringReader which can be read by read-dataset"
  [s]
  (java.io.StringReader. s))

(with-test
  (defn cars-ds
    [& options]
    (apply read-dataset (str incanter-home "data/cars.csv") options))
  (testing "header options"
    (is (= (col-names (cars-ds :header true)) [:speed :dist])
        "Default header function should be keyword.")
    (is (= (col-names (cars-ds)) (default-col-names 2))
        "No header given, column names should be :col0 :col1 ....")
    (is (= (col-names (cars-ds :header true
                               :header-fn (comp keyword clojure.string/upper-case)))
           [:SPEED :DIST])
        "Header function should be used.")
    (is (= (col-names (cars-ds :header-fn identity)) (default-col-names 2))
        "Header function should not be used."))

  (testing "options"
    (testing "compress delimeters"
      (let [ds (fn [& options]
                 (apply read-dataset (data "a b c\nd e  f\n") options))]
        (is (= (ds :delim \space :row-fn #(filter seq %))
               (to-dataset [["a" "b" "c"] ["d" "e" "f"]]))
            "compress delimeters")
        (is (= (ds :delim \space :compress-delim false)
               (to-dataset [["a" "b" "c"] ["d" "e" "" "f"]]))
            "Should not compress delimiters")))

    (testing "empty field values"
      (is (= (read-dataset (data "11,,13\n21,22,23")
                           :row-fn (fn [row] (map #(parse-string % :NA) row)))
             (to-dataset [[11 :NA 13][21 22 23]]))
          "Empty values should be :NA")
      (is (= (read-dataset (data "11,,13\n21,22,23")
                           :row-fn (fn [row] (map #(if (seq %) %) row)))
             (to-dataset [["11" nil "13"]["21" "22" "23"]]))
          "Empty values converted to nil")
      (is (= (read-dataset (data "11, ,13\n21,22,23"))
             (to-dataset [["11" " " "13"]["21" "22" "23"]]))
          "Blank strings (\" \") not converted to nil")
      (is (= (read-dataset (data "11, ,13\n21,22,23")
                           :row-fn (fn [row] (map #(when-let [s (seq %)] %) row)))
             (to-dataset [["11" " " "13"]["21" "22" "23"]]))
          "Blank strings (\" \") converted to nil"))

      (testing "should :empty-field-value option always pad rows? "
        (is (= (read-dataset (data "11,,13\n21,22,,24")
                             :row-fn (fn [row] (map #(parse-string % :NA) row)))
               (to-dataset [[11 :NA 13][21 22 :NA 24]]))
            "Should behave same as to-dataset when different row lengths. Empty values :NA")
        (is (= (read-dataset (data "11,,13\n21,22,,24")
                             :row-fn #(map parse-string %))
               (to-dataset [[11 nil 13][21 22 nil 24]]))
            "Should behave same as to-dataset when different row lengths. Empty values nil")
        (is (= (read-dataset (data "11,,13\n21,22,,24"))
               (to-dataset [["11" "" "13"]["21" "22" "" "24"]]))
            "Should behave same as to-dataset when different row lengths. Empty values not converted"))

    (testing "skip empty lines"
      (is (= (read-dataset (data "1,2,3\n\n7,8,9"))
             (to-dataset [["1" "2" "3"] ["7" "8" "9"]]))
          "Should skip blank lines.")
      (is (= (read-dataset (data "1,2,3\n,,\n7,8,9"))
             (to-dataset [["1" "2" "3"] ["7" "8" "9"]]))
          "Should skip lines with only empty values.")
      (is (= (read-dataset (data "1,2,3\n, ,\n7,8,9"))
             (to-dataset [["1" "2" "3"] ["7" "8" "9"]]))
          "Should skip lines with only empty values. blank (\" \") values considered empty.")
      (is (= (read-dataset (data "1,2,3\n,,,\n7,8,9"))
             (to-dataset [["1" "2" "3"] ["7" "8" "9"]]))
          "Should skip lines with only empty values even if it contains extra fields.")
      (is (= (read-dataset (data "1,2,3\n,\n7,8,9"))
             (to-dataset [["1" "2" "3"] ["7" "8" "9"]]))
          "Should skip lines with only empty values even if it contains less fields.")
      (is (= (read-dataset (data "1,2,3\n\n7,8,9")
                           :skip-blank-lines false)
             (to-dataset [["1" "2" "3"] [""] ["7" "8" "9"]]))
          "Should not skip blank lines.")
      (is (= (read-dataset (data "1,2,3\n, ,\n7,8,9")
                           :skip-blank-lines false)
             (to-dataset [["1" "2" "3"] ["" " " ""] ["7" "8" "9"]]))
          "Should not skip blank lines with only empty values."))

    (testing "skip option"
      (is (= (read-dataset (data "11,12,13\n21,22,23\n31,32,33")
                           :dataset-fn (partial drop 1))
             (to-dataset [["21" "22" "23"] ["31" "32" "33"]]))
          "Should skip first line.")
      (is (= (read-dataset (data "11,12,13\n21,22,23\n31,32,33")
                           :header true
                           :dataset-fn (partial drop 1))
             (dataset [:11 :12 :13] [["31" "32" "33"]]))
          "Should skip first line after header."))

    (testing "parsing numbers"
      (is (= (read-dataset (data "001,010,011,100,0100"))
             (to-dataset ["001" "010" "011" "100" "0100"] :transpose true))
          "Categorical data should not be converted to number.")
      (is (= (read-dataset (data "1,2,3\n4,5,6")
                           :row-fn #(update-in % [2] read-string))
             (to-dataset [["1" "2" 3] ["4" "5" 6]]))
          "Third column should be converted to number.")
      (is (thrown-with-msg? RuntimeException #"EOF while reading"
                            (read-dataset (data "1,2,3\n4,5,")
                                          :row-fn #(update-in % [2] read-string)))
          "Should throw exception. read-string can't read empty string."))))

(deftest missing-values-handling
  (testing "Non string"
    (are [x] (= nil (missing-value? x))
         :NA
         :1
         'symbol
         '(1 2)
         []
         pos?))
  (testing "nil"
    (is (= true (missing-value? nil))))
  (testing "Strings"
    (are [x] (= true (missing-value? x))
         ""
         "   "
         "\n")))

(deftest example-skipping-commented-lines
  (testing "skip commented lines"
    (let [comment? (fn [s v] (.startsWith (first v) s))]
      (is (= (read-dataset (data "#a comment\n1,2,3\n#another comment\n4,5,6")
                           :dataset-fn #(remove (partial comment? "#") %))
                           (to-dataset [["1" "2" "3"] ["4" "5" "6"]]))
             "Lines beginning with '#' should be skipped"))))

(deftest parse-string-validation

  (testing "Parsing string values into numbers"
    (is (= 1234 (parse-string "1234")))
    (is (instance? java.lang.Long (parse-string "5678")))
    (is (instance? java.lang.Long (parse-string "1330418008377")))
    ))

(deftest missing-fields-validation
  (testing "Proper handling of missing fields. Pad vectors and convert empty fields to :NA"
    ;; This test shows how to handle complicated scenarios.
    ;; The row function converts the sixth field into a number if possible,
    ;; if field is empty it gets converted to nil. Then all nil values are converted to :NA.
    ;; The dataset function gets the maximum number of fields for all rows in the data,
    ;; and adds :NA to fill out rows with less fields.
    (let [numeric-field (fn [v index] (if-let [val (get v index)]
                                        (assoc v index (when (seq val)
                                                         (read-string val)))
                                        v))
          nil->na (fn [row] (map #(if (nil? %) :NA %) row))
          pad-rows (fn [ds] (let [cols (apply max (map count ds))
                                  pad #(concat % (repeat (- cols (count %))
                                                         :NA))]
                              (map pad ds)))
          row-fn (fn [row] (-> row
                               (numeric-field 6)
                               nil->na))
          ds (read-dataset (str incanter-home "data/missing_values.csv")
                           :header true
                           :row-fn row-fn
                           :dataset-fn pad-rows)]
      (is (= ($ 6 ds) [4 16 :NA 40 35 :NA])))))
