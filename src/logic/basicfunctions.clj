(ns logic.util)

; other logical functions (nand, impl, etc.)

;;;;; NAND ;;;;;;
;;; function ;;;
#_(defn nand
  "Logical negated and (!(phi & psi))"
  ([] false)
  ([x] (not x))
  ([x & next]
   (if x (apply nand next) true)))

;;; macro ;;;
(defmacro nand
  "Logical negated and (!(phi & psi))"
  ([] false)
  ([x] (not x))
  ([x & next]
    `(let [nand# ~x]
       (if nand# (nand ~@next) true))))

;;;;; NOR ;;;;;
;;; functon ;;;
#_(defn nor
  "Logical negated or (!(phi | psi))"
   ([] true)
   ([x] (not x))
   ([x & next]
     (if x false (apply nor next))))

; ??
(defmacro nand1
     [& more]
     `(not (and ~@more)))

;;; macro ;;;
(defmacro nor
  "Logical negated or (!(phi | psi))"
  ([] true)
  ([x] (not x))
  ([x & next]
    `(let [nor# ~x]
       (if nor# false (nor ~@next)))))

(defmacro nor1
     [& more]
     `(not (or ~@more)))

(defn impl 
  "Logical implication (phi -> psi)"
  [phi psi]
  (or (not phi) psi))

; besser als Makro, q wird nicht ausgewertet, wenn p false ist
(defmacro impl1
  [p q]
  `(if (not ~p) 
     true
     ~q))

(defn nimpl 
  "Logical negated implication (!(phi -> psi))"
  [phi psi]
  (not (impl phi psi)))

;;;;; EQUIV ;;;;;
;;; function ;;;
#_(defn equiv
  "Logical equivalence (phi <-> psi)"
   ([] true)
   ([x] true)
   ([x & more]
     (if (= x (first more)) (apply equiv more) false)))

;;; macro ;;;
(defmacro equiv
   "Logical equivalence (phi <-> psi)"
   ([] true)
   ([x] true)
   ([x y]
     `(= ~x ~y))
   ([x y & more]
     `(let [y# ~y]
        (if (= ~x  y#) (equiv y# ~@more) false))))

(defn rimpl
  "Converse logical implication (phi <- psi)"
  [phi psi]
  (impl psi phi))

(defn nrimpl 
  "Negated converse logical implication (!(phi <- psi))"
  [phi psi]
  (nimpl psi phi))

(defn xor
  "Logical exclusive or"
  [phi psi]
  (and (or phi psi) (not (and phi psi))))
