(ns parser_measures.measurement
  (:require
    [parser_measures.kern_simple :as kern-slim]
    [parser_measures.kern :as kern-full]
    [logic.util :as logic]
    [parser_measures.insta_simple :as insta-slim]
    [parser_measures.formulas :refer :all]))

; loading parsers
(def kern-slim-parse kern-slim/kern-parse)
(def kern-full-parse kern-full/kern-parse)

(defn insta-slim-parse [parse-string]
  (logic/transform-ast (insta-slim/logic-parse parse-string)))
(defn insta-full-parse [parse-string]
  (logic/transform-ast (logic/logic-parse parse-string)))

(def javacc-parse logic/javaCCparse)

; testing formulas
(defn formula-parse-test [form-sym ^Boolean slim? ^Boolean insta-full?]
  (println "BEGIN:" form-sym)
  (if slim?
    (println "Using slim grammars")
    (println "NOT using slim grammars"))
  
  (println "Parsing with javacc...")
  (time (javacc-parse (eval form-sym)))
  
  (when slim?
    (println "Parsing with kern, slim grammar...")
    (time (kern-slim-parse (eval form-sym))))
  
  (println "Parsing with kern, full grammar...")
  (time (kern-full-parse (eval form-sym)))
  
  (when slim?
    (println "Parsing with instaparse, slim grammar...")
    (time (insta-slim-parse (eval form-sym))))
  
  (when insta-full?
	  (println "Parsing with instaparse, full grammar...")
	  (time (insta-full-parse (eval form-sym))))
  
  (println "END:" form-sym))

stop

(formula-parse-test 'formula-usa true true)
(formula-parse-test 'formula-4-queens false true)
(formula-parse-test 'formula-8-queens true true)
(formula-parse-test 'formula-half-sudoku true false)
(formula-parse-test 'formula-sudoku true false)