(ns gui.main
  (:gen-class)
  (require [seesaw.core :refer :all]
           [seesaw.rsyntax :as syntax]
           [seesaw.tree :refer (simple-tree-model node-removed node-inserted node-changed node-structure-changed)]
           [seesaw.dev :refer (show-options show-events)]
           [seesaw.chooser :refer (choose-file)]
           [seesaw.border :as border]
           [logic.util :as logic]
           [clojure.string :as str]
           [tools.main :as tools]
           [clojure.java.io :as io])
  (import [java.io File]
          [gui Node]
          [java.net URI]
          [java.awt Desktop]
          [org.fife.ui.rsyntaxtextarea TokenMakerFactory DefaultTokenMakerFactory]))  
 
(def _ (native!))

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
(declare save-project)

(load "tools")
(load "project-tree")
(load "handler")
(load "frames")
(load "menus")

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

(def editor-tabs (tabbed-panel))

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
                  :icon (icon-path "lwb-logo.gif")
                  :size [980 :by 600]
                  :content main-panel
                  :menubar (menubar 
                             :items [project-menu
                                     tasks-menu
                                     options-menu
                                     help-menu])))

(defn -main [& args]
  (listen editor
    :document tab-mark-new-listener)
  (-> main-frame pack! show!))