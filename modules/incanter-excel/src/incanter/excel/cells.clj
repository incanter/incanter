(ns
    ^{:doc "Functions for reading and writing to cells."}
  incanter.excel.cells
  (:import [org.apache.poi.ss.usermodel Cell CellStyle DateUtil CellType]
           [org.apache.poi.ss.usermodel Row Sheet]))

(defmulti
  write-cell
  #(cond
    (isa? (. % getClass) Number) :numeric
    (keyword? %)                 :keyword
    :else                        :other))

(defmulti
  get-cell-formula-value
  "Get the value after the evaluating the formula.  See http://poi.apache.org/spreadsheet/eval.html#Evaluate"
  (fn [evaled-cell evaled-type]
    evaled-type))

(defmulti
  get-cell-value
  "Get the cell value depending on the cell type."
  (fn [cell]
    (let [ct (. cell getCellType)]
      (if (not (= CellType/NUMERIC ct))
        ct
        (if (DateUtil/isCellDateFormatted cell)
          :date
          ct)))))

(defn write-line [^Sheet sheet row-num line ^CellStyle style]
  (let [^Row xl-line (. sheet createRow row-num)]
    (dorun
     (map
      #(if (not (nil? %2))
         (doto (. xl-line createCell %1) (.setCellValue (write-cell %2)) (.setCellStyle style)))
      (iterate inc 0)
      (seq line)))))

(defn write-line-values [{:keys [sheet bold normal workbook]} bold? row-number coll]
  (write-line sheet row-number coll (if bold? bold normal)))
(defn cell-iterator [^Row row]
  (if row
    (for [idx (range (.getFirstCellNum row) (.getLastCellNum row))]
      (if-let [cell (.getCell row idx)]
        cell
        (.createCell row idx CellType/BLANK)))
    ()))

(defn read-line-values [row-iterator-item]
  (doall (map get-cell-value (cell-iterator row-iterator-item))))

;; Implementations of the multi-methods:

(defmethod write-cell :keyword [k] (name k))
(defmethod write-cell :other   [o] (str o))
(defmethod write-cell :numeric [n] (. n doubleValue))

(defmethod get-cell-formula-value
  CellType/BOOLEAN [evaled-cell evaled-type]
  (. evaled-cell getBooleanValue))

(defmethod get-cell-formula-value
  CellType/STRING  [evaled-cell evaled-type]
  (. evaled-cell getStringValue))

(defmethod get-cell-formula-value
  :number                [evaled-cell evaled-type]
  (. evaled-cell getNumberValue))

(defmethod get-cell-formula-value
  :date                  [evaled-cell evaled-type]
  (DateUtil/getJavaDate (. evaled-cell getNumberValue)))

(defmethod get-cell-formula-value
  :default               [evaled-cell evaled-type]
  (str "Unknown cell type " (. evaled-cell getCellType)))

(defmethod get-cell-value CellType/BLANK   [cell])
(defmethod get-cell-value CellType/FORMULA [cell]
  (let [val (.
             (.. cell
                 getSheet
                 getWorkbook
                 getCreationHelper
                 createFormulaEvaluator)
             evaluate cell)
        evaluated-type (. val getCellType)]
    (get-cell-formula-value
     val
     (if (= CellType/NUMERIC evaluated-type)
       (if (DateUtil/isCellInternalDateFormatted cell)
         ;; Check the original for date formatting hints
         :date
         :number)
       evaluated-type))))

(defmethod get-cell-value CellType/BOOLEAN [cell]
  (. cell getBooleanCellValue))
(defmethod get-cell-value CellType/STRING  [cell]
  (. cell getStringCellValue))
(defmethod get-cell-value CellType/NUMERIC [cell]
  (. cell getNumericCellValue))
(defmethod get-cell-value :date [cell]
  (. cell getDateCellValue))
(defmethod get-cell-value :default [cell]
  (str "Unknown cell type " (. cell getCellType)))

