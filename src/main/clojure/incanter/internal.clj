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
  (:gen-class)
  (:import (incanter Matrix)
           (cern.colt.matrix.tdouble.algo DoubleFormatter)
           (cern.jet.math.tdouble DoubleFunctions DoubleArithmetic)
           (cern.colt.function.tdouble DoubleDoubleFunction DoubleFunction))
  (:use [clojure.contrib.monads 
	 :only [m-lift m-bind m-result defmonad with-monad]]))


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


;;TODO: this doesn't have to be macros if the m-lift is not a macro
(defn lift-apply [f args]
  ((m-lift 1 (partial apply f)) args))

(defn any? [p c]
  (if (some p c)
    true
    false))

(defn nil-coll? [mv] 
  (or (nil? mv) 
      (and (coll? mv)
	   (any? nil? mv))))

;;TODO: refactor based on email exchange with Konrad.
(defmonad maybe-seq-m 
   [m-zero   nil
    m-result (fn m-result-maybe [v] v)
    m-bind   (fn m-bind-maybe [mv f]
               (if (nil-coll? mv)
		 nil 
		 (f mv)))
    m-plus   (fn m-plus-maybe [& mvs]
	       (first (drop-while nil-coll? mvs)))])

;;TODO: this doesn't have to be macros if the m-lift is not a macro might be able to do eval trickery
(defn maybe? [pred & args]
 (or 
   (with-monad maybe-seq-m
     ((m-lift 1 (partial apply pred)) args))
  false))
;;      (lift-apply pred args)))

;;TODO: combine into one tree comp that can figure out if it should call one branch function on each leave, or each branch function on all leaves.
(defn tree-comp-each [root branch & leaves]
 (apply 
  root (map branch leaves)))

(defn tree-comp  [root & branches] 
  (fn [& leaves] 
    (with-monad maybe-seq-m
    ((m-lift 1 (partial apply root))
      (map 
       (fn [branch] 
	 (if (ifn? branch)
	   ((m-lift 1 (partial apply branch)) leaves)
	   branch)) 
       branches))))) 


(defn all [& fs] 
  (apply tree-comp 
	 (fn [& xs] 
	   (eval (conj xs 'and))) 
	 fs))

(def both all)
(defn either [f g] (tree-comp (fn [a b] (or a b)) f g))  
(defn neither [f g] (tree-comp (fn [a b] (not (or a b))) f g))  

(defn cond-comp [p a b]
  (fn [x] 
    (if (p x) (a x) (b x))))

(defn makekey [ks obs] 
  (apply str (map #(% obs) ks)))

(defn vector-comp 
"compose a list of functions such that they are each applied to the arguments to which the composed function is applied and the results of each application are inserted as slots in a vector."
[& fns]
  (fn [& args] 
    (into [] 
	  (map #(apply % args) fns))))

(defn first-match [pred coll]
  (first (filter pred coll)))

(defn seqify [x]
  (let [colled (if (coll? x) x [x])]
     (seq colled)))

(defn seqable? [x] (or (seq? x) (string? x)))

(defn nil-or-empty [coll]
  (or (nil? coll) 
      (and (seqable? coll) (empty? coll))))

(defn all-present? [ks m] 
(not-any? nil-or-empty (map #(get m %) ks)))

;;TODO: all the safe stuff needs refacotring and generalization
;;should form a coherent system with above monadic compositions.
(defn safe
  "for safe division - returns zero for division by zero"
  [f n d] 
  (if (= d 0) 
    0.0 
    (float (f n d))))

(defn safe-max [x]
  (let [res (filter (complement nil?) (seqify x))]
    (apply max (if (empty? res) 0 res))))

(defn safe-max-date [x]
  (let [res (filter (complement nil?) (seqify x))]
    (last (sort res))))
     
(defn threshold-to [threshold x]
  (safe-max (conj (seqify x) threshold)))

(defn seqable? [x] 
  (or (coll? x) (string? x)))

(defn nil-or-empty? 
[coll] 
  (or (nil? coll) 
      (and (seqable? coll) 
	   (empty? coll))))

(defn all-present? [ks m] 
  (not-any? nil-or-empty? 
	    (map #(get m %) ks)))

(defn r-acc [look? extract x]
  "usage
     - (r-acc map? :a [:a 1 2 3 {:a 1 :b [:a 2 3] :c {:b 1 :a 2}} 3 #{:a :b 1} {:b {:a 3}}])
       (1 2 3)"
  (letfn [(children [coll] (if (map? coll) (vals coll) coll))]
    (filter (complement nil-or-empty?) 
	    (map extract 
		 (filter look? 
			 (tree-seq coll? children x))))))

(defn duplicates? [x]
  (not (= (count (distinct x))
	  (count x))))