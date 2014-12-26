;;; stats.clj -- Statistics library for Clojure built on the CERN Colt Library

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


(ns ^{:doc "This is the core statistical library for Incanter.
            It provides probability functions (cdf, pdf, quantile),
            random number generation, statistical tests, basic
            modeling functions, similarity/association measures,
            and more.

            This library is built on Parallel Colt
            (http://sites.google.com/site/piotrwendykier/software/parallelcolt),
            an extension of the Colt numerics library
            (http://acs.lbl.gov/~hoschek/colt/).
            "
       :author "David Edgar Liebke and Bradford Cross"}
  incanter.stats
  (:import (cern.colt.list.tdouble DoubleArrayList)
           (cern.jet.random.tdouble Gamma Beta Binomial ChiSquare DoubleUniform
                                    Exponential NegativeBinomial Normal Poisson
                                    StudentT)
           (cern.jet.random.tdouble.engine DoubleMersenneTwister)
           (cern.jet.stat.tdouble DoubleDescriptive
                                  Probability)
           (incanter Weibull))
  (:require [clatrix.core :as clx])
  (:use [clojure.set :only [difference intersection union]])
  (:use [incanter.core :only ($ abs plus minus div mult mmult to-list bind-columns
                              gamma pow sqrt diag trans regularized-beta ncol
                              nrow identity-matrix decomp-cholesky decomp-svd
                              matrix length log10 sum sum-of-squares sel matrix?
                              cumulative-sum solve vectorize bind-rows safe-div)]))

(defn scalar-abs
  "Fast absolute value function"
  [x]
  (if (< x 0)
    (*' -1 x)
    x))

(defn- deep-merge-with
  "
  Copied here from clojure.contrib.map-utils. The original may have
  been a casualty of the clojure.contrib cataclysm.

  Like merge-with, but merges maps recursively, applying the given fn
  only when there's a non-map at a particular level.

  (deepmerge + {:a {:b {:c 1 :d {:x 1 :y 2}} :e 3} :f 4}
               {:a {:b {:c 2 :d {:z 9} :z 3} :e 100}})
  -> {:a {:b {:z 3, :c 3, :d {:z 9, :x 1, :y 2}}, :e 103}, :f 4}
  "
  [f & maps]
  (apply
    (fn m [& maps]
      (if (every? map? maps)
        (apply merge-with m maps)
        (apply f maps)))
    maps))

(defn indicator
  "
  Returns a sequence of ones and zeros, where ones
  are returned when the given predicate is true for
  corresponding element in the given collection, and
  zero otherwise.

  Examples:
    (use 'incanter.stats)

    (indicator #(neg? %) (sample-normal 10))

    ;; return the sum of the positive values in a normal sample
    (def x (sample-normal 100))
    (sum (mult x (indicator #(pos? %) x)))
  "

  [pred coll]
  (let [pred-int (fn [pred el]
                   (if (pred el) 1 0))]
    (if (coll? coll)
      (map (partial pred-int pred) coll)
      (pred-int pred coll))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CONTINUOUS DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; F DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-f
  "
  Returns the F pdf of the given value, x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's df function.

  Options:
    :df1 (default 1)
    :df2 (default 1)

  See also:
      cdf-f and quantile-f

  References:
      http://en.wikipedia.org/wiki/F_distribution
      http://mathworld.wolfram.com/F-Distribution.html
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-f 1.0 :df1 5 :df2 2)
  "
  ([x & {:keys [df1 df2] :or {df1 1 df2 1}}]
    (let [pdf-fx (fn [x]
                   (* (/ (gamma (/ (+ df1 df2) 2))
                         (* (gamma (/ df1 2)) (gamma (/ df2 2))))
                      (pow (/ df1 df2) (/ df1 2))
                      (pow x (dec (/ df1 2)))
                      (pow (inc (* (/ df1 df2) x))
                           (- 0 (/ (+ df1 df2) 2)))))
         ]
      (if (coll? x)
        (map pdf-fx x)
        (pdf-fx x)))))



(defn cdf-f
  "
  Returns the F-distribution cdf of the given value, x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's pf function.

  Options:
    :df1 (default 1)
    :df2 (default 1)
    :lower-tail? (default true)

  See also:
      pdf-f and quantile-f

  References:
      http://en.wikipedia.org/wiki/F_distribution
      http://mathworld.wolfram.com/F-Distribution.html
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-f 1.0 :df1 5 :df2 2)
  "
  ([x & {:keys [df1 df2 lower-tail?]
         :or {df1 1
              df2 1
              lower-tail? true}}]
    (let [cdf-fx (if lower-tail?
                   (fn [x1] (regularized-beta
                              (/ (* df1 x1) (+ df2 (* df1 x1)))
                              (/ df1 2)
                              (/ df2 2)))
                   (fn [x1] (- 1 (regularized-beta
                                   (/ (* df1 x1) (+ df2 (* df1 x1)))
                                   (/ df1 2)
                                   (/ df2 2)))))
         ]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NORMAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-normal
  "
  Returns the Normal pdf of the given value, x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's dnorm function.

  Options:
    :mean (default 0)
    :sd (default 1)

  See also:
      cdf-normal, quantile-normal, sample-normal

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html
      http://en.wikipedia.org/wiki/Normal_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-normal 1.96 :mean -2 :sd (sqrt 0.5))
  "
  ([x & {:keys [mean sd] :or {mean 0 sd 1}}]
    (let [dist (Normal. mean sd (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))



(defn cdf-normal
  "
  Returns the Normal cdf of the given value, x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's pnorm function.

  Options:
    :mean (default 0)
    :sd (default 1)

  See also:
      pdf-normal, quantile-normal, sample-normal

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html
      http://en.wikipedia.org/wiki/Normal_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-normal 1.96 :mean -2 :sd (sqrt 0.5))
  "
  ([x & {:keys [mean sd] :or {mean 0 sd 1}}]
    (let [dist (Normal. mean sd (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.cdf dist %) x)
        (.cdf dist x)))))


(defn quantile-normal
  "
  Returns the inverse of the Normal CDF for the given probability.
  It will return a sequence of values, if given a sequence of
  probabilities. This is equivalent to R's qnorm function.

  Options:
    :mean (default 0)
    :sd (default 1)

  Returns:
    a value x, where (cdf-normal x) = probability

  See also:
      pdf-normal, cdf-normal, and sample-normal

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Probability.html
      http://en.wikipedia.org/wiki/Normal_distribution
      http://en.wikipedia.org/wiki/Quantile

  Example:
      (quantile-normal 0.975)
      (quantile-normal [0.025 0.975] :mean -2 :sd (sqrt 0.5))
  "
  ([probability & {:keys [mean sd] :or {mean 0 sd 1}}]
    (let [x (if (coll? probability)
              (map #(Probability/normalInverse %) probability)
              (Probability/normalInverse probability))]
      (plus mean (mult sd x)))))



(defn sample-normal
  "
  Returns a sample of the given size from a Normal distribution
  This is equivalent to R's rnorm function.

  Options:
    :mean (default 0)
    :sd (default 1)

  See also:
      pdf-normal, cdf-normal, quantile-normal

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html
      http://en.wikipedia.org/wiki/Normal_distribution

  Example:
      (sample-normal 1000 :mean -2 :sd (sqrt 0.5))
  "
  ([^Integer size & {:keys [mean sd] :or {mean 0 sd 1}}]
    (if (= size 1)
      (Normal/staticNextDouble mean sd)
      (for [_ (range size)] (Normal/staticNextDouble mean sd)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MULTIVARIATE NORMAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn sample-mvn
  "
  Returns a sample of the given size from a Multivariate Normal
  distribution. This is equivalent to R's mvtnorm::rmvnorm function.

  Arguments:
    size -- the size of the sample to return

  Options:
    :mean (default (repeat (ncol sigma) 0))
    :sigma (default (identity-matrix (count mean)))


  Examples:

    (use '(incanter core stats charts))
    (def mvn-samp (sample-mvn 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))
    (covariance mvn-samp)
    (def means (map mean (trans mvn-samp)))

    ;; plot scatter-plot of points
    (def mvn-plot (scatter-plot (sel mvn-samp :cols 0) (sel mvn-samp :cols 1)))
    (view mvn-plot)
    ;; add centroid to plot
    (add-points mvn-plot [(first means)] [(second means)])

    ;; add regression line to scatter plot
    (def x (sel mvn-samp :cols 0))
    (def y (sel mvn-samp :cols 1))
    (def lm (linear-model y x))
    (add-lines mvn-plot x (:fitted lm))


  References:
    http://en.wikipedia.org/wiki/Multivariate_normal
  "
  ([^Integer size & {:keys [mean sigma]}]
    (let [mean (or mean (if sigma (repeat (ncol sigma) 0) [0]))
          sigma (or sigma (identity-matrix (count mean)))
          p (count mean)
          chol (decomp-cholesky sigma)
          norm-samp (mmult (matrix (sample-normal (* size p)) p) chol)]
      (if (> (nrow norm-samp) 1)
        (matrix (map #(plus % (trans mean)) norm-samp))
        (matrix (plus norm-samp (trans mean)))))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIFORM DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn pdf-uniform
  "
  Returns the Uniform pdf of the given value of x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's dunif function.

  Options:
    :min (default 0)
    :max (default 1)

  See also:
      cdf-uniform and sample-uniform

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html
      http://en.wikipedia.org/wiki/Uniform_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-uniform 5)
      (pdf-uniform 5 :min 1 :max 10)
  "
  ([x & {:keys [min max] :or {min 0.0 max 1.0}}]
    (let [dist (DoubleUniform. min max (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))


(defn cdf-uniform
  "
  Returns the Uniform cdf of the given value of x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's punif function.

  Options:
    :min (default 0)
    :max (default 1)

  See also:
      pdf-uniform and sample-uniform

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html
      http://en.wikipedia.org/wiki/Uniform_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-uniform 5)
      (cdf-uniform 5 :min 1 :max 10)
  "
  ([x & {:keys [min max] :or {min 0.0 max 1.0}}]
    (let [dist (DoubleUniform. (double min) (double max) (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.cdf dist %) x)
        (.cdf dist x)))))


(defn sample-uniform
  "
  Returns a sample of the given size from a Uniform distribution.
  This is equivalent to R's runif function.

  Options:
    :min (default 0)
    :max (default 1)
    :integers (default false)

  See also:
      pdf-uniform and cdf-uniform

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html
      http://en.wikipedia.org/wiki/Uniform_distribution

  Example:
      (sample-uniform 1000)
      (sample-uniform 1000 :min 1 :max 10)
  "
  ([^Integer size & {:keys [min max integers]
                     :or {min 0.0 max 1.0 integers false}}]
    (let [min-val (double min)
          max-val (double max)
          dist (DoubleUniform. min-val max-val (DoubleMersenneTwister.))]
      (if integers
        (for [_ (range size)] (DoubleUniform/staticNextIntFromTo min-val max-val))
        (for [_ (range size)] (DoubleUniform/staticNextDoubleFromTo min-val max-val))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; BETA DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-beta
  "
  Returns the Beta pdf of the given value of x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's dbeta function.

  Options:
    :alpha (default 1)
    :beta (default 1)

  See also:
      cdf-beta and sample-beta

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html
      http://en.wikipedia.org/wiki/Beta_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-beta 0.5 :alpha 1 :beta 2)
  "
  ([x & {:keys [alpha beta] :or {alpha 1 beta 1}}]
    (let [dist (Beta. alpha beta (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))


(defn cdf-beta
  "
  Returns the Beta cdf of the given value of x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's pbeta function.

  Options:
    :alpha (default 1)
    :beta (default 1)
    :lower-tail (default true)

  See also:
      pdf-beta and sample-beta

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html
      http://en.wikipedia.org/wiki/Beta_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-beta 0.5 :alpha 1 :beta 2)
      (cdf-beta 0.5 :alpha 1 :beta 2 :lower-tail false)
  "
  ([x & {:keys [alpha beta lower-tail?] :or {alpha 1 beta 1 lower-tail? false}}]
    (let [cdf-fx (if lower-tail?
                   (fn [x1] (Probability/beta alpha beta x1))
                   (fn [x1] (- 1 (Probability/betaComplemented alpha beta x1))))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-beta
  "
  Returns a sample of the given size from a Beta distribution.
  This is equivalent to R's rbeta function.

  Options:
    :alpha (default 1)
    :beta (default 1)
    These default values produce a Uniform distribution.

  See also:
      pdf-beta and cdf-beta

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html
      http://en.wikipedia.org/wiki/Beta_distribution

  Example:
      (sample-beta 1000 :alpha 1 :beta 2)
  "
  ([^Integer size & {:keys [alpha beta] :or {alpha 1 beta 1}}]
    (if (= size 1)
      (Beta/staticNextDouble alpha beta)
      (for [_ (range size)] (Beta/staticNextDouble alpha beta)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; WEIBULL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn pdf-weibull
  "
  Returns the Weibull pdf for the given value of x. It will return a sequence
  of values, if x is a sequence.

  Options:
      :scale (default 1)
      :shape (default 1)

  See also:
      cdf-weibull and sample-weibull

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html
      http://en.wikipedia.org/wiki/Weibull_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-weibull 2 :alpha 1 :beta 0.5)
  "
  ([x & options]
    (let [opts (when options (apply assoc {} options))
          scale (or (:scale opts) 1)
          shape (or (:shape opts) 1)
          dist (Weibull. scale shape (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))

(defn cdf-weibull
  "
  Returns the Weibull cdf for the given value of x. It will return a sequence
  of values, if x is a sequence.

  Options:
    :shape (default 1)
    :scale (default 1)

  See also:
      pdf-weibull and sample-weibull

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html
      http://en.wikipedia.org/wiki/Weibull_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-weibull 10 :shape 1 :scale 0.2)
  "
  ([x & options]
    (let [opts (when options (apply assoc {} options))
          scale (or (:scale opts) 1)
          shape (or (:shape opts) 1)
          dist (Weibull. scale shape (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.cdf dist %) x)
        (.cdf dist x)))))

(defn sample-weibull
  "
  Returns a sample of the given size from a Weibull distribution

  Options:
    :shape (default 1)
    :scale (default 1)

  See also:
      pdf-weibull, cdf-weibull

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html
      http://en.wikipedia.org/wiki/Weibull_distribution

  Example:
      (sample-weibull 1000 :shape 1 :scale 0.2)
  "
  ([size & options]
    (let [opts (when options (apply assoc {} options))
          scale (or (:scale opts) 1)
          shape (or (:shape opts) 1)]
      (if (= size 1)
        (Weibull/staticNextDouble scale shape)
        (for [_ (range size)] (Weibull/staticNextDouble scale shape))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; GAMMA DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-gamma
  "
  Returns the Gamma pdf for the given value of x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's dgamma function.

  Options:
    :shape (k) (default 1)
    :scale (θ) (default 1 or 1/rate, if :rate is specified)
    :rate  (β) (default 1/scale, if :scale is specified)

  See also:
      cdf-gamma and sample-gamma

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html
      http://en.wikipedia.org/wiki/Gamma_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-gamma 10 :shape 1 :scale 2)
  "
  ([x & {:keys [shape scale rate] :or {shape 1}}]
     (let [tscale (or scale (if (nil? rate) 1 (/ 1.0 rate)))
           dist (Gamma. shape tscale (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))

(defn cdf-gamma
  "
  Returns the Gamma cdf for the given value of x. It will return a sequence
  of values, if x is a sequence. This is equivalent to R's pgamma function.

  Options:
    :shape (k) (default 1)
    :scale (θ) (default 1 or 1/rate, if :rate is specified)
    :rate  (β) (default 1/scale, if :scale is specified)
    :lower-tail (default true)

  See also:
      pdf-gamma and sample-gamma

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html
      http://en.wikipedia.org/wiki/Gamma_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-gamma 10 :shape 1 :scale 2)
      (cdf-gamma 3 :shape 1 :lower-tail false)
  "
  ([x & {:keys [shape scale rate lower-tail?] :or {shape 1 lower-tail? true}}]
     (let [trate (or rate (if (nil? scale) 1 (/ 1.0 scale)))
           cdf-fx (if lower-tail?
                   (fn [x1] (Probability/gamma trate shape x1))
                   (fn [x1] (Probability/gammaComplemented trate shape x1)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))

(defn sample-gamma
  "
  Returns a sample of the given size from a Gamma distribution.
  This is equivalent to R's rgamma function.

  Options:
    :shape (k) (default 1)
    :scale (θ) (default 1 or 1/rate, if :rate is specified)
    :rate  (β) (default 1/scale, if :scale is specified)

  See also:
      pdf-gamma, cdf-gamma, and quantile-gamma

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html
      http://en.wikipedia.org/wiki/Gamma_distribution

  Example:
      (sample-gamma 1000 :shape 1 :scale 2)
  "
  ([^Integer size & {:keys [shape scale rate] :or {shape 1}}]
     (let [trate (or rate (if (nil? scale) 1 (/ 1.0 scale)))]
       (if (= size 1)
         (Gamma/staticNextDouble shape trate)
         (for [_ (range size)] (Gamma/staticNextDouble shape trate))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHI SQUARE DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-chisq
  "
  Returns the Chi Square pdf of the given value of x.  It will return a sequence
  of values, if x is a sequence. Equivalent to R's dchisq function.

  Options:
    :df (default 1)

  See also:
      cdf-chisq and sample-chisq

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html
      http://en.wikipedia.org/wiki/Chi_square_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-chisq 5.0 :df 2)
  "
  ([x & {:keys [df] :or {df 1}}]
    (let [dist (ChiSquare. df (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))



(defn cdf-chisq
  "
  Returns the Chi Square cdf of the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's pchisq function.

  Options:
    :df (default 1)
    :lower-tail (default true)

  See also:
      pdf-chisq and sample-chisq

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html
      http://en.wikipedia.org/wiki/Chi_square_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-chisq 5.0 :df 2)
      (cdf-chisq 5.0 :df 2 :lower-tail false)
  "
  ([x & {:keys [df lower-tail?] :or {df 1 lower-tail? true}}]
    (let [cdf-fx (if lower-tail?
                   (fn [x1] (Probability/chiSquare df x1))
                   (fn [x1] (Probability/chiSquareComplemented df x1)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-chisq
  "
  Returns a sample of the given size from a Chi Square distribution
  Equivalent to R's rchisq function.

  Options:
    :df (default 1)

  See also:
      pdf-chisq and cdf-chisq

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html
      http://en.wikipedia.org/wiki/Chi_square_distribution

  Example:
      (sample-chisq 1000 :df 2)
  "
  ([^Integer size & {:keys [df] :or {df 1}}]
    (if (= size 1)
      (ChiSquare/staticNextDouble df)
      (for [_ (range size)] (ChiSquare/staticNextDouble df)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; STUDENT'S T DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-t
  "
  Returns the Student's t pdf for the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's dt function.

  Options:
    :df (default 1)

  See also:
      cdf-t, quantile-t, and sample-t

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html
      http://en.wikipedia.org/wiki/Student-t_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-t 1.2 :df 10)
  "
  ([x & {:keys [df] :or {df 1}}]
    (let [dist (StudentT. df (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))


(defn cdf-t
  "
  Returns the Student's t cdf for the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's pt function.

  Options:
    :df (default 1)

  See also:
      pdf-t, quantile-t, and sample-t

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html
      http://en.wikipedia.org/wiki/Student-t_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-t 1.2 :df 10)
  "
  ([x & {:keys [df lower-tail?] :or {df 1 lower-tail? true}}]
    (let [cdf-fx (if lower-tail?
                   (fn [x1] (Probability/studentT df x1))
                   (fn [x1] (- 1 (Probability/studentT df x1))))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))


(defn quantile-t
  "
  Returns the inverse of the Student's t CDF for the given probability
  (i.e. the quantile).  It will return a sequence of values, if x is
  a sequence of probabilities. This is equivalent to R's qt function.

  Options:
    :df (default 1)

  Returns:
    a value x, where (cdf-t x) = probability

  See also:
     pdf-t, cdf-t, and sample-t

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Probability.html
      http://en.wikipedia.org/wiki/Student-t_distribution
      http://en.wikipedia.org/wiki/Quantile

  Example:
      (quantile-t 0.975)
      (quantile-t [0.025 0.975] :df 25)
      (def df [1 2 3 4 5 6 7 8 9 10 20 50 100 1000])
      (map #(quantile-t 0.025 :df %) df)
  "
  ([probability & {:keys [df] :or {df 1}}]
    (let [to-alpha (fn [prob] ;; need to convert the probability to an alpha value
                     (if (< prob 1/2)
                       (* 2 prob)
                       (* 2 (- 1 prob))))
          sign-fx (fn [x1 prob] (if (< prob 1/2) (* -1 x1) x1))
          x (if (coll? probability)
              (map #(sign-fx (Probability/studentTInverse (to-alpha %) df) %) probability)
              (sign-fx (Probability/studentTInverse (to-alpha probability) df) probability))]
      x)))



(defn sample-t
  "
  Returns a sample of the given size from a Student's t distribution.
  Equivalent to R's rt function.

  Options:
    :df (default 1)

  See also:
      pdf-t, cdf-t, and quantile-t

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html
      http://en.wikipedia.org/wiki/Student-t_distribution

  Example:
      (cdf-t 1000 :df 10)
  "
  ([size & {:keys [df] :or {df 1}}]
    (if (= size 1)
      (StudentT/staticNextDouble df)
      (for [_ (range size)] (StudentT/staticNextDouble df)))))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EXPONENTIAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-exp
  "
  Returns the Exponential pdf of the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's dexp.

  Options:
    :rate (default 1)

  See also:
      cdf-exp and sample-exp

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html
      http://en.wikipedia.org/wiki/Exponential_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-exp 2.0 :rate 1/2)
  "
  ([x & {:keys [rate] :or {rate 1}}]
    (let [dist (Exponential. rate (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))


(defn cdf-exp
  "
  Returns the Exponential cdf of the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's pexp.

  Options:
    :rate (default 1)

  See also:
      pdf-exp and sample-exp

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html
      http://en.wikipedia.org/wiki/Exponential_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-exp 2.0 :rate 1/2)
  "
  ([x & {:keys [rate] :or {rate 1}}]
    (let [dist (Exponential. rate (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.cdf dist %) x)
        (.cdf dist x)))))


(defn sample-exp
  "
  Returns a sample of the given size from a Exponential distribution.
  Equivalent to R's rexp.

  Options:
    :rate (default 1)

  See also:
      pdf-exp and cdf-exp

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html
      http://en.wikipedia.org/wiki/Exponential_distribution

  Example:
      (sample-exp 1000 :rate 1/2)
  "
  ([size & {:keys [rate] :or {rate 1}}]
    (if (= size 1)
      (Exponential/staticNextDouble rate)
      (for [_ (range size)] (Exponential/staticNextDouble rate)))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; WISHART DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn sample-wishart
  "
  Returns a p-by-p symmetric distribution drawn from a Wishart distribution

  Options:
    :p (default 2) -- number of dimensions of resulting matrix
    :df (default p) -- degree of freedoms (aka n), df <= p
    :scale (default (identity-matrix p)) -- positive definite matrix (aka V)

  Examples:
    (use 'incanter.stats)
    (sample-wishart :df 10  :p 4)

    ;; calculate the mean of 1000 wishart matrices, should equal (mult df scale)
    (div (reduce plus (for [_ (range 1000)] (sample-wishart :p 4))) 1000)


  References:
    http://en.wikipedia.org/wiki/Wishart_distribution
  "
  ([& {:keys [scale p df] :or {p 2}}]
    (let [scale (or scale (when p (identity-matrix p)))
          p (count scale)
          df (or df p)
          diagonal (for [i (range 1 (inc p))]
                     (pow (sample-chisq 1 :df (inc (- df i))) 1/2))
          mat (diag diagonal)
          indices (for [i (range p) j (range p) :when (< j i)] [i j])
          _ (doseq [indx indices] (clx/set mat (first indx) (second indx) (sample-normal 1)))
          chol (decomp-cholesky scale)
          x (mmult chol mat (trans mat) (trans chol))]
      x)))



(defn sample-inv-wishart
  "
  Returns a p-by-p symmetric distribution drawn from an inverse-Wishart distribution

  Options:
    :p (default 2) -- number of dimensions of resulting matrix
    :df (default p) -- degree of freedoms (aka n), df <= p
    :scale (default (identity-matrix p)) -- positive definite matrix (aka V)

  Examples:
    (use 'incanter.stats)
    (sample-inv-wishart :df 10  :p 4)

    ;; calculate the mean of 1000 wishart matrices, should equal (mult df scale)
    (div (reduce plus (for [_ (range 1000)] (sample-wishart :p 4))) 1000)


  References:
    http://en.wikipedia.org/wiki/Inverse-Wishart_distribution
  "
  ([& {:keys [scale p df] :or {p 2}}]
    (let [scale (or scale (when p (identity-matrix p)))
          p (count scale)
          df (or df p)]
      (solve (sample-wishart :p p :df df :scale scale)))))


(defn sample-dirichlet
  "
  Examples:
    (use '(incanter core stats charts))

    ;; a total of 1447 adults were polled to indicate their preferences for
    ;; candidate 1 (y1=727), candidate 2 (y2=583), or some other candidate or no
    ;; preference (y3=137).

    ;; the counts y1, y2, and y3 are assumed to have a multinomial distribution
    ;; If a uniform prior distribution is assigned to the multinomial vector
    ;; theta = (th1, th2, th3), then the posterior distribution of theta is
    ;; proportional to g(theta) = th1^y1 * th2^y2 * th3^y3, which is a
    ;; dirichlet distribution with parameters (y1+1, y2+1, y3+1)
    (def  theta (sample-dirichlet 1000 [(inc 727) (inc 583) (inc 137)]))
    ;; view means, 95% CI, and histograms of the proportion parameters
    (mean (sel theta :cols 0))
    (quantile (sel theta :cols 0) :probs [0.0275 0.975])
    (view (histogram (sel theta :cols 0)))
    (mean (sel theta :cols 1))
    (quantile (sel theta :cols 1) :probs [0.0275 0.975])
    (view (histogram (sel theta :cols 1)))
    (mean (sel theta :cols 2))
    (quantile (sel theta :cols 2) :probs [0.0275 0.975])
    (view (histogram (sel theta :cols 2)))

    ;; view  a histogram of the difference in proportions between the first
    ;; two candidates
    (view (histogram (minus (sel theta :cols 0) (sel theta :cols 1))))
  "

  ([^Integer size alpha]
    (let [W (trans (for [a alpha] (sample-gamma size :shape a :scale 1)))
          T (map sum W)]
      (matrix (map #(div %1 %2) W T)))))






;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DISCRETE DISTRIBUTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; BINOMIAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-binomial
  "
  Returns the Binomial pdf of the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's dbinom.

  Options:
    :size (default 1)
    :prob (default 1/2)

  See also:
      cdf-binomial and sample-binomial

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html
      http://en.wikipedia.org/wiki/Binomial_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-binomial 10 :prob 1/4 :size 20)
  "
  ([x & {:keys [size prob] :or {size 1 prob 1/2}}]
    (let [dist (Binomial. size prob (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))



(defn cdf-binomial
  "
  Returns the Binomial cdf of the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's pbinom.

  Options:
    :size (default 1)
    :prob (default 1/2)
    :lower-tail (default true)

  See also:
      pdf-binomial and sample-binomial

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html
      http://en.wikipedia.org/wiki/Binomial_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-binomial 10 :prob 1/4 :size 20)
  "
  ([x & {:keys [size prob lower-tail?] :or {size 1 prob 1/2 lower-tail? true}}]
    (let [cdf-fx (if lower-tail?
                   (fn [x1] (Probability/binomial x1 size prob))
                   (fn [x1] (Probability/binomialComplemented x1 size prob)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-binomial
  "
  Returns a sample of the given size from a Binomial distribution.
  Equivalent to R's rbinom.

  Options:
    :size (default 1)
    :prob (default 1/2)

  See also:
      pdf-binomial and cdf-binomial

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html
      http://en.wikipedia.org/wiki/Binomial_distribution

  Example:
      (sample-binomial 1000 :prob 1/4 :size 20)
  "
  ([^Integer samplesize & {:keys [size prob] :or {size 1 prob 1/2}}]
    (if (= samplesize 1)
      (Binomial/staticNextInt size prob)
      (repeatedly samplesize #(Binomial/staticNextInt size prob)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MULTINOMIAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn sample-multinomial
  "
  Returns a sequence representing a sample from a multinomial distribution.

  Arguments: size -- number of values to return

  Options:
    :categories (default [0 1]) -- the values returned
    :probs (default [0.5 0.5]) -- the probabilities associated with each category


  References:
    http://en.wikipedia.org/wiki/Multinomial_distribution#Sampling_from_a_multinomial_distribution


  Examples:
    (use '(incanter core stats charts))

    (sample-multinomial 10)
    (sample-multinomial 10 :probs [0.25 0.5 0.25])

    ;; estimate sample proportions
    (def sample-size 1000.0)
    (def categories [:red :yellow :blue :green])
    (def data (to-dataset (sample-multinomial sample-size
                                              :categories categories
                                              :probs [0.5 0.25 0.2 0.05])))

    ;; check the sample proportions
    (view (pie-chart categories
                     (map #(div (count ($ :col-0 ($where {:col-0 %} data)))
                                sample-size)
                          categories)))
  "
  ([size & {:keys [probs categories] :or {probs [0.5 0.5]}}]
    (let [categories (or categories (range (count probs)))
          cumulative-probs (cumulative-sum probs)]
      (for [x (sample-uniform size)]
        (loop [i 0]
          (if (>= (nth cumulative-probs i) x)
            (nth categories i)
            (recur (inc i))))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; POISSON DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-poisson
  "
  Returns the Poisson pdf of the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's dpois.

  Options:
    :lambda (default 1)

  See also:
      cdf-poisson and sample-poisson

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html
      http://en.wikipedia.org/wiki/Poisson_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-poisson 5 :lambda 10)
  "
  ([x & {:keys [lambda] :or {lambda 1}}]
    (let [dist (Poisson. lambda (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))



(defn cdf-poisson
  "
  Returns the Poisson cdf of the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent R's ppois.

  Options:
    :lambda (default 1)
    :lower-tail (default true)

  See also:
      pdf-poisson and sample-poisson

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html
      http://en.wikipedia.org/wiki/Poisson_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-poisson 5 :lambda 10)
  "
  ([x & {:keys [lambda lower-tail?] :or {lambda 1 lower-tail? true}}]
    (let [cdf-fx (if lower-tail?
                   (fn [x1] (Probability/poisson x1 lambda))
                   (fn [x1] (Probability/poissonComplemented x1 lambda)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-poisson
  "
  Returns a sample of the given size from a Poisson distribution.
  Equivalent to R's rpois.

  Options:
    :lambda (default 1)

  See also:
      pdf-poisson and cdf-poisson

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html
      http://en.wikipedia.org/wiki/Poisson_distribution

  Example:
      (sample-poisson 1000 :lambda 10)
  "
  ([^Integer size & {:keys [lambda] :or {lambda 1}}]
    (if (= size 1)
      (Poisson/staticNextInt lambda)
      (for [_ (range size)] (Poisson/staticNextInt lambda)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NEGATIVE BINOMIAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn pdf-neg-binomial
  "
  Returns the Negative Binomial pdf of the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's dnbinom.

  Options:
    :size (default 10)
    :prob (default 1/2)

  See also:
      cdf-neg-binomial and sample-neg-binomial

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html
      http://en.wikipedia.org/wiki/Negative_binomial_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example:
      (pdf-neg-binomial 10 :prob 1/2 :size 20)
  "
  ([x & {:keys [size prob] :or {size 10 prob 1/2}}]
    (let [dist (NegativeBinomial. size prob (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))




(defn cdf-neg-binomial
  "
  Returns the Negative Binomial cdf of the given value of x. It will return a sequence
  of values, if x is a sequence. Equivalent to R's dnbinom.

  Options:
    :size (default 10)
    :prob (default 1/2)
    :lower-tail? (default true)

  See also:
      pdf-neg-binomial and sample-neg-binomial

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html
      http://en.wikipedia.org/wiki/Negative_binomial_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example:
      (cdf-neg-binomial 10 :prob 1/2 :size 20)
  "
  ([x & {:keys [size prob lower-tail?] :or {size 10 prob 1/2 lower-tail? true}}]
    (let [cdf-fx (if lower-tail?
                   (fn [x1] (Probability/negativeBinomial x1 size prob))
                   (fn [x1] (Probability/negativeBinomialComplemented x1 size prob)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-neg-binomial
  "
  Returns a sample of the given size from a Negative Binomial distribution.
  Equivalent to R's rnbinom.

  Options:
    :size (default 10)
    :prob (default 1/2)

  See also:
      pdf-neg-binomial and cdf-neg-binomial

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html
      http://en.wikipedia.org/wiki/Negative_binomial_distribution

  Example:
      (sample-neg-binomial 1000 :prob 1/2 :size 20)
  "
  ([^Integer samplesize & {:keys [size prob] :or {size 10 prob 1/2}}]
    (if (= samplesize 1)
      (NegativeBinomial/staticNextInt size prob)
      (repeatedly samplesize #(NegativeBinomial/staticNextInt size prob)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EMPIRICAL DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn cdf-empirical
  "
  Returns a step-function representing the empirical cdf of the given data.
  Equivalent to R's ecdf function.

  The following description is from the ecdf help in R: The e.c.d.f.
  (empirical cumulative distribution function) Fn is a step function
  with jumps i/n at observation values, where i is the number of tied
  observations at that value.  Missing values are ignored.

  For observations 'x'= (x1,x2, ... xn), Fn is the fraction of
  observations less or equal to t, i.e.,

  Fn(t) = #{x_i <= t} / n  =  1/n sum(i=1,n) Indicator(xi <= t).


  Examples:
    (use '(incanter core stats charts))

    (def exam1 [192 160 183 136 162 165 181 188 150 163 192 164 184
                189 183 181 188 191 190 184 171 177 125 192 149 188
                154 151 159 141 171 153 169 168 168 157 160 190 166 150])

    ;; the ecdf function returns an empirical cdf function for the given data
    (def ecdf (cdf-empirical exam1))

    ;; plot the data's empirical cdf
    (view (scatter-plot exam1 (map ecdf exam1)))
  "


  ([x] (fn [t] (div (sum (indicator #(<= % t) x)) (count x)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; STATISTICAL FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn mean
  "
  Returns the mean of the data, x.

  Examples:
    (mean (sample-normal 100))

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
    http://en.wikipedia.org/wiki/Mean
  "
  ([x]
    (let [xx (to-list x)]
      (DoubleDescriptive/mean (DoubleArrayList. (double-array xx))))))



(defn variance
  "
  Returns the sample variance of the data, x. Equivalent to R's var function.

  Examples:
    (variance (sample-normal 100))

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
    http://en.wikipedia.org/wiki/Sample_variance#Population_variance_and_sample_variance
  "
  ([x]
    (let [xx (to-list x)]
      (DoubleDescriptive/sampleVariance (DoubleArrayList. (double-array xx)) (mean x)))))



(defn covariance
  "
  Returns the sample covariance of x and y.

  Examples:
    ;; create some data that covaries
    (def x (sample-normal 100))
    (def err (sample-normal 100))
    (def y (plus (mult 5 x) err))
    ;; estimate the covariance of x and y
    (covariance x y)

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
    http://en.wikipedia.org/wiki/Covariance
  "
  ([x y]
    (let [xx (to-list x)
          yy (to-list y)]
      (DoubleDescriptive/covariance
        (DoubleArrayList. (double-array xx))
        (DoubleArrayList. (double-array yy)))))
  ([mat]
    (let [n (ncol mat)]
      (matrix
        (for [i (range n) j (range n)]
          (covariance (sel mat true i) (sel mat true j))) n))))



(defn sd
  "
  Returns the sample standard deviation of the data, x. Equivalent to
  R's sd function.

  Examples:
    (sd (sample-normal 100))

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
    http://en.wikipedia.org/wiki/Standard_deviation
  "
  ([x]
    ;; population sd, not the sample sd
    ;; return the sample standard deviation
    (sqrt (variance x))))



(defn correlation
  "
  Returns the sample correlation of x and y, or the correlation
  matrix of the given matrix.

  Examples:

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
    http://en.wikipedia.org/wiki/Correlation
  "
  ([x y]
    (let [xx (to-list x)
          yy (to-list y)
          sd-x (sd x)
          sd-y (sd y)]
      (if (not= (* sd-x sd-y) 0)
        (DoubleDescriptive/correlation
          (DoubleArrayList. (double-array xx)) (sd x)
          (DoubleArrayList. (double-array yy)) (sd y))
        0.0)))
  ([mat]
   (div (covariance mat)
        (sqrt (mmult (diag (covariance mat)) (trans (diag (covariance mat))))))))


(defn auto-correlation
  "
  Returns the auto-correlation of x with given lag, mean, and variance.
  If no mean or variance is provided, the they are calculated from x.

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
  "
  ([x lag]
    (auto-correlation x lag (mean x) (variance x)))
  ([x lag mean variance]
    (DoubleDescriptive/autoCorrelation
      (DoubleArrayList. (double-array (to-list x)))
      lag
      mean
      variance)))


(defn median
  "
  Returns the median of the data, x.

  Examples:
    (median (sample-normal 100))

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
    http://en.wikipedia.org/wiki/Median
  "
  ([x]
    (let [xx (sort (to-list x))]
      (if (empty? xx)
        Double/NaN
        (DoubleDescriptive/median (DoubleArrayList. (double-array xx)))))))


(defn kurtosis
  "
  Returns the kurtosis of the data, x. \"Kurtosis is a measure of the \"peakedness\"
  of the probability distribution of a real-valued random variable. Higher kurtosis
  means more of the variance is due to infrequent extreme deviations, as opposed to
  frequent modestly-sized deviations.\" (Wikipedia)

  Examples:

    (kurtosis (sample-normal 100000)) ;; approximately 0
    (kurtosis (sample-gamma 100000)) ;; approximately 6

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
    http://en.wikipedia.org/wiki/Kurtosis
  "
  ([x] (DoubleDescriptive/kurtosis (DoubleArrayList. (double-array x)) (mean x) (sd x))))



(defn skewness
  "
  Returns the skewness of the data, x. \"Skewness is a measure of the asymmetry
  of the probability distribution of a real-valued random variable.\" (Wikipedia)

  Examples:

    (skewness (sample-normal 100000)) ;; approximately 0
    (skewness (sample-gamma 100000)) ;; approximately 2

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
    http://en.wikipedia.org/wiki/Skewness
  "
  ([x] (DoubleDescriptive/skew (DoubleArrayList. (double-array x)) (mean x) (sd x))))



(defn quantile
  "
  Returns the quantiles of the data, x. By default it returns the min,
  25th-percentile, 50th-percentile, 75th-percentile, and max value.

  Options:
    :probs (default [0.0 0.25 0.5 0.75 1.0])

  Examples:
    (quantile (sample-normal 100))
    (quantile (sample-normal 100) :probs [0.025 0.975])
    (quantile (sample-normal 100) :probs 0.975)

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html
    http://en.wikipedia.org/wiki/Quantile
  "
  ([x & {:keys [probs] :or {probs (DoubleArrayList. (double-array [0.0 0.25 0.5 0.75 1.0]))}}]
    (let [_x (to-list x)
          data (DoubleArrayList. (double-array (sort _x)))
          probs (if (coll? probs)
                  (DoubleArrayList. (double-array probs))
                  probs)]
      (if (number? probs)
        (DoubleDescriptive/quantile data probs)
        (seq (.elements (DoubleDescriptive/quantiles data probs)))))))



(defmulti sample
  "
  Returns a sample of the given size from the given collection. If replacement
  is set to false it returns a set, otherwise it returns a list.

  Arguments:
    coll -- collection or dataset to be sampled from

  Options:
    :size -- (default (count x) sample size
    :replacement (default true) -- sample with replacement


  Examples:
    (sample (range 10)) ; permutation of numbers zero through ten
    (sample [:red :green :blue] :size 10) ; choose 10 items that are either :red, :green, or :blue.
    (sample (seq \"abcdefghijklmnopqrstuvwxyz\")  :size 4 :replacement false) ; choose 4 random letters.
  "
  (fn [coll & _]
    (cond
      (instance? incanter.core.Dataset coll) ::dataset
      :else ::coll)))

(defmethod sample ::coll
  [x & {:keys [size replacement] :or {size (count x) replacement true}}]
  (let [max-idx (dec (count x))]
    (if (= size 1)
      (nth x (rand-int (inc max-idx)))
      (if replacement
        (map #(nth x %) (sample-uniform size :min 0 :max max-idx :integers true))
        (if (> size (count x))
          (throw (Exception. "'size' can't be larger than (count x) without replacement!"))
          (map #(nth x %)
               (loop [samp-indices [] indices-set #{}]
                 (if (= (count samp-indices) size)
                   samp-indices
                   (let [i (first (sample-uniform 1 :min 0 :max max-idx :integers true))]
                     (if (contains? indices-set i)
                       (recur samp-indices indices-set)
                       (recur (conj samp-indices i) (conj indices-set i))))))))))))

(defmethod sample ::dataset
  [ds & options]
  (let [r  (range (nrow ds))
        r  (apply sample r options)
        r  (if (seq? r)
             (sort r)
             r)]
    (sel ds :rows r)))


(defn bootstrap
  "
  Returns a bootstrap sample of the given statistic on the given data.

  Arguments:
    data -- vector of data to resample from
    statistic -- a function that returns a value given a vector of data

  Options:
    :size -- the number of bootstrap samples to return
    :smooth -- (default false) smoothing option
    :smooth-sd -- (default (/ (sqrt (count data)))) determines the standard
                  deviation of the noise to use for smoothing
    :replacement -- (default true) determines if sampling of the data
                    should be done with replacement


  References:
    1. Clifford E. Lunneborg, Data Analysis by Resampling Concepts and Applications, 2000, pages 105-117
    2. http://en.wikipedia.org/wiki/Bootstrapping_(statistics)


  Examples:

    ;; example from Data Analysis by Resampling Concepts and Applications
    ;; Clifford E. Lunneborg (pages 119-122)

    (use '(incanter core stats charts))

    ;; weights (in grams) of 50 randoincanter. sampled bags of preztels
    (def weights [464 447 446 454 450 457 450 442
                  433 452 449 454 450 438 448 449
                  457 451 456 452 450 463 464 453
                  452 447 439 449 468 443 433 460
                  452 447 447 446 450 459 466 433
                  445 453 454 446 464 450 456 456
                  447 469])

    ;; calculate the sample median, 450
    (median weights)

    ;; generate bootstrap sample
    (def t* (bootstrap weights median :size 2000))

    ;; view histogram of bootstrap histogram
    (view (histogram t*))

    ;; calculate the mean of the bootstrap median ~ 450.644
    (mean t*)

    ;; calculate the standard error ~ 1.083
    (def se (sd t*))

    ;; 90% standard normal CI ~ (448.219 451.781)
    (plus (median weights) (mult (quantile-normal [0.05 0.95]) se))

    ;; 90% symmetric percentile CI ~ (449.0 452.5)
    (quantile t* :probs [0.05 0.95])


    ;; 90% non-symmetric percentile CI ~ (447.5 451.0)
    (minus (* 2 (median weights)) (quantile t* :probs [0.95 0.05]))

    ;; calculate bias
    (- (mean t*) (median weights)) ;; ~ 0.644

    ;; example with smoothing
    ;; Newcomb's speed of light data

    (use '(incanter core stats charts))

    ;; A numeric vector giving the Third Series of measurements of the
    ;; passage time of light recorded by Newcomb in 1882. The given
    ;; values divided by 1000 plus 24 give the time in millionths of a
    ;; second for light to traverse a known distance. The 'true' value is
    ;; now considered to be 33.02.

    (def speed-of-light [28 -44  29  30  24  28  37  32  36  27  26  28  29
                         26  27  22  23  20  25 25  36  23  31  32  24  27
                         33  16  24  29  36  21  28  26  27  27  32  25 28
                         24  40  21  31  32  28  26  30  27  26  24  32  29
                         34  -2  25  19  36 29  30  22  28  33  39  25  16  23])

    ;; view histogram of data to see outlier observations
    (view (histogram speed-of-light :nbins 30))

    (def samp (bootstrap speed-of-light median :size 10000))
    (view (histogram samp :density true :nbins 30))
    (mean samp)
    (quantile samp :probs [0.025 0.975])

    (def smooth-samp (bootstrap speed-of-light median :size 10000 :smooth true))
    (view (histogram smooth-samp :density true :nbins 30))
    (mean smooth-samp)
    (quantile smooth-samp :probs [0.025 0.975])
  "
  ([data statistic & {:keys [size replacement smooth? smooth-sd]
                      :or {replacement true
                           smooth? false
                           smooth-sd (/ (sqrt (count data)))}}]
     (let [n (count data)
           B1 100
           B2 25
          max-iter 10
          D 0.01
          samp (if (nil? size)
                 (loop [stats (for [_ (range B1)] (statistic (sample data :size n :replacement replacement)))
                        k 0]
                   (let [stats2 (concat stats (for [_ (range B2)] (statistic (sample data :size n :replacement replacement))))
                         se1 (sd stats)
                         se2 (sd stats2)]
                     (if (or (= k max-iter) (< (* (- 1 D) se1) se2 (* (inc D) se1)))
                       stats2
                       (recur stats2 (inc k)))))
                 (for [_ (range size)] (statistic (sample data :size n :replacement replacement))))
          samp-size (count samp)]
      (if smooth? (plus samp (sample-normal samp-size :sd smooth-sd)) samp))))







(defn cumulative-mean
  "
  Returns a sequence of cumulative means for the given collection. For instance
  The first value equals the first value of the argument, the second value is
  the mean of the first two arguments, the third is the mean of the first three
  arguments, etc.

  Examples:
    (cumulative-mean (sample-normal 100))
  "
  ([coll] (map / (cumulative-sum coll) (range 1 (inc (count coll))))))



(defn sweep
  "
  Return an array obtained from an input array by sweeping out a
  summary statistic. Based to R's sweep function.

  Arguments:
    x is an sequence


  Options:
        :stat (default mean) the statistic to sweep out
        :fun (defaul minus) the function used to sweep the stat out

  Example:

    (use '(incanter core stats))

    (def x (sample-normal 30 :mean 10 :sd 5))
    (sweep x) ;; center the data around mean
    (sweep x :stat sd :fun div) ;; divide data by its sd
  "
  ([x & {:keys [stat fun] :or {stat mean fun minus}}]
    (fun x (stat x))))




(defn permute
  "
  If provided a single argument, returns a permuted version of the
  given collection. (permute x) is the same as (sample x).

  If provided two arguments, returns two lists that are permutations
  across the given collections. In other words, each of the new collections
  will contain elements from both of the given collections. Useful for
  permutation tests or randomization tests.

  Examples:

    (permute (range 10))
    (permute (range 10) (range 10 20))
  "
  ([x]
    (sample x))

  ([x y]
    (let [n1 (count x)
          samp (sample (concat x y) :replacement false)
          new-x (take n1 samp)
          new-y (drop n1 samp)]
      (list new-x new-y))))



(defn sample-permutations
  "
  If provided a two arguments (n x), it returns a list of n permutations
  of x. If provided three (n x y) arguments, returns a list with two with n permutations of
  each arguments, where each permutation is drawn from the pooled arguments.

  Arguments:
    n -- number of randomized versions of the original two groups to return
    x -- group 1
    y -- (default nil) group 2


  Examples:

    (use '(incanter core stats))
    (sample-permutations 10 (range 10))
    (sample-permutations 10 (range 10) (range 10 20))

    ;; extended example with plant-growth data
    (use '(incanter core stats datasets charts))

    ;; load the plant-growth dataset
    (def data (to-matrix (get-dataset :plant-growth)))

    ;; break the first column of the data into groups based on treatment (second column).
    (def groups (group-on data 1 :cols 0))

    ;; define a function for the statistic of interest
    (defn means-diff [x y] (minus (mean x) (mean y)))

    ;; calculate the difference in sample means between the two groups
    (def samp-mean-diff (means-diff (first groups) (second groups))) ;; 0.371

    ;; create 500 permuted versions of the original two groups
    (def permuted-groups (sample-permutations 1000 (first groups) (second groups)))

    ;; calculate the difference of means of the 500 samples
    (def permuted-means-diffs1 (map means-diff (first permuted-groups) (second permuted-groups)))

    ;; use an indicator function that returns 1 when the randomized means diff is greater
    ;; than the original sample mean, and zero otherwise. Then take the mean of this sequence
    ;; of ones and zeros. That is the proportion of times you would see a value more extreme
    ;; than the sample mean (i.e. the p-value).
    (mean (indicator #(> % samp-mean-diff) permuted-means-diffs1)) ;; 0.088

    ;; calculate the 95% confidence interval of the null hypothesis. If the
    ;; sample difference in means is outside of this range, that is evidence
    ;; that the two means are statistically significantly different.
    (quantile permuted-means-diffs1 :probs [0.025 0.975]) ;; (-0.606 0.595)

    ;; Plot a histogram of the permuted-means-diffs using the density option,
    ;; instead of the default frequency, and then add a normal pdf curve with
    ;; the mean and sd of permuted-means-diffs data for a visual comparison.
    (doto (histogram permuted-means-diffs1 :density true)
          (add-lines (range -1 1 0.01) (pdf-normal (range -1 1 0.01)
                                                   :mean (mean permuted-means-diffs1)
                                                   :sd (sd permuted-means-diffs1)))
          view)

    ;; compare the means of treatment 2 and control
    (def permuted-groups (sample-permutations 1000 (first groups) (last groups)))
    (def permuted-means-diffs2 (map means-diff (first permuted-groups) (second permuted-groups)))
    (def samp-mean-diff (means-diff (first groups) (last groups))) ;; -0.4939
    (mean (indicator #(< % samp-mean-diff) permuted-means-diffs2)) ;; 0.022
    (quantile permuted-means-diffs2 :probs [0.025 0.975]) ;; (-0.478 0.466)

    ;; compare the means of treatment 1 and treatment 2
    (def permuted-groups (sample-permutations 1000 (second groups) (last groups)))
    (def permuted-means-diffs3 (map means-diff (first permuted-groups) (second permuted-groups)))
    (def samp-mean-diff (means-diff (second groups) (last groups))) ;; -0.865
    (mean (indicator #(< % samp-mean-diff) permuted-means-diffs3)) ;;  0.002
    (quantile permuted-means-diffs3 :probs [0.025 0.975]) ;; (-0.676 0.646)

    (doto (box-plot permuted-means-diffs1)
          (add-box-plot permuted-means-diffs2)
          (add-box-plot permuted-means-diffs3)
          view)


    Further Reading:
      http://en.wikipedia.org/wiki/Resampling_(statistics)
  "
  ([^Integer n x]
    (loop [samp '() i 0]
      (if (= i n)
        samp
          (recur
            (conj samp (sample x)) (inc i)))))

  ([^Integer n x y]
    (let [pool (concat x y)
          m1 (count x)]
      (loop [samp-x '() samp-y '() i 0]
        (if (= i n)
          (list samp-x samp-y)
          (let [perm-samp (sample pool)
                new-x (take m1 perm-samp)
                new-y (drop m1 perm-samp)]
            (recur (conj samp-x new-x) (conj samp-y new-y) (inc i))))))))



;;;;;;;;;;;;;;; OLS REGRESSION FUNCTIONS ;;;;;;;;;;;;;;;;;;;;;;;;;;



(defn linear-model
  "
  Returns the results of performing a OLS linear regression of y on x.

  Arguments:
    y is a vector (or sequence) of values for the dependent variable
    x is a vector or matrix of values for the independent variables

  Options:
    :intercept (default true) indicates weather an intercept term should be included

  Returns:
    a map, of type ::linear-model, containing:
      :design-matrix -- a matrix containing the independent variables, and an intercept columns
      :coefs -- the regression coefficients
      :t-tests -- t-test values of coefficients
      :t-probs -- p-values for t-test values of coefficients
      :coefs-ci -- 95% percentile confidence interval
      :fitted -- the predicted values of y
      :residuals -- the residuals of each observation
      :std-errors -- the standard errors of the coeffients
      :sse -- the sum of squared errors, also called the residual sum of squares
      :ssr -- the regression sum of squares, also called the explained sum of squares
      :sst -- the total sum of squares (proportional to the sample variance)
      :r-square -- coefficient of determination

  Examples:
    (use '(incanter core stats datasets charts))
    (def iris (to-matrix (get-dataset :iris) :dummies true))
    (def y (sel iris :cols 0))
    (def x (sel iris :cols (range 1 6)))
    (def iris-lm (linear-model y x)) ; with intercept term

    (keys iris-lm) ; see what fields are included
    (:coefs iris-lm)
    (:sse iris-lm)
    (quantile (:residuals iris-lm))
    (:r-square iris-lm)
    (:adj-r-square iris-lm)
    (:f-stat iris-lm)
    (:f-prob iris-lm)
    (:df iris-lm)

    (def x1 (range 0.0 3 0.1))
    (view (xy-plot x1 (cdf-f x1 :df1 4 :df2 144)))


  References:
    http://en.wikipedia.org/wiki/OLS_Regression
    http://en.wikipedia.org/wiki/Coefficient_of_determination
  "
  ([y x & {:keys [intercept] :or {intercept true}}]
    (let [_x (if intercept (bind-columns (repeat (nrow x) 1) x) x)
          xtx (mmult (trans _x) _x)
          xtxi (if (number? xtx) (/ 1 xtx) (solve xtx))
          xty (mmult (trans _x) y)
          coefs (if (and (number? xtxi) (number? xty))
                  (* xtxi xty)
                  (to-list (if (or (number? xtxi) (number? xty))
                    (mult xtxi xty)
                    (mmult xtxi xty))))
          fitted (to-list (if (number? coefs)
                   (mult _x coefs)
                   (mmult _x coefs)))
          resid (to-list (minus y fitted))
          sse (sum-of-squares resid)
          ssr (sum-of-squares (minus fitted (mean fitted)))
          sst (+ sse ssr)
          r-square (safe-div ssr sst)
          n (nrow y)
          p (ncol _x)
          p-1 (if intercept (dec p) p)
          adj-r-square (- 1 (* (- 1 r-square) (safe-div (dec n) (- n p 1))))
          mse (safe-div sse (- n p))
          msr (safe-div ssr p-1)
          f-stat (safe-div msr mse)
          df1 p-1
          df2 (- n p)
          f-prob (cdf-f f-stat :df1 df1 :df2 df2 :lower-tail? false)
          coef-var (mult mse xtxi)
          std-errors (sqrt (diag coef-var))
          t-tests (div coefs std-errors)
          t-probs (mult 2 (cdf-t (abs t-tests) :df df2 :lower-tail? false))
          t-95 (mult (quantile-t 0.975 :df df2) std-errors)
          coefs-ci (if (number? std-errors)
                     [(plus coefs t-95)
                      (minus coefs t-95)]
                     (partition 2
                       (interleave
                         (minus coefs t-95)
                         (plus coefs t-95))))]
      (with-meta
        {:x _x
         :y y
         :fitted fitted
         :design-matrix _x
         :coefs coefs
         :t-tests t-tests
         :t-probs t-probs
         :coefs-ci coefs-ci
         :residuals resid
         :std-errors std-errors
         :sse sse
         :ssr ssr
         :sst sst
         :mse mse
         :msr msr
         :f-stat f-stat
         :f-prob f-prob
         :df [df1 df2]
         :coef-var coef-var
         :r-square r-square
         :adj-r-square adj-r-square
        }
        {:type ::linear-model}))))



(defn t-test
  "
  Argument:
    x : sample to test

  Options:
    :y (default nil)
    :mu (default (mean y) or 0) population mean
    :alternative (default :two-sided) other choices :less :greater
    :var-equal TODO (default false) variance equal
    :paired TODO (default false) paired test
    :conf-level (default 0.95) for returned confidence interval

  Examples:

    (t-test (range 1 11) :mu 0)
    (t-test (range 1 11) :mu 0 :alternative :less)
    (t-test (range 1 11) :mu 0 :alternative :greater)

    (t-test (range 1 11) :y (range 7 21))
    (t-test (range 1 11) :y (range 7 21) :alternative :less)
    (t-test (range 1 11) :y (range 7 21) :alternative :greater)
    (t-test (range 1 11) :y (conj (range 7 21) 200))

  References:
    http://en.wikipedia.org/wiki/T_test
    http://www.socialresearchmethods.net/kb/stat_t.php
  "

  ([x & {:keys [y mu paired conf-level alternative var-equal]
         :or {paired false
              alternative :two-sided
              conf-level 0.95
              var-equal false}}]
    (let [one-sample? (nil? y)
          mu (or mu (if y (mean y) 0))
          x-mean (mean x)
          x-var (variance x)
          n1 (count x)
          y-mean (when-not one-sample? (mean y))
          y-var (when-not one-sample? (variance y))
          n2 (when-not one-sample? (count y))
          t-stat (if one-sample?
                   (/ (- x-mean mu) (/ (sqrt x-var) (sqrt n1)))
                   ;; calculate Welch's t test
                   (/ (- x-mean y-mean) (sqrt (+ (/ x-var n1) (/ y-var n2)))))
          df (if one-sample?
               (dec n1)
               ;; calculate Welch-Satterthwaite equation
               (/ (pow (+ (/ x-var n1) (/ y-var n2)) 2)
                  (+ (/ (pow (/ x-var n1) 2) (dec n1))
                     (/ (pow (/ y-var n2) 2) (dec n2)))))
          lower-tail? (cond
                        (= alternative :two-sided)
                          (if (neg? t-stat) true false)
                        (= alternative :lower)
                          (if (neg? t-stat) false true)
                        (= alternative :greater)
                          (if one-sample?
                            (if (neg? t-stat) true false)
                            (if (neg? t-stat) false true)))
          one-sided-p (cdf-t t-stat :df df :lower-tail? lower-tail?)
          qt (if (= alternative :two-sided)
               (quantile-t (/ (- 1 conf-level) 2) :df df)
               (quantile-t (- 1 conf-level) :df df))]
      {:t-stat t-stat
       :df df
       :x-mean x-mean
       :x-var x-var
       :n1 n1
       :y-mean y-mean
       :y-var y-var
       :n2 n2
       ;;FIXME: This should never be *2!  This is wrong...
       :p-value (if (= alternative :two-sided) (* 2 one-sided-p) one-sided-p)
       :conf-int (if one-sample?
                   ;; one-sample confidence interval
                   [(if (= alternative :less)
                      Double/NEGATIVE_INFINITY
                      (+ x-mean (/ (* qt (sqrt x-var)) (sqrt n1))))
                    (if (= alternative :greater)
                      Double/POSITIVE_INFINITY
                      (- x-mean (/ (* qt (sqrt x-var)) (sqrt n1))))]
                   ;; two-sample confidence interval
                   [(if (= alternative :less)
                      Double/NEGATIVE_INFINITY
                      (+ (- x-mean y-mean) (* qt (sqrt (+ (/ x-var n1) (/ y-var n2))))))
                    (if (= alternative :greater)
                      Double/POSITIVE_INFINITY
                      (- (- x-mean y-mean) (* qt (sqrt (+ (/ x-var n1) (/ y-var n2))))))])
       })))

(defn simple-t-test
  "Perform a simple t-test on the data contained in coll."
  [coll mu]
  (let [m (mean coll)
        c (count coll)]
    (/ (- m mu)
       (/ (sd coll)
          (sqrt c)))))

(defn simple-p-value
  "Returns the p-value for the data contained in coll."
  [coll mu]
  (* 2
     (cdf-t
      (- (scalar-abs (simple-t-test coll mu)))
      :df (dec (count coll)))))

(defn simple-ci
  "Get the confidence interval for the data."
  [coll]
  (let [m (mean coll)
        c (count coll)
        s (sd coll)
        e (*
           (quantile-t 0.975 :df (dec c))
           (/ s (sqrt c)))]
    {:lower (- m e) :upper (+ m e)}))

(defn f-test
  "
  Test for different variances between 2 samples

  Argument:
    x : 1st sample to test
    y : 2nd sample to test

  Options:

  References:
    http://en.wikipedia.org/wiki/F-test
    http://people.richland.edu/james/lecture/m170/ch13-f.html
  "
  ([x y]
    (let [x-var (variance x)
          y-var (variance y)
          pval (* 2 (- 1.0
                       (if (> x-var y-var)
                         (cdf-f (/ x-var y-var) :df1 (dec (count x)) :df2 (dec (count y)))
                         (cdf-f (/ y-var x-var) :df1 (dec (count y)) :df2 (dec (count x))))))]
      pval)))


(defn tabulate
  "
  Cross-tabulates the values of the given numeric matrix.

  Returns a hash-map with the following fields:
    :table -- the table of counts for each combination of values,
              this table is only returned if x has two-columns
    :levels -- a sequence of sequences, where each sequence list
               the levels (possible values) of the corresponding
               column of x.
    :margins -- a sequence of sequences, where each sequence
                represents the marginal total for each level
                of the corresponding column of x.
    :counts -- a hash-map, where vectors of unique combinations
               of the cross-tabulated levels are the keys and the
               values are the total count of each combination.
    :N  -- the grand-total for the contingency table


  Examples:

    (use '(incanter core stats))
    (tabulate [1 2 3 2 3 2 4 3 5])
    (tabulate (sample-poisson 100 :lambda 5))

    (use '(incanter core stats datasets))
    (def math-prog (to-matrix (get-dataset :math-prog)))
    (tabulate (sel math-prog :cols [1 2]))


    (def data (matrix [[1 0 1]
                       [1 1 1]
                       [1 1 1]
                       [1 0 1]
                       [0 0 0]
                       [1 1 1]
                       [1 1 1]
                       [1 0 1]
                       [1 1 0]]))
    (tabulate data)


    (def data (matrix [[1 0]
                       [1 1]
                       [1 1]
                       [1 0]
                       [0 0]
                       [1 1]
                       [1 1]
                       [1 0]
                       [1 1]]))
    (tabulate data)
  "
  ([x & options]
    (let [_x (if (matrix? x) x (matrix x))
          p (ncol _x)
          n (nrow _x)
          levels (for [i (range p)] (sort (seq (into #{} (sel _x :cols i)))))
          margins (for [j (range p)]
                  (loop [marg {} i (int 0)]
                    (if (= i n)
                      marg
                      (let [lvl (sel _x :rows i :cols j)]
                        (recur (let [cnt (get marg lvl)]
                                 (if cnt
                                   (assoc marg lvl (inc cnt))
                                   (assoc marg lvl 1)))
                              (inc i))))))
          counts (loop [tab {} i (int 0)]
                    (if (= i n)
                      tab
                      (recur (let [row (if (> p 1)
                                         (to-list (nth _x i))
                                         (nth _x i))
                                   cnt (get tab row)]
                              (if (nil? cnt)
                                (assoc tab row 1)
                                (assoc tab row (inc cnt))))
                            (inc i))))
          n-levels (map #(count (keys %)) margins)]

      {:counts counts
       :margins margins
       :table (when (= p 2)
                (matrix (for [r (first levels)
                              c (second levels)]
                          (let [c (get counts (to-list (trans [r c])))]
                            (if c c 0)))
                        (second n-levels)))
       :n-vars p
       :N (reduce + (vals (first margins)))
       :n-levels n-levels
       :levels levels})))







(defn chisq-test
  "
  Performs chi-squared contingency table tests and goodness-of-fit tests.

  If the optional argument :y is not provided then a goodness-of-fit test
  is performed. In this case, the hypothesis tested is whether the
  population probabilities equal those in :probs, or are all equal if
  :probs is not given.

  If :y is provided, it must be a sequence of integers that is the
  same length as x. A contingency table is computed from x and :y.
  Then, Pearson's chi-squared test of the null hypothesis that the joint
  distribution of the cell counts in a 2-dimensional contingency
  table is the product of the row and column marginals is performed.
  By default the Yates' continuity correction for 2x2 contingency
  tables is performed, this can be disabled by setting the :correct
  option to false.


  Options:
    :x -- a sequence of numbers.
    :y -- a sequence of numbers
    :table -- a contingency table. If one dimensional, the test is a goodness-of-fit
    :probs (when (nil? y) -- (repeat n-levels (/ n-levels)))
    :freq (default nil) -- if given, these are rescaled to probabilities
    :correct (default true) -- use Yates' correction for continuity for 2x2 contingency tables


  Returns:
    :X-sq -- the Pearson X-squared test statistics
    :p-value -- the p-value for the test statistic
    :df -- the degress of freedom


  Examples:
    (use '(incanter core stats))
    (chisq-test :x [1 2 3 2 3 2 4 3 5]) ;; X-sq 2.6667
    ;; create a one-dimensional table of this data
    (def table (matrix [1 3 3 1 1]))
    (chisq-test :table table) ;; X-sq 2.6667
    (chisq-test :table (trans table)) ;; throws exception

    (chisq-test :x [1 0 0 0  1 1 1 0 0 1 0 0 1 1 1 1]) ;; 0.25

    (use '(incanter core stats datasets))
    (def math-prog (to-matrix (get-dataset :math-prog)))
    (def x (sel math-prog :cols 1))
    (def y (sel math-prog :cols 2))
    (chisq-test :x x :y y) ;; X-sq = 1.24145, df=1, p-value = 0.26519
    (chisq-test :x x :y y :correct false) ;; X-sq = 2.01094, df=1, p-value = 0.15617

    (def table (matrix [[31 12] [9 8]]))
    (chisq-test :table table) ;; X-sq = 1.24145, df=1, p-value = 0.26519
    (chisq-test :table table :correct false) ;; X-sq = 2.01094, df=1, p-value = 0.15617
    ;; use the detabulate function to create data rows corresponding to the table
    (def detab (detabulate :table table))
    (chisq-test :x (sel detab :cols 0) :y (sel detab :cols 1))

    ;; look at the hair-eye-color data
    ;; turn the count data for males into a contingency table
    (def male (matrix (sel (get-dataset :hair-eye-color) :cols 3 :rows (range 16)) 4))
    (chisq-test :table male) ;; X-sq = 41.280, df = 9, p-value = 4.44E-6
    ;; turn the count data for females into a contingency table
    (def female (matrix (sel (get-dataset :hair-eye-color) :cols 3 :rows (range 16 32)) 4))
    (chisq-test :table female) ;; X-sq = 106.664, df = 9, p-value = 7.014E-19,


    ;; supply probabilities to goodness-of-fit test
    (def table [89 37 30 28 2])
    (def probs [0.40 0.20 0.20 0.19 0.01])
    (chisq-test :table table :probs probs) ;; X-sq = 5.7947, df = 4, p-value = 0.215

    ;; use frequencies instead of probabilities
    (def freq [40 20 20 15 5])
    (chisq-test :table table :freq freq) ;; X-sq = 9.9901, df = 4, p-value = 0.04059



  References:
    http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm
    http://en.wikipedia.org/wiki/Pearson's_chi-square_test
    http://en.wikipedia.org/wiki/Yates'_chi-square_test
  "

  ([& {:keys [x y correct table probs freq] :or {correct true }}]
    (let [table? (if table true false)
          xtab (when (or x y)
                 (if y
                   (tabulate (bind-columns x y))
                   (tabulate x)))
          levels (if table?
                   (map range ((juxt nrow ncol) table))
                   (:levels xtab))
          r-levels (first levels)
          c-levels (second levels)
          table (cond
                  table? table
                  (and x y) (:table xtab)
                  :else (matrix (map #(get-in xtab [:counts %] 0) r-levels)))
          two-samp? (if (or (and x y)
                            (and table?
                                 (and (> (nrow table) 1) (> (ncol table) 1))))
                      true false)
          r-margins (if table?
                      (if two-samp?
                        (apply hash-map (interleave r-levels (map sum table)))
                        (if (> (nrow table) 1)
                          (to-list table)
                          (throw (Exception. "One dimensional tables must have only a single column"))))
                      (first (:margins xtab)))
          c-margins (if table?
                      (if two-samp?
                        (apply hash-map (interleave c-levels (map sum (trans table))))
                        0)
                      (second (:margins xtab)))

          counts (if two-samp? (vectorize table) table)
          N (if table?
              (sum counts)
              (:N xtab))
          n (when-not two-samp? (count r-levels))
          df (if two-samp? (* (dec (nrow table)) (dec (ncol table))) (dec n))
          probs (when-not two-samp?
                  (cond
                    (not (nil? probs)) probs
                    (not (nil? freq)) (div freq (sum freq))
                    :else (repeat n (/ n))))
          E (if two-samp?
              (for [c c-levels r r-levels]
                (/ (* (c-margins c) (r-margins r)) N))
              (mult N probs))
          X-sq (if (and correct (and (= (count r-levels) 2) (= (count c-levels) 2)))
                 (reduce + (map (fn [o e] (/ (pow (- (scalar-abs (- o e)) 0.5) 2) e)) counts E))
                 (reduce + (map (fn [o e] (/ (pow (- o e) 2) e)) counts E)))
         ]
      {:X-sq X-sq
       :df df
       :two-samp? two-samp?
       :p-value (cdf-chisq X-sq :df df :lower-tail? false)
       :probs probs
       :N N
       :table table
       :col-levels c-levels
       :row-levels r-levels
       :col-margins c-margins
       :row-margins r-margins
       :E E})))

;;;; START Benford's Law ;;;;
(defn first-digit
  [x]
  (Character/digit (first (str x)) 10))

;; define function for Benford's law
(defn- benford-law [d] (log10 (inc (div d))))
;; calculate the probabilities for digits 1-9
(def ^{:private true}
      benford-probs (map benford-law (range 1 11)))

(defn get-counts [digits]
  (map #(get (:counts (tabulate digits)) % 0)
       (range 1.0 10.0 1.0)))

(defn benford-test
  "
  Performs Benford's Law test using chisq-test.

  Argument:
  coll: -- a sequence of numbers

  Returns:
    :X-sq -- the Pearson X-squared test statistics
    :p-value -- the p-value for the test statistic
    :df -- the degress of freedom

  Reference:
  http://data-sorcery.org/2009/06/21/chi-square-goodness-of-fit/
  http://en.wikipedia.org/wiki/Benford%27s_Law
  "
  [coll]
  (let [digits (map first-digit coll)]
    (chisq-test :table (get-counts digits)
                :probs benford-probs)))

;;;; END Benford's Law ;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Dataset Summarization Functions

(defn- count-col-types
  "Takes in a column name or number and a dataset. Returns a raw count of each type present in that column. Counts nils."
  ([col ds]
    (frequencies (map type ($ col ds)))))


(defn- stat-summarizable
  "Placeholder stub function, for more advanced cases where we want to automatically ignore occasional bad values in a column."
  ([types]
    ; FIXME Add the column name
    "Statistical summarizablity is currently stubbed out. Please contact the dev team if you're seeing this message."))


(defn numeric-col-summarizer
  "Returns a summarizer function which takes a purely numeric column with no non-numeric values"
  ([col ds]
    {:col col :min (reduce min (remove nil? ($ col ds))) :max (reduce max (remove nil? ($ col ds)))
     :mean (mean (remove nil? ($ col ds))) :median (median (remove nil? ($ col ds))) :is-numeric true}))


(defn category-col-summarizer
  "Returns a summarizer function which takes a category column and returns a list of the top 5 columns by volume, and a
   count of remaining rows"
  ([col ds]
    (let [freqs (frequencies ($ col ds)) top-5 (take 5 (reverse (sort-by val freqs)))]
      (into {:col col :count (- (reduce + (map val freqs)) (reduce + (map val (into {} top-5)))) :is-numeric false} top-5))))


(defn choose-singletype-col-summarizer
  "Takes in a type, and returns a suitable column summarizer"
  ([col-type]
    (if (.isAssignableFrom java.lang.Number col-type)
        numeric-col-summarizer
        (if (or (.isAssignableFrom java.lang.String col-type) (.isAssignableFrom clojure.lang.Keyword col-type))
          category-col-summarizer
          ; FIXME Deal with date columns
          (str "Don't know how to summarize a column of type: " col-type)
          ))))


(defn summarizer-fn
  "
  Takes in a column (number or name) and a dataset. Returns a function
  to summarize the column if summarizable, and a string describing why
  the column can't be summarized in the event that it can't
  "
  ([col ds]
   (let [type-counts (dissoc (count-col-types col ds) nil)]
    (if (= 1 (count type-counts))
        (choose-singletype-col-summarizer (nth (keys type-counts) 0))
        (if (every? #(.isAssignableFrom java.lang.Number %) (keys type-counts))
            numeric-col-summarizer
            (if (and (= 2 (count type-counts)) (contains? type-counts java.lang.String) (contains? type-counts clojure.lang.Keyword))
                category-col-summarizer
                (stat-summarizable type-counts)))))))

(defn summarizable?
  "Takes in a column name (or number) and a dataset. Returns true if the column can be summarized, and false otherwise"
  ([col ds]
    (fn? (summarizer-fn col ds))))


(defn summary
  "
  Takes in a dataset. Returns a summary of that dataset (as a map of maps),
  having automatically figured out the relevant datatypes of columns.
  Will be slightly forgiving of mangled data in columns."
  ([ds]
    (let [cols (:column-names ds)]
      (map #(let [r (summarizer-fn %1 ds)]
              (if (fn? r)
                  (r %1 ds)
                  r)) cols))))

; (def amt-fn (summarizer-fn (keyword "Amount Funded By Investors") loans))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(defn principal-components
  "
  Performs a principal components analysis on the given data matrix.
  Equivalent to R's prcomp function.

  Returns:
    A map with the following fields:
    :std-dev -- the standard deviations of the principal components
        (i.e. the square roots of the eigenvalues of the correlation
        matrix, though the calculation is actually done with the
        singular values of the data matrix.
    :rotation -- the matrix of variable loadings (i.e. a matrix
        whose columns contain the eigenvectors).


  Examples:

    (use '(incanter core stats charts datasets))
    ;; load the iris dataset
    (def iris (to-matrix (get-dataset :iris)))
    ;; run the pca
    (def pca (principal-components (sel iris :cols (range 4))))
    ;; extract the first two principal components
    (def pc1 (sel (:rotation pca) :cols 0))
    (def pc2 (sel (:rotation pca) :cols 1))

    ;; project the first four dimension of the iris data onto the first
    ;; two principal components
    (def x1 (mmult (sel iris :cols (range 4)) pc1))
    (def x2 (mmult (sel iris :cols (range 4)) pc2))

    ;; now plot the transformed data, coloring each species a different color
    (doto (scatter-plot (sel x1 :rows (range 50)) (sel x2 :rows (range 50))
                        :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\")
          (add-points (sel x1 :rows (range 50 100)) (sel x2 :rows (range 50 100)))
          (add-points (sel x1 :rows (range 100 150)) (sel x2 :rows (range 100 150)))
          view)


    ;; alternatively, the :group-by option can be used in scatter-plot
    (view (scatter-plot x1 x2
                        :group-by (sel iris :cols 4)
                        :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))


  References:
    http://en.wikipedia.org/wiki/Principal_component_analysis
  "
  ([x & options]
    (let [svd (decomp-svd (correlation x))
          rotation (:V svd)
          std-dev (sqrt (:S svd))]
      {:std-dev std-dev
       :rotation rotation})))



(defn detabulate
  "
  Take a contingency table of counts and returns a matrix of observations.

  Examples:

    (use '(incanter core stats datasets))

    (def by-gender (group-on (get-dataset :hair-eye-color) 2))
    (def table (matrix (sel (first by-gender) :cols 3) 4))

    (detabulate :table table)
    (tabulate (detabulate :table table))

    ;; example 2
    (def data (matrix [[1 0]
                       [1 1]
                       [1 1]
                       [1 0]
                       [0 0]
                       [1 1]
                       [1 1]
                       [1 0]
                       [1 1]]))
    (tabulate data)

    (tabulate (detabulate :table (:table (tabulate data))))
  "
  ([& {:keys [table row-labels col-labels]}]
     (let [row-labels (when table (or row-labels (range (nrow table))))
           col-labels (when table (or col-labels (range (ncol table))))
           data (apply bind-rows
                       (apply concat
                              (for [r row-labels c col-labels]
                                (repeat (sel table :rows r :cols c) [r c]))))]
       data)))

;;TODO: finish gamma, kendall's-w and

;;TODO mote this to a new task in tracker: nonparametric stuff such as: http://en.wikipedia.org/wiki/Median_absolute_deviation

;;smoothing.
;;http://en.wikipedia.org/wiki/Smoothing
;;http://en.wikipedia.org/wiki/Kernel_smoother
;;http://en.wikipedia.org/wiki/Kernel_density_estimation
;;http://en.wikipedia.org/wiki/Local_regression
;;http://en.wikipedia.org/wiki/Kernel_regression

;;DEPENDENCE
;; http://en.wikipedia.org/wiki/Association_(statistics)
;; http://en.wikipedia.org/wiki/Independence_(probability_theory)
;; http://en.wikipedia.org/wiki/Goodman_and_Kruskal%27s_lambda
;; http://en.wikipedia.org/wiki/Category:Statistical_dependence
;; http://en.wikipedia.org/wiki/Tetrachoric_correlation_coefficient
;; http://en.wikipedia.org/wiki/Polychoric_correlation
;;TODO:  http://en.wikipedia.org/wiki/Multiple_correlation
;;TODO: further work in mutivariate dependence
;;http://en.wikipedia.org/wiki/Concordance_correlation_coefficient

;;TODO: more research on spellchecking, string similarity
;;http://en.wikipedia.org/wiki/Approximate_string_matching
;;http://en.wikipedia.org/wiki/Longest_common_subsequence_problem

(defn within
  "y is within z of x in metric space."
  [z x y]
    (< (scalar-abs (- x y)) z))

(defn square-devs-from-mean
  "takes either a sample or a sample and a precalculated mean.
  returns the squares of the difference between each observation and the sample mean."
  ([x]
    (square-devs-from-mean x (mean x)))

  ([x m]
    (map
      #(pow
        (- % m) 2)
      x)))

(defn sum-of-square-devs-from-mean
  "takes either a sample or a sample and a precalculated mean.

  returns the sum of the squares of the difference between each observation and the sample mean."
  ([x]
    (sum-of-square-devs-from-mean x (mean x)))

  ([x m]
    (apply + (square-devs-from-mean x m))))

(defn simple-regression
  "A stripped version of linear-model that returns a map containing only
  the coefficients."
  ([y x & {:keys [intercept] :or {intercept true}}]
    (let [_x (if intercept (bind-columns (repeat (nrow x) 1) x) x)
          xtx (mmult (trans _x) _x)
          xtxi (if (number? xtx) (/ 1 xtx) (solve xtx))
          xty (mmult (trans _x) y)
          coefs (if (and (number? xtxi) (number? xty))
                  (* xtxi xty)
                  (to-list (if (or (number? xtxi) (number? xty))
                    (mult xtxi xty)
                    (mmult xtxi xty))))]
      {:coefs coefs})))


(defn predict
  "Takes a linear-model and an x value (either a scalar or vector)
 and returns the predicted value based on the linear-model."
 ([model x]
   (let [predictor (if (coll? x)
                     (cons 1 x)
                     [1 x])
         res (mmult (trans (:coefs model))
                    predictor)]
     (if (and (matrix? res)
              (= 1 (nrow res)) (= 1 (ncol res)))
       (sel res 0 0)
       res))))


(defn odds-ratio
  "
  http://en.wikipedia.org/wiki/Odds_ratio

  Definition in terms of group-wise odds

  The odds ratio is the ratio of the odds of an event occurring in one group
  to the odds of it occurring in another group, or to a sample-based estimate of that ratio.


  Suppose that in a sample of 100 men, 90 have drunk wine in the previous week,
  while in a sample of 100 women only 20 have drunk wine in the same period.
  The odds of a man drinking wine are 90 to 10, or 9:1,
  while the odds of a woman drinking wine are only 20 to 80, or 1:4 = 0.25:1.
  The odds ratio is thus 9/0.25, or 36, showing that men are much more likely
  to drink wine than women.

  Relation to statistical independence

  If X and Y are independent, their joint probabilities can be expressed in
  terms of their marginal probabilities. In this case, the odds ratio equals one,
  and conversely the odds ratio can only equal one if the joint probabilities
  can be factored in this way. Thus the odds ratio equals one if and only if
  X and Y are independent.
  "

  [p1 p2]
    (/ (* p1 (- 1 p2))
       (* p2 (- 1 p1))))

(defn correlation-ratio
  "
  http://en.wikipedia.org/wiki/Correlation_ratio

  In statistics, the correlation ratio is a measure of the relationship between
  the statistical dispersion within individual categories and the
  dispersion across the whole population or sample. i.e. the weighted variance
  of the category means divided by the variance of all samples.

  Example

  Suppose there is a distribution of test scores in three topics (categories):

    * Algebra: 45, 70, 29, 15 and 21 (5 scores)
    * Geometry: 40, 20, 30 and 42 (4 scores)
    * Statistics: 65, 95, 80, 70, 85 and 73 (6 scores).

  Then the subject averages are 36, 33 and 78, with an overall average of 52.

  The sums of squares of the differences from the subject averages are 1952
  for Algebra, 308 for Geometry and 600 for Statistics, adding to 2860,
  while the overall sum of squares of the differences from the overall average
  is 9640. The difference between these of 6780 is also the weighted sum of the
  square of the differences between the subject averages and the overall average:

    5(36 − 52)2 + 4(33 − 52)2 + 6(78 − 52)2 = 6780

  This gives

    eta^2 =6780/9640=0.7033

  suggesting that most of the overall dispersion is a result of differences
  between topics, rather than within topics. Taking the square root

    eta = sqrt 6780/9640=0.8386

  Observe that for η = 1 the overall sample dispersion is purely due to dispersion
  among the categories and not at all due to dispersion within the individual
  categories. For a quick comprehension simply imagine all Algebra, Geometry,
  and Statistics scores being the same respectively, e.g. 5 times 36, 4 times 33, 6 times 78.
  "

  [& xs]
    (let [sos (map sum-of-square-devs-from-mean xs)
          all (apply concat xs)
          overall-sos (sum-of-square-devs-from-mean all)]
      (sqrt
        (/ (- overall-sos (apply + sos))
        overall-sos))))

(defn correlation-linearity-test
  "
  http://en.wikipedia.org/wiki/Correlation_ratio
  It is worth noting that if the relationship between values of  and values of
  overline y_x is linear (which is certainly true when there are only two
  possibilities for x) this will give the same result as the square of the
  correlation coefficient, otherwise the correlation ratio will be larger in magnitude.
  It can therefore be used for judging non-linear relationships.
  "
  [a b]
    (- (correlation-ratio a b)
       (correlation a b)))

(defn rank-index
  "
  Given a seq, returns a map where the keys are the values of the seq
  and the values are the positional rank of each member o the seq.
  "
  [x]
  (apply merge-with concat (map hash-map (sort x) (map list (range 1 (inc (count x)))))))


(defn spearmans-rho
  "
  http://en.wikipedia.org/wiki/Spearman%27s_rank_correlation_coefficient

  In statistics, Spearman's rank correlation coefficient or Spearman's rho,
  is a non-parametric measure of correlation – that is, it assesses how well
  an arbitrary monotonic function could describe the relationship between two
  variables, without making any other assumptions about the particular nature
  of the relationship between the variables. Certain other measures of correlation
  are parametric in the sense of being based on possible relationships of a
  parameterised form, such as a linear relationship.
  "
  [a b]
    {:pre [(= (count a) (count b))]}
    (let [n (count a)
          arank (rank-index a)
          brank (rank-index b)
	  dsos (apply 
                 + (map (fn [x y] (pow
                  (- (incanter.stats/mean (arank x)) (incanter.stats/mean (brank y)))
                    2))
           a b))]
    (- 1 (/ (* 6 dsos)
            (* n (dec (pow n 2)))))))





(defn- key-compare
  [x y]
    (cond
      (and
        (keyword? x)
        (not (keyword? y))) 1
      (and
        (keyword? y)
        (not (keyword? x))) -1
      :otherwise (compare x y)))

;;weird inversion makes us revers k1 and k2
(defn- kv-compare [[k1 v1] [k2 v2]] (key-compare k2 k1))

;;TDOO: doesn't seem to work? test and beat on it.
;;use clojure sorting: sort-by, sorted-map-by, etc.
(defn- sort-map [m] (into {} (sort kv-compare m)))



(defn kendalls-tau
  "
  http://en.wikipedia.org/wiki/Kendall_tau_rank_correlation_coefficient
  http://www.statsdirect.com/help/nonparametric_methods/kend.htm
  http://mail.scipy.org/pipermail/scipy-dev/2009-March/011589.html
  best explanation and example is in \"cluster analysis for researchers\" page 165.
  http://www.amazon.com/Cluster-Analysis-Researchers-Charles-Romesburg/dp/1411606175
  "
  [a b]
  {:pre [(= (count a) (count b))]}
    (let [n (count a)
          ranked (reverse (sort-map (zipmap a b)))
          ;;dcd is the meat of the calculation, the difference between the doncordant and discordant pairs
          dcd (second
                (reduce
                  (fn [[vals total] [k v]]
                    (let [diff (- (count (filter #(> % v) vals))
                                  (count (filter #(< % v) vals)))]
                      [(conj vals v) (+ total diff)]))
                  [[] 0]
                  ranked))]
    (/ (* 2 dcd)
       (* n (dec n)))))

(defn pairs
  "
  Returns unique pairs of a and b where members of a and b can not
  be paired with the corresponding slot in the other list.
  "
  [a b]
    ((fn combine [combos ra rb]
      (let [heada (first ra)
            level-combos (for [bx (rest rb)]
                           [heada bx])
            all-combos (concat combos level-combos)]
        (if (zero? (count (rest ra)))
          all-combos
          (combine all-combos (rest ra) (rest rb))))) [] a b))

(defn pairings
  "
  Creates pairs by matching a1 with b1, a2 with b2, etc. and returns
  all pairs of those pairs without matching a pair with itself.
  "
  [a b]
  (let [tuples (zipmap a b)]
    (pairs tuples tuples)))

(defn concordant?
  "
  Given two pairs of numbers, checks if they are concordant.
  "
  [[[a1 b1][a2 b2] & more]]
  (if (nil? more)
    (= (compare a1 a2)
       (compare b1 b2))
    (throw (Exception. "Too many arguments!"))))

(defn concordant-pairs
  "http://en.wikipedia.org/wiki/Concordant_pairs"
  [a b]
    (let [tuples (zipmap a b)
          ps (pairs tuples tuples)]
      (count (filter concordant? ps))))


(def discordant? (comp not concordant?))

(defn discordant-pairs
  "http://en.wikipedia.org/wiki/Discordant_pairs"
  [a b]
    (let [tuples (zipmap a b)
          ps (pairs tuples tuples)]
      (count (filter discordant? ps))))

(def kendalls-tau-distance discordant-pairs)

;;TODO: factor out duplication between the distance metric and regular kendall's tau
(defn normalized-kendall-tau-distance
  "
  http://en.wikipedia.org/wiki/Kendall_tau_distance
  Kendall tau distance is the total number of discordant pairs.
  "
  [a b]
    (let [n (count a)
          discords (discordant-pairs a b)]
      (/ (* 2 discords)
         (* n (dec n)))))


(defn gamma-coefficient
  "
  http://www.statsdirect.com/help/nonparametric_methods/kend.htm
  The gamma coefficient is given as a measure of association that
  is highly resistant to tied data (Goodman and Kruskal, 1963)
  "
  [a b]
  (let [nc (concordant-pairs a b)
        nd (discordant-pairs a b)]
    (/ (- nc nd) (+ nc nd))))

(defn kendalls-w
  "
  http://en.wikipedia.org/wiki/Kendall%27s_W
  http://faculty.chass.ncsu.edu/garson/PA765/friedman.htm

  Suppose that object i is given the rank ri,j by judge number j, where there
  are in total n objects and m judges. Then the total rank given to object i is

    Ri = sum Rij

  and the mean value of these total ranks is

    Rbar = 1/2 m (n + 1)

  The sum of squared deviations, S, is defined as

    S=sum1-n (Ri - Rbar)

  and then Kendall's W is defined as[1]

    W= 12S / m^2(n^3-n)

  If the test statistic W is 1, then all the survey respondents have been
  unanimous, and each respondent has assigned the same order to the list
  of concerns. If W is 0, then there is no overall trend of agreement among
  the respondents, and their responses may be regarded as essentially random.
  Intermediate values of W indicate a greater or lesser degree of unanimity
  among the various responses.

  Legendre[2] discusses a variant of the W statistic which accommodates ties
  in the rankings and also describes methods of making significance tests based on W.

  [{:observation [1 2 3]} {} ... {}] -> W
  "
  []) ;TODO: implement




(defn sum-variance-test
  "
  The variance of the sum of n independent variables is equal
  to the sum of their variances.

   (variance-independence-test [[1 2 3 4] [1 2 3 4]]) -> 5/2
  "
  [vs]
    (- (variance (apply map + vs))
       (apply + (map variance vs))))

;;TODO: don't implement until fully understanding whether these are just f-divergences.
(defn product-marginal-test
  "the joint PMF of independent variables is equal to the product of their marginal PMFs."
  [j])



;;TODO: combine into one tree comp that can figure out if it should call one branch function on each leave, or each branch function on all leaves.
(defn- tree-comp-each [root branch & leaves]
  (apply root (map branch leaves)))



;;TODO: seems very useful for clustering: http://en.wikipedia.org/wiki/Mahalanobis_distance and http://en.wikipedia.org/wiki/Partial_leverage
;;TODO: add http://en.wikipedia.org/wiki/Jaro-Winkler
;;TODO: add graphical approaches to similarity: http://en.wikipedia.org/wiki/SimRank
;;TODO: string similarity measures: http://en.wikipedia.org/wiki/String_metric

(defn minkowski-distance
  "
  http://en.wikipedia.org/wiki/Minkowski_distance
  http://en.wikipedia.org/wiki/Lp_space

  The Minkowski distance is a metric on Euclidean space which can be considered
  as a generalization of both the Euclidean distance and the Manhattan distance.

  Minkowski distance is typically used with p being 1 or 2. The latter is the
  Euclidean distance, while the former is sometimes known as the Manhattan distance.

  In the limiting case of p reaching infinity we obtain the Chebyshev distance.
  "
  [a b p]
  {:pre [(= (count a) (count b))]}
    (pow
      (reduce +
              (map
                #(pow
                   (scalar-abs
                     (pow (- %1 %2) p)))
                a b))
      (/ 1 p)))

(defn euclidean-distance
  "
  http://en.wikipedia.org/wiki/Euclidean_distance

  the Euclidean distance or Euclidean metric is the ordinary distance
  between two points that one would measure with a ruler, and is
  given by the Pythagorean formula. By using this formula as distance,
  Euclidean space (or even any inner product space) becomes a metric space.
  The associated norm is called the Euclidean norm.
  Older literature refers to the metric as Pythagorean metric.
  "
  [a b]
    (minkowski-distance a b 2))

(defn chebyshev-distance
  "In the limiting case of Lp reaching infinity we obtain the Chebyshev distance."
  [a b]
  {:pre [(= (count a) (count b))]}
    (reduce max
            (map
              #(scalar-abs (- %1 %2))
              a b)))

(defn manhattan-distance
  "
  http://en.wikipedia.org/wiki/Manhattan_distance

  usual metric of Euclidean geometry is replaced by a new metric in which
  the distance between two points is the sum of the (absolute) differences
  of their coordinates. The taxicab metric is also known as rectilinear distance,
  L1 distance or l1 norm (see Lp space), city block distance,
  Manhattan distance, or Manhattan length
  "
  [a b]
    (minkowski-distance a b 1))


;;TODO: factor out duplication between cosine similarity and tanimoto.
;;perhaps switch to matrix representation?

(defn cosine-similarity
  "
  http://en.wikipedia.org/wiki/Cosine_similarity
  http://www.appliedsoftwaredesign.com/cosineSimilarityCalculator.php

  The Cosine Similarity of two vectors a and b is the ratio: a dot b / ||a|| ||b||

  Let d1 = {2 4 3 1 6}
  Let d2 = {3 5 1 2 5}

  Cosine Similarity (d1, d2) =  dot(d1, d2) / ||d1|| ||d2||

  dot(d1, d2) = (2)*(3) + (4)*(5) + (3)*(1) + (1)*(2) + (6)*(5) = 61

  ||d1|| = sqrt((2)^2 + (4)^2 + (3)^2 + (1)^2 + (6)^2) = 8.12403840464

  ||d2|| = sqrt((3)^2 + (5)^2 + (1)^2 + (2)^2 + (5)^2) = 8

  Cosine Similarity (d1, d2) = 61 / (8.12403840464) * (8)
                             = 61 / 64.9923072371
                             = 0.938572618717
  "
  [a b]
  (let [counts (apply merge-with +
                      (map
                        (fn [[x y]]
                          {:dot (* x y)
                           :a (pow x 2)
                           :b (pow y 2)})
                        (map vector a b)))]
      (/ (:dot counts)
         (* (sqrt (:a counts))
            (sqrt (:b counts))))))

(defn tanimoto-coefficient
  "
  http://en.wikipedia.org/wiki/Jaccard_index

  The cosine similarity metric may be extended such that it yields the
  Jaccard coefficient in the case of binary attributes.
  This is the Tanimoto coefficient. "
  [a b]
    (let [counts (apply merge-with +
                        (map
                          (fn [[x y]]
                            {:dot (* x y)
                             :a (pow x 2)
                             :b (pow y 2)})
                          (map vector a b)))]
      (/ (:dot counts)
         (-
           (+ (:a counts)
           (:b counts))
           (:dot counts)))))

(defn jaccard-index
  "
  http://en.wikipedia.org/wiki/Jaccard_index

  The Jaccard index, also known as the Jaccard similarity coefficient
  (originally coined coefficient de communauté by Paul Jaccard), is a
  statistic used for comparing the similarity and diversity of sample sets.

  The Jaccard coefficient measures similarity between sample sets,
  and is defined as the size of the intersection divided by the
  size of the union of the sample sets.
  "
  [a b]
    (/ (count (intersection a b))
       (count (union a b))))

(defn jaccard-distance
  "
  http://en.wikipedia.org/wiki/Jaccard_index
  The Jaccard distance, which measures dissimilarity between sample sets,
  is complementary to the Jaccard coefficient and is obtained by subtracting
  the Jaccard coefficient from 1, or, equivalently, by dividing the difference
  of the sizes of the union and the intersection of two sets by the size of the union.
  "
  [a b]
    (- 1 (jaccard-index a b)))

;;TODO: mutlimethod for dice coefficient.
;;(defmulti dice-coefficient (fn [a b] (into [] (map class [a b]))))

(defn dice-coefficient
  "
  http://en.wikipedia.org/wiki/Dice%27s_coefficient
  Dice's coefficient (also known as the Dice coefficient)
  is a similarity measure related to the Jaccard index.
  "
  [a b]
  (let [an (count a)
        bn (count b)
        cn (count (intersection a b))]
    (/ (* 2 cn)
       (+ an bn))))

(defn n-grams
  "
  Returns a set of the unique n-grams in a string.
  this is using actual sets here, discards duplicate n-grams?
  "
  [n s]
  (into #{}
    (map
      #(apply str %)
      (partition n 1 s))))

(defn bigrams [s] (n-grams 2 s))

(defn dice-coefficient-str
  "
  http://en.wikipedia.org/wiki/Dice%27s_coefficient

  When taken as a string similarity measure, the coefficient
  may be calculated for two strings, x and y using bigrams.
  Here nt is the number of character bigrams found in both strings,
  nx is the number of bigrams in string x and
  ny is the number of bigrams in string y.
  For example, to calculate the similarity between:

    night
    nacht

  We would find the set of bigrams in each word:

    {ni,ig,gh,ht}
    {na,ac,ch,ht}

  Each set has four elements, and the intersection of these two sets has only one element: ht.

  Plugging this into the formula, we calculate, s = (2 · 1) / (4 + 4) = 0.25.
  "
  [a b]
  (dice-coefficient
    (bigrams a)
    (bigrams b)))

(defn- bool-to-binary [pred] (if pred 1 0))

(defn hamming-distance
  "
  http://en.wikipedia.org/wiki/Hamming_distance

  In information theory, the Hamming distance between two strings of equal
  length is the number of positions at which the corresponding symbols are different.
  Put another way, it measures the minimum number of
  substitutions required to change one string into the other,
  or the number of errors that transformed one string into the other.
  "
  [a b]
  (if (and (integer? a) (integer? b))
    (hamming-distance (str a) (str b))
    (let [_ (assert (= (count a) (count b)))]
      (apply
        tree-comp-each
        +
        #(bool-to-binary (not (apply = %)))
        (map vector a b)))))

;;TODO: not exactly sure if this is right.
(defn lee-distance
  "
  http://en.wikipedia.org/wiki/Lee_distance

  In coding theory, the Lee distance is a distance between
  two strings x1x2...xn and y1y2...yn of equal length n
  over the q-ary alphabet {0,1,…,q-1} of size q >= 2. It is metric.

  If q = 2 or q = 3 the Lee distance coincides with the Hamming distance.

  The metric space induced by the Lee distance is a discrete analog of the elliptic space.
  "

  [a b q]
  (if (and (integer? a) (integer? b))
    (lee-distance (str a) (str b) q)
    (let [_ (assert (= (count a) (count b)))]
      (apply
        tree-comp-each
        +
        (fn [x]
          (let [diff (scalar-abs (apply - (map int x)))]
            (min diff (- q diff))))
        (map vector a b)))))

(defn sorensen-index
  "
  http://en.wikipedia.org/wiki/S%C3%B8rensen_similarity_index#cite_note-4

  The Sørensen index, also known as Sørensen’s similarity coefficient,
  is a statistic used for comparing the similarity of two samples.
  where A and B are the species numbers in samples A and B, respectively,
  and C is the number of species shared by the two samples.

  The Sørensen index is identical to Dice's coefficient which is always in [0, 1] range.
  Sørensen index used as a distance measure, 1 − QS, is identical
  to Hellinger distance and Bray–Curtis dissimilarity.

  The Sørensen coefficient is mainly useful for ecological community data
  (e.g. Looman & Campbell, 1960[3]). Justification for its use is primarily
  empirical rather than theoretical
  (although it can be justified theoretically as the intersection of two fuzzy sets[4]).
  As compared to Euclidean distance, Sørensen distance retains sensitivity
  in more heterogeneous data sets and gives less weight to outliers

  This function assumes you pass in a and b as sets.

  The sorensen index extended to abundance instead of incidence of species is called the Czekanowski index.
  "
  [a b]
    (dice-coefficient a b))

;; Possible improvements to this algorithm include:

;;     * We can adapt the algorithm to use less space, O(m) instead of O(mn), since it only requires that the previous row and current row be stored at any one time.
;;     * We can store the number of insertions, deletions, and substitutions separately, or even the positions at which they occur, which is always j.
;;     * We can normalize the distance to the interval [0,1].
;;     * If we are only interested in the distance if it is smaller than a threshold k, then it suffices to compute a diagonal stripe of width 2k+1 in the matrix. In this way, the algorithm can be run in O(kl) time, where l is the length of the shortest string.[2]
;;     * We can give different penalty costs to insertion, deletion and substitution. We can also give penalty costs that depend on which characters are inserted, deleted or substituted.
;;     * By initializing the first row of the matrix with 0, the algorithm can be used for fuzzy string search of a string in a text [3]. This modification gives the end-position of matching substrings of the text. To determine the start-position of the matching substrings, the number of insertions and deletions can be stored separately and used to compute the start-position from the end-position.[4]
;;     * This algorithm parallelizes poorly, due to a large number of data dependencies. However, all the cost values can be computed in parallel, and the algorithm can be adapted to perform the minimum function in phases to eliminate dependencies.
;;     * By examining diagonals instead of rows, and by using lazy evaluation, we can find the Levenshtein distance in O(m (1 + d)) time (where d is the Levenshtein distance), which is much faster than the regular dynamic programming algorithm if the distance is small.[5]
(defn levenshtein-distance
  "
  http://en.wikipedia.org/wiki/Levenshtein_distance

  internal representation is a table d with m+1 rows and n+1 columns

  where m is the length of a and m is the length of b.

  In information theory and computer science, the Levenshtein distance
  is a metric for measuring the amount of difference between two sequences
  (i.e., the so called edit distance).
  The Levenshtein distance between two strings is given by the minimum number
  of operations needed to transform one string into the other,
  where an operation is an insertion, deletion, or substitution of a single character.

  For example, the Levenshtein distance between \"kitten\" and \"sitting\" is 3,
  since the following three edits change one into the other,
  and there is no way to do it with fewer than three edits:

   1. kitten → sitten (substitution of 's' for 'k')
   2. sitten → sittin (substitution of 'i' for 'e')
   3. sittin → sitting (insert 'g' at the end).

  The Levenshtein distance has several simple upper and lower bounds that are useful
  in applications which compute many of them and compare them. These include:

    * It is always at least the difference of the sizes of the two strings.
    * It is at most the length of the longer string.
    * It is zero if and only if the strings are identical.
    * If the strings are the same size, the Hamming distance is an upper bound on the Levenshtein distance.
  "
  [a b]
    (let [m (count a)
          n (count b)
          init (apply deep-merge-with (fn [a b] b)
                      (concat
                       ;;deletion
                       (for [i (range 0 (inc m))]
                         {i {0 i}})
                       ;;insertion
                       (for [j (range 0 (inc n))]
                         {0 {j j}})))
          table (reduce
                 (fn [d [i j]]
                   (deep-merge-with
                    (fn [a b] b)
                    d
                    {i {j (if (= (nth a (dec i))
                                 (nth b (dec j)))
                            ((d (dec i)) (dec j))
                            (min
                             (+ ((d (dec i))
                                 j) 1) ;;deletion
                             (+ ((d i)
                                 (dec j)) 1) ;;insertion
                             (+ ((d (dec i))
                                 (dec j)) 1))) ;;substitution
                        }}))
                 init
                 (for [j (range 1 (inc n))
                       i (range 1 (inc m))] [i j]))]

      ((table m) n)))


(defn damerau-levenshtein-distance
  [a b]
  (let [m (count a)
        n (count b)
        init (apply deep-merge-with (fn [a b] b)
                    (concat
                     ;;deletion
                     (for [i (range 0 (inc m))]
                       {i {0 i}})
                     ;;insertion
                     (for [j (range 0 (inc n))]
                       {0 {j j}})))
        table (reduce
               (fn [d [i j]]
                 (deep-merge-with
                  (fn [a b] b)
                  d
                  (let [cost (bool-to-binary (not (= (nth a (dec i))
                                          (nth b (dec j)))))
                        x
                          (min
                           (inc ((d (dec i)) j)) ;;deletion
                           (inc ((d i) (dec j))) ;;insertion
                           (+ ((d (dec i))
                               (dec j)) cost)) ;;substitution

                        val (if (and (> i 1)
                               (> j 1)
                               (= (nth a (dec i))
                                  (nth b (- j 2)))
                               (= (nth a (- i 2))
                                  (nth b (dec j))))
                        (min x (+ ((d (- i 2))
                                   (- j 2)) ;;transposition
                                  cost))
                        x)]
                    {i {j val}})))
               init
               (for [j (range 1 (inc n))
                     i (range 1 (inc m))] [i j]))]

    ((table m) n)))

(defn mahalanobis-distance
  "
  Returns the Mahalanobis distance between x, which is
   either a vector or matrix of row vectors, and the
   centroid of the observations in the matrix :y.

  Arguments:
    x -- either a vector or a matrix of row vectors

  Options:
    :y -- Defaults to x, must be a matrix of row vectors which will be used to calculate a centroid
    :W -- Defaults to (solve (covariance y)), if an identity matrix is provided, the mahalanobis-distance
          function will be equal to the Euclidean distance.
    :centroid -- Defaults to (map mean (trans y))


  References:
    http://en.wikipedia.org/wiki/Mahalanobis_distance


  Examples:

    (use '(incanter core stats charts))

    ;; generate some multivariate normal data with a single outlier.
    (def data (bind-rows
                (bind-columns
                  (sample-mvn 100
                              :sigma (matrix [[1 0.9]
                                              [0.9 1]])))
                [-1.75 1.75]))

    ;; view a scatter plot of the data
    (let [[x y] (trans data)]
      (doto (scatter-plot x y)
        (add-points [(mean x)] [(mean y)])
        (add-pointer -1.75 1.75 :text \"Outlier\")
        (add-pointer (mean x) (mean y) :text \"Centroid\")
        view))

    ;; calculate the distances of each point from the centroid.
    (def dists (map first (mahalanobis-distance data)))
    ;; view a bar-chart of the distances
    (view (bar-chart (range 102) dists))

    ;; Now contrast with the Euclidean distance.
    (def dists (map first (mahalanobis-distance data :W (matrix [[1 0] [0 1]]))))
    ;; view a bar-chart of the distances
    (view (bar-chart (range 102) dists))


    ;; another example
    (mahalanobis-distance [-1.75 1.75] :y data)
    (mahalanobis-distance [-1.75 1.75]
                      :y data
                      :W (matrix [[1 0]
                                  [0 1]]))
  "
  ([x & {:keys [y W centroid]}]
    (let [y (or y x)
          W (or W (solve (covariance y)))
          centroid (or centroid (map mean (trans y)))
          x-centroid (if (matrix? x)
                       (map #(minus (trans %) centroid) x)
                       (minus x centroid))
          dist-fn (fn [a-b W] (sqrt (mmult (trans a-b) W a-b)))]
      (if (matrix? x)
        (map #(dist-fn % W) x-centroid)
        (dist-fn x-centroid W)))))
