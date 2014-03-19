(ns logic.util)

(def reserved-symbols 
  #{'and 'nand 'or 'nor 'impl 'nimpl 'cimpl 'ncimpl 'equiv 'xor 'true 'false 'not})

(def n-ary-symbols
  #{'and 'or})

(defn boolean?
  "True if the parameter is of the type Boolean, false otherwise"
  [x]
  (= (class x) java.lang.Boolean))

(defn one-is-bool?
  "True if one element of the formula is a boolean constant."
  [formula]
  (if (coll? formula)
    (if (nil? (some boolean? (rest formula)))
      false
      true)
    false))

(defn one-is-true?
  "True if one element of the formula is true."
  [formula]
  (if (coll? formula)
    (if (nil? (some true? (rest formula)))
      false
      true)
    false))

(defn one-is-false?
  "True if one element of the formula is false."
  [formula]
  (if (coll? formula)
    (if (nil? (some false? (rest formula)))
      false
      true)
    false))

(defn negate-formula-elements
  "Negates all arguments of a formula (e.g. (and a b c) => (and (not a) (not b) (not c))."
  [formula]
  (conj
    (map #(list 'not %) (rest formula))
    (first formula)))

(defn n-ary?
  [x]
  (contains? n-ary-symbols x))

(defn literal?
  "True if x is a constant (true/false) or an atom"
  [x]
  (or 
    (boolean? x) 
    (and (symbol? x) (not (contains? reserved-symbols x)))))

(def reconvert-map
  {'not "!"
   'or  "|"
   'nor "!|"
   'and "&"
   'nand "!&"
   'cimpl "<-"
   'ncimpl " nif "
   'impl "->"
   'nimpl " nimpl "
   'equiv "<->"
   'xor " xor "})

(defn clj-to-fml 
  "Takes a formula in clojure code and produces the human formula of it (only 'and', 'or' and 'not')."
  [fml]
  (if (seq? fml)
    (let [op (first fml)
          arg1 (second fml)]
      (if (= op 'not)
        (str "!" (clj-to-fml arg1))
        (str "(" 
          (clj-to-fml arg1) 
          (apply str (map #(str (reconvert-map op) (clj-to-fml %)) (rest (rest fml)))) ")"))) 
    (if (boolean? fml)
      (if (true? fml)
        "T"
        "F")
      fml)))

(defn- count-rec [f [cnt-op cnt-var]]
  (cond 
    (not (seq? f)) [cnt-op (inc cnt-var)]
    (= (first f) 'not) (count-rec (second f) [(inc cnt-op) cnt-var])
    :else (count-rec (nth f 2) (count-rec (second f) [(inc cnt-op) cnt-var]))
    ))

(comment
  (defn count-vars-ops [fml]
    (let [p-fml (parse fml)
          [cnt-op cnt-var] (count-rec p-fml [0 0])]
    
      (println "Number of characters:" (count fml))
      (println "Number of operators:" cnt-op)
      (println "Number of variables:" cnt-var))))

(comment
	(require '[parser_measures.formulas :as forms])
	
	(count-vars-ops forms/formula-usa)
	(count-vars-ops forms/formula-4-queens)
	(count-vars-ops forms/formula-8-queens)
	(count-vars-ops forms/formula-quarter-sudoku)
	(count-vars-ops forms/formula-half-sudoku)
	(count-vars-ops forms/formula-sudoku)
)