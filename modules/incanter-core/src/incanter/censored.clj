(ns ^{:doc "Statistical functions for work with 'censored' (truncated) distributions."}
    incanter.censored
  (:use [incanter.core :only (mult pow)]
        [incanter.stats :only (pdf-normal cdf-normal sd)]))


(defn- lambda-two-sided
  ([a-std b-std]
    (/ (- (pdf-normal a-std) (pdf-normal b-std))
       (- (cdf-normal b-std) (cdf-normal a-std)))))

(defn- lambda-lower
  ([a-std] (/ (pdf-normal a-std) (- 1 (cdf-normal a-std)))))

(defn- lambda-upper
  ([b-std]
    (/ (- (pdf-normal b-std)) (cdf-normal b-std))))

(defn- psi [y mu sigma] (/ (- y mu) sigma))


(defn censored-mean-two-sided
  "
  Returns the mean of a normal distribution (with mean mu and standard
  deviation sigma) with the lower tail censored at 'a' and the upper
  tail censored at 'b'
  "
  ([a b mu sigma]
    (let [a-std (psi a mu sigma)
          b-std (psi b mu sigma)
          cdf-a (cdf-normal a-std)
          cdf-b (cdf-normal b-std)]
      (+ (* cdf-a a)
         (* (- cdf-b cdf-a) (+ mu (* sigma (lambda-two-sided a-std b-std))))
         (* (- 1 cdf-b) b)))))


(defn censored-variance-two-sided
  "
  Returns the variance of a normal distribution (with mean mu and standard
  deviation sigma) with the lower tail censored at 'a' and the upper
  tail censored at 'b'
  "
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
  "
  Returns the mean of a normal distribution (with mean mu and standard
  deviation sigma) with the lower tail censored at 'a'
  "
  ([a mu sigma]
    (let [a-std (psi a mu sigma)]
      (+ (* (cdf-normal a-std) a)
         (* (- 1 (cdf-normal a-std))
            (+ mu (* sigma (lambda-lower a-std))))))))


(defn censored-variance-lower
  "
  Returns the variance of a normal distribution (with mean mu and standard
  deviation sigma) with the lower tail censored at 'a'
  "
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
  "
  Returns the mean of a normal distribution (with mean mu and standard
  deviation sigma) with the upper tail censored at 'b'
  "
  ([b mu sigma]
    (let [b-std (psi b mu sigma)]
      (+ (* (cdf-normal b-std) (+ mu (* sigma (lambda-upper b-std))))
         (* (- 1 (cdf-normal b-std)) b)))))


(defn censored-variance-upper
  "
  Returns the variance of a normal distribution (with mean mu and standard
  deviation sigma) with the upper tail censored at 'b'
  "
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



(defn truncated-variance
  "
  Returns the variance of a normal distribution truncated at a and b.

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
    (def plot (xy-plot x (map #(truncated-variance :a %) x)))
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
  ([& {:keys [mean sd a b]
       :or {mean 0
            sd 1
            a Double/NEGATIVE_INFINITY
            b Double/POSITIVE_INFINITY}}]
    (let [sigma-sq (* sd sd)
          lambda (fn [alpha] (/ (pdf-normal alpha) (- 1 (cdf-normal alpha))))
          delta (fn [alpha] (* (lambda alpha) (- (lambda alpha) alpha)))
          ;one-tail-var (fn [alpha s-sq]
          ;               (* s-sq
          ;                  (- 1 (cdf-normal alpha))
          ;                  (+ (- 1 (delta alpha))
          ;                     (* (pow (- alpha (lambda alpha)) 2)
          ;                        (cdf-normal alpha)))))
          a-std (if (= a Double/NEGATIVE_INFINITY) Double/NEGATIVE_INFINITY (/ (- a mean) sd))
          b-std (if (= b Double/POSITIVE_INFINITY) Double/POSITIVE_INFINITY (/ (- b mean) sd))
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
