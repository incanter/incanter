(ns incanter.interpolation
  (:use [incanter
         [core :only (plus minus mult div)]
         [cubic-spline :rename {interpolate interpolate-cubic}]
         [b-spline :only (b-spline)]] ))

(defn- out-of-range [x points]
  (throw (IllegalArgumentException.
          (format "x = %s is out of range [%s, %s]"
                  x
                  (apply min (map first points))
                  (apply max (map first points))))))

(defn- interpolate-linear [points]
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

(defn- interpolate-polynomial [points]
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

(defn- validate-unique [xs]
  (when-not (apply distinct? xs)
    (throw (IllegalArgumentException. "All x must be distinct."))))

(defn interpolate
"  Returns a function that interpolates given collection of points.
   http://en.wikipedia.org/wiki/Interpolation

   Arguments:
     points -- collection of points. Each point is a vector where first element - x, second element f(x). Note that f(x) can be number or vector of numbers.
     type -- type of interpolation - :linear, :polynomial or :cubic-spline. For most cases you should use :cubic-spline - it usually gives best result. Check http://en.wikipedia.org/wiki/Interpolation for brief explanation of each kind.
"
  [points type]
  (let [method (case type
                 :linear interpolate-linear
                 :polynomial interpolate-polynomial
                 :cubic-spline interpolate-cubic)]
    (validate-unique (map first points))
    (method (sort-by first points))))

(defn approximate
 "  Approximates given collection of points using B-spline. Returns parametric function f that takes values from 0 to 1. f(0) will give you first point, f(1) - last point.

    Arguments:
      points -- collection of points. Each point either a number of collection of numbers.

    Options:
      degree -- degree of a B-spline. Default 3.

    Note that number of points must be greater or equals than degree + 1 otherwise degree will be reduced to biggest valid. Example: (approximate [1 2]) will return B-spline of 1-degree instead of 3-degree because number of points is 2. Minimal number of points for B-spline of 3-degree is 4.
"
  [points & options]
  (let [opts (when options (apply assoc {} options))
        degree (min (:degree opts 3)
                    (dec (count points)))]
   (b-spline points degree)))

#_(

   ((interpolate [0 2 1] [[0 1] [2 3] [4 5]] :linear) 1.5)

   ((interpolate [1 2] [3 4] :lagrange) 1.5)


   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (let [n 20
           xs (take n (distinct (repeatedly #(rand-int (* n 2)))))
           ys (repeatedly n #(rand-int (* n 2)))
           points (map vector xs ys)
           min-x (apply min xs)
           max-x (apply max xs)
           f (interpolate points :cubic-spline)]
       (doto (charts/function-plot f min-x max-x)
         (charts/add-points xs ys)
         (core/view))))

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (let [n 5
           zeros (vec (repeat n 0))
           vectors (map #(assoc-in zeros [%] 1) (range n))
           [f & fns] (map #(approximate % :degree 0) vectors)
           plot (charts/function-plot f 0 1)]
       (doto (reduce #(charts/add-function %1 %2 0 1) plot fns)
         (core/view))))

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (let [n 3
           xs (repeatedly n #(rand-int 20))
           ys (repeatedly n #(rand-int 20))
           points (map vector xs ys)
           plot (charts/parametric-plot (approximate points :degree 0) 0 1)]
       (doto (reduce #(charts/add-parametric %1 (approximate points :degree %2) 0 1) plot [1 2 3])
         (charts/add-points xs ys)
         (core/view))))

   ((approximate [[0 0] [0 0] [0 0] [1 1]]) 0.9)

   )