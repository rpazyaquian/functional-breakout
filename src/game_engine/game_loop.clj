(ns game-engine.game-loop
  (:require [game-engine.processing-pipeline :as pipeline]))

(defn game-loop
  "Loops the results of the processing pipeline back in on itself."
  [initial-state inputs]
  (let [pipeline pipeline/processing-pipeline]
    (loop [initial-state initial-state
           inputs inputs
           initial-time (System/currentTimeMillis)]
      (let [final-time (System/currentTimeMillis)
            time-delta (- final-time initial-time)
            final-game-state (pipeline initial-state inputs time-delta)]
        ; here you send stuff to the renderer
        ; or just print the state or whatever
        (println final-game-state)
        (println final-time)
        (recur final-game-state inputs final-time)))))
