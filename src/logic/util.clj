(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr]
            [mpa.cst.parser.parser :as mpaParser]
            [clojure.repl :refer :all])
  (:use     [clojure.math.combinatorics :only (selections combinations)]
            [clojure.walk :only (postwalk)]
            [clojure.set :only (union)]))

(def tseitin-prefix "t_")

(load "tools"
      "logical-operators"
      "evaluator"
      "parser"
      "transform"
      "truthtable"
      "cnf"
      "tseitin"
      "clause"
      "dimacs"
      "sat-solve"
      "resolution")
