#!/bin/bash
rm -rf target

mkdir -p target

echo "Compiling Java source..."
javac -d target webserver/Webserver.java
if [ $? -ne 0 ]; then
    echo "Compilation failed"
    exit 1
fi

echo "Running Webserver..."
java -cp target webserver.Webserver
