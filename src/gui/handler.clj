(ns gui.main)

;; Menu Bar
(defn handler-parse
  "Handler function for action 'parse to clojure formula'."
  [_]
  (set-text-result! (str (apply list (parse-editor)))))
  
(defn handler-reparse
  "Reads a clojure formula form the editor and makes the formula of it."
  [_]
  (set-text-result! (logic/clj-to-fml (read-string (.getText editor)))))

(defn handler-sat
  "Handler function for action 'sat solve'."
  [_]
  (let [proc-res (map #(if (coll? %)
                         [(second %) false]
                         [% true]) 
                   (parse-editor))]
    (if (zero? (count proc-res))
      (set-table-result! [:columns [{:key :message :text "Message"}]
                          :rows [["not satisfiable"]]])
      (set-table-result! [:columns [{:key :symbol :text "Variable"} 
                                    {:key :value :text "Value"}]
                          :rows proc-res]))))

(defn handler-tt
  "Handler function for action 'truth table'."
  [_]
  (let [tt (logic/generate-truth-table (parse-editor))
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
  [_]
  (let [cnf (logic/transform-cnf (parse-editor))]
    (set-text-result! (str (apply list cnf)))))

(defn handler-tseitin
 "Handler function for action 'tseitin cnf'."
 [_]
 (let [tcnf (:tseitin-formula (logic/transform-tseitin (parse-editor)))]
   (set-text-result! (str (apply list tcnf)))))

(defn handler-dimacs
  "Handler function for action 'show dimacs'."
  [_]
  (let [dimacs (logic/generate-dimacs (logic/flatten-ast (parse-editor)))]
    (set-text-result! dimacs)))

(defn handler-dimacs-cnf
  "Handler function for action 'make cnf, then show dimacs'."
  [_]
  (let [dimacs (logic/generate-dimacs (logic/transform-cnf (parse-editor)))]
    (set-text-result! dimacs)))

(defn handler-dimacs-tseitin
  "Handler function for action 'make tseitin-cnf, then show dimacs'."
  [_]
  (let [dimacs (logic/generate-dimacs (:tseitin-formula (logic/transform-tseitin (parse-editor))))]
    (set-text-result! dimacs)))

;; Project Tree
(defn handler-tree
  [e]
  (let [s (selection e)]
    (when (>= (count s) 2)
      (.setText editor (.getProposition (last s))))))
