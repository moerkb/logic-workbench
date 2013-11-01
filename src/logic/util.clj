(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr]
            [clojure.math.combinatorics :only (selections)]
            [clojure.set]))

(def debug true)

(load "basicfunctions"
      "evaluator"
      "parser"
      "transform"
      "truthtable")
