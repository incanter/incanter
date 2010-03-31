
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; SCRATCH SPACE
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn to-binary [n] (for [bit (Integer/toBinaryString n)] (if (= bit \1) 1 0)))

(def A (matrix [[1 2 3]
                [2 4 6]
                [3 6 9]
                [4 -8 -10]]))

;; http://acs.lbl.gov/~hoschek/colt/api/cern/colt/matrix/doublealgo/Statistic.html
(cern.colt.matrix.doublealgo.Statistic/covariance A)
(cern.colt.matrix.doublealgo.Statistic/correlation (cern.colt.matrix.doublealgo.Statistic/covariance A))
(cern.colt.matrix.doublealgo.Statistic/distance A cern.colt.matrix.doublealgo.Statistic/EUCLID)
(cern.colt.matrix.doublealgo.Statistic/distance A cern.colt.matrix.doublealgo.Statistic/CANBERRA)
(cern.colt.matrix.doublealgo.Statistic/distance A cern.colt.matrix.doublealgo.Statistic/BRAY_CURTIS)
(cern.colt.matrix.doublealgo.Statistic/distance A cern.colt.matrix.doublealgo.Statistic/MANHATTAN)
(cern.colt.matrix.doublealgo.Statistic/distance A cern.colt.matrix.doublealgo.Statistic/MAXIMUM)

(eigenvalue-decomp (mmult (trans A) A))
(svd-decomp A)
(lu-decomp (mmult (trans A) A))
(qr-decomp A)
(det (mmult (trans A) A))
(trace A)

;; should create a multi-method called 'view' for viewing matrices, like 
;; inspect-table, and datasets (with column names), and plots (instead
;; of calling the command 'plot' which seems redundant.
(inspect-table (to-matrix (read-dataset "./data/test.dat" :delim \space :header true)))

;; adding meta data to functions: from #clojure irc
;; rhickey: user=> (def f (proxy [clojure.lang.AFn] [{:my :meta}] (invoke [] 42)))
;; rhickey: #'user/f
;; rhickey: user=> (f)
;; rhickey: 42
;; rhickey: user=> ^f
;; rhickey: {:my :meta}

(def ols-data (to-matrix (read-dataset 
                           (str (System/getProperty "incanter.home") "/data/olsexamp.dat")
                           :delim \space
                           :header true)))

(def x (sel ols-data (range 0 2313) (range 1 10)))
(def y (sel ols-data (range 0 2313) 10))

(time (def b-reg-full (bayes-regression-full 5000 x y)))


;; cumulative-mean function
(defn cumulative-mean [coll] (map mean (for [i (range (dec (count coll)) -1 -1)] (drop-last i coll))))
;; trace plots
(def traceplot (line-plot (range (count (:var b-reg-full))) (:var b-reg-full) 
                        :title "Trace Plot" 
                        :x-label "Iteration" 
                        :y-label "Value"))
(add-series traceplot (range (count (:var b-reg-full))) (cumulative-mean (:var b-reg-full)))

(view traceplot)



(defn newton-raphson
"
  Examples:
    (use '(incanter core optimize charts))
    (defn quad-fx [[x y]] (+ (* x x) (* x y) (* y y y) -8))
    (newton-raphson quad-fx [1 1])

    (newton-raphson #((derivative pdf-normal) (first %)) [0])

"
  ([fx theta & options]
    (let [opts (if options (apply assoc {} options) nil)
          tol (if (:tolerance opts) (:tolerance opts) 1E-5)
          max-iter (if (:max-iterations opts) (:max-iterations opts) 500)
          p (count theta)
          grad-fn (gradient fx p)
          hess-fn (hessian fx p)]
      (loop [i 0 th theta]
        (println i th)
        (if (or (= i max-iter) (< (reduce #(max (Math/abs %1) (Math/abs %2)) (grad-fn th)) tol))
          (grad-fn th)
          (recur (inc i) (vec (map - th (mmult (solve (hess-fn th)) (grad-fn th))))))))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; generate data with a cdf interaction
(def x1 (sample-normal 100))
(def x2 (sample-normal 100))
(def err (sample-normal 100))

(def x-cdf (mult (cdf-normal x1) x2))
(def x (bind-columns x1 x2 x-cdf err))
(def y (mmult x [0.0 0.0 0.5 0.1]))


;; no interaction term
(def lm (linear-model y (sel x :cols (range 2))))
(:coefs lm)
(:r-square lm)
(:adj-r-square lm)
(:f-prob lm)


;; add simple interaction term
(def x-prod (bind-columns x1 x2 (mult x1 x2)))
(def lm (linear-model y x-prod))
(:coefs lm)
(:r-square lm)
(:adj-r-square lm)
(:f-prob lm)

;; add transformed interaction term
(def lm (linear-model y (sel x :cols (range 3))))
(:coefs lm)
(:r-square lm)
(:adj-r-square lm)
(:f-prob lm)

;;-------------------------------------------

;; generated data with a pdf interaction
(def x-pdf (mult (pdf-normal x1) x2))
(def x (bind-columns x1 x2 x-pdf err))
(def y (mmult x [0.0 0.0 0.5 0.1]))


;; no interaction term 
(def lm (linear-model y (sel x :cols (range 2))))
(:coefs lm)
(:r-square lm)
(:adj-r-square lm)
(:f-prob lm)

(view (scatter-plot (:fitted lm) (:residuals lm)))

;; add simple interaction term
(def x-prod (bind-columns x1 x2 (mult x1 x2)))
(def lm (linear-model y x-prod))
(:coefs lm)
(:r-square lm)
(:adj-r-square lm)
(:f-prob lm)
(view (scatter-plot (:fitted lm) (:residuals lm)))

;; add transformed interaction term
(def lm (linear-model y (sel x :cols (range 3))))
(:coefs lm)
(:r-square lm)
(:adj-r-square lm)
(:f-prob lm)
(view (scatter-plot (:fitted lm) (:residuals lm)))



(view (scatter-plot x1 (sort x-cdf)))
(view (scatter-plot x1 (sort x-pdf)))

(def plot (line-plot x1 (cdf-normal x1)))
(view plot)
(doseq [b (range 0.1 3 0.5)] (add-lines plot  x1 (mult b (cdf-normal x1))))
(clear-background plot)


(def plot (line-plot x1 (pdf-normal x1)))
(view plot)
(doseq [b (range 0.1 3 0.5)] (add-lines plot  x1 (mult b (pdf-normal x1))))
(clear-background plot)


(def foo (range 0.1 3 0.1))

(def plot (line-plot foo (pdf-chisq foo :df 1)))
(view plot)
(doseq [b (range 0.1 3 0.5)] (add-lines plot  foo (mult b (pdf-chisq foo :df 1))))
(clear-background plot)


(defn truncated-variance 
" Returns the variance of a normal distribution truncated at a and b.

  Options:
    :mean (default 0) mean of untruncated normal distribution
    :sd (default 1) standard deviation of untruncated normal distribution
    :a (default -infinity) lower truncation point
    :b (default +infinity) upper truncation point

  Examples:
  
    (use '(incanter core stats))
    (truncated-variance :a -1.96 :b 1.96)  
    (truncated-variance :a 0)
    (truncated-variance :b 0)

    (use 'incanter.charts)
    (def x (range -3 3 0.1))
    (def plot (line-plot x (map #(truncated-variance :a %) x)))
    (view plot)
    (add-lines plot x (map #(truncated-variance :b %) x))

    (def samp (sample-normal 10000))
    (add-lines plot x (map #(variance (filter (fn [s] (> s %)) samp)) x))
    (add-lines plot x (map #(variance (mult samp (indicator (fn [s] (> s %)) samp))) x))


  References:
    DeMaris, A. (2004) Regression with social data: modeling continuous and limited response variables. 
      Wiley-IEEE.
    
    http://en.wikipedia.org/wiki/Truncated_normal_distribution
"
  ([& options]
    (let [opts (if options (apply assoc {} options) nil)
          mu (if (:mean opts) (:mean opts) 0)
          sd (if (:sd opts) (:sd opts) 1)
          sigma-sq (* sd sd)
          a (if (:a opts) (:a opts) Double/NEGATIVE_INFINITY)
          b (if (:b opts) (:b opts) Double/POSITIVE_INFINITY)
          lambda (fn [alpha] (/ (pdf-normal alpha) (- 1 (cdf-normal alpha))))
          delta (fn [alpha] (* (lambda alpha) (- (lambda alpha) alpha)))
          ;one-tail-var (fn [alpha s-sq] 
          ;               (* s-sq 
          ;                  (- 1 (cdf-normal alpha)) 
          ;                  (+ (- 1 (delta alpha)) 
          ;                     (* (pow (- alpha (lambda alpha)) 2) 
          ;                        (cdf-normal alpha)))))
          a-std (if (= a Double/NEGATIVE_INFINITY) Double/NEGATIVE_INFINITY (/ (- a mu) sd))
          b-std (if (= b Double/POSITIVE_INFINITY) Double/POSITIVE_INFINITY (/ (- b mu) sd))
          pdf-a (if (= a Double/NEGATIVE_INFINITY) 0 (pdf-normal a-std))
          pdf-b (if (= b Double/POSITIVE_INFINITY) 0 (pdf-normal b-std))
          cdf-a (if (= a Double/NEGATIVE_INFINITY) 0 (cdf-normal a-std))
          cdf-b (if (= b Double/POSITIVE_INFINITY) 1 (cdf-normal b-std))]
      (cond 
        (and (= b Double/POSITIVE_INFINITY) (= a Double/NEGATIVE_INFINITY))
          sigma-sq
        (and (= b Double/POSITIVE_INFINITY) (> a Double/NEGATIVE_INFINITY))
          (* sigma-sq (- 1 (delta a-std)))
          ;(one-tail-var a-std sigma-sq)
        (and (= a Double/NEGATIVE_INFINITY) (< b Double/POSITIVE_INFINITY))
          (* sigma-sq (- 1 (delta (- 1 b-std))))
          ;(- sigma-sq (one-tail-var b-std sigma-sq))
        :else
          (* sigma-sq 
            (+ 1 (/ (- (* a-std pdf-a) (* b-std pdf-b))
                    (- cdf-b cdf-a))
              (- (pow (/ (- pdf-a pdf-b) (- cdf-b cdf-a)) 2))))))))



(def x (range -3 3 0.1))
(def plot (line-plot x (map #(truncated-variance :a %) x)))
(add-lines plot (abs x) (map #(truncated-variance :a %) (abs x)))
(view plot)
(def samp (sample-normal 10000))
(add-lines plot x (map #(variance (filter (fn [s] (> s %)) samp)) x))
(add-lines plot (abs x) (map #(variance (filter (fn [s] (> s %)) (abs samp))) (abs x)))
;(add-lines plot x (map #(variance (mult samp (indicator (fn [s] (> s %)) samp))) x))
;(add-lines plot x (map #(truncated-variance :a % :b 100) x))


(def x (range -3 3 0.1))
(def plot (line-plot x (cdf-normal x)))
(view plot)
(def samp (sample-normal 10000))
(add-lines plot x (map #(variance (mult samp (indicator (fn [s] (> s %)) samp))) x))
(add-lines plot (abs x) (map #(variance (mult samp (indicator (fn [s] (> s %)) (abs samp)))) (abs x)))
(use 'incanter.optimize)
(def pdf-deriv (derivative pdf-normal))
(add-lines plot x (map pdf-deriv x))



(use 'incanter.optimize)
(def trunc-var-deriv (derivative #(truncated-variance :a %)))
(add-lines plot x (map trunc-var-deriv x))



;; censored normal distributions
;; http://www.timthomas.net/statistics/Appendices%20on%20Censored%20Data%20Analysis%20v20.doc
                          
  
(defn lambda-two-sided 
  ([a-std b-std] 
   (/ (- (pdf-normal a-std) (pdf-normal b-std)) 
      (- (cdf-normal b-std) (cdf-normal a-std)))))

(defn lambda-lower 
  ([a-std] (/ (pdf-normal a-std) (- 1 (cdf-normal a-std)))))

(defn lambda-upper 
  ([b-std] 
   (/ (- (pdf-normal b-std)) (cdf-normal b-std))))

(defn psi [y mu sigma] (/ (- y mu) sigma))


(defn censored-mean-two-sided 
  ([a b mu sigma]
   (let [a-std (psi a mu sigma)
         b-std (psi b mu sigma)
         cdf-a (cdf-normal a-std)
         cdf-b (cdf-normal b-std)]
      (+ (* cdf-a a)
         (* (- cdf-b cdf-a) (+ mu (* sigma (lambda-two-sided a-std b-std))))
         (* (- 1 cdf-b) b)))))


(defn censored-variance-two-sided
  ([a b mu sigma]
   (let [a-std (psi a mu sigma)
         b-std (psi b mu sigma)
         sigma-sq (* sigma sigma)
         Ey (censored-mean-two-sided a b mu sigma)
         cdf-a (cdf-normal a-std)
         cdf-b (cdf-normal b-std)]
     (+ (* cdf-a (* a a))
        (* (- cdf-b cdf-a) 
           (+ sigma-sq (* mu mu) (* 2 mu sigma (lambda-two-sided a-std b-std))))
        (* sigma-sq (- (* a-std (pdf-normal a-std)) (* b-std (pdf-normal b-std))))
        (- (* (- 1 cdf-b) (* b b)) (* Ey Ey))))))



(defn censored-mean-lower
  ([a mu sigma]
   (let [a-std (psi a mu sigma)]
    (+ (* (cdf-normal a-std) a)
       (* (- 1 (cdf-normal a-std)) 
          (+ mu (* sigma (lambda-lower a-std))))))))


(defn censored-variance-lower
  ([a mu sigma]
   (let [a-std (psi a mu sigma)
         cdf-a (cdf-normal a-std)
         sigma-sq (* sigma sigma)
         Ey (censored-mean-lower a mu sigma)]
     (+ (* cdf-a (* a a))
        (* (- 1 cdf-a)
           (+ sigma-sq (* mu mu) (* 2 mu sigma (lambda-lower a-std))))
        (* sigma-sq a-std (pdf-normal a-std))
        (- (* Ey Ey))))))



(defn censored-mean-upper
  ([b mu sigma]
   (let [b-std (psi b mu sigma)]
    (+ (* (cdf-normal b-std) (+ mu (* sigma (lambda-upper b-std))))
       (* (- 1 (cdf-normal b-std)) b)))))


(defn censored-variance-upper
  ([b mu sigma]
   (let [sigma-sq (* sigma sigma)
         b-std (psi b mu sigma)
         cdf-b (cdf-normal b-std)
         Ey (censored-mean-upper b mu sigma)]
      (+ (* cdf-b
            (+ sigma-sq (* mu mu) (* 2 mu sigma (lambda-upper b-std))))
         (- (* sigma-sq b-std (pdf-normal b-std)))
         (* (- 1 cdf-b) (* b b))
         (- (* Ey Ey))))))


(defn censored-mean-zero
  ([a mu sigma]
   (let [a-std (psi a mu sigma)]
     (* (- 1 (cdf-normal a-std)) (+ mu (* sigma (lambda-lower a-std)))))))


(defn censored-variance-zero
  ([a mu sigma]
   (let [a-std (psi a mu sigma)
         sigma-sq (* sigma sigma)
         Ey (censored-mean-zero a mu sigma)]
    (+ (* (- 1 (cdf-normal a-std))
          (+ sigma-sq (* mu mu) (* 2 mu sigma (lambda-lower a-std))))
       (* sigma-sq a-std (pdf-normal a-std))
       (- (* Ey Ey))))))



;; examples
  
(use 'incanter.charts)
(def x (range -3 3 0.1))
(def plot (line-plot x (map #(censored-variance-lower % 0 1) x)))
(view plot)
(add-lines plot x (map #(censored-variance-upper % 0 1) x))

(add-lines plot x (map #(censored-variance-two-sided % 100 0 1) x))

(add-lines plot x (map #(truncated-variance :a %) x))
(add-lines plot (abs x) (map #(truncated-variance :a %) (abs x)))
(add-points plot [0] [(censored-variance-zero 0 0 1)])


;; normal sample
(def samp (sample-normal 10000))
;; truncated sample
(add-lines plot x (map #(variance (filter (fn [s] (> s %)) samp)) x))
;; censored sample
(add-lines plot x (map #(variance (plus
                                    (mult samp (indicator (fn [s] (> s %)) samp))
                                    (mult % (indicator (fn [s] (<= s %)) samp))))
                                    x))



;; compare censored-variance-two-sided to simulated censored data
(def a -0.5)
(def b 0.5)
(censored-variance-two-sided a b 0 1)
(def samp (sample-normal 10000))
(variance (plus (mult samp (indicator (fn [s] (and (> s a) (< s b))) samp))
                (mult a (indicator (fn [s] (<= s a)) samp))
                (mult b (indicator (fn [s] (>= s b)) samp))))


;; variance of lower tail of normal distribution
(def b -0.5)
(censored-variance-upper b 0 1)
(def samp (sample-normal 10000))
(variance (plus (mult samp (indicator (fn [s] (< s b)) samp))
                (mult b (indicator (fn [s] (>= s b)) samp))))


;; variance of upper tail of normal distribution
(def a 0.5)
(censored-variance-lower a 0 1)
(def samp (sample-normal 10000))
(variance (plus (mult samp (indicator (fn [s] (> s a)) samp))
                (mult a (indicator (fn [s] (<= s a)) samp))))

;; variance of 'flat-topped' normal distribution
(def t 0.5)
(- (+ (censored-variance-lower (- t) 0 1)
      (censored-variance-upper t 0 1))
   1 ) ;(censored-variance-two-sided (- t) t 0 1))
(def samp (sample-normal 10000))
(variance (plus (mult samp (indicator (fn [s] (or (< s (- t)) (> s t))) samp))
                (mult t (indicator (fn [s] (or (>= s (- t)) (<= s t))) samp))))



