(defproject incanter/incanter-sql "1.4.1"
  :description "Database interaction via ClojureQL"
  :dependencies [[incanter/incanter-core "1.4.1"]
                 [clojureql "1.0.4"]
                 ]
  :profiles {:dev {:dependencies [[org.apache.derby/derby "10.8.1.2"]]}}
  )
