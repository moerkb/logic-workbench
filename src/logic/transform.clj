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
  
(declare flatten-ast)

(defn- flatten-ast-intern
  ([o x] 
    (do (println :unary)
      `(~o ~(flatten-ast x))))
  ([o x y]
    (if (binary? o)
      (do (println :binary)
        `(~o ~(flatten-ast x) ~(flatten-ast y)))
      (do (println :n-ary)
        (if (or (= o (first x)) (= o (first y)))
          (do (println :flatten)
            (make-flat o x y))
          (do (println :notflatten)
            `(~o ~(flatten-ast x) ~(flatten-ast y))))))))

(defn- make-flat ;;; TODO
  [o x y]
  (cond 
    (and (= o (first x)) (= o (first y))) ()
    (= o (first x)) ()
    :else (make-flat o y x)))

(defn flatten-ast
  "Needs a transformed ast (binary) and flats n-ary operations"
  [ast]
  (if (or (boolean? ast) (symbol? ast))
    (do (println :boolORatom) 
      ast)
     (do (println :apply)
       (apply flatten-ast-intern ast))))
