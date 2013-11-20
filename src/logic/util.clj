(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr])
  (:use     [clojure.math.combinatorics :only (selections combinations)]
            [clojure.walk :only (postwalk)]))

(def debug true)

(def tseitin-prefix "t_")

(load "tools"
      "basicfunctions"
      "evaluator"
      "parser"
      "transform"
      "truthtable"
      "cnf")

;for debugging
;(def ast-atom (transform-ast (logic-parse "diesIstEinAtom")))
;(def ast-unary (transform-ast (logic-parse "!neinNein")))
;(def ast-binary (transform-ast (logic-parse "a -> b -> true")))
;(def ast-n-ary (transform-ast (logic-parse "a & b & c")))
