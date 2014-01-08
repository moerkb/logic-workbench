(ns logic.infix)

(def infix-op
  {'not    "!",
   'and    " & ",
   'nand   " !& ",
   'or     " | ",
   'nor    " !| ",
   'cimpl  " <- ",
   'ncimpl " !<- ",
   'impl   " -> ",
   'nimpl  " !-> ",
   'equiv  " <-> ",
   'xor    " ^ "})

(defn prefix->infix 
  "Transforms formula in prefix to string in infix"
  [expr]
  (let [transform (fn p->i [e]
                    (cond
                      (= e true)  "T"
                      (= e false) "F"
                      (symbol? e) (name e)
                      (list? e)
                        (if (= (first e) 'not)
                          (apply str (infix-op 'not) (p->i (second e)))
                          (str "(" (apply str (interpose (infix-op (first e))
                                                         (map p->i (rest e)))) ")"))))
        infix-expr (transform expr)]
    (if (= (first infix-expr) \()
    (subs infix-expr 1 (dec (.length infix-expr)))
    infix-expr)))


(def exp1 true)
(prefix->infix exp1)

(def exp2 false)
(prefix->infix exp2)

(def exp3 'p)
(prefix->infix exp3)

(def exp4 'phi)
(prefix->infix exp4)

(def exp5 '(and p q r s t))
(prefix->infix exp5)

(def exp6 '(and p q true))
(prefix->infix exp6)

(def exp7 '(or p q true))
(prefix->infix exp7)

(def exp8 '(impl p q))
(prefix->infix exp8)

(def exp9 '(and (impl p q) s false (or s1 s2) (equiv a b)))
(prefix->infix exp9)

(def exp10 '(and (or (and x y) (or y z)) (impl (and a b) (or a c))))
(prefix->infix exp10)


