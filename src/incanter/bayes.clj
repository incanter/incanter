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



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MCMC Sampling
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn sample-model-params 
" Returns a sample of the given size of the the parameters (coefficients and
  error variance) of the given linear-model. The sample is generated using 
  Gibbs sampling.

  See also:
    incanter.stats/linear-model

  Examples:
    (use '(incanter core io stats charts bayes))

    (def ols-data (to-matrix (read-dataset \"data/olsexamp.dat\" :header true)))
    (def x (sel ols-data (range 0 2313) (range 1 10)))
    (def y (sel ols-data (range 0 2313) 10))
    (def lm (linear-model y x :intercept false))
    (def param-samp (sample-model-params 5000 lm))
    
    ;; view trace plots
    (view (trace-plot (:var param-samp ))) 
    (view (trace-plot (sel (:coefs param-samp) :columns 0)))

    ;; view histograms
    (view (histogram (:var param-samp))) 
    (view (histogram (sel (:coefs param-samp) :columns 0)))

    ;; calculate statistics
    (map mean (trans (:coefs param-samp)))
    (map median (trans (:coefs param-samp)))
    (map sd (trans (:coefs param-samp)))

    ;; show the 95% bayesian confidence interval for the firt coefficient
    (quantile (sel (:coefs param-samp) :columns 0) :probs [0.025 0.975])

"
  ([size linear-model]
    (let [x (:x linear-model)
          y (:y linear-model)
          pars (:coefs linear-model)
          xtxi (solve (mmult (trans x) x))
          resid (:residuals linear-model)
          shape (/ (- (nrow x) (ncol x)) 2)
          rate (mult 1/2 (mmult (trans resid) resid))
          s-sq (div 1 (sample-gamma size :shape shape :rate rate))]
      {:coefs 
        (matrix 
          ;(pmap ;; run a parallel map over the values of s-sq
          (map
            (fn [s2] 
              (to-list (plus (trans pars)
                  (mmult (trans (sample-normal (ncol x))) 
                    (decomp-cholesky (mult s2 xtxi))))))
            (to-list (trans s-sq)))) 
      :var s-sq})))






;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Alternative MCMC Sampling Techniques
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn bayes-regression-noref [N x y]
  (let [lm (linear-model y x :intercept false)
        pars (trans (:coefs lm))
        xtxi (solve (mmult (trans x) x))
        nx (ncol x)
        shape (/ (- (nrow x) (ncol x)) 2)
       ]
      (loop [
             coefs [[0 0 0 0 0 0 0 0 0]]
             variances [1] 
             i 0
            ]
        (if (= i N)
          {:coef (matrix coefs) :var variances}
          (let [
                b (to-list (plus pars (mmult (trans (sample-normal nx)) (decomp-cholesky (mult xtxi (variances i))))))
                resid (minus y (mmult x b))
                s2 (/ 1 (sample-gamma 1 :shape shape :rate (mult (mmult (trans resid) resid) 0.5) ))
               ]
            (recur (conj coefs b) (conj variances s2) (inc i)))))))


(defn bayes-regression-full [N x y]
  (let [lm (linear-model y x :intercept false)
        pars (trans (:coefs lm))        
        xtxi (solve (mmult (trans x) x))
        nx (ncol x)
        b (ref [[0 0 0 0 0 0 0 0 0]])
        s2 (ref [1])
        resid (ref 0)
        shape (/ (- (nrow x) (ncol x)) 2)
       ]
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
                         cand-s2)
               ]
        (recur (conj coefs new-b) (conj variances new-s2) (inc i)))))))


