(ns incanter.interp.linear-test
  (:use clojure.test
        incanter.interp.linear
        incanter.interp.test-common))

(deftest interpolate-normal
  (test-interpolate-normal interpolate))

(deftest interpolate-parametric
  (test-interpolate-parametric interpolate))

(deftest interpolate-grid-test
  (test-interpolate-grid interpolate-grid))