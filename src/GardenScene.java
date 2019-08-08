//package gui;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.io.File;
//import handlers.SunButtonHandler;
import javafx.application.Platform;
//import drivers.Session;
//import handlers.PlantButtonHandler;
//import handlers.GardenButtonHandler;
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
import javafx.scene.text.Font;
//import logic.Player;
//import logic.Game;
//import logic.Sun;

/**
 * GardenScene class is an extension of BaseScene.
 * It is the second scene to appear after the 'start' button
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
	/**
	 * This root will serve as the root of the plant and gardenplot buttons and
	 * as well as the background image. 
	 * It is static to be able to be able to update the fullImage when plants are planted. 
	 */
	static StackPane root = new StackPane();
	/**
	 * Using a pane as a root since it allows the zombies and unimplemented suns to be positioned anywhere. 
	 * It is static so it can be changed, adding the suns and plant image gifs, through the garden button event handlers.
	 */
	public static Pane fullImage = new Pane(root);

	/**
	 * GardenScene constructors. This passes a session to the parent (BaseScene).
	 * @param aSession	session to be passed
	 */
	public GardenScene(Session aSession, int levelNum) {
		super(aSession);
		this.levelNum = levelNum;
	}

	/**
	 * Override abstract parent's (BaseScene) method to setup scene
	 * (actual drawing in window).
	 */
	@Override
	public void setup() throws Exception{

	String grasswalk = "MenuImages//grasswalk.mp3";
	Media hit = new Media(new File(grasswalk).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	mediaPlayer.play();


	/**
	 * Importing the garden image and setting its size to fit the buttons and scene.
	 * 1275 are the length of pixels a bit bigger than the window.	
	 * 800 are the pixels are the height of pixels a bit bigger than the window.
	 */
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
	GameCharacter[][] gardenPlot = new GameCharacter[5][9];
	Game game = new Game(player, gardenPlot);

	/**
	 * Setting up the stacks in the stackpane/root
	 * Garden (image) is the first stack so it wouldn't interfere with clickable buttons in front.
	 * gardenButtons is a node that takes a player and a game as a parameter.
	 * gardenButtons is where the user interacts with the window to play
	 * the game.
	 */
	root.getChildren().add(Garden);
	root.getChildren().add(gardenButtons(game.getPlayer(), game));

	/*//creating the sun and for it to appear randomly on screen
	ImageView sunGIF = new ImageView(new Image(new FileInputStream("sun.gif")));
	Button sunGif = new Button("", sunGIF);
	sunGif.setStyle("-fx-background-color: transparent;");
	*/

	//the time for the first sun to appear in milliseconds
		int timeBetweenSuns = 5000;
		//adds the suns in a for loop
		for (int i = 0; i<=30; i++) {
			Button sunButton = new Sun().getSunButton();
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

	/**
	 * Set and display scene
	 * setScene(Scene) and display() are methods from BaseScene class
	 */
	Level level = new Level(this.levelNum); //Can be Level1, Level2, or Level3
	int counter = 0;
	for (Zombie z : level.getZombies()) {
		fullImage.getChildren().add(z.newZombieImage());
		z.zombieTracker();
	}
	Scene scene = new Scene(fullImage, LENGTH, WIDTH);
	setScene(scene);
	display();


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

		/**
		 * Creates the sun counter as static to be able to call .setText() on it and update the money 
		 * with aPlayer.getMoney()
		 * @param Player	aPlayer is the player of the game 
		 * @return returns the sunCounter
		 * @throws Exception
		 */
		public static Button getSunCounter(Player aPlayer) throws Exception {
			sunCounter.setText("  " + aPlayer.getMoney());
			sunCounter.setStyle("-fx-background-image: url('/characters/pvzsun.png')");
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

		/**
		 * This root is the inner root within the scene.
		 * It is part of the only node within the scene and creates a VBox.
		 */
		VBox root = new VBox();

		/**
		 * This box will be the layout manager for the plant buttons.
		 * The plant buttons will be the first row in the root/VBox.
		 */
		HBox box = new HBox();

		/**
		 * Loop created instead of manually creating buttons.
		 * It will import and add each plant button up to 5 times
		 * Plants will be labeled as p0 to p4 and the respective plants in the eventhandlers are setup to
		 * match the order of the buttons.
		 * Eventhandlers will also be attached to these buttons.
		 */
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

		/**
		 * Create an empty array for the gardenplot buttons
		 * @param MAXSLOTS	refers to the constant variable MAXSLOTS which
		 * 					gives the max amount of buttons for the grid.
		 */
		gardenButtons = new Button[MAXSLOTS];

		/**
		 * This grid will be the layout manager for the gardenplots aligned to adjust and fir the window.
		 * This will be the second row of the root/VBox.
		 */
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(65, 60, 32, 0));
		grid.setAlignment(Pos.BOTTOM_RIGHT);

		/**
		 * Loop created instead of manually creating 45 buttons.
		 * It labels and add every button into a nestled loop to have
		 * the garden buttons in the gui fixed to preferred size and font (of labels).
		 * Eventhandlers will be attached to every button within the loop.
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
				//add each created button to the gridpane
				grid.add(gardenButtons[button], column, row);
				++button;
			}
		}
		//add firstrow (plantwidgets) and secondrow (gardenplot) to root
		root.getChildren().add(box);
		root.getChildren().add(grid);

		return root;
	}

}
