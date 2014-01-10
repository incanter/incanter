(ns ^{:doc "Excel module for reading and writing Incanter datasets.  Recognizes both old and new
Excel file formats (.xls and .xlsx)."
      :author "David James Humphreys"}
  incanter.excel
  (:import
    [java.io FileOutputStream FileInputStream])
  (:require [clojure.java.io :as io]
            [incanter.dataset :as ds])
  (:use
   [incanter.core :only [dataset dataset? col-names]]
   [incanter.excel.cells :only [read-line-values write-line-values]]
   [incanter.excel.workbook :only [get-workbook-sheet make-workbook-map write-workbook create-workbook-object create-sheet get-all-sheets]]))

(defn- commit-sheet!
  "Internally save the dataset into the :sheet object."
  [workbook-blob dataset use-bold]
  (let [align-row (fn [row cols] (map #(get row %1) cols))]
    (write-line-values workbook-blob use-bold 0 (col-names dataset))
    (dorun
     (map
      (partial write-line-values workbook-blob false)
      (iterate inc 1)
      (seq (map #(align-row % (col-names dataset)) (ds/rows dataset)))))))

(defmulti
  ^{:doc "Save a dataset to an Excel file.  Can save in both older and newer
Excel formats, uses the filename suffix or :override-format option.

By passing in a collection of datasets and names it is possible to write more than
one sheet at a time: e.g.
  (save-xls [\"first sheet\" dataset1 \"second\" dataset2] my-file)

Options are:
:sheet defaults to \"dataset\" if not provided.
:use-bold defaults to true.  Set the header line in bold.
:override-format If nil use the filename suffix to guess the Excel file format.
If :xls or :xlsx override the suffix check.

Examples:
  (use '(incanter core datasets excel))
  (save-xls (get-dataset :cars) \"/tmp/cars.xls\")

"}
  save-xls

  (fn [dataset
      ^String filename
      & {:keys [use-bold sheet override-format]
         :or {use-bold true sheet "dataset" override-format nil}}]
    (if (dataset? dataset)
      :incanter.core/dataset
      :collection)))

(defmethod
  save-xls
  :incanter.core/dataset
  [dataset
   ^String filename
   & {:keys [use-bold sheet override-format]
      :or {use-bold true sheet "dataset" override-format nil}}]
  (write-workbook
   (let [workbook-blob (make-workbook-map (create-workbook-object filename override-format) sheet)]
     (commit-sheet! workbook-blob dataset use-bold)
     (:workbook workbook-blob))
   filename))

(defmethod
  save-xls
  :collection
  [datasets
   ^String filename
   & {:keys [use-bold override-format]
      :or {use-bold true override-format nil}}]
  (if (not (zero? (mod (count datasets) 2)))
    (throw (Exception. "dataset count must be even: a name then a dataset."))
    (write-workbook
     (loop [workbook-blob (make-workbook-map (create-workbook-object filename override-format))
            [sheet-name dataset & others] (doall datasets)]
       (if (and sheet-name dataset)
         (let [next-blob (create-sheet workbook-blob sheet-name)]
           (commit-sheet! next-blob dataset use-bold)
           (recur next-blob others))
         (:workbook workbook-blob)))
     filename)))

(defn- read-sheet [rows-it header-keywords]
  (let [colnames  (read-line-values (first rows-it))]
    (dataset
       (if header-keywords
         (map keyword colnames)
         colnames)
       (map
        read-line-values
        (rest rows-it)))))

(defmulti
  ^{:doc "Read an Excel file into a dataset. Note: cells containing formulas will be
empty upon import.  Can read both older and newer Excel file formats, uses the filename suffix
or :override-format option.

Options are:
:sheet either a String for the tab name or an int for the sheet index -- defaults to 0
:header-keywords convert the incoming header line to keywords -- defaults to false (no conversion)
:override-format If nil use the filename suffix to guess the Excel file format.  If :xls
or :xlsx override the suffix check.
:all-sheets? true to try to read in all sheets of data (false by default).

 Examples:
   (use '(incanter core io excel))
   (view (read-xls \"http://incanter.org/data/aus-airline-passengers.xls\"))

   (use '(incanter core charts excel))
   ;; read .xls file of Australian airline passenger data from the 1950s.
   (with-data (read-xls \"http://incanter.org/data/aus-airline-passengers.xls\")
   (view $data)
   ;; time-series-plot needs time in millisecs
   ;; create a function, to-millis, to convert a sequence of Date objects
   ;; to a sequence of milliseconds
   (let [to-millis (fn [dates] (map #(.getTime %) dates))] 
     (view (time-series-plot (to-millis ($ :date)) ($ :passengers)))))

"}

  read-xls
  (fn [^String filename
      & {:keys [sheet header-keywords override-format all-sheets?]
         :or {sheet 0 header-keywords false override-format nil all-sheets? false}}]
    (if all-sheets?
      :collection
      :singleton)))
(defmethod
  read-xls
  :singleton
  [^String filename
   & {:keys [sheet header-keywords override-format]
      :or {sheet 0 header-keywords false override-format nil}}]
  (with-open [in-fs (io/input-stream filename)]
    (let [rows-it   (iterator-seq
                     (. (get-workbook-sheet
                         (create-workbook-object filename override-format in-fs)
                         sheet)
                        iterator))]
      (read-sheet rows-it header-keywords))))

(defmethod
  read-xls
  :collection
  [^String filename
   & {:keys [sheet header-keywords override-format all-sheets?]
      :or {sheet 0 header-keywords false override-format nil all-sheets? false}}]
  (with-open [in-fs (io/input-stream filename)]
    (let [workbook (create-workbook-object filename override-format in-fs)]
     (if all-sheets?
       (for [current-sheet (get-all-sheets workbook)]
         (let [rows-it   (iterator-seq (. current-sheet iterator))]
           (read-sheet rows-it header-keywords)))))))
