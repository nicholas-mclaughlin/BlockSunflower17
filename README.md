# BlockSunflower17
BlockSunflower17 containes the game "Garden Defense" which is inspired from 'Plants vs Zombies'. In our game, the currency in the world is "suns" which the player can use to buy plants. These can be placed in a garden to block the incoming zombies from reaching the house which is the goal of the game. Certain plants are better for slowing down the zombies from getting to the house while other plants are better for attacking the zombies to decrease their health. The game ends when either the zombies get to the house or the plants successfully defend the house from zombies by eliminating the last one standing. 

## How our game works:
Session is the class that launches the menu scene which consists of a start button. When clicked, the user is redirected to the garden scene where the game takes place. The garden scene is where the window layout, size, and necessary node is created. It will only have one node which will contain the plant and garden plot buttons. In the same scene, an instance of a player object is created which will then be used to create an instance of a game object initializing the actual game for each time a session is run. For the logic part of the game, a 2D array of strings containing the coordinates of the gardenplots is set as default in order to keep track of the players' moves (which plant was selected to be planted on which coordinate). The scene also references the Zombie class that creates the zombies and adds them to the gui together with the other necessary visuals for plants and garden in the proper order. The GameCharacter class has all the basic attributes that plants and zombies would have. 

# Installation
To install, go to the link https://github.com/nicholas-mclaughlin/BlockSunflower17 and dowload all the files in a zip file, including the images on there. When extracting this file, ensure that the images.css file as well as all the images from zombies and plants are outside their folders, and indivdually in the main java project folder BlockSunflower17, if they are not already there. As well, ensure that the java project folder has a src (source) folder with a default package inside it. All the classes should be inside the default package.

# Running and Compiling
To compile the code, on your computer, through the command prompt, navigate to where the BlockSunflower17 folder is installed, and compile the class 'Session.java'. To run the code, after compiling, type in 'java Session'. If using an IDE, simply run the Session class as a Java application. 

## Technologies needed
This game uses Java and JavaFX. If running in the Eclipse IDE, an external *.jar file would be needed in the java project folder.

## Playing
Once the game starts running, click start to begin playing. Choose a plant type to plant by clicking it and then clicking the garden slot you would like to place the plant in. The strategy to win the game is to place plants in garden slots to block or attack the zombies from reaching the house.

# Version 2
This is the second iteration of our game that now contains a GUI. The plants and zombies are not interacting yet, and the suns have not yet been created. 

## Version 1 
This is the link to our first text-based iteration of the game Garden Defense. https://github.com/nicholas-mclaughlin/BlockTower17

# Authors
Group 17:
Nicholas McLaughlin
Kelly Osena
Uijin Park
Mubarak Adetunji
Luisa Vargas

# References for code development
1) Nathaly Verwaal. Lectures and course material from CPSC 233 Summer 2019.
2) Java Official Oracle Documentation
3) Zain Rizvi, TA at University of Calgary, Youtube Tutorials: https://www.youtube.com/watch?v=AAsCDBIet9w&list=PLaKSf4aF5le5vC9K7k9zJlqv48IW7H1JM&index=9&t=3008s

