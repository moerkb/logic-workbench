(ns logic.util)

; other logical functions (nand, impl, etc.)

; IMPORTANT
; These must stay macros and must not be changed to functions!

(defmacro nand
   "Logical negated and"
	 [x & more]
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
     `(and (impl ~x ~y) (impl ~y ~x)))
   ([x y & more]
     `(equiv (equiv ~x ~y) (equiv ~more))))

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
  `(not (equiv phi psi)))
