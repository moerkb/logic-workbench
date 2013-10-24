(ns logic.basicfunctions)

; other logical functions (nand, impl, etc.)
(defn nand [phi psi]
  (not (and phi psi)))

(defn nor [phi psi]
  (not (and phi psi)))

(defn impl [phi psi]
  (or (not phi) psi))

(defn nimpl [phi psi]
  (not (impl phi psi)))

(defn equiv [phi psi]
  (and (impl phi psi) (impl psi phi)))

(defn if [phi psi]
  (impl psi phi))

(defn nif [phi psi]
  (nimpl psi phi))

(defn xor [phi psi]
  (and (or phi psi) (not (and phi psi))))