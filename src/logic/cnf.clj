(ns logic.util)

; TODO impl expands to macro (if (not A) true B) - should that be?

(defn impl-free 
  "Take a formula in clojure code and substitutes all subformulas that only 
   not, and, or are used"
  [formula]
  (if (literal? formula)
    ;then do the trivial case
    formula

    ;else proceed with recursion
	  (let [op (first formula)
	        name-op (name op)]
      (if (or
            (= name-op "and")
            (= name-op "or")
            (= name-op "not"))
        
        ;then do not expand and macros, but proceed recursivley
        (concat
          (list op)
            (for [elem (rest formula)]
              (impl-free elem)
            ))
        
        ;else expand one macro and proceed recursively
        (let [expform (macroexpand-1 formula)]
          (concat
            (list (first expform))
            (for [elem (rest expform)]
              (impl-free elem)
            )))))))
  
(defn nnf
  "Takes an impl-free formula and produces the negation normal form, that is
   all negations are applied to atoms only and nested nots are reduced."
  [formula]
  (if (literal? formula)
	   ;then trivial case
	   formula
	   
	   ;else
	   (let [op (first formula)
	         name-op (name op)]
	     (cond
	       ;(not (not formula)) -> (nnf formula)
	       (and 
	         (= name-op "not")
	         (not (literal? (-> formula second)))
	         (= "not" (-> formula second first name)))

	       (nnf (-> formula second second))
	     
         ;(and a b) -> (and (nnf a) (nnf b))  
         ;same for or, but for arbitrary number or params
         (or 
           (= name-op "and")
           (= name-op "or"))
         
         (concat 
           (list op) 
           (for [elem (rest formula)]
             (nnf elem)))
        
         ;(not (and a b)) -> (nnf (or (not a) (not b)))
         ;same for or, arbitrary number of params
         (and
           (= name-op "not")
           (not (literal? (-> formula second)))
	         (not= "not" (-> formula second first name)))
         
         (let [sec-op (-> formula second first name)]
	         (nnf `(              
                    ~(if (= sec-op "and")
                       `or
                       `and)
                    ~@(map (fn [e] `(not ~e)) (-> formula second rest)))))
         
	       :else formula)
	     )))

(defn transform-cnf 
  "Takes a formula in clojure code and produces the cnf of it"
  [formula]
  (-> formula impl-free nnf))