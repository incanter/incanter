(ns incanter.excel.workbook
  (:import [org.apache.poi.ss.usermodel Font]
           [org.apache.poi.ss.usermodel Workbook]
           [org.apache.poi.hssf.usermodel HSSFWorkbook]
           [org.apache.poi.xssf.usermodel XSSFWorkbook]
           [java.io FileOutputStream]))

(defmulti ^ {:doc "Retrieve the Excel workbook based on either the index or the sheet name."}
  get-workbook-sheet
  (fn [wbk index-or-name] (if (integer? index-or-name) :indexed :named)))

(defmethod get-workbook-sheet :indexed [wbk index-or-name]
  (. wbk getSheetAt index-or-name))

(defmethod get-workbook-sheet :named [wbk index-or-name]
  (. wbk getSheet (str index-or-name)))

(defn make-font [normal? ^Workbook w]
  (let [f (. w createFont)
        c (. w createCellStyle)]
    (. f setBold (not normal?))
    (. c setFont f)
    c))

(defn create-sheet [blob ^String sheet]
  (assoc blob :sheet (. (:workbook blob) createSheet sheet)))

(defn make-workbook-map
  ([^Workbook w]
     {:workbook w
      :normal (make-font true w)
      :bold   (make-font false w)})
  ([^Workbook w ^String sheet]
     (create-sheet (make-workbook-map w) sheet)))

(defn write-workbook
  [^Workbook workbook ^String filename]
  (with-open [f (FileOutputStream. filename)]
    (. workbook write f)))

(defn create-workbook-object
  ([filename override?]
     (cond
      (= override? :xlsx) (XSSFWorkbook.)
      (= override? :xls)  (HSSFWorkbook.)
      :else (if (. filename endsWith "xlsx")
              (XSSFWorkbook.)
              (HSSFWorkbook.))))
  ([filename override? filestream]
     (cond
      (= override? :xlsx) (XSSFWorkbook. filestream)
      (= override? :xls)  (HSSFWorkbook. filestream)
      :else (if (. filename endsWith "xlsx")
              (XSSFWorkbook. filestream)
              (HSSFWorkbook. filestream)))))

(defn get-all-sheets [^Workbook w]
  (for [i (range 0 (. w getNumberOfSheets))]
    (. w getSheetAt i)))
