(defproject incanter "1.5.6-SNAPSHOT"
  :description "Incanter is a Clojure-based, R-like statistical programming and data visualization environment."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/incanter/incanter"}
  :min-lein-version "2.0.0"
  ;; :uberjar-name "incanter.jar"
  :dependencies [[incanter/incanter-core "1.5.6-SNAPSHOT"]
                 [incanter/incanter-io "1.5.6-SNAPSHOT"]
                 [incanter/incanter-charts "1.5.6-SNAPSHOT"]
                 [incanter/incanter-mongodb "1.5.6-SNAPSHOT"]
                 [incanter/incanter-pdf "1.5.6-SNAPSHOT"]
                 [incanter/incanter-svg "1.5.6-SNAPSHOT"]
                 [incanter/incanter-latex "1.5.6-SNAPSHOT"]
                 [incanter/incanter-excel "1.5.6-SNAPSHOT"]
                 [incanter/incanter-sql "1.5.6-SNAPSHOT"]
                 [incanter/incanter-zoo "1.5.6-SNAPSHOT"]
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
  :sub ["modules/incanter-charts"
        "modules/incanter-core"
        "modules/incanter-excel"
        "modules/incanter-io"
        "modules/incanter-latex"
        "modules/incanter-mongodb"
        "modules/incanter-pdf"
        "modules/incanter-sql"
        "modules/incanter-svg"
        "modules/incanter-zoo"
        ]
  :plugins [[lein-sub "0.3.0"]]
  )
