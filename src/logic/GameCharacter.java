package logic;

/*
 * Class that has all the attributes that all character will inherit from
 */
public class GameCharacter {
	/*
	 * attributes child classes will inherit
	 */
	private String type = " ";
	private int health;
	private int attack;
	private char firstChar; //The first character of the type of character ie. if PeaShooter than 'p'

	
	public GameCharacter(String aType) {
		this.type = new String(aType);
	}
	
	public GameCharacter(GameCharacter aCharacter) {
		this.type = aCharacter.getType();
		this.health = aCharacter.health;
		this.attack = aCharacter.attack;
	}
	
	
	
	//Getters and setters
	public int getHealth() {
		return health;
	}
	
	public int getAttack() {
		return attack;
	}
	
	//health has to be positive
	public void setHealth(int health) {
		if (health > 0) {
			this.health = health;
		}
	}
	/*
	 * setting Attack, if it is an invalid negative number it is set to a default of 100
	 */
	public void setAttack(int attack) {
		if (attack >= 0 ) {
			this.attack = attack;
		}
	}
	public String getType() {
		return new String(type);
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public char getFirstChar() {
		return firstChar;
	}
	public void setFirstChar(char firstChar) {
		this.firstChar = firstChar;
	}
	
	/*
	 * Method to make the character lose health when attacked
	 */
	public void loseHealth(int damage) {
		health -= damage;
	}
	
	static void deadCharacter(GameCharacter c) {
		if (c.health <= 0) {
			c = null;
		}
	}


}


