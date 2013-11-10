(ns logic.util)

(declare only-and-or-not)

(def cnf-trans-map
  {:impl   (fn [a b] `(or 
                        (not ~(only-and-or-not a)) 
                          ~(only-and-or-not b)))
   :nimpl  (fn [a b] `(not 
                        (or 
                          (not ~(only-and-or-not a)) 
                          ~(only-and-or-not b))))
   :rimpl  (fn [a b] `(or 
                        ~(only-and-or-not a) 
                        (not ~(only-and-or-not b))))
   :nrimpl (fn [a b] `(not 
                        (or 
                          ~(only-and-or-not a)
                          (not ~(only-and-or-not b)))))
   :equiv  (fn [a b] `(and
					              (or 
                          (not ~(only-and-or-not a)) 
                          ~(only-and-or-not b))
					              (or 
                         ~(only-and-or-not a) 
                         (not ~(only-and-or-not b)))))
   :xor    (fn [a b] `(not (and
			                  (or 
                          (not ~(only-and-or-not a)) 
                          ~(only-and-or-not b))
			                  (or 
                          ~(only-and-or-not a) 
                          (not ~(only-and-or-not b))))))
   :nor    (fn [a b] `(not 
                        (or 
                          ~(only-and-or-not a) 
                          ~(only-and-or-not b))))
   :nand   (fn [a b] `(not 
                        (and 
                          ~(only-and-or-not a) 
                          ~(only-and-or-not b))))  
   :not    (fn [a]   `(not ~(only-and-or-not a)))
  })

(defn not-atoms-only
  "Takes a formula in clojure code (preferably only with and, or, not) and makes all 
   negations stand only in front of atoms."
  [formula]
  (if (and (not (boolean? formula)) (not (symbol? formula)))
    (let [op (first formula)
          a (second formula)]
	    (if (and 
	          (= (name op) "not")
            (not (boolean? a))
            (not (symbol? a)))
	      
	      ; then: look ahead the child operator
        (let [child-op (name (first a))
              child-a (second a)]
          
          (if (= child-op "not")
            
            ; child operator is not - go one with recursion
            (not-atoms-only child-a)
            
            ; child operator is not 'not' - apply De Morgan rules
            (case child-op
              "and" `(or (not ~(not-atoms-only child-a)) 
                         (not ~(not-atoms-only (nth a 2))))
              
              "or" `(and (not ~(not-atoms-only child-a)) 
                         (not ~(not-atoms-only (nth a 2))))
              
              ;else
              (throw (Exception. (str "Unimplmented for operation:" child-op)))
              )))
	 
	      ; else: proceed recursively
	      (remove nil? (list 
	                     op
	                     (not-atoms-only a)
	                     (when (not= (name op) "not")
	                       (not-atoms-only (nth formula 2)))
	                     )))
	      )
	    formula))

(defn only-and-or-not
  "Takes a formula in clojure code and replaces all equivalents, implications and their
   corresponding negated parts with not, and, or."
  [formula]
  (if (and (not (boolean? formula)) (not (symbol? formula)))
    
    (let [junctor (-> formula first name keyword)
          a (second formula)]
	    
      (if (contains? cnf-trans-map junctor)
        (if (= junctor :not)
	         ((cnf-trans-map junctor) a)	      
	         ((cnf-trans-map junctor) a (nth formula 2)))	      
	       formula))

    formula))
     
(defn reduce-not
  "Takes a formula in clojure code and reduces all negations in the form, e. g.
     (not (not expr)) -> expr
     (not false) -> true"
  [formula]
  (if (and (not (boolean? formula)) (not (symbol? formula)))
    (let [op (first formula)
          a (second formula)]

      (if (= (name op) "not")
        ; reduce not
        (cond
          ;case boolean
          (and (not (symbol? a)) (boolean? a))
          
          (not a)
          
          ;case another not
          (and 
            (not (symbol? a)) 
            (not (boolean? a))
            (= (-> a first name) "not"))
          
          (do 
            (println "Hello" )
          (reduce-not (second a)))
            
          :else (remove nil? (list 
	                           op
	                           (reduce-not a)
	                           (when (not= (name op) "not")
	                             (reduce-not (nth formula 2)))
	                           )))
          
        ; else
        (remove nil? (list 
                       op
                       (reduce-not a)
                       (when (not= (name op) "not")
                         (reduce-not (nth formula 2)))
                       ))
      ))
    ; else
    formula))
  
(defn transform-cnf 
  "Takes a formula in clojure code and produces the cnf of it"
  [formula]
  (-> formula only-and-or-not not-atoms-only reduce-not))