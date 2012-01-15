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
  (:use [incanter.core :only ($ abs plus minus div mult mmult to-list bind-columns
                              gamma pow sqrt diag trans regularized-beta ncol
                              nrow identity-matrix decomp-cholesky decomp-svd
                              matrix length log10 sum sum-of-squares sel matrix?
                              cumulative-sum solve vectorize bind-rows)]
        [incanter.stats :only (mean)]))

;; Credit: http://www.learningclojure.com/2010/03/moving-average-of-list.html
(defn partialsums [start coll]
  (lazy-seq
    (if-let [coll (seq coll)]
          (cons start (partialsums (+ start (first coll)) (rest coll)))
          (list start))))

(defn sliding-window-sma [n coll]
  (map #(/ % n)
       (let [start   (apply + (take n coll))
             diffseq (map   - (drop n coll) coll)]
         (partialsums start diffseq))))

(defn partition-sma [n coll]
  (map mean (partition n 1 coll)))

(defn rollmean
" 
  Returns the unweighted mean of the previous n data points.
  
  Arguments:
  :small-n?  Algorithm is optimised for either small n (e.g. n < 20) 
             or large n (e.g. n > 20). Default is true.

  References: 
  http://en.wikipedia.org/wiki/Moving_average#Simple_moving_average
"  
  [n coll & {:keys [small-n?]
             :or   [small-n? true]}]
  (if :small-n?
    (partition-sma n coll)
    (sliding-window-sma n coll)))

