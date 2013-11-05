(ns logic.tseitin-test
  (:require [clojure.test :refer :all]
            [logic.util :refer :all]
            [instaparse.core :as insta]))

(deftest all-subformulas-test
  (is (=
        (-> "(A | B) & C" logic-parse transform-ast all-subformulas)
        '((clojure.core/and (clojure.core/or A B) C) (clojure.core/or A B))))
  (is (nil?
        (-> "A" logic-parse transform-ast all-subformulas)))
)