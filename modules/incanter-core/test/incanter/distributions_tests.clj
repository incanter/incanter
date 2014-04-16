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
        incanter.distributions
        [incanter.stats :exclude (mean variance)]))

;; testing helpers
(defn- all? [coll] (every? true? coll))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.distributions.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest extending-seq-type-tests
  (let [coll ()]
    (is (= 0 (pdf coll 1)))

    (is (= 0 (cdf coll 1)))

    (is (thrown? RuntimeException (draw coll)))

    (is (= #{} (support coll)))

    (is (= nil (mean coll)))

    (is (= nil (variance coll))))

  (let [coll '(1 2 1 2 2 1 3 3)]
    (is (= 0   (pdf coll 0)))
    (is (= 3/8 (pdf coll 2)))
    (is (= 0   (pdf coll 4)))

    (is (= 0   (cdf coll 0)))
    (is (= 3/4 (cdf coll 2)))
    (is (= 1   (cdf coll 3)))
    (is (= 1   (cdf coll 4)))

    (is (= #{1 2 3} (support coll)))

    (is (= 15/8 (mean coll)))

    (is (= 39/64 (variance coll))))

  (let [coll [1 2 3 2 :foo :bar]]
    (is (= 1/3 (pdf coll 2)))
    (is (= 1/6 (pdf coll :foo)))
    (is (= 0 (pdf coll :baz)))

    (is (thrown? Exception (cdf coll 2)))

    (is (= #{1 2 3 :foo :bar} (support coll)))

    (is (= nil (mean coll)))

    (is (= nil (variance coll))))
)

(deftest extending-set-type-tests
  (let [coll #{}]
    (is (= 0 (pdf coll 1)))

    (is (empty? (cdf coll 1)))

    (is (thrown? RuntimeException (draw coll)))

    (is (= #{} (support coll)))

    (is (= nil (mean coll)))

    (is (= nil (variance coll))))

  (let [coll (set '(1 2 1 2 2 1 3 3))]
    (is (= 0   (pdf coll 0)))
    (is (= 1/3 (pdf coll 2)))
    (is (= 0   (pdf coll 4)))

    (is (= nil (cdf coll 0)))
    (is (= nil (cdf coll 2)))
    (is (= nil (cdf coll 3)))
    (is (= nil (cdf coll 4)))

    (is (= #{1 2 3} (support coll)))

    (is (= 2 (mean coll)))

    (is (= 2/3 (variance coll))))

  (let [coll (set [1 2 3 2 :foo :bar])]
    (is (= 1/5 (pdf coll 2)))
    (is (= 1/5 (pdf coll :foo)))
    (is (= 0 (pdf coll :baz)))

    (is (= nil (cdf coll 2)))

    (is (= #{1 2 3 :foo :bar} (support coll)))

    (is (empty? (mean coll)))

    (is (empty? (variance coll))))

  (let [coll (sorted-set 2 2 1 3 3 3)]
    (is (= 0   (pdf coll 0)))
    (is (= 1/3 (pdf coll 2)))
    (is (= 0   (pdf coll 4)))

    (is (= 0   (cdf coll 0)))
    (is (= 2/3 (cdf coll 2)))
    (is (= 1   (cdf coll 3)))
    (is (= 1   (cdf coll 4)))

    (is (= #{1 2 3} (support coll)))

    (is (= 2 (mean coll)))

    (is (= 2/3 (variance coll))))
)

(deftest extending-map-type-tests

  (let [hmap (hash-map)]                ; hmm...
    (is (= 0 (pdf hmap :5)))

    (is (= nil (cdf hmap :5)))

    (is (thrown? RuntimeException (draw hmap)))

    (is (= nil (support hmap)))

    (is (= nil (mean hmap)))

    (is (= nil (variance hmap))))

  (let [hmap (hash-map :5 20)]
    (is (= 0 (pdf hmap :4)))
    (is (= 1 (pdf hmap :5)))
    (is (= 0 (pdf hmap :6)))

    (is (= nil (cdf hmap :4)))
    (is (= nil (cdf hmap :5)))
    (is (= nil (cdf hmap :6)))

    (is (= #{:5} (set (support hmap))))

    (is (= 20 (mean hmap)))

    (is (= 0 (variance hmap))))

  (let [hmap (hash-map :6 30 :5 20 :7 40 :8 10)]
    (is (= 0    (pdf hmap :4)))
    (is (= 1/5  (pdf hmap :5)))
    (is (= 3/10 (pdf hmap :6)))
    (is (= 2/5  (pdf hmap :7)))
    (is (= 1/10 (pdf hmap :8)))
    (is (= 0    (pdf hmap :9)))

    (is (= nil (cdf hmap :4)))
    (is (= nil (cdf hmap :5)))
    (is (= nil (cdf hmap :6)))
    (is (= nil (cdf hmap :7)))
    (is (= nil (cdf hmap :8)))
    (is (= nil (cdf hmap :9)))

    (is (= #{:5 :6 :7 :8} (set (support hmap))))

    (is (= 25 (mean hmap)))

    (is (= 125 (variance hmap))))

  (let [trmap (sorted-map)]             ; hmm...
    (is (= 0 (pdf trmap :5)))

    (is (= 0 (cdf trmap :5)))

    (is (thrown? RuntimeException (draw trmap)))

    (is (= nil (support trmap)))

    (is (= nil (mean trmap)))

    (is (= nil (variance trmap))))

  (let [trmap (sorted-map :5 20)]
    (is (= 0 (pdf trmap :4)))
    (is (= 1 (pdf trmap :5)))
    (is (= 0 (pdf trmap :6)))

    (is (= 0 (cdf trmap :4)))
    (is (= 1 (cdf trmap :5)))
    (is (= 1 (cdf trmap :6)))

    (is (= '(:5) (support trmap)))

    (is (= 20 (mean trmap)))

    (is (= 0 (variance trmap))))

  (let [trmap (sorted-map :6 30 :5 20 :7 40 :8 10)]
    (is (= 0    (pdf trmap :4)))
    (is (= 1/5  (pdf trmap :5)))
    (is (= 3/10 (pdf trmap :6)))
    (is (= 2/5  (pdf trmap :7)))
    (is (= 1/10 (pdf trmap :8)))
    (is (= 0    (pdf trmap :9)))

    (is (= 0    (cdf trmap :4)))
    (is (= 1/5  (cdf trmap :5)))
    (is (= 1/2  (cdf trmap :6)))
    (is (= 9/10 (cdf trmap :7)))
    (is (= 1    (cdf trmap :8)))
    (is (= 1    (cdf trmap :9)))

    (is (= '(:5 :6 :7 :8) (support trmap)))

    (is (= 25 (mean trmap)))

    (is (= 125 (variance trmap)))))

;; TODO replace
(deftest test-roulette-wheel
  (is (= nil (roulette-wheel '(0 0 0 0 0))))
  (is (= 3 (roulette-wheel '(0 0 0 5 0))))
  (is (not (= 5 (roulette-wheel '(0 0 0 5 0)))))

  (let [freqs (map #(val %) (into (sorted-map) (frequencies (repeatedly 10000 #(roulette-wheel '(40 30 20 10))))))
        diffs (map #(Math/abs (- %1 %2)) freqs '(4000 3000 2000 1000))]
    (is (every? #(< (float (/ % 10000)) 0.05) diffs) "If this fails, check distribution manually")))

(deftest basic-integer-distribution-tests
  (is (thrown? AssertionError (integer-distribution 5 0))) ; wrong argument order
  (let [u (integer-distribution 1 5)]
    (is (= (pdf u 5) 1/4))
    (is (= (cdf u 2) 1/2))
    (is (< 0 (draw u)))
    (is (= '(1 2 3 4) (support u)))
    (is (= 10/4 (mean u)))
    (is (= 5/4 (variance u)))
    (is (all? (repeatedly 100 #(> (:end u) (draw u)))))))

(deftest large-integer-tests
  (let [u (integer-distribution (reduce *' (repeat 100 2)) (reduce *' (repeat 100 3)))]
    (is (all? (repeatedly 100 #(<= (:start u) (draw u)))))
		(is (all? (repeatedly 100 #(> (:end u) (draw u)))))))

(deftest combination-distribution-tests
  (let [cd (combination-distribution 5 3)]
    (is (thrown? AssertionError (combination-distribution 0 5))) ; wrong argument order
    (is (thrown? AssertionError (combination-distribution 3 -1))) ; wrong argument sign
    (is (thrown? AssertionError (combination-distribution -2 -5))) ; wrong argument sign
    
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
    (is (= (support lady-tasting-tea) #{0 1 2 3 4})) ; possible cups correctly classified
    (is (= (map #(pdf lady-tasting-tea %) [0 1 2 3 4]) [1/70 16/70 36/70 16/70 1/70]))))

    
;; tests against incanter.stats, checking for similar behaviour
;; and other functions
(deftest beta-distribution-tests
  (let [alpha 1
        beta 2
        dist (beta-distribution alpha beta)]
    (is (= (pdf dist 0.5) (pdf-beta 0.5 :alpha alpha :beta beta)))
    (is (= (cdf dist 0.5) (cdf-beta 0.5 :alpha alpha :beta beta)))

    (is (= (/ alpha (+ alpha beta)) (mean dist)))

    (is (= (/ (* alpha beta)
              (* (+ alpha beta)
                 (+ alpha beta)
                 (+ alpha beta 1))) (variance dist))))
)

(deftest binomial-distribution-tests
  (let [p 1/4
        n 20
        dist (binomial-distribution n p)]
    (is (= (pdf dist 10) (pdf-binomial 10 :prob p :size n)))
    (is (= (cdf dist 10) (cdf-binomial 10 :prob p :size n)))
  
    (is (= (* n p) (mean dist)))

    (is (= (* n p (- 1 p)) (variance dist))))
)

(deftest chisq-distribution-tests
  (let [df 2
        dist (chisq-distribution df)]
    (is (= (pdf dist 5.0) (pdf-chisq 5.0 :df df)))
    (is (= (cdf dist 5.0) (cdf-chisq 5.0 :df df)))

    (is (= df (mean dist)))

    (is (= (* 2 df) (variance dist))))
)

(deftest exponential-distribution-tests
  (let [lambda 1/2
        dist (exponential-distribution lambda)]
    (is (= (pdf dist 2.0) (pdf-exp 2.0 :rate lambda)))
    (is (= (cdf dist 2.0) (cdf-exp 2.0 :rate lambda)))

    (is (= (/ 1 lambda) (mean dist)))

    (is (= (/ 1 lambda lambda) (variance dist))))
)

(deftest f-distribution-tests
  (let [df1 5
        df2 2
        dist (f-distribution df1 df2)]
    (is (= (pdf dist 1.0) (pdf-f 1.0 :df1 df1 :df2 df2)))
    (is (= (cdf dist 1.0) (cdf-f 1.0 :df1 df1 :df2 df2)))

    (is (= nil (mean dist)))

    (is (= nil (variance dist))))

  (let [df1 5
        df2 5
        dist (f-distribution df1 df2)]
    (is (= (pdf dist 1.0) (pdf-f 1.0 :df1 df1 :df2 df2)))
    (is (= (cdf dist 1.0) (cdf-f 1.0 :df1 df1 :df2 df2)))

    (is (= (/ df2 (- df2 2)) (mean dist)))

    (is (= (/ (* 2 df2 df2 (+ df1 df2 -2))
              (* df1 (- df2 2) (- df2 2) (- df2 4))) (variance dist))))
)

(deftest gamma-distribution-tests
  (let [k 1
        theta 2
        dist (gamma-distribution k theta)]
    (is (= (pdf dist 10) (pdf-gamma 10 :shape k :rate theta)))
    (is (= (cdf dist 10) (cdf-gamma 10 :shape k :rate theta)))

    (is (= (* k theta) (mean dist)))

    (is (= (* k theta theta) (variance dist))))
)

(deftest neg-binomial-distribution-tests
  (let [p 1/2
        r 20
        dist (neg-binomial-distribution r p)]
    (is (= (pdf dist 10) (pdf-neg-binomial 10 :prob p :size r)))
    (is (= (cdf dist 10) (cdf-neg-binomial 10 :prob p :size r)))

    (is (= (/ (* r p)
              (- 1 p)) (mean dist)))

    (is (= (/ (* r p)
              (* (- 1 p) (- 1 p))) (variance dist))))
)

(deftest normal-distribution-tests
  (let [mu -2
        sdsd 0.5
        sd (Math/sqrt sdsd)
        dist (normal-distribution mu sd)]
    (is (= (pdf dist 1.96) (pdf-normal 1.96 :mean mu :sd sd)))
    (is (= (cdf dist 1.96) (cdf-normal 1.96 :mean mu :sd sd)))

    (is (= mu (mean dist)))

    (is (<= (Math/abs (- sdsd (variance dist))) 0.0001))) ; TODO fix
)

(deftest poisson-distribution-tests
  (let [lambda 10
        dist (poisson-distribution lambda)]
    (is (= (pdf dist 5) (pdf-poisson 5 :lambda lambda)))
    (is (= (cdf dist 5) (cdf-poisson 5 :lambda lambda)))

    (is (= lambda (mean dist)))

    (is (= lambda (variance dist)))))

(deftest t-distribution-tests
  (let [df 10
        dist (t-distribution df)]
    (is (= (pdf dist 1.2) (pdf-t 1.2 :df df)))
    (is (= (cdf dist 1.2) (cdf-t 1.2 :df df)))

    (is (= 0 (mean dist)))

    (is (= (/ df (- df 2)) (variance dist)))))

(deftest uniform-distribution-tests
  (let [lb 1.0
        ub 10.0
        dist (uniform-distribution lb ub)]
    (is (= (pdf dist 5) (pdf-uniform 5 :min lb :max ub)))
    (is (= (cdf dist 5) (cdf-uniform 5 :min lb :max ub)))

    (is (= 5.5 (mean dist)))

    (is (= (/ (* (- ub lb) (- ub lb))
              12) (variance dist)))
    ;; up to 0e17345542f795260651989b83694079cd9d2b28 used (0,1) for
    ;; draw, ignoring lb and ub
    (is (<= lb (draw dist) ub))))
