(defproject incanter/incanter-sql "1.5.8-SNAPSHOT"
  :description "Database interaction via ClojureQL"
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-sql"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.5.8-SNAPSHOT"]
                 [org.clojure/clojure "1.5.1"]
                 [clojureql "1.0.4" :exclusions [org.clojure/clojure]]
                 ]
  :profiles {:dev {:dependencies [[org.apache.derby/derby "10.10.1.1"]]}}
  )

