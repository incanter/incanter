(defproject incanter/incanter-core "1.3.0"
  :description "Incanter-core is the core module of the Incanter project."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/math.combinatorics "0.0.1"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]
                 [incanter/parallelcolt "0.9.4"
                  :exclusions [org.clojure/clojure
                               org.clojure/clojure-contrib]]]
  :dev-dependencies [[lein-clojars "0.7.0"
                      :exclusions [org.clojure/clojure
                                   org.clojure/clojure-contrib]]]
  :java-source-path "java"
  ;;; Set a custom repository because math.combinatorics isn't into
  ;;; Clojars or Maven central yet.
  :repositories {"snapshots" {:url "http://oss.sonatype.org/content/repositories/snapshots"}}
  )
