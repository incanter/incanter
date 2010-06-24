;;; distributions_tests.clj -- Tests of incanter.distributions namespace

;; by Mark Fredrickson http://www.markmfredrickson.com
;; May 10, 2010
;; Changes added by William Leung
;; Jun 24, 2010

;; Copyright (c) Mark M. Fredrickson, 2010. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns incanter.distributions-tests
  (:use clojure.test
        clojure.set
        (incanter distributions stats)))

;; testing helpers
(defn- all? [coll] (every? true? coll))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.distributions.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest extending-basic-types 
	(is (= (pdf [1 2 2] 1) 1/3))
  (is (= (pdf '(1 2 1 2 2 1) 2) 1/2))
	(is (= (support [1 2 3 2 :foo :bar]) #{1 2 3 :foo :bar}))
  (is (= (cdf [1 2 3] 2) 2/3))
  (is (= (pdf #{:foo :bar :baz} :baz) 1/3))
  (is (= (pdf #{:foo :bar} :baz) 0)))

(deftest basic-integer-distribution-tests
  (is (thrown? AssertionError (integer-distribution 5 0))) ; wrong argment order
  (let [u (integer-distribution 1 5)]
    (is (< 0 (draw u)))
    (is (= (pdf u 5) 1/4))
    (is (= (cdf u 2) 1/2))
  	(is (all? (repeatedly 100 #(> (:end u) (draw u)))))))

(deftest large-integer-tests
  (let [u (integer-distribution (reduce * (repeat 100 2)) (reduce * (repeat 100 3)))]
    (is (all? (repeatedly 100 #(<= (:start u) (draw u)))))
		(is (all? (repeatedly 100 #(> (:end u) (draw u)))))))

(deftest combination-distribution-tests
  (let [cd (combination-distribution 5 3)]
    (is (= 3 (count (draw cd))))
		(is (= 3 (count (set (draw cd)))))))

(deftest normal-tests
  ;; generate a sample of standard normal data
  (let [N (normal-distribution)
        std-normal-data (repeatedly 1000 #(draw N))]
  	(is (= (count std-normal-data) 1000))
  	(is (= (Math/round (mean std-normal-data)) 0))
  	(is (= (Math/round (sd std-normal-data)) 1)))
  
  ;; generate a sample of normal data with mean = 10 and sd = 5
  (let [N (normal-distribution 10 5)
        nonstd-normal-data (repeatedly 1000 #(draw N))]
 		(is (= (count nonstd-normal-data) 1000))
  	(is (= (Math/round (mean nonstd-normal-data)) 10))
  	(is (= (Math/round (sd nonstd-normal-data)) 5))))

(deftest lady-tasting-tea
  ; http://en.wikipedia.org/wiki/Lady_tasting_tea
  ; under fisher's lady tasting tea experiment, the test statistic is
  ; the number of milk first cups correctly classified. Therefore, we only care
  ; that the treatment units correspond to the "true" treatments,
  ; which I arbitrarily decide are cups 0 -3
  (let [lady-fn (fn [x] (count (intersection (set x) #{0 1 2 3})))
        lady-tasting-tea (test-statistic-distribution lady-fn 8 4)]
    (= (support lady-tasting-tea) #{0 1 2 3}) ; possible cups correctly classified
    (= (map #(pdf lady-tasting-tea %) [0 1 2 3 4]) [1/70 16/70 36/70 16/70 1/70])))

    
;; tests against incanter.stats, checking for similar behaviour
(deftest beta-distribution-tests
  (is (= (pdf (beta-distribution 1 2) 0.5) (pdf-beta 0.5 :alpha 1 :beta 2)))
  (is (= (cdf (beta-distribution 1 2) 0.5) (cdf-beta 0.5 :alpha 1 :beta 2))))

(deftest binomial-distribution-tests
  (is (= (pdf (binomial-distribution 20 1/4) 10) (pdf-binomial 10 :prob 1/4 :size 20)))
  (is (= (cdf (binomial-distribution 20 1/4) 10) (cdf-binomial 10 :prob 1/4 :size 20))))

(deftest chisq-distribution-tests
  (is (= (pdf (chisq-distribution 2) 5.0) (pdf-chisq 5.0 :df 2)))
  (is (= (cdf (chisq-distribution 2) 5.0) (cdf-chisq 5.0 :df 2))))

(deftest exponential-distribution-tests
  (is (= (pdf (exponential-distribution 1/2) 2.0) (pdf-exp 2.0 :rate 1/2)))
  (is (= (cdf (exponential-distribution 1/2) 2.0) (cdf-exp 2.0 :rate 1/2))))

(deftest f-distribution-tests
  (is (= (pdf (f-distribution 5 2) 1.0) (pdf-f 1.0 :df1 5 :df2 2)))
  (is (= (cdf (f-distribution 5 2) 1.0) (cdf-f 1.0 :df1 5 :df2 2))))

(deftest gamma-distribution-tests
  (is (= (pdf (gamma-distribution 1 2) 10) (pdf-gamma 10 :shape 1 :rate 2)))
  (is (= (cdf (gamma-distribution 1 2) 10) (cdf-gamma 10 :shape 1 :rate 2))))

(deftest neg-binomial-distribution-tests
  (is (= (pdf (neg-binomial-distribution 20 1/2) 10) (pdf-neg-binomial 10 :prob 1/2 :size 20)))
  (is (= (cdf (neg-binomial-distribution 20 1/2) 10) (cdf-neg-binomial 10 :prob 1/2 :size 20))))

(deftest normal-distribution-tests
  (is (= (pdf (normal-distribution -2 (Math/sqrt 0.5)) 1.96) (pdf-normal 1.96 :mean -2 :sd (Math/sqrt 0.5))))
  (is (= (cdf (normal-distribution -2 (Math/sqrt 0.5)) 1.96) (cdf-normal 1.96 :mean -2 :sd (Math/sqrt 0.5)))))

(deftest poisson-distribution-tests
  (is (= (pdf (poisson-distribution 10) 5) (pdf-poisson 5 :lambda 10)))
  (is (= (cdf (poisson-distribution 10) 5) (cdf-poisson 5 :lambda 10))))

(deftest t-distribution-tests
  (is (= (pdf (t-distribution 10) 1.2) (pdf-t 1.2 :df 10)))
  (is (= (cdf (t-distribution 10) 1.2) (cdf-t 1.2 :df 10))))

(deftest uniform-distribution-tests
  (is (= (pdf (uniform-distribution 1.0 10.0) 5) (pdf-uniform 5 :min 1 :max 10)))
  (is (= (cdf (uniform-distribution 1.0 10.0) 5) (cdf-uniform 5 :min 1 :max 10))))
