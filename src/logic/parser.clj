(ns logic.util)

; define parser
#_(comment
	 (def logic-parser
	   (insta/parser "src/logic/grammar.txt"))
	
	 (defn logic-parse [formula]
	   (let [ast (insta/parses logic-parser formula)]
	     (first (first ast))))
	
	 (defn pure-parse [formula]
	   (insta/parse logic-parser formula)))

(defn parse
  "Invokes the JavaCC parser and performs (remove-constants) on it."
  [formula]
  (remove-constants (mpaParser/javaCCparse formula)))