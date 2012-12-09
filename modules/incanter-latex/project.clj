(defproject incanter/incanter-latex "1.4.1"
  :description "Library for rendering LaTeX math equations using the jLateXMath library."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/liebke/incanter"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-charts "1.4.0"]
                 [org.scilab.forge/jlatexmath "0.9.6"]]
  )
