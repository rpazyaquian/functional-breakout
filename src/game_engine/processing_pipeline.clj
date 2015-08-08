(ns game-engine.processing-pipeline
  (:gen-class))

(defn processing-pipeline
  "Takes a game state, list of inputs, and time delta, and returns a final game state."
  [initial-state inputs time-delta]
  {:player {:x 1
            :y 1
            :jumping true}})
