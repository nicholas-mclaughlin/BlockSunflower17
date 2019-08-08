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
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import java.io.File;

// Menu is the first scene to appear once the application is run.
public class Menu extends BaseScene {

	//private static MediaPlayer mediaPlayer;

	//constructor
	public Menu(Session aSession) {
		super(aSession);
	}

	//setup and display scene (actual drawing in window)
	@Override
	public void setup() throws Exception {
/*
		//initialize music (intro)
		String grasswalk = "MenuImages//intro.mp3";
		Media hit = new Media(new File(grasswalk).toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		//mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
*/
		//Create level buttons that will launch the second scene (gardenScene)
		Button level1 = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzlvl1.png"))));
		Button level2 = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzlvl2.png"))));
		Button level3 = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzlvl3.png"))));
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
