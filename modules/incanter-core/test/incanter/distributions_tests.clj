;;; symbolic-test-cases.clj -- Unit tests of Incanter functions 

;; by Mark M. Fredrickson
;; May 10 2010

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.



(ns incanter.distributions-tests
  (:use clojure.test 
        (incanter distributions)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UNIT TESTS FOR incanter.distributions.clj
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest extending-basic-types 
	(= (pdf [1 2 2] 1) 1/3)
  (= (pdf '(1 2 1 2 2 1) 2) 1/2)
	(= (support [1 2 3 2 :foo :bar]) [1 2 3 :foo :bar]))

