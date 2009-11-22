;; from the following blog post
;; http://incanter-blog.org/2009/06/13/plotting-with-non-numeric-data/
;; plotting categorical data

(use '(incanter core charts datasets))

;; bar-charts
(view (bar-chart ["a" "b" "c" "d" "e"] [10 20 30 25 20]))

(view (bar-chart ["a" "a" "b" "b" "c" "c" ] [10 20 30 10 40 20]
                     :legend true 
                     :group-by ["I" "II" "I" "II" "I" "II"]))

(view (line-chart ["a" "b" "c" "d" "e"] [20 10 30 25 40]))


(def data (get-dataset :airline-passengers))
(def by-year (group-by data 0))
(view (bar-chart (sel (last by-year) :cols 2) 
                 (sel (last by-year) :cols 1)
                 :title "Airline Travel in 1960"
                 :y-label "Passengers"
                 :x-label "Month"))


;; line-charts
(view (line-chart (sel (last by-year) :cols 2) 
                 (sel (last by-year) :cols 1)
                 :title "Airline Travel in 1960"
                 :y-label "Passengers"
                 :x-label "Month"))

(view (line-chart (sel data :cols 2) 
                 (sel data :cols 1)
                 :group-by (sel data :cols 0)
                 :title "Airline Travel in 1949-1960"
                 :legend true
                 :y-label "Passengers"
                 :x-label "Month"))


;; more bar-charts
(view (bar-chart (sel data :cols 2) 
                 (sel data :cols 1)
                 :group-by (sel data :cols 0)
                 :title "Airline Travel in 1949-1960"
                 :legend true
                 :y-label "Passengers"
                 :x-label "Year"))

(view (bar-chart (sel data :cols 0) 
                 (sel data :cols 1)
                 :group-by (sel data :cols 2)
                 :title "Airline Travel in 1949-1960"
                 :legend true
                 :y-label "Passengers"
                 :x-label "Year")
       :width 525)

