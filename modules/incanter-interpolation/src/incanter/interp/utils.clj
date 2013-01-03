(ns incanter.interp.utils)

(defn binary-search
  "Finds index of rightmost value in sorted vector that is less or equal to given value."
  [vec value]
  (loop [left 0
         right (dec (count vec))]
    (if (= (- right left) 1)
      (if (<= (nth vec right) value) right left)
      (let [middle (quot (+ left right) 2)]
        (if (<= (nth vec middle) value)
          (recur middle right)
          (recur left middle))))))


(defn out-of-range
  "Throws out-of-range exception."
  [x points]
  (throw (IllegalArgumentException.
          (format "x = %s is out of range [%s, %s]"
                  x
                  (apply min (map first points))
                  (apply max (map first points))))))