(ns logic.parser-test
  (:require [clojure.test :refer :all]
            [logic.util :refer :all]
            [instaparse.core :as insta]))

(deftest transform-cnf-test
 (is (= 
       (-> "!B and !C" logic-parse transform-ast) 
       (-> "!A and (B or C)" logic-parse transform-ast transform-cnf )))
  
 (is (=
       `(and (or A C) (or B C))
       (-> "(A and B) or C" logic-parse transform-ast transform-cnf)))
  
 (is (=
       `(and A (or B D) (or B E))
       (-> "A and (B and (D and E))" logic-parse transform-ast transform-cnf)))
)