;;; dataset.clj -- Dataset type for the Incanter library

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.



(ns ^{:doc "This is the dataset type for Incanter.
            "
       :author "Matthew Farrell"}

  incanter.dataset

  (:require [clojure.pprint :refer [print-table]]))

;;; Protocols

;;; required
(defprotocol PDataset
  (column-names [d]
    "Returns a seq of column names")
  (rows [d]
    "Returns a seq of row maps"))

;;; Protocol common functions
(defmethod print-method ::dataset [o, ^java.io.Writer w]
  (binding [*out* w]
    (print-table (column-names o) (rows o))))
(prefer-method print-method ::dataset Object)

;;; Implementations
(deftype RowMapDataset [cn r]
  PDataset
  (column-names [_] cn)
  (rows [_] r)

  clojure.lang.IPersistentCollection
  (equiv [_ o]
    (and (satisfies? PDataset o)
         (= (into #{} cn) (into #{} (column-names o)))
         (= r (rows o)))))
(derive RowMapDataset ::dataset)

;;; Factory
(defn make-dataset [column-names rows]
  (RowMapDataset. column-names rows))
