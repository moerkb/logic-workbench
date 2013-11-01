(ns logic.util)

(defn find-symbols 
  "Takes an ast as produced by isntaparse and returns all variables."
  [ast symbol-set]
  (case (first ast)
    :symbol (conj symbol-set (keyword (first (rest ast))))
    :negation (find-symbols (first (rest ast)) symbol-set)
    (union (find-symbols (first (rest ast)) symbol-set) (find-symbols (first (rest (rest ast))) symbol-set))
    ))

(defn eval-formula 
  "Takes a formula in clojure code and evaluates it with the given substitution vector. 
  The last must be of the shape [:a true :b false]"
  [formula val-assignment]
  (eval `(let ~val-assignment ~formula)))
