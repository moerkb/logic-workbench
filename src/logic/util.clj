(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr])
  (:use     [clojure.math.combinatorics :only (selections)]))

(def debug true)

(defn boolean?
  "True if the parameter is of the type Boolean, false otherwise"
  [x]
  (= (class x) java.lang.Boolean))

(load "basicfunctions"
      "evaluator"
      "parser"
      "transform"
      "truthtable"
      "cnf")
