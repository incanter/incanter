(ns incanter.stats-tests
 (:use incanter.stats)
 (:use incanter.stats)
 (:use clojure.test))

(deftest sample-mean
 (is (= 3 
      (mean [2 3 4]))))

(deftest stdev-test
 (is (= 2.138089935299395 
      (sd [2 4 4 4 5 5 7 9]))))

(deftest simple-regresssion-tests
 (let [r (simple-regression [2 4] [1 3])]
  (is (= 3
	 (predict r 2)))))	 

(deftest smooth-discrete-probs-test
  (is (= {1 0.19047619047619047, 
	  2 0.3333333333333334, 
	  3 0.47619047619047616}
	 (smooth-discrete-probs 
	  {1 0.2
	   3 0.5}
	  [1 2 3]))))

(deftest odds-ratio-test
  (let [p1 9/10
	p2 2/10]
    (is (= 36
	   (odds-ratio p1 p2)))))

(deftest covariance-test
  (is (= 5.333333333333333
	 (covariance
	  [3 1 3 9]
	  [4 4 8 8]))))

(deftest pearson-test
  (is (within 0.0001 1
	      (correlation [5 6 7 8] [8 9 10 11]))))

(deftest correlation-ratio-example
  (let [algebra [45, 70, 29, 15, 21]
	geometry [40, 20, 30 42]
	statistics [65, 95, 80, 70, 85, 73]]

    (is (within 0.0001
		0.8386
		(correlation-ratio
		 algebra
		 geometry
		 statistics)))))

(deftest ranking-test
(is (=
     {97 2, 99 3, 100 4, 101 5, 103 6, 106 7, 110 8, 112 9, 113 10, 86 1}
     (rank-index [106 86 100 101 99 103 97 113 112 110]))))

(deftest spearmans-rho-test
  (is (within 0.000001 (float -29/165)
	 (spearmans-rho [106 86 100 101 99 103 97 113 112 110] 
			[7 0 27 50 28 29 20 12 6 17]))))

(deftest kendalls-tau-test
  (is (= 23/45
	 (kendalls-tau [4 10 3 1 9 2 6 7 8 5] 
		       [5 8 6 2 10 3 9 4 7 1])))
  (is (= 9/13
	 (kendalls-tau
	  [1 3 2 4 5 8 6 7 13 10 12 11 9]
	  [1 4 3 2 7 5 6 8 9 10 12 13 11]))))

(deftest concordancy-test
  (is (discordant? [[4 2] [2 4]]))
  (is (= 4
	 (discordant-pairs [1 2 3 4 5] 
			   [3 4 1 2 5]))))

(deftest kendalls-tau-distance-test
  (is (= 4
	 (kendalls-tau-distance [1 2 3 4 5] 
				[3 4 1 2 5])))
  (is (= 2/5
	 (normalized-kendall-tau-distance [1 2 3 4 5] 
					  [3 4 1 2 5])))) 


(deftest jaccard-examples
 (is (= 2/6
	(jaccard-index #{1 2 3 4} #{3 4 5 6})))
 (is (= 2/5
	(jaccard-distance #{1 2 3 4} #{2 3 4 5}))))

;; Each set has four elements, and the intersection of these two sets has only one element: ht.
;;     {ni,ig,gh,ht}
;;     {na,ac,ch,ht}
;; Plugging this into the formula, we calculate, s = (2 · 1) / (4 + 4) = 0.25.
(deftest dice-string
  (is 
   (= 0.25
      (dice-coefficient-str "night" "nacht"))))

(deftest get-ngrams
  (is (= #{"gh" "ht" "ni" "ig"}
	 (bigrams "night"))))

;;TODO: think about a hamming distance that measure how far someting is off for k-way classification rathern than jsut binary classification.
(deftest hamming-ints-and-strings
 (is (= 2
	(hamming-distance 1011101 1001001)))
 (is (= 3
	(hamming-distance 2173896 2233796)))
 (is (= 3
        (hamming-distance "toned" "roses"))))

(deftest lee-distance-withq
 (= (+ 1 2 0 3)
    (lee-distance 3340 2543 6)))

;;since the following three edits change one into the other, and there is no way to do it with fewer than three edits:
   ;; 1. kitten → sitten (substitution of 's' for 'k')
   ;; 2. sitten → sittin (substitution of 'i' for 'e')
   ;; 3. sittin → sitting (insert 'g' at the end).
(deftest levenshtein-kitten
 (is (= 3
	(levenshtein-distance "kitten" "sitting")))
 (is (= 3
	(levenshtein-distance "Sunday" "Saturday"))))

(deftest damerau-levenshtein-distance-tests
  (let [a "TO"
	b "OT"
	c "OST"]
    (is (= 1 (damerau-levenshtein-distance a b)))
    (is (= 1 (damerau-levenshtein-distance b c)))
    (is (= 3 (damerau-levenshtein-distance a c)))))

(deftest euclid
  (is 
   (= 2.8284271247461903 
      (euclidean-distance [2 4 3 1 6]
			  [3 5 1 2 5]))))

(deftest manhattan
  (is 
   (= (+ 1 1 2 1 1) 
      (manhattan-distance [2 4 3 1 6]
			  [3 5 1 2 5]))))

(deftest chebyshev
  (is 
   (= 2 
      (chebyshev-distance [2 4 3 1 6]
			  [3 5 1 2 5]))))

(deftest cosine-sim
  (is 
   (=  0.9242424242424241 ;;0.938572618717
       (cosine-similarity  [2 4 3 1 6]
			   [3 5 1 2 5]))))

(deftest tanimoto-sim
  (is 
   (=  0.8591549295774648
       (tanimoto-coefficient  [2 4 3 1 6]
			      [3 5 1 2 5]))))