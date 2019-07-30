# BlockSunflower17
BlockSunflower17 containes the game "Garden Defense" which is inspired from 'Plants vs Zombies'. In our game, the currency in the world is "suns" which the player can use to buy plants. These can be placed in a garden to block the incoming zombies from reaching the house. Certain plants are better for slowing down the zombies from getting to the house while other plants are better for attacking the zombies to decrease their health. The game ends when either the zombies get to the house or there are no more zombies that go to the house. 

## How our game works:
Session is the class that launches the scenes where the game takes place. The garden scene creates an instance of a player and a game for each time session is run. It also references the Zombie class that creates zombies and the button handlers which setup the plants and plant them. The GameCharacter class has all the basic attributes that plants and zombies would have. 

# Installation
To install, go to the link https://github.com/nicholas-mclaughlin/BlockSunflower17 and dowload all the files in a zip file, including the images on there. When extracting this file, ensure that the images.css file as well as all the images from zombies and plants are outside their folders, and indivdually in the main java project folder BlockSunflower17, if they are not already there. As well, ensure that the java project folder has a src (source) folder with a default package inside it. All the classes should be inside the default package.

# Running and Compiling
To compile the code, on your computer, through the command prompt, navigate to where the BlockSunflower17 folder is installed, and compile the class 'Session.java'. To run the code, after compiling, type in 'java Session'. If using an IDE, simply run the Session class as a Java application. 

## Technologies needed
This game uses Java and JavaFX. If running in the Eclipse IDE, an external *.jar file would be needed in the java project folder.

## Playing
Once the game starts running, click start to begin playing. Choose a plant type to plant by clicking it and then clicking the garden slot you would like to place the plant in.

# Version 2
This is the second iteration of our game that now contains a GUI. The plants and zombies are not interacting yet, and the suns have not yet been created. 

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

