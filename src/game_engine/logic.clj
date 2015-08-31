(ns game-engine.logic
  (:gen-class)
  (:require [game-engine.physics :as p]))

; INITS

;; Define samples of our components and entities.

(def position-component
  {:entity "entity"
   :x 0
   :y 0})

(def velocity-component
  {:entity "entity"
   :vx 0
   :vy 0})

(def rectangle-size-component
  {:entity "entity"
   :w 40
   :h 40})

(def circle-size-component
  {:entity "entity"
   :d 10})

;; Define our initial entities and components.

(def initial-entities
  {:entities ["paddle"
              "ball"
              "brick"]})

(def initial-components
  ;; key concept: **struct of arrays**
  {:position-components [{:entity "paddle"
                          :x 0
                          :y 0}
                         {:entity "ball"
                          :x 0
                          :y 0}
                         {:entity "brick"
                          :x 0
                          :y 0}]
   :velocity-components [{:entity "paddle"
                          :vx 0
                          :vy 0}
                         {:entity "ball"
                          :vx 0
                          :vy 0}]
   :rectangle-size-components [{:entity "paddle"
                                :w 40
                                :h 10}
                               {:entity "brick"
                                :w 40
                                :h 10}]
   :circle-size-components [{:entity "ball"
                             :d 10}]})

;; Put together a new game state, usually for starting a new game.

(defn new-game-state []
  (merge initial-components
         initial-entities
         {:system-time (System/currentTimeMillis)
          :inputs #{}}))

; UPDATING

(defn calc-new-state [old-state time-delta]
  (-> old-state
      (p/physics-system time-delta)))

; TOP-LEVEL

(defn tick-game-state [old-state]
  (let [system-time (System/currentTimeMillis)
        time-delta (- system-time (:system-time old-state))]
    (-> old-state
        (calc-new-state time-delta)
        (assoc :system-time system-time))))
