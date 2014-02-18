(ns gui.main)

(defn set-result! [result-widget]
  "Clears the result area and sets the new widget."
  (let [old (select results [:#res :*])]
    (apply remove! results old)
    (add! results (scrollable 
                    result-widget
                    :preferred-size [690 :by 200]))))

(def icon-folder "resources/icons/")

(defn icon-path
  "Takes a file name and gives the file object with the relative path to the correct icon folder, e.g.
  (icon-file \"save.gif\") does the same as (File. resources\\icons\\save.gif)."
  [file-name]
  (File. (str icon-folder file-name)))