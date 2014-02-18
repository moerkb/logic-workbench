(ns gui.main
  (require [seesaw.core :refer :all]
           [seesaw.rsyntax :as syntax]
           [seesaw.dev :refer (show-options)]
           [logic.util :as logic])
  (import [java.io File]
          [org.fife.ui.rsyntaxtextarea TokenMakerFactory DefaultTokenMakerFactory]))

(native!)

; register own editor parser for highlighting
(let [tmf (TokenMakerFactory/getDefaultInstance)]
  (.putMapping tmf "text/mpa" "fully.qualified.MpaTokenMaker"))

; result handling
; this must be placed here, so it can be accessed from handler.clj
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

; LOADING OF OTHER FILES
(declare editor)

(load "tools")
(load "handler")
(load "menus")

; final window building
(def form-tree (scrollable (tree)
                           :preferred-size [255 :by 600]
                           :maximum-size [255 :by 32000]))

(def editor (syntax/text-area :syntax :mpa))
(def form-editor (scrollable editor
                             :preferred-size [690 :by 400]))

(def ver-panel (vertical-panel
                 :items [form-editor
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
