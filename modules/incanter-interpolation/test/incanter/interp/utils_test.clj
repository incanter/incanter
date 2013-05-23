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

(deftest find-segment-test
  (let [xs [0 1 2 3 4 5]]
    (are [x expected] (= (find-segment xs x) expected)
         0   0
         0.5 0
         1   1
         1.5 1
         4   4
         4.5 4
         5   4
         -1  0
         6   4)))


(deftest find-rect-test
  (let [xs [0 1 2 3 4 5]
        ys [0 2 4 6 8 10]]
    (are [x y expected] (= (find-rect xs ys x y) expected)
         0 0   [0 0]
         1.5 3 [1 1]
         2 7   [2 3]
         0 10  [0 4]
         5 10  [4 4]
         5 0   [4 0])))

(deftest translate-domain-test
  (are [from to x expected] (== ((translate-domain inc from to) x)
                                expected)
       [0 1] [0 1] 0 1
       [0 1] [0 1] 1 2
       [0 1] [-1 1] 0   0
       [0 1] [-1 1] 1/2 1
       [0 1] [-1 1] 1   2
       [-1 1] [3 103] -1 4
       [-1 1] [3 103] 0 54
       [-1 1] [3 103] 1 104))

(deftest uniform-test
  (are [range n expected] (->> (uniform range n)
                               (map == expected)
                               (every? true?))
       [0 1] 2 [0 1]
       [0 2] 3 [0 1 2]
       [-1 1] 5 [-1 -0.5 0 0.5 1]))
