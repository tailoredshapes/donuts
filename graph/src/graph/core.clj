(ns graph.core
  (:require
    [clojure.java.io :as io]
    [com.walmartlabs.lacinia.util :as util]
    [com.walmartlabs.lacinia.schema :as schema]
    [clojure.edn :as edn]
    [clojure.data.json :as json]
    [outpace.config :refer [defconfig]]))

(defconfig schema-file "resources/schema.edn")
(defconfig data-file "../data/src/5e-SRD-Races.json")

(def data (-> (io/file data-file)
              slurp
              (json/read-str :key-fn keyword)))

(defn resolver-map
  []
  {:query/by-index (fn [context args value]
                       {:index 1})
   :link (fn [context args value] "Found link")})

(defn load-schema
  []
  (-> (io/file schema-file)
      slurp
      edn/read-string
      (util/attach-resolvers (resolver-map))
      schema/compile))



