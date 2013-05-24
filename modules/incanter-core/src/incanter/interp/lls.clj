(ns ^{:skip-wiki true}
  incanter.interp.lls
  (:require [incanter.core :refer (solve matrix mmult trans)]
            [incanter.interp
             [utils :refer (translate-domain uniform)]
             [b-spline :as b-spline]]))

(defn cheb-basis [xs n]
  (let [mn (apply min xs)
        mx (apply max xs)
        add-cheb (fn [vals x]
                   (let [n (count vals)]
                     (conj vals (- (* 2 x (vals (- n 1)))
                                   (vals (- n 2))))))]
    (translate-domain
     (fn [x]
       (-> (iterate #(add-cheb % x) [1.0 x])
           (nth (- n 2))))
     [mn mx]
     [-1 1])))

(defn b-spline-basis [xs n degree]
  (let [mn (apply min xs)
        mx (apply max xs)
        ns-finder (b-spline/calc-Ns-and-k-fn (- n degree) degree)]
    (translate-domain
     (fn [t]
       (let [[Ns k] (ns-finder t)]
         (concat (repeat (- k degree) 0)
                 Ns
                 (repeat (- n k 1) 0))))
     [mn mx]
     [0 1])))

(defn polynomial-2d-basis [n]
  (->> (for [degree (iterate inc 0)
             x-deg (range (inc degree))
             :let [y-deg (- degree x-deg)]]
         #(* (Math/pow %1 x-deg)
             (Math/pow %2 y-deg)))
       (take n)
       (partial (fn [fns x y] (map #(% x y) fns)))))

(defn- get-basis [xs opts]
  (let [n (:n opts 4)
        basis-type (:basis opts :polynomial)]
    (if (fn? basis-type)
      basis-type
      (case basis-type
        :polynomial (cheb-basis xs n)
        :b-spline (let [degree (min (:degree opts 3)
                                    (dec n))]
                    (b-spline-basis xs n degree))
        (throw (IllegalArgumentException. (str "Unsupported basis type " basis-type)))))))

(defn- alphas-fn [basis xs]
  (let [m (matrix (map basis xs))
        m-tr (trans m)
        gram (mmult m-tr m)
        gram-inv (solve gram)]
    (fn [ys]
      (->> (matrix ys)
           (mmult gram-inv m-tr)))))

(defn interpolate [points opts]
  (let [xs (map first points)
        ys (map second points)
        basis (get-basis xs opts)
        alphas ((alphas-fn basis xs) ys)]
    (fn [x]
      (->> (basis x)
           (map * alphas)
           (reduce +)))))

(defn interpolate-parametric [points opts]
  (let [rng (:range opts [0 1] )
        ts (uniform rng (count points))
        basis (get-basis ts opts)
        coll-vals? (coll? (first points))
        points (if coll-vals?
                 (apply map vector points)
                 points)
        alph-fn (alphas-fn basis ts)
        alphas (if coll-vals?
                 (map alph-fn points)
                 (alph-fn points))]
    (fn [x]
      (let [calc #(->> (basis x)
                       (map * %)
                       (reduce +))]
        (if coll-vals?
          (map calc alphas)
          (calc alphas))))))

(defn- get-2d-basis [opts]
  (let [n (:n opts 6)
        basis-type (:basis opts :polynomial)]
    (if (fn? basis-type)
      basis-type
      (case basis-type
        :polynomial (polynomial-2d-basis n)
        (throw (IllegalArgumentException. (str "Unsupported basis type " basis-type)))))))

(defn interpolate-grid
  [grid xs ys opts]
  (let [xs (for [y ys x xs] [x y])
        ys (flatten grid)
        basis (get-2d-basis opts)
        alphas (-> (partial apply basis)
                   (alphas-fn xs)
                   (#(% ys)))]
    (fn [x y]
      (->> (basis x y)
           (map * alphas)
           (reduce +)))))
