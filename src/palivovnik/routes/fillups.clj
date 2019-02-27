(ns palivovnik.routes.fillups
    (:use compojure.core
          noir.util.middleware
          net.cgrand.enlive-html
          palivovnik.db.cars
          palivovnik.db.fillups
          noir.util.route
          palivovnik.validators.defs
          palivovnik.views.layout
          palivovnik.views.fillups
          palivovnik.utils
          palivovnik.import)
    (:require
          [noir.response :as r]
          [noir.session :as s]
          [noir.validation :as v]
          [clojure.java.io :as io]
          [clojure.data.csv :as csv]))

(def fillupscripts
  (js ["https://maps.googleapis.com/maps/api/js"
       "/js/services.js"
       "/js/fillup.js"]))

(defn fillup-page [fid-map]
  (layout {:main (fillup fid-map)
           :scripts fillupscripts}))

(defn do-save-fillup [body]
  (let [fillup (mongoize body)
        current-user (s/get :user)
        car (current-car)]

          (apply r/status
            (if (valid-fillup-data? fillup)
              [200 (r/json (save-fillup car fillup))]
              [400 (r/json (v/get-errors))]))))

(defn import-page []
  (layout {:main (import-fillups)}))

(def dsource-csv-conf
  {:spritmonitor  [:separator \;]})

(defroutes fillups
    ; REST
    (restricted POST "/car/fillup/:id" {body :body {id :id} :params}
        (do-save-fillup body))

    (restricted POST "/car/fillup" {body :body}
        (do-save-fillup body))

    (restricted GET "/car/fillup/:id" [id]
      (r/json (fillup-by-id-for-car id (current-car-id))))

    (restricted DELETE "/car/fillup/:id" [id]
      (delete-fillup-by-id id (current-car-id))
      (r/json {:deleted "OK"}))

    (GET "/car/:id/fillups/count" [id]
      (r/json {:count (fillups-count-for-car-id (Integer/parseInt id))}))

    (GET "/car/:id/fillups/p/:page" [id page]
      (r/json (fillups-for-car-id (Integer/parseInt id)
                                  (Integer/parseInt page))))

    ; Pages
    (restricted GET   "/fillup"           []    (fillup-page {}))
    (restricted GET   "/import"           []    (import-page))
    (restricted POST  "/import"           [dsource data]
                (insert-multiple-fillups (current-car-id)
                  (with-open [in-file (io/reader (:tempfile data))]
                    (let [dsource-kw (keyword dsource)]
                      (fuellups-from-csv
                        dsource-kw
                        (doall (apply csv/read-csv (concat [in-file]
                                                           (get dsource-csv-conf dsource-kw []))))))))

                (r/redirect "/dashboard"))

    (restricted GET   "/fillup/:id/edit"  [id]  (fillup-page {:id id}))
    )
