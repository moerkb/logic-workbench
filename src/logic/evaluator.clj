(ns logic.evaluator
  (:require [clojure.string :as cstr])
  (:use [clojure.set]
        [logic.basicfunctions]))

(defn find-symbols 
  "Takes an ast as arbitrary collection and returns all keyword symbol that have no semantic yet (etc. :a, :B).
  Second parameter must be a set."
  [ast symbol-set]
  (case (first ast)
    :symbol (conj symbol-set (keyword (first (rest ast))))
    :negation (find-symbols (first (rest ast)) symbol-set)
    (union (find-symbols (first (rest ast)) symbol-set) (find-symbols (first (rest (rest ast))) symbol-set))
    ))

(defn eval-formula 
  "Takes a formula in clojure code and evaluates it with the given substitution map. 
  The last must be of the shape {:a true :b false}"
  [formula smap]
  (eval
    (clojure.walk/prewalk-replace smap formula)))
