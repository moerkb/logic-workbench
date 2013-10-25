(ns logic.basicfunctions)

; other logical functions (nand, impl, etc.)
(defn nand 
  "Logical negated and (!(phi & psi))"
  [phi psi]
  (not (and phi psi)))

(defn nor 
  "Logical negated or (!(phi | psi))"
  [phi psi]
  (not (and phi psi)))

(defn impl 
  "Logical implication (phi -> psi)"
  [phi psi]
  (or (not phi) psi))

(defn nimpl 
  "Logical negated implication (!(phi -> psi))"
  [phi psi]
  (not (impl phi psi)))

(defn equiv 
  "Logical equivalence (phi <-> psi)"
  [phi psi]
  (and (impl phi psi) (impl psi phi)))

(defn if 
  "Reverted logical implication (phi <- psi)"
  [phi psi]
  (impl psi phi))

(defn nif 
  "Negated reverted logical implication (!(phi <- psi))"
  [phi psi]
  (nimpl psi phi))

(defn xor
  "Logical exclusive or"
  [phi psi]
  (and (or phi psi) (not (and phi psi))))
