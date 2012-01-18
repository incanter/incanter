(ns incanter.backstage.zoo-commons-test
  (:use clojure.test 
        incanter.backstage.zoo-commons))

(def test-coll [2 5 1 3 8 0 6 7])

(deftest partialsums-test
  (is (= [11 17 12 17 21]
         (partialsums (apply + (take 4 test-coll))
                      (map   - (drop 4 test-coll) test-coll)))))