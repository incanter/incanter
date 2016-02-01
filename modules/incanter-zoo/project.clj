(defproject incanter/incanter-zoo "1.5.7"
  :description "Incanter Zoo is a port of Zoo from R."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-zoo"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-io "1.5.7"]
                 [org.clojure/clojure "1.5.1"]
                 [clj-time "0.6.0"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  )
