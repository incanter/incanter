;;; som.clj -- Self-Organizing-Map Neural Network Library

;; by David Edgar Liebke http://incanter.org
;; June 13, 2009

;; Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.htincanter.at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.



(ns ^{:doc "Self-Organizing-Map Neural Network Library."}
    incanter.som
  (:use [incanter.core :only (sel ncol nrow mult div plus minus trans to-vect sqrt sum pow)]
        [incanter.stats :only (mean principal-components covariance)]))



(defn- som-dimensions
  ([pc1-sd pc2-sd]
    (let [dim-1 (mult 5 pc1-sd)
          dim-2 (mult (div pc2-sd pc1-sd) dim-1)]
      [dim-1 dim-2])))


(defn- som-initialize-linear
  ([data]
    (let [pc (principal-components (covariance data))
          pc1-sd (nth (:std-dev pc) 0)
          pc2-sd (nth (:std-dev pc) 1)
          pc1 (sel (:rotation pc) :cols 0)
          pc2 (sel (:rotation pc) :cols 1)
          [dim-1 dim-2] (map #(Math/ceil %) (som-dimensions pc1-sd pc2-sd))
          data-mean (map mean (trans data))
          weight-fn (fn [i j data-mean pc1-sd dim-1 dim-2 pc1 pc2]
                      (to-vect
                        (plus data-mean
                          (mult (mult 5 pc1-sd)
                                (plus (mult pc1 (minus i (div dim-1 2)))
                                      (mult pc2 (minus j (div dim-2 2))))))))
          weights (reduce conj {}
                          (for [i (range dim-1) j (range dim-2)]
                            {[i j] (weight-fn i j data-mean pc1-sd dim-1 dim-2 pc1 pc2)} ))
          sets (reduce conj {}
                       (for [i (range dim-1) j (range dim-2)]
                         {[i j] #{}} ))]
      {:dims [dim-1 dim-2]
       :weights weights
       :sets sets
       :data-means data-mean})))


(defn- dist-euclidean [x y] (sqrt (sum (pow (minus x y) 2))))


(defn- get-distances
  ([x som]
    (reduce conj {}
            (pmap #(hash-map % (dist-euclidean x ((:weights som) %)))
                  (keys (:weights som))))))


(defn- get-min-dist
  ([x som]
    (let [distances (get-distances x som)
          min-dist-key (reduce #(if (<= (distances %1) (distances %2)) %1  %2)
                               (keys distances))]
      {:index min-dist-key :dist (distances min-dist-key)} )))


(defn- som-update-cells
  ([data som]
    (let [sets (loop [i 0 sets {}]
                 (if (= i (nrow data))
                   sets
                   (let [{idx :index min-dist :dist} (get-min-dist (trans (nth data i)) som)]
                     (recur (inc i) (assoc sets idx (conj (sets idx) i))))))]
      (assoc som :sets sets))))


(defn- alpha-fn
  ([r total-cycles alpha-init]
    (max 0.01 (* alpha-init (- 1 (/ r total-cycles))))))


(defn- beta-fn
  ([r beta-init]
    (max 0 (- beta-init r))))


(defn- som-neighborhoods
  ([r dim-1 dim-2 total-cycles beta0]
    (reduce conj {}
            (for [i (range dim-1) j (range dim-2)]
              [[i j]
               (for [s1 (range (if (pos? (- i (beta-fn r beta0))) (- i (beta-fn r beta0)) 0)
                               (if (<= (+ i (beta-fn r beta0)) dim-1) (+ i (beta-fn r beta0) 1) dim-1))
                     s2 (range (if (pos? (- j (beta-fn r beta0))) (- j (beta-fn r beta0)) 0)
                               (if (<= (+ j (beta-fn r beta0)) dim-2) (+ j (beta-fn r beta0) 1) dim-2))]
                 [s1 s2])]))))



(defn- som-update-weights [r total-cycles som alpha-init beta-init]
  (let [sets (:sets som)
        weights (:weights som)
        indices (keys weights)
        dims (:dims som)
        neighborhoods (som-neighborhoods r (first dims) (second dims) total-cycles beta-init)]
    (assoc som :weights
           (reduce conj {}
             (pmap (fn [indx]
                       {indx
                         (plus (weights indx)
                               (mult (alpha-fn r total-cycles alpha-init)
                                     (minus (if (pos? (count (sets indx)))
                                              (div (sum (sets indx))
                                                   (count (sets indx)))
                                              0)
                                            (weights indx))))} ) indices)))))


(defn- som-fitness
  ([data som]
    (/ (sum (for [indx (keys (:weights som))]
            (sum (map #(dist-euclidean ((:weights som) indx) (trans (nth data %)))
                      ((:sets som) indx)))))
       (nrow data))))


(defn som-batch-train
  "
  Performs BL-SOM (batch-learning self organizing map) learning on
  the given data, returning a hashmap containing resulting BL-SOM
  values.


  Arguments:
    data -- data matrix

  Options:
    :cycles -- number of cycles of learning
    :alpha -- initial value of alpha learning parameter
    :beta -- initial value of beta learning parameter


  Returns: A hashmap containing the following fields:

    :fit -- array of fitness values for each cycle of SOM learning
    :weights -- hashmap of weight vectors, keyed by lattice indices
    :sets -- hashmap mapping data elements to lattice nodes
             (key lattice index) (value list of row indices from data)
    :dims -- dimensions of SOM lattice
    :data-means -- column means of input data matrix


  Examples:

    (use '(incanter core som stats charts datasets))
    (def data (to-matrix (sel (get-dataset :iris)
                           :cols [\"Sepal.Length\" \"Sepal.Width\" \"Petal.Length\" \"Petal.Width\"])))

    (def som (som-batch-train data :cycles 10 :alpha 0.5 :beta 3))

    ;; plot the fitness for each cycle of training
    (view (xy-plot (range (count (:fit som))) (:fit som)))
    ;; view indices of data items in each cell
    (:sets som)
    ;; view the species in each cell
    (doseq [rws (vals (:sets som))]
      (println (sel (get-dataset :iris) :cols \"Species\" :rows rws) \\newline))

    ;; plot the means of the data vectors in each cell/cluster
    (def cell-means (map #(map mean (trans (sel data :rows ((:sets som) %)))) (keys (:sets som))))
    (def x (range (ncol data)))
    (doto (xy-plot x (first cell-means))
          view
          (add-lines x (nth cell-means 1))
          (add-lines x (nth cell-means 2)))


  References:

    http://en.wikipedia.org/wiki/Self-organizing_map
  "
  ([data & {:keys [alpha beta cycles]
            :or {alpha 0.5
                 beta 3
                 cycles 10}}]
    (loop [r 1 som (som-initialize-linear data) fit []]
      (if (= r cycles)
        (assoc som :fit fit)
        (let [new-som (som-update-weights r cycles (som-update-cells data som)
                                          alpha beta)]
          (recur (inc r) new-som (conj fit (som-fitness data new-som))))))))
