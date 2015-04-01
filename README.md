# Mesosphere Coding Challenge

## Problem Statement

Design a elevator control system to manage upto 16 elevators and uses  optimal scheduling techniques to accommodate streams of pick up requests 



## Implementation Status

The Project is currently partially complete , most of the major pieces are there , very simple straightforward simulation can take place , but the algorithms are not complete are currently broken for most cases.

Most of the implementation is simple . ElevatorControlSystemImpl.java contains most of the critical logic , the design is also currently synchronous . A simple command line input terminal is also available which is mostly complete .

Critical things are missing are tests and an incomplete scheduling algorithm implementation which is mostly due a lot of experimentation during the allocated time.


### Scheduling Algorithm 

A Simple FCFS approach isn't sufficient for more complex simulations so an the attempt was made for a more dynamically optimizing solution , some small assumptions were made about the specified api but no major changes made. The key to the algorithm are two treeset data structures maintained for each elevator instance.
One contains the floor to be visited above the current floor of the elevator and the other set maintains the floors list under it . At each stage of simulation a cost estimation function is used which tries to accommodate any new pick up requests , the goal is that the request can be accommodated along the queued path of one of the elevators already scheduled , for this we estimate the cost to insert the request into each elevator queue , another reason for the tree set approach as opposed to a priority queue was to easily deal with duplicate requests in queue , the overall approach should provide for good runtime efficiency but is not complete , the big issue is modelling all the state changes between up , down and idle states of the elevator as new pick up requests are streaming in.



## Build Instructions


The build instructions are simple . This project requires jdk7

```

from the project direcotry
$:find -name "*.java" > sources.txt
$:mkdir build
$:javac @sources.txt -d ./build
$:cd build
$:java driver.MainDriver 3


```

One can use driver.MainDriver 3 to start the interactive simulator
java driver.TestDriver is also available which has some of the incomplete unit test cases.