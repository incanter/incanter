;;; distributions.clj -- A common distribution protocol with several implmentations.

;; by Mark Fredrickson http://www.markmfredrickson.com
;; May 10, 2010

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG



(ns #^{:doc "Distributions. TODO: provide a useful string" :author "Mark M. Fredrickson"}
	incanter.distributions
  (:import java.util.Random))

(defprotocol Distribution
	"The distribution protocol defines operations on probability distributions.
	 Distributions may be univariate (defined over scalars) or multivariate
	 (defined over vectors). Distributions may be discrete or continuous."
	(pdf [d v] "Returns the value of the probability density/mass function for the d at support v")
	(cdf [d v] "Returns the value of the cumulative distribution function for the distribution at support v")
	(draw [d] "Returns 1 or n samples drawn from d") ; [d n] version removed temporarily
  (support [d] "Returns the support of d in the form of XYZ"))
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

;; Extending some all sequence types to be distributions
(extend-type clojure.lang.Sequential
	Distribution
		(pdf [d v] (get (tabulate d) v 0))
		(cdf [d v] (simple-cdf d v))
		(draw [d] (nth d (rand-int (count d))))
    ; (draw [d n] (repeatedly n #(draw d))) 
		(support [d] (keys (frequencies d))))

(extend-type clojure.lang.PersistentHashSet
	Distribution
  	(pdf [d v] (if (contains? d v) (/ 1 (count d)) 0))
    (cdf [d v] nil) ; should this throw an exception?
    (draw [d] (nth (support d) (rand-int (count d))))
    (support [d] (vec d)))

; TODO set up a map extension that takes the values as frequencies

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
    ; (draw [d n] (repeatedly n #(draw d))) 
		(support [d] (range start end)))

(defn uniform-int
  "Convenience function to a create a uniform distribution over
	a set of integers over the (start, end] interval.
	[] => start = 0, end = 1
	[end] => start = 0
	[start end] => user specified

	This function is preferred to creating a UniformInt object
	directly.
	"
  ([] (uniform-int 1))
  ([end] (uniform-int 0 end))
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

(defn- list-map ;; TODO rewrite this as a lazy operation
  "Instead of providing (first lst) at each iteration, provides the
	entire remaining list."
  [f lst]
  (loop [acc '() lst lst]
    (if (or (nil? lst) (empty? lst)) acc
    	(recur (conj acc (f lst)) (rest lst)))))

(defn- fast-comb-sampler
	"Get a sample from the nCk possible combinations"
  [n k]
  (sort
   (list-map
    (fn [lst] (+ (first lst) (count (filter #(>= % (first lst)) (rest lst)))))
    (map draw (map uniform-int (range (- n k) n))))))

(defrecord Combination [n k u]
  Distribution
  	(pdf [d v] (/ 1 (nCk n k)))
  	(cdf [d v] nil) ; TODO: this requires encoding combinations
  	(draw [d] (fast-comb-sampler n k))
  	(support [d] (map #(decode-combinadic n k %) (range 0 (nCk n k)))))

(defn combination-distribution [n k]
  (assert (>= n k)) (assert (and (<= 0 n) (<= 0 k)))
  (Combination. n k (uniform-int 0 (nCk n k))))
