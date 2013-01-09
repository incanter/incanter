(ns incanter.interpolation-test
  (:use clojure.test
        incanter.interpolation))

(defn points-eq?
  "Checks whether 2 points are equals.
   Point either a number or collection of numbers (multidimensional point)."
  [p1 p2]
  (if (coll? p1)
    (every? true? (map points-eq? p1 p2))
    (== p1 p2)))

(defn test-interpolation [xs ys]
  (doseq [method [:linear :polynomial :cubic-spline]]
    (testing (str "Test " (name method) " interpolation")
      (let [points (map vector xs ys)
            interp-fn (interpolate points method)]
        (doseq [[x y] (map vector xs ys)]
          (is (points-eq? (interp-fn x) y) (str "x = " x " expecting f(x) = " y)))))))

(deftest interpolate-test
  (test-interpolation (range 10)
                      (range 10 0 -1)))

(deftest interpolate-test-parametric
  (test-interpolation (range 10)
                      (map vector
                           (range 10)
                           (range 10 0 -1))))

(deftest interpolate-grid-test
  (doseq [method [:bilinear :polynomial :bicubic-spline]]
    (testing (str "Test " (name method) " grid interpolation")
      (let [xs (range 6)
            ys (range 6)
            grid (vec (for [x xs] (vec (for [y ys] (+ x y)))))
            interp-fn (interpolate-grid grid method :xs xs :ys ys)]
        (doseq [x xs
                y ys]
          (is (points-eq? (interp-fn x y) (get-in grid [x y]))
              (str "x = " x " , y = " y ". Expecting f(x, y) = " (get-in grid [x y]))))))))

