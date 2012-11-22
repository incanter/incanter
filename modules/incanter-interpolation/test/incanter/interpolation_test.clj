(ns incanter.interpolation-test
  (:use clojure.test
        incanter.interpolation))

(deftest interpolation
  (let [xs (range 10)
        ys (range 10 0 -1)
        data (map vector xs ys)
        interp-fn (interpolate xs ys)]
    (doseq [[x y] data]
      (is (= (interp-fn x) y) (str "Test in " x " expecting " y)))))