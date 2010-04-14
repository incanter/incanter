;;; io.clj -- Data I/O library for Clojure built on CSVReader

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 11, 2009: First version


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DATA IO FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns 
    #^{:doc 
       "
lib for handy io goodness.

-ability to pretty print reports of various models
-read in clojure and json literals in different ways; files, classpath resources, etc.
-support for csv and sql


http://stackoverflow.com/questions/613929/how-do-i-connect-to-a-mysql-database-from-clojure

;;example sql transformation

 (def stuff (sql-select 
  (sql-unique \"something\") 
  (sql-where (str \"something = \"something\"))
  (sql-from \"mytable\"))
  (sql-order-by \"foo, bar\")))

 (defn transform-query [t q] #(with-query-results res [(q %)] (t res)))

 (defn sql-transformer [key-query
		       transform
		       view-query
		       output-file]
  (with-data key-query
    #(with-out-writer output-file
        (dorun (do-for :something % (transform-query transform view-query))))))

 (defn sql-to-hadoop [transform]
  (sql-transformer stuff transform myview \"/target/dir/preprocessed.pre\"))

 (defn transform-dates [] (sql-to-hadoop #(binding [*print-dup* true] (prn (preprocess %)))))
"}

incanter.io
  ;(:gen-class)
  (:import (java.io FileReader FileWriter File)
           (au.com.bytecode.opencsv CSVReader))
  (:use [incanter.core :only (dataset save)])
  (:use [org.danlarkin.json 
	 :only [decode-from-reader decode-from-str encode-to-str]])
  (:use [clojure.contrib.duck-streams :only [reader read-lines spit]])
  ;(:use [incanter.chrono :only [joda-date]])
  (:use [clojure.contrib.pprint :only [pprint]])
  (:use [incanter.classification :only [model-from-maps]])
  (:use [incanter.transformations :only [sort-map-of-maps all-keys]])
  (:use [clojure.contrib.java-utils :only [file]])
  (:use [clojure.contrib.sql 
	 :only [with-connection with-query-results]])
  (:use [clojure.contrib.str-utils :only [str-join]])
  (:use [incanter.core :only (dataset save)]))

(defn- parse-string [value]
  (try (Integer/parseInt value)
    (catch NumberFormatException _
      (try (Double/parseDouble value)
        (catch NumberFormatException _ value)))))


(defn- get-input-reader [location] 
  (try
    (java.io.InputStreamReader. (.openStream (java.net.URL. location)))
  (catch java.net.MalformedURLException _
    (java.io.FileReader. location))))




(defn read-dataset
  "
    Returns a dataset read from a file or a URL.

    Options:
      :delim (default \\,), other options (\\tab \\space \\|  etc)
      :quote (default \\\") character used for quoting strings
      :skip (default 0) the number of lines to skip at the top of the file.
      :header (default false) indicates the file has a header line
      :compress-delim (default true if delim = \\space, false otherwise) means
                      compress multiple adjacent delimiters into a single delimiter
  "
  ([filename & options]
   (let [opts (when options (apply assoc {} options))
         delim (or (:delim opts) \,) ; comma delim default
         quote-char (or (:quote opts) \")
         skip (or (:skip opts) 0)
         header? (or (:header opts) false)
         compress-delim? (or (:compress-delim opts)
                             (if (= delim \space) true false))]
     (with-open [reader #^CSVReader (CSVReader.
                    (get-input-reader filename)
                    delim
                    quote-char
                    skip)]
       (let [data-lines (map seq (seq (.readAll reader)))
             raw-data (filter #(> (count (filter (fn [field] (not= field "")) %)) 0) 
			      (if compress-delim? 
                                (map (fn [line] (filter #(not= % "") line)) data-lines)
                                data-lines))
             parsed-data (into [] (map (fn [row] (into [] (map parse-string row))) 
				       raw-data))
            ]
    (if header?
      ; have header row
      (dataset (first parsed-data) (rest parsed-data))
      ; no header row so build a default one
      (let [col-count (count (first parsed-data))
            col-names (apply vector (map str 
					 (repeat col-count "col") 
					 (iterate inc 0)))]
        (dataset col-names parsed-data))))))))




(defmethod save incanter.Matrix [mat filename & options]
  (let [opts (when options (apply assoc {} options))
        delim (or (:delim opts) \,)
        header (or (:header opts) nil)
        append? (if (true? (:append opts)) true false)
        file-writer (java.io.FileWriter. filename append?)]
    (do
      (when (and header (not append?))
        (.write file-writer (str (first header)))
        (doseq [column-name (rest header)]
          (.write file-writer (str delim column-name)))
        (.write file-writer (str \newline)))
      (doseq [row mat]
        (if (number? row)
          (.write file-writer (str row \newline))
          (do
            (.write file-writer (str (first row)))
            (doseq [column (rest row)]
              (.write file-writer (str delim column)))
            (.write file-writer (str \newline)))))
      (.flush file-writer)
      (.close file-writer))))




(defmethod save :incanter.core/dataset [dataset filename & options]
  (let [opts (when options (apply assoc {} options))
        delim (or (:delim opts) \,)
        header (or (:header opts) (map #(if (keyword? %) (name %) %) (:column-names dataset)))
        append? (if (true? (:append opts)) true false)
        file-writer (java.io.FileWriter. filename append?)
        rows (:rows dataset)
        columns (:column-names dataset)]
    (do
      (when (and header (not append?))
        (.write file-writer (str (first header)))
        (doseq [column-name (rest header)]
          (.write file-writer (str delim column-name)))
        (.write file-writer (str \newline)))
      (doseq [row rows]
          (do
            (.write file-writer (str (row (first columns))))
            (doseq [column-name (rest columns)]
              (.write file-writer (str delim (row column-name))))
            (.write file-writer (str \newline))))
      (.flush file-writer)
      (.close file-writer))))



(defmethod save java.awt.image.BufferedImage
  ([img filename & options]
     (javax.imageio.ImageIO/write img 
				  "png" 
				  (.getAbsoluteFile (java.io.File. filename)))))





(defn read-map 
[& keys] 
  (into {} (for [k keys] [k (comp eval read-string)]))) 

;(defn string-date-read-map 
;[& keys] 
  ;(into {} (for [k keys] [k joda-date]))) 

(defn read-json-file 
""
[f] 
  (decode-from-reader (reader f)))

;;todo if we want this process to be lazy we can remove the doall.
(defn read-json-lines 
[f] 
  (doall 
   (for [l (read-lines f)] (decode-from-str l))))

;;TODO: switch back to stream impl? 
(defn clj-to-json-file 
""
[c f] 
  (spit (File. f) 
      (encode-to-str c)))

;;doesn't work in maven builds.  must use fn below.
;;(ClassLoader/getSystemResource f)))))
(defn load-resource 
""
[f]
  (.getResourceAsStream 
   (.getClassLoader 
    (class *ns*)) f)) 

(defn read-from-classpath 
""
[f]
 (reader (load-resource f)))

(defn json-from-classpath 
""
[f]
  (decode-from-reader (read-from-classpath f)))


(def report-model (comp pprint sort-map-of-maps model-from-maps))

(defn package-model 
[file prob-map-tuple]
  (clj-to-json-file (model-from-maps prob-map-tuple) file))

(defn unpackage-model 
[file]
  (read-json-file file))

(defn into-file 
""
[filename stuff]
  (let [f (file filename)] 
   (spit f stuff)))

(defn csv-line 
 "turn a vector into a csv line"
[v]
  (let [commas (repeat (- (count v) 1) ", ")
	;;the seperated list must be a vector so that conj appends
	;;conj prepends for list type.
	seperated (into [] (interleave v commas))
	tail (last v)
	cells (conj seperated (str tail "\n"))]
    (apply str cells)))

(defn csv-table 
  "turn a 2-level map into a csv table"
[m]
  (let [column-names (all-keys (vals m))
	rows (for [[k v] m]
	       (cons k
		     (for [name column-names] (if-let [val (v name)] val 0))))
	table (cons (cons "" column-names) rows)]
  (apply str 
	  (map csv-line table))))



(defn with-mysql-results
"
takes dbinfo, query and a fn and applys the fn to query results.

example dbinfo:

 {:host \"localhost\"
 :port 3306
 :name \"testimport\"
 :classname \"com.mysql.jdbc.Driver\"
 :subprotocol \"mysql\"
 :user \"root\"
 :password \"12345\"}
"
 [dbinfo query f]
  (let [db (merge dbinfo {:subname (str "//" (:host dbinfo) 
			 ":" (:port dbinfo) 
			 "/" (:name dbinfo))})]
    (with-connection db
      (with-query-results rs [query] (f rs)))))

(defn sql-query 
""
[d q] 
  (let [printer #(println (:internaluage :iso_code %))]
  (with-mysql-results d q 
    #(dorun (map printer %)))))

(defn query 
""
[table sample & columns] 
  (str "select " (str-join ", " columns) 
       " from " table 
       " limit " sample))

(defn sql-select 
""
[& x] 
  (str-join " " (cons "select" x)))

(def sql-from #(str "from " %))
(def sql-unique #(str "distinct " %))
(def sql-limit #(str "limit " %))
(def random-row "order by rand()")

(defn sql-order-by 
""
[c] 
  (str "order by " c))

(defn sql-where 
""
[pred] 
  (str "where " pred))

(defn columns 
""
[& x] 
  (str-join ", " x))
