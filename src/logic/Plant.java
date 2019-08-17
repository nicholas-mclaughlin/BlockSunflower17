package logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import gui.GardenScene;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Plant extends GameCharacter{
	private int price;
	private int frequency; //How often a plant will create something (ie: a pea, sun, etc.)
	private int row; // Row of plant
	private int column; //Column of plant
	private ImageView plantImage; 
	private ImageView bullet = null;
	private boolean freeze = false; //Whether the bullet will freeze the zombie
	private boolean plantNotDestroyed = true; //Used to check if the plant is alive or not
	private boolean hasImage = false;
	private boolean hasBullet = false;
	
	private double xPosition; //X coordinate of the plant
	private double yPosition; //Y coordinate of the plant
	private double bulletStartPosition; //Where the PeaShooters mouth is
	private double bulletXPosition; //The x coordinate of the bullet 
	private double bulletYPosition; //The x coordinate of the bullet
	private int bulletTimer = 0;
	private ImageView sunGIF = new ImageView(new Image(new FileInputStream("PlantImages//sun.gif")));
	private Button sunGif = new Button("",sunGIF);
	private static boolean sunflowerStillAlive = true;
	private boolean newImageSet = false;
	//public static int rowPosition;
	//public static int coloumnPosition;
	
	
	
	
	
	public boolean isNotDestroyed() {
		return plantNotDestroyed;
	}


	public boolean isNewImageSet() {
		return newImageSet;
	}


	public void setNewImageSet(boolean newImageSet) {
		this.newImageSet = newImageSet;
	}


	public int getBulletTimer() {
		return bulletTimer;
	}

	public void setBulletTimer(int bulletTimer) {
		this.bulletTimer = bulletTimer;
	}

	public static boolean isSunflowerStillAlive() {
		return sunflowerStillAlive;
	}

	public static void setSunflowerStillAlive(boolean sunflowerStillAlive) {
		Plant.sunflowerStillAlive = sunflowerStillAlive;
	}

	public void setNotDestroyed(boolean bool) {
		this.plantNotDestroyed = bool;
	}
	
	
	public double getxPosition() {
		return xPosition;
	}



	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}



	public double getyPosition() {
		return yPosition;
	}



	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}
	
	
	
	public double getBulletXPosition() {
		return bulletXPosition;
	}



	public void setBulletXPosition(double bulletXPosition) {
		this.bulletXPosition = bulletXPosition;
	}



	public double getBulletYPosition() {
		return bulletYPosition;
	}



	public void setBulletYPosition(double bulletYPosition) {
		this.bulletYPosition = bulletYPosition;
	}
	
	



	public double getBulletStartPosition() {
		return bulletStartPosition;
	}



	public void setBulletStartPosition(double bulletStartPosition) {
		this.bulletStartPosition = bulletStartPosition;
	}

	//constructor
	public Plant(Plant aPlant) throws Exception {
		super(aPlant);
		setPrice(aPlant.getPrice());
		setAttack(aPlant.getAttack());
		this.column = aPlant.getColumn();
		this.row = aPlant.getRow();
		setxPosition(aPlant.getxPosition());
		setyPosition(aPlant.getyPosition());
		setBulletXPosition(aPlant.getBulletXPosition());
		setBulletYPosition(aPlant.getBulletYPosition());
		setBulletStartPosition(aPlant.getBulletStartPosition());
		setPlantImage(aPlant.getPlantImage());
		setBullet(aPlant.getBullet());
		this.hasImage = aPlant.hasImage;
		this.hasBullet = aPlant.hasBullet;
		this.plantNotDestroyed = aPlant.plantNotDestroyed;
		this.bullet = aPlant.bullet;
		this.freeze = aPlant.freeze;
		this.newImageSet = aPlant.newImageSet;
	}

	

	//sets default attributes of certain types of plants
	public Plant(String typeOfPlant) throws FileNotFoundException{
		super(typeOfPlant);

		if (typeOfPlant == "PeaShooter") {
			this.plantNotDestroyed = true;
			//hasImage = true;
			setFirstChar('P');
			setFrequency(10);
		    setAttack(20);
		    setHealth(200);
		    setPrice(100);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//pea-shooter.gif"))));


		    setBullet(new ImageView(new Image( new FileInputStream("PlantImages//pea-bullet.png"))));
		    /*
		    bulletRect = getBulletBounds(bullet);
			
		    bulletRect.setFill(Color.TRANSPARENT);
			bulletRect.setStroke(Color.BLACK);
		    bulletRect.setStrokeWidth(2);
		    */

		}
		else if (typeOfPlant == "Frozen PeaShooter") {
			this.plantNotDestroyed = true;
			//hasImage = true;
			setFirstChar('F');
			setFrequency(10);
			setAttack(12);
			setHealth(200);
		    setPrice(175);
		    setFreeze(true);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//frozen-pea.gif"))));
		    setBullet(new ImageView(new Image(new FileInputStream("PlantImages//frozen-pea-bullet.png"))));

		  
		}

		else if (typeOfPlant == "Wallnut") {
			this.plantNotDestroyed = true;
			//hasImage = true;
			setFirstChar('W');
			setFrequency(0);
			setAttack(0);
			setPrice(50);
			setHealth(10000);
			setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//walnut_full_life.gif"))));

		}
		else if (typeOfPlant == "Potato Mine") {
			this.plantNotDestroyed = true;
			//hasImage = true;
			setFirstChar('M');
			setFrequency(10);
			setAttack(10000);
			setPrice(25);
			setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//potato-mine-active.gif"))));
			setPlantImage(plantImage);
			getPlantImage().setFitWidth(70);
			getPlantImage().setPreserveRatio(true);

		}
		else if (typeOfPlant == "Sunflower") {
			this.plantNotDestroyed = true;
			//hasImage = true;
		    setHealth(300);
		    setPrice(50);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//Sunflower.gif"))));
		}
		
		Player.setPlantHeld("");
		
		//setImage();
	 }
		 
	public void setImage() {
             	
                	if (getPlantImage() != null && hasImage == false && plantNotDestroyed) {
                		plantImage.setLayoutX(xPosition);
                		plantImage.setLayoutY(yPosition);
                		GardenScene.fullImage.getChildren().addAll(plantImage);
                		hasImage = true;
                		
                	}
	}
	
	public void setBulletImage() {
		if (bullet != null && hasBullet == false && plantNotDestroyed) {
    		 bullet.setLayoutX(bulletXPosition);
    		 bullet.setLayoutY(bulletYPosition);
    				GardenScene.fullImage.getChildren().addAll(bullet);
    				hasBullet = true;
    				moveBullet();
		}
	}
	
	public void moveBullet() {
		if (bullet != null && plantNotDestroyed) {
			Timer timer2 = new Timer();
				timer2.schedule(new TimerTask() {
				        @Override
				        public void run() {
				            Platform.runLater(new Runnable() {
				                @Override
				                public void run() {
				                	bulletTimer += 1;
				                	if (bulletXPosition <= 1200) {
				                		bulletXPosition += 0.75;
				                		
				                	}
				                	else {
				                		GardenScene.fullImage.getChildren().remove(bullet);
				                	}
				                	/*
				                	else {
				                		bulletXPosition = bulletStartPosition;
				                	}
				                	*/
				                	if (bulletTimer >= 1199 && bulletTimer <= 1201) {
				                		bulletXPosition = bulletStartPosition;
				                		GardenScene.fullImage.getChildren().add(bullet);
				                		bulletTimer = 0;
				                	}
				                	//System.out.println(bulletXPosition);
				                	bullet.setLayoutX(bulletXPosition);
				                	if (hasBullet == false) {
				                		timer2.cancel();
					                     timer2.purge();
				                	}
				                	
				                	
				                	}


				            });

				        }
				    }, 0, 1); 
		}
	}
                

	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	
	public void addToBulletTimer() {
		bulletTimer += 1;
	}
	public void attack(Zombie aZombie) {
		if (aZombie.getRow() == this.getRow()) {
			aZombie.loseHealth(this.getAttack());
		}
	}

	//sets row of plant with reference to default gardenPlots
	public void setRow(Game aGame, String coordinate) throws Exception {
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
	public void setColumn(Game aGame, String coordinate) throws Exception {
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

	
	
	public boolean isFreeze() {
		return freeze;
	}



	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}



	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public ImageView getBullet() {
		return bullet;
	}

	public void setBullet(ImageView bullet) {
		this.bullet = bullet;
	}

	
	public Button getSunButton() {
		//sets the background to transparent so only the sun image is seen.
		sunGif.setStyle("-fx-background-color: transparent;");
		return sunGif;
	}

	public void setSunButton(Button sunGif) {
		this.sunGif = sunGif;
	}

	public ImageView getPlantImage() {
		return this.plantImage;
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
	

	public boolean isHasImage() {
		return hasImage;
	}

	public void setHasImage(boolean hasImage) {
		this.hasImage = hasImage;
	}

	public boolean isHasBullet() {
		return hasBullet;
	}

	public void setHasBullet(boolean hasBullet) {
		this.hasBullet = hasBullet;
	}

	

/*	public void checkForZombie(game) {
		Zombie zombie = game.level
		Timer timer = new Timer();
 		timer.schedule(new TimerTask() {
 		        @Override
 		        public void run() {
 		            Platform.runLater(new Runnable() {
 		                @Override
 		                public void run() {
 		                	if (p.getxPosition() == z.getPosition()){
 		                		 System.out.println("Colliding");
 		                		 z.setStopZombie(true);
 				              timer.cancel();
		                       timer.purge();

 		                	}
 		                }

 		            });

 		        }
 		    }, 0, 10);
	}
*/
	

		
}



