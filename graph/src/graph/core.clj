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
  [d]
  {:query/by-index (fn [_ {i :index} _]
                     (first (filter #(= (:index %) i) d)))
   :link           (fn [context args value] "Found link")})

(defn load-schema
  [f rm]
  (-> (io/file f)
      slurp
      edn/read-string
      (util/attach-resolvers rm)
      schema/compile))



