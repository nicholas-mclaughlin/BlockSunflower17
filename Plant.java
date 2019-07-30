//package logic;

public class Plant extends GameCharacter {
	private int price;
	private int frequency; //How often a plant will create something (ie: a pea, sun, etc.)
	//private int row;
	//private int column;
	
	public Plant(Plant aPlant) {
		super(aPlant);
		this.price = aPlant.price;
		this.row = aPlant.price;
		this.column = aPlant.price;
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
/*	public String getLocation(){
		return "(" + getRow() + ", " + getColumn() + ")";
	}
	
	public void setRow(int row){
		this.row = row;
	}
	public void setColumn(int column){
		this.column = column;
	}
*/	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
/*	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return "Plants [price=" + getPrice() + ", frequency=" + getFrequency() + ", row=" + getRow() + ", column=" + getColumn()
				+ ", getHealth()=" + getHealth() + ", getAttack()=" + getAttack() + ", getType()=" + getType()
				+ ", getFirstChar()=" + getFirstChar() +  "]";
	}
*/	
	
}
