(ns palivovnik.db.stats
    (:require [monger.collection :as mc]
              [clj-time.core :as t]
              [clj-time.coerce :as c])
    (:use monger.operators))

(defn avg-cons-last-12-months [cid]
  (let [from  (t/first-day-of-the-month (t/minus (t/now) (t/months 12)))
        to    (t/plus 
                (t/last-day-of-the-month (t/minus (t/now) (t/months 1))) 
                (t/seconds 43199))]
    
    (mc/aggregate "fillups" [{$match    {:car_id cid :date {$gte (c/to-date from) 
                                                            $lte (c/to-date to)}}} 
                             {$group    {:_id {$month "$date"} 
                                         :avgcons {$avg "$cons"} 
                                         :distance {$sum "$distance"} 
                                         :firstdate {$min "$date"}}} 
                             {$sort     {:firstdate 1}}])))
