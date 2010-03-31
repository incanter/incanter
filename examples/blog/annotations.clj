;; from the following blog post
;; http://incanter-blog.org/2009/06/07/annotating-incanter-plots/

(use '(incanter core charts))

(def x (range (* -2 Math/PI) (* 2 Math/PI) 0.01))
(def plot (xy-plot x (sin x)))
(view plot)

;; annotate the plot
(doto plot
  (add-pointer (- Math/PI) (sin (- Math/PI)) 
               :text "(-pi, (sin -pi))")
  (add-pointer Math/PI (sin Math/PI) 
               :text "(pi, (sin pi))" :angle :ne)
  (add-pointer (* 1/2 Math/PI) (sin (* 1/2 Math/PI)) 
               :text "(pi/2, (sin pi/2))" :angle :south))


;; try the different angle options
(doto plot
  (add-pointer 0 0 :text "north" :angle :north)
  (add-pointer 0 0 :text "nw" :angle :nw)
  (add-pointer 0 0 :text "ne" :angle :ne)
  (add-pointer 0 0 :text "west" :angle :west)
  (add-pointer 0 0 :text "east" :angle :east)
  (add-pointer 0 0 :text "south" :angle :south)
  (add-pointer 0 0 :text "sw" :angle :sw)
  (add-pointer 0 0 :text "se" :angle :se))



;; PCA chart example
(use '(incanter core stats charts datasets))
;; load the iris dataset
(def iris (to-matrix (get-dataset :iris)))
;; run the pca
(def pca (principal-components (sel iris :cols (range 4))))
;; extract the first two principal components
(def pc1 (sel (:rotation pca) :cols 0))
(def pc2 (sel (:rotation pca) :cols 1))

;; project the first four dimension of the iris data onto the first
;; two principal components
(def x1 (mmult (sel iris :cols (range 4)) pc1)) 
(def x2 (mmult (sel iris :cols (range 4)) pc2)) 
   
;; now plot the transformed data, coloring each species a different color
(def plot (scatter-plot x1 x2 
            :group-by (sel iris :cols 4)
            :x-label "PC1" :y-label "PC2" :title "Iris PCA"))

(view plot)


;; add some text annotations
(doto plot
  (add-text -2.5 -6.5 "Setosa")
  (add-text -5 -5.5 "Versicolor") 
  (add-text -8 -5.5 "Virginica"))

;; put box around the first group
(add-polygon plot [[-3.2 -6.3] [-2 -6.3] [-2 -3.78] [-3.2 -3.78]])

