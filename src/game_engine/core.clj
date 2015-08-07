(ns game-engine.core
  (:gen-class)
  (:require [game-engine.game-loop :as game-loop]))

(def initial-state {:player {:x 0
                             :y 0
                             :jumping true}})

(def inputs [:a :right])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [game-loop game-loop/game-loop]
    (game-loop initial-state inputs)))
