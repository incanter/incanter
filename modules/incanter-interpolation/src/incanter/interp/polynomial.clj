(ns incanter.interp.polynomial
  (:require [incanter.core :refer (matrix trans mmult $ to-list)]))


(defn interpolate
  "Interpolates point by polynomial using Newton form.
   http://en.wikipedia.org/wiki/Newton_polynomial"
  [points]
  (let [xs (mapv first points)
        ys (map second points)
        divided-difference (fn [[f1 f2]]
                             {:f (/ (- (:f f2) (:f f1))
                                    (- (:x-r f2) (:x-l f1)))
                              :x-r (:x-r f2)
                              :x-l (:x-l f1)})
        next-level-differences (fn [fs]
                                 (doall (map divided-difference (partition 2 1 fs))))
        fs (->> (map (fn [x f] {:f f :x-l x :x-r x}) xs ys)
                (iterate next-level-differences)
                (take (count xs))
                (map first)
                (mapv :f))]
    (fn [^double x]
      ; Use ugly loop instead of functional reductions, map, reduce
      ; Because it speeds up ~5 times.
      (loop [sum (double 0)
             pow (double 1)
             ind 0]
        (if (= (count fs) ind)
          sum
          (recur (+ (* (fs ind) pow) sum)
                 (* pow (- x (xs ind)))
                 (inc ind)))))))

(defn- update-P [P xs ys k]
  (letfn [(update-upper-right [i j]
            (/ (- (get-in P [i j]) (get-in P [i (dec j)]))
               (- (xs j) (xs (- j k)))))
          (update-bottom-left [i j]
            (/ (- (get-in P [i j]) (get-in P [(dec i) j]))
               (- (ys i) (ys (- i k)))))
          (update-bottom-right [i j]
            (/ (- (+ (get-in P [i j]) (get-in P [(dec i) (dec j)]))
                  (+ (get-in P [i (dec j)]) (get-in P [(dec i) j])))
               (* (- (ys i) (ys (- i k)))
                  (- (xs j) (xs (- j k))))))
          (update [P-new [i j]]
            (assoc-in P-new [i j]
                      ((cond (< i k) update-upper-right
                             (< j k) update-bottom-left
                             :default update-bottom-right)
                       i j)))]
    (let [n (count P)
          m (count (first P))]
      (reduce update P
              (for [i (range n)
                    j (range (if (< i k) k 0) m)]
                [i j])))))

(defn- calc-P [grid xs ys]
  (let [k (max (count grid) (count (first grid)))]
    (reduce #(update-P %1 xs ys %2) grid (range 1 k))))


(defn interpolate-grid
  "Interpolates grid of points using polynomial in Newton form.
   Implemented algorithm is taken from here:
   http://www.academia.edu/1387278/On_the_Newton_Multivariate_Polynomial_Interpolation_with_Applications
   Part III"
  [grid xs ys options]
  (let [P (matrix (calc-P grid xs ys))
        xs (butlast xs)
        ys (butlast ys)]
    (fn [x y]
      (let [Y (reductions #(* %1 (- y %2)) 1 ys)
            X (reductions #(* %1 (- x %2)) 1 xs)]
        (to-list ($ 0 0 (mmult (trans Y) P X)))))))

