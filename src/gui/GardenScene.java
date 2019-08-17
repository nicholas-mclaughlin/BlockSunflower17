package gui;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import drivers.Session;
import handlers.*;
import java.io.File;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import logic.Game;
import logic.Level;
import logic.Plant;
import logic.Player;
import logic.Zombie;

/**
 * GardenScene is the second scene to appear after the 'start' button
 * is clicked on the first scene (Menu class).
 * This sets up main scene/GUI.
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
	private static Button sunCounter = new Button();
	private static MediaPlayer mediaPlayer;
	static StackPane root = new StackPane(); //Has all default visuals
	private static Pane fullImage = new Pane(root); //Displays everything

	private static Button errorMessage = new Button("Buy a plant first!");
	private boolean gameOver = false;
	private static Button gameOverMessage = new Button("");
	private static int deathCounter = 0; /*A counter that goes up everytime a zombie is killed. Once it hits the num of zombies
											in the level. Game displays "you won". */


	public GardenScene(Session aSession, int levelNum) {
		super(aSession);
		this.levelNum = levelNum;
	}


	@Override
	public void setup() throws Exception{

	String grasswalk = "MenuImages//grasswalk.mp3";
	Media hit = new Media(new File(grasswalk).toURI().toString());
	mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	mediaPlayer.play();


	/*
	 * set up garden image and garden image size to fit the buttons and scene
	 */
	ImageView Garden = new ImageView(new Image(new FileInputStream("PlantImages//Garden.PNG")));
	Garden.setFitHeight(800);
	Garden.setFitWidth(1275);

	/**
	 * Initialize game and arguments needed for game
	 * @param Player	player of the session being run
	 * @param Plant[][]	gardenPlot is the logic version of the gardenplot buttons;
	 * 			it keeps track of where a plant is.
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

	/*
	 * Setup stacks in stackpane
	 */
	root.getChildren().add(Garden); //first stack
	root.getChildren().add(gardenButtons(game.getPlayer(), game)); //second stack


	/*
	 * Setup the random suns that'll appear while the game is
	 * being played.
	 */
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
		fullImage.getChildren().addAll(z.getZombieImage());
		game.zombieTracker(z);

	}


	/*
	 * 	Creates the error message that the player has to buy a plant before clicking on the garden buttons
	 *  adds it to the scene but sets it up to not be visible
	 */
	fullImage.getChildren().add(errorMessage);
	errorMessage.setStyle("-fx-opacity: 0.0;");



	Scene scene = new Scene(fullImage, LENGTH, WIDTH);
	setScene(scene);
	display();

	checkAll(game, level);

	}


	/*
	 * creates random X and Y positions for the suns to appear in and returns these values
	 */
		public int generateRandomY() {
			int randomY = (int)(Math.random() * (700));
			return randomY;
		}
		public int generateRandomX() {
			int randomX = (int)(Math.random()*(1220));
			return randomX;
		}

		/*
		 * creates the sunCounter as static to be able to use .setText and change the money displayed
		 */
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
	 * @param Game	aGame is the game that was run depending on which level was chosen
	 * @return	returns second stack; buttons
	 * @throws Exception
	 */
	public Node gardenButtons(Player aPlayer, Game aGame) throws Exception{

		/*
		 * root is the inner root within the scene.
		 * box will be the layout manager for the plant buttons.
		 */
		VBox root = new VBox();
		HBox box = new HBox();

		/*
		 * create plant buttons and attach handlers.
		 * made plant buttons transparent so the visual of 'clicking' them isn't seen when the player did not buy a plant
		 */
		for (int column = 0; column <5; ++column){
			ImageView plant = new ImageView(new Image(new FileInputStream("PlantImages//Plant"+ column + ".jpg")));
			Button plantbuttons = new Button("p"+ column, plant);
			plantbuttons.setFont(new Font(0));
			plantbuttons.setStyle("-fx-background-color: transparent;");
			plantbuttons.setOnAction(new PlantButtonHandler(aPlayer));
			box.getChildren().add(column, plantbuttons);
		}


		/**
		 * Importing the sun image and setting the counter with the getMoney() form the player class that controls changes in money.
		 * adds the sunCounter to the plant buttons box
		 */		
		box.getChildren().add(getSunCounter(aPlayer));

		//create empty array for garden buttons
		gardenButtons = new Button[MAXSLOTS];

		// create layout manager for the gardenplots
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(65, 60, 32, 0));
		grid.setAlignment(Pos.BOTTOM_RIGHT);

		/*
		 * create garden plot buttons and attach handlers
		 * add each created button to the gridpane
		 */
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
				
				grid.add(gardenButtons[button], column, row);
				++button;
			}
		}
		//add firstrow (plant buttons) and secondrow (gardenplot buttons) to root
		root.getChildren().add(box);
		root.getChildren().add(grid);

		return root;
	}

	/*
	 * creates a gameovermessage saying "you win".
	 * makes background transparent.
	 */
	public void youWin() {
		System.out.println("YOU WIN");
		GardenScene.gameOverMessage = new Button("YOU WIN");
		GardenScene.fullImage.getChildren().add(GardenScene.gameOverMessage);
		GardenScene.gameOverMessage.setStyle("-fx-font-size: 75; -fx-background-color: transparent; -fx-font-weight: bold;");
		GardenScene.gameOverMessage.setDisable(false);

		GardenScene.gameOverMessage.setLayoutY(0);
		GardenScene.gameOverMessage.setLayoutX(0);
		GardenScene.gameOverMessage.setPrefSize(1220,720);
	}


	/*
	 * removes the image of a plant and replaces it with an explosion gif
	 * for a certain amount of time and then removes the image again
	 * 
	 * @param Plant p is an object Plant that will 'explode' visually
	 */
	public void potatoExplosion(Plant p) throws FileNotFoundException {
		GardenScene.fullImage.getChildren().remove(p.getPlantImage());
		 p.setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//boom.gif"))));
		 p.getPlantImage().setLayoutX(p.getxPosition() - 5);
		 p.getPlantImage().setLayoutY(p.getyPosition() - 15);
		 p.getPlantImage().setFitHeight(95);
		 p.getPlantImage().setFitWidth(65);
		 GardenScene.fullImage.getChildren().add(p.getPlantImage());
		 Timer timer2 = new Timer();
			timer2.schedule(new TimerTask() {
			        @Override
			        public void run() {
			            Platform.runLater(new Runnable() {
			                @Override
			                public void run() {
			                	 GardenScene.fullImage.getChildren().remove(p.getPlantImage());
			                }
			            });
			        }
			    }, 800);
	}
	
	
	/*
	 * removes the image of a plant
	 * if plant type is Sunflower, sunflowers' sun buttons get disabled
	 * if plant type is PeaShooter or Frozen PeaShooter, bullets get removed
	 * 
	 * @param Plant p is an object Plant that will 'explode' visually
	 */
	public void plantRemoval(Plant p) {
		GardenScene.fullImage.getChildren().remove(p.getPlantImage());
 		if (p.getType().equals("Sunflower")) {
 			Plant.setSunflowerStillAlive(false);
 			}
 		if (p.getType().equals("PeaShooter") || p.getType().equals("Frozen PeaShooter")) {
 			p.setHasBullet(false);
 			GardenScene.fullImage.getChildren().remove(p.getBullet());
 			}
	}
	
	/*
	 * Main method that runs in a timer while the game is not over.
	 * It checks multiple things at the same time.
	 * --what plant is on a coordinate and is a zombie on the same row OR column as it is
	 * --did a zombie die, if yes, add 1 to the deathcounter
	 * --has the deathcounter reached the same amount of zombies programmed to deploy on the game
	 * 
	 * @param Game   game is the game that was run depending on which level was chosen
	 * @param Level   contains the data of zombies (how many and which row it enter from)
	 * 				  to be deployed depending on which level was chosen
	 */
	public void checkAll(Game game, Level level) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		@SuppressWarnings("static-access")
		@Override
        public void run() {
			Platform.runLater(new Runnable() {
            @Override
            public void run() {

          	for(int i = 0; i<5; i++) {
        	    for(int j = 0; j<9; j++) {
        	        try {
        	        	Plant p = game.getPlant(i, j);
        	        	p.setImage();
        	        	p.setBulletImage();


						if (p.getType().equals( "Wallnut")
							|| p.getType().equals( "PeaShooter")
							|| p.getType().equals("Frozen PeaShooter")
							|| p.getType().equals("Sunflower")
							|| p.getType().equals("Potato Mine")) {

							// because plant rows go by 0 to 4 while zombie rows go by 1 to 5
							// 1 has to be added to i (when comparing plant and zombie rows)
							if (game.getZombieRow(i+1) != null) {
								for (int k = 0; k < game.getZombieRow(i+1).size(); k++) {
									Zombie z = game.getZombieRow(i+1).get(k);

									if ((p.getType().equals("PeaShooter") || p.getType().equals("Frozen PeaShooter"))
											&& (p.getRow()+1) == z.getRow()) {

										if ((p.getBulletXPosition() + 3) >= z.getPosition()
												&& (p.getBulletXPosition() - 3) <= z.getPosition()) {
											z.loseHealth(p.getAttack());
											p.setBulletXPosition(10000); //Does this so that bullet won't also affect zombie behind it
											fullImage.getChildren().remove(p.getBullet());


											if (p.isFreeze() && z.isFrozen() == false) {
												z.setSpeed(0.12);
												z.setFrozen(true);
											}
										}
									}

									if (p.getColumn() == z.getColumn() && p.isNotDestroyed()) {
										z.setStopZombie(true);

										if (p.getType().equals("Potato Mine")) {
											z.loseHealth(p.getAttack());
											potatoExplosion(p);
											p.setNotDestroyed(false);
		 		                			game.resetPlot(p);
										}	else {
											
		 		                		p.loseHealth(z.getAttack());

			 		                	if (p.getPlantImage() != null && p.getHealth() < 0 && p.isNotDestroyed()) {
			 		                		z.startZombie();
			 		                		plantRemoval(p);
			 		                		p.setNotDestroyed(false);
			 		                		game.resetPlot(p);

			 		                		}
		 		                		}
									}
									if (p.getType().equals("Wallnut") && p.getHealth() <= 5000 && p.isNewImageSet() == false) {
	 		                			GardenScene.fullImage.getChildren().remove(p.getPlantImage());
	 		                			p.setPlantImage(new ImageView(new Image(new FileInputStream("PlantImages//walnut_half_life.gif"))));
	 		                			p.setHasImage(false);
	 		                			p.setNewImageSet(true);
	 		                		}

									if (z.getHealth() <= 0) {
										GardenScene.fullImage.getChildren().remove(z.getZombieImage());
										game.removeZombie(i+1, k);
										deathCounter += 1;
									}
								}

							}
							else {
								for (int k = 0; k < game.getZombieRow(i+1).size(); k++) {
									Zombie z = game.getZombieRow(i+1).get(k);
									if (p.getColumn() == z.getColumn()){
										z.setStopZombie(false);
									}
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
        	    }
        	}
				if (deathCounter == level.getZombies().size()) {
					youWin();
					timer.cancel();
					timer.purge();

				}

                }});
			}
		    }, 1, 10);
		}



	public static Button getSunCounter() {
		return sunCounter;
	}



	public static void setSunCounter(Button sunCounter) {
		GardenScene.sunCounter = sunCounter;
	}



	public static Pane getFullImage() {
		return fullImage;
	}



	public static void setFullImage(Pane fullImage) {
		GardenScene.fullImage = fullImage;
	}



	public static Button getGameOverMessage() {
		return gameOverMessage;
	}



	public static void setGameOverMessage(Button gameOverMessage) {
		GardenScene.gameOverMessage = gameOverMessage;
	}



	public static Button getErrorMessage() {
		return errorMessage;
	}



	public static void setErrorMessage(Button errorMessage) {
		GardenScene.errorMessage = errorMessage;
	}




}
