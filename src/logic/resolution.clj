(ns logic.resolution
 (:require [clojure.math.combinatorics :as combo])
 (:require [clojure.set :as set]))

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
(clause '(-1 2 -3 -6))
(clause (filter pos? (range 6)))
(clause [1 2 3 -1])
(clause [1 2])
(clause [1 -1])
(clause [1 -1 2 -2 3])
(clause (filter pos? (range 1000)))

(defn cl-set
  "Returns the set of the distinct elements in the collection of clauses"
  [cl-coll]
  (set cl-coll))

; example
(cl-set [(clause [1 2]) (clause [-1 2]) (clause [1 -2]) (clause [-1 -2])])

(defn resolvents
  "Returns the set of the resolvents of the clauses,
   ignoring tautologies, i.e. with tautlogy elimination."
  ([c1 c2]
    (cl-set (filter #(not (nil? %))
                 (map #(clause (concat (disj c1 %) (disj c2 (- %)))) 
                     (filter #(contains? c2 (- %)) c1)))))
  ([c1 c2 & more]
    (let [pairs (combo/combinations (cons c1 (cons c2 more)) 2)]
      (reduce set/union (map #(resolvents (first %) (second %)) pairs)))))

; examples
(resolvents (clause [1]) (clause [2 -1]))
(resolvents (clause [1 -2]) (clause [2 -1]))
(resolvents (clause [1]) (clause [-1]))
(resolvents (clause [1]) (clause [2]))
(resolvents (clause [1 2 3]) (clause [1 2 -3]))
(resolvents (clause [1 2 3]) (clause [1 2 -3 -4]))
(resolvents (clause [1 2 3 -4]) (clause [1 2 -3 -4]))
(resolvents (clause [1 2 3 4]) (clause [1 2 -3 -4]))
(resolvents (clause [1 2 3 4]) (clause [1 2 -3 -4]) (clause [-1 2]))

(resolvents #{1 2} #{-1 2} #{1 -2} #{-1 -2})
(resolvents #{1} #{-1 2} #{-2})
(resolvents #{-1 2} #{-3 4} #{1} #{3} #{-2 -4})
(resolvents #{1} #{-1 3} #{1 2} #{-3})
(resolvents #{1 2} #{-1 3} #{-1 -3} #{-1 -2})

          