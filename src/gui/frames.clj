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
        ; settings for MMP include path
        mmp-curr-setting (:mmp-include-path curr-settings)
        mmp-text (text :text mmp-curr-setting)
        mmp-panel (vertical-panel
                    :border (border/compound-border "Include path for MMP")
                    :items [(label "Path to the directoreis, in which should be searched")
                            (label "for .mml files. Separate by semicolon.")
                            mmp-text])
        
        ; putting it together
        save-button (button :text "Save")
        settings-dialog (custom-dialog
                          :title "Settings"
                          :modal? true
                          :id :settings-dialog
                          :content (border-panel
                                     :border 20
                                     :center (vertical-panel :items [tt-radio-panel
                                                                     sat-radio-panel
                                                                     mmp-panel])
                                     :south save-button))]
    (listen save-button
            :mouse-clicked (fn [_] 
                             (let [new-tt-setting (config (selection tt-radios) :id)
                                   new-sat-setting (config (selection sat-radios) :id)
                                   new-mmp-setting (.getText mmp-text)]
                               (set-settings {:show-tt new-tt-setting,
                                              :show-sat new-sat-setting
                                              :mmp-include-path new-mmp-setting})
                               (dispose! settings-dialog))))
    (-> settings-dialog pack! show!)))

(defn about-frame!
  "Dislays Help->About."
  []
  (let [info-frame (dialog :type :plain
                     :content (label :text
"<html><strong>Logic Workbench</strong><br />
<br />
Written 2013/2014<br />
Project home: github.com/moerkb/logic-workbench<br />
<br />
<strong>Authors</strong><br />
Prof. Dr. B. Renz (homepages.thm.de/~hg11260)<br />
Daniel Kirsten (daniel.kirsten@mni.thm.de)<br />
Markus Bader (markus.bader@mni.thm.de)<br />
<br />
Licensed under <strong>Eclipse Public License 1.0</strong><br />
(http://www.eclipse.org/legal/epl-v10.html)<br />
<br />
This program uses much code and many ideas of the<br />
<strong>MNI Propopsition Analyzer (MNI)</strong><br />
(homepages.thm.de/~hg11260/mpa.html)<br />
<br />
Written in <strong>Clojure</strong> (clojure.org)<br />
<br />
<strong>Libraries</strong> used:<br />
Instaparse (github.com/Engelberg/instaparse)<br />
SAT4J (sat4j.org)<br />
Kern (github.com/blancas/kern)<br />
Seesaw (github.com/daveray/seesaw)
"
))]
    (-> info-frame pack! show!)))