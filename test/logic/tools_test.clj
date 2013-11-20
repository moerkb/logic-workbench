(ns logic.parser-test
  (:require [clojure.test :refer :all]
            [logic.util :refer :all]))

(deftest literal-test
  (is (literal? true))
  (is (literal? false))
  (is (literal? 'ABC))
  (is (not (literal? 'and))))