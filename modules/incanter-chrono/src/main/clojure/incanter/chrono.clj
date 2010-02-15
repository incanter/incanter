(ns #^{:doc "chrono.clj --- Because calling it date-utils would be boring.

Complete and total re-write, centered around two multimethods, joda-tz and to-joda*.
See the doc string for joda-tz to learn what type of objects can be used to create a
 time zone object.

The to-joda* fn is designed to create a Joda DateTime object based on several different
types of input.  It can dispatch on the following:

* java.util.Date (and subclasses)
* java.util.Calendar (and subclasses)
* java.lang.Long (as the ms count in the epoch)
* java.lang.Integer - with vairable arity, so that you can create from the following:
    [year month day]
    [year month day hour min sec]
    [year month day hour min sec ms]
* java.lang.String - This defaults to :basic-date-time-no-ms, but you can choose from
51 existing ISO-8601 formatters.  Use display-formats for more information.
* clojure.lang.IPersistentMap - See the constant time-keys for the proper keys to use.
* clojure.lang.IPersistentVector - Calls (apply to-joda* v) on the vector v.
* org.joda.time.DateTime - Passes the Joda DateTime object through.

All empty calls default to the instant the fn is called.

Each of this casses also takes an optional time zone value at the end.  Any time zone
represnetation joda-tz knows how to handle can be passed in.  This makes it very simple
to convert time types to a different time zone.

It is also possible to create other representations of time objects using the following
fns:

* joda-date (preffered over to-joda*)
* date
* to-sql
* greg-cal
* time-vec
* time-map
* time-str

Each of these fns wrap a call to to-joda*, so they accept the same bredth of arguments.

This library also contains several preicate & time manipulation fns.  Each fns casts its
input to the appriate time object, so you don't have to worry about converting them before
use.  Check the specific doc strnigs for more information."
       :author "Sean Devlin"}
  incanter.chrono
  (:import (java.util Calendar TimeZone SimpleTimeZone Date GregorianCalendar)
	   (java.sql Timestamp)
           (org.joda.time DateTime DateTime$Property DateTimeZone
                          Minutes Hours Period Interval)
	   (org.joda.time.tz FixedDateTimeZone CachedDateTimeZone)
	   (org.joda.time.format ISODateTimeFormat DateTimeFormatter))
  (:use clojure.template))

;;--------------------------
;; Constants
;;--------------------------

(def default-format :basic-date-time-no-ms)

(def #^{:doc "This is a map of available formatters that can be
used with date pasing.  They come from the ISO8601 standard.  Use the
fn display-formats to see all the different formats available"}
     formatters
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

(def #^{:doc "This is a vector with the keys that are expected to be
used with time map."}
     time-keys
     [:year :month :day :hour :minute :second :ms])

(def default-time-map 
     {:year 1970
      :month 1
      :day 1
      :hour 0
      :minute 0
      :second 0
      :ms 0
      :tz (.getZone (DateTime.))})

(def #^{:doc "This is a sorted set of every sting time zone represnetation avialable"
       }available-ids (apply sorted-set (DateTimeZone/getAvailableIDs)))
;;-------------------
;; Time Zone Constructor
;;-------------------
(defmulti
  #^{
     :doc "Converts the following types to a DateTimeZone

* DateTimeZone
* TimeZone
* Number (numeric offset)
* String (iso conversion)
* java.util.Calendar (based on instance tz)
* Date (based on instance tz)
* DateTime (based on instance tz)
"}
  joda-tz class)


(defmethod joda-tz DateTimeZone
  [#^DateTimeZone zone]
  zone)

(defmethod joda-tz Number
  [#^Number offset]
  (DateTimeZone/forOffsetHours offset))

(defmethod joda-tz String
  [#^String named-zone]
  (DateTimeZone/forID named-zone))

(defmethod joda-tz Calendar
  [#^Calendar cal]
  (joda-tz (.getTimeZone cal)))

(defmethod joda-tz Date
  [#^Date d]
  (joda-tz (doto (GregorianCalendar. ) (.setTime d))))

(defmethod joda-tz DateTime
  [#^DateTime d]
  (.getZone d))

(defmethod joda-tz TimeZone
  [#^TimeZone zone]
  (DateTimeZone/forTimeZone zone))

(defn java-tz
  "Accepts the same types as joda-tz, but returns a TimeZone object
instead."
  [arg]
  (.toTimeZone (joda-tz arg)))

(def tz joda-tz)
(def time-zone joda-tz)

(def utc (DateTimeZone/UTC))

;;------------------------------
;; to-joda*
;; This is the workhorse for chrono
;;------------------------------
(defmulti to-joda* (fn [a & args] (class a)))

;;Assumes that the long is the number of ms in the current epoch.
(defmethod to-joda* Long
  ([l] (DateTime. l))
  ([l zone] (DateTime. l #^DateTimeZone(tz zone))))

;;Creates the current instant at the supplied Joda Time Zone
(defmethod to-joda* DateTimeZone
  ([#^DateTimeZone zone] (DateTime. zone)))

;;Creates the current instant at the supplied Java Time Zone
(defmethod to-joda* TimeZone
  ([#^TimeZone zone] (DateTime. #^DateTimeZone(tz zone))))

;;Creates a Date object from a Calendar, recurrsively calls to-joda*.  Preserves TimeZone
(defmethod to-joda* Calendar
  ([#^Calendar cal] (to-joda* #^Date(.getTime cal) (tz cal)))
  ([#^Calendar cal zone] (to-joda* #^Date(.getTime cal) zone)))

;;Creates a Long object from a Calendar, recurrsively calls to-joda*.  Preserves TimeZone
(defmethod to-joda* Date
  ([#^Date d] (to-joda* #^Long(.getTime d) (tz d)))
  ([#^Date d zone] (to-joda* #^Long(.getTime d) zone)))

;;Passes through a Joda DateTime object, or changes tz if required.
(defmethod to-joda* DateTime
  ([#^DateTime d] d)
  ([#^DateTime d zone] (.withZone d #^DateTimeZone(tz zone))))

(defmethod to-joda* clojure.lang.IPersistentMap
  ([input-map]
     (let [resulting-map (merge default-time-map input-map)
	   [y mo d h mi s ms zone] ((apply juxt (conj time-keys :tz))
				    resulting-map)]
       (DateTime. y mo d h mi s ms (tz zone))))
    ([input-map zone]
     (let [resulting-map (merge default-time-map input-map)
	   [y mo d h mi s ms] ((apply juxt time-keys) resulting-map)]
       (DateTime. y mo d h mi s ms (tz zone)))))

(defmethod to-joda* Integer
  ([zone] (to-joda* (tz zone)))
  ([y mo d] (to-joda* y mo d 0 0 0 0))
  ([y mo d zone] (to-joda* y mo d 0 0 0 0 zone))
  ([y mo d h mi s] (to-joda* y mo d h mi s 0))
  ([y mo d h mi s ms]
     (to-joda* (DateTime. y mo d h mi s ms)))
  ([y mo d h mi s ms zone]
     (to-joda* (DateTime. y mo d h mi s ms #^DateTimeZone(tz zone)))))

(defmethod to-joda* clojure.lang.IPersistentVector
  [v] (apply to-joda* v))

(defmethod to-joda* String
  ([s] (try (to-joda* (tz s)) ;Try treating it like a tz
	    (catch Exception e (to-joda* s default-format)))) ;Then assume it needs to be parsed
  ([s f] (.parseDateTime (formatters f) s)) ;formatter
  ([s f zone] (to-joda* (.parseDateTime (formatters f) s) zone)))
  
;;--------------------
;; Dispatched fns
;;--------------------
(defn joda-date
  "Creates a joda-date based on a wide variety of inputs."
  ([] (DateTime. ))
  ([& args] (apply to-joda* args)))

(defn to-ms
  "Does not support time zones :("
  [& args]
  (.getMillis (apply joda-date args)))

(defn greg-cal
  "Creates a gregorian calendar that preserves time zones"
  [& args]
  (let [j (apply joda-date args)]
    (doto (GregorianCalendar. )
      (.setTimeZone #^TimeZone(java-tz j))
      (.setTimeInMillis (to-ms j)))))

(defn date
  "Does not support time zones :("
  [& args]
  (.getTime (apply greg-cal args)))

(defn to-sql
  "Used to create a java.sql.Timestamp"
  [& args]
  (Timestamp. (apply to-ms args)))

(defn time-map
  ;TO-DO: Add tz support
  "Returns a map of time objects, without time zone.  Check the constant
time-keys to see what keys are used when creating/reading a time-map."
  [& args]
  (let [time-extractor (juxt :year 
			     :monthOfYear 
			     :dayOfMonth 
			     :hourOfDay
			     :minuteOfHour
			     :secondOfMinute
			     :millisOfSecond)
	joda-bean   (bean (apply joda-date args))]
    (zipmap time-keys (time-extractor joda-bean))))

(defn str-time
  "Creates a string reprentation of the time.  Accpets the keyword formatting parameters
as the input parsers as a second argument position."
  ([] (str-time (joda-date)))
  ([& params]
     (cond
       (keyword? (first params)) (str-time (joda-date) (first params))
       (= (count params) 1) (str-time (first params) default-format)
       true (.print (formatters (second params)) (joda-date (first params))))))

(defn time-vec
  "Turns a time into a vector of ints, in the same order as time-keys."
  [& args]
  ((apply juxt time-keys) (apply time-map args)))

(def #^{:doc "Creates a string reprentation of the time.  Accpets the keyword formatting parameters
as the input parsers as a second argument position."
	:arglists '([] [& params])
	}time-str str-time)

;;--------------------
;; String Helpers
;;--------------------
(defn display-formats
  "This fn takes a date object in, and displays it in every String format that chrono
is aware of.  If no date is passed, it uses now.  Very useful for determining which
ISO8601 parser to use."
  ([] (display-formats (to-ms)))
  ([an-instant]
     ;Ignore the parsers that are for input only
     (let [input-only #{:date-element-parser 
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
	    println))))

;;-------------------
;; Predicates
;;-------------------
(defn compare-time
  "Compares two times objects, a and b."
  [a b]
  (.compareTo (date a) (date b)))
 
(defn earlier?
  "Tests to determine if time a is earlier than time b"
  [a b]
  (= (compare-time a b) -1))
 
(defn later?
  "Tests to determine if time a is later than time b"
  [a b]
  (= (compare-time a b) 1))

(defn valid-range? 
  "Tests to determine if the range is valid, i.e.
start is before finish." 
  [[start end]] (earlier? start end))

(defn is-within? 
  "Tests to see if a date d is within a range specified
by start and end"
  [d [start end]]
  (and (earlier? start d)
       (earlier? d end)))

(defn are-overlapping? 
  "Tests to see if two ranges are overlapping.  i.e. 
each range is valid and the start of range 2 is before
the end of range 1."
  [[start-1 end-1] [start-2 end-2]]
  (and (valid-range? start-1 end-1)
       (valid-range? start-2 end-2)
       (earlier? start-2 end-1)))

;;--------------------
;; Relative functions
;;--------------------
(defn period
  "This returns a Period object n units long."
  [n unit]
  ((unit period-fns) n))

;;--------------------
;; Period Shorthand
;;--------------------
(do-template
 [fn-name unit]
 (defn fn-name "A shorthand fn.  Creates a period n long." [n] (period n unit))
 year :year
 month :month
 day :day
 hour :hour
 minute :minute
 sec :second
 ;; Plural Support
 years :year
 months :month
 days :day
 hours :hour
 minutes :minute
 secs :second)

(defn period-between
  "This find the period between start and end, in ms."
  [start end]
  (Period. (to-ms start) (to-ms end)))

(do-template
 [fn-name method]
 (defn fn-name "Returns a period between the start and end
in the same interval as the fn name."
   [start end]
   (let [standard-period (method #^Period(period-between start end))
	 field-type (.getFieldType standard-period 0)]
     (.get standard-period field-type)))
 secs-between .getStandardSeconds
 minutes-between .getStandardMinutes
 hours-between .getStandardHours
 days-between .getStandardDays
 weeks-between .getStandardWeeks)

(defn +periods
  "A fn to add joda period objects.  Returns a period."
  ([] (Period.))
  ([x] x)
  ([x y] (.plus x y))
  ([x y & more] (+periods x (apply +periods y more))))

(defn -periods
  "A fn to subtract joda period objects.  Returns a period."
  ([x] (.minus (Period.) x))
  ([x y] (.minus x y))
  ([x y & more] (-periods x (apply +periods y more))))

(defn later
  "This returns a date later by a-period"
  ([a-date interval] (.plus (joda-date a-date) interval))
  ([a-date n unit] (later a-date (period n unit))))

(defn earlier
  "This returns a date earlier by a-period"
  ([a-date interval] (.minus (joda-date a-date) interval))
  ([a-date n unit] (earlier a-date (period n unit))))

;;--------------------
;; Interval Utils
;;--------------------
(defn start-of
  "This returns a the beginning of field for a given time
t.  If t is not provided, the current system time is assumed."
  ([field] (start-of (to-ms) field))
  ([the-date field]
     (let [fields (take-while (complement (hash-set field)) time-keys)
	   fields (set (drop (inc (count fields)) time-keys))
	   start-point (into {} (filter (comp fields key) default-time-map))]
       (joda-date (merge (time-map the-date) start-point)))))

(defn end-of
  "Return a time at the end of the month, year, day, etc. from the-date."
  ([unit] (end-of (to-ms) unit))
  ([the-date unit]
  (earlier (later (start-of the-date unit) (period 1 unit)) (period 1 :second))))

(defn date-seq
  "Returns a lazy seq of dates starting with from up until to in
  increments of units. If to is omitted, returns an infinite seq.
  If from is omitted, assumes now."
  ([units] (date-seq units (joda-date) nil))
  ([units from] (date-seq units from nil))
  ([units from to]
     (lazy-seq
       (when (or (nil? to) (earlier? from to))
         (cons from (date-seq units (later from (period 1 units)) to))))))

(defn later-seq
  "Returns a lazy seq of DateTime objects that is later than start by
 a constant period, interval.  A default of now is used if start is not
 provided."
  ([interval] (later-seq interval (joda-date)))
  ([interval start] (iterate #(later % interval) (joda-date start))))

(defn earlier-seq
  "Returns a lazy seq of DateTime objects that is earlier than start by
 a constant period, interval.  A default of now is used if start is not
 provided."
  ([interval] (earlier-seq interval (joda-date)))
  ([interval start] (iterate #(earlier % interval) (joda-date start))))