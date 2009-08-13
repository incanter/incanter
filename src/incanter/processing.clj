;; simple processing wrapper for Clojure
;; Roland Sadowski [szabla gmail com]

;; Copyright (c) 2008 Roland Sadowski. All rights reserved.  The use and
;; distribution terms for this software are covered by the Common
;; Public License 1.0 (http://www.opensource.org/licenses/cpl1.0.php)
;; which can be found in the file CPL.TXT at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Modified by David Edgar Liebke 30 June 2009
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(ns incanter.processing
  (:use [incanter.core :only (view save)])
  (:import (processing.core PApplet PImage PGraphics PFont)))


;;(defn abs-int [n] (PApplet/abs (int n)))

;;(defn abs-float [n] (PApplet/abs (float n)))

;;(defn acos [n] (PApplet/acos n))

;; $$addListeners

;; added by DEL
(declare color)


(defn alpha
  [sketch what] (.alpha sketch (int what)))


;;(defn ambient
;;  ([sketch rgb]
;;   (if (string? rgb)
;;     (.ambient sketch (color sketch rgb))
;;     (.ambient sketch (float rgb))))
;;  ([sketch x y z] (.ambient sketch (float x) (float y) (float z))))

;;(defn ambient-int
;;  [sketch rgb] (.ambient sketch (int rgb)))

;; modified by DEL
(defn ambient
  [sketch rgb] (.ambient sketch rgb))

;; modified by DEL
;;(defn ambient-light
;;  ([sketch rgb]
;;    (.ambientLight sketch rgb))
;;  ([sketch rgb x y z]
         (.ambientLight sketch rgb (float x) (float y) (float z))))

(defn ambient-light
  ([sketch red green blue]
     (.ambientLight sketch (float red) (float green) (float blue)))
  ([sketch red green blue x y z]
     (.ambientLight sketch (float red) (float green) (float blue) (float x) (float y) (float z))))

;; $$append

(defn apply-matrix
  ([sketch n00 n01 n02 n10 n11 n12]
         (.applyMatrix sketch (float n00) (float n01) (float n02)
                                   (float n10) (float n11) (float n12)))
  ([sketch n00 n01 n02 n03
        n10 n11 n12 n13
        n20 n21 n22 n23
        n30 n31 n32 n33]
         (.applyMatrix sketch (float n00) (float n01) (float n02) (float 03)
                                   (float n10) (float n11) (float n12) (float 13)
                                   (float n20) (float n21) (float n22) (float 23)
                                   (float n30) (float n31) (float n32) (float 33))))

(defn arc
  [sketch a b c d start stop]
  (.arc sketch (float a)(float b) (float c) (float d) (float start) (float stop)))

;; $$arraycopy

;;(defn asin [sketch val] (PApplet/asin (float val)))

;;(defn atan [sketch val] (PApplet/atan (float val)))

;;(defn atan2 [sketch a b] (PApplet/atan2 (float a) (float b)))

;;(defn background-float
;;  ([#^PApplet sketch gray] (.background sketch (float gray)))
;;  ([#^PApplet sketch gray alpha] (.background sketch (float gray) (float alpha)))
;;  ([#^PApplet sketch r g b] (.background sketch (float r) (float g) (float b)))
;;  ([#^PApplet sketch r g b a] (.background sketch (float r) (float g) (float b) (float a))))

;;(defn background-int
;;  ([#^PApplet sketch rgb] (.background sketch (int rgb)))
;;  ([#^PApplet sketch rgb alpha] (.background sketch (int rgb) (float alpha))))


;; modified by DEL
(defn background
"
  Examples:

    (use 'incanter.processing)
    (def sktch (sketch))

    (background sktch (color 255 0 255))
    (draw sktch)

    (background sktch (color 255 0 255 255)) ;; with alpha value
    (draw sktch)

    (background sktch (color \"0xFF00FF\"))
    (draw sktch)

    (background sktch (color 0xFF00FF))
    (draw sktch)

    (background sktch (color -65281))
    (draw sktch)

    (background sktch (color 0xFFFF00FF true)) ;; with alpha? true
    (draw sktch)

    (background sktch (color 0xFF 0x00 0xFF))
    (draw sktch)

    (background sktch (color 0xFF 0x00 0xFF 0xFF)) ;; with alpha value
    (draw sktch)

    (background sktch (color 1.0 0.0 1.0))
    (draw sktch)

    (background sktch (color 1.0 0.0 1.0 1.0)) ;; with alpha value
    (draw sktch)



"
;;  ([#^PApplet sketch rgb] (.background sketch rgb)))
  ([#^PApplet sketch gray]
     (cond
       (string? gray)
         (.background sketch (color gray))
       (integer? gray)
         (.background sketch (int gray))
       (float? gray)
         (.background sketch (float gray))
       (= java.awt.Color (type gray))
         (.background sketch #^java.awt.Color gray)))
  ([#^PApplet sketch gray alpha]
    (cond
      (string? gray)
        (.background sketch (color gray) (int alpha))
      (integer? gray)
       (.background sketch (int gray) (int alpha))
      (float? gray)
       (.background sketch (float gray) (float alpha))))
  ([#^PApplet sketch r g b]
   (cond
     (or (integer? r) (integer? g) (integer? b))
       (.background sketch (int r) (int g) (int b))
     (or (float? r) (float? g) (float? b))
     (.background sketch (float r) (float g) (float b))))
  ([#^PApplet sketch r g b a]
    (cond
      (or (integer? r) (integer? g) (integer? b))
        (.background sketch (int r) (int g) (int b) (int a))
      (or (float? r) (float? g) (float? b))
      (.background sketch (float r) (float g) (float b) (float a)))))


;; DEL
;;(defn background
;;  ([#^PApplet sketch color]
;;    (if (float? color)
;;      (.background sketch (float color))
;;      (.background sketch (int color))))
;;  ([#^PApplet sketch color alpha]
;;    (if (float? color)
;;      (.background sketch (float color) (float alpha))
;;      (.background sketch (int color) (float alpha))))
;;  ([#^PApplet sketch r g b] (.background sketch (float r) (float g) (float b)))
;;  ([#^PApplet sketch r g b alpha] (.background sketch (float r) (float g) (float b) (float alpha))))

(defn background-image
  [#^PApplet sketch #^PImage img] (.background sketch img))

(defn begin-camera
  [#^PApplet sketch] (.beginCamera sketch))

(defn begin-raw
  ([#^PApplet sketch #^PGraphics rawGfx] (.beginRaw sketch rawGfx))
  ([#^PApplet sketch #^java.lang.String renderer #^java.lang.String filename]
         (.beginRaw sketch renderer filename)))

;; $$beginRecord

(defn begin-shape
  ([#^PApplet sketch ] (.beginShape sketch))
  ([#^PApplet sketch kind] (.beginShape sketch (int kind))))

(defn bezier
  ([#^PApplet sketch x1 y1 x2 y2 x3 y3 x4 y4]
         (.bezier sketch
                          (float x1) (float y1)
                          (float x2) (float y2)
                          (float x3) (float y3)
                          (float x4) (float y4)))
  ([#^PApplet sketch x1 y1 z1 x2 y2 z2 x3 y3 z3 x4 y4 z4]
         (.bezier sketch
                          (float x1) (float y1) (float z1)
                          (float x2) (float y2) (float z2)
                          (float x3) (float y3) (float z3)
                          (float x4) (float y4) (float z4))))

(defn bezier-detail
  [#^PApplet sketch detail] (.bezierDetail sketch (int detail)))

(defn bezier-point
  [#^PApplet sketch a b c d t] (.bezierPoint sketch (float a) (float b) (float c) (float d) (float t)))

(defn bezier-tangent
  [#^PApplet sketch a b c d t] (.bezierTangent sketch (float a) (float b) (float c) (float d) (float t)))

(defn bezier-vertex
  ([#^PApplet sketch x2 y2 x3 y3 x4 y4]
         (.bezierVertex sketch
                          (float x2) (float y2)
                          (float x3) (float y3)
                          (float x4) (float y4)))
  ([#^PApplet sketch x1 y1 z1 x2 y2 z2 x3 y3 z3 x4 y4 z4]
         (.bezierVertex sketch
                          (float x2) (float y2) (float z2)
                          (float x3) (float y3) (float z3)
                          (float x4) (float y4) (float z4))))

;; $$binary

(defn blend
  ([#^PApplet sketch sx1 sy1 sx2 sy2 dx1 dy1 dx2 dy2 mode]
         (.blend sketch (int sx1) (int sy1) (int sx2) (int sy2)
                        (int dx1) (int dy1) (int dx2) (int dy2)
                        (int mode)))
  ([#^PApplet sketch #^PImage src sx1 sy1 sx2 sy2 dx1 dy1 dx2 dy2 mode]
         (.blend sketch src (int sx1) (int sy1) (int sx2) (int sy2)
                            (int dx1) (int dy1) (int dx2) (int dy2)
                            (int mode))))

(defn blend-color
  [c1 c2 mode] (PApplet/blendColor (int c1) (int c2) (int mode)))

(defn blue [#^PApplet sketch what] (.blue sketch (int what)))

(defn box
  ([#^PApplet sketch size] (.box sketch (int size)))
  ([#^PApplet sketch w h d] (.box sketch (float w) (float h) (float d))))

(defn brightness [#^PApplet sketch what] (.brightness sketch (int what)))

(defn camera
  ([#^PApplet sketch ] (.camera sketch))
  ([#^PApplet sketch eyeX eyeY eyeZ centerX centerY centerZ upX upY upZ]
         (.camera sketch (float eyeX) (float eyeY) (float eyeZ) (float centerX) (float centerY) (float centerZ) (float upX) (float upY) (float upZ))))

(defn can-draw? [#^PApplet sketch ] (.canDraw sketch))

(defn ceil [n] (PApplet/ceil (float n)))

;;(defn color-float
;;  ([#^PApplet sketch gray] (.background sketch (float gray)))
;;  ([#^PApplet sketch gray alpha] (.background sketch (float gray) (float alpha)))
;;  ([#^PApplet sketch r g b] (.background sketch (float r) (float g) (float b)))
;;  ([#^PApplet sketch r g b a] (.background sketch (float r) (float g) (float b) (float a))))

;;(defn color-int
;;  ([#^PApplet sketch gray] (.background sketch (int gray)))
;;  ([#^PApplet sketch gray alpha] (.background sketch (int gray) (float alpha)))
;;  ([#^PApplet sketch r g b] (.background sketch (int r) (int g) (int b)))
;;  ([#^PApplet sketch r g b a] (.background sketch (int r) (int g) (int b) (int a))))


;; modified by DEL
(defn color
"

  Examples:

    (use 'incanter.processing)
    (color 255 0 255)
    (color 255 0 255 255) ;; with alpha value
    (color \"0xFF00FF\")
    (color 0xFF00FF)
    (color -65281)
    (color 0xFFFF00FF true) ;; with alpha? true
    (color 0xFF 0x00 0xFF)
    (color 0xFF 0x00 0xFF 0xFF) ;; with alpha value
    (color 1.0 0.0 1.0)
    (color 1.0 0.0 1.0 1.0) ;; with alpha value

"
([rgb]
  (.getRGB (java.awt.Color. (int rgb))))
;; (cond
;;   (string? rgb)
;;     (.getRGB (java.awt.Color/decode rgb))
;;   (or (integer? rgb) (float? rgb))
;;     (.getRGB (java.awt.Color. (int rgb)))))
([rgb alpha?]
  ;;(.getRGB (java.awt.Color. (color rgb) alpha?)))
  (.getRGB (java.awt.Color. (int rgb) alpha?)))
([x y z]
 (if (or (float? x) (float? y) (float? z))
    (.getRGB (java.awt.Color. (float x) (float y) (float z)))
    (.getRGB (java.awt.Color. (int x) (int y) (int z)))))
([x y z alpha]
 (if (or (float? x) (float? y) (float? z))
    (.getRGB (java.awt.Color. (float x) (float y) (float z) (float alpha)))
    (.getRGB (java.awt.Color. (int x) (int y) (int z) (int alpha))))))

;;([#^PApplet sketch rgb]
;; (if (string? rgb)
;;   (.getRGB (java.awt.Color/decode rgb))
;;   (.color sketch (int rgb))))
;;([#^PApplet sketch rgb alpha] (.color sketch (int rgb) (float alpha)))
;;([#^PApplet sketch r g b] (.color sketch (int r) (int g) (int b)))
;;([#^PApplet sketch r g b a] (.color sketch (int r) (int g) (int b) (int a))))



;;  ([#^PApplet sketch gray]
;;    (cond
;;      (string? gray)
;;        (.color sketch (Integer/parseInt gray 16))
;;      (integer? gray)
;;        (.color sketch (int gray))
;;      (float? gray)
;;        (.color sketch (float gray))))
;;  ([#^PApplet sketch gray alpha]
;;    (cond
;;      (string? gray)
;;        (.color sketch (Integer/parseInt gray 16) (Integer/parseInt alpha 16))
;;      (integer? gray)
;;        (.color sketch (int gray) (int alpha))
;;      (float? gray)
;;        (.color sketch (float gray) (int alpha))))
;;  ([#^PApplet sketch r g b]
;;    (if (or (integer? r) (integer? g) (integer? b))
;;      (.color sketch (int r) (int g) (int b))
;;      (.color sketch (float r) (float g) (float b))))
;;  ([#^PApplet sketch r g b alpha]
;;    (if (or (integer? r) (integer? g) (integer? b))
;;      (.color sketch (int r) (int g) (int b) (int alpha))
;;      (.color sketch (float r) (float g) (float b) (float alpha)))))
;;

;;  ([rgb]
;;   (if (string? rgb)
;;     (.getRGB (java.awt.Color/decode rgb))
;;     (.getRGB (java.awt.Color. (int rgb)))))
;     (let [rgb (cond
;                 (> rgb 255) 255
;                 (< rgb 0) 0
;                 :else rgb)]
;         (reduce bit-or [(int 0xff000000)
;                         (bit-shift-left (int rgb) 16)
;                         (bit-shift-left (int rgb) 8)
;                         (int rgb)]))))


(defn color-mode
  ([#^PApplet sketch mode]
    (.colorMode sketch (int mode)))
  ([#^PApplet sketch mode max]
    (.colorMode sketch (int mode) (float max)))
  ([#^PApplet sketch mode max-x max-y max-z]
    (.colorMode sketch (int mode) (float max-x) (float max-y) (float max-z)))
  ([#^PApplet sketch mode max-x max-y max-z max-a]
    (.colorMode sketch (int mode) (float max-x) (float max-y) (float max-z) (float max-a))))

;; $$concat

;; modified by DEL
(defn constrain
  [amt low high]
  (if (or (float? amt) (float? low) (float? high))
    (PApplet/constrain (float amt) (float low) (float high))
    (PApplet/constrain (int amt) (int low) (int high))))

;;(defn constrain-float
;;  [amt low high]
;;  (PApplet/constrain (float amt) (float low) (float high)))

;;(defn constrain-int
;;  [amt low high]
;;  (PApplet/constrain (int amt) (int low) (int high)))

(defn copy-pixels
" Processing copy function. "
  ([#^PApplet sketch [sx1 sy1 sx2 sy2] [dx1 dy1 dx2 dy2]]
         (.copy sketch (int sx1) (int sy1) (int sx2) (int sy2)
                        (int dx1) (int dy1) (int dx2) (int dy2)))
  ([#^PApplet sketch #^PImage img [sx1 sy1 sx2 sy2] [dx1 dy1 dx2 dy2]]
         (.copy sketch img (int sx1) (int sy1) (int sx2) (int sy2)
                        (int dx1) (int dy1) (int dx2) (int dy2))))

;;(defn cos [angle] (PApplet/cos (float angle)))

(defn create-font
  ([#^PApplet sketch name size] (.createFont sketch name (float size)))
  ([#^PApplet sketch name size smooth] (.createFont sketch name (float size) smooth))
  ([#^PApplet sketch name size smooth #^chars charset]
         (.createFont sketch name (float size) smooth charset)))


;; added by DEL
(defn list-fonts
  "Returns a list of the fonts available on current system."
  ([] (into [] (processing.core.PFont/list))))

;; added by DEL
(defn save-font
  ([#^processing.core.PApplet sketch #^processing.core.PFont font filename]
    (let [out (java.io.FileOutputStream. filename)]
      (.save font out))))

;; added by DEL
(defn export-font
" Exports the given system font to a vlw file.

  Examples:

    (use '(incanter core processing))
    (export-font \"Ariel\" 48 \"/tmp/ariel_48.vlw\")

    (view
      (sketch
        (setup []
          (let [font (load-font this \"/tmp/ariel_48.vlw\")]
            (doto this
              (text-font font)
              smooth
              (fill 0))))

            (draw []
              (doto this
                (text \"LAX\" 0 40)
                (text \"AMS\" 0 70)
                (text \"FRA\" 0 100))))
      :size [120 120])


"
  ([font-name size smooth filename]
    (let [sketch (processing.core.PApplet.)
          font (create-font sketch font-name size smooth)]
      (save-font sketch font filename)))
  ([font-name size filename]
    (export-font font-name size true filename)))



(defn create-graphics
  ([#^PApplet sketch w h renderer]
         (.createGraphics sketch (int w) (int h) renderer))
  ([#^PApplet sketch w h renderer path]
         (.createGraphics sketch (int w) (int h) renderer path)))

(defn create-image [#^PApplet sketch w h format] (.createImage sketch (int w) (int h) (int format)))

(defn create-input [filename]
        (PApplet/createInput (java.io.File. filename)))

(defn create-input-raw
        "Call openStream() without automatic gzip decompression."
        [#^PApplet sketch filename]
        (.createInputRaw sketch filename))

(defn create-output [filename]
        (PApplet/createOutput (java.io.File. filename)))

(defn create-path [filename] (PApplet/createPath filename))

(defn create-reader [#^PApplet sketch filename] (.createReader sketch filename))

(defn create-writer [#^PApplet sketch filename] (.createWriter sketch filename))

(defn cursor
  ([#^PApplet sketch ] (.cursor sketch))
  ([#^PApplet sketch cur-type] (.cursor sketch (int cur-type))))

(defn cursor-image
  ([#^PApplet sketch #^PImage img] (.cursor sketch img))
  ([#^PApplet sketch #^PImage img hx hy] (.cursor sketch img (int hx) (int hy))))

(defn curve
  ([#^PApplet sketch x1 y1 x2 y2 x3 y3 x4 y4]
         (.curve sketch
                          (float x1) (float y1)
                          (float x2) (float y2)
                          (float x3) (float y3)
                          (float x4) (float y4)))
  ([#^PApplet sketch x1 y1 z1 x2 y2 z2 x3 y3 z3 x4 y4 z4]
         (.curve sketch
                          (float x1) (float y1) (float z1)
                          (float x2) (float y2) (float z2)
                          (float x3) (float y3) (float z3)
                          (float x4) (float y4) (float z4))))

(defn curve-detail [#^PApplet sketch detail] (.curveDetail sketch (int detail)))

(defn curve-point
  [#^PApplet sketch a b c d t] (.bezierPoint sketch (float a) (float b) (float c) (float d) (float t)))

(defn curve-tangent
  [#^PApplet sketch a b c d t] (.curveTangent sketch (float a) (float b) (float c) (float d) (float t)))

(defn curve-tightness [#^PApplet sketch ti] (.curveTightness sketch (float ti)))

(defn curve-vertex
  ([#^PApplet sketch x y] (.curveVertex sketch (float x) (float y)))
  ([#^PApplet sketch x y z] (.curveVertex sketch (float x) (float y) (float z))))

;; $$dataFile
;; $$dataPath

(defn day
  "Get the current day of the month (1 through 31)."
  []
  (PApplet/day))

(defn degrees [radians] (PApplet/degrees (float radians)))

(defn delay-frame [#^PApplet sketch nap-time] (.delay sketch (int nap-time)))

(defn destroy [#^PApplet sketch ] (.destroy sketch))

;; $$die

(defn directional-light
  [#^PApplet sketch r g b nx ny nz]
  (.directionalLight sketch (float r) (float g) (float b) (float nx) (float ny) (float nz)))

(defn displayable? [#^PApplet sketch ] (.displayable sketch))

(defn dist
  ([a b x y] (PApplet/dist (float a) (float b) (float x) (float y)))
  ([a b c x y z] (PApplet/dist (float a) (float b) (float c) (float x) (float y) (float z))))

;; $$draw

(defn ellipse
  [#^PApplet sketch a b c d]
  (.ellipse sketch (float a) (float b) (float c) (float d)))

(defn ellipse-mode [#^PApplet sketch mode] (.ellipseMode sketch (int mode)))

;; modified by DEL
(defn emissive
  ([#^PApplet sketch gray]
   (if (integer? gray)
     (.emissive sketch (int gray))
     (.emissive sketch (float gray))))
  ([#^PApplet sketch x y z]
   (if (integer? gray)
    (.emissive sketch (int x) (int y) (int z))
    (.emissive sketch (float x) (float y) (float z)))))


(defn end-camera [#^PApplet sketch ] (.endCamera sketch))

(defn end-raw [#^PApplet sketch ] (.endRaw sketch))

(defn end-shape
  ([#^PApplet sketch ] (.endShape sketch))
  ([#^PApplet sketch mode] (.endShape sketch (int mode))))

;; $$exec

(defn exit [#^PApplet sketch ] (.exit sketch))

;;(defn exp [a] (PApplet/exp (float a)))

;; $$expand


;; modified by DEL
(defn fill
"
  Sets the color used to fill shapes. For example, if you run (fill 204 102 0), all
  subsequent shapes will be filled with orange. This color is either specified in
  terms of the RGB or HSB color depending on the current colorMode() (the default
  color space is RGB, with each value in the range from 0 to 255).

  When using hexadecimal notation to specify a color, use '16r' before
  the values (e.g. 16rCCFFAA, 16rFFCCFFAA). Use six digits to specify
  a color (the way colors are specified in HTML and CSS). When eight
  digits are used, the first two characters define the alpha component and the
  remainder the red, green, and blue components.

  The value for the parameter \"gray\" must be less than or equal to the current
  maximum value as specified by (colorMode). The default maximum value is 255.

  To change the color of an image (or a texture), use (tint).



  Syntax:
    (fill sketch gray)
    (fill sketch gray alpha)
    (fill sketch value1 value2 value3)
    (fill sketch value1 value2 value3 alpha)
    (fill sketch color)
    (fill sketch color alpha)
    (fill sketch hex)
    (fill sketch hex alpha)


  Parameters:
    sketch    PApplet
    gray      int or float: number specifying value between white and black
    alpha     int or float: opacity of the fill
    value1    int or float: red or hue value
    value2    int or float: green or saturation value
    value3    int or float: blue or brightness value
    color     color: any value of the color datatype
    hex	int:  color value in hexadecimal notation (i.e. #FFCC00 or 0xFFFFCC00)

  Returns:
    None

  References:
    http://processing.org/reference/fill_.html


  Examples:

    (def sktch (sketch))
    (fill sktch \"0xFF00FF\")
    (fill sktch 0xFF 0x00 0xFF)
    (fill sktch 255 0 255)

"
  ([#^PApplet sketch gray]
   (cond
     (string? gray)
       (.fill sketch (color sketch gray))
     (integer? gray)
      (.fill sketch (int gray))
     (float? gray)
      (.fill sketch (float gray))))
  ([#^PApplet sketch gray alpha]
   (cond
     (string? gray)
       (.fill sketch (color sketch gray) (int alpha))
     (integer? gray)
      (.fill sketch (int gray) (int alpha))
     (float? gray)
      (.fill sketch (float gray) (float alpha))))
  ([#^PApplet sketch x y z]
   (if (or (float? x) (float? y) (float? z))
      (.fill sketch (float x) (float y) (float z))
      (.fill sketch (int x) (int y) (int z))))
  ([#^PApplet sketch x y z alpha]
   (if (or (float? x) (float? y) (float? z))
      (.fill sketch (float x) (float y) (float z) (float alpha))
      (.fill sketch (int x) (int y) (int z) (int alpha)))))






(defn filter-kind
  ([#^PApplet sketch kind] (.filter sketch (int kind)))
  ([#^PApplet sketch kind param] (.filter sketch (int kind) (float param))))

;; $$focusGained
;; $$focusLost

(defn framerate [#^PApplet sketch new-rate] (.frameRate sketch (float new-rate)))

;; added by DEL
(defn frame-count [#^PApplet sketch] (.frameCount sketch))


(defn frustum
  [#^PApplet sketch l r b t near far]
  (.frustum sketch (float l) (float r) (float b) (float t) (float near) (float far)))

(defn get-pixel
  ([#^PApplet sketch ] (.get sketch))
  ([#^PApplet sketch x y] (.get sketch (int x) (int y)))
  ([#^PApplet sketch x y w h] (.get sketch (int x) (int y) (int w) (int h))))

(defn green [#^PApplet sketch what] (.green sketch (int what)))

;; $$handleDraw
;; $$hex

(defn hint [#^PApplet sketch which] (.hint sketch (int which)))

(defn hour [] (PApplet/hour))

(defn hue [#^PApplet sketch what] (.hue sketch (int what)))

(defn image
  ([#^PApplet sketch #^PImage img x y] (.image sketch img (float x) (float y)))
  ([#^PApplet sketch #^PImage img x y c d] (.image sketch img (float x) (float y) (float c) (float d)))
  ([#^PApplet sketch #^PImage img x y c d u1 v1 u2 v2] (.image sketch img (float x) (float y) (float c) (float d) (float u1) (float v1) (float u2) (float v2))))

(defn image-mode [#^PApplet sketch mode] (.imageMode sketch (int mode)))

;; $$init
;; $$insertFrame
;; $$join
;; $$keyPressed
;; $$keyReleased
;; $$keyTyped
;; $$lerp
;; $$lerpColor
;; $$lightFallof


;; added by DEL
(defn lerp-color
 "
  Calculates a color or colors between two color at a specific increment.
  The amt parameter is the amount to interpolate between the two values
  where 0.0 equal to the first point, 0.1 is very near the first point,
  0.5 is half-way in between, etc.

  Parameters:
    c1  color: interpolate from this color
    c2  color: interpolate to this color
    amt float: between 0.0 and 1.0

  Returns:
    float

 "
  ([c1 c2 amt]
   (PApplet/lerpColor c1 c2 (float amt) 1))) ;; use RGB mode


(defn lights [#^PApplet sketch ] (.lights sketch))

(defn light-specular
  [#^PApplet sketch x y z]
  (.lightSpecular sketch (float x) (float y) (float z)))

(defn line
  ([#^PApplet sketch p1 p2] (apply line (concat p1 p2)))
  ([#^PApplet sketch x1 y1 x2 y2] (.line sketch (float x1) (float y1) (float x2) (float y2)))
  ([#^PApplet sketch x1 y1 z1 x2 y2 z2] (.line sketch (float x1) (float y1) (float z1) (float x2) (float y2) (float z2))))

;; $$link

(defn load-bytes [filename] (PApplet/loadBytes filename))

(defn load-font [#^PApplet sketch filename] (.loadFont sketch filename))

(defn load-image [#^PApplet sketch filename] (.loadImage sketch filename))

(defn load-matrix [#^PApplet sketch ] (.loadMatrix sketch))

(defn load-pixels [#^PApplet sketch ] (.loadPixels sketch))

(defn load-shape
        "Load a geometry from a file as a PShape."
        [#^PApplet sketch filename]
        (.loadShape sketch filename))

(defn load-strings
        "Load data from a file and shove it into a String array."
        [#^PApplet sketch filename]
        (.loadStrings sketch filename))

;; $$log

(defn start-loop [#^PApplet sketch ] (.loop sketch))

;; $$mag
;; $$main

;; renamed from map-to to remap by DEL
(defn remap [val istart istop ostart ostop]
  (PApplet/map (float val) (float istart) (float istop) (float ostart) (float ostop)))

(defn mask
  ([#^PApplet sketch #^ints alpha-array] (.mask sketch alpha-array)))

(defn mask-image [#^PApplet sketch #^PImage img] (.mask sketch img))

;; $$match
;; $$max

(defn millis [#^PApplet sketch ] (.millis sketch))

;; $$min

(defn minute [] (PApplet/minute))

(defn model-x [#^PApplet sketch x y z] (.modelX sketch (float x) (float y) (float z)))
(defn model-y [#^PApplet sketch x y z] (.modelY sketch (float x) (float y) (float z)))
(defn model-z [#^PApplet sketch x y z] (.modelZ sketch (float x) (float y) (float z)))

(defn month [] (PApplet/month))

;; $$mouseClicked
;; $$mouseDragged
;; $$mouseEntered
;; $$mouseExited
;; $$mouseMoved
;; $$mousePressed
;; $$mouseReleased
;; $$nf
;; $$nfc
;; $$nfp
;; $$nfs

(defn mouse-x [#^java.awt.event.MouseEvent mouse-event ] (.getX mouse-event))

(defn mouse-y [#^java.awt.event.MouseEvent mouse-event ] (.getY mouse-event))

(defn no-cursor [#^PApplet sketch ] (.noCursor sketch))

(defn no-fill [#^PApplet sketch ] (.noFill sketch))

(defn noise
  ([#^PApplet sketch x] (.noise sketch (float x)))
  ([#^PApplet sketch x y] (.noise sketch (float x) (float y)))
  ([#^PApplet sketch x y z] (.noise sketch (float x) (float y) (float z))))

(defn noise-detail
  ([#^PApplet sketch int detail] (.noiseDetail sketch (int detail)))
  ([#^PApplet sketch int detail falloff] (.noiseDetail sketch (int detail) (float falloff))))

(defn noise-seed [#^PApplet sketch what] (.noiseSeed sketch (int what)))

(defn no-lights [#^PApplet sketch ] (.noLights sketch))

(defn no-loop [#^PApplet sketch ] (.noLoop sketch))

(defn norm
        "Normalize a value to exist between 0 and 1 (inclusive)."
        [val start stop]
        (PApplet/norm (float val) (float start) (float stop)))

(defn normal [#^PApplet sketch nx ny nz] (.normal sketch (float nx) (float ny) (float nz)))

(defn no-smooth [#^PApplet sketch ] (.noSmooth sketch))

(defn no-stroke [#^PApplet sketch ] (.noStroke sketch))

(defn no-tint [#^PApplet sketch ] (.noTint sketch))

(defn open [#^java.lang.String filename] (PApplet/open filename))

;; $$open -- overload

(defn ortho
  ([#^PApplet sketch ] (.ortho sketch))
  ([#^PApplet sketch l r b t near far] (.ortho sketch (float l) (float r) (float b) (float t) (float near) (float far))))

;; $$paint
;; $$param
;; $$parseBoolean
;; $$parseByte
;; $$parseChar
;; $$parseFloat
;; $$parseInt

(defn perspective
  ([#^PApplet sketch ] (.perspective sketch))
  ([#^PApplet sketch fovy aspect z-near z-far]
         (.perspective sketch (float fovy) (float aspect) (float z-near) (float z-far))))

;; pmouse-x doesn't work, always returns 0
;;(defn pmouse-x [#^PApplet sketch ] (.pmouseX sketch))

;; pmouse-y doesn't work, always returns 0
;;(defn pmouse-y [#^PApplet sketch ] (.pmouseY sketch))

(defn point
  ([#^PApplet sketch x y] (.point sketch (float x)(float y)))
  ([#^PApplet sketch x y z] (.point sketch (float x) (float y) (float z))))

(defn point-light
  [#^PApplet sketch r g b x y z]
  (.pointLight sketch r g b x y z))

(defn pop-matrix [#^PApplet sketch ] (.popMatrix sketch))

;;(defn pow [a b] (PApplet/pow (float a) (float b)))

;; $$print

(defn print-camera [#^PApplet sketch ] (.printCamera sketch))

;; $$println

(defn print-matrix [#^PApplet sketch ] (.printMatrix sketch))

(defn print-projection [#^PApplet sketch ] (.printProjection sketch))

(defn push-matrix [#^PApplet sketch ] (.pushMatrix sketch))

(defn quad
  [#^PApplet sketch x1 y1 x2 y2 x3 y3 x4 y4]
  (.quad sketch x1 y1 x2 y2 x3 y3 x4 y4))

(defn radians [deg] (PApplet/radians (float deg)))

;;(defn random
;;  ([#^PApplet sketch max] (.random sketch (float max)))
;;  ([#^PApplet sketch min max] (.random sketch (float min) (float max))))


;;(defn random-seed [#^PApplet sketch w] (.randomSeed sketch (float w)))

(defn rect [#^PApplet sketch x1 y1 x2 y2]
  (.rect sketch (float x1) (float y1) (float x2) (float y2)))

(defn rect-mode [#^PApplet sketch mode] (.rectMode sketch (int mode)))

(defn red [#^PApplet sketch what] (.red sketch (int what)))

(defn redraw [#^PApplet sketch ] (.redraw sketch))

;; $$registerDispose
;; $$registerDraw
;; $$reqisterKeyEvent
;; $$registerMouseEvent
;; $$registerPost
;; $$registerPre
;; $$registerSize
;; $$registerSize

(defn request-image
        ([#^PApplet sketch filename] (.requestImage sketch filename))
        ([#^PApplet sketch filename extension] (.requestImage sketch filename extension)))

(defn reset-matrix [#^PApplet sketch ] (.resetMatrix sketch))

(defn reverse-array [arr] (PApplet/reverse arr))

(defn rotate
  ([#^PApplet sketch angle] (.rotate sketch (float angle)))
  ([#^PApplet sketch angle vx vy vz] (.rotate sketch (float angle) (float vx) (float vy) (float vz))))

(defn rotate-x [#^PApplet sketch angle] (.rotateX sketch (float angle)))

(defn rotate-y [#^PApplet sketch angle] (.rotateY sketch (float angle)))

(defn rotate-z [#^PApplet sketch angle] (.rotateZ sketch (float angle)))

(defn round [what] (PApplet/round (float what)))

;; $$run

(defn saturation [#^PApplet sketch what] (.saturation sketch (int what)))

;; modified by DEL
(defmethod save :sketch ([#^PApplet sketch filename]
                         (doto sketch
                               ;.redraw
                               (.save filename))))

;; $$saveBytes
;; $$saveFile

(defn save-frame
  ([#^PApplet sketch ] (.saveFrame sketch))
  ([#^PApplet sketch what] (.saveFrame sketch what)))

;; $$savePath
;; $$saveStream
;; $$saveStrings

(defn scale
  ([#^PApplet sketch s] (.scale sketch (float s)))
  ([#^PApplet sketch sx sy] (.scale sketch (float sx) (float sy))))

(defn screen-x
  ([#^PApplet sketch x y] (.screenX sketch (float x) (float y)))
  ([#^PApplet sketch x y y] (.screenX sketch (float x) (float y))))

(defn screen-y
  ([#^PApplet sketch x y] (.screenY sketch (float x) (float y)))
  ([#^PApplet sketch x y z] (.screenY sketch (float x) (float y) (float z))))

(defn screen-z
  [#^PApplet sketch x y z] (.screenX sketch (float x) (float y) (float z)))

(defn seconds [] (PApplet/second))

;; $$selectFolder
;; $$selectInput
;; $$selectOutput

(defn set-pixel
  [#^PApplet sketch x y c] (.set sketch (int x) (int y) (int c)))

(defn set-image-at
  [#^PApplet sketch dx dy #^PImage src] (.set sketch (int dx) (int dy) src))

;; $$setup

;; $$setupExternalMessages
;; $$setupFrameListener

(defn shininess [#^PApplet sketch shine] (.shininess sketch (float shine)))

;; $$shorten

;;(defn sin [angle] (PApplet/sin (float angle)))

(defn size
  ([#^PApplet sketch w h] (.size sketch (int w) (int h)))
  ([#^PApplet sketch w h #^java.lang.String renderer] (.size sketch (int w) (int h) renderer)))

;; $$sketchFile
;; $$sketchPath

(defn smooth [#^PApplet sketch ] (.smooth sketch))

;; $$sort

(defn specular
  ([#^PApplet sketch gray] (.specular sketch (float gray)))
  ([#^PApplet sketch gray alpha] (.specular sketch (float gray) (float alpha)))
  ([#^PApplet sketch x y z] (.specular sketch (float x) (float y) (float z)))
  ([#^PApplet sketch x y z a] (.specular sketch (float x) (float y) (float z) (float a))))

(defn sphere
  [#^PApplet sketch r] (.sphere sketch (float r)))

(defn sphere-detail
  ([#^PApplet sketch res] (.sphereDetail sketch (int res)))
  ([#^PApplet sketch ures vres] (.sphereDetail sketch (int ures) (int vres))))

;; $$splice
;; $$split
;; $$splitTokens

(defn spotlight
  ([#^PApplet sketch r g b x y z nx ny nz angle concentration]
         (.spotLight sketch r g b x y z nx ny nz angle concentration))
  ([#^PApplet sketch [r g b] [x y z] [nx ny nz] angle concentration]
         (.spotLight sketch r g b x y z nx ny nz angle concentration)))

;;(defn sq [a] (PApplet/sq (float a)))

;;(defn sqrt [a] (PApplet/sqrt (float a)))

;; $$start
;; $$status
;; $$stop
;; $$str

;; modified by DEL
(defn stroke
  ([#^PApplet sketch gray]
   (cond
     (string? gray)
      (.stroke sketch (color sketch gray))
     (integer? gray)
       (.stroke sketch (int gray))
     (float? gray)
       (.stroke sketch (float gray))))
  ([#^PApplet sketch gray alpha]
   (cond
     (string? gray)
      (.stroke sketch (color sketch gray) (int alpha))
     (integer? gray)
       (.stroke sketch (int gray) (int alpha))
     (float? gray)
       (.stroke sketch (float gray) (float alpha))))
  ([#^PApplet sketch x y z]
     (.stroke sketch (float x) (float y) (float z)))
  ([#^PApplet sketch x y z alpha]
     (.stroke sketch (float x) (float y) (float z) (float alpha))))


(defn stroke-cap [#^PApplet sketch cap] (.strokeCap sketch (int cap)))

(defn stroke-join [#^PApplet sketch jn] (.strokeJoin sketch (int jn)))

(defn stroke-weight [#^PApplet sketch weight] (.strokeWeight sketch (float weight)))

;; $$subset

;;(defn tan [angle] (PApplet/tan (float angle)))

;; added by DEL
(defn text
  ([#^PApplet sketch s]
    (.text sketch s))
  ([#^PApplet sketch s x y]
    (.text sketch s (float x) (float y)))
  ([#^PApplet sketch s x y z]
    (.text sketch s (float x) (float y) (float z)))
  ([#^PApplet sketch s x1 y1 x2 y2]
         (.text sketch s (float x1) (float y1) (float x2) (float y2)))
  ([#^PApplet sketch s x1 y1 x2 y2 z]
         (.text sketch s (float x1) (float y1) (float x2) (float y2) (float z))))


(defn char->text
  ([#^PApplet sketch c] (.text sketch (char c)))
  ([#^PApplet sketch c x y] (.text sketch (char c) (float x) (float y)))
  ([#^PApplet sketch c x y z] (.text sketch (char c) (float x) (float y) (float z))))

(defn num->text
  ([#^PApplet sketch num x y] (.text sketch (float num) (float x) (float y)))
  ([#^PApplet sketch num x y z] (.text sketch (float num) (float x) (float y) (float z))))

(defn string->text
  ([#^PApplet sketch #^java.lang.String s] (.text sketch s))
  ([#^PApplet sketch #^java.lang.String s x y] (.text sketch s (float x) (float y)))
  ([#^PApplet sketch #^java.lang.String s x y z] (.text sketch s (float x) (float y) (float z))))

(defn string->text-in
  ([#^PApplet sketch #^java.lang.String s x1 y1 x2 y2]
         (.text sketch s (float x1) (float y1) (float x2) (float y2)))
  ([#^PApplet sketch #^java.lang.String s x1 y1 x2 y2 z]
         (.text sketch s (float x1) (float y1) (float x2) (float y2) (float z))))

(defn text-align
  ([#^PApplet sketch align] (.textAlign sketch (int align)))
  ([#^PApplet sketch align-x align-y] (.textAlign sketch (int align-x) (int align-y))))

(defn text-ascent [#^PApplet sketch ] (.textAscent sketch))

(defn text-descend [#^PApplet sketch ] (.textDescent sketch))

(defn text-font
  ([#^PApplet sketch #^PFont which] (.textFont sketch which))
  ([#^PApplet sketch #^PFont which size] (.textFont sketch which (int size))))

(defn text-leading [#^PApplet sketch leading] (.textLeading sketch (float leading)))

(defn text-mode [#^PApplet sketch mode] (.textMode sketch (int mode)))

(defn text-size [#^PApplet sketch size] (.textSize sketch (float size)))

(defn texture [#^PApplet sketch #^PImage img] (.texture sketch img))

(defn texture-mode [#^PApplet sketch mode] (.textureMode sketch (int mode)))

(defmulti text-width #(= (class %) (class \a)))

(defmethod text-width true
  [#^PApplet sketch c] (.textWidth sketch (char c)))

(defmethod text-width false
  [#^PApplet sketch #^java.lang.String s] (.textWidth sketch s))

;; modified by DEL
(defn tint
  ([#^PApplet sketch gray]
   (cond
     (string? gray)
       (.tint sketch (color sketch gray))
     (integer? gray)
       (.tint sketch (int gray))
     (float? gray)
       (.tint sketch (float gray))))
  ([#^PApplet sketch gray alpha]
   (cond
     (string? gray)
       (.tint sketch (color sketch gray) (int alpha))
     (integer? gray)
       (.tint sketch (int gray) (int alpha))
     (float? gray)
       (.tint sketch (float gray) (float alpha))))
  ([#^PApplet sketch x y z]
   (if (or (integer? x) (integer? y) (integer? z))
       (.tint sketch (int x)(int y) (int z))
       (.tint sketch (float x)(float y) (float z))))
  ([#^PApplet sketch x y z a]
   (if (or (integer? x) (integer? y) (integer? z))
       (.tint sketch (int x)(int y) (int z) (int alpha))
       (.tint sketch (float x)(float y) (float z) (float alpha)))))


(defn translate
        ([v] (apply translate v))
  ([#^PApplet sketch tx ty] (.translate sketch (float tx) (float ty)))
  ([#^PApplet sketch tx ty tz] (.translate sketch (float tx) (float ty) (float tz))))

(defn triangle
  [#^PApplet sketch x1 y1 x2 y2 x3 y3]
  (.triangle sketch (float x1) (float y1) (float x2) (float y2) (float x3) (float y3)))

;; $$trim
;; $$unbinary
;; $$unhex
;; $$unint
;; $$unregisterDispose
;; $$unregisterDraw
;; $$unregiserKeyEvent
;; $$unregiserMouseEvent
;; $$unregiserKeyEvent
;; $$unregiserPost
;; $$unregisterPre
;; $$unregisterSize
;; $$update
;; $$updatePixels

(defn vertex
  ([#^PApplet sketch x y] (.vertex sketch (float x) (float y)))
  ([#^PApplet sketch x y z] (.vertex sketch (float x) (float y) (float z)))
  ([#^PApplet sketch x y u v] (.vertex sketch (float x) (float y) (float u) (float v)))
  ([#^PApplet sketch x y z u v]
         (.vertex sketch (float x) (float y) (float z) (float u) (float v))))

;; added by DEL
(defn width [sketch] (.width sketch))
(defn height [sketch] (.height sketch))


(defn year [] (PApplet/year))

;; utility functions. clj-processing specific

(defmacro with-translation
        "Berforms body with translation, restores current transformation on exit."
        [translation-vector & body]
        `(let [tr# ~translation-vector]
                 (push-matrix)
                 (translate tr#)
                 ~@body
                 (pop-matrix)))

(defmacro with-rotation
        "Berforms body with rotation, restores current transformation on exit.
  Accepts a vector [angle] or [angle x-axis y-axis z-axis].

  Example:
    (with-rotation [angle]
      (vertex 1 2))"
        [rotation & body]
        `(let [tr# ~rotation]
                 (push-matrix)
                 (apply rotate tr#)
                 ~@body
                 (pop-matrix)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; These CONSTANTS are located in the constants.clj file in the original
;; clj-processing library
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def P2D PApplet/P2D)
(def P3D PApplet/P3D)
(def JAVA2D PApplet/JAVA2D)
(def OPENGL PApplet/OPENGL)
(def PDF PApplet/PDF)
(def DXF PApplet/DXF)

;; platform IDs for PApplet.platform
(def WINDOWS PApplet/WINDOWS)
(def MACOSX PApplet/MACOSX)
(def LINUX PApplet/LINUX)
(def OTHER PApplet/OTHER)

;(def EPSILON (PApllet 0.0001))
(def EPSILON PApplet/EPSILON)

(def MAX_FLOAT PApplet/MAX_FLOAT)
(def MIN_FLOAT PApplet/MIN_FLOAT)

(def MAX_INT PApplet/MAX_FLOAT)
(def MIN_INT PApplet/MIN_FLOAT)

(def PI  (float Math/PI))
(def HALF_PI    (/ PI (float 2.0)))
(def THIRD_PI   (/ PI (float 3.0)))
(def QUARTER_PI (/ PI (float 4.0)))
(def TWO_PI     (* PI (float 2.0)))

(def DEG_TO_RAD (/ PI (float 180.0)))
(def RAD_TO_DEG (/ (float 180.0) PI))

;; for colors and/or images

(def RGB (int 1)) ; image & color
(def ARGB (int 2)) ; image
(def HSB (int 3)) ; color
(def ALPHA (int 4)) ; image
(def CMYK (int 5)) ; image & color (someday)

;; filter/convert types

(def BLUR      11)
(def GRAY      12)
(def INVERT    13)
(def OPAQUE    14)
(def POSTERIZE 15)
(def THRESHOLD 16)
(def ERODE     17)
(def DILATE    18)

;; blend mode keyword definitions
;; see processing.core.PImage#blendColor(int,int,int)

(def REPLACE     PApplet/REPLACE)
(def BLEND       PApplet/BLEND)
(def ADD         PApplet/ADD)
(def SUBTRACT    PApplet/SUBTRACT)
(def LIGHTEST    PApplet/LIGHTEST)
(def DARKEST     PApplet/DARKEST)
(def DIFFERENCE  PApplet/DIFFERENCE)
(def EXCLUSION   PApplet/EXCLUSION)
(def MULTIPLY    PApplet/MULTIPLY)
(def SCREEN      PApplet/SCREEN)
(def OVERLAY     PApplet/OVERLAY)
(def HARD_LIGHT  PApplet/HARD_LIGHT)
(def SOFT_LIGHT  PApplet/SOFT_LIGHT)
(def DODGE       PApplet/DODGE)
(def BURN        PApplet/BURN)

;; colour component bitmasks

(def ALPHA_MASK  PApplet/ALPHA_MASK)
(def RED_MASK    PApplet/RED_MASK)
(def GREEN_MASK  PApplet/GREEN_MASK)
(def BLUE_MASK   PApplet/BLUE_MASK)

;; for messages

(def CHATTER    PApplet/CHATTER)
(def COMPLAINT  PApplet/COMPLAINT)
(def PROBLEM    PApplet/PROBLEM)

;; types of projection matrices

(def CUSTOM        PApplet/CUSTOM)        ;; user-specified fanciness
(def ORTHOGRAPHIC  PApplet/ORTHOGRAPHIC)  ;; 2D isometric projection
(def PERSPECTIVE   PApplet/PERSPECTIVE)   ;; perspective matrix

;; shapes

;; the low four bits set the variety,
;; higher bits set the specific shape type

; (def GROUP            PApplet/GROUP)

(def POINT            PApplet/POINT) ;shared with light
(def POINTS           PApplet/POINTS)

(def LINE             PApplet/LINE)
(def LINES            PApplet/LINES)

(def TRIANGLE         PApplet/TRIANGLE)
(def TRIANGLES        PApplet/TRIANGLES)
(def TRIANGLE_STRIP   PApplet/TRIANGLE_STRIP)
(def TRIANGLE_FAN     PApplet/TRIANGLE_FAN)

(def QUAD             PApplet/QUAD)
(def QUADS            PApplet/QUADS)
(def QUAD_STRIP       PApplet/QUAD_STRIP)

(def POLYGON          PApplet/POLYGON)
(def PATH             PApplet/PATH)

(def RECT             PApplet/RECT)
(def ELLIPSE          PApplet/ELLIPSE)
(def ARC              PApplet/ARC)

(def SPHERE           PApplet/SPHERE)
(def BOX              PApplet/BOX)

(def OPEN             PApplet/OPEN)
(def CLOSE            PApplet/CLOSE)

(def CONCAVE_POLYGON  (bit-or (bit-shift-left 1 8) 1))
(def CONVEX_POLYGON   (bit-or (bit-shift-left 1 8) 2))

(def CORNER PApplet/CORNER)
(def CORNERS PApplet/CORNERS)
(def RADIUS PApplet/RADIUS)
(def CENTER PApplet/CENTER)
(def DIAMETER PApplet/DIAMETER)

;; vertical alignment for text placement

(def BASELINE PApplet/BASELINE)
(def TOP PApplet/TOP)
(def BOTTOM PApplet/BOTTOM)

;; uv texture orientation modes

(def NORMAL PApplet/NORMAL)
(def NORMALIZED PApplet/NORMALIZED)
(def IMAGE PApplet/IMAGE)

;; stroke modes

(def SQUARE PApplet/SQUARE)
(def ROUND PApplet/ROUND)
(def PROJECT PApplet/PROJECT)
(def MODEL PApplet/MODEL)

;; LIGHTING

(def AMBIENT PApplet/AMBIENT)
(def DIRECTIONAL PApplet/DIRECTIONAL)
;; (def POINT PApplet/POINT) ; shared with shape feature
(def SPOT PApplet/SPOT)

;; keys

(def BACKSPACE PApplet/BACKSPACE)
(def TAB PApplet/TAB)
(def ENTER PApplet/ENTER)
(def RETURN PApplet/RETURN)
(def ESC PApplet/ESC)
(def DELETE PApplet/DELETE)

(def UP PApplet/UP)
(def DOWN PApplet/DOWN)
(def LEFT PApplet/LEFT)
(def RIGHT PApplet/RIGHT)

(def ALT PApplet/ALT)
(def CONTROL PApplet/CONTROL)
(def SHIFT PApplet/SHIFT)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Incanter specific additions to clj-processing
;; author: David Edgar Liebke
;; date: 31 July 2009



(defmacro sketch [& methods]
" Returns a Processing PApplet with the given methods.

  Arguments:
    methods -- Processing methods including: setup (require)
               draw, mousePressed, etc.


  Examples:

(use '(incanter core processing))


(let [map-image (ref nil)
      sktch (sketch

              ;; define the setup function
              (setup []
                (dosync (ref-set map-image
                                 (load-image this \"examples/images/map.png\")))
                (size this 640 400))

              ;; define the draw function
              (draw []
                (doto this
                  ;(background-float 255)
                  (background 255)
                  (image @map-image 0 0))))]

  (view-sketch sktch :title \"US Map\" :width 640 :height 400))


  References:
    http://processing.org/reference/

"
  `(let [sktch# (proxy [processing.core.PApplet] [] ~@methods)]
     (do (.init sktch#)
         sktch#)))



(defmethod view :sketch
  ([sketch & options]
    (let [opts (when options (apply assoc {} options))
          title (or (:title opts) "Processing Sketch")
          width (or (:width opts) (.width (.getSize sketch)))
          height (or (:height opts) (.height (.getSize sketch)))
          [width height] (or (:size opts)
                             [(.width (.getSize sketch))
                              (.height (.getSize sketch))])
          frame (javax.swing.JFrame. title)
          ]
      (doto frame
            (.add sketch)
            (.setDefaultCloseOperation javax.swing.WindowConstants/EXIT_ON_CLOSE)
            (.setSize width height)
            (.setVisible true)))))
