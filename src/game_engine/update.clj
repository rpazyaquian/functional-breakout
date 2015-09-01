(ns game-engine.update
  (:gen-class))

(defn get-new-ecsdb
  "Gets a new ECSDB state given old ecsdb, inputs, system-time, and time delta."
  [ecsdb inputs system-time time-delta]
  ecsdb)

(defn update-state
  "Takes an old state and returns a new state."
  [old-state]
  (let [new-system-time (System/currentTimeMillis)
        new-time-delta (- new-system-time (:system-time old-state))
        new-ecsdb (get-new-ecsdb (:ecsdb old-state) (:inputs old-state) new-system-time new-time-delta)]
    (-> old-state
        (assoc :system-time new-system-time)
        (assoc :time-delta new-time-delta)
        (assoc :ecsdb new-ecsdb)
        (identity))))