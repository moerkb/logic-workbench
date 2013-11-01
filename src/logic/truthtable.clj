(ns logic.util)

(defn abbrev-bool 
  "Replaces 'true' with 'T' and 'false' with 'F' for better reading of a truth table."
  [value]
  (cstr/replace (cstr/replace value "true" "T") "false" "F"))

(defn print-tt 
  "Prints a truth table in a nice format."
  [symbols assign-map formula original-formula]

  ; print header
  (println "Truth table for formula:" original-formula)
  (doseq [sym symbols]
    (print (cstr/replace sym ":" "") ""))
  (print \u03A6 \newline )

  ; print the combinations and results
  (doseq [s assign-map]
    (doseq [sym symbols]
      (print (abbrev-bool (sym  s)) ""))
    (print (abbrev-bool (eval-formula formula s)) \newline)))

(defn truth-table 
  "Takes a human-readable formula, parses it and prints a truth table."
  [formula]
  (let [ast (logic-parse formula)
        clj-code (transform-ast ast)
        symbols (find-symbols ast (sorted-set))
        allcomb (selections [true false] (count symbols))
        assign-map (vec (for [comb allcomb]
                                 (interleave symbols comb)))
        ]
            (print-tt symbols assign-map clj-code formula)
          ))

