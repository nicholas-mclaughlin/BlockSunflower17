# BlockSunflower17
BlockSunflower17 contains the game "Garden Defense" which is inspired from 'Plants vs Zombies'. In our game, the currency in the world
is "suns" which the player can use to buy plants. These can be placed in a garden to block or attack the incoming zombies from reaching 
the house, which is the goal of the game. Certain plants are better for slowing down the zombies from getting to the house while other 
plants are better for attacking the zombies to decrease their health. The game ends when either the zombies get to the house or the 
plants successfully defend the house from zombies by eliminating the last one standing. 

## How our game works:
Session is the class that launches the home scene which has a play button and a credits button. The credits has information on the 
authors. The play redirects the user to the menu scene which consists of three buttons that when clicked, redirect the user to the 
different levels of the garden scene, where the game takes place. The garden scene is where the window layout, size, and necessary node
is created. It will only have one node which will contain the plant and garden plot buttons. In the same scene, an instance of a player
object is created which will then be used to create an instance of a game object initializing the actual game for each time a session is
run. 
For the logic part of the game, a 2D array of strings containing the coordinates of the gardenplots is initially set as default in order
to keep track of the players' moves (which plant was selected to be planted on which coordinate) until a player makes changes by 
planting plants within the grid. The scene also references the Zombie class which creates the zombies and adds them to the GUI together
with the other necessary visuals for plants and garden in the proper order. The GameCharacter class has all the basic attributes that 
plants and zombies would have. The animations of the zombies and plant bullets are created using timers which update the x coordinate.
The main game logic is run through the checkAll(game, level) method in GardenScene that keeps track of the collisions between the plants
(and bullets) and zombies every 10 milliseconds.

# Installation
To install, go to the link https://github.com/nicholas-mclaughlin/BlockSunflower17 and download all the folders in a zip file, including
the images on there. When extracting this file, ensure that the images.css file is in the src (source) folder, inside the Java project 
folder. All the images should be in their respective folders which are the MenuImages, PlantImages, and ZombieImages. The grasswalk.mp3
file should be in the MenuImages folder. All the classes should be in their respective package folders. Such as, the handlers in the 
handler package, the GUI classes which are BaseScene, Credits, Homepage, GardenScene, and Menu, in the gui package. The pvzsun.png 
should be the only image that is in a package, the gui package. Session class should be in the drivers package and the logic package 
classes are Game, GameCharacter, Level, Plant, Player, and Zombie.

## Notes 
The ZombieTest in the logic package is the JUnit test for the Zombie class. 

# Running and Compiling
To compile the code, on your computer, through the command prompt, navigate to where the BlockSunflower17 folder is installed, and 
compile the class 'Session.java'. To run the code, after compiling, type in 'java Session'. If using an IDE, simply run the Session 
class as a Java application. 

## Technologies needed
This game uses Java and JavaFX. If running in the Eclipse IDE, an external *.jar file would be needed in the java project folder.

## Playing
Once the game starts running, click start to begin playing. Choose a plant type to plant by clicking it and then clicking the garden 
slot you would like to place the plant in. The strategy to win the game is to place plants in garden slots to block or attack the 
zombies from reaching the house. Get "suns"-- which randomly appear or are produced when the sunflower plant is planted in order to buy 
plants -- by clicking on them. 

# Final Version 
The final iteration of the game is completely playable and has all the features. The game is now winnable once all the zombies in the 
level are destroyed. Collision also now occur between plants and zombies, and bullets and zombies. Each plant also are able to do
different abilities. All of the animations were also switched from using translateTransition to using timers.

# Version 4
This is the fourth iteration of the game which now has a start scene with a play button that leads to the levels and a credits scene 
that shows the contributions. Both have a back button. The plants and zombies are logically interacting. There is now a "GAME OVER" sign
that appears when the zombies reach the house and the buttons, suns, and garden are no longer clickable, thus the game ends. 

## Version 3
This was the third iteration of our game which had the plants and zombies logically interacting, where the zombie would lose health
everytime it was shot. The plants were now shooting bullets and creating suns. Music had been added as well as a Menu scene to select
different levels, each which is of differing difficulty.

## Version 2
This was the second iteration of our game that contained a GUI version. The plants and zombies were not interacting yet, and the suns 
had not yet been created. 

## Version 1 
For version 1 (Text-based version of our game), navigate to the text-based file on the master branch, then compile and run 
MainClass.java

# Text-Based
For the Text-based version of our game, navigate to the text-based file on the master branch, then compile and run MainClass.java.

# Authors
Group 17:
Nicholas McLaughlin
Kelly Osena
Mubarak Adetunji
Luisa Vargas
Uijin Park

# References for code development
1) Nathaly Verwaal. Lectures and course material from CPSC 233 Summer 2019.
2) Java Official Oracle Documentation
3) Zain Rizvi, TA at University of Calgary, Youtube Tutorials: https://www.youtube.com/watch?
v=AAsCDBIet9w&list=PLaKSf4aF5le5vC9K7k9zJlqv48IW7H1JM&index=9&t=3008s

