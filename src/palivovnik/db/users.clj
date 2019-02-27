(ns palivovnik.db.users
    (:require   [clojurewerkz.neocons.rest.nodes :as nn]))

(defn user-exists [id]
    (or (nn/find-one "users" "email" id)
        (nn/find-one "users-twitter" "uid" id)
        false))

(defn new-user [email id]
    (let [u (nn/create-unique-in-index "users" "email" email {:email email})]
      (when (not= email id)
        (nn/add-to-index u "users-twitter" "uid" id))
      u))

