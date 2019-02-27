(ns palivovnik.import_tests
  (:require [midje.sweet :refer :all]
            [palivovnik.import :refer :all]))

(facts "Imported data conversion"
    (fact "Fuelly data is converted to the right format"
          (fuellups-from-csv  :fuelly 
                              [["car_name" "model" "l/100km" "odometer" "km" "litres" "price" "city_percentage" "fuelup_date" "date_added" "tags" "notes" "missed_fuelup" "partial_fuelup" "latitude" "longitude"]
                               ["Griffin" "Saab 9-5" "0.0" "87432.0" "0.0" "55.782" "34.500" "25" "2011-08-19" "2011-08-19 10:43:17" "" "N95" "0" "0" "49.6228915141" "18.4069422647"]
                               ["Griffin" "Saab 9-5" "10.2" "87938.0" "506.0" "51.440" "35.500" "60" "2011-08-23" "2011-08-24 22:58:56" "" "" "1" "0" "" ""]]) 
          => [{:date (java.util.Date. 1313712000000) :odo 87432 :phases [{:perunit 34.5 :volume 55.782}] :city_driving 25 :loc [18.4069422647 49.6228915141] :missed false}
              {:date (java.util.Date. 1314057600000) :odo 87938 :phases [{:perunit 35.5 :volume 51.44}] :city_driving 60 :missed true}])

    (fact "Spritmonitor data is converted to the right format"
          (fuellups-from-csv  :spritmonitor
                              [["Date" "Odometer" "Trip" "Quantity" "Total price" "Currency" "Type" "Tires" "Roads" "Driving style" "Fuel" "Note" "Consumption"]
                               ["11.01.2014" "12345,00" "12345,00" "50,00" "1750,00" "CZK" "3" "0" "0" "0" "15" "" "0,00"]
                               ["11.01.2014" "12645,00" "300,00" "30,00" "1110,00" "CZK" "1" "0" "0" "0" "15" "" "10,00"]]) 
          => [{:date (java.util.Date. 1389398400000) :odo 12345 :phases [{:perunit 35 :volume 50}] :missed true}
              {:date (java.util.Date. 1389398400000) :odo 12645 :phases [{:perunit 37 :volume 30}] :missed false}])) 
