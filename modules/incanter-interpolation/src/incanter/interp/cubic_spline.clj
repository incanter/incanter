(ns incanter.interp.cubic-spline
  (:require [incanter.interp.utils :refer (find-segment)]))

(defn- map-pairs [fn coll]
  (mapv #(apply fn %) (partition 2 1 coll)))

(defn- calc-coefs [hs ys]
  (let [alphas ys
        gammas (incanter.interpolation.Utils/calcGammas ys hs)
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
  (let [xs (mapv #(double (first %)) points)
        ys (mapv #(double (second %)) points)
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
  (let [xs (mapv double xs)
        hs (map-pairs #(- %2 %1) xs)
        grid (map #(mapv double %) grid)
        coefs (pmap #(calc-coefs hs %) grid)
        trans-coefs (apply map vector coefs)
        strip-points (map #(map vector ys %) trans-coefs)
        strip-interpolators (vec (pmap interpolate-parametric strip-points))]
    (fn [x y]
      (let [ind-x (find-segment xs x)
            x-i (xs (inc ind-x))
            coefs ((strip-interpolators ind-x) y)]
        (calc-polynom coefs (- x x-i))))))

