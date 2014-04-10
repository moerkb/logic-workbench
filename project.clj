(defproject logic "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/math.combinatorics "0.0.4"]
                 [org.ow2.sat4j/org.ow2.sat4j.core "2.3.4"]
                 [seesaw "1.4.4"]
                 [org.blancas/kern "0.7.0"]
                 [instaparse "1.3.1"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot [gui.main]}}
  :java-source-paths ["src"]
  :manifest {"SplashScreen-Image" "help/img/LWB-splash.png"}
  :resource-paths ["resources"]
  :main ^:skip-aot gui.main)

