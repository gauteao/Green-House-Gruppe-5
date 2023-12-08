# Communication protocol

This document describes the protocol used for communication between the different nodes of the
distributed application.

## Terminology

* Sensor - a device which senses the environment and describes it with a value (an integer value in
  the context of this project). Examples: temperature sensor, humidity sensor.
* Actuator - a device which can influence the environment. Examples: a fan, a window opener/closer,
  door opener/closer, heater.
* Sensor and actuator node - a computer which has direct access to a set of sensors, a set of
  actuators and is connected to the Internet.
* Control-panel node - a device connected to the Internet which visualizes status of sensor and
  actuator nodes and sends control commands to them.
* Graphical User Interface (GUI) - A graphical interface where users of the system can interact with
  it.

## The underlying transport protocol

TODO - what transport-layer protocol do you use? TCP? UDP? What port number(s)? Why did you 
choose this transport layer protocol?

We use TCP as our transport-layer protocol. We use TCP because it is reliable and we don't have to manage potential packet loss.
Using TCP means that we don't have to implement our own reliability mechanisms. 
Using TCP also means that we don't have to worry about the order of the packets.
We use TCP for both the server and the clients.
We use port number we use for the server is ____ and ____ for the clients.




## The architecture

TODO - show the general architecture of your network. Which part is a server? Who are clients? 
Do you have one or several servers? Perhaps include a picture here. 

We have one server and several clients(nodes). The server connects to the nodes which consist of control panel and sensor/actuator nodes.
The server takes input from the nodes and updates the GUI accordingly.
The user can also interact with the GUI and the server will send the input to the nodes.
The clients are nodes which consist of control panel and sensor/actuator nodes.



## The flow of information and events

TODO - describe what each network node does and when. Some periodic events? Some reaction on 
incoming packets? Perhaps split into several subsections, where each subsection describes one 
node type (For example: one subsection for sensor/actuator nodes, one for control panel nodes).

The sensor/actuator nodes periodically(could be more specific) send data to the server. (what kind of data?)
The server receives the data and updates the GUI accordingly.
The server uses a bufferedReader to read the data from the nodes.
The server uses a printWriter to send data to the nodes.
The user can interact with the GUI and the server will send the input to the nodes. (i.e. the user can open a window/turn on a fan or heater)
The nodes will then react to the input and update the sensors/actuators accordingly.
Most nodes will only have a toggle function; on or off for the actuators.
The server will then receive the updated data and update the GUI accordingly.

The control panel is connected to the GUI directly while the server is connected to the nodes using TCP sockets.
The nodes are connected to the sensors/actuators directly.



## Connection and state

TODO - is your communication protocol connection-oriented or connection-less? Is it stateful or 
stateless? 

Our communication protocol is connection-oriented and stateful.
We use TCP which is connection-oriented and stateful.



## Types, constants

TODO - Do you have some specific value types you use in several messages? Then you can describe 
them here.

We mostly use strings because they are easy to read and write to the server.


## Message format

TODO - describe the general format of all messages. Then describe specific format for each 
message type in your protocol.

The general format of all messages is as follows:
* The first line is the ID of the node that sent the message.
* The second line is the type of the message.
* The third line is the content of the message.

### Error messages

TODO - describe the possible error messages that nodes can send in your system.

The possible error messages that nodes can send in our system are as follows:
* "ERROR: Invalid input" - if the user input is invalid
* "ERROR: Invalid ID" - if the ID of the node is invalid
* "ERROR: Invalid message type" - if the message type is invalid
* "ERROR: Invalid message content" - if the message content is invalid
* "ERROR: Failed to connect to server" - if the node failed to connect to the server
* "ERROR: Failed to send message" - if the server failed to send a message to the node
* "ERROR: Failed to receive message" - if the server failed to receive a message from the node
* "ERROR: Failed to read message" - if the server failed to read a message from the node

## An example scenario

TODO - describe a typical scenario. How would it look like from communication perspective? When 
are connections established? Which packets are sent? How do nodes react on the packets? An 
example scenario could be as follows:
1. A sensor node with ID=1 is started. It has a temperature sensor, two humidity sensors. It can
   also open a window.
2. A sensor node with ID=2 is started. It has a single temperature sensor and can control two fans
   and a heater.
3. A control panel node is started.
4. Another control panel node is started.
5. A sensor node with ID=3 is started. It has a two temperature sensors and no actuators.
6. After 5 seconds all three sensor/actuator nodes broadcast their sensor data.
7. The user of the first-control panel presses on the button "ON" for the first fan of
   sensor/actuator node with ID=2.
8. The user of the second control-panel node presses on the button "turn off all actuators".

1. A sensor node with ID=1 is started. Upon starting, it establishes a TCP connection to the server 
using the given port number. It then sends a message containing ID and the types of sensors and actuators it has.

2.  A sensor node with ID=2 is started. It behaves the same way as the first sensor node.

3. A control panel node is started. Upon starting, it establishes a TCP connection to the server 
using the given port number. The control panel node listens for incoming packets from the server.

4. Another control panel node is started. It behaves the same way as the first control panel node.

5. A sensor node with ID=3 is started. Upon starting, it establishes a TCP connection to the server
using the given port number. It then sends a message containing ID and the types of sensors and actuators it has.

6. After 5 seconds all three sensor/actuator nodes broadcast their sensor data. The data is sent as packets over the
established TCP connection to the server. The control panel nodes receive these packets, process the sensor data, and update the GUI accordingly.

7. The user of the first control panel interacts with the GUI and presses the "ON" button for the first fan of the sensor/actuator 
node with ID=2. The control panel node sends a packet to this node with a command to turn on the fan.

8. The sensor/actuator node with ID=2 receives the command, turns on the fan, and sends a confirmation packet back
to the control panel node. The control panel node updates the GUI to reflect the new status of the fan.

9. The user of the second control panel presses the "turn off all actuators" button. The control panel node sends a 
packet to all actuator nodes with a command to turn off all actuators.

10. All actuator nodes receive the command, turn off all their actuators, and send confirmation packets back to the control
panel node. The control panel nodes update the GUI to reflect the new status of the actuators. 



## Reliability and security

TODO - describe the reliability and security mechanisms your solution supports.

We use TCP which is reliable. We don't have to implement our own reliability mechanisms.
