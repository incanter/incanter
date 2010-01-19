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

(def time-keys
     [:year :month :day :hour :minute :second :ms])

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
  (let [default-map {:year 2000
		     :month 1
		     :day 1
		     :hour 0
		     :minute 0
		     :second 0
		     :ms 0}
	input-map (first params)
	resulting-map (merge default-map input-map)
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
    (apply str
	   (interpose "\n"
		      (sort
		       (map #(str (name %) "\t"  (str-time an-instant %))
			    (remove input-only (keys formatters))))))))


(defn- make-calendar
  "Given some date values, create a Java Calendar object."
  ([] (doto (Calendar/getInstance)
        (.clear)
        (.setLenient true)))
  ([calendar]
     (.clone calendar))
  ([year month day]
     (doto (make-calendar)
       (.set year (dec month) day)))
  ([year month day hours minutes]
     (doto (make-calendar)
       (.set year (dec month) day hours minutes)))
  ([year month day hours minutes seconds]
     (doto (make-calendar)
       (.set year (dec month) day hours minutes seconds))))

(defn- get-unit [calendar unit]
  (.get calendar (units-to-calendar-units unit)))

(defmulti format-date
  "Take in a date and a format (either a keyword or a string) and
  return a string with the formatted date."
  (fn [date & form] (first form)))

(defmulti parse-date
  "Take in a string with a formatted date and a format (either a
  keyword or a string) and return a parsed date."
  (fn [source & form] (first form)))

(defn date
  "Returns a new date object. Takes year, month, and day as args as
  well as optionally hours, minutes, and seconds."
  [& args]
  (let [calendar (apply make-calendar args)]
    (proxy [clojure.lang.IFn clojure.lang.Associative] []
      (toString [] (format-date this :iso8601))
      (equals [other-date]
              (and (instance? (.getClass this) other-date)
                   (.equals calendar (other-date :calendar))))
      ;; look up :year, :month, :date, etc.
      (invoke [unit]
              (cond (= :calendar unit) calendar ;; mostly for internal use
                    (= :month unit) (inc (get-unit calendar :month))
                    true (get-unit calendar unit)))
      ;; These (along with implementing Associative) allow us to use
      ;; (:month my-date), etc. Good idea? Not sure since we don't
      ;; implement all of Associative, just enough for keywords.
      (valAt [unit] (.invoke this unit))
      (equiv [o] (.equals this o)))))

;;brad's hacks

(defn date> 
  ""
  ([d] 
    (let [cal (Calendar/getInstance)]
      (.setTime cal d)
    (date cal))))

;;joda time 

(defn time-zone 
  ""
  ([offset] (DateTimeZone/forOffsetHours offset)))

(defn joda-date
  "Creates a joda date object"
  ([str-d] (DateTime. str-d))
  ([y m d h min sec mill zone]
  (DateTime. y m d h min sec mill zone)))
    
(defn joda-proxy
  "joda-date object wraped in a proxy of goodness."
  [& args]
  (let [d (apply joda-date args)]
    (proxy [clojure.lang.IFn 
            clojure.lang.Associative] []
      (toString [] (str d))
      (equals [other-date]
              (and (instance? (.getClass this) other-date)
                   (.equals d (other-date :datetime))))
      (invoke [unit]
              (let [res
              (cond (= :years unit) (.year d) 
                    (= :months unit) (.monthOfYear d) 
                    (= :days unit) (.dayOfMonth d) 
                    (= :day-of-week unit) (.dayOfWeek d) 
                    (= :hours unit) (.hourOfDay d) 
                    (= :minutes unit) (.minuteOfHour d) 
                    (= :seconds unit) (.secondOfMinute d) 
                    (= :millis unit) (.millisOfSecond d) 
                    :otherwise d)]
                (if (instance? DateTime$Property res)
                  (.get res)
                  res)))
      ;; These (along with implementing Associative) allow us to use
      ;; (:month my-date), etc. Good idea? Not sure since we don't
      ;; implement all of Associative, just enough for keywords.
      (valAt [unit] (.invoke this unit))
      (equiv [o] (.equals this o)))))

(defn joda-str 
  ""
  ([d] (str `(DateTime. ~(str d)))))

(defmethod print-dup org.joda.time.DateTime [d w] (.write w (joda-str d)))
 
(defn joda-guard 
  ""
  ([d] (not (instance? org.joda.time.DateTime d))))

  ;;TODO: make this stuff monadic
(defn minutes-between 
  ""
  ([start end] 
    (if (or (joda-guard start) (joda-guard end))
      nil
      (.getMinutes (Minutes/minutesBetween start end)))))

(defn hours-between 
  ""
  ([start end] 
    (if (or (joda-guard start) (joda-guard end))
      nil
      (.getHours (Hours/hoursBetween start end)))))

(defn hours-from 
  ""
  ([d #^Integer h] (.plusHours d h)))

(defn minutes-from 
  ""
  ([d #^Integer m] (.plusMinutes d m)))

(defn hours-around 
  ""
  ([r #^Integer d] (map #(.plusHours d %) r)))

(defn before? 
  ""
  ([start end] (.isBefore start end)))

(defn valid-range? 
  "" 
  ([[start end]] (maybe? before? start end)))

(defn is-within? 
  ""
  ([d [s e]] (.contains (Interval. s e) d)))

(defn are-overlapping? 
  ""
  ([[s e] [s1 e1]]
    (if (and (valid-range? [s e]) (valid-range? [s1 e1]))
      (letfn [(has-overlap? [start end start1 end1]
                          (not (nil? (.overlap (Interval. start end) (Interval. start1 end1)))))]
        (maybe? has-overlap? s e s1 e1))
      false)))

;;todo: find out why this yields different resutls than reading the
;;other joda-str function output back in.
;;  joda-date 
;;        ~(.getYear d)
;;        ~(.getMonthOfYear d)
;;        ~(.getDayOfWeek d)
;;        ~(.getHourOfDay d)
;;        ~(.getMinuteOfHour d)
;;        ~(.getSecondOfMinute d)
;;        ~(.getMillisOfSecond d)
;;        (DateTimeZone/forID ~(str (.getZone d))))))

(defn now
  "Returns a new date object with the current date and time."
  []
  (date (Calendar/getInstance)))

(defn today
  "Returns a new date object with only the current date. The time
  fields will be set to 0."
  []
  (let [d (now)]
    (date (d :year) (d :month) (d :day))))

;;; Relative functions

(defn later
  "Returns a date that is later than the-date by amount units.
  Amount is one if not specified."
  ([the-date amount units]
     (date (doto (.clone (the-date :calendar))
             (.set (units-to-calendar-units units)
                   (+ (.get (the-date :calendar)
                            (units-to-calendar-units units))
                      amount)))))
  ([the-date units]
     (later the-date 1 units)))

(defn date-time 
  ""
  ([d t] (later d t :minute)))

(defn earlier
  "Returns a date that is earlier than the-date by amount units.
  Amount is one if not specified."
  ([the-date amount units]
     (later the-date (- amount) units))
  ([the-date units]
     (later the-date -1 units)))

(defn later? 
  "Is date-a later than date-b?"
  ([date-a date-b]
    (.after (date-a :calendar) (date-b :calendar))))

(defn earlier? 
  "Is date-a earlier than date-b?"
  ([date-a date-b] (.before (date-a :calendar) (date-b :calendar))))

(defn time-between
  "How many units between date-a and date-b? Units defaults to seconds."
  ([date-a date-b]
     (/ (java.lang.Math/abs
      (- (.getTimeInMillis (date-a :calendar))
         (.getTimeInMillis (date-b :calendar)))) 1000))
  ([date-a date-b units]
     ;; TODO: should we move plural support up to
     ;; units-in-seconds and units-to-calendar-units?
     (let [units (if (re-find #"s$" (name units)) ;; Allow plurals
                   ;; This relies on the patched subs defn below
                   (keyword (subs (name units) 0 -1))
                   units)]
       (/ (time-between date-a date-b)
          (units-in-seconds units)))))

(defn- args-for "Allow round-tripping through date function"
  [date]
  [(date :year) (date :month) (date :day)
   (date :hour) (date :minute) (date :second)])

(defn beginning-of
  "Return a date at the beginning of the month, year, day, etc. from the-date."
  [the-date unit]
  ;; TODO: clean up!
  (let [position ({:year 1 :month 2 :day 3 :hour 4 :minute 5 :second 6} unit)]
    (apply date (concat (take position (args-for the-date))
                        (drop position [1970 1 1 0 0 0])))))

(defn end-of
  "Return a date at the end of the month, year, day, etc. from the-date."
  [the-date unit]
  ;; TODO: this is kinda ugly too?
  (earlier (later (beginning-of the-date unit) unit) :second))

(defn date-seq
  "Returns a lazy seq of dates starting with from up until to in
  increments of units. If to is omitted, returns an infinite seq."
  ([units from to]
     (lazy-seq
       (when (or (nil? to) (earlier? from to))
         (cons from (date-seq units (later from units) to)))))
  ([units from] (date-seq units from nil)))

;;; Formatting and Parsing

(defmacro def-date-format 
  ""
  ([fname [arg] & body]
    `(defmethod format-date ~(keyword (name fname)) [~arg ~'_]
       ~@body)))

(defmacro def-date-parser 
  ""
  ([fname [arg] & body]
    `(defmethod parse-date ~(keyword (name fname)) [~arg ~'_]
       ~@body)))

;;; Use the normal Java date formats

(def #^{:private true}
     format-to-java-const
     {:short DateFormat/SHORT,
      :medium DateFormat/MEDIUM,
      :long DateFormat/LONG,
      :full DateFormat/FULL})

(doseq [[key con] format-to-java-const]
  (defmethod format-date (keyword (str (name key) "-date")) [date _]
    (.format (DateFormat/getDateInstance con)
             (.getTime (date :calendar))))
  (defmethod parse-date (keyword (str (name key) "-date")) [source _]
    (date (doto (make-calendar)
            (.setTime (.parse (DateFormat/getDateInstance con)
                              source)))))
  (defmethod format-date (keyword (str (name key) "-date-time")) [date _]
    (.format (DateFormat/getDateTimeInstance con con)
             (.getTime (date :calendar))))
  (defmethod parse-date (keyword (str (name key) "-date-time")) [source _]
    (date (doto (make-calendar)
            (.setTime (.parse (DateFormat/getDateTimeInstance con con)
                              source))))))

;;; Formats dates with a custom string format
(defmethod format-date :default [date form]
  (.format (SimpleDateFormat. form)
           (.getTime (date :calendar))))

;;; Parse a date from a string format
(defmethod parse-date :default [source form]
  (date
   (doto (make-calendar)
     (.setTime (.parse (SimpleDateFormat. form) source)))))

(defmethod format-date nil [date]
  (format-date date :iso8601))

(defmacro def-simple-date-format [fname form]
  `(do
     (def-date-format ~fname [date#]
       (format-date date# ~form))
     (def-date-parser ~fname [source#]
       (parse-date source# ~form))))

;; Technically this should also have a single character time zone
;; indicator, but I'm not sure how to do that yet.
(def-simple-date-format iso8601 "yyyy-MM-dd HH:mm:ss")

;; TODO: parse-date should be able to guess at the format

;; Redefine subs to allow for negative indices.
;; TODO: This should be submitted as a patch to Clojure.
(in-ns 'clojure.core)
(defn subs
  "Returns the substring of s beginning at start inclusive, and ending
  at end (defaults to length of string), exclusive."
  ([#^String s start] (subs s start (count s)))
  ([#^String s start end]
     (let [count-back #(if (< % 0) (+ (count s) %) %)]
       (.substring s (count-back start) (count-back end)))))
