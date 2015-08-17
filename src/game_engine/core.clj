(ns game-engine.core
  (:gen-class)
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def screen-width
  380)

(def screen-height
  640)

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

(defn update-paddle [paddle]
  paddle)

(defn update-ball [ball]
  ball)

(defn update-brick [brick]
  brick)

(defn draw-paddle [paddle]
  (let [{:keys [x y w h]} paddle]
    (q/fill 0)
    (q/rect x y w h)))

(defn draw-ball [ball]
  (let [{:keys [x y d]} ball]
    (q/fill 0)
    (q/ellipse x y d d)))

(defn draw-brick [brick]
  (let [{:keys [x y w h]} brick]
    (q/fill 0)
    (q/rect x y w h)))

(defn setup []
  ; game initialization, maybe?
  (q/smooth)
  (q/frame-rate 10)
  (q/color-mode :hsb)
  (q/background 255)

  
  ; this would be the initial state
  ; it would be the first thing returned from (game-engine)
  ; when the initialization is injected    
  {:paddles new-paddles
   :balls new-balls
   :bricks new-bricks})

(defn update-state [old-state]
  (let [{:keys [paddles balls bricks]} old-state]
    {:paddles (map update-paddle paddles)
     :balls (map update-ball balls)
     :bricks (map update-brick bricks)}))

(defn draw-state [state]
  (let [{:keys [paddles balls bricks]} state]
    (q/background 255)
    (dorun (map draw-paddle paddles))
    (dorun (map draw-ball balls))
    (dorun (map draw-brick bricks))))

(defn key-pressed [state event]
  (println event)
  state)

(defn make-sketch []
  (q/sketch
    :title "breakout"
    :size [screen-width screen-height]
    :setup setup
    :update update-state
    :draw draw-state
    :key-pressed key-pressed
    :features [:keep-on-top
               :exit-on-close]
    :middleware [m/fun-mode]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (make-sketch))
