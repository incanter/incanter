(ns incanter.interp.cubic-spline
  (:use [incanter.core :only (plus minus div mult)]
        [incanter.interp.utils :only (find-segment)]))


(defn- map-pairs [fn coll]
  (map #(apply fn %) (partition 2 1 coll)))

(defn- calc-bs [hs ys]
  (letfn [(calc-b [[y_i-1 y_i y_i+1] [h_i h_i+1]]
            (mult (/ 6 (+ h_i h_i+1))
                  (minus (div (minus y_i+1 y_i) h_i+1)
                         (div (minus y_i y_i-1) h_i))))]
    (map calc-b
         (partition 3 1 ys)
         (partition 2 1 hs))))

(defn- solve-tridiagonal [as cs ds]
  ; bs equals to [2 2 2 2 2 2 ..] so we substitute in place
  (let [next-cd (fn [[c-prev d-prev] [a c d]]
                  (let [coef (- 2 (* c-prev a))]
                    [(if (nil? c) nil (/ c coef))
                     (div (minus d (mult d-prev a))
                          coef)]))
        [cs ds] (->> (map vector as (concat (rest cs) [nil]) (rest ds))
                     (reductions next-cd
                                 [(/ (first cs) 2)
                                  (div (first ds) 2)])
                     (apply map vector))
        prev-x (fn [x [c d]] (minus d (mult c x)))
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
                 [(div (first bs) 2)]
                 (solve-tridiagonal (rest cs) (drop-last es) bs))]
    (concat [0] gammas [0])))

(defn- calc-coefs [hs ys]
  (let [alphas ys
        gammas (calc-gammas hs ys)
        deltas (map div (map-pairs #(minus %2 %1) gammas) hs)
        betas (map plus
                   (map div (map-pairs #(minus %2 %1) ys) hs)
                   (map #(mult %1 (/ %2 6))
                        (map-pairs #(plus (mult 2 %2) %1) gammas)
                        hs))]
    (mapv vector
          (rest alphas)
          betas
          (map #(div % 2) (rest gammas))
          (map #(div % 6) deltas))))

(defn- calc-polynom [coefs x]
  (reduce #(plus (mult %1 x) %2) 0 (reverse coefs)))

(defn interpolate
  "Interpolates set of points using cubic splines.
   http://en.wikipedia.org/wiki/Spline_interpolation"
  [points]
  (let [xs (mapv first points)
        ys (map second points)
        hs (map-pairs #(- %2 %1) xs)
        all-coefs (calc-coefs hs ys)]
    (fn [x]
      (let [ind (find-segment xs x)
            x-i (xs (inc ind))
            coefs (all-coefs ind)]
        (calc-polynom coefs (- x x-i))))))

(defn interpolate-grid
  "Interpolates grid using bicubic splines."
  [grid xs ys options]
  (let [hs (map-pairs #(- %2 %1) ys)
        coefs (map #(calc-coefs hs %) grid)
        trans-coefs (apply map vector coefs)
        strip-points (map #(map vector xs %) trans-coefs)
        strip-interpolators (mapv interpolate strip-points)]
    (fn [x y]
      (let [ind-y (find-segment ys y)
            y-i (ys (inc ind-y))
            coefs ((strip-interpolators ind-y) x)]
        (calc-polynom coefs (- y y-i))))))


