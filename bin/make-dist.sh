#!/bin/sh

## build Incanter
mvn clean; mvn compile; mvn clojure:compile; mvn package

## create dist directory structure
mkdir dist
mkdir dist/incanter
mkdir dist/incanter/target
mkdir dist/incanter/bin

## copy jars to dist
cp target/incanter-1.0-SNAPSHOT.jar dist/incanter/target
cp target/incanter-1.0-SNAPSHOT-jar-with-dependencies.jar dist/incanter/target
## copy examples and example data to dist
cp -R data dist/incanter/data
cp -R examples dist/incanter/examples
## copy executable scripts to dist
cp bin/incanter dist/incanter/bin/incanter
cp bin/incanter dist/incanter/bin/clj
cp bin/incanter.bat dist/incanter/bin/incanter.bat
cp bin/incanter.bat dist/incanter/bin/clj.bat

## zip the distribution
cd dist
zip -r incanter.zip incanter


