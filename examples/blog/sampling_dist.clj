
Introduction to statistics with Clojure: Sampling distributions and the central limit theorem


The discipline of statistics is concerned with making decisions and reasoning with limited data. Data consists of observations and variables, and is limited because you only have a subset, or sample, of all possible observations. In some cases, you may have all the currently available observations, but you want to make predictions about future observations. A variable, or random variable, is a collection of numeric values, representing a measurement or a category.

A statistic is a mathematical function applied to a sample of data. For instance, the mean, variance, standard deviation are each a statistic. Also the t-statistic, the chi-square (a.k.a. X square statistic) are statistics. 

The data has a distribution, in the case of our population, the distribution is uniform. But if you repeatedly sample from the population, and then calculate a statistic from each sample, these statistics will follow a normal distribution, despite the distribution of the original data. This is what the central limit theorem states, and what I will show below.


(use '(incanter core stats charts))

;; Start by creating a population of 10,000 values taken from a uniform distribution, ranging in value from zero to one. 
(def population (sample-uniform 10000))

;; and view a histogram of the population.
(view (histogram population))

;; Not surprisingly, the histogram shows that each value is equally probable, in other words, there is no central tendency of the distribution unlike a normal distribution where the values tend toward a central value.

;; the mean of a uniform distribution is just the average of the maximum and minimum values of the range.
;; $latex \large \frac{a+b}{2} $

;; Here's a function that calculates the mean of a uniform distribution given its max and min values.
(defn uniform-mean [min-val max-val]
  (/ (+ min-val max-val) 2))

;; Calling the function with the range of our population (0 and 1), produces the expected result of 1/2.
(uniform-mean 0 1)

;; We can use Incanter's mean function to confirm the population has a value near the theoretically expected one.
(def pop-mean (mean population))
pop-mean

;; It returns a value of 0.499, you simulated population may have a slightly different mean.

;; the variance of a uniform distribution is:
;; $latex \large \frac{(b-a)^2}{12} $

;; A clojure function to calculate the variance:
(defn uniform-variance [min-val max-val]
  (/ (sq (- max-val min-val)) 12))

;; The standard deviation is just the square root of the variance,
(sqrt (uniform-variance 0 1))

;; is 0.289. Now compare the observed standard deviation of the population with the theoretically expected one.
(def pop-sd (sd population))
pop-sd


;; This value is a bit different than you might expect, and this is because Incanter's variance functions calculate the sample variance, and not the population variance. So, let's create a function to calculate the population variance.
(defn pop-variance [x]
  (/ (sum (sq (minus x (mean x))))
     (count x)))

(sqrt (pop-variance population))

;; This returns 0.290, as expected by the theoretical expectation. This brings up the question, why are there two versions of the variance formula, one for populations and one for samples? 

;; Sampling Distributions

;; Unfortunately, in most situations, we don't have access to all the data in a population, and we have to work with just a sample from a population; a population we frequently don't even know the size of. By calculating values like the mean, variance, and standard deviation on a sample, instead of the whole population, error is introduced. 
;; We can demonstrate this error by creating 1000 samples from our population, and then comparing the means and standard deviations calculated from each sample to those from the population.

;; Start by creating a matrix, where each of the 1000 rows represent a sample of 35 values from the population.
;;
;(def samples (matrix (for [_ (range 1000)] (sample population :size 35))))
;(def samples (matrix (take 1000 (repeatedly #(sample population :size 35)))))
(def samples (take 1000 (repeatedly #(sample population :size 35))))

(def samp-means (map mean samples))
;; you'd expect the majority of the above sample means to be near the population mean
;; although you might expect some to not be very close. This is essentially what the 
;; central limit theorem states.

(def samp-sds (map sd samples))

(def mean-of-samp-means (mean samp-means))
mean-of-samp-means
(def mean-of-samp-sds (mean samp-sds))
mean-of-samp-sds

(view (histogram samp-means))
(view (histogram samp-sds))

(defn std-error [s n]
  (/ s (sqrt n)))

(def std-errors (map #(std-error % 35) samp-sds))
(mean std-errors) ;; 0.048

(view (histogram std-errors))

(sd samp-means) ;; 0.047


(view (qq-plot population))

(view (qq-plot samp-means))
(view (qq-plot samp-sds)) 


