(ns incanter.transformations
  (:use [clojure.set :only [intersection]])
  (:use [clojure.contrib.seq-utils :only [flatten]]))

(defn same-length? [a b] (= (count a) (count b)))

(defn table-to-vectors
"takes a big vector that is composed of two vectors of alternating membership in the super vector.

splits out the individual vectors.

 [106 	7
 86 	0
 100 	27
 101 	50
 99 	28
 103 	29
 97 	20
 113 	12
 112 	6
 110 	17]

 ->

 [[106 86 100 101 99 103 97 113 112 110] [7 0 27 50 28 29 20 12 6 17]]
"
[z]
 (reduce 
  (fn [[x y][x1 y1]] 
    [(conj x x1) 
     (conj y y1)]) 
  [[][]]
(partition 2 2 z)))

;;map transformations
(defn map-map [f x] 
  (into {} (for [[k v] x] [k (f v)])))

(defn map-from-keys [a f] 
  (into {} (for [k a] [k (f k)])))

(defn map-from-pairs [a f] 
  (into {} (for [[k v] a] [k (f k v)])))

(defn map-from-nested-map [a f] 
  (into {} (for [[k v] a] [k (map-map f v)])))

(defn set-to-unit-map [s] 
  (apply hash-map (interleave s (repeat (count s) 1))))

(defn key-compare
 [x y]
  (cond 
    (and 
       (keyword? x)
       (not (keyword? y))) 1
    (and 
       (keyword? y)
       (not (keyword? x))) -1
    :otherwise (compare x y)))

;;weird inversion makes us revers k1 and k2 
(defn kv-compare [[k1 v1] [k2 v2]] (key-compare k2 k1))

;;TDOO: doesn't seem to work? test and beat on it.
;;use clojrue sorting: sort-by, sorted-map-by, etc. 
(defn sort-map [m] (into {} (sort kv-compare m)))

(defn map-compare [k] 
  #(compare (k %1) (k %2)))

(defn sort-maps-by [k maps]
  (sort (map-compare k) maps))  

(defn sort-map-of-maps [m]
  (sort-map   
   (for [[k v] m] 
     [k (if (map? v) 
	  (sort-map-of-maps v) 
	  v)])))

(defn all-keys
  "returns a set of all the keys from an arbitarily deeply nested map or seq of maps."
  [m]
  (cond (not (map? m))
	  (apply intersection (map all-keys m))
	:otherwise
	  (into #{} 
	      (flatten
	       ((fn get-keys [x]
		  (cons (keys x)
			(map get-keys 
			     (filter map? (vals x)))))
		m)))))

(defn bottom-level? 
"
given a map; is this the bottom level in the map?

 (bottom-level? {:a 1}) -> true
 (bottom-level? {:a {:b 1}}) -> false
"
[m]
(not (map? (second (first m)))))

(defn levels-deep
"
returns the number of levels of depth of nesting for a nested map.

 1  -> 0
 {} -> 0
 {0 1} -> 1
 {1 {0 1}} -> 2
 ...
"
 [m]
  (apply max 0 
	 (flatten
	  ((fn count-level [m n]
	     (if (not (coll? m)) 0
	     (for [[k v] m]
	       (if (map? v) 
		 (count-level v (+ n 1))
		 n))))
	   m 1))))

(defn flatten-with 
"
takes an arbitrarily deeply nested map, and flattens it to one level by merging keys. 

 (flatten-with str {:a {:b {:c 1}}}) -> {\":a:b:c\" 1}

"
[f nested-map]
(apply hash-map
(flatten
((fn flatten-level [key-acc m]  
(for [[k v] m
      ;;TODO: really shady ass use of nil for conditional, better impl?
      :let [new-key (if key-acc (f key-acc k) k)]]
      (if (not (map? v))
	[new-key v]
      (flatten-level new-key v)))) nil nested-map))))
      