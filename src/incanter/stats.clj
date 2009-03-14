;;; stats.clj -- Statistics library for Clojure built on the CERN Colt Library

;; by David Edgar Liebke http://incanter.org
;; March 11, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

;; CHANGE LOG
;; March 11, 2009: First version


;NOTE
;(time (let [arr  (make-array Float/TYPE 1 1)] 
;(dotimes [_ 100000] 
;  (let [#^floats sub-arr (aget arr 0)] 
;    (aset-float sub-arr 0 0.0))))) 
;

; to compile (compile 'incanter.stats)
; to use (use incanter.stats)


(ns incanter.stats 
  (:import (cern.colt.list DoubleArrayList)
           (cern.jet.random Gamma)
           (cern.jet.random.engine MersenneTwister)
           (cern.jet.stat Descriptive))
  (:use (incanter matrix)))


;;------------------------------------------------------------------------------
;; DATASET FUNCTIONS
;;------------------------------------------------------------------------------

(defn colnames 
  ([mat] (keys (:colnames ^mat)))
  ([mat col-names] 
   (if col-names 
    (with-meta mat 
              (assoc ^mat :colnames 
                (into {} (map vector col-names (range (count col-names)))))))))

;;;;;;;;;;;;;;; RANDOM DIST FUNCTIONS ;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn runif [n & options]
  (let [opts (if options (apply assoc {} options) nil) 
        rnd (new java.util.Random)
       ]
    (loop [v [] i 0]
      (if (< i n)
        (recur (conj v (.nextDouble rnd)) (inc i))
        v))))


(defn rnorm [n & options]
   (let [opts (if options (apply assoc {} options) nil) 
          rnd (new java.util.Random)
          m (if (number? (:mean opts)) (:mean opts) 0)
          s (if (number? (:sd opts)) (:sd opts) 1)]
          (loop [v [] i (int 0)]
            (if (< i n)
              (recur 
                (conj v (if (and (= m 0) (= 1)) 
                          (.nextGaussian rnd) 
                          (+ (* (.nextGaussian rnd) s) m)))
                (inc i))
              v))))


(defn normal-generator []
  (let [rnd (java.util.Random.)]
    (fn [mean sd] 
      (+ (* (.nextGaussian rnd) sd) mean))))


(defn rand-gamma [shape rate]
  (Gamma/staticNextDouble shape rate))


(defn gamma-generator []
  (let [rnd (Gamma. 1 1 
                 (MersenneTwister. (java.util.Date.)))]
    (fn [shape rate] 
      (.nextDouble rnd shape rate))))


(defn rgamma [n & options]
   (let [opts (if options (apply assoc {} options) nil) 
          rnd (gamma-generator)
          shape (if (number? (:shape opts)) (:shape opts) 1)
          rate (if (number? (:rate opts)) (:rate opts) 1)
        ]
          (loop [v [] i 0]
            (if (< i n)
              (recur 
                (conj v (rnd shape rate)) 
                (inc i))
              v))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; STATISTICAL FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn covariance 
  ([x y]
    (let [
          xx (to-1D-vect x)
          yy (to-1D-vect y)
        ]
      (Descriptive/covariance 
        (DoubleArrayList. (double-array xx))
        (DoubleArrayList. (double-array yy)))))
  ([mat]
        (let [n (ncol mat)]
          (matrix 
            (for [i (range n) j (range n)] 
              (covariance (sel mat true i) (sel mat true j))) n))))


(defn sum-of-squares [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/sumOfSquares (DoubleArrayList. (double-array xx)))))


(defn sum [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/sum (DoubleArrayList. (double-array xx)))))


(defn variance [x]
  (Descriptive/sampleVariance (length x) (sum x) (sum-of-squares x)))


(defn mean [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/mean (DoubleArrayList. (double-array xx)))))


(defn median [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/median (DoubleArrayList. (double-array xx)))))


(defn sd [x]
  (Descriptive/sampleStandardDeviation (length x) (variance x)))


(defn prod [x]
  (let [xx (to-1D-vect x)]
    (Descriptive/product (DoubleArrayList. (double-array xx)))))


(defn hist [x & options]
  (let [opts (if options (apply assoc {} options) nil)
        xx (to-1D-vect x)
        nbin (if (:nbin opts) 
               (:nbin opts) 
               (if (< (count xx) 10) (count xx) 10))
        min-val (reduce min xx)
        max-val (reduce max xx)
        bin-size (/ (- max-val min-val) nbin)
        bin-edges (into [] (for [i (range nbin)] (+ min-val (* (inc i) bin-size))))
        cumm-counts (into [] (for [b bin-edges] (reduce + (map #(if (<= % b) 1 0) xx))))
        bin-counts (into [(first cumm-counts)] (map #(- %2 %1) cumm-counts (into [] (next cumm-counts))))
       ]
    {:min-val min-val 
     :max-val max-val 
     :bin-size bin-size
     :nbin nbin
     :bin-edges bin-edges 
     :cumm-counts cumm-counts 
     :bin-counts bin-counts}))


(defn print-hist [x & options]
  (let [opts (if options (apply assoc {} options) nil)
        nbin (if (:nbin opts) (:nbin opts) 10)
        hist-width (if (:hist-width opts) (:hist-width opts) 80)
        h (if (:nbin opts) (hist x :nbin (:nbin opts)) (hist x))
        max-count (reduce max (:bin-counts h))
       ]
    (dotimes [i (:nbin h)]
      (dotimes [_ (* hist-width (/ ((:bin-counts h) i) max-count))] (print \*))
      (print (format " %.2f " ((:bin-edges h) i)))
      (print [((:bin-counts h) i)])
      (println))))



;;;;;;;;;;;;;;; OLS FUNCTIONS ;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn lm-coef [x y]
  (mmult (solve (mmult (trans x) x)) (mmult (trans x) y)))


(defn lm-resid [x y]
  (minus y (mmult x (lm-coef x y))))


(defn lm-se [x y]
  (let [S (sum-of-squares (lm-resid x y))
        n (nrow y)
        p (ncol x)
        xtxi (solve (mmult (trans x) x))
       ]
    (into [] (for [i (range p)] (* (/ S (- n p 1)) (sel xtxi i i))))))




