//package logic;

public class Plant extends GameCharacter{
	private int price;
	private int frequency; //How often a plant will create something (ie: a pea, sun, etc.)
	private int row;
	private int column;
	
	public Plant(Plant aPlant) {
		super(aPlant);
		this.price = aPlant.price;
	}
	
	
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public Plant(String typeOfPlant) {
		super(typeOfPlant);
		if (typeOfPlant == "PeaShooter") {
			setFirstChar('P');
			setFrequency(10);
		    setAttack(20);
		    setHealth(200);
		    setPrice(100);
		}
		else if (typeOfPlant == "Frozen PeaShooter") {
			setFirstChar('F');
			setFrequency(10);
			setAttack(15);
			setHealth(200);
		    setPrice(175);
		}
		else if (typeOfPlant == "Wallnut") {
			setFirstChar('W');
			setFrequency(0);
			setAttack(0);
			setPrice(50);
			setHealth(1000);
		}
		else if (typeOfPlant == "Cherry Bomb") {
			setFirstChar('C');
			setFrequency(10);
			setAttack(10000);
			setPrice(150);
			
		}
		else if (typeOfPlant == "Sunflower") {
		    setHealth(300);
		    setPrice(50);
		}

	}

	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	
	public void attack(Zombie aZombie) {
		if (aZombie.getRow() == this.getRow()) {
			aZombie.loseHealth(this.getAttack());
		}
	}
	
	public void setRow(Game aGame, String coordinate) {
		int theRow = 0;
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				if (aGame.getGardenPlots()[row][column].getType().equals(coordinate)) {
					theRow = row;
					break;
				}
			}
		} this.row = theRow;
	}
	
	public void setColumn(Game aGame, String coordinate) {
		int theColumn = 0;
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 9; column++) {
				if (aGame.getGardenPlots()[row][column].getType().equals(coordinate)) {
					theColumn = column;
					break;
				}
			}
		} this.column = theColumn;
	}
	
	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	//Peashooter checks if there is a zombie on the garden to shoot at
	public boolean startShooting(Zombie zombie) {
		return row == zombie.getRow() && zombie.zombieOnRow();
	}
	
}
