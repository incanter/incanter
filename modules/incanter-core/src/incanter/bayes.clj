;;; bayes.clj -- Bayesian estimation library for Clojure

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 11, 2009: First version



(ns ^{:doc "This is library provides functions for performing
            basic Bayesian modeling and inference.
            "
       :author "David Edgar Liebke"}
  incanter.bayes
  (:use [incanter.core :only (matrix mmult mult div minus trans ncol nrow
                              plus to-list decomp-cholesky solve half-vectorize
                              vectorize symmetric-matrix identity-matrix kronecker
                              bind-columns)]
        [incanter.stats :only (sample-normal sample-gamma sample-dirichlet
                               sample-inv-wishart sample-mvn mean)]))





(defn sample-model-params
  "
  Returns a sample of the given size of the parameters (coefficients and
  error variance) of the given linear-model. The sample is generated using
  Gibbs sampling.

  See also:
    incanter.stats/linear-model

  Examples:
    (use '(incanter core datasets stats charts bayes))

    (def ols-data (to-matrix (get-dataset :survey)))
    (def x (sel ols-data (range 0 2313) (range 1 10)))
    (def y (sel ols-data (range 0 2313) 10))
    (def lm (linear-model y x :intercept false))
    (def param-samp (sample-model-params 5000 lm))

    ;; view trace plots
    (view (trace-plot (:var param-samp )))
    (view (trace-plot (sel (:coefs param-samp) :cols 0)))

    ;; view histograms
    (view (histogram (:var param-samp)))
    (view (histogram (sel (:coefs param-samp) :cols 0)))

    ;; calculate statistics
    (map mean (trans (:coefs param-samp)))
    (map median (trans (:coefs param-samp)))
    (map sd (trans (:coefs param-samp)))

    ;; show the 95% bayesian confidence interval for the first coefficient
    (quantile (sel (:coefs param-samp) :cols 0) :probs [0.025 0.975])
  "
  ([^Integer size {:keys [x y coefs residuals]}]
    (let [xtxi (solve (mmult (trans x) x))
          shape (/ (- (nrow x) (ncol x)) 2)
          scale (first (mult 1/2 (mmult (trans residuals) residuals)))
          s-sq (div 1 (sample-gamma size :shape shape :scale scale))]
      {:coefs
        (matrix
          ;(pmap ;; run a parallel map over the values of s-sq
          (map
            (fn [s2]
              (to-list (plus (trans coefs)
                  (mmult (trans (sample-normal (ncol x)))
                    (decomp-cholesky (mult s2 xtxi))))))
            (to-list (trans s-sq))))
      :var s-sq})))




(defn sample-proportions
  "sample-proportions has been renamed sample-multinomial-params"
  ([size counts]
   (throw (Exception. "sample-proportions has been renamed sample-multinomial-params"))))



(defn sample-multinomial-params
  "
  Returns a sample of multinomial proportion parameters.
  The counts are assumed to have a multinomial distribution.
  A uniform prior distribution is assigned to the multinomial vector
  theta, then the posterior distribution of theta is
  proportional to a dirichlet distribution with parameters
  (plus counts 1).


  Examples:
    (use '(incanter core stats bayes charts))

    (def  samp-props (sample-multinomial-params 1000 [727 583 137]))

    ;; view means, 95% CI, and histograms of the proportion parameters
    (mean (sel samp-props :cols 0))
    (quantile (sel samp-props :cols 0) :probs [0.0275 0.975])
    (view (histogram (sel samp-props :cols 0)))
    (mean (sel samp-props :cols 1))
    (quantile (sel samp-props :cols 1) :probs [0.0275 0.975])
    (view (histogram (sel samp-props :cols 1)))
    (mean (sel samp-props :cols 2))
    (quantile (sel samp-props :cols 2) :probs [0.0275 0.975])
    (view (histogram (sel samp-props :cols 2)))

    ;; view  a histogram of the difference in proportions between the first
    ;; two candidates
    (view (histogram (minus (sel samp-props :cols 0) (sel samp-props :cols 1))))
  "
  ([^Integer size counts]
    (sample-dirichlet size (plus counts 1))))




(defn sample-mvn-params
  "
  Returns samples of means (sampled from an mvn distribution) and vectorized covariance
  matrices (sampled from an inverse-wishart distribution) for the given mvn data.

  Arguments:
    size -- the number of samples to return
    y -- the data used to estimate the parameters


  Returns map with following fields:
    :means
    :sigmas


  Examples:

    (use '(incanter core stats bayes charts))
    (def y (sample-mvn 500 :mean [0 0] :sigma (identity-matrix 2)))
    (def samp (sample-mvn-params 1000 y))

    (map mean (trans (:means samp)))
    (symmetric-matrix (map mean (trans (:sigmas samp))) :lower false)

    (view (histogram (sel (:means samp) :cols 0) :x-label \"mean 1\"))
    (view (histogram (sel (:means samp) :cols 1) :x-label \"mean 2\"))
    (view (histogram (sel (:sigmas samp) :cols 1) :x-label \"covariance\"))
    (view (histogram (sel (:sigmas samp) :cols 0) :x-label \"variance 1\"))
    (view (histogram (sel (:sigmas samp) :cols 2) :x-label \"variance 2\"))

    (map #(quantile % :probs [0.025 0.0975]) (trans (:means samp)))
    (map #(quantile % :probs [0.025 0.0975]) (trans (:sigmas samp)))




    (use '(incanter core stats bayes charts))
    (def y (sample-mvn 500 :sigma (symmetric-matrix [10 5 10]) :mean [5 2]))
    (def samp (sample-mvn-params 1000 y))
    (symmetric-matrix (map mean (trans (:sigmas samp))) :lower false)
    (map mean (trans (:means samp)))
  "
  ([^Integer size y & options]
    (let [opts (when options (apply assoc {} options))
          means (map mean (trans y))
          n (count y)
          S (reduce plus
                    (map #(mmult (minus (to-list %) means)
                                 (trans (minus (to-list %) means)))
                         y))
          sigma-samp (matrix (for [_ (range size)]
                               (half-vectorize (sample-inv-wishart :df (dec n) :scale (solve S)))))
          mu-samp (matrix (for [sigma sigma-samp]
                            (sample-mvn 1
                                        :mean means
                                        :sigma (div (symmetric-matrix sigma :lower false) n))))
          ]
  {:means mu-samp :sigmas sigma-samp})))




(defn- sample-mv-model-params
  "
  Examples:

    (use '(incanter core stats bayes datasets))
    (def survey (to-matrix (get-dataset :survey)))
    (def x (sel survey :cols (range 2 10)))
    (def y (sel survey :cols (range 10 14)))

    (time (def params (sample-mv-model-params 100 y x)))
    (trans (matrix (map mean (trans (:coefs params))) (inc (ncol x))))
    (matrix (map mean (trans (:sigmas params))) (ncol y))
  "
  ([^Integer size y x & options]
    (let [opts (when options (apply assoc {} options))
          _x (bind-columns (repeat (nrow x) 1) x)
          ;_x x
          d (ncol y)
          k (ncol _x)
          df (dec (nrow y))
          ;y-vec (mapcat identity y)
          y-vec (vectorize y)
          I-d (identity-matrix d)
          xt (trans _x)
          xtx (mmult xt _x)
          kron-I-x (kronecker I-d _x)
         ]
      (loop [i 0 coefs nil sigmas (list (vectorize (identity-matrix d)))]
        (if (= i size)
          {:coefs (matrix coefs) :sigmas (matrix sigmas)}
          (let [s (trans (matrix (first sigmas) d))
                vb (solve (kronecker (solve s) xtx))
                ;mn (mmult vb (mapcat identity (mmult xt y (trans (solve s)))))
                mn (mmult vb (vectorize (mmult xt y (trans (solve s)))))
                b (plus mn (trans (mmult (trans (sample-normal (* d k))) (decomp-cholesky vb)))) ;; added trans to sample-normal output
                ;_ (println b)
                ;; draw s from inverse wishart
                ;e (matrix (minus y-vec (mmult kron-I-x b)) d)
                e (trans (matrix (minus y-vec (mmult kron-I-x b)) (nrow y)))
                ;_ (println (incanter.core/dim e))
                v (mmult (trans e) e)
                s-new (sample-inv-wishart :df df :scale v)]
            (recur (inc i) (conj coefs b) (conj sigmas (vectorize s-new)))))))))
