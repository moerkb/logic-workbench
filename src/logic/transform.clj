(ns logic.util)

(def transform-map
  {:atom  (fn [& a] (symbol (apply str a)))
   :not   (fn [a] `(not ~a))
   :and   (fn [a b] `(and ~a ~b))
   :nand  (fn [a b] `(nand ~a ~b))
   :or    (fn [a b] `(or ~a ~b))
   :nor   (fn [a b] `(nor ~a ~b))
   :impl  (fn [a b] `(impl ~a ~b))
   :nimpl (fn [a b] `(nimpl ~a ~b))
   :if    (fn [a b] `(rimpl ~a ~b))
   :nif   (fn [a b] `(nrimpl ~a ~b))
   :equiv (fn [a b] `(equiv ~a ~b))
   :xor   (fn [a b] `(xor ~a ~b))
   :false (fn [] false)
   :true  (fn [] true)
   })

(defn transform-ast
  "Takes an ast as produced by instaparse and transforms it into evaluatable clojure code."
  [ast]
  (insta/transform transform-map ast))