(ns logic.util)

; SAT4J code example
; ##################
;final int MAXVAR = 1000000;
;final int NBCLAUSES = 500000;
;
;ISolver solver = SolverFactory.newDefault();
;
;// prepare the solver to accept MAXVAR variables. MANDATORY for MAXSAT solving
;solver.newVar(MAXVAR);
;solver.setExpectedNumberOfClauses(NBCLAUSES);
;// Feed the solver using Dimacs format, using arrays of int
;// (best option to avoid dependencies on SAT4J IVecInt)
;for (int i=0;<NBCLAUSES;i++) {
;  int [] clause = // get the clause from somewhere
;  // the clause should not contain a 0, only integer (positive or negative)
;  // with absolute values less or equal to MAXVAR
;  // e.g. int [] clause = {1, -3, 7}; is fine
;  // while int [] clause = {1, -3, 7, 0}; is not fine 
;  solver.addClause(new VecInt(clause)); // adapt Array to IVecInt
;}
;
;Reader reader = new DimacsReader(solver);
;// we are done. Working now on the IProblem interface
;IProblem problem = solver;
;if (problem.isSatisfiable()) {
;   System.out.println(("Satisfiable !"));
;   System.out.println(reader.decode(problem.model()));
;} else {
;   System.out.println("Unsatisfiable !");
;}

(import '(org.sat4j.minisat SolverFactory)
        '(org.sat4j.minisat.core Solver)
        '(org.sat4j.core VecInt)
        '(org.sat4j.specs IProblem)
        '(org.sat4j.reader DimacsReader))

(def MAXVAR 1000000)
(def NBCLAUSES 500000)
(def solver (SolverFactory/newDefault))
(.newVar solver MAXVAR)
(.setExpectedNumberOfClauses solver NBCLAUSES)

; now only for test
; TODO read given args

(.addClause solver (VecInt. (int-array '(1 -2 3))))

(if (.isSatisfiable solver)
  (do
    (println "Satisfiable!")
    (def reader (DimacsReader. solver))
    (.decode reader (.model solver))
    )
  (println "Unsatisfiable!"))
