@echo off
setLocal EnableDelayedExpansion
set INCANTER_HOME=.
set CLASSES_DIR=%INCANTER_HOME%\classes

set CLASSPATH="
for /R ./lib %%a in (*.jar) do (
   set CLASSPATH=!CLASSPATH!;%%a
)
set CLASSPATH=!CLASSPATH!"
set CLASSPATH=%CLASSPATH%;src;test;config;data;.
echo CLASSPATH=%CLASSPATH%

@rem jline breaks inferior-lisp.
if not defined LABREPL_SWANK set JLINE=jline.ConsoleRunner


IF (%1)==() (
  java -Xmx1G -cp %CLASSPATH% %JLINE% -Dclojure.compile.path=%CLASSES_DIR% -Dincanter.home=%INCANTER_HOME%  clojure.main -i script/run.clj -r
) ELSE (
  java -Xmx1G -cp %CLASSPATH% %JLINE% -Dclojure.compile.path=%CLASSES_DIR% -Dincanter.home=%INCANTER_HOME% clojure.main %1 -- %*
)
