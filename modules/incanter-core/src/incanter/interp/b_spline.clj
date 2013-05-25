(ns ^{:skip-wiki true}
    incanter.interp.b-spline
  (:require [incanter.interp.utils :refer (binary-search)]))

(defn- calc-Ns [ts m t k]
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

(defn calc-Ns-and-k-fn
  "Returns function that calculates non zero Ns and returns Ns and index of last non zero N in form [Ns k]"
  [n degree]
  (let [ts-inner (mapv #(/ % (double n)) (range 0 (inc n)))
        ; ts is a knot vector. Example: [0 0 0 1/5 2/5 3/5 4/5 1 1 1]
        ts (vec (concat
                 (repeat degree 0)
                 ts-inner
                 (repeat degree 1)))]
    (fn [t]
      (let [k (+ (min (dec n)
                      (binary-search ts-inner t))
                 degree)
            Ns (calc-Ns ts degree t k)]
        [Ns k]))))

(defn b-spline [points opts]
  (let [degree (min (:degree opts 3)
                    (dec (count points)))
        n (- (count points) degree)
        coll-vals? (coll? (first points))
        points (if coll-vals?
                 (apply map vector points)
                 (vec points))
        ns-finder (calc-Ns-and-k-fn n degree)]
    (fn [t]
      (let [[Ns k] (ns-finder t)
            Ns (vec Ns)
            calc (fn [points]
                   (let [sub-points (subvec points (- k degree) (inc k))]
                     (loop [sum (double 0)
                            ind 0]
                       (if (= (count Ns) ind)
                         sum
                         (recur (+ sum (* (sub-points ind) (Ns ind))) (inc ind))))))]
        (if coll-vals?
          (map calc points)
          (calc points))))))

(defn b-surface [grid _ _ opts]
  (let [degree (min (:degree opts 3)
                    (dec (count grid))
                    (dec (count (first grid))))
        grid (mapv vec grid)
        n (- (count grid) degree)
        m (- (count (first grid)) degree)
        x-ns-finder (calc-Ns-and-k-fn m degree)
        y-ns-finder (calc-Ns-and-k-fn n degree)]
    (fn [x y]
      (let [[x-Ns x-k] (x-ns-finder x)
            [y-Ns y-k] (y-ns-finder y)
            calc-x (fn [points]
                     (->> (subvec points (- x-k degree) (inc x-k))
                          (map * x-Ns)
                          (reduce +)))]
        (->> (subvec grid (- y-k degree) (inc y-k))
             (map calc-x)
             (map * y-Ns)
             (reduce +))))))

