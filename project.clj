(defproject incanter "1.5.5"
  :description "Incanter is a Clojure-based, R-like statistical programming and data visualization environment."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/incanter/incanter"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.5.5"]
                 [incanter/incanter-io "1.5.5"]
                 [incanter/incanter-charts "1.5.5"]
                 [incanter/incanter-mongodb "1.5.5"]
                 [incanter/incanter-pdf "1.5.5"]
                 [incanter/incanter-svg "1.5.5"]
                 [incanter/incanter-latex "1.5.5"]
                 [incanter/incanter-excel "1.5.5"]
                 [incanter/incanter-sql "1.5.5"]
                 [incanter/incanter-zoo "1.5.5"]
                 [org.clojure/clojure "1.5.1"]
                 ]
  :profiles {:dev {:resource-paths ["data"]}
             :debug {:debug true}
             :uberjar {:aot :all
                       :main incanter.main
                       :dependencies [[reply "0.3.0" :exclusions [org.clojure/clojure]]
                                      [swingrepl "1.3.0"
                                       :exclusions [org.clojure/clojure org.clojure/clojure-contrib]]
                                      ]
                       }
             }  
  :repl-options {:init-ns incanter.irepl
                 :resource-paths ["data"]
                 :init (do
                         (set! *print-length* 500)
                         (use 'clojure.repl))
                 }
  :jvm-opts ["-Xmx1g" "-Djsse.enableSNIExtension=false"
             ~(str "-Dincanter.home=" (System/getProperty "user.dir"))]
  )
