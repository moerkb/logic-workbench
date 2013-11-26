(ns logic.util)

(def transform-map
  {:atom  (fn [& a] (symbol (apply str a)))
   :not   #(list 'not %)
   :and   #(list 'and %1 %2)
   :nand  #(list 'nand %1 %2)
   :or    #(list 'or %1 %2)
   :nor   #(list 'nor %1 %2)
   :impl  #(list 'impl %1 %2)
   :nimpl #(list 'nimpl %1 %2)
   :if    #(list 'cimpl %1 %2)
   :nif   #(list 'ncimpl %1 %2)
   :equiv #(list 'equiv %1 %2)
   :xor   #(list 'xor %1 %2)
   :false (fn [] false)
   :true  (fn [] true)
   })

(defn transform-ast
  "Takes an ast as produced by instaparse and transforms it into evaluatable clojure code."
  [ast]
  (insta/transform transform-map ast))

(defn- flatten-subast
  "Private functin to flat a flattenable sub ast with a depth of one level."
  [ast]
  (if (and
        (coll? ast)
        (n-ary? (first ast)))
    (let [op (first ast) args (rest ast)
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

(defn create-ast
  "Parses a formula and transforms it to an ast."
  [formula]
  (-> formula logic-parse transform-ast))
