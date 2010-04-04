
(ns newlook.core
  (:use [incanter core stats charts datasets]))


(view (histogram (sample-normal 1000)))



(view (function-plot sin -10 10))

(doto (function-plot sin -10 10)
  (add-function cos -10 10)
  view)

(view (scatter-plot :Sepal.Length :Sepal.Width :data (get-dataset :iris)))

(doto (scatter-plot :Sepal.Length :Sepal.Width :data (get-dataset :iris))
  (set-stroke-color java.awt.Color/gray)
  view)


(view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species :data (get-dataset :iris)))




(with-data (->>  (get-dataset :hair-eye-color)
                      ($rollup :sum :count [:hair :eye]))
       (view (bar-chart :hair :count :group-by :eye :legend true)))



(doto (box-plot (sample-gamma 1000 :shape 1 :rate 2)
                    :legend true :y-label "")
      view 
      (add-box-plot (sample-gamma 1000 :shape 2 :rate 2))
      (add-box-plot (sample-gamma 1000 :shape 3 :rate 2)))



(use '(incanter core charts))
(defn log32 [x] (/ (log x) (log 32)))
(defn f1 [n] (plus (log2 n) (mult (log32 n) 5000)))
(defn f2 [n] n)



(def min-val 10)
(def max-val 40000)
(def chart (doto (function-plot f1 min-val max-val 
				:legend true 
				:series-label "O(log2 n) + O(log32 n) * 5000"
				:x-label ""
				:y-label "")
	     (add-function f2 min-val max-val :step-size 5000 :series-label "O(n)") 
	     (set-stroke :width 2)
	     (set-stroke :width 2 :series 1 :dash 5)))

(view chart)

;; PLOT (A)
(doto chart
  (set-title "(A)")
  (set-x-range 100 5000)
  (set-y-range 30 12000))

;; PLOT (B)
(doto chart
  (set-title "(B)")
  (set-y-range 10000 16000)
  (set-x-range 10000 16000))


;; PLOT (C)
(doto chart
  (set-title "(C)")
  (set-y-range 0 30000)
  (set-x-range 0 30000))



