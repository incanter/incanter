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
            (http://acs.lbl.gov/~hoschek/colt/)."
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
  "
  Return the :rows of a dataset, with :index dissoc'd.
  Intended to be used internally time series function to get at data.
  "
  [x]
  (->> x ($ [:not :index]) :rows))

;; Credit: http://stackoverflow.com/questions/3249334/test-whether-a-list-contains-a-specific-value-in-clojure
(defn- in?
  "true if seq contains elm."
  [seq elm]
  (some #(= elm %) seq))

;; Single protocol that convert a value to a Joda value.  These changes
;; have now been merged upsteam in sean-corfield/clj-time, so we'll not have to do it ourselves
;; once he releases.
(defprotocol JodaCoercible
  (to-date-time [x]))

(extend-protocol JodaCoercible
  java.lang.String
  (to-date-time [x] (c/from-string x))
  org.joda.time.DateTime
  (to-date-time [x] x)
  java.lang.Long
  (to-date-time [x] (c/from-long x)))

(defn aligned?
  "Is the :index column identical for all zs."
  [& zs]
  (and
   ;; All have the same length
   (->> zs
        (map nrow)
        (apply =))
   ;; Every index is identical
   (->> zs
        (map (partial $ :index))
        (apply map =)
        (every? identity))))

(defn- row-order
  "Orders a seq of rows by index"
  [z]
  (->> z
      (apply sorted-set-by #(compare (:index %1) (:index %2)))
      (into [])))

(defn- order
  "Order a zoo so that the :index is increasing in time."
  [z]
  (update-in z [:rows] row-order))

(defn within-zoo?
  "Is t between the first and last indices."
  [t z]
  (t/within?
   (t/interval ($ 0 :index z)  ($ (dec (nrow z)) :index z))
   (to-date-time t)))

(defn zoo
  "
  Return the given dataset as a zoo value which is simply a dataset
  that contains an column of clj-time values specified by index-col,
  default :index. That column must contain values that can be coerced
  into Jodas using the TimeCoercible Protocol.
  "
  ([x] (zoo x :index))
  ([x index-col]
     {:pre [(-> x :column-names (in? index-col))]}
     (order
      (to-dataset
       (conj-cols
        (map (fn [{i index-col :as v}]
               (-> v
                   (dissoc index-col)
                   (assoc :index (to-date-time i))))
             (:rows x)))))))

(defn- zoo-simplify
  "Returns a vector if 1 row, else identity"
  [z]
  (let [n (nrow z)]
    (cond
     (= n 1) ($ 0 :all z)
     :else z)))

;; I suspect this could be far better implemented using sel or such.
(defn $$
  "
  This is the equivalent of :: in xts. That is, it slices out
  the timeseries between ind-1 and ind-2. These are any values
  that can be coerced into clj-time values.
  "
  ([ind ts]
     (zoo-simplify ($ :all ($where {:index (to-date-time ind)} ts))))
  ([ind cols ts]
     (zoo-simplify ($ cols ($where {:index (to-date-time ind)} ts))))
  ([ind-1 ind-2 cols ts]
     ($ cols
        ($where (fn [row]
                  (let [i1 (to-date-time ind-1)
                        i2 (to-date-time ind-2)]
                    ;; Extend by 1 milli, to close interval
                    (let [interval (t/extend
                                    (t/interval i1 i2)
                                         (t/millis 1))]
                      (t/within? interval (row :index)))))
                ts))))

(defn- nil-row
  "
  Returns a map with the same keys as x, but with nils for
  each value.  Used for padding zoo in functions that shorten it.
  "
  [x]
  (zipmap (keys x) (repeat nil)))

(defn lag
  "
  Return the timeseries lagged by n units or 1 if not specified.
  No time calculations are made in the index column. The output
  timeseries is of the same length as the input.
  "
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
  "
  Behave as for roll-apply but accept a zoo and a single column
  upon which to roll-apply f. Returns a zoo of the same length
  as input zoo with pre-pended nils
  "
  [f n zoo column & args]
  {:post [(= (nrow zoo) (nrow %))]}
  (col-names
   (conj-cols
    ($ [:index] zoo)
    (concat (take (dec n) (repeat nil))
            (roll-apply f n ($ column zoo))))
   [:index column]))

(defn zoo-row-map-
  "
  Accept a number of aligned zoo object and pass them row-wise into f,
  return a seq of maps of the output of the output.
  f must accept and return maps. The :index column is stripped out before
  f is applied, and then replaced afterwards.
  "
  [f & zs]
  {:pre [(apply aligned? zs)]}
  (->> zs
       (map coredata)
       (apply (fn [& rows] (apply map f rows)))
       (map #(assoc %2 :index %1) ($ :index (first zs)))))

(defn zoo-row-map
  "
  Accept a number of aligned zoo object and pass them row-wise
  into f, return a zoo. f must accept and return maps. The :index
  column is stripped out before f is applied, and then replaced
  afterwards with the :index of the first.
  "
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


(comment
  ;; This is for thinking about at the core level. Not here.
  (defn fill-missing
  "Return a dataset with previously missing values replaced with a default value."
  [d v]
  (to-dataset
   (map merge
        (-> d
            :column-names
            (zipmap (repeat v))
            repeat)
        (:rows d)))))


