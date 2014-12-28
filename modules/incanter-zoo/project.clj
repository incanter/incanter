(defproject incanter/incanter-zoo "1.9.1-SNAPSHOT"
  :description "Incanter Zoo is a port of Zoo from R."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-zoo"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-io "1.9.1-SNAPSHOT"]
                 [org.clojure/clojure "1.6.0"]
                 [clj-time "0.6.0"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  :profiles {:dev {:dependencies [[clatrix "0.4.0" :exclusions [org.clojure/clojure]]
                                  [org.jblas/jblas "1.2.3"]]}}
  )
