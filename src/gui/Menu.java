package gui;

import drivers.Session;
import handlers.*;

import java.io.FileInputStream;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 *  Menu is the second scene to appear once the 'play' button from homepage is clicked.
 *  It contains level buttons that will launch the third scene (gardenScene)
 */
public class Menu extends BaseScene {

	//constructor
	public Menu(Session aSession) {
		super(aSession);
	}


	@Override
	public void setup() throws Exception {
		
		
		Button level1 = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzlvl1.PNG"))));
		Button level2 = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzlvl2.PNG"))));
		Button level3 = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzlvl3.PNG"))));
		Button back = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzback.PNG"))));
		level1.setPrefSize(550, 100);
		level2.setPrefSize(550, 100);
		level3.setPrefSize(550, 100);
		back.setPrefSize(180, 80);
		level1.setStyle("-fx-background-color: transparent;");
		level2.setStyle("-fx-background-color: transparent;");
		level3.setStyle("-fx-background-color: transparent;");
		back.setStyle("-fx-background-color: transparent;");

		//Add buttons to vbox
		VBox box = new VBox();
		box.getChildren().addAll(level1, level2, level3, back);
		box.setAlignment(Pos.CENTER);


		/*
		 *  Attach button handlers to buttons.
		 */
		level1.setOnAction(new LevelHandler(getSession(), 1));
		level2.setOnAction(new LevelHandler(getSession(), 2));
		level3.setOnAction(new LevelHandler(getSession(), 3));
		back.setOnAction(new BackHandler(getSession(), "Homepage"));

		//add background image
		ImageView background = new ImageView(new Image(new FileInputStream("MenuImages//PvZStreet.jpeg")));
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
