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

(defn- projects
  [file-vector]
  (let [get-data (fn [f] (let [file-type (last (str/split f #"\."))
                               data (slurp f)]
                           (case file-type
                             "lwf" (read-string data)
                             "mpf" (read-string (tools/mpf2lwf data)))))
                             
        file2node (fn [f] (let [data (get-data f)]
                            (Node. (apply str (drop-last 4 (last (str/split f #"/"))))
                                   (:__description data)
                                   (apply list (map
                                                 #(Node.
                                                    (apply str (rest (str (first %))))
                                                    (second %))
                                                 (dissoc data :__description))))))
        
        get-project-list (fn [f] (apply list (map file2node f)))]
    
    (Node. "Projects"
           nil
         (if (first file-vector)
             (get-project-list file-vector)
             nil))))

(def tree-model
  (let [children #(.getChildren %)]
    (simple-tree-model
		  children
		  children
		  (projects ["examples/test.lwf" "examples/Coloring.mpf"]))))
