(ns incanter.interpolation-test
  (:use clojure.test
        incanter.interpolation))

(defn test-interpolation [xs ys]
  (doseq [method [:linear :lagrange]]
    (testing (str "Test " (name method) " interpolation")
      (let [interp-fn (interpolate xs ys method)]
        (doseq [[x y] (map vector xs ys)]
          (is (= (interp-fn x) y) (str "x = " x " expecting f(x) = " y)))))))

(deftest interpolation
  (test-interpolation (range 10)
                      (range 10 0 -1)))

(deftest interpolation-points
  (test-interpolation (range 10)
                      (map vector
                           (range 10)
                           (range 10 0 -1))))