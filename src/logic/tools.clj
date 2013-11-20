(ns logic.util)

(def reserved-symbols 
  #{'and 'nand 'or 'nor 'impl 'nimpl 'cimpl 'ncimpl 'equiv 'xor 'true 'false 'not})

(def n-ary-symbols
  #{'and 'nand 'or 'nor 'equiv})

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

(defn negate-all
  [& more]
  (map #(list 'not %) more))