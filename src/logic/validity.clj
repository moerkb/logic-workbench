(ns logic.util)

(defn valid
  "Checks if the prefix formula is valid. Returns a vector, where the first element is always a vector:
  true if the formula is valid, false if not and a counter example as the second element."
  [formula]
  (let [form (remove-constants formula)]
    (if (boolean? formula)
      [formula]
      (let [neg-formula (list 'not (remove-constants form))
            tseit-map (transform-tseitin neg-formula)
            tseit-formula (:tseitin-formula tseit-map)
            result (-> tseit-formula generate-dimacs-clauses sat-solve)
            retrans (retransform-tseitin (rest result) tseit-map)]
        (if (zero? (first result))
          [true]
          [false retrans])))))