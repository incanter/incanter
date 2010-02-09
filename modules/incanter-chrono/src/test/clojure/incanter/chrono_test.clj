(ns incanter.chrono-test
  (:use clojure.test)
  (:use :reload-all incanter.chrono))

(def christmas (joda-date 2007 12 25, 3 00 02))
(def new-years (joda-date 2008 1 1))
(def day-one   (joda-date 2008 11 21, 11 21 48))

(deftest test-date-creation-lookup
  (are [field expected] (= expected ((time-map day-one) field))
       :year 2008
       :month 11
       :day 21
       :hour 11
       :minute 21
       :second 48
       :ms 0))

(deftest test-map-creation
  (is (= day-one
	 (joda-date
	  {:year 2008
	   :month 11
	   :day 21
	   :hour 11
	   :minute 21
	   :second 48
	   :ms 0}))))

(deftest test-int-vec
  (is (= (int-vec day-one)
	 [2008 11 21 11 21 48 0])))

(deftest test-equality
  (is (= (date 2009 3 2)
         (date 2009 3 2)))
  (is (not (= 25 christmas)))
  (is (not (= christmas 25))))

(deftest test-to-string
  (is (= "2007-12-25 03:00:02" (.toString christmas))))

(deftest test-later
  (is (= (date 2009 11 21 11 21 48)
         (later day-one 1 :year)))
  (is (= (date 2008 10 21 11 21 48)
         (later day-one -1 :month)))
  (is (= (date 2008 11 24 11 21 48)
         (later day-one 3 :day)))
  (is (= (date 2008 11 21 12 21 48)
         (later day-one 1 :hour)))
  (is (= (date 2008 11 21 13 1 48)
         (later day-one 100 :minute)))
  (is (= (date 2008 11 21 11 21 49)
         (later day-one 1 :second)))
  (is (= (later christmas 1 :day)
         (later christmas 1 :day)))
  (let [party (date 2007 12 31, 22 0 0)
        later-party (date 2007 12 31, 23 0 0)]
    (is (= later-party (later party :hour)))))

(deftest test-earlier
  (is (= (date 2008 8 13 11 21 48)
         (earlier day-one 100 :day)))
  (is (= (date 2008 11 23 11 21 48)
         (earlier day-one -2 :day)))
  (is (= (date 2008 11 21 9 21 48)
         (earlier day-one 2 :hour))))

(deftest test-earlier?
  (is (earlier? (date 2008 12 12)
                (date 2009 12 12))))

(deftest test-later?
  (is (later? (date 2008 12 31)
              (date 2009 1 1))))

(deftest test-start-of
  (is (= (date 2007 12 1)  (start-of christmas :month)))
  (is (= (date 2007 12 25) (start-of christmas :day))))

(deftest test-end-of
  (is (= (date 2007 12 31, 23 59 59) (end-of christmas :month)))
  (is (= (date 2007 12 25, 23 59 59) (end-of christmas :day)))
  (is (= (date 2007 12 25,  3 59 59) (end-of christmas :hour))))

(deftest valid-range-test
  (let [start (joda-date "2009-06-10T08:45:27Z")
        end   (joda-date "2009-06-10T09:45:27Z")]
  (is (valid-range? [start end]))
  (is (earlier? start end))
  (is (not (valid-range? [end start])))
  (is (false? (valid-range? [start nil])))))

(deftest are-overlapping-test
  (let [start  (joda-date "2009-06-10T08:45:27Z")
        end    (joda-date "2009-06-10T09:45:27Z")
        start1 (joda-date "2009-06-10T08:55:27Z")
        end1   (joda-date "2009-06-10T09:45:27Z")]
    (is (true? 
         (are-overlapping? [start end] [start1 end1])))
    (is (true? 
         (are-overlapping? [start1 end1] [start end])))
    (is (false?
         (are-overlapping? [start end] [(joda-date "2009-06-11T08:45:27Z") (joda-date "2009-06-11T08:45:27Z")])))
    (is (false?
        (are-overlapping? [start end] [start1 nil])))))

(deftest is-within-date-range
  (let [in  (joda-date "2009-06-10T08:45:27Z")
        out (joda-date "2009-06-10T9:27:27Z")
        s (joda-date "2009-06-10T08:27:27Z")
        e (joda-date "2009-06-10T09:27:27Z")]
    (is (= true
           (is-within? in [s e])))
    (is (= false
           (is-within? out [s e])))
    (is (thrown? java.lang.IllegalArgumentException
                 (is-within? in [e s])))
    ;;notice that nil seems to resovle to infinite creates unbounded ranges
    (is (= true
           (is-within? in [s nil])))
    (is (= false
           (is-within? in [e nil])))))

(deftest test-date-seq
  (is (= (list christmas
               (date 2007 12 26, 3 0 02)
               (date 2007 12 27, 3 0 02)
               (date 2007 12 28, 3 0 02)
               (date 2007 12 29, 3 0 02)
               (date 2007 12 30, 3 0 02)
               (date 2007 12 31, 3 0 02))
         (date-seq :day christmas new-years)))
  (let [party (date 2007 12 31, 22 0 0)
        party2 (date 2007 12 31, 23 0 0)
        the-seq (date-seq :hour party new-years)]
    (is (= (list party party2)
           (take 2 the-seq)))))

(deftest secs-between-test
  (let [start (joda-date "2009-06-10T08:45:27Z")
        end   (joda-date "2009-06-10T09:45:27Z")]
    (is (= 3600  (secs-between start end)))
    (is (= -3600 (secs-between end start)))))

(deftest minutes-between-test
  (let [start (joda-date "2009-06-10T08:45:27Z")
        end   (joda-date "2009-06-10T09:45:27Z")]
    (is (= 60  (minutes-between start end)))
    (is (= -60 (minutes-between end start)))))

(deftest hours-between-test
  (let [start (joda-date "2009-06-10T08:45:27Z")
        end   (joda-date "2009-06-10T09:45:27Z")]
    (is (= 1  (hours-between start end)))
    (is (= -1 (hours-between end start)))))

(deftest days-between-test
  (let [start (joda-date "2009-06-10T08:45:27Z")
        end   (joda-date "2009-06-17T08:45:27Z")]
    (is (= 7  (days-between start end)))
    (is (= -7 (days-between end start)))))

(deftest weeks-between-test
  (let [start (joda-date "2009-06-10T08:45:27Z")
        end   (joda-date "2009-06-17T08:45:27Z")]
    (is (= 1  (weeks-between start end)))
    (is (= -1 (weeks-between end start)))))

;;---------------------------------------
;; FAIL - The tests below fail.  Problem?
;; These fns don't exist in Sean's Design...
;;---------------------------------------
(comment
  ;;Recommend killing time-between, use specific *-between fns
  ;;instead. - SFD
(deftest test-time-between
  ;; Seconds is the default unit
  (is (= 5 (time-between (date 2009 1 1, 10 10 10)
			 (date 2009 1 1, 10 10 15))))
  (is (= 10 (time-between (date 2009 1 1, 10 10 10)
                          (date 2009 1 1, 10 20 10)
                          :minutes)))
  (is (= 6 (int (time-between christmas new-years :day)))))

;; Completely deprecated by advanced parsing methods. See display-formats
;; in action to understand why. - SFD
(deftest test-iso-date-format
  (is (= (date 2008 12 25) (parse-date "2008-12-25 00:00:00" :iso8601)))
  (is (= "2008-11-21 11:21:48" (format-date day-one :iso8601))))

  ;;Killed - Could be fixed.  Should it? date-seq does job - SFD
(deftest create-date-range-around-a-date
  (let [you (joda-date 2009 6 5 11 0 0 0 (time-zone 0))]
  (is (= (list (hours-from you -1) you (hours-from you 1))
         (hours-around (range -1 2) you)))))
)
;(run-tests)