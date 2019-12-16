(defproject incanter/incanter-charts "1.9.4-SNAPSHOT"
  :description "Incanter-charts is the JFreeChart module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-charts"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-io "1.9.4-SNAPSHOT"]
                 [org.jfree/jfreechart "1.5.0"]
                 [clj-time "0.14.0" :exclusions [org.clojure/clojure]]]
  )
