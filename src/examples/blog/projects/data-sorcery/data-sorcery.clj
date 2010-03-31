;; CREATING DATASETS

(use 'incanter.core)

(dataset ["x1" "x2" "x3"] 
         [[1 2 3] 
          [4 5 6] 
          [7 8 9]])


(to-dataset [{"x1" 1, "x2" 2, "x3" 3}
             {"x1" 4, "x2" 5, "x3" 6}
             {"x1" 7, "x2" 8, "x3" 9}])


(to-dataset [[1 2 3] 
             [4 5 6] 
             [7 8 9]])


(conj-cols [1 4 7] [2 5 8] [3 6 9])


(conj-rows [1 2 3] [4 5 6] [7 8 9])


;; READING DATA

(use '(incanter core io))

(read-dataset "./data/cars.csv" 
              :header true)


(read-dataset "./data/cars.tdd" 
              :header true 
              :delim \tab)


(read-dataset "http://bit.ly/aZyjKa" 
              :header true)


(use 'incanter.datasets)
(get-dataset :cars)


(use 'incanter.mongodb)
(use 'somnium.congomongo)
(mongo! :db "mydb")
(view (fetch-dataset :cars))


;; SAVING DATA

(use '(incanter core io))
(save data "./data.csv")



(use '(incanter core mongodb))
(use 'somnium.congomongo)
(mongo! :db "mydb")
(insert-dataset :cars data)


;; COLUMN SELECTION

(use '(incanter core datasets))

($ :speed (get-dataset :cars))


(with-data (get-dataset :cars)
  [(mean ($ :speed))
   (sd ($ :speed))])


(with-data (get-dataset :iris)
  (view $data)
  (view ($ [:Sepal.Length :Sepal.Width :Species]))
  (view ($ [:not :Petal.Width :Petal.Length]))
  (view ($ 0 [:not :Petal.Width :Petal.Length])))


;; ROW SELECTION

(use '(incanter core datasets))

($where {"Species" "setosa"} 
        (get-dataset :iris))


($where {"Petal.Width" {:lt 1.5}} 
        (get-dataset :iris))


($where {"Petal.Width" {:gt 1.0, :lt 1.5}} 
        (get-dataset :iris))


($where {"Petal.Width" {:gt 1.0, :lt 1.5}
         "Species" {:in #{"virginica" "setosa"}}}
        (get-dataset :iris))


($where (fn [row] 
          (or (< (row "Petal.Width") 1.0)
              (> (row "Petal.Length") 5.0)))
        (get-dataset :iris))


;; SORTING DATA

(use '(incanter core datasets))

(with-data (get-dataset :hair-eye-color)
  (view $data)
  (view ($order :count :desc))
  (view ($order [:hair :eye] :desc)))


;; ROLLING UP DATA

(use '(incanter core datasets stats))

(->> (get-dataset :iris)
     ($rollup mean :Petal.Length :Species)
     view)

(->> (get-dataset :iris)
     ($rollup #(/ (sd %) (count %)) 
              :Petal.Length :Species)
     view)

(->> (get-dataset :hair-eye-color)
     ($rollup sum :count [:hair :eye])
     ($order :count :desc)
     view)

;; CHART OPTIONS

(view (scatter-plot :Sepal.Length :Sepal.Width 
                    :data (get-dataset :iris)
                    :theme :dark
                    :group-by :Species
                    :title "Fisher Iris Data"
                    :x-label "Sepal Length (cm)"
                    :y-label "Sepal Width (cm)"))


;; SAVING CHARTS

;; SAVING CHARTS AS PNG
(save (scatter-plot :Sepal.Length :Sepal.Width 
                    :data (get-dataset :iris)
                    :theme :dark
                    :group-by :Species
                    :title "Fisher Iris Data"
                    :x-label "Sepal Length (cm)"
                    :y-label "Sepal Width (cm)")
      "./iris-plot.png")


;; SAVING CHARTS TO AN OUTPUT STREAM
(def output-stream (java.io.ByteArrayOutputStream.))
(save (scatter-plot :Sepal.Length :Sepal.Width 
                    :data (get-dataset :iris)
                    :theme :dark
                    :group-by :Species
                    :title "Fisher Iris Data"
                    :x-label "Sepal Length (cm)"
                    :y-label "Sepal Width (cm)")
      output-stream)


;; SAVING CHARTS AS PDF
(use 'incanter.pdf)
(save-pdf (scatter-plot :Sepal.Length :Sepal.Width 
                        :data (get-dataset :iris)
                        :theme :dark
                        :group-by :Species
                        :title "Fisher Iris Data"
                        :x-label "Sepal Length (cm)"
                        :y-label "Sepal Width (cm)")
      "./iris-plot.pdf")


;; ADDING DATA TO A CHART
(use '(incanter core charts datasets stats))

(with-data (get-dataset :iris)
  (let [lm (linear-model ($ :Petal.Length) ($ :Petal.Width))] 
    (doto (scatter-plot :Petal.Width :Petal.Length
                        ;:theme :dark
                        :data ($where {"Petal.Length" {:lte 2.0} 
                                       "Petal.Width" {:lt 0.75}}))
          (add-points :Petal.Width :Petal.Length
                      :data ($where {"Petal.Length" {:gt 2.0}
                                     "Petal.Width" {:gte 0.75}}))
          (add-lines :Petal.Width (:fitted lm))
          view)))



;; BAR & LINE CHARTS

;; CREATING BAR CHARTS
(use '(incanter core charts datasets))

(with-data ($rollup mean :Sepal.Length :Species 
                    (get-dataset :iris))
  (view (bar-chart :Species :Sepal.Length
                   :theme :dark)))


;; CREATING LINE CHARTS
(use '(incanter core charts datasets))

(with-data ($rollup mean :Sepal.Length :Species 
                    (get-dataset :iris))
  (view (line-chart :Species :Sepal.Length
                    :theme :dark)))


;; BAR CHART W/ GROUP-BY
(with-data ($rollup :mean :count [:hair :eye] 
                    (get-dataset :hair-eye-color))
  (view $data)
  (view (bar-chart :hair :count 
                   :group-by :eye 
                   :legend true
                   :theme :dark)))


;; LINE CHART W/ GROUP-BY
(with-data ($rollup :mean :count [:hair :eye] 
                    (get-dataset :hair-eye-color))
  (view $data)
  (view (line-chart :hair :count 
                    :group-by :eye 
                    :legend true
                    :theme :dark)))


;; BAR CHART W/ GROUP-BY
(with-data (->>  (get-dataset :hair-eye-color)
		          ($where {:hair {:in #{"brown" "blond"}}})
		          ($rollup :sum :count [:hair :eye])
		          ($order :count :desc))
  (view $data)
  (view (bar-chart :hair :count 
                   :group-by :eye 
                   :legend true 
                   :theme :dark)))


;; LINE CHART W/ GROUP-BY
(with-data (->>  (get-dataset :hair-eye-color)
		          ($where {:hair {:in #{"brown" "blond"}}})
		          ($rollup :sum :count [:hair :eye])
		          ($order :count :desc))
  (view $data)
  (view (line-chart :hair :count 
                    :group-by :eye 
                    :legend true 
                    :theme :dark)))

;; XY-PLOT
(use '(incanter (core charts)))

(with-data (get-dataset :cars)
  (view (xy-plot :speed :dist
                 :theme :dark)))

;; FUNCTION-PLOT
(use '(incanter (core charts optimize)))

(defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))

(doto (function-plot cubic -10 10 :theme :dark)
      (add-function (derivative cubic) -10 10)
      (add-function #(* 1000 (sin %)) -10 10)
      view)


;; HISTOGRAM
(use '(incanter (core charts stats)))
(doto (histogram (sample-gamma 1000)
                 :density true 
                 :nbins 30 
                 :theme :dark)
     (add-function pdf-gamma 0 8)
     view)


;; BOX PLOT
(use '(incanter core datasets))

(with-data (get-dataset :iris)
  (view (box-plot :Petal.Width 
		  :group-by :Species
		  :theme :dark)))



;; ANNOTATING CHARTS
(use '(incanter core charts))
(doto (function-plot sin -10 10)
  (add-text 0 0 "text at (0,0)")
  (add-pointer (- Math/PI) (sin (- Math/PI)) 
               :text "pointer at (sin -pi)")
  (add-pointer Math/PI (sin Math/PI) 
               :text "pointer at(sin pi)" 
               :angle :ne)
  (add-pointer (* 1/2 Math/PI) (sin (* 1/2 Math/PI)) 
               :text "pointer at(sin pi/2)" 
               :angle :south)
  view)




