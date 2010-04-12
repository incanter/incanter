(defproject incanter/incanter-io "1.2.1"
  :description "Incanter-io is the I/O module of the Incanter project."
  :dependencies [[incanter/incanter-core "1.2.1"]
                 [net.sf.opencsv/opencsv "2.0.1"]
                 [org.danlarkin/clojure-json "1.1-SNAPSHOT"
                   :exclusions [org.clojure/clojure org.clojure/clojure-contrib]]]
  :dev-dependencies [[lein-clojars "0.5.0-SNAPSHOT"]
                     [leiningen/lein-swank "1.1.0"]])
