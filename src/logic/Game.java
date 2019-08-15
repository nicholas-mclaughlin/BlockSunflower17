package logic;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

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
	private static Plant[][] gardenPlots;
	
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
	
	public void resetPlot(int row, int column) throws FileNotFoundException {
		Plant thePlant = new Plant(row + "," + column);
		thePlant.setPlantImage(null);
		gardenPlots[row][column] = thePlant;
		theGarden[row][column] = thePlant.getType();
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
	
	public ArrayList<Zombie> getZombieRow(int row) throws FileNotFoundException {
		ArrayList<Zombie> zombieRow = null;
		if (row == 1) {
			zombieRow = this.zombieRow1;
		} else if (row == 2) {
			zombieRow = zombieRow2;
		} else if (row == 3) {
			zombieRow = zombieRow3;
		} else if (row == 4) {
			zombieRow = zombieRow4;
		} else if (row == 5) {
			zombieRow = zombieRow5;
		}
		ArrayList<Zombie> newZombieRow = new ArrayList<Zombie>();
		for (Zombie z: zombieRow) {
			newZombieRow.add(z);
		}
		return newZombieRow;
	}

	
public void zombieTracker(Zombie aZombie) throws FileNotFoundException {
		
		if (aZombie.getRow() == 1) {
			zombieRow1.add(aZombie);
		} else if (aZombie.getRow() == 2) {
			zombieRow2.add(aZombie);
		} else if (aZombie.getRow() == 3) {
			zombieRow3.add(aZombie);
		} else if (aZombie.getRow() == 4) {
			zombieRow4.add(aZombie);
		} else if (aZombie.getRow() == 5) {
			zombieRow5.add(aZombie);
		}
		
		int delay = 0; //No delay
		int updateTime = 100; //Gets the location to update every second
		double j=  (aZombie.getSpeed() / updateTime);
		Timer t = new Timer();
		t.schedule(new TimerTask() {
		            @Override
		             public void run() {

		            	//aZombie.setPosition(aZombie.getPosition() - gardenLength / j);

		             }
		 }, delay, updateTime);
	} 
	
/*	public Zombie getClosestZombie(int row) throws FileNotFoundException {
		ArrayList<Zombie> zombieRow = getZombieRow(row);
		int closestPosition = 0;
		Zombie zombie = null;
		for (Zombie z: zombieRow) {
			if (z.columnNumber() < closestPosition) {
				closestPosition = z.columnNumber();
				zombie = z;
				//System.out.println("" + z.columnNumber());
			}
		}
		return zombie;
	}
*/	
	public String rowsToString() {
		String zombieRow1 = "Row1: ";
		String zombieRow2 = "\nRow2: ";
		String zombieRow3 = "\nRow3: ";
		String zombieRow4 = "\nRow4: ";
		String zombieRow5 = "\nRow5: ";
		
		if (this.zombieRow1 != null) {
			for (Zombie z: this.zombieRow1) {
				zombieRow1 += z.getType() + ", ";
			}
		}
		if (this.zombieRow2 != null) {
			for (Zombie z: this.zombieRow2) {
				zombieRow2 += z.getType() + ", ";
			}
		}
		if (this.zombieRow3 != null) {
			for (Zombie z: this.zombieRow3) {
				zombieRow3 += z.getType() + ", ";
			}
		}
		if (this.zombieRow4 != null) {
			for (Zombie z: this.zombieRow4) {
				zombieRow4 += z.getType() + ", ";
			}
		}
		if (this.zombieRow5 != null) {
			for (Zombie z: this.zombieRow5) {
				zombieRow5 += z.getType() + ", ";
			}
		}
		return zombieRow1 + zombieRow2 + zombieRow3 + zombieRow4 + zombieRow5 + "\n";
	}
	
}
