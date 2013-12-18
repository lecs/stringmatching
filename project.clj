(defproject stringmatching "0.1.0-SNAPSHOT"
  n:description "A collection of string matching related algorithms implemented in Clojure"
  :url "https://github.com/lecs/stringmatching"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [expectations "1.4.52"]
                 [org.apache.commons/commons-lang3 "3.1"]
                 [org.clojure/tools.trace "0.7.6"]]
  :plugins [[lein-autoexpect "1.2.1"]])
