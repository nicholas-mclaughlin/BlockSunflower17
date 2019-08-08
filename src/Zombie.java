import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Zombie extends GameCharacter{
	private double speed = 50000; //How many milliseconds it'll take for the zombie to go across the garden
	private int row; //Which row the zombie will start walking down from
	private double position = 1500; //1250 is the very right side of the garden
	private double position2 = 1500;
	private ImageView zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//Zombieidle.gif"))); //Original Zombie image


	/**
	 *
	 * @param typeOfZombie
	 * @throws FileNotFoundException
	 */
	public Zombie(String typeOfZombie, int rowNum) throws FileNotFoundException {
		super(typeOfZombie);
		if (typeOfZombie == "Cone Zombie") { // Sets attributes of zombie if it is conehead
			setFirstChar('c');
			setHealth(560);
			setAttack(100);
			zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//ConeHead.gif")));
		}
		else if (typeOfZombie == "Flag Zombie") { // Sets attributes of zombie if it is flag zombie
			setFirstChar('g');
			setHealth(200);
			setSpeed(45000);
			setAttack(100);
			zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//FlagZombie.gif")));
		}
		else if (typeOfZombie == "Football Zombie") { // Sets attributes of zombie if it is football
			setFirstChar('f');
			setHealth(1600);
			setSpeed(40000);
			setAttack(100);
			zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//Football.gif")));
		}
		else { //Just creates a normal zombie if there are any errors
			setFirstChar('z');
			setHealth(200);
			setAttack(100);
		}
		if (rowNum >= 1 && rowNum <= 5) {
			setRow(rowNum);
		}
		else {
			setRow(3);
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
	public int getRow() {
		return row;
	}
	public void setSpeed(double d) {
		this.speed = d;
	}

	public double getPosition() {
		return position;
	}
	public void setPosition(double d) {
		this.position = d;
	}

	/*public void multiplyPosition(int amount) {
		this.position = amount * position;
		this.speed = amount * speed;
	} */
	public void addToPosition(int distance) {
		this.speed += (speed / position) * distance;
		this.position += distance;
		this.position2 += distance;

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
		return "Zombie [type= " + getType() + ", row= " + row + ", position= " + position + ", getHealth()= " + getHealth() + "]";
	}
	
	public boolean zombieOnRow() {
		return position <= 1250;
	}
	
	public boolean zombieOnGarden() {
		return position <= 1251 && position >= 1248.5;
	}
	public boolean zombieAtHouse() {
		return position <= 250;
	}
	public boolean onColumn1() {
		return position > 250 && position <= 366;
	}
	public boolean onColumn2() {
		return position > 366 && position <= 477;
	}
	public boolean onColumn3() {
		return position > 477 && position <= 588;
	}
	public boolean onColumn4() {
		return position > 588 && position <= 699;
	}
	public boolean onColumn5() {
		return position > 699 && position <= 810;
	}
	public boolean onColumn6() {
		return position > 810 && position <= 921;
	}
	public boolean onColumn7() {
		return position > 921 && position <= 1032;
	}
	public boolean onColumn8() {
		return position > 1032 && position <= 1143;
	}
	public boolean onColumn9() {
		return position > 1143 && position <= 1254;
	}
	public int columnNumber() {
		if (position > 250 && position <= 366) {
			return 10;
		}
		else if (position > 366 && position <= 477) {
			return 1;
		}
		else if (position > 477 && position <= 588) {
			return 2;
		}
		else if (position > 588 && position <= 699) {
			return 3;
		}
		else if (position > 699 && position <= 810) {
			return 4;
		}
		else if (position > 810 && position <= 921) {
			return 5;
		}
		else if (position > 921 && position <= 1032) {
			return 6;
		}
		else if (position > 1032 && position <= 1143) {
			return 7;
		}
		else if (position > 1143 && position <= 1254) {
			return 8;
		}
		else {
			return 8;
		}
	}
	
	public boolean checkForPlant() {
		return Game.theGarden[row][columnNumber()] == "Sunflower" || Game.theGarden[row][columnNumber()] == "PeaShooter" || Game.theGarden[row][columnNumber()] == "Frozen PeaShooter" || Game.theGarden[row][columnNumber()] == "Wallnut" || Game.theGarden[row][columnNumber()] == "Cherry Bomb"; 
	}
	

	public void zombieTracker() throws FileNotFoundException {
		
		int delay = 0; //No delay
		int updateTime = 100; //Gets the location to update every second
		double gardenLength = position2 - 250;
		double j=  (speed / updateTime);
		Timer t = new Timer();
		t.schedule(new TimerTask() {
		            @Override
		             public void run() {
		            	/*if (onColumn1()) {
		            		Level.textGarden[row][0] = getFirstChar();
		            		Level.printGarden();
		            	}
		            	else if (onColumn2()) {
		            		Level.textGarden[row][1] = getFirstChar();
		            		Level.printGarden();
		            	}
		            	else if (onColumn3()) {
		            		Level.textGarden[row][2] = getFirstChar();
		            		Level.printGarden();
		            	}
		            	else if (onColumn4()) {
		            		Level.textGarden[row][3] = getFirstChar();
		            		Level.printGarden();
		            	}
		            	else if (onColumn5()) {
		            		Level.textGarden[row][4] = getFirstChar();
		            		Level.printGarden();
		            	}
		            	else if (onColumn6()) {
		            		Level.textGarden[row][5] = getFirstChar();
		            		Level.printGarden();
		            	}
		            	else if (onColumn7()) {
		            		Level.textGarden[row][6] = getFirstChar();
		            		Level.printGarden();
		            	}
		            	else if (onColumn8()) {
		            		Level.textGarden[row][7] = getFirstChar();
		            		Level.printGarden();
		            	}
		            	else if (onColumn9()) {
		            		Level.textGarden[row][8] = getFirstChar();
		            		Level.printGarden();
		            	} */
		            	System.out.println(checkForPlant());
		            	
		            	position -= gardenLength / j;
		            	
		             }
		 }, delay, updateTime);
	} 

	public static void main(String[] args) throws FileNotFoundException {
		Zombie z1 = new Zombie("Zombie", 2);
				//z1.zombieTracker();
	}
}
