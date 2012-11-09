(defproject incanter/incanter-mongodb "1.4.0"
  :description "Incanter-mongodb is the MongoDB module of the Incanter project."
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.4.0"]
                 [congomongo "0.3.3"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  )
