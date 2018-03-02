(defproject kafka-health-check "0.1.0-SNAPSHOT"
  :description "Kafka Health Check Module for Ancillary"
  :url "https://github.com/timotheosh/kafka-health-check"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/java.jmx "0.3.4"]]
  :main ^:skip-aot kafka-health-check.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
