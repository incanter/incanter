(ns incanter.excel-tests
  (:use clojure.test 
        incanter.core
        incanter.excel)
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