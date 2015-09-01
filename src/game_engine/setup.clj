(ns game-engine.setup
  (:gen-class))

; build functions

(defn create-ecs-state
  "Create an ECS state given a map of entities and a map of components."
  [entities components]
  (merge entities components))

(defn create-initial-state
  "Create an initial state given initial entities and initial components."
  [initial-entities initial-components]
  {:inputs #{}
   :system-time 0
   :ecs-state (create-ecs-state initial-entities initial-components)})
