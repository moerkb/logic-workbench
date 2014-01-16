(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr]
            [mpa.cst.parser.parser :as mpaParser])
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

(def javaCCparse mpaParser/javaCCparse) ; only an ugly helper to use javaCCparse by using logic.util
