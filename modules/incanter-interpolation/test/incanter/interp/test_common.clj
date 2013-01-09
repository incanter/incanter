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

(defn test-interpolation [method-fn xs ys]
  (let [points (sort-by first (map vector xs ys))
        interp-fn (method-fn points)]
    (doseq [[x y] (map vector xs ys)]
      (is (points-eq? (interp-fn x) y) (str "x = " x " expecting f(x) = " y)))))

(defn rand-coll [n]
  (->> #(rand-int 100)
       repeatedly
       distinct
       (take n)))


(defn test-interpolate-normal [method-fn]
  (test-interpolation method-fn
                      (rand-coll 10)
                      (rand-coll 10)))

(defn test-interpolate-parametric [method-fn]
  (test-interpolation method-fn
                      (rand-coll 10)
                      (map vector
                           (rand-coll 10)
                           (rand-coll 10)
                           (rand-coll 10))))

(defn test-interpolate-grid [method-fn]
  (let [xs (vec (range 6))
        ys (vec (range 6))
        grid (vec (for [x xs] (vec (for [y ys] (+ x y)))))
        interp-fn (method-fn grid xs ys {})]
    (doseq [x xs
            y ys]
      (is (points-eq? (interp-fn x y) (get-in grid [x y]))
          (str "x = " x " , y = " y ". Expecting f(x, y) = " (get-in grid [x y]))))))
