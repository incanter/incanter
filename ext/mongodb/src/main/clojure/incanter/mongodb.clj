(ns incanter.mongodb
    (:use [somnium.congomongo :only (fetch)]))


(defn fetch-dataset
  ""
  [& args]
    (let [results (apply fetch args)
	  colnames  (keys (first results))]
      (with-meta {:column-names colnames
		         :rows results}
	                 {:type :incanter.core/dataset})))
