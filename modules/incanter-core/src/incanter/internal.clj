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



(ns incanter.internal
  (:import (incanter Matrix)
           (cern.colt.matrix.tdouble.algo DoubleFormatter)
           (cern.jet.math.tdouble DoubleFunctions DoubleArithmetic)
           (cern.colt.function.tdouble DoubleDoubleFunction DoubleFunction)))



(derive Matrix ::matrix)

(defn is-matrix
  " Test if obj is 'derived' from ::matrix (e.g. class incanter.Matrix)."
  ([obj] (instance? Matrix obj)))

(def double_arr_type (Class/forName "[D"))

(defn make-matrix
  ([data]
    (cond
     (instance? double_arr_type data)
      (Matrix. ^"[D" data)
     (coll? (first data))
      (Matrix. ^"[[D" (into-array (map double-array data)))
     (number? (first data))
      (Matrix. (double-array data))))
  ([data ncol]
    (cond
      (instance? double_arr_type data)
        (Matrix. ^"[D" data (int ncol))
      (or (coll? data) (.isArray (class data)))
        (Matrix. (double-array data) (int ncol))
       (number? data)
        (Matrix. (int data) (int ncol)))) ; data is the number of rows in this case
  ([init-val rows cols]
    (Matrix. (int rows) (int cols) ^Number init-val)))


(defmacro hint
  "Applies a type hint to a body"
  [type body]
  `~(with-meta body {:tag type}))


(defmacro ^Matrix transform-with [A op fun]
  (let [mA (with-meta (gensym "A") {:tag "Matrix"})
        df (with-meta (gensym "fun") {:tag "DoubleFunction"})]
   `(let [~df (. DoubleFunctions ~fun)]
      (cond
      (is-matrix ~A)
        (let [~mA ~A]
          (.assign (hint "Matrix" (.copy ~mA)) ~df))
      (and (coll? ~A) (coll? (first ~A)))
        (let [~mA (make-matrix ~A)]  
          (.assign ~mA ~df))
      (coll? ~A)
        (map ~op ~A)
      (number? ~A)
        (~op ~A)))))


(defmacro combine-with [A B op fun]
  (let [mA (with-meta (gensym "A") {:tag "Matrix"})
        mB (with-meta (gensym "B") {:tag "Matrix"})
        df (with-meta (gensym "fun") {:tag "DoubleDoubleFunction"})]
   `(let [~df (. DoubleFunctions ~fun)]
      (cond
      (and (number? ~A) (number? ~B))
         (~op ~A ~B)
      (and (is-matrix ~A) (is-matrix ~B))
        (let [~mA ~A
              ~mB ~B] 
          (.assign (hint "Matrix" (.copy ~mA)) ~mB ~df))
      (and (is-matrix ~A) (number? ~B))
        (let [~mA ~A
              ~mB (make-matrix ~B (.rows ~mA) (.columns ~mA))] 
          (.assign (hint "Matrix" (.copy ~mA)) ~mB ~df))
      (and (number? ~A) (is-matrix ~B))
        (let [~mB ~B
              ~mA (make-matrix ~A (.rows ~mB) (.columns ~mB))]
          (.assign ~mA ~mB ~df))
      (and (coll? ~A) (is-matrix ~B))
        (let [~mB ~B
              ~mA (make-matrix ~A (.columns ~mB))]
          (.assign ~mA ~mB ~df))
      (and (is-matrix ~A) (coll? ~B))
        (let [~mA ~A
              ~mB (make-matrix ~B)]
        (.assign (hint "Matrix" (.copy ~mA)) ~mB ~df))
      (and (coll? ~A) (coll? ~B) (coll? (first ~A)))
        (let [~mA (make-matrix ~A)
              ~mB (make-matrix ~B)]
          (.assign ~mA~mB ~df))
      (and (coll? ~A) (number? ~B) (coll? (first ~A)))
        (let [~mA (make-matrix ~A)
              ~mB (make-matrix ~B)]
          (.assign ~mA ~mB ~df))
      (and (number? ~A) (coll? ~B) (coll? (first ~B)))
        (let [~mA (make-matrix ~A (.rows ~B) (.columns ~B))
              ~mB (make-matrix ~B)]
          (.assign ~mA ~mB ~df))        
      (and (coll? ~A) (coll? ~B))
        (map ~op ~A ~B)
      (and (number? ~A) (coll? ~B))
        (map ~op (replicate (count ~B) ~A)  ~B)
      (and (coll? ~A) (number? ~B))
        (map ~op ~A (replicate (count ~A) ~B))
  ))))


;; PRINT METHOD FOR COLT MATRICES
(defmethod print-method Matrix [o, ^java.io.Writer w]
  (let [formatter (DoubleFormatter. "%1.4f")]
    (do
      (.setPrintShape formatter false)
      (.write w "[")
      (.write w (.toString formatter o))
      (.write w "]\n"))))





