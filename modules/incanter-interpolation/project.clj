(defproject incanter/incanter-interpolation "1.5.0-SNAPSHOT"
  :description "Incanter-interpolation is module for functions interpolation. Interpolates functions of 1 and 2 arguments."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/liebke/incanter"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [incanter/incanter-core "1.5.0-SNAPSHOT"]
                 [incanter/incanter-charts "1.5.0-SNAPSHOT"]]
  :java-source-paths ["java"]
  :profiles {:dev {:dependencies [[criterium "0.3.1"]]}})
