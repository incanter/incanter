;; from the following blog post:
;; http://incanter-blog.org/2009/06/03/students-t-test/

;; compare the means of different treatments in the plant-growth data set

(use '(incanter core stats charts datasets))

;; load the plant-growth data
(def plant-growth (to-matrix (get-dataset :plant-growth)))

;; create box-plots of the three treatment groups
(view (box-plot (sel plant-growth :cols 0) 
                :group-by (sel plant-growth :cols 1)))
  

(def groups (group-by plant-growth 1 :cols 0))
(map mean groups) ;; returns (5.032 4.661 5.526)

;; run three different t-tests comparing the treatments
(def t-tests [(t-test (second groups) :y (first groups))
              (t-test (last groups) :y (first groups))
              (t-test (second groups) :y (last groups))])

;; view the p-values of the three-tests
(map :p-value t-tests) ;; returns (0.250 0.048 0.009)


  

