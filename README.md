# TheAntGame
This assignment is a semester long project for Dr. Gordon's CSC 133 class (Object-Oriented Computer Graphics Programming) at Sacramento State University. 

## Requirements To Run 
This project is being completed using CodeNameOne, an open-source cross-platform framework aiming to provide write once, run anywhere code for various mobile and desktop operating systems, along with the Eclipse IDE.

• Install latest version of “Java SE JDK version 8” (release 1.8.0_231): <a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">LINK</a>

• Latest version of “Eclipse IDE for Java Developers” (Eclipse IDE 2019-12 R - version 4.14.0) is required : <a href="https://www.eclipse.org/downloads/packages/release/2019-12/r/eclipse-ide-java-developers">LINK</a>

• Install CN1 plugin (latest version 6.0.0) to Eclipse: In Eclipse select “Help” → “Eclipse Marketplace” → search for “Codename One” and follow the installation process
## How To Run
You will need to create a new codenameone project folder to run each of the assignments. 

For example, to run A1: 

• Open Eclispe, File → New → Project → Codename One Project

• Give a project name and uncheck “Java 8 project”. Hit “Next”

• Project Name: A1Prj

• Give a main class name, package name, and select a “native” theme, and “Hello World(Bare Bones)” template (for manual GUI building). Hit “Finish”.

Main Class Name: Starter (keep the same for all assignments)

Package: com.mycompany.a1

## Introduction for A1
This semester we will study object-oriented graphics programming and design by developing a simple video game we’ll call ThePath. In this game, you (the player) will be controlling an ant walking around a path while trying to avoid collisions with spiders and to keep your ant fed.


The goal of this first assignment is to develop a good initial class hierarchy and control structure by designing the program in UML and then implementing it in Codename One (CN1). This version will use keyboard input commands to control and display the contents of a “game world” containing the set of objects in the game. In future assignments, many of the keyboard commands will be replaced by interactive GUI operations, and we will add graphics, animation, and sound. For now we will simply simulate the game in “text mode” with user input coming from the keyboard and “output” being lines of text on the screen.

### Output for A1
<img src="A1_Output.png?raw=true">

## Introduction for A2
For A2, you are to extend your game from A1 to incorporate several important design patterns, and a Graphical User Interface (GUI). The rest of the game will appear to the user to be similar to the one in A1, and most of the code from A1 will be reused, although it will require some modification and reorganization.

An important goal for this assignment will be to reorganize your code so that it follows the Model-View-Controller (MVC) architecture. If you followed the structure specified in A1, you should already have a “controller”: the Game class containing the play()method. The GameWorld class becomes the “data model”, containing the collection of game objects and other game state information. You are also required to add two classes acting as “views”: a score view which will be graphical, and a map view which for now will retain the text-based form generated by the ‘m’ command in A1 (in A3 we will replace the text-based map with an interactive graphical map).

Single-character commands entered via a text field in A1 will be replaced by GUI components (side menu items, buttons, key bindings, etc.). Each such component will have an associated “command” object, and the command objects will perform the same operations as previously performed by the single-character commands.

The program must use appropriate interfaces and built-in classes for organizing the required design patterns. The following design patterns are to be implemented in this assignment:

• Observer/Observable – to coordinate changes in the model with the various views,

• Iterator – to walk through all the game objects when necessary,

• Command – to encapsulate the various commands the player can invoke,

• Singleton – to insure that only a single instance of Ant can exist.

### Output for A2
<img src="A2_Output.png?raw=true">

## Introduction for A3
The purpose of this assignment is to help you gain experience with interactive graphics and animation techniques such as repainting, timer-driven animation, collision detection, and object selection. Specifically, you are to make the following modifications to your game:

(1) the game world map is to display in the GUI (in addition to the text form on the console)

(2) the movement(animation) of game objects is to be driven by a timer

(3) the game is to support dynamic collision detection and response

(4) the game is to support simple interactive editing of some of the objects in the world

(5) the game is to include sounds appropriate to collisions and other events

### Output for A3
<img src="A3_Output.png?raw=true">
