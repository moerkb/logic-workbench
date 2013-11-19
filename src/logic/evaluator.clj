(ns logic.util)
  
(defn stringify-symbol
  "Takes a symbol or a boolean and makes a simple string out of it.
   Needed because a symbol needs the name function applied and a 
   boolean the str function.
   ((str and) would give the full qualified name)"
  [s]
  (if (boolean? s)
    (str s)
    (name s)))

(defn find-variables
  "Takes a formula in clojure code as produced by instaparse/transform and returns all variables."
  [formula]
  (apply sorted-set (filter 
                      #(not (contains? reserved-symbols (stringify-symbol %1))) 
                      (flatten (list formula)))))

(defn eval-formula 
  "Takes a formula in clojure code and evaluates it with the given substitution vector. 
  The last must be of the shape ['a true 'b false]"
  [formula val-assignment]
  (eval `(let ~val-assignment ~formula)))
