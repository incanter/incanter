
(ns  ^{:doc "A library for performing symbolic math, a port from SICP (http://mitpress.mit.edu/sicp/)."
       :author "Bryce Nyeggen, with modifications by David Edgar Liebke"}
 incanter.symbolic
  (:use [incanter.core :only (pow)]))

;functions of multiple arguments
(def fn-list #{ '+ '- '* '/ 'pow '** 'exp})

;functions of one argument
(def chain-list #{'exp 'log 'sin 'cos 'tan })

;their combination
(def all-fn-list (into fn-list chain-list ))

(defn- same-var?
  "on one arg, checks if variable, ie a symbol not in our list,
  on two, checks vars and equality"
  ([v1] (and (not (contains? all-fn-list v1)) (symbol? v1)))
  ([v1 v2] (and (= v1 v2) (same-var? v1))))

(defn- reduce-expr
  "return the last (third) item of a list, or a symbol and then everything after that"
  [e op]
  (if (= (count e) 3)
      (nth e 2)
      (conj (nthnext e 2) op)))

(defn- sum? [x] (and (>= (count x) 3) (= (first x) '+)))
(defn- product? [x] (and (>= (count x) 3) (= (first x) '*)))
(defn- expnt? [x] (and (= (count x) 3) (contains? #{'** 'pow 'exp} (first x))))
(defn- difference? [x] (and (>= (count x) 3) (= (first x) '-)))
(defn- quotient? [x] (and (>= (count x) 3) (= (first x) '/)))

(defn- conv-qtnt "Convert a quotient to a product of a base with an inverse"
  [x]
  (list '* (second x) (list 'pow (list* '*  1 (nthnext x 1))) -1))

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

(defn- make-prod
  "assemble a product expression properly"
  [a1 a2]
  (cond
    (= a1 0) 0
    (= a2 0) 0
    (= a1 1) a2
    (= a2 1) a1
    (and (number? a1) (number? a2)) (* a1 a2)
    (number? a1) (list '* a1 a2)
    true (list '* a2 a1)))

(defn- make-expnt
  "assemble an exponent expression properly."
  [b e]
  (cond
    (= b 0) 0
    (= b 1) 1
    (= e 0) 1
    (= e 1) b
    (and (number? b) (number? e)) (pow b e)
    true (list 'pow b e)))

(defn deriv*
  "
  Main sub-function for differentiation. with 2 args, takes 1st degree deriv.
  with 3, takes arbitrary degrees. contains all deriv rules for basic funcs.


  Examples:

    (use '(incanter core symbolic))

    (deriv* '(+ x 3) 'x)
    (deriv* '(* x y) 'x)
    (deriv* '(* (* x y) '(+ x 3)) x)
    (deriv* '(* (* x y) (+ x 3)) 'y)

    (deriv* '(* x y (+ x 3)) 'x)
    (deriv* '(* x y (+ x 3)) 'y)

    (deriv* '(* x y (+ x 3)) 'x 2)
    (deriv* '(* x y (+ x 3)) 'x 3)
  "
  ([exp v]
    (cond
      (number? exp) 0
      (same-var? exp v) 1
      (and (same-var? exp) (not= exp v)) 0
      (sum? exp) (make-sum (deriv* (second exp) v) (deriv* (reduce-expr exp '+) v))
      (difference? exp) (make-sum (deriv* (second exp) v)
                          (deriv* (make-prod -1 (reduce-expr exp '+)) v))
      (product? exp)
      (make-sum
        (make-prod (second exp)
          (deriv* (reduce-expr exp '*) v))
        (make-prod (deriv* (second exp) v)
          (reduce-expr exp '*)))
      (quotient? exp) (deriv* (conv-qtnt exp) v)
      (expnt? exp)
      (let [u (second exp)
            n (expnt exp)]
        (make-prod (make-prod
                     (expnt exp)
                     (make-expnt (second exp) (make-sum (expnt exp) -1)))
          (deriv* (second exp) v)))
      (chainable? exp)
      (let [u (first exp)
            n (second exp)]
        (cond
          (number? n) 0;things could be out-of-bounds a la log(0), but that's philosophical
          (= 'sin u) (make-prod (list 'cos n) (deriv* n v))
          (= 'cos u) (make-prod (list '* -1 (list 'sin n)) (deriv* n v))
          (= 'tan u) (make-prod (list 'pow (list 'cos n) -2) (deriv* n v))
          ;multiply by inverse of denominator is same as numerator/denominator
          (= 'log u) (make-prod (deriv* n v) (list 'pow n -1))
          (= 'exp u) (make-prod (list 'exp n) (deriv* n v))
          true false));should not happen as chainable? refers to a list that
      ;we should completely specify here
      true (list 'deriv* exp v);some kind of error here, return a description of
      ;"the derivative of this function" rather than the actual result
      ))
  ([exp vr degree]
    (loop [x exp v vr dgr degree]
      (if (zero? dgr) x
        (recur (deriv* x v) v (dec dgr) )))))


(defmacro deriv
  "
  Macro for symbolic differentiation. with 2 args, takes 1st degree deriv.
  with 3, takes arbitrary degrees. contains all deriv rules for basic funcs.


  Examples:

    (use '(incanter core symbolic))

    (deriv (+ x 3) x) ; => 1
    (deriv (* x y) x) ; => y
    (deriv (* (* x y) (+ x 3)) x) ; => (+ (* (+ x 3) y) (* x y))
    (deriv (* (* x y) (+ x 3)) y) ; => (* (+ x 3) x)

    (deriv (* x y (+ x 3)) x) ; => (+ (* y (+ x 3)) (* y x))
    (deriv (* x y (+ x 3)) y) ; => (* (+ x 3) x)

    (deriv (sin x) x) ; => (cos x)
    (deriv (cos x) x) ; => (* -1 (sin x))

    (deriv (sin (* x y)) y) ; => (* x (cos (* x y)))

    (deriv (pow x 3) x) ; => (* 3 (pow x 2))
    (deriv (** x 3) x) ; => (* 3 (pow x 2))

    (deriv (pow x 3) x 2) ; => (* 3 (* 2 x))

    (deriv (* x y (+ x 3)) x 2) ; => (+ y y)
    (deriv (* x y (+ x 3)) x 3) ; => 0

    (deriv (+ (* 3 x) (* 8 x)) x) ; => 11



    ;; NOT WORKING YET

    (deriv (/ 1 x) x) ; => (* (deriv* (* (x)) x) (* -1 (pow (* (x)) -2)))
                                          ^-- need to fix"
  ([exp v]
     `(deriv* '~exp '~v))
  ([exp v degree]
     `(deriv* '~exp '~v ~degree)))



(defn- tree-subst
  "
  Examples:
    (use '(incanter core symbolic))

    (def ops {'+ clojure.core/+
    '- clojure.core/-
    '* clojure.core/*
    '/ clojure.core//
    'sin incanter.core/sin
    'cos incanter.core/cos
    'tan incanter.core/tan
    'pow incanter.core/pow
    '** incanter.core/pow
    'exp incanter.core/exp
                'fn clojure.core/fn})

    (tree-subst '(+ (* x y) x) {'x 3, 'y 9, '* 'clojure.core/*, '+ 'clojure.core/+})
    (eval (tree-subst '(+ (* x y) x) {'x 3, 'y 9, '* 'clojure.core/*, '+ 'clojure.core/+}))

    (tree-subst (deriv (+ (* x y) x) x)  (apply assoc ops ['x 3 'y 9]))
    (eval (tree-subst (deriv (+ (* x y) x) x) (apply assoc ops ['x 3 'y 9])))

    (fn [x y] (tree-subst (deriv (+ (* x y) x) x)  (apply assoc ops ['x 3 'y 9])))

    ((fn [x y] (eval (tree-subst (deriv (+ (* x y) x) x) (apply assoc ops ['x 3 'y 9])))) 5 9)

    ((eval (tree-subst (list 'fn '[x y] (deriv (+ (* x y) x) x))
                       (apply assoc ops ['x (gensym 'x) 'y (gensym 'y)])))
      5 9)

     ((eval (tree-subst (list 'fn '[x y] (deriv* '(+ (* x y) x) 'x))
                       (apply assoc ops ['x (gensym 'x) 'y (gensym 'y)])))
      5 9)"
  ([tree subst-map]
    (let [subst-fn (fn [el]
         (cond
           (vector? el)
             (apply vector (tree-subst el subst-map))
           (coll? el)
             (tree-subst el subst-map)
           :else
             (or (subst-map el) el)))]
      (map subst-fn tree))))


(defn deriv-fn*
  "
  Examples:
    (use '(incanter core symbolic))

    (deriv-fn* '[x y] '(+ (* x y) x) 'x)

    ((deriv-fn* '[x y] '(+ (* x y) x) 'x) 5 9)
  "
  ([[& args] expr v]
     (deriv-fn* args expr v 1))
  ([[& args] expr v degree]
     (let [ops {'+ clojure.core/+
    '- clojure.core/-
    '* clojure.core/*
    '/ clojure.core//
    'sin incanter.core/sin
    'cos incanter.core/cos
    'tan incanter.core/tan
    'pow incanter.core/pow
    '** incanter.core/pow
    'exp incanter.core/exp}]
       (eval (tree-subst (list 'fn (apply vector args) (deriv* expr v degree))
       (apply assoc ops (interleave args (map gensym args))))))))


(defmacro deriv-fn
  "
  Examples:
    (use '(incanter core symbolic))

    (deriv-fn [x y] (+ (* x y) x) x)

    ((deriv-fn [x y] (+ (* x y) x) x) 5 9)

    (use 'incanter.charts)
    (doto (function-plot sin -5 5)
       (add-function (deriv-fn [x] (sin x) x) -5 5)
       (add-function (deriv-fn [x] (sin x) x 2) -5 5)
       view)

    (let [f (fn [x] (pow x 2))
          df (deriv-fn [x] (pow x 2) x)]
      (doto (function-plot f -5 5)
        (add-function df -5 5)
        view))


    (let [f (fn [x] (pow x 3))
          df (deriv-fn [x] (pow x 3) x)]
      (doto (function-plot f -5 5)
        (add-function df -5 5)
        view))


    ;; NOT WORKING YET

    (let [f (fn [x] (/ 1 x ))
          df (deriv-fn [x] (/ 1 x) x)]
      (doto (function-plot f 0.5 5)
        (add-function df 0.5 5)
        view))
  "
([[& args] expr v]
   `(deriv-fn* '[~@args] '~expr '~v 1))
([[& args] expr v degree]
   `(deriv-fn* '[~@args] '~expr '~v ~degree)))
