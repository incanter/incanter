(ns 
#^{:doc 
"
fundamentals of classification, cross validation, and performance measures such as precision and recall.

classifiers are maps of classifier-name -> functions, data are maps of feature-name features.
"}
incanter.classification
  (:use [clojure.contrib.seq-utils :only [flatten]])
  (:use [clojure.contrib.map-utils :only [deep-merge-with]])
  (:use [incanter.transformations :only [map-map levels-deep all-keys]])
  (:use [incanter.probability :only [bucket +cond-prob-tuples]])
  (:use [incanter.internal :only [safe threshold-to]]))

(defn classifier 
""
[fns classify]
  (fn [data]
    (classify fns data)))

(defn numerical-classifiers
"makes a bucketing classifier out of each range for use with |each."  
[ranges]
  (into {}
	(for [[k range] ranges]
	  [k (bucket k range)])))

(defn categorical-classifiers
  "makes a categorical classifier for use with |each." 
  [features]
  (into {}
	(for [feature features]
	  [feature feature])))

(defn category-classifier 
""
[x] 
  {x 1})

(defn category-map-classifier 
""
  ([x] (map-map category-classifier x))
  ([s x] (category-map-classifier (into {} (filter #(contains? s (key %)) x)))))

;;TODO: rename? likely to be confusing due to "wrapper and filter" taxonomy for feature selection.
(defn wrapper-classifiers
  "makes a wrapping classifier like categortical but with wrapper fn." 
  [features]
  (into {}
	(for [[k wrapper] features]
	  [k #(wrapper (k %))])))

(defn classify-one-to-one 
"
takes a map of fns and a map of features, where there is one classifier fn per feature and they share the same key names in the classifier and feature maps.  apply each classifer fn to the corresponding feature.

usage:

 (classify-one-to-one  
   {:a (present-when (gt 5)) :b (present-when (lt 5))}
   {:a 10 :b 5})
-> {:a 1 :b 0}

"
[fns data]
  (merge-with #(%1 %2) fns data))

(defn classify-one-to-each 
"
takes a map of fns and a map of features, apply each classifer fn to each feature in the data map individually.

usage:

 (classify-one-to-each  
   {:a (present-when (gt 5)) :b (present-when (lt 5))}
   {:a 10 :b 5})
-> {:a {:a 1 :b 0} :b {:a 0 :b 0}}

"
[fns data]
  (into {} (for [[f-k f] fns] [f-k (map-map f data)])))

(defn classify-one-to-all 
"
takes a map of fns and a map of features, apply each classifer fn to the entire feature map.

usage:

 (classify-one-to-each  
   {:a (present-when #(and 
				     (> (:a %) 9)
				     (< (:b %) 6)))}
   {:a 10 :b 5})
-> {:a 1}
"
[fns data]
(into {} (for [[f-k f] fns] [f-k (f data)])))

;;this wrapping of the classifer in a fn/macro is necessary to stop cascading from trying to 
;;serialize keywords, which are not serailizeable
;;classify all is here befcause we are classifying-many because we are classifying each member of a time series.
(defmacro classifier-macro 
  [classify classify-one-to-one] 
  `(fn [x#] (map (classifier ~classify ~classify-one-to-one) x#)))

(defn classification-workflow 
"
composes a classification workflow from a classifier a counter and a transformer.

note that count-all has been abstracted due to the fact taht you may count with reduce or merge-with depending on wheter you ahve vectors or maps.
"
  [transformer classifier count-all]
  (fn [obs] (count-all (map classifier (transformer obs)))))

(defn heterogenious-group-by 
  "Returns a sorted map of the elements of coll keyed by the result of
  f on each element. The value at each key will be a vector of the
  corresponding elements, in the order they appeared in coll."
  [f coll]
  (reduce
   (fn [ret x]
     (let [k (f x)]
       (assoc ret k (conj (get ret k []) x))))
   {} coll))

(defn equivalence-classes 
"
takes a map where key is class and value is a set of equivalence classes to the key class.  it then inverts the mapping so that you can look up classes that are equivalence classes of a new larger class.

usage:
 (equivalence-classes {0 #{0 1}, 1 #{2, 3, 4}, 2 #{5 6}})
-> {0 0, 1 0, 2 1, 3 1, 4 1, 5 2, 6 2}
"
[class-mappings]
  (into {} (for [[k v] class-mappings]
    (into {} (for [equiv v] [equiv k])))))

(defn merge-levels 
""
[class-mappings coll]
     (map-map 
      #(apply deep-merge-with + (map second %))
      (heterogenious-group-by 
       (fn [[k v]] 
	 (if-let [new-key (class-mappings k)]
	   new-key
	   k))
       coll)))

(defn merge-equivalence-classes 
"
 (defn merge-classes-time-before-dep [model]
  \"take in [[{modelcounts} {totalcounts}] [{} {}]]\"
  ;;starting with 2 becasue 1 is the first slot, which is time before departure for the metrics
  (let [model-merger {2 bucket-eq-classes
		      3 bucket-eq-classes
		      6 bucket-eq-classes}
	count-merger {2 bucket-eq-classes
		      3 bucket-eq-classes}]
  (map (fn [[modelcnts totalcnts]] [(merge-equivalence-classes model-merger modelcnts) 
				    (merge-equivalence-classes count-merger totalcnts)]) model)))
"
[class-mappings x]
  (letfn [(merger [coll levels]
		    (let [merged (merge-levels (if-let [mapping (class-mappings levels)] 
						 mapping 
						 identity) coll)]
		      (into {} (for [[k v] merged] 
				 (if (not (map? v)) 
				   [k v] 
				   [k (merger v (+ 1 levels))])))))] 
    (merger x 1)))

(defn probs-only 
"compute probability from computed counts.  

this is division, you have to count up the proper numerator and denominator in your counting step."
  ([k a b] [k (probs-only a b)])
  ([a b] (safe / a b)))

(defn process-prob-map
"process probability maps using a provided report function. 

 beware: can't pass keys to reporter our you get double nested final level in map."
 [[a-and-b b] report]
  (into {}
   (for [[bkey bval] a-and-b] 
    [bkey
     (if (map? bval) 
       (process-prob-map [bval (b bkey)] report)  
       (report bval b))])))

(defn model-from-maps
"creates a model from probability report maps."
 [prob-map]
  (process-prob-map prob-map probs-only))

(defn most-likely 
"
computes the most likely class from a map of classes to class probability.

usage:
 (most-likely {:a 0.6 :b 0.4}) -> :a
"
[m] 
  (let [[k v] (reduce 
	    (fn [[k1 v1][k2 v2]] 
	      (if (> v1 v2) 
		[k1 v1] 
		[k2 v2])) 
	    m)] k)) 

(defn confusion-matrix 
  "computes a confusion matrix from the counts on train and test data sets represented as maps. traverse map...as you get to the point of counts of actuals, replace the series of nested keys that lead to that point with the single key of the predicted"
[trd tst]
  (apply deep-merge-with +
	 (flatten  
	  ((fn each-level [tr ts]
	     (for [[k v] ts
		   :let [it (tr k)
			 can-predict (not (nil? it))]]
	       (if (= 1 (levels-deep v))
		 (if can-predict
		   {(most-likely it) v}
		   {:no-prediction v})
		 (if can-predict
		   (each-level it v)
		   (each-level {} v)))))
	   trd tst))))

(defn precision 
 "computes precision by class label from confusion matrix."
[m]
  (into {} 
	(for [[k v] m
	      :let [predicted (v k)]
	      :when (not (= k :no-prediction))]
	  [k (float (/ (if predicted predicted 0)
		(apply + (vals v))))])))

(defn recall 
 "computes recall by class label from confusion matrix."
[m]
  (into {} 
	(for [k (all-keys m)
	      :let [v-predicted (m k)]
	      :when (not (or (= k :missing)
			     (= k :no-prediction)))]
	  [k
	   (if (not v-predicted) 0
	       (let [the-prediction (v-predicted k)]
		 (float (/ (if the-prediction the-prediction 0) 
			   (apply + (for [[k-actual v-actual] m]
				      (threshold-to 0 
						    (v-actual k))))))))])))

(defn confusion-matrix-from-counts 
"
produces a confusion matrix from teh joint distributions of test and train data.

right now the tests and train data are con-prob-tuples this may change if we store only the joint PMFs
"
[test & train]
   (confusion-matrix
    (model-from-maps 
     (reduce +cond-prob-tuples train))
     (first test)))  ;;only needs first part of count matrix, which si the jpoint distribution.
  
(defn cross-validation-confusion-matrix
"
takes a set of n joint PMFs, and holds each joint PMF out in turn as the test set.  

merges the resulting n cross-validation matrices into a single matrix.
"
[& xs]
 (apply deep-merge-with +
  (for [x xs]
    (apply 
     confusion-matrix-from-counts 
       x (remove #{x} xs)))))
    
(defn n-times-k-fold-cross-validation-confusion-matrix 
""
[list-of-lists]
  (apply deep-merge-with +
  (map (partial apply cross-validation-confusion-matrix)
       list-of-lists)))

(defn map-of-vectors 
""
[keys]
 (into {} 
       (map (fn [k] [k []]) keys)))

(defn vectorize 
""
[maps]
  (map-of-vectors (all-keys maps)))

(defn collect-vals 
""
[maps]
  "(collect-vals [{:a 1 :b 2} {:a 4 :b 5} {:c 4} {:c 5}]) > {:b [2 5], :c [4 5], :a [1 4]}"
  (apply merge-with conj 
	 (cons (vectorize maps) maps)))

(defn prob-map-tuples-by-time 
"use to transform data for confusion matrix by time before departure"
[prob-map-tuple]
  (into {} 
	(for [[k v] (first prob-map-tuple)] 
	  [k [v ((second prob-map-tuple) k)]])))

(defn confusion-matrix-by-time 
""
[results]
  (let [results-by-time (collect-vals 
			 (map prob-map-tuples-by-time results))]
    (into {} 
	  (for [[k v] results-by-time] 
	    [k (apply cross-validation-confusion-matrix v)]))))

;;stuff for looking at precision and recall at time-before-prediction.

;;todo: these below are hardcoded to certian levels of depth
(defn merge-counts [x] 
  (into {} 
	(for [[k v] x] 
	  [k (into {} 
		   (for [[pk pv] v] 
		     [pk (apply + (vals pv))]))])))

;;TODO: not really recall - what to call this?
(defn recall-by-time [confustion-matrix]
  (let [merged (merge-counts confustion-matrix)
	totals (apply merge-with + 
		      (vals merged))]
    (into {}
	  (for [[k v] merged]
	    [k 
	    (into {}
		  (for [[predicted count] v]
	    [predicted (float (/ count (totals predicted)))]))]))))

(defn percent-of-total-predictions-by-time [counts]
  (let [totals (apply merge-with + 
		      (vals counts))]
    (into {}
	  (for [[k v] counts]
	    [k 
	    (into {}
		  (for [[predicted count] v]
	    [predicted (float (/ count (totals predicted)))]))]))))
