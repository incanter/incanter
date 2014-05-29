(ns incanter.sql-tests
  (:use clojure.test 
        [incanter sql core]
        [clojure.java.jdbc :exclude [resultset-seq]]
        [clojureql.core :as q :exclude [conj! disj! take drop distinct case compile sort aggregate]]
       )
 )

;;HACK: Jack into the clojureql namespace to override the syntax of
;;the commands that are written.
(in-ns 'clojureql.core)
;;HACK: This vector is only used as a way to verify the lazy
;;testing.  It is used when *debug* is true, appending for each
;;query called.  The user must reset the atom when needed by calling
;;reset-cql-count!
(def statements-made (clojure.core/atom []))
(defmethod compile :default [tble db]
  (let [{:keys [cnx tname tcols restriction renames joins combinations
                grouped-by pre-scope scope order-by modifiers having]} tble
        aliases    (when joins (extract-aliases joins))
        aggregates (-?>> (if (table? tcols) (:tcols tcols) tcols)
                         (filter #(and (vector? %) (= 3 (count %))))
                         (map (comp name last)))
        mods       (join-str \space (map upper-name modifiers))
        combs      (if (seq combinations)
                     (for [{:keys [table mode opts]} combinations]
                       (let [[stmt & [env]] (compile table (or (:dialect cnx) :default))]
                         [(format " %s (%s)"
                                  (str (upper-name mode) (if opts (str \space (upper-name opts))))
                                  stmt) env])))
        fields     (when-not (table? tcols)
                     (str (if tcols (to-fieldlist tname tcols) "*")
                          (when (seq aliases)
                            (str ","
                                 (->> (map rest aliases)
                                      flatten
                                      (join-str ","))))))
        jdata      (when joins
                     (for [join-data joins]
                       (build-join (:dialect cnx) join-data aliases)))
        tables    (cond
                   joins
                    (str (if renames
                           (with-rename tname (map #(add-tname tname %) tcols) renames)
                           (to-tablename tname))
                         \space
                         (join-str " " (map first jdata)))
                    (table? tcols)
                    (compile tcols (or (:dialect cnx) :default))
                    :else
                    (if renames
                      (with-rename tname (map #(add-tname tname %) tcols) renames)
                      (to-tablename tname)))
        pre-order  (filter #(true? (-> % meta :prepend)) order-by)
        post-order (remove #(true? (-> % meta :prepend)) order-by)
        preds     (when restriction restriction)
        statement (clean-sql [(when combs "(")
                       "SELECT" mods (or fields "*")
                       (when tables "FROM") (if (string? tables)
                                              tables
                                              (format "(%s)" (first tables)))
                       (when preds "WHERE") (str preds)

                       (when (or (and (seq grouped-by) (not (seq combs)))
                                 (-> grouped-by first meta :prepend))
                         (str "GROUP BY " (to-fieldlist tname (first grouped-by))))
                       (when (seq having) (str "HAVING " having))
                       (when (seq pre-order)
                         (str "ORDER BY " (to-orderlist tname aggregates (first pre-order))))
                       (when-let [offset (-> pre-scope :offset)]
                         (str "OFFSET " offset " ROWS "))
                       (when-let [limit (-> pre-scope :limit)]
                         (str "FETCH NEXT " limit " ROWS ONLY "))
                       
                       (when combs
                         (->> (map first combs) (interpose \space)
                              (apply str)       (format ") %s")))

                       (when (and (seq grouped-by) (seq combs)
                                  (nil? (-> order-by first meta :prepend)))
                         (str "GROUP BY " (to-fieldlist tname (first grouped-by))))
                       (when (and (seq having) (seq combs)) (str "HAVING " having))
                       (when (seq post-order)
                         (str "ORDER BY " (to-orderlist tname :all (first post-order))))
                       (when-let [offset (-> scope :offset)]
                         (str "OFFSET " offset " ROWS "))
                       (when-let [limit (-> scope :limit)]
                         (str "FETCH NEXT " limit " ROWS ONLY "))

                       ])
        env       (concat
                   (->> [(if-let [cases (filter map? tcols)]
                           (interleave (mapcat #(mapcat (fn [clause] (:env clause)) %)
                                               (map :clauses cases))
                                       (mapcat :returns cases)))
                         (mapcat last jdata)
                         (map :else (filter map? tcols))
                         (map (comp :env second) jdata)
                         (if (table? tcols) (rest tables))
                         (if preds [(:env preds)])
                         (if having [(:env having)])]
                        flatten (remove nil?) vec)
                   (->> (mapcat rest combs)
                        (remove nil?)))
        sql-vec   (into [statement] env)]
    (when *debug*
      (do
        ;; Collect all of the statements in a vec instead of printing out.
        (swap! statements-made (partial cons sql-vec))
        ))
    sql-vec))
(in-ns 'incanter.sql-tests)

(defn in-mem-db
  ([db-name create?]
     {:classname "org.apache.derby.jdbc.EmbeddedDriver"
      :subprotocol "derby"
      :subname (str "memory:" db-name)
      :create create?
      :dialect :derby}))

(defn close-db! [db-name]
  (let [d (dissoc
           (assoc (in-mem-db db-name true) :drop true)
           :create)]
    ;; 45000 is the Derby error code for successful shutdown
    (= 45000
       (try
         (with-connection d)
         (catch Exception e
           (. e getErrorCode))))))

(defn- reset-cql-count!
  "The derby-dialect file alter the debug mode of ClojureQL. It saves
the vec of statements instead of printing.  They can be counted to
verify the lazy fetching."
  []
  (reset! clojureql.core/statements-made []))

;; Open a named memory DB do the testing, then clean up
;; afterwards.
(defmacro wrap-db-test [db-name & body]
  `(try
     (binding [*debug* true]
       (let [~'conn (in-mem-db ~db-name true)]
         (q/open-global ~'conn)
         (reset-cql-count!)
        ~@body))
     (finally (close-db! ~db-name))))
;; Helper functions and macros^^^

;; Test datasets              vvv

(def dataset-1 (dataset [:a_number :a_blob] [
     [java.lang.Math/PI  (byte-array (. "a big blob" getBytes))]
     [2.0                (byte-array (take 4097 (cycle (. "one zero" getBytes))))]]))

(def dataset-2 (dataset [:xnum :xstr :xdte] [
     ; Derby has some problems with using the plain old Date class
     [1 "Hello" (java.sql.Date. 65)]
     [2 "Goodbye" (java.sql.Date. 67)]]))

(def nulled-dataset
  (dataset
   [:cola :colb :colc]
   [[1 "a" 3]
    [2 "b" 6]
    [3 "c" 9]
    [4 "d" 12]]))

(def large-set
  (dataset
   [:a :b]
   (map #(vec (list %1 (str "X" %2))) (range 1 1000) (range 101 2000))))

(def new-large-set
  (dataset
   [:a :b]
   (map #(vec (list %1 (str "Y" %2))) (range 1 1000) (range 101 2000))))

(def dataset-j1 (dataset [:a :b] [["a" 1] ["b" 2] ["c" 3]]))
(def dataset-j2 (dataset [:b :c] [[1 "A"] [2 "B"] [3 "C"]]))

(deftest test-full-db
  (wrap-db-test
   "inmem-1"
   (with-connection conn
     (do-commands "create table TAB1 (my_id numeric)")
     (insert-dataset (dataset [:my_id] [[3] [7]]) (q/table :TAB1))
     )
   (let [dset (read-dataset (q/table :TAB1))]
     (is (dataset? dset))
     (is (= [:my_id] (:column-names dset)))
     (is (= 3M (:my_id (first (:rows dset))))))))

(deftest test-db-data-types
  (wrap-db-test
   "datatypetest"
   (do
     (with-connection conn (do-commands "create table COMPLEX1 (a_number numeric, a_blob BLOB)"))
     (insert-dataset dataset-1 (q/table :COMPLEX1))
     (let [dset (read-dataset (q/table :COMPLEX1))]
       (is (= [:a_number :a_blob] (:column-names dset)))
       (is (= 2 (count (:rows dset))))))))

(deftest test-roundtrip-1
  (wrap-db-test
   "roundtrip-1"
   (do
     (with-connection
       conn
       (do-commands "create table ROUNDTRIP (xnum numeric, xstr varchar(100), xdte date)"))

     (insert-dataset dataset-2 (q/table :ROUNDTRIP))
     (let [ret-val (read-dataset (q/table :ROUNDTRIP))]
       (is (dataset? ret-val))
       (is (= [:xnum :xstr :xdte] (:column-names dataset-2)))
       ))))

(deftest test-incanter-join
  (wrap-db-test
   "incanter-join"
   (do
     (with-connection conn
       (do-commands "create table ROUNDTRIP (xnum numeric, xstr varchar(100), xdte date)"))
     (insert-dataset dataset-2 (q/table :ROUNDTRIP))
     (let [ret-val1 (read-dataset (q/table :ROUNDTRIP))
           ret-val2 (read-dataset (q/table :ROUNDTRIP))]
       (is (dataset? ret-val1))
       (let [j ($join [[:xnum] [:xnum]] ret-val1 ret-val2)]
         (is (dataset? j))
         )))))

(deftest cql-and-nulls
  (testing "Does ClojureQL fill in all keys for each map?"
    (wrap-db-test
     "cql-null"
     (testing "Inserting the test data, adding a null value"
       (q/open-global conn)
       (with-connection
         conn
         (do-commands "create table NIL_SET (cola numeric, colb varchar(10) default null, colc numeric)")
         (insert-dataset nulled-dataset (q/table :NIL_SET))
         (insert-records "NIL_SET" {:cola 0 :colc 0})))
     (testing "Reading back via ClojureQL-style..."
       (try
         (reset-cql-count!)
         (let [query (q/table :NIL_SET)
              dset (read-dataset query)]
          (is (dataset? dset))
          (is (= (:columns nulled-dataset) (:columns dset)))
          )
         (catch Exception _ (prn @clojureql.core/statements-made)))))))

(deftest windowing-algorithm
  (testing "Lazy data windowing algorithm to pull back the required frames."
    (wrap-db-test
     "windowing"
     (q/open-global conn)
     (with-connection
       conn
       (do-commands "create table LARGE_SET (a numeric, b varchar(5))")
       (insert-dataset large-set (q/table :LARGE_SET)))
     (testing "Reading back"
       (reset-cql-count!)
       (let [query1 (-> (q/table :LARGE_SET))]
         (let [ret (read-dataset query1)]
           (is (dataset? ret))
           (let [retrows (:rows ret)]
             ;; Check the first row looks OK...
             (is (== 1                (:a (first retrows))))
             (is (= {:a 20M :b "X120"} (nth retrows 19)))

             ;; Confirm the query count:
             ;; It's not 2, because we wanted to get the population
             ;; count before.
             (is (= 3 (count @clojureql.core/statements-made)))

             ;; Now let's get something further out in the list
             (is (= {:a 65M :b "X165"} (nth retrows 64)))
             ;; And check again
             (is (= 6 (count @clojureql.core/statements-made)))

             )))))))

(deftest ql-insert
  (testing "Inserting a dataset using ClojureQL"
    (wrap-db-test
     "insert"
     (with-connection
       conn
       (do-commands
        "create table LARGE (a numeric, b varchar(10) default null, colc numeric)")
       (q/open-global conn)
       (insert-dataset large-set (q/table :LARGE))
       (let [ret (read-dataset (q/table :LARGE))]
         (is (dataset? ret))
         (is (= 999 (count (:rows ret))))
         )))))

