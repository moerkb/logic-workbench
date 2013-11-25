(ns logic.util)

; IMPORTANT
; The following macros must stay macros and must not be changed to functions,
; because transformations (to cnf e.g.) use them with macroexpand!

(defmacro nand
  "Logical negated and"
  [& more]
  (list 'not 
    (apply list 'and more)))

(defmacro nor
  "Logical negated or"
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

; TODO: negate-all has dependency to tools.clj

(defmacro cimpl
  "Converse logical implication (phi <- psi)"
  [phi psi]
  (list 'or
    (list 'not psi) phi))

(defmacro ncimpl 
  "Negated converse logical implication (!(phi <- psi))"
  [phi psi]
  (list 'not
    (list 'cimpl phi psi)))

(defmacro xor
  "Logical exclusive or"
  [phi psi]
  (list 'not
        (list 'equiv phi psi)))

(defmacro ite
  "Logical if-then-else"
  [i t e]
  (list 'or (list 'and i t)
            (list 'and (list 'not i) e))) 

; Further useful functions

(defn min-kof 
  "(min-kof k coll) -> cfml expressing that 
   at least k of the symbols in coll are true."
  [k coll]
  {:pre [(<= 1 k (count coll))]}
  (if (= (count coll) k)
    (cons 'and (seq coll))
    (let [clauses (map #(cons 'or %)(combinations coll (inc (- (count coll) k))))]
       (if (> (count clauses) 1)
         (cons 'and clauses)
         (first clauses)))))
        

(defn max-kof 
  "(max-kof k coll) -> cfml expressing that 
   at most k of the symbols in coll are true."
  [k coll]
  {:pre [(<= 0 k (dec (count coll)))]}
  (if (= k 0)
    (cons 'and (map #(list 'not %) coll))
    (cons 'and 
          (for [s (combinations coll (inc k))]
            (cons 'or (map #(list 'not %) s))))))

(defn kof
  "(kof k coll) -> cfml expressing that
   exactly k of the symbols in coll are true."
  [k coll]
  (condp = k
    0            (max-kof 0 coll)
    (count coll) (min-kof k coll)
    (list 'and (min-kof k coll) (max-kof k coll))))

(defn oneof
  "oneof k coll) -> cfml expressing that
   exactly 1 symbol in coll ist true."
  [coll]
  (if (empty? coll)
    false
    (kof 1 coll)))

; Generating symbols from other symbols

(defn combine-syms
  "(combine & syms) -> symbol
   returns a symbol named 'sym1-sym2...'"
  [& syms]
  (symbol (cstr/join "-" (map name syms))))


