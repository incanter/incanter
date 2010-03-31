@echo off
setLocal EnableDelayedExpansion
set CLASSPATH="
for /R ./lib %%a in (*.jar) do (
   set CLASSPATH=!CLASSPATH!;%%a
)
set CLASSPATH=!CLASSPATH!"
set CLASSPATH=%CLASSPATH%;src;test;config;data;.
echo CLASSPATH=%CLASSPATH%

@rem jline breaks inferior-lisp.
if not defined LABREPL_SWANK set JLINE=jline.ConsoleRunner

java -Xmx1G -cp %CLASSPATH% %JLINE% clojure.main -i script/run.clj -r
