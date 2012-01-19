(ns incanter.zoo-test
  (:use clojure.test 
        (incanter zoo core stats)))

(def integers (iterate inc 0))

(deftest roll-apply-test
  (is (= [3 6 9 12 15]
         (take 5 (roll-apply #(apply + %) 3 integers)))))

(deftest roll-mean-test
  (is (= [2 3 4 5 6 7 8 9 10 11]
         (take 10  (roll-mean 5 integers)))))

(deftest roll-median-test
  (is (= [2.0 3.0 4.0 5.0 6.0]
         (take 5 (roll-median 5 integers)))))

(deftest roll-max-test
  (is (= [2 3 4 5 6]
         (take 5 (roll-max 3 integers)))))

(deftest roll-min-test
  (is (= [0 1 2 3 4]
         (take 5 (roll-min 3 integers)))))