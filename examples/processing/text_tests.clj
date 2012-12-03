


;; example 13-01
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (let [font (load-font this "examples/processing/data/Ziggurat-HTF-Black-32.vlw")]
        (doto this
          (text-font font)
          smooth
          (fill 0))))

        (draw []
          (doto this
            ;(text 9 0 40)
            ;(text 8 0 70)
            ;(text 7 0 100))))
            ;(text \L 0 40)
            ;(text \a 0 70)
            ;(text \F 0 100))))
            (text "LAX" 0 40)
            (text "AMS" 0 70)
            (text "FRA" 0 100))))
  :size [120 120])



(use '(incanter core processing))
;; use list-fonts to see the fonts available on the current system
;; (list-fonts)

(view
  (sketch 
    (setup [] 
      (let [font (create-font this "Chalkboard" 48 true)]
        (doto this
          (text-font font)
          ;smooth
          (fill 0))))

        (draw []
          (doto this
            ;(text 9 0 40)
            ;(text 8 0 70)
            ;(text 7 0 100))))
            ;(text \L 0 40)
            ;(text \a 0 70)
            ;(text \F 0 100))))
            (text "LAX" 0 40)
            (text "AMS" 0 70)
            (text "FRA" 0 100))))
  :size [120 120])


