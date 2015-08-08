(ns game-engine.game-loop
  (:require [game-engine.processing-pipeline :as pipeline]))

(defn loop-action
  "Actual things that happen each time the loop is called."
  [final-state inputs final-time]
  (println final-state)
  (println final-time)
  (recur final-state inputs final-time))

(defn game-loop
  "Loops game state and time delta back in on itself."
  [initial-state inputs pipeline]
  (loop [initial-state initial-state
         inputs inputs
         initial-time (System/currentTimeMillis)]
    (println initial-state)
    (println initial-time)
    (let [final-time (System/currentTimeMillis)
          time-delta (- final-time initial-time)
          final-state (pipeline initial-state inputs time-delta)]
      (loop-action final-state inputs final-time))))

(defn game-engine
  "Setup and coordination for the game loop."
  [initial-state inputs]
  (let [pipeline pipeline/processing-pipeline]
    (game-loop initial-state inputs pipeline)))
