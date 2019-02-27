(ns palivovnik.db.fillups_tests
  (:require [palivovnik.db.fillups :as f]
            [midje.sweet :refer :all]))

(facts "avg-cons-test"
    (fact "Avg cons with evenly distributed volume should be pure arithmetic average"
          (f/avg-consumption [{:cons 10 :phases [{:volume 5}] :distance 5}
                              {:cons 20 :phases [{:volume 5}] :distance 5}]
                             10) => 15.0)


    (fact "Weighed average, towards the higher one" 
          (f/avg-consumption [{:cons 10 :phases [{:volume 5}] :distance 5}
                              {:cons 20 :phases [{:volume 10}] :distance 10}]
                             15) => (float (/ 50 3)))


    (fact "Weighed average, towards the lower one"
          (f/avg-consumption [{:cons 10 :phases [{:volume 10}] :distance 10}
                              {:cons 20 :phases [{:volume 4} {:volume 1}] :distance 5}]
                             15) => (float (/ 40 3))))

(fact "distance computation works for diff. fillups"
      (f/compute-distance {:_id 1 :odo 100} {:_id 2 :odo 50}) => 50)

(fact "distance computation works for edited fillup"
      (f/compute-distance {:_id 1 :odo 100} {:_id 1 :odo 98 :distance 300}) => 302)

(fact "Price per km is computed correctly"
      (f/avg-price-per-km 140 529) 
      => (float (/ 529 140)))

(fact "Price per l is computed correctly"
      (f/avg-price-per-l 
        [{:cons 10 :phases [{:perunit 35.8 :volume 10}] :total 358 :distance 100}
         {:cons 20 :phases [{:perunit 36 :volume 4} 
                            {:perunit 27 :volume 1}] :total 171 :distance 40}]
        529) 
      => (float (/ 529 15)))

(fact "Fillup consumption, based on the kms driven"
    (f/calculate-fillup-cons {:phases [{:volume 10} {:volume 50}] :odo 1600} 
                                        600) => 10.0)

(fact "Calculation of consumption for multiple sequential fillups"
      (f/calculate-multiple-fillup-conses 
        {:odo 120} 
        [{:_id 1 :date "01.01.1970" :odo 600 :phases 
          [{:perunit 36 :volume 50} {:perunit 36 :volume 10}]}
         {:_id 2 :date "02.01.1970" :odo 1000 :phases 
          [{:perunit 36 :volume 35} {:perunit 36 :volume 12}]}]) 
      
      => [{:_id 1 :date "01.01.1970" :odo 600 :phases 
           [{:perunit 36 :volume 50} {:perunit 36 :volume 10}] 
           :cons 12.5 :distance 480 :total 2160}
          {:_id 2 :date "02.01.1970" :odo 1000 :phases 
           [{:perunit 36 :volume 35} {:perunit 36 :volume 12}] 
           :cons 11.75 :distance 400 :total 1692}])

(fact "Calculation of consumption for multiple sequential fillups - with missing"
      (f/calculate-multiple-fillup-conses 
        {:odo 120} 
        [{:_id 1 :date "01.01.2014" :odo 1000 :phases 
          [{:perunit 36 :volume 35} {:perunit 36 :volume 12}] :missed true}
         {:_id 2 :date "29.10.2013" :odo 600 :phases 
          [{:perunit 36 :volume 50} {:perunit 36 :volume 10}]}]) 
      
      => [{:_id 2 :date "29.10.2013" :odo 600 :phases 
           [{:perunit 36 :volume 50} {:perunit 36 :volume 10}] 
           :cons 12.5 :distance 480 :total 2160}
          {:_id 1 :date "01.01.2014" :odo 1000 :phases 
           [{:perunit 36 :volume 35} {:perunit 36 :volume 12}] 
           :missed true }])
