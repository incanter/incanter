
;;; test-cases.clj -- Unit tests of Incanter functions 

;; by David Edgar Liebke http://incanter.org
;; March 12, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 12, 2009: First version

;; to run these tests:
;; (use 'tests test-cases)
;;  need to load this file to define data variables
;; (use 'clojure.contrib.test-is) 
;; then run tests
;; (run-tests 'incanter.tests.test-cases)

(ns incanter.charts-tests
  (:use clojure.test 
        (incanter core stats charts)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; TESTS FOR incanter.charts.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def test-mat (matrix
  [[39      10 ]
   [51      20 ]
   [60      30 ]
   [64      40 ]
   [73      50 ]
   [83      60 ]
   [90      70 ]
   [93      80 ]
   [99      90 ]
   [105     100]
   [110     110]
   [111     120]
   [113     130]
   [117     140]
   [120     150]
   [125     160]
   [130     170]
   [133     180]
   [133     190]
   [134     200]
   [138     210]
   [140     220]
   [145     230]
   [146     240]
   [148     250]]))


(deftest histogram-tests
  (view (histogram (sample-normal 1000)))
  (view (histogram (sample-gamma 1000)))
  (view (histogram (sample-uniform 1000)))
  (save (histogram (sample-normal 1000)) 
	(new java.io.File (System/getProperty "java.io.tmpdir") 
		                "norm_hist.png"))
  
  (view (histogram (sample-gamma 1000) 
                   :nbins 30 
                   :title "Gamma Distribution" 
                   :x-label "Value"))
  
  (def hist0 (histogram (sample-normal 100)))
  (view hist0)
  (add-histogram hist0 (sample-gamma 100))
  (set-alpha hist0 0.5))
  
 
(deftest scatter-tests
  (view (scatter-plot 
          (sel test-mat :cols 0) 
          (sel test-mat :cols 0) 
          :series-lab "Test data col 1 versus col 2"))
  
  
  (def plot1 (scatter-plot (sample-normal 100) (sample-normal 100)))
  (view plot1)
  (add-points plot1 (sample-normal 100) (sample-normal 100))
  (add-points plot1 (sample-normal 100) (sample-normal 100))
  (add-points plot1 (sample-normal 100) (sample-normal 100))
  (add-points plot1 (sample-normal 100) (sample-normal 100))
  
  (set-title plot1 "new title") 
  (set-x-label plot1 "new x label")
  (set-y-label plot1 "new y label"))
  
  
(deftest boxplot-tests 
  (def boxplt (box-plot (sample-gamma 1000))) 
  (view boxplt)
  (add-box-plot boxplt (sample-gamma 1000))
  (add-box-plot boxplt (sample-gamma 1000))
  (add-box-plot boxplt (sample-gamma 1000))
  (add-box-plot boxplt (sample-gamma 1000)))
  
 
(deftest xy-plot-tests
  (def chart1 (xy-plot (range 100) (range 100))) 
  (view chart1) 
  (add-lines chart1 (range 100) (mult 1/2 (range 100)))
  
  
  (def x1 (range -10 10 0.01))
  (def chart2 (xy-plot x1 (pow x1 2)))
  (view chart2) 
  (add-lines chart2 x1 (mult 1/2 (pow x1 2)))
  
  
  (def x2 (range 0 4 0.01))
  (def chart2 (xy-plot x2 (exp x1)))
  (view chart2))



