(defproject incanter "1.2.3-SNAPSHOT"
  :description "Incanter is a Clojure-based, R-like statistical programming and data visualization environment."
  :dependencies [[incanter/incanter-core "1.2.3-SNAPSHOT"]
                 [incanter/incanter-io "1.2.3-SNAPSHOT"]
                 [incanter/incanter-charts "1.2.3-SNAPSHOT"]
                 [incanter/incanter-processing "1.2.3-SNAPSHOT"]
                 [incanter/incanter-mongodb "1.2.3-SNAPSHOT"]
                 [incanter/incanter-pdf "1.2.3-SNAPSHOT"]
                 [incanter/incanter-latex "1.2.3-SNAPSHOT"]
                 [incanter/incanter-excel "1.2.3-SNAPSHOT"]
                 [swingrepl "1.0.0-SNAPSHOT"]]
  :dev-dependencies [[lein-clojars "0.5.0-SNAPSHOT"]
                     [swank-clojure "1.2.1"]
                     [jline "0.9.94"]]
  :main incanter.main)
