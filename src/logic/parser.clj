(ns logic.util)

; define parser
(def logic-parser
  (insta/parser "src/logic/grammar.txt"))

(defn logic-parse [formula]
  (let [ast (insta/parses logic-parser formula)]
    (do
      (if debug (println "INFO: Number of possible asts:" (count ast)))
      (first (first ast)))))
