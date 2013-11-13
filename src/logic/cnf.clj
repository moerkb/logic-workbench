(ns logic.util)

; TODO impl expands to macro (if (not A) true B) - should that be?

(defn impl-free 
  "Take a formula in clojure code and substitutes all subformulas that only 
   not, and, or are used"
  [formula]
    (if (literal? formula)
      formula

		  (let [op (first formula)
		        name-op (name op)]
	      (if (or
	            (= name-op "and")
	            (= name-op "or")
	            (= name-op "not"))
	        (concat
	          (list op)
	            (for [elem (rest formula)]
	              (impl-free elem)
	            ))
	          (let [expform (macroexpand-1 formula)]
	            (concat
	              (list (first expform))
	              (for [elem (rest expform)]
	                (impl-free elem)
	              )))))))
  
(defn transform-cnf 
  "Takes a formula in clojure code and produces the cnf of it"
  [formula]
  (-> formula impl-free))