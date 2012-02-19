(defproject incanter-zoo "1.3.0"
  :description "Incanter Zoo is a port of Zoo from R."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-core "1.3.0"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/incanter-io "1.3.0"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [clj-time "0.3.5"]]
  :dev-dependencies [[lein-clojars "0.7.0"
                      :exclusions [org.clojure/clojure
                                   org.clojure/clojure-contrib]]]
  :repositories {"snapshots" "http://tyrol:8081/nexus/content/repositories/snapshots"
                 "releases"  "http://tyrol:8081/nexus/content/repositories/releases"}
  )
