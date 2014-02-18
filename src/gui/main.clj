(ns gui.main
  (require [seesaw.core :refer :all]
           [seesaw.rsyntax :as syntax]
           [seesaw.dev :refer (show-options)]
           [logic.util :as logic])
  (import [java.io File]
          [org.fife.ui.rsyntaxtextarea TokenMakerFactory DefaultTokenMakerFactory]))

; early declarations of important widgets for actions

; register own editor parser for highlighting
(let [tmf (TokenMakerFactory/getDefaultInstance)]
  (.putMapping tmf "text/mpa" "fully.qualified.MpaTokenMaker"))

; this must be placed here, so it can be accessed from handler.clj
(def editor (syntax/text-area :syntax :mpa))
(def form-editor (scrollable editor
                             :preferred-size [690 :by 400]))

(def results-start (scrollable 
                     (text 
                       :text "Welcome to the Logic Workbench"
                       :multi-line? true
                       :editable? false)
                     :preferred-size [690 :by 200]))

(def results (flow-panel
               :id :res
               :align :left
               :items [results-start]))

(def ver-panel (vertical-panel
                 :items [form-editor
                         results]))

(defn set-result! [result-widget]
  "Clears the result area and sets the new widget."
  (let [old (select results [:#res :*])]
    (apply remove! results old)
    (add! results (scrollable 
                    result-widget
                    :preferred-size [690 :by 200]))))

(load "handler")

; general stuff
(native!)

(def icon-folder "resources/icons/")

(defn icon-path
  "Takes a file name and gives the file object with the relative path to the correct icon folder, e.g.
  (icon-file \"save.gif\") does the same as (File. resources\\icons\\save.gif)."
  [file-name]
  (File. (str icon-folder file-name)))

; main window building
(def form-tree (scrollable (tree)
                           :preferred-size [255 :by 600]
                           :maximum-size [255 :by 32000]))

(def tool-bar (toolbar
                :floatable? false
                :orientation :horizontal
                :items [(button :icon (icon-path "new_project.gif"))
                        (button :icon (icon-path "open_project.gif"))
                        :separator
                        (button :icon (icon-path "new_proposition.gif"))
                        (button :icon (icon-path "rename_proposition.gif"))
                        :separator
                        (button :icon (icon-path "new_m4file.gif"))
                        (button :icon (icon-path "open_m4file.gif"))
                        :separator
                        (button :icon (icon-path "save.gif"))
                        (button :icon (icon-path "save_all.gif"))
                        (button :icon (icon-path "close.gif"))
                        (button :icon (icon-path "delete.gif"))
                        :separator
                        (button :icon (icon-path "task_sat.gif"))
                        (action 
                          :icon (icon-path "task_tt.gif")
                          :handler #(handler-tt %))
                        (button :icon (icon-path "task_cnf.gif"))
                        (button :icon (icon-path "task_tcnf.gif"))
                        (button :text "Dimacs")
                        (button :icon (icon-path "task_m4.gif"))
                        :separator
                        (button :icon (icon-path "select_font.gif"))
                        (button :icon (icon-path "settings.gif"))
                        (combobox
                          :maximum-size [135 :by 32000]
                          :model ["DefaultSAT4J" "LightSAT4J" "NaiveDPLL"])]))

(def hor-panel (horizontal-panel
                 :items [form-tree
                         ver-panel]))

(def main-panel (border-panel
                  :north tool-bar
                  :center hor-panel))

; menus
(def project-menu (menu 
                    :text "Project"
                    :mnemonic "P"
                    :items [(action 
                              :name "New Project"
                              :key "ctrl shift N"
                              :icon (icon-path "new_project.gif"))
                            (action 
                              :name "Open Project"
                              :key "ctrl shift O"
                              :icon (icon-path "open_project.gif"))
                            (separator)
                            (action 
                              :name "New Proposition"
                              :key "ctrl N"
                              :icon (icon-path "new_proposition.gif"))
                            (action 
                              :name "Rename Proposition"
                              :key "ctrl R"
                              :icon (icon-path "rename_proposition.gif"))
                            (separator)
                            (action 
                              :name "New M4 File"
                              :key "alt N"
                              :icon (icon-path "new_m4file.gif"))
                            (action 
                              :name "Open M4 File"
                              :key "alt O"
                              :icon (icon-path "open_m4file.gif"))
                            (separator)
                            (action 
                              :name "Save" 
                              :key "ctrl S" 
                              :mnemonic "S"
                              :icon (icon-path "save.gif")
                              :handler (fn [_] (println "Saving"))) ;TODO
                            (action 
                              :name "Save All"
                              :key "ctrl shift S"
                              :mnemonic "A"
                              :icon (icon-path "save_all.gif")
                              :handler (fn [_] (println "Saving All")) ;TODO
                              )
                            (action 
                              :name "Close"
                              :icon (icon-path "close.gif"))
                            (action 
                              :name "Delete"
                              :icon (icon-path "delete.gif"))
                            (separator)
                            (action 
                              :name "Exit"
                              :key "ctrl Q"
                              :icon (icon-path "exit.gif"))]))

(def tasks-menu (menu
                  :text "Tasks"
                  :mnemonic "T"
                  :items [(action 
                            :name "Check SAT"
                            ;:key "ctrl T+S"
                            :icon (icon-path "task_sat.gif"))
                          (action 
                            :name "Truth Table"
                            ;:key "ctrl T T"
                            :icon (icon-path "task_tt.gif"))
                          (action 
                            :name "Transform to CNF"
                            ;:key "ctrl T C"
                            :icon (icon-path "task_cnf.gif"))
                          (action 
                            :name "Tseitin Transformation"
                            :icon (icon-path "task_tcnf.gif"))
                          (action 
                            :name "Generate DIMACS")
                          (action 
                            :name "Preprocess with M4"
                            ;:key "ctrl T M"
                            :icon (icon-path "task_m4.gif"))
                  ]))

(def options-menu (menu
                    :text "Options"
                    :mnemonic "O"
                    :items [(action 
                              :name "Select Font"
                              :icon (icon-path "select_font.gif"))
                            (action 
                              :name "Settings"
                              :key "alt S"
                              :icon (icon-path "settings.gif"))
                            (action :name "Include Path")]))

(def help-menu (menu
                 :text "Help"
                 :mnemonic "H"
                 :items [(action 
                           :name "Help Contents"
                           :mnemonic "H"
                           :key "F1")
                         (separator)
                         (action :name "About")]))

; we're rolling
(def main-frame (frame 
                  :title "Logical Workbench"
                  :on-close :exit
                  :size [980 :by 600]
                  :content main-panel
                  :menubar (menubar 
                             :items [project-menu
                                     tasks-menu
                                     options-menu
                                     help-menu])))

(-> main-frame pack! show!)
