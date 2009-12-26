
;(ns example.mongodb
;  (:use [[incanter core stats charts io mongodb]
;           [somnium congomongo]]))

(use '(incanter core stats charts io mongodb))
(use 'somnium.congomongo) ;; doesn't work with lein swank, only lein repl clojure.contrib.json error

;(mongo! :db "surveydb")
;(def survey (get-dataset :survey))
;(def colnames (:column-names survey))
;(mass-insert! :survey (:rows survey))


(def data (read-dataset "http://github.com/liebke/incanter/raw/master/data/airline_passengers.csv"
			             :header true))
(view data)

(def data2 (read-dataset "http://www.census.gov/popest/national/files/NST_EST2009_ALLDATA.csv"
			             :header true))
(view data2)



(mongo! :db "mydb")

(mass-insert! :airline-data (:rows data))


(def results (fetch :airline-data))
(def new-data (dataset [:year :month :passengers] results))
(view new-data)


(def new-data (fetch-dataset :airline-data))

(def new-data (fetch-dataset :airline-data
			      :only [:year]))

(def new-data (fetch-dataset :airline-data
			      :where {:month "Feb"}))

(view new-data)

(view (bar-chart (sel new-data :cols :year)
		  (sel new-data :cols :passengers)))



(insert-dataset :census-data data2)
(def new-census (fetch-dataset :census-data))
(def region-names (sel new-census :cols :NAME))
(def popest-2009 (sel new-census :cols :POPESTIMATE2009))
(view (bar-chart region-names popest-2009))

(view (bar-chart (drop 5 region-names) (drop 5 popest-2009)))

