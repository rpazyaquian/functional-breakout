(ns game-engine.logic
  (:gen-class))

; INITIALIZATION

(defn make-paddle [w h]
  {:x (/ (- 380 w) 2)
   :y (- 640 (* 2 h))
   :w w
   :h h})

(defn make-ball [d]
  {:x (/ (- 380 d) 2)
   :y (/ (- 640 (* 2 d)) 2)
   :d d})

(defn make-brick [w h]
  {:x (/ (- 380 w) 2)
   :y (* 2 h)
   :w w
   :h h})

(def new-paddles
  (let [paddle (make-paddle 60 20)]
    [paddle]))

(def new-balls
  (let [ball (make-ball 20)]
    [ball]))

(def new-bricks
  (let [brick (make-brick 60 20)]
    [brick]))

(defn new-game-state []
  {:paddles new-paddles
   :balls new-balls
   :bricks new-bricks})

; UPDATING

(defn update-paddles [old-state input]
  (:paddles old-state))

(defn update-balls [old-state]
  (:balls old-state))

(defn update-bricks [old-state]
  (:bricks old-state))

(defn update-entities [old-state input]
  (-> old-state
      (update-paddles input)
      (update-balls)
      (update-bricks)))

(defn calc-new-state [old-state input]
  (-> old-state
      (update-entities input)))

; TOP-LEVEL

(defn render-filter [state]
  (let [{:keys [paddles balls bricks]} state]
    {:paddles paddles
     :balls balls
     :bricks bricks}))

(defn tick-game-state [old-state input]
  (println (-> old-state
              (render-filter)))
  old-state)
