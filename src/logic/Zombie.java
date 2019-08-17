package logic;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import gui.GardenScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.scene.control.Button;


/**
 * Plant is an object with attributes inherited from GameCharacter.
 * Different types of Plants have different attributes set as default.
 */
public class Zombie extends GameCharacter{
	
	private double speed = 0.2; //How many pixels the zombie will move over everytime the timer to move zombie goes off
	private int row; //Row the zombie is on
	
	/**
	 * Initial position is set far away from the edge of the right side of the garden in order
	 * to time their entrance to the actual GUI of the garden
	 */
	private double position = 1650; //The X coordinate of the zombie. Will decrease with the timer
	private final int endOfGarden = 220;
	private ImageView zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//Zombieidle.gif")));
	private final int IMAGEHEIGHT = 100; //The size the zombie image will be
	private final int IMAGEWIDTH = 130;
	private boolean stopZombie = false;
	private int column; //Column the zombie is on
	private boolean isFrozen = false; //Checks whether the zombie is frozen or not
	
	
	/**
	 * Constructor sets default attributes depending on what the type is.
	 * Sets the zombieImage at the very right side of the garden
	 * Sets the y coordinate of the image according to the row it'll be in
	 *
	 * @param typeOfZombie  if typeOfPlant is any of the given characters specific attributes are set as default
	 * @throws FileNotFoundException
	 */
	public Zombie(String typeOfZombie, int rowNum) throws FileNotFoundException {
		super(typeOfZombie);
		if (typeOfZombie == "Cone Zombie") {
			setFirstChar('c');
			setHealth(350);
			setAttack(10);
			zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//ConeHead.gif")));
		}
		else if (typeOfZombie == "Flag Zombie") {
			setFirstChar('g');
			setHealth(250);
			setSpeed(0.25);
			setAttack(10);
			zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//FlagZombie.gif")));
		}
		else if (typeOfZombie == "Football Zombie") {
			setFirstChar('f');
			setHealth(500);
			setSpeed(0.33);
			setAttack(10);
			zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//Football.gif")));
		}
		else { //Just creates a normal zombie if there are any errors, its type would be a general Zombie
			setType("Zombie");
			setFirstChar('z');
			setHealth(200);
			setAttack(10);
		}
		if (rowNum >= 1 && rowNum <= 5) {
			setRow(rowNum);
		}
		else {
			setRow(3);
		}

		
	      zombieImage.setX(position);

	      if (row == 1) {
	    	  zombieImage.setY(215);
	      }
	      else if (row == 2) {
	    	  zombieImage.setY(310);
	      }

	      else if (row == 3){
	    	  zombieImage.setY(410);
	      }
	      else if (row == 4) {
	    	  zombieImage.setY(525);
	      }

	      else if (row == 5) {
	    	  zombieImage.setY(630);
	    	  
	      }
	      zombieImage.setFitHeight(IMAGEHEIGHT);
	      zombieImage.setFitWidth(IMAGEWIDTH);

	      //Setting the preserve ratio of the image view
	      zombieImage.setPreserveRatio(true);

	      startZombie();
	}
	

	/**
	 * Copy constructor that makes sure every attribute is copied
	 * 
	 * @param Zombie   c is the object Plant to be copied
	 * @throws Exception   FileNotFoundException
	 */
	public Zombie(Zombie c) throws FileNotFoundException {
		super(c);
		setFirstChar(c.getFirstChar());
		setSpeed(c.getSpeed());
		setRow(c.getRow());
		setPosition(c.getPosition());
		zombieImage = c.getZombieImage();
	}



	//Getters and setters
	
	public double getSpeed() {
		return speed;
	}
	public boolean isStopZombie() {
		return stopZombie;
	}

	public void setStopZombie(boolean stopZombie) {
		this.stopZombie = stopZombie;
	}
	
	public void setFrozen(boolean frozen) {
		this.isFrozen = frozen;
	}

	public int getRow() {
		return row;
	}
	//if it is an invalid negative number set speed to 45000.0
	public void setSpeed(double d) {
		if (d < 0) {
			this.speed = 0.2;
	}
		else {
			this.speed = d;
		}
	}

	public double getPosition() {
		return position;
	}
	public void setPosition(double d) {
		this.position = d;
	}

	public void addToPosition(int distance) {
		this.position += distance;
	}
	

	public ImageView getZombieImage() {
		return zombieImage;
	}
	
	
	public void setZombieImage(ImageView zombieImage) {
		this.zombieImage = zombieImage;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	
	public boolean isFrozen() {
		return isFrozen;
	}


	/**
	 * translates the zombie's position and GUI position using a timer if stopZombie is false
	 * zombie stops if stopZombie is true
	 * if zombie reaches 220 or below (house position) then a gameOver scene comes on
	 */
	public void startZombie() {
	stopZombie = false;
	Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        @Override
		        public void run() {
		            Platform.runLater(new Runnable() {
		                @Override
		                public void run() {
		                	position -= speed;
		                	columnNumber(position);
		                	zombieImage.setX(position);
		                	
		                	if (stopZombie == true) {
		                    	  timer.cancel();
			                       timer.purge();
			                       
		                      }
		                	
		                	if (getHealth() <= 0) {
		                		System.out.println("Dead " + getType());
		                		timer.cancel();
 		                        timer.purge();
		                	} 
		                	
		                	
		                	if (position <= endOfGarden) {
		                		System.out.println("GAMEOVER");
		                		GardenScene.setGameOverMessage(new Button("GAME OVER"));
		                		GardenScene.getFullImage().getChildren().add(GardenScene.getGameOverMessage());
		                		GardenScene.getGameOverMessage().setStyle("-fx-font-size: 75; -fx-background-color: transparent; -fx-font-weight: bold;");
		                		GardenScene.getGameOverMessage().setDisable(false);
		                		
		                		GardenScene.getGameOverMessage().setLayoutY(0);
		                		GardenScene.getGameOverMessage().setLayoutX(0);
		                		GardenScene.getGameOverMessage().setPrefSize(1220,720);
		                	
		                		timer.cancel();
		                       timer.purge();
		                       
		                      
		                	}
		                }
		                
		            });
		            
		        }
		    }, 0, 10);	
	}
	 
	
	/**
	 * sets column depending on which pixels/position the zombie is for better accuracy
	 */
	public void columnNumber(double position) {
		if (position > 250 && position <= 366) {
			this.column = 0;
		}
		else if (position > 366 && position <= 477) {
			this.column = 1;
		}
		else if (position > 477 && position <= 569) {
			this.column = 2;
		}
		else if (position > 569 && position <= 671) {
			this.column = 3;
		}
		else if (position > 671 && position <= 774) {
			this.column = 4;
		}
		else if (position > 774 && position <= 878) {
			this.column = 5;
		}
		else if (position > 878 && position <= 978) {
			this.column = 6;
		}
		else if (position > 978 && position <= 1074) {
			this.column = 7;
		}
		else if (position > 1074 && position <= 1170) {
			this.column = 8;
		}
		else {
			this.column = 9;
		}
	}
		

	public int getColumn() {
		return this.column;
	}

}
