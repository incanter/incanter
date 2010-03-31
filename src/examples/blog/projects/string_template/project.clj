(defproject myproject "1.0.0-SNAPSHOT" 
              :description "Example Incanter project" 
              :repositories {"incanter" "http://repo.incanter.org"}
              :dependencies [[org.incanter/incanter-full "1.0.0"]
              ;:dependencies [[org.incanter/incanter-full "1.2.0-SNAPSHOT"]
                             [antlr/stringtemplate "2.2"]]
              ;:dev-dependencies [[leiningen/lein-swank "1.1.0"]]
              :main hello)
