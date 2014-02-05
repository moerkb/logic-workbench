(ns gui.main
  (require [seesaw.core :refer :all]))

; general stuff
(native!)

(defn display [content]
  (config! main-frame :content content)
  content)

; window building
(def main-frame (frame :title "Logical Workbench"))

(-> main-frame pack! show!)

(def lbl (label "Here comes the formula"))                            
(display lbl)        

(def b (button :text "Wahrheitstafel"))
(display b)

(listen b :action #(alert % "Tolle Sache!"))