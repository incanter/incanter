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
  (:use (incanter core)
        (clojure set)))


(defn -parse-string [value] 
  (try (Integer/parseInt value) 
    (catch NumberFormatException _ 
      (try (Double/parseDouble value)
        (catch NumberFormatException _ value)))))


(defn dataset [column-names & data] 
  {:column-names column-names
   :rows (into [] (map #(apply assoc {} (interleave column-names %)) (first data)))})



(defn read-dataset 
  "
    Returns a dataset read from a file.

    Options:
      :delim (default space), other options (tab ,  etc)
      :quote (default \") character used for quoting strings
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
         parsed-data (into [] (map (fn [row] (into [] (map #(-parse-string %) row))) raw-data))
       ]
    (if header? (dataset (first parsed-data) (rest parsed-data) (dataset parsed-data))))))
  

(defn get-column-id [dataset column-key]
  (let [headers (:column-names dataset)
        id (if (number? column-key)
             (if (some #(= column-key %) headers)
               column-key
               (nth headers column-key))
             column-key)]
    id))


(defn get-columns [dataset column-keys]
  (map (fn [col-key] (map #(% (get-column-id dataset col-key)) (:rows dataset))) column-keys))


(defn get-factors [coll]
  (let [unique-factors (into #{} coll)
        nfactors (count unique-factors)]
    (apply assoc {} (interleave unique-factors (range nfactors)))))



(defn to-factor-codes [coll]
  (let [levels (get-factors coll)]
    (for [value coll] (levels value))))

;(defn log2 [a]
;  (/ (Math/log a) (Math/log 2)))


(defn bit-encode [n]
  (let [nbits (dec (Math/ceil (log2 n)))]
    (map #(for [i (range nbits -1 -1)] (if (bit-test % i) 1 0))
         (range n))))


(defn bit-encode-vector [coll]
  (let [levels (get-factors coll)
        codes (to-factor-codes coll)
        bit-map (bit-encode (count (keys levels)))]
    (for [id codes] (nth bit-map id))))

; (bit-encode-vector (first (get-columns iris-map [4])))

(defn transpose-seq [coll]
  (map (fn [idx] (map #(nth % idx) coll)) (range (count (first coll)))))

;(transpose-seq (bit-encode-vector (first (get-columns iris-map [4]))))

(defn bit-encode-column [dataset column-key]
  (let [col (first (get-columns dataset [column-key]))]
    (if (some string? col) 
      (matrix (bit-encode-vector col))
      (matrix col))))


(defn to-matrix 
  "Converts a dataset into a matrix."
  ([dataset]
   (reduce bind-columns 
           (map #(bit-encode-column dataset %) 
                (range (count (keys (:column-names dataset))))))))



;(use 'clojure.set ) ;:only map-invert) 

;(defn to-factor-levels [factors coll]
;  (let [codes (map-invert factors)]
;    (for [value coll] (codes (int value)))))


;(defn factor-map [dataset]
;  (let [ncol (count (:column-names dataset))
;        columns (get-columns dataset (range ncol))
;       ]
;    (loop [f-map {} i 0]
;      (if (= i ncol)
;        f-map
;        (recur 
;          (if (some string? (nth columns i))
;            (assoc f-map (nth (:column-names dataset) i) (get-factors (nth columns i)))
;            f-map) (inc i))))))

; (to-factor-levels (get-factors (get-columns iris-map 4)) (to-factor-codes (get-columns iris-map 4)))


;(defn insert-factors [dataset column-key]
;  (let [codes (to-factor-codes (first (get-columns dataset [column-key])))
;        col-id (get-column-id dataset column-key)]
;    (assoc dataset :rows (map #(assoc %1 col-id %2) (:rows dataset) codes))))



;(defn insert-factors [dataset column-keys]
;  (let [codes (map to-factor-codes (get-columns dataset column-keys))
;        col-ids (map #(get-column-id dataset %) column-keys)]
;    (map (fn [code col-id] (assoc dataset :rows (map #(assoc %1 col-id %2) (:rows dataset) code))) codes col-ids)))




