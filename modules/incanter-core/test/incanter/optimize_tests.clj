
;;; optimize-test-cases.clj -- Unit tests of Incanter functions

;; by John Sullivan
;; March 19, 2013

;; Copyright (c) John Sullivan, 2013. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.


(ns incanter.optimize-tests
  (:use clojure.test
    (incanter core optimize)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.optimize.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn rosenbrock
  [[x y]]
  ($= (1 - x) ** 2 + 100 * (y - x ** 2) ** 2))

(defn rosenbrock-der
  [[x y]]
  [($= 2 * (200 * x ** 3 - 200 * x * y + x - 1))
   ($= 200 * (y - x ** 2))])

(def neg-rosenbrock (comp - rosenbrock))
(def neg-rosenbrock-der (comp (partial map -) rosenbrock-der))


(deftest minimize-test
  (is (= (:value (minimize rosenbrock [0 0] rosenbrock-der {:max-iter 500})) (matrix [1 1]))))

(deftest maximize-test
  (is (= (:value (maximize neg-rosenbrock [0 0] neg-rosenbrock-der {:max-iter 500})) (matrix [1 1]))))
