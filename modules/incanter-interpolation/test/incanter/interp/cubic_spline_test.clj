(ns incanter.interp.cubic-spline-test
  (:use clojure.test
        incanter.interp.cubic-spline
        incanter.interp.test-common))

(deftest interpolate-test
  (test-interpolate interpolate))

(deftest interpolate-grid-test
  (test-interpolate-grid interpolate-grid))