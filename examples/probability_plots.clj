
(ns examples.probability-plots
  (:use (incanter matrix stats charts)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; SCRATCH SPACE
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

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
(svd A)
(lu-decomp (mmult (trans A) A))
(qr-decomp A)
(det (mmult (trans A) A))
(trace A)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NORMAL DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Examples of plots from the Normal Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Normal_distribution)
(def x (range -3 3 0.01))

;; plot the PDFs of the normal distributions
(def norm-plot (xyplot x (pdf (normal-dist) x) 
                       :title "Normal PDF"
                       :series-label "(0,1)" 
                       :y-label "Density"))
(plot norm-plot)
(add-series norm-plot x (pdf (normal-dist :sd (sqrt 0.2)) x) 
            :series-label "(0,sqrt(0.2))")
(add-series norm-plot x (pdf (normal-dist :sd (sqrt 5.0)) x) 
            :series-label "(0,sqrt(5))")
(add-series norm-plot x (pdf (normal-dist :mean -2 :sd (sqrt 0.5)) x) 
            :series-label "(-2,sqrt(0.5))")

;; plot the CDFs of the normal distributions
(def norm-cdf-plot (xyplot x (cdf (normal-dist) x) 
                           :title "Normal CDF"
                           :series-label "(0,1)"
                           :y-label "Density"))
(plot norm-cdf-plot)
(add-series norm-cdf-plot x (cdf (normal-dist :sd (sqrt 0.2)) x)
            :series-label "(0,sqrt(0.2))")
(add-series norm-cdf-plot x (cdf (normal-dist :sd (sqrt 5.0)) x)
            :series-label "(0,sqrt(5))")
(add-series norm-cdf-plot x (cdf (normal-dist :mean -2 :sd (sqrt 0.5)) x)
            :series-label "(-2,sqrt(0.5))")


;; make boxplots for each of the normal distributions
(def norm-box-plot (boxplot (sample (normal-dist) 1000) 
                           :title "Normal Boxplot"
                           :series-label "(0,1)"))
(plot norm-box-plot)
(add-series norm-box-plot (sample (normal-dist :sd (sqrt 0.2)) 1000)
            :series-label "(0,sqrt(0.2))")
(add-series norm-box-plot (sample (normal-dist :sd (sqrt 5.0)) 1000)
            :series-label "(0,sqrt(5))")
(add-series norm-box-plot (sample (normal-dist :mean -2 :sd (sqrt 0.5)) 1000)
            :series-label "(-2,sqrt(0.5))")


;; make a histogram of a sample of 1000 standard normal deviates
(plot (histogram (sample (normal-dist) 1000) 
        :title "Normal Histogram"
        :series-label "(0,1)"))

;; plot the inverse of the normal distribution
(def p (range 0.01 1 0.01))
(def norm-inv-plot (xyplot p (normal-inv p) 
                           :title "Normal Inverse" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(0,1)"))
(plot norm-inv-plot)
(add-series norm-inv-plot p (normal-inv p :sd (sqrt 0.2)) 
            :series-label "(0,sqrt(0.2))")
(add-series norm-inv-plot p (normal-inv p :sd (sqrt 5.0)) 
            :series-label "(0,sqrt(5))")
(add-series norm-inv-plot p (normal-inv p :mean -2 :sd (sqrt 0.5)) 
            :series-label "(-2,sqrt(0.5))")




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; GAMMA DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Examples of plots from the Gamma Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Gamma_distribution)
(def x (range 0 20 0.1))
(def gamma-plot (xyplot x (pdf (gamma-dist :shape 1 :rate 2) x) :title "Gamma PDF"))
(plot gamma-plot)
(add-series gamma-plot x (pdf (gamma-dist :shape 2 :rate 2) x))
(add-series gamma-plot x (pdf (gamma-dist :shape 3 :rate 2) x))
(add-series gamma-plot x (pdf (gamma-dist :shape 5 :rate 1) x))
(add-series gamma-plot x (pdf (gamma-dist :shape 9 :rate 0.5) x))

(def gamma-cdf-plot (xyplot x (cdf (gamma-dist :shape 1 :rate 2) x) :title "Gamma CDF"))
(plot gamma-cdf-plot)
(add-series gamma-cdf-plot x (cdf (gamma-dist :shape 2 :rate 2) x))
(add-series gamma-cdf-plot x (cdf (gamma-dist :shape 3 :rate 2) x))
(add-series gamma-cdf-plot x (cdf (gamma-dist :shape 5 :rate 1) x))
(add-series gamma-cdf-plot x (cdf (gamma-dist :shape 9 :rate 0.5) x))


;; plot the inverse of the gamma distribution
(def p (range 0.01 1 0.01))
(def gamma-inv-plot (xyplot p (gamma-inv p :shape 1 :rate 2) 
                           :title "Gamma Inverse" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(1,1)"))
(plot gamma-inv-plot)
(add-series gamma-inv-plot p (gamma-inv p :shape 2 :rate 2) 
            :series-label "(2,2)")
(add-series gamma-inv-plot p (gamma-inv p :shape 3 :rate 2)
            :series-label "(3,2)")
(add-series gamma-inv-plot p (gamma-inv p :shape 5 :rate 1)
            :series-label "(5,1)")
(add-series gamma-inv-plot p (gamma-inv p :shape 9 :rate 0.5)
            :series-label "(9,0.5)")




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; BETA DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;; Examples of plots from the Beta Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Beta_distribution)
(def x (range 0 1 0.01))
(def beta-plot (xyplot x (pdf (beta-dist :alpha 1 :beta 1) x) :title "Beta PDF"))
(plot beta-plot)
(add-series beta-plot x (pdf (beta-dist :alpha 5 :beta 1) x))
(add-series beta-plot x (pdf (beta-dist :alpha 1 :beta 3) x))
(add-series beta-plot x (pdf (beta-dist :alpha 2 :beta 2) x))
(add-series beta-plot x (pdf (beta-dist :alpha 2 :beta 5) x))

(def beta-cdf-plot (xyplot x (cdf (beta-dist :alpha 1 :beta 1) x) :title "Beta CDF"))
(plot beta-cdf-plot)
(add-series beta-cdf-plot x (cdf (beta-dist :alpha 5 :beta 1) x))
(add-series beta-cdf-plot x (cdf (beta-dist :alpha 1 :beta 3) x))
(add-series beta-cdf-plot x (cdf (beta-dist :alpha 2 :beta 2) x))
(add-series beta-cdf-plot x (cdf (beta-dist :alpha 2 :beta 5) x))


;; plot the inverse of the Beta distribution
(def p (range 0.01 1 0.01))
(def beta-inv-plot (xyplot p (beta-inv p :alpha 1 :beta 1) 
                           :title "Gamma Inverse" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(1,1)"))
(plot beta-inv-plot)
(add-series beta-inv-plot p (beta-inv p :alpha 5 :beta 1) 
            :series-label "(5,1)")
(add-series beta-inv-plot p (beta-inv p :alpha 1 :beta 3)
            :series-label "(1,3)")
(add-series beta-inv-plot p (beta-inv p :alpha 2 :beta 2)
            :series-label "(2,2)")
(add-series beta-inv-plot p (beta-inv p :alpha 2 :beta 5)
            :series-label "(5,5)")




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHI SQUARE DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Chi Square Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Chi_square_distribution)
(def x (range 0.01 8 0.01))
(def chisq-plot (xyplot x (pdf (chisq-dist :df 1) x) :title "Chi Square PDF"))
(plot chisq-plot)
(add-series chisq-plot x (pdf (chisq-dist :df 2) x))
(add-series chisq-plot x (pdf (chisq-dist :df 3) x))
(add-series chisq-plot x (pdf (chisq-dist :df 4) x))
(add-series chisq-plot x (pdf (chisq-dist :df 5) x))

(def chisq-cdf-plot (xyplot x (cdf (chisq-dist :df 1) x) :title "Chi Square CDF"))
(plot chisq-cdf-plot)
(add-series chisq-cdf-plot x (cdf (chisq-dist :df 2) x))
(add-series chisq-cdf-plot x (cdf (chisq-dist :df 3) x))
(add-series chisq-cdf-plot x (cdf (chisq-dist :df 4) x))
(add-series chisq-cdf-plot x (cdf (chisq-dist :df 5) x))

;; plot the inverse of the Chi square distribution
(def p (range 0.01 1 0.01))
(def chisq-inv-plot (xyplot p (chisq-inv p :df 1) 
                           :title "Chi Square Inverse" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(df=1)"))
(plot chisq-inv-plot)
(add-series chisq-inv-plot p (chisq-inv p :df 2) 
            :series-label "(df=2)")
(add-series chisq-inv-plot p (chisq-inv p :df 3)
            :series-label "(df=2)")
(add-series chisq-inv-plot p (chisq-inv p :df 4)
            :series-label "(df=4)")
(add-series chisq-inv-plot p (chisq-inv p :df 5)
            :series-label "(df=5)")





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; STUDENT'S T DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Student's T-Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Student-t_distribution)
(def x (range -5 5 0.01))
(def t-plot (xyplot x (pdf (t-dist :df 1) x) :title "Student's T PDF"))
(plot t-plot)
(add-series t-plot x (pdf (t-dist :df 2) x))
(add-series t-plot x (pdf (t-dist :df 5) x))
(add-series t-plot x (pdf (t-dist :df 10) x))
(add-series t-plot x (pdf (t-dist :df 1000) x))

(def t-cdf-plot (xyplot x (cdf (t-dist :df 1) x) :title "Student's T CDF"))
(plot t-cdf-plot)
(add-series t-cdf-plot x (cdf (t-dist :df 2) x))
(add-series t-cdf-plot x (cdf (t-dist :df 5) x))
(add-series t-cdf-plot x (cdf (t-dist :df 10) x))
(add-series t-cdf-plot x (cdf (t-dist :df 1000) x))


;; plot the inverse of the Student's t distribution
(def p (range 0.01 1 0.01))
(def t-inv-plot (xyplot p (t-inv p :df 1) 
                           :title "Student's t Inverse" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(df=1)"))
(plot t-inv-plot)
(add-series t-inv-plot p (t-inv p :df 2) 
            :series-label "(df=2)")
(add-series t-inv-plot p (t-inv p :df 5)
            :series-label "(df=5)")
(add-series t-inv-plot p (t-inv p :df 10)
            :series-label "(df=10)")
(add-series t-inv-plot p (t-inv p :df 50)
            :series-label "(df=1000)")



          
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EXPONENTIAL DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Exponential Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Exponential_distribution)
(def x (range 0 5 0.01))
(def exp-plot (xyplot x (pdf (exp-dist :lambda 1/2) x) :title "Exponential PDF"))
(plot exp-plot)
(add-series exp-plot x (pdf (exp-dist :lambda 1) x))
(add-series exp-plot x (pdf (exp-dist :lambda 1.5) x))

(def exp-cdf-plot (xyplot x (cdf (exp-dist :lambda 1/2) x) :title "Exponential CDF"))
(plot exp-cdf-plot)
(add-series exp-cdf-plot x (cdf (exp-dist :lambda 1) x))
(add-series exp-cdf-plot x (cdf (exp-dist :lambda 1.5) x))


;;; Examples of plots from the Continuous Uniform Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Uniform_distribution)
(def x (range 1 10.01 0.01))
(def uniform-plot (xyplot x (pdf (uniform-dist :min 1 :max 10) x) :title "Uniform PDF"))
(plot uniform-plot)

(def uniform-cdf-plot (xyplot x (cdf (uniform-dist :min 1 :max 10) x) :title "Uniform CDF"))
(plot uniform-cdf-plot)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DISCRETE DISTRIBUTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; BINOMIAL DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Binomial Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Binomial_distribution)
(def x1 (range 0 20))
(def x2 (range 0 40))
(def binomial-plot (scatter x1 (pdf (binomial-dist :p 1/2 :n 20) x1) :title "Binomial PDF"))
(plot binomial-plot)
(add-series binomial-plot x1 (pdf (binomial-dist :p 0.7 :n 20) x1))
(add-series binomial-plot x2 (pdf (binomial-dist :p 1/2 :n 40) x2))

(def binomial-cdf-plot (scatter x1 (cdf (binomial-dist :p 1/2 :n 20) x1) :title "Binomial CDF"))
(plot binomial-cdf-plot)
(add-series binomial-cdf-plot x1 (cdf (binomial-dist :p 0.7 :n 20) x1))
(add-series binomial-cdf-plot x2 (cdf (binomial-dist :p 1/2 :n 40) x2))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NEGATIVE BINOMIAL DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Negative Binomial Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Negative_binomial_distribution)
(plot (histogram (sample (neg-binomial-dist :p 1/2 :n 1) 1000) :title "Negative Binomial (p=1/2, n=1)"))
(plot (histogram (sample (neg-binomial-dist :p 1/2 :n 2) 1000) :title "Negative Binomial (p=1/2, n=2)"))
(plot (histogram (sample (neg-binomial-dist :p 1/2 :n 5) 1000) :title "Negative Binomial (p=1/2, n=5)"))
(plot (histogram (sample (neg-binomial-dist :p 1/2 :n 10) 1000) :title "Negative Binomial (p=1/2, n=10)"))
(plot (histogram (sample (neg-binomial-dist :p 1/2 :n 20) 1000) :title "Negative Binomial (p=1/2, n=20)"))
(plot (histogram (sample (neg-binomial-dist :p 1/2 :n 40) 1000) :title "Negative Binomial (p=1/2, n=40)"))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; POISSON DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Poisson Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Poisson_distribution)
(def x1 (range 0 20))
(def poisson-plot (scatter x1 (pdf (poisson-dist :mean 1) x1) :title "Poisson PDF"))
(plot poisson-plot)
(add-series poisson-plot x1 (pdf (poisson-dist :mean 4) x1))
(add-series poisson-plot x1 (pdf (poisson-dist :mean 10) x1))

(def poisson-cdf-plot (scatter x1 (cdf (poisson-dist :mean 1) x1) :title "Poisson CDF"))
(plot poisson-cdf-plot)
(add-series poisson-cdf-plot x1 (cdf (poisson-dist :mean 4) x1))
(add-series poisson-cdf-plot x1 (cdf (poisson-dist :mean 10) x1))



