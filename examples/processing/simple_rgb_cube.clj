;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Simple 3d Clojure code example for Incanter and Processing
; Adapted by James Swift (james at 3dengineer com)
; from the simple_interation.clj example that comes with incanter
; and the RGB cube example here http://processing.org/learning/3d/rgbcube.html

(ns example
  (:use [incanter core processing]))

;; set up variable references to use in the sketch object
(let [X (ref nil)
      Y (ref nil)
      nX (ref nil)
      nY (ref nil)
      delay 16

      ;; define a sketch object (i.e. PApplet)
      sktch (sketch

              ;; define the setup function
              (setup []
                     (doto this
                       (no-stroke)
                       (size 790 590 P3D)
                       (color-mode RGB 1))
                     (dosync
                       (ref-set X (/ (width this) 2))
                       (ref-set Y (/ (width this) 2))
                       (ref-set nX @X)
                       (ref-set nY @Y)))

              ;; define the draw function
              (draw []
                    (doto this
                      (background (color 0xFFFFFF))
                      (push-matrix)
                      (translate (/ (width this) 2) (/ (height this) 2) -30)
                      (rotate-x (- @nX))
                      (rotate-y (- @nY))
                      (scale 90)
                      (begin-shape QUADS)
                      (fill 0 1 1) (vertex -1  1  1)
                      (fill 1 1 1) (vertex  1  1  1)
                      (fill 1 0 1) (vertex  1 -1  1)
                      (fill 0 0 1) (vertex -1 -1  1)

                      (fill 1 1 1) (vertex  1  1  1)
                      (fill 1 1 0) (vertex  1  1 -1)
                      (fill 1 0 0) (vertex  1 -1 -1)
                      (fill 1 0 1) (vertex  1 -1  1)

                      (fill 1 1 0) (vertex  1  1 -1)
                      (fill 0 1 0) (vertex -1  1 -1)
                      (fill 0 0 0) (vertex -1 -1 -1)
                      (fill 1 0 0) (vertex  1 -1 -1)

                      (fill 0 1 0) (vertex -1  1 -1)
                      (fill 0 1 1) (vertex -1  1  1)
                      (fill 0 0 1) (vertex -1 -1  1)
                      (fill 0 0 0) (vertex -1 -1 -1)

                      (fill 0 1 0) (vertex -1  1 -1)
                      (fill 1 1 0) (vertex  1  1 -1)
                      (fill 1 1 1) (vertex  1  1  1)
                      (fill 0 1 1) (vertex -1  1  1)

                      (fill 0 0 0) (vertex -1 -1 -1)
                      (fill 1 0 0) (vertex  1 -1 -1)
                      (fill 1 0 1) (vertex  1 -1  1)
                      (fill 0 0 1) (vertex -1 -1  1)
                      (end-shape)
                      (pop-matrix)
                      ))

              ;; define mouseMoved function (mouseMoved and mouseDraw 
              ;; require a 'mouse-event' argument unlike the standard Processing 
              ;; methods)
              (mouseMoved [mouse-event]
                (dosync
                  ;; mouse-x and mouse-y take the mouse-event as an argument
                  (ref-set nX (* (/ (mouse-y mouse-event) (width this)) TWO_PI)) 
                  (ref-set nY (* (/ (mouse-x mouse-event) (height this)) TWO_PI)))))]

  ;; use the view function to display the sketch
  (view sktch :size [800 600]))

