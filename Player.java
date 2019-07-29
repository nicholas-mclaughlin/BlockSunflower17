//package logic;

public class Player {
//	private String name;
	private int money = 50;
	private String plantHeld;
	
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

	public String getPlantHeld() {
		return this.plantHeld;
	}

	public void setPlantHeld(String aPlant) {
		this.plantHeld = aPlant;
		System.out.println(getPlantHeld());
	}
	
	public void increaseMoney(int amount) {
		this.money += amount;
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
