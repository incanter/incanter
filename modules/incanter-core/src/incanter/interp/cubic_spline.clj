(ns ^{:skip-wiki true}
  incanter.interp.cubic-spline
  (:require [incanter.interp.utils :refer (find-segment)]))

(defn- map-pairs [fn coll]
  (mapv #(apply fn %) (partition 2 1 coll)))

(defn- get-hs [xs]
  (map-pairs #(- %2 %1) xs))

(defn- transpose [grid]
  (apply map vector grid))

(defn- calc-coefs [hs ys type]
  (let [alphas ys
        gammas (cond (= type :natural) (incanter.interpolation.Utils/calcNaturalGammas ys hs)
                     (= type :closed) (incanter.interpolation.Utils/calcClosedGammas ys hs)
                     :default (throw (IllegalArgumentException. (str "Unknown type " type))))
        deltas (map / (get-hs gammas) hs)
        betas (map +
                   (map / (get-hs ys) hs)
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
        hs (get-hs xs)
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
                          (transpose))
        interpolators (map #(interpolate % options) point-groups)]
    (fn [t]
      (map #(% t) interpolators))))

(defn interpolate-grid
  "Interpolates grid using bicubic splines."
  [grid xs ys options]
  (let [type (:boundaries options :natural)
        xs (mapv double xs)
        hs (get-hs xs)
        grid (map #(mapv double %) grid)
        coefs (pmap #(calc-coefs hs % type) grid)
        trans-coefs (transpose coefs)
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

(defn- differences [ys hs]
  (map #(difference ys hs %) (range (count ys))))

(defn- get-alphas
  ([[y0 y1] [yd0 yd1] h]
     (get-alphas [y0 yd0 y1 yd1] h))
  ([[y0 yd0 y1 yd1] h]
     [y0
      yd0
      (/ (- y1 y0 (* h yd0)) h h)
      (/ (+ (* 2 (- y0 y1))
            (* h (+ yd0 yd1)))
         h h h)]))

(defn- hermite-polynom [alphas [a b]]
  (let [a0 (double (nth alphas 0))
        a1 (double (nth alphas 1))
        a2 (double (nth alphas 2))
        a3 (double (nth alphas 3))
        a (double a)
        b (double b)]
    (fn [^double x]
      (+ a0 (* (- x a)
               (+ a1 (* (- x a)
                        (+ a2 (* (- x b) a3)))))))))

(defn interpolate-hermite
  "Interpolates set of points using cubic hermite splines.
http://en.wikipedia.org/wiki/Cubic_Hermite_spline"
  [points options]
  (let [xs (mapv #(first %) points)
        ys (mapv #(second %) points)
        hs (get-hs xs)
        yds (:derivatives options (differences ys hs))
        all-alphas (map get-alphas
                        (partition 2 1 ys)
                        (partition 2 1 yds)
                        hs)
        polynoms (mapv hermite-polynom all-alphas (partition 2 1 xs))]
    (fn [x]
      (let [ind (find-segment xs x)
            x-i (xs (inc ind))
            polynom (polynoms ind)]
        (polynom x)))))

(defn- add-partial-derivatives [grid xs ys]
  (let [hs-x (get-hs xs)
        hs-y (get-hs ys)
        grid-dx (map #(differences % hs-x) grid)
        grid-dy (->> (transpose grid)
                     (map #(differences % hs-y))
                     transpose)
        grid-dxdy (->> (transpose grid-dx)
                       (map #(differences % hs-y))
                       transpose)]
    (interleave (map interleave grid grid-dx)
                (map interleave grid-dy grid-dxdy))))

(defn- get-alphas-grid [grid h-x h-y]
  (->> grid
       (map #(get-alphas % h-x))
       transpose
       (map #(get-alphas % h-y))
       transpose))

(defn- hermite-rect-interpolator [square [a b] [c d]]
  (let [alphas (get-alphas-grid square (- b a) (- d c))
        [a0 a1 a2 a3] (map #(hermite-polynom % [a b]) alphas)]
    (fn [x y]
      (+ (a0 x) (* (- y c)
                   (+ (a1 x) (* (- y c)
                                (+ (a2 x) (* (- y d) (a3 x))))))))))

(defn interpolate-grid-hermite
  "Interpolates grid using bicubic hermite splines."
  [grid xs ys options]
  (let [xs (mapv double xs)
        ys (mapv double ys)
        grid (map #(mapv double %) grid)
        grid-ex (add-partial-derivatives grid xs ys)
        interpolators (mapv (fn [strip ys]
                              (mapv #(hermite-rect-interpolator %1 %2 ys)
                                    (transpose (map #(partition 4 2 %) strip))
                                    (partition 2 1 xs)))
                           (partition 4 2 grid-ex)
                           (partition 2 1 ys))]
    (fn [x y]
      (let [ind-x (find-segment xs x)
            ind-y (find-segment ys y)]
        ((get-in interpolators [ind-y ind-x]) x y)))))
