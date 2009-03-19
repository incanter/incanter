;;; bayes.clj -- Bayesian estimation library for Clojure

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



(ns incanter.bayes 
  (:use (incanter core stats)))



(defn bayes-regression-noref [N x y]
  (let [pars (trans (lm-coef y x))
        xtxi (solve (mmult (trans x) x))
        nx (ncol x)
        shape (/ (- (nrow x) (ncol x)) 2)
        ;;gamma-rnd (gamma-generator)
       ]
      (loop [
             coefs [[0 0 0 0 0 0 0 0 0]]
             variances [1] 
             i 0
            ]
        (if (= i N)
          {:coef (matrix coefs) :var variances}
          (let [
                ;;b (to-list (plus pars (mmult (trans (rnorm nx)) (decomp-cholesky (mult xtxi (variances i))))))
                b (to-list (plus pars (mmult (trans (sample-normal nx)) (decomp-cholesky (mult xtxi (variances i))))))
                resid (minus y (mmult x b))
                ;;s2 (/ 1 (gamma-rnd shape (mult (mmult (trans resid) resid) 0.5) ))
                s2 (/ 1 (sample-gamma 1 :shape shape :rate (mult (mmult (trans resid) resid) 0.5) ))
               ]
            (recur (conj coefs b) (conj variances s2) (inc i)))))))


(defn bayes-regression-full [N x y]
  (let [pars (trans (lm-coef y x))
        xtxi (solve (mmult (trans x) x))
        nx (ncol x)
        b (ref [[0 0 0 0 0 0 0 0 0]])
        s2 (ref [1])
        resid (ref 0)
        shape (/ (- (nrow x) (ncol x)) 2)
        ;;gamma-rnd (gamma-generator)
       ]
    (do
      (dotimes [i N]
        (dosync
          (alter b conj 
            ;;(to-list (plus pars (mmult (trans (rnorm nx)) (decomp-cholesky (mult xtxi (@s2 i)))))))
            (to-list (plus pars (mmult (trans (sample-normal nx)) (decomp-cholesky (mult xtxi (@s2 i)))))))
          (ref-set resid (minus y (mmult x (@b (inc i)))))
          ;;(alter s2 conj (/ 1 (gamma-rnd shape (mult (mmult (trans @resid) @resid) 0.5) )))))
          (alter s2 conj (/ 1 (sample-gamma 1 :shape shape :rate (mult (mmult (trans @resid) @resid) 0.5) )))))
        ;; return a map with the estimated coefficients and variances
       {:coef (matrix @b) :var @s2})))



(defn bayes-regression [N x y]
  (let [pars (lm-coef y x)
        xtxi (solve (mmult (trans x) x))
        resid (lm-resid y x)
        shape (/ (- (nrow x) (ncol x)) 2)
        rate (mult 1/2 (mmult (trans resid) resid))
        ;;s-sq (div 1 (rgamma N :shape shape :rate rate))
        s-sq (div 1 (sample-gamma N :shape shape :rate rate))
       ]
    ;; return a map with the estimated coefficients and variances
    {:coef 
      (matrix 
        ;(pmap ;; run a parallel map over the values of s-sq
        (map
          (fn [s2] 
            (to-list (plus (trans pars)
                ;;(mmult (trans (rnorm (ncol x))) 
                (mmult (trans (sample-normal (ncol x))) 
                  (decomp-cholesky (mult s2 xtxi))))))
          (to-list (trans s-sq)))) 
     :var s-sq}))



(defn bayes-regression-mh [N x y]
  (let [
         b-scale (to-list (div (sqrt (lm-se y x)) 2))
         s2-scale (/ (sd (mult (lm-resid y x) (div (dec (nrow x)) (- (nrow x) (ncol x))))) 2)
         post-fn (fn [x y b s-sq] 
                  (let [resid (minus y (mmult x b))]
                    (plus (mult -1157.5 (log s-sq)) 
                          (mult (div -0.5 s-sq) (mmult (trans resid) resid))))) 
        reject? (fn [x y cand-b cand-s2 old-b old-s2] 
                  (< (- (post-fn x y cand-b cand-s2) 
                        (post-fn x y old-b old-s2))
                     (log (rand))))
        ;;norm-rand (normal-generator)
        ncol-x (ncol x)
       ]
    (loop [
           coefs [[0 0 0 0 0 0 0 0 0]]
           variances [1] 
           i 0
          ]
      (if (= i N)
          {:coef (matrix coefs) :var variances}
          (let [
                old-b (coefs i)
                old-s2 (variances i)
                ;;cand-b (into [] (map #(+ (norm-rand 0 %1) %2) b-scale old-b))
                cand-b (into [] (map #(+ (sample-normal 1 :mean 0 :sd %1) %2) b-scale old-b))
                new-b (loop [b old-b j 0]
                          (if (= j ncol-x)
                            b
                            (recur 
                              (if (reject? x y (assoc b j (cand-b j)) old-s2 old-b old-s2) 
                                b
                                (assoc b j (cand-b j)))
                              (inc j)))) 
                ;;cand-s2 (+ (norm-rand 0 s2-scale) old-s2)
                cand-s2 (+ (sample-normal 1 :mean 0 :sd s2-scale) old-s2)
                new-s2 (if (or (< cand-s2 0) (reject? x y new-b cand-s2 new-b old-s2)) 
                         old-s2 
                         cand-s2)
               ]
        (recur (conj coefs new-b) (conj variances new-s2) (inc i)))))))



