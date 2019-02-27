(ns palivovnik.init
    (:require [clojurewerkz.neocons.rest :as nr]
              [monger.core :as mg]))

(defn on-start []
  (nr/connect! "secret")
  (mg/connect! {:host "secret" :port 27017})
  (mg/set-db! (mg/get-db "palivovnik")))
