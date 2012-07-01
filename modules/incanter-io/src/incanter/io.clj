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
    ^{:doc 
       "Library for reading and writing Incanter datasets and matrices."}

incanter.io
  (:import (java.io FileReader FileWriter File)
           (au.com.bytecode.opencsv CSVReader))
  (:use [incanter.core :only (dataset save get-input-reader)]))

(defn- parse-string [value]
  (if (re-matches #"\d+" value)
    (try (Long/parseLong value)
         (catch NumberFormatException _ value))
    (try (Double/parseDouble value)
         (catch NumberFormatException _ value))))


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
  ([filename & {:keys [delim keyword-headers quote skip header compress-delim]
                :or {delim \,
                     quote \"
                     skip 0
                     header false
                     keyword-headers true}}]
     (let [compress-delim? (or compress-delim (if (= delim \space) true false))]
     (with-open [reader ^CSVReader (CSVReader.
				    (get-input-reader filename)
				    delim
				    quote
				    skip)]
       (let [data-lines (map seq (seq (.readAll reader)))
             raw-data (filter #(> (count (filter (fn [field] (not= field "")) %)) 0) 
                              (if compress-delim? 
                                (map (fn [line] (filter #(not= % "") line)) data-lines)
                                data-lines))
             parsed-data (into [] (map (fn [row] (into [] (map parse-string row))) 
                                       raw-data))]
    (if header
      ; have header row
      (dataset (if keyword-headers
                 (map keyword (first parsed-data))
                 (first parsed-data)) 
               (rest parsed-data))
      ; no header row so build a default one
      (let [col-count (count (first parsed-data))
            col-names (apply vector (map str 
                                         (repeat col-count "col") 
                                         (iterate inc 0)))]
        (dataset (if keyword-headers
                   (map keyword col-names) 
                   col-names) 
                 parsed-data))))))))




(defmethod save incanter.Matrix [mat filename & {:keys [delim header append]
                                                 :or {append false delim \,}}]
  (let [file-writer (java.io.FileWriter. filename append)]
    (do
      (when (and header (not append))
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




(defmethod save :incanter.core/dataset [dataset filename & {:keys [delim header append]
                                                            :or {append false delim \,}}]
  (let [header (or header (map #(if (keyword? %) (name %) %) (:column-names dataset)))
        file-writer (java.io.FileWriter. filename append)
        rows (:rows dataset)
        columns (:column-names dataset)]
    (do
      (when (and header (not append))
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
  ([img filename & {:keys [format] :or {format "png"}}]
     (javax.imageio.ImageIO/write img 
                                  format 
                                  (.getAbsoluteFile (java.io.File. filename)))))





