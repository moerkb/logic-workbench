(ns logic.util)

; define parser
(def logic-parser
  (insta/parser "src/logic/grammar.txt"))

(defn logic-parse [formula]
  (let [ast (insta/parses logic-parser formula)]
    (first (first ast))))

(defn pure-parse [formula]
  (insta/parse logic-parser formula))