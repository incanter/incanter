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
   (:refer clojure :exclude [sort])
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
  (->> x ($ [:not :index]) :rows))

;; Credit: http://stackoverflow.com/questions/3249334/test-whether-a-list-contains-a-specific-value-in-clojure
(defn- in? 
  "true if seq contains elm."
  [seq elm]  
  (some #(= elm %) seq))

;; Single protocol that convert a value to a Joda value
(defprotocol JodaCoercible
  (to-clj-time [x]))

(extend-protocol JodaCoercible
  java.lang.String
  (to-clj-time [x] (c/from-string x))
  org.joda.time.DateTime
  (to-clj-time [x] x)
  java.lang.Long
  (to-clj-time [x] (c/from-long x)))

(defn zoo
  "Return the given dataset as a zoo value which is simply a dataset
that contains an column of clj-time values specified by index-col, default :index.
That column must contain values that can be coerced into Jodas using the TimeCoercible Protocol."
  ([x] (zoo x :index))
  ([x index-col]
     {:pre [(-> x :column-names (in? index-col))]}
     (to-dataset
      (conj-cols
       (map (fn [{i index-col :as v}]
              (-> v
                  (dissoc index-col)
                  (assoc :index (to-clj-time i))))
            (:rows x))))))

(defn $$
  "This is the equivalent of :: in xts.  That is, it slices
 out the timeseries between ind-1 and ind-2.  These are any values that
 can be coerced into clj-time values. No column selection is supported"
  ([ind ts]
     ($where {:index (to-clj-time ind)} ts))
  ([ind-1 ind-2 ts]
     ($where (fn [row]
               (let [i1 (to-clj-time ind-1)
                     i2 (to-clj-time ind-2)]
                   ;; Extend by 1 milli, to close interval
                   (let [interval (t/extend
                                   (t/interval i1 i2)
                                   (t/millis 1))]
                     (t/within? interval (row :index)))))
             ts)))

(defn- nil-row
  "Returns a map with the same keys as x, but with nils for
each value.  Used for padding zoo."
  [x]
  (zipmap (keys x) (repeat nil)))

(defn lag
  "Return the timeseries lagged by n units or 1 if not specified. No time calculations
 are made in the index column.  The output timeseries is of the same length as the input."
  ([z] (lag z 1))
  ([z n]
     {:post [(= (nrow z) (nrow %))]}
     (conj-cols
      (map #(select-keys % [:index]) (:rows z))
      (to-dataset
       (concat
        (take n (repeat (nil-row (-> z coredata first))))
        (->> z
             coredata
             (drop-last n)))))))

(defn zoo-apply
  "Behave as for roll-apply but accept a zoo and a single column upon which to roll-apply f. Returns a zoo of the same length as input zoo with pre-pended nils"
  [f n zoo column & args]
  {:post [(= (nrow zoo) (nrow %))]}
  (col-names
   (conj-cols
    ($ [:index] zoo)
    (concat (take (dec n) (repeat nil))
            (roll-apply f n ($ column zoo))))
   [:index column]))

(defn aligned?
  "Is the :index column identical for all zs."
  [& zs]
  (->> zs
       (map (partial $ :index))
       (apply map =)
       (every? identity)))

(defn zoo-row-map-
  "Accept a number of aligned zoo object and pass them row-wise into f, return a seq
of maps of the output of the output. f must accept and return maps.  The :index column is stripped out before f is applied, and then replaced afterwards."
  [f & zs]
  {:pre [(apply aligned? zs)]}
  (->> zs
       (map coredata)
       (apply (fn [& rows] (apply map f rows)))
       (map #(assoc %2 :index %1) ($ :index (first zs)))))

(defn zoo-row-map
  "Accept a number of aligned zoo object and pass them row-wise into f, return a zoo. f must accept and return maps.  The :index column is stripped out before f is applied, and then replaced afterwards with the :index of the first."
  [f & zs]
  (to-dataset (apply zoo-row-map- f zs)))

(defn- index-only?
  "Returns true if a map contains only an index"
  [x]
  (-> x (dissoc :index) empty?))

(defn zoo-row-map-occupied
  "zoo-row-map- and remove the empties. This returns a seq of maps"
  [f & s]
  (->> s
       (apply zoo-row-map- f)
       (remove index-only?)))

(defn- row-sort
  "Sorts a seq of rows by index"
  [z]
  (-> z
      (apply sorted-set-by #(compare (:index %1) (:index %2)))
      (into [])))

(defn sort
  "Sort a zoo so that the index increasing in time."
  [z]
  (update-in z [:rows] row-sort))

(defn within-zoo?
  "Is t between the first and last indices."
  [t z]
  (t/within? 
   (t/interval (-> z :rows first :index)
               (-> z :rows last :index))
   t))

(comment
  "This is just here for now to demo the zoo functions."
  ;; First create a normal dataset
  (def ds1 (to-dataset [{:index "2012-01-01" :temp 32 :press 100}
                        {:index "2012-01-02" :temp 35 :press 98}
                        {:index "2012-01-03" :temp 30 :press 102}
                        {:index "2012-01-04" :temp 31 :press 103}
                        {:index "2012-01-05" :temp 32 :press 104}
                        {:index "2012-01-06" :temp 33 :press 105}]))

  (def ds2 (to-dataset [{:date "2012-01-01" :temp 32 :press 100}
                        {:date "2012-01-02" :temp 35 :press 98}
                        {:date "2012-01-03" :temp 30 :press 102}
                        {:date "2012-01-04" :temp 31 :press 103}
                        {:date "2012-01-05" :temp 32 :press 104}
                        {:date "2012-01-06" :temp 33 :press 105}]))
  
  ;; Turn it into a zoo.  Clearly we should just mirror the to-dataset
  ;; with a to-zoo function
  (def ts1 (zoo ds1))
  (def ts2 (zoo ds2 :date))

  ;; Slice out easily
  ($$ "2012-01-01" ts1)
  ($$ "2012-01-01" "2012-01-03" ts1)
  ($$ "2012-01-01" (c/from-string "2012-01-03") ts1)  ;; Cool !
  

  ;; Lag easily.  Clearly there should be a k, maybe with negatives for lead.
  (lag ts1)
  (lag ts1 2)

  ;; ordering
  
  ;; Demo zoo-apply
  (zoo-apply #(apply min %) 2 ts1 :temp)

  ;; Demo of aligned?
  (aligned? ts1 (lag ts1))
  
  ;; Demo zoo-row-map
  ;; First a map consuming function
  (defn map-diff
    "Return a map with the different values of v2 - v1"
    [m1 m2]
    (into {} (map (fn [[k1 v1] [k2 v2]]
                    (if (and v1 v2)
                      {k2 (- v1 v2)}
                      {k2 nil}))
                  m1 m2)))
        
  ;; Now use it
  (zoo-row-map- map-diff ts1 ts1)
  (zoo-row-map map-diff ts1 ts1)
  (zoo-row-map map-diff ts1 (lag ts1))
  
  ;; Questions
  ;; Think of general nil handling.  Feels like a maybe monad here.
  ;; Should it be called roll-map not roll-apply to conform to Clojure rather than R naming ?
  ;; Should roll-apply perhaps has (apply f args) etc internall ?

  )


