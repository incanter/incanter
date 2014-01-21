;;; io.clj -- Data I/O library for Clojure built on data.csv

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

(ns ^{:doc "Library for reading and writing Incanter datasets and matrices."}
  incanter.io
  (:import (java.io FileWriter File))
  (:use [incanter.core :only (dataset save)])
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]))

(defn- parse-string [value & [empty-value]]
  (if (= value "")
    empty-value
    (if (re-matches #"\d+" value)
      (try (Long/parseLong value)
           (catch NumberFormatException _ value))
      (try (Double/parseDouble value)
           (catch NumberFormatException _ value)))))

(defn- pad-vector [v new-len value]
  (into v (repeat (- new-len (count v)) value)))

(defn read-dataset
  "
    Returns a dataset read from a file or a URL.

    Options:
      :delim (default \\,), other options (\\tab \\space \\|  etc)
      :quote (default \\\") character used for quoting strings
      :skip (default 0) the number of lines to skip at the top of the file.
      :header (default false) indicates the file has a header line
      :compress-delim (default true if delim = \\space, false otherwise) means
                      compress multiple adjacent delimiters into a single delimiter.
      :empty-field-value (default nil) indicates the interpretation of an empty field.
      :header-fn applied to each header value in the dataset, defaults to keyword. Only applied if :header is true.
  "

  [filename & {:keys [delim keyword-headers quote skip header compress-delim empty-field-value header-fn]
               :or {delim \, quote \" skip 0 header false keyword-headers true header-fn keyword}}]

  (let [compress-delim? (or compress-delim (= delim \space))
        compress-delim-fn (if compress-delim?
                            (fn [line] (filter #(not= % "") line))
                            identity)
        remove-empty-fn #(when (some (fn [field] (not= field "")) %) %)
        parse-data-fn (fn [line]
                        (vec (map #(parse-string % empty-field-value) line)))
        [parsed-data column-count]
        (with-open [reader (io/reader filename)]
            (let [lines (map #(-> %
                                    compress-delim-fn
                                    remove-empty-fn
                                    parse-data-fn
                                    ((fn [s] [s (count s)])))
                               (csv/read-csv reader :separator delim :quote quote))]
                [(filter seq (map first lines)) (apply max (map second lines))]))
        header-row (when header (map header-fn (first parsed-data)))
        dataset-body (if header (rest parsed-data) parsed-data)
        padded-body
          (if (not (nil? empty-field-value))
            (map #(pad-vector % column-count empty-field-value)
                 dataset-body)
            dataset-body)]
    (if header
      (dataset header-row padded-body)
      (incanter.core/to-dataset padded-body))))

(defmethod save :incanter.core/matrix [mat filename & {:keys [delim header append]
                                                       :or {append false delim \,}}]
  (let [file-writer (if (= "-" filename)
                      *out*
                      (java.io.FileWriter. filename append))]
    (try
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
      (finally
        (.flush file-writer)
        (when (= "-" filename)
            (.close file-writer))))))

(defmethod save :incanter.core/dataset [dataset filename & {:keys [delim header append]
                                                            :or {append false delim \,}}]
  (let [header (or header (map #(if (keyword? %) (name %) %) (:column-names dataset)))
        file-writer (if (= "-" filename)
                      *out*
                      (java.io.FileWriter. filename append))
        rows (:rows dataset)
        columns (:column-names dataset)]
    (try
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
      (finally
        (.flush file-writer)
        (when (= "-" filename)
          (.close file-writer))))))


(defmethod save java.awt.image.BufferedImage
  ([img filename & {:keys [format] :or {format "png"}}]
     (javax.imageio.ImageIO/write img 
                                  format 
                                  (.getAbsoluteFile (java.io.File. filename)))))





