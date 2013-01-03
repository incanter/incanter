(ns incanter.interp.utils-test
  (:use clojure.test
        incanter.interp.utils))

(deftest binary-search-test
  (are [value pos] (= (binary-search [0 2 4 8 16 32 64 128] value) pos)
       0   0
       1   0
       2   1
       3   1
       4   2
       8   3
       20  4
       50  5
       100 6
       128 7))

(deftest binary-search-repeat-test
  (are [value pos] (= (binary-search [0 0 0 1 2 3 3 3 4 5 6 6 6] value) pos)
       0 2
       3 7
       6 12))