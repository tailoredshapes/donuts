(ns graph.core-test
  (:require [clojure.test :refer :all]
            [graph.core :refer :all]
            [com.walmartlabs.lacinia :refer [execute]]
            [graph.util :as u]))

(deftest returns-a-graph
  (let [data (u/slurpj "../data/src/5e-SRD-Races.json")
        resolvers (resolver-map data)
        schema (load-schema "resources/schema.edn" resolvers)
        query "{by_index(index: \"dwarf\") {name speed size}}"
        expected {:data {:by_index {:name "Dwarf", :speed 25, :size "Medium"}}}]
    (is (= expected (u/q schema query)))))



