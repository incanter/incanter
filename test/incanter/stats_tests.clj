
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

(ns incanter.stats-tests
  (:use clojure.test 
        (incanter core stats)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.stats.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; define a simple matrix for testing
(def A (matrix [[1 2 3] 
                [4 5 6] 
                [7 8 9] 
                [10 11 12]]))

(def V (matrix [[1 2 3] 
                [4 5 6] 
                [7 8 9] 
                [10 11 12]]))

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


  
(deftest mean-test
  (is (= (map mean (trans test-mat)) [108.0 130.0])))

(deftest variance-test
  (is (= (map variance (trans test-mat)) [1001.5833333333334 5416.666666666667])))

(deftest sd-test
  ;; calculate the standard deviation of a variable
  (is (= (sd x) 31.6478013980961))
  (is (= (map sd A) [1.0 1.0 1.0 1.0])))

(deftest covariance-test
  ;; get the covariance between two variables
  (is (= (Math/round (covariance x y)) 2263)))

(deftest median-test
  ;; calculate the median of a variable
  (is (= (median x) 113)))
  
 
(deftest sample-normal-tests
  ;; generate a sample of standard normal data
  (def std-normal-data (sample-normal 1000))
  (is (= (count std-normal-data) 1000))
  (is (= (Math/round (mean std-normal-data)) 0))
  (is (= (Math/round (sd std-normal-data)) 1))
  
  ;; generate a sample of normal data with mean = 10 and sd = 5
  (def nonstd-normal-data (sample-normal 1000 :mean 10 :sd 5))
  (is (= (count nonstd-normal-data) 1000))
  (is (= (Math/round (mean nonstd-normal-data)) 10))
  (is (= (Math/round (sd nonstd-normal-data)) 5)))

(deftest sample-tests
  ;; test sample function
  (is (not= (sample (range 10) :replacement false) (range 10)))
  (is (= (count (sample (range 10))) 10))
  (is (= (count (sample (range 10) :size 5)) 5))
  (is (= (count (sample (range 10) :size 5 :replacement false)) 5))
  (is (= (count (sample (range 10) :replacement false)) (count (range 10))))
  (is (= (into #{} (sample (range 10) :replacement false)) (into #{} (range 10))))) 

