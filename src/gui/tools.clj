(ns gui.main)

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
  (logic/parse (.getText (current-editor))))

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
    (config! editor-tabs :tabs [{:title title :content new-editor}])
    (swap! *node-tabs* #(assoc % node (dec (tab-count))))
    (selection! editor-tabs (dec (tab-count)))))

(defn tab-marked-new?
  "Checks if the last character of the current activated editor tab's name is an asterisk."
  []
  (let [tab-name (:title (selection editor-tabs))
        last-char (get tab-name (dec (count tab-name)))]
    (= last-char \*)))