(ns palivovnik.import
  (:require [clj-time.format :as d]
            [clj-time.coerce :as c]
            [clojure.string :refer [blank?]])
  (:use palivovnik.utils))

(defn parse-date [f d]
  (c/to-date (d/parse (d/formatter f) d)))

(def converter-map 
  {:fuelly [[3  [:odo]                (comp int read-string)]
            [5  [:phases 0 :volume]   read-string]
            [6  [:phases 0 :perunit]  read-string]
            [7  [:city_driving]       read-string]
            [8  [:date]               #(parse-date "yyyy-MM-dd" (subs % 0 10))]
            [12 [:missed]             #(= % "1")]
            [15 [:loc 0]              read-string]
            [14 [:loc 1]              read-string]]
   
   :spritmonitor  
           [[1      [:odo]                (comp int read-string)]
            [3      [:phases 0 :volume]   read-string]
            [[4 3]  [:phases 0 :perunit]  #(reduce / (map read-string %))]
            [0      [:date]               (partial parse-date "dd.MM.yyyy")]
            [12     [:missed]             #(= % "0,00")]]})

(defmulti multi-nth (fn [coll i] (vector? i)))

(defmethod multi-nth false [coll idx] (nth coll idx))
(defmethod multi-nth true [coll idxs] (map (partial nth coll) idxs))

(defn convert [f path v]
  (try 
     (f v)
     (catch Exception e 
       (throw (RuntimeException. (str "Parsing " path " failed: " (.getMessage e)))))))

(defn assoc-in-if-not-empty [fillup c row]
  (let [origval (multi-nth row (first c))
        path (second c)]
    (if ((some-fn empty? (every-pred string? blank?)) origval)
        fillup
        (vassoc-in fillup 
                   path
                   (convert (last c) path origval)))))

(defn fuellups-from-csv [service data]
  (let [fillups (rest data)]
    (map (fn [row]
           (loop [convrtrs (service converter-map) fillup {}]
             (if (empty? convrtrs)
               fillup
               (recur (rest convrtrs)                      
                      (let [c (first convrtrs)]
                        (assoc-in-if-not-empty fillup c row))))))
         fillups)))
