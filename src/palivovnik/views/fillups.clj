(ns palivovnik.views.fillups
    (:use palivovnik.data.cars
          palivovnik.utils
          net.cgrand.enlive-html))

(defsnippet fillup "templates/fillup.htm" [:div#main-content]
    [{:keys [id]}]
            
    [:#fuelselect] (content (options fillup-fuels))
    [:#fid] (set-attr :value id))

(defsnippet import-fillups "templates/import.htm" [:div#import]
            []            
            [:#dsource] (content (options {:fuelly        "Fuelly.com"
                                           :spritmonitor  "Spritmonitor.de"})))
