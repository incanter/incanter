
;;; test-cases.clj -- Unit tests of Incanter functions

;; by David Edgar Liebke http://incanter.org
;; March 12, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 12, 2009: First version

;; to run these tests:
;; (use 'tests test-cases)
;;  need to load this file to define data variables
;; (use 'clojure.contrib.test-is)
;; then run tests
;; (run-tests 'incanter.tests.test-cases)

(ns incanter.charts-tests
  (:use clojure.test
        (incanter core stats charts datasets)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; TESTS FOR incanter.charts.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def wait-timeout 3000)

(def test-mat (matrix
  [[39      10 ]
   [51      20 ]
   [60      30 ]
   [64      40 ]
   [73      50 ]
   [83      60 ]
   [90      70 ]
   [93      80 ]
   [99      90 ]
   [105     100]
   [110     110]
   [111     120]
   [113     130]
   [117     140]
   [120     150]
   [125     160]
   [130     170]
   [133     180]
   [133     190]
   [134     200]
   [138     210]
   [140     220]
   [145     230]
   [146     240]
   [148     250]]))


(deftest histogram-tests
  (def hw1 (view (histogram (sample-normal 1000))))
  (def hw2 (view (histogram (sample-gamma 1000))))
  (def hw3 (view (histogram (sample-uniform 1000))))
  (save (histogram (sample-normal 1000))
        (new java.io.File (System/getProperty "java.io.tmpdir")
                                "norm_hist.png"))

  (def hw4 (view (histogram (sample-gamma 1000)
                            :nbins 30
                            :title "Gamma Distribution"
                            :x-label "Value")))

  (def hist0 (histogram (sample-normal 100)))
  (def hw5 (view hist0))
  (add-histogram hist0 (sample-gamma 100))
  (set-alpha hist0 0.5)
  (Thread/sleep wait-timeout)
  (.dispose hw1)
  (.dispose hw2)
  (.dispose hw3)
  (.dispose hw4)
  (.dispose hw5)
  )


(deftest scatter-tests
  (def sw1 (view (scatter-plot
                  (sel test-mat :cols 0)
                  (sel test-mat :cols 0)
                  :series-lab "Test data col 1 versus col 2")))


  (def plot1 (scatter-plot (sample-normal 100) (sample-normal 100)))
  (def sw2 (view plot1))
  (add-points plot1 (sample-normal 100) (sample-normal 100))
  (add-points plot1 (sample-normal 100) (sample-normal 100))
  (add-points plot1 (sample-normal 100) (sample-normal 100))
  (add-points plot1 (sample-normal 100) (sample-normal 100))

  (set-title plot1 "new title")
  (set-x-label plot1 "new x label")
  (set-y-label plot1 "new y label")
  (Thread/sleep wait-timeout)
  (.dispose sw1)
  (.dispose sw2)
  )


(deftest boxplot-tests
  (def boxplt (box-plot (sample-gamma 1000)))
  (def bw1 (view boxplt))
  (add-box-plot boxplt (sample-gamma 1000))
  (add-box-plot boxplt (sample-gamma 1000))
  (add-box-plot boxplt (sample-gamma 1000))
  (add-box-plot boxplt (sample-gamma 1000))
  (Thread/sleep wait-timeout)
  (.dispose bw1)
  )


(deftest xy-plot-tests
  (def chart1 (xy-plot (range 100) (range 100)))
  (def cw1 (view chart1))
  (add-lines chart1 (range 100) (mult 1/2 (range 100)))


  (def x1 (range -10 10 0.01))
  (def chart2 (xy-plot x1 (pow x1 2)))
  (def cw2 (view chart2))
  (add-lines chart2 x1 (mult 1/2 (pow x1 2)))


  (def x2 (range 0 4 0.01))
  (def chart2 (xy-plot x2 (exp x1)))
  (def cw3 (view chart2))
  (Thread/sleep wait-timeout)
  (.dispose cw1)
  (.dispose cw2)
  (.dispose cw3)
  )

(deftest time-series-plot-tests
  (def epoch 0)
  (defn num-years-to-milliseconds [x]
    (* 365 24 60 60 1000 x))
  (def dates (map num-years-to-milliseconds (range 100)))
  (def chart1 (time-series-plot dates (range 100)))
  (def cw1 (view chart1))
  (add-lines chart1 dates (mult 1/2 (range 100)))

  (def chart2 (time-series-plot (take 10 dates) (mult 1/2 (range 10))
               :y-label "number of units"
               :x-label "years"
               :title "Units Sold"))
  (def cw2 (view chart2))
  (Thread/sleep wait-timeout)
  (.dispose cw1)
  (.dispose cw2))


(deftest bar-chart-tests
  (def data (get-dataset :co2))
  (def grass-type (sel data :cols 1))
  (def treatment-type (sel data :cols 2))
  (def uptake (sel data :cols 4))
  (def bw1 (view (bar-chart grass-type uptake
                            :title "CO2 Uptake"
                            :group-by treatment-type
                            :x-label "Grass Types" :y-label "Uptake"
                            :legend true)))


  (def data (get-dataset :airline-passengers))
  (def years (sel data :cols 0))
  (def months (sel data :cols 2))
  (def passengers (sel data :cols 1))
  (def bw2 (view (bar-chart years passengers :group-by months :legend true)))
  (def bw3 (view (bar-chart months passengers :group-by years :legend true)))
  (Thread/sleep wait-timeout)
  (.dispose bw1)
  (.dispose bw2)
  (.dispose bw3))


(deftest pie-chart-tests
  (def pw1 (view (pie-chart ["a" "b" "c"] [10 20 30])))
  (Thread/sleep wait-timeout)
  (.dispose pw1))


(deftest line-chart-tests
  (def data (get-dataset :airline-passengers))
  (def years (sel data :cols 0))
  (def months (sel data :cols 2))
  (def passengers (sel data :cols 1))
  (def lw1 (view (line-chart years passengers :group-by months :legend true)))
  (def lw2 (view (line-chart months passengers :group-by years :legend true)))


  (def seasons (mapcat identity (repeat 3 ["winter" "spring" "summer" "fall"])))
  (def years (mapcat identity (repeat 4 [2007 2008 2009])))
  (def x (sample-uniform 12 :integers true :max 100))
  (def lw3 (view (line-chart years x :group-by seasons :legend true)))
  (Thread/sleep wait-timeout)
  (.dispose lw1)
  (.dispose lw2)
  (.dispose lw3)
  )


(deftest function-plot-tests
  (def fw1 (view (function-plot sin (- Math/PI) Math/PI)))
  (def fw2 (view (function-plot pdf-normal -3 3)))

  (defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))
  (def fw3 (view (function-plot cubic -10 10)))
  (Thread/sleep wait-timeout)
  (.dispose fw1)
  (.dispose fw2)
  (.dispose fw3))


(deftest heat-map-tests
  (defn f [x y] (sin (sqrt (plus (sq x) (sq y)))))
  (def hw1 (view (heat-map f -10 10 -10 10)))
  (def hw2 (view (heat-map f -10 10 -10 10 :color? false)))
  (Thread/sleep wait-timeout)
  (.dispose hw1)
  (.dispose hw2))

(deftest candle-stick-tests
  (let [test-data (incanter.core/dataset [:date :high :low :open :volume :close]
                                         (map
                                          (fn [day]
                                            (let [open (+ day (rand-int 10))
                                                  close (+ day (rand-int 10))
                                                  high (+ (max open close) (rand-int 10))
                                                  low (- (min open close) (rand-int 10))
                                                  volume (rand-int 1000000)]
                                              {:date (java.util.Date. 2011 10 day)
                                               :high high
                                               :low low
                                               :open open
                                               :close close
                                               :volume volume}))
                                          (range 1 31)))
        fw1 (view (candle-stick-plot :data test-data))]
    (Thread/sleep wait-timeout)
    (.dispose fw1)))



(deftest annotations-tests
  (def x (range (* -2 Math/PI) (* 2 Math/PI) 0.01))
  (def plot (xy-plot x (sin x)))
  (def aw1 (view plot))
  ;; annotate the plot
  (doto plot
    (add-pointer (- Math/PI) (sin (- Math/PI)) :text "(-pi, (sin -pi))")
    (add-pointer Math/PI (sin Math/PI) :text "(pi, (sin pi))" :angle :ne)
    (add-pointer (* 1/2 Math/PI) (sin (* 1/2 Math/PI)) :text "(pi/2, (sin pi/2))" :angle :south))

  ;; try the different angle options
  (add-pointer plot 0 0 :text "north" :angle :north)
  (add-pointer plot 0 0 :text "nw" :angle :nw)
  (add-pointer plot 0 0 :text "ne" :angle :ne)
  (add-pointer plot 0 0 :text "west" :angle :west)
  (add-pointer plot 0 0 :text "east" :angle :east)
  (add-pointer plot 0 0 :text "south" :angle :south)
  (add-pointer plot 0 0 :text "sw" :angle :sw)
  (add-pointer plot 0 0 :text "se" :angle :se)

  (add-text plot -5 -0.75 "-5, -0.75")

  (add-polygon plot [[(- Math/PI) -1] [Math/PI -1] [Math/PI 1] [(- Math/PI) 1]])
  (Thread/sleep wait-timeout)
  (.dispose aw1)
  )

;(run-tests)
