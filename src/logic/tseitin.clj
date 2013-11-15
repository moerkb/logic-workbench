(ns logic.util)

(defn tseitin-transform
  "Takes a formula in clojure code and substitutes all subformulas with a new variable."
  [formula]
  
  (if (literal? formula)
    formula
    
    `(and 
       (equiv ~(gensym tseitin-prefix) ~formula) 
       ~@(map tseitin-subs (rest formula)))))
