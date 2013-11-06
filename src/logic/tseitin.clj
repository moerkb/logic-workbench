(ns logic.util)


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
	(when (and (not (boolean? formula)) (not (symbol? formula))) 
    (concat
      (list formula)
      (all-subformulas (second formula))
      (when (not= (name (first formula)) "not")
        (all-subformulas (nth formula 2)))
    )
))

(defn- subs-variables
  "Takes a formula in clojure code and a map of the form p_n -> phi and substitutes
   all yet defined variables in that formula recursively. Used by make-variables."
  [formula rev-map]
  ; if not on last level
  (if (and (not (boolean? formula)) (not (symbol? formula)))
    ; then
    (if (contains? rev-map formula)
      ; then: substitute
      (rev-map formula)
      
      ; else: go on recursively
      (remove nil? (concat
                     (list (first formula))
	                   (list (subs-variables (second formula) rev-map))
	                   (list (when (not= (name (first formula)) "not")
	                           (subs-variables (nth formula 2) rev-map)))))
    )
    ; else
    formula)
)

(defn make-variables
  "Take a list of all subformulas and creates a list of new variables for them"
  [subforms]
  (let [forms (sort-by (comp count flatten) subforms)
        var-map (zipmap (map #(keyword (str "p_" %)) (range 0 (count forms))) forms)
        var-keys (sort (keys var-map))]
       
    ; substitute each subformula if it already has a variable assigned
    (apply merge
	    (for [k var-keys
            :let [map-wo-formula (dissoc var-map k)]]
	      (hash-map k (subs-variables 
                     (var-map k) 
                     (zipmap (vals map-wo-formula) (keys map-wo-formula))
                     )))))
  )

(defn tseitin-transform
  "Takes a formula in clojure code and applies the tseitin transfomation to it"
  [formula]
  (let [subforms (all-subformulas formula)
        var-map (make-variables subforms)]
    var-map
    ))