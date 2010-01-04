
;; Basic dataset usage
(use '(incanter core stats charts io))

;; load a dataset from a URL pointing to the data. 
(def data 
  (read-dataset
    "http://github.com/liebke/incanter/raw/master/data/cars.csv"
     :header true))


;; the dataset could have been loaded with the incanter.datasets/get-dataset function
(use 'incanter.datasets)
(incanter.datasets/get-dataset :cars)

;; view the dimensions of the dataset
(dim data)

;; view the column names
(col-names data)

(with-data (get-dataset :cars)
  (view (conj-cols (range (nrow $data)) $data)))

;; plot a scatter plot of speed vs. distance and add a regression line
(with-data data
  (def lm (linear-model ($ :dist) ($ :speed)))
  (doto (scatter-plot ($ :speed) ($ :dist))
    (add-lines ($ :speed) (:fitted lm))
    view))


;; create a new dataset that includes the orig data and the 
;; fitted values from the liner-model function
(def results (conj-cols data (:fitted lm)))

;; give the new dataset meaningful column names
(def results (col-names data [:speed :dist :predicted-dist]))

;; do both steps at once with the -> macro, and also add the residuals to the new dataset
(def results (-> (conj-cols data (:fitted lm) (:residuals lm))
                 (col-names [:speed :dist :predicted :residuals])))


;; now use the $where function

($where {:speed 10} results)
($where {:speed {:$gt 10 :$lt 20}} results)
($where {:speed {:$in #{4 7 24 25}}} results)
($where {:speed {:$nin #{4 7 24 25}}} results)

(with-data results
  (mean ($ :speed ($where {:residuals {:$gt -10 :$lt 10}}))))


(with-data results 
  (conj-rows ($where {:speed {:$lt 10}})
             ($where {:speed {:$gt 20}})))

(with-data results
  ($where (fn [row] (or (< (:speed row) 10) 
			(> (:speed row) 20)))))


;; Now let's use MongoDB
(use 'somnium.congomongo)
(use 'incanter.mongodb)

;; connect to a MongoDB server running on the localhost on the default port.
(mongo! :db "mydb")

(insert-dataset :breaking-dists results)

(def breaking-dists (fetch-dataset :breaking-dists))
(col-names breaking-dists)
(view breaking-dists)

(insert-dataset :breaking-dists breaking-dists)
(view (fetch-dataset :breaking-dists))

;; use fetch-dataset's :where option to retrieve only the
;; rows where the speed is between 10 and 20 mph, and
;; then calculate the mean breaking distance.

(with-data (fetch-dataset :breaking-dists
			  :where {:speed {:$gt 10 :$lt 20}})
  (mean ($ :dist)))



(doc incanter.mongodb)


