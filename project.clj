(defproject incanter "1.2.1"
  :description "Incanter is a Clojure-based, R-like statistical programming and data visualization environment."
  :dependencies [
                 [org.clojure/clojure "1.1.0"]
                 [org.clojure/clojure-contrib "1.1.0"]
                 ;[org.clojure/clojure "1.2.0-master-SNAPSHOT"]
                 ;[org.clojure/clojure-contrib "1.2.0-master-SNAPSHOT"]
                 [incanter/incanter-core "1.2.1"]
                 [incanter/incanter-core "1.2.1"]
                 [incanter/incanter-io "1.2.1"]
                 [incanter/incanter-charts "1.2.1"]
                 [incanter/incanter-processing "1.2.1"]
                 [incanter/incanter-mongodb "1.2.1"]
                 [incanter/incanter-pdf "1.2.1"]]
  :dev-dependencies [[lein-clojars "0.5.0-SNAPSHOT"]
                     [leiningen/lein-swank "1.1.0"]
                     [jline "0.9.94"]])
