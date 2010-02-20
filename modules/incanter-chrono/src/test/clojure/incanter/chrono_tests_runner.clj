(ns incanter.chrono-tests-runner
  (:use [clojure.contrib.test-is :only (run-tests)])
  (:gen-class))

(def test-names [:chrono-test])

(def test-namespaces
     (map #(symbol (str "incanter." (name %)))
          test-names))

(defn run
  "Runs all defined tests"
  []
  (println "Loading core tests...")
  (apply require :reload test-namespaces)
  (apply run-tests test-namespaces))

(run)