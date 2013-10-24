(ns logic.truthtable
  (:require [clojure.string :as cstr])
  (:use [logic.evaluator]
        [logic.parser]
        [clojure.math.combinatorics :only (selections)]))

; make false to "f" and true to "t"
(defn abbrev-bool [value]
  (cstr/replace (cstr/replace value "true" "T") "false" "F"))

; SIDE EFFECT
; print truth table
(defn print-tt [symbols assign-map formula original-formula]
  ; print header
  (println "Truth table for formula:" (clear-brackets (strip-spaces original-formula)))
  (doseq [sym symbols]
    (print (cstr/replace sym ":" "") ""))
  (print \u03A6 \newline )

  (doseq [s assign-map]
    (doseq [sym symbols]
      (print (abbrev-bool (sym  s)) ""))
    (print (abbrev-bool (eval-formula formula s)) \newline)))

; takes a human-readable formula and prints the truth-table
(defn truth-table [formula]
  (let [ast (parse formula)]
    (let [clj-code (convert-ast ast)]
      (let [symbols (find-symbols ast (sorted-set))]
        (let [allcomb (selections [true false] (count symbols))]
          (let [assign-map (for [comb allcomb]
                             (zipmap symbols comb))]
            (print-tt symbols assign-map clj-code formula)
          ))))))