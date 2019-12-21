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


(deftest bar-chart-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (bar-chart 0 1 :data (to-dataset [[1 2 3]]))))))

(deftest bar-chart-single-row-with-group
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (bar-chart 0 1 :data (to-dataset [[1 2 3]]) :group-by 2)))))

(deftest pie-chart-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (pie-chart 0 1 :data (to-dataset [[1 2 3]]))))))

(deftest scatter-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (scatter-plot 0 1 :data (to-dataset [[1 2 3]]))))))

(deftest scatter-single-row-with-group
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (scatter-plot 0 1 :data (to-dataset [[1 2 3]]) :group-by 2)))))

(deftest scatter-group-by
  (let [iris (get-dataset :iris)]
    (is (not (nil? (scatter-plot ($ :Sepal.Width iris) ($ :Sepal.Length iris)
                                 :group-by ($ :Species iris)))))
    (is (not (nil? (scatter-plot :Sepal.Length :Sepal.Width
                                 :group-by :Species :data iris))))))

(deftest area-chart-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (area-chart 0 1 :data (to-dataset [[1 2 3]]))))))

(deftest area-chart-single-row-with-group
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (area-chart 0 1 :data (to-dataset [[1 2 3]]) :group-by 2)))))

(deftest stacked-area-chart-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (stacked-area-chart 0 1 :data (to-dataset [[1 2 3]]))))))

(deftest stacked-area-chart-single-row-with-group
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (stacked-area-chart 0 1 :data (to-dataset [[1 2 3]]) :group-by 2)))))

(deftest stacked-bar-chart-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (stacked-bar-chart 0 1 :data (to-dataset [[1 2 3]]))))))

(deftest stacked-bar-chart-single-row-with-group
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (stacked-bar-chart 0 1 :data (to-dataset [[1 2 3]]) :group-by 2)))))

(deftest box-plot-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (box-plot 0 :data (to-dataset [[1 2 3]]))))))

(deftest box-plot-single-row-with-group
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (box-plot 0 :data (to-dataset [[1 2 3]]) :group-by 2)))))

(deftest box-plot-groups-with-single-elements
  "Asserting that no error is thrown, for a dataset that represents several groups with single element in each group."
  (is (not (nil? (box-plot 0 :data (to-dataset [[1 2 3] [4 5 6] [7 8 9]]) :group-by 2)))))

(deftest trace-plot-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (trace-plot 0 :data (to-dataset [[1 2 3]]))))))

(deftest bland-altman-plot-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (bland-altman-plot 0 1 :data (to-dataset [[1 2 3]]))))))

(deftest line-chart-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (line-chart 0 1 :data (to-dataset [[1 2 3]]))))))

(deftest line-chart-single-row-with-group
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (line-chart 0 1 :data (to-dataset [[1 2 3]]) :group-by 2)))))

(deftest histogram-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (histogram 0 :data (to-dataset [[1 2 3]]))))))

(deftest xy-plot-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (xy-plot 0 1 :data (to-dataset [[1 2 3]]))))))

(deftest polar-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (polar-chart [[34 93] [83 03] [78 138]])))))            ;

(deftest add-histogram-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (let [data (to-dataset [[1 2 3]])
        hist (histogram 0 :data data)]
  (is (not (nil? (add-histogram hist 1 :data data))))))

(deftest add-box-plot-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (let [data (to-dataset [[1 2 3]])
        box (box-plot 0 :data data)]
  (is (not (nil? (add-box-plot box 1 :data data))))))

(deftest add-categories-single-row
  "Asserting that no error is thrown, for a single item dataset"
   (let [data (to-dataset [[1 2 3]])
         chart (line-chart 0 1 :data data)]
  (is (not (nil? (add-categories chart 2 1 :data data))))))

(deftest add-categories-single-row-with-group
  "Asserting that no error is thrown, for a single item dataset"
   (let [data (to-dataset [[1 2 3]])
         chart (line-chart 0 1 :data data)]
  (is (not (nil? (add-categories chart 2 1 :data data :group-by 0))))))

(deftest add-lines-single-row
  "Asserting that no error is thrown, for a single item dataset"
   (let [data (to-dataset [[1 2 3]])
         plot (scatter-plot 0 1 :data data)]
  (is (not (nil? (add-lines plot 2 1 :data data))))))

(deftest add-points-single-row
  "Asserting that no error is thrown, for a single item dataset"
   (let [data (to-dataset [[1 2 3]])
         plot (scatter-plot 0 1 :data data)]
  (is (not (nil? (add-points plot 2 1 :data data))))))

(deftest add-polar-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (let [plot (polar-chart [[34 93] [83 03] [78 138]] :series-label "Polar one")]
    (is (not (nil? (add-polar plot [[34 93] [83 03] [78 138] [101 145] [23 144]] :series-label "Polar two"))))))

(deftest ring-chart-single-row
  "Asserting that no error is thrown, for a single item dataset"
  (is (not (nil? (ring-chart 0 1 :data (to-dataset [[1 2 3]]))))))

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


(defn scatter-tests [m]
  (def sw1 (view (scatter-plot
                  (sel m :cols 0)
                  (sel m :cols 0)
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
  (set-point-size plot1 1 :series 0)
  (set-point-size plot1 10 :series 1)
  (set-point-size plot1 5)
  (Thread/sleep wait-timeout)
  (.dispose sw1)
  (.dispose sw2))

(deftest boxplot-tests
  (def boxplt (box-plot (sample-gamma 1000)))
  (def bw1 (view boxplt))
  (add-box-plot boxplt (sample-gamma 1000))
  (add-box-plot boxplt (sample-gamma 1000))
  (add-box-plot boxplt (sample-gamma 1000))
  (add-box-plot boxplt (sample-gamma 1000))
  (Thread/sleep wait-timeout)
  (.dispose bw1))


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
  (.dispose cw3))

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


(defn bar-chart-tests []
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

(defn line-chart-tests []
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
  (.dispose lw3))


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
  (let [test-data1 (incanter.core/dataset
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
        test-data2 (incanter.core/dataset
                                          (map
                                           (fn [day]
                                             (let [open (+ day (rand-int 10))
                                                   close (+ day (rand-int 10))
                                                   high (+ (max open close) (rand-int 10))
                                                   low (- (min open close) (rand-int 10))
                                                   volume (rand-int 1000000)]
                                               {:date (str "2011-10-" day)
                                                :high high
                                                :low low
                                                :open open
                                                :close close
                                                :volume volume}))
                                          (range 1 31)))
        fw1 (view (candle-stick-plot :data test-data1))
        fw2 (view (candle-stick-plot :data test-data2))]
    (Thread/sleep wait-timeout)
    (.dispose fw1)
    (.dispose fw2)))

(deftest polar-chart-tests
  (let [sw1 (view (polar-chart [[13 15] [34 23] [23 45]] :series-label "A"))
        plot1 (polar-chart [[130 150] [340 230] [230 450]] :series-label "A"
                           :title "Relation between A, B and C")
        sw2 (view plot1)]
    (add-polar plot1 [[130 180] [380 290] [240 460]] :series-label "B")
    (add-polar plot1 [[150 120] [190 200] [390 300] [260 500]] :series-label "C")

    (set-title plot1 "new title")
    (set-point-size plot1 1 :series 0)
    (set-point-size plot1 10 :series 1)
    (set-point-size plot1 5 :series 2)
    (set-point-size plot1 5)
    (Thread/sleep wait-timeout)
    (.dispose sw1)
    (.dispose sw2)))


(deftest ring-chart-tests
  (def pw1 (view (ring-chart ["a" "b" "c"] [10 20 30])))
  (Thread/sleep wait-timeout)
  (.dispose pw1))

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

(deftest log-axis-tests
  (let [a10 (log-axis)]
    (is (isa? (type a10) org.jfree.chart.axis.ValueAxis))
    (is (= "1" (.. a10 (getStandardTickUnits) (getCeilingTickUnit 0.0) (valueToString 1.0)))))
  ;; TODO: FIX IT in locale-independent way
  (comment  (let [a2 (log-axis :base 2, :label "my precious", :int-ticks? false)]
              (is (= 2.0 (.getBase a2)))
              (is (= "my precious" (.getLabel a2)))
              (is (= "10^0.0" (.. a2 (getStandardTickUnits) (get 0) (valueToString 1.0)))))))

(deftest set-axis-tests
  (let [make-xy #(xy-plot (range 1) (range 1))
        pie-chart (pie-chart ["a"] [10])
        scatter-chart (scatter-plot [4] [2])
        axis (log-axis)]
    (testing "chart with X log axis"
      (let [ch (set-axis (make-xy) :x axis)]
        (is (= axis (.. ch (getPlot) (getDomainAxis))))
        (is (not= axis (.. ch (getPlot) (getRangeAxis))))))
    (testing "chart with Y log axis"
      (let [ch (set-axis (make-xy) :y axis)]
        (is (not= axis (.. ch (getPlot) (getDomainAxis))))
        (is (= axis (.. ch (getPlot) (getRangeAxis))))))
    (testing "Another XY-like chart"
      (is (= axis (.. (set-axis scatter-chart :x axis)
                      (getPlot)
                      (getDomainAxis)))))
    (testing "unsupported chart type"
      (try
        (set-axis pie-chart :x axis)
        (assert false "Should have failed for set-axis doesn't support PieChart")
        (catch AssertionError e)))))

(deftest extend-line-tests
  (let [chart (xy-plot [1 2 3 4] [1 2 3 4] :series-label :series1)]
    (extend-line chart [5 6 7] [10 11 12] :series-label :series1)
    (extend-line chart [1 2 3 4 5] [5 6 7 8 9] :series-label :series2)
    (is (= (has-series? chart :series2) true))
    (remove-series chart :series2)
    (is (= (has-series? chart :series2) false))))

(deftest multi-chart-tests
  (let [chart1 (xy-plot [1 2 3 4] [1 2 3 4] :series-label :series1)
        chart2 (histogram (sample-normal 1000))
        x (range (* -2 Math/PI) (* 2 Math/PI) 0.01)
        chart3 (xy-plot x (sin x))
        x1 (range -10 10 0.01)
        chart4 (xy-plot x1 (pow x1 2))
        mc (multi-chart chart1 chart2 chart3 chart4)
        hw1 (view mc)]
    (Thread/sleep wait-timeout)
    (.dispose hw1)))

;; (run-tests)

(deftest compliance-test
  (doseq [impl [:vectorz :ndarray :persistent-vector]]
    (set-current-implementation impl)
    (println (str "compliance test " impl))
    (let [m (matrix [[39      10 ]
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
                     [148     250]])]
      (scatter-tests m)
      (bar-chart-tests)
      (line-chart-tests)
      (polar-chart-tests))))
