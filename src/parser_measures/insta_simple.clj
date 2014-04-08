(ns parser_measures.insta_simple
  (:require [instaparse.core :as insta]))

(def logic-parser
  (insta/parser "src/parser_measures/grammar-slim.txt"))

(defn logic-parse [formula]
  (let [ast (insta/parses logic-parser formula)]
    (first (first ast))))

(defn pure-parse [formula]
  (insta/parse logic-parser formula))