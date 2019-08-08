//package gui;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

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
import javafx.scene.text.Font;
//import logic.Game;
//import logic.Player;

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

	/**
	 * GardenScene constructors. This passes a session to the parent (BaseScene).
	 * @param aSession	session to be passed
	 */
	public GardenScene(Session aSession) {
		super(aSession);
	}

	/**
	 * Override abstract parent's (BaseScene) method to setup scene
	 * (actual drawing in window).
	 */
	@Override
	public void setup() throws Exception{
		
	/**
	 * This root will serve as the root of the plant and gardenplot buttons and
	 * as well as the background image.
	 */
	StackPane root = new StackPane();

	/**
	 * Importing the garden image and setting its size to fit the buttons and scene. 
	 * 1275 are the length of pixels a bit bigger than the window.		???
	 * 800 are the pixels are the height of pixels a bit bigger than the window.	???
	 */
	ImageView Garden = new ImageView(new Image(new FileInputStream("Garden.PNG")));
	Garden.setFitHeight(800);
	Garden.setFitWidth(1275);

	/**
	 * Initialize game and arguments needed for game
	 * @param Player	player of the session being run
	 * @param String[][]	gardenPlot is the logic version of the gardenplot buttons;
	 * 			it keeps track of where a plant and/or zombie is.
	 */
	Player player = new Player();
	String[][] gardenPlot = new String[5][9];
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
	
	/**
	 * Using a pane as a root since it allows the zombies and unimplemented suns to be positioned anywhere
	 */
	Pane fullImage = new Pane(root);
	
	/**
	 * Set and display scene
	 * setScene(Scene) and display() are methods from BaseScene class
	 */
	Scene scene = new Scene(fullImage, LENGTH, WIDTH);
	setScene(scene);
	display();
	//Just an example of the 4 zombies we can generate
	fullImage.getChildren().add(new Zombie("Cone Zombie").getZombieImage());
	fullImage.getChildren().add(new Zombie("Zombie").getZombieImage());
	fullImage.getChildren().add(new Zombie("Flag Zombie").getZombieImage());
	fullImage.getChildren().add(new Zombie("Football Zombie").getZombieImage());
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
			ImageView plant = new ImageView(new Image(new FileInputStream("Plant"+ column + ".jpg")));
			Button plantbuttons = new Button("p"+ column, plant);
			plantbuttons.setFont(new Font(0));
			
			plantbuttons.setOnAction(new PlantButtonHandler(aPlayer));
			
			box.getChildren().add(column, plantbuttons);
		}


		/**
		 * Importing the sun image and setting setting the counter with the getMoney() form the player class that controls changes in money.
		 */
		ImageView sun = new ImageView(new Image(new FileInputStream("sun.png")));
		Button sunCounter = new Button("Suns: " + aPlayer.getMoney(), sun);
		box.getChildren().add(sunCounter);
		
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
		grid.setPadding(new Insets(60, 65, 32, 0));
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
