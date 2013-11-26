(ns logic.util)

(defn create-dimacs
  "Takes a cnf formula in clojure code and produces the output as for the dimacs file format."
  [formula]
  (let [vars (find-variables formula)
        subs (zipmap vars (map inc (range)))
        num-vars (count vars)
        num-clauses (count (rest formula))]
    (apply str
      "c dimacs file for the logic formula" \newline
      "c " (seq formula) \newline
      "c " \newline
      "p cnf " num-vars " " num-clauses \newline
      (map #(str (apply str (map (fn [atom] (if (literal? atom)
				                                      (str (subs atom) " ")
				                                      (str "-" (subs (second atom)) " "))) 
                             (rest %)))
                 0
                 \newline) 
           (rest formula))
      )))
  
(defn write-dimacs
  "Taks a cnf formula in cloure code, produces the dimacs format and writes to given file name."
  [formula filename]
  (spit filename (create-dimacs formula)))