(defproject game-engine "0.1.0-SNAPSHOT"
  :description "A functional version of Breakout."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [quil "2.2.6"]
                 [circleci/clj-yaml "0.5.4"]]
  :main ^:skip-aot game-engine.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
