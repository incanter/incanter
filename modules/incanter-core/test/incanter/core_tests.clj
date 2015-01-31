;;; core-test-cases.clj -- Unit tests of Incanter functions

;; by David Edgar Liebke http://incanter.org
;; October 31, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.


;; to run these tests:
;; (use 'tests core-test-cases)
;;  need to load this file to define data variables
;; (use 'clojure.test)
;; then run tests
;; (run-tests 'incanter.tests.core-test-cases)

(ns incanter.core-tests
  (:require [clojure.core.matrix :as m])
  (:use  clojure.test
         (incanter core)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.core.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def dataset1 (dataset [:a :b :c] [[1 2 3] [4 5 6]]))
(def dataset2 (dataset [" a" "b" "c"] [[1 2 3] [4 5 6]]))
(def dataset3 (dataset [:a :b :c] [{:a 1 :b 2 :c 3} {:a 4 :b 5 :c 6}]))
(def dataset4 (dataset ["a" "b" "c"] [{"a" 1 "b" 2 "c" 3} {"a" 4 "b" 5 "c" 6}]))
(def dataset5 (dataset ["a" "b" "c"] [{"a" 1 "b" 2 "c" 3} {"b" 5 "c" 6}]))
(def dataset6 (dataset [:a :b :c] [[1 2 3]]))

(deftest dataset-tests
  (is (= (sel dataset1 :cols :a) [1 4]))
  (is (= (sel dataset1 :all 1) [2 5]))
  (is (= (sel dataset1 :all :b) [2 5]))
  (is (= (sel dataset1 :all [:a :c]) (dataset [:a :c] [[1 3] [4 6]])))
  (is (= (sel dataset1 :all [:a]) (dataset [:a] [[1] [4]])))
  (is (= (sel dataset1 :all :all) dataset1))
  (is (= (sel dataset2 :cols "c") [3 6]))
  (is (= (sel dataset3 :cols :a) [1 4]))
  (is (= (sel dataset1 :cols [:a]) (dataset [:a] [[1] [4]])))
  (is (= (sel dataset4 :cols "c") [3 6]))
  (is (= (sel dataset6 :cols :a) 1))
  (is (= (sel dataset6 :cols [:a] :rows :all) (dataset [:a] [[1]])))
  (is (= (sel (dataset [:a :b] [[11 12]]) :except-cols :b) (dataset [:a] [[11]]))))

(def map1 {0 [1.0 2.0 3.0] 1 [4.0 5.0 6.0]})

(deftest dataset-construction
  (is (= (to-dataset map1) (to-dataset [[1.0 4.0][2.0 5.0][3.0 6.0]]))))

(deftest dataset-transforms
  (is (= (transform-col dataset6 :b + 10) (dataset [:a :b :c] [[1 12 3]]))
      "Single-row special case")
  (is (= (transform-col dataset1 :b (partial + 10)) (dataset [:a :b :c] [[1 12 3] [4 15 6]])))
  (is (= (transform-col dataset1 :b * 2) (dataset [:a :b :c] [[1 4 3] [4 10 6]]))))

(deftest $group-by-tests
  (let [cs [:c1 :c2 :c3]
        a-dataset (dataset cs
                           [[1 3 3]
                            [1 2 4]
                            [1 2 7]
                            [6 6 8]
                            [6 5 10]
                            [11 12 13]])]

    (are [group-cols n-groups]
      (= n-groups (count ($group-by group-cols a-dataset)))
      :c1 3
      [:c1 :c2] 5) ; 2 arity version

    (are [group-cols n-groups]
      (= n-groups (count
                   (with-data a-dataset
                     ($group-by group-cols))))
      :c1 3
      [:c1 :c2] 5) ; 3 arity version

    (is (= ($group-by :c1 a-dataset)
           {{:c1 1} (dataset cs [[1 3 3]
                                 [1 2 4]
                                 [1 2 7]])
            {:c1 6} (dataset cs [[6 6 8]
                                 [6 5 10]])
            {:c1 11} (dataset cs [[11 12 13]])}))

    (is (= ($group-by [:c1 :c2] a-dataset)
           {{:c1 1 :c2 3} (dataset cs [[1 3 3]])
            {:c1 1 :c2 2} (dataset cs [[1 2 4]
                                       [1 2 7]])
            {:c1 6 :c2 6} (dataset cs [[6 6 8]])
            {:c1 6 :c2 5} (dataset cs [[6 5 10]])
            {:c1 11 :c2 12} (dataset cs [[11 12 13]])}))))

(deftest rename-cols-tests
  (let [data (dataset [:c1 :c2 :c3] [[1 2 3]])
        col-map {:c1 :new-c1 2 :new-c3}
        expected [:new-c1 :c2 :new-c3]]
    (is (= (:column-names (rename-cols col-map data))
           expected))
    (is (= (:column-names (with-data data
                            (rename-cols col-map)))
           expected))))

(deftest infix-test
 (is (= ($= 10 - 1 + 10) 19))
 (is (= ($= 1 / 2 * 3) 3/2)))


(deftest factorial-test
  (is (= (factorial 5) 120.0))
  (is (= (factorial 0) 1.0))
  (is (thrown? AssertionError (factorial -1))))

(defn nan? [x]
  (.isNaN x))

(deftest safe-div-test
  (is (= 1/2 (safe-div 2)))
  (is (= 2 (safe-div 10 5)))
  (is (= 2 (safe-div 20 2 5)))

  (is (= -1/2 (safe-div -2)))
  (is (= -2 (safe-div -10 5)))
  (is (= -2 (safe-div -20 2 5)))

  (is (= Double/POSITIVE_INFINITY (safe-div 0)))
  (is (nan? (safe-div 0 0)))
  (is (nan? (safe-div 0 0 5)))
  (is (= Double/POSITIVE_INFINITY (safe-div 10 0)))
  (is (= Double/POSITIVE_INFINITY (safe-div 20 2 2 0)))
  (is (= Double/POSITIVE_INFINITY (safe-div 20 0 2 2)))
  (is (= Double/NEGATIVE_INFINITY (safe-div -10 0)))
  (is (= Double/NEGATIVE_INFINITY (safe-div -10 5 0))))


(defn non-mutable-ops []
  (testing "non-mutable-ops"
    (let [MO (matrix [[-1 2 3]
                         [4 -5 6]
                         [7 8 -9]
                         [10 11 -12]])
          MC (matrix [[-1 2 3]
                         [4 -5 6]
                         [7 8 -9]
                         [10 11 -12]])
          MA (matrix [[1 2 3]
                         [4 5 6]
                         [7 8 9]
                         [10 11 12]])
          res (abs MO)]
      (is (m/equals res MA))
      (is (m/equals MC MO))
      (is (not (m/equals MO MA))))))


(defn matrix-from-arrays
  "constructing matrices from arrays"
  []
  (is (m/equals (matrix [1.0 2.0 3.0])
         (matrix (double-array [1.0 2.0 3.0]))))
  (is (m/equals (matrix [1.0 2.0 3.0])
         (matrix  (long-array [1 2 3]))))
  (is (m/equals (matrix [[1 2] [3 4]])
             (matrix (object-array [(double-array [1.0 2.0])
                                    (double-array [3.0 4.0])])))))


(defn matrix-trans-test
  "transposing matrices"
  [m]
  (is (m/equals (trans m) (matrix [[1 4 7 10]
                            [2 5 8 11]
                            [3 6 9 12]])))
  (is (m/equals (trans (trans m)) m)))

(defn bind-rows-test
  "combining matrices/vectors by row"
  [m]
  (is (m/equals (bind-rows m m m) (bind-rows m (bind-rows m m))))
  (is (m/equals (bind-rows (matrix [1 2 3])
                           (matrix [4 5 6]))
             (matrix [[1 2 3]
                      [4 5 6]])))
  (is (m/equals (bind-rows m [13 14 15])
         (matrix [[1 2 3]
                  [4 5 6]
                  [7 8 9]
                  [10 11 12]
                  [13 14 15]])))
  (is (m/equals (bind-rows (matrix [13 14 15]) m)
             [[13 14 15]
              [1 2 3]
              [4 5 6]
              [7 8 9]
              [10 11 12]])))

(def A (matrix [[1 2 3]
                [4 5 6]
                [7 8 9]
                [10 11 12]]))

(defn bind-cols-test
  "combining matrices/vectors by column"
  [m]
  (is (m/equals (bind-columns (m/get-column m 0)
                           (m/get-column m 2))
             (matrix [[1 3]
                      [4 6]
                      [7 9]
                      [10 12]])))
  (is (m/equals (bind-columns
          (matrix [[1] [2] [3]])
          (matrix [[4] [5] [6]])
          (matrix [[7] [8] [9]]))
                (matrix [[1 4 7]
                         [2 5 8]
                         [3 6 9]])))
  (is (m/equals (bind-columns [13 14 15 16] m)
         [[13 1 2 3]
          [14 4 5 6]
          [15 7 8 9]
          [16 10 11 12]]))
  (is (m/equals (bind-columns m [13 14 15 16])
         (matrix [[1 2 3 13]
                  [4 5 6 14]
                  [7 8 9 15]
                  [10 11 12 16]]))))

(defn matrix-creation-test
  "creating matrices"
  [m]
  ;; create a 3x2 matrix with initial value 99
  (is (m/equals (matrix 99 3 2)
             (matrix [[99 99]
                      [99 99]
                      [99 99]])))
  ;; create a 3x2 matrix with initial value 0
  (is (m/equals (matrix 0 3 2)
             (matrix [[0 0]
                      [0 0]
                      [0 0]])))
  ;; create a matrix with the given data that has 3 columns
  (is (m/equals (matrix [1 2 3 4 5 6 7 8 9] 3)
             (matrix [[1 2 3]
                      [4 5 6]
                      [7 8 9]])))
  ;; take the diagonal elements of matrix A
  (is (m/equals (diag (matrix m))
             (matrix [1.0 5.0 9.0])))
  ;; create a diagonal matrix with the given data on the diagonal
  (is (m/equals (diag [1/2 1/2 1/2])
             (matrix [[1/2 0 0]
                      [0 1/2 0]
                      [0 0 1/2]])))
  ;; create a 3x3 identity matrix
  (is (m/equals (identity-matrix 3) (matrix [[1 0 0]
                                          [0 1 0]
                                          [0 0 1]])))

  ;; create a 3x3 toeplitz matrix
  (is (m/equals (toeplitz [1 2 3])
             (matrix [[1 2 3]
                      [2 1 2]
                      [3 2 1]]))))

(defn matrix-to-list-test
  "convert a matrix to clojure lists"
  [m]
  (is (m/equals (to-list m)
             '((1.0 2.0 3.0)
               (4.0 5.0 6.0)
               (7.0 8.0 9.0)
               (10.0 11.0 12.0))))
  ;; one-dimensional matrices are coverted to one-dimension vectors
  (is (m/equals (to-list (matrix [1 2 3 4 5 6])) '(1 2 3 4 5 6)))
  (is (m/equals (to-list (trans (matrix [1 2 3 4 5 6]))) '(1 2 3 4 5 6)))
  (is (m/equals (to-list (matrix [[1 2] [3 4]])) '((1 2) (3 4))))
  (is (m/equals (to-list 3) 3))
  (is (m/equals (to-list (matrix [1])) '(1.0)))
  (is (nil? (to-list nil))))

(defn matrix-to-vect-test
  "convert a matrix to clojure vectors"
  [m]
  (is (m/equals (to-vect m)
             [[1.0 2.0 3.0]
              [4.0 5.0 6.0]
              [7.0 8.0 9.0]
              [10.0 11.0 12.0]]))
  ;; one-dimensional matrices are coverted to one-dimension vectors
  (is (m/equals (to-vect (matrix [1 2 3 4 5 6])) [1.0 2.0 3.0 4.0 5.0 6.0]))
  (is (m/equals (to-vect (trans (matrix [1 2 3 4 5 6]))) [1.0 2.0 3.0 4.0 5.0 6.0]))
  (is (m/equals (to-vect (matrix [1])) [1.0]))
  (is (m/equals (to-vect (matrix [[1 2] [3 4]])) [[1 2] [3 4]]))
  (is (m/equals (to-vect 3) 3))
  (is (nil? (to-vect nil))))

(defn matrix-sel-tests
  [im]
  (let [m (matrix im A)]
    ;; select the element at row 3 (i.e. fourth row) and column 2 (i.e. third column)
    (is (== (sel m 3 2) 12.0))
    ;; use 'true' to select an entire row or column
    (is (= (sel m :cols 2) (matrix im [3 6 9 12])))
    (is (= (sel m :rows 1) (matrix im [4 5 6] 3)))
    (is (= (sel m :all [0 2]) (matrix im [[1 3]
                                              [4 6]
                                              [7 9]
                                              [10 12]])))
    (is (= (sel m :all :all) m))
    (is (= (sel m :all 2) (matrix im [3 6 9 12])))
    (is (= (sel m true true) m))
    ;; pass a vector of indices to select a set of rows and/or columns
    (is (= (sel m :cols [0 2]) (matrix im [[1 3]
                                           [4 6]
                                           [7 9]
                                           [10 12]])))
    (is (= (sel m :rows [0 1]) (matrix im [[1 2 3]
                                           [4 5 6]])))
    (is (= (sel m [1 3] [0 2]) (matrix im [[4 6]
                                           [10 12]])))))

(defn matrix-plus-test
  "element by element addition on matrices"
  [im]
  (let [m (matrix im A)]
    (is (= (plus m m m) (matrix im [[3 6 9]
                                        [12 15 18]
                                        [21 24 27]
                                        [30 33 36]])))
    (is (= (plus m 1) (matrix im  [[2 3 4]
                                       [5 6 7]
                                       [8 9 10]
                                       [11 12 13]])))
    (is (= (plus 1 m) (matrix im [[2 3 4]
                                      [5 6 7]
                                      [8 9 10]
                                      [11 12 13]])))
    (is (= (plus (matrix im [1.0 2.0 3.0])
                     (matrix im [1.0 2.0 3.0]))
               (matrix im [2 4 6])))
    (is (= (plus (matrix im [1.0 2.0 3.0]) 1)
               (matrix im [2 3 4])))
    (is (= (plus 1 (matrix im [1.0 2.0 3.0]))
               (matrix im [2 3 4])))))


(defn matrix-minus-test
  "element by element subtraction on matrices"
  [m]
  (is (m/equals (minus m m m) (matrix [[-1 -2 -3]
                                [-4 -5 -6]
                                [-7 -8 -9]
                                [-10 -11 -12]])))
  (is (m/equals (minus m 1) (matrix [[0 1 2]
                              [3 4 5]
                              [6 7 8]
                              [9 10 11]])))
  (is (m/equals (minus 1 m) (matrix [[0 -1 -2]
                              [-3 -4 -5]
                              [-6 -7 -8]
                              [-9 -10 -11]])))
  (is (m/equals (minus (matrix [1 2 3])
                (matrix [1 2 3]))
         (matrix [0 0 0])))
  (is (m/equals (minus (matrix [1 2 3]) 1)
         (matrix [0 1 2])))
  (is (m/equals (minus 1 (matrix [1 2 3]))
         (matrix [0 -1 -2])))
  (is (m/equals (minus (matrix [1 2 3])
                (m/array [1 2 3]))
         (matrix [0 0 0])))
  (is (m/equals (minus 1) -1))
  (is (m/equals (minus (matrix [1 2 3]))
         (matrix [-1 -2 -3])))
  (is (m/equals (minus m) (matrix [[-1 -2 -3]
                            [-4 -5 -6]
                            [-7 -8 -9]
                            [-10 -11 -12]]))))


(defn matrix-mult-test
  "element by element multiplication on matrices"
  [m]
  (is (m/equals (mult m m m) (matrix [[1 8 27]
                                   [64 125 216]
                                   [343 512 729]
                                   [1000 1331 1728]])))
  (is (m/equals (mult m 2) (matrix [[2 4 6]
                             [8 10 12]
                             [14 16 18]
                             [20 22 24]])))
  (is (m/equals (mult 2 m) (matrix [[2 4 6]
                             [8 10 12]
                             [14 16 18]
                             [20 22 24]])))
  (is (m/equals (mult (matrix [1 2 3]) [1 2 3])
         (matrix [1 4 9])))
  (is (m/equals (mult (matrix [1 2 3]) 2.0)
         (matrix [2 4 6])))
  (is (m/equals (mult 2.0 (matrix [1 2 3]))
         (matrix [2 4 6])))
  (is (m/equals (mult (matrix [1 2 3])
                      (matrix [1 2 3]))
                (matrix [1 4 9]))))

(defn matrix-div-test
  "element by element division on matrices"
  [m]
  (is (m/equals (div m m m) (matrix [[1 1/2 1/3]
                                     [1/4 1/5 1/6]
                                     [1/7 1/8 1/9]
                                     [1/10 1/11 1/12]])
                1E-12))
  (is (m/equals (div m 2) (matrix [[1/2 1 3/2]
                                   [4/2 5/2 6/2]
                                   [7/2 8/2 9/2]
                                   [10/2 11/2 12/2]])
                1E-12))
  (is (m/equals (div 2 m) (matrix [[2/1 1 2/3]
                                   [2/4 2/5 2/6]
                                   [2/7 2/8 2/9]
                                   [2/10 2/11 2/12]])
                1E-12))
  (is (m/equals (div (matrix [1 2 3]) [1 2 3])
                (matrix [1 1 1])
                1E-12))
  (is (m/equals (div (matrix [1 2 3]) 2.0)
                (matrix [1/2 1 3/2])
                1E-12))
  (is (m/equals (div 2.0 (matrix [1 2 3]))
                (matrix [2 1 0.6666666666666666])
                1E-12))
  (is (m/equals (div (matrix [1 2 3]) (matrix [1 2 3]))
                (matrix [1 1 1])
                1E-12)))

(defn matrix-mmult-test
  [m]
  ;;perform matrix multiplication
  (is (m/equals (mmult m (trans m))
                (matrix [[14  32  50  68]
                         [32  77 122 167]
                         [50 122 194 266]
                         [68 167 266 365]])))
  ;; perform matrix multiplication on more than two matrices
  (is (m/equals (mmult m (trans m) m)
                (matrix [[1172 1336 1500]
                         [2864 3262 3660]
                         [4556 5188 5820]
                         [6248 7114 7980]]))))

(def ^:private test-mat-source [[39      10 ]
                                [51      20 ]
                                [60      30 ]
                                [64      40 ]
                                [73      50 ]
                                [83      60 ]
                                [90      70 ]
                                [93      80 ]
                                [99      90 ]
                                [105     100]
                                [110     110]
                                [111     120]
                                [113     130]
                                [117     140]
                                [120     150]
                                [125     160]
                                [130     170]
                                [133     180]
                                [133     190]
                                [134     200]
                                [138     210]
                                [140     220]
                                [145     230]
                                [146     240]
                                [148     250]])

(defn arithmetic-test
  []
  (let [test-mat (matrix test-mat-source)
        x (sel test-mat :cols 0)
        y (sel test-mat :cols 1)]
    (testing "Calculate the sum of values in a vector or 1D matrix"
      (is (== (sum x) 2700.0)))
    (testing "Calculate the sum of squares of values in a vector or 1D matrix"
      (is (== (sum-of-squares x) 315638.0)))
    (testing "Calculate the product of values in a vector or 1D matrix"
      (is (== (prod [1 2 3 4 5 6]) 720.0))
      (is (== (prod x) 1.8185668405921276E50)))))

(defn det-test []
   (is (== 6.0 (det (matrix [[-2 2 3] [-1 1 3] [2 0 -1]])))))


(defn matrix-map-test []
  (let [mat (matrix (range 9) 3)]
    (are [x y] (m/equals x y)
         '((0.0 1.0 0.0) (1.0 0.0 1.0) (0.0 1.0 0.0)) (matrix-map #(mod % 2) mat)
         1.0 (matrix-map #(mod % 2) ($ 1 0 mat))
         '(1 0 1 0) (matrix-map #(mod % 2) [1 2 3 4])
         1 (matrix-map #(mod % 2) 9))))


(defn pow-test []
  (is (= (pow 2 2) 4.0))
  (is (m/equals (pow [1 2 3] 2) [1.0 4.0 9.0]))
  (is (m/equals (pow [[1 2 3]] 2) [[1.0 4.0 9.0]]))
  (is (m/equals (pow (matrix [[1 2 3] [4 5 6]]) 2)
                (matrix [[1.0 4.0 9.0] [16.0 25.0 36.0]])))
  (is (m/equals (pow (matrix [[1 2 3]]) 2)
                (matrix [[1.0 4.0 9.0]])))
  (is (m/equals (pow (matrix [1 2 3]) 2)
                (matrix [1.0 4.0 9.0])))
  (is (= (pow (dataset [:a :b :c] [[1 2 3]]) 2)
         (dataset [:a :b :c] [[1.0 4.0 9.0]]))))


(defn sel-filter-test []
  (let [m (matrix [[110 110]])
        test-mat (matrix test-mat-source)]
    (is (m/equals m (sel test-mat :filter-fn (fn [row] (= (first row) (second row))))))))

(defn group-on-test []
  (let [m (matrix [[1 0] [2 1]])]
    (is (m/equals [(matrix [[1 0]]) (matrix [[2 1]])] (group-on m 1)))))

(deftest compliance-test
  (doseq [impl [:clatrix :ndarray :persistent-vector :vectorz]]
    (set-current-implementation impl)
    (println (str "compliance test " impl))
    (let [m (matrix [[1 2 3]
                     [4 5 6]
                     [7 8 9]
                     [10 11 12]])]
      (non-mutable-ops)
      (matrix-from-arrays)
      (matrix-trans-test m)
      (bind-rows-test m)
      (bind-cols-test m)
      (matrix-creation-test m)
      (matrix-to-list-test m)
      (matrix-to-vect-test m)
      (matrix-minus-test m)
      (matrix-mult-test m)
      (matrix-div-test m)
      (matrix-mmult-test m)
      (arithmetic-test)
      (det-test)
      (matrix-map-test)
      (pow-test)
      (sel-filter-test)
      (group-on-test))))

(deftest data-table-test
  (let [table (data-table dataset1)]
    (testing "creates JTable from dataset"
      (is (= (type table) javax.swing.JTable)))
    (testing "JTable has correct value at specified cell"
      (let [row 1 col 2
            cell-value (-> table (.getModel) (.getValueAt row col))
            dataset-value ($ row col dataset1)]
        (is (= cell-value dataset-value))))))
