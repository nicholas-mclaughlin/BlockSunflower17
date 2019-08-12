package logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plant extends GameCharacter{
	private int price;
	private int frequency; //How often a plant will create something (ie: a pea, sun, etc.)
	private int row;
	private int column;
	public ImageView plantImage;

	//constructor
	public Plant(Plant aPlant) {
		super(aPlant);
		this.price = aPlant.price;
	}

	public ImageView getPlantImage() {
		return plantImage;
	}

	public void setPlantImage(ImageView plantImage) {
		this.plantImage = plantImage;
	} 

	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	//sets default attributes of certain types of plants
	public Plant(String typeOfPlant) throws FileNotFoundException{
		super(typeOfPlant);
		
		if (typeOfPlant == "PeaShooter") {
			setFirstChar('P');
			setFrequency(10);
		    setAttack(20);
		    setHealth(200);
		    setPrice(100);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//pea-shooter.gif"))));
		   
		}
		else if (typeOfPlant == "Frozen PeaShooter") {
			setFirstChar('F');
			setFrequency(10);
			setAttack(15);
			setHealth(200);
		    setPrice(175);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//frozen-pea.gif"))));
		}
		else if (typeOfPlant == "Wallnut") {
			setFirstChar('W');
			setFrequency(0);
			setAttack(0);
			setPrice(50);
			setHealth(1000);
			setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//walnut_full_life.gif"))));
		}
		else if (typeOfPlant == "Potato Mine") {
			setFirstChar('M');
			setFrequency(10);
			setAttack(10000);
			setPrice(25);
			setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//potato-mine-active.gif"))));
			getPlantImage().setFitWidth(70);
			getPlantImage().setPreserveRatio(true);
		}
		else if (typeOfPlant == "Sunflower") {
		    setHealth(300);
		    setPrice(50);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//Sunflower.gif"))));
		 
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

	//sets row of plant with reference to default gardenPlots
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

	//sets column of plant with reference to default gardenPlots
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

}
