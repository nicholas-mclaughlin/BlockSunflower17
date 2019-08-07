//package gui;
//import drivers.Session;
//import handlers.LevelHandler;
import java.io.FileInputStream;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Menu class is an extension of BaseScene.
 * It is the first scene to appear once the application is run.
 *
 */
public class Menu extends BaseScene {

	/**
	 * Menu constructors. This passes a session to the parent (BaseScene).
	 * @param aSession	session to be passed
	 */
	public Menu(Session aSession) {
		super(aSession);
	}

	/**
	 * Override abstract parent's (BaseScene) method to setup and display scene
	 * (actual drawing in window).
	 */
	@Override
	public void setup() throws Exception {
		/**
		 * Create a start button that will launch the second scene (gardenScene)
		 * once clicked.
		 */
		Button level1 = new Button("", new ImageView(new Image(new FileInputStream("pvzlvl1.PNG"))));
		Button level2 = new Button("", new ImageView(new Image(new FileInputStream("pvzlvl2.PNG"))));
		Button level3 = new Button("", new ImageView(new Image(new FileInputStream("pvzlvl3.PNG"))));
		level1.setPrefSize(550, 100);
		level2.setPrefSize(550, 100);
		level3.setPrefSize(550, 100);
		level1.setStyle("-fx-background-color: transparent;");
		level2.setStyle("-fx-background-color: transparent;");
		level3.setStyle("-fx-background-color: transparent;");
//		Button quit = new Button("quit");	//quits
		
		//Add buttons to vbox
		VBox box = new VBox();
		box.getChildren().addAll(level1, level2, level3);
//		box.getChildren().add(quit);
		box.setAlignment(Pos.CENTER);
		
/*		BtnQuitHandler qHandler = new BtnQuitHandler();
		quit.setOnAction(qHandler);
*/		
		// Attach start button handler to start button.
		level1.setOnAction(new LevelHandler(getSession(), 1));
		level2.setOnAction(new LevelHandler(getSession(), 2));
		level3.setOnAction(new LevelHandler(getSession(), 3));
		
		ImageView background = new ImageView(new Image(new FileInputStream("PvZStreet.JPEG")));
		background.setFitHeight(720);
		background.setFitWidth(1220);
		
		//Add hbox with the button/s to the pane
		StackPane pane = new StackPane();
     		pane.getChildren().add(background);
		pane.getChildren().add(box);
		
		//Set the scene with preferred dimensions.
		setScene(new Scene(pane, 1220, 720));
		display();
	}

}
