(ns gui.main
  (require [seesaw.core :refer :all]
           [seesaw.rsyntax :as syntax]
           [seesaw.dev :refer (show-options)]
           [seesaw.util :refer (to-mnemonic-keycode)])
  (import [java.io File]))

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

(def form-editor (scrollable (syntax/text-area
                               :syntax :clojure)
                             :preferred-size [690 :by 400]))

(def result-area (scrollable (table)
                             :preferred-size [690 :by 200]))

(def tool-bar (toolbar
                :floatable? false
                :orientation :horizontal
                :items [(button :text "Truth Table")
                        (button :text "SAT-Solve")]))

(def ver-panel (vertical-panel
                 :items [form-editor
                         result-area]))

(def hor-panel (horizontal-panel
                 :items [form-tree
                         ver-panel]))

(def main-panel (border-panel
                  :north tool-bar
                  :center hor-panel))

; menus
(def project-menu (menu 
                    :text "Project"
                    :items [(action :name "New Project")
                            (action :name "Open Project")
                            (separator)
                            (action :name "New Proposition")
                            (action :name "Rename Proposition")
                            (separator)
                            (action :name "New M4 File")
                            (action :name "Open M4 File")
                            (separator)
                            (action 
                              :name "Save" 
                              :key "ctrl S" 
                              :mnemonic "s"
                              :icon (icon-path "save.gif")
                              :handler (fn [_] (println "Saving")))
                            (action 
                              :name "Save All"
                              :key "ctrl shift S"
                              :mnemonic "a"
                              :icon (icon-path "saveall.gif")
                              :handler (fn [_] (println "Saving All"))
                              )
                            (action :name "Close")
                            (action :name "Delete")
                            (separator)
                            (action :name "Exit")]))

(def tasks-menu (menu
                  :text "Tasks"
                  :items [(action :name "Check SAT")
                          (action :name "Truth Table")
                          (action :name "Transform to CNF")
                          (action :name "Tseitin Transformation")
                          (action :name "Generate DIMACS")
                          (action :name "Preprocess with M4")
                  ]))

(def options-menu (menu
                    :text "Options"
                    :items [(action :name "Select Font")
                            (action :name "Settings")
                            (action :name "Include Path")]))

(def help-menu (menu
                 :text "Help"
                 :items [(action :name "Help Contents")
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
