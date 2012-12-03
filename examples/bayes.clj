
;;; examples/bayes.clj -- Bayesian estimation library for Clojure

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 11, 2009: First version



(ns examples.bayes 
  (:use (incanter core stats)))



(defn bayes-regression-noref [N x y]
"
  This function implements the Gibbs sampling example using full conditional in OLS
  from Scott Lynch book 'Introduction to Applied Bayesian Statistics in the Social
  Sciences (page 171). This version is purely functional with no immutability.
"
  (let [lm (linear-model y x :intercept false)
        pars (trans (:coefs lm))
        xtxi (solve (mmult (trans x) x))
        nx (ncol x)
        shape (/ (- (nrow x) (ncol x)) 2)]
      (loop [coefs (transient [[0 0 0 0 0 0 0 0 0]])
             variances (transient [1]) 
             i 0]
        (if (= i N)
          {:coef (matrix (persistent! coefs)) :var (persistent! variances)}
          (let [b (to-list (plus pars (mmult (trans (sample-normal nx)) 
                                             (decomp-cholesky (mult xtxi (variances i))))))
                resid (minus y (mmult x b))
                s2 (/ 1 (sample-gamma 1 :shape shape :rate (mult (mmult (trans resid) resid) 0.5) ))]
            (recur (conj! coefs b) (conj! variances s2) (inc i)))))))




(defn bayes-regression-full [N x y]
"
  This function implements the Gibbs sampling example using full conditional in OLS
  from Scott Lynch book 'Introduction to Applied Bayesian Statistics in the Social
  Sciences (page 171). This version uses immutability (i.e. references)
"
  (let [lm (linear-model y x :intercept false)
        pars (trans (:coefs lm))        
        xtxi (solve (mmult (trans x) x))
        nx (ncol x)
        b (ref [[0 0 0 0 0 0 0 0 0]])
        s2 (ref [1])
        resid (ref 0)
        shape (/ (- (nrow x) (ncol x)) 2)]
    (do
      (dotimes [i N]
        (dosync
          (alter b conj 
            (to-list (plus pars (mmult (trans (sample-normal nx)) (decomp-cholesky (mult xtxi (@s2 i)))))))
          (ref-set resid (minus y (mmult x (@b (inc i)))))
          (alter s2 conj (/ 1 (sample-gamma 1 :shape shape :rate (mult (mmult (trans @resid) @resid) 0.5) )))))
        ;; return a map with the estimated coefficients and variances
       {:coef (matrix @b) :var @s2})))




(defn bayes-regression [N x y]
"
  This function implements the Gibbs sampling example using the composition method
  in OLS from Scott Lynch book 'Introduction to Applied Bayesian Statistics in the 
  Social Sciences (page 173)
"
  (let [lm (linear-model y x :intercept false)
        pars (:coefs lm)
        xtxi (solve (mmult (trans x) x))
        resid (:residuals lm)
        shape (/ (- (nrow x) (ncol x)) 2)
        rate (mult 1/2 (mmult (trans resid) resid))
        s-sq (div 1 (sample-gamma N :shape shape :rate rate))]
    ;; return a map with the estimated coefficients and variances
    {:coef 
      (matrix 
        ;(pmap ;; run a parallel map over the values of s-sq
        (map
          (fn [s2] 
            (to-list (plus (trans pars)
                (mmult (trans (sample-normal (ncol x))) 
                  (decomp-cholesky (mult s2 xtxi))))))
          (to-list (trans s-sq)))) 
     :var s-sq}))




(defn bayes-regression-mh [N x y]
"
  This function implements the Gibbs sampling example using Metropolis Hastings
  in OLS from Scott Lynch book 'Introduction to Applied Bayesian Statistics in the 
  Social Sciences (page 168)
"
  (let [ lm (linear-model y x :intercept false)
         b-scale (to-list (div (sqrt (:std-errors lm)) 2))
         s2-scale (/ (sd (mult (:residuals lm) (div (dec (nrow x)) (- (nrow x) (ncol x))))) 2)
         post-fn (fn [x y b s-sq] 
                  (let [resid (minus y (mmult x b))]
                    (plus (mult -1157.5 (log s-sq)) 
                          (mult (div -0.5 s-sq) (mmult (trans resid) resid))))) 
        reject? (fn [x y cand-b cand-s2 old-b old-s2] 
                  (< (- (post-fn x y cand-b cand-s2) 
                        (post-fn x y old-b old-s2))
                     (log (rand))))
        ncol-x (ncol x)]
    (loop [coefs (transient [[0 0 0 0 0 0 0 0 0]])
           variances (transient [1])
           i 0]
      (if (= i N)
          {:coef (matrix (persistent! coefs)) :var (persistent! variances)}
          (let [old-b (coefs i)
                old-s2 (variances i)
                cand-b (into [] (map #(+ (sample-normal 1 :mean 0 :sd %1) %2) b-scale old-b))
                new-b (loop [b old-b j 0]
                          (if (= j ncol-x)
                            b
                            (recur 
                              (if (reject? x y (assoc b j (cand-b j)) old-s2 old-b old-s2) 
                                b
                                (assoc b j (cand-b j)))
                              (inc j)))) 
                cand-s2 (+ (sample-normal 1 :mean 0 :sd s2-scale) old-s2)
                new-s2 (if (or (< cand-s2 0) (reject? x y new-b cand-s2 new-b old-s2)) 
                         old-s2 
                         cand-s2)]
        (recur (conj! coefs new-b) (conj! variances new-s2) (inc i)))))))


