package gui;
import drivers.Session;
import handlers.StartButtonHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Menu extends BaseScene {

	public Menu(Session aSession) {
		super(aSession);
	}

	@Override
	public void setup() throws Exception {
		//Create buttons
		Button start = new Button("start");	//launches second scene
//		Button quit = new Button("quit");	//quits
		
		//Add buttons to hbox
		HBox box = new HBox();
		box.getChildren().add(start);
//		box.getChildren().add(quit);
		box.setAlignment(Pos.CENTER);
		
		//Attach eventhandler
/*		BtnQuitHandler qHandler = new BtnQuitHandler();
		quit.setOnAction(qHandler);
*/		
		StartButtonHandler sHandler = new StartButtonHandler(getSession());
		start.setOnAction(sHandler);
		
		//Add hbox to pane
		StackPane pane = new StackPane();
		pane.getChildren().add(box);
		
		//Set the scene
		setScene(new Scene(pane, 600, 500));
		display();
	}

}
