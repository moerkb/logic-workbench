(ns logic.parser-test
  (:require [clojure.test :refer :all]
            [logic.util :refer :all]
            [instaparse.core :as insta]))

(deftest one-symbol-test
  (is (= [:atom "A"] (logic-parse "A")))
  (is (= [:atom "A"] (logic-parse "(A)")))
  (is (= [:atom "A"] (logic-parse " A ")))
  (is (= [:atom "A"] (logic-parse " ( A ) ")))
  (is (= [:atom "A"] (logic-parse "[ A ] ")))
)

(deftest simple-and-test
  (is (= [:and [:atom "A"] [:atom "B"]] (logic-parse "A and B")))
  (is (= [:and [:atom "A"] [:atom "B"]] (logic-parse "A&B")))
  (is (= [:and [:atom "A"] [:atom "B"]] (logic-parse "A & B")))
  (is (= [:and [:atom "A"] [:atom "B"]] (logic-parse " ( A and (B) )")))
)

(deftest complex-and-test
  (is (= [:and [:and [:atom "A"] [:atom "B"]] [:atom "C"]] (logic-parse "A&B&C")))
  (is (= [:and [:atom "A"] [:and [:atom "B"] [:atom "C"]]] (logic-parse "A&[B&C]")))
)

(deftest one-symbol-not-test
  (is (= [:not [:atom "A"]] (logic-parse "!A")))
  (is (= [:not [:atom "A"]] (logic-parse "not A")))
)

(deftest simple-not-and-test
  (is (= [:and [:not [:atom "A"]] [:atom "B"]] (logic-parse "!A and B")))
  (is (= [:and [:atom "A"] [:not [:atom "B"]]] (logic-parse "A and !B")))
  (is (= [:and [:atom "A"] [:not [:atom "B"]]] (logic-parse "A and not B")))
)

(deftest constant-test
  (is (= [:true] (logic-parse "true")))  
  (is (= [:true] (logic-parse "True")))  
  (is (= [:true] (logic-parse "T")))  
  (is (= [:true] (logic-parse "t")))  
  (is (= [:true] (logic-parse "1")))  
  (is (= [:not [:true]] (logic-parse "!t")))  
  (is (= [:false] (logic-parse "false")))  
  (is (= [:false] (logic-parse "False")))  
  (is (= [:false] (logic-parse "f")))  
  (is (= [:false] (logic-parse "F")))  
  (is (= [:false] (logic-parse "0")))  
)

(deftest complext-multiple-test
  (= 
    [:xor [:impl [:not [:atom "A"]] [:false]] [:nand [:atom "ABC"] [:equiv [:false] [:atom "p" "_" "0" "0" "1"]]]]
    (logic-parse "!A -> f xor ABC nand [f <-> p_001]"))
)