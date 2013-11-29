(ns logic.util)

(defn generate-dimacs-clauses
  "Takes a cnf clojure code formula and generates the dimacs clauses for it."
  [formula]
  (let [vars (find-variables formula)
	      subs (zipmap vars (map inc (range)))
	      num-vars (count vars)
	      num-clauses (count (rest formula))]
    {:formula formula
     :num-vars num-vars
     :num-clauses num-clauses
     :clauses (vec (map #(conj (apply vector 
                                      (map (fn [atom] (if (literal? atom)
				                                       (subs atom)
				                                       (- (subs (second atom))))) 
                                           (if (literal? %) (list %) (rest %)))) 
                                 0)
                        (rest formula)))}))

(defn generate-dimacs
  "Takes a cnf formula in clojure code and produces the output as for the dimacs file format."
  [formula]
  (let [tseit-clauses (generate-dimacs-clauses formula)
        num-vars (tseit-clauses :num-vars)
        num-clauses (tseit-clauses :num-clauses)
        clauses (tseit-clauses :clauses)]
    (str
      "c dimacs file for the logic formula" \newline
      "c " (seq formula) \newline
      "c " \newline
      "p cnf " num-vars " " num-clauses \newline
      (apply str (map #(str (apply str (map (fn [elem] (str elem " "))
                                            %))
                            \newline)
                      clauses)))))
  
(defn write-dimacs
  "Taks a cnf formula in cloure code, produces the dimacs format and writes to given file name."
  [formula filename]
  (spit filename (generate-dimacs formula)))