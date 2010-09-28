(ns 
  ^{
    :doc "Excel module for reading and writing Incanter datasets."
    :author "David James Humphreys"}
  incanter.excel
  (:import
    [org.apache.poi.hssf.usermodel HSSFWorkbook HSSFCell HSSFFont HSSFRow HSSFSheet]
    [org.apache.poi.ss.usermodel Font CellStyle Cell DateUtil]
    org.apache.poi.hssf.model.Sheet
    [java.io FileOutputStream FileInputStream])
  (:use
    [incanter.core :only [dataset get-input-stream]]))

(defn- do-loop [fun start-number data]
  (dorun (map fun (iterate inc start-number) (seq data))))

(defn- make-font [normal? ^HSSFWorkbook w]
  (let [f (. w createFont)
        c (. w createCellStyle)]
    (. f setBoldweight (if normal? Font/BOLDWEIGHT_NORMAL Font/BOLDWEIGHT_BOLD))
    (. c setFont f)
    c))

(defmulti write-cell #(let [c (. % getClass)]
  (cond (isa? c Number) :numeric
        :else           :other )))

(defmethod write-cell :other   [o] (str o))
(defmethod write-cell :numeric [n] (. n doubleValue))

(defn- write-line [^HSSFSheet sheet row-num line ^CellStyle style]
   (let [^HSSFRow xl-line (. sheet createRow row-num)]
     (do-loop
      #(doto (. xl-line createCell %1) (.setCellValue (write-cell %2)) (.setCellStyle style))
      0
      line)))

(defn- write-file
  [^HSSFWorkbook workbook ^String filename]
  (with-open [f (FileOutputStream. filename)]
    (. workbook write f)))

(defn ^{:doc "Save a dataset to an Excel file.
Options are:
:sheet defaults to \"dataset\" if not provided.
:use-bold defaults to true.  Set the header line in bold.

Examples:
  (use '(incanter core datasets excel))
  (save-xls (get-dataset :cars) \"/tmp/cars.xls\")

"}
  save-xls
  [^:incanter.core/dataset dataset  ^String filename  & options]
  (write-file
   (let [opts (when options (apply assoc {} options))
	 bold-header (or (:use-bold opts) true)
	 workbook-blob (let [w (HSSFWorkbook.)]
			 {:workbook w
			  :normal  (make-font true w)
			  :bold    (make-font false w)})
	 workbook-sheet (. (:workbook workbook-blob) createSheet (or (:sheet opts) "dataset"))
	 align-row (fn [row cols] (map #(get row %1) cols))]
     (write-line workbook-sheet 0 (:column-names dataset) (if bold-header (:bold workbook-blob) (:normal workbook-blob)))
     (do-loop
      #(write-line workbook-sheet %1 (align-row %2 (:column-names dataset)) (:normal workbook-blob))
      1
      (:rows dataset))
     (:workbook workbook-blob))
   filename))

(defmulti ^ {:private true
	     :doc "Retrieve the Excel workbook based on either the index or the sheet name."}
  get-workbook-sheet
  (fn [wbk index-or-name] (if (integer? index-or-name) :indexed :named)))

(defmethod get-workbook-sheet :indexed [wbk index-or-name]
	   (. wbk getSheetAt index-or-name))

(defmethod get-workbook-sheet :named [wbk index-or-name]
 (. wbk getSheet (str index-or-name)))

(defmulti ^ {:private true
	     :doc "Get the value after the evaluating the formula.  See http://poi.apache.org/spreadsheet/eval.html#Evaluate"}
  get-cell-formula-value
  (fn [evaled-cell evaled-type]
    evaled-type))
(defmethod get-cell-formula-value Cell/CELL_TYPE_BOOLEAN [evaled-cell evaled-type] (. evaled-cell getBooleanValue))
(defmethod get-cell-formula-value Cell/CELL_TYPE_STRING  [evaled-cell evaled-type] (. evaled-cell getStringValue))
(defmethod get-cell-formula-value :number  [evaled-cell evaled-type] (. evaled-cell getNumberValue))
(defmethod get-cell-formula-value :date    [evaled-cell evaled-type] (DateUtil/getJavaDate (. evaled-cell getNumberValue)))
(defmethod get-cell-formula-value :default [evaled-cell evaled-type] (str "Unknown cell type " (. evaled-cell getCellType)))


(defmulti ^ {:private true
              :doc "Get the cell value depending on the cell type."}
          get-cell-value
	(fn [cell]
		(let [ct (. cell getCellType)]
			(if (not (= Cell/CELL_TYPE_NUMERIC ct))
				ct
				(if (DateUtil/isCellDateFormatted cell)
					:date
					ct)))))

(defmethod get-cell-value Cell/CELL_TYPE_BLANK   [cell])
(defmethod get-cell-value Cell/CELL_TYPE_FORMULA [cell]
	   (let [val (. (.. cell getSheet getWorkbook getCreationHelper createFormulaEvaluator) evaluate cell)
		 evaluated-type   (. val getCellType)]
	     (get-cell-formula-value val (if (= Cell/CELL_TYPE_NUMERIC evaluated-type)
					   (if (DateUtil/isCellInternalDateFormatted cell) ; Check the original for date formatting hints
					     :date
					     :number)
					   evaluated-type))))

(defmethod get-cell-value Cell/CELL_TYPE_BOOLEAN [cell] (. cell getBooleanCellValue))
(defmethod get-cell-value Cell/CELL_TYPE_STRING  [cell] (. cell getStringCellValue))
(defmethod get-cell-value Cell/CELL_TYPE_NUMERIC [cell] (. cell getNumericCellValue))
(defmethod get-cell-value :date                  [cell] (. cell getDateCellValue))
(defmethod get-cell-value :default [cell] (str "Unknown cell type " (. cell getCellType)))

(defn- cell-iterator [^HSSFRow row]
  (for [idx (range (.getFirstCellNum row) (.getLastCellNum row))]
    (if-let [cell (.getCell row idx)]
      cell
      (.createCell row idx Cell/CELL_TYPE_BLANK))))

(defn ^{:doc "Read an Excel file into a dataset. Note: cells containing formulas will be
empty upon import.
Options are:
:sheet either a String for the tab name or an int for the sheet index -- defaults to 0

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
  [^String filename  & options]
    (let [opts (when options (apply assoc {} options))
          sheet-pointer (or (:sheet opts) 0)]
      (with-open [in-fs (get-input-stream filename)]
	(let [workbook  (HSSFWorkbook. in-fs)
	      sheet     (get-workbook-sheet workbook sheet-pointer)
	      rows-it   (iterator-seq (. sheet iterator))
	      rowi      (cell-iterator (first rows-it))
	      colnames  (doall (map get-cell-value rowi))
	      data      (map #(cell-iterator %) (rest rows-it))]
	  (dataset colnames
		   (map (fn [d] (map get-cell-value d)) data))))))
