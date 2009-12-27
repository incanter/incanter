(ns #^{:doc "A simple library that provides functions for persisting Incanter data structures using MongoDB.
            "
       :author "David Edgar Liebke"}

 incanter.mongodb
    (:use [incanter.core :only (dataset)]
             [somnium.congomongo :only (fetch mass-insert!)]))


(defn fetch-dataset
  "Queries a MongoDB database, accepting the same arguments as somnium.congomongo/fetch, but returning an Incanter dataset instead of a sequence of maps.

Examples:

   (use '(incanter core datasets mongodb)
   (use '(somnium.congomongo))

   ;; first load some sample data
   (def data (get-dataset :airline-passengers))
   (view data)

   ;; a MongoDB server must be running on the localhost on the default port
   ;; for the following steps.

   (mongo! :db \"mydb\")
   (mass-insert! :airline-data (:rows data))

   ;; and then retrieve it
   ;; notice that the retrieved data set has two additional columns,  :_id :_ns
   (view (fetch-dataset :airline-data))

"
  [& args]
    (let [results (apply fetch args)
	   colnames  (keys (first results))]
      (dataset colnames results)))



(defn insert-dataset
  "Inserts the rows of the Incanter dataset into the given MongoDB collection.

   Examples:

   (use '(incanter core datasets mongodb)
   (use '(somnium.congomongo))

   (def data (get-dataset :airline-passengers))
   (view data)

   ;; a MongoDB server must be running on the localhost on the default port
   ;; for the following steps.

   (mongo! :db \"mydb\")
   (mass-insert! :airline-data (:rows data))

   ;; notice that the retrieved data set has two additional columns,  :_id :_ns
   (view (fetch-dataset :airline-data))


"
  [mongodb-coll dataset]
    (mass-insert! mongodb-coll (:rows dataset)))
