(ns logic.util)

(defn- tseit-sub
  "Substitution function for tseitin-tranform postwalk."
  [node]
  (if (list? node)
    (let [gen (gensym tseitin-prefix)]
      (case (first node)
        not (let [a (second node)] 
			        (list 'and 
				        (list 'or 
				          (list 'not gen)
				          (list 'not (second node)))
				        (list 'or
				          gen
				          (second node))))
        
        and (let [[_ a b] node]
              (list 'and
                (list 'or (list 'not gen) a)
                (list 'or (list 'not gen) b)
                (list 'or gen (list 'not a) (list 'not b))))
        
        or (let [[_ a b] node]
             (list 'and
               (list 'or gen (list 'not a))
               (list 'or gen (list 'not b))
               (list 'or (list 'not gen) a b)))
        
        impl (let [[_ a b] node]
               (list 'and
                 (list 'or gen a)
                 (list 'or gen (list 'not b))
                 (list 'or (list 'not gen) (list 'not a) b)))
        
        (str "tseit-sub not implemented for: " (first node))))

    node)
)

;(-> "p1 or (p1 and (p3 -> p4))" logic-parse transform-ast tseitin-transform)
(-> "!A and !(B or C)" logic-parse transform-ast tseitin-transform)

(defn tseitin-transform
  "Takes a formula in clojure code and applies the tseitin transformation to it."
  [formula]
  (postwalk tseit-sub formula)
)
