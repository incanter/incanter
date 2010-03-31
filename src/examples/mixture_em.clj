

(use '(incanter core stats charts))

(def data [-0.39 0.12 0.94 1.67 1.76 2.44 3.72 4.28 4.92 5.53 
           0.06 0.48 1.01 1.68 1.80 3.25 4.12 4.60 5.28 6.22])


(def mu-hats (sample data :size 2 :replacement false))
;(def mu-hats [4.5 1.0])
;(def mu-hats [5 1])
(def sigma-hats [(variance data) (variance data)])
;(def sigma-hats [0.9 0.8])
(def pi-hat 0.6)


(defn next-gamma-hats [data mu-hats sigma-hats pi-hat]
  (map (fn [y_i]
         (/ (* pi-hat (pdf-normal y_i :mean (second mu-hats) :sd (sqrt (second sigma-hats))))
            (+ (* (- 1 pi-hat) (pdf-normal y_i :mean (first mu-hats) :sd (sqrt (first sigma-hats))))
               (* pi-hat (pdf-normal y_i :mean (second mu-hats) :sd (sqrt (second sigma-hats)))))))
        data))

(defn next-mu-hats [data gamma-hats]
  [(/ (sum (mult (minus 1 gamma-hats) data))
      (sum (minus 1 gamma-hats)))
   (/ (sum (mult gamma-hats data))
      (sum gamma-hats))])

(defn next-sigma-hats [data gamma-hats]
  [(/ (sum (mult (minus 1 gamma-hats) (sq (minus data (first mu-hats)))))
      (sum (minus 1 gamma-hats)))
   (/ (sum (mult gamma-hats (sq (minus data (second mu-hats)))))
      (sum gamma-hats))])

(defn next-pi-hat [gamma-hats]
  (div (sum gamma-hats) (count gamma-hats)))


(defn log-likelihood [data mu-hats sigma-hats pi-hat gamma-hats]
  (+ (sum (map (fn [y g]
                 (plus (mult (minus 1 g) (log (pdf-normal y :mean (first mu-hats) :sd (sqrt (first sigma-hats)))))
                       (mult g (log (pdf-normal y :mean (second mu-hats) :sd (sqrt (second sigma-hats)))))))
               data gamma-hats))
     (sum (map (fn [y g]
                 (plus (mult (minus 1 g) (log (- 1 pi-hat))) (mult g (log pi-hat))))
               data gamma-hats))))


(def results
  (pmap (fn [j]
   (let [mu-hats (sample data :size 2 :replacement false)]
  ;; main loop
    (loop [i 0 
           m mu-hats 
           s sigma-hats 
           p pi-hat]
      (let [g (next-gamma-hats data m s p)
            m-tmp (next-mu-hats data g)
            s-tmp (next-sigma-hats data g)
            p-tmp (next-pi-hat g)
            diff [(minus m m-tmp) (minus s s-tmp) (minus p p-tmp)]]
        ;(if (or (= [0.1 0.1 0.1] diff) (= i 20))
        (if (= i 50)
          ;[i m s p diff]
          [i m s p (log-likelihood data m s p g)]
          (do
            ;(println [i m s p (log-likelihood data m s p g)])
            ;(println diff)
            (recur (inc i) 
                   (next-mu-hats data g)
                   (next-sigma-hats data g)
                   (next-pi-hat g)))))))) 
        (range 500)))

(defn max-index 
  "Returns the index of the maximum value in the given sequence."
  ([x] 
    (let [max-x (reduce max x)
          n (length x)]
      (loop [i 0]
        (if (= (nth x i) max-x)
          i
          (recur (inc i)))))))



(nth results (max-index (map #(nth % 4) results)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Mixture model for heart disease data
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(use '(incanter core stats charts io))

;; info available at: http://www-stat.stanford.edu/~tibs/ElemStatLearn/datasets/SAheart.info

(def heart-data (to-matrix 
                  (read-dataset "http://www-stat.stanford.edu/~tibs/ElemStatLearn/datasets/SAheart.data" :header true)))

(def groups (group-by heart-data 10 :cols [9 10]))
(view (histogram (sel (first groups) :cols 0)))
(view (histogram (sel (second groups) :cols 0)))
(view (histogram (sel heart-data :cols 9)))

(def data (sel heart-data :cols 9))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def sigma-hats [(variance data) (variance data)])
;(def sigma-hats [0.9 0.8])
(def pi-hat 0.5)


(defn next-gamma-hats [data mu-hats sigma-hats pi-hat]
  (map (fn [y_i]
         (/ (* pi-hat (pdf-normal y_i :mean (second mu-hats) :sd (sqrt (second sigma-hats))))
            (+ (* (- 1 pi-hat) (pdf-normal y_i :mean (first mu-hats) :sd (sqrt (first sigma-hats))))
               (* pi-hat (pdf-normal y_i :mean (second mu-hats) :sd (sqrt (second sigma-hats)))))))
        data))

(defn next-mu-hats [data gamma-hats]
  [(/ (sum (mult (minus 1 gamma-hats) data))
      (sum (minus 1 gamma-hats)))
   (/ (sum (mult gamma-hats data))
      (sum gamma-hats))])

(defn next-sigma-hats [data mu-hats gamma-hats]
  [(/ (sum (mult (minus 1 gamma-hats) (sq (minus data (first mu-hats)))))
      (sum (minus 1 gamma-hats)))
   (/ (sum (mult gamma-hats (sq (minus data (second mu-hats)))))
      (sum gamma-hats))])

(defn next-pi-hat [gamma-hats]
  (div (sum gamma-hats) (count gamma-hats)))


(defn log-likelihood [data mu-hats sigma-hats pi-hat gamma-hats]
  (+ (sum (map (fn [y g]
                 (plus (mult (minus 1 g) (log (pdf-normal y :mean (first mu-hats) :sd (sqrt (first sigma-hats)))))
                       (mult g (log (pdf-normal y :mean (second mu-hats) :sd (sqrt (second sigma-hats)))))))
               data gamma-hats))
     (sum (map (fn [y g]
                 (plus (mult (minus 1 g) (log (- 1 pi-hat))) (mult g (log pi-hat))))
               data gamma-hats))))


(def results
  (pmap (fn [j]
   (let [mu-hats (sample data :size 2 :replacement false)]
  ;; main loop
    (loop [i 0 
           m mu-hats 
           s sigma-hats 
           p pi-hat]
      (let [g (next-gamma-hats data m s p)
            m-tmp (next-mu-hats data g)
            s-tmp (next-sigma-hats data m g)
            p-tmp (next-pi-hat g)
            diff [(minus m m-tmp) (minus s s-tmp) (minus p p-tmp)]]
        ;(if (or (= [0.1 0.1 0.1] diff) (= i 20))
        (if (= i 200)
          ;[i m s p diff]
          [i m s p (log-likelihood data m s p g)]
          (do
            ;(println [i m s p (log-likelihood data m s p g)])
            ;(println diff)
            (recur (inc i) 
                   (next-mu-hats data g)
                   (next-sigma-hats data m g)
                   (next-pi-hat g)))))) )) 
        (range 50)))


(defn max-index 
  "Returns the index of the maximum value in the given sequence."
  ([x] 
    (let [max-x (reduce max x)
          n (length x)]
      (loop [i 0]
        (if (= (nth x i) max-x)
          i
          (recur (inc i)))))))



(println (nth results (max-index (map #(nth % 4) results))))


