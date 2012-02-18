(defproject incanter/incanter-charts "1.3.0-SNAPSHOT"
  :description "Incanter-charts is the JFreeChart module of the Incanter project."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-core "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-io "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/jfreechart "1.0.13-no-gnujaxp"]
                 [clj-time "0.3.7"]]
  :dev-dependencies [[lein-clojars "0.7.0"
                      :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]])
