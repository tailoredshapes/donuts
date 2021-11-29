(defproject graph "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [com.walmartlabs/lacinia-pedestal "0.15.0"]
                 [org.clojure/data.json "2.4.0"]
                 [com.outpace/config "0.13.5"]
                 [compojure "1.6.2"]
                 [ring "1.8.1"]
                 [ring/ring-jetty-adapter "1.8.1"]]

  :aot [graph.server]
  :main graph.server

  :repl-options {:init-ns graph.core})
