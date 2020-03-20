(defproject incanter/incanter-io "1.9.4-SNAPSHOT"
  :description "Incanter-io is the I/O module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-io"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.9.4-SNAPSHOT"]
                 [org.clojure/data.csv "0.1.4"
                  :exclusions [org.clojure/clojure]]
                 ;; TODO: switch to data.json?
                 [org.danlarkin/clojure-json "1.1"
                  :exclusions [org.clojure/clojure org.clojure/clojure-contrib]]]
  :profiles {:dev {:dependencies [[clatrix "0.5.0" :exclusions [org.clojure/clojure net.mikera/core.matrix]]
                                  [org.jblas/jblas "1.2.3"]]}}
  )
