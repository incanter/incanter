(ns incanter.interpolation)

(defn out-of-range [x points]
  (throw (IllegalArgumentException.
          (format "x = %s is out of range [%s, %s]"
                  x
                  (apply min (map first points))
                  (apply max (map first points))))))

(defn interpolate-linear [points]
  (let [pairs (partition 2 1 points)]
    (fn [x]
      (if-let [interv (->> pairs
                           (filter (fn [[[xl] [xr]]] (<= xl x xr)))
                           first)]
        (let [[[xl yl] [xr yr]] interv
              coef (/ (- x xl) (- xr xl))
              calc (fn [yl yr] (+ (* (- 1 coef) yl)
                                  (* coef yr)))]
          (if (coll? yl)
            (map calc yl yr)
            (calc yl yr)))
        (out-of-range x points)))))

(defn interpolate [xs ys]
  (->> (map vector xs ys)
       (sort-by first)
       interpolate-linear))



#_(

   ((interpolate [0 2 1] [[0 1] [2 3] [4 5]]) 1.5)

   (require '[incanter.core :as core])
   (require '[incanter.charts :as charts])

   (let [n 10
         xs (repeatedly n #(rand-int 10))
         ys (repeatedly n #(rand-int 10))
         min-x (apply min xs)
         max-x (apply max xs)
         f (interpolate xs ys)]
     (core/view (charts/function-plot f min-x max-x)))


   )