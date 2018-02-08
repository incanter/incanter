(ns
^{:doc "This library currently has only a single function, save-svg, which saves
  charts as an SVG file."}

  incanter.svg
  (:use (incanter charts))
  (:import (java.io File FileOutputStream OutputStreamWriter)
           (java.awt Rectangle)
           (org.apache.batik.dom GenericDOMImplementation)
           (org.apache.batik.svggen SVGGraphics2D)))

;; Adapted from Java code at: http://dolf.trieschnigg.nl/jfreechart
(defn save-svg
  "
  Save a chart object as an SVG document.

  As with incanter.core/save, a java.io.OutputStream can be used in place of a
  filename.

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
  ([chart filename-or-stream & {:keys [width height ] :or {width 500 height 400}}]
     (let [domImpl (GenericDOMImplementation/getDOMImplementation)
           document (.createDocument domImpl nil "svg" nil)
           svgGenerator (SVGGraphics2D. document)
           bounds (Rectangle. width height)
           ;; if filename is not a string, assume it's already a stream
           outputStream (if (string? filename-or-stream)
                          (FileOutputStream. (File. filename-or-stream))
                          filename-or-stream)
           out (OutputStreamWriter. outputStream "UTF-8")]
       (do
         (.draw chart svgGenerator bounds)
         (.stream svgGenerator out true)
         (.flush outputStream)
         (.close outputStream)))))

