(ns palivovnik.views.cars
    (:use palivovnik.data.cars
          palivovnik.utils
          net.cgrand.enlive-html))

(defn format-if-not-void [n dec-places]
  (if n (format (str "%." dec-places "f") (float n)) "--"))

(defsnippet dashboard "templates/dashboard.htm" [:div#main-content]
    [{:keys [car permalink]}]

    [:#carname]     (set-attr :car-id (:id car))            
    [:#cons]        (content (format-if-not-void (:average-cons (:data car)) 1))
    [:#pricePerKm]  (content (format-if-not-void (:average-price-km (:data car)) 2)) 
    [:#pricePerL]   (content (format-if-not-void (:average-price-l (:data car)) 2))
    [:#permalink]   (set-attr :href permalink))

