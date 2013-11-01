(ns logic.util
  (:require [instaparse.core :as insta]
            [clojure.string :as cstr])
  (:use     [clojure.math.combinatorics :only (selections)]
            [clojure.set :only (union)]))

(def debug true)

(load "basicfunctions"
      "evaluator"
      "parser"
      "transform"
      "truthtable")
