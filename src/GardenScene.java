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
	
	static StackPane root = new StackPane();
	public static Pane fullImage = new Pane(root);

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
	MediaPlayer mediaPlayer = new MediaPlayer(hit);
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
	GameCharacter[][] gardenPlot = new GameCharacter[5][9];
	Game game = new Game(player, gardenPlot);

	//setup stacks in stackpane
	root.getChildren().add(Garden); //first stack
	root.getChildren().add(gardenButtons(game.getPlayer(), game)); //second stack

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

	//level of the game (1, 2, or 3)
	Level level = new Level(this.levelNum);
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

		//creates the sunCounter as static to be able to use .setText and change the money displayed
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

}
