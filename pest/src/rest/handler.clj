(ns rest.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Pizza
  {:name s/Str
   (s/optional-key :description) s/Str
   :size (s/enum :L :M :S)
   :origin {:country (s/enum :FI :PO)
            :city s/Str}})

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Rest"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/api" []
      :tags ["api"]

      (GET "/" []
        :return [s/Str]
        :summary "lists url of latest version of all documents"
        (ok "OK"))

      (GET "/:id" [id]
        :return [s/Str]
        :summary "lists all version of a document"
        (ok "OK"))

      (GET "/:version/:id" [version id]
        :return Pizza
        :summary "returns a document"
        (ok "OK"))

      (POST "/" []
        :body [pizza Pizza]
        :summary "adds a document"
        (temporary-redirect "url"))

      (POST "/:version/:id" [id]
        :body [pizza Pizza]
        :summary "updates a document"
        (permanent-redirect "url")))))
