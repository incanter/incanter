(ns incanter.interp.polynomial-tests
  (:use clojure.test
        incanter.core
        incanter.interp.polynomial
        incanter.interp.test-common))


(deftest compliance-test
  (doseq [impl [:clatrix :ndarray :persistent-vector :vectorz]]
    (set-current-implementation impl)
    (println (str "compliance test " impl))
    (test-interpolate interpolate {})
    (test-interpolate-grid interpolate-grid {})))
