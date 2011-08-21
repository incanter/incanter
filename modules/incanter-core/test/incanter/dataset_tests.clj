(ns incanter.dataset-tests
  (:use clojure.test 
        (incanter core)))

(def dataset1 (dataset [:a :b :c] [[1 2 3] [4 5 6]]))
(def dataset2 (dataset [" a" "b" "c"] [[1 2 3] [4 5 6]]))
(def dataset3 (dataset [:a :b :c] [{:a 1 :b 2 :c 3} {:a 4 :b 5 :c 6}]))
(def dataset4 (dataset ["a" "b" "c"] [{"a" 1 "b" 2 "c" 3} {"a" 4 "b" 5 "c" 6}]))
(def dataset5 (dataset ["a" "b" "c"] [{"a" 1 "b" 2 "c" 3} {"b" 5 "c" 6}]))

(deftest dataset-tests
  (is (= (sel dataset1 :cols :a) [1 4]))
  (is (= (sel dataset2 :cols :b) [2 5]))
  (is (= (sel dataset2 :cols "c") [3 6]))
  (is (= (sel dataset3 :cols :a) [1 4]))
  (is (= (sel dataset4 :cols :b) [2 5]))
  (is (= (sel dataset4 :cols "c") [3 6]))
  (is (= (sel dataset5 :rows 1 :cols :a) nil)))

(def cars (dataset [:speed :weight :colour] [[60, 6000, :green] [70, 7000, :silver]]))
(def cars-without-weight (dataset [:speed :colour] [[60, :green] [70, :silver]]))

(deftest select-col-from-dataset
  (is (= ($ :speed cars) [60 70]))
  (is (= ($ :weight cars) [6000 7000]))
  (is (= ($ :colour cars) [:green :silver])))

(deftest unselect-column-from-dataset
  (is (= ($ [:not :weight] cars) (dataset [:speed :colour] [[60, :green] [70, :silver]]))))

(deftest picks-up-dataset-from-scope
  (with-data cars
    (is (= ($ :speed) [60 70]))))