;;; zoo.clj -- Statistics library for Clojure built on the CERN Colt Library

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



(ns ^{:doc "This is a port of Zoo from R in order to create the basis
            of a library for time series data.

            This library is built on Parallel Colt 
            (http://sites.google.com/site/piotrwendykier/software/parallelcolt),
            an extension of the Colt numerics library 
            (http://acs.lbl.gov/~hoschek/colt/).
            "
       :author "David Edgar Liebke"}
  incanter.zoo
  (:import (cern.colt.list.tdouble DoubleArrayList))
  (:use incanter.backstage.zoo-commons
        [incanter.core :only ($ abs plus minus div mult mmult to-list bind-columns
                                gamma pow sqrt diag trans regularized-beta ncol
                                nrow identity-matrix decomp-cholesky decomp-svd
                                matrix length log10 sum sum-of-squares sel matrix?
                                cumulative-sum solve vectorize bind-rows)]
        [incanter.stats :only (mean median)]))

;;;;; Start of ROLL functions ;;;;;

(defn roll-mean
" 
  Returns the unweighted mean of the previous n data points.

  References: 
  http://en.wikipedia.org/wiki/Moving_average#Simple_moving_average
  http://www.learningclojure.com/2010/03/moving-average-of-list.html
"  
  [n coll]
  (map #(/ % n)
       (let [start   (apply + (take n coll))
             diffseq (map   - (drop n coll) coll)]
         (partialsums start diffseq))))

(defn roll-apply 
"
  A generic function for applying a function to rolling window of a collection.

  Arguments:
  f -- function to be applied
  n -- size of rolling window
  coll -- collection of data
"  
  [f n coll]
  (map f (partition n 1 coll)))

;;;;; TODO rolls, optimise each ;;;;;

(defn roll-median
"
  Returns the rolling median of the previous n elements.
"
  [n coll]
  (roll-apply median n coll))

(defn roll-max
"
  Returns the rolling max of the previous n elements.
"  
  [n coll]
  (roll-apply #(apply max %) n coll))

(defn roll-min
"
  Returns the rolling min of the previous n elements.
"  
  [n coll]
  (roll-apply #(apply min %) n coll))

;;;;; End of ROLL functions ;;;;;