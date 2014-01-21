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

(defn- gen-tseit-rec
  "Recursive helper function for generate-tseitin-symbols."
  [f z t]
  (do 
    (when (not (literal? f))
      (doall (for [f' (rest f)]
               (gen-tseit-rec f' z t))))
    (swap! t #(assoc 
                % 
                (symbol (str tseitin-prefix (swap! z inc)))
                f))))

(defn- generate-tseitin-symbols
  "Generate tseitin symbols for all subformulas and literals."
  [formula z]
  (let [tmap (atom (hash-map))
        _ (gen-tseit-rec formula z tmap)
        rev-tmap (zipmap (vals @tmap) (keys @tmap))
        ]
    (do 
      (doseq [[k v] @tmap]
        (when (not (literal? v))
          (swap! tmap (fn [_]
                        (let [new-v (replace rev-tmap v)]
                          (assoc @tmap k (tseitin-cnf new-v k)))))))
      @tmap
    )))
    
(defn transform-tseitin
  "Takes a formula in clojure code and applies the tseitin transformation to it."
  [formula]
  (let [z (atom 0)
        gen-map (generate-tseitin-symbols formula z)
        rev-subs (zipmap (vals gen-map) (keys gen-map))
        lit-list (set (filter literal? (vals gen-map)))]
    {:formula formula
     :lits (apply merge (map (fn [l] {l (l rev-subs)}) lit-list)) 
     :tseitin-formula (flatten-subast (apply list 'and
                                        (list 'or (symbol (str tseitin-prefix @z)))
                                        (filter (complement literal?) (vals gen-map))))}))

(defn retransform-tseitin
  "Takes a map as produced by transform-tseitin and a solver result and removes all tseitin
   variables and resubstitutes the symbols to the original ones."
  [result tmap]
  (let [subs (zipmap (vals (:lits tmap)) (keys (:lits tmap)))
        lits (set (vals (:lits tmap)))
        cln-res (filter #(contains?
                           lits
                           (if (literal? %) % (second %)))
                 result)]
    (map #(if (seq? %) 
            (list 'not (get subs (second %)))
            (get subs %))
      cln-res)))
    