(ns logic.util)

(defn- tseitin-cnf
  "Takes a formula with tseitin symbols, the tseitin symbol for this formula
   and substitutes the formula with cnf.
   Currently binary only!"
  [formula tsym]
  (if (literal? formula)
    formula
    (let [[op a] formula
          t tsym
          nt (list 'not t)
          na (list 'not a)
          args (rest formula)]
      
      (if (= op 'not)
        (list 'and
          (list 'or nt na))
 
        (let [b (when (> (count formula) 2) (nth formula 2))
              nb (when (not (nil? b)) (list 'not b))]
          (case op
            and (apply list 'and
                  (apply list 'or t (map #(list 'not %) args))
                  (map #(list 'or (list 'not t) %) args))
          
            nand (list 'nand
                   (list 'or t a)
                   (list 'or t b)
                   (list 'or nt na nb))
            
            or (apply list 'and
                  (apply list 'or (list 'not t) args)
                  (map #(list 'or t (list 'not %)) args))
            
            nor (list 'and
                  (list 'or nt na)
                  (list 'or nt nb)
                  (list 'or t a b))
          
            impl (list 'and
                   (list 'or t a)
                   (list 'or t nb)
                   (list 'or nt na b))
            
            nimpl (list 'and
                    (list 'or nt a)
                    (list 'or nt nb)
                    (list 'or t na b))
            
            cimpl (list 'and
                    (list 'or t na)
                    (list 'or t b)
                    (list 'or nt a nb))
            
            ncimpl (list 'and
                     (list 'or nt na)
                     (list 'or nt b)
                     (list 'or t a nb))
            
            equiv (list 'and
                    (list 'or nt a nb)
                    (list 'or nt na b)
                    (list 'or t na nb)
                    (list 'or t a b))
            
            xor (list 'and
                  (list 'or t na b)
                  (list 'or t a nb)
                  (list 'or nt a b)
                  (list 'or nt na nb))
          
            ;default
            (println "Tseitin-CNF not implemented for" op)))))))

(defn- generate-tseitin-symbols
  "Recursive function for tseitin conversion to generate the new symbols."
  [formula tmap]
  (if (literal? formula)
    [formula tmap]
    (let [rem-retvals (map #(generate-tseitin-symbols % tmap) (if (seq? formula) (rest formula) formula))
          new-sym (gensym tseitin-prefix)
          new-formula (tseitin-cnf 
                        (apply list (first formula) (map first rem-retvals))
                        new-sym)
          new-tmap (reduce merge (map second rem-retvals))
          ]

      [new-sym (conj new-tmap [new-sym new-formula])]
    )))

(defn transform-tseitin
  "Takes a formula in clojure code and applies the tseitin transformation to it."
  [formula]
  (let [gen-list (generate-tseitin-symbols formula {})]
    (flatten-ast (apply list 'and
                   (first gen-list)
                   (vals (second gen-list))))
  )
)