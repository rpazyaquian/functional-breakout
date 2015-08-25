(ns game-engine.core
  (:gen-class)
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [game-engine.logic :as l]))

(def screen-width
  640)

(def screen-height
  480)

(defn draw-paddle [paddle]
  (let [{:keys [x y w h]} paddle]
    (q/fill 0)
    (q/rect x y w h)))

(defn draw-ball [ball]
  (let [{:keys [x y d]} ball]
    (q/fill 0)
    (q/ellipse x y d d)))

(defn draw-brick [brick]
  (let [{:keys [x y w h]} brick]
    (q/fill 0)
    (q/rect x y w h)))

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
        time-delta (- system-time (:system-time state))
        new-state (l/tick-game-state state (:input state) time-delta)]
    (assoc new-state :time system-time :time-delta)))

(defn draw [state]
  (let [{:keys [paddles balls bricks]} state]
    (q/background 255)
    (dorun (map draw-paddle paddles))
    (dorun (map draw-ball balls))
    (dorun (map draw-brick bricks))))

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
