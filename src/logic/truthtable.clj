(ns logic.util)

(defn code-to-truth-table
  "Takes clojure code and generates the truth table for it.
   Output format (truth table is a relation):
   {:symbols [A B :result] \"vector of all formula variables, ex. \"A & B\" and the keyword result\"
    :formula '(and A (or C B)) \"the formula in clojure code\"
    :table [[true true true] [true false false]...] \"list of lists representing a row\"
   }"
  [clj-code]
  (let [symbols (find-variables clj-code)]
    (if (> (count symbols) 10)
      (throw (IllegalArgumentException. "This formula has more than 10 variables, which exceeds the limit. The truth table would consist of more than 1024 rows anyway, so you might want to use the SAT solver."))
	    
	    (let [allcomb (selections [true false] (count symbols))
	          assign-map (for [comb allcomb]
	                          (vec (interleave symbols comb)))]
	    {:symbols (conj (vec symbols) :result)
	     :formula clj-code
	     :table (vec (for [curr-valuation assign-map]
						    (let [curr-val-map (apply hash-map curr-valuation)]
							      (conj (vec (for [sym symbols] 
	                               (sym curr-val-map)))
	                    (eval-formula clj-code curr-valuation))
							   )))}))))

(defn abbrev-bool 
  "Replaces 'true' with 'T' and 'false' with 'F' for better reading of a truth table."
  [value]
  (cstr/replace (cstr/replace value "true" "T") "false" "F"))

(defn- print-tt 
  "Prints a truth table (given as relation) in a nice format. If show-truth is set to
   false, it will not show rows where the result is true; analog for show-false."
  [formula ttable show-truth show-false]

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
	  (print \newline)
	
	  ; print the combinations and results
    (doseq [row table]
      (when (or
 			        (and show-truth show-false)
	   	        (and show-truth (last row))
              (and show-false (not (last row))))
	      (doseq [[elem spc] (map list row var-spaces)]
	        (print 
	          (abbrev-bool (str elem)) 
	          (if (> spc 0) 
	            (format (str "%" spc "s") " ") 
	            "")))
	      
	      (print \newline)))))

(defn truth-table 
  "Takes a human-readable formula, parses it and prints a truth table."
  [formula & {:keys [show] :or {show :all}}]
  {:pre [(contains? #{:all :true-only :false-only} show)]}
  (let [bool-args (case show
                    :all [true true]
                    :true-only [true false]
                    :false-only [false true])]
                    
    (apply print-tt formula 
           (-> formula logic-parse transform-ast code-to-truth-table)
           bool-args)))

(defn tt
  "Prints truth table of fml given on Clojure"
  [formula & {:keys [show] :or {show :all}}]
  {:pre [(contains? #{:all :true-only :false-only} show)]}
  (print-tt "phi" (code-to-truth-table formula)))
      

