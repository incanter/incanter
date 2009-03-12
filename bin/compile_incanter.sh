#!/bin/sh

INCANTER_DIR=/Users/dliebke/Desktop/dev/incanter
INCANTER_LIB_DIR=$INCANTER_DIR/lib
INCANTER_SRC=$INCANTER_DIR/src
CLASSES_DIR=$INCANTER_DIR/classes
CLOJURE_JARS=$INCANTER_LIB_DIR/clojure.jar
COLT_JARS=$INCANTER_LIB_DIR/colt.jar

javac -cp .:$COLT_JARS:$CLOJURE_JARS -d $CLASSES_DIR -verbose $INCANTER_SRC/incanter/Matrix.java

