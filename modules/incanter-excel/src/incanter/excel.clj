(ns incanter.excel
  (:import
    [org.apache.poi.hssf.usermodel HSSFWorkbook HSSFCell HSSFFont HSSFRow HSSFSheet]
    [org.apache.poi.ss.usermodel Font CellStyle]
    org.apache.poi.hssf.model.Sheet
    java.io.FileOutputStream)
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

(defn write-line [#^HSSFSheet sheet row-num line #^CellStyle style]
   (let [#^HSSFRow xl-line (. sheet createRow row-num)]
        (do-loop
          #(doto (. xl-line createCell %1) (.setCellValue (str %2)) (.setCellStyle style))
          0
          line)))

(defn- write-file
  [#^HSSFWorkbook workbook
  #^String filename]
  (with-open [f (FileOutputStream. filename)]
    (. workbook write f)))

(defn write-cell [#^HSSFCell c #^Object o]
  (. c setCellValue (str o)))
  
(defn write-cell [#^HSSFCell c #^Number n]
  (. c setCellValue (. n doubleValue)))

(defn save-xls [
  #^:incanter.core/dataset dataset
  #^String filename
  & options]
    (write-file (let [
          opts (when options (apply assoc {} options))
          x (let [w (HSSFWorkbook.)]
            {:workbook w
             :normal  (make-font true w)
             :bold    (make-font false w)})
          s (. (:workbook x) createSheet (or (opts :sheet-name "dataset")))
          ]
          (write-line s 0 (:column-names dataset) (:bold x))
          (do-loop #(write-line s %1 (vals %2) (:normal x)) 1 (:rows dataset))
    (:workbook x))
    filename))
