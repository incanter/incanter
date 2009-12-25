
;(ns example.mongodb
;  (:use [[incanter core stats charts io mongodb]
;           [somnium congomongo]]))

(use '(incanter core stats charts io mongodb))
(use 'somnium.congomongo) ;; doesn't work with lein swank, only lein repl clojure.contrib.json error

;(mongo! :db "surveydb")
;(def survey (get-dataset :survey))
;(def colnames (:column-names survey))
;(mass-insert! :survey (:rows survey))


;(def data (read-dataset "http://github.com/liebke/incanter/raw/master/data/airline_passengers.csv"
;:header true))

(mongo! :db "mydb")
;(mass-insert! :airline-data (:rows data))

;(view data)


 (def new-data (fetch-dataset :airline-data))

 (def new-data (fetch-dataset :airline-data
			      :only [:year]))

 (def new-data (fetch-dataset :airline-data
			      :where {:month "Feb"}))

(view new-data)

(view (bar-chart (sel new-data :cols :year)
		  (sel new-data :cols :passengers)))



