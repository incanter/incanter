(ns #^{:doc "This library is used to render LaTex Math equations and is based
   on the jLateXMath library.
            "
       :author "David Edgar Liebke"}

  incanter.latex
  (:use [incanter.core :only (save)])
  (:import [org.scilab.forge.jlatexmath TeXConstants TeXIcon TeXFormula]))



(defn latex 
"

  Examples:
    (use '(incanter core charts latex))
    (def latex-img (latex \"\\\\frac{(a+b)^2} {(a-b)^2}\"))
    (save latex-img \"/tmp/latex-example1.png\")
    (view \"file:///tmp/latex-example1.png\")


"
  ([latex-txt]
     (let [formula (org.scilab.forge.jlatexmath.TeXFormula. latex-txt)
	   icon (doto (.createTeXIcon formula TeXConstants/STYLE_DISPLAY 20)
		  (.setInsets (java.awt.Insets. 5 5 5 5)))
	   image (java.awt.image.BufferedImage. (.getIconWidth icon) 
						(.getIconHeight icon) 
						java.awt.image.BufferedImage/TYPE_INT_ARGB)
	   g2 (doto (.createGraphics image)
		(.setColor (java.awt.Color. 0 0 0 0))
		(.fillRect 0 0 (.getIconWidth icon) (.getIconHeight icon)))
	   label (doto (javax.swing.JLabel.)
		   (.setForeground java.awt.Color/gray))]
       (do
	 (.paintIcon icon label g2 0 0)
	 image))))






(defn add-latex-subtitle
" Sets the chart's title to the rendered LaTeX equation.

  Examples:
    (use '(incanter core charts latex))

    (doto (function-plot sin -10 10)
      (set-latex-title \" \\\\frac{(a+b)^2} {(a-b)^2}\")
      view)

"
  ([chart latex-str]
     (doto chart
       (.addSubtitle (org.jfree.chart.title.ImageTitle. (latex latex-str))))
     chart))



(defn add-latex
" Adds an latex equation to the chart at the given coordinates.

  Arguments:
    chart -- the chart to add the polygon to.
    x, y -- the coordinates to place the image
    latex-str -- a string of latex code


  Examples:
    (use '(incanter core charts latex))   

     (doto (function-plot sin -10 10)
      (add-latex 0 0 \"\\\\frac{(a+b)^2} {(a-b)^2}\")
      view)

"
  ([chart x y latex-str & options]
    (let [opts (when options (apply assoc {} options))
	  img (latex latex-str)
	  anno (org.jfree.chart.annotations.XYImageAnnotation. x y img)]
      (.addAnnotation (.getPlot chart) anno))))


