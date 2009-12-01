(ns incanter.geospatial-tests
  (:use clojure.contrib.test-is)
  (:use incanter.geospatial))

(deftest distance-tests
 (is ( = 0
	 (distance-between [27.405328 68.607269] [27.405328 68.607269]))))