(ns game-engine.core
  (:gen-class)
  (:require [game-engine.game-loop :as game-loop]
            [quil.core :as q]
            [quil.middleware :as m]))

(def initial-state {:player {:x 0
                             :y 0
                             :jumping true}})

(def inputs [:a :right])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [game-engine game-loop/game-engine]
    (game-engine initial-state inputs)))
