(ns game-engine.draw
  (:gen-class)
  (:require [quil.core :as q]))

; get functions

(defn get-rectangles
  "Creates rectangles given the entities and components of a state. Requires: entity keys, 2d positions, 2d sizes (rectangles)."
  [state]
  (let [{:keys [positions rectangles]} state]
    (clojure.set/join positions rectangles)))

(defn get-circles
  "Creates circles given the entities and components of a state. Requires: entity keys, 2d positions, diameters (circles)."
  [state]
  (let [{:keys [positions circles]} state]
    (clojure.set/join positions circles)))

; draw functions

(defn draw-rectangle
  "Draws a rectangle."
  [rectangle]
  (let [{:keys [x y w h]} rectangle]
    (q/fill 0)
    (q/rect x y w h)))

(defn draw-circle
  "Draws a circle."
  [circle]
  (let [{:keys [x y d]} circle]
    (q/fill 0)
    (q/ellipse x y d d)))

(defn draw-rectangles
  "Draws rectangles."
  [rectangles]
  (dorun (map draw-rectangle rectangles)))

(defn draw-circles
  "Draws circles."
  [circles]
  (dorun (map draw-circle circles)))

(defn draw-state [state]
  (let [ecs-state (:ecs-state state)
        rectangles (get-rectangles ecs-state)
        circles (get-circles ecs-state)]
    (draw-rectangles rectangles)
    (draw-circles circles)
    state))
