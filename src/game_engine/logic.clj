(ns game-engine.logic
  (:gen-class))

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

(defn new-game-state []
  {:paddles [{:x 320
              :y 100
              :w 40
              :h 10}]
   :balls [{:x 320
            :y 50
            :vx 0
            :vy 0
            :d 10}]
   :bricks []
   :system-time (System/currentTimeMillis)
   :inputs #{}})

; UPDATING

(defn movement-system [moveable time-delta]
  (let [{:keys [x y]}]))

(defn update-paddles [old-state time-delta]
  old-state)

(defn update-ball [ball time-delta]
  (-> ball
      (movement-system time-delta)))

(defn update-balls [old-state time-delta]
  (let [old-balls (:balls old-state)
        new-balls (map #(update-ball % time-delta) old-balls)]))

(defn update-bricks [old-state time-delta]
  old-state)

(defn update-entities [old-state time-delta]
  (-> old-state
      (update-paddles time-delta)
      (update-balls time-delta)
      (update-bricks time-delta)))

(defn calc-new-state [old-state time-delta]
  (-> old-state
      (update-entities time-delta)))

; TOP-LEVEL

(defn tick-game-state [old-state]
  (let [system-time (System/currentTimeMillis)
        time-delta (- system-time (:system-time old-state))]
    (-> old-state
      (calc-new-state time-delta)
      (assoc :system-time system-time))))
