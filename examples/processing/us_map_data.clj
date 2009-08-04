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
                  (draw-data data)))) 
      ]
    (view sktch :title "US Map with Random Data" :size [640 400])
    (save sktch "/tmp/map_example.png"))

(view "file:///tmp/map_example.png")



