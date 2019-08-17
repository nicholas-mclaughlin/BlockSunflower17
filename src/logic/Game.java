package logic;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The game class will be responsible for setting and getting the player and
 * keeping track of the changes the player makes to the garden plot.
 */
public class Game {
	
	private Player player;
	private static String[][] theGarden = new String[5][9];
	
	/**
	 *  gardenPlots is a 2D array of Plant objects with type string format "<row>,<column>"
	 *  the row and column being the garden plots index.
	 */
	private static Plant[][] gardenPlots;
	
	/*
	 *  keeps track of where zombies are (row) 
	 */
	private ArrayList<Zombie> zombieRow1 = new ArrayList<>();
	private ArrayList<Zombie> zombieRow2 = new ArrayList<>();
	private ArrayList<Zombie> zombieRow3 = new ArrayList<>();
	private ArrayList<Zombie> zombieRow4 = new ArrayList<>();
	private ArrayList<Zombie> zombieRow5 = new ArrayList<>();
	
	
	//constructor
	@SuppressWarnings("static-access")
	public Game(Player aPlayer, Plant[][] aGardenPlot) throws Exception {
		this.player = aPlayer;
		this.gardenPlots = aGardenPlot;
	}

	public Player getPlayer() {
		return player;
	}
	
	
	public static String[][] getTheGarden() {
		return theGarden;
	}

	public static void setTheGarden(String[][] theGarden) {
		Game.theGarden = theGarden;
	}

	public static Plant getPlant(int row, int column) throws Exception {
		return gardenPlots[row][column];
	}
	
	/**
	 * getGardenPlots() creates a new 2D array of gamecharacters of the same elements in the local
	 * gardenPlot and returns it back.
	 * 
	 * @return copy of gardenPlot
	 * @throws Exception 
	 */
	public Plant[][] getGardenPlots() throws Exception {
		Plant[][] theGarden = new Plant[5][9];
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				theGarden[row][column] = getPlant(row, column);
			}
		}
		return theGarden;
	}
	
	/**
	 * placePlant changes an element in the 2D array list into the type of plant passed into the argument
	 * 
	 * @param aPlant	Plant, type of plant (e.g. PeaShooter)
	 * @param row	row where the plant is to be placed
	 * @param column	column where the plant is to be placed
	 * @throws Exception 
	 */
	public void placePlant(Plant aPlant, int row, int column) throws Exception {
		Plant thePlant = new Plant(aPlant);
		gardenPlots[row][column] = thePlant;
		theGarden[row][column] = thePlant.getType();
	}
	
	
	/*
	 * resetPlot resets a plot of where a given plant was in the grid
	 * it replaces a Plant in the grid with the default Plant object with
	 * with type string format "<row>,<column>" just like when the game was initialized.
	 * 
	 * @param Plant   given param is supposedly a Plant that died during a game.
	 * 					it provides the row and column of the plant as well.
	 */
	public void resetPlot(Plant plant) throws FileNotFoundException {
		Plant thePlant = new Plant(plant.getRow() + "," + plant.getColumn());
		thePlant.setPlantImage(null);
		gardenPlots[plant.getRow()][plant.getColumn()] = thePlant;
		theGarden[plant.getRow()][plant.getColumn()] = thePlant.getType();
	}
	
	public String gardenPlotString() throws Exception {
		String s = "";
		for (int row = 0; row < 5; row++) {
			String line = "";
			for (int column = 0; column < 9; column++) {
				line = (getPlant(row, column).getType() + " ");
			}
			s += line + "\n";
		}
		return s;
	}
	
	/*
	 * getZombieRow gets an arraylist containing the zombies in a specified row
	 * 
	 * @param int 	row is the specified row
	 * @returns ArrayList<Zombie>   returns a new arraylist containing a list of the zombies
	 */
	public ArrayList<Zombie> getZombieRow(int row) throws FileNotFoundException {
		ArrayList<Zombie> zombieRow = null;
		if (row == 1) {
			zombieRow = this.getZombieRow1();
		} else if (row == 2) {
			zombieRow = getZombieRow2();
		} else if (row == 3) {
			zombieRow = getZombieRow3();
		} else if (row == 4) {
			zombieRow = getZombieRow4();
		} else if (row == 5) {
			zombieRow = getZombieRow5();
		}
		ArrayList<Zombie> newZombieRow = new ArrayList<Zombie>();
		for (Zombie z: zombieRow) {
			newZombieRow.add(z);
		}
		return newZombieRow;
	}
	
	/*
	 * removes a zombie from a specified arraylist
	 */
	public void removeZombie(int row, int index) {
		if (row == 1) {
			getZombieRow1().remove(index);
		} else if (row == 2) {
			getZombieRow2().remove(index);
		} else if (row == 3) {
			getZombieRow3().remove(index);
		} else if (row == 4) {
			getZombieRow4().remove(index);
		} else if (row == 5) {
			getZombieRow5().remove(index);
		}
	}

	/*
	 * add a zombie to an arraylist depending which row it was in
	 */
	public void zombieTracker(Zombie aZombie) throws FileNotFoundException {
		
		if (aZombie.getRow() == 1) {
			getZombieRow1().add(aZombie);
		} else if (aZombie.getRow() == 2) {
			getZombieRow2().add(aZombie);
		} else if (aZombie.getRow() == 3) {
			getZombieRow3().add(aZombie);
		} else if (aZombie.getRow() == 4) {
			getZombieRow4().add(aZombie);
		} else if (aZombie.getRow() == 5) {
			getZombieRow5().add(aZombie);
		}
}		

	
	public String rowsToString() {
		String zombieRow1 = "Row1: ";
		String zombieRow2 = "\nRow2: ";
		String zombieRow3 = "\nRow3: ";
		String zombieRow4 = "\nRow4: ";
		String zombieRow5 = "\nRow5: ";
		
		if (this.getZombieRow1() != null) {
			for (Zombie z: this.getZombieRow1()) {
				zombieRow1 += z.getType() + ", ";
			}
		}
		if (this.getZombieRow2() != null) {
			for (Zombie z: this.getZombieRow2()) {
				zombieRow2 += z.getType() + ", ";
			}
		}
		if (this.getZombieRow3() != null) {
			for (Zombie z: this.getZombieRow3()) {
				zombieRow3 += z.getType() + ", ";
			}
		}
		if (this.getZombieRow4() != null) {
			for (Zombie z: this.getZombieRow4()) {
				zombieRow4 += z.getType() + ", ";
			}
		}
		if (this.getZombieRow5() != null) {
			for (Zombie z: this.getZombieRow5()) {
				zombieRow5 += z.getType() + ", ";
			}
		}
		return zombieRow1 + zombieRow2 + zombieRow3 + zombieRow4 + zombieRow5 + "\n";
	}

	public ArrayList<Zombie> getZombieRow1() {
		return zombieRow1;
	}

	public void setZombieRow1(ArrayList<Zombie> zombieRow1) {
		this.zombieRow1 = zombieRow1;
	}

	public ArrayList<Zombie> getZombieRow2() {
		return zombieRow2;
	}

	public void setZombieRow2(ArrayList<Zombie> zombieRow2) {
		this.zombieRow2 = zombieRow2;
	}

	public ArrayList<Zombie> getZombieRow3() {
		return zombieRow3;
	}

	public void setZombieRow3(ArrayList<Zombie> zombieRow3) {
		this.zombieRow3 = zombieRow3;
	}

	public ArrayList<Zombie> getZombieRow4() {
		return zombieRow4;
	}

	public void setZombieRow4(ArrayList<Zombie> zombieRow4) {
		this.zombieRow4 = zombieRow4;
	}

	public ArrayList<Zombie> getZombieRow5() {
		return zombieRow5;
	}

	public void setZombieRow5(ArrayList<Zombie> zombieRow5) {
		this.zombieRow5 = zombieRow5;
	}
	
}
