(ns gui.main)

(defn handler-sat
  "Handler function for action 'sat solve'."
  [e]
  (let [raw-res (logic/sat-solve-formula (.getText editor))
        proc-res (map #(if (coll? %)
                         [(second %) false]
                         [% true]) raw-res)]
    (if (zero? (count proc-res))
      (set-result! (table
                     :auto-resize :off
                     :model [:columns [{:key :message :text "Message"}]
                             :rows [["not satisfiable"]]]))
      (set-result! (table
                     :auto-resize :off
                     :model [:columns [{:key :symbol :text "Variable"} 
                                       {:key :value :text "Value"}]
                             :rows proc-res])))))

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
    
    (set-result! (table 
                   :auto-resize :off
                   :model [:columns var-keys
                           :rows (:table tt)]))))