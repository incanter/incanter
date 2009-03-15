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


;NOTE
;(time (let [arr  (make-array Float/TYPE 1 1)] 
;(dotimes [_ 100000] 
;  (let [#^floats sub-arr (aget arr 0)] 
;    (aset-float sub-arr 0 0.0))))) 
;

; to compile (compile 'incanter.stats)
; to use (use incanter.stats)


(ns incanter.stats 
  (:import (cern.colt.list DoubleArrayList)
           (cern.jet.random Gamma)
           (cern.jet.random.engine MersenneTwister)
           (cern.jet.stat Descriptive))
  (:use (incanter matrix)))


;;------------------------------------------------------------------------------
;; DATASET FUNCTIONS
;;------------------------------------------------------------------------------

(defn colnames 
  ([mat] (keys (:colnames ^mat)))
  ([mat col-names] 
   (if col-names 
    (with-meta mat 
              (assoc ^mat :colnames 
                (into {} (map vector col-names (range (count col-names)))))))))

;;;;;;;;;;;;;;; RANDOM DIST FUNCTIONS ;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn runif [n & options]
  (let [opts (if options (apply assoc {} options) nil) 
        rnd (new java.util.Random)
       ]
    (loop [v [] i 0]
      (if (< i n)
        (recur (conj v (.nextDouble rnd)) (inc i))
        v))))


(defn rnorm [n & options]
   (let [opts (if options (apply assoc {} options) nil) 
          rnd (new java.util.Random)
          m (if (number? (:mean opts)) (:mean opts) 0)
          s (if (number? (:sd opts)) (:sd opts) 1)]
          (loop [v [] i (int 0)]
            (if (< i n)
              (recur 
                (conj v (if (and (= m 0) (= 1)) 
                          (.nextGaussian rnd) 
                          (+ (* (.nextGaussian rnd) s) m)))
                (inc i))
              v))))


(defn normal-generator []
  (let [rnd (java.util.Random.)]
    (fn [mean sd] 
      (+ (* (.nextGaussian rnd) sd) mean))))


(defn rand-gamma [shape rate]
  (Gamma/staticNextDouble shape rate))


(defn gamma-generator []
  (let [rnd (Gamma. 1 1 
                 (MersenneTwister. (java.util.Date.)))]
    (fn [shape rate] 
      (.nextDouble rnd shape rate))))


(defn rgamma [n & options]
   (let [opts (if options (apply assoc {} options) nil) 
          rnd (gamma-generator)
          shape (if (number? (:shape opts)) (:shape opts) 1)
          rate (if (number? (:rate opts)) (:rate opts) 1)
        ]
          (loop [v [] i 0]
            (if (< i n)
              (recur 
                (conj v (rnd shape rate)) 
                (inc i))
              v))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; functions that return Colt distribution objects
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

;; CONTINOUS DISTRIBUTIONS

(defn normal-dist 
" Creates a normal distribution.
  Options: 
    :mean (default value 0) 
    :sd (default value 1)
  Returns: 
    a distribution object of type cern.jet.random.Normal
  See also: 
      pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Normal.html
      http://en.wikipedia.org/wiki/Normal_distribution
  Example: 
      (normal-dist :mean 10 :sd 5)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil)
          mean (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)]
      (cern.jet.random.Normal. mean sd (cern.jet.random.engine.MersenneTwister.)))))


(defn uniform-dist 
" Creates a uniform distribution.
  Options: 
    :min (default value 0) 
    :max (default value 1)
  Returns: 
    a distribution object of type cern.jet.random.Uniform
  See also: 
      pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Uniform.html
      http://en.wikipedia.org/wiki/Uniform_distribution
  Example: 
      (normal-dist)
      (normal-dist :min 1 :max 10)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil)
          min (double (if (:min opts) (:min opts) 0.0))
          max (double (if (:max opts) (:max opts) 1.0))]
      (cern.jet.random.Uniform. min max (cern.jet.random.engine.MersenneTwister.)))))


(defn beta-dist
" Creates a Beta distribution.
  Options: 
    :alpha (default value 1) 
    :beta (default value 1)
  Returns: 
    a distribution object of type cern.jet.random.Beta
  See also: 
      pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Beta.html
      http://en.wikipedia.org/wiki/Beta_distribution
  Example: 
      (beta-dist :alpha 1 :beta 2)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil)
          alpha (if (:alpha opts) (:alpha opts) 1)
          beta (if (:beta opts) (:beta opts) 1)]
      (cern.jet.random.Beta. alpha beta (cern.jet.random.engine.MersenneTwister.)))))


(defn gamma-dist
" Creates a Gamma distribution.
  Options: 
    :shape (default value 1) 
    :rate (default value 1)
  Returns: 
    a distribution object of type cern.jet.random.Gamma
  See also: 
      pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Gamma.html
      http://en.wikipedia.org/wiki/Gamma_distribution
  Example: 
      (gamma-dist :shape 1 :rate 2)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil) 
          shape (if (number? (:shape opts)) (:shape opts) 1)
          rate (if (number? (:rate opts)) (:rate opts) 1)
        ]
      (cern.jet.random.Gamma. shape rate (cern.jet.random.engine.MersenneTwister.)))))



(defn chisq-dist
" Creates a Chi Square distribution.
  Options: 
    :df (default value 1) 
  Returns: 
    a distribution object of type cern.jet.random.ChiSquare
  See also: 
      pdf, cdf, sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/ChiSquare.html
      http://en.wikipedia.org/wiki/Chi_square_distribution
  Example: 
      (chisq-dist :df 2)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)
         ]
      (cern.jet.random.ChiSquare. df (cern.jet.random.engine.MersenneTwister.)))))



(defn t-dist
" Creates a Student's t-distribution.
  Options: 
    :df (default value 1) 
  Returns: 
    a distribution object of type cern.jet.random.StudentT
  See also: 
      pdf, cdf, sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/StudentT.html
      http://en.wikipedia.org/wiki/Student-t_distribution
  Example: 
      (t-dist :df 2)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil) 
          df (if (number? (:df opts)) (:df opts) 1)
         ]
      (cern.jet.random.StudentT. df (cern.jet.random.engine.MersenneTwister.)))))



(defn exp-dist
" Creates a Exponential distribution.
  Options: 
    :lambda (default value 1) 
  Returns: 
    a distribution object of type cern.jet.random.Exponential
  See also: 
      pdf, cdf, sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Exponential.html
      http://en.wikipedia.org/wiki/Exponential_distribution
  Example: 
      (exp-dist :lambda 1/2)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil) 
          lambda (if (number? (:lambda opts)) (:lambda opts) 1)
         ]
      (cern.jet.random.Exponential. lambda (cern.jet.random.engine.MersenneTwister.)))))




;; DISCRETE DISTRIBUTIONS


(defn binomial-dist
" Creates a Binomial distribution.
  Options: 
    :n (default value 1) 
    :p (default value 1/2)
  Returns: 
    a distribution object of type cern.jet.random.Binomial
  See also: 
      pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Binomial.html
      http://en.wikipedia.org/wiki/Binomial_distribution
  Example: 
      (binomial-dist :p 1/4 :n 20)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil) 
          n (if (number? (:n opts)) (:n opts) 1)
          p (if (number? (:p opts)) (:p opts) 1/2)
        ]
      (cern.jet.random.Binomial. n p (cern.jet.random.engine.MersenneTwister.)))))



(defn poisson-dist
" Creates a Poisson distribution.
  Options: 
    :mean (default value 1) 
  Returns: 
    a distribution object of type cern.jet.random.Poisson
  See also: 
      pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/Poisson.html
      http://en.wikipedia.org/wiki/Poisson_distribution
  Example: 
      (poisson-dist :mean 10)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil) 
          mean (if (number? (:mean opts)) (:mean opts) 1)
        ]
      (cern.jet.random.Poisson. mean (cern.jet.random.engine.MersenneTwister.)))))


(defn neg-binomial-dist
" Creates a Negative Binomial distribution.
  Options: 
    :n (default value 10) 
    :p (default value 1/2)
  Returns: 
    a distribution object of type cern.jet.random.NegativeBinomial
  See also: 
      pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/random/NegativeBinomial.html
      http://en.wikipedia.org/wiki/Negative_binomial_distribution
  Example: 
      (neg-binomial-dist :p 1/2 :n 10)
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil) 
          n (if (number? (:n opts)) (:n opts) 10)
          p (if (number? (:p opts)) (:p opts) 1/2)
        ]
      (cern.jet.random.NegativeBinomial. n p (cern.jet.random.engine.MersenneTwister.)))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; functions that operate on distribution objects
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 
  
(defn continuous-dist? [dist]
  (isa? (class dist) cern.jet.random.AbstractContinousDistribution))


(defn pdf 
" Calculates the pdf (the density) of the given value, x, for the given 
  distribution. This is equivalent to R's d{dist} functions 
  (e.g. dnorm, dgamma, etc).
"
  ([dist x]
    (if (coll? x)
      (map #(.pdf dist %) x)
    (if (number? x)
      (.pdf dist x)))))


(defn cdf 
" Calculates the cdf (the distribution function) of the given value, x, 
  for the given distribution. This is equivalent to R's p{dist} functions 
  (e.g. pnorm, pgamma, etc).
"
  ([dist x]
    (if (coll? x)
      (map #(.cdf dist %) x)
    (if (number? x)
      (.cdf dist x)))))


(defmulti sample 
" Draws a sample from the given distribution.
  Arguments:
    dist -- a distribution object (e.g. normal-dist, beta-dist, gamma-dist, etc
  Options:
    size -- the size of the sample to draw
  Example:
    (sample (normal-dist :mean 10 :sd 5))
    (sample (beta-dist :alpha 1 :beta 2) 100)
"
  (fn [dist & size] (continuous-dist? dist)))


(defmethod sample true
  ([dist]
    (.nextDouble dist))
  ([dist size]
    (for [_ (range size)] (.nextDouble dist))))


(defmethod sample false
  ([dist]
    (.nextInt dist))
  ([dist size]
    (for [_ (range size)] (.nextInt dist))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;; Inverse Distributions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 


(defn normal-inv 
" Calculates the inverse of the Normal CDF for the given probability
  (i.e. the quantile). This is equivalent to R's qnorm function.
  Options: 
    :mean (default value 0) 
    :sd (default value 1)
  Returns: 
    a value x, where (cdf (normal-dist) x) = probability
  See also: 
      normal-dist pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Probability.html
      http://en.wikipedia.org/wiki/Normal_distribution
  Example: 
      (normal-inv 0.975)
      (normal-inv [0.025 0.975] :mean -2 :sd (sqrt 0.5))
"
  ([probability & options]
    (let [opts (if options (apply assoc {} options) nil)
          mean (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)
          x (if (coll? probability) 
              (map #(cern.jet.stat.Probability/normalInverse %) probability)
              (cern.jet.stat.Probability/normalInverse probability))]
      (plus mean (mult sd x)))))



(defn chisq-inv 
" Calculates the inverse of the Chi Square CDF for the given probability
  (i.e. the quantile). This is equivalent to R's qchisq function.
  Options: 
    :df (default value 1) 
  Returns: 
    a value x, where (cdf (chisq-dist) x) = probability
  See also: 
      chisq-dist pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Probability.html
      http://en.wikipedia.org/wiki/Chi_square_distribution
  Example: 
      (chisq-inv 0.975)
      (chisq-inv [0.025 0.975] :df 2)
"
  ([probability & options]
    (let [opts (if options (apply assoc {} options) nil)
          df (if (:df opts) (:df opts) 1)
          x (if (coll? probability) 
              (map #(cern.jet.stat.Probability/chiSquareComplemented df %) probability)
              (cern.jet.stat.Probability/chiSquareComplemented df probability))]
      x)))



(defn gamma-inv 
" Calculates the inverse of the Gamma CDF for the given probability
  (i.e. the quantile). This is equivalent to R's qgamma function.
  Options: 
    :shape (default value 1) 
    :rate (default value 1)
  Returns: 
    a value x, where (cdf (gamma-dist) x) = probability
  See also: 
      gamma-dist pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Probability.html
      http://en.wikipedia.org/wiki/Gamma_distribution
  Example: 
      (gamma-inv 0.975)
      (gamma-inv [0.025 0.975] :shape 2 :rate 2)
"
  ([probability & options]
    (let [opts (if options (apply assoc {} options) nil)
          shape (if (:shape opts) (:shape opts) 1)
          rate (if (:rate opts) (:rate opts) 1)
          x (if (coll? probability) 
              (map #(cern.jet.stat.Probability/gammaComplemented shape rate %) probability)
              (cern.jet.stat.Probability/gammaComplemented shape rate probability))]
        x)))


(defn beta-inv 
" Calculates the inverse of the Beta CDF for the given probability
  (i.e. the quantile). This is equivalent to R's qbeta function.
  Options: 
    :alpha (default value 1) 
    :alpha (default value 1)
  Returns: 
    a value x, where (cdf (beta-dist) x) = probability
  See also: 
      beta-dist pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Probability.html
      http://en.wikipedia.org/wiki/Gamma_distribution
  Example: 
      (beta-inv 0.975)
      (beta-inv [0.025 0.975] :alpha 2 :beta 2)
"
  ([probability & options]
    (let [opts (if options (apply assoc {} options) nil)
          alpha (if (:alpha opts) (:alpha opts) 1)
          beta (if (:beta opts) (:beta opts) 1)
          x (if (coll? probability) 
              (map #(cern.jet.stat.Probability/betaComplemented alpha beta %) probability)
              (cern.jet.stat.Probability/betaComplemented alpha beta probability))]
        x)))


(defn t-inv 
" Calculates the inverse of the Student's t CDF for the given probability
  (i.e. the quantile). This is equivalent to R's qt function.
  Options: 
    :df (default value 1) 
  Returns: 
    a value x, where (cdf (t-dist) x) = probability
  See also: 
      t-dist pdf cdf sample
  References: 
      http://acs.lbl.gov/~hoschek/colt/api/cern/jet/stat/Probability.html
      http://en.wikipedia.org/wiki/Student-t_distribution
  Example: 
      (t-inv 0.975)
      (t-inv [0.025 0.975] :df 25)
"
  ([probability & options]
    (let [opts (if options (apply assoc {} options) nil)
          df (if (:df opts) (:df opts) 1)
          x (if (coll? probability) 
              (map #(cern.jet.stat.Probability/studentTInverse % df) probability)
              (cern.jet.stat.Probability/studentTInverse probability df))]
        x)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; STATISTICAL FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn covariance 
  ([x y]
    (let [
          xx (to-1D-vect x)
          yy (to-1D-vect y)
        ]
      (Descriptive/covariance 
        (DoubleArrayList. (double-array xx))
        (DoubleArrayList. (double-array yy)))))
  ([mat]
        (let [n (ncol mat)]
          (matrix 
            (for [i (range n) j (range n)] 
              (covariance (sel mat true i) (sel mat true j))) n))))


(defn sum-of-squares [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/sumOfSquares (DoubleArrayList. (double-array xx)))))


(defn sum [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/sum (DoubleArrayList. (double-array xx)))))


(defn variance [x]
  (Descriptive/sampleVariance (length x) (sum x) (sum-of-squares x)))


(defn mean [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/mean (DoubleArrayList. (double-array xx)))))


(defn median [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/median (DoubleArrayList. (double-array xx)))))


(defn sd [x]
  (Descriptive/sampleStandardDeviation (length x) (variance x)))


(defn prod [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/product (DoubleArrayList. (double-array xx)))))


(defn hist [x & options]
  (let [opts (if options (apply assoc {} options) nil)
        xx (to-1D-vect x)
        nbin (if (:nbin opts) 
               (:nbin opts) 
               (if (< (count xx) 10) (count xx) 10))
        min-val (reduce min xx)
        max-val (reduce max xx)
        bin-size (/ (- max-val min-val) nbin)
        bin-edges (into [] (for [i (range nbin)] (+ min-val (* (inc i) bin-size))))
        cumm-counts (into [] (for [b bin-edges] (reduce + (map #(if (<= % b) 1 0) xx))))
        bin-counts (into [(first cumm-counts)] (map #(- %2 %1) cumm-counts (into [] (next cumm-counts))))
       ]
    {:min-val min-val 
     :max-val max-val 
     :bin-size bin-size
     :nbin nbin
     :bin-edges bin-edges 
     :cumm-counts cumm-counts 
     :bin-counts bin-counts}))


(defn print-hist [x & options]
  (let [opts (if options (apply assoc {} options) nil)
        nbin (if (:nbin opts) (:nbin opts) 10)
        hist-width (if (:hist-width opts) (:hist-width opts) 80)
        h (if (:nbin opts) (hist x :nbin (:nbin opts)) (hist x))
        max-count (reduce max (:bin-counts h))
       ]
    (dotimes [i (:nbin h)]
      (dotimes [_ (* hist-width (/ ((:bin-counts h) i) max-count))] (print \*))
      (print (format " %.2f " ((:bin-edges h) i)))
      (print [((:bin-counts h) i)])
      (println))))



;;;;;;;;;;;;;;; OLS FUNCTIONS ;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn lm-coef [x y]
  (mmult (solve (mmult (trans x) x)) (mmult (trans x) y)))


(defn lm-resid [x y]
  (minus y (mmult x (lm-coef x y))))


(defn lm-se [x y]
  (let [S (sum-of-squares (lm-resid x y))
        n (nrow y)
        p (ncol x)
        xtxi (solve (mmult (trans x) x))
       ]
    (into [] (for [i (range p)] (* (/ S (- n p 1)) (sel xtxi i i))))))




