(ns #^{:doc "This library is used to render LaTex Math equations, based
   on the jLateXMath library, and applying them incanter.charts as annotations
   and subtitles.
            "
       :author "David Edgar Liebke"}

  incanter.latex
  ;; (:use [incanter.core :only (save)])
  (:import [org.scilab.forge.jlatexmath TeXConstants TeXIcon TeXFormula]))



(defn latex 
" Returns the given LaTeX equation rendered as an java.awt.Image.

  Options:
    :color (default java.awt.Color/black) -- the text color

  Examples:
    (use '(incanter core charts stats latex))

    (def latex-img (latex \"\\\\frac{(a+b)^2} {(a-b)^2}\"))
    (save latex-img \"/tmp/latex-example1.png\")
    (view \"file:///tmp/latex-example1.png\")

    (view (latex \"f(x)=\\\\frac {1} {\\\\sqrt {2\\\\pi \\\\sigma ^2}} e^{\\\\frac {-(x - \\\\mu)^2}{2 \\\\sigma ^2}}\"))

    (view (latex \"\\\\begin{pmatrix}
                   a & b & c \\\\\\\\
                   d & e & f \\\\\\\\
                   g & h & i
                   \\\\end{pmatrix}\"))


"
  ([latex-txt & options]
     (let [opts (apply hash-map options)
	   color (or (:color opts) java.awt.Color/black)
	   formula (org.scilab.forge.jlatexmath.TeXFormula. latex-txt)
	   icon (doto (.createTeXIcon formula TeXConstants/STYLE_DISPLAY 20)
		  (.setInsets (java.awt.Insets. 5 5 5 5)))
	   image (java.awt.image.BufferedImage. (.getIconWidth icon) 
						(.getIconHeight icon) 
						java.awt.image.BufferedImage/TYPE_INT_ARGB)
	   g2 (doto (.createGraphics image)
		(.setColor (java.awt.Color. 0 0 0 0))
		(.fillRect 0 0 (.getIconWidth icon) (.getIconHeight icon)))
	   label (doto (javax.swing.JLabel.)
		   (.setForeground color))]
       (do
	 (.paintIcon icon label g2 0 0)
	 image))))






(defn add-latex-subtitle
" Adds the given LaTeX equation as a subtitle to the chart.


  Options:
    :color (default java.awt.Color/darkGray) -- the text color


  Examples:
    (use '(incanter core charts stats latex))

    (doto (function-plot pdf-normal -3 3)
      (add-latex-subtitle \"f(x)=\\\\frac{1}{\\\\sqrt{2\\\\pi \\\\sigma^2}} e^{\\\\frac{-(x - \\\\mu)^2}{2 \\\\sigma^2}}\")
      view)

"
  ([chart latex-str & options]
     (let [opts (apply hash-map options)
	   color (or (:color opts) java.awt.Color/darkGray)] 
       (.addSubtitle chart (org.jfree.chart.title.ImageTitle. (latex latex-str :color color)))
       chart)))




(defn add-latex
" Adds an LaTeX equation annotation to the chart at the given x,y coordinates.

  Arguments:
    chart -- the chart to add the polygon to.
    x, y -- the coordinates to place the image
    latex-str -- a string of latex code


  Options:
    :color (default java.awt.Color/darkGray) -- the text color


  Examples:
    (use '(incanter core charts stats latex))   

      (doto (function-plot pdf-normal -3 3)
        (add-latex 0 0.1 \"f(x)=\\\\frac{1}{\\\\sqrt{2\\\\pi \\\\sigma^2}} e^{\\\\frac{-(x - \\\\mu)^2}{2 \\\\sigma^2}}\")
        view)

"
  ([chart x y latex-str & options]
    (let [opts (apply hash-map options)
	  color (or (:color opts) java.awt.Color/darkGray)
	  img (latex latex-str :color color)
	  anno (org.jfree.chart.annotations.XYImageAnnotation. x y img)]
      (.addAnnotation (.getPlot chart) anno)
      chart)))


