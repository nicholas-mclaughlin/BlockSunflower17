
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class GardenScene extends BaseScene {

	private Button[] gardenslots;
	private Button[] plantbuttons;
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
		Game game = new Game(player);

		//adding the garden first so it doesn't interfere with the clickable buttons
		root.getChildren().add(Garden);
		root.getChildren().add(gardenButtons(game.getPlayer()));
		
		Group fullImage = new Group(root, spawnZombie());

		Scene scene = new Scene(fullImage, LENGTH, WIDTH);
		setScene(scene);
		display();

	}

	public Node gardenButtons(Player aPlayer) throws Exception{

		VBox root = new VBox();

		//setup plants buttons in first row of VBox layout
		HBox box = new HBox();
		plantbuttons = new Button[5];
		int theButton = 0;
		for (int column = 0; column <5; ++column){
			//imports and adds each plant button
			ImageView plant = new ImageView(new Image(new FileInputStream("Plant"+ column + ".jpg")));
			plantbuttons[column] = new Button("p"+ column, plant);
			plantbuttons[column].setFont(new Font(0));
			box.getChildren().add(column, plantbuttons[theButton]);
			theButton++;
		}
        plantbuttons[0].setOnAction(new PlantButtonHandler(plantbuttons[0]));
        plantbuttons[1].setOnAction(new PlantButtonHandler(plantbuttons[1]));
        plantbuttons[2].setOnAction(new PlantButtonHandler(plantbuttons[2]));
        plantbuttons[3].setOnAction(new PlantButtonHandler(plantbuttons[3]));
        plantbuttons[4].setOnAction(new PlantButtonHandler(plantbuttons[4]));

		//create empty button array for garden buttons
		gardenslots = new Button[MAXSLOTS];	//number of elements must be stated

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
					gardenslots[i] = new Button(column + ","+ row);	//Reference to buttons of their row and column
					gardenslots[i].setStyle("-fx-background-color: transparent;");//sets garden slots to be transparent
					gardenslots[i].setFont(new Font(0));//sets garden slot button reference to be 'invisible' 
					gardenslots[i].setPrefSize(100,  106);	//set button size

				}
				//adding each created button to the gridpane
				grid.add(gardenslots[button], column, row);
				++button;
			}
		}
		
		//gardenslots[0].setOnAction(new GardenButtonHandler(gardenslots[0]));

		//add firstrow (plantwidgets) and secondrow (gardenslots) to root
		root.getChildren().add(box);
		root.getChildren().add(grid);

		return root;
	}

}
