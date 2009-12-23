(ns incanter.tests
  (:use [clojure.contrib.test-is :only (run-tests)])
  (:gen-class))

(def test-names [:chrono-tests
                 :internal-tests
                 :bayes-tests
                 :information-theory-tests
                 :probability-tests])

(def test-namespaces
     (map #(symbol (str "incanter." (name %)))
          test-names))

(defn run
  "Runs all defined tests"
  []
  (println "Loading tests...")
  (apply require :reload test-namespaces)
  (apply run-tests test-namespaces))

(defn -main
  "Run all defined tests from the command line"
  [& args]
  (run)
  (System/exit 0))

