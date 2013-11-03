(ns logic.util)

(def reserved-symbols 
  #{"and" "nand" "or" "nor" "impl" "nimpl" "rimpl" "nrimpl" "equiv" "xor" "true" "false" "not"})

(defn find-variables
  "Takes a formula in clojure code as produced by instaparse/transform and returns all variables."
  [formula]
  (apply sorted-set (remove nil?  
                 (for [sym (flatten formula)] 
                    (when (not (contains? reserved-symbols (name sym))) sym)))))

(defn eval-formula 
  "Takes a formula in clojure code and evaluates it with the given substitution vector. 
  The last must be of the shape ['a true 'b false]"
  [formula val-assignment]
  (eval `(let ~val-assignment ~formula)))
