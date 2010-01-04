@echo off
    set INCANTER_HOME=.
    set CLASSES_DIR=%INCANTER_HOME%\classes
    set INCANTER_JAR=%INCANTER_HOME%\modules\incanter-app\target\incanter-app-1.0-master-SNAPSHOT.jar

    IF (%1)==() (
        java -cp %INCANTER_JAR% -Dclojure.compile.path=%CLASSES_DIR% -Dincanter.home=%INCANTER_HOME%  clojure.main
    ) ELSE (
        java -cp %INCANTER_JAR% -Dclojure.compile.path=%CLASSES_DIR% -Dincanter.home=%INCANTER_HOME% clojure.main %1 -- %*
    )


