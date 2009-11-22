;; from the following blog post:
;; http://incanter-blog.org/2009/07/01/bayes-multinomial/

;; Bayesian inference of multinomial distribution parameters

(use '(incanter core stats bayes charts))


(def y [727 583 137])

(div y 1447.) ;; (0.502 0.403 0.095)


(def  theta (sample-multinomial-params 1000 y))
(def theta1 (sel theta :cols 0))
(def theta2 (sel theta :cols 1))
(def theta3 (sel theta :cols 2))

;; view means, 95% CI, and histograms of the proportion parameters
(mean theta1) ;; 0.502
(sd theta1) ;; 0.0129
(quantile theta1 :probs [0.025 0.975]) ;; (0.476 0.528)
(view (histogram theta1
                 :title "Bush, Sr. Support"))

(mean theta2) ;; 0.403
(sd theta2) ;; 0.013
(quantile theta2 :probs [0.025 0.975]) ;; (0.376 0.427)
(view (histogram theta2
                 :title "Dukakis Support"))

(mean theta3) ;; 0.095
(sd theta3) ;; 0.008
(quantile theta3 :probs [0.025 0.975]) ;; (0.082 0.111)
(view (histogram theta3
                 :title "Other/Undecided"))

;; view  a histogram of the difference in proportions between the first
;; two candidates
(view (histogram (minus theta1 theta2) 
                 :title "Bush, Sr. and Dukakis Diff"))
    


;; sample the proportions directly from a Dirichlet distribution
(def alpha [1 1 1])
(def  props (sample-dirichlet 1000 (plus y alpha)))



