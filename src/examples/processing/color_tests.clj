
;; example 9-01
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (background 242 204 47)
        (size 100 100))))
  :size [120 120])


;; example 9-02
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (background 174 224 60)
        (size 100 100))))
  :size [120 120])




;; example 9-02
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        no-stroke
        (fill (color 174 221 60))))

    (draw []
      (doto this
        (background 129 130 87)
        (rect 17 17 66 66))))
  :size [120 120])



;; example 9-04
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        no-fill
        (stroke-weight 4)))

    (draw []
      (doto this
        (background 129 130 87)
        (stroke (color 174 221 60))
        (rect 17 17 66 66))))
  :size [120 120])



;; example 9-05
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (no-stroke this))

    (draw []
      (doto this
        (background 116 193 206)
        (fill 129 130 87 102) ;; more transparent, orig value 102
        (rect 20 20 30 60)
        (fill 129 130 87 204) ;; less transparent, orig value 204
        (rect 50 20 30 60))))
  :size [120 120])



;; example 9-06
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
        (no-stroke this))

    (draw []
      (background this 116 193 206)
      (let [alpha (range 51 256 51)]
      ;(let [alpha (range 5 25 5)]
        (doseq [i (range (count alpha))]
          (doto this
            (fill 129 130 87 (nth alpha i))
            (rect (* 20 i) 20 20 60))))))
  :size [120 120])


;; example 9-03 rewritten with hex color code
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (background (color 0x818257))
        (size 100 100)
        no-stroke
        (fill (color 0xAEDD3C))))

        (draw []
          (doto this
            (rect 17 17 66 66))))
  :size [120 120])



;; example 9-07 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        smooth
        (stroke-weight 12)))

        (draw []
          (doto this
            (background 56 90 94) ;; background needs to be in draw or it won't refresh per frame
            (stroke 242 204 47 102) ;; more transparency 
            (line 30 20 50 80)
            (stroke 242 204 47 204) ;; less transparency
            (line 50 20 70 80))))
  :size [120 120])




;; example 9-08 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        smooth
        (stroke-weight 12)))

        (draw []
          (background this 56 90 94) 
          (let [alpha (range 51 255 51)]
            (doseq [i (range (count alpha))]
              (doto this
                (stroke 242 204 47 (nth alpha i)) 
                (line (* i 20) 20 (+ (* i 20) 20) 80))))))
  :size [120 120])



;; example 9-09 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        smooth
        no-stroke))

        (draw []
          (doto this
            (background 0) 
            (fill 242 204 47 160) ;; yellow
            (ellipse 47 36 64 64)
            (fill 174 221 69 160) ;; green
            (ellipse 90 47 64 64)
            (fill 116 193 206 160) ;; blue
            (ellipse 57 79 64 64))))
  :size [120 120])




;; example 9-10 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        smooth
        no-stroke))

        (draw []
          (doto this
            (background 255) 
            (fill 242 204 47 160) ;; yellow
            (ellipse 47 36 64 64)
            (fill 174 221 69 160) ;; green
            (ellipse 90 47 64 64)
            (fill 116 193 206 160) ;; blue
            (ellipse 57 79 64 64))))
  :size [120 120])



;; example 9-11 
(use '(incanter core processing))

(let [ruby (color 211 24 24 160)
      pink (color 237 159 176)]
  (view
    (sketch 
      (setup [] 
        (doto this
          (size 100 100)
          no-stroke))

          (draw []
            (doto this
              (background pink)
              (fill ruby)
              (rect 35 0 20 100))))
    :size [120 120]))



;; example 9-15 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        (color-mode HSB)))

        (draw []
          (doseq [i (range 0 100)]
            (doto this
              (stroke (* i 2.5) 255 255)
              (line i 0 i 100)))))
  :size [120 120])




;; example 9-16 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        (color-mode HSB)))

        (draw []
          (doseq [i (range 0 100)]
            (doto this
              (stroke 132 (* i 2.5) 204)
              (line i 0 i 100)))))
  :size [120 120])




;; example 9-17 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        (color-mode HSB)))

        (draw []
          (doseq [i (range 0 100)]
            (doto this
              (stroke 132 108 (* i 2.5))
              (line i 0 i 100)))))
  :size [120 120])




;; example 9-18 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        (color-mode HSB)))

        (draw []
          (doseq [i (range 0 100) j (range 0 100)]
            (doto this
              (stroke 132 (* j 2.5) (* i 2.5))
              (point i j)))))
  :size [120 120])



;; example 9-19 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        (color-mode RGB)))

        (draw []
          (doseq [i (range 0 100)]
            (let [r (+ 61 (* i 0.92))
                  g (+ 156 (* i 0.48))
                  b (- 204 (* i 1.43))]
            (doto this
              (stroke r g b)
              (line i 0 i 100))))))
  :size [120 120])

;; example 9-20 
(use '(incanter core processing))

(view
  (sketch 
    (setup [] 
      (doto this
        (size 100 100)
        (color-mode HSB 360 100 100)))

        (draw []
          (doseq [i (range 0 100)]
            (let [hue (- 200 (* i 1.2))]
            (doto this
              (stroke hue 70 80)
              (line i 0 i 100))))))
  :size [120 120])



