(ns incanter.information-theory-tests
  (:use incanter.information-theory)
  (:use incanter.probability)
  (:use clojure.contrib.math)
  (:use clojure.contrib.test-is))

(defn =within [delta x y]
  (>= delta (abs (- x y))))

(deftest witin-tests
    (is (not (=within (expt 10 -10) 
		 1
		 (+ 1 (* 2 (expt 10 -10))))))
    (is (=within  (expt 10 -10) 
		 1
		 (+ 1 (expt 10 -10)))))

(deftest entropy-tests
    (is (=within (expt 10 -10) 
		 3
		 (entropy (n-sided-die 8))))
    (is (=within (expt 10 -10) 
		 2
		 (entropy (n-sided-die 4))))
    (is (=within (expt 10 -10) 
		 1
		 (entropy {0 10, 1 10})))
    (is (=within (expt 10 -10) 
		 0
		 (entropy {0 0, 1 10}))))

(deftest gain-tests
  (is (=within
       (expt 10 4)
       0.048
	 (gain {:weak {:positive 6 :negative 2} 
		:strong {:positive 3 :negative 3}}))))

;;Mutual information quantifies the dependence between the joint distribution of X and Y and what the joint distribution would be if X and Y were independent. 
(deftest mutual-information-tests
 (let [py {1 3
	   2 5}
       pz {1 4 
	   2 2 
	   3 1 
	   4 1} 
       independent-joint
       {1 {1 12
	   2 6
	   3 3
	   4 3}
	2 {1 20
	   2 10
	   3 5
	   4 5}}]

 (is (= 0
	(mutual-information independent-joint [py pz])))))