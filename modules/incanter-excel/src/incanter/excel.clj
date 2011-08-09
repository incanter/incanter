(ns 
  ^{
    :doc "Excel module for reading and writing Incanter datasets.  Recognises both old and new
Excel file formats (.xls and .xlsx)."
    :author "David James Humphreys"}
  incanter.excel
  (:import
    [java.io FileOutputStream FileInputStream])
  (:use
   [incanter.core :only [dataset get-input-stream]]
   [incanter.excel.cells :only [read-line-values write-line-values]]
   [incanter.excel.workbook :only [get-workbook-sheet make-workbook-map write-workbook create-workbook-object]]))

(defn ^{:doc "Save a dataset to an Excel file.  Can save in both older and newer
Excel formats, uses the filename suffix or :override-format option.
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
  [^:incanter.core/dataset dataset  ^String filename  & {:keys [use-bold sheet override-format]
                                                         :or {use-bold true sheet "dataset" override-format nil}}]
  (write-workbook
   (let [workbook-blob (make-workbook-map (create-workbook-object filename override-format) sheet)
         align-row (fn [row cols] (map #(get row %1) cols))]
     (write-line-values workbook-blob use-bold 0 (:column-names dataset))
     (dorun
      (map
       (partial write-line-values workbook-blob false)
       (iterate inc 1)
       (seq (map #(align-row % (:column-names dataset)) (:rows dataset)))))
     (:workbook workbook-blob))
   filename))

(defn ^{:doc "Read an Excel file into a dataset. Note: cells containing formulas will be
empty upon import.  Can read both older and newer Excel file formats, uses the filename suffix
or :override-format option.

Options are:
:sheet either a String for the tab name or an int for the sheet index -- defaults to 0
:header-keywords convert the incoming header line to keywords -- defaults to false (no conversion)
:override-format If nil use the filename suffix to guess the Excel file format.  If :xls
or :xlsx override the suffix check.

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
  [^String filename & {:keys [sheet header-keywords override-format]
                       :or {sheet 0 header-keywords false override-format nil}}]
      (with-open [in-fs (get-input-stream filename)]
	(let [rows-it   (iterator-seq
                         (. (get-workbook-sheet
                             (create-workbook-object filename override-format in-fs)
                             sheet)
                            iterator))
	      colnames  (read-line-values (first rows-it))]
	  (dataset
           (if header-keywords
             (map keyword colnames)
             colnames)
           (map
            read-line-values
            (rest rows-it))))))
