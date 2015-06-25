(ns ^{:doc "Interpolation and approximation of collection of points..
            Supported types: linear, polynomial, cubic spline,
            cubic hermite spline, B-spline, linear least squares.
            Supports 1-dimensional and 2-dimensional interpolations."
      :author "Nikita Beloglazov"}
  incanter.interpolation
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

(defn- validate-more-than-one-point-given [xs]
  (when-not (< 1 (count xs))
    (throw (IllegalArgumentException. "Must give more than 1 point to interpolate over"))))

(defn interpolate
  "
  Builds a function that interpolates given collection of points.
  http://en.wikipedia.org/wiki/Interpolation
  http://en.wikipedia.org/wiki/Linear_least_squares_(mathematics)

  Arguments:
    points -- collection of points. Each point is a collection [x y].
    type -- type of interpolation - :linear, :polynomial, :cubic, :cubic-hermite, :linear-least-squares.
            For most cases you should use :cubic or :cubic-hermite - they usually give best results.
            Check http://en.wikipedia.org/wiki/Interpolation for brief explanation of each kind.

  Options:
    :boundaries - valid only for :cubic interpolation. Defines boundary condition for cubic spline.
                  Possible values - :natural and :closed.
                  Let's S - spline, a- leftmost point, b- rightmost point.
                  :natural - S''(a) = S''(b) = 0
                  :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be
                  useful if you want to get periodic or closed curve.
                  Default value is :natural

    :derivatives - valid only for :cubic-hermite. Defines first derivatives for spline.
                   If not specified derivatives will be approximated from points.

  Options for linear least squares:
    :basis - type of basis functions. There are 2 built-in bases: chebushev polynomials and b-splines (:polynomial and :b-spline).
             You also can supply your own basis. It should be a function that takes x and returns collection [f1(x) f2(x) ... fn(x)].
             Example of custom basis of 2 functions (1 and  x*x): (interpolate :linear-least-squares :basis (fn [x] [1 (* x x)]))
             Default value is :chebyshev

    :n - number of functions in basis if you use built-in basis.
         Note that if n is greater that number of points then you might get singular matrix and exception.
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
      (validate-more-than-one-point-given points)
      (validate-unique (map first points))
      (method points opts)))


(defn interpolate-parametric
  "
  Builds a parametric function that interpolates given collection of points.
  Parametric function represents a curve that go through all points. By default domain is [0, 1].

  Arguments:
    points -- collection of points. Each point either a single value or collection of values.
    type -- type of interpolation - :linear, :polynomial, :cubic, :cubic-hermite, :b-spline, :linear-least-squares.

  Options:
    :range -- defines range for parameter t.
              Default value is [0, 1]. f(0) = points[0], f(1) = points[n].

    :boundaries -- valid only for :cubic interpolation.
                   Defines boundary condition for cubic spline. Possible values - :natural and :closed.
                   Let's S - spline, a- leftmost point, b- rightmost point.
                   :natural - S''(a) = S''(b) = 0
                   :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be useful
                   if you want to get periodic or closed curve

                   Default value is :natural

    :derivatives - valid only for :cubic-hermite. Defines first derivatives for spline.
                   If not specified derivatives will be approximated from points.

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
  (def cubic (interpolate-parametric points :cubic :range [-10 10]))
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
  "
  Interpolates 2-dimensional grid. Returns function f that takes 2 arguments: x and y.
  By default function interpolates on [0,1]x[0,1].

  Arguments:
    grid -- collection of collection of numbers to be interpolated.
            If you need to interpolate vectors - interpolate each component by separate interpolator.
    type -- type of interpolation. Available: :bilinear, :polynomial, :bicubic, :bicubic-hermite, :b-surface, :linear-least-squares

  Common options:
    :x-range, :y-range - range of possible x and y.
                         By default :x-range = [0 1] and :y-range = [0 1]
                         :b-surface ignores this option and always uses [0, 1] x [0, 1]

    :xs, :ys - coordinates of grid points. Size of xs and ys must be consistent with grid size.
               If you have grid 4x3 then xs must have size 3 and ys - 4.

    Note that (:x-range, :y-range) and (:xs, :ys) both do same job - they specify coordinates of points in grid.
    So you should use only one of them or none at all.

  Type specific options:
    :boundaries - valid only for :cubic interpolation. Defines boundary condition for bicubic spline.
                  Possible values - :natural and :closed.
                  Default - :natural. Check documentation of 'interpolate' method for more explanation.

    :degree - valid only for :b-spline. Degree of a B-spline. Default 3. Degree will be reduced if there are too few points.

    :basis - defines basis for :linear-least-squares. It has 1 predefined basis :polynomial. :polynomial basis
             contains functions: (1, x, y, x^2, xy, y^2, x^3, ...)
             You can specify how many functions basis contains by using :n option.
             You can also specify custom basis. Custom basis is a function that takes 2 arguments - x and y, and returns collection of values.
             Example: basis that contains only 2-degree polynomials: (fn [x y] [(* x x) (* x y) (* y y)])

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
  (interpolator  2 100) => 0"
  [grid type & options]
    (let [opts (when options (apply assoc {} options))]
      (interpolate-grid* grid type opts)))
