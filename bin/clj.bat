@echo off
    set INCANTER_HOME=.
    set CLOJURE_JAR=%INCANTER_HOME%\clojure-1.0-SNAPSHOT.jar
    set INCANTER_LIB_DIR=%INCANTER_HOME%\lib
    set INCANTER_SRC=%INCANTER_HOME%\src\main\clojure
    set CLASSES_DIR=%INCANTER_HOME%\classes
    set INCANTER_JAR=%INCANTER_HOME%\target\incanter-1.0-SNAPSHOT.jar

    set CLOJURE_JARS=%INCANTER_LIB_DIR%\clojure.jar;%INCANTER_LIB_DIR%\clojure-contrib.jar

    set JLINE_JARS=%INCANTER_LIB_DIR%\jline-0.9.94.jar
    set COLT_JARS=%INCANTER_LIB_DIR%\parallelcolt-0.7.2.jar;%INCANTER_LIB_DIR%\netlib-java-0.9.1.jar;%INCANTER_LIB_DIR%\arpack-combo-0.1.jar
    set JFREECHART_JARS=%INCANTER_LIB_DIR%\jfreechart-1.0.13.jar;%INCANTER_LIB_DIR%\jcommon-1.0.16.jar;%INCANTER_LIB_DIR%\gnujaxp.jar
    set OPENCSV_JARS=%INCANTER_LIB_DIR%\opencsv-1.8.jar
    set PROCESSING_JARS=%INCANTER_LIB_DIR%\processing\core.jar;%INCANTER_LIB_DIR%\processing
 
    set JODA_TIME_JARS=%INCANTER_LIB_DIR%\joda-time-1.6.jar
    set CLJ_JSON_JARS=%INCANTER_LIB_DIR%\clojure-json-1.1-SNAPSHOT.jar

    set TESTS_DIR=%INCANTER_HOME%\src\test\clojure

    IF (%1)==() (
        java -cp .;..;%INCANTER_JAR%;%COLT_JARS%;%JLINE_JARS%;%CLOJURE_JARS%;%JFREECHART_JARS%;%OPENCSV_JARS%;%PROCESSING_JARS%;%JODA_TIME_JARS%;%CLJ_JSON_JARS%;%TESTS_DIR% -Dclojure.compile.path=%CLASSES_DIR% -Dincanter.home=%INCANTER_HOME%  clojure.main
    ) ELSE (
        java -cp .;..;%INCANTER_JAR%;%COLT_JARS%;%JLINE_JARS%;%CLOJURE_JARS%;%JFREECHART_JARS%;%OPENCSV_JARS%;%PROCESSING_JARS%;%JODA_TIME_JARS%;%CLJ_JSON_JARS%;%TESTS_DIR% -Dclojure.compile.path=%CLASSES_DIR% -Dincanter.home=%INCANTER_HOME% clojure.lang.Script %1 -- %*
    )


