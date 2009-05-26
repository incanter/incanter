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
   :iris {:filename "data/iris.dat"
          :delim \space
          :header true}
   :cars {:filename "data/cars.dat"
           :delim \space
           :header true}
   :cars-csv {:filename "data/cars.dat"
               :delim \,
               :header true}
   :cars-tdd {:filename "data/cars.dat"
               :delim \tab
               :header true}
   :survey {:filename "data/olsexamp.dat"
            :delim \space
            :header true}
   :us-arrests {:filename "data/us_arrests.dat"
            :delim \,
            :header true}
   :flow-meter {:filename "data/flow_meter.dat"
            :delim \space
            :header true}
   :co2 {:filename "data/co2.csv"
            :delim \,
            :header true}
   :chick-weight {:filename "data/chick_weight.csv"
            :delim \,
            :header true}
   :plant-growth {:filename "data/plant_growth.csv"
            :delim \,
            :header true}
  })


(defn get-dataset
" Returns the sample dataset associated with the given key. Most datasets
  are from R's sample data sets, as are the descriptions below.

  Options:

    :incanter-home -- if the incanter.home property is not set when the JVM is
                      started, use the :incanter-home options to provide the
                      parent directory of the sample data directory.


  Datasets:

    :iris -- the Fisher's or Anderson's Iris data set gives the
             measurements in centimeters of the variables sepal 
             length and width and petal length and width, 
             respectively, for 50 flowers from each of 3 species 
             of iris.

    :cars -- The data give the speed of cars and the distances taken 
              to stop. Note that the data were recorded in the 1920s.

    :survey -- survey data used in Scott Lynch's 'Introduction to Applied Bayesian Statistics 
               and Estimation for Social Scientists'

    :us-arrests -- This data set contains statistics, in arrests per 100,000
                   residents for assault, murder, and rape in each of the 50 US
                   states in 1973. Also given is the percent of the population living
                   in urban areas.

    :flow-meter -- flow meter data used in Bland Altman Lancet paper.

    :co2 -- has 84 rows and 5 columns of data from an experiment on the cold tolerance 
            of the grass species _Echinochloa crus-galli_.

    :chick-weight -- has 578 rows and 4 columns from an experiment on the effect of diet 
                     on early growth of chicks.

    :plant-growth -- Results from an experiment to compare yields (as measured by dried
                     weight of plants) obtained under a control and two different
                     treatment conditions.
"
  ([dataset-key & options]
    (let [opts (if options (apply assoc {} options) nil)
          incanter-home (if (:incanter-home opts) (:incanter-home opts) (System/getProperty "incanter.home"))
          ds (**datasets** dataset-key)
          filename (if (nil? incanter-home) (ds :filename) (str incanter-home "/" (ds :filename)))
          delim (ds :delim)
          header (ds :header)]
   (read-dataset filename :delim delim :header header))))



