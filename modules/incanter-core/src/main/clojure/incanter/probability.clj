(ns incanter.probability
  (:import [java.io File])
  (:import [java.util Date Calendar])
  (:use [clojure.set :only [difference]])
  (:use [clojure.contrib.map-utils :only [deep-merge-with]])
  (:use [clojure.contrib.seq-utils :only [flatten]])
  (:use [incanter.internal :only [tree-comp any?]])
  (:use [incanter.transformations 
         :only [set-to-unit-map bottom-level? map-map same-length?]]))

(defn binary 
"
a function for binary classification that takes a booleavn value and returns 1 for true and 0 for false.
"
[pred] (if pred 1 0))

(defn count-missing 
"set based counting for keys in maps"
[exp act]
  (let [diff (difference exp (keys act))] 
    (set-to-unit-map diff)))

(defn rolling-windows 
[len] 
  (fn [col] (partition len 1 col)))

(defn map-counter 
"
wraps a counting function for maps in apply and deep-merge-with, curreid fn expects a seq of maps to count.
" 
[f] (partial apply deep-merge-with f))

(defn vector-counter 
"
wraps a counting function for vectors in apply and deep-merge-with, curreid fn expects a seq of vectors to count.
" 
[f] (partial reduce f))

(defn +cond-prob-tuples  
 "adds two conditional probability tuples. [[{}{}][{}{}]] -> [{}{}]
  passes through a single conditional probability tuple. [[{}{}] -> [{}{}]"
  ([[x y] [p q]]
    [(deep-merge-with + x p) 
     (deep-merge-with + y q)])
  ([x] x))

;;TODO: generalize to n-dimensional.
(defn comb-merge
  "combinatorial merge takes two maps and a fn and and merges all combinations fo keys between the two maps using the fn.
"   
  [f x y] 
  (apply merge-with merge
        (for [[kx vx] x
              [ky vy] y]
          {kx {ky (f vx vy)}})))

(defn summate
"summate all counts in a deeply nested map of counts."
  [m]
  (apply +
         (flatten (cond (bottom-level? m) 
                        (vals m)
                        :otherwise 
                        (map summate (vals m))))))

(defn summate-level 
"given a nested map, summates all counts below the tree from each key-node in the map-tree."
[j]
  (assert (not (bottom-level? j)))
      (map-map summate j))

(defn marginals
"
computes the marginal PMFs from the joint PMF.

of the form:   {a {b n}} where n is the nubmer of co-occurances of a and b.

for summation note that a variable, suppose it is x, is represented as a level of depth in the nested maps, so summation for margianl of x occurs on all branches stemming from maps at the level corresponding with x. 
"
[j]
((fn [jx marginals]
(if (bottom-level? jx) (conj marginals jx)
  (recur (apply deep-merge-with + (vals jx))
         (conj marginals  (summate-level jx))))) j []))


;;TODO: in progress.  meditating on the simplest implimentation
;;this is process is called "marginalization" or how to marginalize out an arbitary variable from a joint distribution, leaving a joint distribution of a sebset of the variables.
;; (defn subjoint
;; "
;; computes a joint PMF for a subset of variables in the given larger joint PMF.

;; of the form:   {a {b n}} where n is the nubmer of co-occurances of a and b.

;; "
;; [j ps]
;; ((fn [jx level]
;;    (let [nxt (if (contains? ps level)
;;             jx
;;             (apply deep-merge-with + (vals jx)))]
;;   (recur next (+ 1 level))))
;;  j 0))

(defn n-sided-die 
[#^Integer n] 
     (apply hash-map 
            (interleave (range 1 (+ n 1) 1) 
                        (repeat n 1))))

;;Predicates and constraints
(defn pred [f arg] #(f % arg))
(defn gt [y] (pred > y))
(defn lt [y] (pred < y))
(defn eq [y] (pred = y))
(defn ne [y] (pred (comp not =) y))

(defn present-when 
  ([f] #(binary (f %)))
  ([f & keys] #(binary ((apply tree-comp f keys) %))))

(defn constrain [k f v] #(f (k %) v))

(defn any [x] true)
(defn always-false [x] false)
(defn bucket-negative? [x] 
  (if (pos? x) false :negative))

(defn range-classifier  
"
classify one item based on what interval in a range the item falls into.
"
  [range item]
  (reduce 
   #(+ %1 (binary (> item %2))) 
   0
   range))

(defn missing? [x]
  (any? nil?
     (cond (map? x) (vals x) 
           (coll? x) x
           :otherwise [x])))

;;TODO: refactor to use joint-only represetnation and compute marginals and pairwise as needed from the full joint.  this should also lead to some renamings.

;;TODO: this can be replaced with a cond-let like form similar to pattern mathcing.
;; (match
;;    missing? [trans (t x)] :missing
;;    missing? [val-to-bucket (f trans)] :missing
;;    false? [short-circuit (p val-to-bucket)] short-circuit
;;    :otherwise (range-classifier r val-to-bucket))
(defn bucket 
  ([f r] (bucket f identity r))
  ([f t r] (bucket f t r always-false))
  ([f t r p]
     (fn [x]
       (let [trans (t x)]
         (if (missing? trans) :missing
            (let [val-to-bucket (f trans)]
               (if (missing? val-to-bucket) :missing
                   (if-let [short-circuit (p val-to-bucket)] 
                     short-circuit
                     (range-classifier r val-to-bucket)))))))))

(defn cond-prob-tuple
  "build [a&b b] count tuples for calculating conditional probabilities p(a | b)"
  [a b] 
  [{b {a 1}} {b 1}])

(defn label-cond-prob-dependent [a & bs]
  (let [tree-maker
        (fn [[ab-tree b-tree] b] 
          [{b ab-tree} {b b-tree}])
        init [{a 1} 1]]
    (if (coll? bs)
      (reduce tree-maker init (reverse bs))
      (tree-maker init bs))))

(defn | 
  "this is the core of the conditional probability based classification model.  this model takes a & bs in the form a given bs.  a and bs are all functions, and the conditional probability classification model composes a new classifier function that ultimately returns the cond-prob-tuple: [{a's counts}{b's counts}]."
  [a b]
  (apply 
   tree-comp 
   label-cond-prob-dependent 
   a b))

(defn |each
  "conditional probability where the bs are taken to be a map of feature->classifier-function paris, where we want to compute the conditional probability between a and each b independently."
  [a bs]
  (fn [obs]
    (into {} (for [[k b] bs]
               [k (cond-prob-tuple (a obs) (b obs))]))))

(defn P 
""
  ([a given b & bs] (given a (cons b bs)))
  ([a given b] (given a (if (coll? b) b [b])))) 
