//package logic;

/**
 * Player class is responsible for keeping track of the player's
 * money and plant.
 */
public class Player {
	/**
	 * The default start amount of money is 50 suns.
	 * plantHeld will keep track of which plant did the player pick
	 */
//	private String name;
	private int money = 50;
	private String plantHeld;
	
	//constructor
	public Player(int aMoney, String aPlant) {
		this.money = aMoney;
		this.plantHeld = aPlant;
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
	 * by clicking the plant's button on the gardescene
	 * 
	 * @return	new String object of the type of plant
	 */
	public String getPlantHeld() {
		return new String(this.plantHeld);
	}

	//plantHeld setter
	public void setPlantHeld(String aPlant) {
		this.plantHeld = aPlant;
	}
	
	public void increaseMoney() {
		this.money += 25;
	}
	
	public void decreaseMoney(int amount) {
		
/**		KEEP IN MIND: price have to be lower or equal to your money
 * 		so somewhere in the eventhandlers for plant buttons must have:
*		if (Player.decreaseMoney(some amount) >= 0) {
*			<some method to buy the plant that also>
*		}
*/		this.money -= amount;
	}
	
/**	public Plants buyPlant(Plants aPlant) {
*		if (this.money >= Plants.getPrice()) {
*			
*		}
*	}
*/	
}
