(ns ^{:skip-wiki true}
    incanter.backstage.zoo-commons)

(defn partialsums [start coll]
  (lazy-seq
    (if (seq coll)
          (cons start (partialsums (+ start (first coll)) (rest coll)))
          (list start))))
