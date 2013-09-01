(defproject incanter "1.5.3"
  :description "Incanter is a Clojure-based, R-like statistical programming and data visualization environment."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/liebke/incanter"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.5.3"]
                 [incanter/incanter-io "1.5.3"]
                 [incanter/incanter-charts "1.5.3"]
                 [incanter/incanter-mongodb "1.5.3"]
                 [incanter/incanter-pdf "1.5.3"]
                 [incanter/incanter-svg "1.5.3"]
                 [incanter/incanter-latex "1.5.3"]
                 [incanter/incanter-excel "1.5.3"]
                 [incanter/incanter-sql "1.5.3"]
                 [incanter/incanter-zoo "1.5.3"]
                 [org.clojure/clojure "1.5.1"]
                 [swingrepl "1.3.0"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [jline/jline "2.11"]]
  :main incanter.main
  :profiles {:dev {:resource-paths ["data"]}
             :debug {:debug true}
             }  
  :repl-options {:init-ns incanter.main
                 :init (do
                         (set! *print-length* 500)
                         (use 'clojure.repl))
                 }
  :jvm-opts ["-Xmx1g"]

  )
