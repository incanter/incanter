(ns incanter.interp.polynomial
  (:use [incanter.core :only (plus minus mult div)]))

(defn interpolate [points]
  (let [xs (map first points)
        ys (map second points)
        divided-difference (fn [[f1 f2]]
                             {:f (div (minus (:f f2) (:f f1))
                                      (minus (:x-r f2) (:x-l f1)))
                              :x-r (:x-r f2)
                              :x-l (:x-l f1)})
        next-level-differences (fn [fs]
                                 (map divided-difference (partition 2 1 fs)))
        fs (->> (map (fn [x f] {:f f :x-l x :x-r x}) xs ys)
                (iterate next-level-differences)
                (take (count xs))
                (map first)
                (map :f))]
    (fn [x]
      (->> (reductions #(* %1 (- x %2)) 1 xs)
           (map mult fs)
           (apply plus)))))