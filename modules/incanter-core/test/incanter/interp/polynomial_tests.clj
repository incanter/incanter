(ns incanter.interp.polynomial-tests
  (:use clojure.test
        incanter.interp.polynomial
        incanter.interp.test-common))

(deftest interpolate-test
  (test-interpolate interpolate {}))

(deftest interpolate-grid-test
  (test-interpolate-grid interpolate-grid {}))
