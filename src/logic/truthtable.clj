(ns logic.util)

(defn code-to-truth-table
  "Takes clojure code and generates the truth table for it.
   Output format (truth table is a relation):
   {:symbols [A B] \"vector of all formula variables, ex. \"A & B\"\"
    :table [[true true true] [true false false]...] \"list of lists representing a row\"
   }"
  [clj-code]
  (let [symbols (find-variables clj-code)
        allcomb (selections [true false] (count symbols))
        assign-map (for [comb allcomb]
                            (vec (interleave symbols comb)))]
    {:symbols (vec symbols)
     :table (vec (for [curr-valuation assign-map]
					    (let [curr-val-map (apply hash-map curr-valuation)]
						      (conj (vec (for [sym symbols] 
                               (sym curr-val-map)))
                    (eval-formula clj-code curr-valuation))
						   )))}))

(defn abbrev-bool 
  "Replaces 'true' with 'T' and 'false' with 'F' for better reading of a truth table."
  [value]
  (cstr/replace (cstr/replace value "true" "T") "false" "F"))

(defn- print-tt 
  "Prints a truth table (given as relation) in a nice format."
  [formula ttable]

  (let [symbols (ttable :symbols)
        table (ttable :table)
        ;var-spaces (conj (vec (map (comp dec count name) symbols)) 0)]
        var-spaces (-> (comp dec count name) (map symbols) vec (conj 0))]

    ; print original formula
    (println "Truth table for formula:" formula)
    (println var-spaces)
    ; print header
	  (doseq [sym symbols]
	    (print (name sym) ""))
	  (print \u03A6 \newline )
	
	  ; print the combinations and results
    (doseq [row table]
      (doseq [[elem spc] (map list row var-spaces)]
        (print (abbrev-bool (str elem)) (if (> spc 0) (format (str "%" spc "s") " ") ""))
      )
      (print "\n")
    )
  ))

(defn truth-table 
  "Takes a human-readable formula, parses it and prints a truth table."
  [formula]
  (print-tt formula (code-to-truth-table (transform-ast (logic-parse formula)))))
      

