(defproject incanter/incanter-core "1.2.3-SNAPSHOT"
  :description "Incanter-core is the core module of the Incanter project."
  :dependencies [
                 [org.clojure/clojure "1.2.0-beta1"]
                 [org.clojure/clojure-contrib "1.2.0-beta1"]
                 [incanter/parallelcolt "0.9.4"]]
  :dev-dependencies [[lein-javac "0.0.2-SNAPSHOT"]
                     [lein-clojars "0.5.0-SNAPSHOT"]]
  :repositories {"clojure-releases" "http://build.clojure.org/releases"})
