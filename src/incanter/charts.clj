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





(defn histogram 
" Returns a JFreeChart object representing the histogram of the given data.
  Use the 'view' function to display the chart, or the 'save-png' function
  to write it to a file.

  See also:
    view, save-png, add-series

  Examples:
    (view (histogram (sample-normal 1000)))

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          data (if (matrix? x) (to-list x) x)
          nbins (if (:nbins opts) (:nbins opts) 10)
          main-title (if (:title opts) (:title opts) "Histogram")
          x-lab (if (:x-label opts) (:x-label opts) "X")
          y-lab (if (:y-label opts) (:y-label opts) "Frequency")
          series-lab (if (:series-label opts) (:series-label opts) "Data")
          legend? (if (:series-label opts) true false)
          dataset (org.jfree.data.statistics.HistogramDataset.)
        ]
      (do
        (.addSeries dataset series-lab (double-array data) nbins)
        (org.jfree.chart.ChartFactory/createHistogram 
            main-title
            x-lab
            y-lab
            dataset 
            org.jfree.chart.plot.PlotOrientation/VERTICAL 
            legend? ; no legend 
            true  ; tooltips
            false)))))



(defn scatter-plot 
" Returns a JFreeChart object representing a scatter-plot of the given data.
  Use the 'view' function to display the chart, or the 'save-png' function
  to write it to a file.

  See also:
    view, save-png, add-series

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
    (let [opts (if options (apply assoc {} options) nil)
          _x (if (matrix? x) (to-list x) x)
          _y (if (matrix? y) (to-list y) y)
          main-title (if (:title opts) (:title opts) "Scatter Plot")
          x-lab (if (:x-label opts) (:x-label opts) "x")
          y-lab (if (:y-label opts) (:y-label opts) "y")
          series-lab (if (:series-label opts) (:series-label opts) "Data")
          legend? (if (:series-label opts) true false)
          data-series (org.jfree.data.xy.XYSeries. series-lab)
          dataset (org.jfree.data.xy.XYSeriesCollection.)]
      (do
        (doseq [i (range (count _x))] (.add data-series (nth _x i)  (nth _y i)))
        (.addSeries dataset data-series)
        (org.jfree.chart.ChartFactory/createScatterPlot 
            main-title
            x-lab
            y-lab
            dataset 
            org.jfree.chart.plot.PlotOrientation/VERTICAL 
            legend? ; legend 
            true  ; tooltips
            false)))))



(defn xy-plot 
  ([x y & options]
    (let [opts (if options (apply assoc {} options) nil)
          _x (if (matrix? x) (to-list x) x)
          _y (if (matrix? y) (to-list y) y)
          main-title (if (:title opts) (:title opts) "XY Plot")
          x-lab (if (:x-label opts) (:x-label opts) "x")
          y-lab (if (:y-label opts) (:y-label opts) "y")
          series-lab (if (:series-label opts) (:series-label opts) "Data")
          legend? (if (:series-label opts) true false)
          data-series (org.jfree.data.xy.XYSeries. series-lab)
          dataset (org.jfree.data.xy.XYSeriesCollection.)]
      (do
        (doseq [i (range (count _x))] (.add data-series (nth _x i)  (nth _y i)))
        (.addSeries dataset data-series)
        (org.jfree.chart.ChartFactory/createXYLineChart  
            main-title
            x-lab
            y-lab
            dataset 
            org.jfree.chart.plot.PlotOrientation/VERTICAL 
            legend? ; legend 
            true  ; tooltips
            false)))))


(defn box-plot 
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          data (if (matrix? x) (to-list x) x)
          main-title (if (:title opts) (:title opts) "Boxplot")
          x-label (if (:x-label opts) (:x-label opts) "Series")
          y-label (if (:y-label opts) (:y-label opts) "Values")
          series-label (if (:series-label opts) (:series-label opts) "X1")
          category-label (if (:series-label opts) (:series-label opts) "Category 1")
          legend? (if (:series-label opts) true false)
          dataset (org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset.)
          chart (org.jfree.chart.ChartFactory/createBoxAndWhiskerChart 
                    main-title
                    x-label
                    y-label
                    dataset 
                    legend?)]
      (do
        (.add dataset data series-label category-label)
        (.. chart getCategoryPlot getRenderer (setMaximumBarWidth 0.25))
        chart))))


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


(defmulti add-series (fn [chart & args] (class (.. chart getPlot getDataset))))


(defmethod add-series org.jfree.data.xy.XYSeriesCollection 
  ([chart x y & options]
    (let [opts (if options (apply assoc {} options) nil)
          _x (if (matrix? x) (to-list x) x)
          _y (if (matrix? y) (to-list y) y)
          data-plot (.getPlot chart)
          n (.getDatasetCount data-plot)
          series-lab (if (:series-label opts) (:series-label opts) (str "Data " n))
          legend? (if (:series-label opts) true false)
          ;lines? (if (:lines opts) (:lines opts) false)
          ;points? (if (:lines opts) (:lines opts) true)
          ;line-renderer (org.jfree.chart.renderer.xy.XYLineAndShapeRenderer. lines? points?)
          data-series (org.jfree.data.xy.XYSeries. series-lab)
          ;data-set (org.jfree.data.xy.XYSeriesCollection.)
        ]
      (do
        (doseq [i (range (count _x))] (.add data-series (nth _x i)  (nth _y i)))
        (.setSeriesRenderingOrder data-plot org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
        (.setDatasetRenderingOrder data-plot org.jfree.chart.plot.DatasetRenderingOrder/FORWARD)
        ;(.setDataset data-plot (inc (.getDatasetCount data-plot)) data-set)
        ;(.setRenderer data-plot (inc (.getDatasetCount data-plot)) line-renderer)
        ;(.addSeries data-set data-series))))
        (.addSeries (.getDataset data-plot) data-series)))))



(defmethod add-series org.jfree.data.statistics.HistogramDataset 
  ([chart x & options]
    (let [opts (if options (apply assoc {} options) nil)
          _x (if (matrix? x) (to-list x) x)
          data-plot (.getPlot chart)
          n (.getDatasetCount data-plot)
          nbins (if (:nbins opts) (:nbins opts) 10)
          series-lab (if (:series-label opts) (:series-label opts) (str "Data " n))
          legend? (if (:series-label opts) true false)
        ]
      (do
        (.addSeries (.getDataset data-plot) series-lab (double-array _x) nbins)
        (.setSeriesRenderingOrder data-plot org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
        (.fireChartChanged chart)))))



(defmethod add-series org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset 
  ([chart x & options]
    (let [opts (if options (apply assoc {} options) nil)
          _x (if (matrix? x) (to-list x) x)
          data-plot (.getCategoryPlot chart)
          n-col (.getColumnCount (.getDataset data-plot)) 
          n-row (.getRowCount (.getDataset data-plot))
          series-label (if (:series-label opts) (:series-label opts) (str "X" (inc n-row)))
          category-label (if (:series-label opts) 
                          (:series-label opts) 
                          (str "Category " n-col))
          legend? (if (:series-label opts) true false)
        ]
      (do
        (.add (.getDataset data-plot) _x series-label category-label)))))



(defn set-alpha 
  ([chart alpha] (.setForegroundAlpha (.getPlot chart) alpha)))


(defn set-title 
  ([chart title] (.setTitle chart title)))


(defn set-x-label 
  ([chart label] (.setLabel (.getDomainAxis (.getPlot chart)) label)))


(defn set-y-label 
  ([chart label] (.setLabel (.getRangeAxis (.getPlot chart)) label)))

 
;; FUNCTION TO ADD LINES TO SCATTER PLOT
;;


(defn add-line 
" Plots a line on the given scatter-plot of the (x,y) points.

  Examples:
    (use '(incanter core stats io datasets charts))
    (def speed (to-matrix (get-dataset :speed)))
    (def y (sel speed true 1))
    (def x (sel speed true 2))
    ;(def lm1 (linear-model y x :intercept false))
    (def lm1 (linear-model y x))
    (def y-hat (plus (first (:coefs lm1)) (mult (second (:coefs lm1)) x)))
    ;(def y-hat (mult (:coefs lm1) x))
    (def plot1 (scatter-plot x y))
    (view plot1)
    (add-line plot1 x y-hat)


"
  ([chart x y & options]
  (let [opts (if options (apply assoc {} options) nil)
        _x (if (matrix? x) (to-list x) x)
        _y (if (matrix? y) (to-list y) y)
        data-plot (.getPlot chart)
        n (.getDatasetCount data-plot)
        series-lab (if (:series-label opts) (:series-label opts) (str "Data " n))
        legend? (if (:series-label opts) true false)
        points? (if (:points opts) true false) ;; TODO
        data-series (org.jfree.data.xy.XYSeries. series-lab)
        line-renderer (org.jfree.chart.renderer.xy.XYLineAndShapeRenderer. true false)
        data-set (org.jfree.data.xy.XYSeriesCollection.)
       ]
    (do
      (doseq [i (range (count _x))] (.add data-series (nth _x i)  (nth _y i)))
      ;(.addSeries (.getDataset data-plot) data-series)
      (.setSeriesRenderingOrder (.getPlot chart) org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
      (.addSeries data-set data-series)
      (.setDataset data-plot 1 data-set)
      ;(.setSeriesPaint line-renderer 1 java.awt.Color/blue)
      (.setRenderer data-plot 1 line-renderer)))))



(defn trace-plot 
  " Returns a trace-plot object, use the 'view' function to display it.

    Examples:
      (use '(incanter core io stats bayes charts))
      (def ols-data (to-matrix (read-dataset \"data/olsexamp.dat\" :header true)))
      (def x (sel ols-data (range 0 2313) (range 1 10)))
      (def y (sel ols-data (range 0 2313) 10))
      (def sample-params (sample-linear-model-params 5000 y x))
      (view (trace-plot (:var sample-params))) 

      (view (trace-plot (sel (:coef sample-params) :columns 0)))

"
  ([x & options]
    (let [opts (if options (apply assoc {} options) nil)
          title (if (:title opts) (:title opts) "Trace Plot")
          x-label (if (:x-label opts) (:x-label opts) "Iteration")
          y-label (if (:y-label opts) (:y-label opts) "Value")
          series-lab (if (:series-label opts) (:series-label opts) "Value")
          legend? (if (:series-label opts) (:series-label opts) true)
          n (count x)
          chart (xy-plot (range n) 
                         x 
                         :title title
                         :x-label x-label
                         :y-label y-label
                         :series-label series-lab)]
      (do
        (add-series chart (range n) (cumulative-mean x) :series-label "running mean")
        chart))))



