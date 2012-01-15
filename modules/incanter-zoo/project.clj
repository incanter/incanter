(defproject incanter-zoo "1.3.0-SNAPSHOT"
  :description "Incanter Zoo is a port of Zoo from R."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-core "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-io "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  :dev-dependencies [[lein-clojars "0.7.0"
                      :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]])