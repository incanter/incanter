;; from the following blog post:
;; http://incanter-blog.org/2009/06/04/linear-regression-with-higher-order-terms/

;; load the necessary libraries
(use '(incanter core stats charts datasets))

;; load the NIST filip data set
;; see information on this data set at http://www.itl.nist.gov/div898/strd/lls/data/Filip.shtml
(def data (to-matrix (get-dataset :filip)))

(def y (sel data :cols 0))

;; use the sweep function to center the x values to reduce collinearity of the polynomial terms
(def x (sweep (sel data :cols 1)))

(def plot (scatter-plot x y))
(view plot)

;; use the following model for the data y = x + x^2 + x^3 + ... + x^10

;; the following line of code creates a matrix of the polynomial terms x, x^2, x^3, ..., x^10,
(def X (reduce bind-columns (for [i (range 1 11)] (pow x i))))


;; run the regression
(def lm (linear-model y X))

;; view the results

;; view the overall model fit
(:f-stat lm) ;; 2162.439
(:f-prob lm) ;; 1.1E-16

;; view the R^2 of the model
(:r-square lm) ;; 0.997

;; view the estimates of the coefficients
(:coefs lm)
;; (0.878 0.065 -0.066 -0.016 0.037 0.003 -0.009 -2.8273E-4 9.895E-4 1.050E-5 -4.029E-5)
 
;; view the p-values for each of the coefficients
(:t-probs lm)
;; (0 0 0 1.28E-5 0.0 0.083 1.35E-12 0.379 3.74E-8 0.614 2.651E-5)

;; overlay the fitted values on the original scatter-plot
(add-points plot x (:fitted lm))

