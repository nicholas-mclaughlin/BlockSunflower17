//package gui;
import java.io.FileInputStream;
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

public class GardenScene extends BaseScene {

	private Button[] gardenslots;

	private final int MAXSLOTS = 45;
	private final int LENGTH = 1220;
	private final int WIDTH = 720;

	//constructor, pass Session inst variable to parent
	public GardenScene(Session aSession) {
		super(aSession);
	}

	//override abstract parent
	@Override
	public void setup() throws Exception{
	StackPane root = new StackPane();

	//creating garden image and garden image size to fit the buttons and scene
	ImageView Garden = new ImageView(new Image(new FileInputStream("Garden.PNG")));
	Garden.setFitHeight(800);
	Garden.setFitWidth(1275);

	//Initialize game
	Player player = new Player();
	String[][] gardenPlot = new String[5][9];
	Game game = new Game(player, gardenPlot);

	//adding the garden first so it doesn't interfere with the clickable buttons
	root.getChildren().add(Garden);
	root.getChildren().add(gardenButtons(game.getPlayer(), game));

	ImageView zombieImage = ZombiesGUI.movingZombie("Zombie"); //Example 

	Pane fullImage = new Pane(root, zombieImage);

	Scene scene = new Scene(fullImage, LENGTH, WIDTH);
	setScene(scene);
	display();

	}

	public Node gardenButtons(Player aPlayer, Game aGame) throws Exception{

		VBox root = new VBox();

		//setup plants buttons in first row of VBox layout
		HBox box = new HBox();

		for (int column = 0; column <5; ++column){
			//imports and adds each plant button
			ImageView plant = new ImageView(new Image(new FileInputStream("Plant"+ column + ".jpg")));
			Button plantbuttons = new Button("p"+ column, plant);
			plantbuttons.setFont(new Font(0));
			
			plantbuttons.setOnAction(new PlantButtonHandler(aPlayer));
			
			box.getChildren().add(column, plantbuttons);
		}
		//create sun counter 
		ImageView sun = new ImageView(new Image(new FileInputStream("sun.png")));
		Button sunCounter = new Button("Suns: " + aPlayer.getMoney(), sun);
													//may need to update this method so it updates 
		box.getChildren().add(sunCounter);
		
		/*
		//creating the sun and for it to appear randomly on screen
		ImageView sunGIF = new ImageView(new Image(new FileInputStream("sun.gif")));
		Button sunGif = new Button("", sunGIF);
		sunGif.setStyle("-fx-background-color: transparent;");
		*/
		
		//create empty button array for garden buttons
		gardenButtons = new Button[MAXSLOTS];	//number of elements must be stated

		//set up gridpane as second row of layout
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(18, 22, 32, 0));
		grid.setAlignment(Pos.BOTTOM_RIGHT);

		//create and add every button into array
		int button = 0;
		for (int row = 0; row < 5; ++row) {
			for (int column = 0; column < 9; ++column) {
				//add each button to the created button array
				for (int i = 0; i < MAXSLOTS; ++i) {
					gardenslots[i] = new Button(row + ","+ column);	//Reference to buttons of their row and column
					gardenslots[i].setStyle("-fx-background-color: transparent;");//sets garden slots to be transparent
					gardenslots[i].setFont(new Font(0));//sets garden slot button reference to be 'invisible' 
					gardenslots[i].setPrefSize(100,  106);	//set button size
					gardenslots[i].setOnAction(new GardenButtonHandler(aPlayer, aGame));
				}
				//adding each created button to the gridpane
				grid.add(gardenButtons[button], column, row);
				++button;
			}
		}

		//add firstrow (plantwidgets) and secondrow (gardenslots) to root
		root.getChildren().add(box);
		root.getChildren().add(grid);

		return root;
	}

}
