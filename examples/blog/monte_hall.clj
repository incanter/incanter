;; from the following blog post
;; http://incanter-blog.org/2009/06/23/monty-hall-in-monte-carlo/

;; Monty Hall problem (Let's Make a Deal gameshow)
;; http://www.marilynvossavant.com/articles/gameshow.html
(use '(incanter core stats charts))

;; set a simulation sample size
(def n 10000)

;; generate samples of initial-guesses, prize-doors, and switch decisions
(def initial-guesses (sample [1 2 3] :size n))
(def prize-doors (sample [1 2 3] :size n))
(def switches (sample [true false] :size n))


;; define a function that returns 1 if a switch decision results in winning
(defn switch-win? [initial-guess switch prize-door] 
  (if (and switch (not= initial-guess prize-door)) 1 0))

;; define a function that returns 1 if a stay decision results in winning
(defn stay-win? [initial-guess switch prize-door] 
  (if (and (not switch) (= initial-guess prize-door)) 1 0))



;; calculate the joint probability of winning and switching
(def prob-switch-win (/ (sum (map switch-win? 
                              initial-guesses 
                              switches 
                              prize-doors)) 
                        n))

;; calculate the probability of switching doors
(def prob-switch (/ (sum (indicator true? switches)) n))

;; calculate the conditional probability of winning given a switch
(def prob-win-given-switch (/ prob-switch-win prob-switch))


;; calculate the joint probability of winning and NOT switching
(def prob-stay-win (/ (sum (map stay-win? 
                            initial-guesses 
                            switches 
                            prize-doors)) 
                      n))

;; calculate the probability of NOT switching doors
(def prob-stay (/ (sum (indicator false? switches)) n))

;; calculate the conditional probability of winning given NOT switching
(def prob-win-given-stay (/ prob-stay-win prob-stay))

(view (bar-chart ["Switch" "Stay"] 
                 [prob-win-given-switch prob-win-given-stay]
                 :title "Monty Hall Problem" 
                 :x-label "Decision" 
                 :y-label "Win Probability"))



