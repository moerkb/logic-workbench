(ns logic.util)

(import '(org.sat4j.minisat SolverFactory)
        '(org.sat4j.minisat.core Solver)
        '(org.sat4j.core VecInt)
        '(org.sat4j.specs IProblem)
        '(org.sat4j.reader DimacsReader))

(defn sat4j-solve
  [dimacs-map]
  (let [solver (SolverFactory/newDefault)
        MAXVAR (:num-vars dimacs-map)
        NBCLAUSES (:num-clauses)]
    (.newVar solver MAXVAR)
    (.setExpectedNumberOfClauses solver NBCLAUSES)

    (let [clauses (:clauses dimacs-map)]
      (map #(.addClause solver (VecInt. (int-array %))) clauses))

    (if (.isSatisfiable solver)
      (do
        (println "Satisfiable!")
        (.decode (DimacsReader. solver) (.model solver))
        )
      (println "Unsatisfiable!"))))

(defn sat-solve
  [dimacs-map]
  (sat4j-solve dimacs-map))
