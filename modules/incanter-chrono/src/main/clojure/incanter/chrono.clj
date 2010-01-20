(ns #^{:doc "
       chrono.clj --- Because calling it date-utils would be boring.

       Use the date function to create dates. You can look up components
       much like you would in a map:
      
      (def my-date (date 2009 2 27 12 34 56))
      
      (my-date :year)   ;; 2009
      (my-date :month)  ;; 2
      (my-date :day)    ;; 27
      (my-date :hour)   ;; 12
      (my-date :minute) ;; 34
      (my-date :second) ;; 56
      
       You may omit the time if you like:
      
      (def my-other-date (date 2009 2 27))
      (my-other-date :hour) ;; 0
      
       To get a date relative to another date, use earlier and later:
      
      (earlier my-date 100 :minute) ;; 2009 2 27 10:54:56
      (later my-other-date 10 :day) ;; 2009 3 9
      
       For comparing dates, use earlier? and later?:
      
      (earlier? my-date my-other-date) ;; false
      (later? (later my-date 10 :day) my-date) ;; true
      
       You can see the time between two dates by calling time-between:
      
      (time-between my-other-date (date 2009 2 25) :days) ;; 2
      
      The date-seq function returns a lazy seq of dates incrementing by
      the units in its first arg starting from its second arg. The third
      arg if given dictates the end of the sequence.
      
      (date-seq :hours my-other-date my-date) ;; (a seq of twelve hours)
      (take 4 (date-seq :years my-date))
      ;; (date 2009 2 27 12 34 56) (date 2010 2 27 12 34 56)
      ;; (date 2011 2 27 12 34 56) (date 2012 2 27 12 34 56) [...]
      
       For converting between strings and dates, use format-date and
       parse-date
      
      (format-date my-date :short-date-time) ;; 2/27/09 12:34 PM
      (format-date my-other-date :long-date) ;; February 27, 2009
      (parse-date \"12/25/09\" :short-date) ;; (date 2009 12 25)
      (parse-date \"January 1, 2008 1:45:23 PM EST\" :long-date-time)
      ;; (date 2008 1 1 13 45 23)
      
      Supported date formats are:
        iso8601
        short-date
        medium-date
        long-date
        full-date
        short-date-time
      
      Both format-date and parse-date also support a string for the
      format argument, which will use the string as the format for a
      SimpleDateFormat (see the javadocs of that class for how to write
      these formats).
      
      See test_contrib/chrono.clj for more details.
      
       TODO:
      
      * Timezones
      * More support for weeks
      * Various others scattered through code
      
      "
       :author "Matt Moriarity, Phil Hagelberg, and Bradford Cross"}
  incanter.chrono
  (:use [incanter.internal :only maybe?])
  (:import (java.util Calendar TimeZone Date GregorianCalendar)
	   (java.sql Timestamp)
           (org.joda.time DateTime DateTime$Property DateTimeZone 
                          Minutes Hours Period Interval)
	   (org.joda.time.format ISODateTimeFormat DateTimeFormatter)))

(def #^{:doc "Conversion of unit keywords to Calendar units"}
     units-to-calendar-units
     {:year Calendar/YEAR,
      :month Calendar/MONTH,
      :day Calendar/DATE,
      :hour Calendar/HOUR_OF_DAY,
      :minute Calendar/MINUTE,
      :second Calendar/SECOND,
      :dayofweek Calendar/DAY_OF_WEEK})

(def #^{:doc "Number of seconds in each unit"}
     units-in-seconds
     {:year 31557600,
      :month 2592000,
      :week 604800,
      :day 86400,
      :hour 3600,
      :minute 60,
      :second 1,
      :millisecond 0.001})

;;--------------------------
;; Constants & Dispatch fn
;;--------------------------

(def default-format :basic-date)

(def formatters
     {:basic-date (ISODateTimeFormat/basicDate)
      :basic-date-time (ISODateTimeFormat/basicDateTime)
      :basic-date-time-no-ms (ISODateTimeFormat/basicDateTimeNoMillis)
      :basic-ordinal-date (ISODateTimeFormat/basicOrdinalDate)
      :basic-ordinal-date-time (ISODateTimeFormat/basicOrdinalDateTime)
      :basic-ordinal-date-time-no-ms (ISODateTimeFormat/basicOrdinalDateTimeNoMillis)
      :basic-time (ISODateTimeFormat/basicTime)
      :basic-time-no-ms (ISODateTimeFormat/basicTimeNoMillis)
      :basic-t-time (ISODateTimeFormat/basicTTime)
      :basic-t-time-no-ms (ISODateTimeFormat/basicTTimeNoMillis)
      :basic-week-date (ISODateTimeFormat/basicWeekDate)
      :basic-week-date-time (ISODateTimeFormat/basicWeekDateTime)
      :basic-week-date-time-no-ms (ISODateTimeFormat/basicWeekDateTimeNoMillis)
      :date (ISODateTimeFormat/date)
      :date-element-parser (ISODateTimeFormat/dateElementParser)
      :date-hour (ISODateTimeFormat/dateHour)
      :date-hour-minute (ISODateTimeFormat/dateHourMinute)
      :date-hour-minute-second (ISODateTimeFormat/dateHourMinuteSecond)
      :date-hour-minute-second-fraction (ISODateTimeFormat/dateHourMinuteSecondFraction)
      :date-hour-minute-second-ms (ISODateTimeFormat/dateHourMinuteSecondMillis)
      :date-opt-time (ISODateTimeFormat/dateOptionalTimeParser)
      :date-parser (ISODateTimeFormat/dateParser)
      :date-time (ISODateTimeFormat/dateTime)
      :date-time-no-ms (ISODateTimeFormat/dateTimeNoMillis)
      :date-time-parser (ISODateTimeFormat/dateTimeParser)
      :hour (ISODateTimeFormat/hour)
      :hour-minute (ISODateTimeFormat/hourMinute)
      :hour-minute-second (ISODateTimeFormat/hourMinuteSecond)
      :hour-minute-second-fraction (ISODateTimeFormat/hourMinuteSecondFraction)
      :hour-minute-second-ms (ISODateTimeFormat/hourMinuteSecondMillis)
      :local-date-opt-time (ISODateTimeFormat/localDateOptionalTimeParser)
      :local-date (ISODateTimeFormat/localDateParser)
      :local-time (ISODateTimeFormat/localTimeParser)
      :ordinal-date (ISODateTimeFormat/ordinalDate)
      :ordinal-date-time (ISODateTimeFormat/ordinalDateTime)
      :ordinal-date-time-no-ms (ISODateTimeFormat/ordinalDateTimeNoMillis)
      :time (ISODateTimeFormat/time)
      :time-element-parser (ISODateTimeFormat/timeElementParser)
      :time-no-ms (ISODateTimeFormat/timeNoMillis)
      :time-parser (ISODateTimeFormat/timeParser)
      :t-time (ISODateTimeFormat/tTime)
      :t-time-no-ms (ISODateTimeFormat/tTimeNoMillis)
      :week-date (ISODateTimeFormat/weekDate)
      :week-date-time (ISODateTimeFormat/weekDateTime)
      :week-date-time-no-ms (ISODateTimeFormat/weekDateTimeNoMillis)
      :weekyear (ISODateTimeFormat/weekyear)
      :weekyear-week (ISODateTimeFormat/weekyearWeek)
      :weekyear-week-day (ISODateTimeFormat/weekyearWeekDay)
      :year (ISODateTimeFormat/year)
      :year-month (ISODateTimeFormat/yearMonth)
      :year-month-day (ISODateTimeFormat/yearMonthDay)
      })

(def period-fns
     {:ms #(Period/millis %)
      :second #(Period/seconds %)
      :minute #(Period/minutes %)
      :hour #(Period/hours %)
      :day #(Period/days %)
      :month #(Period/months %)
      :year #(Period/years %)})

(def time-keys
     [:year :month :day :hour :minute :second :ms])

(def default-time-map 
     {:year 1970
      :month 1
      :day 1
      :hour 0
      :minute 0
      :second 0
      :ms 0})

(defn- to-ms-dispatch
  [& params]
  (let [lead-param (first params)]
    (cond
     (empty? params) ::empty
     (nil? lead-param) ::nil
     (instance? java.util.Calendar lead-param) ::calendar
     (map? lead-param) ::map
     true (class lead-param))))

;;------------------------------
;; Long Conversion Multmethod
;;------------------------------
(defmulti to-ms to-ms-dispatch)

(defmethod to-ms Long
  [& params]
  (first params))

(defmethod to-ms Date
  [& params]
  (.getTime (first params)))

(defmethod to-ms Timestamp
  [& params]
  (.getTime (first params)))

(defmethod to-ms ::calendar
  [& params]
  (to-ms (.getTime (first params))))

(defmethod to-ms DateTime
  [& params]
  (.getMillis (first params)))

(defmethod to-ms ::map
  [& params]
  (let [input-map (first params)
	resulting-map (merge default-time-map input-map)
	[y mo d h mi s ms] ((apply juxt time-keys)
			    resulting-map)]
    (to-ms (DateTime. y mo d h mi s ms))))

(defmethod to-ms String
  [& params]
  (if (= (count params) 1)
    (to-ms (first params) default-format)
    (to-ms (.parseDateTime (formatters (second params)) (first params)))))

(defmethod to-ms ::empty
  [& params]
  (to-ms (Date. )))

(defmethod to-ms ::nil
  [& params]
  nil)

;;--------------------
;; Dispatched fns
;;--------------------
(defn date [& params]
  (Date. (apply to-ms params)))

(defn greg-cal [& params]
  (doto (GregorianCalendar. )
    (.setTime (apply date params))))

(defn sql-ts [& params]
  (Timestamp. (apply to-ms params)))

(defn joda [& params]
  (DateTime. (apply to-ms params)))

(defn time-map [& params]
  (let [time-extractor (juxt :year 
			     :monthOfYear 
			     :dayOfMonth 
			     :hourOfDay
			     :minuteOfHour
			     :secondOfMinute
			     :millisOfSecond)
	joda-bean   (bean (apply joda params))]
    (zipmap time-keys (time-extractor joda-bean))))

(defn str-time [& params]
  (cond
    (zero? (count params)) (str-time (to-ms))
    (keyword? (first params)) (str-time (to-ms) (first params))
    (= (count params) 1) (str-time (first params) default-format)
    true (.print (formatters (second params)) (joda (first params)))))

;;--------------------
;; String Helpers
;;--------------------
(defn display-formats []
  (let [an-instant (to-ms)
	;Ignore the parsers that are for input only
	input-only #{:date-element-parser 
		     :date-opt-time
		     :date-parser 
		     :date-time-parser
		     :local-date-opt-time
		     :local-date 
		     :local-time}]
    (->> formatters
	 keys
	 (remove input-only)
	 (map #(str (name %) "\t"  (str-time an-instant %)))
	 sort
	 (interpose "\n")
	 (apply str)
	 println)))

;;-------------------
;; Time Zone Stuff
;;-------------------
(defn tz 
  "Creates a Joda Time Zone"
  ([offset] (DateTimeZone/forOffsetHours offset)))

(def utc (DateTimeZone/UTC))

(defn switch-tz
  "Switches an instant to a different time zone"
  [d zone]
  (.toDateTime (joda d) zone))


;;-------------------
;; Predicates
;;-------------------

(defn compare-time
  "Compares 2 times a and b."
  [a b]
  (.compareTo (date a) (date b)))
 
(defn before?
  "Tests to determine if time a is before time b"
  [a b]
  (= (compare-time a b) -1))
 
(defn after?
  "Tests to determine if time a is after time b"
  [a b]
  (= (compare-time a b) 1))

(defn valid-range? 
  "Tests to determine if the range is valid, i.e.
start is before finish." 
  [[start end]] (before? start end))

(defn is-within? 
  "Tests to see if a date d is within a range specified
by start and end"
  [d [start end]]
  (and (before? start d)
       (before? d end)))

(defn are-overlapping? 
  "Tests to see if two ranges are overlapping.  i.e. 
each range is valid and the start of range 2 is before
the end of range 1."
  [[start-1 end-1] [start-2 end-2]]
  (and (valid-range? start-1 end-1)
       (valid-range? start-2 end-2)
       (before? start-2 end-1)))

;;--------------------
;; Relative functions
;;--------------------
(defn period
  "This returns a period n units long."
  [n unit]
  ((unit period-fns) n))

(defn period-between
  "This find the period between start and end"
  [start end]
  (Period. (to-ms start) (to-ms end)))

(defn later
  "This returns a date later by a-period"
  [a-date a-period]
  (.plus (joda a-date) a-period))

(defn earlier
  "This returns a date earlier by a-period"
  [a-date a-period]
  (.minus (joda a-date) a-period))

;;--------------------
;; Interval Utils
;;--------------------
(defn start-of
  "This returns a the start of field for a given time
t.  If t is not provided, now is assumed."
  ([field] (start-of (to-ms) field))
  ([t field]
     (let [fields (take-while (complement (hash-set field)) time-keys)
	   fields (set (drop (inc (count fields)) time-keys))
	   start-point (into {} (filter (comp fields key) default-time-map))]
       (merge (time-map t) start-point ))))

(defn end-of
  "Return a date at the end of the month, year, day, etc. from the-date."
  ([unit] (end-of (to-ms) unit))
  ([the-date unit]
  (earlier (later (start-of the-date unit) (period 1 unit)) (period 1 :second))))

(defn date-seq
  "Returns a lazy seq of dates starting with from up until to in
  increments of units. If to is omitted, returns an infinite seq."
  ([units from to]
     (lazy-seq
       (when (or (nil? to) (earlier? from to))
         (cons from (date-seq units (later from units) to)))))
  ([units from] (date-seq units from nil)))