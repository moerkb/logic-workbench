(ns logic.util
 (:require [clojure.math.combinatorics :as combo])
 (:require [clojure.set :as set]))

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
;(resolvents (clause [1]) (clause [2 -1]))
;(resolvents (clause [1 -2]) (clause [2 -1]))
;(resolvents (clause [1]) (clause [-1]))
;(resolvents (clause [1]) (clause [2]))
;(resolvents (clause [1 2 3]) (clause [1 2 -3]))
;(resolvents (clause [1 2 3]) (clause [1 2 -3 -4]))
;(resolvents (clause [1 2 3 -4]) (clause [1 2 -3 -4]))
;(resolvents (clause [1 2 3 4]) (clause [1 2 -3 -4]))
;(resolvents (clause [1 2 3 4]) (clause [1 2 -3 -4]) (clause [-1 2]))

;(resolvents #{1 2} #{-1 2} #{1 -2} #{-1 -2})
;(resolvents #{1} #{-1 2} #{-2})
;(resolvents #{-1 2} #{-3 4} #{1} #{3} #{-2 -4})
;(resolvents #{1} #{-1 3} #{1 2} #{-3})
;(resolvents #{1 2} #{-1 3} #{-1 -3} #{-1 -2})


(defn resolution
  "Return :unsat if the given set of clauses is unsatisfiable,
   :sat otherwise"
  [cl-set]
  (let [res (apply resolvents cl-set) ext-cl-set (set/union cl-set res)]
    (cond
	    (contains? res #{}) :unsat
      (= cl-set ext-cl-set) :sat
      :else (recur ext-cl-set))))
     
; examples
;(resolution #{#{1} #{-1}})
;(resolution #{#{1} #{2}})
;(resolution #{(clause [1 2]) (clause [-1 2]) (clause [1 -2]) (clause [-1 -2])})
;(resolution #{(clause [1]) (clause [-1 2]) (clause [-2])})
;(resolution #{(clause [-1 2]) (clause [-3 4]) (clause [1]) (clause [3]) (clause [-4 -2])})
    
          