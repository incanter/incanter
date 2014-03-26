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

(defn parse-string [value & [empty-value]]
  (if (= value "")
    empty-value
    (if (re-matches #"\d+" value)
      (try (Long/parseLong value)
           (catch NumberFormatException _ value))
      (try (Double/parseDouble value)
           (catch NumberFormatException _ value)))))

(defn missing-value?
  "Returns true if s is nil or a string that contains only whitespace."
  [s]
  (if (or (string? s) (nil? s))
    (clojure.string/blank? s)))

(defn remove-blank-lines
  "Removes empty lines from coll."
  [coll]
  (remove (partial every? missing-value?) coll))

(defn read-dataset
  "
    Returns a dataset read from a file or a URL.

    Options:
      :delim (default \\,), other options (\\tab \\space \\|  etc)
      :quote (default \\\") character used for quoting strings
      :header (default false) indicates the file has a header line
      :skip-blank-lines (default true) if true, blank lines are ignored
      :header-fn applied to each header value in the dataset, defaults to keyword. Only applied if :header is true
      :row-fn (default identity) applied to each row before constructing dataset.
      :dataset-fn (default doall) applied to entire sequence of rows before constructing dataset.
  "

  [filename & {:keys [delim quote header skip-blank-lines compress-delim empty-field-value header-fn row-fn dataset-fn]
               :or {delim \, quote \" header false skip-blank-lines true header-fn keyword}}]
  (with-open [reader (io/reader filename)]
    (let [lines (csv/read-csv reader
                              :separator delim
                              :quote quote)
          skip-blank-lines (when skip-blank-lines remove-blank-lines)
          dataset-fns (conj [dataset-fn] skip-blank-lines)
          fns (fn [r] ((apply comp (remove nil? dataset-fns)) r))
          do-fns (fn [rows] (doall (fns
                                    (if row-fn
                                      (map row-fn rows)
                                      rows))))]
      (if header
        (dataset (map header-fn (first lines)) (do-fns (rest lines)))
        (incanter.core/to-dataset (do-fns lines))))))

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
