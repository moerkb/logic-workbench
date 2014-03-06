(ns tools.main)

(defn needs-mmp?
  "Takes a proposition as a string and returns either true or false depending
   on if it needs to be preprocessed by the MMP."
  [formula]
  (if (< (count formula) 13)
    false
    (= "dnl needs mmp"
      (subs (str/trim formula) 0 13))))

(defn invoke-mmp
  "Takes a proposition as a string and if needed, runs the MMP and returns its
   output. Returns the original string otherwise. The second argument is a 
   collection with arbitrary number of paths as strings."
  [formula include-paths]
  (if (needs-mmp? formula)
    (let [result (StringWriter.)
          engine (Engine. (StringReader. formula) result)]
      (doseq [path include-paths]
        (.. engine (getSettings) (addToSearchPath path)))
      (.run engine)
      (str "/* Result of preprocessing by mmp: */"
        \newline
        (.toString result)))
    formula))