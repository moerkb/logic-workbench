(ns logic.util)

(defn tseitin-transform
  "Takes a formula in clojure code and applies the tseitin transfomation to it"
  [formula]
  (all-subformulas formula))

(defn all-subformulas
	"Takes a formula in clojure code and finds all subformulas.
	   The result is a list of lists containing the formulas, e.g.
	   '(and A (or (not B) C)) becomes (in no particular order)
	   '(
	     (!B)
	     (or !B C)
	     (and A (or !B C))
	    )"
	[formula]
	(when (and (not= (class formula) java.lang.Boolean) (not (symbol? formula))) 
    (concat
      (list formula)
      (all-subformulas (second formula))
      (when (not= (name (first formula)) "not")
        (all-subformulas (nth formula 2)))
    )
))