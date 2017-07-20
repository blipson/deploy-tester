(ns deploy-api.server
  (:use [liberator.representation :only [wrap-convert-suffix-to-accept-header]]
        [ring.middleware.multipart-params :only [wrap-multipart-params]]
        ring.middleware.session.memory
        sandbar.stateful-session
        compojure.core
        [compojure.handler :only [api]]
        deploy-api.resources)
  (:require
   [clojure.data.json :as json]
   [ring.adapter.jetty :as jetty]))

(defonce my-session (atom {}))

(defn assemble-routes []
  (routes
   (GET "/" [] home)))

(defn create-handler []
  (fn [request]
    ((->
      (assemble-routes)
      api
      wrap-multipart-params
      (wrap-stateful-session {:store (memory-store my-session)})
      (wrap-convert-suffix-to-accept-header
       {".html" "text/html"
        ".txt" "text/plain"
        ".xhtml" "application/xhtml+xml"
        ".xml" "application/xml"
        ".json" "application/json"}))
     request)))

(defn handler [request]
  ((create-handler) request))

(defn start [options]
  (jetty/run-jetty
   (fn [request]
     ((create-handler) request))
   (assoc options :join? false)))

(defn -main
  ([port]
   (start {:port (Integer/parseInt port)}))
  ([]
   (-main "8000")))
