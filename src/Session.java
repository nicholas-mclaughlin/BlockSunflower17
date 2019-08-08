import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//This session class will contain a main method.
public class Session extends Application {
	
	//Initialize stage for Application Structure.
	private Stage stage;

	//Sets scene to be initialized in stage or upon running the application.
	public void setScene(Scene aScene) {
		stage.setScene(aScene);
	}

	//Override start() method in Application.
	@Override
	public void start(Stage aStage) throws Exception {
		this.stage = aStage;
		stage.setTitle("PvZ");
		stage.setResizable(false); //Window will not be resizable; images will not be moved
		stage.show();
		
		//Create new first scene object upon running application.
		Menu scene = new Menu(this);
		scene.setup(); //setup scene for stage (method is in class gardenscene)
	}

	//launch application
	public static void main(String[] args) {
		launch(args);
	}

}
