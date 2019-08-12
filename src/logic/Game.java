package logic;

/**
 * The game class will be responsible for setting and getting the player and
 * keeping track of the changes the player makes to the garden plot.
 */
public class Game {
	
	private Player player;
	public static String[][] theGarden = new String[5][9];
	
	
	/**
	 *  gardenPlots is a 2D array of GameCharacter with type string format "<row>,<column>"
	 *  the row and column being the garden plots index.
	 */
	private Plant[][] gardenPlots = {{new Plant("0,0"), new Plant("0,1"), new Plant("0,2"), new Plant("0,3"),
		new Plant("0,4"),new Plant("0,5"), new Plant("0,6"), new Plant("0,7"), new Plant("0,8")},
		{new Plant("1,0"), new Plant("1,1"), new Plant("1,2"), new Plant("1,3"), new Plant("1,4"), new Plant("1,5"),
		new Plant("1,6"), new Plant("1,7"), new Plant("1,8")}, {new Plant("2,0"), new Plant("2,1"),
		new Plant("2,2"), new Plant("2,3"), new Plant("2,4"), new Plant("2,5"), new Plant("2,6"), new Plant("2,7"),
		new Plant("2,8")}, {new Plant("3,0"), new Plant("3,1"), new Plant("3,2"), new Plant("3,3"),
		new Plant("3,4"), new Plant("3,5"), new Plant("3,6"), new Plant("3,7"), new Plant("3,8")},
		{new Plant("4,0"), new Plant("4,1"), new Plant("4,2"), new Plant("4,3"), new Plant("4,4"), new Plant("4,5"),
			new Plant("4,6"), new Plant("4,7"), new Plant("4,8")}};
	{
	for (int r = 0; r < 5; r++) {
		for (int column = 0; column < 9; column++) {
			theGarden[r][column] = gardenPlots[r][column].getType();
		}}
	}
	/*
	private Zombie[] zombieRow0 = new Zombie[1220];
	private Zombie[] zombieRow1 = new Zombie[1220];
	private Zombie[] zombieRow2 = new Zombie[1220];
	private Zombie[] zombieRow3 = new Zombie[1220];
	private Zombie[] zombieRow4 = new Zombie[1220];
	*/
	//constructor
	public Game(Player aPlayer, Plant[][] aGardenPlot) {
		this.player = aPlayer;
		this.gardenPlots = getGardenPlots();
	}

	public Player getPlayer() {
		return player;
	}

	public Plant getCharacter(int row, int column) {
		return new Plant(this.gardenPlots[row][column]);
	}
	
	
	/**
	 * getGardenPlots() creates a new 2D array of gamecharacters of the same elements in the local
	 * gardenPlot and returns it back.
	 * 
	 * @return copy of gardenPlot
	 */
	public Plant[][] getGardenPlots() {
		Plant[][] theGarden = new Plant[5][9];
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
	public void placePlant(Plant aPlant, int row, int column) throws Exception {
		Plant thePlant = new Plant(aPlant);
		gardenPlots[row][column] = thePlant;
		theGarden[row][column] = thePlant.getType();
	}
	
	public void gardenPlotString() {
		
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				theGarden[row][column] = getCharacter(row, column).getType();
			}
		}
		//return theGarden;
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
	
	
	
}
