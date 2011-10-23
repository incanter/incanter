(ns
    ^ {:doc "SQL module for interacting with databases."}
    incanter.sql
  (:use
   [incanter.core :only [dataset]]
   clojure.contrib.sql
   [clojureql.core :as ql :exclude [conj! disj! distinct]]))

(defn window [psize wsize]
  (let [full-chunks (quot psize wsize)
        last-chunk  (rem psize wsize)]
    (for [x (range 0 (inc full-chunks))]
      {:offset (* wsize x) :window wsize})))

(defn- generate-population-query
  "Wrap the ClojureQL statement in a population query."
  [cql-statement]
  (ql/pick
   (ql/aggregate
    cql-statement
    [[:count/* :as :population]])
   :population))

(defn- read-dataframe
  "Read a single frame from the database table"
  [cql-statement window offset]
  (deref
   (if (pos? offset)
     (-> cql-statement
         (ql/take (+ window offset))
         (ql/drop offset))
     (-> cql-statement
         (ql/take window)))))

(defn read-dataset
  "Lazily read a dataset for the given ClojureQL query."
  [cql-statement]
  (let [lazy-data
        (lazy-seq
         (loop
             [[ch & ch-rest] (window (deref (generate-population-query cql-statement)) 16)
              data           (vec '())]
           (if (not ch)
             data
             (recur
              ch-rest
              (concat data (lazy-seq (read-dataframe cql-statement (:window ch) (:offset ch))))
              ))))]
    (dataset (keys (first lazy-data)) lazy-data)
    ))

(defn insert-dataset [dset table]
  (ql/conj! table (map #(zipmap (keys %) (vals %)) (:rows dset))))
