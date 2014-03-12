(ns gui.main)

(declare get-include-paths)

(defmacro std-catch
  "Puts the argument in a try block and catches all exceptions for a standard message.
  Additional catch clauses are applied in order before a final clause catches all of type Exception."
  [code & catches]
  `(try ~code
     ~@catches
     (catch Exception _# (alert "An error ocurred. Please check the entered formula."))))

(defn current-editor 
  "Returns the currently selected editor (due to tabs)."
  []
  (first (select (:content (selection editor-tabs)) [:<org.fife.ui.rsyntaxtextarea.RSyntaxTextArea!>])))

(defn parse-editor []
  "Parses the text of the editor and returns the clojure formula."
  (logic/parse (tools/invoke-mmp 
                 (.getText (current-editor))
                 (get-include-paths))))

(defn set-result! [result-widget]
  "Clears the result area and sets the new widget."
  (let [old (select results [:#res :*])]
    (apply remove! results old)
    (add! results (scrollable 
                    result-widget
                    :preferred-size [690 :by 200]))))

(defn set-table-result! [table-model & {:keys [auto-resize]
                                        :or {auto-resize :off}}]
  "Takes a TableModel and sets it with a JTable in result area."
  (set-result! (table
                 :auto-resize auto-resize
                 :model table-model)))

(defn set-text-result! [result-text]
  "Takes a string and displays it in the result area."
   (set-result! (text 
                  :multi-line? true
                  :editable? false
                  :wrap-lines? true
                  :text result-text)))

(def icon-folder "resources/icons/")

(defn icon-path
  "Takes a file name and gives the file object with the relative path to the correct icon folder, e.g.
  (icon-file \"save.gif\") does the same as (File. resources\\icons\\save.gif)."
  [file-name]
  (File. (str icon-folder file-name)))

(defn tab-count
  "Returns the number of tabs in the editor pane."
  []
  (.getTabCount editor-tabs))
  
(declare tab-mark-new-listener)
(defn add-editor
  "Creates a new editor widget and adds it to the tabs for the editors."
  [title content node]
  (let [new-editor (scrollable (syntax/text-area 
                                 :text content
                                 :listen [:document tab-mark-new-listener])
                               :preferred-size [690 :by 400])]
    (config! editor-tabs :tabs [{:title title
                                 :content new-editor}])
    (swap! *node-tabs* #(assoc % node (dec (tab-count))))
    (selection! editor-tabs (dec (tab-count)))))

(defn tab-demark-new
  "Removes the ending asterisk of a title of the currently active tab, if there is any."
  []
  (let [tab (selection editor-tabs)
        title (:title tab)]
    (when (= (last title) \*)
      (.setTitleAt editor-tabs (:index tab) (subs title 0 (dec (count title)))))))
        

(defn tab-marked-new?
  "Checks if the last character of the current activated editor tab's name is an asterisk."
  []
  (let [tab-name (:title (selection editor-tabs))
        last-char (get tab-name (dec (count tab-name)))]
    (= last-char \*)))

(defn get-settings
  "Reads the settings from the settings file and returns them as a map. For any error, i.e. no file
   exist or cannot be read properly, an exception will be thrown (see 'slurp' and 'read-string')."
  []
  (read-string (slurp "settings.clj")))

(defn set-settings
  "Takes a map and overwrites the complete settings with it. Don't forget anything!"
  [settings-map]
  (spit "settings.clj" settings-map))

(defn set-setting
  "Takes a key value pair and overwrites the current setting with it. Applies on the current settings file.
   As of now, no responsiblity for wrong arguments is taken."
  [key value]
  (spit "settings.clj" (assoc (get-settings) key value)))
  
(defn get-include-paths
  "Reads the MMP include paths from the settings and returns them as a collection."
  []
  (map str/trim (str/split (:mmp-include-path (get-settings)) #";")))
