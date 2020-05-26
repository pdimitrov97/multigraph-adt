# Multigraph ADT
This is an implementation of a multigraph ADT.

## Description
The aim of this exercise is to design and implement a multi-graph ADT and to use the multigraph to model the Boston Metro System in a program that is able to provide directions to passengers on how to get from one station to another.

A key requirement is that both the design and implementation decouple the multigraph ADT from the rest of the system.

The program implements Breadth-first search for providing the directions.

https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-170-laboratory-in-software-engineering-fall-2005/assignments/ps4.pdf

## How to use it
When started the program asks for the origin station to start the search. After enetering the origin station, the program asks for the destination station. If both stations are valid, a valid path between the stations will be displayed. Type <b>exit</b> at any inpyt stage to stop the program.

## Requirements:
- JDK 8
- Maven

## Build:
````
mvn clean install
````
