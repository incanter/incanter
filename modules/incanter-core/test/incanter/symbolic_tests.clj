;;; symbolic-test-cases.clj -- Unit tests of Incanter functions 

;; by David Edgar Liebke http://incanter.org
;; May 3 2010

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.



(ns incanter.symbolic-tests
  (:use clojure.test 
        (incanter core symbolic)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.stats.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest deriv-test 
  (is (= (deriv (+ x 3) x) 1))
  (is (= (deriv (* x y) x) 'y))
  (is (= (deriv (* (* x y) (+ x 3)) x) '(+ (* (+ x 3) y) (* x y))))
  (is (= (deriv (* (* x y) (+ x 3)) y) '(* (+ x 3) x)))

  (is (= (deriv (* x y (+ x 3)) x) '(+ (* y (+ x 3)) (* y x))))
  (is (= (deriv (* x y (+ x 3)) y) '(* (+ x 3) x)))

  (is (= (deriv (sin x) x) '(cos x)))
  (is (= (deriv (cos x) x) '(* -1 (sin x))))

  (is (= (deriv (sin (* x y)) y) '(* x (cos (* x y)))))

  (is (= (deriv (pow x 3) x) '(* 3 (pow x 2))))
  (is (= (deriv (** x 3) x) '(* 3 (pow x 2))))

  (is (= (deriv (pow x 3) x 2) '(* 3 (* 2 x))))

  (is (= (deriv (* x y (+ x 3)) x 2) '(+ y y)))
  (is (= (deriv (* x y (+ x 3)) x 3) 0))

  (is (= (deriv (+ (* 3 x) (* 8 x)) x) 11))
  )