(ns gui.main
  (require [seesaw.core :refer :all]
           [seesaw.rsyntax :as syntax]
           [seesaw.dev :refer (show-options)]
           [seesaw.tree :refer (simple-tree-model)]
           [logic.util :as logic])
  (import [java.io File]
          [gui Node]
          [org.fife.ui.rsyntaxtextarea TokenMakerFactory DefaultTokenMakerFactory]))

(native!)

; global variables
(def ^:dynamic *node-tabs* (atom {}))

; register own editor parser for highlighting
;(let [tmf (TokenMakerFactory/getDefaultInstance)]
;  (.putMapping tmf "text/mpa" "fully.qualified.MpaTokenMaker"))
  
; result handling
; this must be placed here, so it can be accessed from handler.clj
(def results-start (scrollable 
                     (text 
                       :text "Welcome to the Logic Workbench\n\nFor help, please press F1."
                       :multi-line? true
                       :editable? false)
                     :preferred-size [690 :by 200]))

(def results (horizontal-panel
               :id :res
               :items [results-start]))

; LOADING OF OTHER FILES
(declare editor)
(declare project-tree)
(declare editor-tabs)

(load "tools")
(load "handler")
(load "menus")
(load "project-tree")

; final window building
(def project-tree (tree :model tree-model
                        :root-visible? false
                        :selection-mode :single
                        :shows-root-handles? true
                        :listen [:mouse-clicked handler-tree-clicked
                                 :key-released handler-tree-key]))

(def form-tree (scrollable project-tree
                           :preferred-size [255 :by 600]
                           :maximum-size [255 :by 32000]))

(def editor (syntax/text-area))
(def form-editor (scrollable editor
                             :preferred-size [690 :by 400]))

(def editor-tabs (tabbed-panel :tabs [{:title "New*" :content form-editor}]))

(def ver-panel (vertical-panel
                 :items [editor-tabs
                         results]))

(def hor-panel (horizontal-panel
                 :items [form-tree
                         ver-panel]))

(def main-panel (border-panel
                  :north tool-bar
                  :center hor-panel))

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

; we're rolling!
(-> main-frame pack! show!)
