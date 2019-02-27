(ns palivovnik.db.fillups
    (:use robert.hooke
          palivovnik.utils
          clojure.algo.generic.functor)
    (:require [monger.collection :as mc]
              [clojurewerkz.neocons.rest.nodes :as nn]
              [monger.query :as q]
              digest)
    (:import [java.util UUID]))

(defn save-fillup [car data]
  (let [fillup (merge {:car_id (:id car)
                       :_id (org.bson.types.ObjectId.)} data)]
    (mc/upsert "fillups" {:_id (:_id fillup) :car_id (:id car)} 
               {"$set" (dissoc fillup :_id)})
    fillup))

(defn fillups-count-for-car-id [carid]
    (mc/count "fillups" {:car_id carid}))

(defn fillups-with-cons [carid]
  (mc/find-maps "fillups" {:car_id carid :cons {:$exists true}}))

(defn fillup-by-id-for-car [fid cid]
  (mc/find-one-as-map "fillups" 
                      {:_id (org.bson.types.ObjectId. fid) 
                       :car_id cid}))

(defn delete-fillup-by-id [fid cid]
  (mc/remove "fillups" 
             {:_id (org.bson.types.ObjectId. fid) 
              :car_id cid})
  {:car_id cid})

(defn fillups-for-car-id
  ([carid]
    (mc/find-maps "fillups" {:car_id carid}))
  
  ([carid page]
    (q/with-collection "fillups"
      (q/find {:car_id carid})
      (q/sort {:odo -1})
      (q/paginate :page page :per-page 10))))

(defn last-fillup-or-existing-for-car-id [fid carid]
  (if fid
      (fillup-by-id-for-car (str fid) carid)
      (first
        (q/with-collection "fillups"
            (q/find {:car_id carid})
            (q/sort {:odo -1})
            (q/limit 1)))))

(defn avg-consumption [fillups total-dist]
  (/ (sum (map #(* (:cons %) (:distance %)) fillups))
     total-dist))

(defn avg-price-per-km [total-dist totals]
  (/ totals
     total-dist))

(defn avg-price-per-l [fillups totals]
  (/ totals
     (sum (map (comp (partial sum-vals :volume) :phases) fillups))))

(defn refresh-stats [fillups]
  (let [total-dist  (sum-vals :distance fillups)
        totals      (sum-vals :total fillups)]
     {:average-cons      (avg-consumption fillups total-dist)
      :average-price-km  (avg-price-per-km total-dist totals)
      :average-price-l   (avg-price-per-l fillups totals)}))

(defn calculate-fillup-cons [fillup distance]
  (float (* 100 (/ (sum-vals :volume (:phases fillup))
                   distance))))

(defn compute-distance [current prev]
  (+ (if (= (:_id current) (:_id prev)) (:distance prev) 0) 
     (- (:odo current) (:odo prev))))

(defn assoc-calculated-cons [f prev]
  (merge f (when (and f prev (not (:missed f))) 
            (let [distance (compute-distance f prev)]
              {:cons (calculate-fillup-cons f distance)
               :distance distance
               :total (sum (map #(* (:perunit %) (:volume %)) (:phases f)))}))))

(defn calculate-multiple-fillup-conses [f fs]
  (rest (reduce #(conj %1 (assoc-calculated-cons %2 (last %1))) 
                [f] 
                (sort-by :odo fs))))

(defn insert-multiple-fillups [carid fs]
  (let [lastf (last-fillup-or-existing-for-car-id false carid)
        fs-with-cons (calculate-multiple-fillup-conses lastf fs)]
    (mc/insert-batch "fillups" 
                     (map #(assoc % :car_id carid) fs-with-cons))
    {:car_id carid}))

; --------- hooks
(defn refresh-stats-hook [f car data]
  (let [fu   (f car data)
        cid  (:car_id fu)
        fs   (fillups-with-cons cid)
        current-car-data (:data (nn/get cid))]
    (nn/update (long cid) 
               (into {} 
                     (filter (comp not nil? val)
                       (merge current-car-data (refresh-stats fs)))))    
    fu))

(defn fillup-cons-hook [f car data]
  (let [last-fillup (last-fillup-or-existing-for-car-id (:_id data) (:id car))]
    (f car (assoc-calculated-cons data last-fillup))))

(defn stat-functions-guard [f x y]
  (if (= y 0) nil (float (f x y))))

(add-hook #'save-fillup               #'fillup-cons-hook)
(add-hook #'save-fillup               #'refresh-stats-hook)
(add-hook #'insert-multiple-fillups   #'refresh-stats-hook)
(add-hook #'delete-fillup-by-id       #'refresh-stats-hook)

(add-hook #'avg-consumption           #'stat-functions-guard)
(add-hook #'avg-price-per-l           #'stat-functions-guard)
(add-hook #'avg-price-per-km          #'stat-functions-guard)
