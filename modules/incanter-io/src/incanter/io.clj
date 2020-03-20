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
(:use [incanter.core :only (dataset save to-list)])
  (:require [clojure.java.io :as io]
            [clojure.core.matrix :as m]
            [clojure.core.matrix.dataset :as ds]
            [clojure.data.csv :as csv]))

(defn- parse-string [value & [empty-value]]
  (if (= value "")
    empty-value
    (if (re-matches #"-?\d+" value)
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
  (let [column-names (mapv str column-names) 
        column-to-type (reverse-types types)
        get-type (fn [name] (or (get column-to-type name) default-type))
        column-types (mapv get-type column-names)
        field-transformers (mapv #(or (get transformers %) identity) column-names)
        field-parsers (mapv #(get parsers %) column-types)
        number-of-columns (count column-names)
        common-type (when (apply = column-types)
                      (first column-types))
        row-vector (if common-type
                     (fn [row] (apply (get vector-constructors common-type) [number-of-columns row]))
                     (fn [row] (vec row)))
        buffer (vector (repeatedly number-of-columns nil)) ]
    (fn [row]
      (when (not (empty? row)) 
        (let [parse-field (fn [i]
                            (let [s           (clojure.string/trim (get row i))
                                  parser      (get field-parsers i)
                                  transformer (get field-transformers i)]
                              (if (= s "")
                                empty-field-value
                                (try (parser (transformer s))  
                                  (catch Exception e
                                    (throw (Exception.
                                      (str "Parsing column " (get column-names i) ": '" s "' "
                                        (.getMessage e))))))))) ]
          (row-vector (loop [i 0 v (transient buffer)]
                        (if (< i number-of-columns)
                          (recur (inc i) (assoc! v i (parse-field i)))
                          (persistent! v)))) )))))

(defn- pad-vector [v new-len value]
  (into v (repeat (- new-len (count v)) value)))

(defn read-dataset
  [filename & {:keys [delim keyword-headers quote skip header compress-delim empty-field-value comment-char options]
               :or   {delim           \,
                      quote           \u0022
                      header          false
                      keyword-headers true
                      skip            0
                      options         nil}}]
  (with-open [reader (io/reader filename)]
    (let [rename-columns (:rename-columns options)
          max-rows (:max-rows options)
          default-type (:default-type options)
          types (:types options)
          transformers (:transformers options)
          lines (doall (csv/read-csv reader :separator delim :quote quote))
          compress-delim? (or compress-delim (= delim \space))
          header-row (when header (map #(or (get rename-columns %) %) (first lines)))
          skipped-body (drop (+ (if (not= skip 0) (dec skip) 0) (if header 1 0)) lines)
          trim-body (if max-rows (take (if header (inc max-rows) max-rows) skipped-body) skipped-body)
          compress-delim-fn #(if compress-delim? (filter (fn [field] (not= field "")) %) %)
          comment-char-fn #(if (and comment-char (boolean (seq %)) (.startsWith (first %) comment-char))
                             '()
                             %)
          remove-empty-fn #(when (some (fn [field] (not= field "")) %) %)
          parse-data-fn (if (and header (or types default-type)) ;TODO: should work without header
                          (make-typed-parse-row header-row types default-type empty-field-value transformers)
                          (fn [line] (vec (map #(parse-string % empty-field-value) line))))
          [dataset-body column-count]
          (loop [dataset-body [] row-number (if header 1 0) column-count 0]
            (if-let [line (nth trim-body row-number nil)]
              (let [new-line (-> line
                                 compress-delim-fn
                                 comment-char-fn
                                 remove-empty-fn
                                 parse-data-fn)]
                (if (or (not max-rows) (< row-number (if header (inc max-rows) max-rows)))
                  (recur (if-not (empty? new-line) (conj dataset-body new-line) dataset-body)
                         (inc row-number)
                         (max column-count (count new-line)))
                  [dataset-body column-count]))
              [dataset-body column-count]))
          column-names-strs
          (map (fn [hr-entry idx]
                 (let [text (if hr-entry (str hr-entry) (str "col" idx))]
                   (if keyword-headers (keyword text) text)))
               (concat header-row (repeat nil))
               (range column-count))
          padded-body (if (or types default-type)
                        dataset-body
                        (map #(pad-vector % column-count empty-field-value) dataset-body))]
      (clojure.core.matrix.dataset/dataset column-names-strs padded-body))))

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
        (when-not (= "-" filename)
            (.close file-writer))))))

(defmethod save :incanter.core/dataset
  [dataset filename & {:keys [delim header append]
                       :or {append false delim \,}}]
  (let [header (or header (map #(if (keyword? %) (name %) %)
                               (:column-names dataset)))
        rows (to-list dataset)
        data (if append
               rows
               (conj rows header)) ]
    (with-open [out-file (io/writer (if (= "-" filename)
                                      *out*
                                      filename)
                                    :append append)]
      (csv/write-csv out-file data :separator delim))))

(defmethod save java.awt.image.BufferedImage
  ([img filename & {:keys [format] :or {format "png"}}]
     (javax.imageio.ImageIO/write img
                                  format
                                  (.getAbsoluteFile (java.io.File. filename)))))
