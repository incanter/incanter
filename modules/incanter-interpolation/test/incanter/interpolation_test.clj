(ns incanter.interpolation-test
  (:use clojure.test
        incanter.interpolation))

(defn test-interpolation [xs ys]
  (doseq [method [:linear :polynomial :cubic-spline]]
    (testing (str "Test " (name method) " interpolation")
      (let [points (map vector xs ys)
            interp-fn (interpolate points method)]
        (doseq [[x y] (map vector xs ys)]
          (is (= (interp-fn x) y) (str "x = " x " expecting f(x) = " y)))))))

(deftest interpolate-test
  (test-interpolation (range 10)
                      (range 10 0 -1)))

(deftest interpolate-test-parametric
  (test-interpolation (range 10)
                      (map vector
                           (range 10)
                           (range 10 0 -1))))