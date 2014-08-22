(defproject incanter/incanter-charts "1.5.6-SNAPSHOT"
  :description "Incanter-charts is the JFreeChart module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-charts"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.5.6-SNAPSHOT"]
                 [incanter/incanter-io "1.5.6-SNAPSHOT"]
                 [incanter/jgui "1.5.6-SNAPSHOT"]
                 [incanter/jfreechart "1.0.13-no-gnujaxp"]
                 [clj-time "0.6.0" :exclusions [org.clojure/clojure]]]
  )
