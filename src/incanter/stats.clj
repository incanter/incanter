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
  (:import (cern.colt.list DoubleArrayList)
           (cern.jet.random Gamma)
           (cern.jet.random.engine MersenneTwister)
           (cern.jet.stat Descriptive))
  (:use (incanter core)))





(defn gamma 
"
  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Gamma.html
"
  ([x]  (cern.jet.stat.Gamma/gamma x)))


(defn beta 
"
  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Gamma.html
"
  ([a b]  (cern.jet.stat.Gamma/beta a b)))


(defn incomplete-beta 
"
  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Gamma.html
"

  ([x a b]  (* (cern.jet.stat.Gamma/incompleteBeta a b x) (cern.jet.stat.Gamma/beta a b))))



(defn regularized-beta 
"
  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Gamma.html
    http://en.wikipedia.org/wiki/Regularized_incomplete_beta_function
    http://mathworld.wolfram.com/RegularizedBetaFunction.html
"
  ([x a b] 
    (cern.jet.stat.Gamma/incompleteBeta a b x)))


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
                   (fn [x1] (regularized-beta (/ (* df1 x1) (+ df2 (* df1 x1)))
                                     (/ df1 2)
                                     (/ df2 2)))
                   (fn [x1] (- 1 (regularized-beta (/ (* df1 x1) (+ df2 (* df1 x1)))
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Normal.html
      http://en.wikipedia.org/wiki/Normal_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-normal 1.96 :mean -2 :sd (sqrt 0.5))
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          mean (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)
          dist (cern.jet.random.Normal. mean sd (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Normal.html
      http://en.wikipedia.org/wiki/Normal_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example: 
      (cdf-normal 1.96 :mean -2 :sd (sqrt 0.5))
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          mean (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)
          dist (cern.jet.random.Normal. mean sd (cern.jet.random.engine.MersenneTwister.))] 
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Probability.html
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
              (map #(cern.jet.stat.Probability/normalInverse %) probability)
              (cern.jet.stat.Probability/normalInverse probability))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Normal.html
      http://en.wikipedia.org/wiki/Normal_distribution

  Example: 
      (sample-normal 1000 :mean -2 :sd (sqrt 0.5))
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil)
          mean (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)]
      (if (= size 1)
        (cern.jet.random.Normal/staticNextDouble mean sd)
        (for [_ (range size)] (cern.jet.random.Normal/staticNextDouble mean sd))))))


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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Uniform.html
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
          dist (cern.jet.random.Uniform. min max (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Uniform.html
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
          dist (cern.jet.random.Uniform. min max (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Uniform.html
      http://en.wikipedia.org/wiki/Uniform_distribution

  Example: 
      (sample-uniform 1000)
      (sample-uniform 1000 :min 1 :max 10)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil)
          min (double (if (:min opts) (:min opts) 0.0))
          max (double (if (:max opts) (:max opts) 1.0))
          ints? (if (true? (:integers opts)) true false)
          dist (cern.jet.random.Uniform. min max (cern.jet.random.engine.MersenneTwister.))]
      (if (= size 1)
        (if ints? 
          (cern.jet.random.Uniform/staticNextIntFromTo min max)
          (cern.jet.random.Uniform/staticNextDoubleFromTo min max))
        (if ints? 
          (for [_ (range size)] (cern.jet.random.Uniform/staticNextIntFromTo min max))
          (for [_ (range size)] (cern.jet.random.Uniform/staticNextDoubleFromTo min max)))))))


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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Beta.html
      http://en.wikipedia.org/wiki/Beta_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-beta 0.5 :alpha 1 :beta 2)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          alpha (if (:alpha opts) (:alpha opts) 1)
          beta (if (:beta opts) (:beta opts) 1)
          dist (cern.jet.random.Beta. alpha beta (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Beta.html
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
                  (fn [x1] (cern.jet.stat.Probability/beta alpha beta x1))
                  (fn [x1] (- 1 (cern.jet.stat.Probability/betaComplemented alpha beta x1))))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Beta.html
      http://en.wikipedia.org/wiki/Beta_distribution

  Example: 
      (sample-beta 1000 :alpha 1 :beta 2)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil)
          alpha (if (:alpha opts) (:alpha opts) 1)
          beta (if (:beta opts) (:beta opts) 1)]
      (if (= size 1)
        (cern.jet.random.Beta/staticNextDouble alpha beta)
        (for [_ (range size)] (cern.jet.random.Beta/staticNextDouble alpha beta))))))


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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Gamma.html
      http://en.wikipedia.org/wiki/Gamma_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-gamma 10 :shape 1 :rate 2)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          shape (if (number? (:shape opts)) (:shape opts) 1)
          rate (if (number? (:rate opts)) (:rate opts) 1)
          dist (cern.jet.random.Gamma. shape rate (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Gamma.html
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
          lower-tail? (if (false (:lower-tail opts)) false true) 
          cdf-fx (if lower-tail?
                  (fn [x1] (cern.jet.stat.Probability/gamma rate shape x1))
                  (fn [x1] (cern.jet.stat.Probability/gammaComplemented rate shape x1)))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Gamma.html
      http://en.wikipedia.org/wiki/Gamma_distribution

  Example: 
      (sample-gamma 1000 :shape 1 :rate 2)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          shape (if (number? (:shape opts)) (:shape opts) 1)
          rate (if (number? (:rate opts)) (:rate opts) 1)]
      (if (= size 1)
        (cern.jet.random.Gamma/staticNextDouble shape rate)
        (for [_ (range size)] (cern.jet.random.Gamma/staticNextDouble shape rate))))))




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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/ChiSquare.html
      http://en.wikipedia.org/wiki/Chi_square_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-chisq 5.0 :df 2)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)
          dist (cern.jet.random.ChiSquare. df (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/ChiSquare.html
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
                  (fn [x1] (cern.jet.stat.Probability/chiSquare df x1))
                  (fn [x1] (cern.jet.stat.Probability/chiSquareComplemented df x1)))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/ChiSquare.html
      http://en.wikipedia.org/wiki/Chi_square_distribution

  Example: 
      (sample-chisq 1000 :df 2)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)]
      (if (= size 1)
        (cern.jet.random.ChiSquare/staticNextDouble df)
        (for [_ (range size)] (cern.jet.random.ChiSquare/staticNextDouble df))))))



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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/StudentT.html
      http://en.wikipedia.org/wiki/Student-t_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-t 1.2 :df 10)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)
          dist (cern.jet.random.StudentT. df (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/StudentT.html
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
                  (fn [x1] (cern.jet.stat.Probability/studentT df x1))
                  (fn [x1] (- 1 (cern.jet.stat.Probability/studentT df x1))))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Probability.html
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
              (map #(sign-fx (cern.jet.stat.Probability/studentTInverse (to-alpha %) df) %) probability)
              (sign-fx (cern.jet.stat.Probability/studentTInverse (to-alpha probability) df) probability))]
        x)))



(defn sample-t
" Returns a sample of the given size from a Student's t distribution.
  Same as R's rt function.

  Options: 
    :df (default 1) 

  See also: 
      pdf-t, cdf-t, and quantile-t

  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/StudentT.html
      http://en.wikipedia.org/wiki/Student-t_distribution

  Example: 
      (cdf-t 1000 :df 10)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)]
      (if (= size 1)
        (cern.jet.random.StudentT/staticNextDouble df)
        (for [_ (range size)] (cern.jet.random.StudentT/staticNextDouble df))))))





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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Exponential.html
      http://en.wikipedia.org/wiki/Exponential_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-exp 2.0 :rate 1/2)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          rate (if (number? (:rate opts)) (:rate opts) 1)
          dist (cern.jet.random.Exponential. rate (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Exponential.html
      http://en.wikipedia.org/wiki/Exponential_distribution
      http://en.wikipedia.org/wiki/Cumulative_distribution_function

  Example: 
      (cdf-exp 2.0 :rate 1/2)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          rate (if (number? (:rate opts)) (:rate opts) 1)
          dist (cern.jet.random.Exponential. rate (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Exponential.html
      http://en.wikipedia.org/wiki/Exponential_distribution

  Example: 
      (sample-exp 1000 :rate 1/2)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          rate (if (number? (:rate opts)) (:rate opts) 1)]
      (if (= size 1)
        (cern.jet.random.Exponential/staticNextDouble rate)
        (for [_ (range size)] (cern.jet.random.Exponential/staticNextDouble rate))))))






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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Binomial.html
      http://en.wikipedia.org/wiki/Binomial_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-binomial 10 :prob 1/4 :size 20)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          n (if (number? (:size opts)) (:size opts) 1)
          p (if (number? (:prob opts)) (:prob opts) 1/2)
          dist (cern.jet.random.Binomial. n p (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Binomial.html
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
                  (fn [x1] (cern.jet.stat.Probability/binomial x1 n p))
                  (fn [x1] (cern.jet.stat.Probability/binomialComplemented x1 n p)))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Binomial.html
      http://en.wikipedia.org/wiki/Binomial_distribution

  Example: 
      (sample-binomial 1000 :prob 1/4 :size 20)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          n (if (number? (:size opts)) (:size opts) 1)
          p (if (number? (:prob opts)) (:prob opts) 1/2)]
      (if (= size 1)
        (cern.jet.random.Binomial/staticNextInt n p)
        (for [_ (range size)] (cern.jet.random.Binomial/staticNextInt n p))))))



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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Poisson.html
      http://en.wikipedia.org/wiki/Poisson_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-poisson 5 :lambda 10)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          lambda (if (number? (:lambda opts)) (:lambda opts) 1)
          dist (cern.jet.random.Poisson. lambda (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Poisson.html
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
                  (fn [x1] (cern.jet.stat.Probability/poisson x1 lambda))
                  (fn [x1] (cern.jet.stat.Probability/poissonComplemented x1 lambda)))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Poisson.html
      http://en.wikipedia.org/wiki/Poisson_distribution

  Example: 
      (sample-poisson 1000 :lambda 10)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          lambda (if (number? (:lambda opts)) (:lambda opts) 1)]
     (if (= size 1)
        (cern.jet.random.Poisson/staticNextInt lambda)
        (for [_ (range size)] (cern.jet.random.Poisson/staticNextInt lambda))))))



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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/NegativeBinomial.html
      http://en.wikipedia.org/wiki/Negative_binomial_distribution
      http://en.wikipedia.org/wiki/Probability_density_function

  Example: 
      (pdf-neg-binomial 10 :prob 1/2 :size 20)
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          size (if (number? (:size opts)) (:size opts) 10)
          prob (if (number? (:prob opts)) (:prob opts) 1/2)
          dist (cern.jet.random.NegativeBinomial. size prob (cern.jet.random.engine.MersenneTwister.))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/NegativeBinomial.html
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
                  (fn [x1] (cern.jet.stat.Probability/negativeBinomial x1 size prob))
                  (fn [x1] (cern.jet.stat.Probability/negativeBinomialComplemented x1 size prob)))]
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
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/NegativeBinomial.html
      http://en.wikipedia.org/wiki/Negative_binomial_distribution

  Example: 
      (sample-neg-binomial 1000 :prob 1/2 :size 20)
"
  ([size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          size (if (number? (:size opts)) (:size opts) 10)
          prob (if (number? (:prob opts)) (:prob opts) 1/2)]
     (if (= size 1)
        (cern.jet.random.NegativeBinomial/staticNextInt size prob)
        (for [_ (range size)] (cern.jet.random.NegativeBinomial/staticNextInt size prob))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; STATISTICAL FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn sum-of-squares 
  "Returns the sum-of-squares of the given sequence."
  ([x]
    (let [xx (to-list x)]
      (Descriptive/sumOfSquares (DoubleArrayList. (double-array xx))))))


(defn sum 
  "Returns the sum of the given sequence."
  ([x]
    (let [xx (to-list x)]
      (Descriptive/sum (DoubleArrayList. (double-array xx))))))


(defn prod 
  "Returns the product of the given sequence."
  ([x]
    (let [xx (to-list x)]
      (Descriptive/product (DoubleArrayList. (double-array xx))))))



(defn mean
"
  Returns the mean of the data, x. 

  Examples:
    (mean (sample-normal 100))

  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Descriptive.html
    http://en.wikipedia.org/wiki/Mean

" 
([x]
  (let [xx (to-list x)]
    (Descriptive/mean (DoubleArrayList. (double-array xx))))))



(defn variance 
"
  Returns the sample variance of the data, x. 

  Examples:
    (variance (sample-normal 100))

  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Descriptive.html
    http://en.wikipedia.org/wiki/Sample_variance#Population_variance_and_sample_variance

"
  ([x] (Descriptive/sampleVariance (length x) (sum x) (sum-of-squares x))))



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
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Descriptive.html
    http://en.wikipedia.org/wiki/Covariance
"
  ([x y]
    (let [
          xx (to-list x)
          yy (to-list y)
        ]
      (Descriptive/covariance 
        (DoubleArrayList. (double-array xx))
        (DoubleArrayList. (double-array yy)))))
  ([mat]
    ;(incanter.Matrix. (cern.colt.matrix.doublealgo.Statistic/covariance mat))))
        (let [n (ncol mat)]
          (matrix 
            (for [i (range n) j (range n)] 
              (covariance (sel mat true i) (sel mat true j))) n))))



(defn median 
"
  Returns the median of the data, x. 

  Examples:
    (median (sample-normal 100))

  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Descriptive.html
    http://en.wikipedia.org/wiki/Median

" 
  ([x]
    (let [xx (to-list x)]
      (Descriptive/median (DoubleArrayList. (double-array xx))))))


(defn sd 
"
  Returns the standard deviation of the data, x. 

  Examples:
    (sd (sample-normal 100))

  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Descriptive.html
    http://en.wikipedia.org/wiki/Standard_deviation
" 
  ([x]
    (Descriptive/sampleStandardDeviation (length x) (variance x))))



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
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Descriptive.html
    http://en.wikipedia.org/wiki/Quantile

"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil) 
          _x (if (matrix? x) (to-list x) x)
          data (cern.colt.list.DoubleArrayList. (double-array (sort _x)))
          probs (cond 
                  (number? (:probs opts))
                    (:probs opts)
                  (coll? (:probs opts))
                    (cern.colt.list.DoubleArrayList. (double-array (:probs opts)))
                  :default
                    (cern.colt.list.DoubleArrayList. (double-array [0.0 0.25 0.5 0.75 1.0])))]
        (if (number? probs)
          (cern.jet.stat.Descriptive/quantile data probs)
          (seq (.elements (cern.jet.stat.Descriptive/quantiles data probs)))))))



(defn sample 
" Returns a sample of the given size from the given collection. If replacement 
  is set to false it returns a set, otherwise it returns a list.

  Arguments:
    coll -- collection to be sampled from
    size -- sample size

  Options:
    :replacement (default true) -- sample with replacement


  Examples:
    (sample [:red :green :blue] 10) ; choose 10 items that are either :red, :green, or :blue.
    (sample (seq \"abcdefghijklmnopqrstuvwxyz\")  4 :replacement false) ; choose 4 random letters.

"
  ([coll size & options]
    (let [opts (if options (apply assoc {} options) nil) 
          replacement? (if (false? (:replacement opts)) false true)
          max-idx (dec (count coll))]
      (if replacement?
        (map #(nth coll %) (sample-uniform size :min 0 :max max-idx :integers true))
        (if (> size (count coll))
          (throw (Exception. "'size' can't be larger than (count coll) without replacement!"))
          (loop [samp #{}]
            (if (= (count samp) size)
              samp
            (recur (conj samp (nth coll (sample-uniform 1 :min 0 :max max-idx :integers true)))))))))))




(defn cumulative-sum 
  " Returns a sequence of cumulative sum for the given collection. For instance 
    The first value equals the first value of the argument, the second value is
    the sum of the first two arguments, the third is the sum of the first three
    arguments, etc.

    Examples:
      (cumulative-sum (range 100))
  "
  ([coll] 
   (let [n (count coll)]
    (loop [in-coll (rest coll)
           cumu-sum [(first coll)]]
      (if (empty? in-coll)
        cumu-sum
        (recur (rest in-coll) (conj cumu-sum (+ (last cumu-sum) (first in-coll)))))))))



(defn cumulative-mean 
  " Returns a sequence of cumulative means for the given collection. For instance 
    The first value equals the first value of the argument, the second value is
    the mean of the first two arguments, the third is the mean of the first three
    arguments, etc.

    Examples:
      (cumulative-mean (sample-normal 100))
  "
  ([coll] (map / (cumulative-sum coll) (range 1 (inc (count coll))))))




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
      :fitted -- the predicted values of y
      :residuals -- the residuals of each observation
      :std-errors -- the standard errors of the coeffients
      :sse -- the sum of squared errors, also called the residual sum of squares
      :ssr -- the regression sum of squares, also called the explained sum of squares
      :sst -- the total sum of squares (proportional to the sample variance)
      :r-square -- coefficient of determination

  Examples:
    (use '(incanter core stats io))
    (def iris (to-matrix (read-dataset \"data/iris.dat\" :header true)))
    (def y (sel iris :columns 0))
    (def x (sel iris :columns (range 1 6)))
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
    (view (xy-plot x1 (pdf-f x1 :df1 4 :df2 144)))
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
          coefs (to-list (if (or (number? xtxi) (number? xty)) 
                  (mult xtxi xty)
                  (mmult xtxi xty)))
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
          adj-r-square (- 1 (* (- 1 r-square) (/ (- n 1) (- n p 1))))
          mse (/ sse (- n p))
          msr (/ ssr (- p 1))
          f-stat (/ msr mse)
          df1 (- (ncol _x) 1)
          df2 (- (nrow _x) (ncol _x))
          f-prob (cdf-f f-stat :df1 df1 :df2 df2 :lower-tail false)
          coef-var (mult mse xtxi)
          std-errors (if (number? xtxi)
                       (* (/ sse (- n p 1)) xtxi)
                       (for [i (range p)] (* (/ sse (- n p 1)) (sel xtxi i i))))
         ]
      (with-meta
        {:x _x
         :y y
         :fitted fitted
         :design-matrix _x
         :coefs coefs
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


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CATEGORICAL VARIABLES
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn categorical-var
" Returns a categorical variable based on the values in the given collection.

  Options:
    :data (default nil) factors will be extracted from the given data.
    :ordered? (default false) indicates that the variable is ordinal.
    :labels (default (sort (into #{} data)))
    :levels (range (count labels))

  Examples:
    (categorical-var :data [:a :a :c :b :a :c :c])
    (categorical-var :labels [:a :b :c])
    (categorical-var :labels [:a :b :c] :levels [10 20 30])
    (categorical-var :levels [1 2 3])

"
  ([& args]
   (let [opts (if args (apply assoc {} args) nil)
         data (if (:data opts) (:data opts) nil)
         ordered? (if (false? (:ordered? opts)) true false)
         labels (if (:labels opts) 
                  (:labels opts) 
                  (if (nil? data)
                    (:levels opts)
                    (sort (into #{} data))))
         levels (if (:levels opts) (:levels opts) (range (count labels)))]
    {:ordered? ordered?
     :labels labels
     :levels levels
     :to-labels (apply assoc {} (interleave levels labels))
     :to-levels (apply assoc {} (interleave labels levels))})))


(defn to-levels 
"
"
  ([coll & options]
    (let [opts (if options (apply assoc {} options) nil)
          cat-var (if (:categorical-var opts) (:categorical-var opts) (categorical-var :data coll))
          to-levels (:to-levels cat-var)]
      (for [label coll] (to-levels label)))))


(defn to-labels 
"
"
  ([coll cat-var] 
    (let [to-labels (:to-labels cat-var)]
      (for [level coll] (to-labels level)))))



(defn get-dummy-vars [n]
  (let [nbits (dec (Math/ceil (log2 n)))]
    (map #(for [i (range nbits -1 -1)] (if (bit-test % i) 1 0))
         (range n))))


(defn to-dummy-vars [coll]
  (let [cat-var (categorical-var :data coll)
        levels (:levels cat-var)
        encoded-data (to-levels coll :categorical-var cat-var)
        bit-map (get-dummy-vars (count levels))]
    (for [item encoded-data] (nth bit-map item))))



(defn cat-to-dummy-vars [dataset column-key]
  (let [col (first (get-columns dataset [column-key]))]
    (if (some string? col) 
      (matrix (to-dummy-vars col))
      (matrix col))))


(defn to-matrix 
  "Converts a dataset into a matrix."
  ([dataset & options]
    (let [opts (if options (apply assoc {} options) nil)
          dummy-vars (if (:dummy-vars opts) (:dummy-vars opts) nil)]
      (reduce bind-columns 
              (map #(cat-to-dummy-vars dataset %) 
                    (range (count (keys (:column-names dataset)))))))))


(defn transpose-seq [coll]
  (map (fn [idx] (map #(nth % idx) coll)) (range (count (first coll)))))

;(transpose-seq (to-dummy (first (get-columns iris-map [4]))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

