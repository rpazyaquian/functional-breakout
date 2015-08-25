(ns game-engine.core
  (:gen-class)
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [game-engine.logic :as l]
            [game-engine.draw :as d]))

(def screen-width
  640)

(def screen-height
  480)

(defn setup []
  (q/smooth)
  (q/frame-rate 10)
  (q/color-mode :hsb)
  (q/background 255)

  (l/new-game-state))

(defn update [state]
  ; state looks like
  ; {:paddles []
  ;  :balls []
  ;  :bricks []
  ;  :system-time 0
  ;  :input #{}}

  (let [system-time (System/currentTimeMillis)
        new-state (l/tick-game-state state)]
    new-state))

(defn draw [state]
  (let [{:keys [paddles balls bricks]} state]
    (q/background 255)
    (dorun (map d/draw-paddle paddles))
    (dorun (map d/draw-ball balls))
    (dorun (map d/draw-brick bricks))))

(defn key-pressed [state event]
  (let [key (:key event)]
    (assoc state :input key)))

(defn make-sketch []
  (q/sketch
    :title "breakout"
    :size [screen-width screen-height]
    :setup setup
    :update update
    :draw draw
    :key-pressed key-pressed
    :features [:keep-on-top
               :exit-on-close]
    :middleware [m/fun-mode]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (make-sketch))
