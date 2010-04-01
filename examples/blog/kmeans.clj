
(use '(incanter core stats charts))

;(defn init-k-means 
;  ""
;  ([k data]
;    (let [dist-fn [a b] (sq (minus a b))
;         ]
;      (loop [
;             dists []
;             centroids [(sample data)]
;            ]
;        (if (= k (count centroids))
;          centroids
;          (recur
;            (loop [])))))))

(defn index-of [coll value]
"
  Examples:

    (use '(incanter core stats))

    (def data [2 4 6 7 5 3 1])
    (index-of data (apply max data))


    (def data (diag (repeat 10 1)))
    (map #(index-of % (apply max %)) data)

"
  (loop [i 0]
    (if (= value (nth coll i))
      i
      (recur (inc i)))))


(defn indices-of [coll value]
"
  Examples:

    (use '(incanter core stats))

    (def data [2 7 4 6 7 5 3 1])
    (indices-of data (apply max data))


    (def data (diag (repeat 10 1)))
    (map #(indices-of % (apply max %)) data)

"
  (for [i (range (count coll)) :when (= value (nth coll i))] i))


(defn k-means
"
   Examples:

     (use 'incanter.datasets)
     (def iris (sel (to-matrix (get-dataset :iris)) :cols (range 4)))

     (def clusters (k-means iris 3))
     (dim (:dist-matrix clusters))
     (dim (:cluster-indices clusters))

     (count (first (:dist-matrix clusters)))
     (count (second (:dist-matrix clusters)))
     (count (nth (:dist-matrix clusters) 2))

     clusters

"
  ([data k]
    (let [p (ncol data)
          W (diag (repeat p 1))
          dist (fn [a b] 
                 (mahalanobis-dist a 
                                   :centroid (trans b)
                                   :W W))
          n (nrow data)]
      (loop [centroids (sample data :size k)
             last-members nil
             member-indices nil
             i 0]
        (let [last-members member-indices
              dist-mat (trans (map #(dist data %) centroids))
              ;dist-mat (trans 
              ;           (map #(mahalanobis-dist data %)
              ;                (trans (sel data :rows %))
              member-indices (map #(index-of % (apply min %)) dist-mat)
              cluster-indices (map (fn [idx] 
                                     (indices-of member-indices idx))
                                   (range k))
              centroids (matrix (map #(map mean (trans (sel data :rows %)) )
                             cluster-indices))
              ]
          (if (= member-indices last-members)
          ;(if true
            { :dist-matrix dist-mat
              :cluster-indices cluster-indices
              :centroids centroids
              :member-indices member-indices
              :iterations i}
            (recur centroids last-members member-indices (inc i))))))))



;(trans (map #(mahalanobis-dist iris :centroid (trans %) :W (diag (repeat (ncol iris) 1))) (sample iris :size 3)))




(defn k-means
"
   Examples:

     (use 'incanter.datasets)
     (def iris (sel (to-matrix (get-dataset :iris)) :cols (range 4)))

     (def clusters (k-means iris 3))
     ;(:member-indices clusters)
     (partition 50 (:member-indices clusters))
     (:iterations clusters)

     ;; calculate average distance of all the observations are from 
     ;; its cluster's centroid
     (:mean-sq-dist clusters)
     

     (def mahalanobis-clusters (k-means iris 3 :mahalanobis true))
     ;(:member-indices mahalanobis-clusters)
     (partition 50 (:member-indices mahalanobis-clusters))
     (:iterations mahalanobis-clusters)

"
  ([data k & options]
    (let [opts (when options (apply assoc {} options))
          mahalanobis (:mahalanobis opts)
          p (ncol data)
          W (diag (repeat p 1))
          euclid-dist (fn [a b] 
                        (mahalanobis-dist 
                          a 
                          :centroid (trans b)
                          :W W))
          n (nrow data)
          mean-sq-dist (fn [centroids membership]
                         (mean (map (fn [obs clust-idx] 
                                      (sum 
                                        (sq 
                                          (minus obs 
                                                 (sel centroids 
                                                      :rows clust-idx)))))
                                      data
                                      membership)))]
      (loop [centroids (sample data :size k)
             dist-mat (trans (map #(euclid-dist data %) centroids)) ;; euclidean to init
             last-members nil
             member-indices nil
             i 0]
        (let [last-members member-indices
              member-indices (map #(index-of % (apply min %)) dist-mat)
              cluster-indices (map (fn [idx] 
                                     (indices-of member-indices idx))
                                   (range k))
              centroids (matrix (map #(map mean (trans (sel data :rows %)) )
                             cluster-indices))
              dist-mat (if mahalanobis 
                         (trans 
                           (map (fn [row] (mahalanobis-dist data :y row))
                                (map #(matrix (sel data :rows %))
                                     cluster-indices)))
                         ;; else euclidean
                         (trans (map #(euclid-dist data %) centroids)))
              ]
          (if (= member-indices last-members)
          ;(if true
            { :dist-matrix dist-mat
              :cluster-indices cluster-indices
              :centroids centroids
              :member-indices member-indices
              :iterations i
              :mean-sq-dist (mean-sq-dist centroids member-indices)}
            (recur centroids dist-mat last-members member-indices (inc i))))))))



