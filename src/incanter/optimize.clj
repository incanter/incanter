;;; optimize.clj -- Statistics library for Clojure built on the CERN Colt Library

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
;; June 6, 2009: First version



(ns incanter.optimize 
  (:use [incanter.core :only (plus minus div mult mmult symmetric-matrix
                              identity-matrix)]))



(defn integrate 
" Integrate a function f from a to b


  Examples:
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
    (def plot (line-plot x (map cube x)))
    (view plot)
    (add-lines plot x (map cube-deriv x))

    ;; get the second derivative function
    (def cube-deriv2 (derivative cube-deriv))
    (add-lines plot x (map cube-deriv2 x))
   
    ;; plot the normal pdf and its derivatives
    (def plot (line-plot x (pdf-normal x)))
    (view plot)
    (def pdf-deriv (derivative pdf-normal))
    (add-lines plot x (pdf-deriv x))

    ;; plot the second derivative function
    (def pdf-deriv2 (derivative pdf-deriv))
    (add-lines plot x (pdf-deriv2 x))

"
  ([f & options]
    (let [opts (if options (apply assoc {} options) nil)
          dx (if (:dx opts) (:dx opts) 0.0001)
          f-prime (fn [x] (div (minus (f (plus x dx)) (f x)) dx))]
      f-prime)))





(defn- partial-derivative
"
  Examples:

    (defn quad-fx [[x y]] (+ (* x x) (* x y) (* y y)))
    (def quad-dfx0 (partial-derivative quad-fx 0)) 
    (def quad-dfx1 (partial-derivative quad-fx 1))
    (quad-dfx0 [1 1]) 

    (use '(incanter core optimize charts))
    (def x (range -3 3 0.1))
    (def plot (line-plot x (map #(quad-fx [% 1]) x)))
    (view plot)
    (add-lines plot x (map #(quad-dfx0 [% 1]) x))

  References:
    http://en.wikipedia.org/wiki/Partial_derivative
    
"
  ([fx i & options]
    (let [opts (if options (apply assoc {} options) nil)
          dx (if (:dx opts) (:dx opts) 0.0001)]
      (fn [theta] 
        (let [theta-next (assoc theta i (+ (theta i) dx))]
          (/ (- (fx theta-next) (fx theta)) dx))))))



(defn- gradient-fn
"
  Returns a function that approximates the gradient of the given function,
  which takes a single vector argument.


  Examples:

    (use '(incanter core optimize charts))
    (defn quad-fx [[x y]] (+ (* x x) (* x y) (* y y)))
    (def quad-grad (gradient-fn quad-fx 2))
    (quad-grad [1 1])

"
  ([fx n & options]
    (let [funs (for [i (range n)] (partial-derivative fx i))]
      (fn [theta] (map #(% theta) funs)))))



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
    (def data (to-matrix (get-dataset :thurstone)))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (view (scatter-plot x y))

    (def grad (gradient-forward-diff f start))
    (grad start x)



"
  ([f start & options]
    (let [opts (if options (apply assoc {} options) nil)
          tol (if (:tol opts) (:tol opts) 1E-4)
          dx (if (:dx opts) (:dx opts) (mult start tol))
          p (count start)
          e (to-list (identity-matrix p))
         ]
      (fn [theta x]
        (reduce bind-columns
                (for [i (range p)]
                  (div (map - (map (partial f (plus theta (mult (nth e i) (nth dx i)))) x)
                              (map (partial f theta) x)) 
                       (nth dx i))))))))





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
    (def data (to-matrix (get-dataset :thurstone)))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (view (scatter-plot x y))

    (def grad (gradient f start))
    (time (doall (grad start x)))


"
  ([f start & options]
    (let [opts (if options (apply assoc {} options) nil)
          tol (if (:tol opts) (:tol opts) 1E-4)
          dx (if (:dx opts) (:dx opts) (mult start tol))
          p (count start)
          e (to-list (identity-matrix p))]
      (fn [theta x]
        (reduce bind-columns
                (for [i (range p)]
                  (let [h (mult (nth e i) dx)]
                    (div 
                      (map + (map (partial f (minus theta (mult 2 h))) x) 
                            (map - (mult 8 (map (partial f (minus theta h)) x))) 
                            (mult 8 (map (partial f (plus theta h)) x)) 
                            (map - (map (partial f (plus theta (mult 2 h))) x))) 
                      (* 12 (nth dx i))))))))))




(defn hessian
" Returns a function that calculates an approximation to the Hessian matrix 
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
    (def data (to-matrix (get-dataset :thurstone)))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (view (scatter-plot x y))

    (time (def hess (hessian f start)))
    (time (doall (hess start x)))


"
  ([f start & options]
    (let [opts (if options (apply assoc {} options) nil)
          tol (if (:tol opts) (:tol opts) 1E-4)
          dx (if (:dx opts) (:dx opts) (mult start tol))
          p (count start)
          e (to-list (identity-matrix p))]
      (fn [theta x]
        (reduce bind-columns
                (for [i (range p) j (range p) :when (<= i j)]
                  (let [hi (mult (nth e i) dx)
                        hj (mult (nth e j) dx)
                        hij (mult (plus (nth e i) (nth e j)) dx)]
                    (div 
                      (map +
                        (map - (map (partial f theta) x) 
                               (map (partial f (plus theta hi)) x) 
                               (map (partial f (plus theta hj)) x)) 
                        (map (partial f (plus theta hij)) x))
                      (* (nth dx i) (nth dx j))))))))))






;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ROUTINES FOR NON-LINEAR LEAST SQUARES
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 


(defn- nls-rss
" Returns the residual sum-of-squares for the given function evaluated at x with
  the given parameters theta. The function must take the form of (f theta x).


  Examples:

    (use '(incanter core optimize datasets charts))
    (defn f [theta x] 
      (+ (nth theta 0) 
            (div (* x (- (nth theta 1) (nth theta 0)))
                 (+ (nth theta 2) x))))

    (def start [20 200 100])
    (def data (to-matrix (get-dataset :thurstone)))
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
" Returns the gradient of the least sqaures function applied to the given function evaluated
  at x with the parameters theta. The function, f, must be of the form (f theta x),
  df must be of the form (df theta x) and return a vector representing the gradient of f.


  Examples:

    (use '(incanter core optimize datasets charts))
    (defn f [theta x] 
      (+ (nth theta 0) 
            (div (* x (- (nth theta 1) (nth theta 0)))
                 (+ (nth theta 2) x))))

    (def theta-init [20 200 100])
    (def data (to-matrix (get-dataset :thurstone)))
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
" Returns the Hessian matrix of the least sqaures function applied to f evaluated
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
    (def data (to-matrix (get-dataset :thurstone)))
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
            :by-row false))))



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

;    (defn f [theta x] 
;      (let [[a b c] theta]
;        (plus a (div (mult x (minus b a)) (plus c x)))))

    (def start [20 200 100])
    (def data (to-matrix (get-dataset :thurstone)))
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
  ([f df d2f start x y & options]
    (let [opts (if options (apply assoc {} options) nil)
          max-iter (if (:max-iter opts) (:max-iter opts) 200)
          tol (if (:tol opts) (:tol opts) 1E-5)
          g (nls-gradient f df start x y)]
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
    (def data (to-matrix (get-dataset :thurstone)))
    (def x (sel data :cols 1))
    (def y (sel data :cols 0))
    ;; view the data
    (def plot (scatter-plot x y))
    (view plot)

    ;(def df (gradient f start))
    (def result (nls-gauss-newton f df start x y))

    ;(add-lines plot x (f (:theta result) x)) 
    (add-lines plot x (map (partial f (:theta result)) x)) 



" 
  ([f df start x y & options]
    (let [opts (if options (apply assoc {} options) nil)
          max-iter (if (:max-iter opts) (:max-iter opts) 200)
          tol (if (:tol opts) (:tol opts) 1E-5)
          grad (gradient f start)
          g (grad start x)]
      (loop [i (int 0)
             th start]
        (let [y-hat (map (partial f th) x)
              resid (minus y y-hat)
              g (grad th x)
              update (mmult (solve (mmult (trans g) g)) (trans g) resid)
              ]
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
    f -- model function, takes two argumetns the first a list of parameters
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
    (def data (to-matrix (get-dataset :thurstone)))
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
    (def data (to-matrix (get-dataset :chwirut)))
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
    ;; algorith converges to the correct solution.

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
  ([f y x start & options]
    (let [opts (if options (apply assoc {} options) nil)
          method (if (:method opts) (:method opts) :guass-newton) ;; other option is :newton-raphson
          tol (if (:tol opts) (:tol opts) 1E-5)
          max-iter (if (:max-iter opts) (:max-iter opts) 200)
          nls (if (= method :newton-raphson)
                (nls-newton-raphson f (gradient f start) (hessian f start) start x y :tol tol :max-iter max-iter)
                (nls-gauss-newton f (gradient f start) start x y :tol tol :max-iter max-iter))
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



