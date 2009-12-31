
;;; test-cases.clj -- Unit tests of Incanter functions 

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
;; (use 'tests test-cases)
;;  need to load this file to define data variables
;; (use 'clojure.contrib.test-is) 
;; then run tests
;; (run-tests 'incanter.tests.test-cases)

(ns incanter.bayes-tests
  (:use clojure.test 
        (incanter core io stats bayes)
        (examples bayes)))

;; read in the social science survey dataset from a space delimited file 
(def ols-data (to-matrix (read-dataset 
                           (str (System/getProperty "incanter.home")
                                "/data/olsexamp.dat")
                           :delim \space
                           :header true)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.stats.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def x (sel ols-data (range 0 2313) (range 1 10)))
(def y (sel ols-data (range 0 2313) 10))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.bayes.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; These tests will fail periodically due to the stochastic nature of the,
;; simulations. Examine the failure report to determine if it's a "normal" failure.

(deftest bayes-simulations
  
  ;-------------------------------------------------------------------------------
  (println "bayes-regression-noref 5000")
  (time (def b-reg-noref (bayes-regression-noref 5000 x y)))
  (let [s (sum (map #(Math/round (* 10 %)) (map mean (trans (:coef b-reg-noref)))))]
        (is (or (= s 192) (= s 193)))) ;; coefs [196 0 -24 5 1 0 7 4 3]
  (is (= (Math/round (mean (:var b-reg-noref))) 21))
  
  ;-------------------------------------------------------------------------------
  (println "bayes-regression-full 5000")
  (time (def b-reg-full (bayes-regression-full 5000 x y)))
  (let [s (sum (map #(Math/round (* 10 %)) (map mean (trans (:coef b-reg-full)))))]
        (is (or (= s 192) (= s 193)))) ;  [196 0 -24 5 1 0 7 4 3]
  (is (= (Math/round (mean (:var b-reg-full))) 21))
  
  ;-------------------------------------------------------------------------------
  (println "bayes-regression 5000")
  ;(time (def b-reg (bayes-regression 20000 x y)))
  (time (def b-reg (bayes-regression 5000 x y)))
  (let [s (sum (map #(Math/round (* 10 %)) (map mean (trans (:coef b-reg)))))]
        (is (or (= s 192) (= s 193)))) ;  [196 0 -24 5 1 0 7 4 3]
  (is (= (Math/round (mean (:var b-reg))) 21))
  
  ;-------------------------------------------------------------------------------
  (println "sample-model-params 5000")
  (def lm (linear-model y x :intercept false))
  (time (def param-samp (sample-model-params 5000 lm)))
  (let [s (sum (map #(Math/round (* 10 %)) (map mean (trans (:coefs param-samp)))))]
        (is (or (= s 192) (= s 193)))) ;  [196 0 -24 5 1 0 7 4 3]
  (is (= (Math/round (mean (:var param-samp))) 21))

  ;-------------------------------------------------------------------------------
;  (println "bayes-regression-mh 5000")
;  ;(time (def b-reg-mh (bayes-regression-mh 20000 x y))) ; takes too long to do every time
;  (time (def b-reg-mh (bayes-regression-mh 5000 x y))) ; takes too long to do every time
;  (let [s (sum (map #(Math/round (* 10 %)) (map mean (trans (:coef b-reg-mh)))))] 
;        (is (or (= s 192) (= s 193)))) ;  [196 0 -24 5 1 0 7 4 3]
;  (is (= (Math/round (mean (:var b-reg-mh))) 21))

) ;; end of bayes-simulation tests




