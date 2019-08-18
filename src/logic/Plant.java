package logic;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import gui.GardenScene;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Plant is an object with attributes inherited from GameCharacter.
 * Different types of Plants have different attributes set as default.
 */
public class Plant extends GameCharacter{
	private int price;
	private int row;
	private int column;
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
	private int bulletTimer = 0; //Checks how long each bullet has been shot out for. Resets once it hits bulletTimeLimit
	private final int bulletTimeLimit = 1200; //How long each bullet can be shot out for
	private ImageView sunGIF = new ImageView(new Image(new FileInputStream("PlantImages//sun.gif")));
	private Button sunGif = new Button("",sunGIF);
	private static boolean sunflowerStillAlive = true;
	private boolean newImageSet = false;


	/**
	 * Copy constructor that makes sure every attribute is copied
	 * 
	 * @param Plant   aPlant is the object Plant to be copied
	 * @throws Exception   FileNotFoundException, NullPointerException, etc
	 */
	public Plant(Plant aPlant) throws Exception {
		super(aPlant);
		setPrice(aPlant.getPrice());
		setAttack(aPlant.getAttack());
		this.column = aPlant.getColumn();
		this.row = aPlant.getRow();
		setHealth(aPlant.getHealth());
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

	

	/**
	 * Constructor sets default attributes depending on what the type is.
	 * Last method sets plantHeld of Player class to an empty string.
	 * 
	 * @param String   if typeOfPlant is any of the given characters specific attributes are set as default
	 * 					on the type, but if type is not a given character in the game, attributes are set as 
	 * 					 javafx default values
	 */
	public Plant(String typeOfPlant) throws FileNotFoundException{
		super(typeOfPlant);

		if (typeOfPlant == "PeaShooter") {
			this.plantNotDestroyed = true;
			//hasImage = true;
			setFirstChar('P');
		    setAttack(20);
		    setHealth(200);
		    setPrice(100);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//pea-shooter.gif"))));
		    setBullet(new ImageView(new Image( new FileInputStream("PlantImages//pea-bullet.png"))));

		}
		else if (typeOfPlant == "Frozen PeaShooter") {
			this.plantNotDestroyed = true;
			setFirstChar('F');
			setAttack(12);
			setHealth(200);
		    setPrice(175);
		    setFreeze(true);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//frozen-pea.gif"))));
		    setBullet(new ImageView(new Image(new FileInputStream("PlantImages//frozen-pea-bullet.png"))));

		}

		else if (typeOfPlant == "Wallnut") {
			this.plantNotDestroyed = true;
			setFirstChar('W');
			setAttack(0);
			setPrice(50);
			setHealth(10000);
			setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//walnut_full_life.gif"))));

		}
		else if (typeOfPlant == "Potato Mine") {
			this.plantNotDestroyed = true;
			setFirstChar('M');
			setAttack(10000);
			setPrice(25);
			setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//potato-mine-active.gif"))));
			setPlantImage(plantImage);
			getPlantImage().setFitWidth(70);
			getPlantImage().setPreserveRatio(true);

		}
		else if (typeOfPlant == "Sunflower") {
			this.plantNotDestroyed = true;
		    setHealth(300);
		    setPrice(50);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//Sunflower.gif"))));
		}
		
		Player.setPlantHeld("");
	 }
	
	
	/*
	 * sets the ImageView of plant and its position on the scene
	 * if no ImageView has been set yet and if the Plant is not dead/destroyed
	 */
	public void setImage() {
    	if (getPlantImage() != null && hasImage == false && plantNotDestroyed) {
    		plantImage.setLayoutX(xPosition);
    		plantImage.setLayoutY(yPosition);
    		GardenScene.getFullImage().getChildren().addAll(plantImage);
    		hasImage = true;
    		
    	}
	}
	
	/**
	 * sets the ImageView of bullet and its position on the scene
	 * if no ImageView has been set yet and if the Plant is not dead/destroyed
	 */
	public void setBulletImage() {
		if (bullet != null && hasBullet == false && plantNotDestroyed) {
    		 bullet.setLayoutX(bulletXPosition);
    		 bullet.setLayoutY(bulletYPosition);
    				GardenScene.getFullImage().getChildren().addAll(bullet);
    				hasBullet = true;
    				moveBullet();
		}
	}
	
	/**
	 * translates a bullet's position using a timer
	 * 
	 * Shoots a new bullet after a certain amount of timer
	 * 
	 * Bullets stops at end of garden
	 */
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
				                		GardenScene.getFullImage().getChildren().remove(bullet);
				                	}
				                	if (bulletTimer >= (bulletTimeLimit - 1) && bulletTimer <= (bulletTimeLimit + 1)) {
				                		bulletXPosition = bulletStartPosition;
				                		GardenScene.getFullImage().getChildren().add(bullet);
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

	/**
	 * setRow sets row of plant with reference to the default gardenPlot clicked from
	 * where the method is called in gardenButtonHandler
	 * 
	 * @param Game
	 * @param String  coordinate must be a string of coordinate with format "<row>,<column>"
	 */
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

	/**
	 * setColumn sets row of plant with reference to the default gardenPlot clicked from
	 * where the method is called in gardenButtonHandler
	 * 
	 * @param Game
	 * @param String  coordinate must be a string of coordinate with format "<row>,<column>"
	 */
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

		
}



