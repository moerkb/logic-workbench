(ns logic.util)

(defn- tseitin-cnf
  "Takes a formula with tseitin symbols, the tseitin symbol for this formula
   and substitutes the formula with cnf.
   Currently binary only!"
  [formula tsym]
  (if (literal? formula)
    formula
    (let [[op a] formula]
      (if (= op 'not)
        (list 'and
          (list 'or
            (list 'not tsym)
            (list 'not a)))
      
        (let [b (nth formula 2)]
          (case op
            and (list 'and
                  (list 'or (list 'not tsym) a)
                  (list 'or (list 'not tsym) b)
                  (list 'or tsym (list 'not a) (list 'not b)))
          
            nand (list 'nand
                   (list 'or (list 'not tsym) (list 'not a) (list 'not b))
                   (list 'or tsym a)
                   (list 'or tsym b))
            
            or (list 'and
                 (list 'or tsym (list 'not a))
                 (list 'or tsym (list 'not b))
                 (list 'or (list 'not tsym) (list 'not a) b))
            
            nor (list 'and
                  (list 'or tsym a b)
                  (list 'or (list 'not tsym) (list 'not a))
                  (list 'or (list 'not tsym) (list 'not b)))
          
            impl (list 'and
                   (list 'or tsym a)
                   (list 'or tsym (list 'not b))
                   (list 'or (list 'not tsym) (list 'not a) b))
          
            ;default
            (println "Tseitin-CNF not implemented for" op)))))))

(defn- generate-tseitin-symbols
  "Recursive function for tseitin conversion to generate the new symbols."
  [formula tmap]
    (if (literal? formula)
      [formula tmap]
      (let [rem-retvals (map #(generate-tseitin-symbols % tmap) (rest formula))
            new-sym (gensym tseitin-prefix)
            new-formula (tseitin-cnf 
                          (apply list (first formula) (map first rem-retvals))
                          new-sym)
            new-tmap (reduce merge (map second rem-retvals))
            ]

        [new-sym (conj new-tmap [new-sym new-formula])]
      )))

(defn tseitin-transform
  "Takes a formula in clojure code and applies the tseitin transformation to it."
  [formula]
  (let [gen-list (generate-tseitin-symbols formula {})]
    (apply list 'and
      (first gen-list)
      (vals (second gen-list)))
  )
)