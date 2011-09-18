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
  (is (= ($ [:not 0] :all cars) car1))
  (is (= ($ [:not 1] :all cars) car0)))

(deftest select-all-returns-input
  (is (= ($ :all cars) cars)))

(testing "picks up data from scope"
  (with-data cars
    (is (= ($ :speed) [60 70]))
    (is (= ($ :speed nil) [60 70]))))


;; (deftest selects-on-badly-named-atoms
;;   (let [with-nots (dataset [:first :second] [[:not :all] [:all :not]])]
;;     (is (= ($ :first
;;     )))))
