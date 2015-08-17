(ns game-engine.core
  (:gen-class)
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [game-engine.logic :as l]))

(def screen-width
  380)

(def screen-height
  640)

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

(defn update-state [state]
  (let [{:keys [paddles balls bricks]} (l/tick-game-state state (:input state))]
    {:paddles paddles
     :balls balls
     :bricks bricks}))

(defn draw-state [state]
  (let [{:keys [paddles balls bricks]} state]
    (q/background 255)
    (dorun (map draw-paddle paddles))
    (dorun (map draw-ball balls))
    (dorun (map draw-brick bricks))))

(defn key-pressed [state event]
  (let [key (:key event)]
    ; add key to input
    (assoc state :input key)))

(defn make-sketch []
  (q/sketch
    :title "breakout"
    :size [screen-width screen-height]
    :setup setup
    :update update-state
    :draw draw-state
    :key-pressed key-pressed
    :features [:keep-on-top
               :exit-on-close]
    :middleware [m/fun-mode]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (make-sketch))
