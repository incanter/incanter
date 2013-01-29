(ns incanter.interpolation
  (:require [incanter.interp
             [cubic-spline :as cubic-spline]
             [b-spline :as b-spline]
             [polynomial :as polynomial]
             [linear :as linear]]
            [incanter.core :refer (plus minus mult div)]))

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
                 :linear linear/interpolate
                 :polynomial polynomial/interpolate
                 :cubic-spline cubic-spline/interpolate)]
    (validate-unique (map first points))
    (method (sort-by first points))))

(defn approximate
 "  Approximates given collection of points using B-spline. Returns parametric function f that takes values from 0 to 1. f(0) will give you first point, f(1) - last point.

    Arguments:
      points -- collection of points. Each point either a number of collection of numbers.

    Options:
      :degree -- degree of a B-spline. Default 3.

    Note that number of points must be greater or equals than degree + 1 otherwise degree will be reduced to biggest valid. Example: (approximate [1 2]) will return B-spline of 1-degree instead of 3-degree because number of points is 2. Minimal number of points for B-spline of 3-degree is 4.
"
  [points & options]
  (let [opts (when options (apply assoc {} options))
        degree (min (:degree opts 3)
                    (dec (count points)))]
    (b-spline/b-spline points degree)))

(defn- interpolate-grid* [grid type {:keys [x-range y-range xs ys] :as options}]
  (if-not (or (nil? xs) (nil? ys))
    (let [method (case type
                   :bilinear linear/interpolate-grid
                   :polynomial polynomial/interpolate-grid
                   :bicubic-spline cubic-spline/interpolate-grid)
          xs (vec (sort xs))
          ys (vec (sort ys))]
      (validate-unique xs)
      (validate-unique ys)
      (method grid xs ys options))
    (let [n (count grid)
          m (count (first grid))
          x-range (or x-range [0 1])
          y-range (or y-range [0 1])
          uniform (fn [[a b] n]
                    (map #(-> % (* (- b a)) (/ (dec n)) (+ a))
                         (range n)))
          options (assoc options
                    :xs (uniform x-range n)
                    :ys (uniform y-range m))]
      (interpolate-grid* grid type options))))


(defn interpolate-grid
  "Interpolates 2-dimensional grid"
  [grid type & options]
  (let [opts (when options (apply assoc {} options))]
    (interpolate-grid* grid type opts)))

(defn approximate-grid
 "  Approximates given grid using B-splines and tensor product. Returns parametric function f that takes 2 arguments: x and y. both x and y must be in range [0, 1].

    Arguments:
      grid -- collection of collection of points to be approximated. Each point either a number of collection of numbers.

    Options:
      :degree -- degree of a B-spline. Default 3.

    Note that grid sizes must be greater or equals than degree + 1 otherwise degree will be reduced to biggest valid. Example: (approximate-surface [[1 2] [3 4]]) will return surface built using B-splines of 1-degree instead of 3-degree because grid size is 2x2. Minimal grid size for B-spline of 3-degree is 4x4.
"
  [grid & options]
  (let [opts (when options (apply assoc {} options))
        degree (min (:degree opts 3)
                    (dec (count grid))
                    (dec (count (first grid))))]
    (b-spline/b-surface grid degree)))


#_((interpolate-grid [[1 2 3] [3 4 5]] :bilinear :x-range [0 10] :y-range [-5 5]) 5 -2.5)
#_((interpolate-grid [[1 2 3] [3 4 5]] :bilinear) 0 1)

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
           f (interpolate points :linear)]
       (doto (charts/function-plot f min-x max-x)
         (charts/add-points xs ys)
         (core/view))))

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (let [n 5
           zeros (vec (repeat n 0))
           vectors (map #(assoc-in zeros [%] 1) (range n))
           degree 3
           [f & fns] (map #(approximate % :degree 3) vectors)
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