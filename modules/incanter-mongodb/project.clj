(defproject incanter/incanter-mongodb "1.9.1"
  :description "Incanter-mongodb is the MongoDB module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-mongodb"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.9.1"]
                 [congomongo "0.3.3"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  )
