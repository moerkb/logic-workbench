(ns tools.main
  (require [clojure.string :as str]
           [clojure.pprint :as pretty])
  (import [mmp.engine Engine]
          [java.io StringReader StringWriter]))

(load "file-transformation")
(load "mmp")

(defn path-conformer
  [p]
  (str/replace p "\\" "/"))
