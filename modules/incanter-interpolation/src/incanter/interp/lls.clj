-(ns incanter.interp.lls
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

#_(do
    (require '[incanter.core :as core])
    (require '[incanter.charts :as charts])
    (let [n 20
          xs (take n (distinct (repeatedly #(rand-int (* n 2)))))
          ys (repeatedly n #(rand-int (* n 2)))
          points (map vector xs ys)
          min-x (apply min xs)
          max-x (apply max xs)
          cheb (interpolate points {:n 3})
          custom (interpolate points {:basis (fn [x] [1 (* x x)])})
          b-spline (interpolate points {:basis :b-spline :degree 3})]
      (doto (charts/function-plot cheb min-x max-x :legend true)
        (charts/add-function b-spline min-x max-x)
        (charts/add-function custom min-x max-x)
        (charts/add-points xs ys)
        (core/view))))

#_(
   (require '[incanter.core :as core])

   (def m (matrix (repeatedly 10000 #(rand-int 100)) 100))

   (def gram (mmult (trans m) m))

   (def b (matrix (range 100)))

   (defn test [decomp sol]
     (time (dotimes [_ 1000] (decomp m)))
     (let [s (decomp m)]
       (time (dotimes [_ 20000] (sol s b)))))

   (def m2 (matrix [[4 12 -16]
                    [12 37 -43]
                    [-16 -43 98]]))

   (def b2 (matrix [1 2 3]))

   (defn check [decomp sol]
     (map -
          (core/to-list (mmult (solve m2) b2))
          (core/to-list (sol (decomp m2) b2))))
   )
