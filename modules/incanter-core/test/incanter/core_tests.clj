
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
  (:use clojure.test
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
  (is (= (sel dataset2 :cols :b) [2 5]))
  (is (= (sel dataset2 :cols "c") [3 6]))
  (is (= (sel dataset3 :cols :a) [1 4]))
  (is (= (sel dataset1 :cols [:a]) (dataset [:a] [[1] [4]])))
  (is (= (sel dataset4 :cols :b) [2 5]))
  (is (= (sel dataset4 :cols "c") [3 6]))
  (is (= (sel dataset5 :rows 1 :cols :a) nil))
  (is (= (sel dataset6 :cols :a) 1))
  (is (= (sel (dataset [:a :b] [[11 12]]) :except-cols :b) (dataset [:a] [[11]]))))

(def map1 {:col-0 [1.0 2.0 3.0] :col-1 [4.0 5.0 6.0]})

(deftest dataset-construction
  (is (= (to-dataset map1) (to-dataset [[1.0 4.0][2.0 5.0][3.0 6.0]]))))

(deftest dataset-transforms
  (is (= (transform-col dataset6 :b + 10) (dataset [:a :b :c] [[1 12 3]]))
      "Single-row special case")
  (is (= (transform-col dataset1 :b (partial + 10))) (dataset [:a :b :c] [[1 12 3] [4 15 6]]))
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

;; define a simple matrix for testing
(def A (matrix [[1 2 3]
                [4 5 6]
                [7 8 9]
                [10 11 12]]))

(def V (matrix [[1 2 3]
                [4 5 6]
                [7 8 9]
                [10 11 12]]))

;; define a 2d list for testing
(def l [[1 2 3]
        [4 5 6]
        [7 8 9]
        [10 11 12]])

(deftest matrix-dims
  ;; checking dimensions
  (is (= (nrow A) 4))
  (is (= (ncol A) 3))
  (is (= (dim A) [4 3]))
  (is (= (count A) 4))
  (is (= (count (first A)) 3))
  (is (= (count (rest A)) 3))
  (is (= (count (rest (first A))) 2)))

(deftest non-mutable-ops
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
    (is (= res MA))
    (is (= MC MO))
    (is (not (= MO MA)))))


;; constructing matrices from arrays
(deftest matrix-from-arrays
  (is (= (matrix [1.0 2.0 3.0]) (matrix (double-array [1.0 2.0 3.0]))))
  (is (= (matrix [1.0 2.0 3.0]) (matrix (long-array [1 2 3]))))
  (is (= (matrix [[1 2] [3 4]])
         (matrix (object-array [(double-array [1.0 2.0])
                                (double-array [3.0 4.0])])))))


  ;; performing clojure sequence operations on matrices
(deftest matrix-seq-tests
  (is (= (first A) (matrix [1 2 3] 3))) ; first of A is a 1D row matrix
  (is (= (rest A) (matrix [[4 5 6]
                           [7 8 9]
                           [10 11 12]])))
  (is (= (conj A [[13 14 15]]) (matrix [[1 2 3]
                                      [4 5 6]
                                      [7 8 9]
                                      [10 11 12]
                                      [13 14 15]])))
  (is (= (conj A A) (matrix [[1 2 3]
                             [4 5 6]
                             [7 8 9]
                             [10 11 12]
                             [1 2 3]
                             [4 5 6]
                             [7 8 9]
                             [10 11 12]])))
  (is (= (seq A) A))
  (is (= (first (first A)) 1.0))
  (is (= (nth (nth A 1) 1.0)))
  ;; get column 1 (i.e. second column) of matrix A
  (is (= (map #(nth % 1) A) [2.0 5.0 8.0 11.0])))



  ;; transposing matrices
(deftest matrix-trans-test
  (is (= (trans A) (matrix [[1 4 7 10]
                            [2 5 8 11]
                            [3 6 9 12]])))
  (is (= (trans (trans A)) A))
  (is (= (first (trans A)) (matrix [1 4 7 10] 4))))

(deftest bind-rows-test
  ;; combining matrices/vectors by row
  (is (= (bind-rows (first A) (rest A)) A))
  (is (= (bind-rows A A A) (bind-rows A (bind-rows A A))))
  (is (= (bind-rows [1 2 3] [4 5 6]) (matrix [[1 2 3]
                                              [4 5 6]])))
  (is (= (bind-rows A [13 14 15]) (matrix [[1 2 3]
                                       [4 5 6]
                                       [7 8 9]
                                       [10 11 12]
                                       [13 14 15]])))
  (is (= (bind-rows [13 14 15] A) (matrix [[13 14 15]
                                       [1 2 3]
                                       [4 5 6]
                                       [7 8 9]
                                       [10 11 12]]))))

(deftest bind-cols-test
  ;; combining matrices/vectors by column
  (is (= (bind-columns (trans (nth (trans A) 0))
                       (trans (nth (trans A) 2)))
         (matrix [[1 3]
                  [4 6]
                  [7 9]
                  [10 12]])))
  (is (= (bind-columns [[1] [2] [3]] [[4] [5] [6]] [[7] [8] [9]])
         (matrix [[1 4 7]
                  [2 5 8]
                  [3 6 9]])))
  (is (= (bind-columns [13 14 15 16] A)
         (matrix [[13 1 2 3]
                  [14 4 5 6]
                  [15 7 8 9]
                  [16 10 11 12]])))
  (is (= (bind-columns A [13 14 15 16])
         (matrix [[1 2 3 13]
                  [4 5 6 14]
                  [7 8 9 15]
                  [10 11 12 16]]))))

(deftest matrix-creation-tests
  ;; creating matrices
  ;; create a 3x2 matrix with initial value 99
  (is (= (matrix 99 3 2)
         (matrix [[99 99]
                  [99 99]
                  [99 99]])))
  ;; create a 3x2 matrix with initial value 0
  (is (= (matrix 0 3 2)
         (matrix [[0 0]
                  [0 0]
                  [0 0]])))
  ;; create a matrix with the given data that has 3 columns
  (is (= (matrix [1 2 3 4 5 6 7 8 9] 3)
         (matrix [[1 2 3]
                  [4 5 6]
                  [7 8 9]])))
  ;; take the diagonal elements of matrix A
  (is (= (diag A) [1.0 5.0 9.0]))
  ;; create a diagonal matrix with the given data on the diagonal
  (is (= (diag [1/2 1/2 1/2]) (matrix [[1/2 0 0]
                                       [0 1/2 0]
                                       [0 0 1/2]])))
  ;; create a 3x3 identity matrix
  (is (= (identity-matrix 3) (matrix [[1 0 0]
                                      [0 1 0]
                                      [0 0 1]])))

  ;; create a 3x3 toeplitz matrix
  (is (= (toeplitz [1 2 3]) (matrix [[1 2 3]
                                     [2 1 2]
                                     [3 2 1]]))))

(deftest matrix-to-list-tests
  ;; convert a matrix to clojure vectors
  (is (= (to-list A) [[1.0 2.0 3.0]
                      [4.0 5.0 6.0]
                      [7.0 8.0 9.0]
                      [10.0 11.0 12.0]]))
  ;; one-dimensional matrices are coverted to one-dimension vectors
  (is (= (to-list (matrix [1 2 3 4 5 6])) [1.0 2.0 3.0 4.0 5.0 6.0]))
  (is (= (to-list (trans (matrix [1 2 3 4 5 6]))) [1.0 2.0 3.0 4.0 5.0 6.0]))
  (is (= (to-list [1 2 3]) [1 2 3]))
  (is (= (to-list [[1 2] [3 4]]) [[1 2] [3 4]]))
  (is (= (to-list 3) 3))
  (is (nil? (to-list nil))))

(deftest matrix-to-vect-tests
  ;; convert a matrix to clojure vectors
  (is (= (to-vect A) [[1.0 2.0 3.0]
                      [4.0 5.0 6.0]
                      [7.0 8.0 9.0]
                      [10.0 11.0 12.0]]))
  ;; one-dimensional matrices are coverted to one-dimension vectors
  (is (= (to-vect (matrix [1 2 3 4 5 6])) [1.0 2.0 3.0 4.0 5.0 6.0]))
  (is (= (to-vect (trans (matrix [1 2 3 4 5 6]))) [1.0 2.0 3.0 4.0 5.0 6.0]))
  (is (= (to-vect [1 2 3]) [1 2 3]))
  (is (= (to-vect [[1 2] [3 4]]) [[1 2] [3 4]]))
  (is (= (to-vect 3) 3))
  (is (nil? (to-vect nil))))

(deftest matrix-sel-tests
  ;; select the element at row 3 (i.e. fourth row) and column 2 (i.e. third column)
  (is (= (sel A 3 2) 12.0))
  ;; use 'true' to select an entire row or column
  (is (= (sel A :cols 2) (matrix [3 6 9 12])))
  (is (= (sel A :rows 1) (matrix [[4 5 6]])))
  (is (= (sel A :all [0 2]) (matrix [[1 3]
                                     [4 6]
                                     [7 9]
                                     [10 12]])))
  (is (= (sel A :all :all) A))
  (is (= (sel A :all 2) (matrix [[3]
                                 [6]
                                 [9]
                                 [12]])))
  (is (= (sel A true true) A))
  ;; pass a vector of indices to select a set of rows and/or columns
  (is (= (sel A :cols [0 2]) (matrix [[1 3]
                                     [4 6]
                                     [7 9]
                                     [10 12]])))
  (is (= (sel A :rows [0 1]) (matrix [[1 2 3]
                                     [4 5 6]])))
  (is (= (sel A [1 3] [0 2]) (matrix [[4 6]
                                      [10 12]]))))

(deftest list-sel-tests
  ;; select the element at row 3 (i.e. fourth row) and column 2 (i.e. third column)
  (is (= (sel l 3 2) 12))
  ;; use 'true' to select an entire row or column
  (is (= (sel l :cols 2) [3 6 9 12]))
  (is (= (sel l :rows 1) [4 5 6]))
  (is (= (sel l :all [0 2]) [[1 3]
                             [4 6]
                             [7 9]
                             [10 12]]))
  (is (= (sel l :all :all) l))
  (is (= (sel l :all 2) [3 6 9 12]))
  (is (= (sel l true true) l))
  ;; pass a vector of indices to select a set of rows and/or columns
  (is (= (sel l :cols [0 2]) [[1 3]
                              [4 6]
                              [7 9]
                              [10 12]]))
  (is (= (sel l :rows [0 1]) [[1 2 3]
                              [4 5 6]]))
  (is (= (sel l [1 3] [0 2]) [[4 6]
                              [10 12]])))

(deftest matrix-filter-tests
  ;; filtering: return the rows that sum to more than 6
  (is (= (matrix (filter #(> (sum %) 6.0) A)) (matrix [[4 5 6]
                                                       [7 8 9]
                                                       [10 11 12]])))
  ;; the following tests pass, but the result of these seq ops isn't a proper matrix,
  ;; rather its a sequence of row vectors that behave like a matrix except ncol
  ;; returns 1 no matter how many columns are in the row matrices. Wrap the result
  ;; in a call to matrix (like above to get a proper matrix).
  (is (= (filter #(> (sum %) 6.0) A) (matrix [[4 5 6]
                                              [7 8 9]
                                              [10 11 12]])))
  ;; return rows where the first element is greater than 6
  (is (= (filter #(> (nth % 0) 6.0) A)  (matrix [[7 8 9]
                                                 [10 11 12]]))))

(deftest matrix-drop-test
  ;; drop the first two rows
  (is (= (drop 2 A) (matrix [[7 8 9]
                             [10 11 12]])))
  ;; drop all but the first two rows
  (is (= (take 2 A) (matrix [[1 2 3]
                             [4 5 6]]))))

(deftest matrix-plus-test

  ;; element by element addition on matrices
  (is (= (plus A A A) (matrix [[3 6 9]
                               [12 15 18]
                               [21 24 27]
                               [30 33 36]])))
  (is (= (plus A 1) (matrix [[2 3 4]
                             [5 6 7]
                             [8 9 10]
                             [11 12 13]])))
  (is (= (plus 1 A) (matrix [[2 3 4]
                             [5 6 7]
                             [8 9 10]
                             [11 12 13]])))
  (is (= (plus V V V) (matrix [[3 6 9]
                               [12 15 18]
                               [21 24 27]
                               [30 33 36]])))
  (is (= (plus V 1) (matrix [[2 3 4]
                             [5 6 7]
                             [8 9 10]
                             [11 12 13]])))
  (is (= (plus 1 V) (matrix [[2 3 4]
                             [5 6 7]
                             [8 9 10]
                             [11 12 13]])))
  (is (= (plus [1.0 2.0 3.0] [1.0 2.0 3.0]) (matrix [2 4 6])))
  (is (= (plus [1.0 2.0 3.0] 1) (matrix [2 3 4])))
  (is (= (plus 1 [1.0 2.0 3.0]) (matrix [2 3 4])))
  (is (= (plus [1.0 2.0 3.0] (matrix [1 2 3]) (matrix [2 4 6])))))

(deftest matrix-minus-test
  ;; element by element subtraction on matrices
  (is (= (minus A A A) (matrix [[-1 -2 -3]
                                [-4 -5 -6]
                                [-7 -8 -9]
                                [-10 -11 -12]])))
  (is (= (minus A 1) (matrix [[0 1 2]
                              [3 4 5]
                              [6 7 8]
                              [9 10 11]])))
  (is (= (minus 1 A) (matrix [[0 -1 -2]
                              [-3 -4 -5]
                              [-6 -7 -8]
                              [-9 -10 -11]])))
  (is (= (minus V V V) (matrix [[-1 -2 -3]
                                [-4 -5 -6]
                                [-7 -8 -9]
                                [-10 -11 -12]])))
  (is (= (minus V 1) (matrix [[0 1 2]
                              [3 4 5]
                              [6 7 8]
                              [9 10 11]])))
  (is (= (minus 1 V) (matrix [[0 -1 -2]
                              [-3 -4 -5]
                              [-6 -7 -8]
                              [-9 -10 -11]])))
  (is (= (minus [1.0 2.0 3.0] [1.0 2.0 3.0]) (matrix [0 0 0])))
  (is (= (minus [1.0 2.0 3.0] 1) (matrix [0 1 2])))
  (is (= (minus 1 [1.0 2.0 3.0]) (matrix [0 -1 -2])))
  (is (= (minus [1 2 3] (matrix [1 2 3]) (matrix [0 0 0])))))

(deftest matrix-mult-tests
  ;; element by element multiplication on matrices
  (is (= (mult A A A) (matrix [[1 8 27]
                               [64 125 216]
                               [343 512 729]
                               [1000 1331 1728]])))
  (is (= (mult A 2) (matrix [[2 4 6]
                             [8 10 12]
                             [14 16 18]
                             [20 22 24]])))
  (is (= (mult 2 A) (matrix [[2 4 6]
                             [8 10 12]
                             [14 16 18]
                             [20 22 24]])))
  (is (= (mult V V V) (matrix [[1 8 27]
                               [64 125 216]
                               [343 512 729]
                               [1000 1331 1728]])))
  (is (= (mult V 2) (matrix [[2 4 6]
                             [8 10 12]
                             [14 16 18]
                             [20 22 24]])))
  (is (= (mult 2 V) (matrix [[2 4 6]
                             [8 10 12]
                             [14 16 18]
                             [20 22 24]])))
  (is (= (mult [1.0 2.0 3.0] [1.0 2.0 3.0]) (matrix [1 4 9])))
  (is (= (mult [1 2 3] 2.0) (matrix [2 4 6])))
  (is (= (mult 2.0 [1 2 3]) (matrix [2 4 6])))
  (is (= (mult [1 2 3] (matrix [1 2 3])) (matrix [1 4 9]))))

(deftest matrix-div-tests
  ;; element by element division on matrices
  (is (= (div A A A) (matrix [[1 1/2 1/3]
                              [1/4 1/5 1/6]
                              [1/7 1/8 1/9]
                              [1/10 1/11 1/12]])))
  (is (= (div A 2) (matrix [[1/2 1 3/2]
                            [4/2 5/2 6/2]
                            [7/2 8/2 9/2]
                            [10/2 11/2 12/2]])))
  (is (= (div 2 A) (matrix [[2/1 1 2/3]
                            [2/4 2/5 2/6]
                            [2/7 2/8 2/9]
                            [2/10 2/11 2/12]])))
  (is (= (div V V V) (matrix [[1 1/2 1/3]
                              [1/4 1/5 1/6]
                              [1/7 1/8 1/9]
                              [1/10 1/11 1/12]])))
  (is (= (div V 2) (matrix [[1/2 1 3/2]
                            [4/2 5/2 6/2]
                            [7/2 8/2 9/2]
                            [10/2 11/2 12/2]])))
  (is (= (div 2 V) (matrix [[2/1 1 2/3]
                            [2/4 2/5 2/6]
                            [2/7 2/8 2/9]
                            [2/10 2/11 2/12]])))
  (is (= (div [1.0 2.0 3.0] [1.0 2.0 3.0]) (matrix [1 1 1])))
  (is (= (div [1 2 3] 2.0) (matrix [1/2 1 3/2])))
  (is (= (div 2.0 [1 2 3]) (matrix [2 1 0.6666666666666666])))
  (is (= (div [1 2 3] (matrix [1 2 3])) (matrix [1 1 1]))))

(deftest matrix-mapreduce-tests
  ;; getting row sums
  (is (= (reduce + (first A)) 6.0))
  (is (= (map #(reduce + %) A) [6.0 15.0 24.0 33.0]))
  (is (= (reduce plus (trans A)) (matrix [6 15 24  33] 4)))

  ;; getting column sums
  (is (= (reduce plus A) (matrix [22 26 30] 3)))
  (is (= (map #(reduce + %) (trans A)) [22.0 26.0 30.0]))

  ;; getting column products
  (is (= (reduce mult A) (matrix [280 880 1944] 3)))
  (is (= (reduce mult (trans A)) (matrix [6 120 504 1320] 4))))

(deftest matrix-mmult-tests
;; perform matrix multiplication
(is (= (mmult A (trans A)) (matrix [[14  32  50  68]
                                    [32  77 122 167]
                                    [50 122 194 266]
                                    [68 167 266 365]])))
;; perform matrix multiplication on more than two matrices
(is (= (mmult A (trans A) A) (matrix [[1172 1336 1500]
                                      [2864 3262 3660]
                                      [4556 5188 5820]
                                      [6248 7114 7980]]))))

(def test-mat (matrix
  [[39      10 ]
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
   [148     250]]))

(def x (sel test-mat :cols 0))
(def y (sel test-mat :cols 1))

(deftest matrix-solve-test
;; calculate the inverse of a matrix
(is (= (solve (matrix [[2 0 0] [0 2 0] [0 0 2]])) (diag [1/2 1/2 1/2]))))

(deftest sum-test
;; calculate the sum of values in a vector or 1D matrix
(is (= (sum x) 2700.0)))

(deftest sum-of-squares-test
;; calculate the sum of squares of values in a vector or 1D matrix
(is (= (sum-of-squares x) 315638.0)))

(deftest prod-test
;; calculate the product of values in a vector or 1D matrix
(is (= (prod [1 2 3 4 5 6]) 720.0)))

(deftest decomp-svd-test
  (let [m (matrix [[1 0] [0 1] [0 0]])
        expect-full {:U (diag [1 1 1])
                     :S '(1.0 1.0)
                     :V (diag [1 1])}
        expect-compact {:U m
                        :S '(1.0 1.0)
                        :V (diag [1 1])}
        expect-values {:S '(1.0 1.0)}
        check (fn [type mtest]
                (let [mtrue (decomp-svd m :type type)]
                  (testing (str "svd " type)
                  (is (= (:U mtrue) (:U mtest)))
                  (is (= (:S mtrue) (:S mtest)))
                  (is (= (:V mtrue) (:V mtest))))))]
    (check nil      expect-full)
    (check :full    expect-full)
    (check :compact expect-compact)
    (check :values  expect-values)))

(deftest decomp-qr-test
  (let [m (matrix [[1 0] [0 1] [0 0]])
        expect-full {:Q (diag [1 1 1])
                     :R m}
        expect-compact {:Q m
                        :R (diag [1 1])}
        check (fn [type mtest]
                (let [mtrue (decomp-qr m :type type)]
                  (testing (str "qr " type)
                    (is (= (:Q mtrue) (:Q mtest)))
                    (is (= (:R mtrue) (:R mtest))))))]
    (check nil      expect-full)
    (check :full    expect-full)
;    (check :compact expect-compact)
    ))

(deftest decomp-lu-test
  (let [m (matrix [[0 1 2] [3 3 2] [4 0 1]])
        {:keys [L U P]} (decomp-lu m)]
    (is (= m (mmult P L U)))))

(deftest det-test
  (let [m (matrix [[-2 2 3] [-1 1 3] [2 0 -1]])]
    (is (= 6.0 (det m)))))

(deftest test-metadata
  (let [md {:name "metadata test"}
        m  (with-meta (identity-matrix 3) md)]
    (testing "Basic Matrix metadata conformance"
      (is (= (meta m) md))
      (is (nil? (meta (rest m)))))
    (testing "Matrix decomposition/mult functions"
      (is (nil? (meta (kronecker 4 m))))
      (is (nil? (meta (mmult m (trans m)))))
      (is (nil? (some map?
                      (for [op [solve decomp-cholesky decomp-svd decomp-eigenvalue decomp-lu]]  ;; decomp-qr
                        (meta (-> m op)))))))
    (testing "Matrix math ops"
      (is (nil? (some map?
                      (for [op [plus minus mult div pow atan2]]
                        (meta (-> m #(op % %)))))))
      (is (nil? (some map?
                      (for [op [sqrt sq log log2 log10 exp abs sin asin cos acos tan atan]]
                        (meta (-> m op)))))))
    ;(testing "Known Matrix metadata holes"  ;; TODO wrong test? (meta (seq mat)) should be nil
      ;(is (nil? (some nil?
                      ;(for [op [seq trans pow atan2]]
                        ;(meta (-> m op)))))))
    ))

(deftest matrix-map-test
  (let [mat (matrix (range 9) 3)]
    (are [x y] (= x y)
         '((0.0 1.0 0.0) (1.0 0.0 1.0) (0.0 1.0 0.0)) (matrix-map #(mod % 2) mat)
         '(0.0 1.0 0.0) (matrix-map #(mod % 2) (first mat))
         1.0 (matrix-map #(mod % 2) ($ 1 0 mat))
         '(1 0 1 0) (matrix-map #(mod % 2) [1 2 3 4])
         1 (matrix-map #(mod % 2) 9))))

(deftest infix-test
 (is (= ($= 10 - 1 + 10) 19))
 (is (= ($= 1 / 2 * 3) 3/2)))


(deftest factorial-test
  (is (factorial 5) 120.0)
  (is (factorial 0) 1.0)
  (is (thrown? AssertionError (factorial -1))))


(deftest safe-div-test
  (is 1/2 (safe-div 2))
  (is 2 (safe-div 10 5))
  (is 2 (safe-div 20 2 5))

  (is -1/2 (safe-div -2))
  (is -2 (safe-div -10 5))
  (is -2 (safe-div -20 2 5))

  (is Double/NaN (safe-div 0))
  (is Double/NaN (safe-div 0 0))
  (is Double/NaN (safe-div 0 0 5))
  (is Double/POSITIVE_INFINITY (safe-div 10 0))
  (is Double/POSITIVE_INFINITY (safe-div 20 2 2 0))
  (is Double/POSITIVE_INFINITY (safe-div 20 0 2 2))
  (is Double/NEGATIVE_INFINITY (safe-div -10 0))
  (is Double/NEGATIVE_INFINITY (safe-div -10 5 0)))
