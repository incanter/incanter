(ns ^{:doc "A simple library that provides functions for persisting
Incanter data structures using MongoDB.

Use incanter.mongodb in combination with the somnium.congomongo library.
For usage examples, see the Congomongo README at http://github.com/somnium/congomongo,
and the examples/blog/mongodb_datasets.clj file in the Incanter distribution.

Here are Somnium's descriptions of Congomongo's functions:

  (mongo! & args) : Creates a Mongo object and sets the default database.
     Keyword arguments include:
     :host -> defaults to localhost
     :port -> defaults to 27017
     :db   -> defaults to nil (you'll have to set it anyway, might as well do it now.)

  (get-coll coll) : Returns a DBCollection object

  (fetch coll & options) : Fetches objects from a collection. Optional arguments include
   :where  -> takes a query map
   :only   -> takes an array of keys to retrieve
   :as     -> what to return, defaults to :clojure, can also be :json or :mongo
   :from   -> argument type, same options as above
   :one?   -> defaults to false, use fetch-one as a shortcut
   :count? -> defaults to false, use fetch-count as a shortcut

  (fetch-one coll & options) : same as (fetch collection :one? true)

  (fetch-count coll & options) : same as (fetch collection :count? true)

  (insert! coll obj & options) : Inserts a map into collection. Will not overwrite existing maps.
   Takes optional from and to keyword arguments. To insert
   as a side-effect only specify :to as nil.

  (mass-insert! coll objs & options) : Inserts a sequence of maps.

  (update! coll old new & options) : Alters/inserts a map in a collection. Overwrites existing objects.
   The shortcut forms need a map with valid :_id and :_ns fields or
   a collection and a map with a valid :_id field.

  (destroy! coll query-map) : Removes map from collection. Takes a collection name and
    a query map

  (add-index! coll fields & options) : Adds an index on the collection for the specified fields if it does not exist.
    Options include:
    :unique -> defaults to false
    :force  -> defaults to true

  (drop-index! coll fields) : Drops an index on the collection for the specified fields

  (drop-all-indexes! coll) : Drops all indexes from a collection

  (get-indexes coll & options) : Get index information on collection

  (drop-database title) : drops a database from the mongo server

  (set-database title) : atomically alters the current database

  (databases) : List databases on the mongo server

  (collections) : Returns the set of collections stored in the current database

  (drop-collection coll) : Permanently deletes a collection. Use with care.

"
       :author "David Edgar Liebke"}

  incanter.mongodb
  (:require [clojure.core.matrix.dataset :as ds])
  (:use [incanter.core :only (dataset)]
        [somnium.congomongo :only (fetch mass-insert!)]))


(defn fetch-dataset
  "
  Queries a MongoDB database, accepting the same arguments as
  somnium.congomongo/fetch, but returning an Incanter dataset instead
  of a sequence of maps.

  Examples:

   (use '(incanter core datasets mongodb))
   (require '[clojure.core.matrix.dataset :as ds])
   (use 'somnium.congomongo)

   ;; first load some sample data
   (def data (get-dataset :airline-passengers))
   (view data)

   ;; a MongoDB server must be running on the localhost on the default port
   ;; for the following steps.

   (mongo! :db \"mydb\")
   (mass-insert! :airline-data (ds/row-maps data))

   ;; and then retrieve it
   ;; notice that the retrieved data set has two additional columns,  :_id :_ns
   (view (fetch-dataset :airline-data))

  "
  [& args]
    (let [results (apply fetch args)
          colnames (keys (first results))]
      (dataset colnames results)))



(defn insert-dataset
  "
  Inserts the rows of the Incanter dataset into the given MongoDB collection.

  Examples:

    (use '(incanter core datasets mongodb))
    (require '[somnium.congomongo :refer [mongo! mass-insert!]])

    (def data (get-dataset :airline-passengers))
    (view data)

    ;; a MongoDB server must be running on the localhost on the default port
    ;; for the following steps.

    (mongo! :db \"mydb\")
    (insert-dataset :airline-data data)

    ;; notice that the retrieved data set has two additional columns,  :_id :_ns
    (view (fetch-dataset :airline-data))
  "
  [mongodb-coll dataset]
  (mass-insert! mongodb-coll (into [] (ds/row-maps dataset))))
