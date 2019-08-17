package logic;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Level class is where the zombies (number of zombies and their rows) get set up for
 * a game depending on which level was desired.
 */
public class Level {
	private int levelNum;
	private ArrayList<Zombie> zombies = new ArrayList<>();
	static int counter = 0;
	static char[][] textGarden = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
								  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
								  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
								  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
								  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
	boolean collision = false;
	
	/**
	 * Adds certain zombies and sets their row
	 * 
	 * @param int   leveNumber can be 1, 2, or 3
	 */
	public Level(int levelNumber) throws FileNotFoundException {
		this.setLevelNum(levelNumber);
		if (levelNumber == 1) {
			zombies.add(new Zombie("Zombie", 4));
			zombies.add(new Zombie("Zombie", 3));
			zombies.add(new Zombie("Cone Zombie",1));
			zombies.add(new Zombie("Zombie", 2));
			zombies.add(new Zombie("Flag Zombie", 4));
			zombies.add(new Zombie("Zombie", 2));
			zombies.add(new Zombie("Cone Zombie", 5));
			zombies.add(new Zombie("Zombie", 3)); 
			int counter = 0;
			for (int z = 0; z < zombies.size(); z++) {
				zombies.get(z).addToPosition(counter);
				counter += 200;  
		} 
		}
		else if (levelNumber == 2) {
			zombies.add(new Zombie("Zombie", 2));
			zombies.add(new Zombie("Zombie", 3));
			zombies.add(new Zombie("Cone Zombie", 5));
			zombies.add(new Zombie("Zombie", 1));
			zombies.add(new Zombie("Football Zombie", 4));
			zombies.add(new Zombie("Flag Zombie", 3));
			zombies.add(new Zombie("Zombie", 3));
			zombies.add(new Zombie("Cone Zombie", 1));
			zombies.add(new Zombie("Zombie", 2));
			zombies.add(new Zombie("Zombie", 5));
			zombies.add(new Zombie("Football Zombie", 1));
			int counter = 0;
			for (int z = 0; z < zombies.size(); z++) {
				zombies.get(z).addToPosition(counter);
				counter += 200;

		}}
		else if (levelNumber == 3) {
			zombies.add(new Zombie("Cone Zombie", 4));
			zombies.add(new Zombie("Zombie", 2));
			zombies.add(new Zombie("Cone Zombie", 1));
			zombies.add(new Zombie("Football Zombie", 5));
			zombies.add(new Zombie("Zombie", 3));
			zombies.add(new Zombie("Flag Zombie", 2));
			zombies.add(new Zombie("Football Zombie", 4));
			zombies.add(new Zombie("Zombie", 1));
			zombies.add(new Zombie("Zombie", 1));
			zombies.add(new Zombie("Football Zombie", 3));
			zombies.add(new Zombie("Zombie", 5));
			zombies.add(new Zombie("Cone Zombie", 2));
			int counter = 0;
			for (int z = 0; z < zombies.size(); z++) {
				zombies.get(z).addToPosition(counter);
				counter += 200;
		}}
	}
	
	public int getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	public ArrayList<Zombie> getZombies() {
		return zombies;
	}
	
	
	public static void printGarden() {
		System.out.println("------------------");
		for(int i = 0; i<5; i++)
		{
		    for(int j = 0; j<9; j++)
		    {
		        System.out.print(textGarden[i][j] + " ");
		    }
		    System.out.println();
		}
		System.out.println("------------------");
	}

}
