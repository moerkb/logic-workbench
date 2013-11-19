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
   :if    #(list 'rimpl %1 %2)
   :nif   #(list 'nrimpl %1 %2)
   :equiv #(list 'equiv %1 %2)
   :xor   #(list 'xor %1 %2)
   :false (fn [] false)
   :true  (fn [] true)
   })

(defn transform-ast
  "Takes an ast as produced by instaparse and transforms it into evaluatable clojure code."
  [ast]
  (insta/transform transform-map ast))
  
(declare flatten-ast)
(declare make-flat)

(defn- flatten-ast-intern
  ([o x] 
    (do (println :unary)
      `(~o ~(flatten-ast x))))
  ([o x y]
    ;TODO nicht mit binary? arbeiten (beliebige Argumente)
    ;TODO binary? aus tools.clj entfernen
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
