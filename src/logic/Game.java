package logic;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import gui.GardenScene;
import javafx.scene.control.Button;

/**
 * The game class will be responsible for setting and getting the player and
 * keeping track of the changes the player makes to the garden plot.
 */
public class Game {
	
	private Player player;
	public static String[][] theGarden = new String[5][9];
	public Zombie zombie;
	public Plant plant;	
	
	/**
	 *  gardenPlots is a 2D array of GameCharacter with type string format "<row>,<column>"
	 *  the row and column being the garden plots index.
	 */
	private GameCharacter[][] gardenPlots = {{new GameCharacter("0,0"), new GameCharacter("0,1"), new GameCharacter("0,2"), new GameCharacter("0,3"),
		new GameCharacter("0,4"),new GameCharacter("0,5"), new GameCharacter("0,6"), new GameCharacter("0,7"), new GameCharacter("0,8")},
		{new GameCharacter("1,0"), new GameCharacter("1,1"), new GameCharacter("1,2"), new GameCharacter("1,3"), new GameCharacter("1,4"), new GameCharacter("1,5"),
		new GameCharacter("1,6"), new GameCharacter("1,7"), new GameCharacter("1,8")}, {new GameCharacter("2,0"), new GameCharacter("2,1"),
		new GameCharacter("2,2"), new GameCharacter("2,3"), new GameCharacter("2,4"), new GameCharacter("2,5"), new GameCharacter("2,6"), new GameCharacter("2,7"),
		new GameCharacter("2,8")}, {new GameCharacter("3,0"), new GameCharacter("3,1"), new GameCharacter("3,2"), new GameCharacter("3,3"),
		new GameCharacter("3,4"), new GameCharacter("3,5"), new GameCharacter("3,6"), new GameCharacter("3,7"), new GameCharacter("3,8")},
		{new GameCharacter("4,0"), new GameCharacter("4,1"), new GameCharacter("4,2"), new GameCharacter("4,3"), new GameCharacter("4,4"), new GameCharacter("4,5"),
			new GameCharacter("4,6"), new GameCharacter("4,7"), new GameCharacter("4,8")}};
	{
	for (int r = 0; r < 5; r++) {
		for (int column = 0; column < 9; column++) {
			theGarden[r][column] = gardenPlots[r][column].getType(); 
		}}
	}
	
	private Plant[][] plantPlots;
	
	
	//constructor
	public Game(Player aPlayer, GameCharacter[][] aGardenPlot) throws FileNotFoundException {
		this.player = aPlayer;
		this.gardenPlots = getGardenPlots();
		this.plantPlots = new Plant[][] {{new Plant("0,0"), new Plant("0,1"), new Plant("0,2"), new Plant("0,3"),
			new Plant("0,4"),new Plant("0,5"), new Plant("0,6"), new Plant("0,7"), new Plant("0,8")},
				{new Plant("1,0"), new Plant("1,1"), new Plant("1,2"), new Plant("1,3"), new Plant("1,4"), new Plant("1,5"),
				new Plant("1,6"), new Plant("1,7"), new Plant("1,8")}, {new Plant("2,0"), new Plant("2,1"),
				new Plant("2,2"), new Plant("2,3"), new Plant("2,4"), new Plant("2,5"), new Plant("2,6"), new Plant("2,7"),
				new Plant("2,8")}, {new Plant("3,0"), new Plant("3,1"), new Plant("3,2"), new Plant("3,3"),
				new Plant("3,4"), new Plant("3,5"), new Plant("3,6"), new Plant("3,7"), new Plant("3,8")},
				{new Plant("4,0"), new Plant("4,1"), new Plant("4,2"), new Plant("4,3"), new Plant("4,4"), new Plant("4,5"),
					new Plant("4,6"), new Plant("4,7"), new Plant("4,8")}};
		
	}

	public Player getPlayer() {
		return player;
	}

	public GameCharacter getCharacter(int row, int column) {
		return new GameCharacter(this.gardenPlots[row][column]);
	}
	
	public Plant getPlant(int row, int column) throws Exception {
		return new Plant(this.plantPlots[row][column]);
	}
	
	/**
	 * getGardenPlots() creates a new 2D array of gamecharacters of the same elements in the local
	 * gardenPlot and returns it back.
	 * 
	 * @return copy of gardenPlot
	 */
	public GameCharacter[][] getGardenPlots() {
		GameCharacter[][] theGarden = new GameCharacter[5][9];
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				theGarden[row][column] = getCharacter(row, column);
			}
		}
		return theGarden;
	}
	
	/**
	 * placePlant changes an element in the 2D array list into the type of plant passed into the argument
	 * 
	 * @param aPlant	GameCharacter, type of plant (e.g. PeaShooter)
	 * @param row	row where the plant is to be placed
	 * @param column	column where the plant is to be placed
	 * @throws Exception 
	 */
	public void placePlant(GameCharacter aPlant, int row, int column) throws Exception {
		GameCharacter thePlant = new GameCharacter(aPlant);
		gardenPlots[row][column] = thePlant;
		theGarden[row][column] = thePlant.getType();
	}
	
	public void placePlant2(Plant aPlant, int row, int column) throws Exception{
		Plant thePlant = new Plant(aPlant);
		plantPlots[row][column] = thePlant;
	}
	
	public void gardenPlotString() {
		
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				theGarden[row][column] = getCharacter(row, column).getType();
			}
		}
		//return theGarden;
	}
	
	
	public Plant[][] getPlantPlots() throws Exception {
		Plant[][] thePlants = new Plant[5][9];
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				if (thePlants[row][column] != null) {
					thePlants[row][column] = getPlant(row, column);
				}
			}
		}
		return thePlants;
	}
	
	public void printGardenPlotString() {
		//String[][] textGarden = gardenPlotString();
		gardenPlotString();
		System.out.println("---------------------------------------");
		for(int i = 0; i<5; i++)
		{
		    for(int j = 0; j<9; j++)
		    {
		        System.out.print(theGarden[i][j] + " ");
		    }
		    System.out.println();
		}
		System.out.println("---------------------------------------");
	}
	
	public static void gameOver(boolean gameOver, String gameStatus) {
		if (gameOver) {
			GardenScene.gameOverMessage = new Button(gameStatus);
	
	
			GardenScene.fullImage.getChildren().add(GardenScene.gameOverMessage);
	
			GardenScene.gameOverMessage.setStyle("-fx-opacity: 0.4;-fx-font-size: 75;  -fx-font-weight: bold;");
			//-fx-background-color: transparent;
			GardenScene.gameOverMessage.setDisable(false);
			//the message is as big as the window size so nothing is clickable
			GardenScene.gameOverMessage.setLayoutY(0);
			GardenScene.gameOverMessage.setLayoutX(0);
			GardenScene.gameOverMessage.setPrefSize(1220,720);
	
		}
	}
	
	
}
