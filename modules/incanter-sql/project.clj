(defproject incanter/incanter-sql "1.5.0-SNAPSHOT"
  :description "Database interaction via ClojureQL"
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/liebke/incanter"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.5.0-SNAPSHOT"]
                 [clojureql "1.0.4"]
                 ]
  :profiles {:dev {:dependencies [[org.apache.derby/derby "10.8.1.2"]]}}
  )
