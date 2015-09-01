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

(def initial-entities
  {:entities ["paddle"
              "ball"
              "brick"]})

(def initial-components
  ;; key concept: **struct of arrays**
  {:positions [{:entity "paddle"
                :x 0
                :y 0}
               {:entity "ball"
                :x 0
                :y 0}
               {:entity "brick"
                :x 0
                :y 0}]
   :velocities [{:entity "paddle"
                 :vx 0
                 :vy 0}
                {:entity "ball"
                 :vx 0
                 :vy 0}]
   :rectangles [{:entity "paddle"
                 :w 40
                 :h 10}
                {:entity "brick"
                 :w 40
                 :h 10}]
   :circles [{:entity "ball"
              :d 10}]})

;; A state looks something like this:
;;
;; ```clojure
;; {:inputs #{}
;;  :system-time 0
;;  :ecs-state {:entities []
;;              :components []}}
;; ```

(def initial-state
  (s/create-initial-state initial-entities initial-components))

;; FUNCTIONS

;; Initialize the Quil sketch and define our initial game state.

(defn setup
  "Set up our sketch and our initial state."
  []
  (q/smooth)
  (q/frame-rate 10)
  (q/color-mode :hsb)
  (q/background 255)

  initial-state)

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
  "I don't do a whole lot ... yet."
  [& args]
  (make-sketch))
