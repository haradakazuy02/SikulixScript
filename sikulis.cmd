@echo off
setlocal
java -cp %~dp0target\universal\stage\lib\*; jp.gr.java_conf.harada.SikulixScript %*
endlocal