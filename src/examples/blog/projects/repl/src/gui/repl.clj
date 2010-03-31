;(ns  #^{:doc "A simple gui Clojure repl"
;       :author "johnmn3"}
(ns gui.repl
  (:gen-class)
  (:use [clojure.main :only [repl repl-read]])
  (:import (javax.swing JFrame 
			JTextArea 
			JPanel 
			UIManager 
			SwingUtilities 
			JScrollPane 
			BoxLayout)
	  (java.awt.event ActionListener KeyAdapter)
	  (java.io StringReader PrintWriter PushbackReader Writer 
		   StringReader OutputStreamWriter)
	  (java.awt GridLayout FlowLayout Font)
	  (java.util.concurrent LinkedBlockingQueue ArrayBlockingQueue)
	  (clojure.lang IDeref Associative LineNumberingPushbackReader)))

(. UIManager (setLookAndFeel (. UIManager (getSystemLookAndFeelClassName))))


(defn fn->kl [fun]
  (proxy [KeyAdapter] []
    (keyTyped [event] (fun event))))

(defmacro EDT [& body]                                                                                 
  `(SwingUtilities/invokeLater (fn [] ~@body)))                                                        

(defn bagger [aq]
  (let [newstr (atom [])
        started (atom false)
        quoted  (atom false)
        ignore  (atom false)
        open-p  (atom 0)
        q aq]
    (fn
    ;;; two param bag resets manually.
    ([a b] (do (swap! newstr  (fn [_] []))
               (swap! started (fn [_] false))
               (swap! quoted  (fn [_] false))
               (swap! ignore  (fn [_] false))
               (swap! open-p  (fn [_] 0))))
    ([c]
      (do
        (println (apply str @newstr))
        (println "ding")
        (swap! newstr conj c)
        (cond
          (= c \\)
            (swap! ignore (fn [_] true))
          (= c \")
            (do
              (println "in quote")
              (if (not @ignore)
                (if (not @quoted)
                  (swap! quoted (fn [_] true))
                  (swap! quoted (fn [_] false))))
              (swap! ignore (fn [_] false)))
          (= c \()
            (do
              (println "in open")
              (if (not @ignore)
                (if (not @quoted)
                  (do
                    (swap! started (fn [_] true))
                    (swap! open-p inc))))
              (swap! ignore (fn [_] false)))
          (= c \))
            (do
              (println "in close")
              (if (not @ignore)
                (if (not @quoted)
                  (if @started
                    (swap! open-p dec))))
              (swap! ignore (fn [_] false)))
          :else (swap! ignore (fn [_] false)))
        (if @started
          (if (= 0 @open-p)
            (do
              (println "in open = zero")
              (.put q (-> (apply str @newstr)
                         StringReader.
                         LineNumberingPushbackReader.))
              (println "done")
              (println (apply str @newstr)) ; temp, for testing
              (swap! newstr  (fn [_] []))
              (swap! started (fn [_] false))
              (swap! quoted  (fn [_] false))
              (swap! ignore  (fn [_] false)))))
        (if (> 0 @open-p)
          (println "Oops.  We have more closed than open.")))))))

(defn jta->ops [jta]
  (let [buffer (StringBuffer.)]
    (proxy [java.io.OutputStream IDeref] []
      (deref [] buffer)
      (flush []
             (when (< 0 (.length buffer))
              (let [sb (.toString buffer)]
                (EDT (.append jta sb))
                (.setLength buffer 0))))
      (close [] (.flush this))
      (write
        ([i] (.append buffer (char i)))
        ([buf off len]
         (doseq [i (take len (drop off buf))]
           (.append buffer (char i))))))))


(defn start-repl-thread [output transfer-q]
  (.start
    (Thread.
      #(binding [*out* (-> output jta->ops OutputStreamWriter. PrintWriter.)]
         (clojure.main/repl
           :caught (fn [x] (.printStackTrace x *out*) (.flush *out*) (println ""))
           :need-prompt (constantly false)
           :read (fn [a b]
                   (binding [*in* (.take transfer-q)]
                     (clojure.main/repl-read a b)))
           :print (fn [x]
                    (EDT (.append output (prn-str x)))))))))

(defn add-ctrl-enter-listener [text-area output abag]
  (EDT
    (.addKeyListener text-area
                     (fn->kl
                       (fn [event]
                         (when
                           (= \newline (:keyChar (bean event)))
                           (.append output (str (.getText text-area) "\n"))
                           (let [s (.getText text-area)]
                             (doseq [x s]
                               (abag x)))
                           (.setText text-area "")
                           (.requestFocus text-area)))))))

(defn start-gui [window text-area output]
  (EDT
    (doto (.getContentPane window)
      (.setLayout (BoxLayout. (.getContentPane window) (. BoxLayout Y_AXIS)))
      (.add (JScrollPane.
              (doto output
                (.setLineWrap true)
                (.setTabSize 2)
                (.setColumns 80)
                (.setRows 15)
                (.setFont (Font. "monospaced" Font/PLAIN 14))
                (.setEditable false))))
      (.add (JScrollPane.
              (doto text-area
                (.setColumns 80)
                (.setFont (Font. "monospaced" Font/PLAIN 14))))))
    (doto window
      .pack
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setVisible true))))

(defn -main [& args]
  (let [text-area (javax.swing.JTextField.)
       output (JTextArea.)
       transfer-q (ArrayBlockingQueue. 1)
       bag (bagger transfer-q)]
   (start-repl-thread output transfer-q)
   (add-ctrl-enter-listener text-area output bag)
   (start-gui (JFrame. "Clojure") text-area output)))

