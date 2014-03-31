(ns examples.luxemburg  (require [logic.util :refer :all]))
(def colors '[red green blue])

(def countries '[lu de fr be])

(defn one-color [country]  (oneof (map #(combine-syms country %) colors)))

(def countries-one-color  (apply list 'and (map #(one-color %) countries)))￼￼￼
(defn adjacent [c1 c2]  (apply list 'and     (map #(list 'or 
            (list 'not %1) 
            (list 'not %2))      (map #(combine-syms c1 %) colors) 
      (map #(combine-syms c2 %) colors))))

(def fml   (list 'and    countries-one-color 
    (adjacent 'lu 'de) 
    (adjacent 'lu 'fr) 
    (adjacent 'lu 'be) 
    (adjacent 'de 'fr) 
    (adjacent 'fr 'be) 
    (adjacent 'be 'de)))

(-> fml transform-cnf generate-dimacs-clauses sat-solve)