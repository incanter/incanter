(ns incanter.io-tests-runner
  (:use [clojure.contrib.test-is :only (run-tests)])
  (:gen-class))

(def test-names [:io-tests
                 :transformations-tests
                 :internal-tests
                 :bayes-tests
                 :probability-tests])

(def test-namespaces
     (map #(symbol (str "incanter." (name %)))
          test-names))

(defn run
  "Runs all IO-related tests"
  []
  (println "Loading IO tests...")
  (apply require :reload test-namespaces)
  (apply run-tests test-namespaces))

(run)