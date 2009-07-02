;;; stats.clj -- Statistics library for Clojure built on the CERN Colt Library

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



(ns incanter.stats 
  (:import (cern.colt.list.tdouble DoubleArrayList)
           (cern.jet.random.tdouble Gamma Beta Binomial ChiSquare DoubleUniform
                                    Exponential NegativeBinomial Normal Poisson
                                    StudentT)
           (cern.jet.random.tdouble.engine DoubleMersenneTwister)
           (cern.jet.stat.tdouble DoubleDescriptive
                                  Probability))
  (:use [incanter.core :only (abs plus minus div mult mmult to-list bind-columns 
                              gamma pow sqrt diag trans regularized-beta ncol
                              nrow identity-matrix decomp-cholesky decomp-svd
                              matrix length sum sum-of-squares sel matrix?
                              cumulative-sum solve vectorize bind-rows)]))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; CONTINOUS DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; F DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 


  
(defn pdf-f
" Returns the F pdf of the given value, x. It will return a sequence 
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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          df1 (if (:df1 opts) (:df1 opts) 0)
          df2 (if (:df2 opts) (:df2 opts) 1)
          pdf-fx (fn [x]
                   (* (/ (gamma (/ (+ df1 df2) 2)) 
                         (* (gamma (/ df1 2)) (gamma (/ df2 2))))
                       (pow (/ df1 df2) (/ df1 2))
                       (pow x (- (/ df1 2) 1))
                       (pow (+ 1 (* (/ df1 df2) x))
                           (- 0 (/ (+ df1 df2) 2)))))
         ]
      (if (coll? x)
        (map pdf-fx x)
        (pdf-fx x)))))



(defn cdf-f 
" Returns the F-distribution cdf of the given value, x. It will return a sequence 
  of values, if x is a sequence. This is equivalent to R's pf function. 

  Options: 
    :df1 (default 1) 
    :df2 (default 1)

  See also: 
      pdf-f and quantile-f

  References: 
      http://en.wikipedia.org/wiki/F_distribution
      http://mathworld.wolfram.com/F-Distribution.html
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example: 
      (cdf-f 1.0 :df1 5 :df2 2)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          lower-tail? (if (false? (:lower-tail opts)) false true)
          df1 (if (:df1 opts) (:df1 opts) 1)
          df2 (if (:df2 opts) (:df2 opts) 1)
          cdf-fx (if lower-tail?
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
" Returns the Normal pdf of the given value, x. It will return a sequence 
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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          mean (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)
          dist (Normal. mean sd (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))



(defn cdf-normal
" Returns the Normal cdf of the given value, x. It will return a sequence 
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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          mean (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)
          dist (Normal. mean sd (DoubleMersenneTwister.))] 
      (if (coll? x)
        (map #(.cdf dist %) x)
        (.cdf dist x)))))


(defn quantile-normal 
" Returns the inverse of the Normal CDF for the given probability. 
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
  ([probability & options]
    (let [opts (if options (apply assoc {} options) nil)
          mean (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)
          x (if (coll? probability) 
              (map #(Probability/normalInverse %) probability)
              (Probability/normalInverse probability))]
      (plus mean (mult sd x)))))



(defn sample-normal
" Returns a sample of the given size from a Normal distribution
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
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil)
          mean (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)]
      (if (= size 1)
        (Normal/staticNextDouble mean sd)
        (for [_ (range size)] (Normal/staticNextDouble mean sd))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MULTIVARIATE NORMAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn sample-multivariate-normal 
" Returns a sample of the given size from a Multivariate Normal 
  distribution. This is equivalent to R's mvtnorm::rmvnorm function. 

  Arguments:
    size -- the size of the sample to return

  Options: 
    :mean (default (repeat (ncol sigma) 0)) 
    :sigma (default (identity-matrix (count mean)))


  Examples:

    (use '(incanter core stats charts))
    (def mvn-samp (sample-multivariate-normal 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))
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
  ([size & options]
   (let [opts (if options (apply assoc {} options) nil)
         mean (if (:mean opts) 
                (:mean opts) 
                (if (:sigma opts) 
                  (repeat (ncol (:sigma opts)) 0)
                  [0]))
         sigma (if (:sigma opts) 
                 (:sigma opts) 
                 (identity-matrix (count mean)))
         p (count mean)
         chol (decomp-cholesky sigma)
         norm-samp (mmult (matrix (sample-normal (* size p)) p) chol)
        ]
     (matrix (map #(plus % (trans mean)) norm-samp)))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIFORM DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn pdf-uniform 
" Returns the Uniform pdf of the given value of x. It will return a sequence 
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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          min (double (if (:min opts) (:min opts) 0.0))
          max (double (if (:max opts) (:max opts) 1.0))
          dist (DoubleUniform. min max (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))


(defn cdf-uniform 
" Returns the Uniform cdf of the given value of x. It will return a sequence 
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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          min (double (if (:min opts) (:min opts) 0.0))
          max (double (if (:max opts) (:max opts) 1.0))
          dist (DoubleUniform. min max (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.cdf dist %) x)
        (.cdf dist x)))))


(defn sample-uniform 
" Returns a sample of the given size from a Uniform distribution.
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
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil)
          min-val (double (if (:min opts) (:min opts) 0.0))
          max-val (double (if (:max opts) (:max opts) 1.0))
          ints? (if (true? (:integers opts)) true false)
          dist (DoubleUniform. min-val max-val (DoubleMersenneTwister.))]
      (if (= size 1)
        (if ints? 
          (DoubleUniform/staticNextIntFromTo min-val max-val)
          (DoubleUniform/staticNextDoubleFromTo min-val max-val))
        (if ints? 
          (for [_ (range size)] (DoubleUniform/staticNextIntFromTo min-val max-val))
          (for [_ (range size)] (DoubleUniform/staticNextDoubleFromTo min-val max-val)))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; BETA DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(defn pdf-beta
" Returns the Beta pdf of the given value of x. It will return a sequence 
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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          alpha (if (:alpha opts) (:alpha opts) 1)
          beta (if (:beta opts) (:beta opts) 1)
          dist (Beta. alpha beta (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))


(defn cdf-beta
" Returns the Beta cdf of the given value of x. It will return a sequence 
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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          alpha (if (:alpha opts) (:alpha opts) 1)
          beta (if (:beta opts) (:beta opts) 1)
          lower-tail? (if (false? (:lower-tail opts)) false true)
          cdf-fx (if lower-tail?
                  (fn [x1] (Probability/beta alpha beta x1))
                  (fn [x1] (- 1 (Probability/betaComplemented alpha beta x1))))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))





(defn sample-beta
" Returns a sample of the given size from a Beta distribution.
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
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil)
          alpha (if (:alpha opts) (:alpha opts) 1)
          beta (if (:beta opts) (:beta opts) 1)]
      (if (= size 1)
        (Beta/staticNextDouble alpha beta)
        (for [_ (range size)] (Beta/staticNextDouble alpha beta))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; GAMMA DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(defn pdf-gamma
" Returns the Gamma pdf for the given value of x. It will return a sequence 
  of values, if x is a sequence. This is equivalent to R's dgamma function.

  Options: 
    :shape (default 1) 
    :rate (default 1)

  See also: 
      cdf-gamma and sample-gamma

  References: 
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html
      http://en.wikipedia.org/wiki/Gamma_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-gamma 10 :shape 1 :rate 2)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          shape (if (number? (:shape opts)) (:shape opts) 1)
          rate (if (number? (:rate opts)) (:rate opts) 1)
          dist (Gamma. shape rate (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))



(defn cdf-gamma
" Returns the Gamma cdf for the given value of x. It will return a sequence 
  of values, if x is a sequence. This is equivalent to R's pgamma function.

  Options: 
    :shape (default 1) 
    :rate (default 1)
    :lower-tail (default true)

  See also: 
      pdf-gamma and sample-gamma

  References: 
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html
      http://en.wikipedia.org/wiki/Gamma_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example: 
      (cdf-gamma 10 :shape 1 :rate 2)
      (cdf-gamma 3 :shape 1 :lower-tail false)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          shape (if (number? (:shape opts)) (:shape opts) 1)
          rate (if (number? (:rate opts)) (:rate opts) 1)
          lower-tail? (if (false? (:lower-tail opts)) false true) 
          cdf-fx (if lower-tail?
                  (fn [x1] (Probability/gamma rate shape x1))
                  (fn [x1] (Probability/gammaComplemented rate shape x1)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-gamma
" Returns a sample of the given size from a Gamma distribution.
  This is equivalent to R's rgamma function.

  Options: 
    :shape (default 1) 
    :rate (default 1)

  See also: 
      pdf-gamma, cdf-gamma, and quantile-gamma

  References: 
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html
      http://en.wikipedia.org/wiki/Gamma_distribution

  Example: 
      (sample-gamma 1000 :shape 1 :rate 2)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          shape (if (number? (:shape opts)) (:shape opts) 1)
          rate (if (number? (:rate opts)) (:rate opts) 1)]
      (if (= size 1)
        (Gamma/staticNextDouble shape rate)
        (for [_ (range size)] (Gamma/staticNextDouble shape rate))))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; CHI SQUARE DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(defn pdf-chisq
" Returns the Chi Square pdf of the given value of x.  It will return a sequence 
  of values, if x is a sequence. Same as R's dchisq function.

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)
          dist (ChiSquare. df (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))



(defn cdf-chisq
" Returns the Chi Square cdf of the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's pchisq function.

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)
          lower-tail? (if (false? (:lower-tail opts)) false true)
          cdf-fx (if lower-tail?
                  (fn [x1] (Probability/chiSquare df x1))
                  (fn [x1] (Probability/chiSquareComplemented df x1)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-chisq
" Returns a sample of the given size from a Chi Square distribution
  Same as R's rchisq function.

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
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)]
      (if (= size 1)
        (ChiSquare/staticNextDouble df)
        (for [_ (range size)] (ChiSquare/staticNextDouble df))))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; STUDENT'S T DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(defn pdf-t
" Returns the Student's t pdf for the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's dt function.

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)
          dist (StudentT. df (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))


(defn cdf-t
" Returns the Student's t cdf for the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's pt function.

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)
          lower-tail? (if (false? (:lower-tail opts)) false true)
          cdf-fx (if lower-tail?
                  (fn [x1] (Probability/studentT df x1))
                  (fn [x1] (- 1 (Probability/studentT df x1))))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))


(defn quantile-t 
" Returns the inverse of the Student's t CDF for the given probability
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
  ([probability & options]
    (let [opts (if options (apply assoc {} options) nil)
          df (if (:df opts) (:df opts) 1)
          to-alpha (fn [prob] ;; need to convert the probability to an alpha value
                     (if (< prob 1/2) 
                      (* 2 prob)
                      (* 2 (- 1 prob))))
          sign-fx (fn [x1 prob] (if (< prob 1/2) (* -1 x1) x1))
          x (if (coll? probability) 
              (map #(sign-fx (Probability/studentTInverse (to-alpha %) df) %) probability)
              (sign-fx (Probability/studentTInverse (to-alpha probability) df) probability))]
        x)))



(defn sample-t
" Returns a sample of the given size from a Student's t distribution.
  Same as R's rt function.

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
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)]
      (if (= size 1)
        (StudentT/staticNextDouble df)
        (for [_ (range size)] (StudentT/staticNextDouble df))))))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; EXPONENTIAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(defn pdf-exp
" Returns the Exponential pdf of the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's dexp

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          rate (if (number? (:rate opts)) (:rate opts) 1)
          dist (Exponential. rate (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))


(defn cdf-exp
" Returns the Exponential cdf of the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's pexp

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          rate (if (number? (:rate opts)) (:rate opts) 1)
          dist (Exponential. rate (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.cdf dist %) x)
        (.cdf dist x)))))


(defn sample-exp
" Returns a sample of the given size from a Exponential distribution.
  Same as R's rexp

  Options: 
    :rate (default 1) 

  See also: 
      pdf-exp, and cdf-exp

  References: 
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html
      http://en.wikipedia.org/wiki/Exponential_distribution

  Example: 
      (sample-exp 1000 :rate 1/2)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          rate (if (number? (:rate opts)) (:rate opts) 1)]
      (if (= size 1)
        (Exponential/staticNextDouble rate)
        (for [_ (range size)] (Exponential/staticNextDouble rate))))))




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
    http://en.wikipedia.org/wiki/Wishart_distribution#

"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil)
          p (if (:p opts) (:p opts) 2)
          df (if (:df opts) (:df opts) p)
          scale (if (:scale opts) (:scale opts) (identity-matrix p))
          diagonal (for [i (range 1 (inc p))] 
                     (pow (sample-chisq 1 :df (inc (- df i))) 1/2))
          mat (diag diagonal)
          indices (for [i (range p) j (range p) :when (< j i)] [i j])
          _ (doseq [indx indices] (.set mat (first indx) (second indx) (sample-normal 1)))
          chol (decomp-cholesky scale)
          x (mmult chol mat (trans mat) (trans chol))]
        x)))



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
  ([size alpha]
    (let [W (trans (for [a alpha] (sample-gamma size :shape a :rate 1)))
          T (map sum W)]
      (matrix (map #(div %1 %2) W T)))))


          



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; DISCRETE DISTRIBUTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; BINOMIAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(defn pdf-binomial
" Returns the Bionomial pdf of the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's dbinom

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          n (if (number? (:size opts)) (:size opts) 1)
          p (if (number? (:prob opts)) (:prob opts) 1/2)
          dist (Binomial. n p (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))



(defn cdf-binomial
" Returns the Bionomial cdf of the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's pbinom

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          n (if (number? (:size opts)) (:size opts) 1)
          p (if (number? (:prob opts)) (:prob opts) 1/2)
          lower-tail? (if (false? (:lower-tail opts)) false true)
          cdf-fx (if lower-tail?
                  (fn [x1] (Probability/binomial x1 n p))
                  (fn [x1] (Probability/binomialComplemented x1 n p)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-binomial
" Returns a sample of the given size from a Binomial distribution.
  Same as R's rbinom

  Options: 
    :size (default 1) 
    :prob (default 1/2)

  See also: 
      cdf-binomial and sample-binomial

  References: 
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html
      http://en.wikipedia.org/wiki/Binomial_distribution

  Example: 
      (sample-binomial 1000 :prob 1/4 :size 20)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          n (if (number? (:size opts)) (:size opts) 1)
          p (if (number? (:prob opts)) (:prob opts) 1/2)]
      (if (= size 1)
        (Binomial/staticNextInt n p)
        (for [_ (range size)] (Binomial/staticNextInt n p))))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; POISSON DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(defn pdf-poisson
" Returns the Poisson pdf of the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's dpois

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          lambda (if (number? (:lambda opts)) (:lambda opts) 1)
          dist (Poisson. lambda (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))



(defn cdf-poisson
" Returns the Poisson cdf of the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's ppois

  Options: 
    :lambda (default 1) 
    :lower-tail (default true)

  See also: 
      cdf-poisson and sample-poisson

  References: 
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html
      http://en.wikipedia.org/wiki/Poisson_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example: 
      (cdf-poisson 5 :lambda 10)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          lambda (if (number? (:lambda opts)) (:lambda opts) 1)
          lower-tail? (if (false? (:lower-tail opts)) false true)
          cdf-fx (if lower-tail?
                  (fn [x1] (Probability/poisson x1 lambda))
                  (fn [x1] (Probability/poissonComplemented x1 lambda)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-poisson
" Returns a sample of the given size from a Poisson distribution.
  Same as R's rpois

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
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          lambda (if (number? (:lambda opts)) (:lambda opts) 1)]
     (if (= size 1)
        (Poisson/staticNextInt lambda)
        (for [_ (range size)] (Poisson/staticNextInt lambda))))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; NEGATIVE BINOMIAL DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(defn pdf-neg-binomial
" Returns the Negative Binomial pdf of the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's dnbinom

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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          size (if (number? (:size opts)) (:size opts) 10)
          prob (if (number? (:prob opts)) (:prob opts) 1/2)
          dist (NegativeBinomial. size prob (DoubleMersenneTwister.))]
      (if (coll? x)
        (map #(.pdf dist %) x)
        (.pdf dist x)))))




(defn cdf-neg-binomial
" Returns the Negative Binomial cdf of the given value of x. It will return a sequence 
  of values, if x is a sequence. Same as R's dnbinom

  Options: 
    :size (default 10) 
    :prob (default 1/2)

  See also: 
      cdf-neg-binomial and sample-neg-binomial

  References: 
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html
      http://en.wikipedia.org/wiki/Negative_binomial_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example: 
      (cdf-neg-binomial 10 :prob 1/2 :size 20)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          size (if (number? (:size opts)) (:size opts) 10)
          prob (if (number? (:prob opts)) (:prob opts) 1/2)
          lower-tail? (if (false? (:lower-tail opts)) false true)
          cdf-fx (if lower-tail?
                  (fn [x1] (Probability/negativeBinomial x1 size prob))
                  (fn [x1] (Probability/negativeBinomialComplemented x1 size prob)))]
      (if (coll? x)
        (map cdf-fx x)
        (cdf-fx x)))))



(defn sample-neg-binomial
" Returns a sample of the given size from a Negative Binomial distribution.
  Same as R's rnbinom

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
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          size (if (number? (:size opts)) (:size opts) 10)
          prob (if (number? (:prob opts)) (:prob opts) 1/2)]
     (if (= size 1)
        (NegativeBinomial/staticNextInt size prob)
        (for [_ (range size)] (NegativeBinomial/staticNextInt size prob))))))


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
  ([x] (DoubleDescriptive/sampleVariance (length x) (sum x) (sum-of-squares x))))



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
    (let [
          xx (to-list x)
          yy (to-list y)
        ]
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
    ;(DoubleDescriptive/sampleStandardDeviation (length x) (variance x))))
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
    (let [
          xx (to-list x)
          yy (to-list y)
        ]
      (DoubleDescriptive/correlation 
        (DoubleArrayList. (double-array xx)) (sd x)
        (DoubleArrayList. (double-array yy)) (sd y))))
  ([mat]
   (div (covariance mat) 
        (sqrt (mmult (diag (covariance mat)) (trans (diag (covariance mat))))))))



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
      (DoubleDescriptive/median (DoubleArrayList. (double-array xx))))))

 

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
  ([x] (DoubleDescriptive/kurtosis (DoubleArrayList. (double-array x)) (mean x) (variance x))))



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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          _x (if (matrix? x) (to-list x) x)
          data (DoubleArrayList. (double-array (sort _x)))
          probs (cond 
                  (number? (:probs opts))
                    (:probs opts)
                  (coll? (:probs opts))
                    (DoubleArrayList. (double-array (:probs opts)))
                  :default
                    (DoubleArrayList. (double-array [0.0 0.25 0.5 0.75 1.0])))]
        (if (number? probs)
          (DoubleDescriptive/quantile data probs)
          (seq (.elements (DoubleDescriptive/quantiles data probs)))))))



(defn sample 
" Returns a sample of the given size from the given collection. If replacement 
  is set to false it returns a set, otherwise it returns a list.

  Arguments:
    x -- collection to be sampled from

  Options:
    :size -- (default (count x) sample size
    :replacement (default true) -- sample with replacement


  Examples:
    (sample (range 10)) ; permutation of numbers zero through ten
    (sample [:red :green :blue] :size 10) ; choose 10 items that are either :red, :green, or :blue.
    (sample (seq \"abcdefghijklmnopqrstuvwxyz\")  :size 4 :replacement false) ; choose 4 random letters.

"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          size (if (:size opts) (:size opts) (count x))
          replacement? (if (false? (:replacement opts)) false true)
          max-idx (dec (count x))]
      (if replacement?
        (map #(nth x %) (sample-uniform size :min 0 :max max-idx :integers true))
        (if (> size (count x))
          (throw (Exception. "'size' can't be larger than (count x) without replacement!"))
          (map #(nth x %)
               (loop [samp-indices [] indices-set #{}]
                 (if (= (count samp-indices) size)
                   samp-indices
                   (let [i (sample-uniform 1 :min 0 :max max-idx :integers true)]
                     (if (contains? indices-set i)
                       (recur samp-indices indices-set)
                       (recur (conj samp-indices i) (conj indices-set i))))))))))))

            ;(recur (conj samp-indices (nth x (sample-uniform 1 :min 0 :max max-idx :integers true)))))))))))


(defn bootstrap
" Returns a bootstrap sample of the given statistic on the given data


  Examples:
    (use '(incanter core stats datasets charts))
    (def x (sel (get-dataset :flow-meter) :cols 1))
    (view (histogram x :density true))
    (median x)

    (def boot-median (bootstrap x median))
    (mean boot-median)
    (median boot-median)
    (sd boot-median)
    (count boot-median)
    (view (histogram boot-median :density true))



"
  ([data statistic & options]
    (let [opts (if options (apply assoc {} options) nil) 
          B1 100
          B2 25
          max-iter 10
          D 0.01
          n (if (:n opts) (:n opts) (count data))
          replacement (if (false? (:replacement opts)) false true)]
      (loop [stats (for [_ (range B1)] (statistic (sample data :size n :replacement replacement)))
             k 0]
        (let [stats2 (concat stats (for [_ (range B2)] (statistic (sample data :size n :replacement replacement))))
              se1 (sd stats)
              se2 (sd stats2)]
          (if (or (= k max-iter) (< (* (- 1 D) se1) se2 (* (+ 1 D) se1)))
            stats2
            (recur stats2 (inc k))))))))


        




(defn cumulative-mean 
  " Returns a sequence of cumulative means for the given collection. For instance 
    The first value equals the first value of the argument, the second value is
    the mean of the first two arguments, the third is the mean of the first three
    arguments, etc.

    Examples:
      (cumulative-mean (sample-normal 100))
  "
  ([coll] (map / (cumulative-sum coll) (range 1 (inc (count coll))))))



(defn sweep
" Return an array obtained from an input array by sweeping out a
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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          stat-fn (if (:stat opts) (:stat opts) mean)
          fun (if (:fun opts) (:fun opts) minus)
          stat (stat-fn x)]
      (fun x stat))))

    


(defn permute
" If provided a single argument, returns a permuted version of the 
  given collection. (perm x) is the same as (sample x).

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
" If provided a two arguments (n x), it returns a list of n permutations
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
    (def groups (group-by data 1 :cols 0))
    
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
  ([n x]
    (loop [samp '() i 0]
      (if (= i n)
          samp
          (recur 
            (conj samp (sample x)) (inc i)))))

  ([n x y]
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
  ([y x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          intercept? (if (false? (:intercept opts)) false true)
          _x (if intercept? (bind-columns (replicate (nrow x) 1) x) x)
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
          r-square (/ ssr sst)
          n (nrow y)
          p (ncol _x)
          p-1 (if intercept? (- p 1) p)
          adj-r-square (- 1 (* (- 1 r-square) (/ (- n 1) (- n p 1))))
          mse (/ sse (- n p))
          msr (/ ssr p-1)
          f-stat (/ msr mse)
          df1 p-1
          df2 (- n p)
          f-prob (cdf-f f-stat :df1 df1 :df2 df2 :lower-tail false)
          coef-var (mult mse xtxi)
          std-errors (sqrt (diag coef-var))
          t-tests (div coefs std-errors)
          t-probs (mult 2 (cdf-t (abs t-tests) :df df2 :lower-tail false))
          t-95 (mult (quantile-t 0.975 :df df2) std-errors)
          coefs-ci (if (number? std-errors)
                       [(plus coefs t-95) 
                        (minus coefs t-95)]
                       (partition 2 
                         (interleave 
                           (minus coefs t-95) 
                           (plus coefs t-95))))
         ]
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
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          y (if (:y opts) (:y opts) nil)   
          one-sample? (nil? y)
          mu (if (:mu opts) (:mu opts) 
               (if y (mean y) 0))
          paired? (if (true? (:paired opts)) true false)
          var-equal? (if (true? (:var-equal opts)) true false)
          conf-level (if (:conf-level opts) (:conf-level opts) 0.95)
          alternative (if (:alternative opts) (:alternative opts) :two-sided)
          x-mean (mean x)
          x-var (variance x)
          n1 (count x)
          y-mean (if one-sample? nil (mean y))
          y-var (if one-sample? nil (variance y))
          n2 (if one-sample? nil (count y))
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
          one-sided-p (cdf-t t-stat :df df :lower-tail lower-tail?)
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



(defn tabulate
" Cross-tabulates the values of the given numeric matrix.

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
    (let [opts (if options (apply assoc {} options) nil)
          _x (if (matrix? x) x (matrix x))
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
    :table -- a contigency table. If one dimensional, the test is a goodness-of-fit
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
    ;; turn the count data for males into a contigency table
    (def male (matrix (sel (get-dataset :hair-eye-color) :cols 3 :rows (range 16)) 4))
    (chisq-test :table male) ;; X-sq = 41.280, df = 9, p-value = 4.44E-6
    ;; turn the count data for females into a contigency table
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
  ([& options]
    (let [opts (if options (apply assoc {} options) nil)
          correct (if (false? (:correct opts)) false true)
          x (when (:x opts) (:x opts))
          y (when (:y opts) (:y opts))
          table? (if (:table opts) true false)
          xtab (when (or x y)
                 (if y 
                   (tabulate (bind-columns x y)) 
                   (tabulate x)))
          table (cond 
                  table? 
                   (:table opts) 
                  (and x y)
                    (:table xtab))
          two-samp? (if (or (and x y) 
                            (and table? 
                                 (and (> (nrow table) 1) (> (ncol table) 1)))) 
                      true false)
          r-levels (if table?
                     (range (nrow table)) 
                     (first (:levels xtab)))
          c-levels (if table? 
                     (range (ncol table)) 
                     (second (:levels xtab)))
          r-margins (if table?
                      (if two-samp?
                        (apply hash-map (interleave r-levels (map sum (trans table))))
                        (if (> (nrow table) 1) 
                          (to-list table) 
                          (throw (Exception. "One dimensional tables must have only a single column"))))
                      (second (:margins xtab)))
          c-margins (if table? 
                      (if two-samp?
                        (apply hash-map (interleave c-levels (map sum table)))
                        0)
                      (first (:margins xtab)))
                  
          counts (if two-samp? (vectorize table) table)
          N (if table? 
              (sum counts) 
              (:N xtab))
          n (when (not two-samp?) (count r-levels))
          df (if two-samp? (* (dec (nrow table)) (dec (ncol table))) (dec n))
          probs (when (not two-samp?)
                  (cond 
                    (:probs opts) 
                      (:probs opts) 
                    (:freq opts)
                      (div (:freq opts) (sum (:freq opts)))
                    :else
                      (repeat n (/ n))))
          E (if two-samp?
              (for [r r-levels c c-levels]
                (/ (* (c-margins c) (r-margins r)) N))
              (mult N probs))
          X-sq (if (and correct (and (= (count r-levels) 2) (= (count c-levels) 2)))
                 (reduce + (map (fn [o e] (/ (pow (- (abs (- o e)) 0.5) 2) e)) counts E))
                 (reduce + (map (fn [o e] (/ (pow (- o e) 2) e)) counts E)))
         ]
      {:X-sq X-sq
       :df df
       :two-samp? two-samp?
       :p-value (cdf-chisq X-sq :df df :lower-tail false)
       :probs probs
       :N N
       :table table
       :col-levels c-levels
       :row-levels r-levels
       :col-margins c-margins
       :row-margins r-margins
       :E E})))




(defn principal-components 
"
  Performs a principal components analysis on the given data matrix.
  Equivalent to R's prcomp function.
  
  Returns:
    A map with the following fields:
    :std-dev -- the standard deviations of the principal compoenents
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
  ([pred coll] 
    (for [el coll] (if (pred el) 1 0))))




(defn detabulate
" Take a contingency table of counts and returns a matrix of observations.

  Examples:

    (use '(incanter core stats datasets))

    (def by-gender (group-by (get-dataset :hair-eye-color) 2))
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
  ([& options]
    (let [opts (if options (apply assoc {} options) nil)
          table (when (:table opts) (:table opts))
          row-labels (when table (if (:row-labels opts) (:row-labels opts) (range (nrow table))))
          col-labels (when table (if (:col-labels opts) (:col-labels opts) (range (ncol table))))
          data (apply bind-rows 
                      (apply concat
                             (for [r row-labels c col-labels]
                                  (repeat (sel table :rows r :cols c) [r c]))))]
       data)))




