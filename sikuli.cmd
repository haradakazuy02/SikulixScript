@echo off
if "%1"=="" goto help
java -cp %~dp0target\pack\lib\*; org.sikuli.script.Sikulix %*
goto end

:help
echo sikuli -r [dir] : Sikuliスクリプトを実行. 例 sikuli -r test.sikuli

:end
