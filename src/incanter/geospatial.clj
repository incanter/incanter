(ns incanter.geospatial
  (:use [incanter.internal :only [any?]])
  (:import org.geotools.referencing.GeodeticCalculator))

(defn distance-between [[start-lon start-lat] [end-lon end-lat]] 
  (if (any? nil? [start-lon start-lat end-lon end-lat]) 0
  (let [geo (GeodeticCalculator.)]
    (.setStartingGeographicPoint geo start-lon start-lat) 
    (.setDestinationGeographicPoint geo end-lon end-lat) 
    (.getOrthodromicDistance geo))))
