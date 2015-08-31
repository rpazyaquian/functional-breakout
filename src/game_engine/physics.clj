(ns game-engine.physics
  (:gen-class))

;; Code for handling physics. 

(defn process-position-velocity [position-velocity time-delta]
  (let [{:keys [entity x y vx vy]} position-velocity]
    (merge {:entity entity} (update-position x y vx vy time-delta))))

(defn process-position-velocities [position-velocities time-delta]
  (map #(process-position-velocity % time-delta) position-velocities))

(defn update-physics [positions velocities time-delta]
  (let [position-velocities (clojure.set/join positions velocities {:entity :entity})
        new-position-velocities (process-position-velocities position-velocities time-delta)]
    {:position-components (get-new-position-components new-position-velocities)
     :velocity-components (get-new-velocity-components new-position-velocities)}))

(defn physics-system [old-state time-delta]
  (let [{:keys [position-components velocity-components]} old-state
        {:keys [new-position-components new-velocity-components]} (update-physics position-components velocity-components time-delta)]
    (merge new-position-components new-velocity-components old-state)))

(defn update-position [x y vx vy time-delta]
  {:x (+ x (* vx time-delta))
   :y (+ y (* vy time-delta))
   :vx vx
   :vy vy})
