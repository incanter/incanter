(defproject incanter/incanter-excel "1.9.0"
  :description "Incanter-excel provides access to reading and writing Excel files."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-excel"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.9.0"]
                 [org.clojure/clojure "1.6.0"]
                 [org.apache.poi/poi-ooxml "3.9"]]
  )
