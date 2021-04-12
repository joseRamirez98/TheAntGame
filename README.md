# TheAntGame

## Background 
This assignment is a semester long project for Dr. Gordon's CSC 133 class (Object-Oriented Computer Graphics Programming) at Sacramento State University. 
This project is being completed using CodeNameOne, an open-source cross-platform framework aiming to provide write once, run anywhere code for various mobile and desktop operating systems, along with the Eclipse IDE.
## Introduction for A1
This semester we will study object-oriented graphics programming and design by developing a simple video game we’ll call ThePath. In this game, you (the player) will be controlling an ant walking around a path while trying to avoid collisions with spiders and to keep your ant fed.


The goal of this first assignment is to develop a good initial class hierarchy and control structure by designing the program in UML and then implementing it in Codename One (CN1). This version will use keyboard input commands to control and display the contents of a “game world” containing the set of objects in the game. In future assignments, many of the keyboard commands will be replaced by interactive GUI operations, and we will add graphics, animation, and sound. For now we will simply simulate the game in “text mode” with user input coming from the keyboard and “output” being lines of text on the screen.

## Introduction for A2
For A2, you are to extend your game from A1 to incorporate several important design patterns, and a Graphical User Interface (GUI). The rest of the game will appear to the user to be similar to the one in A1, and most of the code from A1 will be reused, although it will require some modification and reorganization.

An important goal for this assignment will be to reorganize your code so that it follows the Model-View-Controller (MVC) architecture. If you followed the structure specified in A1, you should already have a “controller”: the Game class containing the play()method. The GameWorld class becomes the “data model”, containing the collection of game objects and other game state information. You are also required to add two classes acting as “views”: a score view which will be graphical, and a map view which for now will retain the text-based form generated by the ‘m’ command in A1 (in A3 we will replace the text-based map with an interactive graphical map).

Single-character commands entered via a text field in A1 will be replaced by GUI components (side menu items, buttons, key bindings, etc.). Each such component will have an associated “command” object, and the command objects will perform the same operations as previously performed by the single-character commands.

The program must use appropriate interfaces and built-in classes for organizing the required design patterns. The following design patterns are to be implemented in this assignment:
• Observer/Observable – to coordinate changes in the model with the various views,
• Iterator – to walk through all the game objects when necessary,
• Command – to encapsulate the various commands the player can invoke,
• Singleton – to insure that only a single instance of Ant can exist.
