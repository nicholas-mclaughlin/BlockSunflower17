package logic;

/**
 * Player class is responsible for keeping track of the player's
 * money and plant.
 */
public class Player {
	/**
	 * The default start amount of money is 50 suns.
	 * plantHeld will keep track of which plant did the player pick
	 */
	private int money = 50; //Amount of suns held by player
	private static String plantHeld = "";
	
	//constructor
	public Player(int aMoney, String aPlant) {
		this.money = aMoney;
		plantHeld = aPlant;
	}

	public Player() {

	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * getPlantHeld returns the type of plant picked by the player
	 * by clicking the plant's button on the gardenscene
	 * 
	 * @return	new String object of the type of plant
	 */
	public String getPlantHeld() {
		return (plantHeld);
	}

	public static void setPlantHeld(String aPlant) {
		plantHeld = aPlant;
	}
	
	/*
	 * increases the money by 25 because each sun is worth 25 suns
	 */
	public void increaseMoney() {
		this.money += 25;
	}
	
	/*
	 * decreases the money by the amount which is the price
	 */
	public void decreaseMoney(int amount) {
		this.money -= amount;
	}
	
}
