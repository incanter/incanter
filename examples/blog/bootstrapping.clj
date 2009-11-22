;; from the following blog post
;; http://incanter-blog.org/2009/07/04/bootstrapping/

;; example with smoothing
;; Newcomb's speed of light data

(use '(incanter core stats charts))


;; A numeric vector giving the Third Series of measurements of the
;; passage time of light recorded by Newcomb in 1882. The given
;; values divided by 1000 plus 24 give the time in millionths of a
;; second for light to traverse a known distance. The 'true' value is
;; now considered to be 33.02.

(def x [28 -44  29  30  24  28  37  32  36  
        27  26  28  29 26  27  22  23  20  
        25 25  36  23  31  32  24  27 33  
        16  24  29  36  21  28  26  27  27  
        32  25 28 24  40  21  31  32  28  
        26  30  27  26  24  32  29 34  -2  
        25  19  36 29  30  22  28  33  39  
        25  16  23])

;; view histogram of data to see outlier observations
(view (histogram x 
                 :nbins 20
                 :title "Newcomb's speed of light data"))



;; calculate the median of the data
(median x) ;; 27

;; define a function that converts Newcomb's data into speeds
(defn to-speed 
  "Converts Newcomb's data into speed (meters/sec)"
  ([x] (div 7400 (div (plus 24800 x) 1E9))))


;; convert the data to speeds and calculate the median
(median (to-speed x)) ;; 2.981E8



;; Draw 10000 bootstrap samples of the median
(def t* (bootstrap x median :size 10000))

;; view a histogram of the bootstrap medians
(view (histogram t* 
                 :density true 
                 :nbins 20
                 :title "Bootstrap sample of medians"
                 :x-label "median"))

;; Calculate the estimate of the median: 27.301
(mean t*)

;; Convert bootstrap median estimate to speed: 2.981E8
(to-speed (mean t*))

;; Calculate a 95% CI for the median: (26.0 28.5)
(quantile t* :probs [0.025 0.975])

;; Convert to speed and calculate 95% CI: (2.9804E8 2.9807E8)
(quantile (to-speed t*) :probs [0.025 0.975])

;; estimate the standard error of the median: 0.681
(sd t*)

;; estimate the bias of the sample median: -0.3
(- (mean t*) (median x))


;; draw 10000 smoothed bootstrap samples of the median
(def smooth-t* (bootstrap x median :size 10000 :smooth true))

(view (histogram smooth-t* 
                 :density true 
                 :nbins 20
                 :title "Smoothed bootstrap sample of medians"
                 :x-label "median"))(mean smooth-samp)

;; Calculate the estimate of the median
(mean smooth-t*)

;; Calculate a 95% CI: (25.905 28.446)
(quantile smooth-t* :probs [0.025 0.975])

