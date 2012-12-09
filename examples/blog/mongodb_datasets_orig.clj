
;; load the necessary Incanter libraries
(use '(incanter core stats charts io))

;; load Somnium's Congomongo library
(use '(incanter io mongodb))
(use 'somnium.congomongo)

;; read in some data, you provide the read-dataset function a string that is either a 
;; filename or a URL to the data

(def data (read-dataset "http://github.com/liebke/incanter/raw/master/data/cars.csv"
			:header true))


(use 'incanter.datasets)
(def data (get-dataset :cars))
(view data)

;; I used a URL to the sample data included with the Incanter distribution. I could have used
;; (incanter.datasets/get-dataset :cars) to retrieve the same data

;; I will use the new with-data macro and $ column-selector function to simplify access
;; to the dataset's columns. Within the with-data macro, columns of the bound dataset
;; can be accessed, by name, using the $ function, e.g. ($ colname). Columns can
;; also be accessed using the column index for both datasets and matrices, ($ 0).

;; The following code will create a scatter plot of the data (speed vs. dist),
;; and then add a regression line using the fitted values returned from the linear-model
;; function.

(column-names data)

(with-data data
  (def lm (linear-model ($ :dist) ($ :speed)))
  (doto (scatter-plot ($ :speed) ($ :dist))
    view
    (add-lines ($ :speed) (:fitted lm))))

;; We can add the fitted (or predicted values) to the data using the conj-cols
;; function.

(def data (conj-cols data (:fitted lm)))

(view data)

;; Notice that the column names are changed, this is done to prevent naming conflicts
;; when merging datasets. We can add more meaningful names with the column-names
;; function

(def data (column-names data [:speed :dist :predicted-dist]))

;; We can use the -> macro to perform both steps.
(view data)

(with-data data
   
  (def lm (linear-model ($ :dist) ($ :speed)))
   
  (doto (scatter-plot ($ :speed) ($ :dist))
           (add-lines ($ :speed) (:fitted lm))
	   view)

  (view (scatter-plot ($ :speed) (:residuals lm))) 

  ;; let's append the fitted values and residuals, from the regression, to the original dataset
  (def results (-> $data 
	                   (conj-cols (:fitted lm) (:residuals lm))
		           (column-names [:speed :dist :predicted-dist :residuals])))
  (view results)

  ;; get the mean speed of the observations that have residuals between -10 and 10.
  (mean ($ :speed ($where {:residuals {:$gt -10 :$lt 10}} results))) ; =14.32

  (mean ($ :speed (-> ($where {:speed {:$lt 10}}) 
	                          (conj-rows ($where {:speed {:$gt 20}})))))

  ;; Now connect to MongoDB. If mydb doesn't exist, it will be created.
  (mongo! :db "mydb")
  (insert-dataset :data results))


(def results (fetch-dataset :data))
(view results)
(with-data (sel results :rows (range 50))
  ($ :residuals))

;; mean speed for observations with residuals greater than 10 or less than -10
(with-data data
  (mean ($ :speed (-> ($where {:speed {:$lt 10}}) 
	                          (conj-rows ($where {:speed {:$gt 20}}))))))

;; Note: congomongo doesn't seem to work with 'lein swank', 
;; it throws a clojure.contrib.json error. It does work with 'lein repl'.

;; Now read a data set from the Web.
(def data (read-dataset "http://github.com/liebke/incanter/raw/master/data/airline_passengers.csv"
			             :header true))
(view data)


;; Here's a quick start guide: http://www.mongodb.org/display/DOCS/Quickstart
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

;; or use the $ column selector function
(view ($ [:NAME :POPESTIMATE200] new-census))

;; select the region names and population estimates for 2009 from the dataset
(def region-names (sel new-census :cols :NAME))
(def popest-2009 (sel new-census :cols :POPESTIMATE2009))

;; and view a bar-chart
(view (bar-chart region-names popest-2009))

;; or use the with-data macro
(with-data new-census
  (view (bar-chart ($ :NAME) ($ :POPESTIMATE2009)))
  ;; drop the first five values from each, since they are aggregate data
  (view (bar-chart (drop 5 ($ :NAME)) (drop 5 ($ :POPESTIMATE)))))

;; the first 5 values were aggregate data, so let's drop them and 
;; create a new bar-chart.
(view (bar-chart (drop 5 region-names) (drop 5 popest-2009)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
 (use '(incanter core stats datasets))

(def cars (get-dataset :cars))
($where {"speed" 10} cars)
($where {:speed 10} cars)

(with-data (get-dataset :cars)
  (view $data)
  (view ($where {"speed" {:$gt -10 :$lt 10}}))    
  (view ($where {"dist" {:$in #{10 12 16}}})))

(with-data (get-dataset :cars)
  (view $data)
  (view ($where {:speed {:$gt -10 :$lt 10}}))    
  (view ($where {:dist {:$in #{10 12 16}}})))


(with-data (get-dataset :cars)
 (view ($ :dist 
	      (conj-rows ($where {:speed {:$lt 10}})
				($where {:speed {:$gt 20}})))))

(with-data (get-dataset :cars)
 (view (conj-rows ($where {:speed {:$lt 10}})
		            ($where {:speed {:$gt 20}}))))


(use '(incanter core stats charts))


(def data (-> (conj-cols (sample-normal 50000) 
			 (sample-normal 50000)
			 (sample-uniform 50000 
					 :integers 
					 true 
					 :max 100))
	             (column-names [:x1 :x2 :y])))


(with-data ($where {:x1 {:$gt 2} :y {:$in #{1 50 100}}} data)
  (view $data)
  (view (scatter-plot ($ :x1) ($ :x2)))
  (nrow $data))

(use '(incanter core datasets charts stats))

(with-data (get-dataset :cars) 
  ($where {:speed {:$fn #(> (log %) 3)}}))


(view (query-dataset cars 
		     (fn [row] 
		       (> (/ (row "speed") (row "dist")) 
			  1/2))))




(use '(incanter core datasets))
(def cars (get-dataset :cars))

($map (fn [s] (/ s)) "speed" cars)
($map (fn [s d] (/ s d)) ["speed" "dist"] cars)

($map (fn [s] (/ s)) :speed cars)
($map (fn [s d] (/ s d)) [:speed :dist] cars)

(with-data (get-dataset :cars)
  (view $data)
  (view ($map (fn [s] (/ s)) :speed))
  (view ($map (fn [s d] (/ s d)) [:speed :dist])))


(use '(incanter core stats charts datasets))
  
(with-data  (get-dataset :cars)
  (def lm (linear-model ($ :dist) ($ :speed)))
  (doto (scatter-plot ($ :speed) ($ :dist))
    (add-lines ($ :speed) (:fitted lm))
    view))

;; create a dataset where :speed greater than 10 or less than -10
(with-data (get-dataset :cars)
  (view (-> ($where {:speed {:$gt 20}}) 
	    (conj-rows ($where {:speed {:$lt 10}})))))


(to-map (get-dataset :cars))

(to-map (matrix (range 9) 3))


(use '(incanter core datasets))

(def cars (get-dataset :cars))
($where {:speed 10} cars)

    ;; use the with-data macro and the one arg version of $where
(with-data cars
  (view ($where {:speed {:$gt -10 :$lt 10}}))     
  (view ($where {:dist {:$in #{10 12 16}}}))
  (view ($where {:dist {:$nin #{10 12 16}}})))

    ;; create a dataset where :speed greater than 10 or less than -10
(with-data (get-dataset :cars)
  (view (-> ($where {:speed {:$gt 20}}) 
	    (conj-rows ($where {:speed {:$lt 10}})))))
       

(def data (get-dataset :cars))
(col-names data)

(def renamed-data (col-names data [:x1 :x2]))
(col-names renamed-data)

 