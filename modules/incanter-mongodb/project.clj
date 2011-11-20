(defproject incanter/incanter-mongodb "1.3.0-SNAPSHOT"
  :description "Incanter-mongodb is the MongoDB module of the Incanter project."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-core "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [congomongo "0.1.7-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  :dev-dependencies [[lein-clojars "0.7.0"]])
