;;; Wrapper for maven running tests.

(ns incanter.mvn_tests
  (:require [incanter.tests]))

(System/setProperty "incanter.home" ".")
(incanter.tests/run)
