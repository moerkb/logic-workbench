(ns logic.util)

(defn clause 
  "Returns a clause of the distinct non-zero integers in coll,
   nil if the clause is trivially true."
  [coll]
  (loop [in coll result (transient #{})]
    (if (empty? in)
      (persistent! result)
      (let [l (first in) l' (- l)]
        (if (= (result l') l')  ; contains? does not work with transient set, see CLJ-700
          nil
          (recur (next in) (conj! result l)))))))

; examples
;(clause '(-1 2 -3 -6))
;(clause (filter pos? (range 6)))
;(clause [1 2 3 -1])
;(clause [1 2])
;(clause [1 -1])
;(clause [1 -1 2 -2 3])
;(clause (filter pos? (range 1000)))

(defn cl-set
  "Returns the set of the distinct elements in the collection of clauses"
  [cl-coll]
  (set cl-coll))
; better using a transient -> persistent! pattern?

; example
;(cl-set [(clause [1 2]) (clause [-1 2]) (clause [1 -2]) (clause [-1 -2])])