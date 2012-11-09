(defproject incanter/incanter-charts "1.4.0"
  :description "Incanter-charts is the JFreeChart module of the Incanter project."
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.4.0"]
                 [incanter/incanter-io "1.4.0"]
                 [incanter/jfreechart "1.0.13-no-gnujaxp"]
                 [clj-time "0.4.4"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  )
