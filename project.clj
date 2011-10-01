(defproject incanter "1.3.0-SNAPSHOT"
  :description "Incanter is a Clojure-based, R-like statistical programming and data visualization environment."
  :dependencies [[incanter/incanter-core "1.3.0-SNAPSHOT"]
                 [incanter/incanter-io "1.3.0-SNAPSHOT"]
                 [incanter/incanter-charts "1.3.0-SNAPSHOT"]
                 [incanter/incanter-processing "1.3.0-SNAPSHOT"]
                 [incanter/incanter-mongodb "1.3.0-SNAPSHOT"]
                 [incanter/incanter-pdf "1.3.0-SNAPSHOT"]
                 [incanter/incanter-latex "1.3.0-SNAPSHOT"]
                 [incanter/incanter-excel "1.3.0-SNAPSHOT"]
                 [swingrepl "1.3.0"]
                 [jline "0.9.94"]]
  :dev-dependencies [[lein-clojars "0.7.0"]]
  :main incanter.main)
