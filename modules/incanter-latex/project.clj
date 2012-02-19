(defproject incanter/incanter-latex "1.3.0"
  :description "Library for rendering LaTeX math equations using the jLateXMath library."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-charts "1.3.0"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [org.scilab.forge/jlatexmath "0.9.6"]]
  :dev-dependencies [[lein-clojars "0.7.0"
                      :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]])
