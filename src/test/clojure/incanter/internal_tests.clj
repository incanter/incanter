(ns incanter.internal-tests
  (:use incanter.internal)
  (:use clojure.contrib.monads)
  (:use incanter.probability)
  (:use incanter.chrono)
  (:use clojure.contrib.test-is))

(deftest maybe-predicates
  (is (maybe? #(> % 10) 15))
  (is ((fn [x] (maybe? #(> % 10) x)) 15))
  (is (maybe? #(> (+ %1 %2) 10) 10 5))
  (is (maybe? #(> (+ %1 %2) 10) (+ 5 5) 5))
  (is (not (maybe? #(> % 10) nil))))

(deftest cond-comp-test
  (is (= 6
	 ((cond-comp #(> 4 %) #(* 3 %) #(* 2 %)) 2)))
  (is (= 10
	 ((cond-comp #(> 4 %) #(* 3 %) #(* 2 %)) 5))))

(deftest threshold-to-zero
  (is (= 0
	 (threshold-to 0 [nil nil])))
  (is (= 5
	 (threshold-to 5 [nil nil 4])))
  (is (= 10
	 (threshold-to 0 [10 nil nil]))))

(deftest vector-composing
(is (= [10 5/2]
 ((vector-comp #(* % 2) #(/ % 2)) 5))))

(deftest seqable-test
  (is (seqable? []))
  (is (not (seqable? 2))))

(deftest nilorempty 
  (is (= true 
	 (nil-or-empty? [])))
  (is (= true 
	 (nil-or-empty? nil)))
  (is (= false 
	 (nil-or-empty? {:a 1})))
  (is (= false 
	 (nil-or-empty? (map str [1 2 3 4])))))
      
(deftest make-key
  (is (= "ab24"
	 (makekey [:a :b :c :d] {:a "a" :b "b" :c 2 :d 4}))))

(deftest tree-comp-in-action
  (is (= 10
	 ((tree-comp * #(- % 3) identity) 5))))

(deftest monadic-tree-comp-properties
 (is (= nil
	((tree-comp + :a :b) {:a nil :b nil})))
 (is (= nil
	((tree-comp + :a :b) {})))
  (is (= nil
	 ((tree-comp + first second) [nil nil]))))

(deftest apply-f-to-map-args
  (is (= 3
	 ((tree-comp + :a :b) {:a 1 :b 2})))
  (is (= 3
	 ((tree-comp + :a) {:a 3})))
  (is (= 3
	 ((tree-comp + :a 2) {:a 1 :b 2})))
  (is (= -1
	 (((fn [a & b] (apply tree-comp a b)) #(- %1 %2) :a :b) {:a 1 :b 2})))
  (is (= -1
	 ((tree-comp #(- %1 %2) :a :b) {:a 1 :b 2}))))

(deftest p-with-tree-comp
  (is (= [{1 {0 1}} {1 1}]
	 ((P (present-when (tree-comp > :a 5)) | (present-when (tree-comp < :b 10))) {:a 4 :b 5})))
  (is (= [{1 {1 1}} {1 1}]
	 ((P (present-when (tree-comp > :a 5)) | (present-when (tree-comp < :b 10))) {:a 6 :b 6}))))

(deftest tree-comp-edge-case
  (is (=
       ((tree-comp #(not (nil? %)) :foo) {:foo (joda-date 1 1 1 2 0 0 0 (time-zone 0))}))))
