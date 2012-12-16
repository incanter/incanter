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
  (:require [clatrix.core :as clx])
  (:import (incanter Matrix)
           (cern.colt.matrix.tdouble.algo DoubleFormatter)
           (cern.jet.math.tdouble DoubleFunctions DoubleArithmetic)
           (cern.colt.function.tdouble DoubleDoubleFunction DoubleFunction)))



(derive Matrix ::matrix)

(defn is-matrix
  " Test if obj is 'derived' from ::matrix (e.g. class incanter.Matrix)."
  ([obj] (clx/matrix? obj)))

(def double_arr_type (Class/forName "[D"))

(defn make-matrix
  ([data]
    (cond
     (coll? (first data))
      (clx/matrix data)
     (number? (first data))
      (clx/matrix (map vector data))))
  ([data ncol]
   {:pre [(number? (first data))]}
   (let [chunked  (partition ncol data)]
     (make-matrix chunked)))
  ([init-val rows cols]
    (clx/constant (int rows) (int cols) ^Number init-val)))


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

(defmacro combine-with [A B op fun] ;; TODO too much hackery
  (let [a (gensym)
        b (gensym)]
    `(let [~a (if (and (is-matrix ~A) (= [1 1] (clx/size ~A))) 
                (clx/get ~A 0 0)
                ~A)
           ~b (if (and (is-matrix ~B) (= [1 1] (clx/size ~B))) 
                (clx/get ~B 0 0)
                ~B)]
       (cond
         (and (number? ~a) (number? ~b))
         (~op ~a ~b)
         (and (is-matrix ~a) (is-matrix ~b)  ;; TODO clean for use during reduce
              ;(not (or (clx/vector? ~a) (clx/vector? ~b)))
              (= 1 (clx/nrows ~a))
              (= (clx/size ~a) (clx/size ~b)))
         (make-matrix (vector (map ~op ~a ~b))) 
         (and (is-matrix ~a) (is-matrix ~b))
         (~fun ~a ~b)
         (and (is-matrix ~a) (number? ~b))
         (~fun ~a ~b)
         (and (number? ~a) (is-matrix ~b))
         (~fun ~a ~b)
         (and (coll? ~a) (is-matrix ~b))
         (~fun (make-matrix ~a (clx/nrows ~b)) ~b) 
         (and (is-matrix ~a) (coll? ~b))
         (~fun ~a (make-matrix ~b))
         (and (coll? ~a) (coll? ~b) (coll? (first ~a)))
         (~fun (make-matrix ~a) (make-matrix ~b))
         (and (coll? ~a) (number? ~b) (coll? (first ~a)))
         (~fun (make-matrix ~a) ~b)
         (and (number? ~a) (coll? ~b) (coll? (first ~b)))
         (~fun ~a (make-matrix ~b))
         (and (coll? ~a) (coll? ~b))
         (make-matrix (map ~op ~a ~b)) 
         (and (number? ~a) (coll? ~b))
         (make-matrix (map ~op (replicate (count ~b) ~a)  ~b)) 
         (and (coll? ~a) (number? ~b))
         (make-matrix (map ~op ~a (replicate (count ~a) ~b)))))))

;; PRINT METHOD FOR COLT MATRICES
(defmethod print-method Matrix [o, ^java.io.Writer w]
  (let [formatter (DoubleFormatter. "%1.4f")]
    (do
      (.setPrintShape formatter false)
      (.write w "[")
      (.write w (.toString formatter o))
      (.write w "]\n"))))





