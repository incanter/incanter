(defproject incanter/incanter-pdf "1.3.0-SNAPSHOT"
  :description "Incanter-pdf is the PDF module of the Incanter project."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [incanter/incanter-charts "1.3.0-SNAPSHOT"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [com.lowagie/itext "1.4"]]
  :dev-dependencies [[lein-clojars "0.7.0"
                      :exclusions [org.clojure/clojure
                                   org.clojure/clojure-contrib]]])
