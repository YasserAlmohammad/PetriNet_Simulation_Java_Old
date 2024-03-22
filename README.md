# PetriNet_Simulation_Java_Old
Simulation of Petri Nets using Java (XML, Concurrency, Sockets)

Three Apps, to simulate the petrinet execution where first program reads the XML files and builds initial objects, the second program reads these objects and builds the Petri net tree, the third programs reads the petrinet tree objects from the second program and executes it.
The first and second programs have server socket while the third program only contains a client socket. Each program does a different job on its own.
We used a semaphore in the place which controls adding and removal of tokens and allows only one token to be accessed at any given time by the transitions which in turn uses the scheduling algorithm defined in the policy of the transition.
 Output of each program was shown in [Complete output and description]()

 ![image](/images/1.png)
![image](/images/2.png)
![image](/images/3.png)

