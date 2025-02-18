@echo off
REM Delete previous build files if the target folder exists.
if exist target rmdir /s /q target

REM Create the target directory
mkdir target

echo Compiling Java source...
javac -d target webserver/Webserver.java
if errorlevel 1 (
    echo Compilation failed.
    pause
    exit /b 1
)

echo Running Webserver...
java -cp target webserver.Webserver

pause
