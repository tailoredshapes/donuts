(ns model.core
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]
            [malli.provider :as mp]))

(def data-dir "../data/src")

(def json-files (filter (fn [x] (re-matches #".*\.json"
                                            (.getName x)))
                        (file-seq (io/file data-dir))))

(def j (zipmap (map (fn [x] (second (re-matches #".*SRD-(.*)\.json$" (.getName x))))
                    json-files)
               (map (fn [x]
                      (-> x
                          slurp
                          (json/read-str :key-fn keyword)))
                    json-files)))

(def schema (reduce-kv (fn [m k v] (assoc m k (mp/provide v)))
                       {} j
                       ))