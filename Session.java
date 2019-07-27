import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//This is a driver class which will just contain a main method

public class Session extends Application {
	private Stage stage;

	//setter for application
	public void setScene(Scene aScene) {
		stage.setScene(aScene);
	}

	//override application method start()
	@Override
	public void start(Stage aStage) throws Exception {
		this.stage = aStage;
		stage.setTitle("PvZ");
		stage.setResizable(false);
		stage.show();

		//start button (scene) omitted for now

		//create the scene for gameplay
		GardenScene scene = new GardenScene(this);
		scene.setup(); //setup scene for stage (method is in class gardenscene)
	}

	//launch application
	public static void main(String[] args) {
		launch(args);
	}

}
