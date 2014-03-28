
;;; optimize.clj -- Statistics library for Clojure built on the CERN Colt Library

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
;; June 6, 2009: First version



(ns ^{:doc "Optimization-relates functions."}
    incanter.optimize
  (:use [incanter.core :only (plus minus div mult mmult symmetric-matrix ncol solve
                              abs sel trans bind-columns to-list identity-matrix $=)]))


(defn integrate
  "
  Integrate a function f from a to b

  Examples:
    (use '(incanter optimize))

    (defn f1 [x] 1)
    (defn f2 [x] (Math/pow x 2))
    (defn f3 [x] (* x (Math/exp (Math/pow x 2))))

    (integrate f1 0 5)
    (integrate f2 0 1)
    (integrate f3 0 1)

    ;; normal distribution
    (def std 1)
    (def mu 0)
    (defn normal [x]
      (/ 1
        (* (* std (Math/sqrt (* 2 Math/PI)))
          (Math/exp (/ (Math/pow (- (- x mu)) 2)
          (* 2 (Math/pow std 2)))))))

    (integrate normal 1.96 10)


  Reference:
    http://jng.imagine27.com/articles/2009-04-09-161839_integral_calculus_in_lambda_calculus_lisp.html
    http://iam.elbenshira.com/archives/151_integral-calculus-in-haskell/
  "
  ([f a b]
    (let [small-dx 0.0001
          integrate-gen (fn [f x b dx sum]
                          (if (> x b)
                            sum
                            (recur f (+ x dx) b dx (+ sum (* (f x) dx)))))]
      (integrate-gen f a b small-dx 0))))




(defn derivative
  "
  Returns a function that approximates the derivative of the given function.

  Options:
    :dx (default 0.0001)

  Examples:

    (use '(incanter core optimize charts stats))
    (defn cube [x] (* x x x))
    (def cube-deriv (derivative cube))
    (cube-deriv 2) ; value: 12.000600010022566
    (cube-deriv 3) ; value: 27.00090001006572
    (cube-deriv 4) ; value: 48.00120000993502

    (def x (range -3 3 0.1))
    (def plot (xy-plot x (map cube x)))
    (view plot)
    (add-lines plot x (map cube-deriv x))

    ;; get the second derivative function
    (def cube-deriv2 (derivative cube-deriv))
    (add-lines plot x (map cube-deriv2 x))

    ;; plot the normal pdf and its derivatives
    (def plot (xy-plot x (pdf-normal x)))
    (view plot)
    (def pdf-deriv (derivative pdf-normal))
    (add-lines plot x (pdf-deriv x))

    ;; plot the second derivative function
    (def pdf-deriv2 (derivative pdf-deriv))
    (add-lines plot x (pdf-deriv2 x))
  "
  ([f & {:keys [dx] :or {dx 0.0001}}]
     (fn [x] (div (minus (f (plus x dx)) (f x)) dx))))





(defn- partial-derivative
  "
  Examples:

    (defn quad-fx [[x y]] (+ (* x x) (* x y) (* y y)))
    (def quad-dfx0 (partial-derivative quad-fx 0))
    (def quad-dfx1 (partial-derivative quad-fx 1))
    (quad-dfx0 [1 1])

    (use '(incanter core optimize charts))
    (def x (range -3 3 0.1))
    (def plot (xy-plot x (map #(quad-fx [% 1]) x)))
    (view plot)
    (add-lines plot x (map #(quad-dfx0 [% 1]) x))

  References:
    http://en.wikipedia.org/wiki/Partial_derivative
  "
  ([fx i & {:keys [dx] :or {dx 0.0001}}]
    (fn [theta]
      (let [theta-next (assoc theta i (+ (theta i) dx))]
        (/ (- (fx theta-next) (fx theta)) dx)))))



(defn- gradient-fn
  "Returns a function that approximates the gradient of the given function,
  which takes a single vector argument.


  Examples:

    (use '(incanter core optimize charts))
    (defn quad-fx [[x y]] (+ (* x x) (* x y) (* y y)))
    (def quad-grad (gradient-fn quad-fx 2))
    (quad-grad [1 1])
  "
  ([fx n & {:keys [dx] :or {dx 0.0001}}]
    (let [funs (for [i (range n)] (partial-derivative fx i :dx dx))]
      (fn [theta] (map #(% (apply vector theta)) funs)))))


(defn- second-partial-derivative
  "
  Examples:

    (use '(incanter core optimize charts))
    (defn quad-fx [[x y]] (+ (* x x) (* x y) (* y y)))
    (def quad-dfx00 (second-partial-derivative quad-fx 0 0))
    (def quad-dfx10 (second-partial-derivative quad-fx 1 0))
    (def quad-dfx01 (second-partial-derivative quad-fx 0 1))
    (def quad-dfx11 (second-partial-derivative quad-fx 1 1))
    (quad-dfx00 [1 1])
    (quad-dfx10 [1 1])
    (quad-dfx01 [1 1])
    (quad-dfx11 [1 1])
  "
  ([fx i j]
   (partial-derivative (partial-derivative fx i) j)))



(defn- hessian-fn
  "
  Examples:

    (use '(incanter core optimize charts))
    (defn quad-fx [[x y]] (+ (* x x) (* x y) (* y y y)))
    (def quad-hess (hessian-fn quad-fx 2))
    (quad-hess [1 1])


  References:
    http://en.wikipedia.org/wiki/Hessian_matrix
  "
  ([fx n & options]
    (let [funs (for [i (range n) j (range n) :when (<= j i)]
                 (second-partial-derivative fx i j))]
      (fn [theta] (symmetric-matrix (map #(% theta) funs))))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;  NUMERIC APPROXIMATIONS FOR FIRST AND SECOND DERIVATIVES
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn- gradient-forward-diff
  "
  Examples:

  (use '(incanter core optimize datasets charts))
  (defn f [theta x]
    (+ (nth theta 0)
          (div (* x (- (nth theta 1) (nth theta 0)))
               (+ (nth theta 2) x))))

  (def start [20 200 100])
  (def data (get-dataset :thurstone))
  (def x (sel data :cols 1))
  (def y (sel data :cols 0))
  ;; view the data
  (view (scatter-plot x y))

  (def grad (gradient-forward-diff f start))
  (grad start x)
  "

  ([f start & {:keys [tol dx] :or {tol 1E-4}}]
    (let [tdx (or dx (mult start tol))
          p (count start)
          e (to-list (identity-matrix p))
         ]
      (fn [theta x]
        (reduce bind-columns
                (for [i (range p)]
                  (div (map - (map (partial f (plus theta (mult (nth e i) (nth tdx i)))) x)
                              (map (partial f theta) x))
                       (nth tdx i))))))))





(defn gradient
  "
  Returns a function that calculates a 5-point approximation to
  the gradient of the given function. The vector of start values are
  used to determine the number of parameters required by the function, and
  to scale the step-size. The generated function accepts a vector of
  parameter values and a vector of x data points and returns a matrix,
  where each row is the gradient evaluated at the corresponding x value.

  Examples:

    (use '(incanter core optimize datasets charts))
    (defn f [theta x]
      (+ (nth theta 0)
            (div (* x (- (nth theta 1) (nth theta 0)))
                 (+ (nth theta 2) x))))

    (def start [20 200 100])
    (def data (get-dataset :thurstone))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (view (scatter-plot x y))

    (def grad (gradient f start))
    (time (doall (grad start x)))
  "

  ([f start & {:keys [tol dx] :or {tol 1E-4}}]
    (let [tdx (or dx (mult start tol))
          p (count start)
          e (to-list (identity-matrix p))]
      (fn [theta x]
        (reduce bind-columns
                (for [i (range p)]
                  (let [h (mult (nth e i) tdx)]
                    (div
                      (map + (map (partial f (minus theta (mult 2 h))) x)
                            (map - (mult 8 (map (partial f (minus theta h)) x)))
                            (mult 8 (map (partial f (plus theta h)) x))
                            (map - (map (partial f (plus theta (mult 2 h))) x)))
                      (* 12 (nth tdx i))))))))))




(defn hessian
  "
  Returns a function that calculates an approximation to the Hessian matrix
  of the given function. The vector of start values are used to determine
  the number of parameters required by the function, and to scale the
  step-size. The generated function accepts a vector of
  parameter values and a vector of x data points and returns a matrix,
  where each row with p*(p+1)/2 columns, one for each unique entry in
  the Hessian evaluated at the corresponding x value.

  Examples:

    (use '(incanter core optimize datasets charts))
    (defn f [theta x]
      (+ (nth theta 0)
            (div (* x (- (nth theta 1) (nth theta 0)))
                 (+ (nth theta 2) x))))

    (def start [20 200 100])
    (def data (get-dataset :thurstone))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (view (scatter-plot x y))

    (time (def hess (hessian f start)))
    (time (doall (hess start x)))
  "

  ([f start & {:keys [tol dx] :or {tol 1E-4}}]
    (let [tdx (or dx (mult start tol))
          p (count start)
          e (to-list (identity-matrix p))]
      (fn [theta x]
        (reduce bind-columns
                (for [i (range p) j (range p) :when (<= i j)]
                  (let [hi (mult (nth e i) tdx)
                        hj (mult (nth e j) tdx)
                        hij (mult (plus (nth e i) (nth e j)) tdx)]
                    (div
                      (map +
                        (map - (map (partial f theta) x)
                               (map (partial f (plus theta hi)) x)
                               (map (partial f (plus theta hj)) x))
                        (map (partial f (plus theta hij)) x))
                      (* (nth tdx i) (nth tdx j))))))))))






;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ROUTINES FOR NON-LINEAR LEAST SQUARES
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn- nls-rss
  "
  Returns the residual sum-of-squares for the given function evaluated at x with
  the given parameters theta. The function must take the form of (f theta x).


  Examples:

    (use '(incanter core optimize datasets charts))
    (defn f [theta x]
      (+ (nth theta 0)
            (div (* x (- (nth theta 1) (nth theta 0)))
                 (+ (nth theta 2) x))))

    (def start [20 200 100])
    (def data (get-dataset :thurstone))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (view (scatter-plot x y))

    (nls-rss f start x y)
  "
  ([f theta x y]
    (let [y-hat (map (partial f theta) x)
          resid (map - y y-hat)]
      (mmult (trans resid) resid))))



(defn- nls-gradient
  "
  Returns the gradient of the least squares function applied to the given function evaluated
  at x with the parameters theta. The function, f, must be of the form (f theta x),
  df must be of the form (df theta x) and return a vector representing the gradient of f.


  Examples:

    (use '(incanter core optimize datasets charts))
    (defn f [theta x]
      (+ (nth theta 0)
            (div (* x (- (nth theta 1) (nth theta 0)))
                 (+ (nth theta 2) x))))

    (def theta-init [20 200 100])
    (def data (get-dataset :thurstone))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (view (scatter-plot x y))

    (nls-gradient f
                  (gradient f theta-init)
                  theta-init x y)
  "

  ([f df theta x y]
    (let [_df (df theta x)
          y-hat (map (partial f theta) x)
          resid (map - y y-hat)]
      (mmult (mult -2 (trans _df)) resid))))





(defn- nls-hessian
  "
  Returns the Hessian matrix of the least squares function applied to f evaluated
  at x with the parameters theta. The function, f, must be of the form (f theta x),
  df must be of the form (df theta x) and return a vector representing the gradient of f,
  d2f must be of the form (d2f theta x) and return a matrix representing the hessian of f.


  Examples:

    (use '(incanter core optimize datasets charts))
    (defn f [theta x]
      (+ (nth theta 0)
            (div (* x (- (nth theta 1) (nth theta 0)))
                 (+ (nth theta 2) x))))

    (def theta-init [20 200 100])
    (def data (get-dataset :thurstone))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (view (scatter-plot x y))

    (time (doall (nls-hessian f
                 (gradient f theta-init)
                 (hessian f theta-init)
                 theta-init x y)))
  "


  ([f df d2f theta x y]
    (let [p (count theta)
          _df (df theta x)
          _d2f (d2f theta x)
          y-hat (map (partial f theta) x)
          resid (map - y y-hat)]
          (symmetric-matrix
            (map minus
                (for [j (range p) k (range p) :when (<= j k)]
                  (mult 2 (mmult (trans (sel _df :cols j)) (sel _df :cols k))))
                (for [m (range (ncol _d2f))]
                  (mult 2 (mmult (trans resid) (sel _d2f :cols m)))))
            :lower false))))



(defn- nls-newton-raphson
  "
  Examples:

    (use '(incanter core optimize datasets charts))
    ;; define the Michaelis-Menton model function
    ;; y = a + (b - a)*x/(c + x)
    (defn f [theta x]
      (+ (nth theta 0)
            (div (* x (- (nth theta 1) (nth theta 0)))
                 (+ (nth theta 2) x))))


    (def start [20 200 100])
    (def data (get-dataset :thurstone))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (def plot (scatter-plot x y))
    (view plot)

    (def df (gradient f start))
    (def d2f (hessian f start))
    (def result (nls-newton-raphson f df d2f start x y))

    ;(add-lines plot x (f (:theta result) x))
    (add-lines plot x (map (partial f (:theta result)) x))
  "
  ([f df d2f start x y & {:keys [max-iter tol]
                          :or {max-iter 200
                               tol 1E-5}}]
    (let [g (nls-gradient f df start x y)]
      (loop [i (int 0)
             th start]
        (let [H (solve (nls-hessian f df d2f th x y))
              g (nls-gradient f df th x y)]
          (if (or (< (reduce max (abs g)) tol) (= i max-iter))
            {:theta th
             :iterations i
             :gradient g
             :hessian H
             :rss (nls-rss f th x y)}
            (recur (inc i) (map - th (mmult H g)))))))))






(defn- nls-gauss-newton
  "
  Examples:

    (use '(incanter core optimize datasets charts))
    ;; define the Michaelis-Menton model function
    ;; y = a + (b - a)*x/(c + x)
    (defn f [theta x]
      (+ (nth theta 0)
            (div (* x (- (nth theta 1) (nth theta 0)))
                 (+ (nth theta 2) x))))

  ;    (defn f [theta x]
  ;      (let [[a b c] theta]
  ;        (plus a (div (mult x (minus b a)) (plus c x)))))

    (def start [20 200 100])
    (def data (get-dataset :thurstone))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (def plot (scatter-plot x y))
    (view plot)

    ;(def df (gradient f start))
    (def result (nls-gauss-newton f start x y))

    ;(add-lines plot x (f (:theta result) x))
    (add-lines plot x (map (partial f (:theta result)) x))
  "
  ([f start x y & {:keys [max-iter tol]
                   :or {max-iter 200
                        tol 1E-5}}]
    (let [grad (gradient f start)
          ;; g
          ;; (grad start x)
          ]
      (loop [i (int 0)
             th start]
        (let [y-hat (map (partial f th) x)
              resid (minus y y-hat)
              g (grad th x)
              update (mmult (solve (mmult (trans g) g)) (trans g) resid)]
          (if (or (< (reduce max (abs update)) tol) (= i max-iter))
            {:theta th
             :iterations i
             :gradient g
             :rss (nls-rss f th x y)}
            (recur (inc i) (map + th update))))))))




(defn non-linear-model
  "
  Determine the nonlinear least-squares estimates of the
  parameters of a nonlinear model.
  Based on R's nls (non-linear least squares) function.

  Arguments:
    f -- model function, takes two arguments the first a list of parameters
         that are to be estimated, and an x value.
    y -- sequence of dependent data
    x -- sequence of independent data
    start -- start values for the parameters to be estimated

  Options:
    :method (default :gauss-newton) other option :newton-raphson
    :tol (default 1E-5)
    :max-iter (default 200)

  Returns: a hash-map containing the following fields:
    :method -- the method used
    :coefs  -- the parameter estimates
    :gradient  -- the estimated gradient
    :hessian -- the estimated hessian, if available
    :iterations -- the number of iterations performed
    :fitted -- the fitted values of y (i.e. y-hat)
    :rss -- the residual sum-of-squares
    :x -- the independent data values
    :y -- the dependent data values


  Examples:

    ;; example 1
    (use '(incanter core optimize datasets charts))
    ;; define the Michaelis-Menton model function
    ;; y = a + (b - a)*x/(c + x)
    (defn f [theta x]
      (let [[a b c] theta]
        (plus a (div (mult x (minus b a)) (plus c x)))))

    (def start [20 200 100])
    (def data (get-dataset :thurstone))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (def plot (scatter-plot x y))
    (view plot)

    (def nlm (non-linear-model f y x start))
    (add-lines plot x (:fitted nlm))


    ;; example 2
    (use '(incanter core optimize datasets charts))
    ;; Chwirut data set from NIST
    ;; http://www.itl.nist.gov/div898/strd/nls/data/LINKS/DATA/Chwirut1.dat
    (def data (get-dataset :chwirut))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))

    ;; define model function: y = exp(-b1*x)/(b2+b3*x) + e
    (defn f [theta x]
      (let [[b1 b2 b3] theta]
        (div (exp (mult (minus b1) x)) (plus b2 (mult b3 x)))))

    (def plot (scatter-plot x y :legend true))
    (view plot)

    ;; the newton-raphson algorithm fails to converge to the correct solution
    ;; using first set of start values from NIST, but the default gauss-newton
    ;; algorithm converges to the correct solution.

    (def start1 [0.1 0.01 0.02])
    (add-lines plot x (f start1 x))
    (def nlm1 (non-linear-model f y x start1))
    (add-lines plot x (:fitted nlm1))

    ;; both algorithms converges with second set of start values from NIST
    (def start2 [0.15 0.008 0.010])
    (add-lines plot x (f start2 x))
    (def nlm2 (non-linear-model f y x start2))
    (add-lines plot x (:fitted nlm2))
  "


  ([f y x start & {:keys [max-iter tol method]
                   :or {max-iter 200
                        tol 1E-5
                        method :gauss-newton}}] ;; other option is :newton-raphson
    (let [nls (if (= method :newton-raphson)
                  (nls-newton-raphson f (gradient f start) (hessian f start) start x y :tol tol :max-iter max-iter)
                  (nls-gauss-newton f start x y :tol tol :max-iter max-iter))
          fitted (map #(f (:theta nls) %) x)]
      {:method method
       :coefs (:theta nls)
       :gradient (:gradient nls)
       :hessian (:hessian nls)
       :iterations (:iterations nls)
       :rss (:rss nls)
       :fitted fitted
       :x x
       :y y})))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ROUTINES FOR UNCONSTRAINED MINIMIZATION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- dot
  "Scalar product of a pair of collections"
  [xs ys] (reduce + ($= xs * ys)))

(defn- with-counting
  "
  Takes a function and returns a version of the function that tracks how many
  times that function is called, together with a counter.
  "
  [f]
    (let [num-calls (agent 0)]
      [(fn [& args]
        (send num-calls inc)
        (apply f args)) num-calls]))

(defn- line-search-BFGS
  "
  Minimize alpha in the function f(x-k + alpha*p-k) using the interpolation
  algorithm detailed in 'Numerical Optimization' Nocedal and Wright, 1999.
  Pg. 56-57
  "
  [f x-k p-k grad-f-k & {:keys [c-1 alpha-0] :or {c-1 1E-4 alpha-0 1}}]
    (let [phi (memoize (fn [alpha] (f ($= x-k + (alpha * p-k)))))
          phi-prime-0 (dot grad-f-k p-k)
          decrease-condition (fn [alpha] (<= (phi alpha)
                                          ($= (phi 0) + (c-1 * alpha * phi-prime-0))))]
      (if (decrease-condition alpha-0) alpha-0
        (let [phi-factor (memoize (fn [alpha] ($= (phi alpha) - (phi 0) - (phi-prime-0 * alpha))))
              factor (memoize (fn [alpha-k alpha-k-1] ($= (alpha-k-1 ** 2) * (alpha-k ** 2) * (alpha-k - alpha-k-1))))
              alpha-1 ($= -1 * (phi-prime-0 * alpha-0 ** 2) / (2 * (phi-factor alpha-0)))]
          (if (decrease-condition alpha-1) alpha-1
            (let [next-alpha (fn
                               [alpha-k alpha-k-1]
                               (let [a ($= ((alpha-k-1 ** 2) * (phi-factor alpha-k) - (alpha-k ** 2) * (phi-factor alpha-k-1)) / (factor alpha-k alpha-k-1))
                                     b ($= (-1 * ((alpha-k-1 ** 3) * (phi-factor alpha-k)) + (alpha-k-1 ** 3) * (phi-factor alpha-k-1)) / (factor alpha-k alpha-k-1))]
                                 (/ (- (Math/sqrt (Math/abs (- (Math/pow b 2) (* 3 a phi-prime-0)))) b)
                                    (* 3 a))))]
              (loop [alpha-i alpha-1
                     alpha-i-1 alpha-0]
                (let [alpha-i+1 (next-alpha alpha-i alpha-i-1)]
                  (if (decrease-condition alpha-i+1) alpha-i+1
                    (recur (if (or
                                (> (- alpha-i alpha-i+1) (/ alpha-i 2))
                                (< 0.96 (- 1 (/ alpha-i+1 alpha-i)))) (/ alpha-i 2) alpha-i+1) alpha-i))))))))))

(defn- fmin-bfgs
  "
  Minimize a function of multiple variables using the BFGS algorthim, based
  fmin_bfgs in scipy.optimize.

  This function is called by minimize (and maximize) with 'method :bfgs' or
  by default, and shouldn't be used directly.
  "
  [f x-0 f-prime tol max-iter]
    (let [norm (fn [grad-vec] (apply max (map #(Math/abs %) grad-vec)))
       I  (identity-matrix (count x-0))]
      (loop [inv-hessian-k I
             gradient-k (f-prime x-0)
             x-k x-0
             iter max-iter]
        (let [converged? (< (norm gradient-k) tol)
              max-iterations (= 0 iter)]
          (if (or converged? max-iterations)
            {:value x-k :iterations (- max-iter iter)}
            (let [p-k ($= -1 * (inv-hessian-k <*> gradient-k))
                  alpha-k (line-search-BFGS f x-k p-k gradient-k)
                  x-k+1 ($= x-k + (alpha-k * p-k))
                  gradient-k+1 (f-prime x-k+1)
                  y-k ($= gradient-k+1 - gradient-k)
                  s-k ($= x-k+1 - x-k)
                  rho-k (try
                          (/ 1 (dot y-k s-k))
                          (catch java.lang.ArithmeticException ae
                            1000)) ;; Divide-by-zero encountered: rho-k assumed large
                  A-1 ($= I - ((s-k <*> (trans y-k)) * rho-k))
                  A-2 ($= I - ((y-k <*> (trans s-k)) * rho-k))]
              (recur ($= (A-1 <*> (inv-hessian-k <*> A-2))
                         + (rho-k * (s-k <*> (trans s-k))))
                     gradient-k+1 x-k+1 (dec iter))))))))

(defn minimize
  "
  Minimize a scalar function of one or more variables. Based on the
  Implementation from scipy.optimize. Currently only the BFGS algorithim is
  implemented.

  Arguments:
    f -- Objective function. Takes a collection of values and returns a scalar
         of the value of the function.
    start -- Collection of initial guesses for the minimum

  Options:
    :f-prime -- partial derivative of the objective function. Takes
                a collection of values and returns a collection of partial
                derivatives with respect to each variable. If this is not
                provided it will be estimated using gradient-fn.
    :method (default :bfgs) currently no other options
    :tol (default 1E-5)
    :max-iter (default 200)

  Returns: a hash-map containing the following fields:
    :method -- the method used
    :value  -- the minimum of the objective function
    :iterations -- the number of iterations performed
    :fun-calls -- the number of calls to f
    :grad-calls -- the number of calles to f-prime


  Examples:

    (use '(incanter core optimize))
    ;; define the rosenbrock function and derivative
    (defn rosenbrock
      [[x y]]
      ($= (1 - x) ** 2 + 100 * (y - x ** 2) ** 2))

    (defn rosenbrock-der
      [[x y]]
      [($= 2 * (200 * x ** 3 - 200 * x * y + x - 1))
       ($= 200 * (y - x ** 2))])
    ;; run minimize function on rosenbrock to find root
    (= (minimize rosenbrock [0 10] :f-prime rosenbrock-der :max-iter 500) (matrix [1 1])) ;; True
  "
  [f start & {:keys [f-prime max-iter tol method]
                      :or {f-prime (gradient-fn f (count start) :dx 1E-5)
                           max-iter 200
                           tol 1E-5
                           method :bfgs}}]
  (let [min (cond :else fmin-bfgs)
        [f f-calls] (with-counting f)
        [f-prime f-prime-calls] (with-counting f-prime)]
    (assoc (min f start f-prime tol max-iter) :method method :fun-calls @f-calls :grad-calls @f-prime-calls)))

(defn maximize
  "
  This function tries to maximize a scalar function of one or more variables.
  See documentation of 'minimize' function for more information.
  "
  [f start & {:keys [f-prime max-iter tol method]
                      :or {f-prime (gradient-fn f (count start) :dx 1E-5)
                           max-iter 200
                           tol 1E-5
                           method :bfgs}}]
  (minimize (comp - f) start :f-prime (comp (partial map -) f-prime) :max-iter max-iter :tol tol :method method))
