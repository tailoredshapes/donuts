(ns graph.server
  (:gen-class)
  (:require [compojure.core :refer [defroutes POST GET context]]
            [compojure.route :as route]
            [cheshire.core :as cc]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware
             [keyword-params :refer [wrap-keyword-params]]
             [params :refer [wrap-params]]]
            [ring.util.response :as response]
            [clojure.string :as str]
            [com.walmartlabs.lacinia :refer [execute]]
            [graph.core :as gc]
            [outpace.config :refer [defconfig]]
            [clojure.data.json :as json]
            [clojure.java.io :as io]))


(defonce server nil)

(defconfig schema-file "../schema/schema.edn")
(defconfig data-file "../data/src/5e-SRD-Races.json")
(defconfig port 7070)

(def data (-> (io/file data-file)
              slurp
              (json/read-str :key-fn keyword)))

(defn extract-query
  [request]
  (case (:request-method request)
    :get (get-in request [:query-params "query"])
    :post (slurp (:body request))
    :else ""))


(defn variable-map
  [request]
  (let [vars (get-in request [:query-params "variables"])]
    (if-not (str/blank? vars)
      (cc/parse-string vars true)
      {})))


(defn handle-req
  [request compiled-schema]
  (let [query (extract-query request)
        vars (variable-map request)
        result (execute compiled-schema query vars nil)
        status (if (-> result :errors seq)
                 400
                 200)]
    {:status status
     :headers {"Content-Type" "application/json"}
     :body (cc/generate-string result)}))


(defn api-routes
  [compiled-schema]
  (defroutes routes

             (GET "/healthcheck" [] "OK")

             ;; Context to handle GraphQL Queries
             (context "/graphql" []
                      (GET "/" req
                           (handle-req req compiled-schema))

                      (POST "/" req
                            (handle-req req compiled-schema)))

             ;; Resources routes for GraphiQL IDE
             (GET "/graphiql" []
                  (response/resource-response "index.html" {:root "public/graphiql"}))

             (route/resources "/" {:root "public/graphiql"})

             (route/not-found "Not Found")))


(defn app
  [compiled-schema]
  (-> (api-routes compiled-schema)
      wrap-keyword-params
      wrap-params))


(defn start-server
  [port]
  (let [rm (gc/resolver-map data)
        compiled-schema (gc/load-schema schema-file rm)
        server (run-jetty (app compiled-schema)
                          {:port port
                           :join? false})]
    (alter-var-root #'server (constantly server))
    server))


(defn stop-server
  [server]
  (.stop server)
  (alter-var-root #'server (constantly nil)))


(defn -main
  [& args]
  (start-server port))