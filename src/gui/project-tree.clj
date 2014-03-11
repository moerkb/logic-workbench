(ns gui.main)

; A hand made project tree example
;(def test-tree
;  (Node. "Projects"
;         nil
;         (list
;           (Node. "Project_1"
;                  "This is a test project."
;                  (list
;                    (Node. "Formel_1" "a & b")
;                    (Node. "Formel_2" "a -> b")))
;           (Node. "Project_2"
;                  ""
;                  (list
;                    (Node. "Formel_3" "a <-> b")
;                    (Node. "Formel_4" "a !| b"))))))

(defn file2node
  [f]
  (std-catch
	  (let [get-data (fn [f] (let [file-type (last (str/split f #"\."))
	                               data (slurp f)]
	                           (case file-type
	                             "lwf" (read-string data)
	                             "mpf" (read-string (tools/mpf2lwf data)))))
	        
	        data (get-data f)]
	    
	    (Node. (apply str (drop-last 4 (last (str/split f #"/"))))
	           (:__description data)
	           f
	           (apply list (map
	                         #(Node.
	                            (apply str (rest (str (first %))))
	                            (second %))
	                         (dissoc data :__description)))))
   (catch java.io.FileNotFoundException e
     (alert (str "Die Datei " f " konnte nicht gefunden werden!")))
   ))
  

(defn- projects
  [file-vector]
  (let [get-project-list (fn [f] (apply list (remove nil? (map file2node f))))]
    
    (Node. "Projects"
           nil
           nil
         (if (first file-vector)
             (get-project-list file-vector)
             nil))))

(def tree-of-projects
  (projects (:project-tree (get-settings))))

(def tree-model
  (let [children #(.children %)
        tree tree-of-projects]
    (set-setting
          :project-tree
          (vec (map #(.path %) (.children tree))))
    (simple-tree-model
		  children
		  children
		  tree)))

(defn change-project-list
  [new-project-list]
  (set! (.children tree-of-projects) new-project-list)
        (node-structure-changed tree-model (list tree-of-projects))
        (set-setting
          :project-tree
          (vec (map #(.path %) (.children tree-of-projects)))))

(defn file-is-open?
  [file]
  (let [open-files (set (map #(.path %) (.children tree-of-projects)))
        f (tools/path-conformer file)]
    (contains? open-files f)))
