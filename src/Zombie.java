import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Zombie extends GameCharacter{
	private double speed = 33000; //How many milliseconds it'll take for the zombie to go across the garden
	private int row = (int)(Math.random() * 5 + 1); //Which row the zombie will start walking down from
	private int position = 1500; //1250 is the very right side of the garden
	private ImageView zombieImage = new ImageView(new Image(new FileInputStream("Zombieidle.gif"))); //Original Zombie image
	

	/**
	 * 
	 * @param typeOfZombie 
	 * @throws FileNotFoundException
	 */
	public Zombie(String typeOfZombie) throws FileNotFoundException {
		super(typeOfZombie);
		if (typeOfZombie == "Cone Zombie") { // Sets attributes of zombie if it is conehead
			setFirstChar('c');
			setHealth(560);
			setAttack(100);
			zombieImage = new ImageView(new Image(new FileInputStream("ConeHead.gif")));
		}
		else if (typeOfZombie == "Flag Zombie") { // Sets attributes of zombie if it is flag zombie
			setFirstChar('f');
			setHealth(200);
			setSpeed(30000);
			setAttack(100);
			zombieImage = new ImageView(new Image(new FileInputStream("FlagZombie.gif")));
		}
		else if (typeOfZombie == "Football Zombie") { // Sets attributes of zombie if it is football
			setFirstChar('F');
			setHealth(1600);
			setSpeed(27000);
			setAttack(100);
			zombieImage = new ImageView(new Image(new FileInputStream("Football.gif")));
		}
		else { //Just creates a normal zombie if there are any errors
			setFirstChar('z');
			setHealth(200);
			setAttack(100);
		}
		
		
	      zombieImage.setX(position); //Sets the image at the very right side of the garden
	      //Sets the y coordinate of the image according to the row itll be in
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
	    //Size of the zombie
	      zombieImage.setFitHeight(100); 
	      zombieImage.setFitWidth(130); 
	      
	      //Setting the preserve ratio of the image view 
	      zombieImage.setPreserveRatio(true);
	      
	      //Creates the animation of the zombie
	      TranslateTransition translateTransition = new TranslateTransition(); 
	      //How long the animation will take
	      translateTransition.setDuration(Duration.millis(speed));
	      translateTransition.setNode(zombieImage); 
	      //The displacement of the animation
	      translateTransition.setByX(-position + 250); 
	      translateTransition.setCycleCount(1); 
	      translateTransition.setAutoReverse(false); 
	      translateTransition.play();  
	}
	
	
	//Gets the row that the zombie spawns from to be random
		public void generateRandomRow() {
			row = (int)(Math.random() * 5 + 1);
		}
	
		//Getters and setters
	public double getSpeed() {
		return speed;
	}
	public int getRow() {
		return row;
	}
	public void setSpeed(double d) {
		this.speed = d;
	}
	
	public double getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	/*public void multiplyPosition(int amount) {
		this.position = amount * position;
		this.speed = amount * speed;
	} */
	public void addToPosition(int distance) {
		this.speed += (speed / position) * distance;
		this.position += distance;
		
	}
    
    
	public ImageView getZombieImage() {
		return zombieImage;
	}
	
	public ImageView newZombieImage() {
		zombieImage.setX(position); //Sets the image at the very right side of the garden
	      //Sets the y coordinate of the image according to the row itll be in
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
	    //Size of the zombie
	      zombieImage.setFitHeight(100); 
	      zombieImage.setFitWidth(130); 
	      
	      //Setting the preserve ratio of the image view 
	      zombieImage.setPreserveRatio(true);
	      
	      //Creates the animation of the zombie
	      TranslateTransition translateTransition = new TranslateTransition(); 
	      //How long the animation will take
	      translateTransition.setDuration(Duration.millis(speed));
	      translateTransition.setNode(zombieImage); 
	      //The displacement of the animation
	      translateTransition.setByX(-position + 250); 
	      translateTransition.setCycleCount(1); 
	      translateTransition.setAutoReverse(false); 
	      translateTransition.play(); 
	      return zombieImage;
	}

	public void setZombieImage(ImageView zombieImage) {
		this.zombieImage = zombieImage;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "Zombie [type=" + getType() + ", row=" + row + ", position=" + position + ", getHealth()=" + getHealth()
				+ " speed= " + speed + "]";
	}
	
	
	
}
