(ns logic.util)

(defn- generate-tseitin-symbols
  "Recursive function for tseitin conversion to generate the new symbols."
  [formula tmap]
    (if (literal? formula)
      [formula tmap]
      (let [rem-retvals (map #(generate-tseitin-symbols % tmap) (rest formula))
            new-formula (apply list (first formula) (map first rem-retvals))
            new-tmap (reduce merge (map second rem-retvals))
            new-sym (gensym tseitin-prefix)]

        [new-sym (conj new-tmap [new-sym new-formula])]
      )))
                      

(defn tseitin-transform
  "Takes a formula in clojure code and applies the tseitin transformation to it."
  [formula]
  (let [gen-list (generate-tseitin-symbols formula {})]
    (apply list 'and
      (first gen-list)
      (map
        #(list 'equiv (first %) (second %))
        (second gen-list)))
  )
)