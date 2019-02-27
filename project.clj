(defproject palivovnik "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.5"]
                 [lib-noir "0.4.3"]
                 [enlive "1.1.1"]
                 [com.novemberain/monger "1.6.0"]
                 [clojurewerkz/neocons "2.0.1"]                 
                 [clj-time "0.6.0"]
                 [clj-oauth "1.4.0"]
                 [robert/hooke "1.3.0"]
                 [digest "1.4.3"]
                 [midje "1.6.2" :scope "test"]
                 [org.clojure/data.csv "0.1.2"]
                 [org.clojure/algo.generic "0.1.2"]]

  :plugins [[lein-ring "0.8.2"]
            [lein-swank "1.4.5"]]

  :ring {:handler palivovnik.app/handler
         :init palivovnik.init/on-start}
  
  :war-resources-path "resources/public"
)
