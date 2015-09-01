(ns game-engine.facts
  (:gen-class))

;; # Handling Facts
;; 
;; Let's say we have an array of facts like this: 

(def initial-ecsdb
  [; balls
   ["ball" :position/x 280]
   ["ball" :position/y 200]
   ["ball" :velocity/vx 0]
   ["ball" :velocity/vy 0]
   ["ball" :size/w 40]
   ["ball" :size/h 40]
   ["ball" :damage/points 1]
   ["ball" :solidity/enabled true]
   ; paddle
   ["paddle" :position/x 240]
   ["paddle" :position/y 360]
   ["paddle" :velocity/vx 0]
   ["paddle" :velocity/vy 0]
   ["paddle" :size/w 160]
   ["paddle" :size/h 40]
   ["paddle" :solidity/enabled true]
   ; bricks
   ["brick" :position/x 40]
   ["brick" :position/y 40]
   ["brick" :size/w 80]
   ["brick" :size/h 40]
   ["brick" :health/points 1]
   ["brick" :solidity/enabled true]])

;; How do we reach into this 2d array and get what we want?
;;
;; We can say things like:
;; - "Get me all attributes within the 'position' namespace"
;; - "Get me all the :position/x's"
;; 
;; We are likely to want each of a particular kind of component.
;; So let's do something like    

(def position-facts
  [["ball" :position/x 280]
   ["ball" :position/y 200]
   ["paddle" :position/x 240]
   ["paddle" :position/y 360]
   ["brick" :position/x 40]
   ["brick" :position/y 40]])
