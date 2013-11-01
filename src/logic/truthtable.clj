(ns logic.util)

(defn ast-to-truth-table
  "Takes an instaparse-generated ast and generates the truth table for it.
   Output format (truth table is a relation):
   [:symbols [A B] \"vector of all formula variables, ex. \"A & B\"\"
    :table [[true true true] [true false false]...] \"list of lists representing a row\"
   ]"
  [ast]
  (let [symbols (find-symbols ast)
        clj-code (transform-ast ast)
        allcomb (selections [true false] (count symbols))
        assign-map (for [comb allcomb]
                            (vec (interleave symbols comb)))]
    [:symbols (vec symbols)
     :table (vec (for [curr-valuation assign-map]
					    (let [curr-val-map (apply hash-map curr-valuation)]
						      (conj (vec (for [sym symbols] 
                               (sym curr-val-map)))
                    (eval-formula clj-code curr-valuation))
						   )))]))

(defn abbrev-bool 
  "Replaces 'true' with 'T' and 'false' with 'F' for better reading of a truth table."
  [value]
  (cstr/replace (cstr/replace value "true" "T") "false" "F"))

(defn- print-tt 
  "Prints a truth table in a nice format."
  [symbols assign-map formula original-formula]

  ; print header
  (println "Truth table for formula:" original-formula)
  (doseq [sym symbols]
    (print (cstr/replace sym ":" "") ""))
  (print \u03A6 \newline )

  ; print the combinations and results
  (doseq [curr-valuation assign-map]
    (let [curr-val-map (apply hash-map curr-valuation)]
	    (doseq [sym symbols]
	      (print (abbrev-bool (sym curr-val-map)) ""))
	    (print (abbrev-bool (eval-formula formula curr-valuation)) \newline))))

(defn truth-table 
  "Takes a human-readable formula, parses it and prints a truth table."
  [formula]
  (let [ast (logic-parse formula)
        clj-code (transform-ast ast)
        symbols (find-symbols ast)
        allcomb (selections [true false] (count symbols))
        assign-map (for [comb allcomb]
                            (vec (interleave symbols comb)))
        ]
            (print-tt symbols assign-map clj-code formula)
          ))

