(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr])
  (:use     [clojure.math.combinatorics :only (selections)]))

(def debug true)

(def reserved-symbols 
  #{"and" "nand" "or" "nor" "impl" "nimpl" "rimpl" "nrimpl" "equiv" "xor" "true" "false" "not"})

(def n-ary-symbols
  #{"and" "nand" "or" "nor" "equiv"})

(defn boolean?
  "True if the parameter is of the type Boolean, false otherwise"
  [x]
  (= (class x) java.lang.Boolean))

(defn binary?
  [x]
  (not (contains? n-ary-symbols (name x))))

(load "basicfunctions"
      "evaluator"
      "parser"
      "transform"
      "truthtable"
      "cnf")

;for debugging
(def ast-atom (transform-ast (logic-parse "diesIstEinAtom")))
(def ast-unary (transform-ast (logic-parse "!neinNein")))
(def ast-binary (transform-ast (logic-parse "a -> b -> true")))
(def ast-n-ary (transform-ast (logic-parse "a & b & true")))
