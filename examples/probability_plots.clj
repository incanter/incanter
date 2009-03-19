
(ns examples.probability-plots
  (:use (incanter matrix stats charts)))


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
(inspect-table (as-matrix (read-dataset "./data/test.dat" :header true)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NORMAL DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Examples of plots from the Normal Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Normal_distribution)
(def x (range -3 3 0.01))

;; plot the PDFs of the normal distributions
(def norm-plot (xyplot x (pdf-normal x) 
                       :title "Normal PDF (mean, sd)"
                       :series-label "(0,1)" 
                       :y-label "Density"))
(plot norm-plot)
(add-series norm-plot x (pdf-normal x :sd (sqrt 0.2))  
            :series-label "(0,sqrt(0.2))")
(add-series norm-plot x (pdf-normal x :sd (sqrt 5.0)) 
            :series-label "(0,sqrt(5))")
(add-series norm-plot x (pdf-normal x :mean -2 :sd (sqrt 0.5)) 
            :series-label "(-2,sqrt(0.5))")

;; plot the CDFs of the normal distributions
(def norm-cdf-plot (xyplot x (cdf-normal x) 
                           :title "Normal CDF (mean, sd)"
                           :series-label "(0,1)"
                           :y-label "Density"))
(plot norm-cdf-plot)
(add-series norm-cdf-plot x (cdf-normal x :sd (sqrt 0.2))
            :series-label "(0,sqrt(0.2))")
(add-series norm-cdf-plot x (cdf-normal x :sd (sqrt 5.0))
            :series-label "(0,sqrt(5))")
(add-series norm-cdf-plot x (cdf-normal x :mean -2 :sd (sqrt 0.5))
            :series-label "(-2,sqrt(0.5))")


;; make boxplots for each of the normal distributions
(def norm-box-plot (boxplot (sample-normal 1000) 
                           :title "Normal Boxplot (mean, sd)"
                           :series-label "(0,1)"))
(plot norm-box-plot)
(add-series norm-box-plot (sample-normal 1000 :sd (sqrt 0.2))
            :series-label "(0,sqrt(0.2))")
(add-series norm-box-plot (sample-normal 1000 :sd (sqrt 5.0))
            :series-label "(0,sqrt(5))")
(add-series norm-box-plot (sample-normal 1000 :mean -2 :sd (sqrt 0.5))
            :series-label "(-2,sqrt(0.5))")


;; make a histogram of a sample of 1000 standard normal deviates
(plot (histogram (sample-normal 1000) 
        :title "Normal Histogram (mean, sd)"
        :series-label "(0,1)"))

;; plot the inverse of the normal distribution
(def p (range 0.01 1 0.01))
(def norm-quant-plot (xyplot p (quantile-normal p) 
                           :title "Normal Inverse (mean, sd)" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(0,1)"))
(plot norm-quant-plot)
(add-series norm-quant-plot p (quantile-normal p :sd (sqrt 0.2)) 
            :series-label "(0,sqrt(0.2))")
(add-series norm-quant-plot p (quantile-normal p :sd (sqrt 5.0)) 
            :series-label "(0,sqrt(5))")
(add-series norm-quant-plot p (quantile-normal p :mean -2 :sd (sqrt 0.5)) 
            :series-label "(-2,sqrt(0.5))")




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; GAMMA DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Examples of plots from the Gamma Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Gamma_distribution)
(def x (range 0 20 0.1))
(def gamma-plot (xyplot x (pdf-gamma x :shape 1 :rate 2) :title "Gamma PDF (shape,rate)"))
(plot gamma-plot)
(add-series gamma-plot x (pdf-gamma x :shape 2 :rate 2))
(add-series gamma-plot x (pdf-gamma x :shape 3 :rate 2))
(add-series gamma-plot x (pdf-gamma x :shape 5 :rate 1))
(add-series gamma-plot x (pdf-gamma x :shape 9 :rate 0.5))

(def gamma-cdf-plot (xyplot x (cdf-gamma x :shape 1 :rate 2) :title "Gamma CDF (shape,rate)"))
(plot gamma-cdf-plot)
(add-series gamma-cdf-plot x (cdf-gamma x :shape 2 :rate 2))
(add-series gamma-cdf-plot x (cdf-gamma x :shape 3 :rate 2))
(add-series gamma-cdf-plot x (cdf-gamma x :shape 5 :rate 1))
(add-series gamma-cdf-plot x (cdf-gamma x :shape 9 :rate 0.5))


(def gamma-box-plot (boxplot (sample-gamma 1000 :shape 1 :rate 2) 
                           :title "Gamma Boxplot (shape, rate)"
                           :series-label "(1,2)"))
(plot gamma-box-plot)
(add-series gamma-box-plot (sample-gamma 1000 :shape 2 :rate 2)
            :series-label "(2,2)")
(add-series gamma-box-plot (sample-gamma 1000 :shape 3 :rate 2)
            :series-label "(3,2)")
(add-series gamma-box-plot (sample-gamma 1000 :shape 5 :rate 1)
            :series-label "(5,1)")
(add-series gamma-box-plot (sample-gamma 1000 :shape 9 :rate 0.5)
            :series-label "(9,0.5)")


;; make a histogram of a sample of 1000 Gamma deviates
(plot (histogram (sample-gamma 1000 :shape 1 :rate 2) 
        :title "Gamma Histogram (shape, rate)"
        :series-label "(1,2)"))


;; plot the quantiles of the gamma distribution
(def p (range 0.01 1 0.01))
(def quantile-gamma-plot (xyplot p (quantile-gamma p :shape 1 :rate 2) 
                           :title "Gamma Quantiles (shape,rate)" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(1,1)"))
(plot quantile-gamma-plot)
(add-series quantile-gamma-plot p (quantile-gamma p :shape 2 :rate 2) 
            :series-label "(2,2)")
(add-series quantile-gamma-plot p (quantile-gamma p :shape 3 :rate 2)
            :series-label "(3,2)")
(add-series quantile-gamma-plot p (quantile-gamma p :shape 5 :rate 1)
            :series-label "(5,1)")
(add-series quantile-gamma-plot p (quantile-gamma p :shape 9 :rate 0.5)
            :series-label "(9,0.5)")




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; BETA DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;; Examples of plots from the Beta Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Beta_distribution)
(def x (range 0 1 0.01))
(def beta-plot (xyplot x (pdf-beta x :alpha 1 :beta 1) :title "Beta PDF (alpha,beta)"))
(plot beta-plot)
(add-series beta-plot x (pdf-beta x :alpha 5 :beta 1))
(add-series beta-plot x (pdf-beta x :alpha 1 :beta 3))
(add-series beta-plot x (pdf-beta x :alpha 2 :beta 2))
(add-series beta-plot x (pdf-beta x :alpha 2 :beta 5))

(def beta-cdf-plot (xyplot x (cdf-beta x :alpha 1 :beta 1) :title "Beta CDF (alpha,beta)"))
(plot beta-cdf-plot)
(add-series beta-cdf-plot x (cdf-beta x :alpha 5 :beta 1))
(add-series beta-cdf-plot x (cdf-beta x :alpha 1 :beta 3))
(add-series beta-cdf-plot x (cdf-beta x :alpha 2 :beta 2))
(add-series beta-cdf-plot x (cdf-beta x :alpha 2 :beta 5))

            
;; make boxplots for each of the Beta distributions
(def beta-box-plot (boxplot (sample-beta 1000 :alpha 1 :beta 1) 
                           :title "Beta Boxplot (alpha,beta)"
                           :series-label "(1,1)"))
(plot beta-box-plot)
(add-series beta-box-plot (sample-beta 1000 :alpha 5 :beta 1)
            :series-label "(5,1)")
(add-series beta-box-plot (sample-beta 1000 :alpha 1 :beta 3)
            :series-label "(1,3)")
(add-series beta-box-plot (sample-beta 1000 :alpha 2 :beta 2)
            :series-label "(2,2)")
(add-series beta-box-plot (sample-beta 1000 :alpha 2 :beta 5)
            :series-label "(2,5)")


;; make a histogram of a sample of 1000 beta deviates
(plot (histogram (sample-beta 1000 :alpha 5 :beta 1) 
        :title "Beta Histogram (alpha,beta)"
        :series-label "(5,1)"))



;; plot the quantiles of the Beta distribution
(def p (range 0.01 1 0.01))
(def quantile-beta-plot (xyplot p (quantile-beta p :alpha 1 :beta 1) 
                           :title "Gamma Inverse (alpha,beta)" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(1,1)"))
(plot quantile-beta-plot)
(add-series quantile-beta-plot p (quantile-beta p :alpha 5 :beta 1) 
            :series-label "(5,1)")
(add-series quantile-beta-plot p (quantile-beta p :alpha 1 :beta 3)
            :series-label "(1,3)")
(add-series quantile-beta-plot p (quantile-beta p :alpha 2 :beta 2)
            :series-label "(2,2)")
(add-series quantile-beta-plot p (quantile-beta p :alpha 2 :beta 5)
            :series-label "(5,5)")




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; CHI SQUARE DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Chi Square Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Chi_square_distribution)
(def x (range 0.1 8 0.01))
(def chisq-plot (xyplot x (pdf-chisq x :df 1) 
                        :title "Chi Square PDF (df)"
                        :x-label "Probability" 
                        :y-label "X"
                        :series-label "(1)"))
(plot chisq-plot)
(add-series chisq-plot x (pdf-chisq x :df 2)
            :series-label "(2)")
(add-series chisq-plot x (pdf-chisq x :df 3)
            :series-label "(3)")
(add-series chisq-plot x (pdf-chisq x :df 4)
            :series-label "(4)")
(add-series chisq-plot x (pdf-chisq x :df 5)
            :series-label "(5)")



(def chisq-cdf-plot (xyplot x (cdf-chisq x :df 1) 
                            :title "Chi Square CDF (df)"
                            :x-label "Probability" 
                            :y-label "X"
                            :series-label "(1)"))
(plot chisq-cdf-plot)
(add-series chisq-cdf-plot x (cdf-chisq x :df 2)
            :series-label "(2)")
(add-series chisq-cdf-plot x (cdf-chisq x :df 3)
            :series-label "(3)")
(add-series chisq-cdf-plot x (cdf-chisq x :df 4)
            :series-label "(4)")
(add-series chisq-cdf-plot x (cdf-chisq x :df 5)
            :series-label "(5)")



;; make boxplots for each of the Chi Square distributions
(def chisq-box-plot (boxplot (sample-chisq 1000 :df 1) 
                           :title "Chi Square Boxplot (df)"
                           :series-label "(1)"))
(plot chisq-box-plot)
(add-series chisq-box-plot (sample-chisq 1000 :df 2)
            :series-label "(2)")
(add-series chisq-box-plot (sample-chisq 1000 :df 3)
            :series-label "(3)")
(add-series chisq-box-plot (sample-chisq 1000 :df 4)
            :series-label "(4)")
(add-series chisq-box-plot (sample-chisq 1000 :df 5)
            :series-label "(5)")


;; make a histogram of a sample of 1000 Chi Square deviates
(plot (histogram (sample-chisq 1000 :df 1) 
        :title "Chi Square Histogram (df)"
        :series-label "(1)"))




;; plot the inverse of the Chi square distribution
(def p (range 0.01 1 0.01))
(def quantile-chisq-plot (xyplot p (quantile-chisq p :df 1) 
                           :title "Chi Square Quantiles (df)" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(df=1)"))
(plot quantile-chisq-plot)
(add-series quantile-chisq-plot p (quantile-chisq p :df 2) 
            :series-label "(df=2)")
(add-series quantile-chisq-plot p (quantile-chisq p :df 3)
            :series-label "(df=2)")
(add-series quantile-chisq-plot p (quantile-chisq p :df 4)
            :series-label "(df=4)")
(add-series quantile-chisq-plot p (quantile-chisq p :df 5)
            :series-label "(df=5)")





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; STUDENT'S T DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Student's T-Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Student-t_distribution)
(def x (range -5 5 0.01))
(def t-plot (xyplot x (pdf-t x :df 1) :title "Student's T PDF (df)"))
(plot t-plot)
(add-series t-plot x (pdf-t x :df 2))
(add-series t-plot x (pdf-t x :df 5))
(add-series t-plot x (pdf-t x :df 10))
(add-series t-plot x (pdf-t x :df 1000))

(def t-cdf-plot (xyplot x (cdf-t x :df 1) :title "Student's T CDF (df)"))
(plot t-cdf-plot)
(add-series t-cdf-plot x (cdf-t x :df 2))
(add-series t-cdf-plot x (cdf-t x :df 5))
(add-series t-cdf-plot x (cdf-t x :df 10))
(add-series t-cdf-plot x (cdf-t x :df 1000))


;; make boxplots for each of the Student's t distributions
(def t-box-plot (boxplot (sample-t 1000 :df 1) 
                           :title "Student's t Boxplot (df)"
                           :series-label "(1)"))
(plot t-box-plot)
(add-series t-box-plot (sample-t 1000 :df 2)
            :series-label "(2)")
(add-series t-box-plot (sample-t 1000 :df 5)
            :series-label "(5)")
(add-series t-box-plot (sample-t 1000 :df 10)
            :series-label "(10)")
(add-series t-box-plot (sample-t 1000 :df 1000)
            :series-label "(1000)")


;; make a histogram of a sample of 1000 Chi Square deviates
(plot (histogram (sample-t 1000 :df 10) 
        :title "Student's t Histogram (df)"
        :series-label "(1)"))


;; plot the quantiles of the Student's t distribution
(def p (range 0.05 1 0.01))
(def quantile-t-plot (xyplot p (quantile-t p :df 1) 
                           :title "Student's t Quantiles (df)" 
                           :x-label "Probability" 
                           :y-label "X"
                           :series-label "(df=1)"))
(plot quantile-t-plot)
(add-series quantile-t-plot p (quantile-t p :df 2) 
            :series-label "(df=2)")
(add-series quantile-t-plot p (quantile-t p :df 5)
            :series-label "(df=5)")
(add-series quantile-t-plot p (quantile-t p :df 10)
            :series-label "(df=10)")
(add-series quantile-t-plot p (quantile-t p :df 50)
            :series-label "(df=1000)")



          
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EXPONENTIAL DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Exponential Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Exponential_distribution)
(def x (range 0 5 0.01))
(def exp-plot (xyplot x (pdf-exp x :rate 1/2) 
                      :title "Exponential PDF (rate)"
                      :x-label "X" 
                      :y-label "Probability"
                      :series-label "(0.5)"))
(plot exp-plot)
(add-series exp-plot x (pdf-exp x :rate 1)
            :series-label "(1)")
(add-series exp-plot x (pdf-exp x :rate 1.5)
            :series-label "(1.5)")


(def exp-cdf-plot (xyplot x (cdf-exp x :rate 1/2) 
                          :title "Exponential CDF (rate)"
                          :x-label "X" 
                          :y-label "Probability"
                          :series-label "(0.5)"))
(plot exp-cdf-plot)
(add-series exp-cdf-plot x (cdf-exp x :rate 1)
            :series-label "(1)")
(add-series exp-cdf-plot x (cdf-exp x :rate 1.5)
            :series-label "(1.5)")


;; make boxplots for each of the Exponentials distributions
(def exp-box-plot (boxplot (sample-exp 1000 :rate 1/2) 
                           :title "Exponential Boxplot (rate)"
                           :series-label "(0.5)"))
(plot exp-box-plot)
(add-series exp-box-plot (sample-exp 1000 :rate 1)
            :series-label "(1)")
(add-series exp-box-plot (sample-exp 1000 :rate 1.5)
            :series-label "(1.5)")


;; make a histogram of a sample of 1000 Exponential deviates
(plot (histogram (sample-exp 1000 :rate 1.5) 
        :title "Exponential Histogram (rate)"
        :series-label "(1.5)"
        :nbins 20))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIFORM DISTRIBUTION FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;; Examples of plots from the Continuous Uniform Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Uniform_distribution)
(def x (range 1 10.01 0.01))
(def uniform-plot (xyplot x (pdf-uniform x :min 1 :max 10) :title "Uniform PDF (min,max)"))
(plot uniform-plot)

(def uniform-cdf-plot (xyplot x (cdf-uniform x :min 1 :max 10) :title "Uniform CDF (min,max)"))
(plot uniform-cdf-plot)


;; make a histogram of a sample of 1000 standard normal deviates
(plot (histogram (sample-uniform 1000) 
        :title "Uniform Histogram (min,max)"
        :series-label "(0,1)"))
(plot (histogram (sample-uniform 1000 :min 0 :max 100 :integers true) 
        :title "Uniform Histogram (min,max)"
        :series-label "(0,100,ints)"))



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
(def binomial-plot (scatter x1 (pdf-binomial x1 :prob 1/2 :size 20) :title "Binomial PDF (p,n)"))
(plot binomial-plot)
(add-series binomial-plot x1 (pdf-binomial x1 :prob 0.7 :size 20))
(add-series binomial-plot x2 (pdf-binomial x2 :prob 1/2 :size 40))

(def binomial-cdf-plot (scatter x1 (cdf-binomial x1 :prob 1/2 :size 20) :title "Binomial CDF (p,n)"))
(plot binomial-cdf-plot)
(add-series binomial-cdf-plot x1 (cdf-binomial x1 :prob 0.7 :size 20))
(add-series binomial-cdf-plot x2 (cdf-binomial x2 :prob 1/2 :size 40))



;; make boxplots for each of the Binomial distributions
(def binomial-box-plot (boxplot (sample-binomial 1000 :prob 1/2 :size 20) 
                           :title "Binomial Boxplot (p,n)"
                           :series-label "(0.5,20)"))
(plot binomial-box-plot)
(add-series binomial-box-plot (sample-binomial 1000 :prob 0.7 :size 20)
            :series-label "(0.7,20)")
(add-series binomial-box-plot (sample-binomial 1000 :prob 1/2 :size 40)
            :series-label "(0.5,40)")


;; make a histogram of a sample of 1000 Exponential deviates
(plot (histogram (sample-binomial 1000 :prob 1/2 :size 20)
        :title "Binomial Histogram (p,n)"
        :series-label "(0.5,20)"
        :nbins 10))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; NEGATIVE BINOMIAL DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Negative Binomial Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Negative_binomial_distribution)
(def x1 (range 0 100))
(def x2 (range 0 50))
(def x3 (range 0 100))
(def neg-binomial-plot (scatter x2 (pdf-neg-binomial x2 :prob 1/10 :size 50) :title "Negative Binomial PDF (prob,size)"))
(plot neg-binomial-plot)
(add-series neg-binomial-plot x2 (pdf-neg-binomial x2 :prob 1/8 :size 75))
(add-series neg-binomial-plot x3 (pdf-neg-binomial x3 :prob 1/4 :size 150))


(def neg-binomial-cdf-plot (scatter x1 (cdf-neg-binomial x1 :prob 1/2 :size 25) :title "Negative Binomial CDF (prob,size)"))
(plot neg-binomial-cdf-plot)
(add-series neg-binomial-cdf-plot x1 (cdf-neg-binomial x1 :prob 2/3 :size 25))
(add-series neg-binomial-cdf-plot x1 (cdf-neg-binomial x1 :prob 3/4 :size 25))

;; make boxplots for each of the Negative Binomial distributions
(def neg-binomial-box-plot (boxplot (sample-neg-binomial 1000 :prob 1/2 :size 20) 
                           :title "Negative Binomial Boxplot (p,n)"
                           :series-label "(0.5,20)"))
(plot neg-binomial-box-plot)
(add-series neg-binomial-box-plot (sample-neg-binomial 1000 :prob 0.7 :size 20)
            :series-label "(0.7,20)")
(add-series neg-binomial-box-plot (sample-neg-binomial 1000 :prob 1/2 :size 40)
            :series-label "(0.5,40)")


;; make a histogram of a sample of 1000 Exponential deviates
(plot (histogram (sample-neg-binomial 1000 :prob 1/4 :size 500)
        :title "Negative Binomial Histogram (p,n)"
        :series-label "(0.5,20)"
        :nbins 10))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; POISSON DISTRIBUTION
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Examples of plots from the Poisson Distribution page at
;; Wikipedia (http://en.wikipedia.org/wiki/Poisson_distribution)
(def x1 (range 0 20))
(def poisson-plot (scatter x1 (pdf-poisson x1 :lambda 1) :title "Poisson PDF (lambda)"))
(plot poisson-plot)
(add-series poisson-plot x1 (pdf-poisson x1 :lambda 4))
(add-series poisson-plot x1 (pdf-poisson x1 :lambda 10))


(def poisson-cdf-plot (scatter x1 (cdf-poisson x1 :lambda 1) :title "Poisson CDF (lambda)"))
(plot poisson-cdf-plot)
(add-series poisson-cdf-plot x1 (cdf-poisson x1 :lambda 4))
(add-series poisson-cdf-plot x1 (cdf-poisson x1 :lambda 10))


;; make boxplots for each of the Poisson distributions
(def poisson-box-plot (boxplot (sample-poisson 1000 :lambda 1) 
                           :title "Poisson Boxplot (lambda)"
                           :series-label "(1)"))
(plot poisson-box-plot)
(add-series poisson-box-plot (sample-poisson 1000 :lambda 4)
            :series-label "(4)")
(add-series poisson-box-plot (sample-poisson 1000 :lambda 10)
            :series-label "(10)")


;; make a histogram of a sample of 1000 Exponential deviates
(plot (histogram (sample-poisson 1000 :lambda 10)
        :title "Poisson Histogram (lambda)"
        :series-label "(10)"
        :nbins 10))




