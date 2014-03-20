(ns gui.main)

;; Menu Bar
(defn handler-close-selected-project
  [_]
  (let [node (selection project-tree)]
    (if (not= 2 (count node))
      (alert "Please select a project")
      (do
        (close-project-tabs (second node))
        (rm-node (selection project-tree))))))

(defn handler-delete-selected-project-proposition-m4file
  [_]
  (let [node (selection project-tree)
        c (count node)]
    (when
      (when (>= c 2)
        (-> (dialog
              :content "Are you sure you want to delete the project/proposition?"
              :option-type :ok-cancel
              :type :warning) pack! show!))
      (case c
        2 (do  ; case project node
            (close-project-tabs (second node))
            (rm-node node)
            (io/delete-file (.path (second node))))
          
        3 (do  ; case proposition node
            (remove-tab (@*node-tabs* node))
            (rm-node node)
            (save-project (second node)))
        nil))))
    

(defn handler-parse
  "Handler function for action 'parse to clojure formula'."
  [_]
  (std-catch
    (set-text-result! (str (apply list (parse-editor))))))
  
(defn handler-reparse
  "Reads a clojure formula form the editor and makes the formula of it."
  [_]
  (std-catch
    (set-text-result! (logic/clj-to-fml (read-string (.getText (current-editor)))))))

(defn handler-sat
  "Handler function for action 'sat solve'."
  [_]
  (std-catch
    (let [parsed (parse-editor)]
      (if (logic/literal? parsed)
        ; trivial cases
        (set-text-result! (str "SAT solver not invoked, as the result is always trivially "
                            parsed
                            "."))
        
        ; standard case: apply tseitin and sat-solver
        (let [tmap (logic/transform-tseitin parsed)
              overall-result (-> tmap :tseitin-formula logic/generate-dimacs-clauses logic/sat-solve)
              res? (if (zero? (first overall-result)) false true)
              sat-result (rest overall-result)
              result (logic/retransform-tseitin sat-result tmap)
              all-rows (map #(if (coll? %)
                               [(second %) false]
                               [% true]) result)
              curr-settings (get-settings)
              rows (if (= :true-only (:show-sat curr-settings))
                     (filter second all-rows)
                     all-rows)]
          (if res?
            (if (empty? rows)
              (set-text-result! "NOTE: The proposition is satisfiable, but all variables are false. Please change your settings to see all valuations.")
            
              (if (= :formula (:show-sat curr-settings))
                (set-text-result! (apply str (interpose "&" (map #(if (second %)
                                                                    (str (first %))
                                                                    (str "!" (first %)))
                                                              rows))))
                (set-table-result! [:columns [{:key :symbol :text "Variable"} 
                                              {:key :value :text "Value"}]
                                    :rows rows])))
            (set-table-result! [:columns [{:key :message :text "Message"}]
                                  :rows [["The proposition is unsatisfiable."]]]
                :auto-resize :last-column)))))))

(defn handler-tt
  "Handler function for action 'truth table'."
  [_]
  (std-catch
    (let [tt (logic/generate-truth-table
               (parse-editor)
               :lines (:show-tt (get-settings)))
          vars (butlast (:symbols tt))
          var-keys (conj 
                     (vec (map 
                            (fn [key text]
                              {:key key :text (str text)}) 
                            (map keyword vars) 
                            vars))
                     {:key :result :text "\u03D5"})]
    
      (set-table-result! [:columns var-keys
                          :rows (:table tt)]))
    (catch IllegalArgumentException e (alert (.getMessage e)))))

(defn handler-cnf
  "Handler function for action 'cnf'."
  [_]
  (std-catch
    (let [cnf (logic/clj-to-fml (logic/transform-cnf (parse-editor)))]
      (set-text-result! cnf))))

(defn handler-tseitin
 "Handler function for action 'tseitin cnf'."
 [_]
 (std-catch
   (let [tcnf (logic/clj-to-fml (:tseitin-formula (logic/transform-tseitin (parse-editor))))]
     (set-text-result! tcnf))))

(defn handler-dimacs
  "Handler function for action 'show dimacs'."
  [_]
  (std-catch
    (let [dimacs (logic/generate-dimacs (logic/flatten-ast (parse-editor)))]
      (set-text-result! dimacs))))
  

(defn handler-dimacs-cnf
  "Handler function for action 'make cnf, then show dimacs'."
  [_]
  (std-catch
    (let [dimacs (logic/generate-dimacs (logic/transform-cnf (parse-editor)))]
      (set-text-result! dimacs))))

(defn handler-dimacs-tseitin
  "Handler function for action 'make tseitin-cnf, then show dimacs'."
  [_]
  (std-catch
    (let [tseit (logic/transform-tseitin (parse-editor))
          dimacs (logic/generate-dimacs (:tseitin-formula tseit))]
      (set-text-result! (logic/dimacs-sub-vars dimacs (:lits tseit))))))

(defn handler-m4
  "Handler function for action 'preprocess with MMP'."
  [_]
  (std-catch
    (let [m4 (tools/invoke-mmp 
               (.getText (current-editor))
               (get-include-paths))]
      (set-text-result! m4))))

(defn handler-open-file
  [_]
  (std-catch 
	  (let [file (choose-file
	               :filters [["MPA (*.mpf)"
	                          ["mpf"]
	                          ["Folders" #(.isDirectory %)]]]
	               :success-fn (fn [fc file] (.getAbsolutePath file)))]
	    (if (file-is-open? file)
	      nil
	      (add-node (list tree-of-projects) (file2node (tools/path-conformer file)))))
  (catch Exception e nil)))

(defn handler-create-new-project
  [_]
  (std-catch
	  (let [f (choose-file
	            :type :save
	            :filters [["MPA/LWB (*.mpf)"
	                       ["mpf"]
	                       ["Folders" #(.isDirectory %)]]])
	        dir (tools/path-conformer(.getParent f))
	        name (.getName f)
	        file (if (and
	                (= (last (str/split name #"\.")) "mpf")
	                (< 1 (count (str/split name #"\."))))
	            name
	            (str name ".mpf"))
	        new-node (Node.
	                   (str/replace file #"\.mpf" "")
	                   ""
	                   (str dir "/" file)
	                   nil)]
	    (if (file-is-open? (str dir "/" file))
	      nil
        (if (std-catch (slurp (str dir "/" file)) (catch Exception e nil))
          (alert "This file already exists.")
          (do
            (add-node (list tree-of-projects) new-node)
            (save-project new-node)))))
   (catch Exception e nil)))

(defn handler-add-new-proposition
  [_]
  (let [node (second (selection project-tree))
        name (-> (dialog :content
                           (vertical-panel :items ["Enter the proposition name" (text :id :name)])
                           :option-type :ok-cancel
                           :type :question
                           :success-fn (fn [p] (text (select (to-root p) [:#name])))) pack! show!)]
    (if (not node)
      (alert "Please select a project.")
      (when name
        (if (= name "")
          (alert "Empty names are not allowed.")
          (do
            (add-node (list tree-of-projects node) (Node. name ""))
            (save-project node)))))))

(defn handler-rename-proposition
  [_]
  (if (not= 3 (count (selection project-tree)))
    (alert "Please select a Proposition.")
    (let [node (selection project-tree)
          name (-> (dialog :content
                           (vertical-panel :items ["Enter the proposition name" 
                                                   (text :id :name
                                                     :multi-line? false)])
                           :option-type :ok-cancel
                           :type :question
                           :success-fn (fn [p] (text (select (to-root p) [:#name])))) pack! show!)]
      (when name
	      (if (= name "")
	          (alert "Empty names are not allowed.")
	          (do
	            (change-node-name node name)
              (when (contains? @*node-tabs* node)
	              (let [index (@*node-tabs* node)
	                    title (.getTitleAt editor-tabs index)
	                    new-title (if (= (last title) \*)
	                                (str name "*")
	                                name)]
	                (.setTitleAt editor-tabs index new-title)))
	            (save-project (second node))))))))

(defn handler-move-up
  [_]
  (move-node-up (selection project-tree)))

(defn handler-move-down
  [_]
  (move-node-down (selection project-tree)))
  
;; Project Tree
(defn- handler-tree
  [e]
  (let [s (selection e)
        ed-tabs @*node-tabs*]
    (when (>= (count s) 2)
      (if (contains? ed-tabs s)
        (selection! editor-tabs (ed-tabs s))
        (add-editor (last s) (.content (last s)) s)))))
  
(defn handler-tree-clicked
  [e]
  (when (= (.getClickCount e) 2) ; double click on formula
    (handler-tree e)))

(defn handler-tree-key
  [e]
  (when (= (.getKeyCode e) 10)
    (handler-tree e)))

;; On Close
(defn handler-close-window
  "Safe all relevant informations for the next app start."
  [_]
  (println project-tree)) ; TODO

(defn handler-save
  "Saves the content of the currently active tab in the node."
  [_]
  (when (tab-marked-new?)
    (let [formula (.getText (current-editor))
          node-list (get (zipmap (vals @*node-tabs*) (keys @*node-tabs*)) (:index (selection editor-tabs)))
          node (last node-list)]
      (if (nil? node)
        (alert "No node.")
        (do
          (change-node-content node-list formula)
          (tab-demark-new))))))

(defn handler-close-tab
  "Handler function for closing a tab."
  [_]
  (if (not (zero? (tab-count)))
    (let [tab-index (:index (selection editor-tabs))]
      (if (tab-marked-new?)
        (let [q-diag (dialog 
                       :type :question
                       :option-type :yes-no-cancel
                       :content "The tab you want to close has unsaved changes. Save now?"
                       :no-fn (fn [_] (remove-tab tab-index))
                       :success-fn (fn [_] (handler-save nil)))]
          (-> q-diag pack! show!))
        (remove-tab tab-index)))))

(defn handler-save-all
  "Saves all open projects."
  [_]
  (let [unsaved-tabs (map (fn [[_ index]] 
                            [(.getComponentAt editor-tabs index) index])
                       (filter 
                         (fn [[title _]]
                           (if (= (last title) \*)
                             true
                             false))
                         (for [i (range 0 (tab-count))]
                           [(.getTitleAt editor-tabs i) i])))]
    (when-not (empty? unsaved-tabs)
      (doseq [tab unsaved-tabs]
        (let [scroll-pane (first tab)
              index (second tab)
              title (.getTitleAt editor-tabs index)
              editor (first (select scroll-pane [:<org.fife.ui.rsyntaxtextarea.RSyntaxTextArea!>]))
              formula (.getText editor)
              node-list (get (zipmap (vals @*node-tabs*) (keys @*node-tabs*)) index)
              node (last node-list)]
          (if (nil? node)
        (alert "No node.")
        (do 
          (change-node-content node-list formula)
          (.setTitleAt editor-tabs index (apply str (butlast title))))))))))

;; Listener
(defn tab-mark-new-listener
 "Listener function that should be called when a text in an editor changes.
 Puts an asterisk in the end of the tab name, if not already done."
  [_]
  (when (not (tab-marked-new?))
    (let [title (:title (selection editor-tabs))
          index (:index (selection editor-tabs))]
      (.setTitleAt editor-tabs index (str title "*")))))
