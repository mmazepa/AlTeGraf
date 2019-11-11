#!/bin/bash

DIRECTORY=class

if [ ! -d "$DIRECTORY" ]; then
  mkdir $DIRECTORY
else
  rm class/*.class
fi

javac -d class *.java
