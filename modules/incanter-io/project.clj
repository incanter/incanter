(defproject incanter/incanter-io "1.4.0"
  :description "Incanter-io is the I/O module of the Incanter project."
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.4.0"]
                 ;; TODO: switch to data.json?
                 [net.sf.opencsv/opencsv "2.3"]
                 ;; TODO: switch to data.json?
                 [org.danlarkin/clojure-json "1.1"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  )
