;; from the following blog post:
;; http://incanter-blog.org/2009/06/21/chi-square-goodness-of-fit/

;; Chi-square goodness-of-fit analysis of 2009 Iranian election and Benford's law

(use '(incanter core stats charts io))
(def votes (read-dataset "data/iran_election_2009.csv" 
                         :header true))
(view votes)

(def regions (sel votes :cols :Region))


(def ahmadinejad-votes (sel votes :cols :Ahmadinejad))
(def mousavi-votes (sel votes :cols :Mousavi))
(def rezai-votes (sel votes :cols :Rezai))
(def karrubi-votes (sel votes :cols :Karrubi))

(defn first-digit [x] 
  (Character/digit (first (str x)) 10)) 

(def ahmadinejad (map first-digit ahmadinejad-votes))
(def mousavi (map first-digit mousavi-votes))
(def rezai (map first-digit rezai-votes))
(def karrubi (map first-digit karrubi-votes))


;; define function for Benford's law
(defn benford-law [d] (log10 (plus 1 (div d))))
;; calculate the probabilities for digits 1-9
(def benford-probs (benford-law (range 1 11)))
;; calculate the expected frequencies for the 30 regions
(def benford-freq (mult benford-probs (count regions)))

(defn get-counts [digits] 
  (map #(get (:counts (tabulate digits)) % 0) 
       (range 1.0 10.0 1.0))) 


(doto (xy-plot (range 1 10) (get-counts ahmadinejad)
               :legend true :series-label "Ahmadinejad"
               :y-label "First digit frequency"
               :x-label "First digit"
               :title "First digit frequency by candidate")
      (add-lines (range 1 10) (get-counts mousavi) 
                 :series-label "Mousavi")
      (add-lines (range 1 10) benford-freq 
                 :series-label "Predicted")
      ;(add-lines (range 1 10) (get-counts rezai) :series-label "Rezai")
      ;(add-lines (range 1 10) (get-counts karrubi) :series-label "Karrubi")
      clear-background
      view)


(def ahmadinejad-test 
  (chisq-test :table (get-counts ahmadinejad) 
              :probs benford-probs))
(:X-sq ahmadinejad-test) ;; 5.439
(:p-value ahmadinejad-test) ;; 0.7098 

(def mousavi-test 
  (chisq-test :table (get-counts mousavi) 
              :probs benford-probs))
(:X-sq mousavi-test) ;; 5.775
(:p-value mousavi-test) ;; 0.672

(def rezai-test 
  (chisq-test :table (get-counts rezai) 
              :probs benford-probs))
(:X-sq rezai-test) ;; 12.834
(:p-value rezai-test) ;; 0.118

(def karrubi-test 
  (chisq-test :table (get-counts karrubi) 
              :probs benford-probs))
(:X-sq karrubi-test) ;; 8.8696
(:p-value karrubi-test) ;; 0.353



;; now compare the distribution of the last digit, it should be uniform
;; based on Washington Post article:
;; http://www.washingtonpost.com/wp-dyn/content/article/2009/06/20/AR2009062000004.html?referrer=reddit

(defn last-digit [x] 
  (Character/digit (last (str x)) 10)) 

(def ahmadinejad-last (map last-digit 
                           ahmadinejad-votes))
(def mousavi-last (map last-digit 
                       mousavi-votes))
(def rezai-last (map last-digit 
                     rezai-votes))
(def karrubi-last (map last-digit 
                       karrubi-votes))


(defn get-counts [digits] 
  (map #(get (:counts (tabulate digits)) % 0) 
       (range 0.0 10.0 1.0))) 



(doto (xy-plot (range 10) 
               (get-counts ahmadinejad-last)
               :legend true 
               :series-label "Ahmadinejad"
               :y-label "First digit frequency"
               :x-label "First digit"
               :title "Last digit frequency by candidate")
      (add-lines (range 10) (get-counts mousavi-last) 
                 :series-label "Mousavi")
      (add-lines (range 10) (get-counts rezai-last) 
                 :series-label "Rezai")
      (add-lines (range 10) (get-counts karrubi-last) 
                 :series-label "Karrubi")
      clear-background
      view)


(def ahmadinejad-test 
  (chisq-test :table (get-counts ahmadinejad-last)))

(:X-sq ahmadinejad-test) ;; 4.667
(:p-value ahmadinejad-test) ;; 0.862

(def mousavi-test 
  (chisq-test :table (get-counts mousavi-last)))
(:X-sq mousavi-test) ;; 8.667
(:p-value mousavi-test) ;; 0.469

(def rezai-test 
  (chisq-test :table (get-counts rezai-last)))
(:X-sq rezai-test) ;; 15.333 
(:p-value rezai-test) ;; 0.0822

(def karrubi-test 
  (chisq-test :table (get-counts karrubi-last)))
(:X-sq karrubi-test) ;; 4.0
(:p-value karrubi-test) ;; 0.911



