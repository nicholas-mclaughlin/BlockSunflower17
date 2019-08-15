package gui;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import drivers.Session;
import handlers.GardenButtonHandler;
import handlers.PlantButtonHandler;
import handlers.SunButtonHandler;

import java.io.File;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.Game;
import logic.GameCharacter;
import logic.Level;
import logic.Plant;
import logic.Player;
//import logic.Sun;
import logic.Zombie;

/**
 * GardenScene is the second scene to appear after the 'start' button
 * is clicked on the first scene (Menu class).
 *
 */
public class GardenScene extends BaseScene {

	/*
	 * gardenButtons will be the arraylist of buttons for the garden plots GUI
	 * MAXSLOTS, LENGTH, WIDTH are all constant variables for determining length
	 * of window and garden buttons.
	 */
	private Button[] gardenButtons;
	private final int MAXSLOTS = 45;
	private final int LENGTH = 1220;
	private final int WIDTH = 720;
	private int levelNum;
	public static Button sunCounter = new Button();
	private static MediaPlayer mediaPlayer;
	Rectangle home = new Rectangle(1100, 225, 100, 500);

	static StackPane root = new StackPane();
	public static Pane fullImage = new Pane(root);
	//creates the error message that the player has to buy a plant before clicking on the garden buttons
	public static Button errorMessage = new Button("Buy a plant first!");
	private boolean gameOver = false;
	public static Button gameOverMessage = new Button("");

	//constructor
	public GardenScene(Session aSession, int levelNum) {
		super(aSession);
		this.levelNum = levelNum;
	}



	//setup actual drawing in window
	@Override
	public void setup() throws Exception{

	//initialize music in-game
	String grasswalk = "MenuImages//grasswalk.mp3";
	Media hit = new Media(new File(grasswalk).toURI().toString());
	mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	mediaPlayer.play();


	//create garden image and garden image size to fit the buttons and scene
	ImageView Garden = new ImageView(new Image(new FileInputStream("PlantImages//Garden.PNG")));
	Garden.setFitHeight(800);
	Garden.setFitWidth(1275);

	/**
	 * Initialize game and arguments needed for game
	 * @param Player	player of the session being run
	 * @param String[][]	gardenPlot is the logic version of the gardenplot buttons;
	 * 			it keeps track of where a plant and/or zombie is.
	 */
	Player player = new Player();
	Plant[][] gardenPlot = {{new Plant("0,0"), new Plant("0,1"), new Plant("0,2"), new Plant("0,3"),
		new Plant("0,4"),new Plant("0,5"), new Plant("0,6"), new Plant("0,7"), new Plant("0,8")},
		{new Plant("1,0"), new Plant("1,1"), new Plant("1,2"), new Plant("1,3"), new Plant("1,4"), new Plant("1,5"),
		new Plant("1,6"), new Plant("1,7"), new Plant("1,8")}, {new Plant("2,0"), new Plant("2,1"),
		new Plant("2,2"), new Plant("2,3"), new Plant("2,4"), new Plant("2,5"), new Plant("2,6"), new Plant("2,7"),
		new Plant("2,8")}, {new Plant("3,0"), new Plant("3,1"), new Plant("3,2"), new Plant("3,3"),
		new Plant("3,4"), new Plant("3,5"), new Plant("3,6"), new Plant("3,7"), new Plant("3,8")},
		{new Plant("4,0"), new Plant("4,1"), new Plant("4,2"), new Plant("4,3"), new Plant("4,4"), new Plant("4,5"),
			new Plant("4,6"), new Plant("4,7"), new Plant("4,8")}};
	Game game = new Game(player, gardenPlot);
	
	//setup stacks in stackpane
	root.getChildren().add(Garden); //first stack
	root.getChildren().add(gardenButtons(game.getPlayer(), game)); //second stack

	
	
	//fullImage.getChildren().add(home);
	home.setFill(Color.BLUE);

	//the time for the first sun to appear in milliseconds
		int timeBetweenSuns = 5000;
		//adds the suns in a for loop
		for (int i = 0; i<=30; i++) {
			Button sunButton = new Plant("").getSunButton();
			sunButton.setText("sun button");
			sunButton.setFont(new Font(0));
			sunButton.setOnAction(new SunButtonHandler(game.getPlayer()));
			//timer to add the suns after timeBetweenSuns seconds
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
			        @Override
			        public void run() {
			            Platform.runLater(new Runnable() {
			                @Override
			                public void run() {
			                	fullImage.getChildren().add(sunButton);
			                }
			            });

			        }
			    }, timeBetweenSuns);
			//Sets the random position of suns by calling the randomizing methods
			sunButton.setLayoutX(generateRandomX());
			sunButton.setLayoutY(generateRandomY());
			//Increases the time so all suns don't appear at the same time.
			//The time between each sun appearance is 5 seconds.
			timeBetweenSuns +=5000;
		}



	//level of the game (1, 2, or 3)
	Level level = new Level(this.levelNum);
	ArrayList<Zombie> zombies = level.getZombies();

		for (int i = 0; i < zombies.size(); i++) {
			Zombie z = zombies.get(i);
			//fullImage.getChildren().add(z.newZombieImage());
			//fullImage.getChildren().add(z.newRectangle());
			//z.setRect(getBounds(z.getZombieImage()));
			//createZombieTransition(z) ;

			fullImage.getChildren().addAll(z.getZombieImage(), z.getRect());

			//checkCollision(z.getRect(), home);
			game.zombieTracker(z);
			System.out.println(game.rowsToString());
		}
		
		//while (gameOver != true) {
			
		//}


	/*Level level1 = new Level(1);
	 root.getChildren().addAll(level1.getZombies()[0].getZombieImage(), level1.getZombies()[0].getRect()); */

	//adds the error message but sets it up to not be visible
	fullImage.getChildren().add(errorMessage);
	errorMessage.setStyle("-fx-opacity: 0.0;");



	


	Scene scene = new Scene(fullImage, LENGTH, WIDTH);
	setScene(scene);
	display();
	
	checkAll(game, level);
	//checkAll(game, level);
	
	/*Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        @Override
		        public void run() {
		            Platform.runLater(new Runnable() {
		                @Override
		                public void run() {
		                	for(int i = 0; i<5; i++)
		                	{
		                	    for(int j = 0; j<9; j++)
		                	    {

		                	        if (game.getPlant(i, j).getType() == "Wallnut") {
		                	        	for (int z = 0; z < level.zombies.length; z++) {
		                	        		checkCollision(game.getPlant(i, j).getPlantRect(), level.getZombies()[z].getRect());
		                	        	}
		                	        }

		                	    }
		                	}


				}

		                	}


		            });

		        }
		    }, 0, 10); */
	/*for(int i = 0; i<5; i++)
	{
	    for(int j = 0; j<9; j++)
	    {

	        if (game.getPlant(i, j).getType() == "Wallnut") {
	        	for (int z = 0; z < level.zombies.length; z++) {
	        		checkCollision(game.getPlant(i, j).getPlantRect(), level.getZombies()[z].getRect());
	        	}
	        }

	    }
	} */

	}


	//creates random X and Y positions for the suns to appear in and returns these values
		public int generateRandomY() {
			int randomY = (int)(Math.random() * (700));
			return randomY;
		}
		public int generateRandomX() {
			int randomX = (int)(Math.random()*(1220));
			return randomX;
		}

		//creates the sunCounter as static to be able to use .setText and change the money displayed
		public static Button getSunCounter(Player aPlayer) throws Exception {
			sunCounter.setText("  " + aPlayer.getMoney());
			sunCounter.setStyle("-fx-background-image: url('/gui/pvzsun.png')");
			sunCounter.setPrefSize(170,  70);
			sunCounter.setFont(new Font("Arial Bold", 38));
			return sunCounter;
		}

	/**
	 * gardenButtons node contains all the buttons on the gardenScene (actual gameplay)
	 * such as the plant buttons and gardenplotbuttons.
	 * It has its own root and will return it to be added to the setup of the scene.
	 *
	 * @param Player	aPlayer is the player of the game
	 * @param Game	aGame is the game that was run (levels perhaps?)
	 * @return	returns second stack; buttons
	 * @throws Exception
	 */
	public Node gardenButtons(Player aPlayer, Game aGame) throws Exception{

		//root is the inner root within the scene.
		VBox root = new VBox();

		//box will be the layout manager for the plant buttons.
		HBox box = new HBox();

		//create plant buttons and attach handlers
		for (int column = 0; column <5; ++column){
			ImageView plant = new ImageView(new Image(new FileInputStream("PlantImages//Plant"+ column + ".jpg")));
			Button plantbuttons = new Button("p"+ column, plant);
			plantbuttons.setFont(new Font(0));
			//made plant buttons transparent so the visual of 'clicking' them isn't seen when the player did not buy a plant
			plantbuttons.setStyle("-fx-background-color: transparent;");
			plantbuttons.setOnAction(new PlantButtonHandler(aPlayer));

			box.getChildren().add(column, plantbuttons);
		}


		/**
		 * Importing the sun image and setting setting the counter with the getMoney() form the player class that controls changes in money.
		 */
		//adds the sunCounter to the plant buttons box
		box.getChildren().add(getSunCounter(aPlayer));

		//create empty array for garden buttons
		gardenButtons = new Button[MAXSLOTS];

		// create layout manager for the gardenplots
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(65, 60, 32, 0));
		grid.setAlignment(Pos.BOTTOM_RIGHT);

		//create garden plot buttons and attach handlers
		int button = 0;
		for (int row = 0; row < 5; ++row) {
			for (int column = 0; column < 9; ++column) {
				for (int i = 0; i < MAXSLOTS; ++i) {
					gardenButtons[i] = new Button(row + ","+ column);	//Reference to buttons of their row and column
					gardenButtons[i].setStyle("-fx-background-color: transparent;");//sets garden slots to be transparent
					gardenButtons[i].setFont(new Font(0));//sets garden slot button reference to be 'invisible'
					gardenButtons[i].setPrefSize(100,  106);	//set button size
					gardenButtons[i].setOnAction(new GardenButtonHandler(aPlayer, aGame));
				}
				//add each created button to the gridpane
				grid.add(gardenButtons[button], column, row);
				++button;
			}
		}
		//add firstrow (plant buttons) and secondrow (gardenplot buttons) to root
		root.getChildren().add(box);
		root.getChildren().add(grid);

		return root;
	}
	
	
	


	public Rectangle getHome() {
		return home;
	}



	public void setHome(Rectangle home) {
		this.home = home;
	}



	

	public static Rectangle getBounds(ImageView z) {
		return new Rectangle( z.getLayoutX(), z.getLayoutY(), 80, 100);
	}

	public void checkPlantCollision(Plant p, Zombie z) {
		
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
	
public void checkBulletCollision(Plant p, Zombie z) {
		
		Timer timer = new Timer();
 		timer.schedule(new TimerTask() {
 		        @Override
 		        public void run() {
 		            Platform.runLater(new Runnable() {
 		                @Override
 		                public void run() {
 		                	if (p.getBulletRect().getBoundsInParent().intersects(z.getRect().getBoundsInParent())){
 		                		 System.out.println("Zombie Hit");
 		                		p.setBullet(null);
 		                	p.setBulletRect(null);
 		                	 fullImage.getChildren().removeAll(p.getBullet(), p.getBulletRect());
 		                	 /*if (p.isFreeze()) {
 		                	z.setStopZombie(true);
 		                	 } */
 				              timer.cancel();
		                       timer.purge();

 				}

 		                	}


 		            });

 		        }
 		    }, 0, 10);
}
	public void checkAll(Game game, Level level) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@SuppressWarnings("static-access")
			@Override
            public void run() {
				
/*				for (int j = 0; j<9; j++) {
					try {
						if (game.getPlant(3, j).getType().equals("Wallnut")) {
							for (int z = 0; z < game.getZombieRow(4).size(); z++) {
								//System.out.println(game.getPlant(3, j).getColumn() + ",,," + game.getPlant(3, j).getRow());
								//System.out.println(game.getPlant(3, j).getType());
								//System.out.println(game.getPlant(3, j).getColumn() + "," + game.getZombieRow(4).get(z).getColumn());
								if (game.getPlant(3, j).getColumn() == game.getZombieRow(4).get(z).getColumn()){
						    		 System.out.println("Colliding");
						    		game.getZombieRow(4).get(z).setStopZombie(true);
								}
							}
						}
					}  catch (Exception e) {
						System.out.println("Exception");
					}
				}
*/				//System.out.println("Checked");
          	for(int i = 0; i<5; i++) {
            	    for(int j = 0; j<9; j++) {         	    	
            	        try {
            	        	Plant p = game.getPlant(i, j);
							if (p.getType().equals( "Wallnut")
								|| p.getType().equals( "PeaShooter")
								|| p.getType().equals("Frozen PeaShooter")
								|| p.getType().equals("Sunflower")
								|| p.getType().equals("Potato Mine")) {
								if (game.getZombieRow(i+1) != null) {
									for (int k = 0; k < game.getZombieRow(i+1).size(); k++) {
										Zombie z = game.getZombieRow(i+1).get(k);
										if (p.getColumn() == z.getColumn()){
											z.setStopZombie(true);
											if (p.getType().equals("Potato Mine")) {
												z.loseHealth(p.getAttack());
												game.removeZombie(i+1, k);
												//remove PlantImage and ZombieImage
											} else {
			 		                		//System.out.println("Colliding");
			 		                		p.loseHealth(z.getAttack());
			 		                		if (p.getHealth() <= 0) {
			 		                			GardenScene.fullImage.getChildren().remove(p.getPlantImage());
			 		                			game.resetPlot(p);
			 		                		}
			 		                		}
										}
									}
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            	        

            	    }
            	}


	}
		    }, 0, 100);
	}
}
