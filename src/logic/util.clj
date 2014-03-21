(ns logic.util
  (:require [clojure.string :as cstr]
            ;[mpa.cst.parser.parser :as mpaParser]
            [clojure.repl :refer :all]
            [clojure.math.combinatorics :refer (selections combinations)]
            [clojure.walk :refer (postwalk)]
            [clojure.set :refer (union)]))

(def tseitin-prefix "t_")

(load "tools"
      "logical-operators"
      "evaluator"
      "transform"
      "parser"
      "truthtable"
      "cnf"
      "tseitin"
      "clause"
      "dimacs"
      "sat-solve"
      "resolution")
