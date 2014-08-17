(ns incanter.zoo-test
  (:use clojure.test
        (incanter zoo core stats))
  (:require [clj-time.coerce :as c]
            [clojure.core.matrix.dataset :as ds]))


;; --= Tests
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

;; --------------------
;; Standard dataset, with :index column
(def ds1 (to-dataset [{:index "2012-01-01" :temp 32 :press 100}
                      {:index "2012-01-02" :temp 35 :press 98}
                      {:index "2012-01-03" :temp 30 :press 102}
                      {:index "2012-01-04" :temp 31 :press 103}
                      {:index "2012-01-05" :temp 32 :press 104}
                      {:index "2012-01-06" :temp 33 :press 105}]))

;; Standard dataset with :date column
(def ds2 (to-dataset [{:date "2012-01-01" :temp 32 :press 100}
                      {:date "2012-01-02" :temp 35 :press 98}
                      {:date "2012-01-03" :temp 30 :press 102}
                      {:date "2012-01-04" :temp 31 :press 103}
                      {:date "2012-01-05" :temp 32 :press 104}
                      {:date "2012-01-06" :temp 33 :press 105}]))

;; Data with missing values
(def ds3 (to-dataset [{:date "2012-01-01" :temp 32}
                      {:date "2012-01-02" :press 98 :temp 32}
                      {:date "2012-01-06" :temp 33}]))

(defn zoo-test []
  (is (zoo ds1))
  (is (zoo ds2 :date))
  (is (zoo ds3 :date))
  (testing "Check ordering"
    (let [ts (zoo (to-dataset [{:index "2012-01-01" :a 1}
                               {:index "2012-01-02" :a 2}
                               {:index "2012-01-03" :a 3}]))]
      (is (= ts
             (zoo (to-dataset [{:index "2012-01-01" :a 1}
                               {:index "2012-01-03" :a 3}
                               {:index "2012-01-02" :a 2}]))))
      (is (= ts
             (zoo (to-dataset [{:index "2012-01-03" :a 3}
                               {:index "2012-01-02" :a 2}
                               {:index "2012-01-01" :a 1}])))))))

(defn $$-test []
  ;; Time slicing
  (let [ts1 (zoo ds1)]
    (testing "Single date slice"
      (is (= ($$ "2012-01-01" :temp ts1) 32) "Single col")
      (is (= (dataset (ds/column-names ts1) [($$ "2012-01-01" :all ts1)])
             (dataset (ds/column-names ts1)
                      [{:index (to-date-time "2012-01-01")
                        :press 100
                        :temp 32}])) "Whole row")
      (is (= ($$ "2012-01-01" :all ts1)
             ($$ "2012-01-01" ts1)) "Single arity check")
      (is (= (nrow ($$ "2012-01-10" :all ts1)) 0) "Date out of range"))

    (testing "Date range slice"
      (let [slice ($$ "2012-01-01" "2012-01-03" :all ts1)]
        (is (= 3 (nrow slice)))
        (is (= 3 (ncol slice))))
      (is (= ($$ "2012-01-01" "2012-01-03" :temp ts1) [32 35 30]) "Single col"))

    (testing "Native Joda as index"
      (is (= ($$ (c/from-string "2012-01-03") :temp ts1) 30)))

    (testing "End point overlaps"
      (is (= ($$ "2012-01-01" "2012-01-06" :all ts1)
             ($$ "2012-01-01" "2012-06-10" :all ts1)) "RHS")
      (is (= ($$ "2012-01-01" "2012-01-06" :all ts1)
             ($$ "2011-01-01" "2012-06-10" :all ts1)) "LHS&RHS"))))

(defn aligned?-test []
  (let [ts1 (zoo ds1)
        ts2 (zoo ds2 :date)]
    (aligned? ts1 ts2)
    (is (not (aligned? ts1
                       (zoo (to-dataset [{:index "2012-01-01" :a 1}
                                         {:index "2012-01-02" :a 2}]))))
        "Unequal length")
    (is (not (aligned? (zoo (to-dataset [{:index "2012-01-01" :a 1}
                                         {:index "2012-01-02" :a 2}]))
                       (zoo (to-dataset [{:index "2012-01-02" :a 1}
                                         {:index "2012-01-03" :a 2}]))))
        "Different indices")))

(defn within-zoo?-test []
  (let [ts1 (zoo ds1)]
    (is (within-zoo? "2012-01-01" ts1))
    (is (within-zoo? "2012-01-04" ts1))
    (is (not (within-zoo? "2012-01-11" ts1)))
    (is (within-zoo? (c/from-string "2012-01-02T01:03:03") ts1) "Needn't be same frequency")))

(defn lag-test []
  (let [ts1 (zoo ds1)
        ls1 (lag ts1)]
    (is (aligned? ts1 ls1) "Indices equal")
    (is (aligned? ts1 (lag ls1)) "Indices equal")
    (is (aligned? ts1 (lag ts1 3)) "Indices equal")

    (is (= ($ 0 [:press :temp] ls1)
           [nil nil]) "Nil pad the front values")
    (is (= ($ 0 [:press :temp] ts1)
           ($ 1 [:press :temp] ls1)) "Second vals of lag = first of ts")
    (is (= ($ 0 [:press :temp] ts1)
           ($ 2 [:press :temp] (lag ts1 2))) "Multiperiod lag")
    (is (= ($ 2 [:press :temp] (-> ts1 lag lag))
           ($ 2 [:press :temp] (lag ts1 2))) "Multiperiod lag")))

(defn zoo-apply-test []
  (let [ts (zoo ds1)
        zs (zoo-apply #(apply min %) 2 ts :temp)]
    (aligned? ts zs)
    (is (= (rest ($ :temp zs))
           (roll-min 2 ($ :temp ts))))))

;; Helper for test below
(defn- map-diff
  "Return a map with the different values of v2 - v1"
  [m1 m2]
  (into {} (map (fn [[k1 v1] [k2 v2]]
                  (if (and v1 v2)
                    {k2 (- v1 v2)}
                    {k2 nil}))
                m1 m2)))

(defn zoo-row-map-test []
  (let [ts1 (zoo ds1)
        ms1 (zoo-row-map map-diff ts1 ts1)
        ms2 (zoo-row-map map-diff ts1 (lag ts1))]
    (is (every? (partial = 0) ($ :temp ms1)))
    ms2
    (is (= ($ :press ms2) [nil -2 4 1 1 1]))))


(deftest compliance-test
  (doseq [impl [:clatrix :ndarray :persistent-vector :vectorz]]
    (set-current-implementation impl)
    (println (str "compliance test " impl))
    (zoo-test)
    ($$-test)
    (aligned?-test)
    (within-zoo?-test)
    (lag-test)
    (zoo-apply-test)
    (zoo-row-map-test)))
