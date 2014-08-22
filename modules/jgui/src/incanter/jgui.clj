;; jgui.clj - Java swing-based presentation functions

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009
;; modified by G. Reynolds 2014

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns ^{:doc "Java swing-based presentation"
       :author "David Edgar Liebke"}

  incanter.jgui

  (:use [incanter internal]
        [incanter.core :only (dataset dataset? matrix? ncol nrow to-list)]
        [incanter.infix :only (infix-to-prefix defop)]
        [clojure.set :only (difference)]
        [clojure.pprint :only (print-table)])
  (:require [clatrix.core :as clx])
  (:import (clatrix.core Matrix)
           (cern.jet.math.tdouble DoubleArithmetic)
           (cern.colt.list.tdouble DoubleArrayList)
           (cern.jet.stat.tdouble DoubleDescriptive Gamma)
           (javax.swing JTable JScrollPane JFrame)
           (java.util Vector)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; VIEW METHODS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmulti view
  "
  This is a general 'view' function. When given an Incanter matrix/dataset
  or a Clojure numeric collection, it will display it in a Java Swing
  JTable. When given an Incanter chart object, it will display it in a new
  window. When given a URL string, it will open the location with the
  platform's default web browser.

  When viewing charts, a :width (default 500) and :height (default 400)
  option can be provided.

  When viewing an incanter.processing sketch, set the :exit-on-close option
  to true (default is false) to kill the animation processes when you
  close the window (this will also kill your REPL or Swank server),
  otherwise those processing will continue to run in the background.



  Examples:

    (use '(incanter core stats datasets charts jgui))

    ;; view matrices
    (def rand-mat (matrix (sample-normal 100) 4))
    (view rand-mat)

    ;; view numeric collections
    (view [1 2 3 4 5])
    (view (sample-normal 100))

    ;; view Incanter datasets
    (view (get-dataset :iris))

    ;; convert dataset to matrix, changing Species names to numeric codes
    (view (to-matrix (get-dataset :iris)))

    ;; convert dataset to matrix, changing Species names to dummy variables
    (view (to-matrix (get-dataset :iris) :dummies true))

    ;; view a chart
    (view (histogram (sample-normal 1000)) :width 700 :height 700)

    ;; view a URL
    (view \"http://incanter.org\")

    ;; view a PNG file
    (save (histogram (sample-normal 1000)) \"/tmp/norm_hist.png\")
    (view \"file:///tmp/norm_hist.png\")
  "
  (fn [obj & options] (cond
                        (and (not (matrix? obj))
                             (not (dataset? obj))
                             (not (map? obj))
                             (coll? obj))
                        ::coll
                        (.contains (str (type obj)) "processing.core.PApplet")
                          :sketch
                        :else
                          (type obj))))


;;(defmethod view ::coll ([obj & options] (view (matrix obj))))

(defmethod view ::coll
  ([obj & options]
    (let [rows (if (coll? (first obj))
                 obj
                 (map vector obj))
          colnames (range (count (first rows)))]
      (view (dataset colnames rows)))))

(defmethod view :incanter.core/matrix
  ([obj & {:keys [column-names]}]
    (let [col-names (or column-names (range (ncol obj)))
          m (ncol obj)
          n (nrow obj)]
     (doto (JFrame. "Incanter Matrix")
       (.add (JScrollPane.
               (JTable.
                 (cond
                   (and (> m 1) (> n 1))
                     (Vector. (map #(Vector. %) (to-list obj)))
                   (or (and (> m 1) (= n 1)) (and (= m 1) (= n 1)))
                     (Vector. (map #(Vector. %) [(to-list obj) []]))
                   (and (= m 1) (> n 1))
                     (Vector. (map #(Vector. [%]) (to-list obj))))
                                    (Vector. col-names))))
       (.setSize 400 600)
       (.setVisible true)))))


(defmethod view :incanter.core/dataset
  ([obj & options]
    (let [col-names (:column-names obj)
          column-vals (map (fn [row] (map #(row %) col-names)) (:rows obj))]
      (doto (JFrame. "Incanter Dataset")
        (.add (JScrollPane. (JTable. (Vector. (map #(Vector. %) column-vals))
                                     (Vector. col-names))))
        (.setSize 400 600)
        (.setVisible true)))))



(defmethod view javax.swing.JTable
  ([obj & options]
    (doto (javax.swing.JFrame. "Incanter Dataset")
      (.add (javax.swing.JScrollPane. obj))
      (.setSize 500 600)
      (.setVisible true))))


(defmethod view java.awt.Image
  ([obj & options]
    (let [icon (javax.swing.ImageIcon. obj)
          label (javax.swing.JLabel. icon)
          height (+ 15 (.getIconHeight icon))
          width (+ 15 (.getIconWidth icon))]
      (doto (javax.swing.JFrame. "Incanter Image")
        (.add (javax.swing.JScrollPane. label))
        (.setSize height width)
        .pack
        (.setVisible true)))))



;; URL view method code lifted from clojure.contrib.javadoc.browse/open-url-in-browser
(defmethod view String
  ([url]
    (try
      (when (clojure.lang.Reflector/invokeStaticMethod "java.awt.Desktop" "isDesktopSupported" (to-array nil))
        (-> (clojure.lang.Reflector/invokeStaticMethod "java.awt.Desktop" "getDesktop" (to-array nil))
            (.browse (java.net.URI. url)))
        url)
      (catch ClassNotFoundException e nil))))




(defn data-table
"Creates a javax.swing.JTable given an Incanter dataset."
  ([data]
   (let [col-names (:column-names data)
         column-vals (map (fn [row] (map #(row %) col-names)) (:rows data))
         table-model (javax.swing.table.DefaultTableModel. (java.util.Vector. (map #(java.util.Vector. %) column-vals))
                                                           (java.util.Vector. col-names))]

     (javax.swing.JTable. table-model))))



(defmulti set-data
  "
  Examples:

    (use '(incanter core charts datasets))

    (def data (get-dataset :iris))
    (def table (data-table data))
    (view table)
    ;; now view only a subset of the data
    (set-data table ($where {:Petal.Length {:gt 6}} data))


    ;; use sliders to dynamically select the query values
    (let [data (get-dataset :iris)
          table (data-table data)]
      (view table)
      (sliders [species [\"setosa\" \"virginica\" \"versicolor\"]
                min-petal-length (range 0 8 0.1)]
        (set-data table ($where {:Species species
                                 :Petal.Length {:gt min-petal-length}}
                                data))))

  "
  (fn [obj & more] (type obj)))


(defmethod set-data javax.swing.JTable
  ([table data]
     (let [col-names (:column-names data)
           column-vals (map (fn [row] (map #(row %) col-names)) (:rows data))
           table-model (javax.swing.table.DefaultTableModel. (java.util.Vector. (map #(java.util.Vector. %) column-vals))
                                                             (java.util.Vector. col-names))]
       (.setModel table table-model))))

