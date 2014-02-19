(ns gui.main)

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
                        (action 
                          :icon (icon-path "task_sat.gif")
                          :handler handler-sat)
                        (action 
                          :icon (icon-path "task_tt.gif")
                          :handler handler-tt)
                        (action 
                          :icon (icon-path "task_cnf.gif")
                          :handler handler-cnf)
                        (action 
                          :icon (icon-path "task_tcnf.gif")
                          :handler handler-tseitin)
                        (action 
                          :name "Dimacs"
                          :handler handler-dimacs)
                        (button :icon (icon-path "task_m4.gif"))
                        :separator
                        (button :icon (icon-path "select_font.gif"))
                        (button :icon (icon-path "settings.gif"))
                        (combobox
                          :maximum-size [135 :by 32000]
                          :model ["DefaultSAT4J" "LightSAT4J" "NaiveDPLL"])]))

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
                            :icon (icon-path "task_sat.gif")
                            :handler handler-sat)
                          (action 
                            :name "Truth Table"
                            ;:key "ctrl T T"
                            :icon (icon-path "task_tt.gif")
                            :handler handler-tt)
                          (action 
                            :name "Transform to CNF"
                            ;:key "ctrl T C"
                            :icon (icon-path "task_cnf.gif")
                            :handler handler-cnf)
                          (action 
                            :name "Tseitin Transformation"
                            :icon (icon-path "task_tcnf.gif")
                            :handler handler-tseitin)
                          (action 
                            :name "Generate DIMACS"
                            :handler handler-dimacs)
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