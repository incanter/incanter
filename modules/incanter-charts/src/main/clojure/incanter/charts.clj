;;; charts.clj -- Charts library for Clojure built on JFreeChart

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 11, 2009: First version



(ns #^{:doc "This is the core charting library for Incanter.
            It provides basic scatter plots, histograms, box plots
            xy plots, bar charts, line charts, as well as
            specialized charts like trace plots and Bland-Altman
            plots.

            This library is built on the JFreeChart library
            (http://www.jfree.org/jfreechart/).
            "
       :author "David Edgar Liebke"}
  incanter.charts
  ;(:gen-class)
  (:use [incanter.core :only ($ matrix? to-list plus minus div group-by
                              bind-columns view save $group-by conj-cols grid-apply)]
        [incanter.stats :only (quantile quantile-normal cumulative-mean sd)])
  (:import  (java.io File)
            (javax.imageio ImageIO)
            (org.jfree.data.statistics HistogramDataset
                                       HistogramType
                                       DefaultBoxAndWhiskerCategoryDataset)
            (org.jfree.chart ChartFactory
                             ChartUtilities
                             ChartFrame
			     StandardChartTheme)
            (org.jfree.chart.plot PlotOrientation
                                  DatasetRenderingOrder
                                  SeriesRenderingOrder)
            (org.jfree.data.xy XYSeries
                               XYSeriesCollection)
            (org.jfree.data.category DefaultCategoryDataset)
	    (org.jfree.data.general DefaultPieDataset)
            (org.jfree.chart.renderer.xy XYLineAndShapeRenderer)
            (org.jfree.ui TextAnchor)
            (org.jfree.chart.annotations XYPointerAnnotation
                                         XYTextAnnotation
                                         XYPolygonAnnotation)))




(defn set-theme
"  Changes the chart theme.

   Arguments:
     chart -- an Incanter/JFreeChart object
     theme -- either a keyword indicating one of the built-in themes, or a JFreeChart ChartTheme object.

   Built-in Themes:
     :default
     :dark

   Examples:

     (use '(incanter core charts))
     (def chart (function-plot sin -4 4))
     (view chart)
     ;; change the theme of chart to :dark
     (set-theme chart :dark)
     ;; change it back to the default
     (set-theme chart :default)

   
    References:
       http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/StandardChartTheme.html
       http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/ChartTheme.html 

"
  ([chart theme]
     (let [_theme (if (keyword? theme) 
		    (cond
		      (= theme :dark)
		        (StandardChartTheme/createDarknessTheme)
		      (= theme :legacy)
		        (StandardChartTheme/createLegacyTheme)
			:default
			  (StandardChartTheme/createJFreeTheme))
		    theme)]
       (do
	 (.setShadowVisible _theme false)
	 (.apply _theme chart)
	 chart))))



(defn add-histogram*
  ([chart x & options]
    (let [opts (when options (apply assoc {} options))
	  data (:data opts)
	  _x (if (coll? x) (to-list x) ($ x data))
	  data-plot (.getPlot chart)
	  n (.getDatasetCount data-plot)
	  nbins (or (:nbins opts) 10)
	  series-lab (or (:series-label opts) (str 'x))]
      (do
        (.addSeries (.getDataset data-plot) series-lab (double-array _x) nbins)
        (.setSeriesRenderingOrder data-plot org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
        (.fireChartChanged chart)
        chart))))



(defmacro add-histogram
"
  Adds a histogram to an existing histogram plot, returns the modified
  chart object.

  Options:
    :nbins (default 10) number of bins for histogram
    :series-label (default x expression)

  Examples:

    (use '(incanter core charts stats datasets))
    (doto (histogram (sample-normal 1000)
                     :legend true)
          view
          (add-histogram (sample-normal 1000 :sd 0.5)))


    (with-data (get-dataset :iris)
      (doto (histogram :Sepal.Length :legend true)
        (add-histogram :Petal.Length)
        view))

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([chart x & options]
    `(let [opts# ~(when options (apply assoc {} options))
           series-lab# (or (:series-label opts#) (str '~x))
           args# (concat [~chart ~x] 
			 (apply concat (seq (apply assoc opts# 
						   [:series-label series-lab#]))))]
        (apply add-histogram* args#))))





(defn add-box-plot*
  ([chart x & options]
    (let [opts (when options (apply assoc {} options))
	  data (:data opts)
	  _x (if (coll? x) (to-list x) ($ x data))
	  data-plot (.getCategoryPlot chart)
	  n-col (.getColumnCount (.getDataset data-plot))
	  n-row (.getRowCount (.getDataset data-plot))
	  series-label (or (:series-label opts) (str 'x))
	  category-label (or (:category-label opts)
                               (str n-col))]
      (do
        (.add (.getDataset data-plot) _x series-label category-label)
        chart))))


(defmacro add-box-plot
"
  Adds an additional box to an existing box-plot, returns the modified chart object.

  Options:
    :series-label (default x expression)

  Examples:

      (use '(incanter core charts stats))
      (doto (box-plot (sample-normal 1000) :legend true)
            view
            (add-box-plot (sample-normal 1000 :sd 2))
            (add-box-plot (sample-gamma 1000)))


     (with-data (get-dataset :iris)
       (doto (box-plot :Sepal.Length :legend true)
         (add-box-plot :Petal.Length)
         (add-box-plot :Sepal.Width)
         (add-box-plot :Petal.Width)
         view))


  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([chart x & options]
    `(let [opts# ~(when options (apply assoc {} options))
           series-lab# (or (:series-label opts#) (str '~x))
           args# (concat [~chart ~x] (apply concat (seq (apply assoc opts# 
							[:series-label series-lab#]))))]
        (apply add-box-plot* args#))))





(defn add-categories*
  ([chart categories values & options]
    (let [opts (when options (apply assoc {} options))
	  data (:data opts)
	  _values (if (coll? values) (to-list values) ($ values data))
	  _categories (if (coll? categories) (to-list categories) ($ categories data))
	  _group-by (when (:group-by opts) 
		      (if (coll? (:group-by opts)) 
		       (to-list (:group-by opts))
		       ($ (:group-by opts) data)))
           _chart chart
	   series-label (:series-label opts)
           data-plot (.getCategoryPlot _chart)
           n-col (.getColumnCount (.getDataset data-plot))
           n-row (.getRowCount (.getDataset data-plot))]
        (do
          (doseq [i (range 0 (count _values))] (.addValue (.getDataset data-plot)
                                                      (nth _values i)
                                                      (cond 
						       _group-by
                                                         (nth _group-by i)
						       series-label
						         series-label
						       :else
                                                         (str 'values))
                                                       (nth _categories i)))
          chart))))



(defmacro add-categories
"
  Adds an additional categories to an existing bar-chart or line-chart, returns the modified chart object.

  Options:
    :group-by
    :series-label

  Examples:

      (use '(incanter core charts stats datasets))
      (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))
      (def years (mapcat identity (repeat 4 [2007 2008 2009])))
      (def x (sample-uniform 12 :integers true :max 100))
      (def plot (bar-chart years x :group-by seasons :legend true))
      (view plot)
      (add-categories plot years [10 20 40] :series-label \"winter-break\")

      (add-categories plot 
                         (plus 3 years) 
                         (sample-uniform 12 :integers true :max 100) 
                         :group-by seasons)

      (def plot2 (line-chart years x :group-by seasons :legend true))
      (view plot2)
      (add-categories plot2 (plus 3 years) (sample-uniform 12 :integers true :max 100) :group-by seasons)

      (with-data (get-dataset :iris)
        (doto (line-chart :Species :Sepal.Length 
                          :data ($rollup mean :Sepal.Length :Species)
                          :legend true)
          (add-categories :Species :Sepal.Width :data ($rollup mean :Sepal.Width :Species))
          (add-categories :Species :Petal.Length :data ($rollup mean :Petal.Length :Species))
          (add-categories :Species :Petal.Width :data ($rollup mean :Petal.Width :Species))
          view))


  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([chart categories values & options]
    `(let [opts# ~(if options (apply assoc {} options) {})
           group-by# (:group-by opts#) 
           series-lab# (or (:series-label opts#) 
			   (if group-by#
			     (format "%s, %s (0)" '~categories '~values) 
			     (format "%s, %s" '~categories '~values)))
	   args# (concat [~chart ~categories ~values] 
			 (apply concat (seq (apply assoc opts# [:series-label series-lab#]))))]
       (apply add-categories* args#))))




(defn add-lines*
  ([chart x y & options]
     (let [opts (when options (apply assoc {} options))
	   data (:data opts)
	   _x (if (coll? x) (to-list x) ($ x data))
	   _y (if (coll? y) (to-list y) ($ y data))
	   data-plot (.getPlot chart)
	   n (.getDatasetCount data-plot)
	   series-lab (or (:series-label opts) (format "%s, %s" 'x 'y))
	   data-series (XYSeries. series-lab)
           line-renderer (XYLineAndShapeRenderer. true false)
           data-set (XYSeriesCollection.)]
    (do
      (doseq [i (range (count _x))] (.add data-series (nth _x i)  (nth _y i)))
      (.setSeriesRenderingOrder (.getPlot chart) org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
      (.setDatasetRenderingOrder data-plot org.jfree.chart.plot.DatasetRenderingOrder/FORWARD)
      (.addSeries data-set data-series)
      (.setDataset data-plot (.getDatasetCount data-plot) data-set)
      (.setRenderer data-plot (dec (.getDatasetCount data-plot)) line-renderer)
      chart))))



(defmacro add-lines
" Plots lines on the given scatter or line plot of the (x,y) points.
  Equivalent to R's lines function, returns the modified chart object.

  Options:
    :series-label (default x expression)

  Examples:

    (use '(incanter core stats io datasets charts))
    (def cars (to-matrix (get-dataset :cars)))
    (def y (sel cars :cols 0))
    (def x (sel cars :cols 1))
    (def plot1 (scatter-plot x y :legend true))
    (view plot1)

    ;; add regression line to scatter plot
    (def lm1 (linear-model y x))
    (add-lines plot1 x (:fitted lm1))

    ;; model the data without an intercept
    (def lm2 (linear-model y x :intercept false))
    (add-lines plot1 x (:fitted lm2))


    ;; Clojure's doto macro can be used to build a chart
    (doto (histogram (sample-normal 1000) :density true)
          (add-lines (range -3 3 0.05) (pdf-normal (range -3 3 0.05)))
          view)


    (with-data (get-dataset :iris)
        (doto (xy-plot :Sepal.Width :Sepal.Length :legend true)
              (add-lines :Petal.Width :Petal.Length)
              view))



  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html


"
  ([chart x y & options]
    `(let [opts# ~(when options (apply assoc {} options))
           series-lab# (or (:series-label opts#) 
			   (format "%s, %s" '~x '~y))
	   args# (concat [~chart ~x ~y] (apply concat (seq (apply assoc opts# 
								  [:series-label series-lab#]))))]
        (apply add-lines* args#))))





(defn add-function*
  ([chart function min-range max-range & options]
    (let [opts (when options (apply assoc {} options))
           step-size (or (:step-size opts) 
			 (float (/ (- max-range min-range) 500)))
           x (range min-range max-range step-size)
           series-lab (or (:series-label opts) 
			  (format "%s" 'function))]
       (add-lines chart x (map function x) :series-label series-lab))))



(defmacro add-function
" Adds a xy-plot of the given function to the given chart, returning
  a modified version of the chart.

  Options:
    :series-label (default x expression)
    :step-size (default (/ (- max-range min-range) 500))

  See also:
    function-plot, view, save, add-function, add-points, add-lines


  Examples:

    (use '(incanter core stats charts))

    ;; plot the sine and cosine functions
    (doto (function-plot sin (- Math/PI) Math/PI)
          (add-function cos (- Math/PI) Math/PI)
          view)


    ;; plot two normal pdf functions
    (doto (function-plot pdf-normal -3 3 :legend true)
          (add-function (fn [x] (pdf-normal x :mean 0.5 :sd 0.5)) -3 3)
          view)


    ;; plot a user defined function and its derivative
    (use '(incanter core charts optimize))

    ;; define the function, x^3 + 2x^2 + 2x + 3
    (defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))

    ;; use the derivative function to get a function
    ;; that approximates its derivative
    (def deriv-cubic (derivative cubic))

    ;; plot the cubic function and its derivative
    (doto (function-plot cubic -10 10)
          (add-function deriv-cubic -10 10)
          view)

"
  ([chart function min-range max-range & options]
    `(let [opts# ~(when options (apply assoc {} options))
           series-lab# (or (:series-label opts#) (str '~function))
           args# (concat [~chart ~function ~min-range ~max-range] 
			 (apply concat (seq (apply assoc opts# 
						   [:series-label series-lab#]))))]
        (apply add-function* args#))))





(defn add-points*
  ([chart x y & options]
    (let [opts (when options (apply assoc {} options))
	  data (:data opts)
	  _x (if (coll? x) (to-list x) ($ x data))
	  _y (if (coll? y) (to-list y) ($ y data))
	  data-plot (.getPlot chart)
	  n (.getDatasetCount data-plot)
	  series-lab (or (:series-label opts) (format "%s, %s" 'x 'y))
	  data-series (XYSeries. series-lab)
	  line-renderer (XYLineAndShapeRenderer. false true)
	  data-set (XYSeriesCollection.)]
      (do
	(doseq [i (range (count _x))] (.add data-series (nth _x i)  (nth _y i)))
	(.setSeriesRenderingOrder (.getPlot chart) org.jfree.chart.plot.SeriesRenderingOrder/FORWARD)
	(.setDatasetRenderingOrder (.getPlot chart) org.jfree.chart.plot.DatasetRenderingOrder/FORWARD)
	(.addSeries data-set data-series)
	(.setDataset data-plot (.getDatasetCount data-plot) data-set)
	(.setRenderer data-plot (dec (.getDatasetCount data-plot)) line-renderer)
	chart))))



(defmacro add-points
" Plots points on the given scatter-plot or xy-plot of the (x,y) points.
  Equivalent to R's lines function, returns the modified chart object.

  Options:
    :series-label (default x expression)

  Examples:

    (use '(incanter core stats io datasets charts))
    (def cars (to-matrix (get-dataset :cars)))
    (def y (sel cars :cols 0))
    (def x (sel cars :cols 1))

    ;; add regression line to scatter plot
    (def lm1 (linear-model y x))
    ;; model the data without an intercept
    (def lm2 (linear-model y x :intercept false))

    (doto (xy-plot x (:fitted lm1) :legend true)
          view
          (add-points x y)
          (add-lines x (:fitted lm2)))


    (with-data (get-dataset :iris)
      (doto (scatter-plot :Sepal.Length :Sepal.Width :data ($where {:Species \"setosa\"}))
            (add-points :Sepal.Length :Sepal.Width :data ($where {:Species \"versicolor\"}))
            (add-points :Sepal.Length :Sepal.Width :data ($where {:Species \"virginica\"}))
            view))

    ;; of course this chart can be achieved in a single line:
    (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species :data (get-dataset :iris)))



  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html


"
  ([chart x y & options]
    `(let [opts# ~(when options (apply assoc {} options))
           series-lab# (or (:series-label opts#) (format "%s, %s" '~x '~y))
	   args# (concat [~chart ~x ~y] (apply concat (seq (apply assoc opts# 
							   [:series-label series-lab#]))))]
        (apply add-points* args#))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn set-alpha
" Sets the alpha level (transparancy) of the plot's foreground
  returns the modified chart object.

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([chart alpha]
    (.setForegroundAlpha (.getPlot chart) alpha)
    chart))


(defn set-background-alpha
" Sets the alpha level (transparancy) of the plot's background
  returns the modified chart object.

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([chart alpha]
    (.setBackgroundAlpha (.getPlot chart) alpha)
    chart))


(defn clear-background
" Sets the alpha level (transparancy) of the plot's background to zero
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



(defn set-x-range
" Sets the range of the x-axis on the given chart.

  Examples:
    
    (use '(incanter core charts datasets))

    (def chart (xy-plot :speed :dist :data (get-dataset :cars)))
    (view chart)
    (set-x-range chart 10 20)

"
  ([chart lower upper]
     (-> chart
	 .getPlot
	 .getDomainAxis
	 (.setRange lower upper))
     chart))


(defn set-y-range
" Sets the range of the y-axis on the given chart.

  Examples:
    
    (use '(incanter core charts datasets))

    (def chart (xy-plot :speed :dist :data (get-dataset :cars)))
    (view chart)
    (set-y-range chart 10 60)

"
  ([chart lower upper]
     (-> chart
	 .getPlot
	 .getRangeAxis
	 (.setRange lower upper))
     chart))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;  NEW CHART FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- create-xy-plot
  [main-title x-lab y-lab dataset legend? tooltips? urls?]
  (org.jfree.chart.ChartFactory/createXYLineChart
    main-title
    x-lab
    y-lab
    dataset
    org.jfree.chart.plot.PlotOrientation/VERTICAL
    legend?
    tooltips?
    urls?))

(defn- create-time-series-plot
  [main-title x-lab y-lab dataset legend? tooltips? urls?]
  (org.jfree.chart.ChartFactory/createTimeSeriesChart
    main-title
    x-lab
    y-lab
    dataset
    legend?
    tooltips?
    urls?))

(defn xy-plot*
  ([x y create-plot & options]
    (let [opts (when options (apply assoc {} options))
	  data (:data opts)
	  _x (if (coll? x) (to-list x) ($ x data))
	  _y (if (coll? y) (to-list y) ($ y data))
	  _group-by (when (:group-by opts) 
		      (if (coll? (:group-by opts)) 
			(to-list (:group-by opts))
			($ (:group-by opts) data)))
	  x-groups (when _group-by 
		     (map #($ :col-0 %) 
			  (vals ($group-by :col-1 (conj-cols _x _group-by)))))
	  y-groups (when _group-by 
		     (map #($ :col-0 %) 
			  (vals ($group-by :col-1 (conj-cols _y _group-by)))))
	  __x (if x-groups (first x-groups) _x)
           __y (if y-groups (first y-groups) _y)
	  main-title (or (:main-title opts) "XY Plot")
	  x-lab (or (:x-label opts) (str 'x))
	  y-lab (or (:y-label opts) (str 'y))
	  series-lab (or (:series-label opts) 
			  (if x-groups 
			    (format "%s, %s (0)" 'x 'y) 
			    (format "%s, %s" 'x 'y)))
	  theme (or (:theme opts) :default)
	  legend? (true? (:legend opts))
	  data-series (XYSeries. series-lab)
	  dataset (XYSeriesCollection.)
	  chart (do
              (doseq [i (range (count __x))] 
		        (.add data-series (nth __x i)  (nth __y i)))
               (.addSeries dataset data-series)
                    (create-plot
                        main-title
                        x-lab
                        y-lab
                        dataset
                        legend?
                        true  ; tooltips
                        false))
           _ (when x-groups
                (doseq [i (range 1 (count x-groups))]
                  (add-lines chart (nth x-groups i)
                             (nth y-groups i)
                             :series-label (format "%s, %s (%s)" 'x 'y i))))]
      (set-theme chart theme)  
      chart)))



(defmacro xy-plot
" Returns a JFreeChart object representing a xy-plot of the given data.
  Use the 'view' function to display the chart, or the 'save' function
  to write it to a file.

  Options:
    :data (default nil) If the :data option is provided a dataset, 
                        column names can be used instead of sequences 
                        of data as arguments to xy-plot.
    :title (default 'XY Plot') main title
    :x-label (default x expression)
    :y-label (default 'Frequency')
    :legend (default false) prints legend
    :series-label (default x expression)
    :group-by (default nil) -- a vector of values used to group the x and y values into series.

  See also:
    view, save, add-points, add-lines

  Examples:

    (use '(incanter core stats charts))

    ;; plot the cosine function
    (def x (range -1 5 0.01))
    (def y (cos (mult 2 Math/PI x)))
    (view (xy-plot x y))

    ;; plot gamma pdf with different parameters
    (def x2 (range 0 20 0.1))
    (def gamma-plot (xy-plot x2 (pdf-gamma x2 :shape 1 :rate 2)
                               :legend true
                               :title \"Gamma PDF\"
                               :y-label \"Density\"))
    (view gamma-plot)
    (add-lines gamma-plot x2 (pdf-gamma x2 :shape 2 :rate 2))
    (add-lines gamma-plot x2 (pdf-gamma x2 :shape 3 :rate 2))
    (add-lines gamma-plot x2 (pdf-gamma x2 :shape 5 :rate 1))
    (add-lines gamma-plot x2 (pdf-gamma x2 :shape 9 :rate 0.5))

    ;; use :group-by option
    (use '(incanter core charts datasets))

    (with-data (get-dataset :chick-weight)
      (view (xy-plot :Time :weight :group-by :Chick)))


    ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([x y & options]
    `(let [opts# ~(when options (apply assoc {} options))
           group-by# (:group-by opts#) 
           main-title# (or (:title opts#) "XY Plot")
           x-lab# (or (:x-label opts#) (str '~x))
           y-lab# (or (:y-label opts#) (str '~y))
           series-lab# (or (:series-label opts#) 
			   (if group-by#
			     (format "%s, %s (0)" '~x '~y) 
			     (format "%s, %s" '~x '~y)))
	   args# (concat [~x ~y ~create-xy-plot] (apply concat (seq (apply assoc opts# 
							   [:group-by group-by# 
							    :main-title main-title# 
							    :x-label x-lab# 
							    :y-label y-lab# 
							    :series-label series-lab#]))))]
        (apply xy-plot* args#))))

(defmacro time-series-plot
" Returns a JFreeChart object representing a time series plot of the given data.
  Use the 'view' function to display the chart, or the 'save' function
  to write it to a file. Sequence passed in for the x axis should be
  number of milliseconds from the epoch (1 Janurary 1970).

  Options:
    :data (default nil) If the :data option is provided a dataset, 
                        column names can be used instead of sequences 
                        of data as arguments to xy-plot.
    :title (default 'Time Series Plot') main title
    :x-label (default x expression)
    :y-label (default y expression)
    :legend (default false) prints legend
    :series-label (default x expression)
    :group-by (default nil) -- a vector of values used to group the x and y values into series.

  See also:
    view, save, add-points, add-lines

  Examples:

    (use '(incanter core stats charts chrono))

    ;; plot numbers against years starting with 1900 
    (def dates (map #(-> (joda-date (+ 1900 %) 1 1 12 0 0 0 (time-zone 0)) 
                         .getMillis) 
                    (range 100)))
    (def y (range 100))
    (view (time-series-plot dates y
                            :x-label \"Year\"))

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([x y & options]
    `(let [opts# ~(when options (apply assoc {} options))
           group-by# (:group-by opts#) 
           main-title# (or (:title opts#) "Time Series Plot")
           x-lab# (or (:x-label opts#) (str '~x))
           y-lab# (or (:y-label opts#) (str '~y))
           series-lab# (or (:series-label opts#) (if group-by#
						   (format "%s, %s (0)" '~x '~y) 
						   (format "%s, %s" '~x '~y)))
	   args# (concat [~x ~y ~create-time-series-plot] (apply concat (seq (apply assoc opts# 
							   [:group-by group-by# 
							    :main-title main-title# 
							    :x-label x-lab# 
							    :y-label y-lab# 
							    :series-label series-lab#]))))]
        (apply xy-plot* args#))))



(defn scatter-plot*
  ([x y & options]
    (let [opts (when options (apply assoc {} options))
	  data (:data opts)
	  _x (if (coll? x) (to-list x) ($ x data))
	  _y (if (coll? y) (to-list y) ($ y data))
	  _group-by (when (:group-by opts) 
		      (if (coll? (:group-by opts)) 
			(to-list (:group-by opts))
			($ (:group-by opts) data)))
	  x-groups (when _group-by 
		     (map #($ :col-0 %) 
			  (vals ($group-by :col-1 (conj-cols _x _group-by)))))
	  y-groups (when _group-by 
		     (map #($ :col-0 %) 
			  (vals ($group-by :col-1 (conj-cols _y _group-by)))))
          __x (if x-groups (first x-groups) _x)
          __y (if y-groups (first y-groups) _y)
	  main-title (or (:title opts) "Scatter Plot")
	  x-lab (or (:x-label opts) (str 'x))
	  y-lab (or (:y-label opts) (str 'y))
	  series-lab (or (:series-label opts) 
			 (if x-groups 
			   (format "%s, %s (0)" 'x 'y) 
			   (format "%s, %s" 'x 'y)))
	  theme (or (:theme opts) :default)
	  legend? (true? (:legend opts))
	  data-series (XYSeries. series-lab)
	  _dataset (XYSeriesCollection.)
	  chart (do
		  (doseq [i (range (count __x))] (.add data-series (nth __x i)  (nth __y i)))
		  (.addSeries _dataset data-series)
		  (org.jfree.chart.ChartFactory/createScatterPlot
		   main-title
		   x-lab
		   y-lab
		   _dataset
		   org.jfree.chart.plot.PlotOrientation/VERTICAL
		   legend?
		   true		; tooltips
		   false))
	  _ (when x-groups
	      (doseq [i (range 1 (count x-groups))]
		(add-points chart 
			    (nth x-groups i)
			    (nth y-groups i)
			    :series-label (format "%s, %s (%s)" 'x 'y i))))]
      (set-theme chart theme)
      chart)))




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
    :group-by (default nil) -- a vector of values used to group the x and y values into series.

  See also:
    view, save, add-points, add-lines

  Examples:

    (use '(incanter core stats charts datasets))
    ;; create some data
    (def mvn-samp (sample-mvn 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))

    ;; create scatter-plot of points
    (def mvn-plot (scatter-plot (sel mvn-samp :cols 0) (sel mvn-samp :cols 1)))
    (view mvn-plot)

    ;; add regression line to scatter plot
    (def x (sel mvn-samp :cols 0))
    (def y (sel mvn-samp :cols 1))
    (def lm (linear-model y x))
    (add-lines mvn-plot x (:fitted lm))

    ;; use :group-by option
    (use '(incanter core stats datasets charts))
    ;; load the :iris dataset
    (def iris (get-dataset :iris))
    ;; plot the first two columns grouped by the fifth column
    (view (scatter-plot ($ :Sepal.Width iris) ($ :Sepal.Length iris) :group-by ($ :Species iris)))

    (view (scatter-plot :Sepal.Length :Sepal.Width :data (get-dataset :iris)))

    (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species :data (get-dataset :iris)))

    (with-data (get-dataset :iris)
       (view (scatter-plot :Sepal.Length :Sepal.Width)))

    (with-data (get-dataset :iris)
       (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species)))



  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([x y & options]
    `(let [opts# ~(when options (apply assoc {} options))
           group-by# (:group-by opts#) 
           main-title# (or (:title opts#) "Scatter Plot")
           x-lab# (or (:x-label opts#) (str '~x))
           y-lab# (or (:y-label opts#) (str '~y))
           series-lab# (or (:series-label opts#) (if group-by#
						   (format "%s, %s (0)" '~x '~y) 
						   (format "%s, %s" '~x '~y)))
	   args# (concat [~x ~y] (apply concat (seq (apply assoc opts# 
							   [:group-by group-by# 
							    :main-title main-title# 
							    :x-label x-lab# 
							    :y-label y-lab# 
							    :series-label series-lab#]))))]
        (apply scatter-plot* args#))))



(defn histogram*
  ([x & options]
    (let [opts (if options (apply assoc {} options) {})
          data (:data opts)
	  _x (if (coll? x) (to-list x) ($ x data))
          nbins (or (:nbins opts) 10)
	  theme (or (:theme opts) :default)
          density? (true? (:density opts))
          main-title (or (:title opts) "Histogram")
          x-lab (or (:x-label opts) (str 'x))
          y-lab (or (:y-label opts)
                     (if density? "Density" "Frequency"))
          series-lab (or (:series-label opts) (str 'x))
          legend? (true? (:legend opts))
          dataset (HistogramDataset.)]
      (do
        (.addSeries dataset series-lab (double-array _x) nbins)
        (when density? (.setType dataset org.jfree.data.statistics.HistogramType/SCALE_AREA_TO_1))
        (set-theme (org.jfree.chart.ChartFactory/createHistogram
		    main-title
		    x-lab
		    y-lab
		    dataset
		    org.jfree.chart.plot.PlotOrientation/VERTICAL
		    legend?			; no legend
		    true				; tooltips
		    false) theme)))))



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

    (use '(incanter core charts stats))
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

    (use 'incanter.datasets)
    (def iris (get-dataset :iris))
    (view (histogram :Sepal.Width :data iris))

    (with-data (get-dataset :iris)
      (view (histogram :Petal.Length)))



  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([x & options]
    `(let [opts# ~(if options (apply assoc {} options) {})
           main-title# (or (:title opts#) "Histogram")
           x-lab# (or (:x-label opts#) (str '~x))
	   series-lab# (or (:series-label opts#) (str '~x))
           args# (concat [~x] (apply concat (seq (apply assoc opts# 
							[:main-title main-title# 
							 :x-label x-lab# 
							 :series-label series-lab#]))))]
        (apply histogram* args#))))




(defn line-chart*
  ([categories values & options]
    (let [opts (when options (apply assoc {} options))
	  data (:data opts)
	  _values (if (coll? values) (to-list values) ($ values data))
	  _categories (if (coll? categories) (to-list categories) ($ categories data))
	  main-title (or (:title opts) "Line Chart")
	  group-by (when (:group-by opts) 
		     (if (coll? (:group-by opts)) 
		       (to-list (:group-by opts))
		       ($ (:group-by opts) data)))
	  x-label (or (:x-label opts) (str 'categories))
	  y-label (or (:y-label opts) (str 'values))
	  series-label (:series-label opts)
	  vertical? (if (false? (:vertical opts)) false true)
	  theme (or (:theme opts) :default)
	  legend? (true? (:legend opts))
	  dataset (DefaultCategoryDataset.)
	  chart (org.jfree.chart.ChartFactory/createLineChart
		 main-title
		 x-label
		 y-label
		 dataset
		 (if vertical?
		   org.jfree.chart.plot.PlotOrientation/VERTICAL
		   org.jfree.chart.plot.PlotOrientation/HORIZONTAL)
		 legend?
		 true
		 false)]
      (do
	(doseq [i (range 0 (count _values))] (.addValue dataset
						       (nth _values i)
						       (cond 
							group-by
							  (nth group-by i)
							series-label
							  series-label
							:else
							  (str 'values))
                                                       (nth _categories i)))
	(set-theme chart theme)
	chart))))


(defmacro line-chart
" Returns a JFreeChart object representing a line-chart of the given values and categories.
  Use the 'view' function to display the chart, or the 'save' function
  to write it to a file.

  Arguments:
    categories -- a sequence of categories
    values -- a sequence of numeric values

  Options:
    :title (default 'Histogram') main title
    :x-label (default 'Categories')
    :y-label (default 'Value')
    :legend (default false) prints legend
    :series-label
    :group-by (default nil) -- a vector of values used to group the values into
                               series within each category.


  See also:
    view and save

  Examples:

    (use '(incanter core stats charts datasets))

    (def data (get-dataset :airline-passengers))
    (def years (sel data :cols 0))
    (def months (sel data :cols 2))
    (def passengers (sel data :cols 1))
    (view (line-chart years passengers :group-by months :legend true))
    (view (line-chart months passengers :group-by years :legend true))


    (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))
    (def years (mapcat identity (repeat 4 [2007 2008 2009])))
    (def x (sample-uniform 12 :integers true :max 100))
    (view (line-chart years x :group-by seasons :legend true))

    (view (line-chart [\"a\" \"b\" \"c\" \"d\" \"e\" \"f\"] [10 20 30 10 40 20]))

    (view (line-chart (sample \"abcdefghij\" :size 10 :replacement true)
                         (sample-uniform 10 :max 50) :legend true))

    ;; add a series label
    (def plot (line-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))
    (view plot) 
    (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")  
 

    (view (line-chart :year :passengers :group-by :month :legend true :data data)) 
    
    (view (line-chart :month :passengers :group-by :year :legend true :data data))
    
    (with-data data
      (view (line-chart :month :passengers :group-by :year :legend true)))

    (with-data (->> ($rollup :sum :passengers :year (get-dataset :airline-passengers))
                    ($order :year :asc))
      (view (line-chart :year :passengers)))

    (with-data (->> ($rollup :sum :passengers :month (get-dataset :airline-passengers))
                    ($order :passengers :asc))
      (view (line-chart :month :passengers)))


    (with-data ($rollup :sum :passengers :month (get-dataset :airline-passengers))
      (view (line-chart :month :passengers)))



  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([categories values & options]
    `(let [opts# ~(when options (apply assoc {} options))
           group-by# (:group-by opts#) 
           main-title# (or (:title opts#) "Line Chart")
           x-lab# (or (:x-label opts#) (str '~categories))
           y-lab# (or (:y-label opts#) (str '~values))
           series-lab# (or (:series-label opts#) (if group-by#
						   (format "%s, %s (0)" '~categories '~values) 
						   (format "%s, %s" '~categories '~values)))
	   args# (concat [~categories ~values] (apply concat (seq (apply assoc opts# 
							   [:group-by group-by# 
							    :main-title main-title# 
							    :x-label x-lab# 
							    :y-label y-lab# 
							    :series-label series-lab#]))))]
        (apply line-chart* args#))))




(defn bar-chart*
  ([categories values & options]
     (let [opts (when options (apply assoc {} options))
	   data (:data opts)
	  _values (if (coll? values) (to-list values) ($ values data))
	  _categories (if (coll? categories) (to-list categories) ($ categories data))
           main-title (or (:title opts) "Bar Chart")
	   theme (or (:theme opts) :default)
           _group-by (when (:group-by opts) 
		     (if (coll? (:group-by opts)) 
		       (to-list (:group-by opts))
		       ($ (:group-by opts) data)))
           x-label (or (:x-label opts) (str 'categories))
           y-label (or (:y-label opts) (str 'values))
	   series-label (:series-label opts)
           vertical? (if (false? (:vertical opts)) false true)
           legend? (true? (:legend opts))
           dataset (DefaultCategoryDataset.)
           chart (org.jfree.chart.ChartFactory/createBarChart
                     main-title
                     x-label
                     y-label
                     dataset
                     (if vertical?
                       org.jfree.chart.plot.PlotOrientation/VERTICAL
                       org.jfree.chart.plot.PlotOrientation/HORIZONTAL)
                     legend?
                     true
                     false)]
        (do
          (doseq [i (range 0 (count _values))] 
	    (.addValue dataset
		       (nth _values i)
		       (cond 
			_group-by
			  (nth _group-by i)
			series-label
			  series-label
			:else
			  (str 'values))
		       (nth _categories i)))
          (set-theme chart theme)
	  chart))))



(defmacro bar-chart
" Returns a JFreeChart object representing a bar-chart of the given data.
  Use the 'view' function to display the chart, or the 'save' function
  to write it to a file.

  Arguments:
    categories -- a sequence of categories
    values -- a sequence of numeric values

  Options:
    :title (default 'Histogram') main title
    :x-label (default 'Categories')
    :y-label (default 'Value')
    :series-label
    :legend (default false) prints legend
    :vertical (default true) the orientation of the plot
    :group-by (default nil) -- a vector of values used to group the values into
                               series within each category.


  See also:
    view and save

  Examples:


    (use '(incanter core stats charts datasets))

    (with-data (get-dataset :co2)
      (view (bar-chart :Type :uptake
                       :title \"CO2 Uptake\"
                       :group-by :Treatment
                       :x-label \"Grass Types\" :y-label \"Uptake\"
                      :legend true)))


    (def data (get-dataset :airline-passengers))
    (view (bar-chart :year :passengers :group-by :month :legend true :data data))

    (with-data  (get-dataset :airline-passengers)
      (view (bar-chart :month :passengers :group-by :year :legend true)))


    (def data (get-dataset :austres))
    (view data)
    (def plot (bar-chart :year :population :group-by :quarter :legend true :data data))
    (view plot)
    (save plot \"/tmp/austres_plot.png\" :width 1000)
    (view \"file:///tmp/austres_plot.png\")


    (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))
    (def years (mapcat identity (repeat 4 [2007 2008 2009])))
    (def values (sample-uniform 12 :integers true :max 100))
    (view (bar-chart years values :group-by seasons :legend true))

    (view (bar-chart [\"a\" \"b\" \"c\"] [10 20 30]))
    (view (bar-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]
                     :legend true
                     :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))

    ;; add a series label
    (def plot (bar-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))
    (view plot) 
    (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")  
 
    (view (bar-chart (sample \"abcdefghij\" :size 10 :replacement true)
                     (sample-uniform 10 :max 50) :legend true))



  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([categories values & options]
    `(let [opts# ~(when options (apply assoc {} options))
           group-by# (:group-by opts#) 
           main-title# (or (:title opts#) "Bar Chart")
           x-lab# (or (:x-label opts#) (str '~categories))
           y-lab# (or (:y-label opts#) (str '~values))
           series-lab# (or (:series-label opts#) (if group-by#
						   (format "%s (0)" '~categories) 
						   (format "%s" '~categories)))
	   args# (concat [~categories ~values] (apply concat (seq (apply assoc opts# 
							   [:group-by group-by# 
							    :main-title main-title# 
							    :x-label x-lab# 
							    :y-label y-lab# 
							    :series-label series-lab#]))))]
        (apply bar-chart* args#))))



(defn pie-chart*
  ([categories values & options]
     (let [opts (when options (apply assoc {} options))
	   data (:data opts)
	  _values (if (coll? values) (to-list values) ($ values data))
	  _categories (if (coll? categories) (to-list categories) ($ categories data))
           main-title (or (:title opts) "Bar Chart")
	   theme (or (:theme opts) :default)
           legend? (true? (:legend opts))
           dataset (DefaultPieDataset.)
           chart (org.jfree.chart.ChartFactory/createPieChart
                     main-title
		     dataset
		     legend?
                     true
                     false)]
        (do
	  (doseq [i (range 0 (count _values))] 
	    (.setValue dataset (nth _categories i) (nth _values i)))
	  (set-theme chart theme)
	  chart))))



(defmacro pie-chart
" Returns a JFreeChart object representing a pie-chart of the given data.
  Use the 'view' function to display the chart, or the 'save' function
  to write it to a file.

  Arguments:
    categories -- a sequence of categories
    values -- a sequence of numeric values

  Options:
    :title (default 'Histogram') main title
    :legend (default false) prints legend
    

  See also:
    view and save

  Examples:


    (use '(incanter core stats charts datasets))

    (view (pie-chart [\"a\" \"b\" \"c\"] [10 20 30]))

     (view (pie-chart (sample \"abcdefghij\" :size 10 :replacement true)
                     (sample-uniform 10 :max 50) :legend true))


     (with-data (->> (get-dataset :hair-eye-color)
                     ($rollup :sum :count [:hair :eye]))
       (view $data)
       (view (pie-chart :hair :count :title \"Hair Color\"))
       (view (pie-chart :eye :count :title \"Eye Color\")))



  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([categories values & options]
    `(let [opts# ~(when options (apply assoc {} options))
	   main-title# (or (:title opts#) "Bar Chart")
	   args# (concat [~categories ~values] 
			 (apply concat (seq (apply assoc opts# 
						   [:main-title main-title#]))))]
        (apply pie-chart* args#))))



(defn box-plot*
  ([x & options]
    (let [opts (when options (apply assoc {} options))
	  data (:data opts)
	  _x (if (coll? x) (to-list x) ($ x data))
	  _group-by (when (:group-by opts) 
		      (if (coll? (:group-by opts)) 
			(to-list (:group-by opts))
			($ (:group-by opts) data)))
	  x-groups (when _group-by 
		     (map #($ :col-0 %) 
			  (vals ($group-by :col-1 (conj-cols _x _group-by)))))
	  __x (if x-groups
                (first x-groups)
                _x)
	  main-title (or (:title opts) "Boxplot")
	  x-label (or (:x-label opts) "")
	  y-label (or (:y-label opts) (str 'x))
	  series-label (or (:series-label opts) (str 'x))
	  category-label (or (:category-label opts) 0)
	  group-labels (:group-labels opts)
	  theme (or (:theme opts) :default)
	  legend? (true? (:legend opts))
	  dataset (DefaultBoxAndWhiskerCategoryDataset.)
	  chart (org.jfree.chart.ChartFactory/createBoxAndWhiskerChart
		 main-title
		 x-label
		 y-label
		 dataset
		 legend?)]
        (do
          (-> chart .getCategoryPlot .getRenderer (.setMaximumBarWidth 0.25))
          (.add dataset __x 
		(if _group-by 
		  (str series-label " (0)") 
		  series-label) 
		category-label)
          (when-not (empty? (rest x-groups))
            (doseq [i (range 1 (count x-groups))] 
	      (.add dataset 
		    (nth x-groups i) 
		    (str series-label " (" i ")") i)))
          (set-theme chart theme)
	  chart))))



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
    :group-by (default nil) -- a vector of values used to group the x values into series.

  See also:
    view and save

  Examples:

    (use '(incanter core stats charts))
    (def gamma-box-plot (box-plot (sample-gamma 1000 :shape 1 :rate 2)
                          :title \"Gamma Boxplot\"
                          :legend true))
    (view gamma-box-plot)
    (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 2 :rate 2))
    (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 3 :rate 2))

    ;; use the group-by options
    (use '(incanter core stats datasets charts))
    (with-data (get-dataset :iris)
      (view (box-plot :Petal.Length :group-by :Species :legend true))
      (view (box-plot :Petal.Width :group-by :Species :legend true))
      (view (box-plot :Sepal.Length :group-by :Species :legend true))
      (view (box-plot :Sepal.Width :group-by :Species :legend true)))

    ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots

  References:
    http://www.jfree.org/jfreechart/api/javadoc/
    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html

"
  ([x & options]
    `(let [opts# ~(when options (apply assoc {} options))
           group-by# (:group-by opts#) 
           main-title# (or (:title opts#) "Bar Chart")
	   x-lab# (or (:x-label opts#) "")
           y-lab# (or (:y-label opts#) (str '~x))
           series-lab# (or (:series-label opts#) (str '~x))
           category-lab# (or (:category-label opts#) 0)
	   args# (concat [~x] (apply concat (seq (apply assoc opts# 
							[:group-by group-by# 
							 :main-title main-title# 
							 :x-label x-lab# 
							 :y-label y-lab# 
							 :category-label category-lab#
							 :series-label series-lab#]))))]
        (apply box-plot* args#))))




(defn function-plot*
  ([function min-range max-range & options]
   (let [opts (when options (apply assoc {} options))
	 step-size (or (:step-size opts) (float (/ (- max-range min-range) 500)))
	 _x (range min-range max-range step-size)
	 main-title (or (:title opts) "Function Plot")
	 x-lab (or (:x-label opts) (format "%s < x < %s" min-range max-range))
	 y-lab (or (:y-label opts) (str 'function))
	 series-lab (or (:series-label opts) (format "%s" 'function))
	 theme (or (:theme opts) :default)
	 legend? (true? (:legend opts))]
      (set-theme (xy-plot _x (map function _x)
			  :x-label x-lab
			  :y-label y-lab
			  :title main-title
			  :series-label series-lab
			  :legend legend?) theme))))




(defmacro function-plot
" Returns a xy-plot object of the given function over the range indicated
  by the min-range and max-range arguments. Use the 'view' function to
  display the chart, or the 'save' function to write it to a file.

  Options:
    :title (default 'Histogram') main title
    :x-label (default x expression)
    :y-label (default 'Frequency')
    :legend (default false) prints legend
    :series-label (default x expression)
    :step-size (default (/ (- max-range min-range) 500))

  See also:
    view, save, add-points, add-lines


  Examples:

    (use '(incanter core stats charts))

    (view (function-plot sin (- Math/PI) Math/PI))
    (view (function-plot pdf-normal -3 3))

    (defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))
    (view (function-plot cubic -10 10))

"
  ([function min-range max-range & options]
    `(let [opts# ~(when options (apply assoc {} options))
           group-by# (:group-by opts#) 
           main-title# (or (:title opts#) "Function Plot")
           x-lab# (or (:x-label opts#) (format "%s < x < %s" '~min-range '~max-range))
           y-lab# (or (:y-label opts#) (str '~function))
           series-lab# (or (:series-label opts#) (format "%s" '~function))
	   args# (concat [~function ~min-range ~max-range] 
			 (apply concat (seq (apply assoc opts# 
						   [:group-by group-by# 
						    :main-title main-title# 
						    :x-label x-lab# 
						    :y-label y-lab# 
						    :series-label series-lab#]))))]
       (apply function-plot* args#))))



(defn heat-map*
  ([function x-min x-max y-min y-max & options]
     (let [opts (when options (apply assoc {} options))
	   color? (if (false? (:color? opts)) false true)
	   title (or (:title opts) "Heat Map")
	   x-label (or (:x-label opts) "x")
	   y-label (or (:y-label opts) "y")
	   z-label (or (:z-label opts) "z scale")
	   xyz-dataset (org.jfree.data.xy.DefaultXYZDataset.)
	   data (into-array (map double-array 
				 (grid-apply function x-min x-max y-min y-max)))
	   min-z (reduce min (last data))
	   max-z (reduce max (last data))
	   x-axis (doto (org.jfree.chart.axis.NumberAxis. x-label)
		    (.setStandardTickUnits (org.jfree.chart.axis.NumberAxis/createIntegerTickUnits))
		    (.setLowerMargin 0.0)
		    (.setUpperMargin 0.0)
		    (.setAxisLinePaint java.awt.Color/white)
		    (.setTickMarkPaint java.awt.Color/white))
	   y-axis (doto (org.jfree.chart.axis.NumberAxis. y-label)
		    (.setStandardTickUnits (org.jfree.chart.axis.NumberAxis/createIntegerTickUnits))
		    (.setLowerMargin 0.0)
		    (.setUpperMargin 0.0)
		    (.setAxisLinePaint java.awt.Color/white)
		    (.setTickMarkPaint java.awt.Color/white))
	   colors (or (:colors opts) 
		      [[0 0 127] [0 0 212] [0 42 255] [0 127 255] [0 127 255] 
		       [0 226 255] [42 255 212] [56 255 198] [255 212 0] [255 198 0]
		       [255 169 0] [255 112 0] [255 56 0] [255 14 0] [255 42 0]
		       [226 0 0]])
	   scale (if color?
		   (org.jfree.chart.renderer.LookupPaintScale. min-z max-z java.awt.Color/white)
		   (org.jfree.chart.renderer.GrayPaintScale. min-z max-z))
	   add-color (fn [idx color]
		       (.add scale 
			     (+ min-z (* (/ idx (count colors)) (- max-z min-z))) 
			     (apply #(java.awt.Color. %1 %2 %3) color)))
	   scale-axis (org.jfree.chart.axis.NumberAxis. z-label)
	   legend (org.jfree.chart.title.PaintScaleLegend. scale scale-axis)
	   renderer (org.jfree.chart.renderer.xy.XYBlockRenderer.)
	   
	   plot (org.jfree.chart.plot.XYPlot. xyz-dataset x-axis y-axis renderer)
	   chart (org.jfree.chart.JFreeChart. plot)]
       (do
	(.setPaintScale renderer scale)
	(when color? (doseq [i (range (count colors))]
		       (add-color i (nth colors i))))
	(.addSeries xyz-dataset "Series 1" data)
	(.setBackgroundPaint plot java.awt.Color/lightGray)
	(.setDomainGridlinesVisible plot false)
	(.setRangeGridlinePaint plot java.awt.Color/white)
	(.setAxisOffset plot (org.jfree.ui.RectangleInsets. 5 5 5 5))
	(.setOutlinePaint plot java.awt.Color/blue)
	(.removeLegend chart)
	(.setSubdivisionCount legend 20)
	(.setAxisLocation legend org.jfree.chart.axis.AxisLocation/BOTTOM_OR_LEFT)
	(.setAxisOffset legend 5.0)
	(.setMargin legend (org.jfree.ui.RectangleInsets. 5 5 5 5))
	(.setFrame legend (org.jfree.chart.block.BlockBorder. java.awt.Color/red))
	(.setPadding legend (org.jfree.ui.RectangleInsets. 10 10 10 10))
	(.setStripWidth legend 10)
	(.setPosition legend org.jfree.ui.RectangleEdge/RIGHT)
	(.setTitle chart title)
	(.addSubtitle chart legend)
	(org.jfree.chart.ChartUtilities/applyCurrentTheme chart))
       chart)))


(defmacro heat-map
"
  Examples:
    (use '(incanter core charts))
    (defn f [x y] (sin (sqrt (plus (sq x) (sq y)))))
    (view (heat-map f -10 10 -15 15))
    (view (heat-map f -10 10 -10 10 :color? false))

    (defn f2 [x y] (plus (sq x) (sq y)))
    (view (heat-map f2 -10 10 -10 10))
    (view (heat-map f2 -10 10 -10 10 :color? false))

    (use 'incanter.stats)
    (defn f3 [x y] (pdf-normal (sqrt (plus (sq x) (sq y)))))
    (view (heat-map f3 -3 3 -3 3 :x-label \"x1\" :y-label \"x2\" :z-label \"pdf\"))
    (view (heat-map f3 -3 3 -3 3 :color? false))

    (defn f4 [x y] (minus (sq x) (sq y)))
    (view (heat-map f4 -10 10 -10 10))
    (view (heat-map f4 -10 10 -10 10 :color? false))


    (use '(incanter core stats charts))
    (let [points [[0 5] 
  	          [0 10] 
	          [15 0] 
	          [18 10]] 
          diffusion (fn [x y] 
	  	      (sum (map #(pdf-normal (euclidean-distance [x y] %) :sd 2) 
			        points)))]
      (view (heat-map diffusion -5 20 -5 20)))


"
  ([function x-min x-max y-min y-max & options]
    `(let [opts# ~(when options (apply assoc {} options))
           x-lab# (or (:x-label opts#) (format "%s < x < %s" '~x-min '~x-max))
	   y-lab# (or (:y-label opts#) (format "%s < y < %s" '~y-min '~y-max))
           z-lab# (or (:z-label opts#) (str '~function))
           args# (concat [~function ~x-min ~x-max ~y-min ~y-max] 
			 (apply concat (seq (apply assoc opts# 
						   [:z-label z-lab#
						    :x-label x-lab# 
						    :y-label y-lab#]))))]
       (apply heat-map* args#))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;  ANNOTATIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn add-pointer
" Adds an arrow annotation to the given chart.

  Arguments:
    chart -- the chart to annotate
    x, y -- the coordinate to add the annotation


  Options:
      :text -- (default \"\") text to include at the end of the arrow
      :angle -- (default :nw) either a number indicating the angle of the arrow
                or a keyword indicating a direction (:north :nw :west :sw :south
                :se :east :ne)


  Examples:

    (use '(incanter core charts))
    (def x (range (* -2 Math/PI) (* 2 Math/PI) 0.01))
    (def plot (xy-plot x (sin x)))
    (view plot)
    ;; annotate the plot
    (doto plot
      (add-pointer (- Math/PI) (sin (- Math/PI)) :text \"(-pi, (sin -pi))\")
      (add-pointer Math/PI (sin Math/PI) :text \"(pi, (sin pi))\" :angle :ne)
      (add-pointer (* 1/2 Math/PI) (sin (* 1/2 Math/PI)) :text \"(pi/2, (sin pi/2))\" :angle :south))

    ;; try the different angle options
    (add-pointer plot 0 0 :text \"north\" :angle :north)
    (add-pointer plot 0 0 :text \"nw\" :angle :nw)
    (add-pointer plot 0 0 :text \"ne\" :angle :ne)
    (add-pointer plot 0 0 :text \"west\" :angle :west)
    (add-pointer plot 0 0 :text \"east\" :angle :east)
    (add-pointer plot 0 0 :text \"south\" :angle :south)
    (add-pointer plot 0 0 :text \"sw\" :angle :sw)
    (add-pointer plot 0 0 :text \"se\" :angle :se)


"
  ([chart x y & options]
    (let [opts (when options (apply assoc {} options))
          text (or (:text opts) "")
          n (* -1/2 Math/PI)
          s (* 1/2 Math/PI)
          w Math/PI
          e 0
          nw (* -3/4 Math/PI)
          ne (* -1/4 Math/PI)
          se (* 1/4 Math/PI)
          sw (* 3/4 Math/PI)
          angle (if (nil? (:angle opts))
                    nw
                    (if (keyword? (:angle opts))
                      (cond
                        (= (:angle opts) :north) n
                        (= (:angle opts) :south) s
                        (= (:angle opts) :west) w
                        (= (:angle opts) :east) e
                        (= (:angle opts) :nw) nw
                        (= (:angle opts) :ne) ne
                        (= (:angle opts) :sw) sw
                        (= (:angle opts) :se) se)
                      (:angle opts)))
          anno (XYPointerAnnotation. text x y angle)]
      (do
        (cond
          (= angle n)
            (.setTextAnchor anno TextAnchor/BASELINE_CENTER)
          (= angle s)
            (.setTextAnchor anno TextAnchor/CENTER)
          (= angle w)
            (.setTextAnchor anno TextAnchor/CENTER_RIGHT)
          (= angle e)
            (.setTextAnchor anno TextAnchor/CENTER_LEFT)
          (= angle nw)
            (.setTextAnchor anno TextAnchor/CENTER_RIGHT)
          (= angle ne)
            (.setTextAnchor anno TextAnchor/CENTER_LEFT)
          (= angle sw)
            (.setTextAnchor anno TextAnchor/CENTER_RIGHT)
          (= angle se)
            (.setTextAnchor anno TextAnchor/CENTER_LEFT))
        (.addAnnotation (.getPlot chart) anno)))))






(defn add-text
" Adds a text annotation centered at the given coordinates.

  Arguments:
    chart -- the chart to annotate
    x, y -- the coordinates to center the text
    text -- the text to add


  Examples:

    ;; PCA chart example
    (use '(incanter core stats charts datasets))
    ;; load the iris dataset
    (def iris (to-matrix (get-dataset :iris)))
    ;; run the pca
    (def pca (principal-components (sel iris :cols (range 4))))
    ;; extract the first two principal components
    (def pc1 (sel (:rotation pca) :cols 0))
    (def pc2 (sel (:rotation pca) :cols 1))

    ;; project the first four dimension of the iris data onto the first
    ;; two principal components
    (def x1 (mmult (sel iris :cols (range 4)) pc1))
    (def x2 (mmult (sel iris :cols (range 4)) pc2))

    ;; now plot the transformed data, coloring each species a different color
    (def plot (scatter-plot x1 x2
                            :group-by (sel iris :cols 4)
                            :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))
    (view plot)
    ;; add some text annotations
    (add-text plot -2.5 -6.5 \"Setosa\")
    (add-text plot -5 -5.5 \"Versicolor\")
    (add-text plot -8 -5.5 \"Virginica\")


"
  ([chart x y text & options]
    (let [opts (when options (apply assoc {} options))
          anno (XYTextAnnotation. text x y)]
      (.addAnnotation (.getPlot chart) anno))))



(defn add-polygon
" Adds a polygon outline defined by a given coordinates. The last coordinate will
  close with the first. If only two points are given, it will plot a line.

  Arguments:
    chart -- the chart to add the polygon to.
    coords -- a list of coords (an n-by-2 matrix can also be used)


  Examples:
    (use '(incanter core stats charts))
    (def x (range -3 3 0.01))
    (def plot (xy-plot x (pdf-normal x)))
    (view plot)

    ;; add polygon to the chart
    (add-polygon plot [[-1.96 0] [1.96 0] [1.96 0.4] [-1.96 0.4]])
    ;; the coordinates can also be passed in a matrix
    ;; (def points (matrix [[-1.96 0] [1.96 0] [1.96 0.4] [-1.96 0.4]]))
    ;; (add-polygon plot points)
    ;; add a text annotation
    (add-text plot -1.25 0.35 \"95% Conf Interval\")

    ;; PCA chart example
    (use '(incanter core stats charts datasets))
    ;; load the iris dataset
    (def iris (to-matrix (get-dataset :iris)))
    ;; run the pca
    (def pca (principal-components (sel iris :cols (range 4))))
    ;; extract the first two principal components
    (def pc1 (sel (:rotation pca) :cols 0))
    (def pc2 (sel (:rotation pca) :cols 1))

    ;; project the first four dimension of the iris data onto the first
    ;; two principal components
    (def x1 (mmult (sel iris :cols (range 4)) pc1))
    (def x2 (mmult (sel iris :cols (range 4)) pc2))

    ;; now plot the transformed data, coloring each species a different color
    (def plot (scatter-plot x1 x2
                            :group-by (sel iris :cols 4)
                            :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))

    (view plot)
    ;; put box around the first group
    (add-polygon plot [[-3.2 -6.3] [-2 -6.3] [-2 -3.78] [-3.2 -3.78]])
    ;; add some text annotations
    (add-text plot -2.5 -6.5 \"Setosa\")
    (add-text plot -5 -5.5 \"Versicolor\")
    (add-text plot -8 -5.5 \"Virginica\")



"
  ([chart coords & options]
    (let [opts (when options (apply assoc {} options))
          points (double-array (mapcat identity coords))
          anno (XYPolygonAnnotation. points)]
      (.addAnnotation (.getPlot chart) anno))))






;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;  OTHER CHARTS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

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
    (let [opts (when options (apply assoc {} options))
	  data (:data opts)
	  _x (if (coll? x) (to-list x) ($ x data))
          title (or (:title opts) "Trace Plot")
          x-label (or (:x-label opts) "Iteration")
          y-label (or (:y-label opts) "Value")
          series-lab (or (:series-label opts) "Value")
	  theme (or (:theme opts) :default)
          ;legend? (or (:series-label opts) true)
          n (count _x)
          chart (xy-plot (range n)
                         _x ;)]
                         :title title
                         :x-label x-label
                         :y-label y-label
                         :series-label series-lab)]
      (do
        (add-lines chart (range n) (cumulative-mean _x) :series-label "running mean")
        (.setSeriesRenderingOrder (.getPlot chart) SeriesRenderingOrder/FORWARD)
        (.setDatasetRenderingOrder (.getPlot chart) DatasetRenderingOrder/FORWARD)
	(set-theme chart theme)
        chart))))




(defn qq-plot
"
  Returns a QQ-Plot object. Use the 'view' function to display it.

  References:
    http://en.wikipedia.org/wiki/QQ_plot

  Examples:

    (use '(incanter core stats charts datasets))
    (view (qq-plot (sample-normal 100)))
    (view (qq-plot (sample-exp 100)))
    (view (qq-plot (sample-gamma 100)))

    (with-data (get-dataset :iris)
      (view (qq-plot :Sepal.Length)))



"
  ([x & options]
   (let [opts (when options (apply assoc {} options))
	 data (:data opts)
	 _x (if (coll? x) (to-list x) ($ x data))
	 n (count _x)
         quants (for [k (range 1 n)] (/ k (inc n)))
         norm-quants (quantile-normal quants)
	 theme (or (:theme opts) :default)
         y (quantile _x :probs quants)]
         (set-theme (scatter-plot norm-quants y
				  :title "QQ-Plot"
				  :x-label "Normal theoretical quantiles"
				  :y-label "Data quantiles"
				  :series-label "Theoretical Normal")
		    theme))))




(defn bland-altman-plot
"

  Examples:

    (use '(incanter core datasets charts))
    (def flow-meter (to-matrix (get-dataset :flow-meter)))
    (def x1 (sel flow-meter :cols 1))
    (def x2 (sel flow-meter :cols 3))
    (view (bland-altman-plot x1 x2))

    (with-data (get-dataset :flow-meter)
      (view (bland-altman-plot \"Wright 1st PEFR\" \"Mini Wright 1st PEFR\")))



  References:
    http://en.wikipedia.org/wiki/Bland-Altman_plot
    http://www-users.york.ac.uk/~mb55/meas/ba.htm

"
  ([x1 x2 & options]
      (let [opts (when options (apply assoc {} options))
	    data (:data opts)
	    _x1 (if (coll? x1) (to-list x1) ($ x1 data))
	    _x2 (if (coll? x2) (to-list x2) ($ x2 data))
	    plot (scatter-plot (div (plus _x1 _x2) 2) (minus _x1 _x2)
                               :title "Bland Altman Plot"
                               :legend false)
            x-axis (div (plus _x1 _x2) 2)
            y-axis (minus _x1 _x2)
            min-x (reduce min x-axis)
            max-x (reduce max x-axis)
            _x (range min-x max-x (/ (- max-x min-x) 100))
            y-sd (* (sd y-axis) 2)
	    theme (or (:theme opts) :default)]
        (do
          (add-lines plot _x (repeat (count _x) 0) :series-label "mean")
          (add-lines plot _x (repeat (count _x) y-sd) :series-label "mean + sd")
          (add-lines plot _x (repeat (count _x) (- 0 y-sd)) :series-label "mean - sd")
	  (set-theme plot theme)
          plot))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defmethod view org.jfree.chart.JFreeChart
  ([chart & options]
    (let [opts (when options (apply assoc {} options))
          window-title (or (:window-title opts) "Incanter Plot")
          width (or (:width opts) 500)
          height (or (:height opts) 400)
          frame (ChartFrame. window-title chart)]
      (doto frame
        (.setSize width height)
        (.setVisible true))
      frame)))


(defmethod save org.jfree.chart.JFreeChart
  ([chart filename & options]
    (let [opts (when options (apply assoc {} options))
          width (or (:width opts) 500)
          height (or (:height opts) 400)]
      ;; if filename is not a string, treat it as java.io.OutputStream
      (if (string? filename)
        (ChartUtilities/saveChartAsPNG (File. filename) chart width height)
        (ImageIO/write (.createBufferedImage chart width height) "png" filename))
      nil)))





