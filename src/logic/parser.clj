(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr]))

; define parser
(def logic-parser
  (insta/parser "src/logic/grammar.txt"))

(defn logic-parse [formula]
  (let [ast (insta/parses logic-parser formula)]
    (do
      (if debug (println "INFO: Number of possible asts:" (count ast)))
      (first (first ast)))))

(declare convert-ast)

(defn op1 
  "Gets the first operand of an ast tree."
  [ast]
  (convert-ast (first (rest ast))))

(defn op2 
  "Gets the second operand of an ast tree."
  [ast]
  (convert-ast (first (rest (rest ast)))))

(defn convert-ast 
  "Converts an ast to executable clojure code."
  [ast]
  (case (first ast)
    :atom (symbol (apply str (rest ast)))
    :negation `(not ~(op1 ast))
    :and `(and ~(op1 ast) ~(op2 ast))
    :or`(or ~(op1 ast) ~(op2 ast))
    :impl `(impl ~(op1 ast) ~(op2 ast))
    :equiv `(equiv ~(op1 ast) ~(op2 ast))
    ))
