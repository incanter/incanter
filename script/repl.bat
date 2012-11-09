@echo off
setLocal EnableDelayedExpansion
set CLASSPATH=src;test;data;.:incanter.jar
echo CLASSPATH=%CLASSPATH%

@rem jline breaks inferior-lisp.
if not defined LABREPL_SWANK set JLINE=jline.ConsoleRunner

java -Xmx1G -cp %CLASSPATH% %JLINE% clojure.main -i script/run.clj -r
