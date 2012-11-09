(defproject incanter-zoo "1.4.0"
  :description "Incanter Zoo is a port of Zoo from R."
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.4.0"]
                 [incanter/incanter-io "1.4.0"]
                 [clj-time "0.4.4"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  )
