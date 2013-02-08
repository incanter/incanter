(ns incanter.logger)

(def logger-atom (agent {} :error-mode :continue)) 
#_(def fields
    { ;label (init-fn [time]) (update-fn [current-value add-value]) (print-fn [value])
     :times-called {:init-fn (fn [_] 1)
                    :update-fn (fn [c _] (inc c))
                    :print-fn (fn [value] (println "Was called " value "times."))}
     :total-time   {:init-fn (fn [T] T)
                    :update-fn +
                    :print-fn (fn [value] (println "Spent " value "ms."))}
     :average      {:init-fn (fn [T] [1 T])
                    :update-fn (fn [[n old-T] T] [(inc n) (+ T old-T)])
                    :print-fn (fn [[n value]] (println "Average " (/ value n) "ms."))}
     :min          {:init-fn (fn [T] T)
                    :update-fn min
                    :print-fn (fn [value] (println "Min time spent: " value "ms."))}
     :max          {:init-fn (fn [T] T)
                    :update-fn max
                    :print-fn (fn [value] (println "Max time spent" value "ms."))}})

(def fields
  { ;label (init-fn [time]) (update-fn [current-value add-value]) (print-fn [value])
   :average      {:init-fn (fn [T] [1 T])
                  :update-fn (fn [[n old-T] [_ T]] [(inc n) (+ T old-T)])
                  :print-fn (fn [[n value]] ["Average " (/ value n)])}
   :total-time   {:init-fn (fn [T] T)
                  :update-fn +
                  :print-fn (fn [value] ["Total" value])}})

(defn update-log [log handle T]
  (merge-with
   (fn [val-current val-new]
     (into {}
           (for [[label {:keys [update-fn]}] fields]
             [label (update-fn (val-current label) (val-new label))]))) ; updates all values mentioned in 'fields
   log
   {handle (into {} (for [[label {init :init-fn}] fields]
                      [label (init T)]))}))

(defmacro log-time [handle body]
  `(let [start# (. System (nanoTime))
         ret# ~body
         T# (/ (double (- (. System (nanoTime)) start#)) 1000000.0)]
     (send logger-atom update-log ~handle T#)
     ret#))

(defn reset-logger []
  (send logger-atom empty))


(defn print-time-stats
  ([]
     (let [state @logger-atom] (doseq [l (keys state)] (print-time-stats l))) )
  ([label]
     (let [state (@logger-atom label)
           format (str "%-10s: %.3f ms.\n")]
       (println)
       (println label "stats:")
       (doseq [[param value] state]
         (let [[name num] ((get-in fields [param :print-fn]) value)]
           (printf format name num))))))