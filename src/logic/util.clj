(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr])
  (:use     [clojure.math.combinatorics :only (selections combinations)]
            [clojure.walk :only (postwalk)]))

(def tseitin-prefix "t_")

(load "tools"
      "basicfunctions"
      "evaluator"
      "parser"
      "transform"
      "truthtable"
      "cnf")
