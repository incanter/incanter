;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; Load data from Ben Fry's website
;; http://benfry.com/writing/map/random.tsv
;; http://benfry.com/writing/map/locations.tsv
;; http://benfry.com/writing/map/map.png


(use '(incanter core processing io)) 
(let [locations (sel (to-matrix (read-dataset "examples/benfry/locations.tsv" 
                                              :delim \tab)) 
                     :cols [1 2])
      raw-data (sel (read-dataset "examples/benfry/random.tsv" 
                                  :delim \tab) 
                    :cols 1)
      max-val (reduce max raw-data)
      min-val (reduce min raw-data)
      percents (map #(norm % min-val max-val) raw-data)
      data (bind-columns locations 
                         (map #(remap % min-val max-val 2 40) raw-data)
                         percents)
      map-image (ref nil)
      draw-data (fn [sketch data]
                  (doseq [[x y value percent] data]
                    (fill sketch (lerp-color (color "0x296F34")
                                             (color "0x61E2F0") 
                                             percent))
                    (ellipse sketch x y value value)))
      sktch (sketch
              
              ;; define the setup function
              (setup []
                (dosync 
                  (ref-set map-image 
                           (load-image this "examples/benfry/images/map.png")))
                (size this 640 400))
             
              ;; define the draw function 
              (draw []
                (doto this
                  (background (color "0xFFFFFF"))
                  ;(background (color 192 0 0))
                  (image @map-image 0 0)
                  smooth 
                  no-stroke
                  (draw-data data)
                  (save "/tmp/map_example.png")
                  no-loop
                  ))) 
      ]
    (view sktch :title "US Map with Random Data" :size [640 400])
    ;(save sktch "/tmp/map_example.png"))

(view "file:///tmp/map_example.png"))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; Load data from Ben Fry's website
;; http://benfry.com/writing/map/random.tsv
;; http://benfry.com/writing/map/locations.tsv
;; http://benfry.com/writing/map/map.png


(use '(incanter core processing charts io)) 
    

(def rand-dat (read-dataset "examples/benfry/random.tsv" :delim \tab))

(view (bar-chart (sel rand-dat :cols 0) 
                 (sel rand-dat :cols 1)
                 :vertical false))

(let [locations (sel (to-matrix (read-dataset "examples/benfry/locations.tsv" 
                                              :delim \tab)) 
                     :cols [1 2])
      raw-data (sel (read-dataset "examples/benfry/random.tsv" 
                                  :delim \tab) 
                    :cols 1)
      abs-data (abs raw-data)
      max-val (reduce max abs-data)
      min-val (reduce min abs-data)
      ;colors (for [dat raw-data] (pos? dat))
      data (bind-columns locations 
                         (map #(remap % min-val max-val 2 40) abs-data)
                         raw-data)
      map-image (ref nil)
      draw-data (fn [sketch data]
                  (doseq [[x y value raw-dat] data]
                    (fill sketch (if (pos? raw-dat) (color "0x0000ff") (color "0xff0000")))
                    (ellipse sketch x y value value)))
      sktch (sketch
              
              ;; define the setup function
              (setup []
                (dosync 
                  (ref-set map-image 
                           (load-image this "examples/benfry/images/map.png")))
                (size this 640 400))
             
              ;; define the draw function 
              (draw []
                (doto this
                  (background (color "0xFFFFFF"))
                  ;(background (color 192 0 0))
                  (image @map-image 0 0)
                  smooth 
                  no-stroke
                  (draw-data data)
                  (save "/tmp/map_example.png")
                  no-loop
                  ))) 
      ]
    ;(save sktch "/tmp/map_example.png")
    (view sktch :title "US Map with Random Data" :size [640 400]))

(view "file:///tmp/map_example.png")



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; Load data from Ben Fry's website
;; http://benfry.com/writing/map/random.tsv
;; http://benfry.com/writing/map/locations.tsv
;; http://benfry.com/writing/map/map.png


(use '(incanter core processing charts io)) 
    

(def rand-dat (read-dataset "examples/benfry/random.tsv" :delim \tab))

(view (bar-chart (sel rand-dat :cols 0) 
                 (sel rand-dat :cols 1)
                 :vertical false))

(let [map-image (ref nil)
      font (ref nil)
      mse-x (ref 0)
      mse-y (ref 0)
      locations (sel (to-matrix (read-dataset "examples/benfry/locations.tsv" 
                                              :delim \tab)) 
                     :cols [1 2])
      rand-dat (read-dataset "examples/benfry/random.tsv" :delim \tab) 
      abbrevs (sel rand-dat :cols 0)
      raw-data (sel rand-dat :cols 1)
      abs-data (abs raw-data)
      max-val (reduce max abs-data)
      min-val (reduce min abs-data)

      data (bind-columns locations 
                         (map #(remap % min-val max-val 2 40) abs-data)
                         raw-data
                         (range (count locations)))

      draw-data (fn [sketch data abbrevs mse-x mse-y]
                  (doseq [[x y value raw-dat i] data]
                    (fill sketch (if (pos? raw-dat) (color "0x0000ff") (color "0xff0000")))
                    ;(ellipse-mode sketch RADIUS)
                    (ellipse sketch x y value value)
                    (when (< (dist x y mse-x mse-y) (+ 2 value))
                      (fill sketch (color 0))
                      (text-align sketch CENTER)
                      (string->text sketch (str "(" (nth abbrevs i) ") " value) 
                                    x (- y value 4)))))
      sktch (sketch
              
              ;; define the setup function
              (setup []
                (dosync 
                  (ref-set font (create-font this "Ariel" 12))
                  (ref-set map-image 
                           (load-image this "examples/benfry/images/map.png")))
                (text-font this @font)
                (size this 640 400))
             
              ;; define the draw function 
              (draw []
                (doto this
                  (background (color 0))
                  (image @map-image 0 0)
                  smooth 
                  no-stroke
                  (draw-data data abbrevs @mse-x @mse-y)
                  ;(save "/tmp/map_example.png")
                  ;no-loop
                  )) 
      
              ;; define mouseMoved function (mouseMoved and mouseDraw 
              ;; require a 'mouse-event' argument unlike the standard Processing 
              ;; methods)
              (mouseMoved [mouse-event]
                (dosync
                  ;; mouse-x and mouse-y take the mouse-event as an argument
                  (ref-set mse-x (mouse-x mouse-event)) 
                  (ref-set mse-y (mouse-y mouse-event)))))
      ]
    (view sktch :title "US Map with Random Data" :size [640 400]))

;(view "file:///tmp/map_example.png")



