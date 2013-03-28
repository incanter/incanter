(ns incanter.interp.cubic-spline
  (:require [incanter.interp.utils :refer (find-segment)]))

(defn- map-pairs [fn coll]
  (mapv #(apply fn %) (partition 2 1 coll)))

(defn- calc-coefs [hs ys type]
  (let [alphas ys
        gammas (cond (= type :natural) (incanter.interpolation.Utils/calcNaturalGammas ys hs)
                     (= type :closed) (incanter.interpolation.Utils/calcClosedGammas ys hs)
                     :default (throw (IllegalArgumentException. (str "Unknown type " type))))
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
  [points options]
  (let [xs (mapv #(double (first %)) points)
        ys (mapv #(double (second %)) points)
        hs (map-pairs #(- %2 %1) xs)
        type (:boundaries options :natural)
        all-coefs (calc-coefs hs ys type)
        polynoms (mapv polynom all-coefs)]
    (fn [x]
      (let [ind (find-segment xs x)
            x-i (xs (inc ind))
            polynom (polynoms ind)]
        (polynom (- x x-i))))))

(defn- interpolate-parametric [points options]
  (let [point-groups (->> points
                          (map (fn [[t value]]
                                 (map #(vector t %) value)))
                          (apply map vector))
        interpolators (map #(interpolate % options) point-groups)]
    (fn [t]
      (map #(% t) interpolators))))

(defn interpolate-grid
  "Interpolates grid using bicubic splines."
  [grid xs ys options]
  (let [type (:boundaries options :natural)
        xs (mapv double xs)
        hs (map-pairs #(- %2 %1) xs)
        grid (map #(mapv double %) grid)
        coefs (pmap #(calc-coefs hs % type) grid)
        trans-coefs (apply map vector coefs)
        strip-points (map #(map vector ys %) trans-coefs)
        strip-interpolators (vec (pmap #(interpolate-parametric % options) strip-points))]
    (fn [x y]
      (let [ind-x (find-segment xs x)
            x-i (xs (inc ind-x))
            coefs ((strip-interpolators ind-x) y)]
        (calc-polynom coefs (- x x-i))))))

(defn- difference [ys hs ind]
  (let [n (dec (count ys))]
   (cond (zero? ind) (/ (- (ys 1) (ys 0))
                        (hs 0))
         (= ind n) (/ (- (ys n) (ys (dec n)))
                      (hs (dec n)))
         :default (/ (+ (/ (- (ys (inc ind)) (ys ind))
                           (hs ind))
                        (/ (- (ys ind) (ys (dec ind)))
                           (hs (dec ind))))
                     2))))

(defn- calc-coefs-hermite [hs ys]
  (let [n (count ys)
        yds (mapv #(difference ys hs %) (range n))
        alphas ys
        betas yds
        calc-delta (fn [i]
                     (let [h (- (hs (dec i)))]
                       (+ (/ (* 6 (- (ys i) (ys (dec i))))
                             h h h)
                          (/ (* 3 (+ (yds i) (yds (dec i))))
                             h h))))
        deltas (mapv calc-delta (range 1 n))
        calc-gamma (fn [i]
                     (let [h (- (hs (dec i)))]
                       (- (/ (- (yds (dec i)) (yds i))
                             h)
                          (* h (deltas (dec i))))))
        gammas (mapv calc-gamma (range 1 n))]
    (println hs ys yds deltas gammas)
    (mapv vector
          (rest alphas)
          (rest betas)
          (map #(/ % 2) gammas)
          (map #(/ % 3) deltas))))

(defn interpolate-hermite
  "Interpolates set of points using cubic hermite splines.
http://en.wikipedia.org/wiki/Cubic_Hermite_spline"
  [points options]
  (let [xs (mapv #(double (first %)) points)
        ys (mapv #(double (second %)) points)
        hs (map-pairs #(- %2 %1) xs)
        all-coefs (calc-coefs-hermite hs ys)
        polynoms (mapv polynom all-coefs)]
    (println all-coefs)
    (fn [x]
      (let [ind (find-segment xs x)
            x-i (xs (inc ind))
            polynom (polynoms ind)]
        (polynom (- x x-i))))))
