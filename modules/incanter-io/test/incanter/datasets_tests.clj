(ns incanter.datasets-tests
  (:use clojure.test)
  (:require [incanter.datasets :as ds]
            [incanter.core :as i]))

(def incanter-home "../..")

(defn- get-dataset
  [s]
  (ds/get-dataset s :incanter-home incanter-home))

(defn- first-row
  [ds]
  (i/sel ds :rows 0))

(deftest iris
  (let [data (get-dataset :iris)]
    (is (= ["Sepal.Length" "Sepal.Width" "Petal.Length" "Petal.Width" "Species"]
           (i/col-names data)))
    (is (= ["5.1" "3.5" "1.4" "0.2" "setosa"] (first-row data)))))

(deftest cars
  (let [data (get-dataset :cars)]
    (is (= ["speed" "dist"] (i/col-names data)))
    (is (= ["4" "10"] (i/sel data :rows 1)))))

(deftest cars-csv
  (let [data (get-dataset :cars-csv)]
    (is (= ["speed" "dist"] (i/col-names data)))
    (is (= ["4" "2"] (first-row data)))))

(deftest cars-tdd
  (let [data (get-dataset :cars-tdd)]
    (is (= ["speed" "dist"] (i/col-names data)))
    (is (= ["4" "2"] (first-row data)))))

(deftest survey
  (let [data (get-dataset :survey)]
    (is (= ["n" "intercept" "age" "male" "white" "school" "income" "south" "outmig" "inmig" "empth" "toler" "selfless" "altru"]
           (i/col-names data)))
    (is (= ["1" "1" "53" "1" "1" "14" "110" "0" "0" "0" "28" "31" "13" "4"]
           (first-row data)))))

(deftest us-arrests
  (let [data (get-dataset :us-arrests)]
    (is (= ["State" "Murder" "Assault" "UrbanPop" "Rape"] (i/col-names data)))
    (is (= ["Alabama" "13.2" "236" "58" "21.2"] (first-row data)))))

(deftest flow-meter
  (let [data (get-dataset :flow-meter)]
    (is (= ["Subject" "Wright 1st PEFR" "Wright 2nd PEFR" "Mini Wright 1st PEFR" "Mini Wright 2nd PEFR"]
           (i/col-names data)))
    (is (= ["1" "494" "490" "512" "525"] (first-row data)))))

(deftest co2
  (let [data (get-dataset :co2)]
    (is (= ["Plant" "Type" "Treatment" "conc" "uptake"] (i/col-names data)))
    (is (= ["Qn1" "Quebec" "nonchilled" "95" "16.0"] (first-row data)))))

(deftest chick-weight
  (let [data (get-dataset :chick-weight)]
    (is (= ["weight" "Time" "Chick" "Diet"] (i/col-names data)))
    (is (= ["42" "0" "1" "1"] (first-row data)))))

(deftest math-prog
  (let [data (get-dataset :math-prog)]
    (is (= ["student number" "math exam" "programming course"]
           (i/col-names data)))
    (is (= ["01" "pass" "pass"] (first-row data)))))

(deftest plant-growth
  (let [data (get-dataset :plant-growth)]
    (is (= ["weight" "group"] (i/col-names data)))
    (is (= ["4.17" "ctrl"] (first-row data)))))

(deftest filip
  (let [data (get-dataset :filip)]
    (is (= ["y" "x"] (i/col-names data)))
    (is (= ["0.8116" "-6.860120914"] (first-row data)))))

(deftest pontius
  (let [data (get-dataset :pontius)]
    (is (= ["y" "x"] (i/col-names data)))
    (is (= [".11019" "150000"] (first-row data)))))

(deftest longley
  (let [data (get-dataset :longley)]
    (is (= ["y" "x1" "x2" "x3" "x4" "x5" "x6"] (i/col-names data)))
    (is (= ["60323" "83.0" "234289" "2356" "1590" "107608" "1947"]
           (first-row data)))))

(deftest chwirut
  (let [data (get-dataset :chwirut)]
    (is (= ["y" "x"] (i/col-names data)))
    (is (= ["92.9000E0" "0.5000E0"] (first-row data)))))

(deftest hair-eye-color
  (let [data (get-dataset :hair-eye-color)]
    (is (= ["hair" "eye" "gender" "count"] (i/col-names data)))
    (is (= ["black" "brown" "male" "32"] (first-row data)))))

(deftest austres
  (let [data (get-dataset :austres)]
    (is (= ["year" "population" "quarter"] (i/col-names data)))
    (is (= ["1972" "13254.2" "Q1"] (first-row data)))))

(deftest airline-passengers
  (let [data (get-dataset :airline-passengers)]
    (is (= ["year" "passengers" "month"] (i/col-names data)))
    (is (= ["1949" "112" "Jan"] (first-row data)))))

(deftest thurstone
  (let [data (get-dataset :thurstone)]
    (is (= ["y" "x"] (i/col-names data)))
    (is (= ["39" "10"] (first-row data)))))

(comment "This file has mismatched column counts."
  (deftest iran-election
    (let [data (get-dataset :iran-election)]
      (is (= ["Region" "Ahmadinejad" "%" "Rezai" "%" "Karrubi" "%" "Mousavi" "%" "Total votes" "Invalid votes" "Valid votes" "Eligible voters" "Turnout, %"]
             (i/col-names data)))
      (is (= ["South Khorasan" "285984" "75.0147546014684" "3962" "1.03924855142602" "928" "0.24341813622497" "90363" "23.7025787108806" "383157" "1920" "381237"]
             (first-row data))))))
