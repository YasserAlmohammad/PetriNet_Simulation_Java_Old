# PetriNet_Simulation_Java_Old
Simulation of Petri Nets using Java (XML, Concurrency, Sockets)

Three Apps, to simulate the petrinet execution where first program reads the XML files and builds initial objects, the second program reads these objects and builds the Petri net tree, the third programs reads the petrinet tree objects from the second program and executes it.
The first and second programs have server socket while the third program only contains a client socket. Each program does a different job on its own.
We used a semaphore in the place which controls adding and removal of tokens and allows only one token to be accessed at any given time by the transitions which in turn uses the scheduling algorithm defined in the policy of the transition.
 Output of each program was shown in [Complete output and description](https://github.com/YasserAlmohammad/PetriNet_Simulation_Java_Old/blob/main/Output%20samples%20and%20description.pdf)

(It is required to build a Petri net engine. The engine has to take into consideration the
concurrency control and token consumption.
To learn more about Petri net visit this link:
https://en.wikipedia.org/wiki/Petri_net
The transition picks the token based on the following:
1- FCFS
2- SJF3
3- SRTF
4- EDF
Every transition has 1 of the four algorithms and the policy of scheduling is defined in the
XML file
Example of an XML file that defines the Petrinet:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Petrinet>
<Token>
<ID>1</ID>
<Arrival> 5 </Arrival>
<Burst> 100 </Burst>
<Priority> 1 </Priority>
<Deadline> 200 </Deadline>
</Token>
<Token>
<ID>2</ID>
<Arrival> 15 </Arrival>
<Burst> 50 </Burst>
<Priority> 2 </Priority>
<Deadline> 150 </Deadline>
</Token>
<Token>
<ID>3</ID>
<Arrival> 12 </Arrival>
<Burst> 40 </Burst>
<Priority> 1 </Priority>
<Deadline> 70 </Deadline>
</Token>
<Token>
<ID>3</ID>
<Arrival> 12 </Arrival>
<Burst> 40 </Burst>
<Priority> 1 </Priority>
<Deadline> 70 </Deadline>
</Token>
<Token>
<ID>4</ID>
<Arrival> 13 </Arrival>
<Burst> 25 </Burst>
<Priority> 1 </Priority>
<Deadline> 70 </Deadline>4
</Token>
<Token>
<ID>5</ID>
<Arrival> 20 </Arrival>
<Burst> 25 </Burst>
<Priority> 2 </Priority>
<Deadline> 70 </Deadline>
</Token>
<Place>
<ID> 1 </ID>
<Name> P1 </Name>
</Place>
<Place>
<ID> 2 </ID>
<Name> P2 </Name>
</Place>
<Place>
<ID> 3 </ID>
<Name> P3 </Name>
</Place>
<Place>
<ID> 4 </ID>
<Name> P4 </Name>
</Place>
<Place>
<ID> 5 </ID>
<Name> P5 </Name>
</Place>
<Marking>
<PID>1</PID>
<TokenID>1</TokenID>
</Marking>
<Marking>
<PID>1</PID>
<TokenID>2</TokenID>
</Marking>
<Marking>
<PID>2</PID>
<TokenID>3</TokenID>
</Marking>
<Marking>
<PID>2</PID>5
<TokenID>4</TokenID>
</Marking>
<Marking>
<PID>2</PID>
<TokenID>5</TokenID>
</Marking>
<Transition>
<ID>1</ID>
<Name>T1</Name>
<Policy> FCFS </Policy>
</Transition>
<Transition>
<ID>2</ID>
<Name>T2</Name>
<Policy> SJF </Policy>
</Transition>
<Transition>
<ID>3</ID>
<Name>T3</Name>
<Policy> EDF </Policy>
</Transition>
<Topology>
<PID>1</PID>
<TID>1</TID>
<Direction>Input</Direction>
</Topology>
<Topology>
<PID>1</PID>
<TID>2</TID>
<Direction>Input</Direction>
</Topology>
<Topology>
<PID>3</PID>
<TID>1</TID>
<Direction>output</Direction>
</Topology>
<Topology>
<PID>4</PID>
<TID>2</TID>
<Direction>output</Direction>
</Topology>
<Topology>6
<PID>4</PID>
<TID>3</TID>
<Direction>input</Direction>
</Topology>
<Topology>
<PID>3</PID>
<TID>3</TID>
<Direction>input</Direction>
</Topology>
<Topology>
<PID>5</PID>
<TID>3</TID>
<Direction>output</Direction>
</Topology>
</Petrinet>
```
 ![image](/images/1.png)
![image](/images/2.png)
![image](/images/3.png)

