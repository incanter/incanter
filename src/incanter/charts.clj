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
  (:use (incanter core stats io))
  (:import  (java.io File)
            (org.jfree.data.statistics HistogramDataset
                                       HistogramType
                                       DefaultBoxAndWhiskerCategoryDataset)
            (org.jfree.chart ChartFactory
                             ChartUtilities
                             ChartFrame)
            (org.jfree.chart.plot PlotOrientation
                                  DatasetRenderingOrder
                                  SeriesRenderingOrder)
            (org.jfree.data.xy XYSeries
                               XYSeriesCollection)
            (org.jfree.chart.renderer.xy XYLineAndShapeRenderer)
     ))



(defmacro histogram 
" Returns a JFreeChart object representing the histogram of the given data.
  Use the 'view' function to display the chart, or the 'save' function
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
    view, save, add-histogram

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

    ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots

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
          dataset# (HistogramDataset.)
        ]
      (do
        (.addSeries dataset# series-lab# (double-array data#) nbins#)
        (when density?# (.setType dataset# org.jfree.data.statistics.HistogramType/SCALE_AREA_TO_1))
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
  Use the 'view' function to display the chart, or the 'save' function
  to write it to a file.

  Options:
    :title (default 'Histogram') main title
    :x-label (default x expression)
    :y-label (default 'Frequency')
    :legend (default false) prints legend
    :series-label (default x expression)

  See also:
    view, save, add-points, add-lines

  Examples:

    (use '(incanter core stats charts))
    ;; create some data
    (def mvn-samp (sample-multivariate-normal 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))

    ;; create scatter-plot of points
    (def mvn-plot (scatter-plot (sel mvn-samp :cols 0) (sel mvn-samp :cols 1)))
    (view mvn-plot)

    ;; add regression line to scatter plot
    (def x (sel mvn-samp :cols 0))
    (def y (sel mvn-samp :cols 1))
    (def lm (linear-model y x))
    (add-lines mvn-plot x (:fitted lm))

    ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots

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
           data-series# (XYSeries. series-lab#)
           dataset# (XYSeriesCollection.)]
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
" Returns a JFreeChart object representing a line-plot of the given data.
  Use the 'view' function to display the chart, or the 'save' function
  to write it to a file.

  Options:
    :title (default 'Histogram') main title
    :x-label (default x expression)
    :y-label (default 'Frequency')
    :legend (default false) prints legend
    :series-label (default x expression)

  See also:
    view, save, add-points, add-lines

  Examples:

    (use '(incanter core stats charts))

    ;; plot the cosine function
    (def x (range -1 5 0.01))  
    (def y (cos (mult 2 Math/PI x)))
    (view (line-plot x y))

    ;; plot gamma pdf with different parameters
    (def x2 (range 0 20 0.1))
    (def gamma-plot (line-plot x2 (pdf-gamma x2 :shape 1 :rate 2) 
                               :legend true
                               :title \"Gamma PDF\"
                               :y-label \"Density\"))
    (view gamma-plot)
    (add-lines gamma-plot x2 (pdf-gamma x2 :shape 2 :rate 2))
    (add-lines gamma-plot x2 (pdf-gamma x2 :shape 3 :rate 2))
    (add-lines gamma-plot x2 (pdf-gamma x2 :shape 5 :rate 1))
    (add-lines gamma-plot x2 (pdf-gamma x2 :shape 9 :rate 0.5))

    ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots
                                                
  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([x y & options]
    `(let [opts# (if '~options (assoc {} ~@options))
          _x# (if (matrix? ~x) (to-list ~x) ~x)
          _y# (if (matrix? ~y) (to-list ~y) ~y)
          main-title# (if (:title opts#) (:title opts#) "XY Plot")
          x-lab# (if (:x-label opts#) (:x-label opts#) (str '~x))
          y-lab# (if (:y-label opts#) (:y-label opts#) (str '~y))
          series-lab# (if (:series-label opts#) (:series-label opts#) (format "%s, %s" '~x '~y))
          legend?# (true? (:legend opts#))
          data-series# (XYSeries. series-lab#)
          dataset# (XYSeriesCollection.)]
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
" Returns a JFreeChart object representing a box-plot of the given data.
  Use the 'view' function to display the chart, or the 'save' function
  to write it to a file.

  Options:
    :title (default 'Histogram') main title
    :x-label (default x expression)
    :y-label (default 'Frequency')
    :legend (default false) prints legend
    :series-label (default x expression)

  See also:
    view and save

  Examples:

    (use '(incanter stats charts))
    (def gamma-box-plot (box-plot (sample-gamma 1000 :shape 1 :rate 2) 
                          :title \"Gamma Boxplot\"
                          :legend true)) 
    (view gamma-box-plot)
    (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 2 :rate 2))
    (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 3 :rate 2))

    ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots
           
  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
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
          dataset# (DefaultBoxAndWhiskerCategoryDataset.)
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
  Adds a histogram to an existing histogram plot, returns the modified
  chart object.

  Options:
    :nbins (default 10) number of bins for histogram
    :series-label (default x expression)

  Examples:

    (use '(incanter charts stats))
    (doto (histogram (sample-normal 1000) 
                     :legend true)
          view 
          (add-histogram (sample-normal 1000 :sd 0.5)))

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([chart x & options]
    `(let [opts# (if '~options (assoc {} ~@options))
          _x# (if (matrix? ~x) (to-list ~x) ~x)
          chart# ~chart
          data-plot# (.getPlot chart#)
          n# (.getDatasetCount data-plot#)
          nbins# (if (:nbins opts#) (:nbins opts#) 10)
          series-lab# (if (:series-label opts#) (:series-label opts#) (str '~x))
        ]
      (do
        (.addSeries (.getDataset data-plot#) series-lab# (double-array _x#) nbins#)
        (.setSeriesRenderingOrder data-plot# org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
        (.fireChartChanged chart#)
        chart#))))



(defmacro add-box-plot 
"
  Adds an additional box to an existing box-plot, returns the modified chart object.

  Options:
    :series-label (default x expression)

  Examples:

      (use '(incanter charts stats))
      (doto (box-plot (sample-normal 1000) :legend true)
            view
            (add-box-plot (sample-normal 1000 :sd 2))
            (add-box-plot (sample-gamma 1000)))

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([chart x & options]
    `(let [opts# (if '~options (assoc {} ~@options))
          _x# (if (matrix? ~x) (to-list ~x) ~x)
          chart# ~chart
          data-plot# (.getCategoryPlot chart#)
          n-col# (.getColumnCount (.getDataset data-plot#)) 
          n-row# (.getRowCount (.getDataset data-plot#))
          series-label# (if (:series-label opts#) (:series-label opts#) (str '~x))
          category-label# (if (:category-label opts#) 
                            (:category-label opts#) 
                            (str n-col#))
        ]
      (do
        (.add (.getDataset data-plot#) _x# series-label# category-label#)
        chart#))))




(defn set-alpha 
" Sets the alpha level (transparancy) of the plot's foreground, 
  returns the modified chart object.

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([chart alpha] 
    (.setForegroundAlpha (.getPlot chart) alpha)
    chart))


(defn set-background-alpha 
" Sets the alpha level (transparancy) of the plot's background, 
  returns the modified chart object.

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([chart alpha] 
    (.setBackgroundAlpha (.getPlot chart) alpha)
    chart))


(defn clear-background 
" Sets the alpha level (transparancy) of the plot's background to zero, 
  removing the default grid, returns the modified chart object.

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([chart] 
    (.setBackgroundAlpha (.getPlot chart) 0.0)
    chart))



(defn set-title 
" Sets the main title of the plot, returns the modified chart object.

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([chart title] 
    (.setTitle chart title)
    chart))


(defn set-x-label 
" Sets the label of the x-axis, returns the modified chart object.

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([chart label] 
    (.setLabel (.getDomainAxis (.getPlot chart)) label)
    chart))


(defn set-y-label 
" Sets the label of the y-axis, returns the modified chart object.

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    
"
  ([chart label] 
    (.setLabel (.getRangeAxis (.getPlot chart)) label)
    chart))



 
(defmacro add-lines
" Plots lines on the given scatter or line plot of the (x,y) points.
  Equivalent to R's lines function, returns the modified chart object.

  Options:
    :series-label (default x expression)

  Examples:

    (use '(incanter core stats io datasets charts))
    (def speed (to-matrix (get-dataset :speed)))
    (def y (sel speed :cols 1))
    (def x (sel speed :cols 2))
    (def plot1 (scatter-plot x y :legend true))
    (view plot1)
    
    ;; add regression line to scatter plot
    (def lm1 (linear-model y x))
    (add-lines plot1 x (:fitted lm1))

    ;; model the data without an intercept
    (def lm2 (linear-model y x :intercept false))
    (add-lines plot1 x (:fitted lm2))


    ;; Clojure's doto macro can be used to build a chart
    (doto (line-plot x (pdf-normal x))
          view
          clear-background
          (add-lines x (pdf-normal x :sd 1.5))
          (add-lines x (pdf-normal x :sd 0.5)))

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    

"
  ([chart x y & options]
    `(let [opts# (if '~options (assoc {} ~@options))
           _x# (if (matrix? ~x) (to-list ~x) ~x)
           _y# (if (matrix? ~y) (to-list ~y) ~y)
           chart# ~chart
           data-plot# (.getPlot chart#)
           n# (.getDatasetCount data-plot#)
           series-lab# (if (:series-label opts#) (:series-label opts#) (format "%s, %s" '~x '~y))
           ;legend?# (if (false? (:legend opts#)) false true)
           ;points?# (if (false? (:points opts#)) false true) ;; TODO
           data-series# (XYSeries. series-lab#)
           line-renderer# (XYLineAndShapeRenderer. true false)
           data-set# (XYSeriesCollection.)
          ]
    (do
      (doseq [i# (range (count _x#))] (.add data-series# (nth _x# i#)  (nth _y# i#)))
      ;(.addSeries (.getDataset data-plot) data-series)
      (.setSeriesRenderingOrder (.getPlot chart#) org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
      (.setDatasetRenderingOrder data-plot# org.jfree.chart.plot.DatasetRenderingOrder/FORWARD) 
      (.addSeries data-set# data-series#)
      (.setDataset data-plot# (.getDatasetCount data-plot#) data-set#)
      ;(.setSeriesPaint line-renderer 1 java.awt.Color/blue)
      (.setRenderer data-plot# (dec (.getDatasetCount data-plot#)) line-renderer#)
      chart#))))




(defmacro add-points
" Plots points on the given scatter-plot or line-plot of the (x,y) points.
  Equivalent to R's lines function, returns the modified chart object.

  Options:
    :series-label (default x expression)

  Examples:

    (use '(incanter core stats io datasets charts))
    (def speed (to-matrix (get-dataset :speed)))
    (def y (sel speed :cols 1))
    (def x (sel speed :cols 2))
    
    ;; add regression line to scatter plot
    (def lm1 (linear-model y x))
    ;; model the data without an intercept
    (def lm2 (linear-model y x :intercept false))

    (doto (line-plot x (:fitted lm1) :legend true)
          view
          (add-points x y)
          (add-lines x (:fitted lm2)))


  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html
    

"
  ([chart x y & options]
    `(let [opts# (if '~options (assoc {} ~@options))
        _x# (if (matrix? ~x) (to-list ~x) ~x)
        _y# (if (matrix? ~y) (to-list ~y) ~y)
        chart# ~chart
        data-plot# (.getPlot chart#)
        n# (.getDatasetCount data-plot#)
        series-lab# (if (:series-label opts#) (:series-label opts#) (format "%s, %s" '~x '~y))
        ;legend?# (if (false? (:legend opts#)) false true)
        ;points?# (if (false? (:points opts#)) false true) ;; TODO
        data-series# (XYSeries. series-lab#)
        line-renderer# (XYLineAndShapeRenderer. false true)
        data-set# (XYSeriesCollection.)
       ]
    (do
      (doseq [i# (range (count _x#))] (.add data-series# (nth _x# i#)  (nth _y# i#)))
      ;(.addSeries (.getDataset data-plot) data-series)
      (.setSeriesRenderingOrder (.getPlot chart#) org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
      (.setDatasetRenderingOrder (.getPlot chart#) org.jfree.chart.plot.DatasetRenderingOrder/FORWARD)
      (.addSeries data-set# data-series#)
      (.setDataset data-plot# (.getDatasetCount data-plot#) data-set#)
      ;(.setSeriesPaint line-renderer 1 java.awt.Color/blue)
      (.setRenderer data-plot# (dec (.getDatasetCount data-plot#)) line-renderer#)
      chart#))))




(defn trace-plot
  " Returns a trace-plot object, use the 'view' function to display it.

    Examples:
      (use '(incanter core datasets stats bayes charts))
      (def ols-data (to-matrix (get-dataset :survey)))
      (def x (sel ols-data (range 0 2313) (range 1 10)))
      (def y (sel ols-data (range 0 2313) 10))
      (def sample-params (sample-model-params 5000 (linear-model y x :intercept false)))
      (view (trace-plot (:var sample-params))) 

      (view (trace-plot (sel (:coefs sample-params) :cols 0)))

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
                         x ;)]
                         :title title 
                         :x-label x-label 
                         :y-label y-label
                         :series-label series-lab)]
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
    (def x1 (sel flow-meter :cols 1))
    (def x2 (sel flow-meter :cols 3))
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


(defmethod view org.jfree.chart.JFreeChart 
  ([chart & options]
    (let [opts (if options (apply assoc {} options) nil)
          window-title (if (:window-title opts) (:window-title opts) "Incanter Plot")
          width (if (:width opts) (:width opts) 500)
          height (if (:height opts) (:height opts) 400)
          frame (ChartFrame. window-title chart)]
      (doto frame
        (.setSize width height)
        (.setVisible true))
      nil)))


(defmethod save org.jfree.chart.JFreeChart
  ([chart filename & options]
    (let [opts (if options (apply assoc {} options) nil)
          width (if (:width opts) (:width opts) 500)
          height (if (:height opts) (:height opts) 400)]
      (ChartUtilities/saveChartAsPNG (File. filename) chart width height)
      nil)))



