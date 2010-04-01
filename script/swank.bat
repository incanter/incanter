@echo off
setlocal
set LABREPL_SWANK="(require 'swank.swank) (swank.swank/start-repl 4005)"
call %~dp0repl.bat
