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

(defn- interpolate-parametric [method points]
  (let [point-groups (->> points
                          (map (fn [[t value]]
                                 (map #(vector t %) value)))
                          (apply map vector))
        interpolators (map method point-groups)]
    (fn [t]
      (map #(% t) interpolators))))

(defn interpolate
"  Returns a function that interpolates given collection of points.
   http://en.wikipedia.org/wiki/Interpolation

   Arguments:
     points -- collection of points. Each point is a vector where first element - x, second element f(x). Note that f(x) can be number or vector of numbers.
     type -- type of interpolation - :linear, :polynomial or :cubic-spline. For most cases you should use :cubic-spline - it usually gives best result. Check http://en.wikipedia.org/wiki/Interpolation for brief explanation of each kind.

   Options:
     :boundaries - valid only for :cubic-spline interpolation. Defines boundary condition for cubic spline. Possible values - :natural and :closed.
                   Support that our spline is function S. leftmost point is a, rightmost - b.
                   :natural - S''(a) = S''(b) = 0
                   :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be useful if you want to get periodic or closed curve.
                   Default value is :natural

   Examples:

   (def points [[0 0] [1 5] [2 0] [3 5]])
   (def linear (interpolate points :linear))
   (linear 0) => 0.0
   (linear 1) => 5.0
   (linear 1.5) => 2.5

   ; Example of parametric interpolation when y component has several components
   (def points [[0 [0 0]]
                [1 [0 1]]
                [2 [1 1]]
                [3 [1 0]]
                [4 [0 0]]])
   (def cubic (interpolate points :cubic-spline))
   (cubic 0) => [0.0 0.0]
   (cubic 1) => [1.0 1.0]
   (cubic 0.5) => [-0.12053571428571436 0.5669642857142858]

   ; Specify boundary conditions
   (interpolate points :cubic-spline :boundaries :closed)
"
  [points type & options]
  (let [method (case type
                 :linear linear/interpolate
                 :polynomial polynomial/interpolate
                 :cubic-spline cubic-spline/interpolate)
        points (sort-by first points)
        opts (when options (apply assoc {} options))]
    (validate-unique (map first points))
    (if (number? (second (first points)))
      (method points opts)
      (interpolate-parametric #(method % opts) points))))

(defn- approximate-parametric [method points]
  (let [point-groups (apply map vector points)
        interpolators (map method point-groups)]
    (fn [t]
      (map #(% t) interpolators))))

(defn approximate
 "  Approximates given collection of points using B-spline. Returns parametric function f that takes values from 0 to 1. f(0) will give you first point, f(1) - last point.

    Arguments:
      points -- collection of points. Each point either a number or collection of numbers.

    Options:
      :degree -- degree of a B-spline. Default 3.

    Note that number of points must be greater or equals than degree + 1 otherwise degree will be reduced to biggest valid. Example: (approximate [1 2]) will return B-spline of 1-degree instead of 3-degree because number of points is 2. Minimal number of points for B-spline of 3-degree is 4.


    Examples:

    (def points [0 1 5 5 0])
    (def approximator (approximate points))
    (approximator 0) => 0.0
    (approximator 1) => 0.0
    (approximator 0.5) => 0.5

    ; Set degree manually
    (def approximator (approximate points :degree 2))
"
  [points & options]
  (let [opts (when options (apply assoc {} options))
        degree (min (:degree opts 3)
                    (dec (count points)))]
    (if (number? (first points))
      (b-spline/b-spline points degree)
      (approximate-parametric #(b-spline/b-spline % degree) points))))

(defn- interpolate-grid* [grid type {:keys [x-range y-range xs ys] :as options}]
  (if-not (or (nil? xs) (nil? ys))
    (let [method (case type
                   :bilinear linear/interpolate-grid
                   :polynomial polynomial/interpolate-grid
                   :bicubic-spline cubic-spline/interpolate-grid)
          grid (mapv vec grid)
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
                    :xs (uniform x-range m)
                    :ys (uniform y-range n))]
      (interpolate-grid* grid type options))))


(defn interpolate-grid
  "Interpolates 2-dimensional grid. Returns function f that takes 2 arguments: x and y. By default function interpolates on [0,1]x[0,1].

   Arguments:
     grid -- collection of collection of numbers to be interpolated. If you need to interpolate vectors - interpolate each component by separate interpolator.
     type -- type of interpolation. Available: :bilinear, :polynomial, :bicubic-spline

   Options:
     :x-range, :y-range - range of possible x and y. By default :x-range = [0 1] and :y-range = [0 1]
     :xs, :ys - coordinates of grid points. Size of xs and ys must be consistent with grid size. If you have grid 4x3 then xs must have size 3 and ys - 4.
     Note that :x-range, :y-range and :xs, :ys both doing same job - they specify coordinates of points in grid. So you should use only 1 of them or none at all.

   Options:
     :boundaries - valid only for :cubic-spline interpolation. Defines boundary condition for bicubic spline. Possible values - :natural and :closed. Default - :natural. Check documentation of 'interpolate' method for more explanation.


   Examples:

   (def grid [[0 1 0]
              [1 2 1]
              [0 1 0]])
   (def interpolator (interpolate-grid grid :bilinear))
   (interpolator   0   0) => 0
   (interpolator 1/2 1/2) => 2
   (interpolator 1/2   1) => 1
   (interpolator 1/4   0) => 1/2


   ; Specify x-range and y-range
   (def interpolator (interpolate-grid grid :bilinear :x-range [0 10] :y-range [-5 5]))
   (interpolator  0  -5) => 0
   (interpolator  5   0) => 2
   (interpolator 10   5) => 0

   ; Specify xs and ys
   (def interpolator (interpolate-grid grid :bilinear :xs [0 1 2] :ys [0 10 100]))
   (interpolator  0   0) => 0
   (interpolator  1  10) => 2
   (interpolator  2 100) => 0
"
  [grid type & options]
  (let [opts (when options (apply assoc {} options))]
    (interpolate-grid* grid type opts)))

(defn approximate-grid
 "  Approximates 2-dimensional grid using B-splines and tensor product. Returns function f that takes 2 arguments: x and y. both x and y must be in range [0, 1].

    Arguments:
      grid -- collection of collection of numbers to be approximated. If you need to approximate vectors - approximate each component by separate approximator.

    Options:
      :degree -- degree of a B-spline. Default 3.

    Note that grid sizes must be greater or equals than degree + 1 otherwise degree will be reduced to biggest valid. Example: (approximate-surface [[1 2] [3 4]]) will return surface built using B-splines of 1-degree instead of 3-degree because grid size is 2x2. Minimal grid size for B-spline of 3-degree is 4x4.

    Example:

    (def grid [[0 1 2] [3 4 5]])

    ; Approximate grid using B-spline of 1 degree. It is equilavent to interpolating grid with :bilinear method.
    (def approximator (approximate-grid grid :degree 1))

    (approximator 0 0) => 0.0
    (approximator 0 1) => 3.0
    (approximator 1 0) => 2.0
    (approximator 0 1) => 5.0
    (approximator 0 0.5) => 1.5
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
           degree 3
           [f & fns] (map #(approximate % :degree 3) vectors)
           plot (charts/function-plot f 0 1)]
       (doto (reduce #(charts/add-function %1 %2 0 1) plot fns)
         (core/view))))

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (let [n 10
           xs (repeatedly n #(rand-int 20))
           ys (repeatedly n #(rand-int 20))
           ts (range n)
           points (map (fn [t x y] [t [x y]]) ts xs ys )
           plot (charts/parametric-plot (interpolate points :cubic-spline :boundaries :closed) 0 (dec n))]
       (doto plot
         (charts/add-points xs ys)
         (core/view))))

   ((approximate [[0 0] [0 0] [0 0] [1 1]]) 0.9)
   )

#_(; Benchamarking
   do

    (do

      (require '[criterium.core :refer [warmup-for-jit]])
      (use 'incanter.logger)


      (def ^{:dynamic true} *n* 20)
      (def ^{:dynamic true} *creation-times* 50)
      (def ^{:dynamic true} *call-times* 1000)
      (def ^{:dynamic true} *name* "default")
      (def ^{:dynamic true} *warmup-period* (* 5 200000000))

      (defmacro bench [& body]
        `(do
           (reset-logger)
           ~@body
           (print-time-stats)))

      (defn bench-interpolator-creation
        ([interp-fn-fn y-fn]
           (let [xs (range *n*)
                 ys (map y-fn (range *n*))
                 points (map vector xs ys)]
             (dotimes [i *creation-times*]
               (interp-fn-fn points))
             (dotimes [i *creation-times*]
               (log-time *name* (interp-fn-fn points))))))

      (defn bench-interpolator-calls [interp-fn-fn y-fn dl]
        (let [xs (range *n*)
              ys (map y-fn (range *n*))
              points (map vector xs ys)
              interp-fn (interp-fn-fn points)
              queries (->> (range *call-times*)
                           (map #(/ % (dec *call-times*)))
                           (map #(* % *n*)))]
          (doseq [x (apply concat (repeat 3 queries))]
            (interp-fn x))
          (doseq [x queries]
            (log-time *name* (dl (interp-fn x))))))

      (defn grid []
        (for [i (range *n*)]
          (for [j (range *n*)]
            (+ (* 2 i) j))))

      (defn bench-interpolator-grid-creation [interp-fn-fn]
        (let [grid (grid)]
          (dotimes [_ *creation-times*]
            (interp-fn-fn grid))
          (dotimes [_ *creation-times*]
            (log-time *name* (interp-fn-fn grid)))))

      (defn bench-interpolator-grid-calls [interp-fn-fn]
        (let [grid (grid)
              interp-fn (interp-fn-fn grid)
              call-times (Math/sqrt *call-times*)
              xs (map #(/ % (dec call-times)) (range call-times))]
          (doseq [x xs
                  y xs]
            (interp-fn x y)
          (doseq [x xs
                  y xs]
            (log-time *name* (interp-fn x y))))))

      (defn bench-interpolator [interp-fn interp-grid-fn name]
        (binding [*name* (str name " 1d create single")]
          (bench-interpolator-creation interp-fn (fn [i] (* 2 i))))
        #_(binding [*name* (str name " 1d create vector")]
          (bench-interpolator-creation interp-fn (fn [i] [i (* 2 i) (- i)])))
        #_(binding [*name* (str name " 1d calls vector")]
            (bench-interpolator-calls interp-fn (fn [i] [i (* 2 i) (- i)]) doall))
        #_(binding [*name* (str name " 1d call single")]
          (bench-interpolator-calls interp-fn (fn [i] (* 2 i)) identity))
        #_(binding [*name* (str name " 2d create")]
          (bench-interpolator-grid-creation interp-grid-fn))
        #_(binding [*name* (str name " 2d calls")]
          (bench-interpolator-grid-calls interp-grid-fn)))

      (defn test-all []
        (println \newline \newline "Linear")
        (bench (bench-interpolator #(interpolate % :linear) #(interpolate-grid % :bilinear) ""))

        (println \newline \newline "Cubic")
        (bench (bench-interpolator #(interpolate % :cubic-spline) #(interpolate-grid % :bicubic-spline) ""))

        (println \newline \newline "Polynomial")
        (bench (bench-interpolator #(interpolate % :polynomial) #(interpolate-grid % :polynomial) ""))

        (println \newline \newline "B-spline")
        (bench (bench-interpolator #(approximate (map second %)) approximate-grid ""))))

      (test-all)

    (bench (bench-interpolator #(interpolate % :linear) #(interpolate-grid % :bilinear) ""))
    (bench (bench-interpolator #(interpolate % :polynomial) #(interpolate-grid % :bilinear) ""))
    (bench (bench-interpolator #(interpolate % :cubic-spline) #(interpolate-grid % :bilinear) ""))
    (bench (bench-interpolator #(approximate (map second %)) nil ""))

    )
