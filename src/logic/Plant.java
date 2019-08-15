package logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import gui.GardenScene;
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
	private int row;
	private int column;
	public ImageView plantImage;
	public Rectangle plantRect;
	public ImageView bullet = null;
	public Rectangle bulletRect = null;
	public boolean freeze = false;
	private double xPosition;
	private double yPosition;
	private double bulletStartPosition;
	private double bulletXPosition;
	private double bulletYPosition;
	private ImageView sunGIF = new ImageView(new Image(new FileInputStream("PlantImages//sun.gif")));
	private Button sunGif = new Button("",sunGIF);

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
		this.price = aPlant.price;
		this.column = aPlant.getColumn();
		this.row = aPlant.getRow();
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
		    setPlantRect(getBounds(getPlantImage()));
		    plantRect.setFill(Color.TRANSPARENT);
		    //plantRect.setStroke(Color.BLACK);
		    plantRect.setStrokeWidth(2);

		    bullet = new ImageView(new Image( new FileInputStream("PlantImages//pea-bullet.png")));
		    
		    bulletRect = getBulletBounds(bullet);
			
		    bulletRect.setFill(Color.TRANSPARENT);
			bulletRect.setStroke(Color.BLACK);
		    bulletRect.setStrokeWidth(2);
		    
		    
		    
		    
		    /*
		    TranslateTransition translateTransition = new TranslateTransition();
			//How long the animation will take
			translateTransition.setDuration(Duration.millis(3000));
			translateTransition.setNode(bullet);
			//The displacement of the animation
			translateTransition.setByX(500);
			translateTransition.setCycleCount(1000);
			translateTransition.setAutoReverse(false);
			translateTransition.play();


			TranslateTransition translateTransition2 = new TranslateTransition();
			//How long the animation will take
		    translateTransition2.setDuration(Duration.millis(3000));
		    translateTransition2.setNode(bulletRect);
		    //The displacement of the animation
		    translateTransition2.setByX(500);
		    translateTransition2.setCycleCount(1000);
		    translateTransition2.setAutoReverse(false);
		    translateTransition2.play(); */

		}
		else if (typeOfPlant == "Frozen PeaShooter") {
			setFirstChar('F');
			setFrequency(10);
			setAttack(15);
			setHealth(200);
		    setPrice(175);
		    setFreeze(true);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//frozen-pea.gif"))));
		    setPlantRect(getBounds(getPlantImage()));
		    plantRect.setFill(Color.TRANSPARENT);
		    plantRect.setStroke(Color.BLACK);
		    plantRect.setStrokeWidth(2);
		  
		    
			/*TranslateTransition translateTransition = new TranslateTransition();
    	      //How long the animation will take
    	      translateTransition.setDuration(Duration.millis(3000));
    	      translateTransition.setNode(bullet);
    	      //The displacement of the animation
    	      translateTransition.setByX(500);
    	      translateTransition.setCycleCount(1000);
    	      translateTransition.setAutoReverse(false);
    	      translateTransition.play();
  			
  			
  			
  			TranslateTransition translateTransition2 = new TranslateTransition();
    	      //How long the animation will take
    	      translateTransition2.setDuration(Duration.millis(3000));
    	      translateTransition2.setNode(bulletRect);
    	      //The displacement of the animation
    	      translateTransition2.setByX(500);
    	      translateTransition2.setCycleCount(1000);
    	      translateTransition2.setAutoReverse(false);
    	      translateTransition2.play(); */
		}

		else if (typeOfPlant == "Wallnut") {
			setFirstChar('W');
			setFrequency(0);
			setAttack(0);
			setPrice(50);
			setHealth(1000);
			setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//walnut_full_life.gif"))));
			setPlantRect(getBounds(getPlantImage()));
		    plantRect.setFill(Color.TRANSPARENT);
		    //plantRect.setStroke(Color.BLACK);
		    plantRect.setStrokeWidth(2);
		    plantRect.setHeight(80);
		    plantRect.setWidth(70);
		}
		else if (typeOfPlant == "Potato Mine") {
			setFirstChar('M');
			setFrequency(10);
			setAttack(10000);
			setPrice(25);
			setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//potato-mine-active.gif"))));
			getPlantImage().setFitWidth(70);
			getPlantImage().setPreserveRatio(true);
			setPlantRect(getBounds(getPlantImage()));
		    plantRect.setFill(Color.TRANSPARENT);
		   // plantRect.setStroke(Color.BLACK);
		    plantRect.setStrokeWidth(2);
		    plantRect.setHeight(60);
		    plantRect.setWidth(63);
		}
		else if (typeOfPlant == "Sunflower") {
		    setHealth(300);
		    setPrice(50);
		    setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//Sunflower.gif"))));
		    setPlantRect(getBounds(getPlantImage()));
		    plantRect.setFill(Color.TRANSPARENT);
		    //plantRect.setStroke(Color.BLACK);
		    plantRect.setStrokeWidth(2);
		    plantRect.setHeight(80);
		    plantRect.setWidth(70);

		}
		
		Player.setPlantHeld("");
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

		        @Override
		        public void run() {
		            Platform.runLater(new Runnable() {
		                @Override
		                public void run() {
		                	if (plantImage != null) {
		                		plantImage.setLayoutX(xPosition);
		                		plantImage.setLayoutY(yPosition);
		                		plantRect.setLayoutX(xPosition);
		                		plantRect.setLayoutY(yPosition);
		                		 GardenScene.fullImage.getChildren().addAll(plantImage, plantRect);
		                		 }
		                		 if (bullet != null) {
		                		 bullet.setLayoutX(bulletXPosition);
		                		 bullet.setLayoutY(bulletYPosition);
		                		 bulletRect.setLayoutX(bulletXPosition);
		                		 bulletRect.setLayoutY(bulletYPosition);
		                		 
		                		 
		                		 GardenScene.fullImage.getChildren().addAll(bullet, bulletRect);
		                		 }
		                }
		            });
		        }
		    } 0);
		 
		 
		 
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

	public Rectangle getPlantRect() {
		return plantRect;
	}

	public void setPlantRect(Rectangle plantRect) {
		this.plantRect = plantRect;
	}

	public ImageView getBullet() {
		return bullet;
	}

	public void setBullet(ImageView bullet) {
		this.bullet = bullet;
	}

	public Rectangle getBulletRect() {
		return bulletRect;
	}

	public void setBulletRect(Rectangle bulletRect) {
		this.bulletRect = bulletRect;
	}

	public Rectangle getBounds(ImageView z) {
		return new Rectangle(z.getLayoutX(), z.getLayoutY(), 60, 65);
	}
	public Rectangle getBulletBounds(ImageView b) {
		return new Rectangle(b.getLayoutX(), b.getLayoutY(), 25, 25);
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

	public void moveBullet() {
		Timer timer = new Timer();
 		timer.schedule(new TimerTask() {
 		        @Override
 		        public void run() {
 		            Platform.runLater(new Runnable() {
 		                @Override
 		                public void run() {
 		                	bulletXPosition += 1;
 		                	/*
 		                	if (getHealth() <= 0) {
 				              timer.cancel();
		                       timer.purge();
 		                	}
 		                	*/
						
 				

 		                	}


 		            });

 		        }
 		    }, 0, 10);
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



