package gui;

import java.io.FileInputStream;

import drivers.Session;
import handlers.CreditsHandler;
import handlers.LevelHandler;
import handlers.PlayHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Homepage extends BaseScene {
	
	//private static MediaPlayer mediaPlayer;

	//constructor
	public Homepage(Session aSession) {
		super(aSession);
	}

	//setup and display scene (actual drawing in window)
	@Override
	public void setup() throws Exception {
		
		//initialize music in-game
/*		String grasswalk = "intro.mp3";
		Media hit = new Media(new File(grasswalk).toURI().toString());
		setMediaPlayer(new MediaPlayer(hit));
		getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
		getMediaPlayer().play();
*/
		//Create level buttons that will launch the second scene (gardenScene)
		Button play = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzplay.PNG"))));
		Button credits = new Button("", new ImageView(new Image(new FileInputStream("MenuImages//pvzcredits.PNG"))));
		//Button level3 = new Button("", new ImageView(new Image(new FileInputStream("pvzlvl3.PNG"))));
		play.setPrefSize(350, 100);
		credits.setPrefSize(250, 80);
		//level3.setPrefSize(550, 100);
		play.setStyle("-fx-background-color: transparent;");
		credits.setStyle("-fx-background-color: transparent;");
		//button.setStyle("-fx-background-color: transparent;");
		//level3.setStyle("-fx-background-color: transparent;");

		
		// Add buttons to vbox
		VBox box = new VBox();
		box.getChildren().addAll(play, credits);
		box.setAlignment(Pos.CENTER);
		
/*		BtnQuitHandler qHandler = new BtnQuitHandler();
		quit.setOnAction(qHandler);
*/		
		
	    // Attach start button handler to start button.
		play.setOnAction(new PlayHandler(getSession()));
		credits.setOnAction(new CreditsHandler(getSession()));
		
		//add background image
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
