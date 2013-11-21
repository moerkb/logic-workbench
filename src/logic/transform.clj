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

(defn- flat-filter
  "Private function to filter if the sub sub ast is flattable.
   Returns true if the operator of the sub [o] ast the same like the sub sub ast.
   False otherwise."
  [o l]
  (if (coll? l)
    (= o (first l))
    false))

(defn- flat
  "Private functin to flat a flattenable sub ast with a depth of one level.
   This function does not check if the operator is n-ary!"
  [ast]
  (let [o (first ast) a (rest ast) ; operators and arguments
        flat (map #(rest %) (filter (partial flat-filter o) a))
        not-flat (filter (partial (complement flat-filter) o) a)]
      (apply concat  `((~o) ~@flat ~not-flat))))

(defn- flat-ast
  "Private function to flat the deepest sub ast that is not flatted now if it is possible,
   else it returns the original sub ast.
   It should be used to flat a complete ast bottum up.
   e.g. (postwalk flat-ast ast)"
  [ast]
  (if (and
        (coll? ast)
        (n-ary? (first ast)))
    (flat ast)
    ast))
  
(defn flatten-ast
  "Flats the transformed ast.
   Nested binary functions will be transformed to one n-ary function when it is possible.
   (and (and a b) c) => (and a b c)"
  [ast]
  (postwalk flat-ast ast))
