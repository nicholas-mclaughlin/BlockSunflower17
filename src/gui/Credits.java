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

public class Credits extends BaseScene {
	
	StackPane scene = new StackPane();

	public Credits(Session aSession) {
		super(aSession);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setup() throws Exception {
		
		ImageView background = new ImageView(new Image(new FileInputStream("MenuImages//group17.PNG")));
		background.setFitHeight(720);
		background.setFitWidth(1220);
	
		
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
