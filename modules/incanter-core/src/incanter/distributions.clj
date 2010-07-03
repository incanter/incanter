;;; distributions.clj -- A common distribution protocol with several implmentations.

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

;; CHANGE LOG

(ns #^{:doc "Distributions. TODO: provide a useful string" :author "Mark M. Fredrickson"}
	incanter.distributions
  (:import java.util.Random
           (cern.jet.random.tdouble Beta Binomial ChiSquare DoubleUniform Exponential Gamma NegativeBinomial Normal Poisson StudentT)
           (cern.jet.stat.tdouble Probability)
           (cern.jet.random.tdouble.engine DoubleMersenneTwister))
  (:use [clojure.contrib.def :only (defvar defvar-)]
        [clojure.set :only (intersection)]
        [clojure.contrib.combinatorics :only (combinations)]
        [incanter.core :only (gamma pow regularized-beta)]))

;; NOTE: as of this writing, (doc pdf/cdf/...) do not show the doc strings.
;; including them for when this bug is fixed.
(defprotocol Distribution
"
  The distribution protocol defines operations on probability distributions.
	Distributions may be univariate (defined over scalars) or multivariate
	(defined over vectors). Distributions may be discrete or continuous.

	For a list of types that implement the protocol run (extenders Distribution).
	Implementations are provided for the various Clojure collection datatypes.
	See the example below for using the distribution methods on these types.

	See also:
		pdf, cdf, draw, support

	References:
		http://en.wikipedia.org/wiki/Probability_distribution

	Examples:
	  (support [1 3 4 2 1 3 4 2]) ; returns the set #{1 2 3 4}
		(draw [1 3 4 2 1 3 4 2]) ; returns a value from #{1 2 3 4}
		(pdf [2 1 2] 1) ; returns the value 1/3
		(cdf [2 1 2 3] 2) ; returns the value 3/4 
"
	(pdf [d v]
"
  A function of the incanter.distribution.Distribution protocol.

  Returns the value of the probability density/mass function for the
  distribution d at support v.

	See also:
	  Distribution, cdf, draw, support

	References:
	  http://en.wikipedia.org/wiki/Probability_density_function

	Examples:
		(pdf [2 1 2] 1) ; returns the value 1/3\n")
  
	(cdf [d v]
"
  A function of the incanter.distribution.Distribution protocol.

  Returns the value of the cumulative density function for the
  distribution d at support v.

	See also:
	  Distribution, pdf, draw, support

	References:
	  http://en.wikipedia.org/wiki/Cumulative_distribution_function

	Examples:
		(cdf [2 1 2 3] 2) ; returns the value 3/4 \n")

	(draw [d]
"
  A function of the incanter.distribution.Distribution protocol.

  Returns a randomly drawn value from the support of distribution d. 

	See also:
	  Distribution, pdf, cdf, support

	Examples:
		(draw [1 3 4 2 1 3 4 2]) ; returns a value from #{1 2 3 4}\n")
  
  (support [d]
"
	**** EXPERIMENTAL ****
  A function of the incanter.distribution.Distribution protocol.

  Returns the support of the probability distribution d.
	For discrete distributions, the support is a set (i.e. #{1 2 3}).
	For continuous distributions, the support is a 2 element vector
	describing the range. For example, the uniform distribution over
	the unit interval would return the vector [0 1].

	This function is marked as experimental to note that the output
	format might need to adapt to more complex support structures.
	For example, what would best describe a mixture of continuous
	distributions?

	See also:
	  Distribution, pdf, draw, support

	References:
	  http://en.wikipedia.org/wiki/Cumulative_distribution_function

	Examples:
		(cdf [2 1 2 3] 2) ; returns the value 3/4 \n")
;  (mean [d] "TODO")
;  (variance [d] "TODO")

)
;; Notes: other possible methods include moment generating function, transformations/change of vars

(defn- tabulate
  "Private tabulation function that works on any data type, not just numerical"
  [v]
  (let [f (frequencies v)
        total (reduce + (vals f))]
    (into {} (map (fn [[k v]] [k (/ v total)]) f))))

(defn- simple-cdf
  "Compute the CDF at a value by getting the support and adding up the values until
	 you get to v (inclusive)"
  [d v]
	(reduce + (map #(pdf d %) (filter #(>= v %) (support d)))))

;(defn- draw-nr)

;; Extending all sequence types to be distributions
(extend-type clojure.lang.Sequential
  Distribution
  (pdf [d v] (get (tabulate d) v 0))
  (cdf [d v] (simple-cdf d v))
  (draw [d] (nth d (rand-int (count d))))
                                        ; (draw [d n] (repeatedly n #(draw d))) 
  (support [d] (set d))
  ;; (mean [d] (if-not (every? number? d) nil
  ;;                   (/ (reduce + d)
  ;;                      (count d))))
)

;; Sets (e.g. #{1 2 3}) are not seqs, so need their own implementation
(extend-type clojure.lang.PersistentHashSet
	Distribution
  	(pdf [d v] (if (get d v) (/ 1 (count d)) 0))
    (cdf [d v] nil) ; should this throw an exception? ; or just sort ascendingly then find P[X \leq x] (but then what is a comparator is not defined)?
    (draw [d] (nth (support d) (rand-int (count d))))
    (support [d] (vec d)))

(defn- take-to-first
  "Returns a lazy sequence of successive items from coll up to
  and including the point at which it (pred item) returns true.
  pred must be free of side-effects.

  src: http://www.mail-archive.com/clojure@googlegroups.com/msg25706.html"
  [pred coll]
  (lazy-seq
   (when-let [s (seq coll)]
     (if-not (pred (first s))
       (cons (first s) (take-to-first pred (rest s)))
       (list (first s))))))

(defn roulette-wheel
  "Perform a roulette wheel selection given a list of frequencies"
  [freqs]
  (let [nfreqs (count freqs)
        tot (reduce + freqs)]
    (if (= tot 0)
      nil
      (let [dist (map #(/ % tot) freqs)
            rval (double (rand))]
        (loop [acc 0, i 0]
          (let [lb acc, ub (+ acc (nth dist i))]
            (cond (>= (+ i 1) nfreqs) i
                  (and (>= rval lb) (< rval ub)) i
                  :else (recur ub (+ i 1)))))))))

;; map extension takes values as frequencies
(extend-type clojure.lang.APersistentMap
  Distribution
  (pdf [d v] (if-not (contains? d v)
               0
               (/ (get d v) (reduce + (vals d)))))
  (cdf [d v] (if (instance? clojure.lang.PersistentTreeMap d)
               (let [nd (count (support d))
                     compd (.comparator d)
                     fkey (first (keys d))]
                 (cond (= nd 0) 0
                       (= nd 1) (if (= -1 (.compare compd v fkey)) 0 1)
                       :else (let [upto (take-to-first #(= (key %) v) d)]
                               (if-not (contains? d v)
                                 (if (= -1 (.compare compd v fkey)) 0 1)
                                 (/ (reduce + (vals upto))
                                    (reduce + (vals d)))))))
               nil))
  (draw [d] (nth (keys d) (roulette-wheel (vals d))))
  (support [d] (keys d)))

; defrecord expands to have a (contains? ...) (or .contains method) that causes
; a reflection warning. Note much to do about that for now. Perhaps it will be
; fixed in clojure.core later.
(defrecord UniformInt [start end]
  Distribution
  	(pdf [d v] (/ 1 (- end start)))
		(cdf [d v] (* v (pdf d v)))
		(draw [d]
		; for simplicity, cast to BigInt to use the random bitstream
    ; a better implementation would handle different types differently
    	(let [r (bigint (- end start))
            f #(+ start (BigInteger. (.bitLength r) (Random.)))] ; TODO replace with reused, threadsafe random
        (loop [candidate (f)] ; rejection sampler, P(accept) > .5, so don't fret
					(if (< candidate end) candidate (recur (f))))))
		(support [d] (range start end)))

(defn integer-distribution
"
  Create a uniform distribution over a set of integers over
	the (start, end] interval. An alternative method of creating
	a distribution would be to just use a sequence of integers
	(e.g. (draw (range 100000))). For large sequences, like the one
	in the example, using a sequence will be require realizing the
	entire sequence before a draw can be taken. This less efficient than
	computing random draws based on the end points of the distribution.

	Arguments:
		start	The lowest end of the interval, such that (>= (draw d) start)
					is always true. (Default 0)
		end		The value at the upper end of the interval, such that
					(> end (draw d)) is always true. Note the strict inequality.
					(Default 1)

	See also:
		pdf, cdf, draw, support

	References:
		http://en.wikipedia.org/wiki/Uniform_distribution_(discrete)

	Examples:
		(pdf (integer-distribution 0 10) 3) ; returns 1/10 for any value
		(draw (integer-distribution -5 5))
		(draw (integer-distribution (bit-shift-left 2 1000))) ; probably a very large value
"  
  ([] (integer-distribution 0 1))
  ([end] (integer-distribution 0 end))
  ([start end]
     (assert (> end start))
     (UniformInt. start end)))

;;;; Combination Sampling: Draws from the nCk possible combinations ;;;;

(defn- nCk [n k]
  (cond
   	(or (< n 0) (< k 0) (< n k)) 0
    (or (= k 0) (= n k)) 1
  	:else (/ (reduce * 1 (range (inc (- n k)) (inc n))) (reduce * 1 (range 1 (inc k))))))

(defn- decode-combinadic
  "Decodes a 0 to nCk - 1 integer into its combinadic form, a set of
	k-tuple of indices, where each index i is 0 < i < n - 1"
  [n k c]
  (let [max-c (nCk n k)]
      (assert (and (<= 0 c) (> max-c c)))
      (loop [candidate (dec n) ks (range k 0 -1) remaining c tuple '()]
        (if (empty? ks) tuple ;; <- return value of function
            (let [k (first ks)
                  v (first (filter #(>= remaining (nCk % k)) (range candidate (- k 2) -1)))]
              (assert (not (nil? v)))
              (recur v (rest ks) (- remaining (nCk v k)) (conj tuple v)))))))



(defn- res-sampler
  "Get a sample from the nCk possible combinations. Uses a reservoir
	sample from Chapter 4 of Tille, Y. (2006). Sampling Algorithms. Springer, New York."
  [n k]
  (let [res (transient (into [] (range 0 k)))]
    (doall (map
     (fn [i] (if (< (/ k i) (rand)) (assoc! res (rand-int k) i)))
     (range k n)))
    (persistent! res)))
  
; defrecord expands to have a (contains? ...) (or .contains method) that causes
; a reflection warning. Note much to do about that for now. Perhaps it will be
; fixed in clojure.core later.
(defrecord Combination [n k u]
  Distribution
  	(pdf [d v] (/ 1 (nCk n k)))
  	(cdf [d v] nil) ; TODO: this requires encoding combinations
  	(draw [d] (res-sampler n k))
  	(support [d] (map #(decode-combinadic n k %) (range 0 (nCk n k)))))

(defn combination-distribution
"
	Create a distribution of all the k-sized combinations of n integers.
	Can be considered a multivariate distribution over k-dimensions, where
	each dimension is a discrete random variable on the (0, n] range (though
	these variables are decidedly non-independent).

	A draw from this distribution can also be considered a sample without
	replacement from any finite set, where the values in the returned
	vector represent the indices of the items in the set.

	Arguments:
		n		The number of possible items from which to select.
		k		The size of a sample (without replacement) to draw.

	See also:
		test-statistic-distribution, integer-distribution, pdf, cdf, draw, support

	References:
		http://en.wikipedia.org/wiki/Combination

	Examples:
		
"  
  [n k]
  	(assert (>= n k)) (assert (and (<= 0 n) (<= 0 k)))
  	(Combination. n k (integer-distribution 0 (nCk n k))))

(def *test-statistic-iterations* 1000)
(def *test-statistic-map* pmap)

(defn test-statistic-distribution
"
	Create a distribution of the test-statistic over the possible
	random samples of treatment units from the possible units.

	There are two methods for generating the distribution. The
	first method is enumerating all possible randomizations and
	performing the test statistic on each. This gives the exact
	distribution, but is only feasible for small problems.

	The second method uses a combination-distribution to sample
	for the space of possible treatment assignments and applies
	the test statistic the sampled randomizations. While the
	resulting distribution is not exact, it is tractable for
	larger problems.

	The algorithm automatically chooses between the two methods
	by computing the number of possible randomizations and
	comparing it to *test-statistic-iterations*. If the exact
	distribution requires fewer than *test-statistic-iterations*
	the enumeration method is used. Otherwise, it draws
	*test-statistic-iterations* total samples for the simulated
	method.

	By default, the algorithm uses parallel computation. This is
	controlled by the function *test-statistic-map*, which is
	bound to pmap by default. Bind it to map to use a single
	thread for computation.

	Arguments:
		test-statistic	A function that takes two vectors and summarizes
				the difference between them
		n		The number of total units in the pool
		k	  The number of treatment units per sample

	See also:
		combination-distribution, pdf, cdf, draw, support

	References:
		http://en.wikipedia.org/wiki/Sampling_distribution
		http://en.wikipedia.org/wiki/Exact_test
		http://en.wikipedia.org/wiki/Randomization_test
		http://en.wikipedia.org/wiki/Lady_tasting_tea

	Examples:
		
"
	[test-statistic n k]
	; for now returns entire set of computed values, should summarize via frequencies
  (*test-statistic-map* test-statistic ; *t-s-m* is bound to pmap by default
    (let [cd (combination-distribution n k)]
  		(if (> (nCk n k) *test-statistic-iterations*)
      ; simulated method
        (repeatedly *test-statistic-iterations* #(draw cd))
      ; exact method
      	(combinations (range 0 n) k)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NORMAL DISTRIBUTION 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defvar- inf+ Double/POSITIVE_INFINITY)
(defvar- inf- Double/NEGATIVE_INFINITY)

; NOTE: the pdf and cdf functions require a reflection call. They could be made
; to note reflect by type hinting the d argument:
; (fn [#^cern.jet.randome.tdouble.Normal d v] (.pdf d v))
; for now, I'm skipping this optimization so that more distributions can be boostrapped
; quickly using the extenders map. This can be easily pulled out for each distribution
; later, and appropriate type hints inserted.
(defvar- colt-extenders
	{:pdf (fn [d v] (.pdf d v))
   :cdf (fn [d v] (.cdf d v))
   :draw (fn [#^cern.jet.random.tdouble.AbstractDoubleDistribution d] (.nextDouble d))
   :support (fn [d] [inf-, inf+])})

; bootstrap by extending the colt object to implement this protocol
; future versions could implement a Box-Muller transform in clojure for (draw)
; I think clojure.contrib.probabilities would have an example implementation.
; I'm interested to know how pdf/cdf are implemented in colt...
(extend cern.jet.random.tdouble.Normal Distribution colt-extenders)

(defn normal-distribution
"
	Returns a Normal distribution that implements the
  incanter.distributions.Distribution protocol.

	Arguments:
		mean	The mean of the distribution. One of two parameters
					that summarize the Normal distribution (default 0).
		sd		The standard deviation of the distribution.
				 	The second parameter that describes the Normal (default 1).

  See also:
      Distribution, pdf, cdf, draw, support

  References:
      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html
      http://en.wikipedia.org/wiki/Normal_distribution

  Example:
      (pdf (normal-distribution -2 (sqrt 0.5)) 1.96)
"
	([] (normal-distribution 0 1))
  ([mean sd] (Normal. mean sd (DoubleMersenneTwister.))))

;; distributions created using code and default values from http://github.com/markmfredrickson/incanter/blob/distributions/modules/incanter-core/src/incanter/stats.clj

(extend cern.jet.random.tdouble.Beta Distribution colt-extenders)
(defn beta-distribution
  "Returns a Beta distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    alpha      (default 1)
    beta       (default 1)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html
    http://en.wikipedia.org/wiki/Beta_distribution

  Example:
    (pdf (beta-distribution 1 2) 0.5)"
  ([] (beta-distribution 1 1))
  ([alpha beta] (Beta. alpha beta (DoubleMersenneTwister.))))

(extend cern.jet.random.tdouble.Binomial Distribution colt-extenders)
(defn binomial-distribution
  "Returns a Binomial distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    size       (default 1)
    prob       (default 1/2)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html
    http://en.wikipedia.org/wiki/Binomial_distribution

  Example:
    (pdf (binomial-distribution 20 1/4) 10)"
  ([] (binomial-distribution 1 1/2))
  ([n p] (Binomial. n p (DoubleMersenneTwister.))))

(extend cern.jet.random.tdouble.ChiSquare Distribution colt-extenders)
(defn chisq-distribution
  "Returns a Chi-square distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    df         (default 1)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html
    http://en.wikipedia.org/wiki/Chi_square_distribution

  Example:
    (pdf (chisq-distribution 2) 5.0)"
  ([] (chisq-distribution 1))
  ([df] (ChiSquare. df (DoubleMersenneTwister.))))

(extend cern.jet.random.tdouble.Exponential Distribution colt-extenders)
(defn exponential-distribution
  "Returns a Exponential distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    rate       (default 1)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html
    http://en.wikipedia.org/wiki/Exponential_distribution

  Example:
    (pdf (exponential-distribution 1/2) 2.0)"
  ([] (exponential-distribution 1))
  ([rate] (Exponential. rate (DoubleMersenneTwister.))))

(defrecord F [df1 df2]
  Distribution
  (pdf [d v] (* (/ (gamma (/ (+ df1 df2) 2))
                   (* (gamma (/ df1 2)) (gamma (/ df2 2))))
                (pow (/ df1 df2) (/ df1 2))
                (pow v (- (/ df1 2) 1))
                (pow (+ 1 (* (/ df1 df2) v))
                     (- 0 (/ (+ df1 df2) 2)))))
  (cdf [d v] (regularized-beta ; TODO decide on :lower-tail
              (/ (* df1 v) (+ df2 (* df1 v)))
              (/ df1 2)
              (/ df2 2)))
  (draw [d] nil)                        ; TODO
  (support [d] [0,inf+]))

(defn f-distribution
  "Returns a F-distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    df1        (default 1)
    df2        (default 1)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://en.wikipedia.org/wiki/F_distribution
    http://mathworld.wolfram.com/F-Distribution.html

  Example:
    (pdf (f-distribution 5 2) 1.0)"
  ([] (f-distribution 1 1))
  ([df1 df2] (F. df1 df2)))

(defrecord Gamma-rec [shape rate] ; using defrecord since cdf was not matching up in unittest without switching ":lower-tail"
  Distribution
  (pdf [d v] (.pdf (Gamma. shape rate (DoubleMersenneTwister.)) v))
  (cdf [d v] (Probability/gamma rate shape v)) ; TODO decide on :lower-tail
  (draw [d] (cern.jet.random.tdouble.Gamma/staticNextDouble shape rate))
  (support [d] [0,inf+]))

;(extend cern.jet.random.tdouble.Gamma Distribution colt-extenders)
(defn gamma-distribution
  "Returns a Gamma distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    shape      (default 1)
    rate       (default 1)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html
    http://en.wikipedia.org/wiki/Gamma_distribution

  Example:
    (pdf (gamma-distribution 1 2) 10)"
  ([] (gamma-distribution 1 1))
  ([shape rate] (Gamma-rec. shape rate)))

(extend cern.jet.random.tdouble.NegativeBinomial Distribution colt-extenders)
(defn neg-binomial-distribution
  "Returns a Negative binomial distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    size       (default 10)
    prob       (default 1/2)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html
    http://en.wikipedia.org/wiki/Negative_binomial_distribution

  Example:
    (pdf (neg-binomial-distribution 20 1/2) 10)"
  ([] (neg-binomial-distribution 10 1/2))
  ([size prob] (NegativeBinomial. size prob (DoubleMersenneTwister.))))

(extend cern.jet.random.tdouble.Poisson Distribution colt-extenders)
(defn poisson-distribution
  "Returns a Poisson distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    lambda     (default 1)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html
    http://en.wikipedia.org/wiki/Poisson_distribution

  Example:
    (pdf (poisson-distribution 10) 5)"
  ([] (poisson-distribution 1))
  ([lambda] (Poisson. lambda (DoubleMersenneTwister.))))

(extend cern.jet.random.tdouble.StudentT Distribution colt-extenders)
(defn t-distribution
  "Returns a Student-t distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    df         (default 1)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html
    http://en.wikipedia.org/wiki/Student-t_distribution

  Example:
    (pdf (t-distribution 10) 1.2)"
  ([] (t-distribution 1))
  ([df] (StudentT. df (DoubleMersenneTwister.))))

(extend cern.jet.random.tdouble.DoubleUniform Distribution colt-extenders)
(defn uniform-distribution
  "Returns a Uniform distribution that implements the incanter.distributions.Distribution protocol.

  Arguments:
    min        (default 0)
    max        (default 1)

  See also:
    Distribution, pdf, cdf, draw, support

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html
    http://en.wikipedia.org/wiki/Uniform_distribution

  Example:
    (pdf (uniform-distribution 1.0 10.0) 5)"
  ([] (uniform-distribution 0.0 1.0)) ; since "0 1" not implicitly promoted, otheriwse no matching ctor...
  ([min max] (DoubleUniform. min max (DoubleMersenneTwister.))))
