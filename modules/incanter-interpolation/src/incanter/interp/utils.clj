(ns incanter.interp.utils)

(defn binary-search
"  Finds index of rightmost value in sorted vector that is less or equal to given value."
  [vec value]
  (loop [left 0
         right (dec (count vec))]
    (if (= (- right left) 1)
      (if (<= (nth vec right) value) right left)
      (let [middle (quot (+ left right) 2)]
        (if (<= (nth vec middle) value)
          (recur middle right)
          (recur left middle))))))


(defn find-segment
"  Finds indices of segment that contains given point.
   Params:
     xs - sorted coordinates of segments
     x - point"
  [xs x]
  (min (- (count xs) 2)
       (binary-search xs x)))


(defn find-rect
"  Finds indices of region (rectangle) in grid that contains given point.
   Params:
     xs, ys - coordinates of grid
     x, y - coordinates of point we to find region for"
  [xs ys x y]
  [(find-segment xs x)
   (find-segment ys y)])

(defn translate-domain
  "  Translates domain [a b] of function f to [c d] and returns new function such that: f(a) = g(c), f(b) = g(d)"
  [f [a b] [c d]]
  (if (and (== a c)
           (== b d))
    f
    (let [k (/ (- c d) (- a b))]
      (fn [^double x]
        (f (+ (* (- x a) k) c))))))
