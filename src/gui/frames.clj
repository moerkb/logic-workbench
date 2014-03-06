(ns gui.main)

(defn settings-frame!
  "Prints the settings frame and saves the set options."
  []
  (let [curr-settings (get-settings)

        ; settings for truth table
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

        ; settings for sat solving
        sat-curr-setting (:show-sat curr-settings)
        sat-radios (button-group)
        sat-radio-panel (vertical-panel
                          :border (border/compound-border "SAT solutions show...")
                          :items [(radio :id :all
                                         :text "... all valuations."
                                         :selected? (= :all sat-curr-setting)
                                         :group sat-radios)
                                  (radio :id :true-only
                                         :text "... satisfying valuations only."
                                         :selected? (= :true-only sat-curr-setting)
                                         :group sat-radios)
                                  (radio :id :formula
                                         :text "... as MPA formula."
                                         :selected? (= :formula sat-curr-setting)
                                         :group sat-radios)])
        
        ; putting it together
        save-button (button :text "Save")
        settings-dialog (custom-dialog
                          :title "Settings"
                          :modal? true
                          :id :settings-dialog
                          :content (border-panel
                                     :border 20
                                     :center (vertical-panel :items [tt-radio-panel
                                                                     sat-radio-panel])
                                     :south save-button))]
    (listen save-button
            :mouse-clicked (fn [_] 
                             (let [new-tt-setting (config (selection tt-radios) :id)
                                   new-sat-setting (config (selection sat-radios) :id)]
                               (set-settings {:show-tt new-tt-setting,
                                              :show-sat new-sat-setting})
                               (dispose! settings-dialog))))
    (-> settings-dialog pack! show!)))