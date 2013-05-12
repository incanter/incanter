(ns incanter.interpolation
  (:require [incanter.interp
             [cubic-spline :as cubic-spline]
             [b-spline :as b-spline]
             [polynomial :as polynomial]
             [linear :as linear]
             [utils :as utils]
             [lls :as lls]]
            [incanter.core :refer (plus minus mult div)]))


(defn- validate-unique [xs]
  (when-not (apply distinct? xs)
    (throw (IllegalArgumentException. "All x must be distinct."))))


(defn interpolate
"   Builds a function that interpolates given collection of points.
   http://en.wikipedia.org/wiki/Interpolation
   http://en.wikipedia.org/wiki/Linear_least_squares_(mathematics)

   Arguments:
     points -- collection of points. Each point is a collection [x y].
     type -- type of interpolation - :linear, :polynomial, :cubic, :cubic-hermite, :linear-least-squares. For most cases you should use :cubic or :cubic-hermite - they usually give best results. Check http://en.wikipedia.org/wiki/Interpolation for brief explanation of each kind.

   Options:
     :boundaries - valid only for :cubic interpolation. Defines boundary condition for cubic spline. Possible values - :natural and :closed.
                   Support that our spline is function S. leftmost point is a, rightmost - b.
                   :natural - S''(a) = S''(b) = 0
                   :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be useful if you want to get periodic or closed curve.
                   Default value is :natural

     :derivatives - valid only for :cubic-hermite. Defines first derivatives for spline. If not specified derivatives will be approximated from points.

   Options for linear least squares:
     :basis - type of basis functions. There are 2 built-in bases: chebushev polynomials and b-splines (:polynomial and :b-spline).
              You also can supply your own basis. It should be a function that takes x and returns collection [f1(x) f2(x) ... fn(x)].
              Example of custom basis of 2 functions (1 and  x*x): (interpolate :linear-least-squares :basis (fn [x] [1 (* x x)]))
              Default value is :chebyshev

     :n - number of functions in basis if you use built-in basis. Note that if n is greater that number of points then you might get singular matrix and exception.
          Default value is 4.

     :degree - degree of b-spline if you use :b-spline basis.
               Default value is 3.

   Examples:

   (def points [[0 0] [1 5] [2 0] [3 5]])
   (def linear (interpolate points :linear))
   (linear 0) => 0.0
   (linear 1) => 5.0
   (linear 1.5) => 2.5

   ; Specify boundary conditions
   (interpolate points :cubic :boundaries :closed)
"
  [points type & options]
  (let [method (case type
                 :linear linear/interpolate
                 :polynomial polynomial/interpolate
                 :cubic cubic-spline/interpolate
                 :cubic-hermite cubic-spline/interpolate-hermite
                 :linear-least-squares lls/interpolate)
        points (sort-by first points)
        opts (when options (apply assoc {} options))]
    (validate-unique (map first points))
    (method points opts)))


(defn interpolate-parametric
"   Builds a parametric function that interpolates given collection of points.
   Parametric function represents a curve that go through all points. By default domain is [0, 1].

   Arguments:
     points -- collection of points. Each point either a single value or collection of values.
     type -- type of interpolation - :linear, :polynomial, :cubic, :cubic-hermite, :b-spline, :linear-least-squares.

   Options:
     :range -- defines range for parameter t.
               Default value is [0, 1]. f(0) = points[0], f(1) = points[n].

     :boundaries -- valid only for :cubic interpolation. Defines boundary condition for cubic spline. Possible values - :natural and :closed.
                    Support that our spline is function S. leftmost point is a, rightmost - b.
                    :natural - S''(a) = S''(b) = 0
                    :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be useful if you want to get periodic or closed curv

                    Default value is :natural

     :derivatives - valid only for :cubic-hermite. Defines first derivatives for spline. If not specified derivatives will be approximated from points.

     :degree - valid only for :b-spline. Degree of a B-spline. Default 3. Degree will be reduced if there are too few points.

   Options for linear least squares:
     See documentation for interpolate function.

   Examples:

   (def points [[0 0]
                [0 1]
                [1 1]
                [3 5]
                [2 9]])
   (def cubic (interpolate-parametric points :cubic))
   (cubic 0) => [0.0 0.0]
   (cubic 1) => [2.0 9.0]
   (cubic 0.5) => [1.0 1.0]

   ; Specify custom :range
   (def cubic (interpolate-parametric points :cubic :range [-10 10))
   (cubic -10) => [0.0 0.0]
   (cubic 0) => [1.0 1.0]
"
  [points type & options]
  (let [opts (when options (apply assoc {} options))
        rng (:range opts [0 1])]
    (case type
     :polynomial (-> (polynomial/barycentric-cheb points opts)
                     (utils/translate-domain rng [-1 1]))
     :b-spline (-> (b-spline/b-spline points opts)
                   (utils/translate-domain rng [0 1]))
     :linear-least-squares (lls/interpolate-parametric points opts)
     (let [method (case type
                    :linear linear/interpolate
                    :cubic cubic-spline/interpolate
                    :cubic-hermite cubic-spline/interpolate-hermite)
           ts (utils/uniform rng (count points))]
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
                   :bicubic cubic-spline/interpolate-grid
                   :bicubic-hermite cubic-spline/interpolate-grid-hermite
                   :b-surface b-spline/b-surface
                   :linear-least-squares lls/interpolate-grid)
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
                    :xs (utils/uniform x-range m)
                    :ys (utils/uniform y-range n))]
      (interpolate-grid* grid type options))))


(defn interpolate-grid
  "Interpolates 2-dimensional grid. Returns function f that takes 2 arguments: x and y. By default function interpolates on [0,1]x[0,1].

   Arguments:
     grid -- collection of collection of numbers to be interpolated. If you need to interpolate vectors - interpolate each component by separate interpolator.
     type -- type of interpolation. Available: :bilinear, :polynomial, :bicubic, :bicubic-hermite, :b-surface, :linear-least-squares

   Common options:
     :x-range, :y-range - range of possible x and y.
                          By default :x-range = [0 1] and :y-range = [0 1]
                          :b-surface ignores this option and always uses [0, 1] x [0, 1]

     :xs, :ys - coordinates of grid points. Size of xs and ys must be consistent with grid size. If you have grid 4x3 then xs must have size 3 and ys - 4.
     Note that (:x-range, :y-range) and (:xs, :ys) both do same job - they specify coordinates of points in grid. So you should use only one of them or none at all.

   Type specific options:
     :boundaries - valid only for :cubic interpolation. Defines boundary condition for bicubic spline. Possible values - :natural and :closed. Default - :natural. Check documentation of 'interpolate' method for more explanation.

     :degree - valid only for :b-spline. Degree of a B-spline. Default 3. Degree will be reduced if there are too few points.

     :basis - defines basis for :linear-least-squares. It has 1 predefined basis :polynomial. :polynomial basis contains functions: (1, x, y, x^2, xy, y^2, x^3, ...) You can specify how many functions basis contains by using :n option.
              You can also specify custom basis. Custom basis is a function that takes 2 arguments - x and y, and returns collection of values. Example: basis that contains only 2-degree polynomials: (fn [x y] [(* x x) (* x y) (* y y)])

     :n - defines how many functions polynomial contains. Example: 1 - basis is (1), 3 - basis is (1, x, y), 5 - basis is (1, x, y, x^2, x*y)

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

#_(

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (let [n 20
           xs (take n (distinct (repeatedly #(rand-int (* n 2)))))
           ys (repeatedly n #(rand-int (* n 2)))
           points (map vector xs ys)
           min-x (apply min xs)
           max-x (apply max xs)
           f (interpolate points :linear-least-squares :n 2)]
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
           plot (charts/parametric-plot (interpolate points :cubic :boundaries :closed) 0 (dec n))]
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
           interp (interpolate-parametric points :linear-least-squares :range [0 5] :basis :b-spline)
           plot (charts/parametric-plot interp 0 5)]
       (doto plot
         (charts/add-points xs ys)
         (core/view))))

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (defn set-ranges [chart [x-min x-max x-tu] [y-min y-max y-tu]]
       (let [plot (.getXYPlot chart)
             tu #(org.jfree.chart.axis.NumberTickUnit. %)]
         (doto (.getRangeAxis plot)
           (.setRange y-min y-max)
           (.setTickUnit (tu y-tu)))
         (doto (.getDomainAxis plot)
           (.setRange x-min x-max)
           (.setTickUnit (tu x-tu)))
         chart))
     (let [points [[0 0] [1 3] [2 0] [5 2] [6 1] [8 2] [11 1]]
           [xs ys] (core/trans points)
           get-chart (fn [type & options]
                       (-> (apply interpolate points type options)
                           (charts/function-plot  0 11 :x-label "x" :y-label "y")))
           methods [{:name "linear_interpolation"
                     :chart (get-chart :linear)}
                    {:name "polynomial_interpolation"
                     :chart (get-chart :polynomial)
                     :y-range [-1 17.5 2]}
                    {:name "cubic_interpolation"
                     :chart (get-chart :cubic)
                     :y-range [-1 3.5 1]}
                    {:name "cubic_hermite_interpolation"
                     :chart (get-chart :cubic-hermite
                                       :derivatives (repeat 1))}
                    {:name "lls_polynomial_3"
                     :chart (-> (interpolate points :linear-least-squares
                                             :basis :polynomial
                                             :n 3)
                                (charts/function-plot 0 11 :x-label "x" :y-label "y"))}
                    {:name "lls_sin"
                     :chart (-> (interpolate points :linear-least-squares
                                             :basis #(vector 1 % (* % %) (Math/sin %)))
                                (charts/function-plot 0 11 :x-label "x" :y-label "y"))}]]
       (doseq [{:keys [name chart x-range y-range]
                :or {x-range [-0.5 11.5 2]
                     y-range [-0.5 3.5 1]}} methods]
         (doto chart
           (set-ranges x-range y-range)
           (charts/add-points xs ys)
           #_(core/view)
           (core/save (str "/home/nikelandjelo/interpolation-examples/diplom/img/" name "_1_var_small.png")
                      :width 500
                      :height 400)))))

   (let [points [[0 0] [1 3] [2 0] [5 2] [6 1] [8 2] [11 1]]
         [xs ys] (core/trans points)
         chart (charts/scatter-plot xs ys
                                    :x-label "x"
                                    :y-label "y")]
     (doto chart
       (charts/add-points xs ys)
       (set-ranges [-1 12 2] [-1 4 1])
       (core/view)
       (core/save "/home/nikelandjelo/interpolation-examples/diplom/img/points.png")))


   (let [points [[0 0] [1 0] [1 3] [2 3] [2 0] [5 0] [5 2] [6 2] [6 1] [8 1] [8 2] [11 2] [11 1]]
         [xs ys] (core/trans points)
         chart (charts/xy-plot xs ys
                               :x-label "x"
                               :y-label "y")]
     (doto chart
       (charts/add-points xs ys)
       (set-ranges [-1 12 2] [-1 4 1])
       (core/view)
       (core/save "/home/nikelandjelo/interpolation-examples/diplom/img/interrupted.png")))

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (let [points [[0 10] [2 13] [4 10] [2 5] [0 0] [-2 5] [-4 10] [-2 13] [0 10]]]
       (-> (interpolate-parametric points :cubic)
           (charts/parametric-plot 0 1)
           (charts/add-points (map first points) (map second points))
           (core/view))))

   (do
     (require '[incanter.core :as core])
     (require '[incanter.charts :as charts])
     (defn set-ranges [chart [x-min x-max x-tu] [y-min y-max y-tu]]
       (let [plot (.getXYPlot chart)
             tu #(org.jfree.chart.axis.NumberTickUnit. %)]
         (doto (.getRangeAxis plot)
           (.setRange y-min y-max)
           (.setTickUnit (tu y-tu)))
         (doto (.getDomainAxis plot)
           (.setRange x-min x-max)
           (.setTickUnit (tu x-tu)))
         chart))
     (let [points [[0 10] [2 13] [4 10] [2 5] [0 0] [-2 5] [-4 10] [-2 13] [0 10]]
           [xs ys] (core/trans points)
           get-chart (fn [type & options]
                       (-> (apply interpolate-parametric points type options)
                           (charts/parametric-plot  0 1 :x-label "x" :y-label "y")))
           methods [{:name "linear_parametric_interpolation"
                     :chart (get-chart :linear)}
                    {:name "polynomial_parametric_interpolation"
                     :chart (get-chart :polynomial)}
                    {:name "cubic_closed_parametric_interpolation"
                     :chart (get-chart :cubic :boundaries :closed)}
                    {:name "cubic_hermite_parametric_interpolation"
                     :chart (get-chart :cubic-hermite)}
                    {:name "lls_polynomial_parametric_4"
                     :chart (get-chart :linear-least-squares)}
                    {:name "b_spline_parametric"
                     :chart (get-chart :b-spline)}]]
       (doseq [{:keys [name chart x-range y-range]
                :or {x-range [-5 5 2]
                     y-range [-1 14 2]}} methods]
         (doto chart
           (set-ranges x-range y-range)
           (charts/add-points xs ys)
           (core/view)
           (core/save (str "/home/nikelandjelo/interpolation-examples/diplom/img/" name "_small.png")
                      :width 500
                      :height 400)))))

   (map (interpolate-parametric [[0 0] [1 1] [2 0] [3 1]] :b-spline :degree 0) [0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1])

   (.. chart getXYPlot getDomainAxis (setRange 0 5) )

   (core/view chart)

   (require '[incanter [charts :as charts] [core :as core]])

   (let [;points [[0 0] [1 3] [2 0] [5 2] [6 1] [8 2] [11 1]]
         points [[0 10] [2 13] [4 10] [2 5] [0 0] [-2 5] [-4 10] [-2 13] [0 10]]
         xs (map first points)
         ys (map second points)
         fn (interpolate-parametric points :b-spline :degree 3)]
     (-> fn
         (charts/parametric-plot 0 1)
         (charts/add-points xs ys)
;         (core/view)
;         (core/save "img.png" :width 800 :height 600)
         (core/save "img.png" :width 350 :height 262)
         ))

   (def ^:dynamic points [[0 10] [2 13] [4 10] [2 5] [0 0] [-2 5] [-4 10] [-2 13] [0 10]])

   (defn plot [fn]
  (-> (charts/function-plot fn 0 11)
      (charts/add-points (map first points)
                         (map second points))
      (core/view)))

   (defn plot [fn]
  (-> (charts/parametric-plot fn 0 1)
      (charts/add-points (map first points)
                         (map second points))
      (core/view)))


   ((plot (interpolate-parametric points :linear)) 0.5)

   (plot (interpolate-parametric points :cubic :boundaries :closed))
   (plot (interpolate-parametric points :cubic-hermite))

   (plot (interpolate-parametric points :linear-least-squares :basis :polynomial :n 3))

   
(plot (interpolate-parametric points :b-spline))

(doseq [degree [1 2 3 (count points)]]
  (plot (interpolate-parametric points :b-spline :degree degree)))

   (doc core/save)
   )
