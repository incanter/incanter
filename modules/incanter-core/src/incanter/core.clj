;;; core.clj -- Core functions built on the CERN Colt Library

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



(ns ^{:doc "This is the core numerics library for Incanter.
            It provides functions for vector- and matrix-based
            mathematical operations and the core data manipulation
            functions for Incanter.

            This library is built on Clatrix (https://github.com/tel/clatrix)
            and Parallel Colt
            (http://sites.google.com/site/piotrwendykier/software/parallelcolt)
            an extension of the Colt numerics library
            (http://acs.lbl.gov/~hoschek/colt/).
            "
       :author "David Edgar Liebke"}

  incanter.core

  (:refer-clojure :exclude [update])
  (:use [incanter internal]
        [incanter.infix :only (infix-to-prefix defop)]
        [clojure.set :only (difference)]
        [clojure.pprint :only (print-table)])
  (:require [clatrix.core :as clx])
  (:import (clatrix.core Matrix)
           (cern.jet.math.tdouble DoubleArithmetic)
           (cern.colt.list.tdouble DoubleArrayList)
           (cern.jet.stat.tdouble DoubleDescriptive Gamma)
           (javax.swing JTable JScrollPane JFrame)
           (java.util Vector)))


(def ^{:dynamic true
       :doc "This variable is bound to a dataset when the with-data macro is used.
              functions like $ and $where can use $data as a default argument."}
     $data nil)

(defrecord Dataset [column-names rows])
(derive incanter.core.Dataset ::dataset)
(derive clatrix.core.Matrix ::matrix)

(defn matrix
  "
  Returns an instance of an incanter.Matrix, which is an extension of
  Clatrix matrix that implements the Clojure interface
  clojure.lang.ISeq. Therefore Clojure sequence operations can be
  applied to matrices. A matrix consists of a sequence of rows, where
  each row is a one-dimensional row matrix. One-dimensional matrices are
  in turn, sequences of numbers. Equivalent to R's matrix function.

  Examples:
    (def A (matrix [[1 2 3] [4 5 6] [7 8 9]])) ; produces a 3x3 matrix
    (def A2 (matrix [1 2 3 4 5 6 7 8 9] 3)) ; produces the same 3x3 matrix
    (def B (matrix [1 2 3 4 5 6 7 8 9])) ; produces a 9x1 column vector

    (first A) ; produces a row matrix [1 2 3]
    (rest A) ; produces a sub matrix [[4 5 6] [7 8 9]]
    (first (first A)) ; produces 1.0
    (rest (first A)) ; produces a row matrix [2 3]

    ; since (plus row1 row2) adds the two rows element-by-element
    (reduce plus A) ; produces the sums of the columns

    ; and since (sum row1) sums the elements of the row
    (map sum A) ; produces the sums of the rows

    ; you can filter the rows using Clojure's filter function
    (filter #(> (nth % 1) 4) A) ; returns the rows where the second column is greater than 4.
  "
  ([data]
    (make-matrix data))

  ([data ^Integer ncol]
    (make-matrix data ncol))

  ([init-val ^Integer rows ^Integer cols]
    (make-matrix init-val rows cols)))


(defn matrix?
  "Test if obj is 'derived' clatrix.core.Matrix"
  ([obj] (is-matrix obj)))

(defn dataset?
  "Determines if obj is of type incanter.core.Dataset."
  ([obj] (instance? Dataset obj)))

(defn ^Integer nrow
  "Returns the number of rows in the given matrix. Equivalent to R's nrow function."
  ([mat]
    (cond
    (matrix? mat) (first (clx/size ^clatrix.core.Matrix mat))
    (dataset? mat) (count (:rows mat))
    (coll? mat) (count mat))))


(defn ^Integer ncol
  "Returns the number of columns in the given matrix. Equivalent to R's ncol function."
  ([mat]
   (cond
    (matrix? mat) (last (clx/size ^clatrix.core.Matrix mat))
    (dataset? mat) (count (:column-names mat))
    (coll? mat) 1 )))

(defn dim
  "Returns a vector with the number of rows and columns of the given matrix."
  ([mat]
   [(nrow mat) (ncol mat)]))



(defn identity-matrix
  "
  Returns an n-by-n identity matrix.

  Examples:
  (identity-matrix 4)
  "

  ([^Integer n] (clx/eye n)))


(defn diag
  "If given a matrix, diag returns a sequence of its diagonal elements.
  If given a sequence, it returns a matrix with the sequence's elements
  on its diagonal. Equivalent to R's diag function.

  Examples:
  (diag [1 2 3 4])

  (def A (matrix [[1 2 3]
  [4 5 6]
  [7 8 9]]))
  (diag A)"
  [m]
  (clx/diag m))


(defn ^Matrix trans
  "
  Returns the transpose of the given matrix. Equivalent to R's t function

  Examples:
    (def A (matrix [[1 2 3]
                    [4 5 6]
                    [7 8 9]]))
    (trans A)
  "
  ([mat]
   (if (matrix? mat)
     (clx/t mat)
     (clx/t (matrix mat)))))



(defn- except-for
  "
  Returns a lazy list of numbers ranging from 0 to n, except for the given exceptions.
  Examples:

    (except-for 10 3)
    (except-for 10 [5 7])
  "
  ([n exceptions]
    (let [except (if (coll? exceptions) exceptions [exceptions])]
      (for [i (range n) :when (reduce #(and %1 %2) (map #(not= i %) except))] i))))



(defmulti sel
  "
  Returns an element or subset of the given matrix, dataset, or list.
  If the column or row is specified as an atomic object (index or name), then
  the result will be returned as a list (only values from selected column or row).

  Argument:
    a matrix object, dataset, or list.

  Options:
    :rows (default true)
      returns all rows by default, can pass a row index or sequence of row indices
    :cols (default true)
      returns all columns by default, can pass a column index or sequence of column indices
    :except-rows (default nil) can pass a row index or sequence of row indices to exclude
    :except-cols (default nil) can pass a column index or sequence of column indices to exclude
    :filter-fn (default nil)
      a function can be provided to filter the rows of the matrix

  Examples:
    (use 'incanter.datasets)
    (def iris (to-matrix (get-dataset :iris)))
    (sel iris 0 0) ; first element
    (sel iris :rows 0 :cols 0) ; also first element
    (sel iris :cols 0) ; first column of all rows
    (sel iris :cols [0 2]) ; first and third column of all rows
    (sel iris :rows (range 10) :cols (range 2)) ; first two columns of the first 10 rows
    (sel iris :rows (range 10)) ; all columns of the first 10 rows

    ;; exclude rows or columns
    (sel iris :except-rows (range 10)) ; all columns of all but the first 10 rows
    (sel iris :except-cols 1) ; all columns except the second

    ;; return only the first 10 even rows
    (sel iris :rows (range 10) :filter-fn #(even? (int (nth % 0))))
    ;; select rows where distance (third column) is greater than 50
    (sel iris :filter #(> (nth % 2) 4))

    ;; examples with datasets
    (use 'incanter.datasets)
    (def us-arrests (get-dataset :us-arrests))
    (sel us-arrests :cols \"State\")
    (sel us-arrests :cols :State)

    (sel us-arrests :cols [\"State\" \"Murder\"])
    (sel us-arrests :cols [:State :Murder])
  "

  (fn [mat & options] [(type mat) (keyword? (first options))]))


(defmethod sel [nil false] [])
(defmethod sel [nil true] [])

(defmethod sel [java.util.List false]
  ([^java.util.List lst rows cols]
    (sel lst :rows rows :cols cols)))

(defmethod sel [java.util.List true]
  ([^java.util.List lst & {:keys [rows cols except-rows except-cols filter-fn all]}]
    (let [rows (cond
                  rows rows
                  except-rows (except-for (nrow lst) except-rows)
                  :else true)
          cols (cond
                  cols cols
                  except-cols (except-for (nrow (first lst)) except-cols)
                  all all
                  :else true)
          lst (if (nil? filter-fn) lst (filter filter-fn lst))
          all-rows? (or (true? rows) (= rows :all) all)
          all-cols? (or (true? cols) (= cols :all) (= all :all))]
      (cond
        (and (number? rows) (number? cols))
          (nth (nth lst rows) cols)
        (and all-rows? (coll? cols))
          (map (fn [r] (map #(nth r %) cols)) lst)
        (and all-rows? (number? cols))
          (map #(nth % cols) lst)
        (and (coll? rows) (number? cols))
          (map #(nth % cols)
               (map #(nth lst %) rows))
        (and (coll? rows) all-cols?)
          (map #(nth lst %) rows)
        (and (number? rows) all-cols?)
          (nth lst rows)
        (and (number? rows) (coll? cols))
          (map #(nth (nth lst rows) %) cols)
        (and (coll? rows) (coll? cols))
          (map (fn [r] (map #(nth r %) cols))
               (map #(nth lst %) rows))
        (and all-rows? all-cols?)
          lst))))

(defmethod sel [clatrix.core.Matrix false]
  ([^clatrix.core.Matrix mat rows columns]
   (let [rws (if (number? rows) [rows] rows)
         cols (if (number? columns) [columns] columns)
         all-rows? (or (true? rws) (= rws :all))
         all-cols? (or (true? cols) (= cols :all))]
    (cond
      (and (number? rows) (number? columns))
        (clx/get mat rows columns)
      (and all-rows? (coll? cols))
        (clx/get mat (range (nrow mat)) cols)
      (and (coll? rws) all-cols?)
        (clx/get mat rws (range (ncol mat)))
      (and (coll? rws) (coll? cols))
        (clx/get mat rws cols)
      (and all-rows? all-cols?)
        mat))))

(defmethod sel [clatrix.core.Matrix true]
  ([mat & {:keys [rows cols except-rows except-cols filter-fn all]}]
   (let [rows (cond
                rows rows
                except-rows (except-for (nrow mat) except-rows)
                :else true)
         cols (cond
                cols cols
                except-cols (except-for (ncol mat) except-cols)
                all all
                :else true)
         mat (if (nil? filter-fn) mat (matrix (filter filter-fn mat)))
         all-rows? (or (true? rows) (= rows :all) all)
         all-cols? (or (true? cols) (= cols :all) (= all :all))]
     (cond
       (and (number? rows) (number? cols))
         (clx/get mat rows cols)
       (and all-rows? (coll? cols))
         (clx/get mat (range (nrow mat)) cols)
       (and all-rows? (number? cols))
         (clx/get mat (range (nrow mat)) [cols])
       (and (coll? rows) (number? cols))
         (clx/get mat rows [cols])
       (and (coll? rows) all-cols?)
         (clx/get mat rows (range (ncol mat)))
       (and (number? rows) all-cols?)
         (clx/get mat [rows] (range (ncol mat)))
       (and (number? rows) (coll? cols))
         (clx/get mat [rows] cols)
       (and (coll? rows) (coll? cols))
         (clx/get mat rows cols)
       (and all-rows? all-cols?)
         mat))))

(defn bind-rows
  "
  Returns the matrix resulting from concatenating the given matrices
  and/or sequences by their rows. Equivalent to R's rbind.

  Examples:
  (def A (matrix [[1 2 3]
                  [4 5 6]
                  [7 8 9]]))

  (def B (matrix [[10 11 12]
                  [13 14 15]]))

  (bind-rows A B)

  (bind-rows [1 2 3 4] [5 6 7 8])
  "

  ([& args]
    (reduce
      (fn [A B]
        (cond
          (nil? (seq A))
            B
          (nil? (seq B))
            A
          (or (coll? A) (coll? B))
            (conj (if (or (matrix? A) (matrix? (first A)))
                      A
                      (matrix A (count A)))
                  (if (or (matrix? B) (matrix? (first B)))
                      B
                      (matrix B (count B))))
          :else
            (throw (Exception. "Incompatible types"))))
      args)))



(defn bind-columns
  "
  Returns the matrix resulting from concatenating the given matrices
  and/or sequences by their columns. Equivalent to R's cbind.

  Examples:
  (def A (matrix [[1 2 3]
                  [4 5 6]
                  [7 8 9]]))

  (def B (matrix [10 11 12]))

  (bind-columns A B)

  (bind-columns [1 2 3 4] [5 6 7 8])
  "
  [& args]
  (reduce clx/hstack (pass-to-matrix args)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MATH FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn plus
  "
  Performs element-by-element addition on multiple matrices, sequences
  and/or numbers. Equivalent to R's + operator.

  Examples:

  (def A (matrix [[1 2 3]
                  [4 5 6]
                  [7 8 9]]))
  (plus A A A)
  (plus A 2)
  (plus 2 A)
  (plus [1 2 3] [1 2 3])
  (plus [1 2 3] 2)
  (plus 2 [1 2 3])
  "
  [& args] (reduce clx/+ (pass-to-matrix args)))


(defn minus
  "
  Performs element-by-element subtraction on multiple matrices, sequences
  and/or numbers. If only a single argument is provided, returns the negative
  of the given matrix, sequence, or number. Equivalent to R's - operator.

  Examples:
    (def A (matrix [[1 2 3]
                    [4 5 6]
                    [7 8 9]]))
    (minus A)
    (minus A A A)
    (minus A 2)
    (minus 2 A)
    (minus [1 2 3] [1 2 3])
    (minus [1 2 3] 2)
    (minus 2 [1 2 3])
    (minus [1 2 3])
  "
  [& args] (apply clx/- (pass-to-matrix args)))


(defn mult
  "
  Performs element-by-element multiplication on multiple matrices, sequences
  and/or numbers. Equivalent to R's * operator.

  Examples:

  (def A (matrix [[1 2 3]
                  [4 5 6]
                  [7 8 9]]))
  (mult A A A)
  (mult A 2)
  (mult 2 A)
  (mult [1 2 3] [1 2 3])
  (mult [1 2 3] 2)
  (mult 2 [1 2 3])
  "
  [& args] (reduce clx/mult (pass-to-matrix args))) ;; TODO: clean, special case for (reduce mult A)


(defn div
  "
  Performs element-by-element division on multiple matrices, sequences
  and/or numbers. Equivalent to R's / operator.

  Examples:

  (def A (matrix [[1 2 3]
                  [4 5 6]
                  [7 8 9]]))
  (div A A A)
  (div A 2)
  (div 2 A)
  (div [1 2 3] [1 2 3])
  (div [1 2 3] 2)
  (div 2 [1 2 3])

  (div [1 2 3]) ; returns [1 1/2 13]
  "
  ([& args]
    (if (= (count args) 1)
      (clx/div (double 1) (first args))
      (reduce clx/div (pass-to-matrix args)))))

(defn safe-div  ;; TODO modify to work with matrices ?
  "
  DivideByZero safe alternative to clojures / function,
  detects divide by zero and returns Infinity, -Infinity or NaN as appropriate.
  Note: Does not work on matrices, only primitive types
  "
  ([x] (safe-div 1 x))
  ([x y]
    (try (/ x y)
         (catch ArithmeticException _
         (cond (> x 0)   Double/POSITIVE_INFINITY
               (zero? x) Double/NaN
                  :else     Double/NEGATIVE_INFINITY))))
  ([x y & more]
     (reduce safe-div (safe-div x y) more)))

(declare to-list dataset col-names)

(defn- mapping-helper [func args]
  (reduce (fn [A B]
            (cond
              (number? A) (func A B)
              (matrix? A) (let [m (matrix (mapping-helper func (list (to-list A) B)))]
                            (if (clx/row? A)
                              (trans m)
                              m))
              (dataset? A) (dataset (col-names A)
                                    (mapping-helper func (list (to-list A) B)))
              (and (coll? A) (coll? (first A)))
              (map (fn [a] (map #(func %1 B) a)) A)
              (coll? A) (map #(func %1 B) A)))
          args))

(defn pow  ;; TODO use jblas and fix meta
  "
  This is an element-by-element exponent function, raising the first argument
  by the exponents in the remaining arguments. Equivalent to R's ^ operator.
  "
  [& args]
  (mapping-helper #(Math/pow %1 %2) args))

(defn atan2 ;; TODO fix meta
  "
  Returns the atan2 of the elements in the given matrices, sequences or numbers.
  Equivalent to R's atan2 function.
  "
  [& args]
  (mapping-helper #(Math/atan2 %1 %2) args))

(defn sqrt
  "
  Returns the square-root of the elements in the given matrix, sequence or number.
  Equivalent to R's sqrt function.
  "
  [A] (transform-with A #(Math/sqrt %) clx/sqrt!))


(defn sq
  "
  Returns the square of the elements in the given matrix, sequence or number.
  Equivalent to R's sq function.
  "
  ([A] (mult A A)))


(defn log
  "
  Returns the natural log of the elements in the given matrix, sequence or number.
  Equivalent to R's log function.
  "
  ([A] (transform-with A #(Math/log %) clx/log!)))


(defn log2
  "
  Returns the log base 2 of the elements in the given matrix, sequence or number.
  Equivalent to R's log2 function.
  "
  ([A] (transform-with A #(/ (Math/log %) (Math/log 2))
                       #(div (clx/log! %)
                             (matrix (Math/log 2) (nrow %) (ncol %))))))


(defn log10
  "
  Returns the log base 10 of the elements in the given matrix, sequence or number.
  Equivalent to R's log10 function.
  "
  ([A] (transform-with A #(Math/log10 %) clx/log10!)))


(defn exp
  "
  Returns the exponential of the elements in the given matrix, sequence or number.
  Equivalent to R's exp function."
  ([A] (transform-with A #(Math/exp %) clx/exp!)))


(defn abs
  "
  Returns the absolute value of the elements in the given matrix, sequence or number.
  Equivalent to R's abs function.
  "
  ([A] (transform-with A #(Math/abs (double %)) clx/abs!)))


(defn sin
  "
  Returns the sine of the elements in the given matrix, sequence or number.
  Equivalent to R's sin function.
  "
  ([A] (transform-with A #(Math/sin %) clx/sin!)))


(defn asin
  "
  Returns the arc sine of the elements in the given matrix, sequence or number.
  Equivalent to R's asin function.
  "
  ([A] (transform-with A #(Math/asin %) clx/asin!)))


(defn cos
  "
  Returns the cosine of the elements in the given matrix, sequence or number.
  Equivalent to R's cos function.
  "
  ([A] (transform-with A #(Math/cos %) clx/cos!)))


(defn acos
  "
  Returns the arc cosine of the elements in the given matrix, sequence or number.
  Equivalent to R's acos function."
  ([A] (transform-with A #(Math/acos %) clx/acos!)))


(defn tan
  "
  Returns the tangent of the elements in the given matrix, sequence or number.
  Equivalent to R's tan function.
  "
  ([A] (transform-with A #(Math/tan %) clx/tan!)))


(defn atan
  "
  Returns the arc tangent of the elements in the given matrix, sequence or number.
  Equivalent to R's atan function.
  "
  ([A] (transform-with A #(Math/atan %) clx/atan!)))


(defn factorial
  "
  Returns the factorial of k (k must be a positive integer). Equivalent to R's
  factorial function.

  Examples:
    (factorial 6)

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/math/tdouble/DoubleArithmetic.html
    http://en.wikipedia.org/wiki/Factorial

  "
  ([^Integer k] {:pre [(and (number? k) (not (neg? k)))]} (DoubleArithmetic/factorial k)))



(defn choose
  "
  Returns number of k-combinations (each of size k) from a set S with
  n elements (size n), which is the binomial coefficient (also known
  as the 'choose function') [wikipedia]
        choose = n!/(k!(n - k)!)

  Equivalent to R's choose function.

  Examples:
    (choose 25 6) ; => 177,100

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/math/tdouble/DoubleArithmetic.html
    http://en.wikipedia.org/wiki/Combination
  "
  ([n k] (DoubleArithmetic/binomial (double n) (long k))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MATRIX FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defmulti to-list
  "
  Returns a list-of-lists if the given matrix is two-dimensional
  and a flat list if the matrix is one-dimensional.
  "
  type)

(defmethod to-list ::matrix
  ([^clatrix.core.Matrix mat]
    (clx/as-vec mat)))


(defmethod to-list ::dataset
  [data]
  (map (fn [row] (map (fn [col] (row col))
                      (:column-names data)))
       (:rows data)))


(defmethod to-list :default [s] s)

(defmethod to-list nil [s] nil)

(defmulti to-vect
  "
  Returns a vector-of-vectors if the given matrix is two-dimensional
  and a flat vector if the matrix is one-dimensional. This is a bit
  slower than the to-list function
  "
  type)

(defmethod to-vect ::matrix
  ([^clatrix.core.Matrix mat]
   (clx/as-vec mat)))

(defmethod to-vect ::dataset
  [data]
  (into [] (map (fn [row]
                  (into [] (map (fn [col] (row col))
                                (:column-names data))))
                (:rows data))))

(defmethod to-vect nil [s] nil)

(defmethod to-vect :default [s] s)

(defn ^Matrix copy
  "Returns a copy of the given matrix."
  ([^Matrix mat] (clx/matrix (clx/dotom .copy mat) nil)))

(defn mmult
  "
  Returns the matrix resulting from the matrix multiplication of the
  the given arguments. Equivalent to R's %*% operator.

  Examples:

    (def A (matrix [[1 2 3]
                    [4 5 6]
                    [7 8 9]]))
    (mmult A (trans A))
    (mmult A (trans A) A)

  References:
    http://en.wikipedia.org/wiki/Matrix_multiplication
  "
  ([& args]
    (reduce (fn [A B] (clx/* A B)) (pass-to-matrix args))))


(defn kronecker
  "
  Returns the Kronecker product of the given arguments.

  Examples:

    (def x (matrix (range 6) 2))
    (def y (matrix (range 4) 2))
    (kronecker 4 x)
    (kronecker x 4)
    (kronecker x y)
  "
  ([& args]
    (reduce (fn [A B]
              (let [a (cond
                        (matrix? A) A
                        (number? A) (matrix [A])
                        :else (matrix A))
                    b (cond
                        (matrix? B) B
                        (number? B) (matrix [B])
                        :else (matrix B))
                    rows (* (nrow a) (nrow b))
                    cols (* (ncol a) (ncol b))]
                (apply bind-rows
                       (for [i (range (nrow a))]
                         (apply bind-columns
                                (for [j (range (ncol a))]
                                  (mult (sel a i j) b)))))))
            args)))

(defn solve
  "
  Returns a matrix solution if A is square, least squares solution otherwise.
  Equivalent to R's solve function.

  Examples:
    (solve (matrix [[2 0 0] [0 2 0] [0 0 2]]))

  References:
    http://en.wikipedia.org/wiki/Matrix_inverse
  "
([^Matrix A ^Matrix B]
  (clx/solve A B))
([^Matrix A]
  (clx/i A)))

(defn det
  "
  Returns the determinant of the given matrix. Equivalent
  to R's det function.

  References:
    http://en.wikipedia.org/wiki/LU_decomposition
  "
  ([mat] (clx/det mat)))


(defn trace
  "
  Returns the trace of the given matrix.

  References:
  http://en.wikipedia.org/wiki/Matrix_trace
  "
  [mat] (clx/trace mat))


(defn vectorize
  "
  Returns the vectorization (i.e. vec) of the given matrix.
  The vectorization of an m-by-n matrix A, denoted by vec(A)
  is the m*n-by-1 column vector obtain by stacking the columns
  of the matrix A on top of one another.

  For instance:
    (= (vectorize (matrix [[a b] [c d]])) (matrix [a c b d]))

  Examples:
    (def A (matrix [[1 2] [3 4]]))
    (vectorize A)

  References:
    http://en.wikipedia.org/wiki/Vectorization_(mathematics)
  "
  ([mat]
    (mapcat identity (trans mat))))


(defn half-vectorize
  "
  Returns the half-vectorization (i.e. vech) of the given matrix.
  The half-vectorization, vech(A), of a symmetric nxn matrix A
  is the n(n+1)/2 x 1 column vector obtained by vectorizing only
  the upper triangular part of A.

  For instance:
    (= (half-vectorize (matrix [[a b] [b d]])) (matrix [a b d]))

  Examples:
    (def A (matrix [[1 2] [2 4]]))
    (half-vectorize A)

  References:
    http://en.wikipedia.org/wiki/Vectorization_(mathematics)
  "
  ([mat]
   (for [j (range (nrow mat)) i (range j (nrow mat))] (sel mat i j))))



(defn sum-of-squares
  "Returns the sum-of-squares of the given sequence."
  ([x]
    (let [xx (if (or (nil? x) (empty? x)) [0] (to-vect x))]
      (DoubleDescriptive/sumOfSquares (DoubleArrayList. (double-array xx))))))


(defn sum
  "Returns the sum of the given sequence."
  ([x]
    (let [xx (if (or (nil? x) (empty? x)) [0] (to-vect x))]
      (DoubleDescriptive/sum (DoubleArrayList. (double-array xx))))))


(defn prod
  "Returns the product of the given sequence."
  ([x]
    (let [xx (if (or (nil? x) (empty? x))
               [1]
               (to-list x))]
      (DoubleDescriptive/product (DoubleArrayList. (double-array xx))))))



(defn cumulative-sum
  "
  Returns a sequence of cumulative sum for the given collection. For instance
  The first value equals the first value of the argument, the second value is
  the sum of the first two arguments, the third is the sum of the first three
  arguments, etc.

  Examples:
    (use 'incanter.core)
    (cumulative-sum (range 100))
  "
  ([coll]
   (loop [in-coll (rest coll)
          cumu-sum [(first coll)]
          cumu-val (first coll)]
     (if (empty? in-coll)
       cumu-sum
       (let [cv (+ cumu-val (first in-coll))]
         (recur (rest in-coll) (conj cumu-sum cv) cv))))))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MATRIX DECOMPOSITION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn ^Matrix decomp-cholesky
  "
  Returns the Cholesky decomposition of the given matrix. Equivalent to R's
  chol function.

  Returns:
  a matrix of the triangular factor (note: the result from
  cern.colt.matrix.linalg.DenseDoubleCholeskyDecomposition is transposed so
  that it matches the result return from R's chol function.



  Examples:

  (use '(incanter core stats charts datasets))
  ;; load the iris dataset
  (def iris (to-matrix (get-dataset :iris)))
  ;; take the Cholesky decomposition of the correlation matrix of the iris data.
  (decomp-cholesky (correlation iris))

  References:
    http://en.wikipedia.org/wiki/Cholesky_decomposition
  "
  [^Matrix mat]
  (clx/cholesky mat))

(def ^:private ^:const allowed-types #{:full :compact :values})

(defn decomp-svd
  "
  Returns the Singular Value Decomposition (SVD) of the given matrix. Equivalent to
  R's svd function.

  Optional parameters:
  :type -- one of :full, :compact, or :values.  default is :full
  if :full, returns the full SVD
  if :compact, returns the compact SVD
  if :values, only the singular values are calculated

  Returns:
  a map containing:
  :S -- the diagonal matrix of singular values S (the diagonal in vector form)
  :U -- the left singular vectors U
  :V -- the right singular vectors V

  Examples:

  (use 'incanter.core)
  (def foo (matrix (range 9) 3))
  (decomp-svd foo)
  (decomp-svd foo :type :full)
  (decomp-svd foo :type :compact)
  (decomp-svd foo :type :values)


  References:
  http://en.wikipedia.org/wiki/Singular_value_decomposition
  "
  [mat & {:keys [type] :or {type :full}}]
  (let [type (or (get allowed-types type) :full)
        result (if (= type :full)
                 (clx/svd mat :type :full)
                 (clx/svd mat :type :sparse))]
    (if (= type :values)
      {:S (:values (clx/svd mat :type :values))}
      {:S (:values result)
       :U (if (= type :compact) mat (:left result))
       :V (:right result)})))

(defn decomp-eigenvalue
  "
  Returns the Eigenvalue Decomposition of the given matrix. Equivalent to R's eig function.

  Returns:
  a map containing:
  :values -- vector of eigenvalues
  :vectors -- the matrix of eigenvectors

  Examples:

  (use 'incanter.core)
  (def foo (matrix (range 9) 3))
  (decomp-eigenvalue foo)

  References:
  http://en.wikipedia.org/wiki/Eigenvalue_decomposition
  "
  [mat]
  (let [result (clx/eigen mat)]
    {:values (or (:values result) (:ivalues result))
     :vectors (or (:vectors result) (:ivectors result))}))


(defn decomp-lu
  "
  Returns the LU decomposition of the given matrix.

  Examples:

  (use 'incanter.core)
  (def foo (matrix (range 9) 3))
  (decomp-lu foo)


  Returns:
    a map containing:
      :L -- the lower triangular factor
      :U -- the upper triangular factor
      :P -- the permutation matrix

  References:
    http://en.wikipedia.org/wiki/LU_decomposition
    http://mikiobraun.github.io/jblas/javadoc/org/jblas/Decompose.LUDecomposition.html
  "
  ([mat]
    (let [result (clx/lu mat)]
      {:L (:l result)
       :U (:u result)
       :P (:p result)})))

(defn vector-length [u]
  (sqrt (reduce + (map (fn [c] (pow c 2)) u))))

(defn inner-product [u v]
  (apply + (mult u (trans v))))

(defn proj [u v]
  (mult (div (inner-product v u) (inner-product u u)) u))

(defn decomp-qr
  "
  Returns the QR decomposition of the given matrix. Equivalent to R's qr function.

  Optional parameters:
  :type -- possible values: :full.  default is :full
  if :full, returns the full QR decomposition

  Returns:
  a map containing:
  :Q -- orthogonal factors
  :R -- the upper triangular factors

  Examples:

  (use 'incanter.core)
  (def foo (matrix (range 9) 3))
  (decomp-qr foo)
  (decomp-qr foo :type :full)

  References:
  http://en.wikipedia.org/wiki/QR_decomposition

  "
  [m & {:keys [type]}]
  (let [;type (or (#{:full :compact} type) :full)
        qr (clx/qr m)]
    {:Q (:q qr)
     :R (:r qr)})
  )

(defn condition
  "
  Returns the two norm condition number, which is max(S) / min(S), where S is the diagonal matrix of singular values from an SVD decomposition.


  Examples:
    (use 'incanter.core)
    (def foo (matrix (range 9) 3))
    (condition foo)

  References:
    http://en.wikipedia.org/wiki/Condition_number
  "
  ([mat]
    (let [s (:S (decomp-svd mat))]
      (/ (apply max s) (apply min s)))))


(defn rank
  "
  Returns the effective numerical matrix rank, which is the number of nonnegligible singular values.

  Examples:

  (use 'incanter.core)
  (def foo (matrix (range 9) 3))
  (rank foo)

  References:
  http://en.wikipedia.org/wiki/Matrix_rank
  "
  [mat]
  (clx/rank mat))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MISC FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn length
  "
  A version of count that works on collections, matrices, and numbers.
  The length of a number is one, the length of a collection is its count
  and the length of a matrix is the number of elements it contains (nrow*ncol).
  Equivalent to R's length function.
  "
  ([coll]
    (cond
      (number? coll)
        1
      (matrix? coll)
        (* (nrow coll) (ncol coll))
      (coll? coll)
        (count coll)
      :else
        1)))

(defn group-on
  "
  Groups the given matrix by the values in the columns indicated by the
  'on-cols' argument, returning a sequence of matrices. The returned
  matrices are sorted by the value of the group column ONLY when there
  is only a single (non-vector) on-col argument.

  Examples:

    (use '(incanter core datasets))
    (def plant-growth (to-matrix (get-dataset :plant-growth)))
    (group-on plant-growth 1)
    ;; only return the first column
    (group-on plant-growth 1 :cols 0)
    ;; don't return the second column
    (group-on plant-growth 1 :except-cols 1)

    (def plant-growth-dummies (to-matrix (get-dataset :plant-growth) :dummies true))
    (group-on plant-growth-dummies [1 2])
    ;; return only the first column
    (group-on plant-growth-dummies [1 2] :cols 0)
    ;; don't return the last two columns
    (group-on plant-growth-dummies [1 2] :except-cols [1 2])

    ;; plot the plant groups
    (use 'incanter.charts)
    ;; can use destructuring if you know the number of groups
    ;; groups are sorted only if the group is based on a single column value
    (let [[ctrl trt1 trt2] (group-on plant-growth 1 :cols 0)]
      (doto (box-plot ctrl)
            (add-box-plot trt1)
            (add-box-plot trt2)
            view))
  "
  ([mat on-cols & {:keys [cols except-cols]}]
    (let [groups (if (coll? on-cols)
                   (into #{} (to-list (sel mat :cols on-cols)))
                   (sort (into #{} (to-list (sel mat :cols on-cols)))))
          filter-fn (fn [group]
                      (cond
                        (and (coll? on-cols) (> (count on-cols) 1))
                          (fn [row]
                            (reduce #(and %1 %2)
                                    (map (fn [i g] (= (nth row i) g)) on-cols group)))
                        (and (coll? on-cols) (= (count on-cols) 1))
                          (fn [row]
                            (= (nth row (first on-cols)) group))
                        :else
                          (fn [row]
                            (= (nth row on-cols) group))))
         ]
      (cond
        cols
          (map #(sel mat :cols cols :filter-fn (filter-fn %)) groups)
        except-cols
          (map #(sel mat :except-cols except-cols :filter-fn (filter-fn %)) groups)
        :else
          (map #(sel mat :filter-fn (filter-fn %)) groups)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DATASET FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn dataset
  "
  Returns a map of type incanter.core.dataset constructed from the given column-names and
  data. The data is either a sequence of sequences or a sequence of hash-maps.
  "
  ([column-names & data]
    (let [dat (cond
                (or (map? (ffirst data)) (coll? (ffirst data)))
                  (first data)
                (map? (first data))
                  data
                :else
                  (map vector (first data)))
          rows (cond
                 (map? dat)
                   [dat]
                 (map? (first dat))
                   dat
                 :else
                   (map #(apply assoc {} (interleave column-names %)) dat))]
      (Dataset. (into [] column-names) rows))))


(defn- get-column-id [dataset column-key]
  (let [headers (:column-names dataset)]
    (cond
     (and (keyword? column-key) ;; if the given column name is a keyword, and
          ;; a keyword column name wasn't used in the dataset
          (not (some #{column-key} headers)))
     (name column-key) ;; convert the keyword to a string

     (and (string? column-key) ;; if the given column is a string, and
          ;; this column was't used in the dataset, and
          (not (some #{column-key} headers))
          ;; a keyword column name was used in the dataset
          (some #{(keyword column-key)} headers))
     ;; convert string to keyword
     (keyword column-key)

     (and (number? column-key) ;; if the given column name is a number
          ;; and this number is not in headers
          (not (some #(= column-key %) headers)))
     (nth headers column-key) ;; get nth column from headers

     :else column-key)))

(defn- map-get
  ([m k]
    (if (keyword? k)
      (or (get m k) (get m (name k)))
      (get m k)))
  ([m k colnames]
    (cond
      (keyword? k)
        (or (get m k) (get m (name k)))
      (number? k)
        (get m (nth colnames k))
      :else
        (get m k))))

(defn- submap [m ks]
  (zipmap (if (coll? ks) ks [ks])
          (map #(map-get m %) (if (coll? ks) ks [ks]))))




(defn query-to-pred
  "
  Given a query-map, it returns a function that accepts a hash-map and returns true if it
  satisfies the conditions specified in the provided query-map.

  Examples:

    (use 'incanter.core)
    (def pred (query-to-pred {:x 5 :y 7}))
    (pred {:x 5 :y 7 :z :d})

    (def pred (query-to-pred {:x 5 :y {:$gt 5 :$lt 10}}))
    (pred {:x 5 :y 7 :z :d})

    (def pred (query-to-pred {:z {:$in #{:a :b}}}))
    (pred {:x 5 :y 7 :z :d})
  "
  ([query-map]
    (let [in-fn (fn [value val-set] (some val-set [value]))
          nin-fn (complement in-fn)
          ops {:gt #(> (compare %1 %2) 0)
               :lt #(< (compare %1 %2) 0)
               :eq =
               :ne not=
               :gte #(>= (compare %1 %2) 0)
               :lte #(<= (compare %1 %2) 0)
               :in in-fn :nin nin-fn :fn (fn [v f] (f v))
               :$gt #(> (compare %1 %2) 0)
               :$lt #(< (compare %1 %2) 0)
               :$eq = :$ne not=
               :$gte #(>= (compare %1 %2) 0)
               :$lte #(<= (compare %1 %2) 0)
               :$in in-fn :$nin nin-fn
               :$fn (fn [v f] (f v))}
          _and (fn [a b] (and a b))]
      (fn [row]
        (reduce _and
                (for [k (keys query-map)]
                  (if (map? (query-map k))
                    (reduce _and
                            (for [sk (keys (query-map k))]
                              (cond
                               (fn? sk)
                                 (sk (row k) ((query-map k) sk))
                               (nil? (ops sk))
                                 (throw (Exception. (str "Invalid key in query-map: " sk)))
                               :else
                                ((ops sk) (row k) ((query-map k) sk)))))
                    (= (row k) (query-map k)))))))))


(defn query-dataset
  "
  Queries the given dataset using the query-map, returning a new dataset.
  The query-map uses the the dataset's column-names as keys and a
  simple variant of the MongoDB query language.

  For instance, given a dataset with two columns, :x and :category,  to query
  for rows where :x equals 10, use the following query-map: {:x 10}.

  To indicate that :x should be between 10 and 20, use {:x {:$gt 10 :$lt 20}}.

  To indicate that :category should also be either :red, :green, or :blue, use :$in
  {:x {:$gt 10 :$lt 20} :y {:$in #{:green :blue :red}}}

  And to indicate that :category should not include :red, :green, or :blue, use :$nin
  {:x {:$gt 10 :$lt 20} :y {:$nin #{:green :blue :red}}}

  The available query terms include :$gt, :$lt, :$gte, :$lte, :$eq, :$ne, :$in, :$nin, $fn.

  A row predicate function can be used instead of a query-map. The function must accept
  a map, representing a row of the dataset, and return a boolean value indicating whether
  the row should be included in the new dataset.

  Examples:
    (use '(incanter core datasets))
    (def cars (get-dataset :cars))

    (view (query-dataset cars {:speed 10}))
    (view (query-dataset cars {:speed {:$in #{17 14 19}}}))
    (view (query-dataset cars {:speed {:$lt 20 :$gt 10}}))
    (view (query-dataset cars {:speed {:$fn #(> (log %) 3)}}))

    ;; use a row predicate function instead of a query map.
    (view (query-dataset cars (fn [row] (> (/ (row \"speed\") (row \"dist\")) 1/2))))
  "
  ([data query-map]
    (if (fn? query-map)
      (assoc data :rows
             (for [row (:rows data) :when (query-map row)] row))
      (let [qmap (into {}
                       (for [k (keys query-map)]
                         (if (keyword? k)
                           (if (some #{k} (:column-names data))
                             [k (query-map k)]
                             [(name k) (query-map k)])
                           [k (query-map k)])))
            pred (query-to-pred qmap)
            rows (:rows data)]
        (assoc data :rows
               (for [row rows :when (pred row)] row))))))


(defn- except-for-cols
  ([data except-cols]
     (let [colnames (:column-names data)
           _except-cols (if (coll? except-cols)
                          (map #(get-column-id data %) except-cols)
                          [(get-column-id data except-cols)])
           except-names  (if (some number? _except-cols)
                           (map #(nth colnames %) _except-cols)
                           _except-cols)]
       (for [name colnames :when (not (some #{name} except-names))]
         name))))


(defmethod sel [::dataset true]
  ([data & {:keys [rows cols except-rows except-cols filter all]}]
    (let [rows (cond
                 rows rows
                 except-rows (except-for (nrow data) except-rows)
                 :else true)
          cols (cond
                 cols cols
                 except-cols (except-for-cols data except-cols)
                 all all
                 :else true)
          all-rows? (or (true? rows) (= rows :all) all)
          colnames (:column-names data)
          selected-cols (cond
                          (or (= cols :all) (true? cols)) colnames
                          (coll? cols) (map #(get-column-id data %) cols)
                          :else [cols])
          selected-rows (cond
                          all-rows?
                            (:rows data)
                          (number? rows)
                            (list (nth (:rows data) rows))
                          (coll? rows)
                            (map #(nth (:rows data) %) rows))
          _data (map (fn [row] (map #(row (get-column-id data %)) selected-cols)) selected-rows)
          result (if (nil? filter) _data (clojure.core/filter filter _data))]
      (cond
        (and (= (count selected-cols) 1) (not (or (coll? cols) (true? cols))))
          (if (= (count result) 1)
            (ffirst result)
            (mapcat identity result))
        (and (= (count result) 1) (not (or (coll? rows) all-rows?)))
          (first result)
        :else
          (dataset selected-cols (map #(apply assoc {} (interleave selected-cols %)) result))))))

(defn to-dataset
  "
  Returns a dataset containing the given values.

  Examples:

    (use 'incanter.core)
    (to-dataset 1)
    (to-dataset :a)
    (to-dataset [:a])
    (to-dataset (range 10))
    (to-dataset (range 10) :transpose true)
    (to-dataset [[1 2] [3 4] [5 6]])
    (to-dataset {:a 1 :b 2 :c 3})
    (to-dataset {\"a\" 1 \"b\" 2 \"c\" 3})
    (to-dataset [{:a 1 :b 2} {:a 1 :b 2}])
    (to-dataset [{\"a\" 1 \"b\" 2 \"c\" 3} {\"a\" 1 \"b\" 2 \"c\" 3}])
  "
  ([obj & {:keys [transpose]}]
    (let [transpose? (true? transpose)
          colnames (cond
                     (dataset? obj)
                       (:column-names obj)
                     (map? obj)
                       (keys obj)
                     (coll? obj)
                       (cond
                         (map? (first obj))
                           (keys (first obj))
                         (coll? (first obj))
                           (map #(keyword (str "col-" %)) (range (length (first obj))))
                         transpose?
                           (map #(keyword (str "col-" %)) (range (length obj)))
                         :else
                           [:col-0])
                      :else
                        [:col-0])
          rows (cond
                 (dataset? obj)
                   (:rows obj)
                 (map? obj)
                   ;; see if any of the values are collections
                   (if (reduce #(or %1 %2) (map coll? (vals obj)))
                     (trans (vals obj))
                     [(vals obj)])
                   (coll? obj)
                     (cond
                       (coll? (first obj))
                         obj
                       transpose?
                         [obj]
                       :else
                         obj)
                   :else
                     [obj])]
         (dataset colnames rows))))

(defn make-unique
  "
  Take a sequence of keywords and make them unique by possibly
  altering later ones.
  "
  ([coll] (make-unique coll #{}))
  ([coll seen]
    (let [new-name (fn new-name [x]
                     (if (not (contains? seen x))
                         x
                         (let [match (re-matches #"(.*\-)([0-9]+)" (.getName x))]
                           (if match
                             (new-name (keyword (str (second match) (inc (Integer/parseInt (nth match 2))))))
                             (new-name (keyword (str (.getName x) "-1")))))))]

      (if (empty? coll)
          ()
          (let [name (new-name (first coll))]
            (cons name
                  (make-unique (rest coll) (conj seen name))))))))


(defn col-names
  "
  If given a dataset, it returns its column names. If given a dataset and a sequence
  of column names, it returns a dataset with the given column names.

  Examples:
    (use '(incanter core datasets))
    (def data (get-dataset :cars))
    (col-names data)

    (def renamed-data (col-names data [:x1 :x2]))
    (col-names renamed-data)

  "
  ([data] (:column-names data))
  ([data colnames]
    (dataset colnames (to-list data))))



(defn conj-cols
  "
  Returns a dataset created by merging the given datasets and/or collections.
  There must be the same number of rows in each dataset and/or
  collections.  Column names may be changed in order to prevent
  naming conflicts in the conjed dataset.

  Examples:
    (use '(incanter core datasets))
    (def cars (get-dataset :cars))
    (def x (sel cars :cols 0))
    (view (conj-cols cars cars))
    (view (conj-cols cars x))
    (view (conj-cols (range (nrow cars)) cars))
    (view (conj-cols (range 10) (range 10)))
    (view (conj-cols {:a 1 :b 2} {:c 1 :d 2}))
  "
  ([& args]
    (reduce (fn [A B]
              (let [a (to-dataset A)
                    b (to-dataset B)
                    ncol-a (ncol a)
                    ncol-b (ncol b)
                    colnames (make-unique (concat (col-names a) (col-names b)))]
                (dataset colnames
                         (map concat (to-list a) (to-list b)))))
            args)))


(defn conj-rows
  "
  Returns a dataset created by combining the rows of the given datasets and/or collections.

  Examples:

    (use '(incanter core datasets))
    (def cars (get-dataset :cars))
    (view (conj-rows (to-dataset (range 5)) (to-dataset (range 5 10))))
    (view (conj-rows cars cars))
    (view (conj-rows [[1 2] [3 4]] [[5 6] [7 8]]))
    (view (conj-rows [{:a 1 :b 2} {:a 3 :b 4}] [[5 6] [7 8]]))
    (view (conj-rows (to-dataset [{:a 1 :b 2} {:a 3 :b 4}]) [[5 6] [7 8]]))
    (conj-rows (range 5) (range 5 10))
  "
  ([& args]
    (reduce (fn [A B]
              (let [a (to-dataset A :transpose true)
                    b (to-dataset B :transpose true)]
                (dataset (:column-names a)
                         (concat (to-list a) (to-list b)))))
            args)))



(defn $
  "
  An alias to (sel (second args) :cols (first args)). If given only a single argument,
  it will use the $data binding for the first argument, which is set with
  the with-data macro.

  Examples:
    (use '(incanter core stats charts datasets))

    (def cars (get-dataset :cars))
    ($ :speed cars)


    (with-data cars
      (def lm (linear-model ($ :dist) ($ :speed)))
      (doto (scatter-plot ($ :speed) ($ :dist))
        view
        (add-lines ($ :speed) (:fitted lm))))

    ;; standardize speed and dist and append the standardized variables to the original dataset
    (with-data (get-dataset :cars)
      (view (conj-cols $data
                       (sweep (sweep ($ :speed)) :stat sd :fun div)
                       (sweep (sweep ($ :dist)) :stat sd :fun div))))

    (with-data (get-dataset :iris)
      (view $data)
      (view ($ [:Sepal.Length :Sepal.Width :Species]))
      (view ($ [:not :Petal.Width :Petal.Length]))
      (view ($ 0 [:not :Petal.Width :Petal.Length])))


     (use 'incanter.core)
     (def mat (matrix (range 9) 3))
     (view mat)
     ($ 2 2 mat)
     ($ [0 2] 2 mat)
     ($ :all 1 mat)
     ($ 1 mat)
     ($ [:not 1] mat)
     ($ 0 :all mat)
     ($ [0 2] [0 2] mat)
     ($ [:not 1] [:not 1] mat)
     ($ [:not 1] :all mat)
     ($ [0 2] [:not 1] mat)
     ($ [0 2] [:not 1 2] mat)
     ($ [0 2] [:not (range 2)] mat)
     ($ [:not (range 2)] [0 2] mat)

     (with-data mat
       ($ 0 0))
     (with-data mat
       ($ [0 2] 2 mat))
     (with-data mat
       ($ :all 1))
     (with-data mat
       ($ [0 2] [0 2]))
     (with-data mat
       ($ [:not 1] :all))
     (with-data mat
       ($ [0 2] [:not 1]))


     (use 'incanter.datasets)
     (view (get-dataset :cars))
     ($ (range 5) 0 (get-dataset :cars))
     ($ (range 5) :all (get-dataset :cars))
     ($ :all (range 2) (get-dataset :cars))

     ($ (range 5) :dist (get-dataset :cars))
     ($ [:not (range 5)] 0 (get-dataset :cars))
     ($ [:not 0 1 2 3 4] 0 (get-dataset :cars))
     (with-data (get-dataset :cars)
       ($ 0 :dist))

     (with-data (get-dataset :hair-eye-color)
       (view $data)
       (view ($ [:not :gender])))
  "

  ([cols]
    ($ :all cols $data))
  ([arg1 arg2]
    (let [rows-cols-data
          (cond (nil? arg2) [:all arg1 $data]
                (or (matrix? arg2) (dataset? arg2)) [:all arg1 arg2]
                :else [arg1 arg2 $data])]
      (apply $ rows-cols-data)))
  ([rows cols data]
    (let [except-rows? (and (vector? rows) (= :not (first rows)))
          except-cols? (and (vector? cols) (= :not (first cols)))
          _rows (if except-rows?
                  (conj [:except-rows]
                        (if (coll? (second rows))
                          (second rows)
                          (rest rows)))
                  [:rows rows])
          _cols (if except-cols?
                  (if (coll? (second cols))
                    (conj [:except-cols] (second cols))
                    (conj [:except-cols] (rest cols)))
                  [:cols cols])
          args (concat _rows _cols)]
      (apply sel data args))))

(defn head
  "Returns the head of the dataset. 10 or full dataset by default."
  ([len mat]
    (cond
      (= len 0) ($ :none :all mat)
      (<= len (- (nrow mat))) (head 0 mat)
      (< len 0) (head (+ (nrow mat) len) mat)
      :else ($ (range (min len (nrow mat))) :all mat)))
  ([mat]
    (head 10 mat)))

(defn tail
  "Returns the tail of the dataset. 10 or full dataset by default."
  ([len mat]
    (cond
      (= len 0) ($ :none :all mat)
      (<= len (- (nrow mat))) (head 0 mat)
      (< len 0) (head (+ (nrow mat) len) mat)
      :else ($ (range (max 0 (- (nrow mat) len)) (nrow mat)) :all mat)))
  ([mat]
    (tail 10 mat)))

(defn $where
  "
  An alias to (query-dataset (second args) (first args)). If given only a single argument,
  it will use the $data binding for the first argument, which is set with
  the with-data macro.

  Examples:

    (use '(incanter core datasets))

    (def cars (get-dataset :cars))
    ($where {:speed 10} cars)

    ;; use the with-data macro and the one arg version of $where
    (with-data cars
      (view ($where {:speed {:$gt -10 :$lt 10}}))
      (view ($where {:dist {:$in #{10 12 16}}}))
      (view ($where {:dist {:$nin #{10 12 16}}})))

    ;; create a dataset where :speed greater than 10 or less than -10
    (with-data (get-dataset :cars)
      (view (-> ($where {:speed {:$gt 20}})
                      (conj-rows ($where {:speed {:$lt 10}})))))
  "
  ([query-map]
    (query-dataset $data  query-map))
  ([query-map data]
    (query-dataset data query-map)))



(defn $rollup
  "
  Returns a dataset that uses the given summary function (or function identifier keyword)
  to rollup the given column based on a set of group-by columns. The summary function
  should accept a single sequence of values and return a single summary value. Alternatively,
  you can provide a keyword identifier of a set of built-in functions including:

  :max -- the maximum value of the data in each group
  :min -- the minimum value of the data in each group
  :sum -- the sum of the data in each group
  :count -- the number of elements in each group
  :mean -- the mean of the data in each group


  Like the other '$' dataset functions, $rollup will use the dataset bound to $data
  (see the with-data macro) if a dataset is not provided as an argument.

  Examples:

    (use '(incanter core datasets))

    (def iris (get-dataset :iris))
    ($rollup :mean :Sepal.Length :Species iris)
    ($rollup :count :Sepal.Length :Species iris)
    ($rollup :max :Sepal.Length :Species iris)
    ($rollup :min :Sepal.Length :Species iris)

    ;; The following is an example using a custom function, but since all the
    ;; iris measurements are positive, the built-in mean function could have
    ;; been used instead.

    (use 'incanter.stats)
    ($rollup #(mean (abs %)) :Sepal.Width :Species iris)

    ($rollup sd :Sepal.Length :Species iris)
    ($rollup variance :Sepal.Length :Species iris)
    ($rollup median :Sepal.Length :Species iris)

    (def hair-eye-color (get-dataset :hair-eye-color))
    ($rollup :mean :count [:hair :eye] hair-eye-color)

    (use 'incanter.charts)
    (with-data ($rollup :mean :Sepal.Length :Species iris)
      (view (bar-chart :Species :Sepal.Length)))

     ;; the following examples use the built-in data set called hair-eye-color.

     (with-data ($rollup :mean :count [:hair :eye] hair-eye-color)
       (view (bar-chart :hair :count :group-by :eye :legend true)))

     (with-data (->>  (get-dataset :hair-eye-color)
                      ($where {:hair {:in #{\"brown\" \"blond\"}}})
                      ($rollup :sum :count [:hair :eye])
                      ($order :count :desc))
       (view $data)
       (view (bar-chart :hair :count :group-by :eye :legend true)))
  "
  ([summary-fun col-name group-by]
    ($rollup summary-fun col-name group-by $data))
  ([summary-fun col-name group-by data]
    (let [key-fn (if (coll? col-name)
                   (fn [row]
                     (into [] (map #(map-get row %) col-name)))
                   (fn [row]
                     (map-get row col-name)))
          rows (:rows data)
          rollup-fns {:max (fn [col-data] (apply max col-data))
                      :min (fn [col-data] (apply min col-data))
                      :sum (fn [col-data] (apply + col-data))
                      :count count
                      :mean (fn [col-data] (/ (apply + col-data) (count col-data)))}
          rollup-fn (if (keyword? summary-fun)
                      (rollup-fns summary-fun)
                      summary-fun)]
      (loop [cur rows reduced-rows {}]
        (if (empty? cur)
          (let [group-cols (to-dataset (keys reduced-rows))
                res (conj-cols group-cols (map rollup-fn (vals reduced-rows)))]
            (col-names res (concat (col-names group-cols)
                                   (if (coll? col-name) col-name [col-name]))))
          (recur (next cur)
                 (let [row (first cur)
                       k (submap row group-by)
                       a (reduced-rows k)
                       b (key-fn row)]
                   (assoc reduced-rows k (if a (conj a b) [b])))))))))


(defn $order
  "
  Sorts a dataset by the given columns in either ascending (:asc)
  or descending (:desc) order. If used within a the body of
  the with-data macro, the data argument is optional, defaulting
  to the dataset bound to the variable $data.

  Examples:

    (use '(incanter core charts datasets))
    (def iris (get-datset :iris))
    (view ($order :Sepal.Length :asc iris))
    (view ($order [:Sepal.Width :Sepal.Length] :desc iris))

    (with-data (get-dataset :iris)
      (view ($order [:Petal.Length :Sepal.Length] :desc)))

  "
  ([cols order]
    ($order cols order $data))
  ([cols order data]
    (let [key-cols (if (coll? cols) cols [cols])
          key-fn (fn [row] (into [] (map #(map-get row %) key-cols)))
          comp-fn (if (= order :desc)
                    (comparator (fn [a b] (pos? (compare a b))))
                    compare)]
      (dataset (col-names data) (sort-by key-fn comp-fn (:rows data))))))



(defmacro $fn
  "
  A simple macro used as syntactic sugar for defining predicate functions to be used
  in the $where function. The supplied arguments should be column names of a dataset.
  This macro performs map destructuring on the arguments.

  For instance,
  ($fn [speed] (< speed 10)) => (fn [{:keys [speed]}] (< speed 10))

  Examples:
    (use '(incanter core datasets))
    (view ($where ($fn [speed dist] (or (> speed 20) (< dist 10))) (get-dataset :cars)))

    (view ($where ($fn [speed dist] (< (/ dist speed) 2)) (get-dataset :cars)))

    (use '(incanter core datasets charts))
    (with-data (get-dataset :cars)
      (doto (scatter-plot :speed :dist :data ($where ($fn [speed dist] (< (/ dist speed) 2))))
        (add-points :speed :dist :data ($where ($fn [speed dist] (>= (/ dist speed) 2))))
        (add-lines ($ :speed) (mult 2 ($ :speed)))
        view))


    (let [passed? ($fn [speed dist] (< (/ dist speed) 2))
          failed? (complement passed?)]
      (with-data (get-dataset :cars)
        (doto (scatter-plot :speed :dist :data ($where passed?))
          (add-points :speed :dist :data ($where failed?))
          (add-lines ($ :speed) (mult 2 ($ :speed)))
          view)))


    (use '(incanter core stats charts))
    (let [above-sine? ($fn [col-0 col-1] (> col-1 (sin col-0)))
          below-sine? (complement above-sine?)]
      (with-data (conj-cols (sample-uniform 1000 :min -5 :max 5)
                            (sample-uniform 1000 :min -1 :max 1))
        (doto (function-plot sin -5 5)
          (add-points :col-0 :col-1 :data ($where above-sine?))
          (add-points :col-0 :col-1 :data ($where below-sine?))
          view)))


    (view ($where ($fn [] (> (rand) 0.9)) (get-dataset :cars)))

    (view ($where ($fn [Species] ($in Species #{\"virginica\" \"setosa\"})) (get-dataset :iris)))
  "
  ([col-bindings body]
    `(fn [{:keys ~col-bindings}] ~body)))



(defn $group-by
  "
  Returns a map of datasets keyed by a query-map corresponding the group.

  Examples:

    (use '(incanter core datasets))
    ($group-by :Species (get-dataset :iris))

    ($group-by [:hair :eye] (get-dataset :hair-eye-color))

    (with-data (get-dataset :hair-eye-color)
      ($group-by [:hair :eye]))
  "
  ([cols]
    ($group-by cols $data))
  ([cols data]
    (let [orig-col-names (:column-names data);save to preserve order below
          groups (group-by #(submap % cols) (:rows data))]
      (into {}
            (for [[group-value group-rows] groups]
              {group-value (dataset orig-col-names group-rows)})))))


(defn matrix-map
  "
  Like clojure.core/map, but will work on matrices of any dimension:
  1 x 1 (like e.g. a Double), 1 x n, n x 1, and n x m

  Examples:
    (use '(incanter core))
    (def mat (matrix (range 9) 3))
    (matrix-map #(mod % 2) mat)
    (matrix-map #(mod % 2) (first mat))
    (matrix-map #(mod % 2) ($ 1 0 mat))
    (matrix-map #(mod % 2) [1 2 3 4])
    (matrix-map #(mod % 2) 9)
  "
  ([f m]
    (let [m (if (and (matrix? m) (clx/row? m)) (to-list m) m)]
      (if (sequential? m)
        (if (sequential? (first m))
          (map (fn [& a] (apply map f a)) m)
          (map f m))
        (f m))))
  ([f m & ms]
    (let [m (if (and (matrix? m) (clx/row? m)) (to-list m) m)]
      (if (sequential? m)
        (if (sequential? (first m))
          (apply map (fn [& a] (apply map f a)) m ms)
          (apply map f m ms))
        (apply f m ms)))))

(defn $map
  "
  This function returns a sequence resulting from mapping the given function over
  the value(s) for the given column key(s) of the given dataset.
  Like other '$*' functions, it will use $data as the default dataset
  if none is provided, where $data is set using the with-data macro.

  Examples:

    (use '(incanter core datasets))
    (def cars (get-dataset :cars))

    ($map (fn [s] (/ s)) :speed cars)
    ($map (fn [s d] (/ s d)) [:speed :dist] cars)

    (map (fn [s d] (/ s d)) ($ :speed cars) ($ :speed cars))

    (with-data (get-dataset :cars)
      (view ($map (fn [s] (/ s)) :speed))
      (view ($map (fn [s d] (/ s d)) [:speed :dist])))

    ;; calculate the speed to dist ratio and append as new column to dataset
    (with-data (get-dataset :cars)
      (conj-cols $data ($map (fn [s d] (/ s d)) [:speed :dist])))
  "
  ([fun col-keys data]
    (let [rows (:rows data)]
      (if (coll? col-keys)
        (map (fn [row] (apply fun (map (fn [k] (map-get row k)) col-keys))) (:rows data))
        (map (fn [row] (fun (map-get row col-keys))) (:rows data)))))
  ([fun col-keys]
    ($map fun col-keys $data)))



(defn $join
  "
  Returns a dataset created by right-joining two datasets.
  The join is based on one or more columns in the datasets.
  If used within the body of the with-data macro, the second
  dataset is optional, defaulting the the dataset bound to $data.


  Examples:
    (use '(incanter core stats datasets charts))
    (def iris (get-dataset :iris))



    (def lookup (dataset [:species :species-key] [[\"setosa\" :setosa]
                                                  [\"versicolor\" :versicolor]
                                                  [\"virginica\" :virginica]]))
    (view ($join [:species :Species] lookup iris))

    (def hair-eye-color (get-dataset :hair-eye-color))
    (def lookup2 (conj-cols ($ [:hair :eye :gender] hair-eye-color) (range (nrow hair-eye-color))))
    (view ($join [[:col-0 :col-1 :col-2] [:hair :eye :gender]] lookup2 hair-eye-color))

    (with-data hair-eye-color
      (view ($join [[:col-0 :col-1 :col-2] [:hair :eye :gender]] lookup2)))


    (def lookup3 (dataset [:gender :hair :hair-gender] [[\"male\" \"black\" :male-black]
                                                        [\"male\" \"brown\" :male-brown]
                                                        [\"male\" \"red\" :male-red]
                                                        [\"male\" \"blond\" :male-blond]
                                                        [\"female\" \"black\" :female-black]
                                                        [\"female\" \"brown\" :female-brown]
                                                        [\"female\" \"red\" :female-red]
                                                        [\"female\" \"blond\" :female-blond]]))

    (view ($join [[:gender :hair] [:gender :hair]] lookup3 hair-eye-color))

    (use 'incanter.charts)
    (with-data (->>  (get-dataset :hair-eye-color)
                     ($where {:hair {:in #{\"brown\" \"blond\"}}})
                     ($rollup :sum :count [:hair :gender])
                     ($join [[:gender :hair] [:gender :hair]] lookup3)
                     ($order :count :desc))
        (view $data)
        (view (bar-chart :hair :count :group-by :gender :legend true)))
  "
  ([[left-keys right-keys] left-data]
    ($join [left-keys right-keys] left-data $data))
  ([[left-keys right-keys] left-data right-data]
    (let [left-keys (if (coll? left-keys) left-keys [left-keys])
          right-keys (if (coll? right-keys) right-keys [right-keys])
          index (apply hash-map
                       (interleave
                        (map (fn [row]
                               (apply hash-map
                                      (interleave right-keys
                                                  (map #(map-get (submap row left-keys) %)
                                                       left-keys))))
                             (:rows left-data))
                        (map #(reduce dissoc % left-keys) (:rows left-data))))
          rows (map #(merge (index (submap % right-keys)) %) (:rows right-data))]
      (to-dataset rows))))

(defn aggregate
  "
  Performs the aggregation of the data in given dataset using the specified rollup function.
  The fields parameter defines column(s) on which the rollup will happen, and group-by
  specifies the column(s) for joining the results.  The fields & group-by parameters could be
  single values or collections.  The dataset is provided by the :dataset parameter, if it's not
  provided, then the $data is used.  The rollup function is provided by :rollup-fun parameter,
  if it's not provided, then the :sum is used.

    (aggregate [:uptake :conc] :Type :dataset (get-dataset :co2))
    (aggregate [:uptake :conc] [:Type] :dataset (get-dataset :co2) :rollup-fun :min)
"
  [fields group-by & {:keys [dataset rollup-fun] :or {rollup-fun :sum}}]
  (let [dset (or dataset $data)
        fields (if (coll? fields) fields [fields])
        group-by (if (coll? group-by) group-by [group-by])]
    (reduce #($join [group-by group-by] %1 %2)
            (map #($rollup rollup-fun % group-by dset)
                 fields))))

(defn- replace-by-number-or-value [col-vec [old-col new-col-name]]
  (if (number? old-col)
    (assoc col-vec old-col new-col-name)
    (replace {old-col new-col-name} col-vec)))

(defn rename-cols
  "
  Rename columns based on col-map of old-col new-col-name pairs.  If
  old-col is a number it is taken as a 0 based index for the column to
  replace

  Example:
   (use '(incanter core datasets))
   (rename-cols {:Sepal.Length :s.length 3 :p.width} (get-dataset :iris))
  "
  ([col-map]
    (rename-cols col-map $data))
  ([col-map data]
    (let [old-col-names (col-names data)
          new-col-names (reduce
                         replace-by-number-or-value old-col-names col-map)]
      (col-names data new-col-names))))

(defn- update
  ([m key f] (update-in m [key] f))
  ([m key f & kfs] (apply update (update-in m [key] f) kfs)))

(defn replace-column
  "Replaces a column in a dataset with new values."
  ([column-name values]
    (replace-column column-name values $data))
  ([column-name values data]
    (update data :rows
            (fn [rows]
              (map #(assoc %1 column-name %2)
                   rows values)))))

(defn add-column
  "Adds a column, with given values, to a dataset."
  ([column-name values]
    (add-column column-name values $data))
  ([column-name values data]
    (if (some #{column-name} (:column-names data))
      (replace-column column-name values data)
      (update data :column-names #(conj % column-name)
              :rows #(mapv (fn [r v]
                             (assoc r column-name v))
                           % (concat values (repeat nil)))))))

(defn add-derived-column
  "
  This function adds a column to a dataset that is a function of
  existing columns. If no dataset is provided, $data (bound by the
  with-data macro) will be used. f should be a function of the
  from-columns, with arguments in that order.

  Examples:
    (use '(incanter core datasets))
    (def cars (get-dataset :cars))

    (add-derived-column :dist-over-speed [:dist :speed] (fn [d s] (/ d s)) cars)

    (with-data (get-dataset :cars)
      (view (add-derived-column :speed**-1 [:speed] #(/ 1.0 %))))"

  ([column-name from-columns f]
    (add-derived-column column-name from-columns f $data))
  ([column-name from-columns f data]
    (update data :column-names #(conj % column-name)
            :rows (fn [rows]
                    (mapv (fn [row]
                            (assoc row column-name
                                   (apply f (map #(map-get row %)
                                                 from-columns))))
                          rows)))))

;; credit to M.Brandmeyer
(defn transform-col
  "
  Apply function f & args to the specified column of dataset and replace the column
  with the resulting new values.
  "
  [dataset column f & args]
  (->> (map #(apply update-in % [column] f args) (:rows dataset))
    vec
    (assoc dataset :rows)))


(defn deshape
  "
  Returns a dataset where the columns identified by :merge are collapsed into
  two columns called :variable and :value. The values in these columns are grouped
  by the columns identified by :group-by.

  Examples:

    (use '(incanter core charts datasets))
    (with-data (->> (deshape :merge [:Ahmadinejad :Rezai :Karrubi :Mousavi]
                              :group-by :Region
                              :data (get-dataset :iran-election))
                    ($order :value :desc))
      (view $data)
      (view (bar-chart :variable :value :group-by :Region :legend true))

      (view (bar-chart :Region :value :group-by :variable
                       :legend true :vertical false))

      (view (bar-chart :Region :value :legend true :vertical false
                       :data ($order :value :desc ($rollup :sum :value :Region)))))



      (def data (to-dataset [{:subject \"John Smith\" :time 1 :age 33 :weight 90 :height 1.87}
                             {:subject \"Mary Smith\" :time 1 :height 1.54}]))
      (view data)
      (view (deshape :group-by [:subject :time] :merge [:age :weight :height] :data data))
      (view (deshape :merge [:age :weight :height] :data data))
      (view (deshape :group-by [:subject :time] :data data))

      (view (deshape :merge [:age :weight :height] :remove-na false :data data))
  "
  ([& {:keys [data remove-na group-by merge] :or {remove-na true}}]
    (let [data (or data $data)
          colnames (col-names data)
          _group-by (into #{} (when group-by
                                (if (coll? group-by)
                                  group-by
                                  [group-by])))
          _merge (into #{} (when merge
                               (if (coll? merge)
                                 merge
                                 [merge])))
          __group-by (if (empty? _group-by)
                       (difference (into #{} (col-names data)) _merge)
                       _group-by)
          __merge (if (empty? _merge)
                       (difference (into #{} (col-names data)) _group-by)
                       _merge)
          deshaped-data (mapcat (fn [row]
                                  (let [base-map (zipmap __group-by
                                                         (map #(map-get row % colnames) __group-by))]
                                    (filter identity
                                            (map (fn [k]
                                                   (if (and remove-na (nil? (map-get row k colnames)))
                                                     nil
                                                     (assoc base-map :variable k :value (map-get row k colnames))))
                                                 __merge))))
                                (:rows data))]
      (to-dataset deshaped-data))))


(defn get-categories
  "
  Given a dataset and one or more column keys, returns the set of categories for them.

  Examples:

    (use '(incanter core datasets))
    (get-categories :eye (get-dataset :hair-eye-color))
    (get-categories [:eye :hair] (get-dataset :hair-eye-color))
  "
  ([cols data]
    (if (coll? cols)
      (for [col cols] (into #{} ($ col data)))
      (into #{} ($ cols data)))))



(defmacro with-data
  "
  Binds the given data to $data and executes the body.
  Typically used with the $ and $where functions.

  Examples:
    (use '(incanter core stats charts datasets))

    (with-data  (get-dataset :cars)
      (def lm (linear-model ($ :dist) ($ :speed)))
      (doto (scatter-plot ($ :speed) ($ :dist))
                (add-lines ($ :speed) (:fitted lm))
                 view))

     ;; create a dataset where :speed greater than 10 or less than -10
     (with-data (get-dataset :cars)
       (view (-> ($where {:speed {:$gt 20}})
                       (conj-rows ($where {:speed {:$lt 10}})))))
  "
  ([data-binding & body]
    `(binding [$data ~data-binding]
             (do ~@body))))


(defmulti to-map
  "
  Takes a dataset or matrix and returns a hash-map where the keys are
  keyword versions of the column names, for datasets, or numbers, for
  matrices, and the values are sequence of the column values.

  Examples:
    (use '(incanter core datasets))

    (to-map (get-dataset :cars))

    (to-map (matrix (range 9) 3))

  "
  type)

(defmethod to-map :incanter.core/dataset
  ([data]
    (let [cols (map (partial sel data :cols) (col-names data))
          col-keys (map keyword (col-names data))]
      (zipmap col-keys cols))))

(defmethod to-map incanter.Matrix
  ([mat]
    (let [cols (to-list (trans mat))
          col-keys (range (ncol mat))]
      (zipmap col-keys cols))))


(defn melt
  "
  Melt an object into a form suitable for easy casting, like a melt function in R.
  Only accepts one pivot key for now. e.g.

    (use '(incanter core charts datasets))
    (view (with-data (melt (get-dataset :flow-meter) :Subject)
              (line-chart :Subject :value :group-by :variable :legend true)))

  See http://www.statmethods.net/management/reshape.html for more examples."
  [dataset pivot-key]
  (let [in-m (to-map dataset)
        nrows (nrow dataset)
        ks (keys in-m)]
    (to-dataset
     (for [k ks i (range nrows) :when (not (= pivot-key k))]
       (zipmap [pivot-key :variable :value]
               [(nth (pivot-key in-m) i) k (nth (k in-m) i)])))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CATEGORICAL VARIABLES
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn categorical-var
  "
  Returns a categorical variable based on the values in the given collection.
  Equivalent to R's factor function.

  Options:
    :data (default nil) factors will be extracted from the given data.
    :ordered? (default false) indicates that the variable is ordinal.
    :labels (default (sort (into #{} data)))
    :levels (range (count labels))

  Examples:
    (categorical-var :data [:a :a :c :b :a :c :c])
    (categorical-var :labels [:a :b :c])
    (categorical-var :labels [:a :b :c] :levels [10 20 30])
    (categorical-var :levels [1 2 3])

  "
  ([& {:keys [data ordered? labels levels] :or {ordered? false}}]
    (let [labels (or labels
                     (if (nil? data)
                        levels
                        (sort (into #{} data))))
          levels (or levels (range (count labels)))]
      {:ordered? ordered?
       :labels labels
       :levels levels
       :to-labels (apply assoc {} (interleave levels labels))
       :to-levels (apply assoc {} (interleave labels levels))})))


(defn to-levels
  ([coll & options]
    (let [opts (when options (apply assoc {} options))
          cat-var (or (:categorical-var opts) (categorical-var :data coll))
          to-levels (:to-levels cat-var)]
      (for [label coll] (to-levels label)))))


(defn to-labels
  ([coll cat-var]
    (let [to-labels (:to-labels cat-var)]
      (for [level coll] (to-labels level)))))



(defn- get-dummies [n]
  (let [nbits (int (dec (Math/ceil (log2 n))))]
    (map #(for [i (range nbits -1 -1)] (if (bit-test % i) 1 0))
         (range n))))


(defn to-dummies [coll]
  (let [cat-var (categorical-var :data coll)
        levels (:levels cat-var)
        encoded-data (to-levels coll :categorical-var cat-var)
        bit-map (get-dummies (count levels))]
    (for [item encoded-data]
      (nth bit-map item))))


(defn- get-columns [dataset column-keys]
  (map (fn [col-key] (map #(% (get-column-id dataset col-key)) (:rows dataset))) column-keys))



(defn- string-to-categorical [dataset column-key dummies?]
  (let [col (first (get-columns dataset [column-key]))]

    (if (some string? col)
      (if dummies? (matrix (to-dummies col)) (matrix (to-levels col)))
      (matrix col))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn to-matrix
  "
  Converts a dataset into a matrix. Equivalent to R's as.matrix function
  for datasets.

  Options:
    :dummies (default false) -- if true converts non-numeric variables into sets
                                of binary dummy variables, otherwise converts
                                them into numeric codes.
  "
  ([dataset & {:keys [dummies] :or {dummies false}}]
    (reduce bind-columns
            (map #(string-to-categorical dataset % dummies)
                 (range (count (keys (:column-names dataset))))))))


;(defn- transpose-seq [coll]
;  (map (fn [idx] (map #(nth % idx) coll)) (range (count (first coll)))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; GAMMA BASED FUNCTIONS FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn gamma
  "
  Equivalent to R's gamma function.

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html
  "
  ([x]  (Gamma/gamma x)))


(defn beta
  "
  Equivalent to R's beta function.

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html
  "
  ([a b]  (Gamma/beta a b)))


(defn incomplete-beta
  "
  Returns the non-regularized incomplete beta value.

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html
  "

  ([x a b]  (* (Gamma/incompleteBeta a b x) (Gamma/beta a b))))



(defn regularized-beta
  "
  Returns the regularized incomplete beta value. Equivalent to R's pbeta function.

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html
    http://en.wikipedia.org/wiki/Regularized_incomplete_beta_function
    http://mathworld.wolfram.com/RegularizedBetaFunction.html
  "
  ([x a b]
    (Gamma/incompleteBeta a b x)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; SYMMETRIC MATRIX
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn solve-quadratic
  "
  Returns a vector with the solution to x from the quadratic
  equation, a*x^2 + b*x + c.

  Arguments:
    a, b, c: coefficients of a qaudratic equation.

  Examples:
    ;; -2*x^2 + 7*x + 15
    (quadratic-formula -2 7 15)
    ;; x^2 + -2*x + 1
    (quadratic-formula 1 -2 1)

  References:
    http://en.wikipedia.org/wiki/Quadratic_formula

  "
  ([a b c]
    (let [t1 (- 0 b)
          t2 (sqrt (- (* b b) (* 4 a c)))
          t3 (* 2 a)]
      [(/ (- t1 t2) t3)
       (/ (+ t1 t2) t3)])))



(defn symmetric-matrix
  "
  Returns a symmetric matrix from the given data, which represents the lower triangular elements
  ordered by row. This is not the inverse of half-vectorize which returns a vector of the upper-triangular
  values, unless the :lower option is set to false.

  Options:
    :lower (default true) -- lower-triangular. Set :lower to false to reverse the half-vectorize function.

  Examples:

    (use 'incanter.core)
    (symmetric-matrix [1
                       2 3
                       4 5 6
                       7 8 9 10])


    (half-vectorize
      (symmetric-matrix [1
                         2 3
                         4 5 6
                         7 8 9 10] :lower false))
  "
  ([data & {:keys [lower] :or {lower true}}]
    (let [n (count data)
          p (int (second (solve-quadratic 1/2 1/2 (- 0 n))))
          mat (matrix 0 p p)
          indices (if lower
                    (for [i (range p) j (range p) :when (<= j i)] [i j])
                    (for [i (range p) j (range p) :when (<= i j)] [j i]))]
      (doseq [idx (range n)]
        (let [[i j] (nth indices idx)
              res (nth data idx)
              d (if (and (matrix? res) (= 1 (nrow res)) (= 1 (ncol res)))
                (sel res 0 0)
                res)]
          (clx/set mat i j d)
          (clx/set mat j i d)))
      mat)))

(defn toeplitz
  "
  Returns the Toeplitz matrix for the given vector, which form the first row of the matrix
  "
  ([x]
    (symmetric-matrix
      (loop [v (rseq x)
             d []]
        (if (nil? v) d
            (recur (next v) (concat v d)))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; VIEW METHODS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmulti view
  "
  This is a general 'view' function. When given an Incanter matrix/dataset
  or a Clojure numeric collection, it will display it in a Java Swing
  JTable. When given an Incanter chart object, it will display it in a new
  window. When given a URL string, it will open the location with the
  platform's default web browser.

  When viewing charts, a :width (default 500) and :height (default 400)
  option can be provided.

  When viewing an incanter.processing sketch, set the :exit-on-close option
  to true (default is false) to kill the animation processes when you
  close the window (this will also kill your REPL or Swank server),
  otherwise those processing will continue to run in the background.



  Examples:

    (use '(incanter core stats datasets charts))

    ;; view matrices
    (def rand-mat (matrix (sample-normal 100) 4))
    (view rand-mat)

    ;; view numeric collections
    (view [1 2 3 4 5])
    (view (sample-normal 100))

    ;; view Incanter datasets
    (view (get-dataset :iris))

    ;; convert dataset to matrix, changing Species names to numeric codes
    (view (to-matrix (get-dataset :iris)))

    ;; convert dataset to matrix, changing Species names to dummy variables
    (view (to-matrix (get-dataset :iris) :dummies true))

    ;; view a chart
    (view (histogram (sample-normal 1000)) :width 700 :height 700)

    ;; view a URL
    (view \"http://incanter.org\")

    ;; view a PNG file
    (save (histogram (sample-normal 1000)) \"/tmp/norm_hist.png\")
    (view \"file:///tmp/norm_hist.png\")
  "
  (fn [obj & options] (cond
                        (and (not (matrix? obj))
                             (not (dataset? obj))
                             (not (map? obj))
                             (coll? obj))
                        ::coll
                        (.contains (str (type obj)) "processing.core.PApplet")
                          :sketch
                        :else
                          (type obj))))


;;(defmethod view ::coll ([obj & options] (view (matrix obj))))

(defmethod view ::coll
  ([obj & options]
    (let [rows (if (coll? (first obj))
                 obj
                 (map vector obj))
          colnames (range (count (first rows)))]
      (view (dataset colnames rows)))))

(defmethod view :incanter.core/matrix
  ([obj & {:keys [column-names]}]
    (let [col-names (or column-names (range (ncol obj)))
          m (ncol obj)
          n (nrow obj)]
     (doto (JFrame. "Incanter Matrix")
       (.add (JScrollPane.
               (JTable.
                 (cond
                   (and (> m 1) (> n 1))
                     (Vector. (map #(Vector. %) (to-list obj)))
                   (or (and (> m 1) (= n 1)) (and (= m 1) (= n 1)))
                     (Vector. (map #(Vector. %) [(to-list obj) []]))
                   (and (= m 1) (> n 1))
                     (Vector. (map #(Vector. [%]) (to-list obj))))
                                    (Vector. col-names))))
       (.setSize 400 600)
       (.setVisible true)))))


(defmethod view :incanter.core/dataset
  ([obj & options]
    (let [col-names (:column-names obj)
          column-vals (map (fn [row] (map #(row %) col-names)) (:rows obj))]
      (doto (JFrame. "Incanter Dataset")
        (.add (JScrollPane. (JTable. (Vector. (map #(Vector. %) column-vals))
                                     (Vector. col-names))))
        (.setSize 400 600)
        (.setVisible true)))))



(defmethod view javax.swing.JTable
  ([obj & options]
    (doto (javax.swing.JFrame. "Incanter Dataset")
      (.add (javax.swing.JScrollPane. obj))
      (.setSize 500 600)
      (.setVisible true))))


(defmethod view java.awt.Image
  ([obj & options]
    (let [icon (javax.swing.ImageIcon. obj)
          label (javax.swing.JLabel. icon)
          height (+ 15 (.getIconHeight icon))
          width (+ 15 (.getIconWidth icon))]
      (doto (javax.swing.JFrame. "Incanter Image")
        (.add (javax.swing.JScrollPane. label))
        (.setSize height width)
        .pack
        (.setVisible true)))))



;; URL view method code lifted from clojure.contrib.javadoc.browse/open-url-in-browser
(defmethod view String
  ([url]
    (try
      (when (clojure.lang.Reflector/invokeStaticMethod "java.awt.Desktop" "isDesktopSupported" (to-array nil))
        (-> (clojure.lang.Reflector/invokeStaticMethod "java.awt.Desktop" "getDesktop" (to-array nil))
            (.browse (java.net.URI. url)))
        url)
      (catch ClassNotFoundException e nil))))




(defn data-table
"Creates a javax.swing.JTable given an Incanter dataset."
  ([data]
   (let [col-names (:column-names data)
         column-vals (map (fn [row] (map #(row %) col-names)) (:rows data))
         table-model (javax.swing.table.DefaultTableModel. (java.util.Vector. (map #(java.util.Vector. %) column-vals))
                                                           (java.util.Vector. col-names))]

     (javax.swing.JTable. table-model))))



(defmulti set-data
  "
  Examples:

    (use '(incanter core charts datasets))

    (def data (get-dataset :iris))
    (def table (data-table data))
    (view table)
    ;; now view only a subset of the data
    (set-data table ($where {:Petal.Length {:gt 6}} data))


    ;; use sliders to dynamically select the query values
    (let [data (get-dataset :iris)
          table (data-table data)]
      (view table)
      (sliders [species [\"setosa\" \"virginica\" \"versicolor\"]
                min-petal-length (range 0 8 0.1)]
        (set-data table ($where {:Species species
                                 :Petal.Length {:gt min-petal-length}}
                                data))))

  "
  (fn [obj & more] (type obj)))


(defmethod set-data javax.swing.JTable
  ([table data]
     (let [col-names (:column-names data)
           column-vals (map (fn [row] (map #(row %) col-names)) (:rows data))
           table-model (javax.swing.table.DefaultTableModel. (java.util.Vector. (map #(java.util.Vector. %) column-vals))
                                                             (java.util.Vector. col-names))]
       (.setModel table table-model))))



(defn quit
  "Exits the Clojure shell."
  ([] (System/exit 0)))


(defn- count-types
  "
  Helper function. Takes in a seq (usually from a column from an Incanter dataset)
  and returns a map of types -> counts of the occurrence of each type
  "
  ([my-col]
    (reduce
      (fn [counts x]
        (let [t (type x) c (get counts t)] (assoc counts t (inc (if (nil? c) 0 c)))))
      {}
      my-col)))


(defn- count-col-types
  "
  Takes in a column name or number and a dataset. Returns a raw count
  of each type present in that column. Counts nils."
  ([col ds]
    (count-types ($ col ds))))


(defmulti save
  "
  Save is a multi-function that is used to write matrices, datasets and
  charts (in png format) to a file.

  Arguments:
    obj -- is a matrix, dataset, or chart object
    filename -- the filename to create.

  Matrix and dataset options:
    :delim (default \\,) column delimiter
    :header (default nil) an sequence of strings to be used as header line
        for matrices the default value is nil, for datasets, the default is
        the dataset's column-names array.
    :append (default false) determines whether this given file should be
        appended to. If true, a header will not be written to the file again.
    If the filename is exactly \"-\" then *out* the matrix/dataset will be
        written to *out*

  Chart options:
    :width (default 500)
    :height (default 400)


  Matrix Examples:

    (use '(incanter core io))
    (def A (matrix (range 12) 3)) ; creates a 3x4 matrix
    (save A \"A.dat\") ; writes A to the file A.dat, with no header and comma delimited
    (save A \"A.dat\" :delim \\tab) ; writes A to the file A.dat, with no header and tab delimited

    ;; writes A to the file A.dat, with a header and tab delimited
    (save A \"A.dat\" :delim \\, :header [\"col1\" \"col2\" \"col3\"])


  Dataset Example:

    (use '(incanter core io datasets))
    ;; read the iris sample dataset, and save it to a file.
    (def iris (get-dataset :iris))
    (save iris \"iris.dat\")


  Chart Example:

    (use '(incanter core io stats charts))
    (save (histogram (sample-normal 1000)) \"hist.png\")

    ;; chart example using java.io.OutputStream instead of filename
    (use '(incanter core stats charts))
    (import 'java.io.FileOutputStream)
    (def fos (FileOutputStream. \"/tmp/hist.png\"))
    (def hist (histogram (sample-normal 1000)))
    (save hist fos)
    (.close fos)

    (view \"file:///tmp/hist.png\")


  "
  (fn [obj filename & options]
    (if (.contains (str (type obj)) "processing.core.PApplet")
      :sketch
      (type obj))))




(defn grid-apply
  "
  Applies the given function f, that accepts two arguments, to a grid
  defined by rectangle bounded x-min, y-min, x-max, y-max and returns a
  sequence of three sequences representing the cartesian product of x and y
  and z calculated by applying f to the combinations of x and y.
  "
  ([f x-min x-max y-min y-max]
    (let [x-vals (range x-min x-max (/ (- x-max x-min) 100))
          y-vals (range y-min y-max (/ (- y-max y-min) 100))
          xyz (for [_x x-vals _y y-vals] [_x _y (f _x _y)])
          transpose #(list (conj (first %1) (first %2))
                           (conj (second %1) (second %2))
                           (conj (nth %1 2) (nth %2 2)))]
      (reduce transpose [[] [] []] xyz))))




(defop '- 60 'incanter.core/minus)
(defop '+ 60 'incanter.core/plus)
(defop '/ 80 'incanter.core/div)
(defop '* 80 'incanter.core/mult)
(defop '<*> 80 'incanter.core/mmult)
(defop '<x> 80 'incanter.core/kronecker)
(defop '** 100 'incanter.core/pow)

(defmacro $=
  "
  Formula macro translates from infix to prefix


  Examples:

    (use 'incanter.core)
    ($= 7 + 8)
    ($= [1 2 3] + [4 5 6])
    ($= [1 2 3] + (sin [4 5 6]))
    ($= [1 2 3] <*> (trans [1 2 3]))
    ($= [1 2 3] * [1 2 3])
    ($= [1 2 3] <x> [1 2 3])
    ($= 9 * 8 ** 3)
    ($= (sin Math/PI) * 10)

    ($= 10 + 20 * (4 - 5) / 6)

    ($= 20 * (4 - 5) / 6)

    (let [x 10
          y -5]
      ($= x + y / -10))

    ($= 3 ** 3)

    ($= [1 2 3] * [1 2 3])
    ($= [1 2 3] / (sq [1 2 3]) + [5 6 7])

    ($= (sqrt 5 * 5 + 3 * 3))
    ($= (sq [1 2 3] + [1 2 3]))
    ($= ((5 + 4) * 5))
    ($= ((5 + 4 * (3 - 4)) / (5 + 8) * 6))
    ($= [1 2 3] + 5)
    ($= (matrix [[1 2] [4 5]]) + 6)
    ($= (trans [[1 2] [4 5]]) + 6)

    ($= (trans [[1 2] [4 5]]) <*> (matrix [[1 2] [4 5]]))


    (use '(incanter core charts))
    (defn f [x] ($= x ** 2 + 3 * x + 5))
    (f 5)
    (view (function-plot f -10 10))
    (view (function-plot #($= % ** 2 + 3 * % + 5) -10 10))
    (view (function-plot (fn [x] ($= x ** 2 + 3 * x + 5)) -10 10))
    (let [x (range -10 10 0.1)]
      (view (xy-plot x ($= x ** 3 - 5 * x ** 2 + 3 * x + 5))))

    ($= (5 + 7))
    ($= (trans [1 2 3 4]) <*> [1 2 3 4])
    ($= [1 2 3 4] <*> (trans [1 2 3 4]))

    ($= [1 2 3 4] <*> (trans [1 2 3 4]))
    ($= [1 2 3 4] <x> (trans [1 2 3 4]))


    ;; kronecker product example
    ($= (matrix [[1 2] [3 4] [5 6]]) <x> 4)
    ($= (matrix [[1 2] [3 4] [5 6]]) <x> (matrix [[1 2] [3 4]]))
    ($= [1 2 3 4] <x> 4)

    ($= 3 > (5 * 2/7))

    (use '(incanter core datasets charts))
    (with-data (get-dataset :cars)
      (doto (scatter-plot :speed :dist :data ($where ($fn [speed dist] ($= dist / speed < 2))))
        (add-points :speed :dist :data ($where ($fn [speed dist] ($= dist / speed >= 2))))
        (add-lines ($ :speed) ($= 2 * ($ :speed)))
        view))

  "
  ([& equation]
    (infix-to-prefix equation)))


;; PRINT METHOD FOR INCANTER DATASETS
(defmethod print-method incanter.core.Dataset [o, ^java.io.Writer w]
  (binding [*out* w]
    (print-table (:column-names o) (:rows o))))

(comment ;; TODO
  (defn- block-diag2 [block0 block1]
    (.composeDiagonal DoubleFactory2D/dense block0 block1))
  (defn block-diag
    "Blocks should be a sequence of matrices."
    [blocks]
    (new Matrix (reduce block-diag2 blocks)))

  (defn block-matrix
    "Blocks should be a nested sequence of matrices. Each element of the sequence should be a block row."
    [blocks]
    (let [element-class (-> blocks first first class)
          native-rows (for [row blocks] (into-array element-class row))
          native-blocks (into-array (-> native-rows first class) native-rows)]
      (new Matrix (.compose DoubleFactory2D/dense native-blocks))))

  (defn separate-blocks
    "Partitions should be a sequence of [start,size] pairs."
    [matrix partitions]
    (for [p partitions]
      (for [q partitions]
        (.viewPart matrix (first p) (first q) (second p) (second q)))))

  (defn diagonal-blocks
    "Partitions should be a sequence of [start,size] pairs."
    [matrix partitions]
    (for [p partitions]
      (.viewPart matrix (first p) (first p) (second p) (second p))))
  )

(defn reorder-columns
  "
  Produce a new dataset with the columns in the specified order.
  Returns nil if no valid column names are given."
  [dset cols]
  (let [cols (filter (partial contains? (set (:column-names dset))) cols)]
    (cond
     (empty? cols) nil
     (= (count cols) 1) (dataset cols (sel dset :cols (first cols)))
     :else (sel dset :cols cols))))
