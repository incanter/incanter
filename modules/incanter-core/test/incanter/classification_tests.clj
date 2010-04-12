(ns incanter.classification-tests
  (:use incanter.classification)
  (:use incanter.probability)
  (:use clojure.contrib.map-utils)
  (:use clojure.contrib.test-is))

(deftest classify-one-to-one-item
  (let [test-fns {:a (present-when (gt 5)) :b (present-when (lt 5))}]
  (is (= {:a 1 :b 0}
    (classify-one-to-one test-fns {:a 10 :b 5})))))

(deftest classify-one-to-each-test
  (let [test-fns {:a (present-when (gt 5)) :b (present-when (lt 5))}]
  (is (= {:a {:a 1 :b 0} :b {:a 0 :b 0}}
    (classify-one-to-each test-fns {:a 10 :b 5})))))

(deftest classify-one-to-all-test
  (let [test-fns {:a (present-when #(and 
				     (> (:a %) 9)
				     (< (:b %) 6)))}]
  (is (= {:a 1}
    (classify-one-to-all test-fns {:a 10 :b 5})))))

(deftest classify-all-items
  (let [c (classifier 
	     {:a (present-when (gt 5)) :b (present-when (lt 5))}
	     classify-one-to-one)]
  (is (= [{:a 1 :b 0} {:a 0 :b 1}]
    (map c [{:a 10 :b 5} {:a 4 :b 1}])))))

(deftest create-model-from-maps
  (is (= 
       {3 {4 (float (/ 19 3638)) 
	   5 (float (/ 9 3638))
	   3 (float (/ 34 3638))}}
       (model-from-maps 
	[{3 {4 19, 5 9, 3 34}} 
	 {4 88, 5 676, 3 3638}]))))

(deftest deep-recursive-create-model-from-maps
  (is (= 
       {3 {4 {5 (float (/ 19 3638))}, 
	   3 {5 (float (/ 9 5567))}}}
       (model-from-maps 
	[{3 {4 {5 19}, 3 {5 9}}} 
	 {3 {4 3638, 3 5567}}])))
  (is (= 
       {0 {5 (float (/ 9 5567))}
        1 {2 {3 {4 {5 (float (/ 19 3638))}}}}}
       (model-from-maps 
	[{0 {5 9}, 1 {2 {3 {4 {5 19}}}}} 
	 {0 5567, 1 {2 {3 {4 3638}}}}]))))

;;represents matrix where outer key is the predicted value
;;and inner key is the actual values occuring when the outer key was the prediction 
(deftest confusion-matrix-tests
  (is (=  {0 {0 1 1 1}
	   1 {0 0 1 2}}
	 (confusion-matrix 
	  ;;trained
	  {1 {0 0.80 1 0.20} 
	   0 {0 0.1 1 0.9}}
	  ;;test
	  {1 {0 1 1 1}
	   0 {0 0 1 2}})))
  (is (=  {0 {0 2 1 2}
	   1 {0 0 1 4}}  
	 (confusion-matrix 
	  ;;trained
	  {1 {0 {0 0.1 1 0.9}
	      1 {0 0.80 1 0.20}} 
	   0 {0 {0 0.1 1 0.9}
	      1 {0 0.80 1 0.20}}} 
	  ;;test
	  {1 {0 {0 0 1 2} 
	      1 {0 1 1 1}}
	   0 {0 {0 0 1 2} 
	      1 {0 1 1 1}}})))
  (is (=  {0 {0 4 1 4}
	   1 {0 0 1 8}}
	 (confusion-matrix 
	  ;;trained
	  {1 {0 {0 {0 0.1 1 0.9}
	         1 {0 0.80 1 0.20}}
	      1 {0 {0 0.1 1 0.9}
	         1 {0 0.80 1 0.20}}} 
	   0 {0 {0 {0 0.1 1 0.9}
	         1 {0 0.80 1 0.20}}
	      1 {0 {0 0.1 1 0.9}
	         1 {0 0.80 1 0.20}}}} 
	 
	  ;;test
	  {1 {1 {0 {0 0 1 2} 
	         1 {0 1 1 1}}
	      0 {0 {0 0 1 2} 
	         1 {0 1 1 1}}}
	   0 {1 {0 {0 0 1 2} 
	         1 {0 1 1 1}}
	      0 {0 {0 0 1 2} 
	         1 {0 1 1 1}}}}))))

;(deftest heterogenious-depth-confusion-matrix
;  (is (= {1 {0 3, 1 2}
;	  0 {0 25, 1 21}}
;	 (confusion-matrix
;	 {1 {1 0.6, 0 0.4},
;	  0 {0 {1 {0 1.0, 0 0}}}}

;	 {1 {1 2, 0 3},
;	  0 {0 {1 {0 25, 1 21}}}}))))

(def ex1 [{:missing {2 33, 1 157, 0 882}, 0 {:missing 11, 2 20672, 1 56750, 0 293364}, 1 {1 9336, 2 3637, 0 46941}, 2 {1 1888, 2 736, 0 8287}} {:missing 1072, 0 370797, 1 59914, 2 10911}])

(def ex2 [{2 {:missing 14, 1 2704, 2 2054, 0 13924}, :missing {2 20, 1 65, 0 429}, 0 {:missing 5, 2 21107, 1 61157, 0 316896}, 1 {2 3677, 1 9590, 0 50492}} {2 18696, :missing 514, 0 399165, 1 63759}])

(deftest confustion-matrix-integration-test
  (is (= {0 {2 26858, :missing 19, 0 381741, 1 73516}}
       (confusion-matrix (model-from-maps ex1) (first ex2)))))

(deftest precent-predicted-test
  (is (=
       {0 (float (/ 605170 (+ 605170 5032 3377)))
        1 (float (/ 3216 (+ 3216 3571 57663)))
	2 (float (/ 13100 (+ 689 13100 17962)))}
       (recall 
	{1 {2 689, 0 5032, 1 3216}, 
	 2 {1 3571, 0 3377, 2 13100}, 
	 0 {2 17962, 0 605170, 1 57663}})))
  (is (= {0 (float (/ 19817 (+ 19817 558)))
	  1 (float (/ 3960 (+ 3960 1291 198)))
	  2 (float (/ 2132 (+ 2132 69 274)))}
	 (recall 
	  {1 {0 558, 2 274, 1 3960}, 0 {2 69, 1 1291, 0 19817}, 2 {1 198, 2 2132}})))
  (is (= {0 0.5, 1 0, 2 0.5}
	 (recall
	  {0 {0 5, 1 2, 2 5}, 2 {0 5, 1 5, 2 5}})))
  (is (= {2 (float (/ 3718 (+ 3718 11026 645))), 
	  1 (float (/ 4530 (+ 4530 21783 767 17))), 
	  0 (float (/ 176140 (+ 176140 5066 117 14)))}
	 (recall {:missing {:missing 5196}, 
			     :no-prediction {0 14, 1 17}, 
			     1 {0 5066, 1 4530, 2 645}, 
			     0 {2 11026, 1 21783, 0 176140}, 
			     2 {0 117, 1 767, 2 3718}}))))



(deftest precision-test
  (is (= {0 (float 174737/221347)}
	 (precision {0 {:missing 11, 2 25078, 1 68131, 0 349474}})))
  (is (= {0 (float 174737/221347) 
	  1 (float 2438/24951)}
	 (precision {0 {:missing 11, 2 25078, 1 68131, 0 349474}
					   1 {:missing 12, 2 257, 1 9752, 0 89783}}))))

(deftest form-eqiv-classes
  (is (= {1 0, 2 0, 3 0, 4 1, 5 1, 6 1}
       (equivalence-classes {0 #{1 2 3}, 1 #{4 5 6}}))))

(deftest merge-into-equivalence-classes-test
  (let [classes (equivalence-classes {0 #{1 2 3}, 1 #{4 5 6}})
	all-zero (equivalence-classes {0 #{1 2 3 4 5 6}})]
  (is (= {1 {0 3, 1 2}
	  0 {0 25, 1 21}
	  :missing {0 1}}
	 (merge-equivalence-classes
	    {1 classes}
	  {1 {0 25}, 
	   3 {1 21} 
	   4 {0 3}, 
	   6 {1 2}
	   :missing {0 1}})))
  (is (= {1 {0 5}
	  0 {0 46}
	  :missing {0 1}}
	 (merge-equivalence-classes
	    {1 classes
	     2 all-zero}
	  {1 {0 25}, 
	   3 {1 21} 
	   4 {0 3}, 
	   6 {1 2}
	   :missing {0 1}})))
  (is (= {1 {1 {0 3, 1 2}}
  	  0 {0 {0 25, 1 21}}}
  	 (merge-equivalence-classes
  	    {1 classes
  	     2 classes}
  	  {1 {0 {0 25}}, 
  	   3 {1 {1 21}} 
  	   4 {4 {0 3}}, 
  	   6 {6 {1 2}}})))))

(deftest heterogenious-depth-merge-classes
  (let [classes (equivalence-classes {0 #{1 2 3}, 1 #{4 5 6}})]
  (is (= {2 {1 {0 3, 1 2}}
  	  0 {0 46}
	  :override 10}
  	 (merge-equivalence-classes
  	    {1 (equivalence-classes {:override #{0} 0 #{1 2}, 1 #{3}, 2 #{4 5 6 7}})
  	     2 classes}
  	  {0 10,
	   1 {0 25}, 
  	   2 {1 21}, 
  	   4 {4 {0 3}}, 
  	   6 {6 {1 2}}})))))

(def ex1 [{:missing {2 33, 1 157, 0 882}, 0 {:missing 11, 2 20672, 1 56750, 0 293364}, 1 {1 9336, 2 3637, 0 46941}, 2 {1 1888, 2 736, 0 8287}} {:missing 1072, 0 370797, 1 59914, 2 10911}])

(def ex2 [{2 {:missing 14, 1 2704, 2 2054, 0 13924}, :missing {2 20, 1 65, 0 429}, 0 {:missing 5, 2 21107, 1 61157, 0 316896}, 1 {2 3677, 1 9590, 0 50492}} {2 18696, :missing 514, 0 399165, 1 63759}])

(deftest compute-matrix-from-count-maps
  (is (= {0 {:missing 11, 2 25078, 1 68131, 0 349474}}
	 (confusion-matrix-from-counts ex1 ex2)))
  (is (= {0 {:missing 11, 2 25078, 1 68131, 0 349474}}
	 (confusion-matrix-from-counts ex1 ex2 ex2 ex2 ex2))))

(def ex3 [{2 {1 1029, 0 1971, 2 2207}, 0 {:missing 18, 1 37978, 2 12701, 0 192261}, :missing {2 461, 1 1352, 0 8517}, 1 {1 3828, 0 2545, 2 1595}} {2 5207, 0 242958, :missing 10330, 1 7968}])

(def ex4 [{0 {:missing 11, 0 208075, 2 12284, 1 39876}, :missing {2 376, 1 1032, 0 7624}, 2 {1 1151, 2 2346, 0 2233}, 1 {2 1788, 1 4305, 0 3280}} {0 260246, :missing 9032, 2 5730, 1 9373}])

(deftest cross-validate-merges-matrices
  (is (= 
       (deep-merge-with +
			(confusion-matrix-from-counts ex3 ex4)
			(confusion-matrix-from-counts ex4 ex3))
       (cross-validation-confusion-matrix ex3 ex4))))

(deftest merge-counts-tests
  (is (= 
       {0 {0 65955, 1 28369, 2 19051},
	1 {0 71152, 1 12174, 2 3240}}

       (merge-counts
	{0 {0 {0 62569, 1 3280, 2 106},
	    1 {0 2403, 1 24828, 2 1138},
	    2 {0 6, 1 712, 2 18333}},
	 1
	 {0 {0 69000, 1 2110, 2 42},
	  1 {0 1551, 1 10382, 2 241},
	  2 {0 7, 1 217, 2 3016}}}))))
