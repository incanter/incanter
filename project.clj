(defproject incanter "1.9.1"
  :description "Incanter is a Clojure-based, R-like statistical programming and data visualization environment."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/incanter/incanter"}
  :min-lein-version "2.0.0"
  :dependencies [[incanter/incanter-core "1.9.1"]
                 [incanter/incanter-io "1.9.1"]
                 [incanter/incanter-charts "1.9.1"]
                 [incanter/incanter-mongodb "1.9.1"]
                 [incanter/incanter-pdf "1.9.1"]
                 [incanter/incanter-svg "1.9.1"]
                 [incanter/incanter-latex "1.9.1"]
                 [incanter/incanter-excel "1.9.1"]
                 [incanter/incanter-sql "1.9.1"]
                 [incanter/incanter-zoo "1.9.1"]
                 [swingrepl "1.3.0"]
                 [org.clojure/clojure "1.8.0"]
                 ]
  
  :plugins [[lein-sub "0.3.0"]
            [lein-modules "0.3.8"]]
  
  :sub ["modules/incanter-core"
        "modules/incanter-io"
        "modules/incanter-charts"
        "modules/incanter-mongodb"
        "modules/incanter-pdf"
        "modules/incanter-svg"
        "modules/incanter-latex"
        "modules/incanter-excel"
        "modules/incanter-sql"
        "modules/incanter-zoo"
        "."]

  :modules {:dirs ["modules/incanter-core"
                   "modules/incanter-io"
                   "modules/incanter-charts"
                   "modules/incanter-mongodb"
                   "modules/incanter-pdf"
                   "modules/incanter-svg"
                   "modules/incanter-latex"
                   "modules/incanter-excel"
                   "modules/incanter-sql"
                   "modules/incanter-zoo"
                   "."]
            :subprocess false}
	  
  :profiles {:dev {:resource-paths ["data"]}
             :debug {:debug true}
             :uberjar {:aot :all
                       :main incanter.main
                       :dependencies [[reply "0.3.7" :exclusions [org.clojure/clojure]]
                                      [swingrepl "1.3.0"
                                       :exclusions [org.clojure/clojure org.clojure/clojure-contrib]]
                                      ]
                       }
             }  
  
  :main incanter.main
  
  :repl-options {:init-ns incanter.irepl
                 :resource-paths ["data"]
                 :init (do
                         (set! *print-length* 500)
                         (use 'clojure.repl))
                 }
  :jvm-opts ["-Xmx1g" "-Djsse.enableSNIExtension=false"
             ~(str "-Dincanter.home=" (System/getProperty "user.dir"))]
  )
