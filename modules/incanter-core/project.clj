(defproject incanter/incanter-core "1.3.0-SNAPSHOT"
  :description "Incanter-core is the core module of the Incanter project."
  :dependencies [[org.clojure/clojure "1.3.0-beta3"]
                 [org.clojure/math.combinatorics "0.1.0-SNAPSHOT"]
                 [incanter/parallelcolt "0.9.4"]]
  :dev-dependencies [[lein-javac "1.2.1-SNAPSHOT"]
                     [lein-clojars "0.6.0"]]
  :java-source-path "src")
