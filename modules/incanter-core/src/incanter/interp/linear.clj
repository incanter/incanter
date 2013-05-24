(ns ^{:skip-wiki true}
  incanter.interp.linear
  (:require [incanter.interp.utils :refer (find-segment find-rect)]))

(defn- calc-line
  "Finds value in point x.
   Given:
     f(xl) = yl
     f(xr) = yr
     xl <= x <= xr"
  [xl yl xr yr x]
  (let [xl (double xl)
        yl (double yl)
        xr (double xr)
        yr (double yr)
        x (double x)
        coef (/ (- x xl) (- xr xl))]
    (+ (* (- 1.0 coef) yl)
       (* coef yr))))

(defn interpolate
  "Interpolates set of points using linear interpolation."
  [points options]
  (let [xs (mapv first points)
        ys (mapv second points)]
    (fn [x]
      (let [ind-l (find-segment xs x)
            ind-r (inc ind-l)]
        (calc-line (xs ind-l) (ys ind-l) (xs ind-r) (ys ind-r) x)))))

(defn- calc-plane
  "Finds value in point (x, y).
   Given:
     f(xl, yd) = zld
     f(xl, yu) = zlu
     f(xr, yd) = zrd
     f(xr, yu) = zru
     xl <= x <= xr
     yd <= y <= yd"
  [xl xr yd yu [zld zrd] [zlu zru] x y]
  (let [zd (calc-line xl zld xr zrd x)
        zu (calc-line xl zlu xr zru x)
        res (calc-line yd zd yu zu y)]
    res))

(defn interpolate-grid
  "Interpolates grid of values using bilinear interpolation."
  [grid xs ys options]
  (let [grid (vec (map vec grid))]
    (fn [x y]
      (let [[ind-x ind-y] (find-rect xs ys x y)
            subvec-2 (fn [vec start] (subvec vec start (+ start 2)))
            [xl xr] (subvec-2 xs ind-x)
            [yd yu] (subvec-2 ys ind-y)
            [zd zu] (map #(subvec-2 % ind-x) (subvec-2 grid ind-y))]
        (calc-plane xl xr yd yu zd zu x y)))))
