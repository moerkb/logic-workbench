(ns logic.parser-test
  (:require [clojure.test :refer :all]
            [logic.util :refer :all]
            [instaparse.core :as insta])
  (:use [clojure.walk :only (macroexpand-all)]))

(deftest transform-cnf-test
	(is (= 
	      '(and (not A) (or B C))) 
	      (-> "!A and (B or C)" logic-parse transform-ast transform-cnf))
	  
	(is (=
	      '(and (or A C) (or B C))
	      (-> "(A and B) or C" logic-parse transform-ast transform-cnf)))
	  
	(is (=
       ; TODO shall be flattened later
	     '(and A (and B (and D E))))
	      (-> "A and (B and (D and E))" logic-parse transform-ast transform-cnf))
)
