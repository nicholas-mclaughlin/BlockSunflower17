//package logic;

import java.io.FileNotFoundException;

public class Level {
	private int levelNum;
//	private int numberOfWaves;
	public Zombie[] zombies;
	static int counter = 0;

	public Level(int levelNumber) throws FileNotFoundException {
		this.setLevelNum(levelNumber);
		if (levelNumber == 1) {
			zombies = new Zombie[8];
			getZombies()[0] = (new Zombie("Zombie"));
			getZombies()[1] =  (new Zombie("Zombie"));
			getZombies()[2] = (new Zombie("Cone Zombie"));
			getZombies()[3] = (new Zombie("Zombie"));
			getZombies()[4] = (new Zombie("Flag Zombie"));
			getZombies()[5] = (new Zombie("Zombie"));
			getZombies()[6] = (new Zombie("Cone Zombie"));
			getZombies()[7] = (new Zombie("Football Zombie"));
		}
		else if (levelNumber == 2) {
			zombies  = new Zombie[11];
			getZombies()[0] = (new Zombie("Zombie"));
			getZombies()[1] =  (new Zombie("Zombie"));
			getZombies()[2] = (new Zombie("Cone Zombie"));
			getZombies()[3] = (new Zombie("Zombie"));
			getZombies()[4] = (new Zombie("Football Zombie"));
			getZombies()[5] = (new Zombie("Flag Zombie"));
			getZombies()[6] = (new Zombie("Zombie"));
			getZombies()[7] = (new Zombie("Cone Zombie"));
			getZombies()[8] = (new Zombie("Football Zombie"));
			getZombies()[9] = (new Zombie("Zombie"));
			getZombies()[10] = (new Zombie("Football Zombie"));
			
		}
		else if (levelNumber == 3) {
			zombies = new Zombie[13];
			getZombies()[0] = (new Zombie("Cone Zombie"));
			getZombies()[1] =  (new Zombie("Zombie"));
			getZombies()[2] = (new Zombie("Cone Zombie"));
			getZombies()[3] = (new Zombie("Football Zombie"));
			getZombies()[4] = (new Zombie("Zombie"));
			getZombies()[5] = (new Zombie("Zombie"));
			getZombies()[6] = (new Zombie("Flag Zombie"));
			getZombies()[7] = (new Zombie("Football Zombie"));
			getZombies()[8] = (new Zombie("Zombie"));
			getZombies()[9] = (new Zombie("Zombie"));
			getZombies()[10] = (new Zombie("Football Zombie"));
			getZombies()[11] = (new Zombie("Zombie"));
			getZombies()[12] = (new Zombie("Cone Zombie"));
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
