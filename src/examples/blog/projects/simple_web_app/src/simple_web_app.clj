;; This code is from the following blog post:
;; Building a simple Clojure web application with Incanter, Compojure, and Leiningen
;; http://incanter-blog.org/2009/11/29/incanter-webapp/


(ns simple_web_app
  (:gen-class)
  (:use [compojure]
        [compojure.http response]
        [incanter core stats charts])
  (:import (java.io ByteArrayOutputStream 
                    ByteArrayInputStream)))


;; Pass a map as the first argument to be 
;; set as attributes of the element
(defn html-doc 
  [title & body] 
  (html 
    (doctype :html4) 
    [:html 
      [:head 
        [:title title]] 
      [:body 
       [:div 
	[:h2 
	 [:a {:href "/"} 
          "Generate a normal sample"]]]
        body]])) 


(def sample-form 
  (html-doc "sample-normal histogram" 
    (form-to [:get "/sample-normal"] 
      "sample size: " (text-field {:size 4} :size) 
      "mean: " (text-field {:size 4} :mean) 
      "sd: " (text-field {:size 4} :sd) 
      (submit-button "view")))) 



(defn gen-samp-hist-png 
  [request size-str mean-str sd-str] 
    (let [size (if (nil? size-str) 
                 1000 
                 (Integer/parseInt size-str))
          m (if (nil? mean-str) 
              0 
              (Double/parseDouble mean-str))
          s (if (nil? sd-str) 
              1 
              (Double/parseDouble sd-str))
          samp (sample-normal size 
                              :mean m 
                              :sd s)
          chart (histogram 
                  samp
                  :title "Normal Sample"
                  :x-label (str "sample-size = " size 
                                ", mean = " m 
                                ", sd = " s))
          out-stream (ByteArrayOutputStream.)
          in-stream (do
                      (save chart out-stream)
                      (ByteArrayInputStream. 
                        (.toByteArray out-stream)))
          header {:status 200 
                  :headers {"Content-Type" "image/png"}}]
      (update-response request 
                       header
                       in-stream)))



;; define routes
(defroutes webservice
  (GET "/" 
    sample-form) 
  (GET "/sample-normal" 
    (gen-samp-hist-png request 
                       (params :size) 
                       (params :mean) 
                       (params :sd))))

;; define main function that starts webserver
(defn -main [& args]
  (run-server {:port 8080} 
    "/*" (servlet webservice)))


