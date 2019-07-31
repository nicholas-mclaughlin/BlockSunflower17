//package handlers;
//import drivers.Session;
//import gui.GardenScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * StartButtonHandler is the button handler for the first scene's start button.
 * It redirects player to the game once "start" button is clicked.
 *
 */
public class StartButtonHandler implements EventHandler<ActionEvent> {
	//This gives access to the session.
	Session session;
	
	public StartButtonHandler(Session session) {
		this.session = session;
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
		GardenScene scene = new GardenScene(session);
		try {
			scene.setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
