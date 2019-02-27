(ns palivovnik.routes.cars
    (:use compojure.core
          noir.util.middleware
          net.cgrand.enlive-html
          palivovnik.data.cars
          palivovnik.db.users
          palivovnik.db.cars
          palivovnik.db.fillups
          palivovnik.db.stats
          cheshire.core
          noir.util.route
          palivovnik.validators.defs
          palivovnik.views.layout
          palivovnik.views.cars
          palivovnik.utils)
    (:require
                [noir.response :as r]
                [noir.session :as s]
                [noir.validation :as v]))

(defn generate-permalink [car]
  (str "/c/" (:id car) "/" (-> (:label (:data car))
                               (clojure.string/lower-case)
                               (clojure.string/replace #"\s{1,}" "-"))))

(defn render-dashboard [car]
  (layout {:main (dashboard {:car car :permalink (generate-permalink car)})
           :scripts (js ["/jslibs/d3.v3.min.js"
                         "/jslibs/c3.min.js"
                         "/jslibs/moment.min.js"
                         "/jslibs/moment.lang.cs.js"
                         "/js/dashboard.js"])}))

(defroutes cars
    ; REST endpoints
    (GET "/cars/models/:marque" [marque]
        (r/json (get marques-models marque ["--"])))

    (restricted GET "/cars" []
                (r/json 
                  (cars-for (:id (s/get :user)))))
  
    (restricted POST "/car" {body :body}
        (let [body-text (slurp body) data (parse-string body-text true)
              current-user (s/get :user)
              original-cached-uid (:id current-user)
              incoming-email (:email (:user data))
              email (or incoming-email original-cached-uid)
              car (:car data)]

            (apply r/status
              (if (valid-signup-data? email car)
                (do
                  (when incoming-email
                    (s/put! :user (assoc current-user :id incoming-email)))

                  (let [u (new-user email original-cached-uid)] 
                    (new-car car u))
                  [200 (r/json {})])

                [400 (r/json (v/get-errors))]))))

    (GET "/stats/:id/avg-cons-months" [id]
      (r/json (avg-cons-last-12-months (Integer/parseInt id))))           
           
    ; Pages
    (restricted GET "/dashboard" []
                (when (nil? (current-car))                
                  (set-current-car! (first (cars-for (:id (s/get :user))))))            
                (render-dashboard (current-car)))

    (GET "/c/:id/:maketype" [id _]
      (render-dashboard (car-by-id (Integer/parseInt id)))))
