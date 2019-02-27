(ns palivovnik.routes.auth
    (:use compojure.core
          noir.util.middleware
          net.cgrand.enlive-html
          palivovnik.db.users
          palivovnik.views.layout
          palivovnik.views.auth
          palivovnik.utils
          cheshire.core
          noir.util.route)
    (:require   [compojure.route :as route]
                [noir.response :as r]
                [noir.session :as s]
                [oauth.client :as oauth]))

(def service-urls
    {:google    "https://www.googleapis.com/oauth2/v1/tokeninfo"
     :facebook  "https://graph.facebook.com/me"})

(def consumer (oauth/make-consumer
                  ""
                  ""
                  "https://api.twitter.com/oauth/request_token"
                  "https://api.twitter.com/oauth/access_token"
                  "https://api.twitter.com/oauth/authorize"
                  :hmac-sha1))

(defroutes auth
    ; REST endpoints
    (POST "/setup-session/:service" [token service]
        (let [service-kw (keyword service) userinfo (parse-string (slurp (str (service-kw service-urls) "?access_token=" token)) true)
              email (:email userinfo)]

            (s/put! :user {:id email :logged-via service-kw})
            (r/json {:userExists (user-exists email)})))

    (GET "/oauth/twitter/login" []
        (let [request-token (oauth/request-token consumer "http://localhost:3000/oauth/twitter/callback")]
            (s/flash-put! :twitter-req-token request-token)
            (r/redirect (oauth/user-approval-uri consumer
                            (:oauth_token request-token)))))

    (GET "/oauth/twitter/callback" [oauth_token oauth_verifier]
        (let [access-token-response (oauth/access-token consumer
                                      (s/flash-get :twitter-req-token)
                                      oauth_verifier)
              uid (:user_id access-token-response)]

            (r/redirect
              (let [potential-user (user-exists uid)]
                (s/put! :user {:id (or (:email (:data potential-user)) uid)
                               :logged-via :twitter})
                (if potential-user "/dashboard" "/new-user")))))

    ; Pages
    (restricted GET "/new-user" []
        (layout {:main (new-user-tpl {:show-email-info (= :twitter (:logged-via (s/get :user)))})
                 :scripts (js ["/js/new-car.js"])}))

    (restricted GET "/logout" []
        (s/clear!)
        (r/redirect "/")))
