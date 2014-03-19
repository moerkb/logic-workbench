(ns mpa.cst.parser.parser)

(import '(mpa.cst.parser MpaParser
                         MpaStart)
        '(java.io StringReader))

(MpaParser. (StringReader. ""))

(defn- cst2list
  [cst *const?*]
  (case (.jjtGetNumChildren cst)
    0 (case (.getLexeme cst) ; Boolean | Atom
        "TRUE" (do (reset! *const?* true) 'true)
        "FALSE" (do (reset! *const?* true) 'false)
        (symbol (.getLexeme cst)))
    1 (if (= (class cst) mpa.cst.parser.MpaNotExpr) ; NOT | hierarchic descent
        (list 'not (cst2list (.jjtGetChild cst 0) *const?*))
        (cst2list (.jjtGetChild cst 0) *const?*))
    2 (let [op-nr (.getOp cst)
            AND (MpaParser/AND)
						NAND (MpaParser/NAND)
						OR (MpaParser/OR)
						NOR (MpaParser/NOR)
						IF (MpaParser/IF)
						NIF (MpaParser/NIF)
						IMPL (MpaParser/IMPL)
						NIMPL (MpaParser/NIMPL)
						IFF (MpaParser/IFF)
						XOR (MpaParser/XOR)
            op (cond
                 (= op-nr AND) 'and
                 (= op-nr NAND) 'nand
                 (= op-nr OR) 'or
                 (= op-nr NOR) 'nor
                 (= op-nr IF) 'cimpl
                 (= op-nr NIF) 'ncimpl
                 (= op-nr IMPL) 'impl
                 (= op-nr NIMPL) 'nimpl
                 (= op-nr IFF) 'equiv
                 (= op-nr XOR) 'xor)]
        (list op (cst2list (.jjtGetChild cst 0) *const?*) (cst2list (.jjtGetChild cst 1) *const?*)))))

(defn javaCCparse
  [formula]
  (MpaParser/ReInit (StringReader. formula))
  (let [cst (MpaParser/Start)
        *const?* (atom false)
        parsed (cst2list cst *const?*)]
    (with-meta parsed {:constants? @*const?*})))

