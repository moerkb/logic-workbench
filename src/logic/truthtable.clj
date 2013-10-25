(ns logic.truthtable
  (:require [clojure.string :as cstr])
  (:use [logic.evaluator]
        [logic.parser]
        [clojure.math.combinatorics :only (selections)]))

(defn abbrev-bool 
  "Replaces 'true' with 'T' and 'false' with 'F' for better reading of a truth table."
  [value]
  (cstr/replace (cstr/replace value "true" "T") "false" "F"))

(defn print-tt 
  "Prints a truth table in a nice format."
  [symbols assign-map formula original-formula]

  ; print header
  (println "Truth table for formula:" (clear-brackets (strip-spaces original-formula)))
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
  (let [ast (parse formula)]
    (let [clj-code (convert-ast ast)]
      (let [symbols (find-symbols ast (sorted-set))]
        (let [allcomb (selections [true false] (count symbols))]
          (let [assign-map (for [comb allcomb]
                             (zipmap symbols comb))]
            (print-tt symbols assign-map clj-code formula)
          ))))))
