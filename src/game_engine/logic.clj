(ns game-engine.logic
  (:gen-class))

(defn new-game-state []
  {:paddles []
   :balls []
   :bricks []
   :system-time (System/currentTimeMillis)
   :inputs #{}})

; UPDATING

(defn update-paddles [old-state time-delta]
  old-state)

(defn update-balls [old-state time-delta]
  old-state)

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

(defn tick-game-state [old-state system-time]
  (let [system-time ()]
    (-> old-state
      (calc-new-state time-delta)
      (assoc :system-time system-time))))
