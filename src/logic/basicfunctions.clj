(ns logic.util)

; other logical functions (nand, impl, etc.)

; IMPORTANT
; These must stay macros and must not be changed to functions!

(defmacro nand
  "Logical negated and"
  [& more]
  (list 'not 
    (apply list 'and more)))

(defmacro nor
  "Logical negated ore"
  [& more]
  (list 'not 
        (apply list 'or more)))

(defmacro impl
  "Logical implication (phi -> psi)"
  [phi psi]
  (list 'or 
        (list 'not phi) psi))

(defmacro nimpl 
  "Logical negated implication (!(phi -> psi))"
  [phi psi]
  (list 'not 
        (list 'impl phi psi)))

(defmacro equiv
   "Logical equivalence (phi <-> psi)"
   [& more]
   (list 'or (apply list 'and more) (apply list 'and (apply negate-all more))))

(defmacro rimpl
  "Converse logical implication (phi <- psi)"
  [phi psi]
  (list 'impl psi phi))

(defmacro nrimpl 
  "Negated converse logical implication (!(phi <- psi))"
  [phi psi]
  (list 'nimpl psi phi))

(defmacro xor
  "Logical exclusive or"
  [phi psi]
  (list 'not
        (list 'equiv phi psi)))
