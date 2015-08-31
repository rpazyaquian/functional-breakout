(ns game-engine.draw
  (:gen-class)
  (:require [quil.core :as q]))

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

(defn draw [state]
  (let [{:keys [paddles balls bricks]} (render-filter state)]
    (dorun (map draw-paddle paddles))
    (dorun (map draw-ball balls))
    (dorun (map draw-brick bricks))))
