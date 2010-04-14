(ns incanter.excel-tests
  (:use clojure.test 
        incanter.core
        incanter.excel))

(save-xls
  (dataset ["First" "Second"] [[1 2] [3 4] ["String" java.lang.Math/PI]])
  "tmp.xls"
  :sheet-name "Cars")