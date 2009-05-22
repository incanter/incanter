;;; core.clj -- Core functions built on the CERN Colt Library

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 11, 2009: First version



(ns incanter.core 
  (:use (incanter internal))
  (:import (incanter Matrix)
           (cern.colt.matrix.tdouble DoubleMatrix2D 
                                     DoubleFactory2D 
                                     DoubleFactory1D)
           (cern.colt.matrix.tdouble.algo DoubleAlgebra
                                          DoubleFormatter)
           (cern.colt.matrix.tdouble.algo.decomposition DoubleCholeskyDecomposition
                                                        DoubleSingularValueDecompositionDC
                                                        DoubleEigenvalueDecomposition
                                                        DoubleLUDecomposition 
                                                        DoubleQRDecomposition)
           (cern.jet.math.tdouble DoubleFunctions DoubleArithmetic)
           (cern.colt.function.tdouble DoubleDoubleFunction DoubleFunction)
           (cern.colt.list.tdouble DoubleArrayList)
           (cern.jet.stat.tdouble DoubleDescriptive Gamma)
           (javax.swing JTable JScrollPane JFrame)
           (java.util Vector)))


(defn matrix 
"
  Returns an instance of an incanter.Matrix, which is an extension of
  cern.colt.matrix.tdouble.impl.DenseColDoubleMatrix2D that implements the Clojure
  interface clojure.lang.ISeq. Therefore Clojure sequence operations can
  be applied to matrices. A matrix consists of a sequence of rows, where
  each row is a one-dimensional row matrix. One-dimensional matrices are,
  in turn, sequences of numbers. Equivalent to R's matrix function.

  Examples:
    (def A (matrix [[1 2 3] [4 5 6] [7 8 9]])) ; produces a 3x3 matrix
    (def A2 (matrix [1 2 3 4 5 6 7 8 9] 3)) ; produces the same 3x3 matrix
    (def B (matrix [1 2 3 4 5 6 7 8 9])) ; produces a 9x1 column vector

    (first A) ; produces a row matrix [1 2 3]
    (rest A) ; produces a sub matrix [[4 5 6] [7 8 9]]
    (first (first A)) ; produces 1.0
    (rest (first A)) ; produces a row matrix [2 3]

    ; since (plus row1 row2) adds the two rows element-by-element,
    (reduce plus A) ; produces the sums of the columns, 

    ; and since (sum row1) sums the elements of the row, 
    (map sum A) ; produces the sums of the rows, 

    ; you can filter the rows using Clojure's filter function
    (filter #(> (nth % 1) 4) A) ; returns the rows where the second column is greater than 4.

  References:
    http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/DoubleMatrix2D.html
    
"
  ([data]
   (make-matrix data))

  ([data ncol]
   (make-matrix data ncol))

  ([init-val rows cols]
   (make-matrix init-val rows cols)))


(defn matrix? 
  " Test if obj is 'derived' incanter.Matrix."
  ([obj] (is-matrix obj)))


(defn nrow 
  " Returns the number of rows in the given matrix. Equivalent to R's nrow function."
  ([mat]
   (cond 
    (matrix? mat) (.rows #^Matrix mat)
    (coll? mat) (count mat))))


(defn ncol 
  " Returns the number of columns in the given matrix. Equivalent to R's ncol function."
  ([mat]
   (cond 
    (matrix? mat) (.columns #^Matrix mat)
    (coll? mat) 1 )))


(defn identity-matrix 
"   Returns an n-by-n identity matrix.
    
    Examples:
      (identity-matrix 4)

"
   ([n] (Matrix. (.identity DoubleFactory2D/dense n))))


(defn diag 
"   If given a matrix, diag returns a sequence of its diagonal elements.
    If given a sequence, it returns a matrix with the sequence's elements 
    on its diagonal. Equivalent to R's diag function.
  
    Examples:
      (diag [1 2 3 4])

      (def A (matrix [[1 2 3]
                      [4 5 6]
                      [7 8 9]]))
      (diag A)


"
   ([m]
    (cond 
     (matrix? m)
      (seq (.toArray (.diagonal DoubleFactory2D/dense m)))
     (coll? m)
      (Matrix. (.diagonal DoubleFactory2D/dense (.make DoubleFactory1D/dense (double-array m)))))))


(defn #^Matrix trans 
"   Returns the transpose of the given matrix. Equivalent to R's t function

    Examples:
      (def A (matrix [[1 2 3] 
                     [4 5 6] 
                     [7 8 9]]))

      (trans A)
  
"
  ([mat]
   (cond 
    (matrix? mat)
      (.viewDice #^Matrix mat)
    (coll? mat)
      (.viewDice #^Matrix (matrix #^double-array mat)))))



(defn- except-for 
" Returns a lazy list of numbers ranging from 0 to n, except for the given exceptions.

  Examples:

    (except-for 10 3)
    (except-for 10 [5 7])

"
  ([n exceptions] 
    (let [except (if (coll? exceptions) exceptions [exceptions])]
      (for [i (range n) :when (reduce #(and %1 %2) (map #(not= i %) except))] i))))



(defmulti sel 
"
  Returns an element or subset of the given matrix, or dataset. 

  Argument:
    a matrix object or dataset.

  Options:
    :rows (default true) 
      returns all rows by default, can pass a row index or sequence of row indices
    :cols (default true) 
      returns all columns by default, can pass a column index or sequence of column indices
    :except-rows (default nil) can pass a row index or sequence of row indices to exclude
    :except-cols (default nil) can pass a column index or sequence of column indices to exclude
    :filter (default nil) 
      a function can be provided to filter the rows of the matrix

  Examples:
    (use 'incanter.datasets)
    (def speed (to-matrix (get-dataset :speed)))
    (sel speed 0 0) ; first element
    (sel speed :rows 0 :cols 0) ; also first element
    (sel speed :cols 0) ; first column of all rows
    (sel speed :cols [0 2]) ; first and third column of all rows
    (sel speed :rows (range 10) :cols (range 2)) ; first two rows of the first 10 columns
    (sel speed :rows (range 10)) ; all columns of the first 10 rows

    ;; exclude rows or columns
    (sel speed :except-rows (range 10)) ; all columns of all but the first 10 rows
    (sel speed :except-cols 1) ; all columns except the second

    ;; return only the first 10 even rows
    (sel speed :rows (range 10) :filter #(even? (int (nth % 0))))
    ;; select rows where distance (third column) is greater than 50
    (sel speed :filter #(> (nth % 2) 50))

    ;; examples with datasets
    (use 'incanter.datasets)
    (def us-arrests (get-dataset :us-arrests))
    (sel us-arrests :cols \"State\")

    (sel us-arrests :cols [\"State\" \"Murder\"])

"
(fn [mat & options] [(type mat) (keyword? (first options))]))




(defmethod sel [incanter.Matrix false]
  ([#^Matrix mat rows columns]
   (let [rws (if (number? rows) [rows] rows)
         cols (if (number? columns) [columns] columns)]
    (cond
      (and (number? rows) (number? columns))
        (.getQuick mat rows columns)
      (and (true? rws) (coll? cols))
        (.viewSelection mat (int-array (range (.rows mat))) (int-array cols))
      (and (coll? rws) (true? cols))
        (.viewSelection mat (int-array rws) (int-array (range (.columns mat))))
      (and (coll? rws) (coll? cols))
        (.viewSelection mat (int-array rws) (int-array cols))
      (and (true? rws) (true? cols))
        mat))))


(defmethod sel [incanter.Matrix true]
  ([#^Matrix mat & options]
   (let [opts (if options (apply assoc {} options) nil)
         except-rows (when (:except-rows opts) (:except-rows opts))
         except-columns (when (:except-cols opts) (:except-cols opts))
         ;rows (if (:rows opts) (:rows opts) true)
         rows (cond 
                (:rows opts) 
                  (:rows opts) 
                except-rows
                  (except-for (.rows mat) except-rows)
                :else
                  true)
         cols (cond 
                (:cols opts) 
                  (:cols opts) 
                except-columns
                  (except-for (.columns mat) except-columns)
                :else
                  true)
         row-filter (if (:filter opts) (:filter opts) nil)
         mat (if (nil? row-filter) mat (matrix (filter row-filter mat)))]
     (cond
       (and (number? rows) (number? cols))
         (.getQuick mat rows cols)
       (and (true? rows) (coll? cols))
         (.viewSelection mat (int-array (range (.rows mat))) (int-array cols))
       (and (true? rows) (number? cols))
         (.viewSelection mat (int-array (range (.rows mat))) (int-array [cols]))
       (and (coll? rows) (number? cols))
         (.viewSelection mat (int-array rows) (int-array [cols]))
       (and (coll? rows) (true? cols))
         (.viewSelection mat (int-array rows) (int-array (range (.columns mat))))
       (and (number? rows) (true? cols))
         (.viewSelection mat (int-array [rows]) (int-array (range (.columns mat))))
       (and (number? rows) (coll? cols))
         (.viewSelection mat (int-array [rows]) (int-array cols))
       (and (coll? rows) (coll? cols))
         (.viewSelection mat (int-array rows) (int-array cols))
       (and (true? rows) (true? cols))
         mat))))

    

(defn bind-rows 
"   Returns the matrix resulting from concatenating the given matrices 
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
        (and (matrix? A) (matrix? B))
          (conj A B)
        (and (matrix? A) (coll? B))
          (conj A B)
        (and (coll? A) (matrix? B))
          (conj (matrix A (count A)) B)
        (and (coll? A) (coll? B))
          (conj (matrix A (count A)) (matrix B (count B)))
        :else
          (throw (Exception. "Incompatible types")))) 
      args)))


(defn bind-columns 
"   Returns the matrix resulting from concatenating the given matrices 
    and/or sequences by their columns. Equivalent to R's cbind.

    Examples:
      (def A (matrix [[1 2 3] 
                     [4 5 6] 
                     [7 8 9]]))

      (def B (matrix [10 11 12]))

      (bind-columns A B)

      (bind-columns [1 2 3 4] [5 6 7 8])


"
  ([& args]
   (reduce 
    (fn [A B] (.viewDice (bind-rows (trans A) (trans B))))
    args)))


;(defn inner-product [& args] (apply + (apply map * args))) 
;(inner-product [1 2 3] [4 5 6]) ; = 32



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MATH FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(defn plus 
"   Performs element-by-element addition on multiple matrices, sequences, 
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
   ([& args] (reduce (fn [A B] (combine-with A B clojure.core/+ plus)) args)))


(defn minus 
"   Performs element-by-element subtraction on multiple matrices, sequences, 
    and/or numbers. If only a single argument is provided, returns the 
    negative of the given matrix, sequence, or number. Equivalent to R's - operator.


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
   ;([& args] (reduce (fn [A B] (combine-with A B clojure.core/- minus)) args)))
   ([& args] (if (= (count args) 1)
               (combine-with 0 (first args) clojure.core/- minus)
               (reduce (fn [A B] (combine-with A B clojure.core/- minus)) args))))



(defn mult 
"   Performs element-by-element multiplication on multiple matrices, sequences, 
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
   ([& args] (reduce (fn [A B] (combine-with A B clojure.core/* mult)) args)))


(defn div 
"   Performs element-by-element division on multiple matrices, sequences, 
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

"
   ([& args] (reduce (fn [A B] (combine-with A B clojure.core// div)) args)))


(defn pow 
  " This is an element-by-element exponent function, raising the first argument,
    by the exponents in the remaining arguments. Equivalent to R's ^ operator."
   ([& args] (reduce (fn [A B] (combine-with A B #(Math/pow %1 %2) pow)) args)))


(defn sqrt 
  "Returns the square-root of the elements in the given matrix, sequence or number.
   Equivalent to R's sqrt function."
   ([A] (pow A 1/2)))


(defn log 
  "Returns the natural log of the elements in the given matrix, sequence or number.
   Equvalent to R's log function."
   ([A] (transform-with A #(Math/log %) log)))


(defn log2 
  "Returns the log base 2 of the elements in the given matrix, sequence or number.
   Equivalent to R's log2 function."
   ([A] (transform-with A #(/ (Math/log %) (Math/log 2)) log2)))


(defn log10 
  "Returns the log base 10 of the elements in the given matrix, sequence or number.
   Equivalent to R's log10 function."
   ([A] (transform-with A #(Math/log10 %) (lg 10.0))))


(defn exp 
  "Returns the exponential of the elements in the given matrix, sequence or number.
   Equivalent to R's exp function."
   ([A] (transform-with A #(Math/exp %) exp)))


(defn abs 
  "Returns the absolute value of the elements in the given matrix, sequence or number.
   Equivalent to R's abs function."
   ([A] (transform-with A #(Math/abs %) abs)))


(defn sin 
  "Returns the sine of the elements in the given matrix, sequence or number.
   Equivalent to R's sin function."
   ([A] (transform-with A #(Math/sin %) sin)))


(defn asin 
  "Returns the arc sine of the elements in the given matrix, sequence or number.
   Equivalent to R's asin function."
   ([A] (transform-with A #(Math/asin %) asin)))


(defn cos 
  "Returns the cosine of the elements in the given matrix, sequence or number.
   Equivalent to R's cos function."
   ([A] (transform-with A #(Math/cos %) cos)))


(defn acos 
  "Returns the arc cosine of the elements in the given matrix, sequence or number.
   Equivalent to R's acos function."
   ([A] (transform-with A #(Math/acos %) acos)))


(defn tan 
  "Returns the tangent of the elements in the given matrix, sequence or number.
   Equivalent to R's tan function."
   ([A] (transform-with A #(Math/tan %) tan)))


(defn atan 
  "Returns the arc tangent of the elements in the given matrix, sequence or number.
   Equivalent to R's atan function."
   ([A] (transform-with A #(Math/atan %) atan)))


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
  ([k] (DoubleArithmetic/factorial k)))



(defn choose 
"
  Returns number of k-combinations (each of size k) from a set S with 
  n elements (size n), which is the binomial coefficient (also known 
  as the 'choose function') [wikipedia]
        choose = n!/(k!(n - k)!)

  Equivalent to R's choose function.

  Examples:
    (choose 25 6) ; => 2,598,960

  References:
    http://incanter.org/docs/parallelcolt/api/cern/jet/math/tdouble/DoubleArithmetic.html
    http://en.wikipedia.org/wiki/Combination

"
  ([n k] (DoubleArithmetic/binomial (double n) (long k))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MATRIX FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn to-list 
  " Returns a list-of-lists if the given matrix is two-dimensional,
    and a flat list if the matrix is one-dimensional."
 ([#^Matrix mat] 
  (cond
    (and (coll? mat) (not (matrix? mat)))
      mat 
    (= (.columns mat) 1)
      (first (map #(seq %) (seq (.toArray (.viewDice mat)))))
    (= (.rows mat) 1)
      (first (map #(seq %) (seq (.toArray mat))))
    :else
      (map #(seq %) (seq (.toArray mat))))))


(defn #^Matrix copy 
  "Returns a copy of the given matrix."
  ([#^Matrix mat] (.copy mat)))


(defn mmult 
  " Returns the matrix resulting from the matrix multiplication of the
    the given arguments. Equivalent to R's %*% operator.

    Examples:
  
      (def A (matrix [[1 2 3] 
                      [4 5 6] 
                      [7 8 9]]))
      (mmult A (trans A))
      (mmult A (trans A) A)

    References:
      http://en.wikipedia.org/wiki/Matrix_multiplication
      http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/DoubleMatrix2D.html

  "
    ([& args]
     (reduce (fn [A B]
              (let [a (if (matrix? A) A (matrix A))
                    b (if (matrix? B) B (matrix B))
                    result (Matrix. (.zMult #^Matrix a #^Matrix b nil))]
                (if (and (= (.rows result) 1) (= (.columns result) 1))
                  (.getQuick result 0 0)
                  result))) 
            args)))


(defn solve 
" Returns a matrix solution if A is square, least squares solution otherwise.
  Equivalent to R's solve function.
  
  Examples:
    (solve (matrix [[2 0 0] [0 2 0] [0 0 2]]))

  References:
    http://en.wikipedia.org/wiki/Matrix_inverse
    

"
  ([#^Matrix A & B]
   (if B
    (Matrix. (.solve (DoubleAlgebra.) A (first B)))
    (Matrix. (.inverse (DoubleAlgebra.) A)))))



(defn det 
" Returns the determinant of the given matrix using LU decomposition. Equivalent
  to R's det function.

  References:
    http://en.wikipedia.org/wiki/LU_decomposition
    http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleLUDecomposition.html
"
  ;([mat] (.det (cern.colt.matrix.linalg.LUDecomposition. mat))))
  ([mat] (.det DoubleAlgebra/DEFAULT mat)))


(defn trace 
" Returns the trace of the given matrix.

  References:
    http://en.wikipedia.org/wiki/Matrix_trace
    http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/DoubleAlgebra.html
"
  ([mat] (.trace DoubleAlgebra/DEFAULT mat)))



(defn vectorize 
  " Returns the vectorization (i.e. vec) of the given matrix.
    The vectorization of an m-by-n matrix A, denoted by vec(A), 
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
   ;(reduce #(concat %1 (to-list %2)) '() (trans mat))))


(defn half-vectorize 
  " Returns the half-vectorization (i.e. vech) of the given matrix.
    The half-vectorization, vech(A), of a symmetric nxn matrix A 
    is the n(n+1)/2 x 1 column vector obtained by vectorizing only 
    the lower triangular part of A.

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
    (let [xx (if (or (nil? x) (empty? x)) [0] (to-list x))]
      (DoubleDescriptive/sumOfSquares (DoubleArrayList. (double-array xx))))))


(defn sum 
  "Returns the sum of the given sequence."
  ([x]
    (let [xx (if (or (nil? x) (empty? x)) [0] (to-list x))]
      (DoubleDescriptive/sum (DoubleArrayList. (double-array xx))))))


(defn prod 
  "Returns the product of the given sequence."
  ([x]
    (let [xx (if (or (nil? x) (empty? x)) [0] (to-list x))]
      (DoubleDescriptive/product (DoubleArrayList. (double-array xx))))))



(defn cumulative-sum 
  " Returns a sequence of cumulative sum for the given collection. For instance 
    The first value equals the first value of the argument, the second value is
    the sum of the first two arguments, the third is the sum of the first three
    arguments, etc.

    Examples:
      (cumulative-sum (range 100))
  "
  ([coll] 
   (let [n (count coll)]
    (loop [in-coll (rest coll)
           cumu-sum [(first coll)]]
      (if (empty? in-coll)
        cumu-sum
        (recur (rest in-coll) (conj cumu-sum (+ (last cumu-sum) (first in-coll)))))))))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MATRIX DECOMPOSITION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn #^Matrix decomp-cholesky
" Returns the Cholesky decomposition of the given matrix. Equivalent to R's
  chol function.

  Returns:
    a matrix of the triangular factor (note: the result from
    cern.colt.matrix.linalg.CholeskyDecomposition is transposed so 
    that it matches the result return from R's chol function.

  References:
    http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleCholeskyDecomposition.html
    http://en.wikipedia.org/wiki/Cholesky_decomposition
"
  ([#^Matrix mat]
    (.viewDice (.getL (DoubleCholeskyDecomposition. mat)))))
    ;(Matrix. (.viewDice (.getL (CholeskyDecomposition. mat))))) 



(defn decomp-svd
" Returns the Singular Value Decomposition (SVD) of the given matrix. Equivalent to
  R's svd function.

  Returns:
    a map containing:
      :S -- the diagonal matrix of singular values
      :U -- the left singular vectors U
      :V -- the right singular vectors V

  References:
    http://en.wikipedia.org/wiki/Singular_value_decomposition
    http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleSingularValueDecompositionDC.html
"
  ([mat]
    (let [result (DoubleSingularValueDecompositionDC. mat, true, true)]
      {:S (diag (Matrix. (.getS result)))
       :U (Matrix. (.getU result))
       :V (Matrix. (.getV result))})))



(defn decomp-eigenvalue
" Returns the Eigenvalue Decomposition of the given matrix. Equivalent to R's eig function.

  Returns:
    a map containing:
      :values -- vector of eigenvalues
      :vectors -- the matrix of eigenvectors

  References:
    http://en.wikipedia.org/wiki/Eigenvalue_decomposition
    http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleEigenvalueDecomposition.html
"
  ([mat]
    (let [result (DoubleEigenvalueDecomposition. mat)]
      {:values (diag (Matrix. (.getD result)))
       :vectors (Matrix. (.getV result))})))


(defn decomp-lu
" Returns the LU decomposition of the given matrix.

  Returns:
    a map containing:
      :L -- the lower triangular factor
      :U -- the upper triangular factor

  References:
    http://en.wikipedia.org/wiki/LU_decomposition
    http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleLUDecomposition.html
"
  ([mat]
    (let [result (DoubleLUDecomposition. mat)]
      {:L (Matrix. (.getL result))
       :U (Matrix. (.getU result))})))


(defn decomp-qr
" Returns the QR decomposition of the given matrix. Equivalent to R's qr function.

  Returns:
    a map containing:
      :Q -- orthogonal factor
      :R -- the upper triangular factor

  References:
    http://en.wikipedia.org/wiki/QR_decomposition
    http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleQRDecomposition.html
"
  ([mat]
    (let [result (DoubleQRDecomposition. mat)]
      {:Q (Matrix. (.getQ result))
       :R (Matrix. (.getR result))})))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MISC FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn to-vect  
  " Returns a vector-of-vectors if the given matrix is two-dimensional,
    and a flat vector if the matrix is one-dimensional. This is a bit
    slower than the to-list function. "
 ([#^Matrix mat]
  (into [] (cond
             (= (.columns mat) 1)
              (first (map #(into [] (seq %)) (seq (.toArray (.viewDice mat)))))
             (= (.rows mat) 1)
              (first (map #(into [] (seq %)) (seq (.toArray mat))))
             :else
              (map #(into [] (seq %)) (seq (.toArray mat)))))))


(defn length 
  " A version of count that works on collections, matrices, and numbers. 
    The length of a number is one, the length of a collection is its count,
    and the length of a matrix is the number of elements it contains (nrow*ncol).
    Equivalent to R's length function.
  "
  ([coll]
    (cond
      (number? coll) 
        1
      (matrix? coll)
        (* (.rows #^Matrix coll) (.columns #^Matrix coll))
      (coll? coll)
        (count coll)
      :else
        (throw (Exception. "Argument must be a collection or matrix!")))))
      


(defn group-by
" Groups the given matrix by the values in the columns indicated by the 
  'on-cols' argument, returning a sequence of matrices. The returned
  matrices are sorted by the value of the group column ONLY when there
  is only a single (non-vector) on-col argument.

  Examples:

    (use '(incanter core datasets))
    (def plant-growth (to-matrix (get-dataset :plant-growth)))
    (group-by plant-growth 1)
    ;; only return the first column
    (group-by plant-growth 1 :cols 0)
    ;; don't return the second column
    (group-by plant-growth 1 :except-cols 1)

    (def plant-growth-dummies (to-matrix (get-dataset :plant-growth) :dummies true))
    (group-by plant-growth-dummies [1 2])
    ;; return only the first column
    (group-by plant-growth-dummies [1 2] :cols 0) 
    ;; don't return the last two columns
    (group-by plant-growth-dummies [1 2] :except-cols [1 2])

    ;; plot the plant groups
    (use 'incanter.charts)
    ;; can use destructuring if you know the number of groups,
    ;; groups are sorted only if the group is based on a single column value
    (let [[ctrl trt1 trt2] (group-by plant-growth 1 :cols 0)]
      (doto (box-plot ctrl)
            (add-box-plot trt1)
            (add-box-plot trt2)
            view))

"
  ([mat on-cols & options]
    (let [opts (if options (apply assoc {} options) nil)
          cols (when (:cols opts) (:cols opts))
          except-cols (when (:except-cols opts) (:except-cols opts))
          groups (if (coll? on-cols)
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
          (map #(sel mat :cols cols :filter (filter-fn %)) groups)
        except-cols
          (map #(sel mat :except-cols except-cols :filter (filter-fn %)) groups)
        :else
          (map #(sel mat :filter (filter-fn %)) groups)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DATASET FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn dataset 
" Returns a map of type ::dataset constructed from the given column-names and
  data. The data is a sequence of sequences.
"
  ([column-names & data] 
    (with-meta 
      {:column-names column-names
      :rows (map #(apply assoc {} (interleave column-names %)) (first data))}
      {:type ::dataset})))


(defn dataset?
" Determines if obj is of type ::dataset."
  ([obj] (= (type obj) ::dataset)))


(defn- get-column-id [dataset column-key]
  (let [headers (:column-names dataset)
        id (if (number? column-key)
             (if (some #(= column-key %) headers)
               column-key
               (nth headers column-key))
             column-key)]
    id))



(defmethod sel [::dataset true]
  ([dataset & options]
    (let [opts (if options (apply assoc {} options) nil)
          rows (if (:rows opts) (:rows opts) true)
          cols (if (:cols opts) (if (coll? (:cols opts))
                                     (:cols opts) 
                                     [(:cols opts)])
                 (:column-names dataset))
          row-filter (if (:filter opts) (:filter opts) nil)
          selected-rows (cond 
                          (true? rows) (:rows dataset) 
                          (number? rows) (list (nth (:rows dataset) rows))
                          (coll? rows) (map #(nth (:rows dataset) %) rows)) ]
      ;(map (fn [col-key] (map #(% (get-column-id dataset col-key)) selected-rows)) cols))))
      (map (fn [row] (map #(row (get-column-id dataset %)) cols)) selected-rows))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CATEGORICAL VARIABLES
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn categorical-var
" Returns a categorical variable based on the values in the given collection.
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
  ([& args]
   (let [opts (if args (apply assoc {} args) nil)
         data (if (:data opts) (:data opts) nil)
         ordered? (if (false? (:ordered? opts)) true false)
         labels (if (:labels opts) 
                  (:labels opts) 
                  (if (nil? data)
                    (:levels opts)
                    (sort (into #{} data))))
         levels (if (:levels opts) (:levels opts) (range (count labels)))]
    {:ordered? ordered?
     :labels labels
     :levels levels
     :to-labels (apply assoc {} (interleave levels labels))
     :to-levels (apply assoc {} (interleave labels levels))})))


(defn to-levels 
"
"
  ([coll & options]
    (let [opts (if options (apply assoc {} options) nil)
          cat-var (if (:categorical-var opts) (:categorical-var opts) (categorical-var :data coll))
          to-levels (:to-levels cat-var)]
      (for [label coll] (to-levels label)))))


(defn to-labels 
"
"
  ([coll cat-var] 
    (let [to-labels (:to-labels cat-var)]
      (for [level coll] (to-labels level)))))



(defn- get-dummies [n]
  (let [nbits (dec (Math/ceil (log2 n)))]
    (map #(for [i (range nbits -1 -1)] (if (bit-test % i) 1 0))
         (range n))))


(defn to-dummies [coll]
  (let [cat-var (categorical-var :data coll)
        levels (:levels cat-var)
        encoded-data (to-levels coll :categorical-var cat-var)
        bit-map (get-dummies (count levels))]
    (for [item encoded-data] (nth bit-map item))))


(defn- get-columns [dataset column-keys]
  (map (fn [col-key] (map #(% (get-column-id dataset col-key)) (:rows dataset))) column-keys))



(defn- string-to-categorical [dataset column-key dummies?]
  (let [col (first (get-columns dataset [column-key]))]
    (if (some string? col) 
      (if dummies? (matrix (to-dummies col)) (matrix (to-levels col)))
      (matrix col))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn to-matrix 
"  Converts a dataset into a matrix. Equivalent to R's as.matrix function
   for datasets.
"
  ([dataset & options]
    (let [opts (if options (apply assoc {} options) nil)
          dummies? (if (true? (:dummies opts)) true false)]
      (reduce bind-columns 
              (map #(string-to-categorical dataset % dummies?) 
                    (range (count (keys (:column-names dataset)))))))))


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
  ordered by row. This is not the inverse of half-vectorize which returns a vector ordered
  by columns, unless the :by-row option is set to false.

  Options:
    :by-row (default true) -- if false, make symmetric-behavior the inverse of half-vectorize.

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
                         7 8 9 10] :by-row false))


"
  ([data & options]
   (let [opts (if options (apply assoc {} options) nil)
         by-row? (if (false? (:by-row opts)) false true)
         n (count data)
         p (int (second (solve-quadratic 1/2 1/2 (- 0 n))))
         mat (incanter.Matrix. p p 0)
         indices (if by-row?
                   (for [i (range p) j (range p) :when (<= j i)] [i j])
                   (for [i (range p) j (range p) :when (<= i j)] [j i]))]
     (doseq [idx (range n)]
      (let [[i j] (nth indices idx)]
        (.set mat i j (nth data idx))
        (.set mat j i (nth data idx))))
     mat))) 


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; VIEW METHODS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmulti view 
  " This is a general 'view' function. When given an Incanter matrix/dataset 
    or a Clojure numeric collection, it will display it in a Java Swing 
    JTable. When given an Incanter chart object, it will display it in a new 
    window. When given a URL string, it will open the location with the 
    platform's default web browser.

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
      (view (histogram (sample-normal 1000)))

      ;; view a URL
      (view \"http://incanter.org\")

      ;; view a PNG file
      (save (histogram (sample-normal 1000)) \"/tmp/norm_hist.png\")
      (view \"file:///tmp/norm_hist.png\")


"
  (fn [obj & options] (if (and (not (matrix? obj)) 
                               (not (dataset? obj))
                               (coll? obj))
                        ::coll 
                        (type obj))))


(defmethod view ::coll ([obj & options] (view (matrix obj))))


(defmethod view incanter.Matrix
  ([obj & options] 
    (let [opts (if options (apply assoc {} options) nil)
          column-names (if (:column-names opts) (:column-names opts) (range (ncol obj)))
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
                                     (Vector. column-names))))
        (.setSize 400 600)
        (.setVisible true)))))


(defmethod view :incanter.core/dataset
  ([obj & options] 
   (let [column-names (:column-names obj)
         column-vals (map (fn [row] (map #(row %) column-names)) (:rows obj))]
     (doto (JFrame. "Incanter Dataset")
       (.add (JScrollPane. (JTable. (Vector. (map #(Vector. %) column-vals)) 
                                    (Vector. column-names))))
       (.setSize 400 600)
       (.setVisible true)))))




;; URL view method code lifted from clojure.contrib.javadoc.browse/open-url-in-browser 
(defmethod view java.lang.String
  ([url]
    (try 
      (when (clojure.lang.Reflector/invokeStaticMethod "java.awt.Desktop" "isDesktopSupported" (to-array nil))
        (-> (clojure.lang.Reflector/invokeStaticMethod "java.awt.Desktop" "getDesktop" (to-array nil))
            (.browse (java.net.URI. url)))
        url)
      (catch ClassNotFoundException e nil))))    



(defn quit
" Exits the Clojure shell." 
  ([] (System/exit 0)))



(defmulti save 
" Save is a multi-function that is used to write matrices, datasets and 
  charts (in png format) to a file.

  Arguments:
    obj -- is a matrix, dataset, or chart object
    filename -- the filename to create.

  Matrix and dataset options:
    :delim (default \\,) column delimiter 
    :header (default nil) an sequence of strings to be used as header line,
        for matrices the default value is nil, for datasets, the default is
        the dataset's column-names array.
    :append (default false) determines whether this given file should be 
        appended to. If true, a header will not be written to the file again.

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
  

"
  (fn [obj filename & options] (type obj)))



