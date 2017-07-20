(defproject deploy-api "0.1.0-SNAPSHOT"
  :description "Deployment API"
  :url "https://deploy-api.spsprod.in"
  :plugins [[lein-ring "0.8.11"]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.3.4"]
                 [liberator "0.5.0"]
                 [sandbar "0.4.0-SNAPSHOT"]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-jetty-adapter "1.1.0"]]
  :dev-dependencies [[lein-ring "0.7.3"]]
  :ring {:handler deploy-api.server/handler}
  :main deploy-api.server)
