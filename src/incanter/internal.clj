;;; internal.clj -- Internal functions

;; by David Edgar Liebke http://incanter.org
;; April 19, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
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


;;(derive DoubleMatrix2D ::matrix) ; commented out to track down non-ISeq matrices
(derive Matrix ::matrix)

(defn is-matrix 
  " Test if obj is 'derived' from ::matrix (e.g. class incanter.Matrix)."
  ([obj] (isa? (class obj) ::matrix)))


(defn make-matrix 
  ([data]
   (cond 
     (coll? (first data)) 
      (Matrix. (into-array (map double-array data)))
     (number? (first data)) 
      (Matrix. (double-array data))))
  ([data ncol]
    (cond
      (coll? data)
        (Matrix. (double-array data) ncol)
       (number? data)
        (Matrix. data ncol))) ; data is the number of rows in this case
  ([init-val rows cols]
    (Matrix. rows cols init-val)))




(defmacro #^Matrix transform-with [A op fun]
  `(cond 
    (is-matrix ~A)
      (.assign (.copy ~A) (. DoubleFunctions ~fun))
    (and (coll? ~A) (coll? (first ~A))) 
      (.assign #^Matrix (make-matrix ~A) (. DoubleFunctions ~fun))
    (coll? ~A)
      (map ~op ~A)
    (number? ~A)
      (~op ~A)))
    

(defmacro combine-with [A B op fun]
  `(cond 
    (and (number? ~A) (number? ~B))
       (~op ~A ~B)
    (and (is-matrix ~A) (is-matrix ~B))
      (.assign #^Matrix (.copy #^Matrix ~A) 
               #^Matrix ~B 
               #^DoubleDoubleFunction (. DoubleFunctions ~fun))
    (and (is-matrix ~A) (number? ~B))
      (.assign #^Matrix (.copy #^Matrix ~A) 
               (make-matrix ~B (.rows ~A) (.columns ~A))
               #^DoubleDoubleFunction (. DoubleFunctions ~fun))
               ;;#^DoubleDoubleFunction (. DoubleFunctions (~fun ~B)))
    (and (number? ~A) (is-matrix ~B))
      (.assign #^Matrix (make-matrix ~A (.rows ~B) (.columns ~B)) 
               #^Matrix ~B 
               #^DoubleDoubleFunction (. DoubleFunctions ~fun))
    (and (coll? ~A) (is-matrix ~B))
      (.assign #^Matrix (make-matrix ~A (.columns ~B)) 
               #^Matrix (make-matrix ~B) 
               #^DoubleDoubleFunction (. DoubleFunctions ~fun))
    (and (is-matrix ~A) (coll? ~B))
      (.assign #^Matrix (.copy ~A)
               #^Matrix (make-matrix ~B) 
               #^DoubleDoubleFunction (. DoubleFunctions ~fun))
    (and (coll? ~A) (coll? ~B) (coll? (first ~A))) 
      (.assign (make-matrix ~A) 
               (make-matrix ~B)
               (. DoubleFunctions ~fun))
    (and (coll? ~A) (number? ~B) (coll? (first ~A))) 
      (.assign (make-matrix ~A) 
               (make-matrix ~B) 
               (. DoubleFunctions ~fun))
               ;;(. DoubleFunctions (~fun ~B)))
    (and (number? ~A) (coll? ~B) (coll? (first ~B))) 
      (.assign (make-matrix ~A (.rows ~B) (.columns ~B)) 
               (make-matrix ~B)
               (. DoubleFunctions ~fun))
    (and (coll? ~A) (coll? ~B))
      (map ~op ~A ~B) 
    (and (number? ~A) (coll? ~B)) 
      (map ~op (replicate (count ~B) ~A)  ~B)
    (and (coll? ~A) (number? ~B)) 
      (map ~op ~A (replicate (count ~A) ~B))
  ))


;; PRINT METHOD FOR COLT MATRICES
(defmethod print-method Matrix [o, #^java.io.Writer w]
  (let [formatter (DoubleFormatter. "%1.4f")]
    (do 
      (.setPrintShape formatter false)
      (.write w "[")
      (.write w (.toString formatter o))
      (.write w "]\n"))))


;; PRINT METHOD FOR INCANTER DATASETS
(defmethod print-method :incanter.core/dataset [o, #^java.io.Writer w]
  (do 
    (.write w (str (:column-names o)))
    (.write w "\n")
    (doseq [row (:rows o)]
      (.write w (str (apply vector (map #(get row %) (:column-names o)))))
      (.write w "\n"))))



