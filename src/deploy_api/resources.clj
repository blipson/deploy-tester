(ns deploy-api.resources
  (:use hiccup.page
        hiccup.element
        hiccup.form
        sandbar.stateful-session
        [liberator.core :only [defresource]])
  (:require [clojure.data.json :as json]))

(defresource home
  :available-media-types ["application/json"]
  :available-charsets ["utf-8"]
  :handle-ok (json/write-str {:a 1 :b 2}))
