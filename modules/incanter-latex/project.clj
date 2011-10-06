(defproject incanter/incanter-latex "1.2.4"
  :description "Library for rendering LaTeX math equations using the jLateXMath library."
  :dependencies [[incanter/incanter-charts "1.2.4"]
                 [incanter/incanter-core "1.2.4"]
                 [net.sf.alxa/jlatexmath "0.9.1-SNAPSHOT"]]
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT"]
                     [lein-clojars "0.6.0"]]
  :repositories {"alxa-repo" "http://alxa.sourceforge.net/m2"})
