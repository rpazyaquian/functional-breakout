(ns game-engine.draw
  (:gen-class)
  (:require [quil.core :as q]))

; draw functions

(defn component-in-list [components fact]
  (println fact))

(defn facts-to-rectangles [facts]
  (println facts)
  (->> facts
      (filter #(component-in-list ["position" "size"] %))))

(defn render-filter
  "Filters the ecsdb into something the renderer can consume."
  [ecsdb]
  (facts-to-rectangles ecsdb))

; (defn draw-rectangle
;   "Draws a rectangle."
;   [rectangle]
;   (let [{:keys [x y w h]} rectangle]
;     (q/fill 0)
;     (q/rect x y w h)))

; (defn draw-rectangles
;   "Draws rectangles."
;   [rectangles]
;   (dorun (map draw-rectangle rectangles)))

(defn draw-state [state]
  (render-filter (:ecsdb state))
  state)