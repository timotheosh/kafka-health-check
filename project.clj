(defproject kafka-health-check "0.1.0-SNAPSHOT"
  :description "Kafka Health Check Module for Ancillary"
  :url "https://github.com/timotheosh/kafka-health-check"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [me.raynes/conch "0.8.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [org.clojure/java.jmx "0.3.4"]]
  :main ^:skip-aot kafka-health-check.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
