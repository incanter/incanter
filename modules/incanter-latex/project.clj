(defproject incanter/incanter-latex "1.2.2"
  :description "Library for rendering LaTeX math equations using the jLateXMath library."
  :dependencies [[incanter/incanter-charts "1.2.2"]
                 [net.sf.alxa/jlatexmath "0.9.1-SNAPSHOT"]]
  :dev-dependencies [[leiningen/lein-swank "1.1.0"]
                     [lein-clojars "0.5.0-SNAPSHOT"]]
  :repositories [["alxa-repo" "http://alxa.sourceforge.net/m2"]])
