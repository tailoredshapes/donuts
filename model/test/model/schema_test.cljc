(ns model.schema-test
  (:require [model.schema :refer :all]
            [clojure.test :refer [deftest is testing]]
            [malli.core :as m]
            [clojure.data.json :as json]
            [clojure.java.io :as io]))

(def races (-> (io/file "../data/src/5e-SRD-Races.json")
               slurp
               (json/read-str :key-fn keyword)))

(def languages (-> (io/file "../data/src/5e-SRD-Languages.json")
               slurp
               (json/read-str :key-fn keyword)))

(deftest can-parse-a-race
  (let [dwarf (first races)]
    (is (m/validate Role dwarf))))

(deftest can-parse-all-races
  (is (empty? (filter false? (map (partial m/validate Role) races)))))


(deftest can-parse-all-languages
  (is (empty? (filter false? (map (partial m/validate Language) languages)))))