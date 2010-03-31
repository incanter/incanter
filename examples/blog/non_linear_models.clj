;; from the following blog post:
;; http://incanter-blog.org/2009/06/06/fitting-non-linear-models/

(use '(incanter core optimize datasets charts))

;; Chwirut data set from NIST
;; http://www.itl.nist.gov/div898/strd/nls/data/LINKS/DATA/Chwirut1.dat
(def data (to-matrix (get-dataset :chwirut)))
(def x (sel data :cols 1))
(def y (sel data :cols 0))

;; define model function: y = exp(-b1*x)/(b2+b3*x) + e
(defn f [theta x]
  (let [[b1 b2 b3] theta]
    (div (exp (mult (minus b1) x)) (plus b2 (mult b3 x)))))

(def plot (scatter-plot x y :legend true))
(view plot)
 
;; the newton-raphson algorithm fails to converge to the correct solution 
;; using first set of start values from NIST, but the default gauss-newton 
;; algorith converges to the correct solution.

(def start1 [0.1 0.01 0.02])
(add-lines plot x (f start1 x))
(def nlm1 (non-linear-model f y x start1))
(add-lines plot x (:fitted nlm1))

;; both algorithms converges with second set of start values from NIST
(def start2 [0.15 0.008 0.010])
(add-lines plot x (f start2 x))
(def nlm2 (non-linear-model f y x start2))
(add-lines plot x (:fitted nlm2))

