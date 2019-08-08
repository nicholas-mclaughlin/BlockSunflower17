//package logic;

// This class has all the attributes the Plants and Zombies will inherit from.
public class GameCharacter {
	private String type = " "; //The type of character ie. PeaShooter
	private int health; //Health of character
	private int attack; //Attack of character
	private char firstChar; //The first character of the type of character ie. if PeaShooter than 'p'
	
	//Constructors
	
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
	
	public void setHealth(int health) {
		if (health > 0) {
			this.health = health;
		} else {
			System.out.println(getType() + " died.");
		}
	}
	//setting Attack, if it is an invalid negative number it is set to a default of 100
	public void setAttack(int attack) {
		if (attack >= 0 ) {
			this.attack = attack;
		} else {
			this.attack = 100;
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
	
	//Method to make the character lose health when attacked
	public void loseHealth(int damage) {
		setHealth(health - damage);
	}


}


