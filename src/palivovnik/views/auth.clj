(ns palivovnik.views.auth
    (:use palivovnik.data.cars
          palivovnik.utils
          net.cgrand.enlive-html)
    (:require [clj-time.core :as d]))

(defsnippet new-user-tpl "templates/new-user.htm" [:div#newuser]
    [{:keys [show-email-info]}]

    [:#emailInfo] (keep-if show-email-info)
    [:#carmarques] (append (options (keys marques-models)))
    [:#caryear] (set-attr :max (d/year (d/today)))
    [:#carcountry] (append (options marques-countries))
    [(attr= :ng-model "data.car.engine.fuel")] (content (options fuel-categories))
    [(attr= :ng-model "data.car.engine.configuration")] (content (options engine-configs))
    [(attr= :ng-model "data.car.engine.cylinders")] (content (options engine-cyls))
    [(attr= :ng-model "data.car.engine.aspiration")] (append (options engine-aspirations))
    [(attr= :ng-model "data.car.transmission.type")] (append (options transmissions)))

