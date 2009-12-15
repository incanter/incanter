
;; See: http://matlabdatamining.blogspot.com/2006/11/mahalanobis-distance.html
;; http://en.wikipedia.org/wiki/Mahalanobis_distance

(use '(incanter core stats charts))

;; EXAMPLE
;; generate some multivariate normal data with a single outlier.
(def data (bind-rows
            (bind-columns 
              (sample-mvn 100 
                          :sigma (matrix [[1 0.9] 
                                          [0.9 1]])))
            [-1.75 1.75]))

;; view a scatter plot of the data
(let [[x y] (trans data)]
  (doto (scatter-plot x y)
    (add-points [(mean x)] [(mean y)])
    (add-pointer -1.75 1.75 :text "Outlier")
    (add-pointer (mean x) (mean y) :text "Centroid")
    view))

;; calculate the mahalanobis distances of each point from the centroid.
(def dists (mahalanobis-distance data))
;; view a bar-chart of the distances
(view (bar-chart (range 102) dists))

;; calculate the euclidean distances of each point from the centroid.
(def dists (mahalanobis-distance data :W (matrix [[1 0] [0 1]])))
;; view a bar-chart of the distances
(view (bar-chart (range 102) dists))


(mahalanobis-distance [-1.75 1.75] :y data)
(mahalanobis-distance [-1.75 1.75] 
                  :y data 
                  :W (matrix [[1 0] 
                              [0 1]]))

(mahalanobis-distance [2.5 2.5] :y data)
(mahalanobis-distance [3.5 3.5] :y data)

  
