(ns logic.util)

; other logical functions (nand, impl, etc.)

(defmacro nand
  "Logical negated and"
     [& more]
     `(not (and ~@more)))

(defmacro nor
  "Logical negated ore"
     [& more]
     `(not (or ~@more)))

(defmacro impl
  "Logical implication (phi -> psi)"
  [phi psi]
  `(or (not ~phi) ~psi))

(defmacro nimpl 
  "Logical negated implication (!(phi -> psi))"
  [phi psi]
  `(not (impl ~phi ~psi)))

(defmacro equiv
   "Logical equivalence (phi <-> psi)"
   ([] true)
   ([x] true)
   ([x y]
     `(= ~x ~y))
   ([x y & more]
     `(let [y# ~y]
        (if (= ~x  y#) (equiv y# ~@more) false))))

(defmacro rimpl
  "Converse logical implication (phi <- psi)"
  [phi psi]
  `(impl ~psi ~phi))

(defmacro nrimpl 
  "Negated converse logical implication (!(phi <- psi))"
  [phi psi]
  `(nimpl ~psi ~phi))

(defmacro xor
  "Logical exclusive or"
  [phi psi]
  `(let [phi# ~phi, psi# ~psi]
     (and (or phi# psi#) (not (and phi# psi#)))))
