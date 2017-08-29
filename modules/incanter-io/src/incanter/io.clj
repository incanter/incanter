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

(ns ^{:doc "Library for reading and writing Incanter datasets and matrices."}
  incanter.io
  (:import (java.io FileReader FileWriter File)
           (au.com.bytecode.opencsv CSVReader))
  (:use [incanter.core :only (dataset save)])
  (:require [clojure.java.io :as io]
            [clojure.core.matrix :as m]
            [clojure.core.matrix.dataset :as ds]))

(defn- parse-string [value & [empty-value]]
  (if (= value "")
    empty-value
    (if (re-matches #"\d+" value)
      (try (Long/parseLong value)
           (catch NumberFormatException _ value))
      (try (Double/parseDouble value)
           (catch NumberFormatException _ value)))))

(def vector-constructors {
  Boolean boolean-array,
  Byte byte-array,
  ; Char char-array,
  Double double-array,
  Float float-array,
  ; Int int-array,
  Long long-array,
  Object object-array,
  Short short-array,
})

(def parsers {Integer #(Integer/parseInt %)
              Long    #(Long/parseLong %)
              Float   #(Float/parseFloat %)
              Double  #(Double/parseDouble %)
              String  identity })

(defn reverse-type-mapping [[typ columns]]
  (if (instance? String columns)
    {columns typ}
    (reduce conj (map #(hash-map % typ) columns))))

(defn reverse-types [types]
  (reduce conj (map reverse-type-mapping (seq types))))

(defn make-typed-parse-row [column-names types default-type empty-field-value transformers]
  (let [column-to-type (reverse-types types)
        get-type (fn [name] (or (get column-to-type name) default-type))
        column-types (map get-type column-names)
        field-transformers (map #(or (get transformers %) identity) column-names)
        field-parsers (map #(get parsers %) column-types)
        number-of-columns (count column-names)
        common-type (when (apply = column-types)
                      (first column-types))
        row-vector (if common-type
                     (fn [row] (apply (get vector-constructors common-type) [number-of-columns row]))
                     (fn [row] (vec row)))]
    (fn [row]
      (row-vector (mapv (fn [[s parser column-name transformer]]
                          (if (= s "")
                            empty-field-value
                            (try (parser (transformer s))  
                              (catch Exception e
                              (throw (Exception.
                                (str "Parsing column " column-name ": '" s "' " (.getMessage e))))))))
                        (map vector row field-parsers column-names field-transformers))))))

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
    :comment-char (default nil) skip commented lines (\"#\", \"%\", \";\", etc)
    :default-type (default nil) default type of columns.
    :types (default nil) dictionary mapping types to list of column names, e.g:
           {Long [\"foo\" \"bar\"] Float \"boo\"}
    :transformers (default nil) dictionary mapping column names to functions that will transform
                  strings in a given column before they are converted to final types
    :max-rows (default nil) maximum rows to be read, nil means no limit
    :rename-columns (default nil) dictionary mapping column names on file to their names
                    after loading
  "

  [filename & {:keys [delim keyword-headers quote skip header compress-delim empty-field-value comment-char
                      default-type types transformers max-rows rename-columns]
               :or {delim \, quote \" skip 0 header false keyword-headers true
                    default-type nil types nil transformers nil max-rows nil rename-columns nil}}]

  (let [compress-delim? (or compress-delim (= delim \space))
        compress-delim-fn (if compress-delim?
                            (fn [line] (filter #(not= % "") line))
                            identity)
        comment-char-fn (fn [line]
                          (if (and (boolean (seq line)) comment-char)
                            (if (.startsWith (first line) comment-char)
                              '()
                              line)
                            line))
        remove-empty-fn #(when (some (fn [field] (not= field "")) %) %)
        [dataset-body column-count header-row row-number]
          (with-open [reader ^CSVReader (CSVReader. (io/reader filename) delim quote skip)]
            (let [header-row (when header (map (fn [name] (or (get rename-columns name) name))
                                               (.readNext reader)))
                  parse-data-fn (if (and header (or types default-type))
                                  (make-typed-parse-row header-row types default-type empty-field-value transformers)
                                  (fn [line] (vec (map #(parse-string % empty-field-value) line))))]
              (loop [lines [] max-column 0 row-number 0]
                (if-let [line (when (or (not max-rows) (< row-number max-rows))
                                (.readNext reader))]
                  (let [new-line (-> line
                                     compress-delim-fn
                                     comment-char-fn
                                     remove-empty-fn
                                     parse-data-fn)]
                    (recur
                      (if-not (empty? new-line) (conj lines new-line) lines)
                      (max max-column (count new-line))
                      (inc row-number)))
                [lines max-column header-row row-number]))))
        column-names-strs
          (map (fn [hr-entry idx]
                 (if hr-entry
                   (str hr-entry)
                   (str "col" idx)))
               (concat header-row (repeat nil))
               (range column-count))
        column-names (map (if keyword-headers keyword identity) column-names-strs)
        padded-body (map #(pad-vector % column-count empty-field-value)
                         dataset-body)]
    (dataset column-names padded-body)))

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
  (let [header (or header (map #(if (keyword? %) (name %) %) (ds/column-names dataset)))
        file-writer (if (= "-" filename)
                      *out*
                      (java.io.FileWriter. filename append))
        rows (m/rows dataset)
        columns (ds/column-names dataset)]
    (try
      (when (and header (not append))
        (.write file-writer (str (first header)))
        (doseq [column-name (rest header)]
          (.write file-writer (str delim column-name)))
        (.write file-writer (str \newline)))
      (doseq [row rows]
        (do
          (.write file-writer (str (first row)))
          (doseq [i (rest row)]
            (.write file-writer (str delim i)))
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
