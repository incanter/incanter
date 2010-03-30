(ns incanter.transformations-tests
  (:use clojure.contrib.test-is)
;  (:use incanter.chrono)
  (:use incanter.transformations))



(deftest sort-seq-of-maps-by-key
  (is (= '({:a 1} {:a 5})
	 (sort-maps-by :a [{:a 5} {:a 1}]))))

;(deftest sort-seq-maps-by-date
;  (let [d1 (joda-date 2009 9 12 20 1 1 1 (time-zone 1))
;	d2 (joda-date 2009 9 12 20 2 1 1 (time-zone 1))
;	d3 (joda-date 2009 9 12 20 3 1 1 (time-zone 1))]
;  (is (= [{:a d1} {:a d2} {:a d3}]
;	 (sort-maps-by :a [{:a d2} {:a d3} {:a d1}])))))

(deftest compare-heterogenious-keys
  (is (= 1
	 (key-compare :a 2)))
  (is (= -1
	 (key-compare 2 :a))))
       
(deftest sort-map-of-maps-recursively
  (is (= {5 {7 1, 9 5}, 6 {2 1, 5 3}} 
	 (sort-map-of-maps {6 {5 3 2 1} 5 {9 5 7 1}})))
  (is (= {5 {7 1, 9 5}, :foo {2 1, 5 3}} 
	 (sort-map-of-maps {:foo {5 3 2 1} 5 {9 5 7 1}})))
  (is (= {1 {:a 5, :b 3}, 4 {:a {:b 2, :c 4}, :b 4}}
      (sort-map-of-maps {4 {:b 4 :a {:c 4 :b 2}} 1 {:a 5 :b 3}}))))

(deftest get-all-keys
  (is (= #{1 2 3 4}
	 (all-keys {1 {2 0, 3 0}, 2 {4 1}})))
  (is (= #{1 2 3}
	 (all-keys {1 6, 2 5, 3 4})))
  (is (= #{1 2 3}
	 (all-keys (vals {1 {1 6, 2 5, 3 4}})))))

(deftest bottom-level-test 
(is (= true
 (bottom-level? {:a 1})))
(is (= false
 (bottom-level? {:a {:b 1}}))))

(deftest levels-deep-test
  (is (= 1
	 (levels-deep {0 1})))
  (is (= 2
	 (levels-deep {1 {0 1}})))
  (is (= 0
	 (levels-deep {})))
  (is (= 0
	 (levels-deep 1)))
  (is (= 0
	 (levels-deep nil)))
  (is (= 4
	 (levels-deep {1 {0 {0 1, 1 {0 1}}}}))))

(deftest flatten-map-test
  (is (= {":a:b:c" 1}
	 (flatten-with str {:a {:b {:c 1}}}))))


