(ns incanter.b-spline
  (:use [incanter.core :only (plus minus div mult)]))

(defn log [v & args]
  (apply println v args)
  v)

(defn binary-search [vec value]
  (loop [left 0
         right (dec (count vec))]
    (if (= (- right left) 1)
      (if (<= (nth vec right) value) right left)
      (let [middle (quot (+ left right) 2)]
        (if (<= (nth vec middle) value)
          (recur middle right)
          (recur left middle))))))

(defn calc-Ns [ts m t k]
  (letfn [(calc-V [i m]
            (let [t-i (nth ts i 0)
                  t-i+m (nth ts (+ i m) 1)]
              (if (zero? (- t-i t-i+m))
                0
                (/ (- t t-i) (- t-i+m t-i)))))
          (calc-N [prev-Ns i m]
            (+ (* (calc-V i m) (nth prev-Ns (- k i) 0))
               (* (- 1 (calc-V (inc i) m)) (nth prev-Ns (- k (inc i)) 0))))
          (next-level [level]
            (let [m (count level)]
              (mapv #(calc-N level % m) (range k (- k m 1) -1))))]
    (-> (iterate next-level [1]) (nth m) reverse)))

(defn b-spline [points degree]
  (let [points (vec points)
        n (- (count points) degree)
        ts-inner (mapv #(/ % (double n)) (range 0 (inc n)))
        ts (vec (concat
                 (repeat degree 0)
                 ts-inner
                 (repeat degree 1)))]
    (fn [t]
      (let [k (+ (min (dec n)
                      (binary-search ts-inner t))
                 degree)
            Ns (calc-Ns ts degree t k)]
        (->> (subvec points (- k degree) (inc k))
             (map mult Ns)
             (reduce plus))))))
