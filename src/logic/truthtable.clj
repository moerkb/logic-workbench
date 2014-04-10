(ns logic.util)

(defn generate-truth-table
  "Takes clojure code and generates the truth table for it.
   Output format (truth table is a relation):

   {:symbols [A B :result] \"vector of all formula variables, ex. \"A & B\" and the keyword result\"
    :formula '(and A (or C B)) \"the formula in clojure code\"
    :table [[true true true] [true false false]...] \"list of lists representing a row\"
   }

   Takes the keyword :lines with one of :all, :true-only or :false-only as a parameter. It can be used to 
   produce a truth table with rows where the result is true (or false) only. Default is :all.
   "
  [clj-code & {:keys [lines] :or {lines :all}}]
  {:pre [(contains? #{:all :true-only :false-only} lines)]}

  (let [formula (flatten-ast clj-code)
        symbols (find-variables formula)
        line-bools (cond 
                     (= lines :true-only) #{true}
                     (= lines :false-only) #{false}
                     :else #{true false})]
    (if (> (count symbols) 10)
      (throw (IllegalArgumentException. (str "This formula has more than 10 variables, which exceeds the limit." \newline "The truth table would consist of more than 1024 rows anyway, so you might want to use the SAT solver.")))
	    
	    (let [allcomb (selections [true false] (count symbols))
	          assign-map (for [comb allcomb]
	                          (vec (interleave symbols comb)))]
	    {:symbols (conj (vec symbols) :result)
	     :formula clj-code
       :lines lines
	     :table (remove nil? (vec (for [curr-valuation assign-map]
                                  (let [curr-val-map (apply hash-map curr-valuation)
                                        curr-result (eval-formula formula curr-valuation)]
                                    curr-result (when (contains? line-bools curr-result)
                                                 (conj (vec (for [sym symbols] 
                                                              (sym curr-val-map)))
                                                   curr-result))
))))}))))

(defn abbrev-bool 
  "Replaces 'true' with 'T' and 'false' with 'F' for better reading of a truth table."
  [value]
  (cstr/replace (cstr/replace value "true" "T") "false" "F"))

(defn print-tt 
  "Prints a truth table (given as relation) in a nice format. If show-truth is set to
   false, it will not show rows where the result is true; analog for show-false."
  [formula ttable]

  (let [symbols (ttable :symbols)
        table (ttable :table)
        clj-code (ttable :formula)
        var-spaces (-> (comp dec count name) (map symbols) vec)
        lines (cstr/replace (name (ttable :lines)) "-" " ")]

    ; print information on formula
    (println "Truth table for formula:" formula)
    (println "Parsed clojure code:" clj-code)
    (println "Showing lines:" lines)
    
    ; print header
	  (doseq [sym (replace {:result "\u03A6"} symbols)]
	    (print (name sym) ""))
	  (print \newline)
	
	  ; print the combinations and results
    (doseq [row table]
      (doseq [[elem spc] (map list row var-spaces)]
        (print 
          (abbrev-bool (str elem)) 
          (if (> spc 0) 
            (format (str "%" spc "s") " ") 
            "")))
      
      (print \newline))))

(defn truth-table-parse 
  "Takes a human-readable formula, parses it and prints a truth table.
   :lines parameter is forwared to the same named of generate-truth-table."
  [formula & {:keys [lines] :or {lines :all}}]
  (print-tt formula 
            (-> formula parse (generate-truth-table :lines lines))))

(defn truth-table
  "Prints truth table of formula in clojure code. :lines parameter is forwared to the same named of generate-truth-table."
  [formula & {:keys [lines] :or {lines :all}}]
  (print-tt "phi" (generate-truth-table formula :lines lines)))
      

