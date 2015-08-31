(ns game-engine.draw
  (:gen-class)
  (:require [quil.core :as q]))

(defn filter-paddles [state])

(defn filter-balls [state])

(defn filter-bricks [state])

(defn render-filter [state]
  {:paddles (filter-paddles state)
   :balls (filter-balls balls)
   :bricks (filter-bricks bricks)})

(defn draw-paddle [paddle]
  ; expecting a "paddle" to be a map like: {:x 0 :y 0 :w 0 :h 0}
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

(defn draw [state]
  (let [{:keys [paddles balls bricks]} (render-filter state)]
    (dorun (map draw-paddle paddles))
    (dorun (map draw-ball balls))
    (dorun (map draw-brick bricks))))
