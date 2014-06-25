(ns incanter.interp.linear-tests
  (:use clojure.test
        incanter.core
        incanter.interp.linear
        incanter.interp.test-common))


(deftest compliance-test
  (doseq [impl [:clatrix :ndarray :persistent-vector :vectorz]]
    (set-current-implementation impl)
    (println (str "compliance test " impl))
    (test-interpolate interpolate {})
    (test-interpolate-grid interpolate-grid {})))
