(ns logic.util)

(declare only-and-or-not)

(def cnf-trans-map
  {:impl   (fn [a b] `(or (not ~(only-and-or-not a)) ~(only-and-or-not b)))
   :nimpl  (fn [a b] `(not (or (not ~(only-and-or-not a)) ~(only-and-or-not b))))
   :rimpl  (fn [a b] `(or ~(only-and-or-not a) (not ~(only-and-or-not b))))
   :nrimpl (fn [a b] `(not (or ~(only-and-or-not a) (not ~(only-and-or-not b)))))
   :equiv  (fn [a b] `(and
					              (or (not ~(only-and-or-not a)) ~(only-and-or-not b))
					              (or ~(only-and-or-not a) (not ~(only-and-or-not b)))))
   :xor    (fn [a b] `(not (and
			                  (or (not ~(only-and-or-not a)) ~(only-and-or-not b))
			                  (or ~(only-and-or-not a) (not ~(only-and-or-not b))))))
   :nor    (fn [a b] `(not (or ~(only-and-or-not a) ~(only-and-or-not b))))
   :nand   (fn [a b] `(not (and ~(only-and-or-not a) ~(only-and-or-not b))))   
  })

(defn only-and-or-not
  "Takes a formula in clojure code and replaces all equivalents, implications and their
   corresponding negated parts with not, and, or."
  [formula]
  (if (and (not (boolean? formula)) (not (symbol? formula)))
    
    (let [junctor (-> formula first name keyword)
          a (second formula)
          b (nth formula 2)]
	    
      (if (contains? cnf-trans-map junctor)
         ((cnf-trans-map junctor) a b)	      
	       formula))

    formula))
     
(defn transform-cnf 
  "Takes a formula in clojure code and produces the cnf of it"
  [formula]
  (-> formula only-and-or-not))