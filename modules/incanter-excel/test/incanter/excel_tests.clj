(ns incanter.excel-tests
  (:use [clojure.test :only[deftest is]]
        [incanter.core :only [dataset dataset?]]
        [incanter.excel :only [save-xls read-xls]])
  (:import java.lang.Math
           java.util.Date
           java.io.File))

(deftest xls-roundtrip
 (let [ffile (File/createTempFile "excel-test" ".xls")
       fname (. ffile getAbsolutePath)]
  (try
     (let [cols ["Ints" "Doubles" "Strings" "Dates"]
           dset (dataset
            cols
            [
             [1 (Math/sqrt 2) "One"   (Date.)]
             [2 Math/E        "Two"   (Date.)]
             [3 Math/PI       "Three" (Date.)]])
           result (do (save-xls dset fname) (read-xls fname))]
        (do
          (is (dataset? result))
          (is (= cols (:column-names result)))))
     (finally (. ffile delete)))))

(deftest headers
  (let [ffile (File/createTempFile "excel-test" ".xls")
        fname (. ffile getAbsolutePath)]
    (try
      (let [dset (dataset [:a :b :c]
                          [[1 2 3]])
            result (do (save-xls dset fname) (read-xls fname :header-keywords true))]
        (is (dataset? result))
        (is (= (:column-names dset) (:column-names result))))
      (finally (. ffile delete)))))

(deftest xlsx
 (let [ffile (File/createTempFile "excel-test" ".xlsx")
       fname (. ffile getAbsolutePath)]
  (try
     (let [cols ["Ints" "Doubles" "Strings" "Dates"]
           dset (dataset
            cols
            [
             [1 (Math/sqrt 2) "One"   (Date.)]
             [2 Math/E        "Two"   (Date.)]
             [3 Math/PI       "Three" (Date.)]])
           result (do (save-xls dset fname) (read-xls fname))]
        (do
          (is (dataset? result))
          (is (= cols (:column-names result)))))
     (finally (. ffile delete)))))
