(ns incanter.dataset-tests
  (:use clojure.test
        (incanter core)))

(def dataset1 (dataset [:a :b :c] [[1 2] [4 5] [6 7]]))
(def dataset2 (dataset [" a" "b"] [[1 2 3] [4 5 6]]))
(def dataset3 (dataset [{:a 1 :b 2 :c 3} {:a 4 :b 5 :c 6}]))
(def dataset4 (dataset [{"a" 1 "b" 2 "c" 3} {"a" 4 "b" 5 "c" 6}]))

(deftest dataset-tests
  (is (= (sel dataset1 :cols :a) [1 2]))
  (is (= (sel dataset2 :cols "b") [4 5 6]))
  (is (= (sel dataset3 :cols :a) [1 4]))
  (is (= (sel dataset4 :cols "c") [3 6])))

(def car0 [60, 6000, :green])
(def car1 [70, 7000, :silver])


(def cars (dataset [:speed :weight :colour]
                   [(map #(nth % 0) [car0 car1])
                    (map #(nth % 1) [car0 car1])
                    (map #(nth % 2) [car0 car1])]))
(def cars-without-weight (dataset [:speed :colour]
                                  [(map #(nth % 0) [car0 car1])
                                   (map #(nth % 2) [car0 car1])]))

(deftest select-col-from-dataset
  (is (= ($ :speed cars) [60 70]))
  (is (= ($ :weight cars) [6000 7000]))
  (is (= ($ :colour cars) [:green :silver])))

(deftest unselect-column-from-dataset
  (is (= ($ [:not :weight] cars) (dataset [:speed :colour]
                                          [[60 70] [:green :silver]]))))

(deftest select-row-from-dataset
  (is (= ($ 0 :all cars) car0))
  (is (= ($ 1 :all cars) car1)))

(deftest unselect-row-from-dataset
  (is (= ($ [:not 0] :all cars) (dataset [:speed :weight :colour]
                                         (map (fn [k] [k]) car1))))
  (is (= ($ [:not 1] :all cars) (dataset [:speed :weight :colour]
                                         (map (fn [k] [k]) car0)))))

(deftest select-all-returns-input
  (is (= ($ :all cars) cars)))

(testing "picks up data from scope"
  (with-data cars
    (is (= ($ :speed) [60 70]))
    (is (= ($ :speed nil) [60 70]))))

(deftest test-head
  (is (= (head 2 dataset1) ($ (range 2) :all dataset1)))
  (is (= (head dataset1) ($ (range 2) :all dataset1))))

(deftest test-add-column
  (is (= (add-column :price [17599 22099] cars)
         (dataset [:speed :weight :colour :price]
                  (trans [(conj car0 17599) (conj car1 22099)]))))
  (is (= (add-column :weight [2500 3500] cars)
         (dataset [:speed :weight :colour]
                  (trans [(assoc car0 1 2500) (assoc car1 1 3500)])))))

(deftest test-add-derived-column
  (is (= ($ :weight-minus-speed
            (add-derived-column :weight-minus-speed
                                [:weight :speed] #(- %1 %2) cars))
         [5940 6930])))

(deftest reorder-columns-test
  (testing "Reordering column names of a dataset:"
    (let [dset (dataset [:b :a] [[2 4 6] [1 3 5]])]
      (testing "Simple case"
        (let [expected (dataset [:a :b] [[1 3 5] [2 4 6]])
              actual (reorder-columns dset [:a :b])]
          (is (= expected actual))))
      (testing "Nil case"
        (let [expected nil
              actual (reorder-columns dset [:c])]
          (is (= expected actual))))

      (testing "Duplication (does not make sense!)"
        (let [expected (dataset [:a :b :a :b]
                                [[1 3 5] [2 4 6] [1 3 5] [2 4 6]])
              actual (reorder-columns dset [:a :b :a :b])]
          (is (= expected actual))
          ))
      )))

(deftest melt-test
  (testing "Melting the data")
  (let [dset (dataset  [:id :time :x1 :x2 ]
                       [[1 2] [1 2] [5 7] [6 8]])
        expected (dataset [:value :variable :id]
                          [[1 2 5 7 6 8]
                           [:time :time :x1 :x1 :x2 :x2]
                           [1 2 1 2 1 2]])]
    (is (= (melt dset :id) expected))))
