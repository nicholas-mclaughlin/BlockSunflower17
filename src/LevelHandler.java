package handlers;
import drivers.Session;
import gui.GardenScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * StartButtonHandler is the button handler for the first scene's start button.
 * It redirects player to the game once "start" button is clicked.
 *
 */
public class LevelHandler implements EventHandler<ActionEvent> {
	//This gives access to the session.
	Session session;
	private int levelNum;
	
	public LevelHandler(Session session, int levelNum) {
		this.session = session;
		this.levelNum = levelNum;
	}
	
	/**
	 * Override javafx default handler.
	 * This method will create a new GardenScene of the same session and sets it up.
	 * 
	 * @param ActionEvent	event is the case when a (start) button is clicked by the user.
	 */
	@Override
	public void handle(ActionEvent event) {
		
		//Create the second scene and set it up
		GardenScene scene = new GardenScene(session, levelNum);
		try {
			scene.setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}