(defproject incanter/incanter-io "1.3.0"
  :description "Incanter-io is the I/O module of the Incanter project."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-core "1.3.0"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [net.sf.opencsv/opencsv "2.0.1"]
                 [org.danlarkin/clojure-json "1.1"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  :dev-dependencies [[lein-clojars "0.7.0"]])
