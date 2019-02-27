(ns palivovnik.validators.defs
  (:require [noir.validation :as v]))

(defn valid-signup-data? [email car]
  (v/rule (v/is-email? (or email ""))
    [:user.email "Zadejte platný email."])
  (v/rule (v/has-value? (:name (:marque car)))
    [:car.marque.name "Vyberte značku vozidla."])
  (v/rule (v/has-value? (:type car))
    [:car.type "Vyberte typ vozidla."])
  (v/rule (v/has-value? (:fuel (:engine car)))
    [:car.engine.fuel "Vyberte typ paliva."])
  (v/rule (v/has-value? (:configuration (:engine car)))
    [:car.engine.configuration "Vyberte konfiguraci válců v motoru."])
  (v/rule (v/has-value? (:cylinders (:engine car)))
    [:car.engine.cylinders "Zadejte počet válců."])
  (v/rule (v/has-value? (:displacement (:engine car)))
    [:car.engine.displacement "Zadejte objem motoru."])
  (v/rule (v/has-value? (:type (:transmission car)))
    [:car.transmission.type "Vyberte typ převodovky."])
  (v/rule (v/has-value? (:gears (:transmission car)))
    [:car.transmission.gears "Zadejte počet převodových stupňů."])
  (v/rule (> (or (:year car) 0) 1949)
    [:car.year "Rok výroby vozidla nemůže být nižší než 1950."])

  (not (v/errors?)))

(defn valid-fillup-data? [fillup]
  (v/rule (v/has-value? (:date fillup))
    [:fillup.date "Zadejte datum tankování."])
  (v/rule (v/has-value? (:odo fillup))
    [:fillup.odo "Zadejte stav počítadla km."])

  (v/rule (not-any? nil? (reduce concat (map vals (:phases fillup))))
    [:phases "Fáze tankování musí mít vyplněna všechna pole."])

  (not (v/errors?)))
