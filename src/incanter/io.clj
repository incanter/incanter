;;; io.clj -- Data I/O library for Clojure built on CSVReader

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 11, 2009: First version


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DATA IO FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns incanter.io 
  (:import (java.io FileReader)
           (au.com.bytecode.opencsv CSVReader))
  (:use (incanter core) ))
        ;(clojure set)))


(defn- parse-string [value] 
  (try (Integer/parseInt value) 
    (catch NumberFormatException _ 
      (try (Double/parseDouble value)
        (catch NumberFormatException _ value)))))


(defn read-dataset 
  "
    Returns a dataset read from a file.

    Options:
      :delim (default \\space), other options (\\tab \\,  etc)
      :quote (default \\\") character used for quoting strings
      :skip (default 0) the number of lines to skip at the top of the file.
      :header (default false) indicates the file has a header line
  "
  ([filename & options] 
   (let [opts (if options (apply assoc {} options) nil)
         delim (if (:delim opts) (:delim opts) \space) ; space delim default
         quote-char (if (:quote opts) (:quote opts) \")
         skip (if (:skip opts) (:skip opts) 0)
         header? (if (:header opts) (:header opts) false)
         reader (au.com.bytecode.opencsv.CSVReader. 
                    (java.io.FileReader. filename) 
                    delim
                    quote-char
                    skip)
         data-lines (map seq (seq (.readAll reader)))
         raw-data (filter #(> (count %) 0) (map (fn [line] (filter #(not= % "") line)) data-lines))
         parsed-data (into [] (map (fn [row] (into [] (map #(parse-string %) row))) raw-data))
       ]
    (if header? (dataset (first parsed-data) (rest parsed-data) (dataset parsed-data))))))
  


(defmulti save 
" Save is a multi-function that is used to write matrices, datasets and 
  charts to a file.

  Arguments:
    obj -- is a matrix, dataset, or chart object
    filename -- the filename to create.

  Matrix and dataset options:
    :delim (default \\space) column delimiter 
    :header (default nil) an sequence of strings to be used as header line,
        for matrices the default value is nil, for datasets, the default is
        the dataset's column-names array.
    :append (default false) determines whether this given file should be 
        appended to. If true, a header will not be written to the file again.

  Chart options:
    :width (default 500)
    :height (default 400)


  Matrix Examples:

    (use '(incanter core io))
    (def A (matrix (range 12) 3)) ; creates a 3x4 matrix
    (save A \"A.dat\") ; writes A to the file A.dat, with no header and space delimited
   
    ;; writes A to the file A.dat, with a header and comma delimited
    (save A \"A.dat\" :delim \\, :header [\"col1\" \"col2\" \"col3\"]) 


  Dataset Example:

    (use '(incanter core io datasets))
    ;; read the iris sample dataset, and save it to a file.
    (def iris (get-dataset :iris))
    (save iris \"iris.dat\" :delim \\,)


  Chart Example:

    (use '(incanter core io stats charts))
    (save (histogram (sample-normal 1000)) \"hist.png\")
  

"
  (fn [obj filename & options] (type obj)))



(defmethod save incanter.Matrix [mat filename & options]
  (let [opts (if options (apply assoc {} options) nil)
        delim (if (:delim opts) (:delim opts) \space) 
        header (if (:header opts) (:header opts) nil) 
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
  (let [opts (if options (apply assoc {} options) nil)
        delim (if (:delim opts) (:delim opts) \space) 
        header (if (:header opts) (:header opts) (:column-names dataset))
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
    
