;; from the following blog post:
;; http://incanter-blog.org/2009/05/31/principal-components-analysis/

;; example of performing PCA on Fisher's iris data

(use '(incanter core stats charts datasets))

(def iris (to-matrix (get-dataset :iris)))
(view iris)

(def X (sel iris :cols (range 4)))
(def species (sel iris :cols 4))
(def pca (principal-components X))
(def components (:rotation pca))
(def pc1 (sel components :cols 0))
(def pc2 (sel components :cols 1))
(def x1 (mmult X pc1))
(def x2 (mmult X pc2))

(view (scatter-plot x1 x2
                    :group-by species
                    :x-label "PC1"
                    :y-label "PC2"
                    :title "Iris PCA"))

