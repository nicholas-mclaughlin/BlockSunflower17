//package logic;
import java.io.FileNotFoundException;
/**
 * The game class will be responsible for setting and getting the player and
 * keeping track of the changes the player makes to the garden plot.
 */
public class Game {
	
	private Player player;
	static String[][] theGarden = new String[5][10];
	
	/**
	 *  gardenPlots is a 2D array of GameCharacter with type string format "<row>,<column>"
	 *  the row and column being the garden plots index.
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
	 * getGardenPlots() creates a new 2D array of gamecharacters of the same elements in the local
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
	 * placePlant changes an element in the 2D array list into the type of plant passed into the argument
	 * 
	 * @param aPlant	GameCharacter, type of plant (e.g. PeaShooter)
	 * @param row	row where the plant is to be placed
	 * @param column	column where the plant is to be placed
	 */
	public void placePlant(Plant aPlant, int row, int column) {
		Plant thePlant = new Plant(aPlant);
		gardenPlots[row][column] = thePlant;
	}
	
	public String[][] gardenPlotString() {
		
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 10; column++) {
				theGarden[row][column] = getCharacter(row, column).getType();
			}
		}
		return theGarden;
	}
	
	public char[][] gardenPlotChar(){
		char[][] theGarden = new char[5][10];
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 10; column++) {
				theGarden[row][column] = getCharacter(row, column).getFirstChar();
			}
		}
		return theGarden;
		
	}
	public void printGardenPlotString() {
		String[][] textGarden = gardenPlotString();
		System.out.println("---------------------------------------");
		for(int i = 0; i<5; i++)
		{
		    for(int j = 0; j<9; j++)
		    {
		        System.out.print(textGarden[i][j] + " ");
		    }
		    System.out.println();
		}
		System.out.println("---------------------------------------");
	}
	public void printGardenPlotChar() {
		char[][] textGarden = gardenPlotChar();
		System.out.println("---------------------");
		for(int i = 0; i<5; i++)
		{
		    for(int j = 0; j<9; j++)
		    {
		        System.out.print(textGarden[i][j] + " ");
		    }
		    System.out.println();
		}
		System.out.println("----------------------");
	}
	
	
}
