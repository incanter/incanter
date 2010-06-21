(ns incanter.main
  (:require org.dipert.swingrepl.main)
  (:gen-class))

(defn -main
  [& args]
  (org.dipert.swingrepl.main/make-repl-jframe 
    {:on-close javax.swing.JFrame/EXIT_ON_CLOSE}))  
 
