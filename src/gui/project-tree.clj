(ns gui.main)

(def test-tree
  (Node. "Projects" (list
                      (Node. "Project_1" (list
                                              (Node. "Formel_1" "a&b")
                                              (Node. "Formel_2" "a->b")))
                      (Node. "Project_2" (list
                                              (Node. "Formel_3" "a<->b")
                                              (Node. "Formel_4" "a!|b"))))))

(def tree-model
  (let [children #(.getChildren %)]
    (simple-tree-model
		  children
		  children
		  test-tree)))
