
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; LDA and QDA classifiers from chapter 4 of Elements of Statistical Learning
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; LDA 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use '(incanter core stats charts io))

(def training (to-matrix 
                (read-dataset "http://bit.ly/464h4h" 
                              :header true)))
(def testing (to-matrix 
               (read-dataset "http://bit.ly/1btCei" 
                             :header true)))


(def K 11)
(def p 10)
(def N (nrow training))
(def group-counts (map nrow (group-by training 1)))

;; estimate the prior probabilities for each cluster
(def prior-probs (div group-counts N))

;; estimate the centroids for each cluster
(def cluster-centroids 
  (matrix 
    (for [x_k (group-by training 1 :cols (range 2 12))]
      (map mean (trans x_k)))))



;; estimate the covariance matrix to be used for all clusters
(def cluster-cov-mat 
  (let [groups (group-by training 1 :cols (range 2 12))]
    (reduce plus
      (map (fn [group centroid n]
        (reduce plus 
                (map #(div 
                        (mmult (trans (minus % centroid))
                               (minus % centroid))
                        (- N K))
                     group)))
             groups cluster-centroids group-counts))))

;; calculate the inverses of the cluster covariance matrices
(def inv-cluster-cov-mat (solve cluster-cov-mat))


;; define the linear discriminant function (ldf)
(defn ldf [x Sigma-inv mu_k pi_k]
  (+ (mmult x Sigma-inv (trans mu_k))
     (- (mult 1/2 (mmult mu_k Sigma-inv (trans mu_k))))
     (log pi_k)))


;; define a function to calculate the linear quadratic scores.
(defn calculate-scores
  ([data inv-cov-mat centroids priors]
    (matrix 
      (pmap (fn [row]
             (pmap (partial ldf row inv-cov-mat) 
                   centroids 
                   priors))
           (sel data :cols (range 2 12))))))

;; calculate the scores for the training data
(def training-lda-scores 
  (calculate-scores training
                    inv-cluster-cov-mat
                    cluster-centroids
                    prior-probs))


;; calculate the scores for the testing data
(def testing-lda-scores 
  (calculate-scores testing
                    inv-cluster-cov-mat
                    cluster-centroids
                    prior-probs))

;;(bind-columns (sel training :cols 1) (plus 1 (map max-index training-lda-scores)))
;;(bind-columns (sel testing :cols 1) (plus 1 (map max-index testing-lda-scores)))

(defn max-index 
  "Returns the index of the maximum value in the given sequence."
  ([x] 
    (let [max-x (reduce max x)
          n (length x)]
      (loop [i 0]
        (if (= (nth x i) max-x)
          i
          (recur (inc i)))))))


;; define a function to calculate the error rate
(defn error-rate [data scores] 
  (/ (sum (map #(if (= %1 %2) 0 1) 
               (sel data :cols 1) 
               (plus 1 (map max-index scores))))
     (nrow data)))


;; calculate the error rate for the training data (0.316)
(error-rate training training-lda-scores)


;; calculate the error rate for the testing data (0.56)
(error-rate testing testing-lda-scores)







;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; QDA 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use '(incanter core stats charts io))

;(def training (to-matrix (read-dataset "/Users/dliebke/Desktop/esl/vowel.train.txt" :header true)))
(def training (to-matrix 
                (read-dataset "http://www-stat.stanford.edu/~tibs/ElemStatLearn/datasets/vowel.train" 
                              :header true)))
;(def testing (to-matrix (read-dataset "/Users/dliebke/Desktop/esl/vowel.test.txt" :header true)))
(def testing (to-matrix 
               (read-dataset "http://www-stat.stanford.edu/~tibs/ElemStatLearn/datasets/vowel.test" 
                             :header true)))


(def K 11)
(def p 10)
(def N (nrow training))
(def group-counts (map nrow (group-by training 1)))

;; estimate the prior probabilities for each cluster
(def prior-probs (div group-counts N))

;; estimate the centroids for each cluster
(def cluster-centroids (matrix 
                 (for [x_k (group-by training 1 :cols (range 2 12))]
                   (map mean (trans x_k)))))

;;------------------------------------------------------------------------------
;; CALCULATE THE K COVARIANCE MATRICES NECESSARY FOR QDA
;;------------------------------------------------------------------------------
               
;; estimate the covariance matrices for each cluster
(def cluster-cov-mats 
  (let [groups (group-by training 1 :cols (range 2 12))]
    (map (fn [group centroid n]
      (reduce plus 
              (map #(div 
                      (mmult (trans (minus % centroid))
                             (minus % centroid))
                      (dec n))
                   group)))
           groups cluster-centroids group-counts)))

;; calculate the inverses of the cluster covariance matrices
(def inv-cluster-cov-mats (map solve cluster-cov-mats))


;; define the quadratic discriminant function
(defn qdf [x Sigma_k Sigma-inv_k mu_k pi_k]
  (+ (- (mult 1/2 (log (det Sigma_k))))
     (- (mult 1/2 (mmult (minus x mu_k) 
                         Sigma-inv_k 
                         (trans (minus x mu_k)))))
     (log pi_k)))


(defn calculate-scores 
  ([data cov-mats inv-cov-mats centroids priors]
    (matrix 
      (pmap (fn [row]
             (pmap (partial qdf row) 
                   cov-mats 
                   inv-cov-mats 
                   centroids 
                   priors))
           (sel data :cols (range 2 12))))))

;; calculate the scores for the training data
(def training-qda-scores 
  (calculate-scores training 
                    cluster-cov-mats 
                    inv-cluster-cov-mats 
                    cluster-centroids 
                    prior-probs))

;; calculate the scores for the testing data
(def testing-qda-scores 
  (calculate-scores testing 
                    cluster-cov-mats 
                    inv-cluster-cov-mats 
                    cluster-centroids 
                    prior-probs))


;;(bind-columns (sel training :cols 1) (plus 1 (map max-index training-qda-scores)))
;;(bind-columns (sel testing :cols 1) (plus 1 (map max-index testing-qda-scores)))

(defn max-index 
  "Returns the index of the maximum value in the given sequence."
  ([x] 
    (let [max-x (reduce max x)
          n (length x)]
      (loop [i 0]
        (if (= (nth x i) max-x)
          i
          (recur (inc i)))))))


;; define a function to calculate the error rate
(defn error-rate [data scores] 
  (/ (sum (map #(if (= %1 %2) 0 1) 
               (sel data :cols 1) 
               (plus 1 (map max-index scores))))
     (nrow data)))


;; calculate the error rate for the training data
(error-rate training training-qda-scores)


;; calculate the error rate for the testing data
(error-rate testing testing-qda-scores)








;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; QDA w/ Eigenvalue Decomposition
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(use '(incanter core stats charts io))

;(def training (to-matrix (read-dataset "/Users/dliebke/Desktop/esl/vowel.train.txt" :header true)))
(def training (to-matrix 
                (read-dataset "http://www-stat.stanford.edu/~tibs/ElemStatLearn/datasets/vowel.train" 
                              :header true)))
;(def testing (to-matrix (read-dataset "/Users/dliebke/Desktop/esl/vowel.test.txt" :header true)))
(def testing (to-matrix 
               (read-dataset "http://www-stat.stanford.edu/~tibs/ElemStatLearn/datasets/vowel.test" 
                             :header true)))




(def K 11)
(def p 10)
(def N (nrow training))
(def group-counts (map nrow (group-by training 1)))

;; estimate the prior probabilities for each cluster
(def prior-probs (div group-counts N))

;; estimate the centroids for each cluster
(def cluster-centroids (matrix 
                 (for [x_k (group-by training 1 :cols (range 2 12))]
                   (map mean (trans x_k)))))

;; estimate the covariance matrices for each cluster
(def cluster-cov-matrices 
  (let [groups (group-by training 1 :cols (range 2 12))]
    (map (fn [group centroid n]
      (reduce plus 
              (map #(div 
                      (mmult (trans (minus % centroid))
                             (minus % centroid))
                      (dec n))
                   group)))
           groups cluster-centroids group-counts)))

;;------------------------------------------------------------------------------
;; ADDED TO QDA EXAMPLE TO IMPROVE PERFORMANCE
;;------------------------------------------------------------------------------
;; extract the eigenvalues and eigenvectors from the covariance matrices 
;; for each cluster, to improve performance
(def Sigma-decomp 
  (map decomp-eigenvalue cluster-cov-matrices ))
(def D (map #(diag (:values %)) Sigma-decomp))
(def U (map #(:vectors %) Sigma-decomp))

;;------------------------------------------------------------------------------
;; CHANGED FROM QDA EXAMPLE
;;------------------------------------------------------------------------------
;; define the quadratic discriminant function using the eigenvalues and eigenvectors
(defn qdf [x D_k U_k mu_k pi_k]
  (+ (- (mult 1/2 (sum (map log (diag D_k)))))
     (- (mult 1/2 
              (mmult (trans (mmult (trans U_k) 
                                   (trans (minus x mu_k))))
                     (solve D_k) 
                     (mmult (trans U_k) 
                            (trans (minus x mu_k))))))
     (log pi_k)))

;;------------------------------------------------------------------------------
;; END OF CHANGES
;;------------------------------------------------------------------------------


;; define a function to calculate the quadratic discriminant scores
(defn calculate-scores 
  ([data D U centroids priors]
    (matrix 
      (pmap (fn [row]
             (pmap (partial qdf row) D U centroids priors))
           (sel data :cols (range 2 12))))))


;; calculate the scores for each row of the training data set across all 11 clusters
(def training-qda-scores 
  (calculate-scores training
                    D U
                    cluster-centroids
                    prior-probs))

;; calculate the scores for each row of the testing data set across all 11 clusters
(def testing-qda-scores 
  (calculate-scores testing
                    D U
                    cluster-centroids
                    prior-probs))

;;(bind-columns (sel training :cols 1) (plus 1 (map max-index training-qda-scores)))
;;(bind-columns (sel testing :cols 1) (plus 1 (map max-index testing-qda-scores)))

(defn max-index 
  "Returns the index of the maximum value in the given sequence."
  ([x] 
    (let [max-x (reduce max x)
          n (length x)]
      (loop [i 0]
        (if (= (nth x i) max-x)
          i
          (recur (inc i)))))))


;; define a function to calculate the error rate
(defn error-rate [data scores] 
  (/ (sum (map #(if (= %1 %2) 0 1) 
               (sel data :cols 1) 
               (plus 1 (map max-index scores))))
     (nrow data)))


;; calculate the error rate for the training data
(error-rate training training-qda-scores)


;; calculate the error rate for the testing data
(error-rate testing testing-qda-scores)







