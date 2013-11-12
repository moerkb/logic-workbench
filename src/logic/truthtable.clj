(ns logic.util)

(defn code-to-truth-table
  "Takes clojure code and generates the truth table for it.
   Output format (truth table is a relation):
   {:symbols [A B :result] \"vector of all formula variables, ex. \"A & B\" and the keyword result\"
    :formula '(and A (or C B)) \"the formula in clojure code\"
    :table [[true true true] [true false false]...] \"list of lists representing a row\"
   }"
  [clj-code]
  (let [symbols (find-variables clj-code)
        allcomb (selections [true false] (count symbols))
        assign-map (for [comb allcomb]
                            (vec (interleave symbols comb)))]
    {:symbols (conj (vec symbols) :result)
     :formula clj-code
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
        clj-code (ttable :formula)
        var-spaces (-> (comp dec count name) (map symbols) vec)]

    ; print original formula
    (println "Truth table for formula:" formula)
    (println "Parsed clojure code:" clj-code)
    
    ; print header
	  (doseq [sym (replace {:result "\u03A6"} symbols)]
	    (print (name sym) ""))
	  (print \newline )
	
	  ; print the combinations and results
    (doseq [row table]
      (doseq [[elem spc] (map list row var-spaces)]
        (print (abbrev-bool (str elem)) (if (> spc 0) (format (str "%" spc "s") " ") ""))
      )
      (print \newline)
    )
  ))

(defn truth-table 
  "Takes a human-readable formula, parses it and prints a truth table."
  [formula]
  (print-tt formula (code-to-truth-table (transform-ast (logic-parse formula)))))

(defn tt
  "Prints truth table of fml given on Clojure"
  [fml]
  (print-tt "phi" (code-to-truth-table fml)))
      

