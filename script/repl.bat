@echo off
setLocal EnableDelayedExpansion
set CLASSPATH=src;test;data;.:incanter.jar
echo CLASSPATH=%CLASSPATH%

java -Xmx1G -cp %CLASSPATH% incanter.repl -i script/run.clj
