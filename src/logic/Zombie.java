package logic;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import gui.GardenScene;
import javafx.scene.shape.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Zombie extends GameCharacter{
	private double speed = 70000; //How many milliseconds it'll take for the zombie to go across the garden
	private double speed2 = 70000;
	private int row; //Which row the zombie will start walking down from
	private double position = 1500; //1250 is the very right side of the garden
	private double position2 = 1500;
	private int houseLength = 250;
	public ImageView zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//Zombieidle.gif"))); //Original Zombie image
	private int deathTime = 0;
	private int IMAGEHEIGHT = 100;
	private int IMAGEWIDTH = 130;
	private boolean stopZombie = false;

	private Rectangle rect = getBounds(zombieImage);



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

			setAttack(100);
			zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//FlagZombie.gif")));
		}
		else if (typeOfZombie == "Football Zombie") { // Sets attributes of zombie if it is football
			setFirstChar('f');
			setHealth(1600);

			setAttack(100);
			zombieImage = new ImageView(new Image(new FileInputStream("ZombieImages//Football.gif")));
		}
		else { //Just creates a normal zombie if there are any errors, its type would be a general Zombie
			setType("Zombie");
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

		rect = getBounds(zombieImage);
	      zombieImage.setX(position); //Sets the image at the very right side of the garden
	      //Sets the y coordinate of the image according to the row itll be in
	      rect.setX(position);
	      if (row == 1) {
	    	  zombieImage.setY(215);
	    	  rect.setY(215);
	      }
	      else if (row == 2) {
	    	  zombieImage.setY(310);
	    	  rect.setY(310);
	      }

	      else if (row == 3){
	    	  zombieImage.setY(410);
	    	  rect.setY(410);
	      }
	      else if (row == 4) {
	    	  zombieImage.setY(525);
	    	  rect.setY(525);
	      }

	      else if (row == 5) {
	    	  zombieImage.setY(630);
	    	  rect.setY(630);

	      }
	    //Size of the zombie
	      zombieImage.setFitHeight(IMAGEHEIGHT);
	      zombieImage.setFitWidth(IMAGEWIDTH);

	      //Setting the preserve ratio of the image view
	      zombieImage.setPreserveRatio(true);


	      rect.setFill(Color.TRANSPARENT);
	      //rect.setStroke(Color.BLACK);
	      rect.setStrokeWidth(2);

	      startZombie();


	}

	public Rectangle getBounds(ImageView z) {
		return new Rectangle( z.getLayoutX(), z.getLayoutY(), 80, 100);
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
	public boolean isStopZombie() {
		return stopZombie;
	}

	public void setStopZombie(boolean stopZombie) {
		this.stopZombie = stopZombie;
	}

	public int getRow() {
		return row;
	}
	//if it is an invalid negative number set speed to 45000.0
	public void setSpeed(double d) {
		if (d<0) {
			this.speed = 50000.0;
		} else {
			this.speed = d;
		}
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



	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
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


	public void startZombie() {
	stopZombie = false;
	Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        @Override
		        public void run() {
		            Platform.runLater(new Runnable() {
		                @Override
		                public void run() {
		                	position -= 0.15;
		                	zombieImage.setX(position);
		                	rect.setX(position);

		                	if (stopZombie == true) {
		                    	  timer.cancel();
			                       timer.purge();
			                       startZombie();
		                      }

		                	if (getHealth() <= 0) {
		                		System.out.println("Dead Zombie");
		                		//zombieImage = null;

		                		timer.cancel();
 		                       timer.purge();
 		                       
		                	}


		                	if (position <= 220) {
		                		System.out.println("GAMEOVER");
		                		//creates a new gameover mesage to go on top everytime a zombie enters the house so no suns are clickable
								
		                		Game.gameOver(true, "GAME OVER");
		                		timer.cancel();
		                       timer.purge();


		                	}
		                }

		            });

		        }
		    }, 3000, 10);
}


	//Methods not used at the moment but could be useful in future
		/*
	public boolean zombieOnRow(int rowNum) {
		return row == rowNum && position <= 1250;
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
			return 0;
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
	} */



}
