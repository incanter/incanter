;;; datasets.clj -- provides sample datasets for Incanter.

;; by David Edgar Liebke http://incanter.org
;; March 22, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 22, 2009: First version



(ns incanter.datasets 
  (:use (incanter io)))



(def **datasets** 
  { 
   :iris {:filename (str (System/getProperty "incanter.home") "/data/iris.dat")
          :delim \space
          :header true}
   :speed {:filename (str (System/getProperty "incanter.home") "/data/test.dat")
           :delim \space
           :header true}
   :speed-csv {:filename (str (System/getProperty "incanter.home") "/data/test.csv")
               :delim \,
               :header true}
   :speed-tdd {:filename (str (System/getProperty "incanter.home") "/data/test.tdd")
               :delim \tab
               :header true}
   :survey {:filename (str (System/getProperty "incanter.home") "/data/olsexamp.dat")
            :delim \space
            :header true}
   :us-arrests {:filename (str (System/getProperty "incanter.home") "/data/us_arrests.dat")
            :delim \,
            :header true}
   :flow-meter {:filename (str (System/getProperty "incanter.home") "/data/flow_meter.dat")
            :delim \space
            :header true}
  })


(defn get-dataset
" Returns the sample dataset associated with the given key.

  Datasets:
    :iris -- the Fisher Iris dataset
    :speed -- a simple dataset with speed and distance data, there are also csv
              and tab-delimited versions of it for testing (:speed-csv :speed-tdd).
    :survey -- survey data from Lynch book.
    :us-arrests -- arrest data
    :flow-meter -- flow meter data used in Bland Altman Lancet paper
"
  ([dataset-key]
   (let [ds (**datasets** dataset-key)
         filename (ds :filename)
         delim (ds :delim)
         header (ds :header)]
   (read-dataset filename :delim delim :header header))))



