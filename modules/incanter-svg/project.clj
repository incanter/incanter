(defproject incanter/incanter-svg "1.5.7"
  :description "Incanter-svg is the SVG module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-svg"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-charts "1.5.7"]
                 [org.clojure/clojure "1.5.1"]
                  [org.apache.xmlgraphics/batik-dom "1.7"]
                  [org.apache.xmlgraphics/batik-svggen "1.7"]
                  [org.apache.xmlgraphics/batik-awt-util "1.7"]
                  [org.apache.xmlgraphics/batik-util "1.7"]
                  [org.apache.xmlgraphics/batik-xml "1.7"]]
  )
