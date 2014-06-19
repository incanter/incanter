;;; infix-tests.clj -- Unit tests of Incanter infix expression functions

;; by Michael Nygard http://incanter.org
;; Sept 16 2011

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.



(ns incanter.infix-tests
  (:use clojure.test
        (incanter core infix))
  (:require [clojure.core.matrix :as m]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.infix.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn basic-arithmetic []
  (is (= ($= 7 + 8 - 2 * 6 / 2) 9))
  (is (m/equals ($= [1 2 3] + 5) '(6 7 8)))
  (is (m/equals ($= [1 2 3] + [4 5 6]) '(5 7 9)))
  (is (m/equals ($= [1 2 3] * [1 2 3]) '(1 4 9)))
  (is (m/equals ($= [1 2 3] / [1 2 3]) '(1 1 1)))
  (is (m/equals ($= (matrix [[1 2] [4 5]]) + 6) (matrix [[7 8] [10 11]])))
  (is (m/equals ($= (trans [[1 2] [4 5]]) + 6) (matrix [[7 10] [8 11]])))
  (is (= ($= 8 ** 3) 512.0))
  (is (= ($= 8 ** 1/2) 2.8284271247461903))
  (is (= ($= 2 ** -2) 0.25))
  (is (m/equals ($= [1 2 3] ** 2) '(1.0 4.0 9.0)))
  (is (= ($= 10 + 20 * (4 - 5) / 6) 20/3))
  (is (= ($= (10 + 20) * 4 - 5 / 6) 715/6))
  (is (= ($= 10 + 20 * (4 - 5 / 6)) 220/3))
  (is (= ($= ((((5 + 4) * 5)))) 45)))

(defn functions-in-infix-expressions []
  (is (= (let [x 10 y -5] ($= x + y / -10)) 21/2))
  (is (= ($= (sqrt 5) * 5 + 3 * 3) 20.18033988749895))
  (is (m/equals ($= sq [1 2 3] + [1 2 3]) (matrix [2 6 12])))
  (is (= ($= sin 2 * Math/PI * 2) 5.713284232087328))
  (is (= ($= (cos 0) * 10) 10.0))
  (is (= ($= (tan 2) * Math/PI * 10) -68.64505182223235))
  (is (= ($= (asin 1/2) * 10) 5.23598775598299))
  (is (= ($= (acos 1/2) * 10) 10.47197551196598))
  (is (= ($= (atan 1/2) * 10) 4.636476090008061))
  (is (m/equals ($= [1 2 3] / (sq [1 2 3]) + [5 6 7]) '(6 13/2 22/3)))
  (is (m/equals ($= [1 2 3] + (sin [4 5 6]))
                '(0.2431975046920718 1.0410757253368614 2.720584501801074)))
  (is (= ($= 3 > (5 * 2/7)) true))
  (is (= ($= 3 <= (5 * 2/7)) false))
  (is (= ($= 3 != (5 * 2/7)) true))
  (is (= ($= 3 == (5 * 2/7)) false))
  (is (= ($= 3 != 8 || 6 > 12) true)))

(defn matrix-products-in-infix []
  (is (m/equals ($= (matrix [1 2 3]) <*> (matrix [1 2 3])) 14))
  (is (m/equals ($= (trans (matrix [[1 2 3]])) <*> (matrix [[1 2 3]]))
                (matrix [[1 2 3] [2 4 6] [3 6 9]])))
  (is (m/equals ($= (trans [[1 2] [4 5]]) <*> (matrix [[1 2] [4 5]]))
                (matrix [[17 22] [22 29]])))
  (is (m/equals ($= (matrix [[1 2 3 4]]) <*> (trans (matrix [[1 2 3 4]])))
                (matrix [[30.0]])))
  (is (m/equals ($= (matrix [1 2 3 4]) <*> (matrix [1 2 3 4]))
                (matrix 30.0)))
  (is (m/equals ($= (trans (matrix [[1 2 3 4]])) <*> (matrix [[1 2 3 4]]))
                (matrix [[1 2 3 4] [2 4 6 8] [3 6 9 12] [4 8 12 16]]))))

(defn kronecker-product-in-infix []
  (is (m/equals ($= (matrix [1 2 3]) <x> (matrix [1 2 3]))
                (matrix [1 2 3 2 4 6 3 6 9])))
  (is (m/equals ($= (matrix [[1 2 3]]) <x> (matrix [1 2 3]))
                (matrix [[1 2 3 2 4 6 3 6 9]])))
  (is (m/equals ($= (matrix [[1 2] [3 4] [5 6]]) <x> 4)
                (matrix [[4 8] [12 16] [20 24]])))
  (is (m/equals ($= (matrix [[1 2] [3 4] [5 6]]) <x> (matrix [[1 2] [3 4]]))
                (matrix [[1 2 2 4] [3 4 6 8] [3 6 4 8] [9 12 12 16] [5 10 6 12] [15 20 18 24]])))
  (is (m/equals ($= (matrix [1 2 3 4]) <x> 4)
                (matrix [4 8 12 16])))
  (is (m/equals ($= (matrix [[1 2 3 4]]) <x> 4)
                (matrix [[4 8 12 16]])))
  (is (m/equals ($= (trans (matrix [[1 2 3 4]])) <x> (matrix [[1 2 3 4]]))
                (matrix [[1 2 3 4] [2 4 6 8] [3 6 9 12] [4 8 12 16]]))))


(deftest compliance-test
  (doseq [impl [:clatrix :ndarray :persistent-vector :vectorz]]
    (m/set-current-implementation impl)
    (println (str "compliance test " impl))
    (matrix-products-in-infix)
    (kronecker-product-in-infix)))
