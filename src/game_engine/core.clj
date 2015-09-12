;; # Functional Breakout 
;; This is a basic game engine implemented as a clone of Breakout.
;; It's primarily functional in nature.
;; I don't have a clue what I'm doing, to be honest, but hey.

(ns game-engine.core
  (:gen-class)
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [game-engine.setup :as s]
            [game-engine.update :as u]
            [game-engine.draw :as d]))

;; VALUES

;; The screen width. 

(def screen-width
  640)

;; The screen height.

(def screen-height
  480)

;; Inits.

(def initial-ecsdb
  ; array of facts
  [; balls
   ["ball" :position/x 280]
   ["ball" :position/y 200]
   ["ball" :velocity/vx 0]
   ["ball" :velocity/vy 0]
   ["ball" :size/w 40]
   ["ball" :size/h 40]
   ["ball" :damage/points 1]
   ["ball" :solidity/enabled true]
   ; paddle
   ["paddle" :position/x 240]
   ["paddle" :position/y 360]
   ["paddle" :velocity/vx 0]
   ["paddle" :velocity/vy 0]
   ["paddle" :size/w 160]
   ["paddle" :size/h 40]
   ["paddle" :solidity/enabled true]
   ; bricks
   ["brick" :position/x 40]
   ["brick" :position/y 40]
   ["brick" :size/w 80]
   ["brick" :size/h 40]
   ["brick" :health/points 1]
   ["brick" :solidity/enabled true]])

;; FUNCTIONS

;; Initialize the Quil sketch and define our initial game state.

(defn setup
  "Set up our sketch and our initial state."
  []
  (q/smooth)
  (q/frame-rate 60)
  (q/color-mode :hsb)
  (q/background 255)

  (s/create-initial-state initial-ecsdb))

;; Update the state for every tick of the Quil drawing loop.

(defn update [state]
  (u/update-state state))

;; Draw the state for every tick of the Quil drawing loop.

(defn draw [state]
  (d/draw-state state))

;; Call this function whenever a key is pressed.
;; The current state and the tripped event is passed in.

; (defn key-pressed [state event]
;   (let [key (:key event)]
;     (assoc state :input key)))

;; Define our Quil sketch.

(defn make-sketch []
  (q/sketch
    :title "breakout"
    :size [screen-width screen-height]
    :setup setup
    :update update
    :draw draw
    ; :key-pressed key-pressed
    :features [:keep-on-top
               :exit-on-close]
    :middleware [m/fun-mode]))

;; The entry point for the program.
;; At the moment, it just calls make-sketch.

(defn -main
  "I don't do a whole lot. Yet."
  [& args]
  (make-sketch))
