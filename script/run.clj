(when-let [run-swank (System/getenv "LABREPL_SWANK")]
  (println "Starting swank...")
  ;; Drop the enclosing double quotes from the environment variable and eval it.
  (load-string (if (re-find #"^\".*\"$" run-swank)
                 (->> run-swank (drop 1) (butlast) (apply str))
                 run-swank)))

(require 'clojure.contrib.repl-utils)
(set! *print-length* 500)
(clojure.contrib.repl-utils/add-break-thread!)
