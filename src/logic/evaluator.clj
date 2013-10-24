(ns logic.evaluator
  (:require [clojure.string :as cstr])
  (:use [clojure.set]
        [logic.basicfunctions]))

; find all symbols
; give an ast and retrieve a set of all containing symbols
(defn find-symbols [ast symbol-set]
  (case (first ast)
    :symbol (conj symbol-set (keyword (first (rest ast))))
    :negation (find-symbols (first (rest ast)) symbol-set)
    (union (find-symbols (first (rest ast)) symbol-set) (find-symbols (first (rest (rest ast))) symbol-set))
    ))

; evaluate form with given substitute map
(defn eval-formula [formula smap]
  (eval
    (clojure.walk/prewalk-replace smap formula)))