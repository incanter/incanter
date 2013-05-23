(ns incanter.interp.test-common
  (:use clojure.test
        [incanter.core :only (abs)]))

(def eps 1e-5)

(defn points-eq?
  "Checks whether 2 points are equals.
   Point either a number or collection of numbers (multidimensional point)."
  [p1 p2]
  (if (coll? p1)
    (every? true? (map points-eq? p1 p2))
    (< (abs (- p1 p2)) eps)))

(defn rand-coll [n]
  (->> #(rand-int 100)
       repeatedly
       distinct
       (take n)))

(defn test-interpolate [method-fn options]
  (let [xs (rand-coll 10)
        ys (rand-coll 10)
        points (sort-by first (map vector xs ys))
        interp-fn (method-fn points options)]
    (doseq [[x y] (map vector xs ys)]
      (is (points-eq? (interp-fn x) y) (str "x = " x " expecting f(x) = " y)))))

(defn test-interpolate-grid [method-fn options]
  (let [xs (vec (range 7))
        ys (vec (range 5))
        grid (vec (for [y ys] (vec (for [x xs] (+ (* 2 x) y)))))
        interp-fn (method-fn grid xs ys options)]
    (doseq [x xs
            y ys]
      (is (points-eq? (interp-fn x y) (get-in grid [y x]))
          (str "x = " x " , y = " y ". Expecting f(x, y) = " (get-in grid [y x]))))))
