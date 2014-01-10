(ns incanter.excel-tests
  (:use [clojure.test :only[deftest is]]
        [incanter.core :only [dataset dataset? $ col-names]]
        [incanter.excel :only [save-xls read-xls]])
  (:import java.lang.Math
           java.util.Date
           java.io.File))

(def sample-cols
  ["Ints" "Doubles" "Strings" "Dates"])
(def sample-dataset
  (dataset
   sample-cols
   [[1 (Math/sqrt 2) "One"   (Date.)]
    [2 Math/E        "Two"   (Date.)]
    [3 Math/PI       "Three" (Date.)]]))

(deftest xls-roundtrip
 (let [ffile (File/createTempFile "excel-test" ".xls")
       fname (. ffile getAbsolutePath)]
  (try
     (let [cols sample-cols
           dset sample-dataset
           result (do (save-xls dset fname) (read-xls fname))]
        (do
          (is (dataset? result))
          (is (= cols (col-names result)))))
     (finally (. ffile delete)))))

(deftest headers
  (let [ffile (File/createTempFile "excel-test" ".xls")
        fname (. ffile getAbsolutePath)]
    (try
      (let [dset (dataset [:a :b :c]
                          [[1 2 3]])
            result (do (save-xls dset fname) (read-xls fname :header-keywords true))]
        (is (dataset? result))
        (is (= (col-names dset) (col-names result))))
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
          (is (= cols (col-names result)))))
     (finally (. ffile delete)))))

(deftest multi-sheets
  (let [ffile (File/createTempFile "excel-test" ".xls")
        fname (. ffile getAbsolutePath)]
    (try
      (let [data sample-dataset
            data2 (dataset [:a :b :c :d] [[1 2 3 4] [5 6 7 8] [9 10 11 12]])]
        (do
          (save-xls ["first" data "second" data2] fname)
          (let [res (read-xls fname :all-sheets? true :header-keywords true)
                one (first res)
                two (nth res 1)]
            (is (dataset? one))
            (is (= [1 2 3] (map int ($ :Ints one))))
            (is (dataset? two))
            (is (= [1 5 9] (map int ($ :a two))))
            (is (= [:a :b :c :d] (col-names two)))
            )))
      (finally (. ffile delete)))))
