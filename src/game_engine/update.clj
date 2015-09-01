(ns game-engine.update
  (:gen-class))

; get functions

(defn get-new-process-structure
  "Get a new process structure given an old state."
  [old-state]
  (let [process-structure-template {:old-state {}
                                    :system-time nil
                                    :time-delta nil
                                    :inputs nil
                                    :ecs-state nil
                                    :new-state nil}]
    (assoc process-structure-template :old-state old-state)))

(defn get-time-delta
  "Get time delta given initial time and final time."
  [initial-time final-time]
  (- final-time initial-time))

(defn get-ecs-state
  "Get new ECS state given an inital ECS state, inputs, the current system time, and a time delta."
  [initial-ecs-state inputs system-time time-delta]
  ; this will actually be filled by the specific ECS pipeline!
  initial-ecs-state)

(defn get-new-state
  "Get a new state given a process structure."
  [process-structure]
  (let [{:keys [inputs system-time ecs-state]} process-structure]
    {:inputs (:inputs process-structure)
     :system-time (:system-time process-structure)
     :ecs-state (:ecs-state process-structure)}))

; set functions

(defn set-current-time
  "Set the process structure's current time to the current system time in ms."
  [process-structure]
  (assoc process-structure :system-time (System/currentTimeMillis)))

(defn set-time-delta
  "Set the process structure's time delta to its system time minus the old state's system time."
  [process-structure]
  (let [old-system-time (:system-time (:old-state process-structure))
        system-time (:system-time process-structure)
        time-delta (get-time-delta old-system-time system-time)]
    (assoc process-structure :time-delta time-delta)))

(defn set-ecs-state
  "Set the process structure's ECS state to a new ECS state given the old state's ECS state, time delta, and inputs."
  [process-structure]
  (let [{:keys [inputs system-time time-delta]} process-structure
        old-ecs-state (get-in process-structure [:old-state :ecs-state])
        ecs-state (get-ecs-state old-ecs-state inputs system-time time-delta)]
    (assoc process-structure :ecs-state ecs-state)))

(defn set-new-state
  "Set the process structure's new state to a merging of its components that matches the schema of the old state."
  [process-structure]
  (let [new-state (get-new-state process-structure)]
    (assoc process-structure :new-state new-state)))

(defn update-process-structure
  "Runs the process structure through its update pipeline."
  [process-structure]
  (-> process-structure
      (set-current-time)
      (set-time-delta)
      (set-ecs-state)
      (set-new-state)
      (:new-state)))

(defn update-state [old-state]
  "Takes an old state and returns a new state."
  (let [process-structure (get-new-process-structure old-state)
        new-state (update-process-structure process-structure)]
    new-state))
