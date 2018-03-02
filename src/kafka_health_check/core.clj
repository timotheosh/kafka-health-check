(ns kafka-health-check.core
  (:require [clojure.java.jmx :as jmx])
  (:gen-class
   :name cljkafka.core
   :methods [#^{:static true}
             [GET] [String] java.util.HashMap]))

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
         :body {:under-replicated-partitions up
                :offline-partitions op}}))
    (catch java.io.IOException e
      {:status 503
       :header "Content-Type: text/plain"
       :body (str "Failed to Connect!")})
    (catch Exception e (str "caught exception: " (.toString e)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
