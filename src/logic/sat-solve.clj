(ns logic.util)

(import '(org.sat4j.minisat SolverFactory)
        '(org.sat4j.minisat.core Solver)
        '(org.sat4j.core VecInt)
        '(org.sat4j.specs IProblem)
        '(org.sat4j.reader DimacsReader))

(defn- sat4j-solve
  [dimacs-map]
  (let [solver (SolverFactory/newDefault)
        reader (DimacsReader. solver)
        clauses (:clauses dimacs-map)
        MAXVAR (:num-vars dimacs-map)
        NBCLAUSES (:num-clauses dimacs-map)]
    (.newVar solver MAXVAR)
    (.setExpectedNumberOfClauses solver NBCLAUSES)

    (doall (map
             #(.addClause solver (VecInt. (int-array %)))
             clauses))

    (when (.isSatisfiable solver)
      (.decode reader (.model solver)))))

(defn- replace-number-with-literal
  [dimacs-map res-vec]
  (let [substitutions (apply hash-map (apply concat (map (fn [[k v]] [k v (Integer. (str "-" k)) (list 'not v)]) (:subs dimacs-map))))]
                             (replace substitutions res-vec)))

(defn sat-solve-dimacs
  "Returns one result as vector like the dimace clauses in the dimacs-map.
   The first number is 0 or 1:
   0: the formula is unsatisfiable
   1: the formula is satisfiable"
  [dimacs-map]
  (let [res-str (sat4j-solve dimacs-map)]
    (if (nil? res-str)
              [0]
              (vec (cons 1 (filter #(not= 0 %) (map #(Integer. %) (clojure.string/split res-str #" "))))))))

(defn sat-solve
  "Returns one result as set with litereals.
   The first number is 0 or 1:
   0: the formula is unsatisfiable
   1: the formula is satisfiable"
  [dimacs-map]
  (let [res-str (sat4j-solve dimacs-map)]
    (if (nil? res-str)
              [0]
              (vec (cons 1 (let [numvec  (vec (filter #(not= 0 %) (map #(Integer. %) (clojure.string/split res-str #" "))))]
                             (replace-number-with-literal dimacs-map numvec)))))))

(defn sat-solve-dimacs-find-all-results
  "Returns all results as set of vectors.
   Vectors look like the dimace clauses in the dimacs-map."
  [dimacs-map]
  (set (loop
         [d dimacs-map results '()]
         (let [res (sat-solve-dimacs d)]
           (if (= 0 (first res))
        results
        (recur (add-negated-dimacs-result d (rest res)) (conj results (subvec res 1))))))))

(defn sat-solve-find-all-results
  "Returns all result as sequence of vector with litereals."
  [dimacs-map]
  (set (map (partial replace-number-with-literal dimacs-map) (sat-solve-dimacs-find-all-results dimacs-map))))

(defn sat-solve-formula
  [formula]
  (let [tmap (-> formula mpaParser/javaCCparse transform-tseitin)
        result (-> tmap :tseitin-formula generate-dimacs-clauses sat-solve rest)]
    (retransform-tseitin result tmap)))

(defn sat-solve-find-all-results-formula
  [formula]
  (let [tmap (-> formula mpaParser/javaCCparse transform-tseitin)
        result (-> tmap :tseitin-formula generate-dimacs-clauses sat-solve-find-all-results)]
    (set (map #(retransform-tseitin % tmap) result))))
    