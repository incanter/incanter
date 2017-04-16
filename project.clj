(defproject incanter "1.5.8-SNAPSHOT"
  :description "Incanter is a Clojure-based, R-like statistical programming and data visualization environment."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/incanter/incanter"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.5.8-SNAPSHOT"]
                 [incanter/incanter-io "1.5.8-SNAPSHOT"]
                 [incanter/incanter-charts "1.5.8-SNAPSHOT"]
                 [incanter/incanter-mongodb "1.5.8-SNAPSHOT"]
                 [incanter/incanter-pdf "1.5.8-SNAPSHOT"]
                 [incanter/incanter-svg "1.5.8-SNAPSHOT"]
                 [incanter/incanter-latex "1.5.8-SNAPSHOT"]
                 [incanter/incanter-excel "1.5.8-SNAPSHOT"]
                 [incanter/incanter-sql "1.5.8-SNAPSHOT"]
                 [incanter/incanter-zoo "1.5.8-SNAPSHOT"]
                 [org.clojure/clojure "1.5.1"]
                 ]
  
  :plugins [[lein-sub "0.3.0"]]
  
  :sub ["modules/incanter-core"
        "modules/incanter-io"
        "modules/incanter-charts"
        "modules/incanter-mongodb"
        "modules/incanter-pdf"
        "modules/incanter-svg"
        "modules/incanter-latex"
        "modules/incanter-excel"
        "modules/incanter-sql"
        "modules/incanter-zoo"]
  
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
