;; from the following blog post:
;; http://incanter-blog.org/2009/06/17/randomization-significance/

;; significance testing with randomization

(use '(incanter core stats datasets charts))
(def data (to-matrix (get-dataset :plant-growth)))

;; Break the first column of the data into groups based on 
;; treatment type (second column) using the group-by function.
(def groups (group-by data 1 :cols 0))

(t-test (first groups) :y (second groups))
;; returns t-stat :t-stat 1.1912603818487033

(t-test (first groups) :y (last groups))
;; returns t-stat -2.1340204531240676



;; calculate the means of the three groups
(map mean groups)


;; perform the t-test comparing the first two groups
(def t1 (t-test (first groups) :y (second groups)))
(def t2 (t-test (first groups) :y (last groups)))

;; view the test statistic and its p-value
(:t-stat t1) ;; returns 1.1912603818487033
(:p-value t1) ;; returns 0.25038250858754796

(:t-stat t2) ;; returns -2.134
(:p-value t2) ;; returns 0.0479


;; The above p-value is based on the assumption that the
;; distribution of t-test statistics when the null hypothesis, 
;; that the two sample means are identical, is true. Let's see
;; if that assumption appears correct for these samples.

;; Now create 1000 permuted versions of the original two groups using 
;; the sample-permutations function,
(def perm-groups1 (sample-permutations 1000 
                                       (first groups) 
                                       (second groups)))

(def perm-groups2 (sample-permutations 1000 
                                       (first groups) 
                                       (last groups)))


;; create a function, based on t-test, that takes two sequence and returns
;; just the :t-stat value
(defn t-stat [x y] (:t-stat (t-test x :y y)))

;; calculate the t-test statistics for each of the permuted versions of
;; the two samples
(def t-stats1 (map t-stat 
                  (first perm-groups1) 
                  (second perm-groups1)))

(def t-stats2 (map t-stat 
                  (first perm-groups2) 
                  (second perm-groups2)))

;; plot the t-test statistics, and overlay the pdf of a t-distribution
(doto (histogram t-stats1 :density true :nbins 20)
      (add-function #(pdf-t % :df (:df t1)) -3 3)
      view)

;; and view their mean, sd, and 95% confidence interval
(mean t-stats1)
;; returns 0.02308030751953895
(sd t-stats1)
;; returns 1.0640114204888618
(quantile t-stats1 :probs [0.025 0.975])
;; returns (-2.1164160713197497 2.002005620604495)


(doto (histogram t-stats2 :density true :nbins 20)
      (add-function #(pdf-t % :df (:df t2)) -3 3)
      view)


;; and view their mean, sd, and 95% confidence interval
(mean t-stats2)
;; returns -0.014
(sd t-stats2)
;; returns 1.054
(quantile t-stats2 :probs [0.025 0.975])
;; returns (-2.075 2.122)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; do it with raw means-differences instead of the t-test statistic

;; Define a function to calculate the difference in means between two sequences.
(defn means-diff [x y] 
  (minus (mean x) (mean y)))

;; Calculate the difference in sample means between the two groups.
(def samp-mean-diff (means-diff (first groups) 
                                (second groups))) 

;; and calculate the difference of means of the 1000 samples by mapping 
;; the means-diff function, defined earlier, over the rows returned by 
;; sample-permutations.
(def perm-means-diffs1 (map means-diff 
                            (first perm-groups) 
                            (second perm-groups)))


;; Then take the mean of this sequence, which gives the proportion of times 
;; that a value from the permuted sequences are more extreme than the original 
;; sample mean (i.e. the p-value).
(mean (indicator #(> % samp-mean-diff) 
                 perm-means-diffs1))

;; Calculate a 95% interval of the permuted differences. If the original 
;; sample means-difference is outside of this range, there is evidence 
;; that the two means are statistically significantly different.
(quantile perm-means-diffs1 :probs [0.025 0.975])

;; Plot a histogram of the perm-means-diffs using the density option, 
;; instead of the default frequency, and then add a normal pdf curve 
;; with the mean and sd of perm-means-diffs data for a visual comparison.
(doto (histogram perm-means-diffs1 :density true)
      (add-lines (range -1 1 0.01) 
                 (pdf-normal (range -1 1 0.01) 
                             :mean (mean perm-means-diffs1) 
                             :sd (sd perm-means-diffs1)))
      view)

;; now standardize the means and overlay the pdf of a t-distribution
(doto (histogram (sweep (sweep perm-means-diffs1) :stat sd :fun div) :density true)
      (add-lines (range -3 3 0.01) 
                 (pdf-t (range -3 3 0.01) 
                             :df 18))
      view)


;; The permuted data looks normal. Now, calculate the p-values for 
;; the difference in means between the control and treatment two.

(def perm-groups (sample-permutations 1000 
                                      (first groups) 
                                      (last groups)))

(def perm-means-diffs2 (map means-diff 
                            (first perm-groups) 
                            (second perm-groups)))

(def samp-mean-diff (means-diff (first groups) 
                                (last groups)))

(mean (indicator #(< % samp-mean-diff) 
                 perm-means-diffs2))

(quantile perm-means-diffs2 
          :probs [0.025 0.975])


;; Finally, calculate the p-values for the difference in means 
;; between the treatment one and treatment two.

(def perm-groups (sample-permutations 1000 
                                      (second groups) 
                                      (last groups)))

(def perm-means-diffs3 (map means-diff (first perm-groups) 
                                       (second perm-groups)))

(def samp-mean-diff (means-diff (second groups) 
                                (last groups))) 

(mean (indicator #(< % samp-mean-diff) 
                 perm-means-diffs3)) 

(quantile perm-means-diffs3 
          :probs [0.025 0.975])


;; Plot box-plots of the three perm-means-diffs sequences
(doto (box-plot perm-means-diffs1)
      (add-box-plot perm-means-diffs2)
      (add-box-plot perm-means-diffs3)
      view)

