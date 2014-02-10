(ns gui.main)

(defn handler-tt
  "Handler function for action 'truth table."
  [e]
  (let [parsed (logic/parse (.getText editor))
        tt (logic/generate-truth-table parsed)]
    (.setText results "")
    (.append results (apply str (interpose " " (replace {:result \u03D5} (:symbols tt)))))
    (.append results (str \newline))
    
    (doseq [line (:table tt)]
      (.append results (apply str (interpose " " (map logic/abbrev-bool line))))
      (.append results (str \newline)))))