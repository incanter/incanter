(defproject incanter/incanter-pdf "1.9.3-SNAPSHOT"
  :description "Incanter-pdf is the PDF module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/incanter/incanter"
        :dir "modules/incanter-pdf"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-charts "1.9.3-SNAPSHOT"]
                 [com.lowagie/itext "2.1.7"]]
  )
