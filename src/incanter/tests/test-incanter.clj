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


(ns incanter.tests.test-incanter)

(use 'clojure.contrib.test-is)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.matrix.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use 'incanter.matrix)

(def A (matrix [[1 2 3] [4 5 6] [7 8 9] [10 11 12]]))

(is (= (nrow A) 4))
(is (= (ncol A) 3))
(is (= (diag A) [1 5 9]))
(is (= (diag [1/2 1/2 1/2]) (matrix [[1/2 0 0] [0 1/2 0] [0 0 1/2]])))
(is (= (first A) (matrix [1 2 3] 3))) ; first of A is a row matrix
(is (= (rest A) (matrix [[4 5 6] [7 8 9] [10 11 12]]))) 
(is (= (conj A [13 14 15]) (matrix [[1 2 3] [4 5 6] [7 8 9] [10 11 12] [13 14 15]])))
(is (= (conj A A) (matrix [[1 2 3] [4 5 6] [7 8 9] [10 11 12] [1 2 3] [4 5 6] [7 8 9] [10 11 12]])))
(is (= (seq A) A))
(is (= (first (first A)) 1))
(is (= (nth (nth A 1) 1)))
(is (= (count A) 4))
(is (= (count (first A)) 3))
(is (= (count (rest A)) 3))
(is (= (count (rest (first A))) 2))
(is (= (trans A) (matrix [[1 4 7 10] [2 5 8 11] [3 6 9 12]])))
(is (= (trans (trans A)) A))
(is (= (first (trans A)) (matrix [1 4 7 10] 4)))
(is (= (rbind (first A) (rest A)) A))
(is (= (rbind A A A) (rbind A (rbind A A))))
(is (= (rbind [1 2 3] [4 5 6]) (matrix [[1 2 3] [4 5 6]])))
(is (= (rbind A [13 14 15]) (matrix [[1 2 3] [4 5 6] [7 8 9] [10 11 12] [13 14 15]])))
(is (= (rbind [13 14 15] A) (matrix [[13 14 15] [1 2 3] [4 5 6] [7 8 9] [10 11 12]])))

(is (= (cbind (trans (nth (trans A) 0)) (trans (nth (trans A) 2))) (matrix [[1 3] [4 6] [7 9] [10 12]])))
(is (= (cbind [[1] [2] [3]] [[4] [5] [6]] [[7] [8] [9]]) (matrix [[1 4 7] [2 5 8] [3 6 9]])))
(is (= (cbind [13 14 15 16] A) (matrix [[13 1 2 3] [14 4 5 6] [15 7 8 9] [16 10 11 12]])))
(is (= (cbind A [13 14 15 16]) (matrix [[1 2 3 13] [4 5 6 14] [7 8 9 15] [10 11 12 16]])))

(is (= (matrix 99 3 2) (matrix [[99 99] [99 99] [99 99]])))
(is (= (matrix 3 2) (matrix [[0 0] [0 0] [0 0]]))) 
(is (= (matrix [1 2 3 4 5 6 7 8 9] 3) (matrix [[1 2 3] [4 5 6] [7 8 9]])))

(is (= (to-vect A) [[1 2 3] [4 5 6] [7 8 9] [10 11 12]]))
(is (= (to-vect (matrix [1 2 3 4 5 6])) [1 2 3 4 5 6]))
(is (= (to-vect (trans (matrix [1 2 3 4 5 6]))) [1 2 3 4 5 6]))

(is (= (sel A 3 2) 12))
(is (= (sel A true 2) (matrix [[3] [6] [9] [12]])))
(is (= (sel A 1 true) (matrix [[4 5 6]])))
(is (= (sel A true [0 2]) (matrix [[1 3] [4 6] [7 9] [10 12]])))
(is (= (sel A [0 1] true) (matrix [[1 2 3] [4 5 6]])))
(is (= (sel A [1 3] [0 2]) (matrix [[4 6] [10 12]]))) 
(is (= (sel A true true) A))

(is (= (drop 2 A) (matrix [[7 8 9] [10 11 12]])))
(is (= (take 2 A) (matrix [[1 2 3] [4 5 6]])))

(is (= (plus A A A) (matrix [[3 6 9] [12 15 18] [21 24 27] [30 33 36]])))
(is (= (plus A 1) (matrix [[2 3 4] [5 6 7] [8 9 10] [11 12 13]]))) 
(is (= (plus 1 A) (matrix [[2 3 4] [5 6 7] [8 9 10] [11 12 13]]))) 
(is (= (plus [1 2 3] [1 2 3]) (matrix [2 4 6] 1)))
(is (= (plus [1 2 3] 1) (matrix [2 3 4] 1)))
(is (= (plus 1 [1 2 3]) (matrix [2 3 4] 1)))

(is (= (minus A A A) (matrix [[-1 -2 -3] [-4 -5 -6] [-7 -8 -9] [-10 -11 -12]])))
(is (= (minus A 1) (matrix [[0 1 2] [3 4 5] [6 7 8] [9 10 11]]))) 
(is (= (minus 1 A) (matrix [[0 -1 -2] [-3 -4 -5] [-6 -7 -8] [-9 -10 -11]]))) 
(is (= (minus [1 2 3] [1 2 3]) (matrix [0 0 0] 1)))
(is (= (minus [1 2 3] 1) (matrix [0 1 2] 1)))
(is (= (minus 1 [1 2 3]) (matrix [0 -1 -2] 1)))

(is (= (mult A A A) (matrix [[1 8 27] [64 125 216] [343 512 729] [1000 1331 1728]])))
(is (= (mult A 2) (matrix [[2 4 6] [8 10 12] [14 16 18] [20 22 24]]))) 
(is (= (mult 2 A) (matrix [[2 4 6] [8 10 12] [14 16 18] [20 22 24]]))) 
(is (= (mult [1 2 3] [1 2 3]) (matrix [1 4 9] 1)))
(is (= (mult [1 2 3] 2) (matrix [2 4 6] 1)))
(is (= (mult 2 [1 2 3]) (matrix [2 4 6] 1)))

(is (= (div A A A) (matrix [[1 1/2 1/3] [1/4 1/5 1/6] [1/7 1/8 1/9] [1/10 1/11 1/12]])))
(is (= (div A 2) (matrix [[1/2 1 3/2] [4/2 5/2 6/2] [7/2 8/2 9/2] [10/2 11/2 12/2]])))
(is (= (div 2 A) (matrix [[2/1 1 2/3] [2/4 2/5 2/6] [2/7 2/8 2/9] [2/10 2/11 2/12]])))
(is (= (div [1 2 3] [1 2 3]) (matrix [1 1 1] 1)))
(is (= (div [1 2 3] 2) (matrix [1/2 1 3/2] 1)))
(is (= (div 2 [1 2 3]) (matrix [2 1 2/3] 1)))

(is (= (map #(reduce + %) (trans A)) [22 26 30]))
(is (= (reduce plus A) (matrix [22 26 30] 3)))

(is (= (reduce + (first A)) 6))
(is (= (map #(reduce + %) A) [6 15 24 33]))
(is (= (reduce plus (trans A)) (matrix [6 15 24  33] 4)))

(is (= (reduce mult A) (matrix [280 880 1944] 3)))
(is (= (reduce mult (trans A)) (matrix [6 120 504 1320] 4)))
  

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.io.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use 'incanter.io)

(def test-mat (read-matrix (str (System/getProperty "incanter.home") "/data/test.dat")))
(def test-csv-mat (matrix (rest (read-dataset (str (System/getProperty "incanter.home") "/data/test.csv") :delim \,))))
(def test-tdd-mat (matrix (rest (read-dataset (str (System/getProperty "incanter.home") "/data/test.tdd") :delim \tab))))
(def test-data (read-dataset (str (System/getProperty "incanter.home") "/data/test.dat"))) ; default delimiter: \space
(def test-csv-data (read-dataset (str (System/getProperty "incanter.home") "/data/test.csv") :delim \,))
(def test-tdd-data (read-dataset (str (System/getProperty "incanter.home") "/data/test.tdd") :delim \tab)) 
(def iris-data (read-dataset (str (System/getProperty "incanter.home") "/data/iris.dat")))
(def ols-data (read-matrix (str (System/getProperty "incanter.home") "/data/olsexamp.dat")))

(is (= (reduce plus test-mat) (matrix [1275 770 2149] 3)))
(is (= (reduce plus test-csv-mat) (matrix [1275 770 2149] 3)))
(is (= (reduce plus test-tdd-mat) (matrix [1275 770 2149] 3)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.stats.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use 'incanter.stats)

(is (= (map mean (trans test-mat)) [25.5 15.4 42.98]))
(is (= (map mean (trans test-csv-mat)) [25.5 15.4 42.98]))
(is (= (map mean (trans test-tdd-mat)) [25.5 15.4 42.98]))
(is (= (map sd A) [1.1283791670955126 1.1283791670955126 1.1283791670955126 1.1283791670955126]))

(is (= (mmult foo (trans foo)) (matrix [[14  32  50  68] [32  77 122 167] [50 122 194 266] [68 167 266 365]])))
(is (= (solve (matrix [[2 0 0] [0 2 0] [0 0 2]])) (diag [1/2 1/2 1/2])))

(is (= (chol (covariance test-mat))
       (matrix [[14.577379737113251 5.2107570114319826 21.07052616887616] 
                [0.0 0.8984403381871381 0.17090373166850123] 
                [0.0 0.0 14.834572296083813]])))

(def x (sel ols-data (range 0 2313) (range 1 10)))
(def y (sel ols-data (range 0 2313) 10))

(is (= (covariance test-mat)) 
    (matrix [212.50  75.96 307.15 75.96  27.96 109.95 307.15 109.95 664.06] 4))
(is (= (covariance (sel x true 4) (sel x true 5)) 37.8696379778356160))
(is (= (sum-of-squares (sel x true 4)) 446816.0))
(is (= (sum (sel x true 4)) 31430.0))
(is (= (variance (sel x true 4)) 8.534675379268974))
(is (= (mean (sel x true 4)) 13.588413316039775))
(is (= (sd (sel x true 4)) 2.921732570471636))
(is (= (median (sel x true 4)) 10))
(is (= (prod [1 2 3 4 5 6]) 720))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.bayes.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use 'incanter.bayes)

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




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; TESTS FOR incanter.charts.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use 'incanter.charts)

; test plots
(plot (histogram (rnorm 1000)))
(plot (histogram (rgamma 1000)))
(plot (histogram (runif 1000)))
(save-png (histogram (rnorm 1000)) "/Users/dliebke/Desktop/histogram.png")

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

