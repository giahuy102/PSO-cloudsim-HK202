#!/bin/sh
echo "Compile program"
javac -cp .:../:../jars/* Simulation.java
echo "Execute program"
java -cp .:../:../jars/* PSOCloud.Simulation
