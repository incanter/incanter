;;; This is pretty much a straight port from PAIP

(ns examples.patmatch
  (:use clojure.contrib.test-is
        clojure.contrib.seq-utils))

(def fail nil)
(def match)

(with-test
    (defn match-variable [var in bindings]
      (let [b (bindings var)]
        (cond (nil? b) (assoc bindings var in)
              (= in b) bindings
              :else fail)))
  (is (= '{?x 1} (match-variable '?x 1 {})))
  (is (= '{?x 1} (match-variable '?x 1 '{?x 1})))
  (is (= fail (match-variable '?x 2 '{?x 1})))
  (is (= '{?x 1 ?y 2} (match-variable '?x 1 '{?y 2}))))

(with-test
    (defn variable? [x]
      (and (symbol? x)
           (.startsWith (name x) "?")))
  (is (variable? '?x))
  (is (variable? '?y))
  (is (variable? '?whatev))
  (is (not (variable? 'x)))
  (is (not (variable? 'whatev))))

(defmacro with-vars [vars & body]
  `(binding [variable? (fn [x#] (some #{x#} '~vars))]
     ~@body))

(defn match-is [[var pred] in bindings]
  (let [new-bindings (match var in bindings)]
    (if (or (= new-bindings fail)
            (not ((resolve pred) in)))
      fail
      new-bindings)))

(defn match-or [pats in bindings]
  (if (nil? pats)
    fail
    (let [new-bindings (match (first pats) in bindings)]
      (if (= new-bindings fail)
        (match-or (rest pats) in bindings)
        new-bindings))))

(defn match-and [pats in bindings]
  (cond (= bindings fail) fail
        (nil? pats) bindings
        :else (match-and (rest pats)
                         in
                         (match (first pats)
                                in
                                bindings))))

(defn match-not [pats in bindings]
  (if (match-or pats in bindings)
    fail
    bindings))

(def single-matchers
     {'?is match-is
      '?or match-or
      '?and match-and
      '?not match-not})

(with-test
    (defn position
      ([val coll]
         (some (fn [[i e]] (when (= e val) i))
               (indexed coll)))
      ([val coll start]
         (+ start (position val (drop start coll)))))
  (is (= 2 (position 'x '(a b x c d))))
  (is (= 2 (position 'x '(x b x c d) 1)))
  (is (= 0 (position 'x '(x b x c d)))))

(defn first-match-pos [pat in start]
  (if (and (not (coll? pat)) (not (variable? pat)))
    (position pat in start)
    (if (<= start (count in))
      start)))

(defn segment-match*
  ([pat in bindings] (segment-match* pat in bindings 0))
  ([pat in bindings start]
     (let [[[_ v] & p] pat]
       (if p
         (if-let [pos (first-match-pos (first p) in start)]
           (if-let [b2 (match p (drop pos in)
                              (match-variable v (take pos in)
                                              bindings))]
             b2
             (recur pat in bindings (inc pos))))
         (match-variable v in bindings)))))

(defn segment-match+ [pat in bindings]
  (segment-match* pat in bindings 1))

(defn segment-match? [[[_ v] & pat] in bindings]
  (or (match (cons v pat) in bindings)
      (match pat in bindings)))

(def segment-matchers
     {'?* segment-match*
      '?+ segment-match+
      '?? segment-match?
      ;; '?if match-if
      })

(defn single-pattern? [pat]
  (and (coll? pat)
       (single-matchers (first pat))))

(defn single-match [pat in bindings]
  ((single-matchers (first pat))
   (rest pat) in bindings))

(defn segment-pattern? [pat]
  (and (coll? pat) (coll? (first pat))
       (symbol? (first (first pat)))
       (segment-matchers (first (first pat)))))

(defn segment-match [pat in bindings]
  ((segment-matchers (first (first pat)))
   pat in bindings))

(defn match
  ([pat exp] (match pat exp {}))
  ([pat exp bindings]
     (cond (= bindings fail) fail
           (variable? pat) (match-variable pat exp bindings)
           (and (not (coll? pat))
                (not (coll? exp))
                (= pat exp))
           bindings
           (segment-pattern? pat) (segment-match pat exp bindings)
           (single-pattern? pat) (single-match pat exp bindings)
           (and (coll? pat) (coll? exp))
           (recur (rest pat) (rest exp)
                  (match (first pat) (first exp) bindings))
           :else fail)))

(defn expand-abbrevs [pat abbrevs]
  (if-let [exp (abbrevs pat)]
    exp
    (if (coll? pat)
      (cons (expand-abbrevs (first pat) abbrevs)
            (expand-abbrevs (rest pat) abbrevs))
      pat)))

(defn substitute [exp bindings]
  (or (bindings exp)
      (if (coll? exp)
        (cons (substitute (first exp) bindings)
              (substitute (rest exp) bindings))
        exp)))

(defn translate-by-rules [exp rules actfn]
  (some #(if-let [res (match (first %) exp)]
           (actfn (second %) res))
        rules))

(run-tests)

