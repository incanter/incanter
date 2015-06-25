(ns ^{:skip-wiki true}
  incanter.interp.utils)

(defn binary-search
  "  Finds index of rightmost value in sorted vector that is less or equal to given value."
  [vec value]
  (loop [left 0
         right (dec (count vec))]
    (let [middle (quot (+ left right) 2)]
      (cond
       (= right left)
       0

       (= (- right left) 1)
       (if (<= (nth vec right) value) right left)

       (<= (nth vec middle) value)
       (recur middle right)

       :else
       (recur left middle)))))


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
  "  Translates domain [c d] of function f to [a b] and returns new function g such that: f(c) = g(a), f(d) = g(b)"
  [f [a b] [c d]]
  (if (and (== a c)
           (== b d))
    f
    (let [k (/ (- c d) (- a b))]
      (fn [^double x]
        (f (+ (* (- x a) k) c))))))

(defn uniform
" Splits segment [a b] to n points with equals distance between them."
  [[a b] n]
  (map #(-> (* (- b a) %)
            (/ (dec n))
            (+ a))
       (range n)))
