(ns gui.main)

(defn settings-frame!
  "Prints the settings frame and saves the set options."
  []
  (let [curr-settings (get-settings)
        tt-curr-setting (:show-tt curr-settings)
        tt-radios (button-group)
        tt-radio-panel (vertical-panel 
                         :border (border/compound-border "Truth tables show...")
                         :items [(radio :id :all 
                                        :text "... all valuations."
                                        :selected? (= :all tt-curr-setting)
                                        :group tt-radios)
                                 (radio :id :true-only
                                        :text "... satisfying valuations only."
                                        :selected? (= :true-only tt-curr-setting)
                                                      :group tt-radios)
                                 (radio :id :false-only
                                        :text "... not satisfying valuations only."
                                        :selected? (= :false-only tt-curr-setting)
                                                      :group tt-radios)])
        save-button (button :text "Save")
        settings-dialog (custom-dialog
                          :title "Settings"
                          :modal? true
                          :id :settings-dialog
                          :content (border-panel
                                     :center tt-radio-panel
                                     :south save-button))]
    (listen save-button
            :mouse-clicked (fn [_] 
                             (let [new-tt-setting (config (selection tt-radios) :id)]
                               (set-setting :show-tt new-tt-setting)
                               (dispose! settings-dialog))))
    (-> settings-dialog pack! show!)))