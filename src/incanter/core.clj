;;; matrix.clj -- Matrix library for Clojure built on the CERN Colt Library

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
  (:import (incanter Matrix)
           (cern.colt.matrix DoubleMatrix2D 
                             DoubleFactory2D 
                             DoubleFactory1D)
           (cern.colt.matrix.linalg CholeskyDecomposition
                                    Algebra)
           (cern.colt.matrix.doublealgo Formatter)
           (cern.jet.math Functions Arithmetic)
           (cern.colt.function DoubleDoubleFunction DoubleFunction)))

;;(derive DoubleMatrix2D ::matrix) ; commented out to track down non-ISeq matrices
(derive Matrix ::matrix)


(defn matrix 
"
  Returns an instance of an incanter.Matrix, which is an extension of
  cern.colt.matrix.impl.DenseDoubleMatrix2D that implements the Clojure
  interface clojure.lang.ISeq. Therefore Clojure sequence operations can
  be applied to matrices. A matrix consists of a sequence of rows, where
  each row is a one-dimensional row matrix. One-dimensional matrices are,
  in turn, sequences of numbers.

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
    http://acs.lbl.gov/~hoschek/colt/api/cern/colt/matrix/DoubleMatrix2D.html
    
"
  ([data]
   (cond 
     (coll? (first data)) 
      (Matrix. (into-array (map double-array data)))
     (number? (first data)) 
      ;;(Matrix. data (count data) 1)))
      (Matrix. (double-array data))))
  ([data ncol]
    (cond
      (coll? data)
        ;;(Matrix. data (/ (count data) ncol) ncol)
        (Matrix. (double-array data) ncol)
       (number? data)
        (Matrix. data ncol))) ; data is the number of rows in this case
  ([init-val rows cols]
    (Matrix. rows cols init-val)))


(defn matrix? 
  " Test if obj is 'derived' from ::matrix (e.g. class incanter.Matrix)."
  ([obj] (isa? (class obj) ::matrix)))


(defn nrow 
  " Returns the number of rows in the given matrix"
  ([mat]
   (cond 
    (matrix? mat) (.rows #^Matrix mat)
    (coll? mat) (count mat))))


(defn ncol 
  " Returns the number of columns in the given matrix"
  ([mat]
   (cond 
    (matrix? mat) (.columns #^Matrix mat)
    (coll? mat) 1 )))


(defn identity-matrix 
  "Returns an n-by-n identity matrix."
   ([n] (Matrix. (.identity DoubleFactory2D/dense n))))


(defn diag 
  " If given a matrix, diag returns a sequence of its diagonal elements.
    If given a sequence, it returns a matrix with the sequence's elements 
    on its diagonal. "
   ([m]
    (cond 
     (matrix? m)
      (into [] (seq (.toArray (.diagonal DoubleFactory2D/dense m))))
     (coll? m)
      (Matrix. (.diagonal DoubleFactory2D/dense (.make DoubleFactory1D/dense (double-array m)))))))


(defn #^Matrix trans 
  "Returns the transpose of the given matrix."
  ([mat]
   (cond 
    (matrix? mat)
      (.viewDice #^Matrix mat)
    (coll? mat)
      (.viewDice #^Matrix (matrix #^double-array mat)))))


(defn sel 
  "If rows and columns are numbers, sel (i.e. select) returns the corresponding element 
  of the given matrix. If rows and/or columns are sequences, sel returns the corresponding
  submatrix of mat. If rows is the value 'true', all the rows corresponding to the values
  of columns are returned. If columns is the value 'true', all the columns corresponding to
  the values of rows are returned.
  "
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



(defn bind-rows 
  " Returns the matrix resulting from concatenating the given matrices 
    and/or sequences by their rows"
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
  " Returns the matrix resulting from concatenating the given matrices 
    and/or sequences by their columns"
  ([& args]
   (reduce 
    (fn [A B] (.viewDice (bind-rows (trans A) (trans B))))
    args)))


;(defn inner-product [& args] (apply + (apply map * args))) 
;(inner-product [1 2 3] [4 5 6]) ; = 32



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MATH FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defmacro #^Matrix -transform-with [A op fun]
  `(cond 
    (matrix? ~A)
      (.assign #^Matrix (.copy #^Matrix ~A) #^DoubleFunction (. Functions ~fun))
    (coll? ~A)
      (map ~op ~A)
    (number? ~A)
      (~op ~A)))
    

(defmacro -combine-with [A B op fun]
  `(if (and (number? ~A) (number? ~B))
    (~op ~A ~B)
      (cond 
       (and (matrix? ~A) (matrix? ~B))
         (.assign #^Matrix (.copy #^Matrix ~A) #^Matrix ~B #^DoubleDoubleFunction (. Functions ~fun))
       (and (matrix? ~A) (number? ~B))
         (.assign #^Matrix (.copy #^Matrix ~A) #^DoubleDoubleFunction (. Functions (~fun ~B)))
       (and (number? ~A) (matrix? ~B))
         (.assign #^Matrix (matrix ~A (.rows ~B) (.columns ~B)) #^Matrix ~B #^DoubleDoubleFunction (. Functions ~fun))
       (and (coll? ~A) (matrix? ~B))
         (.assign #^Matrix (matrix ~A (.rows ~B) (.columns ~B)) #^Matrix (matrix ~B) #^DoubleDoubleFunction (. Functions ~fun))
       (and (matrix? ~A) (coll? ~B))
         (.assign #^Matrix (.copy ~A) #^Matrix (matrix ~B) #^DoubleDoubleFunction (. Functions ~fun))
       (and (coll? ~A) (coll? ~B)) 
         (map ~op ~A ~B) 
       (and (number? ~A) (coll? ~B)) 
         (.assign #^Matrix (matrix ~A (nrow ~B) (ncol ~B)) #^Matrix (matrix ~B) #^DoubleDoubleFunction (. Functions ~fun))
       (and (coll? ~A) (number? ~B)) 
         (.assign #^Matrix (matrix ~A) #^Matrix (matrix ~B (nrow ~A) (ncol ~A)) #^DoubleDoubleFunction (. Functions ~fun)))))
    


(defn plus 
  " Performs element-by-element addition on multiple matrices, sequences, and/or numbers."
   ([& args] (reduce (fn [A B] (-combine-with A B clojure.core/+ plus)) args)))


(defn minus 
  " Performs element-by-element subtraction on multiple matrices, sequences, and/or numbers."
   ([& args] (reduce (fn [A B] (-combine-with A B clojure.core/- minus)) args)))


(defn mult 
  " Performs element-by-element multiplication on multiple matrices, sequences, and/or numbers."
   ([& args] (reduce (fn [A B] (-combine-with A B clojure.core/* mult)) args)))


(defn div 
  " Performs element-by-element division on multiple matrices, sequences, and/or numbers."
   ([& args] (reduce (fn [A B] (-combine-with A B clojure.core// div)) args)))


(defn pow 
  " This is an element-by-element exponent function, raising the first argument,
    by the exponents in the remaining arguments."
   ([& args] (reduce (fn [A B] (-combine-with A B #(Math/pow %1 %2) pow)) args)))


(defn sqrt 
  "Returns the square-root of the elements in the given matrix, sequence or number."
   ([A] (pow A 1/2)))


(defn log 
  "Returns the natural log of the elements in the given matrix, sequence or number."
   ([A] (-transform-with A #(Math/log %) log)))


(defn log2 
  "Returns the log base 2 of the elements in the given matrix, sequence or number."
   ([A] (-transform-with A #(/ (Math/log %) (Math/log 2)) log2)))


(defn log10 
  "Returns the log base 10 of the elements in the given matrix, sequence or number."
   ([A] (-transform-with A #(Math/log10 %) (lg 10.0))))


(defn exp 
  "Returns the exponential of the elements in the given matrix, sequence or number."
   ([A] (-transform-with A #(Math/exp %) exp)))


(defn factorial 
"
  Returns the factorial of k (k must be a positive integer).

  Examples:
    (factorial 6)

  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/math/Arithmetic.html
    http://en.wikipedia.org/wiki/Factorial

"
  ([k] (cern.jet.math.Arithmetic/factorial k)))



(defn n-choose-k 
"
  Returns number of k-combinations (each of size k) from a set S with 
  n elements (size n), which is the binomial coefficient (also known 
  as the 'choose function') [wikipedia]
        n-choose-k = n!/(k!(n - k)!)

  Examples:
    (n-choose-k 25 6) ; => 2,598,960

  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/jet/math/Arithmetic.html
    http://en.wikipedia.org/wiki/Combination

"
  ([n k] (Arithmetic/binomial (double n) (long k))))


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
    the given arguments.
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
  "Returns a matrix solution if A is square, least squares solution otherwise."
  ([#^Matrix A & B]
   (if B
    (Matrix. (.solve (Algebra.) A (first B)))
    (Matrix. (.inverse (Algebra.) A)))))



(defn det 
" Returns the determinant of the given matrix using LU decomposition.

  References:
    http://en.wikipedia.org/wiki/LU_decomposition
    http://acs.lbl.gov/~hoschek/colt/api/cern/colt/matrix/linalg/LUDecomposition.html
"
  ;([mat] (.det (cern.colt.matrix.linalg.LUDecomposition. mat))))
  ([mat] (.det cern.colt.matrix.linalg.Algebra/DEFAULT mat)))


(defn trace 
" Returns the trace of the given matrix.

  References:
    http://en.wikipedia.org/wiki/Matrix_trace
    http://acs.lbl.gov/~hoschek/colt/api/cern/colt/matrix/linalg/Algebra.html
"
  ([mat] (.trace cern.colt.matrix.linalg.Algebra/DEFAULT mat)))



(defn vectorize 
  " Returns the vectorization (i.e. vec) of the given matrix.
    The vectorization of an m×n matrix A, denoted by vec(A), 
    is the mn × 1 column vector obtain by stacking the columns 
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
   (reduce #(concat %1 (to-list %2)) '() (trans mat))))


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




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MATRIX DECOMPOSITION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn #^Matrix decomp-cholesky
" Returns the Cholesky decomposition of the given matrix.

  Returns:
    a matrix of the triangular factor (note: the result from
    cern.colt.matrix.linalg.CholeskyDecomposition is transposed so 
    that it matches the result return from R's chol function.

  References:
    http://acs.lbl.gov/~hoschek/colt/api/cern/colt/matrix/linalg/CholeskyDecomposition.html
    http://en.wikipedia.org/wiki/Cholesky_decomposition
"
  ([#^Matrix mat]
    (.viewDice (.getL (CholeskyDecomposition. mat)))))
    ;(Matrix. (.viewDice (.getL (CholeskyDecomposition. mat))))) 



(defn decomp-svd
" Returns the Singular Value Decomposition (SVD) of the given matrix.

  Returns:
    a map containing:
      :S -- the diagonal matrix of singular values
      :U -- the left singular vectors U
      :V -- the right singular vectors V

  References:
    http://en.wikipedia.org/wiki/Singular_value_decomposition
    http://acs.lbl.gov/~hoschek/colt/api/cern/colt/matrix/linalg/SingularValueDecomposition.html
"
  ([mat]
    (let [result (cern.colt.matrix.linalg.SingularValueDecomposition. mat)]
      {:S (Matrix. (.getS result))
       :U (Matrix. (.getU result))
       :V (Matrix. (.getV result))})))



(defn decomp-eigenvalue
" Returns the Eigenvalue Decomposition of the given matrix.

  Returns:
    a map containing:
      :eigenvalues -- vector of eigenvalues
      :V -- the matrix of eigenvectors

  References:
    http://en.wikipedia.org/wiki/Eigenvalue_decomposition
    http://acs.lbl.gov/~hoschek/colt/api/cern/colt/matrix/linalg/EigenvalueDecomposition.html
"
  ([mat]
    (let [result (cern.colt.matrix.linalg.EigenvalueDecomposition. mat)]
      {:eigenvalues (diag (Matrix. (.getD result)))
       :V (Matrix. (.getV result))})))


(defn decomp-lu
" Returns the LU decomposition of the given matrix.

  Returns:
    a map containing:
      :L -- the lower triangular factor
      :U -- the upper triangular factor

  References:
    http://en.wikipedia.org/wiki/LU_decomposition
    http://acs.lbl.gov/~hoschek/colt/api/cern/colt/matrix/linalg/LUDecomposition.html
"
  ([mat]
    (let [result (cern.colt.matrix.linalg.LUDecomposition. mat)]
      {:L (Matrix. (.getL result))
       :U (Matrix. (.getU result))})))


(defn decomp-qr
" Returns the QR decomposition of the given matrix.

  Returns:
    a map containing:
      :Q -- orthogonal factor
      :R -- the upper triangular factor

  References:
    http://en.wikipedia.org/wiki/QR_decomposition
    http://acs.lbl.gov/~hoschek/colt/api/cern/colt/matrix/linalg/QRDecomposition.html
"
  ([mat]
    (let [result (cern.colt.matrix.linalg.QRDecomposition. mat)]
      {:Q (Matrix. (.getQ result))
       :R (Matrix. (.getR result))})))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; MISC FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;; PRINT METHOD FOR COLT MATRICES
(defmethod print-method Matrix [o, #^java.io.Writer w]
  (let [formatter (Formatter. "%1.2f")]
    (do 
      (.setPrintShape formatter false)
      (.write w "[")
      (.write w (.toString formatter o))
      (.write w "]\n"))))



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
      


