(ns game-engine.systems
  (:gen-class))

; an experimental way of defining systems and thereby components

(defn intersect-on-x? [aabb1 aabb2]
  (let [x1 (:x aabb1)
        x2 (:x aabb2)
        w1 (:w aabb1)
        w2 (:w aabb2)]
    (< (* 2 (Math/abs (- x1 x2))) (+ w1 w2))))

(defn intersect-on-y? [aabb1 aabb2]
  (let [y1 (:y aabb1)
        y2 (:y aabb2)
        h1 (:h aabb1)
        h2 (:h aabb2)]
    (< (* 2 (Math/abs (- y1 y2))) (+ h1 h2))))

(defn intersect? [aabb1 aabb2]
  (if (and (intersect-on-x? aabb1 aabb2) (intersect-on-y? aabb1 aabb2))
    [aabb1 aabb2]))

(defn find-intersections [aabb aabbs]
  (map #(intersect? aabb) aabbs))

(defn find-all-intersections [aabbs]
  (map #(find-intersections % aabbs) aabbs))

(defn create-aabb [collideable]
  (select-keys collideable [:x :y :w :h]))

(defn create-aabbs [collideables]
  (map create-aabb collideables))

(defn find-collisions [collideables]
  (let [aabbs (create-aabbs collideables)
        intersecting-aabbs (find-all-intersections aabbs)]
    intersecting-aabbs))

(defn collision-system [initial-collideables]
  (let [collisions (find-collisions initial-collideables)
        final-collideables (remove-collided initial-collideables collisions)]
    final-collideables))
