(ns incanter.repl
  (:use [incanter core charts io datasets])
  (:require reply.main)
  (:gen-class)
  )

(defn -main [& args]
  (apply reply.main/-main args)
  )  

