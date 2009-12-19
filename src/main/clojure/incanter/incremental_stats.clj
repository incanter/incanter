(ns incanter.incremental-stats
  (:use [clojure.contrib.monads 
	 :only [domonad state-m update-val fetch-val]])
;;  (:import [org.apache.commons.math.stat.descriptive.rolling RollingMean]) 
  (:use clojure.contrib.math))

;; http://en.wikipedia.org/wiki/Covariance
;; Incremental computation

;; Covariance can be computed efficiently from incrementally available values using a generalization of the computational formula for the variance:

;;     \operatorname{Cov}(X_i, X_j) = \operatorname{E}\left((X_i-\operatorname{E}(X_i))(X_j-\operatorname{E}(X_j))\right) = \operatorname{E}(X_iX_j) -\operatorname{E}(X_i)\operatorname{E}(X_j) 

(defn mean-state 
""
[x val queue]
   [(+ val (/ (- x (peek queue)) (count queue))) (conj (pop queue) x)])

(defn mean-state-2 
[x val queue] 
  (let [m (+ val (/ (- x (peek queue)) (count queue)))] 
    [m #(mean-state-2 %1 m (conj (pop queue) x))]))

;; test> 
;; (defn mean-state-2 [x val queue] 
;;   (let [m (+ val (/ (- x (peek queue)) (count queue)))] 
;;     [m #(mean-state-2 %1 m (conj (pop queue) x))]))
;; #'test/mean-state-2
;; test> (mean-state-2 4 2 (conj clojure.internal.PersistentQueue/EMPTY 1 2 3))
;; [3 #<test$mean_state_2__1717$fn__1719 test$mean_state_2__1717$fn__1719@1c30993>]
;; test> (def nextmean (last (mean-state-2 4 2 (conj clojure.internal.PersistentQueue/EMPTY 1 2 3))))
;; #'test/nextmean
;; test> (nextmean 5)
;; [4 #<test$mean_state_2__1717$fn__1719 test$mean_state_2__1717$fn__1719@1854b38>]
;; test> 

(defn update-sample 
""
[x]
 (update-val :sample #(conj (pop %) x)))

;;Monad spike for stateful rolling stats
(defn update-mean
""
 ([x]
  (domonad state-m
    [sample (fetch-val :sample)
     _ (update-val :mean #(+ % (/ (- x (peek sample)) (count sample))))
     _ (update-sample x)
     mean (fetch-val :mean)]
    mean))
 ([x & xs]
  (domonad state-m
    [_ (update-mean x)
     mean (apply update-mean xs)]
    mean)))

(def a-state {:mean 2 :sample (conj clojure.lang.PersistentQueue/EMPTY 1 2 3)})

;; ((update-mean 4) a-state)

;; ((update-mean 4 5 6) a-state)


(defn tuplize-apply 
""
[f] 
  (fn [x] [(f x) f]))

;; (defn rolling-mean [l k]
;;   (let [rm  (RollingMean. l)]
;;   (let [m (fn [{val k}] (.increment rm val))]
;;     (let [mean-fn (tuplize-apply m)]
;;      (tuplize-apply mean-fn)))))

;; (deftest rolling-mean-test
;;   (is (= 105
;; 	 (let [m (rolling-mean 2 :crselapsedtime)]
;; 	   (let [m1 (m {:crselapsedtime 100})]
;; 	     (first ((second m1) {:crselapsedtime 110})))))))

;; (defn rolling-mean-map [obs len key]
;;   (let [rm (RollingMean. len)]
;;     (map #(.increment rm %) (map key obs))))

;; ;; (deftest rolling-mean-map-test
;; ;;   (is (= 105
;; ;; 	 (nth (rolling-mean-map [{:crselapsedtime 100} {:crselapsedtime 110} {:crselapsedtime 100}] 2 :crselapsedtime) 3))))


