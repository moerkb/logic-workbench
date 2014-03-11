(ns gui.main)

(def tool-bar (toolbar
                :floatable? false
                :orientation :horizontal
                :items [(action
                          :tip "Create a new project."
                          :icon (icon-path "new_project.gif"))
                        (action
                          :tip "Open an exisiting project."
                          :icon (icon-path "open_project.gif")
                          :handler handler-open-file)
                        :separator
                        (action 
                          :tip "Add a new proposition (formula) to the current project."
                          :icon (icon-path "new_proposition.gif"))
                        (action 
                          :tip "Rename the selected proposition (formula)."
                          :icon (icon-path "rename_proposition.gif"))
                        :separator
                        (action 
                          :tip "Create a new M4 file."
                          :icon (icon-path "new_m4file.gif"))
                        (action 
                          :tip "Open an existing M4 file."
                          :icon (icon-path "open_m4file.gif"))
                        :separator
                        (action 
                          :tip "Save"
                          :icon (icon-path "save.gif"))
                        (action 
                          :tip "Save all"
                          :icon (icon-path "save_all.gif"))
                        (action
                          :tip "Closes the selected project, proposition or M4 file."
                          :icon (icon-path "close.gif"))
                        (action 
                          :tip "Deletes the selected project, proposition or M4 file."
                          :icon (icon-path "delete.gif"))
                        :separator
                        (action
                          :tip "Transforms the current MPA proposition to clojure code."
                          :icon (icon-path "formula2clojure.gif")
                          :handler handler-parse)
                        (action
                          :tip "Transforms the current clojure code to a MPA proposition."
                          :icon (icon-path "clojure2formula.gif")
                          :handler handler-reparse)
                        (action 
                          :tip "SAT solves the proposition."
                          :icon (icon-path "task_sat.gif")
                          :handler handler-sat)
                        (action 
                          :tip "Prints a truth table for the proposition."
                          :icon (icon-path "task_tt.gif")
                          :handler handler-tt)
                        (action 
                          :tip "Generates the CNF (Conjunctive Normal Form) of the proposition."
                          :icon (icon-path "task_cnf.gif")
                          :handler handler-cnf)
                        (action 
                          :tip "Performs the tseitin transformation on the proposition."
                          :icon (icon-path "task_tcnf.gif")
                          :handler handler-tseitin)
                        (action 
                          :tip "Generates the dimacs file for the proposition (must be CNF)."
                          :icon (icon-path "dimacs.gif")
                          :handler handler-dimacs)
                        (action 
                          :tip "Generates the CNF, then prints the dimacs file."
                          :icon (icon-path "dimacsCNF.gif")
                          :handler handler-dimacs-cnf)
                        (action 
                          :tip "Performs the tseitin transformation, then prints the dimacs file."
                          :icon (icon-path "dimacsTseitin.gif")
                          :handler handler-dimacs-tseitin)
                        (action
                          :tip "Runs the M4 macro processor on the proposition."
                          :handler handler-m4
                          :icon (icon-path "task_m4.gif"))
                        :separator
                        ;(button :icon (icon-path "select_font.gif"))
                        (action 
                          :tip "Opens a window to change global settings."
                          :icon (icon-path "settings.gif")
                          :handler (fn [_] (settings-frame!)))
                        #_(combobox
                           :maximum-size [135 :by 32000]
                           :model ["DefaultSAT4J" "LightSAT4J" "NaiveDPLL"])]))

(def project-menu (menu 
                    :text "Project"
                    :mnemonic "P"
                    :items [(action 
                              :name "New Project"
                              :tip "Create a new project."
                              :key "ctrl shift N"
                              :icon (icon-path "new_project.gif"))
                            (action 
                              :name "Open Project"
                              :tip "Open an exisiting project."
                              :key "ctrl shift O"
                              :icon (icon-path "open_project.gif")
                              :handler handler-open-file)
                            (separator)
                            (action 
                              :name "New Proposition"
                              :tip "Add a new proposition (formula) to the current project."
                              :key "ctrl N"
                              :icon (icon-path "new_proposition.gif"))
                            (action 
                              :name "Rename Proposition"
                              :tip "Rename the selected proposition (formula)."
                              :key "ctrl R"
                              :icon (icon-path "rename_proposition.gif"))
                            (separator)
                            (action 
                              :name "New M4 File"
                              :tip "Create a new M4 file."
                              :key "alt N"
                              :icon (icon-path "new_m4file.gif"))
                            (action 
                              :name "Open M4 File"
                              :tip "Open an existing M4 file."
                              :key "alt O"
                              :icon (icon-path "open_m4file.gif"))
                            (separator)
                            (action 
                              :name "Save" 
                              :tip "Save"
                              :key "ctrl S" 
                              :mnemonic "S"
                              :icon (icon-path "save.gif")
                              :handler (fn [_] (println "Saving"))) ;TODO
                            (action 
                              :name "Save All"
                              :tip "Save all"
                              :key "ctrl shift S"
                              :mnemonic "A"
                              :icon (icon-path "save_all.gif")
                              :handler (fn [_] (println "Saving All")) ;TODO
                              )
                            (action 
                              :name "Close"
                              :tip "Closes the selected project, proposition or M4 file."
                              :icon (icon-path "close.gif"))
                            (action 
                              :name "Delete"
                              :tip "Deletes the selected project, proposition or M4 file."
                              :icon (icon-path "delete.gif"))
                            (separator)
                            (action 
                              :name "Exit"
                              :tip "Exit the whole application."
                              :key "ctrl Q"
                              :icon (icon-path "exit.gif"))]))

(def tasks-menu (menu
                  :text "Tasks"
                  :mnemonic "T"
                  :items [(action
                            :name "Parse formula to clojure"
                            :tip "Transforms the current MPA proposition to clojure code."
                            :key "ctrl P"
                            :icon (icon-path "formula2clojure.gif")
                            :handler handler-parse)
                          (action
                            :name "Parse clojure back to formula"
                            :tip "Transforms the current clojure code to a MPA proposition."
                            :icon (icon-path "clojure2formula.gif")
                            :handler handler-reparse)
                          (action 
                            :name "Check SAT"
                            :tip "SAT solves the proposition."
                            ;:key "ctrl T+S"
                            :icon (icon-path "task_sat.gif")
                            :handler handler-sat)
                          (action 
                            :name "Truth Table"
                            :tip "Prints a truth table for the proposition."
                            ;:key "ctrl T T"
                            :icon (icon-path "task_tt.gif")
                            :handler handler-tt)
                          (action 
                            :name "Transform to CNF"
                            :tip "Generates the CNF (Conjunctive Normal Form) of the proposition."
                            ;:key "ctrl T C"
                            :icon (icon-path "task_cnf.gif")
                            :handler handler-cnf)
                          (action 
                            :name "Tseitin Transformation"
                            :tip "Performs the tseitin transformation on the proposition."
                            :icon (icon-path "task_tcnf.gif")
                            :handler handler-tseitin)
                          (action 
                            :name "Generate DIMACS"
                            :tip "Generates the dimacs file for the proposition (must be CNF)."
                            :icon (icon-path "dimacs.gif")
                            :handler handler-dimacs)
                          (action 
                            :name "Make CNF, then generate DIMACS"
                            :tip "Generates the CNF, then prints the dimacs file."
                            :icon (icon-path "dimacsCNF.gif")
                            :handler handler-dimacs-cnf)
                          (action 
                            :name "Make Tseitin-CNF, then generate DIMACS"
                            :tip "Performs the tseitin transformation, then prints the dimacs file."
                            :icon (icon-path "dimacsTseitin.gif")
                            :handler handler-dimacs-tseitin)
                          (action 
                            :name "Preprocess with M4"
                            :tip "Runs the M4 macro processor on the proposition."
                            ;:key "ctrl T M"
                            :handler handler-m4
                            :icon (icon-path "task_m4.gif"))
                  ]))

(def options-menu (menu
                    :text "Options"
                    :mnemonic "O"
                    :items [#_(action 
                               :name "Select Font"
                               :icon (icon-path "select_font.gif"))
                            (action 
                              :name "Settings"
                              :tip "Opens a window to change global settings."
                              :key "alt S"
                              :icon (icon-path "settings.gif")
                              :handler (fn [_] (settings-frame!)))
                            #_(action :name "Include Path")]))

(def help-menu (menu
                 :text "Help"
                 :mnemonic "H"
                 :items [(action 
                           :name "Help Contents"
                           :tip "Opens the help files in the system standard web browser."
                           :mnemonic "H"
                           :handler (fn [_]
                                      (let [help-file (File. "resources/help/toc.html")]
                                        (.. Desktop getDesktop 
                                          (browse 
                                            (URI. (str "file://" (.getAbsolutePath help-file)))))))
                           :key "F1")
                         (separator)
                         (action 
                           :name "About"
                           :tip "Show general information on the project, authors, licences, etc."
                           :handler (fn [_] (about-frame!)))]))