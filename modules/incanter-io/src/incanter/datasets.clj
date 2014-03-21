;;; datasets.clj -- provides sample datasets for Incanter.

;; by David Edgar Liebke http://incanter.org
;; March 22, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 22, 2009: First version



(ns ^{:doc "Provides access to different datasets that are bundled with Incanter."}
    incanter.datasets
  (:use [incanter.io :only (read-dataset)]))


(def ^:dynamic **datasets-base-url** "https://github.com/liebke/incanter/raw/master/")


(def ^:dynamic **datasets**
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
   :math-prog {:filename "data/math_prog.csv"
            :delim \,
            :header true}
   :plant-growth {:filename "data/plant_growth.csv"
            :delim \,
            :header true}
   :filip {:filename "data/filip.dat"
            :delim \space
            :header true}
   :pontius {:filename "data/pontius.dat"
            :delim \space
            :header true}
   :longley {:filename "data/longley.dat"
            :delim \space
            :header true}
   :chwirut {:filename "data/Chwirut1.dat"
            :delim \space
            :header true}
   :hair-eye-color {:filename "data/hair_eye_color.csv"
            :delim \,
            :header true}
   :austres {:filename "data/austres.csv"
            :delim \,
            :header true}
   :airline-passengers {:filename "data/airline_passengers.csv"
            :delim \,
            :header true}
   :thurstone {:filename "data/thurstone.dat"
            :delim \tab
            :header true}
   :iran-election {:filename "data/iran_election_2009.csv"
            :delim \,
            :header true}
  })


(defn get-dataset
  "
  Returns the sample dataset associated with the given key. Most datasets
  are from R's sample data sets, as are the descriptions below.

  Options:

    :incanter-home -- if the incanter.home property is not set when the JVM is
                      started (using -Dincanter.home) or there is no INCANTER_HOME
                      environment variable set, use the :incanter-home options to
                      provide the parent directory of the sample data directory.

    :from-repo (default false) -- If true, retrieves the dataset from the online repository
                       instead of locally, it will do this by default if incanter-home is not set.


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

    :pontius -- These data are from a NIST study involving calibration of load cells.
                The response variable (y) is the deflection and the predictor variable
                (x) is load.
                See http://www.itl.nist.gov/div898/strd/lls/data/Pontius.shtml

    :filip -- NIST data set for linear regression certification,
              see http://www.itl.nist.gov/div898/strd/lls/data/Filip.shtml

    :longely -- This classic dataset of labor statistics was one of the first used to
                test the accuracy of least squares computations. The response variable
                (y) is the Total Derived Employment and the predictor variables are GNP
                Implicit Price Deflator with Year 1954 = 100 (x1), Gross National Product
                (x2), Unemployment (x3), Size of Armed Forces (x4), Non-Institutional
                Population Age 14 & Over (x5), and Year (x6).
                See http://www.itl.nist.gov/div898/strd/lls/data/Longley.shtml

    :Chwirut -- These data are the result of a NIST study involving ultrasonic calibration.
                The response variable is ultrasonic response, and the predictor variable is
                metal distance.
                See http://www.itl.nist.gov/div898/strd/nls/data/LINKS/DATA/Chwirut1.dat

    :thurstone -- test data for non-linear least squares.

    :austres -- Quarterly Time Series of the Number of Australian Residents

    :hair-eye-color -- Hair and eye color of sample of students

    :airline-passengers -- Monthly Airline Passenger Numbers 1949-1960

    :math-prog -- Pass/fail results for a high school mathematics assessment test
                  and a freshmen college programming course.

    :iran-election -- Vote counts for 30 provinces from the 2009 Iranian election.

   Examples:
     (def data (get-dataset :cars))
     (def data2 (get-dataset :cars :incanter.home \"/usr/local/packages/incanter\"))

  "
  ([dataset-key & {:keys [incanter-home from-repo]
                   :or {incanter-home (or (System/getProperty "incanter.home")
                                          (System/getenv "INCANTER_HOME"))
                        from-repo false}}]
     (when-let [ds (**datasets** dataset-key)]
       (let [filename (if (and (not from-repo) (not (nil? incanter-home)))
                        (str incanter-home "/" (ds :filename))
                        (str **datasets-base-url** (ds :filename)))
             delim (ds :delim)
             header (ds :header)]
         (println "from repo" from-repo "incanter home" incanter-home "filename" filename)
         (read-dataset filename :delim delim :header header)))))
