(ns logic.util)

(def reserved-symbols 
  #{'and 'nand 'or 'nor 'impl 'nimpl 'cimpl 'ncimpl 'equiv 'xor 'true 'false 'not})

(def n-ary-symbols
  #{'and 'or})

(defn boolean?
  "True if the parameter is of the type Boolean, false otherwise"
  [x]
  (= (class x) java.lang.Boolean))

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
   'and "&"})

(defn clj-to-fml 
  "Takes a formula in clojure code and produces the human formula of it (only 'and', 'or' and 'not')."
  [fml]
  (if (seq? fml)
    (let [op (first fml)
          arg1 (second fml)]
      (if (= op 'not)
        (str "!" (clj-to-fml arg1))
        (str "(" (clj-to-fml arg1) (apply str (map #(str (reconvert-map op) (clj-to-fml %)) (rest (rest fml)))) ")"))) 
    fml))
