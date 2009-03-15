#!/usr/bin/env bin/clj


;;; test-incanter.clj -- Unit tests of Incanter functions 

;; by David Edgar Liebke http://incanter.org
;; March 12, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 12, 2009: First version

;; to run these tests:
;; (use 'incanter.tests test-incanter)
;;  need to load this file to define data variables
;; (use 'clojure.contrib.test-is) 
;; then run tests
;; (run-tests 'incanter.tests.test-incanter)

(ns tests.runtests
  (:use (clojure.contrib test-is) 
        (incanter matrix io stats charts bayes)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.matrix.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; define a simple matrix for testing
(def A (matrix [[1 2 3] 
                [4 5 6] 
                [7 8 9] 
                [10 11 12]]))

(deftest matrix-manip

  ;; checking dimensions
  (is (= (nrow A) 4))
  (is (= (ncol A) 3))

  ;; performing clojure sequence operations on matrices
  (is (= (first A) (matrix [1 2 3] 3))) ; first of A is a 1D row matrix
  (is (= (rest A) (matrix [[4 5 6] 
                           [7 8 9] 
                           [10 11 12]]))) 
  (is (= (conj A [13 14 15]) (matrix [[1 2 3] 
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
  (is (= (first (first A)) 1))
  (is (= (nth (nth A 1) 1)))
  (is (= (count A) 4))
  (is (= (count (first A)) 3))
  (is (= (count (rest A)) 3))
  (is (= (count (rest (first A))) 2))
  ;; get column 1 (i.e. second column) of matrix A
  (is (= (map #(nth % 1) A) [2 5 8 11]))


  ;; transposing matrices
  (is (= (trans A) (matrix [[1 4 7 10] 
                            [2 5 8 11] 
                            [3 6 9 12]])))
  (is (= (trans (trans A)) A))
  (is (= (first (trans A)) (matrix [1 4 7 10] 4)))
  
  ;; combining matrices/vectors by row
  (is (= (rbind (first A) (rest A)) A))
  (is (= (rbind A A A) (rbind A (rbind A A))))
  (is (= (rbind [1 2 3] [4 5 6]) (matrix [[1 2 3] 
                                          [4 5 6]])))
  (is (= (rbind A [13 14 15]) (matrix [[1 2 3] 
                                       [4 5 6] 
                                       [7 8 9] 
                                       [10 11 12] 
                                       [13 14 15]])))
  (is (= (rbind [13 14 15] A) (matrix [[13 14 15] 
                                       [1 2 3] 
                                       [4 5 6] 
                                       [7 8 9] 
                                       [10 11 12]])))
  
  ;; combining matrices/vectors by column
  (is (= (cbind (trans (nth (trans A) 0)) (trans (nth (trans A) 2))) (matrix [[1 3] 
                                                                              [4 6] 
                                                                              [7 9] 
                                                                              [10 12]])))
  (is (= (cbind [[1] [2] [3]] [[4] [5] [6]] [[7] [8] [9]]) (matrix [[1 4 7] 
                                                                    [2 5 8] 
                                                                    [3 6 9]])))
  (is (= (cbind [13 14 15 16] A) (matrix [[13 1 2 3] 
                                          [14 4 5 6] 
                                          [15 7 8 9] 
                                          [16 10 11 12]])))
  (is (= (cbind A [13 14 15 16]) (matrix [[1 2 3 13] 
                                          [4 5 6 14] 
                                          [7 8 9 15] 
                                          [10 11 12 16]])))
  
  ;; creating matrices
  ;; create a 3x2 matrix with initial value 99
  (is (= (matrix 99 3 2) (matrix [[99 99] 
                                  [99 99] 
                                  [99 99]])))
  ;; create a 3x2 matrix with initial value 0
  (is (= (matrix 3 2) (matrix [[0 0] 
                               [0 0] 
                               [0 0]]))) 
  ;; create a matrix with the given data that has 3 columns
  (is (= (matrix [1 2 3 4 5 6 7 8 9] 3) (matrix [[1 2 3] 
                                                 [4 5 6] 
                                                 [7 8 9]])))
  ;; take the diagonal elements of matrix A
  (is (= (diag A) [1 5 9]))
  ;; create a diagonal matrix with the given data on the diagonal
  (is (= (diag [1/2 1/2 1/2]) (matrix [[1/2 0 0] 
                                       [0 1/2 0] 
                                       [0 0 1/2]])))
  ;; create a 3x3 identity matrix
  (is (= (identity-matrix 3) (matrix [[1 0 0] 
                                      [0 1 0] 
                                      [0 0 1]])))
  
  ;; convert a matrix to clojure vectors
  (is (= (to-vect A) [[1 2 3] 
                      [4 5 6] 
                      [7 8 9] 
                      [10 11 12]]))
  ;; one-dimensional matrices are coverted to one-dimension vectors
  (is (= (to-vect (matrix [1 2 3 4 5 6])) [1 2 3 4 5 6]))
  (is (= (to-vect (trans (matrix [1 2 3 4 5 6]))) [1 2 3 4 5 6]))
  
  ;; select the element at row 3 (i.e. fourth row) and column 2 (i.e. third column)
  (is (= (sel A 3 2) 12))
  ;; use 'true' to select an entire row or column
  (is (= (sel A true 2) (matrix [3 6 9 12])))
  (is (= (sel A 1 true) (matrix [[4 5 6]])))
  (is (= (sel A true true) A))
  ;; pass a vector of indices to select a set of rows and/or columns
  (is (= (sel A true [0 2]) (matrix [[1 3] 
                                     [4 6] 
                                     [7 9] 
                                     [10 12]])))
  (is (= (sel A [0 1] true) (matrix [[1 2 3] 
                                     [4 5 6]])))
  (is (= (sel A [1 3] [0 2]) (matrix [[4 6] 
                                      [10 12]]))) 
  
  ;; filtering: return the rows that sum to more than 6
  (is (= (matrix (filter #(> (sum %) 6) A)) (matrix [[4 5 6] 
                                                     [7 8 9] 
                                                     [10 11 12]])))
  ;; the following tests pass, but the result of these seq ops isn't a proper matrix,
  ;; rather its a sequence of row vectors that behave like a matrix except ncol 
  ;; returns 1 no matter how many columns are in the row matrices. Wrap the result
  ;; in a call to matrix (like above to get a proper matrix).
  (is (= (filter #(> (sum %) 6) A) (matrix [[4 5 6] 
                                            [7 8 9] 
                                            [10 11 12]])))
  ;; return rows where the first element is greater than 6
  (is (= (filter #(> (nth % 0) 6) A)  (matrix [[7 8 9] 
                                               [10 11 12]])))
  ;; drop the first two rows
  (is (= (drop 2 A) (matrix [[7 8 9] 
                             [10 11 12]])))
  ;; drop all but the first two rows
  (is (= (take 2 A) (matrix [[1 2 3] 
                             [4 5 6]])))
) ;; end of matrix-manip tests

(deftest matrix-arithmetic

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
  (is (= (plus [1 2 3] [1 2 3]) (matrix [2 4 6])))
  (is (= (plus [1 2 3] 1) (matrix [2 3 4])))
  (is (= (plus 1 [1 2 3]) (matrix [2 3 4])))
  
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
  (is (= (minus [1 2 3] [1 2 3]) (matrix [0 0 0])))
  (is (= (minus [1 2 3] 1) (matrix [0 1 2])))
  (is (= (minus 1 [1 2 3]) (matrix [0 -1 -2])))
  
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
  (is (= (mult [1 2 3] [1 2 3]) (matrix [1 4 9])))
  (is (= (mult [1 2 3] 2) (matrix [2 4 6])))
  (is (= (mult 2 [1 2 3]) (matrix [2 4 6])))
  
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
  (is (= (div [1 2 3] [1 2 3]) (matrix [1 1 1])))
  (is (= (div [1 2 3] 2) (matrix [1/2 1 3/2])))
  (is (= (div 2 [1 2 3]) (matrix [2 1 2/3])))
  
  ;; getting column sums
  (is (= (map #(reduce + %) (trans A)) [22 26 30]))
  (is (= (reduce plus A) (matrix [22 26 30] 3)))
  
  ;; getting row sums
  (is (= (reduce + (first A)) 6))
  (is (= (map #(reduce + %) A) [6 15 24 33]))
  (is (= (reduce plus (trans A)) (matrix [6 15 24  33] 4)))
  
  ;; getting column products
  (is (= (reduce mult A) (matrix [280 880 1944] 3)))
  (is (= (reduce mult (trans A)) (matrix [6 120 504 1320] 4)))
    
) ;; end of matrix-arithmetic tests

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.io.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; read in a dataset from a space delimited file 
(def test-data (read-dataset 
                 (str (System/getProperty "incanter.home") "/data/test.dat") 
                 :header true)) ; default delimiter: \space
;; read in a dataset from a comma delimited file 
(def test-csv-data (read-dataset 
                     (str (System/getProperty "incanter.home") "/data/test.csv") 
                     :header true 
                     :delim \,))
;; read in a dataset from a tab delimited file 
(def test-tdd-data (read-dataset 
                     (str (System/getProperty "incanter.home") "/data/test.tdd") 
                     :header true 
                     :delim \tab)) 
;; read in the iris dataset from a space delimited file 
(def iris-data (read-dataset 
                 (str (System/getProperty "incanter.home") "/data/iris.dat") 
                 :header true))
;; read in the social science survey dataset from a space delimited file 
(def ols-data (as-matrix (read-dataset 
                           (str (System/getProperty "incanter.home") "/data/olsexamp.dat")
                           :header true)))

;; convert the space-delimited dataset into a matrix
(def test-mat (as-matrix test-data))
;; convert the csv dataset into a matrix
(def test-csv-mat (as-matrix test-csv-data)) 
;; convert the tab-delimited dataset into a matrix
(def test-tdd-mat (as-matrix test-tdd-data))
;; convert the iris-data into a matrix, encoding strings into multiple dummy variables
(def iris-mat (as-matrix iris-data))

(deftest io-validation

  ;; validate matrices read from files
  (is (= (reduce plus test-mat) (matrix [1275 770 2149] 3)))
  (is (= (reduce plus test-csv-mat) (matrix [1275 770 2149] 3)))
  (is (= (reduce plus test-tdd-mat) (matrix [1275 770 2149] 3)))
  ;; confirm that iris species factor was converted to two dummy variables
  (is (= (first iris-mat) (matrix [5.10 3.50 1.40 0.20 1 0] 6)))

) ;; end of io-validation tests

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.stats.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def x (sel ols-data (range 0 2313) (range 1 10)))
(def y (sel ols-data (range 0 2313) 10))

(deftest stats-functions

  
  (is (= (map mean (trans test-mat)) [25.5 15.4 42.98]))
  (is (= (map mean (trans test-csv-mat)) [25.5 15.4 42.98]))
  (is (= (map mean (trans test-tdd-mat)) [25.5 15.4 42.98]))
  (is (= (map sd A) [1.1283791670955126 1.1283791670955126 1.1283791670955126 1.1283791670955126]))
  ;; perform matrix multiplication
  (is (= (mmult A (trans A)) (matrix [[14  32  50  68] 
                                      [32  77 122 167] 
                                      [50 122 194 266] 
                                      [68 167 266 365]])))
  ;; perform matrix multiplication on more than two matrices
  (is (= (mmult A (trans A) A) (matrix [[1172 1336 1500]
                                        [2864 3262 3660]
                                        [4556 5188 5820]
                                        [6248 7114 7980]])))
                                 
  ;; calculate the inverse of a matrix
  (is (= (solve (matrix [[2 0 0] [0 2 0] [0 0 2]])) (diag [1/2 1/2 1/2])))
  ;; calculate the Cholesky decomposition of a matrix
  (is (= (chol (covariance test-mat))
         (matrix [[14.577379737113251 5.2107570114319826 21.07052616887616] 
                  [0.0 0.8984403381871381 0.17090373166850123] 
                  [0.0 0.0 14.834572296083813]])))
  
  ;; calculate the mean of the elements of a one-dimensional matrix
  (is (= (mean (sel x true 4)) 13.588413316039775))
  ;; calculate the variance of the elements of a one-dimensional matrix
  (is (= (variance (sel x true 4)) 8.534675379268974))
  ;; get the covariance matrix based on the columns of a data matrix
  (is (= (covariance test-mat)) 
      (matrix [212.50  75.96 307.15 75.96  27.96 109.95 307.15 109.95 664.06] 4))
  ;; get the covariance between two variables
  (is (= (covariance (sel x true 4) (sel x true 5)) 37.8696379778356160))
  ;; calculate the standard deviation of a variable
  (is (= (sd (sel x true 4)) 2.921732570471636))
  ;; calculate the median of a variable
  (is (= (median (sel x true 4)) 10))
  ;; calculate the sum of values in a vector or 1D matrix
  (is (= (sum (sel x true 4)) 31430.0))
  ;; calculate the sum of squares of values in a vector or 1D matrix
  (is (= (sum-of-squares (sel x true 4)) 446816.0))
  ;; calculate the product of values in a vector or 1D matrix
  (is (= (prod [1 2 3 4 5 6]) 720))
  
  ;; generate a sample of standard normal data
  (def std-normal-data (rnorm 1000))
  (is (= (count std-normal-data) 1000))
  (is (= (Math/round (mean std-normal-data)) 0))
  (is (= (Math/round (sd std-normal-data)) 1))
  
  ;; generate a sample of normal data with mean = 10 and sd = 5
  (def nonstd-normal-data (rnorm 1000 :mean 10 :sd 5))
  (is (= (count nonstd-normal-data) 1000))
  (is (= (Math/round (mean nonstd-normal-data)) 10))
  (is (= (Math/round (sd nonstd-normal-data)) 5))
  
) ;; end of stats-functions tests


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.bayes.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; These tests will fail periodically due to the stochastic nature of the,
;; simulations. Examine the failure report to determine if it's a "normal" failure.

(deftest bayes-simulations
  
  ;-------------------------------------------------------------------------------
  (time (def b-reg-noref (bayes-regression-noref 5000 x y)))
  (is (= (map #(Math/round (* 10 %)) (map mean (trans (:coef b-reg-noref)))) [196 0 -24 5 1 0 7 4 3]))
  (is (= (Math/round (mean (:var b-reg-noref))) 21))
  
  ;-------------------------------------------------------------------------------
  (time (def b-reg-full (bayes-regression-full 5000 x y)))
  (is (= (map #(Math/round (* 10 %)) (map mean (trans (:coef b-reg-full)))) [196 0 -24 5 1 0 7 4 3]))
  (is (= (Math/round (mean (:var b-reg-full))) 21))
  
  ;-------------------------------------------------------------------------------
  (time (def b-reg (bayes-regression 20000 x y)))
  (is (= (map #(Math/round (* 10 %)) (map mean (trans (:coef b-reg)))) [196 0 -24 5 1 0 7 4 3]))
  (is (= (Math/round (mean (:var b-reg))) 21))
  
  ;-------------------------------------------------------------------------------
  ;(time (def b-reg-mh (bayes-regression-mh 20000 x y))) ; takes too long to do every time
  ;(is (= (map #(Math/round (* 10 %)) (map mean (trans (:coef b-reg-mh)))) [196 0 -24 5 1 0 7 4 3]))
  ;(is (= (Math/round (mean (:var b-reg-mh))) 21))

) ;; end of bayes-simulation tests



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; TESTS FOR incanter.charts.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(deftest charts
  
  (plot (histogram (rnorm 1000)))
  (plot (histogram (rgamma 1000)))
  (plot (histogram (runif 1000)))
  (save-png (histogram (rnorm 1000)) "/tmp/norm_hist.png")
  
  (plot (histogram (rgamma 1000) 
                   :nbins 30 
                   :title "Gamma Distribution" 
                   :x-label "Value"))
  
  
  (plot (scatter 
          (sel test-mat true 1) 
          (sel test-mat true 2) 
          :series-lab "Test data col 1 versus col 2"))
  
  
  (def plot1 (scatter (rnorm 100) (rnorm 100)))
  (plot plot1)
  (add-series plot1 (rnorm 100) (rnorm 100))
  (add-series plot1 (rnorm 100) (rnorm 100))
  (add-series plot1 (rnorm 100) (rnorm 100))
  (add-series plot1 (rnorm 100) (rnorm 100))
  
  (set-title plot1 "new title") 
  (set-x-label plot1 "new x label")
  (set-y-label plot1 "new y label")
  
  
  (def hist0 (histogram (rnorm 100)))
  (plot hist0)
  (add-series hist0 (rgamma 100))
  (set-alpha hist0 0.5)
  
  (def boxplt (boxplot (rgamma 1000))) 
  (plot boxplt)
  (add-series boxplt (rgamma 1000))
  (add-series boxplt (rgamma 1000))
  (add-series boxplt (rgamma 1000))
  (add-series boxplt (rgamma 1000))
  
  
  (def chart1 (xyplot (range 100) (range 100))) 
  (plot chart1) 
  (add-series chart1 (range 100) (mult 1/2 (range 100)))
  
  
  (def x1 (range -10 10 0.01))
  (def chart2 (xyplot x1 (pow x1 2)))
  (plot chart2) 
  (add-series chart2 x1 (mult 1/2 (pow x1 2)))
  
  
  (def x2 (range 0 4 0.01))
  (def chart2 (xyplot x2 (exp x1)))
  (plot chart2)
 
) ;; end of charts tests


(run-tests 'tests.runtests)

