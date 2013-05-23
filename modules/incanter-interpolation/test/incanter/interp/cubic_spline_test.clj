(ns incanter.interp.cubic-spline-test
  (:require [clojure.test :refer :all]
            [incanter.interp
             [cubic-spline :refer :all]
             [test-common :refer :all]]))

(deftest interpolate-test-natural
  (test-interpolate interpolate {:boundaries :natural}))

(deftest interpolate-grid-test-natural
  (test-interpolate-grid interpolate-grid {:boundaries :natural}))

(deftest interpolate-test-closed
  (test-interpolate interpolate {:boundaries :closed}))

(deftest interpolate-grid-test-closed
  (test-interpolate-grid interpolate-grid {:boundaries :closed}))

(deftest interpolate-hermite-test
  (test-interpolate interpolate-hermite nil))

(deftest interpolate-grid-hermite-test
  (test-interpolate-grid interpolate-grid-hermite nil))
