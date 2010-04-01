
(ns incanter.processing-tests
  (:use clojure.test
        (incanter core processing)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; TESTS FOR incanter.processing.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def wait-timeout 3000)



(deftest processing-tests

;; simple interactive Processing example taken from processingjs.org website:
;; http://processingjs.org/source/basic-example/processingjs_basic-example.html

;; set up variable references to use in the sketch object
(let [radius (ref 50.0)
      X (ref nil)
      Y (ref nil)
      nX (ref nil)
      nY (ref nil)
      delay 16

      ;; define a sketch object (i.e. PApplet)
      sktch (sketch

              ;; define the setup function
              (setup []
                     (doto this
                       ;no-loop
                       (size 200 200)
                       (stroke-weight 10)
                       (framerate 15)
                       smooth)
                     (dosync
                       (ref-set X (/ (width this) 2))
                       (ref-set Y (/ (width this) 2))
                       (ref-set nX @X)
                       (ref-set nY @Y)))

              ;; define the draw function
              (draw []
                    (dosync
                      (ref-set radius (+ @radius (sin (/ (frame-count this) 4))))
                      (ref-set X (+ @X (/ (- @nX @X) delay)))
                      (ref-set Y (+ @Y (/ (- @nY @Y) delay))))
                    (doto this
                      (background 125) ;; gray
                      (fill 0 121 184)
                      (stroke 255)
                      (ellipse @X @Y @radius @radius)
                      ;(save "proc_example1.png")
                      ))

              ;; define mouseMoved function (mouseMoved and mouseDraw 
              ;; require a 'mouse-event' argument unlike the standard Processing 
              ;; methods)
              (mouseMoved [mouse-event]
                (dosync
                  ;; mouse-x and mouse-y take the mouse-event as an argument
                  (ref-set nX (mouse-x mouse-event)) 
                  (ref-set nY (mouse-y mouse-event)))))]

  ;; use the view function to display the sketch
  (def frame1 (view sktch :size [200 200]))
  (Thread/sleep wait-timeout)
  (.dispose frame1))
  (System/exit 0))



