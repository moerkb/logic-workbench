(ns logic.util)

(defn generate-dimacs-clauses
  "Takes a cnf clojure code formula and generates the dimacs clauses for it."
  [formula]
  (let [and-formula (if (= 'or (first formula)) 
                        (list 'and formula)
                        formula)
        vars (find-variables and-formula)
	      subs (zipmap vars (map inc (range)))
	      num-vars (count vars)
	      clauses (set (map #(cond 
                           (literal? %) #{(subs %)}
                           (= 'not (first %)) #{(- (subs (second %)))}
                           :else (set 
                                   (map (fn [atom] (if (literal? atom)
				                                    (subs atom)
				                                    (- (subs (second atom))))) 
                                        (if (literal? %) (list %) (rest %))))) 
                        (rest and-formula)))]
    {:formula formula
     :num-vars num-vars
     :num-clauses (count clauses)
     :clauses clauses
     :subs (zipmap (vals subs) (keys subs))}))

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
                            0 \newline)
                      clauses)))))
  
(defn write-dimacs
  "Taks a cnf formula in cloure code, produces the dimacs format and writes to given file name."
  [formula filename]
  (spit filename (generate-dimacs formula)))

(defn add-negated-dimacs-result
  "Takes the dimacs-map and the result vector and add the negated result to the dimacs-map as new clause to find other results."
  [dimacs-map dimacs-result-vector]
  (apply hash-map (apply concat (map (fn [x]
                                       (if (= (first x) :clauses)
                                         [:clauses (conj (second x) (set (subvec dimacs-result-vector 1)))]
                                         x))
                                     dimacs-map))))