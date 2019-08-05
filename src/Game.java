//package logic;
import java.io.FileNotFoundException;
/**
 * The game class will be responsible for setting and getting the player and
 * keeping track of the changes the player makes to the garden plot.
 */
public class Game {
	
	private Player player;
	
	/**
	 *  gardenPlots is a 2D array of game characters which type contains the string of "<row>,<column>"
	 *  the garden plots as the default.
	 */
	private GameCharacter[][] gardenPlots = {{new GameCharacter("0,0"), new GameCharacter("0,1"), new GameCharacter("0,2"), new GameCharacter("0,3"),
		new GameCharacter("0,4"),new GameCharacter("0,5"), new GameCharacter("0,6"), new GameCharacter("0,7"), new GameCharacter("0,8"), new GameCharacter("0,9")},
		{new GameCharacter("1,0"), new GameCharacter("1,1"), new GameCharacter("1,2"), new GameCharacter("1,3"), new GameCharacter("1,4"), new GameCharacter("1,5"),
		new GameCharacter("1,6"), new GameCharacter("1,7"), new GameCharacter("1,8"), new GameCharacter("1,9")}, {new GameCharacter("2,0"), new GameCharacter("2,1"),
		new GameCharacter("2,2"), new GameCharacter("2,3"), new GameCharacter("2,4"), new GameCharacter("2,5"), new GameCharacter("2,6"), new GameCharacter("2,7"),
		new GameCharacter("2,8"), new GameCharacter("2,9")}, {new GameCharacter("3,0"), new GameCharacter("3,1"), new GameCharacter("3,2"), new GameCharacter("3,3"),
		new GameCharacter("3,4"), new GameCharacter("3,5"), new GameCharacter("3,6"), new GameCharacter("3,7"), new GameCharacter("3,8"), new GameCharacter("3,9")},
		{new GameCharacter("4,0"), new GameCharacter("4,1"), new GameCharacter("4,2"), new GameCharacter("4,3"), new GameCharacter("4,4"), new GameCharacter("4,5"),
			new GameCharacter("4,6"), new GameCharacter("4,7"), new GameCharacter("4,8"), new GameCharacter("4,9")}};
	
	private Zombie[] zombieRow0 = new Zombie[1220];
	private Zombie[] zombieRow1 = new Zombie[1220];
	private Zombie[] zombieRow2 = new Zombie[1220];
	private Zombie[] zombieRow3 = new Zombie[1220];
	private Zombie[] zombieRow4 = new Zombie[1220];
	
	//constructor
	public Game(Player aPlayer, GameCharacter[][] aGardenPlot) {
		this.player = aPlayer;
		this.gardenPlots = getGardenPlots();
	}

	public Player getPlayer() {
		return player;
	}
	
	public GameCharacter getCharacter(int row, int column) {
		return new GameCharacter(this.gardenPlots[row][column]);
	}
	
	/**
	 * getGardenPlots() creates a new 2D array of Strings of the same elements in the local
	 * gardenPlot and returns it back.
	 * 
	 * @return copy of gardenPlot
	 */
	public GameCharacter[][] getGardenPlots() {
		GameCharacter[][] theGarden = new GameCharacter[5][10];
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 10; column++) {
				theGarden[row][column] = getCharacter(row, column);
			}
		}
		return theGarden;
	}
	
	/**
	 * placePlant changes an element in the 2D array list into the String type of plant
	 * based on the row and column passed into the argument
	 * 
	 * @param aPlant	String, type of plant (e.g. PeaShooter)
	 * @param row	row where the plant is to be placed
	 * @param column	column where the plant is to be placed
	 */
	public void placePlant(String aPlant, int row, int column) {
		Plant thePlant = new Plant(aPlant);
		gardenPlots[row][column] = thePlant;
	}
	
	public void trackZombie(Zombie aZombie) throws FileNotFoundException {
		Zombie theZombie = new Zombie(aZombie);
		int position = theZombie.getPosition() - 315;
		if (theZombie.getRow() == 1) {
			zombieRow0[position] = theZombie;
		} else if (theZombie.getRow() == 2) {
			zombieRow1[position] = theZombie;
		} else if (theZombie.getRow() == 3) {
			zombieRow2[position] = theZombie;
		} else if (theZombie.getRow() == 4) {
			zombieRow3[position] = theZombie;
		} else if (theZombie.getRow() == 5) {
			zombieRow4[position] = theZombie;
		}
	}
	
	public String[][] gardenPlotString() {
		String[][] theGarden = new String[5][10];
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 10; column++) {
				theGarden[row][column] = getCharacter(row, column).getType();
			}
		}
		return theGarden;
	}
}
