(ns incanter.interpolation
  (:use [incanter.core :only (plus minus mult div)]))

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
              coef (/ (- x xl) (- xr xl))]
          (plus (mult (- 1 coef) yl)
                (mult coef yr)))
        (out-of-range x points)))))

(defn interpolate-lagrange [points]
  (let [xs (map first points)
        ys (map second points)
        divided-difference (fn [[f1 f2]]
                             {:f (div (minus (:f f2) (:f f1))
                                      (minus (:x-r f2) (:x-l f1)))
                              :x-r (:x-r f2)
                              :x-l (:x-l f1)})
        next-level-differences (fn [fs]
                                 (map divided-difference (partition 2 1 fs)))
        fs (->> (map (fn [x f] {:f f :x-l x :x-r x}) xs ys)
                (iterate next-level-differences)
                (take (count xs))
                (map first)
                (map :f))]
    (fn [x]
      (->> (reductions #(* %1 (- x %2)) 1 xs)
           (map mult fs)
           (apply plus)))))

(defn validate-unique [xs]
  (when-not (apply distinct? xs)
    (throw (IllegalArgumentException. "All x must be distinct."))))

(defn interpolate [xs ys type]
  (let [method (case type
                 :linear interpolate-linear
                 :lagrange interpolate-lagrange)]
    (validate-unique xs)
    (->> (map vector xs ys)
         (sort-by first)
         method)))



#_(

   ((interpolate [0 2 1] [[0 1] [2 3] [4 5]] :linear) 1.5)

   ((interpolate [1 2] [3 4] :lagrange) 1.5)

   

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (let [n 20
          xs (take n (distinct (repeatedly #(rand-int (* n 2)))))
          ys (repeatedly n #(rand-int (* n 2)))
          min-x (apply min xs)
          max-x (apply max xs)
          f (interpolate xs ys :lagrange)]
      (doto (charts/function-plot f min-x max-x)
        (charts/add-points xs ys)
        (core/view))))


   )