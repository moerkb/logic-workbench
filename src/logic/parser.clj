(ns logic.parser
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr]))

; define parser
(def logic-parser
  (insta/parser
    "<exp> = symbol | <'('> (equiv | impl | andexp | orexp) <')'> | negation
     orexp = exp <'|'> exp
     andexp = exp <'&'> exp
     impl = exp <'->'> exp
     equiv = exp <'<->'> exp
     negation = <'('?> <'!'> exp <')'?>
     symbol = <'('?> #'[A-Za-z]' <')'?>"))

; removes all spaces from formula
(defn strip-spaces [formula]
  (cstr/replace formula " " ""))

; add outer parenthesis if needed
(defn clear-brackets [formula]
  (if (and (= (first formula) \() (= (last formula) \)))
    formula
    (str "(" formula ")")))

; call parser
(defn parse [formula]
  (-> formula strip-spaces clear-brackets logic-parser first))

; helpers to write simple code
(declare convert-ast)

(defn op1 [ast]
  (convert-ast (first (rest ast))))

(defn op2 [ast]
  (convert-ast (first (rest (rest ast)))))

; convert ast to executable clojure code
(defn convert-ast [ast]
  (case (first ast)
    :symbol (keyword (first (rest ast)))
    :negation `(not ~(op1 ast))
    :andexp `(and ~(op1 ast) ~(op2 ast))
    :orexp `(or ~(op1 ast) ~(op2 ast))
    :impl `(logic.basicfunctions/impl ~(op1 ast) ~(op2 ast))
    :equiv `(logic.basicfunctions/equiv ~(op1 ast) ~(op2 ast))
    ))
