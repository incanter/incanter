(ns incanter.interp.cubic-spline-test
  (:use clojure.test
        incanter.interp.cubic-spline
        incanter.interp.test-common))

(deftest interpolate-test-natural
  (test-interpolate interpolate {:boundaries :natural}))

(deftest interpolate-grid-test-natural
  (test-interpolate-grid interpolate-grid {:boundaries :natural}))

(deftest interpolate-test-closed
  (test-interpolate interpolate {:boundaries :closed}))

(deftest interpolate-grid-test-closed
  (test-interpolate-grid interpolate-grid {:boundaries :closed}))