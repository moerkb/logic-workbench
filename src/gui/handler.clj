(ns gui.main)

(defn handler-tt
  "Handler function for action 'truth table."
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