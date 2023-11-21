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
        (incanter core stats))
  (:require [clojure.core.matrix :as m]
            [clojure.core.matrix.linear :as l]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.stats.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def dataset1 (dataset [:a :b :c] [[1 2 3] [4 5 6] [7 8 9] [10 11 12]]))


(def summary-ds0 (to-dataset [[1] [4] [7]]))
(def summary-ds1 (to-dataset [[1] [3.142] [7]]))
(def summary-ds2 (to-dataset [["a"] ["b"] ["c"]]))
(def summary-ds3 (to-dataset [[:a] [:b] [:c]]))
(def summary-ds4 (to-dataset [[:a] ["b"] [:c]]))
(def summary-ds5 (to-dataset [[1] [2.1] [:c]]))
(def summary-ds6 (to-dataset [[1] [2.1] ["c"]]))
(def summary-ds7 (to-dataset [[1] [2.1] [nil]]))

(def summary-ds8 (to-dataset [["a"] ["b"] ["c"] ["d"] ["b"] ["e"] ["a"] ["b"] ["f"] ["a"] ["b"] ["e"]]))
(def summary-ds9 (to-dataset [["a" 1.2] [":b" 3] [:c 0.1] ["d" 8] ["b" 9] ["e" 7.21] ["a" 1E1] ["b" 6.0000] ["f" 1e-2] ["a" 3.0] ["b" 4] ["e" 5]]))

(def precision-ds0 (dataset
                    [:large :medium :small :sensitive]
                    [[1000000023   1000.000669  1.00000003   1000000.000023]  [1000000040  1000.000041  1.000000039  1000000.000035]  [1000000064  1000.000059  1.000000065  1000000.000057]
                     [1000000064   1000.000063  1.000000065  1000000.000062]  [1000000120  1000.000121  1.000000116  1000000.000114]  [1000000103  1000.000104  1.000000099  1000000.000096]
                     [1000000035   1000.00004   1.000000037  1000000.000031]  [1000000055  1000.000062  1.000000058  1000000.000054]  [1000000095  1000.000095  1.000000094  1000000.000093]
                     [1000000085   1000.000091  1.000000091  1000000.000082]  [1000000049  1000.00005   1.000000044  1000000.000041]  [1000000070  1000.000074  1.000000068  1000000.000067]
                     [1000000063   1000.000063  1.000000063  1000000.000059]  [1000000083  1000.000076  1.000000079  1000000.000074]  [1000000086  1000.000083  1.000000084  1000000.000078]
                     [1000000055   1000.000061  1.000000058  1000000.000053]  [1000000032  1000.00003   1.000000037  1000000.000028]  [1000000069  1000.000069  1.000000078  1000000.000069]
                     [1000000089   1000.00009   1.000000092  1000000.000086]  [1000000125  1000.000116  1.00000012   1000000.000115]  [1000000063  1000.000058  1.000000055  1000000.000054]
                     [1000000073   1000.000074  1.000000077  1000000.000072]  [1000000037  1000.000035  1.000000035  1000000.000027]  [1000000083  1000.000084  1.000000086  1000000.000081]
                     [1000000070   1000.000067  1.000000066  1000000.000063]  [1000000068  1000.000072  1.000000071  1000000.000063]  [1000000106  1000.000105  1.00000011   1000000.0001]
                     [1000000097   1000.000091  1.000000091  1000000.00009]   [1000000076  1000.000077  1.00000007   1000000.00007]   [1000000045  1000.00004   1.000000044  1000000.000037]
                     [1000000103   1000.000108  1.000000109  1000000.000099]  [1000000110  1000.000117  1.000000109  1000000.000107]  [1000000112  1000.000114  1.00000012   1000000.000111]
                     [1000000114   1000.000117  1.00000011   1000000.000108]  [1000000098  1000.000099  1.000000101  1000000.000096]  [1000000046  1000.000039  1.000000045  1000000.000039]
                     [1000000051   1000.000046  1.000000042  1000000.000042]  [1000000110  1000.000105  1.000000108  1000000.000101]  [1000000056  1000.000057  1.000000056  1000000.00005]]))

(deftest variance-precision-test
  (is (within 1E-13 7.354943E-10 (variance ($ :sensitive precision-ds0))))
  (is (within 1E-19 7.315992E-16 (variance ($ :small precision-ds0))))
  (is (within 1E-13 9.697281E-9 (variance ($ :medium precision-ds0))))
  (is (within 0.001 736.7868 (variance ($ :large precision-ds0)))))

(deftest odds-ratio-test
  (let [p1 9/10
	p2 2/10]
    (is (= 36
	   (odds-ratio p1 p2)))))

(deftest correlation-precision-test
  (is (within 1E-6 -0.05412184 (correlation ($ :large precision-ds0)  ($ :medium precision-ds0))))
  (is (within 1E-6 0.9865475   (correlation ($ :large precision-ds0)  ($ :small precision-ds0))))
  (is (within 1E-6 -0.01092723 (correlation ($ :medium precision-ds0) ($ :small precision-ds0))))
  (is (within 1E-5 0.9941202   (correlation ($ :large precision-ds0)  ($ :sensitive precision-ds0))))
  (is (within 1E-5 -0.02104787 (correlation ($ :medium precision-ds0) ($ :sensitive precision-ds0))))
  (is (within 1E-5 0.9942994   (correlation ($ :small precision-ds0)  ($ :sensitive precision-ds0)))))

(deftest jaccard-examples
 (is (= 2/6
	(jaccard-index #{1 2 3 4} #{3 4 5 6})))
 (is (= 2/5
	(jaccard-distance #{1 2 3 4} #{2 3 4 5}))))

;; Each set has four elements, and the intersection of these two sets has only one element: ht.
;;     {ni,ig,gh,ht}
;;     {na,ac,ch,ht}
;; Plugging this into the formula, we calculate, s = (2 · 1) / (4 + 4) = 0.25.
(deftest dice-string
  (is
   (== 0.25
      (dice-coefficient-str "night" "nacht"))))

(deftest get-ngrams
  (is (= #{"gh" "ht" "ni" "ig"}
	 (bigrams "night"))))

;;TODO: think about a hamming distance that measure how far something is off for k-way classification rather than just binary classification.
(deftest hamming-ints-and-strings
 (is (= 2
	(hamming-distance 1011101 1001001)))
 (is (= 3
	(hamming-distance 2173896 2233796)))
 (is (= 3
        (hamming-distance "toned" "roses"))))

(deftest lee-distance-withq
 (is (= (+ 1 2 0 3)
        (lee-distance 3340 2543 6))))

;;since the following three edits change one into the other, and there is no way to do it with fewer than three edits:
   ;; 1. kitten → sitten (substitution of 's' for 'k')
   ;; 2. sitten → sittin (substitution of 'i' for 'e')
   ;; 3. sittin → sitting (insert 'g' at the end).
(deftest levenshtein-kitten
 (is (= 3
	(levenshtein-distance "kitten" "sitting")))
 (is (= 3
	(levenshtein-distance "Sunday" "Saturday"))))

(deftest damerau-levenshtein-distance-tests
  (let [a "TO"
	b "OT"
	c "OST"]
    (is (= 1 (damerau-levenshtein-distance a b)))
    (is (= 1 (damerau-levenshtein-distance b c)))
    (is (= 3 (damerau-levenshtein-distance a c)))))

(deftest scalar-abs-test
  (is
    (= 9223372036854775808
       (scalar-abs -9223372036854775808))))

(deftest summary-datasets
  (is (summarizable? 0 summary-ds0))
  (is (summarizable? 0 summary-ds1))
  (is (summarizable? 0 summary-ds2))
  (is (summarizable? 0 summary-ds3))
  (is (summarizable? 0 summary-ds4))
  (is (not (summarizable? 0 summary-ds5)))
  (is (not (summarizable? 0 summary-ds6)))
  (is (summarizable? 0 summary-ds7)))

(defn mean-test [m]
  (is (= (map mean (trans m)) [108.0 130.0])))

(defn sample-mean-test []
 (is (= 3.0
        (mean [2 3 4]))))

(defn variance-test [m]
  (is (= (map variance (trans m)) [1001.5833333333334 5416.666666666667])))

(defn sd-test [m x]
  ;; calculate the standard deviation of a variable
  (is (= (sd x) 31.6478013980961))
  (is (= (map sd m) [1.0 1.0 1.0 1.0]))
  (is (= 2.138089935299395 (sd [2 4 4 4 5 5 7 9]))))

(defn median-test [x]
  ;; calculate the median of a variable
  (is (= (median x) 113.0))
  (is (Double/isNaN (median []))))

(defn covariance-test [x y]
  ;; get the covariance between two variables
  (is (= (Math/round (covariance x y)) 2263)))

(defn covariance-matrix-test []
  ;; get the covariance from a matrix
  (let [in [[9   8  7]
            [90 80 70]
            [10  5  0]]
        result [[2160.333 1971.5 1782.667]
                [1971.500 1803.0 1634.500]
                [1782.667 1634.5 1486.333]]]
    (is (m/equals (to-vect result)
          (to-vect (covariance in))
          1E-3))))

(defn covariance-test-2 []
  (is (= 5.333333333333333
	 (covariance
	  [3 1 3 9]
	  [4 4 8 8]))))

(defn pearson-test []
  (is (within 0.0001 1
	      (correlation [5 6 7 8] [8 9 10 11]))))

(defn correlation-ratio-example []
  (let [algebra [45, 70, 29, 15, 21]
	geometry [40, 20, 30 42]
	statistics [65, 95, 80, 70, 85, 73]]

    (is (within 0.0001
		0.8386
		(correlation-ratio
		 algebra
		 geometry
		 statistics)))))

(defn correlation-matrix []
  (let [mat [[1 2 3]
             [1 1 3]
             [2 1 4]]]
    (is (m/equals [[1 -0.5 1]
                   [-0.5 1 -0.5]
                   [1 -0.5 1]]
          (correlation mat)
          1E-7))))

(defn sample-test [m]
  ;; test sample function
  (is (not= (sample (range 10) :replacement false) (range 10)))
  (is (= (count (sample (range 10))) 10))
  (is (= (count (sample (range 10) :size 5)) 5))
  (is (= (count (sample (range 10) :size 5 :replacement false)) 5))
  (is (= (count (sample (range 10) :replacement false)) (count (range 10))))
  (is (= (into #{} (sample (range 10) :replacement false)) (into #{} (range 10))))
  (is (= (nrow (sample m :size 3)) 3))
  (is (= (nrow (sample dataset1 :size 3)) 3)))

(defn ranking-test []
  (is (=
       {97 2, 99 3, 100 4, 101 5, 103 6, 106 7, 110 8, 112 9, 113 10, 86 1}
       (into {}
             (map (fn [[x [y & more]]] [x y])
                  (seq (rank-index [106 86 100 101 99 103 97 113 112 110]))))
       )))

(defn spearmans-rho-test []
  (is (within 0.000001 (float -29/165)
	 (spearmans-rho [106 86 100 101 99 103 97 113 112 110]
			[7 0 27 50 28 29 20 12 6 17]))))

(defn kendalls-tau-test []
  (is (= 23/45
	 (kendalls-tau [4 10 3 1 9 2 6 7 8 5]
		       [5 8 6 2 10 3 9 4 7 1])))
  (is (= 9/13
	 (kendalls-tau
	  [1 3 2 4 5 8 6 7 13 10 12 11 9]
	  [1 4 3 2 7 5 6 8 9 10 12 13 11]))))

(defn concordancy-test []
  (is (discordant? [[4 2] [2 4]]))
  (is (= 4
	 (discordant-pairs [1 2 3 4 5]
			   [3 4 1 2 5]))))

(defn kendalls-tau-distance-test []
  (is (= 4
	 (kendalls-tau-distance [1 2 3 4 5]
				[3 4 1 2 5])))
  (is (= 2/5
	 (normalized-kendall-tau-distance [1 2 3 4 5]
					  [3 4 1 2 5]))))

(defn kurtosis-test []
  (let [test-sample [2.00 2.00 2.00 2.00 2.00
                     2.00 2.00 2.00 2.00 2.00
                     2.00 2.00 2.00 2.00 2.00
                     2.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 3.00 3.00 3.00
                     3.00 3.00 4.00 4.00 4.00
                     4.00 4.00 4.00 4.00 4.00
                     4.00 4.00 4.00 4.00 4.00
                     4.00 4.00 4.00 4.00 2.00]]
    (testing "correctness of result"
      (is (= -0.11735294117647133 (kurtosis test-sample))))
    (testing "invariance of calculation"
      (let [kurtosis-diff (- (kurtosis (map (partial * 10) test-sample))
                             (kurtosis test-sample))]
        (is (< kurtosis-diff 1e-15))))
    ))

(defn gamma-coefficient-test []
  (is (= 1
       (gamma-coefficient [1 2 3]
                          [4 5 6])))
  (is (= -1
       (gamma-coefficient [1 2 3]
                          [0 -1 -2])))
  (is (= 1/3
       (gamma-coefficient [1 2 3]
                          [0 -1 1]))))

(deftest euclid-test
  (is
   (= 2.8284271247461903
      (euclidean-distance [2 4 3 1 6]
			  [3 5 1 2 5]))))

(deftest manhattan-test
  (is
   (= (+ 1.0 1 2 1 1)
      (manhattan-distance [2 4 3 1 6]
			  [3 5 1 2 5]))))

(deftest minkowski-test
  (is
   (= 2.2894284851066637
      (minkowski-distance
        [2 4 3 1 6] [3 5 1 2 5] 3))))

(deftest chebyshev-test
  (is
   (== 2
      (chebyshev-distance [2 4 3 1 6]
			  [3 5 1 2 5]))))

(deftest cosine-sim-test
  (is
   (=  0.938572618717412
       (cosine-similarity  [2 4 3 1 6]
			   [3 5 1 2 5]))))

(deftest tanimoto-sim-test
  (is
   (=  0.8840579710144928
       (tanimoto-coefficient  [2 4 3 1 6]
			      [3 5 1 2 5]))))

(defn simple-p-value-test []
  (testing "Basic p-value testing"
    (is (= 1.0 (simple-p-value (range 1 11) 5.5)))
    (is (< 0.999 (simple-p-value (range 1 11) 5.499)))))

(defn t-test-test []
  (testing "Return values for a t-test"
    (let [t-test-result
          (t-test
           [1,1,1,1,2,2,2,4,4,4,4]
           :y [1,1,2,2,2,4,4])]
      (is (> 1 (:p-value t-test-result))))
    (let [x [8.44 7.16 16.94 9.59 13.25 12.94 11 5.61 10.6 12.81]
          y [32.66 31.66 33.28 39.81 30.29 25.95 30.74 38.4 26.67 41.49]
          paired-t-test-result (t-test x :y y :paired true)
          unpaired-t-test-result (t-test x :y y)]

      (is (:df paired-t-test-result) 9)
      (is (not= (:df paired-t-test-result) (:df unpaired-t-test-result))))))

(defn benford-law-test []
  (let [coll (matrix [1131111 623946 325911 1799255 199654 299357 3819495 359578 285984 2214801 341104 1303129 444480 295177 450269 1758026 498061 422457 315689 1160446 573568 253962 515211 998573 677829 1289257 572988 482990 765723 337178])]
    (is
      (= (format "%.8f" 5.4456385557467595)
         (format "%.8f" (:X-sq (benford-test coll)))))))

(defn linear-model-with-zero-ss []
  ;; pre 1.5.0 linear model would have a divide by zero exception with this data
  (let [data [0.0 2.0 0.0 0.0 0.0 1.0 1.0 3.0 0.0 2.0 0.0 1.0 2.0 0.0 0.0 0.0 0.0 1.0 2.0 0.0 1.0 1.0]
        lm (linear-model data data)]
    (is (= 1.0 (:r-square lm)))))

(defn linear-model-r2-test []
  (let [x [10 12 10 15 14 12 13 15 16 14 13 12 11 10 13 13 14 18 17 14]
        y [3.33 2.92 2.56 3.08 3.57 3.31 3.45 3.93 3.82 3.70
           3.26 3.00 2.74 2.85 3.33 3.29 3.58 3.85 4.00 3.50]
        lm (linear-model y x)]
    (testing "Linear Model R^2 tests"
      (is (m/equals 0.6682675 (:r-square lm)  1E-7))
      (is (m/equals 0.6292401 (:adj-r-square lm) 1E-7)))))

(defn simple-regresssion-test []
 (let [r (simple-regression [2 4] [1 3])]
   (is (m/equals 3.0 (predict r 2)))))

(defn chisq-test-test []
  (let [mat (matrix [[31 12] [9 8]])
        {:keys [X-sq col-levels row-margins table
                p-value df probs col-margins E
                row-levels two-samp? N]} (chisq-test :table mat :correct false)]

    (is (m/equals X-sq 2.0109439124487 1E-7))
    (is (= col-levels (list 0 1)))
    (is (and
         (m/equals (get row-margins 0) 43)
         (m/equals (get row-margins 1) 17)))
    (is (= table mat))
    (is (m/equals p-value 0.15616812746636347 1E-7))
    (is (and
         (m/equals (get col-margins 0) 40)
         (m/equals (get col-margins 1) 20)))
    (is (= row-levels (list 0 1)))
    (is (true? two-samp?))
    (is (m/equals N 60))))

(defn principal-components-test [m]
  (testing "principal components results"
    (let [pca      (principal-components m)
          rotation (:rotation pca)
          std-dev  (:std-dev  pca)]
      (is (not= rotation nil))
      (is (not= std-dev nil))
      (is (m/equals [[0.5773503 0.5773503 0.5773503]
                     [0.8164966 0.4082483 0.4082483]
                     [0.        0.7071068 0.7071068]]
            (abs rotation)
            1E-6)))))

(deftest variable-seed-test 
  (testing " different seeds give different results"
  (let [ compare-fn (fn [samp-fn] 
                      (every? (fn [[a b]] (= a b))  
                     (partition 2 1 
                                (repeatedly 100 samp-fn))))]
    ;;with the same seed, should be equal
      (is (compare-fn #(first (sample-uniform 1 :seed 29))))
      ;;with different (no) seed, should be different
      (is (not (compare-fn #(first (sample-uniform 1)))))))) 

(deftest compliance-test
  (doseq [impl [:clatrix :ndarray :persistent-vector :vectorz]]
    (set-current-implementation impl)
    (let [m1 (matrix [[1 2 3]
                      [4 5 6]
                      [7 8 9]
                      [10 11 12]])
          m2 (matrix [[39      10 ]
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
          x (sel m2 :cols 0)
          y (sel m2 :cols 1)]
      (println (str "compliance test " impl))
      (mean-test m2)
      (sample-mean-test)
      (variance-test m2)
      (sd-test m1 x)
      (median-test x)
      (covariance-test x y)
      (covariance-matrix-test)
      (covariance-test-2)
      ;; single value decomposition (SVD) not yet implemented for some
      ;; types in core.matrix, thus skipping the test then
      (when-not (some #{impl} '(:ndarray :persistent-vector))
        (principal-components-test m1))
      (pearson-test)
      (correlation-ratio-example)
      (correlation-matrix)
      (sample-test m2)
      (ranking-test)
      (spearmans-rho-test)
      (kendalls-tau-test)
      (concordancy-test)
      (kendalls-tau-distance-test)
      (kurtosis-test)
      (gamma-coefficient-test)
      (euclid-test)
      (manhattan-test)
      (minkowski-test)
      (chebyshev-test)
      (cosine-sim-test)
      (tanimoto-sim-test)
      (simple-p-value-test)
      (t-test-test)
      (benford-law-test)
      (linear-model-with-zero-ss)
      (linear-model-r2-test)
      (simple-regresssion-test)
      (chisq-test-test))))

;;;;;; TESTS FOR ROBUST REGRESSION AND IRWLS ;;;;;;
(defn simulation []
  ;; number of obvservations
  (def n-obs 100)
  ;; independent variable
  (def x (sample-mvn n-obs :sigma (diag [1 1])))
  ;; parameters
  (def beta-irwls (matrix [0.5 -0.3] 1))
  ;; unbiased dependent variable
  (def y (m/get-column
          (plus (mmult x beta-irwls)
                (sample-mvn (nrow x)
                            :sigma (diag [1]))) 0))
  ;; sample from random uniform. this probability determines
  ;; whether an obvervation is an outlier
  (def probs (sample-uniform n-obs))
  ;; this function test the above probs and samples from either
  ;; a normal(0, 1), so not an outlier or a normal(0, 25), an outlier
  (defn test-and-sample [p]
    (if (>= 0.85 p)
      (sample-normal 1 :mean 0 :sd 1)
      (sample-normal 1 :mean 0 :sd 25)))
  ;; compute the errors of the model. some are sampled from normal(0,1)
  ;; so they allow for the standard linear regression theory, but some
  ;; are sampled from a normal(0, 25), these are outlier that will poison
  ;; our regression
  (def epsilon (matrix (map test-and-sample probs) 1))
  ;; compute the biased dependent variables
  (def y-star (m/get-column (plus (mmult x beta-irwls)
                                  epsilon) 0))
  {:beta-irwls (first (m/columns beta-irwls))
   :beta-irwls-ls (:coefs (weighted-ls x y))
   :beta-irwls-biased (:coefs (weighted-ls x y-star))
   :beta-irwls-rls (:coefs
              (robust-linear-model x y-star :max-iter 100 :tol 0.001))})

(defn monte-carlo-test [n-sims]
  (pmap eval (repeat n-sims '(simulation))))

(defn analysis-test [n-sims]

  (defn compute-bias [parameters-map coefficients]
    {coefficients
     (minus (coefficients parameters-map)
              (:beta-irwls parameters-map))})
  
  (let [coefficient-types '(:beta-irwls-ls :beta-irwls-rls :beta-irwls-biased)
        simulations (monte-carlo-test n-sims)
        bias (mapcat #(map
                       (fn [x] (compute-bias % x))
                       coefficient-types)
                     simulations)
        mean-bias (map
                   #(div
                     (->> (filter % bias) 
	                  (mapcat vals)
                          (reduce plus))
                     n-sims)
                   coefficient-types)]
    mean-bias))

;; (def results (doall (map l/norm (analysis-test 1000))))
;; (deftest coefficient-behavior
;;   (is (apply <= results)))
