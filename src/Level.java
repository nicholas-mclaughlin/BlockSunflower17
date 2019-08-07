//package logic;

import java.io.FileNotFoundException;

public class Level {
	private int levelNum;
	public Zombie[] zombies;
	static int counter = 0;

	public Level(int levelNumber) throws FileNotFoundException {
		this.setLevelNum(levelNumber);
		if (levelNumber == 1) {
			zombies = new Zombie[8];
			getZombies()[0] = (new Zombie("Zombie", 4));
			getZombies()[1] =  (new Zombie("Zombie", 3));
			getZombies()[2] = (new Zombie("Cone Zombie",1));
			getZombies()[3] = (new Zombie("Zombie", 2));
			getZombies()[4] = (new Zombie("Flag Zombie", 4));
			getZombies()[5] = (new Zombie("Zombie", 2));
			getZombies()[6] = (new Zombie("Cone Zombie", 5));
			getZombies()[7] = (new Zombie("Zombie", 3));
			int counter = 0;
			for (Zombie z : getZombies()) {
				z.addToPosition(counter);
				counter += 200;
		}
		else if (levelNumber == 2) {
			zombies  = new Zombie[11];
			getZombies()[0] = (new Zombie("Zombie", 2));
			getZombies()[1] =  (new Zombie("Zombie", 3));
			getZombies()[2] = (new Zombie("Cone Zombie", 5));
			getZombies()[3] = (new Zombie("Zombie", 1));
			getZombies()[4] = (new Zombie("Football Zombie", 4));
			getZombies()[5] = (new Zombie("Flag Zombie", 3));
			getZombies()[6] = (new Zombie("Zombie", 3));
			getZombies()[7] = (new Zombie("Cone Zombie", 1));
			getZombies()[8] = (new Zombie("Zombie", 2));
			getZombies()[9] = (new Zombie("Zombie", 5));
			getZombies()[10] = (new Zombie("Football Zombie", 1));
			int counter = 0;
			for (Zombie z : getZombies()) {
				z.addToPosition(counter);
				counter += 200;

		}
		else if (levelNumber == 3) {
			zombies = new Zombie[13];
			getZombies()[0] = (new Zombie("Cone Zombie", 4));
			getZombies()[1] =  (new Zombie("Zombie", 2));
			getZombies()[2] = (new Zombie("Cone Zombie", 1));
			getZombies()[3] = (new Zombie("Football Zombie", 5));
			getZombies()[4] = (new Zombie("Zombie", 3));
			getZombies()[5] = (new Zombie("Zombie", 3));
			getZombies()[6] = (new Zombie("Flag Zombie", 2));
			getZombies()[7] = (new Zombie("Football Zombie", 4));
			getZombies()[8] = (new Zombie("Zombie", 1));
			getZombies()[9] = (new Zombie("Zombie", 1));
			getZombies()[10] = (new Zombie("Football Zombie", 3));
			getZombies()[11] = (new Zombie("Zombie", 5));
			getZombies()[12] = (new Zombie("Cone Zombie", 2));
			int counter = 0;
			for (Zombie z : getZombies()) {
				z.addToPosition(counter);
				counter += 200;
		}
	}

/*	public Level(Level toCopy) {
		this.levelNum = toCopy.levelNum;
		this.
	}
*/
	public int getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	public Zombie[] getZombies() {
		return zombies;
	}


}
