(ns incanter.io-tests
  (:use incanter.io)
  (:use clojure.contrib.test-is))

(deftest csv-line-from-vector
  (is (= "1, 2, 3\n"
	 (csv-line [1 2 3]))))

(deftest csv-table-from-map
  (is (= ", 1, 2, 3\n1, 6, 5, 4\n"
	 (csv-table {1 {1 6, 2 5, 3 4}}))))