;;; graphs.clj -- Graph visualization library for Clojure built on JGraph

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



(import '(javax.swing JFrame JScrollPane))
(import '(java.awt Dimension BasicStroke))
(import '(edu.uci.ics.jung.algorithms.layout CircleLayout TreeLayout DAGLayout))
(import '(edu.uci.ics.jung.graph SparseMultigraph))
(import '(edu.uci.ics.jung.visualization BasicVisualizationServer))
(import '(org.apache.commons.collections15 Transformer))
(import '(edu.uci.ics.jung.visualization.decorators ToStringLabeller))
(import '(edu.uci.ics.jung.graph.util EdgeType))
(import '(edu.uci.ics.jung.visualization.renderers Renderer))
(import '(edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel Position))


(def graph (new SparseMultigraph))
(.addVertex graph 1)
(.addVertex graph 2)
(.addVertex graph 3)
(.addEdge graph "Edge-A" 1 2 EdgeType/DIRECTED)
(.addEdge graph "Edge-B" 2 3 EdgeType/DIRECTED)
(def layout (new CircleLayout graph))
(.setSize layout (new Dimension 300 300))
(def vv (new BasicVisualizationServer layout))
(.setPreferredSize vv (new Dimension 350 350))


(def vertex-paint (proxy [org.apache.commons.collections15.Transformer] [] (transform [i] java.awt.Color/GREEN)))
(def dash [10.0])
(def edge-stroke (new BasicStroke 1.0 BasicStroke/CAP_BUTT BasicStroke/JOIN_MITER 10.0 (float-array dash) 0.0))
(def edge-stroke-transformer (proxy [org.apache.commons.collections15.Transformer] [] (transform [s] edge-stroke)))

(.. vv getRenderContext (setVertexFillPaintTransformer vertex-paint))
(.. vv getRenderContext (setEdgeStrokeTransformer edge-stroke-transformer))
(.. vv getRenderContext (setVertexLabelTransformer (new ToStringLabeller)))
(.. vv getRenderContext (setEdgeLabelTransformer (new ToStringLabeller)))

(.. vv getRenderer
  getVertexLabelRenderer
  (setPosition (edu.uci.ics.jung.visualization.renderers.Renderer$VertexLabel$Position/CNTR)))

(def frame (new JFrame "Simple Graph View"))
;(.setDefaultCloseOperation frame JFrame/EXIT_ON_CLOSE) ; this option kills the REPL
(.. frame getContentPane (add vv))
(.pack frame)
(.setVisible frame true)



(def graph (new SparseMultigraph))
(.addVertex graph "Node 1")
(.addVertex graph "Node 2")
(.addVertex graph "Node 3")
(.addEdge graph "b_{2,1}" "Node 1" "Node 2" EdgeType/DIRECTED)
(.addEdge graph "b_{3,2}" "Node 2" "Node 3" EdgeType/DIRECTED)

(defn view-graph [graph]
  (let [
        layout (new CircleLayout graph)
        ;layout (new DAGLayout graph)
        vv (new BasicVisualizationServer layout)
        vertex-paint (proxy [org.apache.commons.collections15.Transformer] [] (transform [i] java.awt.Color/GREEN))
        dash [10.0]
        edge-stroke (new BasicStroke 1.0 BasicStroke/CAP_BUTT BasicStroke/JOIN_MITER 10.0 (float-array dash) 0.0)
        edge-stroke-transformer (proxy [org.apache.commons.collections15.Transformer] [] (transform [s] edge-stroke))
        frame (new JFrame "Simple Graph View")
       ]
    (do
      ;(.setSize layout (new Dimension 300 300))
      ;(.setPreferredSize vv (new Dimension 350 350))
      (.. vv getRenderContext (setVertexFillPaintTransformer vertex-paint))
      (.. vv getRenderContext (setEdgeStrokeTransformer edge-stroke-transformer))
      (.. vv getRenderContext (setVertexLabelTransformer (new ToStringLabeller)))
      (.. vv getRenderContext (setEdgeLabelTransformer (new ToStringLabeller)))
      ;(.. vv getRenderer getVertexLabelRenderer
        ;(setPosition (edu.uci.ics.jung.visualization.renderers.Renderer$VertexLabel$Position/CNTR)))
      ;(.setDefaultCloseOperation frame JFrame/EXIT_ON_CLOSE) ; this option kills the REPL
      (.. frame getContentPane (add vv))
      (.pack frame)
      (.setVisible frame true))))


(view-graph graph)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; JGRAPH
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def model (new org.jgraph.graph.DefaultGraphModel))
(def view (new org.jgraph.graph.GraphLayoutCache model (new org.jgraph.graph.DefaultCellViewFactory)))
(def graph (new org.jgraph.JGraph model view)) ;; creates a default graph model to display

;(def cells [
;            (new org.jgraph.graph.DefaultGraphCell "Hello")
;            (new org.jgraph.graph.DefaultGraphCell "World")
;           ])

(def cells (make-array org.jgraph.graph.DefaultGraphCell 4))
(aset cells 0 (new org.jgraph.graph.DefaultGraphCell "Hello from clojure"))
(aset cells 1 (new org.jgraph.graph.DefaultGraphCell "World"))

(. org.jgraph.graph.GraphConstants (setBounds (.getAttributes (aget cells 0))
            (new java.awt.geom.Rectangle2D$Double 20 20 40 20)))
(. org.jgraph.graph.GraphConstants (setBorderColor (.getAttributes (aget cells 0))
                                                     java.awt.Color/red))
(. org.jgraph.graph.GraphConstants (setBackground (.getAttributes (aget cells 0))
                                                     java.awt.Color/lightGray))
(. org.jgraph.graph.GraphConstants (setOpaque (.getAttributes (aget cells 0))
                                                     true))
(. org.jgraph.graph.GraphConstants (setAutoSize (.getAttributes (aget cells 0)) true))
(.add (aget cells 0) (new org.jgraph.graph.DefaultPort))

(. org.jgraph.graph.GraphConstants (setBounds (.getAttributes (aget cells 1))
            (new java.awt.geom.Rectangle2D$Double 140 140 40 20)))

(. org.jgraph.graph.GraphConstants (setBackground (.getAttributes (aget cells 1))
                                                     java.awt.Color/red))

(. org.jgraph.graph.GraphConstants (setOpaque (.getAttributes (aget cells 1))
                                                     true))

(.add (aget cells 1) (new org.jgraph.graph.DefaultPort))

(def edge (new org.jgraph.graph.DefaultEdge))
(.setSource edge (.getChildAt (aget cells 0) 0))
(.setTarget edge (.getChildAt (aget cells 1) 0))
;(. org.jgraph.graph.GraphConstants (setBendable (.getAttributes edge) true))
(aset cells 2 edge)

(. org.jgraph.graph.GraphConstants (setLineEnd (.getAttributes edge)
                                    org.jgraph.graph.GraphConstants/ARROW_TECHNICAL))
(. org.jgraph.graph.GraphConstants (setEndFill (.getAttributes edge) true))
(. org.jgraph.graph.GraphConstants (setLineBegin (.getAttributes edge)
                                    org.jgraph.graph.GraphConstants/ARROW_TECHNICAL))
(. org.jgraph.graph.GraphConstants (setBeginFill (.getAttributes edge) true))
(. org.jgraph.graph.GraphConstants (setLineWidth (.getAttributes edge) 2.0))
(. org.jgraph.graph.GraphConstants (setLineColor (.getAttributes edge) java.awt.Color/darkGray))


(def edge2 (new org.jgraph.graph.DefaultEdge))
(.setSource edge2 (.getChildAt (aget cells 0) 0))
(.setTarget edge2 (.getChildAt (aget cells 0) 0))
(aset cells 3 edge2)

(. org.jgraph.graph.GraphConstants (setLineEnd (.getAttributes edge2)
                                    org.jgraph.graph.GraphConstants/ARROW_TECHNICAL))
(. org.jgraph.graph.GraphConstants (setEndFill (.getAttributes edge2) true))
(. org.jgraph.graph.GraphConstants (setLineBegin (.getAttributes edge2)
                                    org.jgraph.graph.GraphConstants/ARROW_TECHNICAL))
(. org.jgraph.graph.GraphConstants (setBeginFill (.getAttributes edge2) true))
(. org.jgraph.graph.GraphConstants (setLineWidth (.getAttributes edge2) 2.0))
(. org.jgraph.graph.GraphConstants (setLineColor (.getAttributes edge2) java.awt.Color/darkGray))


;(.. graph getGraphLayoutCache (insert cells))
(.. graph getGraphLayoutCache (insert (aget cells 0)))
(.. graph getGraphLayoutCache (insert (aget cells 1)))
(.. graph getGraphLayoutCache (insert (aget cells 2)))
(.. graph getGraphLayoutCache (insert (aget cells 3)))

(def glc (new javax.swing.JScrollPane graph))
(def frame (new javax.swing.JFrame "Simple Graph View"))
(.. frame getContentPane (add glc))
(.pack frame)
(.setVisible frame true)



;; make change and fire update event
(. org.jgraph.graph.GraphConstants (setDashPattern (.getAttributes edge) (float-array [10 10])))
(.cellsChanged model (to-array [edge]))
;(. model (cellsChanged (to-array [edge])))
