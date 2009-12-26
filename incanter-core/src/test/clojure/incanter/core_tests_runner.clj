(ns incanter.core-tests-runner
  (:use [clojure.contrib.test-is :only (run-tests)])
  (:gen-class))

(def test-names [:core-tests :stats-tests :information-theory-tests])

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