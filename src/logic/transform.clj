(ns logic.util)

(defn flatten-subast
  "Private function to flat a flattenable sub ast with a depth of one level."
  [ast]
  (if (and
        (coll? ast)
        (n-ary? (first ast)))
    (let [op (first ast) 
          args (rest ast)
          flat-filter (fn [op arg] (if (coll? arg) (= op (first arg)) false))
          flat (map #(rest %) (filter (partial flat-filter op) args))
          not-flat (filter (partial (complement flat-filter) op) args)]
        (apply concat  `((~op) ~@flat ~not-flat)))
    ast))
  
(defn flatten-ast
  "Flats the transformed ast.
   Nested binary functions will be transformed to one n-ary function when it is possible.
   (and (and a b) c) => (and a b c)"
  [ast]
  (postwalk flatten-subast ast))