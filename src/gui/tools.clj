(ns gui.main)

(defn set-result! [result-widget]
  "Clears the result area and sets the new widget."
  (let [old (select results [:#res :*])]
    (apply remove! results old)
    (add! results (scrollable 
                    result-widget
                    :preferred-size [690 :by 200]))))

(defn set-table-result! [table-model]
  "Takes a TableModel and sets it with a JTable in result area."
  (set-result! (table
                 :auto-resize :off
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