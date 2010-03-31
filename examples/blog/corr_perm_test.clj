;; from the following blog post
;; http://incanter-blog.org/2009/06/02/correlation-and-permutation-tests/

;; example of testing the significance of a correlation
;; value with a permutation test 

(use '(incanter core stats charts datasets))

;; load the data
(def data (to-matrix (get-dataset :us-arrests)))
(def assault (sel data :cols 2))
(def urban-pop (sel data :cols 3))

;; get the sample correlation
(correlation assault urban-pop)

;; permute the data
(def permuted-assault (sample-permutations 5000 assault))
(def permuted-urban-pop (sample-permutations 5000 urban-pop))

;; generate a distribution of the permuted correlation values
(def permuted-corrs (map correlation
                         permuted-assault
                         permuted-urban-pop))

(view (histogram permuted-corrs))
(mean permuted-corrs)
(sd permuted-corrs)

;; get the 95% interval for the null hypothesis
(quantile permuted-corrs :probs [0.025 0.975])

