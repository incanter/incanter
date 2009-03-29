;;; charts.clj -- Charts library for Clojure built on JFreeChart

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 11, 2009: First version



(ns incanter.charts 
  (:use (incanter core stats)
        (clojure inspector)))




(defmacro histogram 
" Returns a JFreeChart object representing the histogram of the given data.
  Use the 'view' function to display the chart, or the 'save-png' function
  to write it to a file.

  Options:
    :nbins (default 10) number of bins
    :density (default false) if false, plots frequency, otherwise density
    :title (default 'Histogram') main title
    :x-label (default x expression)
    :y-label (default 'Frequency')
    :legend (default false) prints legend
    :series-label (default x expression)


  See also:
    view, save-png, add-histogram

  Examples:

    (use '(incanter charts stats))
    (view (histogram (sample-normal 1000)))

    # plot a density histogram
    (def hist (histogram (sample-normal 1000) :density true))
    (view hist)
    # add a normal density line to the plot
    (def x (range -4 4 0.01))
    (add-lines hist x (pdf-normal x))

    # plot some gamma data
    (def gam-hist (histogram (sample-gamma 1000) :density true :nbins 30))
    (view gam-hist) 
    (def x (range 0 8 0.01))
    (add-lines gam-hist x (pdf-gamma x))


  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([x & options]
    `(let [opts# (if '~options (assoc {} ~@options) nil)
          data# (if (matrix? ~x) (to-list ~x) ~x)
          nbins# (if (:nbins opts#) (:nbins opts#) 10)
          density?# (true? (:density opts#))
          main-title# (if (:title opts#) (:title opts#) "Histogram")
          x-lab# (if (:x-label opts#) (:x-label opts#) (str '~x))
          y-lab# (if (:y-label opts#) (:y-label opts#) 
                   (if density?# "Density" "Frequency"))
          series-lab# (if (:series-label opts#) (:series-label opts#) (str '~x))
          legend?# (true? (:legend opts#))
          dataset# (org.jfree.data.statistics.HistogramDataset.)
        ]
      (do
        (.addSeries dataset# series-lab# (double-array data#) nbins#)
        (when density?# (.setType dataset# org.jfree.data.statistics.HistogramType/SCALE_AREA_TO_1 ))
        (org.jfree.chart.ChartFactory/createHistogram 
            main-title#
            x-lab#
            y-lab#
            dataset# 
            org.jfree.chart.plot.PlotOrientation/VERTICAL 
            legend?# ; no legend 
            true  ; tooltips
            false)))))





(defmacro scatter-plot 
" Returns a JFreeChart object representing a scatter-plot of the given data.
  Use the 'view' function to display the chart, or the 'save-png' function
  to write it to a file.

  See also:
    view, save-png, add-points, add-lines

  Examples:
    (def x (sample-normal 100))
    (def err (sample-normal 100))
    (def y (plus (mult 1.5 x) err))
    (view (scatter-plot x y))

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([x y & options]
    `(let [opts# (if '~options (assoc {} ~@options))
          _x# (if (matrix? ~x) (to-list ~x) ~x)
          _y# (if (matrix? ~y) (to-list ~y) ~y)
          main-title# (if (:title opts#) (:title opts#) "Scatter Plot")
          x-lab# (if (:x-label opts#) (:x-label opts#) (str '~x))
          y-lab# (if (:y-label opts#) (:y-label opts#) (str '~y))
          series-lab# (if (:series-label opts#) (:series-label opts#) (format "%s, %s" '~x '~y))
          legend?# (true? (:legend opts#))
          data-series# (org.jfree.data.xy.XYSeries. series-lab#)
          dataset# (org.jfree.data.xy.XYSeriesCollection.)]
      (do
        (doseq [i# (range (count _x#))] (.add data-series# (nth _x# i#)  (nth _y# i#)))
        (.addSeries dataset# data-series#)
        (org.jfree.chart.ChartFactory/createScatterPlot 
            main-title#
            x-lab#
            y-lab#
            dataset# 
            org.jfree.chart.plot.PlotOrientation/VERTICAL 
            legend?# 
            true  ; tooltips
            false)))))



(defmacro line-plot 
  ([x y & options]
    `(let [opts# (if '~options (assoc {} ~@options))
          _x# (if (matrix? ~x) (to-list ~x) ~x)
          _y# (if (matrix? ~y) (to-list ~y) ~y)
          main-title# (if (:title opts#) (:title opts#) "XY Plot")
          x-lab# (if (:x-label opts#) (:x-label opts#) (str '~x))
          y-lab# (if (:y-label opts#) (:y-label opts#) (str '~y))
          series-lab# (if (:series-label opts#) (:series-label opts#) (format "%s, %s" '~x '~y))
          legend?# (true? (:legend opts#))
          data-series# (org.jfree.data.xy.XYSeries. series-lab#)
          dataset# (org.jfree.data.xy.XYSeriesCollection.)]
      (do
        (doseq [i# (range (count _x#))] (.add data-series# (nth _x# i#)  (nth _y# i#)))
        (.addSeries dataset# data-series#)
        (org.jfree.chart.ChartFactory/createXYLineChart  
            main-title#
            x-lab#
            y-lab#
            dataset# 
            org.jfree.chart.plot.PlotOrientation/VERTICAL 
            legend?# ; legend 
            true  ; tooltips
            false)))))



(defmacro box-plot 
  ([x & options]
    `(let [opts# (if '~options (assoc {} ~@options))
          data# (if (matrix? ~x) (to-list ~x) ~x)
          main-title# (if (:title opts#) (:title opts#) "Boxplot")
          ;x-label# (if (:x-label opts#) (:x-label opts#) (str '~x))
          x-label# (if (:x-label opts#) (:x-label opts#) "")
          y-label# (if (:y-label opts#) (:y-label opts#) "Values")
          series-label# (if (:series-label opts#) (:series-label opts#) (str '~x))
          category-label# (if (:category-label opts#) (:category-label opts#) 0)
          ;category-label# (if (:category-label opts#) (:category-label opts#) (str '~x))
          legend?# (true? (:legend opts#))
          dataset# (org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset.)
          chart# (org.jfree.chart.ChartFactory/createBoxAndWhiskerChart 
                    main-title#
                    x-label#
                    y-label#
                    dataset# 
                    legend?#)]
      (do
        (.add dataset# data# series-label# category-label#)
        (.. chart# getCategoryPlot getRenderer (setMaximumBarWidth 0.25))
        chart#))))



(defmacro add-histogram 
"

  Examples:

    (use '(incanter charts stats))
    (def hist (histogram (sample-normal 1000) 
                :legend true))
    (view hist)
    (add-histogram hist (sample-normal 1000 :sd 0.5))

"
  ([chart x & options]
    `(let [opts# (if '~options (assoc {} ~@options))
          _x# (if (matrix? ~x) (to-list ~x) ~x)
          data-plot# (.getPlot ~chart)
          n# (.getDatasetCount data-plot#)
          nbins# (if (:nbins opts#) (:nbins opts#) 10)
          series-lab# (if (:series-label opts#) (:series-label opts#) (str '~x))
        ]
      (do
        (.addSeries (.getDataset data-plot#) series-lab# (double-array _x#) nbins#)
        (.setSeriesRenderingOrder data-plot# org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
        (.fireChartChanged ~chart)))))



(defmacro add-box-plot 
"
    Examples:

      (use '(incanter charts stats))
      (def boxplt (box-plot (sample-normal 1000) :legend true))
      (view boxplt)
      (add-box-plot boxplt (sample-normal 1000 :sd 2))
      (add-box-plot boxplt (sample-gamma 1000))

"
  ([chart x & options]
    `(let [opts# (if '~options (assoc {} ~@options))
          _x# (if (matrix? ~x) (to-list ~x) ~x)
          data-plot# (.getCategoryPlot ~chart)
          n-col# (.getColumnCount (.getDataset data-plot#)) 
          n-row# (.getRowCount (.getDataset data-plot#))
          series-label# (if (:series-label opts#) (:series-label opts#) (str '~x))
          category-label# (if (:category-label opts#) 
                            (:category-label opts#) 
                            ;(str '~x))
                            (str n-col#))
        ]
      (do
        (.add (.getDataset data-plot#) _x# series-label# category-label#)))))




(defn set-alpha 
  ([chart alpha] (.setForegroundAlpha (.getPlot chart) alpha)))


(defn set-background-alpha 
  ([chart alpha] (.setBackgroundAlpha (.getPlot chart) alpha)))


(defn clear-background 
  ([chart] (.setBackgroundAlpha (.getPlot chart) 0.0)))



(defn set-title 
  ([chart title] (.setTitle chart title)))


(defn set-x-label 
  ([chart label] (.setLabel (.getDomainAxis (.getPlot chart)) label)))


(defn set-y-label 
  ([chart label] (.setLabel (.getRangeAxis (.getPlot chart)) label)))



 
;; FUNCTION TO ADD LINES TO SCATTER PLOT
;;



(defmacro add-lines
" Plots lines on the given scatter-plot of the (x,y) points.
  Equivalent to R's lines function.

  Examples:

    (use '(incanter core stats io datasets charts))
    (def speed (to-matrix (get-dataset :speed)))
    (def y (sel speed :columns 1))
    (def x (sel speed :columns 2))
    (def plot1 (scatter-plot x y :legend true))
    (view plot1)
    
    ;; add regression line to scatter plot
    (def lm1 (linear-model y x))
    (add-lines plot1 x (:fitted lm1))

    ;; model the data without an intercept
    (def lm2 (linear-model y x :intercept false))
    (add-lines plot1 x (:fitted lm2))


"
  ([chart x y & options]
    `(let [opts# (if '~options (assoc {} ~@options))
        _x# (if (matrix? ~x) (to-list ~x) ~x)
        _y# (if (matrix? ~y) (to-list ~y) ~y)
        data-plot# (.getPlot ~chart)
        n# (.getDatasetCount data-plot#)
        series-lab# (if (:series-label opts#) (:series-label opts#) (format "%s, %s" '~x '~y))
        ;legend?# (if (false? (:legend opts#)) false true)
        ;points?# (if (false? (:points opts#)) false true) ;; TODO
        data-series# (org.jfree.data.xy.XYSeries. series-lab#)
        line-renderer# (org.jfree.chart.renderer.xy.XYLineAndShapeRenderer. true false)
        data-set# (org.jfree.data.xy.XYSeriesCollection.)
       ]
    (do
      (doseq [i# (range (count _x#))] (.add data-series# (nth _x# i#)  (nth _y# i#)))
      ;(.addSeries (.getDataset data-plot) data-series)
      (.setSeriesRenderingOrder (.getPlot ~chart) org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
      (.setDatasetRenderingOrder data-plot# org.jfree.chart.plot.DatasetRenderingOrder/FORWARD) 
      (.addSeries data-set# data-series#)
      (.setDataset data-plot# (.getDatasetCount data-plot#) data-set#)
      ;(.setSeriesPaint line-renderer 1 java.awt.Color/blue)
      (.setRenderer data-plot# (dec (.getDatasetCount data-plot#)) line-renderer#)))))




(defmacro add-points
" Plots points on the given scatter-plot or line-plot of the (x,y) points.
  Equivalent to R's lines function.

  Examples:

    (use '(incanter core stats io datasets charts))
    (def speed (to-matrix (get-dataset :speed)))
    (def y (sel speed :columns 1))
    (def x (sel speed :columns 2))
    
    ;; add regression line to scatter plot
    (def lm1 (linear-model y x))
    (def plot1 (line-plot x (:fitted lm1) :legend true))
    (view plot1)

    (add-points plot1 x y)

    ;; model the data without an intercept
    (def lm2 (linear-model y x :intercept false))
    (add-lines plot1 x (:fitted lm2))


"
  ([chart x y & options]
    `(let [opts# (if '~options (assoc {} ~@options))
        _x# (if (matrix? ~x) (to-list ~x) ~x)
        _y# (if (matrix? ~y) (to-list ~y) ~y)
        data-plot# (.getPlot ~chart)
        n# (.getDatasetCount data-plot#)
        series-lab# (if (:series-label opts#) (:series-label opts#) (format "%s, %s" '~x '~y))
        ;legend?# (if (false? (:legend opts#)) false true)
        ;points?# (if (false? (:points opts#)) false true) ;; TODO
        data-series# (org.jfree.data.xy.XYSeries. series-lab#)
        line-renderer# (org.jfree.chart.renderer.xy.XYLineAndShapeRenderer. false true)
        data-set# (org.jfree.data.xy.XYSeriesCollection.)
       ]
    (do
      (doseq [i# (range (count _x#))] (.add data-series# (nth _x# i#)  (nth _y# i#)))
      ;(.addSeries (.getDataset data-plot) data-series)
      (.setSeriesRenderingOrder (.getPlot ~chart) org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
      (.setDatasetRenderingOrder (.getPlot ~chart) org.jfree.chart.plot.DatasetRenderingOrder/FORWARD)
      (.addSeries data-set# data-series#)
      (.setDataset data-plot# (.getDatasetCount data-plot#) data-set#)
      ;(.setSeriesPaint line-renderer 1 java.awt.Color/blue)
      (.setRenderer data-plot# (dec (.getDatasetCount data-plot#)) line-renderer#)))))




(defn trace-plot
  " Returns a trace-plot object, use the 'view' function to display it.

    Examples:
      (use '(incanter core datasets stats bayes charts))
      (def ols-data (to-matrix (get-dataset :survey)))
      (def x (sel ols-data (range 0 2313) (range 1 10)))
      (def y (sel ols-data (range 0 2313) 10))
      (def sample-params (sample-model-params 5000 (linear-model y x :intercept false)))
      (view (trace-plot (:var sample-params))) 

      (view (trace-plot (sel (:coefs sample-params) :columns 0)))

"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          title (if (:title opts) (:title opts) "Trace Plot")
          x-label (if (:x-label opts) (:x-label opts) "Iteration")
          y-label (if (:y-label opts) (:y-label opts) "Value")
          series-lab (if (:series-label opts) (:series-label opts) "Value")
          ;legend? (if (:series-label opts) (:series-label opts) true)
          n (count x)
          chart (line-plot (range n) 
                         x )]
                         ;:title title 
                         ;:x-label x-label 
                         ;:y-label y-label
                         ;:series-label series-lab)]
      (do
        (add-lines chart (range n) (cumulative-mean x) :series-label "running mean")
        (.setSeriesRenderingOrder (.getPlot chart) org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
        (.setDatasetRenderingOrder (.getPlot chart) org.jfree.chart.plot.DatasetRenderingOrder/FORWARD)
        chart))))




(defn qq-plot
"
  Returns a QQ-Plot object. Use the 'view' function to display it.

  References:
    http://en.wikipedia.org/wiki/QQ_plot

  Examples:
    
    (use '(incanter stats charts))
    (view (qq-plot (sample-normal 100)))
    (view (qq-plot (sample-exp 100)))
    (view (qq-plot (sample-gamma 100)))


"
  ([x & options]
   (let [n (count x)
         quants (for [k (range 1 n)] (/ k (inc n)))
         norm-quants (quantile-normal quants)
         y (quantile x :probs quants)]
         (scatter-plot norm-quants y 
                   :title "QQ-Plot"
                   :x-label "Normal theoretical quantiles"
                   :y-label "Data quantiles"
                   :series-label "Theoretical Normal"))))




(defn bland-altman-plot
"

  Examples:

    (use '(incanter core datasets charts))
    (def flow-meter (to-matrix (get-dataset :flow-meter)))
    (def x1 (sel flow-meter :columns 1))
    (def x2 (sel flow-meter :columns 3))
    (view (bland-altman-plot x1 x2))


  References:
    http://en.wikipedia.org/wiki/Bland-Altman_plot
    http://www-users.york.ac.uk/~mb55/meas/ba.htm

"
  ([x1 x2]
      (let [plot (scatter-plot (div (plus x1 x2) 2) (minus x1 x2) 
                               :title "Bland Altman Plot"
                               :legend false)
            x-axis (div (plus x1 x2) 2)
            y-axis (minus x1 x2)
            min-x (reduce min x-axis)
            max-x (reduce max x-axis)
            x (range min-x max-x (/ (- max-x min-x) 100))
            y-sd (* (sd y-axis) 2)]
        (do
          (add-lines plot x (repeat (count x) 0) :series-label "mean")
          (add-lines plot x (repeat (count x) y-sd) :series-label "mean + sd")
          (add-lines plot x (repeat (count x) (- 0 y-sd)) :series-label "mean - sd")
          plot))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; view multi-method for matrices, charts, etc.
(defmulti view 
  " This is a general 'view' function. If given a matrix, it will
    display it in a Java Swing table (using clojure.inspector/inspect-table).
    If given a chart object from incanter.charts, it will display it in a
    new window."
  (fn [obj & args] (class obj)))


(defmethod view incanter.Matrix 
  ([obj & args] (inspect-table obj)))


(defmethod view org.jfree.chart.JFreeChart 
  ([chart & options]
    (let [opts (if options (apply assoc {} options) nil)
          window-title (if (:window-title opts) (:window-title opts) "Incanter Plot")
          width (if (:width opts) (:width opts) 500)
          height (if (:height opts) (:height opts) 400)
          frame (org.jfree.chart.ChartFrame. window-title chart)]
      (doto frame
        (.setSize width height)
        (.setVisible true))
      nil)))


(defn save-png 
  ([chart filename & options]
    (let [opts (if options (apply assoc {} options) nil)
          width (if (:width opts) (:width opts) 500)
          height (if (:height opts) (:height opts) 400)]
      (org.jfree.chart.ChartUtilities/saveChartAsPNG (java.io.File. filename) chart width height)
      nil)))



