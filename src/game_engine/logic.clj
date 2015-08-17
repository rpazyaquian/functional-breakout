(ns game-engine.logic
  (:gen-class))

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

(defn handle-input [state input]
  ; handling input:
  ; if the input is :left,
  ; add an event message
  ; saying "move paddle left".
  ; how the fuck do i implement that?
  )

(defn calc-new-state [old-state input]
  ; new state calculation goes here, maybe?
  (-> old-state
      (handle-input input)
      (update-positions)))

(defn render-filter [state]
  (let [{:keys [paddles balls bricks]} state]
    {:paddles paddles
     :balls balls
     :bricks bricks}))

(defn tick-game-state [old-state input]
  (println input)
  (-> old-state
      (calc-new-state input)
      (render-filter)))
