# Akka Ping-Pong (CLI) Application

[Akka](https://akka.io/docs) is an implementation of the
[actor model](https://en.wikipedia.org/wiki/Actor_model)
proposed by
[Hewitt](https://de.wikipedia.org/wiki/Carl_Hewitt)
 et al. in the seventies. This is a simple implementation of
ping-pong actors.

This Ping-Pong CLI (Common Line Interface) application depends on the following software systems:

- [Akka Actors 2.7](https://akka.io)
- [OpenJDK 17](https://jdk.java.net/17/)
- [Eclipse IDE 2022-09](https://www.eclipse.org/eclipseide/)
- [IntelliJ IDEA CE 2022.3.2](https://www.jetbrains.com/de-de/idea/)
- [Apache Maven 3.8.6](https://maven.apache.org/)


## Ping-Pong Actors

Three actors compose this system: the root actor and two similar actors called Ping and Pong. The main program simply creates the root actor and sends the Init message to it.  Upon receipt of this message, the root actor spawns (creates) the Ping and Pong actors and sends them the Setup message which contains the reference of the other actor to enable them to communicate.
Then, the root actor sends the Start message to Ping to start the communication between Ping and Pong.


### UML Communication diagram

The following UML communication diagram illustrates the actors and the messages that are exchanged:

![Ping-Pong Actor System](img/ping-pong-actor-system.png)

Note that the pingPongRoot:PingPongRoot actor is create through the ActorSystem which is not displayed.

### Ping Actor:

On receipt of the following messages, the Ping actor:

- SetUp &rarr; stores the reference that in the message.
- Start &rarr; sends the Ping message to Pong.
- Ping  &rarr; print a message on the console, sends the Pong message to Pong.


### Pong Actor:

On receipt of the following messages, the Pong actor:

- Setup &rarr; stores the reference that in the message.
- Pong  &rarr; print a message on the console, sends the Ping message to Ping.


### Messages

The following messages are used:

- Init: Sent by the main program to tell to the root actor to initialize the two other actors <br>
        Does not contains any information
		
- SetUp: Sent by the root actor to set up the two child-actors<br>
	     Contains the reference of the other child-actor, this reference is used by the Ping actor
	     to send the Ping message. Symmetric behavior from Pong actor.
		
- Ping: Sent by Ping actor to Pong<br>
        Does not contains any information
			   
- Pong: Sent by Pong actor to Ping<br>
        Does not contains any information


### Output on the console

The program writes a never ending sequence of messages: ping pong ping pong ...

To stop the program the user must hit the 'Enter' key.


### Remarks

- The system is organized as a tree of actors. The root actor is created by the main program and
is in charge to spawn some other actors (child-actors).  Obviously, an actor can also spawn, subsequently, other actors.
- The messages are generally defined as inner classes in the actor that receives them.
- Each actor evolves independently and concurrently.
- To send a message to another actor, the source actor needs the reference of the destination actor.
- An actor can include any information in a message.  However, such information must be immutable to guarantee  the absence of race conditions typical and difficult to manage in classic concurrent programs.
- An actor can send any references it knows to other actors, just by including the references in the message.  Sending its own reference allow the other actor to reply to the sender.
- Sending a message is an asynchronous communication (in contrast, calling a method is generally synchronous).  We observe the asynchronous aspect of the communication is the following UML communication diagram by the arrow that are v-shape and not full-black as for synchronous method calls.
- Some specific notation for an actor has been introduced in the UML communication diagram because no notation is available in UML.


## Import into your favorite IDE

This is a Maven project. Just import the project (use 'Open...' in IntelliJ,
use 'Import...' -> 'Existing Maven Project' in Eclipse).


## Compilation and Execution with Maven

Assuming you have the Maven command available in your terminal,
to compile the demo application, type:

```console
mvn clean package
```

To execute the application, type:

```console
mvn clean package exec:java
```


## Checkstyle, Javadoc

To run Checkstyle, type:

```console
mvn checkstyle:check
```

To generate the Javadoc API documentation, type:

```console
mvn clean compile javadoc:javadoc
```

