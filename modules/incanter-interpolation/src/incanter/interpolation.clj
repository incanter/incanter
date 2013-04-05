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


(defn- uniform
" Splits segment [a b] to n points with equals distance between them."
  [[a b] n]
  (map #(-> (* (- b a) %)
            (/ (dec n))
            (+ a))
       (range n)))


(defn interpolate
"   Builds a function that interpolates given collection of points.
   http://en.wikipedia.org/wiki/Interpolation

   Arguments:
     points -- collection of points. Each point is a collection [x y].
     type -- type of interpolation - :linear, :polynomial, :cubic-spline, :cubic-hermite-spline. For most cases you should use :cubic-spline or :cubic-hermite-spline - they usually give best results. Check http://en.wikipedia.org/wiki/Interpolation for brief explanation of each kind.

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

   ; Specify boundary conditions
   (interpolate points :cubic-spline :boundaries :closed)
"
  [points type & options]
  (let [method (case type
                 :linear linear/interpolate
                 :polynomial polynomial/interpolate
                 :cubic-spline cubic-spline/interpolate
                 :cubic-hermite-spline cubic-spline/interpolate-hermite)
        points (sort-by first points)
        opts (when options (apply assoc {} options))]
    (validate-unique (map first points))
    (method points opts)))


(defn interpolate-parametric
"   Builds a parametric function that interpolates given collection of points.
   Parametric function represents a curve that go through all points. By default domain is [0, 1].

   Arguments:
     points -- collection of points. Each point either a single value or collection of values.
     type -- type of interpolation - :linear, :polynomial, :cubic-spline, :cubic-hermite-spline, :b-spline.

   Options:
     :range -- defines range for parameter t.
               Default value is [0, 1]. f(0) = points[0], f(1) = points[n].
               :b-spline ignores this option and always uses [0, 1]

     :boundaries -- valid only for :cubic-spline interpolation. Defines boundary condition for cubic spline. Possible values - :natural and :closed.
                    Support that our spline is function S. leftmost point is a, rightmost - b.
                    :natural - S''(a) = S''(b) = 0
                    :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be useful if you want to get periodic or closed curv

                    Default value is :natural

     :degree - valid only for :b-spline. Degree of a B-spline. Default 3. Degree will be reduced if there are too few points.

   Examples:

   (def points [[0 0]
                [0 1]
                [1 1]
                [3 5]
                [2 9]])
   (def cubic (interpolate-parametric points :cubic-spline))
   (cubic 0) => [0.0 0.0]
   (cubic 1) => [2.0 9.0]
   (cubic 0.5) => [1.0 1.0]

   ; Specify custom :range
   (def cubic (interpolate-parametric points :cubic-spline :range [-10 10))
   (cubic -10) => [0.0 0.0]
   (cubic 0) => [1.0 1.0]
"
  [points type & options]
  (let [opts (when options (apply assoc {} options))]
    (case type
     :polynomial (polynomial/barycentric-cheb points opts)
     :b-spline (b-spline/b-spline points opts)
     (let [method (case type
                    :linear linear/interpolate
                    :cubic-spline cubic-spline/interpolate
                    :cubic-hermite-spline cubic-spline/interpolate-hermite)
           rng (:range opts [0 1])
           ts (uniform rng (count points))]
       (if (number? (first points))
         (let [t-points (map vector ts points)]
           (method t-points opts))
         (let [point-groups (->> points
                                 (map (fn [t value]
                                        (map #(vector t %) value))
                                      ts)
                                 (apply map vector))
               interpolators (map #(method % opts) point-groups)]
           (fn [t]
             (map #(% t) interpolators))))))))


(defn- interpolate-grid* [grid type {:keys [x-range y-range xs ys] :as options}]
  (if-not (or (nil? xs) (nil? ys))
    (let [method (case type
                   :bilinear linear/interpolate-grid
                   :polynomial polynomial/interpolate-grid
                   :bicubic-spline cubic-spline/interpolate-grid
                   :bicubic-hermite-spline cubic-spline/interpolate-grid-hermite
                   :b-surface b-spline/b-surface)
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
          options (assoc options
                    :xs (uniform x-range m)
                    :ys (uniform y-range n))]
      (interpolate-grid* grid type options))))


(defn interpolate-grid
  "Interpolates 2-dimensional grid. Returns function f that takes 2 arguments: x and y. By default function interpolates on [0,1]x[0,1].

   Arguments:
     grid -- collection of collection of numbers to be interpolated. If you need to interpolate vectors - interpolate each component by separate interpolator.
     type -- type of interpolation. Available: :bilinear, :polynomial, :bicubic-spline, :bicubic-hermite-spline

   Options:
     :x-range, :y-range - range of possible x and y.
                          By default :x-range = [0 1] and :y-range = [0 1]
                          :b-surface ignores this option and always uses [0, 1] x [0, 1]

     :xs, :ys - coordinates of grid points. Size of xs and ys must be consistent with grid size. If you have grid 4x3 then xs must have size 3 and ys - 4.
     Note that :x-range, :y-range and :xs, :ys both doing same job - they specify coordinates of points in grid. So you should use only 1 of them or none at all.

   Options:
     :boundaries - valid only for :cubic-spline interpolation. Defines boundary condition for bicubic spline. Possible values - :natural and :closed. Default - :natural. Check documentation of 'interpolate' method for more explanation.

     :degree - valid only for :b-spline. Degree of a B-spline. Default 3. Degree will be reduced if there are too few points.

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
           f (interpolate points :cubic-hermite-spline)]
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
           [f & fns] (map #(interpolate-parametric % :b-spline :degree 3) vectors)
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

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (require '[incanter.interp.polynomial :as polynomial])
     (let [n 10
           xs (repeatedly n #(rand-int 20))
           ys (repeatedly n #(rand-int 20))
           points (map vector xs ys)
           interp (interpolate-parametric points :polynomial)
           plot (charts/parametric-plot interp -1 1)]
       (doto plot
         (charts/add-points xs ys)
         (core/view))))

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (let [points [[0 0] [1 3] [2 0] [5 2] [6 1] [8 2] [11 1]]
           [xs ys] (core/trans points)
           cubic (interpolate points :cubic-spline :boundaries :closed)
           b-spline (interpolate-parametric  points :b-spline :degree 1)
           plot (charts/parametric-plot b-spline 0 1)]
       (doto plot
         (charts/add-points xs ys)
         (core/view)
         (core/save "/home/nikelandjelo/plot.png"))))

   (doc core/save)


   )
