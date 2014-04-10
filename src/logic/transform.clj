(ns logic.util)

(defn flatten-subast
  "Private function to flat a flattenable sub ast with a depth of one level."
  [ast]
  (if (and
        (coll? ast)
        (n-ary? (first ast)))
    (let [op (first ast) 
          args (rest ast)
          flat-filter (fn [op arg] (if (coll? arg) (= op (first arg)) false))
          flat (map #(rest %) (filter (partial flat-filter op) args))
          not-flat (filter (partial (complement flat-filter) op) args)]
        (apply concat  `((~op) ~@flat ~not-flat)))
    ast))
  
(defn flatten-ast
  "Flats the transformed ast.
   Nested binary functions will be transformed to one n-ary function when it is possible.
   (and (and a b) c) => (and a b c)"
  [ast]
  (postwalk flatten-subast ast))

(defn- remove-constants-one-layer
  "Removes one layer of constants. Call this only if one element of the formula is a boolean."
  [formula]
  (let [falses? (one-is-false? formula)
        trues? (one-is-true? formula)
        a (second formula)
        b (when (> (count formula) 2) 
            (nth formula 2))]
    (case (first formula)
      not (not (second formula))
      and (cond 
            falses? false
            trues? (let [new-form (filter (complement true?) formula)]
                     (cond
                       (= 1 (count new-form))
                       true
                       
                       (= 2 (count new-form))
                       a
                       
                       :else new-form)))
      nand (cond 
             falses? true
             trues? (let [new-form (filter (complement true?) formula)]
                      (cond
                        (= 1 (count new-form))
                        false
                        
                        (= 2 (count new-form))
                        (list 'not a))))
      or (cond
           trues? true
           falses? (let [new-form (filter (complement false?) formula)]
                     (cond 
                       (= 1 (count new-form))
                       false
                       
                       (= 2 (count new-form))
                       a
                       
                       :else new-form)))
      nor (cond 
            trues? false
            falses? (let [new-form (filter (complement false?) formula)]
                      (cond
                        (= 1 (count new-form))
                        true
                        
                        (= 2 (count new-form))
                        (list 'not a))))
      
      impl (cond
             (or (false? a) (true? b))
             true
              
             (and (true? a) (false? b))
             false
              
             (true? a)
             b
              
             (false? b)
             (list 'not a))
      
      nimpl (cond
              (or (false? a) (true? b))
              false
              
              (and (true? a) (false? b))
              true
              
              (false? b)
              a
              
              (true? a)
              (list 'not a))
      
      cimpl (cond
              (or (true? a) (false? b))
              true
              
              (and (false? a) (true? b))
              false
              
              (true? b) 
              a
              
              (false? a)
              (list 'not b))
      
      ncimpl (cond
               (or (true? a) (false? b))
               false
               
               (and (false? a) (true? b))
               true
               
               (false? a)
               b
               
               (true? b)
               (list 'not a))
      
      equiv (cond
              (or
                (and (true? a) (true? b))
                (and (false? a) (false? b)))
              true
              
              (or 
                (and (true? a) (false? b))
                (and (false? a) (true? b)))
              false
              
              (true? a) b
              (true? b) a
              
              (false? a) (list 'not b)
              (false? b) (list 'not a))
      
      xor (cond
          (or
            (and (true? a) (true? b))
            (and (false? a) (false? b)))
          false
              
          (or 
            (and (true? a) (false? b))
            (and (false? a) (true? b)))
          true
              
          (false? a) b
          (false? b) a
              
          (true? a) (list 'not b)
          (true? b) (list 'not a))
      
      ;default 
      (println "Remove constants not implemented for" (first formula)))))

(defn- remove-constants-rec
  "Recursive helper function for (remove-constants)."
  [formula]
  (if (literal? formula)
    formula  ; trivially return bools and atoms
    (let [new-form (conj
                     (map remove-constants-rec (rest formula))
                     (first formula))]
      (if (one-is-bool? new-form)
        ; do the twist: reduce formula if possible
        (remove-constants-one-layer new-form) 
        new-form))))

(defn remove-constants
  "Removes boolean constants from formula, if there are any (known from meta data :constants?,
  invoked if the meta data is not set). Returns the reduced formula; true if trivially true and 
  false if trivially false. If no constants are in there, it returns the original formula."
  [formula]
  (let [form-meta (meta formula)]
    (if-not (and 
              (contains? form-meta :constants?)
              (false? (:constants? form-meta)))
      (remove-constants-rec formula)
      formula)))

(defn- tautology?
  "Needs all symbols of a conjunction as collection."
  [symbols]
  (let [complement-first (if (coll? (first symbols))
                           (second (first symbols))
                           (list 'not (first symbols)))
        rest (set (rest symbols))]
    (if (<= (count symbols) 1)
      false
      (if (contains? rest complement-first)
        true
        (tautology? rest)))))

(defn remove-tautologies
  "Needs a CNF with an 'and' at fist symbol and 'or' as first symbol in every clauses."
  [formula]
  (conj (map
          #(conj (list* (set (rest %))) 'or)
          (filter #(not (tautology? (rest %))) (rest formula)))
        'and))
          
