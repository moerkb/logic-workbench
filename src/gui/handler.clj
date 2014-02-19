(ns gui.main)

;; Menu Bar
(defn handler-sat
  "Handler function for action 'sat solve'."
  [e]
  (let [raw-res (logic/sat-solve-formula (.getText editor))
        proc-res (map #(if (coll? %)
                         [(second %) false]
                         [% true]) raw-res)]
    (if (zero? (count proc-res))
      (set-table-result! [:columns [{:key :message :text "Message"}]
                          :rows [["not satisfiable"]]])
      (set-table-result! [:columns [{:key :symbol :text "Variable"} 
                                    {:key :value :text "Value"}]
                          :rows proc-res]))))

(defn handler-tt
  "Handler function for action 'truth table'."
  [e]
  (let [parsed (logic/parse (.getText editor))
        tt (logic/generate-truth-table parsed)
        vars (butlast (:symbols tt))
        var-keys (conj 
                   (vec (map 
                          (fn [key text]
                            {:key key :text (str text)}) 
                          (map keyword vars) 
                          vars))
                   {:key :result :text "\u03D5"})]
    
    (set-table-result! [:columns var-keys
                        :rows (:table tt)])))

(defn handler-cnf
  "Handler function for action 'cnf'."
  [e]
  (let [parsed (logic/parse (.getText editor))
        cnf (logic/transform-cnf parsed)]
    (set-text-result! (str (apply list cnf)))))

(defn handler-tseitin
 "Handler function for action 'tseitin cnf'."
 [e]
 (let [parsed (logic/parse (.getText editor))
       tcnf (:tseitin-formula (logic/transform-tseitin parsed))]
   (set-text-result! (str (apply list tcnf)))))

(defn handler-dimacs
  "Handler function for action 'show dimacs'."
  [e]
  (let [parsed (logic/parse (.getText editor))
        dimacs (logic/generate-dimacs (logic/flatten-ast parsed))]
    (set-text-result! dimacs)))

;; Project Tree
(defn handler-tree
  [e]
  (let [s (selection e)]
    (when (>= (count s) 2)
      (.setText editor (.getProposition (last s))))))
