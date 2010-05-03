
(ns incanter.symbolic-differentiation
  ;(:require )
  (:use clojure.contrib.math)
  ;(:import )
  )

;functions of multiple arguments
(def fn-list #{ '+ '- '* '/ 'pow '** 'expt})

;functions of one argument
(def chain-list #{'exp 'log 'sin 'cos 'tan })

;their combination
(def all-fn-list (into fn-list chain-list ))

(defn- same-var?
  "on one arg, checks if variable, ie a symbol not in our list, on two,
  checks vars and equality"
  ([v1] (and (not (contains? all-fn-list v1)) (symbol? v1)))
  ([v1 v2] (and (= v1 v2) (same-var? v1))))

(defn- reduce-expr [e op]
  "return the last (third) item of a list, or a symbol and then everything after that"
  (if (= (count e) 3)
      (nth e 2)
      (conj (nthnext e 2) op)))

(defn- sum? [x] (and (>= (count x) 3) (= (first x) '+)))
(defn- product? [x] (and (>= (count x) 3) (= (first x) '*)))
(defn- expnt? [x] (and (= (count x) 3) (contains? #{'** 'pow 'expt} (first x))))
(defn- difference? [x] (and (>= (count x) 3) (= (first x) '-)))
(defn- quotient? [x] (and (>= (count x) 3) (= (first x) '/)))

(defn- conv-qtnt [x] "Convert a quotient to a product of a base with an inverse"
  (list '* (second x) (list '** (list '* (nthnext x 2)) -1)))
;exp can also kind of be chainrulized below, it makes sense not to though since
;it takes 2 args, not one.  log base whatever is same situation
(defn- expnt [e] (nth e 2))
(defn- chainable? [x]
  (and (contains? chain-list (first x)) (= (count x) 2)))

(defn- make-sum
  "assemble a sum expression properly"
  [a1 a2]
  (cond
    (= a1 0) a2
    (= a2 0) a1
    (and (number? a1) (number? a2)) (+ a1 a2)
    ;always put numbers first
    (number? a1) (list '+ a1 a2)
    true (list '+ a2 a1)))

(defn- make-prod [a1 a2]
  "assemble a product expression properly"
  (cond
    (= a1 0) 0
    (= a2 0) 0
    (= a1 1) a2
    (= a2 1) a1
    (and (number? a1) (number? a2)) (* a1 a2)
    (number? a1) (list '* a1 a2)
    true (list '* a2 a1)))

(defn- make-expnt [b e]
  "assemble an exponent expression properly."
  (cond
    (= b 0) 0
    (= b 1) 1
    (= e 0) 1
    (= e 1) b
    (and (number? b) (number? e)) (expt b e)
    true (list '** b e)))

(defn deriv
  "main sub-function for differentiation. with 2 args, takes 1st degree deriv.
  with 3, takes arbitrary degrees. contains all deriv rules for basic funcs."
  ([exp v]
    (cond
      (number? exp) 0
      (same-var? exp v) 1
      (and (same-var? exp) (not= exp v)) 0
      (sum? exp) (make-sum (deriv (second exp) v) (deriv (reduce-expr exp '+) v))
      (difference? exp) (make-sum (deriv (second exp) v)
                          (deriv (make-prod -1 (reduce-expr exp '+)) v))
      (product? exp)
      (make-sum
        (make-prod (second exp)
          (deriv (reduce-expr exp '*) v))
        (make-prod (deriv (second exp) v)
          (reduce-expr exp '*)))
      (quotient? exp) (deriv (conv-qtnt exp) v)
      (expnt? exp)
      (let [u (second exp)
            n (expnt exp)]
        (make-prod (make-prod
                     (expnt exp)
                     (make-expnt (second exp) (make-sum (expnt exp) -1)))
          (deriv (second exp) v)))
      (chainable? exp)
      (let [u (first exp)
            n (second exp)]
        (cond
          (number? n) 0;things could be out-of-bounds a la log(0), but that's philosophical
          (= 'sin u) (make-prod (list 'cos n) (deriv n v))
          (= 'cos u) (make-prod (list '* -1 (list 'sin n)) (deriv n v))
          (= 'tan u) (make-prod (list 'pow (list 'cos n) -2) (deriv n v))
          ;multiply by inverse of denominator is same as numerator/denominator
          (= 'log u) (make-prod (deriv n v) (list '** n -1))
          (= 'exp u) (make-prod (list 'exp n) (deriv n v))
          true false));should not happen as chainable? refers to a list that
      ;we should completely specify here
      true (list 'deriv exp v);some kind of error here, return a description of
      ;"the derivative of this function" rather than the actual result
      ))
  ([exp vr degree]
    (loop [x exp v vr dgr degree]
      (if (zero? dgr) x
        (recur (deriv x v) v (dec dgr) )))))

