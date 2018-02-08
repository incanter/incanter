(ns incanter.interp.cubic-spline-tests
  (:use incanter.core)
  (:require [clojure.test :refer :all]
            [incanter.interp
             [cubic-spline :refer :all]
             [test-common :refer :all]]))

(deftest compliance-test
  (doseq [impl [:clatrix :ndarray :persistent-vector :vectorz]]
    (set-current-implementation impl)
    (println (str "compliance test " impl))
    (test-interpolate interpolate {:boundaries :natural})
    (test-interpolate-grid interpolate-grid {:boundaries :natural})
    (test-interpolate interpolate {:boundaries :closed})
    (test-interpolate-grid interpolate-grid {:boundaries :closed})
    (test-interpolate interpolate-hermite nil)
    (test-interpolate-grid interpolate-grid-hermite nil)))
