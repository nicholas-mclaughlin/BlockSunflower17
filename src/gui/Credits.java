package gui;

import java.io.FileInputStream;

import drivers.Session;
import handlers.BackHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.Game;
import logic.Player;

/*
 * Credits scene has an image showing the credits scene and
 * a back button.
 * 
 * @throws Exception
 */

public class Credits extends BaseScene {
	
	StackPane scene = new StackPane();

	//constructor
	public Credits(Session aSession) {
		super(aSession);
	}

	//method to set up credits scene
	@Override
	public void setup() throws Exception {
		
		//set up the credits image and size of window
		ImageView background = new ImageView(new Image(new FileInputStream("MenuImages//group17.PNG")));
		background.setFitHeight(720);
		background.setFitWidth(1220);
	
		//button will be in a stackpane in order to be able to use setAlignment()
		StackPane button = new StackPane();
		
		Button back = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzback.PNG"))));
		back.setPrefSize(180, 80);
		back.setStyle("-fx-background-color: transparent;");
		back.setOnAction(new BackHandler(getSession(), "Homepage"));
		
		button.getChildren().add(back);
		button.setAlignment(Pos.BOTTOM_CENTER);

		scene.getChildren().addAll(background, button);
		
		//Set the scene with preferred dimensions.
		setScene(new Scene(scene, 1220, 720));
		display();
	}
}
