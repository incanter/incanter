(ns incanter.interp.cubic-spline
  (:require [incanter.interp.utils :refer (find-segment)]))


(defn- map-pairs [fn coll]
  (map #(apply fn %) (partition 2 1 coll)))

(defn- calc-bs [hs ys]
  (letfn [(calc-b [[y_i-1 y_i y_i+1] [h_i h_i+1]]
            (* (/ 6 (+ h_i h_i+1))
               (- (/ (- y_i+1 y_i) h_i+1)
                  (/ (- y_i y_i-1) h_i))))]
    (map calc-b
         (partition 3 1 ys)
         (partition 2 1 hs))))

(defn- solve-tridiagonal [as cs ds]
                                        ; bs equals to [2 2 2 2 2 2 ..] so we substitute in place
  (let [next-cd (fn [[c-prev d-prev] [a c d]]
                  (let [coef (- 2 (* c-prev a))]
                    [(if (nil? c) nil (/ c coef))
                     (/ (- d (* d-prev a))
                        coef)]))
        [cs ds] (->> (map vector as (concat (rest cs) [nil]) (rest ds))
                     (reductions next-cd
                                 [(/ (first cs) 2)
                                  (/ (first ds) 2)])
                     (apply map vector))
        prev-x (fn [x [c d]] (- d (* c x)))
        xs (->> (map vector cs ds)
                reverse
                rest
                (reductions prev-x (last ds))
                reverse)]
    xs))

(defn- calc-gammas [hs ys]
  (let [es (map-pairs #(/ %2 (+ %1 %2)) hs)
        cs (map-pairs #(/ %1 (+ %1 %2)) hs)
        bs (calc-bs hs ys)
        gammas (if (= 1 (count es))
                 [(/ (first bs) 2)]
                 (solve-tridiagonal (rest cs) (drop-last es) bs))]
    (concat [0] gammas [0])))

(defn- calc-coefs [hs ys]
  (let [alphas ys
        gammas (calc-gammas hs ys)
        deltas (map / (map-pairs #(- %2 %1) gammas) hs)
        betas (map +
                   (map / (map-pairs #(- %2 %1) ys) hs)
                   (map #(* %1 (/ %2 6))
                        (map-pairs #(+ (* 2 %2) %1) gammas)
                        hs))]
    (mapv vector
          (rest alphas)
          betas
          (map #(/ % 2) (rest gammas))
          (map #(/ % 6) deltas))))

(defn- calc-polynom [coefs x]
  (reduce #(+ (* %1 x) %2) 0 (reverse coefs)))

(defn- polynom
  "Takes coefficients of 3-order polynom and builds a function that calculates it in given point.
   It's ~3 times faster than calc-polynom function. "
  [coefs]
  (let [d (double (nth coefs 0))
        c (double (nth coefs 1))
        b (double (nth coefs 2))
        a (double (nth coefs 3))]
    (fn [^double x]
      (+ d (* x (+ c (* x (+ b (* a x)))))))))

(defn interpolate
  "Interpolates set of points using cubic splines.
   http://en.wikipedia.org/wiki/Spline_interpolation"
  [points]
  (let [xs (mapv first points)
        ys (map second points)
        hs (map-pairs #(- %2 %1) xs)
        all-coefs (calc-coefs hs ys)
        polynoms (mapv polynom all-coefs)]
    (fn [x]
      (let [ind (find-segment xs x)
            x-i (xs (inc ind))
            polynom (polynoms ind)]
        (polynom (- x x-i))))))

(defn- interpolate-parametric [points]
  (let [point-groups (->> points
                          (map (fn [[t value]]
                                 (map #(vector t %) value)))
                          (apply map vector))
        interpolators (map interpolate point-groups)]
    (fn [t]
      (map #(% t) interpolators))))

(defn interpolate-grid
  "Interpolates grid using bicubic splines."
  [grid xs ys options]
  (let [hs (map-pairs #(- %2 %1) xs)
        coefs (map #(calc-coefs hs %) grid)
        trans-coefs (apply map vector coefs)
        strip-points (map #(map vector ys %) trans-coefs)
        strip-interpolators (mapv interpolate-parametric strip-points)]
    (fn [x y]
      (let [ind-x (find-segment xs x)
            x-i (xs (inc ind-x))
            coefs ((strip-interpolators ind-x) y)]
        (calc-polynom coefs (- x x-i))))))

