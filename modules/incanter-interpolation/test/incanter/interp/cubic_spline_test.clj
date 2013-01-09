(ns incanter.interp.cubic-spline-test
  (:use clojure.test
        incanter.interp.cubic-spline
        incanter.interp.test-common))

(deftest interpolate-normal
  (test-interpolate-normal interpolate))

(deftest interpolate-parametric
  (test-interpolate-parametric interpolate))

(deftest interpolate-grid-test
  (test-interpolate-grid interpolate-grid))