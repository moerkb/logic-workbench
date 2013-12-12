(ns examples.map-colors
  (:require [logic.util :refer :all]))

stop

(def colors '[red green blue yellow])

(def countries '[lu de fr be])

(defn one-color [country colors]
  (oneof (map #(combine-syms country %) colors)))

(defn countries-one-color [countries colors]
  (apply list 'and (map #(one-color % colors) countries)))

(defn adjacent [c1 c2]
  (apply list 'and (map #(list 'or (list 'not %1) (list 'not %2))
                     (map #(combine-syms c1 %) colors)
                     (map #(combine-syms c2 %) colors))))

(def fml 
  (list 'and
    (countries-one-color countries colors)
    (adjacent 'lu 'de)
    (adjacent 'lu 'fr)
    (adjacent 'lu 'be)
    (adjacent 'de 'fr)
    (adjacent 'fr 'be)
    (adjacent 'be 'de)))

(def solution
  (let [tseit (transform-tseitin fml)
        result (-> (tseit :tseitin-formula) generate-dimacs-clauses sat-solve)]
    (if (zero? (first result))
          (println "Problem is not satisfiable.")
          (do
            (println "Problem is satisfiable.")
            (retransform-tseitin result tseit)))))

(filter literal? solution)