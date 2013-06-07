(ns
^{:doc "This library currently has only a single function, save-svg, which saves
  charts as an SVG file. To build this namespace make sure the you have the Batik
  library (http://xmlgraphics.apache.org/batik) as a declared dependency in your pom.xml or
  project.clj file:
  [org.apache.xmlgraphics/batik-dom \"1.7\"]
  [org.apache.xmlgraphics/batik-svggen \"1.7\"]
  [org.apache.xmlgraphics/batik-awt-util \"1.7\"]
  [org.apache.xmlgraphics/batik-util \"1.7\"]
  [org.apache.xmlgraphics/batik-xml \"1.7\"]]"}

  incanter.svg
  (:use (incanter charts))
  (:import (java.io File FileOutputStream OutputStreamWriter)
           (java.awt Rectangle)
           (org.apache.batik.dom GenericDOMImplementation)
           (org.apache.batik.svggen SVGGraphics2D)))

;; Adapted from Java code at: http://dolf.trieschnigg.nl/jfreechart
(defn save-svg
" Save a chart object as an SVG document.

  Arguments:
    chart
    filename

  Options:
    :width (default 500)
    :height (default 400)

  Examples:

    (use '(incanter core charts svg))
    (save-svg (function-plot sin -4 4) \"./svg-chart.svg\")


"
  ([chart filename & {:keys [width height ] :or {width 500 height 400}}]
     (let [domImpl (GenericDOMImplementation/getDOMImplementation)
           document (.createDocument domImpl nil "svg" nil)
           svgGenerator (SVGGraphics2D. document)
           bounds (Rectangle. width height)
           svgFile (File. filename)
           outputStream (FileOutputStream. svgFile)
           out (OutputStreamWriter. outputStream "UTF-8")]
       (do
         (.draw chart svgGenerator bounds)
         (.stream svgGenerator out true)
         (.flush outputStream)
         (.close outputStream)))))


