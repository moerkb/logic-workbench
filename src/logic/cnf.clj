(ns logic.util)

(defn impl-free 
  "Take a formula in clojure code and substitutes all subformulas that only 
   not, and, or are used"
  [formula]
  (if (literal? formula)
    ;then do the trivial case
    formula

    ;else proceed with recursion
	  (let [op (first formula)]
      (if (contains? #{'and 'or 'not} op)
        
        ;then do not expand and macros, but proceed recursivley
        (apply list op (map #(impl-free %) (rest formula)))
        
        ;else expand one macro and proceed recursively
        (binding [*ns* (find-ns 'logic.util)]
          (let [expform (macroexpand-1 formula)]
            (apply list
              (first expform)
              (map #(impl-free %) (rest expform)))))
))))
  
(defn nnf
  "Takes an impl-free formula and produces the negation normal form, that is
   all negations are applied to atoms only and nested nots are reduced."
  [formula]
  (if (literal? formula)
	   ;then trivial case
	   formula
	   
	   ;else
	   (let [op (first formula)]
	     (cond
         ;(and a b) -> (and (nnf a) (nnf b))  
         ;same for or, but for arbitrary number or params
         (contains? #{'and 'or} op)
         
         (apply list op (map #(nnf %) (rest formula)))
        
         ;do nothing but return (made here and not in each of the following test)
         (or 
           (not= op 'not)
           (literal? (second formula)))
         
         formula
        
	       ;(not (not formula)) -> (nnf formula)
	       (= 'not (first (second formula)))

	       (nnf (second (second formula)))
	     
         ;(not (and a b)) -> (nnf (or (not a) (not b)))
         ;same for or, arbitrary number of params
         (not= 'not (first (second formula)))
         
         (let [sec-op (first (second formula))]
	         (nnf (apply list              
		              (if (= sec-op 'and) 'or 'and)
		              (map #(list 'not %) (rest (second formula))))))
		     
	       :else formula)
	     )))

(declare distr)

(defn- distr-bin
  "Takes (exactly) two formulas and applies the distribution."
  [formula-1 formula-2]
  (cond
    ;f-1 has the form (and f-11 f-12)
    ; -> apply (and (distr f-11 f-2) (distr f-12 f-2))
    (and 
      (not (literal? formula-1))
      (= 'and (first formula-1)))
    
    (apply list 'and (map #(distr % formula-2) (rest formula-1)))
    
    ;f-2 has the form (and f-21 f-22)
    ; -> apply (and (distr f-1 f-21) (distr f-1 f-22))
    (and 
      (not (literal? formula-2))
      (= 'and (first formula-2)))
    
    (apply list 'and (map #(distr formula-1 %) (rest formula-2)))
    
    :else
    (list 'or formula-1 formula-2)
))
  
(defn distr
  "Takes nnf formulas and applies the distribution to it."
  ([f] f)
  ([f & more] (distr-bin f (apply distr more))))

(defn cnf 
  "Takes a nnf formula and produces the conjunctive normal form of it. If the formula is not
   impl-free and nnf, you better use transform-cnf, which pipes these functions."
  [formula]
  (cond
    ;formula is literal -> return formula
    (literal? formula)
    (list 'and (list 'or formula))
    
    ;formula has the form 'f1 and f2' -> (and (cnf f1) (cnf f2))
    (= 'and (first formula))
    
    (apply list 'and (map cnf (rest formula)))
    
    ;formula has the form 'f1 or f2' -> (distr (cnf f1) (cnf f2))
    (= 'or (first formula))
    
    (apply distr (map cnf (rest formula)))
    
    
    (= 'not (first formula))
    
    (list 'and (list 'or formula))
    
    :else
    formula
))

(defn transform-cnf
  "Takes a formula in clojure code and produces the conjunctive normal form of it."
  [formula]
  (-> formula impl-free nnf cnf flatten-ast))