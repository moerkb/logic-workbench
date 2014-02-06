(ns gui.main
  (require [seesaw.core :refer :all]
           [seesaw.rsyntax :as syntax]
           [seesaw.dev :refer (show-options)]))

; general stuff
(native!)

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
                :items [(button :text "Truth Table")]))

(def ver-panel (vertical-panel
                 :items [form-editor
                         result-area]))

(def hor-panel (horizontal-panel
                 :items [form-tree
                         ver-panel]))

(def main-panel (vertical-panel
                  :items [tool-bar
                          hor-panel]))

(def main-frame (frame 
                  :title "Logical Workbench"
                  :on-close :exit
                  :size [980 :by 600]
                  :content main-panel
                  :menubar (menubar 
                             :items [(menu :text "Project")
                                     (menu :text "Tasks")
                                     (menu :text "Settings")
                                     (menu :text "Help")])))

(.setAlignmentX tool-bar (java.awt.Component/LEFT_ALIGNMENT))

; we're rolling
(-> main-frame pack! show!)
