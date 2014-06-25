
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


(defn io-validation [m1 m2 m3 m4 m5]
  ;; validate matrices read from files
  (is (m/equals (m/esum (sel m1 :cols 0)) 770))
  (is (m/equals (m/esum (sel m1 :cols 1)) 2149))
  (is (m/equals (m/esum (sel m2 :cols 0)) 770))
  (is (m/equals (m/esum (sel m2 :cols 1)) 2149))
  (is (m/equals (m/esum (sel m3 :cols 0)) 770))
  (is (m/equals (m/esum (sel m3 :cols 1)) 2149))
  ;; confirm that iris species factor was converted to two dummy variables
  (is (m/equals (sel m4 :rows 0) (matrix [5.10 3.50 1.40 0.20 0])))
  (is (m/equals (m/get-row m5 0) (matrix [5.10 3.50 1.40 0.20 0 0]))))
;; end of io-validation tests

(deftest read-dataset-validation
  (doseq [[name cars-dataset]
	  [["dat" test-data]
	   ["csv" test-csv-data]
	   ["tdd" test-tdd-data]]]
    (is (= [:speed :dist] (:column-names cars-dataset)) (str "Reading column names for " name " failed"))
    (is (= 50 (count (:rows cars-dataset))) (str "Reading rows for " name " failed")))) ;; end of read-dataset-validation tests

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
          test-tdd-mat (to-matrix test-tdd-data)
          iris-mat (to-matrix iris-data)
          iris-mat-dummies (to-matrix iris-data :dummies true)]
      (io-validation test-mat test-csv-mat test-tdd-mat
                     iris-mat iris-mat-dummies))))
