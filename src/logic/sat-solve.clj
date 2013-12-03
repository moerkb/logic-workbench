(ns logic.util)

(import '(org.sat4j.minisat SolverFactory)
        '(org.sat4j.minisat.core Solver)
        '(org.sat4j.core VecInt)
        '(org.sat4j.specs IProblem)
        '(org.sat4j.reader DimacsReader))

(defn sat4j-solve
  [dimacs-map]
  (let [solver (SolverFactory/newDefault)
        reader (DimacsReader. solver)
        clauses (:clauses dimacs-map)
        MAXVAR (:num-vars dimacs-map)
        NBCLAUSES (:num-clauses dimacs-map)]
    (.newVar solver MAXVAR)
    (.setExpectedNumberOfClauses solver NBCLAUSES)

    (doall (map
             #(.addClause solver (VecInt. (int-array (subvec % 0 (dec (count %))))))
             clauses))

    (if (.isSatisfiable solver)
      (do
        (println "Satisfiable!")
        (.decode reader (.model solver)))
      (println "Unsatisfiable!"))))

(defn sat-solve
  "Returns one result as vector. The vector is epmty if the formula is unsatisfiable."
  [dimacs-map]
  (let [res-str (sat4j-solve dimacs-map)]
    (if (nil? res-str)
              []
              (vec (filter #(not= 0 %) (map #(Integer. %) (clojure.string/split res-str #" ")))))))
        
    
