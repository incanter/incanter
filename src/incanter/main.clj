(ns incanter.main
  (:require [org.dipert.swingrepl.main]
            [clojure.repl :refer :all]
            [incanter.core :as core]
            [incanter.io :as io]
            [incanter.excel :as excel]
            [incanter.charts :as charts]
            [incanter.datasets :as datasets]
            )
  (:use [incanter core io excel charts datasets ])
  (:gen-class))

(defn -main
  [& args]
  (org.dipert.swingrepl.main/make-repl-jframe 
    {:on-close javax.swing.JFrame/EXIT_ON_CLOSE}))  

(comment

(+ 1 2)
(def in "D:/Downloads/test-in.xlsx")
(def out "D:/Downloads/test-out.xlsx")
(dir io)
(doc excel/read-xls)
(doc excel/save-xls)
(def ds (excel/read-xls in))
(type ds)
(core/view ds)
(excel/save-xls ds out)

)
