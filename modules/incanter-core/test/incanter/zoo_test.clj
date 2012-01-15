(ns incanter.zoo-test
  (:use clojure.test 
        (incanter zoo core stats)))

(def integers (iterate inc 0))
(def sma-5 [2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0 11.0])

(deftest simple-moving-average-test
  (is (= sma-5
         (take 10  (rollmean 5 integers))))
  (is (= [2 3 4 5 6 7 8 9 10 11]
         (take 10 (map int (partition-sma 5 integers)))))
  (is (= [2 3 4 5 6 7 8 9 10 11]
         (take 10 (sliding-window-sma 5 integers)))))