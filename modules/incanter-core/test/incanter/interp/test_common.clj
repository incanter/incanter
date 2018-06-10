(ns incanter.interp.test-common
  (:use clojure.test)
  (:require [clojure.core.matrix :as m]))

(def eps 1e-5)

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
      (is (m/equals (interp-fn x) y eps) (str "x = " x " expecting f(x) = " y)))))

(defn test-interpolate-grid [method-fn options]
  (let [xs (vec (range 7))
        ys (vec (range 5))
        grid (vec (for [y ys] (vec (for [x xs] (+ (* 2 x) y)))))
        interp-fn (method-fn grid xs ys options)]
    (doseq [x xs
            y ys]
      (is (m/equals (interp-fn x y) (get-in grid [y x]) eps)
          (str "x = " x " , y = " y ". Expecting f(x, y) = " (get-in grid [y x]))))))
