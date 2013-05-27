(ns incanter.interp.linear-tests
  (:use clojure.test
        incanter.interp.linear
        incanter.interp.test-common))

(deftest interpolate-test
  (test-interpolate interpolate {}))

(deftest interpolate-grid-test
  (test-interpolate-grid interpolate-grid {}))
