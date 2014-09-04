(defproject incanter/incanter-io "1.9.0-SNAPSHOT"
  :description "Incanter-io is the I/O module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-io"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.9.0-SNAPSHOT"]
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/data.csv "0.1.2"]])
