(ns palivovnik.utils
    (:use net.cgrand.enlive-html
          cheshire.core
          palivovnik.db.cars)
    (:require [noir.session :as s]))

(defn current-car []
  (s/get :car))

(defn set-current-car! [c]
  (s/put! :car c))

(defn current-car-id []
  (:id (current-car)))

(defn js
    "Creates JS tags for every script path"
    [paths]
    (map (fn [p] {:tag :script :attrs {:type "text/javascript" :src p}}) paths))


(defmulti opts map?)

(defmethod opts
    true [^clojure.lang.IPersistentCollection items]
            (map (fn [m] {:tag :option
                          :attrs {:value (let [k (key m)] (if (keyword? k) (name k) (str k)))}
                          :content (str (val m))}) items))

(defmethod opts
    false [^clojure.lang.IPersistentCollection items]
        (opts (into (sorted-map) (map #(vec (repeat 2 %)) items))))

(def options (memoize opts))

(defn keep-if [bool-val]
    #(if bool-val % nil))

(defn update-in-if-exists [m k f]
  (into m (map #(vector (key %) (f (val %))) (select-keys m [k]))))

(defn mongoize [body]
  (let [obj (parse-string (slurp body) true)]
    (-> obj
      (update-in-if-exists :_id   #(org.bson.types.ObjectId. %))
      (update-in-if-exists :date  #(java.util.Date. %)))))

(def sum (partial reduce +))

(defn sum-vals [k c] (reduce + (map k c)))

(defprotocol AssociativeStructKey 
  (get-struct-prototype [this]))

(extend-type clojure.lang.Keyword
  AssociativeStructKey
    (get-struct-prototype [this] {}))

(extend-type Long
  AssociativeStructKey
    (get-struct-prototype [this] []))

(defn vassoc-in
  [m [k & ks] v]
  (if ks
    (assoc m k (assoc-in (get m k (get-struct-prototype (first ks))) 
                         ks v))
    (assoc m k v)))

