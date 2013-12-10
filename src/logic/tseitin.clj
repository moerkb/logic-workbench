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
  "Generate tseitin symbols for all subformulas and literals."
  [formula]
  (let [lits (find-variables formula)
        z (atom 0)
        tmap (atom (apply merge (map 
                                  #(hash-map (symbol (str tseitin-prefix (swap! z inc))) %) 
                                  lits))) 
        ]
    (gen-tseit-rec formula z tmap)))

(defn- gen-tseit-rec
  "Recursive helper function for generate-tseitin-symbols."
  [f z t]
  (if (literal? f)
    t
    (let [rec-syms (for [f' (rest f)]
                     (gen-tseit-rec f' z t))]
      (swap! t #(conj % 
                  {(symbol (str tseitin-prefix (swap! z inc)))
                   (apply list (first f) rec-syms)})))))
  
#_(defn foo  
   (if (literal? formula)
     (do 
       (println formula "in" tmap "=" (contains? tmap formula))
     (if (contains? tmap formula)  ; symbol is already in the hash map    
       [(tmap formula) tmap lit-set]
      
       (let [new-sym (symbol (str tseitin-prefix (swap! z inc)))
             new-tmap (assoc tmap new-sym formula)]
         [new-sym new-tmap (conj lit-set new-sym)])))
    
     (let [rem-retvals (map #(generate-tseitin-symbols % tmap lit-set z) (rest formula))
           new-sym (symbol (str tseitin-prefix (swap! z inc)))
           new-formula (tseitin-cnf 
                         (apply list (first formula) (map first rem-retvals))
                         new-sym)
           new-tmap (reduce merge (map second rem-retvals))
           new-lit-set (reduce union (map #(nth % 2) rem-retvals))]

       [new-sym 
        (conj new-tmap [new-sym new-formula]) 
        new-lit-set]
     )))

(defn transform-tseitin
  "Takes a formula in clojure code and applies the tseitin transformation to it."
  [formula]
  (let [gen-list (generate-tseitin-symbols formula {} #{} (atom 0))
        subs-keys (keys (second gen-list))
        rev-subs (zipmap (vals (second gen-list)) subs-keys)
        lit-list (nth gen-list 2)]
    {:formula formula
     :lits (zipmap (sort lit-list)
                   (sort (map #(% (second gen-list)) (filter #(contains? lit-list %) subs-keys))))
     :tseitin-formula (flatten-ast (apply list 'and
                                     (first gen-list)
                                     (filter #(not (lit-list (rev-subs %))) (vals (second gen-list)))))}))

(defn retransform-tseitin
  "Takes a map as produced by transform-tseitin and a solver result and removes all tseitin
   variables and resubstitutes the symbols to the original ones."
  [result tmap]
  (let [formula (tmap :formula)
        subs (tmap :subs)
        lits (tmap :lits)
        lit-syms (map literal? 
                      (filter #(contains? lits (if (literal? %) % (second %))) (rest result)))
        orig-subs (zipmap (replace subs (sort lits)) lit-syms)]
    orig-subs))
    