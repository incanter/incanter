(defproject incanter "1.3.0-SNAPSHOT"
  :description "Incanter is a Clojure-based, R-like statistical programming and data visualization environment."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-core "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-io "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-charts "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-processing "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-mongodb "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-pdf "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-latex "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-excel "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [swingrepl "1.3.0"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [jline "0.9.94"]]
  :dev-dependencies [[lein-clojars "0.7.0"
                      :exclusions [org.clojure/clojure
                                   org.clojure/clojure-contrib]]]
  :main incanter.main)
