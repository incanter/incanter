(defproject incanter/incanter-io "1.5.7"
  :description "Incanter-io is the I/O module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-io"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.5.7"]
                 [org.clojure/clojure "1.5.1"]
                 ;; TODO: switch to data.csv?
                 [net.sf.opencsv/opencsv "2.3"]
                 ;; TODO: switch to data.json?
                 [org.danlarkin/clojure-json "1.1"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  )
