(ns game-engine.systems
  (:gen-class))

; an experimental way of defining systems and thereby components

(defn comparisons [aabb-x aabb-y]
  [])

(defn intersect? [aabb-x aabb-y]
  (every? (<= %1 %2) (comparisons aabb-x aabb-y)))

(defn create-aabb [collideable]
  (select-keys collideable [:x :y :w :h]))

(defn create-aabbs [collideables]
  (map create-aabb collideables))

(defn find-collisions [collideables]
  (let [aabbs (create-aabbs collideables)
        intersecting-aabbs (find-intersections aabbs)]
    intersecting-aabbs))

(defn collision-system [initial-collideables]
  (let [collisions (find-collisions initial-collideables)
        final-collideables (remove-collided initial-collideables collisions)]
    final-collideables))

