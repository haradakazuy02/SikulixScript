@echo off
if "%1"=="" goto help
java -cp %~dp0target\universal\stage\lib\*; org.sikuli.script.Sikulix %*
goto end

:help
echo sikuli -r [dir] : Sikuli�X�N���v�g�����s. �� sikuli -r test.sikuli

:end
