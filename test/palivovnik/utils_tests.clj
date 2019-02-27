(ns palivovnik.utils_tests
  (:require [midje.sweet :refer :all]
            [palivovnik.utils :as u]
            [clojure.java.io :refer :all]))

(fact "mongoization only works on existing fields"
      (u/mongoize (input-stream (.getBytes "{}"))) => {})

(fact "mongoization does its job"
      (u/mongoize 
        (input-stream 
          (.getBytes "{ \"_id\": \"52f4014f0364bf318ae74ced\",
                      \"date\": 1313712000000 }"))) 
      
      => {:_id (org.bson.types.ObjectId. "52f4014f0364bf318ae74ced")
          :date (java.util.Date. 1313712000000)})
