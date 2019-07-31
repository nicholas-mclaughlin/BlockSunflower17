//package gui;
//import drivers.Session;
//import handlers.StartButtonHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

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
		Button start = new Button("start");
//		Button quit = new Button("quit");	//quits
		
		//Add buttons to hbox
		HBox box = new HBox();
		box.getChildren().add(start);
//		box.getChildren().add(quit);
		box.setAlignment(Pos.CENTER);
		
/*		BtnQuitHandler qHandler = new BtnQuitHandler();
		quit.setOnAction(qHandler);
*/		
		// Attach start button handler to start button.
		StartButtonHandler sHandler = new StartButtonHandler(getSession());
		start.setOnAction(sHandler);
		
		//Add hbox with the button/s to pane
		StackPane pane = new StackPane();
		pane.getChildren().add(box);
		
		//Set the scene with preferred dimensions.
		setScene(new Scene(pane, 600, 500));
		display();
	}

}
