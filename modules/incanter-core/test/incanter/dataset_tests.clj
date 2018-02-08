(ns incanter.dataset-tests
  (:require [clojure.core.matrix :as m])
  (:use clojure.test
        (incanter core)))

(def dataset1 (dataset [:a :b :c] [[1 2 3] [4 5 6]]))
(def dataset2 (dataset [" a" "b" "c"] [[1 2 3] [4 5 6]]))
(def dataset3 (dataset [:a :b :c] [{:a 1 :b 2 :c 3} {:a 4 :b 5 :c 6}]))
(def dataset4 (dataset ["a" "b" "c"] [{"a" 1 "b" 2 "c" 3} {"a" 4 "b" 5 "c" 6}]))
(def dataset5 (dataset ["a" "b" "c"] [{"a" 1 "b" 2 "c" 3} {"b" 5 "c" 6}]))

(deftest dataset-tests
  (is (= (sel dataset1 :cols :a) [1 4]))
  (is (= (sel dataset2 :cols "c") [3 6]))
  (is (= (sel dataset3 :cols :a) [1 4]))
  (is (= (sel dataset4 :cols "c") [3 6])))

(def car0 [60, 6000, :green])
(def car1 [70, 7000, :silver])

(def cars (dataset [:speed :weight :colour] [car0 car1]))
(def cars-without-weight (dataset [:speed :colour] [[60, :green] [70, :silver]]))

(deftest select-col-from-dataset
  (is (= ($ :speed cars) [60 70]))
  (is (= ($ :weight cars) [6000 7000]))
  (is (= ($ :colour cars) [:green :silver])))

(deftest unselect-column-from-dataset
  (is (= ($ [:not :weight] cars) (dataset [:speed :colour] [[60, :green] [70, :silver]]))))

(deftest select-row-from-dataset
  (is (= ($ 0 :all cars) car0))
  (is (= ($ 1 :all cars) car1)))

(deftest unselect-row-from-dataset
  (is (= ($ [:not 0] :all cars) (dataset [:speed :weight :colour] [car1])))
  (is (= ($ [:not 1] :all cars) (dataset [:speed :weight :colour] [car0]))))

(deftest select-all-returns-input
  (is (= ($ :all cars) cars)))

(deftest test-sel-filter-fn
  (let [ds (dataset [[110 110]])
        test-ds (dataset [[105 100] [110 110] [111 120]])]
    (is (m/equals ds (sel test-ds :filter-fn (fn [row] (= (get row 0) (get row 1))))))))

(testing "picks up data from scope"
  (with-data cars
    (is (= ($ :speed) [60 70]))
    (is (= ($ :speed nil) [60 70]))))

(deftest test-head
  (is (= (head 2 dataset1) ($ (range 2) :all dataset1)))
  (is (= (head dataset1) ($ (range 2) :all dataset1))))

(deftest test-add-column
  (is (= (add-column :price [17599 22099] cars)
         (dataset [:speed :weight :colour :price] [(conj car0 17599) (conj car1 22099)])))
  (is (= (add-column :weight [2500 3500] cars)
         (dataset [:speed :weight :colour] [(assoc car0 1 2500) (assoc car1 1 3500)]))))

(deftest test-add-derived-column
  (is (= ($ :weight-minus-speed
            (add-derived-column :weight-minus-speed
                                [:weight :speed] #(- %1 %2) cars))
         [5940 6930])))

(deftest reorder-columns-test
  (testing "Reordering column names of a dataset:"
    (let [dset (dataset [:b :a]
                        [[2 1]
                         [4 3]
                         [6 5]])]
      (testing "Simple case"
        (let [expected (dataset [:a :b]
                                [[1 2]
                                 [3 4]
                                 [5 6]])
              actual (reorder-columns dset [:a :b])]
          (is (= expected actual))))
      (testing "Nil case"
        (let [expected nil
              actual (reorder-columns dset [:c])]
          (is (= expected actual))))

      (testing "Duplication (does not make sense!)"
        (let [expected (dataset [:a :b :a :b]
                                [[1 2 1 2]
                                 [3 4 3 4]
                                 [5 6 5 6]])
              actual (reorder-columns dset [:a :b :a :b])]
          (is (= expected actual)))))))

(deftest melt-test
  (testing "Melting the data")
  (let [dset (dataset  [:id :time :x1 :x2 ]
                       [[1 1 5 6]
                        [2 2 7 8]])
        expected (dataset [:id :variable :value ]
                          [[1 :time 1]
                           [2 :time 2]
                           [1 :x1 5]
                           [2 :x1 7]
                           [1 :x2 6]
                           [2 :x2 8]])]
    (is (= expected (melt dset :id)))))

(deftest $map-test
  (is (= ($map (fn [s] (/ s)) :a dataset1)
         [1 1/4]))
  (is (= ($map (fn [s d] (/ s d)) [:a :b] dataset1)
         [1/2 4/5])))
