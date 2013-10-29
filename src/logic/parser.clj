(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr]))

; define parser
(def logic-parser
  (insta/parser
    "src/logic/grammar.txt"))

(defn strip-spaces
  "Eliminates all spaces from a given string."
  [formula]
  (cstr/replace formula " " ""))

(defn clear-brackets 
  "If no outer brackets are contained in the formula but the should, it adds them."
  [formula]
  (if (and (= (first formula) \() (= (last formula) \)))
    formula
    (str "(" formula ")")))

(defn parse 
  "Parses a formula and returns an ast. Does some other convenient stuff
  (takes care of spaces and parenthesis)."
  [formula]
  (-> formula strip-spaces clear-brackets logic-parser first))

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
    :symbol (keyword (first (rest ast)))
    :negation `(not ~(op1 ast))
    :andexp `(and ~(op1 ast) ~(op2 ast))
    :orexp `(or ~(op1 ast) ~(op2 ast))
    :impl `(impl ~(op1 ast) ~(op2 ast))
    :equiv `(equiv ~(op1 ast) ~(op2 ast))
    ))
