(ns kafka-health-check.core
  (:require [clojure.java.jmx :as jmx]
            [clojure.data.json :refer [write-str]])
  (:gen-class
   :name kafka_health_check.core
   :methods [#^{:static true}
             [GET [String] java.util.HashMap]]))

(defn GET
  [ctx]
  (try
    (jmx/with-connection {:host "localhost" :port 1099}
      (let [up (:Value(jmx/with-connection {:host "localhost" :port 1099}
                        (jmx/mbean "kafka.server:type=ReplicaManager,name=UnderReplicatedPartitions")))
            op (:Value(jmx/with-connection {:host "localhost" :port 1099}
                        (jmx/mbean "kafka.controller:type=KafkaController,name=OfflinePartitionsCount")))]
        {:status
         (if (and (zero? up)
                  (zero? op))
           200
           503)
         :header "Content-Type application/json"
         :body (write-str {:under-replicated-partitions up
                           :offline-partitions op})}))
    (catch java.io.IOException e
      {:status 503
       :header "Content-Type: application/json"
       :body (write-str {:result "Failed to Connect!"})})
    (catch Exception e
      {:status 502
       :header "Content-Type: application/json"
       :body (write-str
              (str "caught exception: " (str e)))})))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (GET ""))
