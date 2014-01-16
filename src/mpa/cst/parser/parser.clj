(ns mpa.cst.parser.parser)

(import '(mpa.cst.parser MpaParser
                         MpaStart)
        '(java.io StringReader))

(MpaParser. (StringReader. ""))

(defn- cst2list
  [cst]
  (case (.jjtGetNumChildren cst)
    0 (case (.getLexeme cst) ; Boolean | Atom
        "TRUE" 'true
        "FALSE" 'false
        (symbol (.getLexeme cst)))
    1 (if (= (class cst) mpa.cst.parser.MpaNotExpr) ; NOT | hierarchic descent
        (list 'not (cst2list (.jjtGetChild cst 0)))
        (cst2list (.jjtGetChild cst 0)))
    2 (let [op (case (.getOp cst)
                 14 'and
                 15 'nand
                 16 'or
                 17 'nor
                 18 'cimpl
                 19 'ncimpl
                 20 'impl
                 21 'nimpl
                 22 'equiv
                 23 'xor)]
        (list op (cst2list (.jjtGetChild cst 0)) (cst2list (.jjtGetChild cst 1))))))

(defn parse
  [formula]
  (MpaParser/ReInit (StringReader. formula))
  (let [cst (MpaParser/Start)]
    (cst2list cst)))
