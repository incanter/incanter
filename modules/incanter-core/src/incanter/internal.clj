;;; internal.clj -- Internal functions

;; by David Edgar Liebke http://incanter.org
;; April 19, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; April 19, 2009: First version



(ns ^{:skip-wiki true}
    incanter.internal
  (:require [clojure.core.matrix :as m])
  (:import (cern.colt.matrix.tdouble.algo DoubleFormatter)
           (cern.jet.math.tdouble DoubleFunctions DoubleArithmetic)
           (cern.colt.function.tdouble DoubleDoubleFunction DoubleFunction)))


(defn is-matrix
  "Test if obj is 'derived' from ::matrix (e.g. class incanter.Matrix)."
  ([obj] (m/matrix? obj)))

(def double_arr_type (Class/forName "[D"))

(defn make-matrix
  ([data]
    (cond
     (number? (first data)) (m/matrix (map vector data))
     :default (m/matrix (map seq data))))
  ([data ncol]
    {:pre [(number? (first data))]}
    (let [chunked  (partition ncol data)]
      (make-matrix chunked)))
  ([init-val rows cols]
     (m/compute-matrix [(int rows) (int cols)]
                       (constantly ^Number init-val))))


(defmacro hint
  "Applies a type hint to a body"
  [type body]
    `~(with-meta body {:tag type}))


(defmacro transform-with
  "Transforms a matrix with a Clatrix function"
  ([A fun]
  `(let [A# ~A]
     (cond
      (m/array? A#) (m/emap ~fun A#)
      (coll? A#)   (map ~fun A#)
      (number? A#) (~fun A#)))))


(defn pass-to-matrix
  "Make each element in coll a row-first matrix else pass it back as-is"
  [coll]
    (map (fn [x]
           (if (and (not (is-matrix x)) (coll? x))
             (make-matrix x)
             x))
         coll))

(defmacro combine-with [A B op fun]
  `(cond
     (and (number? ~A) (number? ~B))
     (~op ~A ~B)
     (and (is-matrix ~A) (is-matrix ~B) (= (first (m/shape ~A)) 1) (= (m/shape ~A) (m/shape ~B)))
     (map ~op ~A ~B)
     (and (not (is-matrix ~A)) (not (is-matrix ~B)))
     (let [a# (if (number? ~A) (replicate (count ~B) ~A) ~A)
           b# (if (number? ~B) (replicate (count ~A) ~B) ~B)]
       (map ~op a# b#))))
