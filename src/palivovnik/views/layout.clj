(ns palivovnik.views.layout
    (:use
        net.cgrand.enlive-html
        palivovnik.utils)
    (:require
        [noir.session :as s]))

(deftemplate layout "templates/_layout.htm"
    [{:keys [main scripts]}]

    [:main] (substitute main)
    [:page-scripts] (substitute scripts)
    [:.restricted] (keep-if (and (s/get :user) (current-car-id)))
    [:#ga] (keep-if ((complement nil?)
                     (System/getenv "OPENSHIFT_APP_NAME"))))

(defsnippet index "templates/index.htm" [:div] [])
(defsnippet error "templates/error.htm" [:div]
    [{:keys [error-id]}]
    [:#error-id] (content error-id))

