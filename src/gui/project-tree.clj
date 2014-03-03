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

(defn- get-project-list
  [f]
  (let [file2node (fn [f] (let [data (read-string (slurp f))]
                            (Node. (apply str (drop-last 4 (last (str/split f #"/"))))
                                   (:__description data)
                                   (apply list (map
                                                 #(Node.
                                                    (apply str (rest (str (first %))))
                                                    (second %))
                                                 (dissoc data :__description))))))]
    (apply list (map file2node f))))

(defn- projects
  [file-vector]
  (Node. "Projects"
         nil
         (if (first file-vector)
           (get-project-list file-vector)
           nil)))

(def tree-model
  (let [children #(.getChildren %)]
    (simple-tree-model
		  children
		  children
		  (projects ["examples/test.lwf"]))))
