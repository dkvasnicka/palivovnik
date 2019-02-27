(ns palivovnik.app
    (:use compojure.core
          noir.util.middleware
          net.cgrand.enlive-html
          palivovnik.routes.auth
          palivovnik.views.layout
          palivovnik.routes.cars
          palivovnik.routes.fillups
          palivovnik.utils
          cheshire.generate)
    (:require   [compojure.route :as route]
                [noir.response :as r]                
                [noir.session :as s])
    (:import [java.util UUID]))

; MongoDB obj ID JSON encoder
(add-encoder
  org.bson.types.ObjectId
  (fn [val json-generator]
    (.writeString json-generator (.toString val))))

; generic routes
(defroutes app
    (GET "/" []
        (layout {:main (index)
                 :scripts (js ["/js/social-login.js"
                               "/jslibs/social-login-fb.js"
                               "/jslibs/social-login-google.js"])}))
    (GET "/error/:id" [id]
        (r/status 500 
                  (r/set-headers {"Content-Type" "text/html;charset=UTF-8"} 
                                 (layout {:main (error {:error-id id})})))))

(defroutes misc
    (route/resources "/")
    (route/not-found "<h1>Page not found</h1>"))

(defn wrap-exception-handling [handler]
  (fn [req]
    (try 
      (handler req)
      (catch Throwable t 
        (let [error-id (UUID/randomUUID)]
          (println "\n\nError ID: " error-id)
          (.printStackTrace t)
          (r/redirect (str "/error/" error-id)))))))

; ring handler
(def handler
    (-> [app auth cars fillups misc]
        (app-handler)
        (wrap-access-rules
            (fn [method url params]
                (not (nil? (s/get :user)))))
        (wrap-exception-handling)))
