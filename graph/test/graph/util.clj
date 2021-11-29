(ns graph.util
  (:require [clojure.test :refer :all]
            [com.walmartlabs.lacinia :as lacinia]
            [clojure.walk :as walk]
            [clojure.data.json :as json]
            [clojure.java.io :as io])
  (:import (clojure.lang IPersistentMap)))

(defn simplify
  "Converts all ordered maps nested within the map into standard hash maps, and
   sequences into vectors, which makes for easier constants in the tests, and eliminates ordering problems."
  [m]
  (walk/postwalk
    (fn [node]0
      (cond
        (instance? IPersistentMap node)
        (into {} node)

        (seq? node)
        (vec node)

        :else
        node))
    m))

(defn q
  [schema query-string]
  (simplify (lacinia/execute schema query-string nil nil)))

(defn slurpj [file]
  (-> (io/file file)
      slurp
      (json/read-str :key-fn keyword)))

