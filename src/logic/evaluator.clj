(ns logic.util)

(defn- rec-find-symbols 
  "Recusive call for finding all symbols in an instaparse-generated ast."
  [ast symbol-set]
  (case (first ast)
    :atom (conj symbol-set (symbol (apply str (rest ast))))
    :true symbol-set
    :false symbol-set
    :not (rec-find-symbols (first (rest ast)) symbol-set)
    (union (rec-find-symbols (first (rest ast)) symbol-set) (rec-find-symbols (first (rest (rest ast))) symbol-set))
    ))

(defn find-symbols
  "Takes an ast as produced by instaparse and returns all variables."
  [ast]
  (rec-find-symbols ast (sorted-set)))

(defn eval-formula 
  "Takes a formula in clojure code and evaluates it with the given substitution vector. 
  The last must be of the shape [:a true :b false]"
  [formula val-assignment]
  (eval `(let ~val-assignment ~formula)))
