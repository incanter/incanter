(when-let [run-swank (System/getenv "LABREPL_SWANK")]
  (println "Starting swank...")
  ;; Drop the enclosing double quotes from the environment variable and eval it.
  (load-string (if-let [found (re-find #"^\"(.*)\"$" run-swank)]
                 (second found)
                 run-swank)))

(require 'clojure.contrib.repl-utils)
(use 'clojure.repl)
(set! *print-length* 500)
(clojure.contrib.repl-utils/add-break-thread!)

