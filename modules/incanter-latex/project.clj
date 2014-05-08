(defproject incanter/incanter-latex "1.5.6-SNAPSHOT"
  :description "Library for rendering LaTeX math equations using the jLateXMath library."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-latex"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-charts "1.5.6-SNAPSHOT"]
                 [org.clojure/clojure "1.5.1"]
                 [org.scilab.forge/jlatexmath "0.9.6"]]
  )
