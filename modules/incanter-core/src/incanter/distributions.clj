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
	incanter.distributions)

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
(extend-type clojure.lang.ISeq
	Distribution
		(pdf [d v] (get (tabulate d) v 0))
		(cdf [d v] (simple-cdf d v))
		(draw [d] (nth d (rand-int (count d))))
    ; (draw [d n] (repeatedly n #(draw d))) 
		(support [d] (keys (frequencies d))))

; TODO set up a map extension that takes the values as frequencies

