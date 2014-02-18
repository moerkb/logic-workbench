(ns gui.main)

(defn handler-tt
  "Handler function for action 'truth table."
  [e]
  (let [parsed (logic/parse (.getText editor))
        tt (logic/generate-truth-table parsed)]
    (println tt)
    ))