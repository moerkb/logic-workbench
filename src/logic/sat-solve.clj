(ns logic.util)

(import '(org.sat4j.minisat SolverFactory)
        '(org.sat4j.minisat.core Solver)
        '(org.sat4j.core VecInt)
        '(org.sat4j.specs IProblem)
        '(org.sat4j.reader DimacsReader))

(defn sat4j-solve
  [dimacs-map]
  (let [solver (SolverFactory/newDefault)
        clauses (:clauses dimacs-map)
        MAXVAR (:num-vars dimacs-map)
        NBCLAUSES (:num-clauses dimacs-map)]
    (.newVar solver MAXVAR)
    (.setExpectedNumberOfClauses solver NBCLAUSES)

    (loop [c clauses]
      (if (first c)
        (let [n (first c)
              clause (subvec n 0 (dec (count n)))]
          (.addClause solver (VecInt. (int-array clause)))
          (recur (rest c)))))

    (if (.isSatisfiable solver)
      (do
        (println "Satisfiable!")
        (.decode (DimacsReader. solver) (.model solver)))
      (println "Unsatisfiable!"))))

(defn sat-solve
  [dimacs-map]
  (sat4j-solve dimacs-map))
