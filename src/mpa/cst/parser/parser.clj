(ns mpa.cst.parser.parser)

(import '(mpa.cst.parser MpaParser
                         MpaStart)
        '(java.io StringReader))

(MpaParser. (StringReader. ""))

(defn- cst2list
  [cst]
  (.jjtGetChild cst 0))

(defn parse
  [formula]
  (MpaParser/ReInit (StringReader. formula))
  (let [cst (MpaParser/Start)]
    (cst2list cst)))
