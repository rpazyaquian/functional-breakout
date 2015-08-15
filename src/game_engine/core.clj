(ns game-engine.core
  (:gen-class)
  (:require [game-engine.game-loop :as game-loop]))

(def map-height 512)
(def map-width 256)

(def screen-height 640)
(def screen-width 360)

(defn make-paddle [map-height map-width paddle-height paddle-width]
  (let [x (center map-width paddle-width)
        y (margin map-height paddle-height margin)]
    {:x x
     :y y
     :width paddle-width
     :height paddle-height}))

; what IS my initial state?
; i have one or more paddles,
; i have one or more balls,
; i have one or more bricks.
(def initial-state
  (let [initial-paddle (make-paddle map-height map-width 64 16)])
  {:paddles [initial-paddle]
   :ball [initial-ball]
   :bricks initial-bricks})

(def inputs [:a :right])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [game-engine game-loop/game-engine]
    (game-engine initial-state inputs)))

; how's rendering gonna be handled?
; are we gonna block it?
; i think the best thing to do is to create a sketch
; and have it take stuff off the queue.
