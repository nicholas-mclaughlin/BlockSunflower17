//package logic;

/**
 * The game class will be responsible for setting and getting the player and
 * keeping track of the changes the player makes to the garden plot.
 */
public class Game {
	
	private Player player;
	
	/**
	 *  gardenPlots is a 2D array of string that contains the string of "<row>,<column>"
	 *  the garden plots as the default.
	 */
	private String[][] gardenPlots = {{"0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6", "0,7", "0,8"},
				{"1,0", "1,1", "1,2", "1,3", "1,4", "1,5", "1,6", "1,7", "1,8"},
				{"2,0", "2,1", "2,2", "2,3", "2,4", "2,5", "2,6", "2,7", "2,8"},
				{"3,0", "3,1", "3,2", "3,3", "3,4", "3,5", "3,6", "3,7", "3,8"},
				{"4,0", "4,1", "4,2", "4,3", "4,4", "4,5", "4,6", "4,7", "4,8"}};
	
	//constructor
	public Game(Player aPlayer, String[][] aGardenPlot) {
		this.player = aPlayer;
		this.gardenPlots = getGardenPlots();
	}

	public Player getPlayer() {
		return player;
	}
	
	/**
	 * getPlot() method creates a new String object containing the string of
	 * the specified row and column in the 2D array gardenPlots.
	 * 
	 * @param row	row is to be used for determining the row in the 2D array
	 * @param column	column is to be used for determing the column
	 * @return	the new String of coordinate
	 */
	public String getPlot(int row, int column) {
		return new String(this.gardenPlots[row][column]);
	}
	
	/**
	 * getGardenPlots() creates a new 2D array of Strings of the same elements in the local
	 * gardenPlot and returns it back.
	 * 
	 * @return copy of gardenPlot
	 */
	public String[][] getGardenPlots() {
		String[][] theGarden = new String[5][9];
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				theGarden[row][column] = getPlot(row, column);
			}
		}
		return theGarden;
	}

	/**
	 * getRow determines the row of the argument passed through
	 * a loop that goes through every element and saves the row number and breaks once
	 * it finds the exact same element in the 2D array
	 * 
	 * @param coordinate	String that must mirror a String element in the 2D array
	 * @return	the row number of where the string was found
	 */
	public int getRow(String coordinate) {
		int theRow = 0;
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				if (gardenPlots[row][column].equals(coordinate)) {
					theRow = row;
					break;
				}
			}
		} return theRow;
	}
	
	/**
	 * getColumn determines the column of the argument passed through a loop that
	 * goes through every element and saves the column number and breaks once
	 * it finds the exact same element in the 2D array
	 * 
	 * @param coordinate	String that must mirror a String element in the 2D array
	 * @return	the column number of where the string was found
	 */
	public int getColumn(String coordinate) {
		int theColumn = 0;
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				if (gardenPlots[row][column].equals(coordinate)) {
					theColumn = column;
					break;
				}
			}
		} return theColumn;
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
		gardenPlots[row][column] = thePlant.getType();
	}

}
