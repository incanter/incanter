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
  (:use (incanter matrix)))

(defn histogram [x & options]
  (let [opts (if options (apply assoc {} options) nil)
        data (if (matrix? x) (to-vect x) x)
        nbins (if (:nbins opts) (:nbins opts) 10)
        main-title (if (:title opts) (:title opts) "Histogram")
        x-lab (if (:x-label opts) (:x-label opts) "X")
        y-lab (if (:y-label opts) (:y-label opts) "Frequency")
        series-lab (if (:series-lab opts) (:series-lab opts) "Data")
        legend? (if (:series-lab opts) true false)
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
          false))))



(defn scatter [x y & options]
  (let [opts (if options (apply assoc {} options) nil)
        _x (if (matrix? x) (to-vect x) x)
        _y (if (matrix? y) (to-vect y) y)
        main-title (if (:title opts) (:title opts) "Scatter Plot")
        x-lab (if (:x-label opts) (:x-label opts) "x")
        y-lab (if (:y-label opts) (:y-label opts) "y")
        series-lab (if (:series-lab opts) (:series-lab opts) "Data")
        legend? (if (:series-lab opts) true false)
        data-series (org.jfree.data.xy.XYSeries. series-lab)
        dataset (org.jfree.data.xy.XYSeriesCollection.)
       ]
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
          false))))



(defn xyplot [x y & options]
  (let [opts (if options (apply assoc {} options) nil)
        _x (if (matrix? x) (to-vect x) x)
        _y (if (matrix? y) (to-vect y) y)
        main-title (if (:title opts) (:title opts) "XY Plot")
        x-lab (if (:x-label opts) (:x-label opts) "x")
        y-lab (if (:y-label opts) (:y-label opts) "y")
        series-lab (if (:series-lab opts) (:series-lab opts) "Data")
        legend? (if (:series-lab opts) true false)
        data-series (org.jfree.data.xy.XYSeries. series-lab)
        dataset (org.jfree.data.xy.XYSeriesCollection.)
       ]
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
          false))))


(defn boxplot [x & options]
  (let [opts (if options (apply assoc {} options) nil)
        data (if (matrix? x) (to-vect x) x)
        main-title (if (:title opts) (:title opts) "Boxplot")
        x-label (if (:x-label opts) (:x-label opts) "Series")
        y-label (if (:y-label opts) (:y-label opts) "Values")
        series-label (if (:series-label opts) (:series-label opts) "X1")
        category-label (if (:series-label opts) (:series-label opts) "Category 1")
        legend? (if (false? (:series-label opts)) true false)
        dataset (org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset.)
        chart (org.jfree.chart.ChartFactory/createBoxAndWhiskerChart 
                  main-title
                  x-label
                  y-label
                  dataset 
                  legend?)
       ]
    (do
      (.add dataset data series-label category-label)
      (.. chart getCategoryPlot getRenderer (setMaximumBarWidth 0.25))
      chart)))




(defn plot [chart & options]
  (let [opts (if options (apply assoc {} options) nil)
        window-title (if (:window-title opts) (:window-title opts) "Incanter Plot")
        width (if (:width opts) (:width opts) 500)
        height (if (:height opts) (:height opts) 400)
        frame (org.jfree.chart.ChartFrame. window-title chart)]
    (doto frame
      (.setSize width height)
      (.setVisible true))
    nil))


(defn save-png [chart filename & options]
  (let [opts (if options (apply assoc {} options) nil)
        width (if (:width opts) (:width opts) 500)
        height (if (:height opts) (:height opts) 400)]
    (org.jfree.chart.ChartUtilities/saveChartAsPNG (java.io.File. filename) chart width height)
    nil))


(defmulti add-series (fn [chart & args] (class (.. chart getPlot getDataset))))


(defmethod add-series org.jfree.data.xy.XYSeriesCollection [chart x y & options]
  (let [opts (if options (apply assoc {} options) nil)
        _x (if (matrix? x) (to-vect x) x)
        _y (if (matrix? y) (to-vect y) y)
        data-plot (.getPlot chart)
        n (.getDatasetCount data-plot)
        series-lab (if (:series-lab opts) (:series-lab opts) (str "Data " n))
        legend? (if (:series-lab opts) true false)
        data-series (org.jfree.data.xy.XYSeries. series-lab)
       ]
    (do
      (doseq [i (range (count _x))] (.add data-series (nth _x i)  (nth _y i)))
      (.addSeries (.getDataset data-plot) data-series))))


(defmethod add-series org.jfree.data.statistics.HistogramDataset [chart x & options]
  (let [opts (if options (apply assoc {} options) nil)
        _x (if (matrix? x) (to-vect x) x)
        data-plot (.getPlot chart)
        n (.getDatasetCount data-plot)
        nbins (if (:nbins opts) (:nbins opts) 10)
        series-lab (if (:series-lab opts) (:series-lab opts) (str "Data " n))
        legend? (if (:series-lab opts) true false)
       ]
    (do
      (.addSeries (.getDataset data-plot) series-lab (double-array _x) nbins)
      (.fireChartChanged chart))))


(defmethod add-series org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset [chart x & options]
  (let [opts (if options (apply assoc {} options) nil)
        _x (if (matrix? x) (to-vect x) x)
        data-plot (.getCategoryPlot chart)
        n-col (.getColumnCount (.getDataset data-plot)) 
        n-row (.getRowCount (.getDataset data-plot))
        series-label (if (:series-label opts) (:series-label opts) (str "X" (inc n-row)))
        category-label (if (:category-label opts) 
                         (:category-label opts) 
                         (str "Category " n-col))
        legend? (if (:category-label opts) true false)
       ]
    (do
      (.add (.getDataset data-plot) _x series-label category-label))))


(defn set-alpha [chart alpha] (.setForegroundAlpha (.getPlot chart) alpha))


(defn set-title [chart title] (.setTitle chart title))


(defn set-x-label [chart label]
  (.setLabel (.getDomainAxis (.getPlot chart)) label))


(defn set-y-label [chart label]
  (.setLabel (.getRangeAxis (.getPlot chart)) label))

  

