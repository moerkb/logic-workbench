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

(defn- flat
  [ast]
  (if (and
        (list? ast)
        (n-ary? (first ast)))
    (do
      (println :can-maybe-flat (rest ast))
      ast) ; TODO
    ast))
  
(defn flatten-ast
  [ast]
  (postwalk flat ast))
