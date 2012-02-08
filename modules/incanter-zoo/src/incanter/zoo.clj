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
                                cumulative-sum solve vectorize bind-rows to-dataset
                                conj-cols $where transform-col col-names)]
        [incanter.stats :only (mean median)])
  (:require [clj-time.core :as t]
            [clj-time.coerce :as c]))

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

;;;;; Start of Zoo value ;;;;;;

;; Zoo values are simply datasets where there exists an :index column
;; which is a clj-time (Joda) value.  They are created by passing
;; a dataset to the zoo function which will return the zoo value.

(defn coredata
  "Return the :rows of a dataset, with :index dissoc'd.
Intended to be used internally time series function to get at data."
  [x]
  (map #(dissoc % :index) (:rows x)))

(defn zoo
  "Return the given dataset as a zoo value which is simply a dataset
that contains an :index column of clj-time values.  Hence the input
 must contain an :index column that may be coerced with clj-time.coerce/from-string
 into a clj-time value.  This could be improved a lot."
  [x]
  ($ (:column-names x)
     (to-dataset
      (conj-cols
       (map (fn [{i :index :as v}]
              (assoc v :index (c/from-string i)))
            (:rows x))))))

(defn $$
  "This is the equivalent of :: in xts.  That is, it slices
out the timeseries between ind-1 and ind-2.  These are strings that
can be coerced into clj-time values. No column selection is supported"
  ([ind ts]
     ($where {:index (c/from-string ind)} ts))
  ([ind-1 ind-2 ts]
     ($where (fn [row]
               ;; Extend by 1 milli, to close interval
               (let [interval (t/extend
                               (t/interval (c/from-string ind-1)
                                           (c/from-string ind-2))
                               (t/millis 1))]
                 (t/within? interval (row :index))))
             ts)))

(defn lag
  "Return the timeseries lagged by one unit. No time calculations
are made in the index column. Both incomplete ends are dropped.
Note that if we can guarantee that the timeseries is regular we
can simply lag the index column using time operations."
  [x]
  ($ (:column-names x)
     (conj-cols
      (map #(select-keys % [:index]) (rest (:rows x)))
      (to-dataset 
       (-> x
           coredata
           drop-last)))))

  (defn zoo-apply
    "Behave as for roll-apply but accept a zoo and a column upon which to roll-apply f.
Returns a zoo of the same length as input zoo with pre-pended nils"
    [f n zoo column & args]
    {:post [#(= (nrow zoo) (nrow %))]}
    (col-names
        (conj-cols
         ($ [:index] zoo)
         (concat (take (dec n) (cycle [nil]))
                 (roll-apply f n ($ column zoo))))
      [:index column]))


(comment
  "This is just here for now to demo the zoo functions."
  ;; First create a normal dataset
  (def ds1 (to-dataset [{:index "2012-01-01" :temp 32 :press 100}
                        {:index "2012-01-02" :temp 35 :press 98}
                        {:index "2012-01-03" :temp 30 :press 102}]))
  ;; Turn it into a zoo.  Clearly we should just mirror the to-dataset
  ;; with a to-zoo function
  (def ts1 (zoo ds1))

  ;; Slice out easily
  ($$ "2012-01-01" ts1)
  ($$ "2012-01-01" "2012-01-02" ts1)

  ;; Lag easily.  Clearly there should be a k, maybe with negatives for lead.
  (lag ts1)

  ;; Demo zoo-apply
  (zoo-apply #(apply min %) 2 ts1 :temp)

  ;; Questions
  ;; Should it be called roll-map not roll-apply to conform to Clojure rather than R naming ?
  ;; Should roll-apply perhaps has (apply f args) etc internall ?

  )


