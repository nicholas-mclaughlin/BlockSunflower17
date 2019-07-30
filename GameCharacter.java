//package logic;
public class GameCharacter {
	private String type = " ";
	private int health;
	private int attack;
	private char firstChar;
	
	public GameCharacter() {
		
	}
	
	public GameCharacter(String aType) {
		this.type = new String(aType);
	}
	
	public GameCharacter(GameCharacter aCharacter) {
		this.type = aCharacter.getType();
		this.health = aCharacter.health;
		this.attack = aCharacter.attack;
	}
	

	public int getHealth() {
		return health;
	}
	
	public int getAttack() {
		return attack;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public String getType() {
		return type;
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
	public void loseHealth(int damage) {
		health -= damage;
	}


}


