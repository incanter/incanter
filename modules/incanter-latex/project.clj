(defproject incanter/incanter-latex "1.3.0-SNAPSHOT"
  :description "Library for rendering LaTeX math equations using the jLateXMath library."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-charts "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [net.sf.alxa/jlatexmath "0.9.1-SNAPSHOT"]]
  :dev-dependencies [[lein-clojars "0.7.0"
                      :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  :repositories {"alxa-repo" "http://alxa.sourceforge.net/m2"})
