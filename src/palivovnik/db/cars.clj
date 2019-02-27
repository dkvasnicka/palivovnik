(ns palivovnik.db.cars
    (:require [clojurewerkz.neocons.rest.nodes :as nn]
              [clojurewerkz.neocons.rest.relationships :as nrel]
              digest))

(defn add-hashmap-md5 [m]
  (assoc m :hash (digest/md5 (reduce str (vals m)))))

(defn create-if-not-exists [idx k node]
  (let [v (get node (keyword k))]
    (if (nil? v)
      false
      (or (nn/find-one idx k v)
          (nn/create-unique-in-index idx k v node)))))

(defn new-car [data user]
  (let [country (create-if-not-exists "countries" "name" {:name (:country (:marque data))})
        marquename (:name (:marque data))
        marque (create-if-not-exists "marques" "name" {:name marquename})
        modelname (:type data)
        model (create-if-not-exists "models" "hash" 
                                    {:name modelname 
                                     :hash (digest/md5 
                                             (str marquename ":" modelname))})
        transmission (create-if-not-exists "trannies" "hash"
                                           (add-hashmap-md5 (:transmission data)))
        engine-data (:engine data)
        engine (create-if-not-exists "engines" "hash"
                                     (add-hashmap-md5 (select-keys engine-data
                                                            [:configuration :cylinders])))
        fuel (create-if-not-exists "fuels" "type" {:type (:fuel engine-data)})
        car (nn/create {:year (:year data)
                        :engine-vol (:displacement engine-data)
                        :label (str marquename " " modelname)})]
    
    (when country (nrel/create car country :from))
    (nrel/create car marque :manufactured-by) 
    (nrel/create car model :is)
    (nrel/create car transmission :shifts)
    (nrel/create car engine :powered-by)
    (nrel/create car fuel :runs-on)
    (nrel/create user car :owns)    
    (nn/add-to-index car "owners" "email" (:email (:data user)))
    car))

(defn cars-for [email]
  (nn/find "owners" "email" email))

(defn car-by-id [id]
  (nn/get id))

