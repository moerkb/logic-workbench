(defproject logic "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [instaparse "1.2.4"]
                 [org.clojure/math.combinatorics "0.0.4"]
                 [org.ow2.sat4j/org.ow2.sat4j.core "2.3.4"]
                 [org.blancas/kern "0.7.0"]
                 [seesaw "1.4.4"]]
  :target-path "target/%s"
  ;:profiles {:uberjar {:aot [mpa.cst.parser.parser logic.util main.gui]}}
  :java-source-paths ["src"]
  :manifest {"SplashScreen-Image" "help/img/splash.png"}
  :main gui.main
  :aot [mpa.cst.parser.parser logic.util gui.main])

