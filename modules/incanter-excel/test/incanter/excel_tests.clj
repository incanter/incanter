(ns incanter.excel-tests
  (:use clojure.test 
        incanter.core
        incanter.excel))

(deftest xls-roundtrip
 (let [fname (. (java.io.File/createTempFile "excel-test" ".xls") getAbsolutePath)]
  (try (is
       (dataset?
        (do
          (save-xls (dataset ["First" "Second"] [[1 2] [3 4] [java.lang.Math/E java.lang.Math/PI]]) fname)
          (read-xls fname))))
       (. (java.io.File. fname) delete))))