(defproject incanter/incanter-excel "1.3.0-SNAPSHOT"
  :description "Incanter-excel provides access to reading and writing Excel files."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-core "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
  		 [org.apache.poi/poi "3.6"]
                 [org.apache.poi/poi-ooxml "3.6"]]
  :dev-dependencies [[lein-clojars "0.7.0"
                      :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]])
