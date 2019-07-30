//package logic;

public class Game {
	
	private Player player;
	private String[][] garden = {{"0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6", "0,7", "0,8"},
				{"1,0", "1,1", "1,2", "1,3", "1,4", "1,5", "1,6", "1,7", "1,8"},
				{"2,0", "2,1", "2,2", "2,3", "2,4", "2,5", "2,6", "2,7", "2,8"},
				{"3,0", "3,1", "3,2", "3,3", "3,4", "3,5", "3,6", "3,7", "3,8"},
				{"4,0", "4,1", "4,2", "4,3", "4,4", "4,5", "4,6", "4,7", "4,8"}};
	
	public Game(Player aPlayer, String[][] aGardenPlot) {
		this.player = aPlayer;
		this.gardenPlots = getGardenPlots();
	}

	public Player getPlayer() {
		return player;
	}
	
	public String getPlot(int row, int column) {
		return new String(this.gardenPlots[row][column]);
	}
	
	public String[][] getGardenPlots() {
		String[][] theGarden = new String[5][9];
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				theGarden[row][column] = getPlot(row, column);
			}
		}
		return theGarden;
	}

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
	
	public void placePlant(String aPlant, int row, int column) {
		Plant thePlant = new Plant(aPlant);
		gardenPlots[row][column] = thePlant.getType();
	}

}
