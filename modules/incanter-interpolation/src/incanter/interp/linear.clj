(ns incanter.interp.linear
  (:use [incanter.interp.utils :only (out-of-range)]
        [incanter.core :only (plus minus mult div)]))


(defn interpolate-linear [points]
  (let [pairs (partition 2 1 points)]
    (fn [x]
      (if-let [interv (->> pairs
                           (filter (fn [[[xl] [xr]]] (<= xl x xr)))
                           first)]
        (let [[[xl yl] [xr yr]] interv
              coef (/ (- x xl) (- xr xl))]
          (plus (mult (- 1 coef) yl)
                (mult coef yr)))
        (out-of-range x points)))))