(defproject incanter/incanter-core "1.5.5-SNAPSHOT"
  :description "Incanter-core is the core module of the Incanter project."
  :url "http://incanter.org/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git" :url "https://github.com/liebke/incanter"}
  :min-lein-version "2.0.0"
  :java-source-paths ["java"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/math.combinatorics "0.0.4"
                  :exclusions [org.clojure/clojure]]
		 [org.jblas/jblas "1.2.3"]
                 [net.mikera/vectorz-clj "0.25.0"]
                 [net.mikera/core.matrix "0.29.1"]
                 [clatrix "0.4.0"
                  :exclusions [org.clojure/clojure]]
                 [net.sourceforge.parallelcolt/parallelcolt "0.10.1"]])
