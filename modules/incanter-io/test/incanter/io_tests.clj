
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
        (incanter core io))
  (:require [clojure.core.matrix :as m]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.io.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;(def incanter-home (System/getProperty "incanter.home"))
(def incanter-home "../../")

;; read in a dataset from a space delimited file
(def test-data (read-dataset
                 (str incanter-home "data/cars.dat")
                 :delim \space
                 :header true)) ; default delimiter: \,
;; read in a dataset from a comma delimited file
(def test-csv-data (read-dataset
                     (str incanter-home "data/cars.csv")
                     :header true))
;; read in a dataset from a tab delimited file
(def test-tdd-data (read-dataset
                     (str incanter-home "data/cars.tdd")
                     :header true
                     :delim \tab))
;; read in the iris dataset from a space delimited file
(def iris-data (read-dataset
                 (str incanter-home "data/iris.dat")
                 :delim \space
                 :header true))
;; read in the social science survey dataset from a space delimited file
(def ols-data (to-matrix (read-dataset
                           (str incanter-home "data/olsexamp.dat")
                           :delim \space
                           :header true)))
;; read-write-read roundtrip to test write functionality, using cars data
(def test-roundtrip-data
  (let [ids (read-dataset (str incanter-home "data/cars.csv")
                      :header true)
        fpath (.getAbsolutePath (java.io.File/createTempFile "tmpcars" "csv"))]
      (do (save ids fpath)
              (read-dataset fpath :header true))))

(defn io-validation [m1 m2 m3 m4 m5 m6]
  ;; validate matrices read from files
  (is (m/equals (m/esum (sel m1 :cols 0)) 770))
  (is (m/equals (m/esum (sel m1 :cols 1)) 2149))
  (is (m/equals (m/esum (sel m2 :cols 0)) 770))
  (is (m/equals (m/esum (sel m2 :cols 1)) 2149))
  (is (m/equals (m/esum (sel m3 :cols 0)) 770))
  (is (m/equals (m/esum (sel m3 :cols 1)) 2149))
  (is (m/equals (m/esum (sel m6 :cols 0)) 770))
  (is (m/equals (m/esum (sel m6 :cols 1)) 2149))
  ;; confirm that iris species factor was converted to two dummy variables
  (is (m/equals (sel m4 :rows 0) (matrix [5.10 3.50 1.40 0.20 0])))
  (is (m/equals (m/get-row m5 0) (matrix [5.10 3.50 1.40 0.20 0 0]))))
;; end of io-validation tests

(deftest read-dataset-validation
  (doseq [[name cars-dataset]
	  [["dat" test-data]
	   ["csv" test-csv-data]
           ["rtrip" test-roundtrip-data]
	   ["tdd" test-tdd-data]]]
    (is (= [:speed :dist] (:column-names cars-dataset)) (str "Reading column names for " name " failed"))
    (is (= 50 (count (m/rows cars-dataset))) (str "Reading rows for " name " failed")))) ;; end of read-dataset-validation tests

(def types {Long ["foo" "bar"], Float "boo"})
(def rtypes {"foo" Long, "bar" Long, "boo" Float})
(def column-names ["foo" "bar" "boo" "baz"])
(def row ["1" "2" "3" "4"])
(deftest make-typed-parse-string-validation
  (testing "Testing parsing to correct types"
    (is (= [1 2 3.0 4] (let [parse-row (#'incanter.io/make-typed-parse-row column-names types Long nil {})]
                       (parse-row row))))
    (is (= (#'incanter.io/reverse-type-mapping [Long "foo"]) {"foo" Long}))
    (is (= (#'incanter.io/reverse-type-mapping [Long ["foo" "bar"]])
           {"foo" Long, "bar" Long}))
    (is (= (#'incanter.io/reverse-types types) rtypes))
    ))

(deftest read-dataset-rename-column-specify-types-validation
  (testing "Testing reading dataset renaming a column and specifying a type."
    (is (= (let [dataset (read-dataset
                           (str incanter-home "data/cars.csv")
                           :header true
                           :rename-columns {"dist" "distance"}
                           :column-types {Float "distance"})]
             [(:column-names dataset) (take 2 (sel dataset :cols :distance))] )
           ['(:speed :distance) '(2.0 10.0)]))
    )) 

(def parse-string (ns-resolve 'incanter.io 'parse-string))
(deftest parse-string-validation
  
  (testing "Parsing string values into numbers"
    (is (= 1234 (parse-string "1234")))
    (is (instance? java.lang.Long (parse-string "5678")))
    (is (instance? java.lang.Long (parse-string "1330418008377")))
    ))

(deftest missing-fields-validation 
  (testing "Proper handling of missing fields."
    (let [ds (read-dataset (str incanter-home "data/missing_values.csv")
                           :header true
                           :empty-field-value :NA)]
      (is (= ($ 6 ds) [4 16 :NA 40 35 :NA])))))


(deftest compliance-test
  (doseq [impl [:clatrix :ndarray :persistent-vector :vectorz]]
    (set-current-implementation impl)
    (println (str "compliance test " impl))
    (let [test-mat (to-matrix test-data)
          test-csv-mat (to-matrix test-csv-data)
          test-rtrip-mat (to-matrix test-roundtrip-data)
          test-tdd-mat (to-matrix test-tdd-data)
          iris-mat (to-matrix iris-data)
          iris-mat-dummies (to-matrix iris-data :dummies true)]
      (io-validation test-mat test-csv-mat test-tdd-mat
                     iris-mat iris-mat-dummies test-rtrip-mat))))
