(ns 
	#^{:doc "Excel module for Incanter datasets."} 
	incanter.excel
  (:import
    [org.apache.poi.hssf.usermodel HSSFWorkbook HSSFCell HSSFFont HSSFRow HSSFSheet]
    [org.apache.poi.ss.usermodel Font CellStyle Cell]
    org.apache.poi.hssf.model.Sheet
    [java.io FileOutputStream FileInputStream])
  (:use
    [incanter core]))

(defn- do-loop [fun start-number data]
  (dorun (map fun (iterate inc start-number) (seq data))))

(defn- make-font [#^boolean normal? #^HSSFWorkbook w]
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

(defn- write-line [#^HSSFSheet sheet row-num line #^CellStyle style]
   (let [#^HSSFRow xl-line (. sheet createRow row-num)]
        (do-loop
          #(doto (. xl-line createCell %1) (.setCellValue (write-cell %2)) (.setCellStyle style))
          0
          line)))

(defn- write-file
  [#^HSSFWorkbook workbook
  #^String filename]
  (with-open [f (FileOutputStream. filename)]
    (. workbook write f)))

(defn #^{:doc "Save a dataset to an Excel file.
Options are:
:sheet-name defaults to \"dataset\" if not provided.
"}
  save-xls [
  #^:incanter.core/dataset dataset
  #^String filename
  & options]
    (write-file (let [
          opts (when options (apply assoc {} options))
          x (let [w (HSSFWorkbook.)]
            {:workbook w
             :normal  (make-font true w)
             :bold    (make-font false w)})
          s (. (:workbook x) createSheet (or (:sheet-name opts) "dataset"))
          align-row (fn [row cols] (map #(get row %1) cols))
          ]
          (write-line s 0 (:column-names dataset) (:bold x))
          (do-loop
            #(write-line s %1 (align-row %2 (:column-names dataset)) (:normal x))
            1
            (:rows dataset))
    (:workbook x))
    filename))

(defmulti get-workbook-sheet "Retrieve the Excel workbook based on either the index or the sheet name."
	(fn [wbk index-or-name] (if (integer? index-or-name) :indexed :named)))
(defmethod get-workbook-sheet :indexed [wbk index-or-name]
 (. wbk getSheetAt index-or-name))
(defmethod get-workbook-sheet :named [wbk index-or-name]
 (. wbk getSheet (str index-or-name)))

(defmulti  get-cell-value (fn [cell] (. cell getCellType)))
(defmethod get-cell-value Cell/CELL_TYPE_BLANK   [cell])
(defmethod get-cell-value Cell/CELL_TYPE_FORMULA [cell]); NOTHING for now.
(defmethod get-cell-value Cell/CELL_TYPE_BOOLEAN [cell] (. cell getBooleanCellValue))
(defmethod get-cell-value Cell/CELL_TYPE_STRING  [cell] (. cell getStringCellValue))
(defmethod get-cell-value Cell/CELL_TYPE_NUMERIC [cell] (. cell getNumericCellValue)) ;TODO: date fields seem to live in here.
(defmethod get-cell-value :default [cell] (str "Unknown cell type " (. cell getCellType)))
(defn #^{:doc "Read an Excel file into a dataset.
Options are:
:sheet-name either a String for the tab name or an int for the sheet index -- defaults to 0"}
  read-xls [
  #^String filename
  & options]
    (let [opts (when options (apply assoc {} options))
          sheet-pointer (or (:sheet-name opts) 0)]
    (with-open [in-fs (FileInputStream. filename)]
      (let [workbook  (HSSFWorkbook. in-fs)
            sheet     (get-workbook-sheet workbook sheet-pointer)
            rows-it   (iterator-seq (. sheet iterator))
            rowi      (. (first rows-it) iterator)
            colnames  (doall (map get-cell-value (iterator-seq rowi)))
            data      (map #(iterator-seq (. % iterator)) (rest rows-it))
           ] (dataset
               colnames
               (map (fn [d] (map get-cell-value d)) data))))))