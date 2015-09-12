(ns game-engine.setup
  (:gen-class)
  (:require [clj-yaml.core :as yaml]))

(defn create-initial-state
  "Create an initial state given an initial ECSDB of facts."
  [ecsdb]
  {:inputs #{}
   :system-time 0
   :time-delta 0
   :ecsdb ecsdb})
