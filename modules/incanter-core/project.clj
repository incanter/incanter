(defproject incanter/incanter-core "1.4.0"
  :description "Incanter-core is the core module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/liebke/incanter"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/math.combinatorics "0.0.3"
                  :exclusions [org.clojure/clojure]]
                 [net.sourceforge.parallelcolt/parallelcolt "0.10.0"]]
  :java-source-paths ["java"]
  )
