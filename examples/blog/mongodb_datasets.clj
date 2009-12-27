
;; load the necessary Incanter libraries
(use '(incanter core stats charts io mongodb))

;; load Somnium's Congomongo library
(use 'somnium.congomongo) 

;; Note: congomongo doesn't seem to work with 'lein swank', 
;; it thows a clojure.contrib.json error. It does work with 'lein repl'.

;; Now read a data set from the Web.
(def data (read-dataset "http://github.com/liebke/incanter/raw/master/data/airline_passengers.csv"
			             :header true))
(view data)


;; Here's a quik start guide: http://www.mongodb.org/display/DOCS/Quickstart
;; Briefly, download and unpack MongoDB
;; run the following commands:
;; $ mkdir -p /data/db
;; $ ./mongodb-xxxxxxx/bin/mongod &

;; Now connect to MongoDB. If mydb doesn't exist, it will be created.
(mongo! :db "mydb")

;; You can insert an Incanter data set into MongoDB using either the mass-insert! function
(mass-insert! :airline-data (:rows data))

;; notice I pass the :rows element of the dataset map object.
;; Alternatively, you can use the incanter.mongodb/insert-dataset function

(insert-dataset :airline-data data)


;; To retrieve the entire data set from MongoDB, either use congomongo's fetch function:
(def results (fetch :airline-data))

;; and wrap a dataset around the results
(def new-data (dataset [:year :month :passengers] results))
(view new-data)

;; Or use incanter.mongodb/fetch-dataset function
(def new-data (fetch-dataset :airline-data))

;; You can pass all of fetch's options to fetch-dataset
(def new-data (fetch-dataset :airline-data
			      :only [:year]))

(def new-data (fetch-dataset :airline-data
			      :where {:month "Feb"}))

(view new-data)

;; now generate a bar-char of the February passenger travel over the years
(view (bar-chart (sel new-data :cols :year)
		  (sel new-data :cols :passengers)))



;; A second example using some census summary data

;; load the data from the Web
(def data2 (read-dataset "http://www.census.gov/popest/national/files/NST_EST2009_ALLDATA.csv"
			             :header true))

;; and insert it into MongoDB
(insert-dataset :census-data data2)

;; Fetch the data from Mongo (obviously this isn't necessary in this case,
;; since we have the original dataset already)

(def new-census (fetch-dataset :census-data))

;; view the region names and population estimates for 2009
(view (sel new-census :cols [:NAME :POPESTIMATE2009]))

;; select the region names and populatione estimates for 2009 from the dataset
(def region-names (sel new-census :cols :NAME))
(def popest-2009 (sel new-census :cols :POPESTIMATE2009))

;; and view a bar-chart
(view (bar-chart region-names popest-2009))

;; the first 5 values were aggregate data, so let's drop them and 
;; create a new bar-chart.
(view (bar-chart (drop 5 region-names) (drop 5 popest-2009)))



